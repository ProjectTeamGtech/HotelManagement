<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Connection con = null;
   try
   {
	   response.setContentType("application/json");
	   JSONObject object=new JSONObject(); 
	   con=Connection_provider.getconnection();
	   PreparedStatement ps=con.prepareStatement("delete * from admin.apm_dumy_apmt");
	   ps.executeUpdate();
	   object.put("delete", "data deleted");
   }catch(Exception e)
   {
	   e.printStackTrace();
   }finally{
	   con.close();
   }


%>
