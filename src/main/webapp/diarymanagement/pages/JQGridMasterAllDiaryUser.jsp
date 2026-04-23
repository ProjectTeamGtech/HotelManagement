<%@page import="com.apm.DiaryManagement.web.common.JQGridMasterAllDiaryUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="atg.taglib.json.util.JSONObject"%>


<%

  JQGridMasterAllDiaryUser gridMasterAllDiaryUser = new JQGridMasterAllDiaryUser();
  JSONObject jsonobject = null; 
  String diaryuserid = request.getParameter("diaryuserid");
  String commencing = request.getParameter("commencing");
  String location = request.getParameter("location");
  
  jsonobject = gridMasterAllDiaryUser.getJSONData(diaryuserid,commencing,location);
  out.println(jsonobject);

%>