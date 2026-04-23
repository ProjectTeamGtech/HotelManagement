<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.json.simple.JSONArray"  %>    
 <%@ page import="org.json.simple.JSONObject"  %>
 <%@ page import="java.sql.Statement"  %>        
 <%@ page import="java.sql.Connection"  %>    
 <%@ page import="java.sql.ResultSet"  %>    
 <%@ page import="com.apm.common.eu.blogic.jdbc.*"  %>    
 
 <% 
 
 String userid=request.getParameter("userid");
 response.setContentType("application/json");
 JSONObject object=new JSONObject();
 
 JSONArray array=new JSONArray();
 
 Connection con = null;
 
 try {
	   
	   
	   	String clinicid=null;    
  	    con=Connection_provider.getconnection();
	    Statement st=con.createStatement();
	    ResultSet rs=st.executeQuery("SELECT clinicid FROM admin.apm_user where userid='"+userid+"'");
	    while(rs.next())
	    {
	      clinicid=rs.getString("clinicid");	
	    	 	
	    }
	    rs=st.executeQuery("SELECT  firstname,lastname,jobtitle,discription,diarycolor,diarycolumnposition,compressionrate,appointmentbookingcontem,appointmentbookingdnatem,email,mobile from "+clinicid+".apm_user where userid='"+userid+"'");
	    while(rs.next())
	    {
	    	JSONObject object2=new JSONObject();
	    	String fname=rs.getString("firstname");
	    	String lname=rs.getString("lastname");
	    	String jobtitle=rs.getString("jobtitle");
	    	String disc=rs.getString("discription");  
	    	String diarycolor=rs.getString("diarycolor");
	    	String diarycolumnposition=rs.getString("diarycolumnposition");
	    	String compressionrate=rs.getString("compressionrate");
	    	String appointmentbookingcontem=rs.getString("appointmentbookingcontem");
	    	String appointmentbookingdnatem=rs.getString("appointmentbookingdnatem");
	    	String email=rs.getString("email");
	    	String mobile=rs.getString("mobile");
	    	object2.put("firstname", fname);
	    	object2.put("lastname", lname);
	    	object2.put("jobtitle", jobtitle);
	    	object2.put("discription", disc);
	    	object2.put("diarycolor", diarycolor);
	    	object2.put("diarycolumnposition", diarycolumnposition);
	    	object2.put("compressionrate", compressionrate);
	    	object2.put("appointmentbookingcontem", appointmentbookingcontem);
	    	object2.put("appointmentbookingdnatem", appointmentbookingdnatem);
	    	object2.put("email", email);
	    	object2.put("mobile", mobile);
	    	array.add(object2);
	    }
	    object.put("profile", array);
	    
	    out.println(object);
	    
	    
	  
} catch (Exception e) {

  e.printStackTrace();
} finally{
	con.close();
}
   
   
 
 %>