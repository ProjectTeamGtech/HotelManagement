<%@page import="com.apm.DiaryManagement.web.common.JQGridMasterNonAvailableSlot"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@page import="atg.taglib.json.util.JSONObject"%>
<%

JQGridMasterNonAvailableSlot gridMaster = new JQGridMasterNonAvailableSlot();

      JSONObject jsonobject = null; 
	
      
      
    String clientid = request.getParameter("clientid");
  	String admissionid = request.getParameter("admissionid");
  	String treatmentid = request.getParameter("treatmentid");
  	
  	
      
      jsonobject = gridMaster.getipddetailsData(clientid, admissionid, treatmentid);
      out.println(jsonobject);
      System.out.println(jsonobject);
%>

