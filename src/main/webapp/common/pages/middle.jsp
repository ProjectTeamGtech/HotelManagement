<%-- 
    Document   : flash
    Created on : May 25, 2013, 1:09:55 AM
    Author     : BATCH 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<html>
<head>

    <link href="common/themes/1/js-image-slider.css" rel="stylesheet" type="text/css" />
    <script src="common/themes/1/js-image-slider.js" type="text/javascript"></script>
    <link href="generic.css" rel="stylesheet" type="text/css" />
</head>
<body>
   

    <table align="center" width="100%" cellpadding="2" cellspacing="2" border="0" style=" background:transparent url(img/blue.jpg); background-size:1000px;border-radius: 15px;margin-top: 20px;">
         <tr>
                <td bgcolor="lightgray" colspan="2" style="font-weight: bold;color:white;"><marquee>We are focused on providing high-quality service and customer satisfaction</marquee></td>
            </tr>
        <tr>
            <td>
    <div id="sliderFrame">
        <div id="ribbon"></div>
        <div id="slider">
            
            
            <img src=" common/images/bk11.jpg"  />
           
            <img src="common/images/bk13.jpg"  />
            <img src="common/images/bk14.jpg"  />
            <img src="common/images/bk5.jpg"  />
            <img src="common/images/bk6.jpg"  />
            <img src="common/images/bk9.jpg"  />
            </div>

    </div>
            </td>
        </tr>
    </table>

</body>
</html>