<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="oracle.jdbc.util.Login"%>
<%@taglib uri="/struts-tags" prefix="s" %>



<%@page import="java.util.ArrayList"%>

		<%@page import="com.apm.Inventory.eu.entity.Product"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<script type="text/javascript" src="common/JsBarcode.all.min.js"> </script>
<style>
			.paddniltopase{
				/* padding-top:38px; */
			}
			p {
    margin: 0 0 0px;
    text-align: center;
    font-size: 9px;
}
 @page {
		size: 100mm 100mm !important;
	}


.setimg{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
       /*  height: 40px; */
     } 
    @media print{
    .row{
   /*  size: 4in 6in !important; */
    }
    @page {
		size: 100mm 100mm !important;
	}
    }  
           
		</style>
	<script type="text/javascript">
$(document).ready(function() {
	
	var index=document.getElementById('final').value;
	for(i=1;i<index;i++){
		var uhid=document.getElementById('imagedata'+i).value;
		JsBarcode("#barcode"+i, uhid, {
			  format: "CODE128",
			  height: 40,
			  width:3,
			  displayValue:true,
			 
			  lineColor: "#000000",
			  fontSize: 15
			});
	}
	
});
</script>	
			
  <%	LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
  <%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
    			<% int p=1,i=0; 
    				Connection connection = Connection_provider.getconnection();
    				PreparedStatement preparedStatement = null;
    				String imgname = "";
    				String product = "";
    				String bno = "";
    				
    				int mdbarcodecount = (Integer)session.getAttribute("mdbarcodecount");
    			%>
    			
    		
    			
    	<div class="row" style="">
    		<div class="" id='hh' >
    		<%for(i=1;i<=mdbarcodecount;i++) %><% {%>
    			<%-- <% for(int j=1;j<=4;j++){%> --%>
    			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 xsss" style="text-align: center;">
    				<%String sql = "SELECT img,product,bno,gprodid FROM apm_mdbarcode where id = "+p+"";
    					preparedStatement = connection.prepareStatement(sql);
    					ResultSet rs = preparedStatement.executeQuery();
    					if(rs.next()){
    						 imgname = rs.getString(1);
    						 product = rs.getString(2);
    						 bno = rs.getString(3);
    					}
    				%>
    				<%String xx=rs.getString(4);%>
    				<%-- <s:hidden name="imagedata" id='imagedata'></s:hidden> --%>
    				<input type="hidden" value="<%=xx%>" id='imagedata<%=i%>'>
	    			<svg id="barcode<%=i%>" style="width:100% !important"></svg>
	    			<%if(loginInfo.isBarcode_productname_show()){ %><p><%if(loginInfo.isBalgopal()){%>B-<%}%><%=product %> </p>	
	    			<p><%=bno %> </p><%} %>
	    			<%p++; %>
    			</div>
    			<%} %>
    			
    			<input type="hidden" id='final' value="<%=i%>">
    		<%-- <%} %> --%>
    		</div>
    		
    		<input type="button" class='btn btn-primary' value='print' onclick="printDiv('hh')">
    	</div>
    	
    	
<script>
function printpage()
  {
  window.print()
  }
  
  
function printDiv(divName) {
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}
  
</script>
      	
 
		
		
	
				
				
				
				
				
				
				
       			
       			
	