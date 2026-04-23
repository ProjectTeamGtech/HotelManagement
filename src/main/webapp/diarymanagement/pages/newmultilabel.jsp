
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.ArrayList"%>

		<style>
		p {
    margin: 0 0 0px;
    font-size: 12px;
    font-weight: bold;
}
			.paddniltopase{
				padding-top:50px;
			}
			label {
    margin-bottom: 2px;
}
		
.setimg{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
        height: 40px;
     }
     
      @media print
   {
      p {
    margin: 0 0 0px;
    font-size: 9px;
    font-weight: bold;
}
.setprintlbl{
	margin-top: 40px;
}
.oneone{
/* padding-ri: -35px; */
padding-left: 40px;
}

body{
width: 4.24 in !important;
height: 2.88 in !important;
}

 /* @page {
  size: 4.25 in 3.0 in !important;
   
}  */
body {
 size: 4.25 in 3.0 in !important;	
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
  
    	<%if(session.getAttribute("totalPatientLabelList")!=null)%><% {%>
    			<%ArrayList<Client>totalBarcodeList = (ArrayList<Client>)session.getAttribute("totalPatientLabelList"); %>
    			<%for(Client barcode : totalBarcodeList) %><% {%>
    			<%if(barcode.getIpdid()==null) {
    			
    				barcode.setIpdid("");
    			
    			}%>
    			
    			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 setprintlbl paddinnil oneone">
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" style="width:500px;height:40px;"></img> --%>
	    			<%-- <p style="text-align:center;"><%=barcode.getClientid() %> , <%=barcode.getAge() %> , <%=barcode.getGender() %></p> --%>
	    			<%if(!barcode.getIpdid().equals("")){ %>
	    			<p><label>IPD No:</label>  <%=barcode.getIpdid() %></p>
	    			<%} %>
	    			<p><label>UHID:</label>  <%=barcode.getAbrivationid() %></p>
	    			<p><label>Name:</label>  <%=barcode.getClientName() %></p>
	    			<p><label>Age/Sex:</label>  <%=barcode.getAge1() %>,<%=barcode.getGender() %> (<%=barcode.getWhopay() %>)</p>
	    			<p><label>Doctor:</label> <%=barcode.getDiaryUser() %></p>
	    			<p><label>Mobile:</label>  <%=barcode.getMobNo() %>
					<%if(!barcode.getWeight().equals("")){ %>
	    			,&nbsp;<label>Wt.:</label><%=barcode.getWeight()%>
	    			<%} %>
					</p>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
	    		</div>
	    		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 setprintlbl paddinnil">
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" style="width:500px;height:40px;"></img> --%>
	    			<%-- <p style="text-align:center;"><%=barcode.getClientid() %> , <%=barcode.getAge() %> , <%=barcode.getGender() %></p> --%>
	    			<%if(!barcode.getIpdid().equals("")){ %>
	    			<p><label>IPD No:</label>  <%=barcode.getIpdid() %></p>
	    			<%} %>
	    			<p><label>UHID:</label>  <%=barcode.getAbrivationid() %></p>
	    			<p><label>Name:</label>  <%=barcode.getClientName() %></p>
	    			<p><label>Age/Sex:</label>  <%=barcode.getAge1() %>,<%=barcode.getGender() %> (<%=barcode.getWhopay() %>)</p>
	    			<p><label>Doctor:</label> <%=barcode.getDiaryUser() %></p>
	    			<p><label>Mobile:</label>  <%=barcode.getMobNo() %>
	    			<%if(!barcode.getWeight().equals("")){ %>
	    			,&nbsp;<label>Wt.:</label><%=barcode.getWeight()%>
	    			<%} %>
	    			</p>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
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
        <h4 class="modal-title">Print Label</h4>
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
        <button type="button" onclick="printDiv('printableArea')" class="btn btn-primary hidden">Print</button>
      </div>
    </div>
  </div>
  
  <s:form action="patientlabelIpd" id="frombarcode" theme="simple">
    <s:hidden name="howmany" id="howmany"></s:hidden>
    <s:hidden name="selectedid" ></s:hidden>
    <input type="hidden" value='<s:property value="logipdid"/>' name="labelipdid">
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
      	
       			
	