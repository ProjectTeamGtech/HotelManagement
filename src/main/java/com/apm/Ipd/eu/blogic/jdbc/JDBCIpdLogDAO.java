package com.apm.Ipd.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.bi.IpdLogDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;

public class JDBCIpdLogDAO  extends JDBCBaseDAO implements IpdLogDAO {
	
	public JDBCIpdLogDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<Bed> getAdmissionlogDAO(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed>list = new ArrayList<Bed>();
		String sql = "select admissionid, lastmodified from ipd_admission_log where clientid="+clientId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Bed bed = new Bed();
				bed.setAddmissionid(rs.getString(1));
				if(rs.getString(2)!=null){
					bed.setAdmissiondate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					bed.setAdmissiondate("");
				}
			
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				String dischargedate = clientDAO.getIpdDischargeDate(rs.getString(1));
				if(dischargedate!=null){
					
					bed.setDischargeDate(dischargedate);
				}else{
					bed.setDischargeDate("");
				}
				
				
				list.add(bed);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Bed> getDischargeLog(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed>list = new ArrayList<Bed>();
		String sql = "select admissionid, lastmodified from ipd_discharge_log where clientid="+clientId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Bed bed = new Bed();
				bed.setAddmissionid(rs.getString(1));
				//bed.setAdmissiondate(rs.getString(2));
				if(rs.getString(2)!=null){
					bed.setAdmissiondate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					bed.setAdmissiondate("");
				}
				
				list.add(bed);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Bed> getBedChangeLogList(String clientId,String admissionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();
		String sql = "SELECT admissionid, wardid, bedid, selectedshiftdata,id FROM ipd_bed_change_log where clientid = "+clientId+" and admissionid ="+admissionid+"  order by id desc";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Bed bed = new Bed();
				bed.setAddmissionid(rs.getString(1));
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(rs.getString(2));
				bed.setWardname(wardname);
				
				String bedname = ipdDAO.getIpdBedName(rs.getString(3));
				bed.setBedname(bedname);
				
				if(rs.getString(4)!=null){
					bed.setDate(DateTimeUtils.getDBDate(rs.getString(4)));
				}else{
					bed.setDate("");
				}
				
				bed.setId(rs.getInt(5));
				
				list.add(bed);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public String getDischargeBedId(String admissionid) {

		String bedid="";
		
		try {
			String sql="SELECT bedid from ipd_discharge_log where admissionid="+admissionid+" and bedid>0";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 bedid=rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return bedid;
	}


	public boolean isBedChanged(String admissionid,String clientid) {

		try {
			String sql="SELECT id from ipd_bed_change_log where admissionid="+admissionid+" and clientid="+clientid+"";
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
	
	public String getDischargeDate(int admissionid) {

		String dischargedate="";
		
		try {
			String sql="SELECT lastmodified from ipd_discharge_log where admissionid="+admissionid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				dischargedate=rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return dischargedate;
	}

	public ArrayList<Client> getPatientEditLog(String clientId) {
		ArrayList<Client> arrayList = new ArrayList<Client>();
		try {
			String sql ="select id, pre_initial, curr_initial, pre_fname, curr_fname, pre_mname, curr_mname, "
					+ "pre_lname, curr_lname, pre_payee, curr_payee, datetime, userid, pre_dob, "
					+ "curr_dob from patient_edit_log where clientid="+clientId+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setPre_initial(rs.getString(2));
				client.setCurr_initial(rs.getString(3));
				client.setPre_fname(rs.getString(4));
				client.setCurr_fname(rs.getString(5));
				client.setPre_mname(rs.getString(6));
				client.setCurr_mname(rs.getString(7));
				client.setPre_lname(rs.getString(8));
				client.setCurr_lname(rs.getString(9));
				client.setPre_payee(rs.getString(10));
				client.setCurr_payee(rs.getString(11));
			    client.setDate(rs.getString(12));	
			    client.setUserid(rs.getString(13));
			    client.setPre_dob(rs.getString(14));
			    client.setCurr_dob(rs.getString(15));
			    arrayList.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Bed> getBedChangeLogListNew(String clientid, String admissionid) {

		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();
		String sql = "SELECT admissionid, wardid, bedid, selectedshiftdata,id FROM ipd_bed_change_log where clientid = "+clientid+" and admissionid ="+admissionid+"  order by id desc";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int previousbedid=0;
			while(rs.next()){
				if(previousbedid==rs.getInt(3)){
					continue;
				}
				previousbedid = rs.getInt(3);
				Bed bed = new Bed();
				bed.setAddmissionid(rs.getString(1));
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(rs.getString(2));
				bed.setWardname(wardname);
				
				String bedname = ipdDAO.getIpdBedName(rs.getString(3));
				bed.setBedname(bedname);
				
				if(rs.getString(4)!=null){
					bed.setDate(DateTimeUtils.getDBDate(rs.getString(4)));
				}else{
					bed.setDate("");
				}
				
				bed.setId(rs.getInt(5));
				
				list.add(bed);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	
	}

	@Override
	public Vector<Bed> getfakepatientBedLogList(String clientid, String admissionid,String dbname) {
		PreparedStatement preparedStatement = null;
		Vector<Bed> list = new Vector<Bed>();
		String sql = "SELECT admissionid, wardid, bedid, selectedshiftdata,id,commencing,lastmodified FROM "+dbname+".ipd_bed_change_log where clientid = "+clientid+" and admissionid ="+admissionid+"  order by id desc";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int previousbedid=0;
			while(rs.next()){
				if(previousbedid==rs.getInt(3)){
					continue;
				}
				previousbedid = rs.getInt(3);
				Bed bed = new Bed();
				bed.setAddmissionid(rs.getString(1));
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(rs.getString(2));
				bed.setWardname(wardname);
				
				String bedname = ipdDAO.getIpdBedName(rs.getString(3));
				bed.setBedname(bedname);
				
				if(rs.getString(4)!=null){
					bed.setDate(DateTimeUtils.getDBDate(rs.getString(4)));
				}else{
					bed.setDate("");
				}
				
				bed.setId(rs.getInt(5));
				bed.setCommencing(rs.getString(6));
				bed.setLastmodified(rs.getString(7));
				
				list.add(bed);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int updateBedlogdata(int fake_patientid, String newcommencing, String new_lastmodified,
			String new_selectedshiftdata, int bedlogid, int aipdid,int chrai_ipdid,String dbname) {
		
		int res =0;
		try {
			
			String sql="update "+dbname+".ipd_bed_change_log set clientid="+fake_patientid+",admissionid='"+chrai_ipdid+"',lastmodified='"+new_lastmodified+"',commencing='"+newcommencing+"',selectedshiftdata='"+new_selectedshiftdata+"'  where id='"+bedlogid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
		
	
	}

	@Override
	public Bed getbedlodDataByid(int id,String dbname) {
		PreparedStatement preparedStatement=null;
		Bed bed=new Bed();
           try {
			
       		String sql = "SELECT admissionid, wardid, bedid, selectedshiftdata,id,commencing,lastmodified,clientid FROM "+dbname+".ipd_bed_change_log where id="+id+"  order by id desc";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			
            while(rs.next()){
            	
				bed.setAddmissionid(rs.getString(1));
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				/*
				 * String wardname = ipdDAO.getIpdWardName(rs.getString(2));
				 * bed.setWardname(wardname);
				 * 
				 * String bedname = ipdDAO.getIpdBedName(rs.getString(3));
				 * bed.setBedname(bedname);
				 */
				bed.setWardid(rs.getString(2));
				bed.setBedid(rs.getString(3));
				if(rs.getString(4)!=null){
					bed.setDate(DateTimeUtils.getDBDate(rs.getString(4)));
				}else{
					bed.setDate("");
				}
				
				bed.setId(rs.getInt(5));
				bed.setCommencing(rs.getString(6));
				bed.setLastmodified(rs.getString(7));
				bed.setClientid(rs.getString(8));
				
            	
		}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return bed;
		
	
		
	}

	@Override
	public int insertfakepatntBedlog(Bed bed1) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ipd_bed_change_log(admissionid, wardid, bedid, lastmodified, clientid,commencing,selectedshiftdata,autosetcharge) values(?,?,?,?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bed1.getAddmissionid());
			preparedStatement.setString(2, bed1.getWardid());
			preparedStatement.setString(3, bed1.getBedid());
			preparedStatement.setString(4, bed1.getLastmodified());
			preparedStatement.setString(5, bed1.getClientid());
			preparedStatement.setString(6, bed1.getCommencing());
			preparedStatement.setString(7, bed1.getDate());
			preparedStatement.setInt(8, 0);
			
			result = preparedStatement.executeUpdate();
			
			/*
			 * if(result == 1){ ResultSet resultSet = preparedStatement.getGeneratedKeys();
			 * if(resultSet.next()){ result = resultSet.getInt(1); } }
			 */
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	
	}

}
