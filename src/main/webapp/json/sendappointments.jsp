<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%


   response.setContentType("application/json");
   String clinicid=request.getParameter("clinicid");
   String fromdate=request.getParameter("fromdate");
   String todate=request.getParameter("todate");
   String userid=request.getParameter("userid"); 
   
   Connection connection = null;
   try
   {
	    int id=0;
	    JSONObject object=new JSONObject();
	    JSONArray array=new JSONArray();
	    connection=Connection_provider.getconnection();
        Statement st=connection.createStatement();
        ResultSet rs2=st.executeQuery("select id from "+clinicid+".apm_user where userid='"+userid+"'");
        while(rs2.next())
        {
        	id=rs2.getInt("id");
        }
        
        rs2.close();
        ResultSet rs=st.executeQuery("select apmslotid ,commencing,starttime,endtime,diaryuserid,diaryusername,clientname,aptmtype,clientid,notes,duration,apmttypetext from "+clinicid+".apm_available_slot where commencing between '"+fromdate+"' and '"+todate+"' and diaryuserid="+id+" order by commencing ");
	    while(rs.next())
	    {
	    	JSONObject jsonObject=new JSONObject();
	    	String apmslotid=rs.getString("apmslotid");
	    	String commencing=rs.getString("commencing");
	    	String starttime=rs.getString("starttime");
	    	String endtime=rs.getString("endtime");
	    	String diaryuserid=rs.getString("diaryuserid");
	    	String diaryusername=rs.getString("diaryusername");
	    	String clientname=rs.getString("clientname");
	    	String aptmtype=rs.getString("aptmtype");
	    	String clientid=rs.getString("clientid");
	    	String notes=rs.getString("notes");
	    	String duration=rs.getString("duration");
	    	String apmttypetext=rs.getString("apmttypetext");
	    	jsonObject.put("apmslotid", apmslotid);
	    	jsonObject.put("commencing", commencing);
	    	jsonObject.put("starttime", starttime);
	    	jsonObject.put("endtime", endtime);
	    	jsonObject.put("diaryuserid", diaryuserid);
	    	jsonObject.put("diaryusername", diaryusername);
	    	jsonObject.put("clientname", clientname);
	    	jsonObject.put("aptmtype", aptmtype);
	    	jsonObject.put("clientid", clientid);
	    	jsonObject.put("notes", notes);
	    	jsonObject.put("duration", duration);
	    	jsonObject.put("apmttypetext", apmttypetext);
	    	
	    	array.add(jsonObject);
	    }
	   
	    object.put("appointment",array);
	    out.println(object);
	    
   }catch(Exception e)
   {
	   e.printStackTrace();
   }finally{
	   connection.close();
   }
   

   
%>

