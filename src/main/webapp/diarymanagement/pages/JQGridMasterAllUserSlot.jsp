<%@page import="com.apm.DiaryManagement.web.common.JQGridMasterNonAvailableSlot"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@page import="atg.taglib.json.util.JSONObject"%>
<%

JQGridMasterNonAvailableSlot gridMaster = new JQGridMasterNonAvailableSlot();

      JSONObject jsonobject = null; 
      JSONObject jsonobject1 = null; 
      
      
    
  	String date = request.getParameter("date");
  	jsonobject = gridMaster.getJSONData1(date);
  	out.println(jsonobject);
    System.out.println(jsonobject);
    
   
    
    
      
      
      
%>

