<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>



<%@page import="java.util.ArrayList"%>

<%@page import="com.apm.Inventory.eu.entity.Product"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<script type="text/javascript" src="common/JsBarcode.all.min.js"> </script>
<style>
@page {
	size: 12in 7in;
}

.paddniltopase {
	
}

p {
	margin: 0 0 0px;
	text-align: center;
	font-size: 40px;
}

.setimg {
	width: 100%;
	margin-left: auto;
	margin-right: auto;
	height: 400px;
}

@media print {
	@page {
		size: 12in 7in;
	}
}
</style>


<script type="text/javascript">
$(document).ready(function() {
	var uhid=document.getElementById('imagedata').value;
	JsBarcode("#barcode", uhid, {
		  format: "CODE128",
		  height: 300,
		  width:8,
		  displayValue:true
		});
});
</script>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
<%
String clientid=(String) request.getAttribute("clientid");
String abrid=(String) request.getAttribute("Abrivationid");
String pname=(String) request.getAttribute("fullname");
	String imgname=clientid+""+abrid+".gif";
%>

<div class="row">

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
		style="padding-top: 60px;text-align: center;">
		<s:hidden name="abrivationid" id='imagedata'></s:hidden>
			
			
	<%if(loginInfo.isAyushman()){ %>
	 <img style="width: 378px;height: 264px;" id="barcode">
	<%}else{ %>
	  <svg id="barcode"></svg> <!-- @Rahul origanal code -->
	<%} %> 	
		<p><%=pname %> / <%=abrid %></p>
		
</div>

</div>

<div class='hidden-print'>
	<input type="button" class='btn btn-primary' value="print"
		onclick="printpage()">
</div>

<script>
	function printpage() {
		window.print()
	}
	
</script>














