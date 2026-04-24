package com.apm.ThirdParties.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.ThirdParties.eu.bi.TPADao;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCTPADao implements TPADao  {

	Connection connection;
	
	public JDBCTPADao(Connection connection) {
		  this.connection=connection;
	}

	public ArrayList<ThirdParty> getTpaList(String fromdate,String todate,String searchtext,Pagination pagination,int access) {
		
		ArrayList<ThirdParty> list= new ArrayList<ThirdParty>();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		BedDao bedDao=new JDBCBedDao(connection);
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
				
		todate = todate + " 23:59:59";
		try {
			
			StringBuffer buffer =new StringBuffer();
			buffer.append("select apm_treatment_episode.id,apm_treatment_episode.clientid,apm_treatment_episode.ref_date,apm_treatment_episode.spendlimit,apm_treatment_episode.authcode,apm_treatment_episode.refenddate,apm_treatment_episode.ipdopd,apm_treatment_episode.invoicee,apm_treatment_episode.status,r_status,apm_treatment_episode.approved from apm_treatment_episode ");
			
			if(searchtext!=null){
				buffer.append("inner join apm_patient on apm_treatment_episode.clientid=apm_patient.id   ");
			}
			
			buffer.append("where apm_treatment_episode.lastmodified between '"+fromdate+"' and '"+todate+"' and payby='Third Party' ");
			if(searchtext!=null){
				
				buffer.append("and (apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('%"+searchtext+"') or apm_patient.abrivationid='"+searchtext+"'   ) ");
			}
			buffer.append(" and iscancel='0'");
			buffer.append("order by apm_treatment_episode.id desc ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				
				ThirdParty thirdParty= new ThirdParty();
				if(access==1){
						  String newipdabbr=ipdDAO.getIpdAbrivationIdsByClientid(rs.getInt(2));
						  thirdParty.setNewipdabbr(newipdabbr);
				}else{
				thirdParty.setId(rs.getInt(1));
				 thirdParty.setNewipdabbr(rs.getString(1));
				}
				thirdParty.setClientid(rs.getString(2));
				thirdParty.setRef_date(rs.getString(3));
				thirdParty.setSpendlimit(rs.getString(4));
				thirdParty.setAuthcode(rs.getString(5));
				thirdParty.setRefenddate(DateTimeUtils.getCommencingDate1(rs.getString(6)));
				String ipdopd= rs.getString(7);
				thirdParty.setThirdPartyName(rs.getString(8));
				thirdParty.setSubmitted(rs.getInt(9));
				thirdParty.setRstatus(rs.getInt(10));
				thirdParty.setApproved(rs.getInt(11));  
				Client client=clientDAO.getClientDetails(thirdParty.getClientid());
				ThirdParty party= client.getTpDetails();
				String tpname= party.getThirdPartyName();
				String company =party.getCompanyName();
				thirdParty.setCompanyName(company);
				thirdParty.setAbrivationid(client.getAbrivationid());
				String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
				thirdParty.setFullname(fullname);

				String age="";
				try {
				     age=DateTimeUtils.getAge(client.getDob());	
				} catch (Exception e) {
				}
				thirdParty.setAgegender(client.getGender()+"/"+age);
				
				int ipdid= getIpdOrNot(thirdParty.getId());
				if(ipdid>0){
					 //ipd
					Bed bed=bedDao.getEditIpdData(String.valueOf(ipdid));
					String bedid= bed.getBedid();
					String wardid =bed.getWardid();
					String bedname= ipdDAO.getIpdBedName(bedid);
					String wardname= ipdDAO.getIpdWardName(wardid);
					thirdParty.setBedname(bedname+" "+wardname);
					thirdParty.setCommencing(DateTimeUtils.getDBDate(bed.getAdmissiondate()));
					thirdParty.setIpdopd(String.valueOf(ipdid));
					thirdParty.setIpdid(ipdid);
				} else {
					 //opd
					NotAvailableSlot notAvailableSlot= getAppointmentData(thirdParty.getId());
					thirdParty.setCommencing(DateTimeUtils.getCommencingDate1(notAvailableSlot.getCommencing()));
					thirdParty.setIpdopd(String.valueOf(notAvailableSlot.getId()));
					thirdParty.setIpdid(0);
				}
			
				
				list.add(thirdParty);
				
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	private int getIpdOrNot(int treatmentEpisodeId) {
		
		int ipdid=0;
		try {
			
			String sql="select id from ipd_addmission_form where treatmentepisodeid="+treatmentEpisodeId+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				
				 ipdid= rs.getInt(1);
			}
			
		} catch (Exception e) {

				e.printStackTrace();
		}
		
		
		return ipdid;
	}
	
	
	public NotAvailableSlot getAppointmentData(int treatmentEpisodeId) {
		
		NotAvailableSlot notAvailableSlot= new NotAvailableSlot();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("select id,apmslotid,commencing,starttime from apm_available_slot where treatmentEpisodeId="+treatmentEpisodeId+" order by id desc limit 0,1  ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				   notAvailableSlot.setId(rs.getInt(1));
				   notAvailableSlot.setApmtSlotId(rs.getInt(2));
				   String commencing= rs.getString(3);
				   notAvailableSlot.setCommencing(commencing);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		return notAvailableSlot;
	}

	public int updateTreatmentEpisodetoSubmit(String treatmentEpisodeId) {

		int res= 0;
		try {
			
			String sql="update apm_treatment_episode set status=1 where id="+treatmentEpisodeId+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			res= ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int rejectTpaStatus(String treatmentepisodeid) {
		int res= 0;
		try {
			
			String sql="update apm_treatment_episode set r_status=1 where id="+treatmentepisodeid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			res= ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}
	public int acceptTpaStatus(String treatmentepisodeid) {
		int res= 0;
		try {
			
			String sql="update apm_treatment_episode set approved=1 where id="+treatmentepisodeid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			res= ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}
	
	public int cancelTpa(String id, String delete_reason) {
		int result = 0;
		  try {
		   String sql = "update apm_treatment_episode set iscancel=?,cancelnote=? where id="+id+"";
		   PreparedStatement ps = connection.prepareStatement(sql);
		   ps.setString(1, "1");
		   ps.setString(2, delete_reason);
		   result = ps.executeUpdate();
		  } catch (Exception e) {

		   e.printStackTrace();
		  }

		  return result;
	}
	public int getTotalTpaListCount(String fromdate,String todate,String searchtext) {
		int res=0;
		todate = todate + " 23:59:59";
		try {
		     
			StringBuffer buffer =new StringBuffer();
			buffer.append("select count(*) from apm_treatment_episode ");
			
			if(searchtext!=null){
				buffer.append("inner join apm_patient on apm_treatment_episode.clientid=apm_patient.id   ");
			}
			
			buffer.append("where apm_treatment_episode.lastmodified between '"+fromdate+"' and '"+todate+"' and payby='Third Party' ");
			if(searchtext!=null){
				
				buffer.append("and (apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('%"+searchtext+"') or apm_patient.abrivationid='"+searchtext+"'   ) ");
			}
			buffer.append(" and iscancel='0'");
			buffer.append("order by apm_treatment_episode.id desc ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				res=rs.getInt(1);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
