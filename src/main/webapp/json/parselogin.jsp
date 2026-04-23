<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.json.simple.JSONArray"  %>    
 <%@ page import="org.json.simple.JSONObject"  %>
 <%@ page import="java.sql.Statement"  %>        
 <%@ page import="java.sql.Connection"  %>    
 <%@ page import="java.sql.ResultSet"  %>    
 <%@ page import="com.apm.common.eu.blogic.jdbc.*"  %>    
 
 <% 
 
 
 response.setContentType("application/json");
 JSONObject object=new JSONObject();
 
 JSONArray array=new JSONArray();
 Connection con = null;
 try {
	
  	     con=Connection_provider.getconnection();
	    Statement st=con.createStatement();
	    ResultSet rs=st.executeQuery("SELECT userid,password,clinicid FROM admin.apm_user");
	    while(rs.next())
	    {
	    	JSONObject object2=new JSONObject();
	    	String userid=rs.getString("userid");
	    	String password=rs.getString("password");
	    	String clinicid=rs.getString("clinicid");
	    	object2.put("userid", userid);
	    	object2.put("password", password);
	    	object2.put("clinicid",clinicid);
	    	array.add(object2);
	    }
	    object.put("logindataapm", array);
	    
	    out.println(object);
	    
	    
	  
} catch (Exception e) {

  e.printStackTrace();
} finally{
	con.close();
}
   
   
 
 %>