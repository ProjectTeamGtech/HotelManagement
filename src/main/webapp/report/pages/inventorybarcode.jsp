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
		  width:10,
		  displayValue:true
		});
});
</script>

<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%
	int p = 1, i = 0;
	Connection connection = Connection_provider.getconnection();
	PreparedStatement preparedStatement = null;
	String imgname = "";
	String product = "";
	String bno = "";
	String procode="";
	int mdbarcodecount = (Integer) session.getAttribute("mdbarcodecount");
%>
<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>

<div class="row">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
		style="padding-top: 0px;">
		<%
			for (i = 1; i <= 1; i++)
		%>
		<%
			{
		%>
		<%-- <% for(int j=1;j<=4;j++){%> --%>
		<div class="" style="margin-bottom: 0px;">
			<%
				String sql = "SELECT img,product,bno,procode FROM apm_mdbarcode where id = " + mdbarcodecount + "";
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if (rs.next()) {
						imgname = rs.getString(1);
						product = rs.getString(2);
						if(product!=null){
							if(product.length()>20){
								product=(product.substring(0,20));
							}
						}
						bno = rs.getString(3);
						procode = rs.getString(4);
					}
					
			%>
<s:hidden name="imagedata" id='imagedata'></s:hidden>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="text-align: center ;"> 
	<svg id="barcode"></svg>


			<p><%=product%>
				<%if(loginInfo.isLmh()){ %>
					(<%=procode%>)
				<%}else{ %>
					(<%=bno%>)
				<%} %>
				
			</p>
</div>
			<%
				p++;
			%>
		</div>
		<%
			}
		%>
		<%-- <%} %> --%>
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














