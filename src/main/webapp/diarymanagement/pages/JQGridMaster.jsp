<%@page import="com.apm.DiaryManagement.web.common.JQGridMaster"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@page import="atg.taglib.json.util.JSONObject"%>
<%

      JQGridMaster gridMaster = new JQGridMaster();

      JSONObject jsonobject = null; 
	
      String diaryuserid = request.getParameter("diaryuserid");
      String tdcode = request.getParameter("tdcode");
      
      jsonobject = gridMaster.getJSONData(diaryuserid,tdcode);
      out.println(jsonobject);
%>

