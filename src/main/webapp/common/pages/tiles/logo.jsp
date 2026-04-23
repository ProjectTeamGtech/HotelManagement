
 <%@ taglib uri="/struts-tags"  prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%

session=request.getSession(false);
if(session==null)
    {
        response.sendRedirect("login.jsp");
    }
String userName=(String)session.getAttribute("username");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>logo</title>
        <style>
            #tablelogo{
              border-radius: 15px;
              border-color: cornflowerblue;
              border-bottom-width: thick;
              
}
        </style>
    </head>
    <body>
       
        <table id="tablelogo"align="center" border="0" width="100%" cellspacing="2" cellpadding="2" style=" background:transparent url(common/images/blue.jpg); background-size:1000px;">
            <tr>
                <td width="180">
                    <img src="common/images/bk12.jpg" height="100"alt="logo"/>
                </td>
                <td align="center">
                    <img src="common/images/bakerylogo.jpg" height="50" alt="bakery logo"/>
                    <%if(userName!=null)
                        {
                        %>
                       
	<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
		<font style="font-weight: bold; font-size: 14px; float: right;"> 
				<span>Welcome </span><span id="username"><s:property value="#session.LOGIN_INFO.branchName"/></span>
		</font>
	</s:if>
                        <%
                         }
                        %>
                </td>
            </tr>
           
        </table>

       
    </body>
</html>
