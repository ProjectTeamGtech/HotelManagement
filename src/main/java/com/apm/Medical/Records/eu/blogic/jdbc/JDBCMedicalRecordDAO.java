package com.apm.Medical.Records.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import com.apm.Master.eu.entity.Master;
import com.apm.Medical.Records.eu.bi.MedicalRecordDAO;
import com.apm.Medical.Records.eu.entity.MedicalRecord;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Pagination;

public class JDBCMedicalRecordDAO extends JDBCBaseDAO implements MedicalRecordDAO{
	
	public JDBCMedicalRecordDAO(Connection connection){
		this.connection = connection;
		
	}

	public int saveMedicalRecord(MedicalRecord medicalRecord) {
		int result = 0;
		int id = 0;
		PreparedStatement pstm = null;
		String sql = "insert into apm_attachments(date,subject,description,filename,client_id,client_name) values (?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, medicalRecord.getDate());
			pstm.setString(2, medicalRecord.getSubject());
			pstm.setString(3, medicalRecord.getDescription());
			pstm.setString(4, medicalRecord.getUserImageFileName());
			pstm.setString(5, medicalRecord.getClientId());
			pstm.setString(6, medicalRecord.getClient());
			
			
			result = pstm.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = pstm.getGeneratedKeys();
				if(resultSet.next()){
					id = resultSet.getInt(1);  
				}
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return id;
		
	}

	public int getTotalMedicakRecordsCount(String searchText,String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM apm_attachments where subject like('%"+searchText+"%') and client_id like('%"+clientId+"%')";

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

	public ArrayList<MedicalRecord> getMedicalRecordList(Pagination pagination,String searchText,String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<MedicalRecord>list = new ArrayList<MedicalRecord>();
		String sql = "SELECT id,date,subject,description,filename,client_id,client_name FROM apm_attachments where subject like('%"+searchText+"%') and client_id like('%"+clientId+"%')";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				MedicalRecord medicalRecord = new MedicalRecord();
				
				medicalRecord.setId(rs.getInt(1));
				medicalRecord.setDate(rs.getString(2));
				medicalRecord.setSubject(rs.getString(3));
				medicalRecord.setDescription(rs.getString(4));
				medicalRecord.setUserImageFileName(rs.getInt(1)+"_"+rs.getString(5));
				medicalRecord.setClientId(rs.getString(6));
				medicalRecord.setClient(rs.getString(7));
				
				
				list.add(medicalRecord);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public MedicalRecord getMedicalDetails(int id, MedicalRecord medicalRecord) {
		PreparedStatement preparedStatement = null;
		
		String sql = "SELECT id,date,subject,description,filename,client_id,client_name FROM apm_attachments where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				medicalRecord.setId(rs.getInt(1));
				medicalRecord.setDate(rs.getString(2));
				medicalRecord.setSubject(rs.getString(3));
				medicalRecord.setDescription(rs.getString(4));
				medicalRecord.setUserImageFileName(rs.getString(5));
				medicalRecord.setClientId(rs.getString(6));
				medicalRecord.setClient(rs.getString(7));
							
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return medicalRecord;
	}

	public int updateMedicalRecord(MedicalRecord medicalRecord, int idSelected) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_attachments set date = ?,subject = ?,description = ?,filename = ?,client_id = ?,client_name = ? where id = "+idSelected+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, medicalRecord.getDate());
			preparedStatement.setString(2, medicalRecord.getSubject());
			preparedStatement.setString(3, medicalRecord.getDescription());
			preparedStatement.setString(4, medicalRecord.getUserImageFileName());
			preparedStatement.setString(5, medicalRecord.getClientId());
			preparedStatement.setString(6, medicalRecord.getClient());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int deleteMedicalRecord(int id, MedicalRecord medicalRecord) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_attachments where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
