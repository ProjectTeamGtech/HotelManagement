package com.apm.Emr.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Emr.eu.bi.NursingDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.DateTimeUtils;

public class JDBCNursingDAO implements NursingDAO{

	  Connection connection;
	
	  public JDBCNursingDAO(Connection connection) {
   
	       this.connection=connection;  
	  }

	public int saveParentNursing(Master master) {
		
		int result=0;
		try {
			
			String sql="insert into apm_client_parent_nursing (clientid, conditionid, practitioner_id, ipdid, notes, lastmodified,todate,givento) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, master.getClientid());
	        ps.setString(2, master.getConditionid());
	        ps.setString(3, master.getPractitionerid());
	        ps.setString(4, master.getIpdid());
	        ps.setString(5, master.getNotes());
	        ps.setString(6, master.getDatetime());
	        ps.setString(7, master.getTodate());
	        ps.setString(8, master.getGivenTo());
	        result=ps.executeUpdate();
	        if(result>0){
	        	
	        	ResultSet rs1=ps.getGeneratedKeys();
	        	while(rs1.next()){
	        		  
	        		result=rs1.getInt(1);
	        	}
	        	
	        }
	        
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}

	public int saveNusingData(int saveparent, Master master2) {

		int result=0;
		
		try {
			
			String sql="insert into apm_client_nursing(clientid, ipdid, conditionid, practitionerid, categoryid, taskname, frequency, notes, datetime, parentid,days) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, master2.getClientid());
			ps.setString(2, master2.getIpdid());
			ps.setString(3, master2.getConditionid());
			ps.setString(4, master2.getPractitionerid());
			ps.setString(5, master2.getNursingtype_id());
			ps.setString(6, master2.getTaskname());
			ps.setString(7, master2.getFrequency());
			//ps.setString(8, "");
			ps.setString(8, master2.getNotes());
			ps.setString(9, master2.getDatetime());
			ps.setInt(10, saveparent);
		  	ps.setInt(11, master2.getDays());
			result=ps.executeUpdate(); 
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Master> nusringCareListOfPatient(String ipdId) {
		ArrayList<Master> list=new ArrayList<Master>();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select * from apm_client_parent_nursing where ipdid='"+ipdId+"'");
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Master master= new Master();
				master.setId(rs.getInt("id"));
				String date=(rs.getString("lastmodified"));
				master.setName(DateTimeUtils.getCommencingDate1(date.split(" ")[0])+" "+date.split(" ")[1]);
				list.add(master);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> childListOfNursingCare(String parentid) {
		ArrayList<Master>list= new ArrayList<Master>();
		try {
			String sql=" select clientid, ipdid, conditionid, practitionerid, categoryid, taskname, frequency, notes, datetime, parentid,days from apm_client_nursing where  parentid='"+parentid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			while (rs.next()) {
				Master master= new Master();
				master.setClientid(rs.getString(1));
				master.setIpdid(rs.getString(2));
				master.setConditionid(rs.getString(3));
				master.setPractitionerid(rs.getString(4));
				master.setNursingtype_id(rs.getString(5));
				master.setTaskname(rs.getString(6));
				master.setFrequency(rs.getString(7));
				master.setDays(Integer.parseInt(rs.getString(11)));
				master.setDatetime(rs.getString(9));
				master.setNursingtype(rs.getString(10));
				master.setNotes(rs.getString(8));
				Master master2=masterDAO.getNursingCategory(rs.getString(5));
				master.setNursingtype(master2.getName());
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	  
	  
	
}
