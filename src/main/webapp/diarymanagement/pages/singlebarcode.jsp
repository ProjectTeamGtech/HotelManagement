<%@taglib uri="/struts-tags" prefix="s" %>



<%@page import="java.util.ArrayList"%>

		<style>
			.paddniltopase{
				margin-top:10px;
			}
			
			p {
    margin: 0 0 0px;
    text-align: center;
    
    
}
.setimg{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
        height: 40px;
     }  
     @media print{
    .setimg{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
        height: 40px;
     }  
}
          
		</style>
		

 <script type="text/javascript">
 
      
      window.onload = function(){
      
             $('#myModal').modal( "show");       
      };
      
      function myfunc(d){
        
      	   document.getElementById("howmany").value=d;
      	   document.getElementById("frombarcode").submit();	
      }
      
 
 
</script>

			

  <%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
  
    	<%if(session.getAttribute("totalBarcodeList")!=null)%><% {%>
    			<%ArrayList<Client>totalBarcodeList = (ArrayList<Client>)session.getAttribute("totalBarcodeList"); %>
    			<%for(Client barcode : totalBarcodeList) %><% {%>
    			<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12" id="printableArea">
	    			<img src="barcode/<%=barcode.getImageName() %>" style="width:500px;height:40px;"></img>
	    			<p style="text-align:center;"><%=barcode.getClientName() %> , <%=barcode.getAge1() %> , <%=barcode.getGender() %></p>	
	    			<p style="text-align:center;"><%=barcode.getWhopay() %> , <%=barcode.getMobNo() %></p>
    			</div>
    			<%} %>
    	<%} %>
    	
    	
    	<!-- Modal -->
<div id="myModal" class="modal fade hidden-print" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Print Barcode</h4>
      </div>
      <div class="modal-body">
        <div class="col-lg-12 col-md-12 col-sm-12">
        	<div class="form-group">
        		<select name="status" id="clientFrm_status" class="form-control" onchange="myfunc(this.value)">
        		 <option value="">Select</option>
				    <option value="0">One</option>
				    <option value="1">Many</option>
				</select>
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" onclick="printDiv('printableArea')" class="btn btn-primary">Print</button>
      </div>
    </div>

  </div>
  
  <s:form action="barcodeIpd" id="frombarcode" theme="simple">
    <s:hidden name="howmany" id="howmany"></s:hidden>
    <s:hidden name="selectedid" ></s:hidden>
    
  </s:form>
  
</div>
    	
<script>
function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}
</script>
      	
 
		
		
	
				
				
				
				
				
				
				
       			
       			
	