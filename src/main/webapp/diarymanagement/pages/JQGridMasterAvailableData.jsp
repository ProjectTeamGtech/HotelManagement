<%@page import="com.apm.DiaryManagement.web.common.JQGridMasterAvailableData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="atg.taglib.json.util.JSONObject"%>


<% 
		JQGridMasterAvailableData gridMasterAvailableData = new JQGridMasterAvailableData();
		JSONObject jsonobject = null; 
		String diaryuserid = request.getParameter("diaryuserid");
		String practitionerid = request.getParameter("practitionerid");
		String rdddval = request.getParameter("rdddval");

		
	  jsonobject = gridMasterAvailableData.getJSONData(diaryuserid,practitionerid,rdddval);
	  out.println(jsonobject);


%>