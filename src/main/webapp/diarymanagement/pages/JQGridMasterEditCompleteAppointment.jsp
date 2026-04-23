<%@page import="com.apm.DiaryManagement.web.common.JQGridMasterCompleteApmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@page import="atg.taglib.json.util.JSONObject"%>
<%

JQGridMasterCompleteApmt gridMaster = new JQGridMasterCompleteApmt();

      JSONObject jsonobject = null; 
	
      
     String id = request.getParameter("id");
  	
      
      jsonobject = gridMaster.getJSONData(id);
      out.println(jsonobject);
      System.out.println(jsonobject);
%>