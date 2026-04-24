package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class hello {
	
	public static String unicodeEscaped(char ch) {
	      if (ch < 0x10) {
	          return "\\u000" + Integer.toHexString(ch);
	      } else if (ch < 0x100) {
	          return "\\u00" + Integer.toHexString(ch);
	      } else if (ch < 0x1000) {
	          return "\\u0" + Integer.toHexString(ch);
	      }
	      return "\\u" + Integer.toHexString(ch);
	  }
	  
	  /**
	   * Converts the string to the unicode format '\u0020'.
	   * 
	   * This format is the Java source code format.
	   * 
	   * If <code>null</code> is passed in, <code>null</code> will be returned.
	   *
	   * <pre>
	   *   CharUtils.unicodeEscaped(null) = null
	   *   CharUtils.unicodeEscaped(' ')  = "\u0020"
	   *   CharUtils.unicodeEscaped('A')  = "\u0041"
	   * </pre>
	   * 
	   * @param ch  the character to convert, may be null
	   * @return the escaped unicode string, null if null input
	   */
	  public static String unicodeEscaped(Character ch) {
	      if (ch == null) {
	          return null;
	      }
	      return unicodeEscaped(ch.charValue());
	  }
	  
	  
	  public static void main(String[] args) throws IOException {
		  Connection connection;
		try {
			connection = hello.getconnection();
			
			String str = hello.getLanguageStr(connection);
			System.out.println(str);
			//str = "जे";
			char c[] = str.toCharArray();
			String tt = "";
			for(int i=0;i<c.length;i++){
				System.out.print(hello.unicodeEscaped(c[i]));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
	  
	  
	  private static String getLanguageStr(Connection connection) {
		PreparedStatement preparedStatement = null;
		String result = null;
		String sql = "SELECT language FROM apm_medicine_dosage where id = 1 ";
		
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

	public static Connection getconnection() throws SQLException
	  {
	  	Connection con=null;
	  	try {
	  		Class.forName("com.mysql.jdbc.Driver");
	  		
	  		
	  		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akdcnagpur?useUnicode=yes&characterEncoding=UTF-8","root","mysql");
	  	} catch (ClassNotFoundException e) {
	  		e.printStackTrace();
	  	}
	  	return con;
	  }
	  

}
