package com.apm.common.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.apm.common.eu.bi.ResetPasswordDAO;
import com.apm.common.utils.Encryption;

public class JDBCResetPassword extends JDBCBaseDAO implements ResetPasswordDAO{
	public JDBCResetPassword(Connection connection){
		this.connection = connection;
	}

	public int updatePassword(String emailId, String password) {
		int result = 0;
		PreparedStatement preparedStatement =null;
		try{
		String encPassword = Encryption.encryptSHA(password);

		String sql = "update apm_user set password = '"+encPassword+"' where email = '"+emailId+"'";
		preparedStatement = connection.prepareStatement(sql);
		result = preparedStatement.executeUpdate();

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean isEmailIDExist(String emailId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select id from apm_user where email = '"+emailId+"' ";
		int id=0;
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				id=rs.getInt(1);
				//result = true;
			}
			if(id>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

public boolean isOldPassword(String oldpassword, String userId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		
		String sql = "select password from apm_user where userid = '"+userId+"' ";
		
		try{
			String encPassword = Encryption.encryptSHA(oldpassword);
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				String password= rs.getString(1);
				if(password.equalsIgnoreCase(encPassword)){
					result = true;
				}
				else{
					result = false;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

public int chnagePswd(String userId, String password) {
	int result = 0;
	PreparedStatement preparedStatement =null;
	try{
	String encPassword = Encryption.encryptSHA(password);

	String sql = "update apm_user set password = '"+encPassword+"' where userid = '"+userId+"'";
	preparedStatement = connection.prepareStatement(sql);
	result = preparedStatement.executeUpdate();

	}
	catch(Exception e){
		e.printStackTrace();
	}
	return result;
}

public String getlinkAddress(String emailId) {
	PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "select clinicid from apm_user where email = '"+emailId+"' ";
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			String sql2 = "select linkaddress from apm_user where userid='"+rs.getString(1)+"'";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			ResultSet rs1 = preparedStatement2.executeQuery();
			while (rs1.next()) {
				result = rs1.getString(1);
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

}
