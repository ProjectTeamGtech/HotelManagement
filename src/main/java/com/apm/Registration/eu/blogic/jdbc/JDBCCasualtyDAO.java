package com.apm.Registration.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Registration.eu.bi.CasualtyDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.entity.Casualty;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCCasualtyDAO extends JDBCBaseDAO implements CasualtyDAO{
	
	public JDBCCasualtyDAO(Connection connection){
		this.connection = connection;
	}

	public int saveCasualty(Casualty casualty) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into his_casualty(title, fname, lname, age, gender, address, mob, provdiag, recbelonging, drincharge, mlc, refby, typeofdis, note, lastmodified, mlcno) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, casualty.getTitle());
			preparedStatement.setString(2, casualty.getFname());
			preparedStatement.setString(3, casualty.getLname());
			preparedStatement.setString(4, casualty.getAge());
			preparedStatement.setString(5, casualty.getGender());
			preparedStatement.setString(6, casualty.getAddress());
			preparedStatement.setString(7, casualty.getMob());
			preparedStatement.setString(8, casualty.getProvdiag());
			preparedStatement.setString(9, casualty.getRecbelonging());
			preparedStatement.setString(10, casualty.getDrincharge());
			preparedStatement.setString(11, casualty.getMlc());
			preparedStatement.setString(12, casualty.getRefby());
			preparedStatement.setString(13, casualty.getTypeofdis());
			preparedStatement.setString(14, casualty.getNote());
			preparedStatement.setString(15, casualty.getDate());
			preparedStatement.setString(16, casualty.getMlcno());
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);  
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int savePatient(Casualty casualty) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_patient(title,firstname,surname,mobno,email,gender,dob,whopay,lastModified,casualtyid,casautoid) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, casualty.getTitle());
			preparedStatement.setString(2, casualty.getFname());
			preparedStatement.setString(3, casualty.getLname());
			preparedStatement.setString(4, casualty.getMob());
			preparedStatement.setString(5, "");
			preparedStatement.setString(6, casualty.getGender());
			preparedStatement.setString(7, casualty.getDob());
			preparedStatement.setString(8, "Client");
			preparedStatement.setString(9, casualty.getDate());
			preparedStatement.setInt(10, casualty.getId());
			preparedStatement.setInt(11, casualty.getId());
			
			result = preparedStatement.executeUpdate();
			

			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);  
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalCasualtyList() {

		int result=0;
		
		 try {
			
			 String sql="select count(*) from his_casualty";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 
			 while(rs.next()){
				 
				   result=rs.getInt(1);
			 }
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
		

	public ArrayList<Casualty> getCasualtyList(Connection connection,Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<Casualty>list = new ArrayList<Casualty>();
		String sql = "select lastmodified,id,concat(title,' ',fname,' ',lname),age,gender, " +
				"provdiag,recbelonging,drincharge,mlc,refby,typeofdis from his_casualty ";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Casualty casualty = new Casualty();
				casualty.setDate(rs.getString(1));
				casualty.setId(rs.getInt(2));
				casualty.setFname(rs.getString(3));
				casualty.setAge(rs.getString(4));
				casualty.setGender(rs.getString(5));
				
				String condition = getConditionName(rs.getString(6));
				
				casualty.setProvdiag(condition);
				casualty.setRecbelonging(rs.getString(7));
				
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(8));
				String drincharge = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getSurname();
				
				casualty.setDrincharge(drincharge);
				casualty.setMlc(rs.getString(9));
				
				userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(10));
				String refby = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastchanged();
				casualty.setRefby(refby);
				casualty.setTypeofdis(rs.getString(11));
				
				list.add(casualty);
				
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	private String getConditionName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_diagnosis where id = "+id+" ";
		
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

	public Casualty getCasualtyDetails(String id) {
		PreparedStatement preparedStatement = null;
		Casualty casualty = new Casualty();
		String sql = "select title, fname, lname, age, gender, address, mob, provdiag, recbelonging, drincharge, mlc, refby, typeofdis, note, mlcno from his_casualty where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				casualty.setTitle(rs.getString(1));
				casualty.setFname(rs.getString(2));
				casualty.setLname(rs.getString(3));
				casualty.setAge(rs.getString(4));
				casualty.setGender(rs.getString(5));
				casualty.setAddress(rs.getString(6));
				casualty.setMob(rs.getString(7));
				casualty.setProvdiag(rs.getString(8));
				casualty.setRecbelonging(rs.getString(9));
				casualty.setDrincharge(rs.getString(10));
				casualty.setMlc(rs.getString(11));
				casualty.setRefby(rs.getString(12));
				casualty.setTypeofdis(rs.getString(13));
				casualty.setNote(rs.getString(14));
				casualty.setMlcno(rs.getString(15));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return casualty;
	}

	public int updateCasualty(Casualty casualty, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update his_casualty set title=?, fname=?, lname=?, age=?, gender=?, address=?, mob=?, provdiag=?, recbelonging=?, drincharge=?, mlc=?, refby=?, typeofdis=?, note=?, lastmodified=?, mlcno=? where id="+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, casualty.getTitle());
			preparedStatement.setString(2, casualty.getFname());
			preparedStatement.setString(3, casualty.getLname());
			preparedStatement.setString(4, casualty.getAge());
			preparedStatement.setString(5, casualty.getGender());
			preparedStatement.setString(6, casualty.getAddress());
			preparedStatement.setString(7, casualty.getMob());
			preparedStatement.setString(8, casualty.getProvdiag());
			preparedStatement.setString(9, casualty.getRecbelonging());
			preparedStatement.setString(10, casualty.getDrincharge());
			preparedStatement.setString(11, casualty.getMlc());
			preparedStatement.setString(12, casualty.getRefby());
			preparedStatement.setString(13, casualty.getTypeofdis());
			preparedStatement.setString(14, casualty.getNote());
			preparedStatement.setString(15, casualty.getDate());
			preparedStatement.setString(16, casualty.getMlcno());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
