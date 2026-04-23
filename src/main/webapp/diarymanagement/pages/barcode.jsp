<%@taglib uri="/struts-tags" prefix="s" %>



<%@page import="java.util.ArrayList"%>

		<style>
		p {
    margin: 0 0 0px;
    text-align: center;
    font-size: 11px;
}
			.paddniltopase{
				padding-top:35px;
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
    			<%String howmany=(String) session.getAttribute("howmany"); %>
    			<%for(Client barcode : totalBarcodeList) %><% {%>
    	<div class="row">
    		<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="margin-bottom: 15px;">
    		   <%if(howmany.equals("0")){ %>
    			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
	    			<img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img>
	    			<p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p>
    			</div>
    			<%} else { %>
    			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
	    			<img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img>
	    			<p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p>
    			</div>
    			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
	    			<img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img>
	    			<p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p>
    			</div>
    			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
	    			<img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img>
	    			<p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p>
    			</div>
    			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
	    			<img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img>
	    			<p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p>
    			</div>
    			<%} %>
    		</div>
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
        <button type="button" onclick="myFunction()" class="btn btn-primary">Print</button>
      </div>
    </div>

  </div>
  
  <s:form action="barcodeIpd" id="frombarcode" theme="simple">
    <s:hidden name="howmany" id="howmany"></s:hidden>
    <s:hidden name="selectedid" ></s:hidden>
    
  </s:form>
  
  
  
</div>
    	
<script>
function myFunction() {
    window.print();
}
</script>
      	
 
		
		
	
				
				
				
				
				
				
				
       			
       			
	