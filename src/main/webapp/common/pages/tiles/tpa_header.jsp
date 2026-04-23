<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <script type="text/javascript" src="inventory/js/addcategory.js"></script>
 <script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>
</head>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
<body>
<div class="row details" style="background: #43b9be !important;">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-7 col-md-7 titlestleftiocn">
								
								<div class="col-lg-2 col-md-2" style="float: right;margin-top: 3px;margin-right: 10px;">
												
												<%-- <s:if test="hidecatagoryid==1">
													
												</s:if>
												<s:else>
													<s:select list="categoryList"  name="category_id" listKey="id" listValue="name" cssClass="form-control chosen-select" id="categoryid" headerKey="0" headerValue="All" onchange="setCategory(this.value)" />
										    		<!--<option value="0">Select Inventory</option>
										    		<option value="1">Housekeeping</option>
										    		<option value="2">Medicine</option>
										    	-->
												</s:else> --%>
												
								</div>

								</div>
							</div>
</body>
<SCRIPT type="text/javascript">
      $("#dropdown").prop("disabled", true);
</SCRIPT>
</html>