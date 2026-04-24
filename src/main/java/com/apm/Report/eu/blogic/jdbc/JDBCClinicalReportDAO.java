package com.apm.Report.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Report.eu.bi.ClinicalReportDAO;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;

public class JDBCClinicalReportDAO extends JDBCBaseDAO implements ClinicalReportDAO{
	public JDBCClinicalReportDAO(Connection connection){
		this.connection  = connection;
	}

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String practitionerId,String fromDate,String toDate,String orderby,String order) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		
		StringBuffer sql = new StringBuffer();
		
		toDate = toDate + " 23:59:59";
		
		if(practitionerId!=null){
			if(!practitionerId.equals("0")){
				sql.append("SELECT apm_treatment_episode.id,apm_treatment_episode.clientid,apm_treatment_episode.clientname,apm_treatment_episode.practitionerid,apm_treatment_episode.name,apm_treatment_episode.payby,apm_treatment_episode.sessions,apm_treatment_episode.lastmodified,apm_treatment_episode.invoicee,apm_treatment_episode.usedsession,abrivationid  ");
				sql.append("FROM apm_treatment_episode ");
				sql.append("inner join apm_patient on apm_patient.id = apm_treatment_episode.clientid ");
				sql.append("where apm_treatment_episode.practitionerid="+practitionerId+" and apm_treatment_episode.lastmodified between '"+fromDate+"' AND '"+toDate+"' ");
			}else{
				sql.append("SELECT apm_treatment_episode.id,apm_treatment_episode.clientid,apm_treatment_episode.clientname,apm_treatment_episode.practitionerid,apm_treatment_episode.name,apm_treatment_episode.payby,apm_treatment_episode.sessions,apm_treatment_episode.lastmodified,apm_treatment_episode.invoicee,apm_treatment_episode.usedsession ,abrivationid ");
				sql.append("FROM apm_treatment_episode ");
				sql.append("inner join apm_patient on apm_patient.id = apm_treatment_episode.clientid ");
				sql.append("where apm_treatment_episode.lastmodified between '"+fromDate+"' AND '"+toDate+"' ");
			}
		}else{
			sql.append("SELECT apm_treatment_episode.id,apm_treatment_episode.clientid,apm_treatment_episode.clientname,apm_treatment_episode.practitionerid,apm_treatment_episode.name,apm_treatment_episode.payby,apm_treatment_episode.sessions,apm_treatment_episode.lastmodified,apm_treatment_episode.invoicee,apm_treatment_episode.usedsession,abrivationid  ");
			sql.append("FROM apm_treatment_episode ");
			sql.append("inner join apm_patient on apm_patient.id = apm_treatment_episode.clientid ");
			sql.append("where apm_treatment_episode.lastmodified between '"+fromDate+"' AND '"+toDate+"' ");
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
				treatmentEpisode.setUsedSession(rs.getString(10));
				//  03 oct 2017 set abrivation id
				treatmentEpisode.setAbrivationid(rs.getString(11));
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

}
