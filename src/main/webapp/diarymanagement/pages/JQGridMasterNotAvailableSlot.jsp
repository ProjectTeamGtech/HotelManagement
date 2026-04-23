<%@page import="com.apm.DiaryManagement.web.common.JQGridMasterNonAvailableSlot"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@page import="atg.taglib.json.util.JSONObject"%>
<%

JQGridMasterNonAvailableSlot gridMaster = new JQGridMasterNonAvailableSlot();

      JSONObject jsonobject = null; 
	
      
      
    String dairyuserid = request.getParameter("diaryUserId");
  	String date = request.getParameter("date");
  	String locationid = request.getParameter("locationid");
  	
  	
      
      jsonobject = gridMaster.getJSONData(dairyuserid,date,locationid);
      out.println(jsonobject);
      System.out.println(jsonobject);
%>

