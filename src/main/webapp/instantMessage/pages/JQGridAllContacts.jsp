<%@page import="com.apm.InstantMessage.web.action.JQGridAllContacts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@page import="atg.taglib.json.util.JSONObject"%>
<%

JQGridAllContacts gridMaster = new JQGridAllContacts();
      JSONObject jsonobject = null; 
	
      
      
    
  	
  	
      
      jsonobject = gridMaster.getJSONData();
      out.println(jsonobject);
      System.out.println(jsonobject);
%>
