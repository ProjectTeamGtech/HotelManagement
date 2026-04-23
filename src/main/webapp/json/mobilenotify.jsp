

<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
Connection con = null;
try
{ 
	
	 response.setContentType("application/json");
	 con=Connection_provider.getconnection();
     JSONObject jsonObject=new JSONObject();
     String data=request.getParameter("data");
     if(data!=null)
     {
    	 try
    	 {
    		 
    		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
    		 PreparedStatement ps=con.prepareStatement("insert into admin.apm_dumy_apmt (name) values (?) ");
    		 ps.setString(1, data);
    		 ps.executeUpdate();
    		 con.close();
    	 }catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }finally{
    		 con.close();
    	 }
    	 jsonObject.put("notify", "null"); 
     } 
     else
     {
    	 String text=null;
    	 int id = 0;
    	 try
    	 {
    		PreparedStatement preparedStatement = null;
    		String sql = "SELECT id,name FROM admin.apm_dumy_apmt order by id desc limit 0,1";
    		preparedStatement = con.prepareStatement(sql);
    		ResultSet rs = preparedStatement.executeQuery();
    		if(rs.next()){
    			id = rs.getInt(1);
    			text = rs.getString(2);
    		}
    		
    		String sqls = "delete from admin.apm_dumy_apmt where id = "+id+" ";
    		PreparedStatement ps = con.prepareStatement(sqls);
    		int res = ps.executeUpdate();
    		
    		jsonObject.put("notify",text);
    		 
    	 }catch(Exception e)
    	 {
    		 e.printStackTrace();
    		 
    	 }finally{
    		 con.close();
    	 }
    		
     }
     
     
     out.println(jsonObject);
}
catch(Exception e)
{
	
e.printStackTrace();
}


%>    
    
    
  