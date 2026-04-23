<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<link rel="stylesheet" type="text/css" href="Design/MenuItems/pro_drop_1.css" />
<script src="Design/MenuItems/stuHover.js" type="text/javascript"></script>
<link href="Design/css/style.css" rel="stylesheet" type="text/css" />


<link rel="icon" href="images/icon.ico">
<link rel="stylesheet" type="text/css" href="Design/MenuItems/pro_drop_1.css" />
<link href="Design/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="Design/js/jquery.js"></script>
<script type="text/javascript" src="Design/js/interface.js"></script>
<script src="Design/MenuItems/stuHover.js" type="text/javascript"></script>

<div class="main_layout_content">
	
 	<div id="edit_contact_details" class="block_div" style="height: 500px;">	
 		<table width="100%">
 			<tr>
 				<td align="center">
 				
 						 <%if(session.getAttribute("logininfo")!=null){ %>
 						 	<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
 						 		
 						 		<%if(loginInfo.getUserType() == 1){ %>
 									<b>Welcome to YUVICARE</b>
 								<%} %> 
 								<%if(loginInfo.getUserType() == 2){ %>
 									<div class="logo">
										<a style="text-decoration:none;" href="index.html">
										<img src="Design/images/logo.png" width="100%" border="0" /></a>
									</div>
 									
 									
 									
 									
 								<%} %>
 								<%if(loginInfo.getUserType() == 3){ %>
 									<b>Welcome to YUVICARE</b>
 								<%} %>
 								<%if(loginInfo.getUserType() == 4){ %>
 									<b>Welcome to YUVICARE</b>
 								<%} %>
 								<%if(loginInfo.getUserType() == 5){ %>
 									<b>Welcome to YUVICARE</b>
 								<%} %> 	 	 	 				
 						 	
 						 <% }else{%>
 						 	<b>Welcome to YUVICARE</b>
 						 <% }%>	
 						 
 				</td>
 			</tr>
 		</table>
		
	</div>
</div>