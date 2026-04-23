<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style>
	p {
    margin: 0 0 1.5px;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.savebigbtn {
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
    line-height: 40px;
}
</style>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>

<style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style>


</head>
<body>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
<div class="col-lg-12 col-xs-12 col-md-12" oncontextmenu="return false;">

<div class="hidden-print">
		<ul class="breadcrumb">
			<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
			<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
				<%if(breadcrumbs.isIscurrent()){ %>
					<li><%=breadcrumbs.getName()%></li>
				<%}else{ %>
					<%if(breadcrumbs.getOn()){ %>
						<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
					<%}else{ %>
						<li><%=breadcrumbs.getName()%></li>
					<%} %>
				<%} %>
				
			<%} %>
		</ul>
	</div>
</div>
<p class="error text-center" style="margin-top: 50px;font-size: 25px;">Indent No. <s:property value="parentid"/> is handling by <s:property value='userid'/> from time <s:property value="date"/>. Please wait for 10 minute.</p>






</body>
</html>