
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<script type="text/javascript" src="common/JsBarcode.all.min.js"> </script>
    <script src="diarymanagement/js/script.js"></script>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.ArrayList"%>
<%LoginInfo loginInfo= LoginHelper.getLoginInfo(request); %>
		<style>
		p {
    margin: 0 0 0px;
    font-size: 17px;
  /*   font-weight: bold;
    padding-left: 10px; */
}
			.paddniltopase{
				padding-top:37px;
			}
			label {
    margin-bottom: 1px;
    font-size: 20px;
}
		
.setimg{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
        height: 40px;
     }
     
     /*  @media print
   {
      p {
    margin: 0 0 0px;
    font-size: 15px;
    font-weight: bold;
}
.setprintlbl{
	margin-top: 70px;
} */

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
 <script type="text/javascript">
$(document).ready(function() {
	var uhid=document.getElementById('imagedata').value;
	JsBarcode("#barcode", uhid, {
		  format: "CODE128",
		  height: 35,
		  width:2,
		  displayValue:true
		});
});
</script>
		

  <%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
  
    	<%if(session.getAttribute("totalPatientLabelList")!=null)%><% {%>
    			<%ArrayList<Client>totalBarcodeList = (ArrayList<Client>)session.getAttribute("totalPatientLabelList"); %>
    			<%for(Client barcode : totalBarcodeList) %><% {%>
    			<%if(barcode.getIpdid()==null) {
    			
    				barcode.setIpdid("");
    			
    			}%>
    			<input type="hidden" id="imagedata" value="<%=barcode.getAbrivationid()%>">
    			<div class="container">
    			
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" style="width:500px;height:40px;"></img> --%>
	    			<%-- <p style="text-align:center;"><%=barcode.getClientid() %> , <%=barcode.getAge() %> , <%=barcode.getGender() %></p> --%>
	    			<%if(!loginInfo.isBalgopal()){ %>	
	    				<%if(!barcode.getIpdid().equals("")){ %>
	    		    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	   
	    			   <label>IPD No:  <%=barcode.getIpdid() %></label>
	    			</div>
	    			<%} %>
	    		<%} %>
	    		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
	    		<label>UHID:  <%=barcode.getAbrivationid() %></label>
	    		
	    		</div>
	    		
	    		
               <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	    			
                <label>Name:  <%=barcode.getClientName() %></label>
                </div>
                
                
                   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	
                
	    			<label>Age/Sex:  <%=barcode.getAge1() %>,<%=barcode.getGender() %>, <%=barcode.getWhopay() %></label>
	    			
	    			</div>
	    			
	    			<%if(!barcode.getWeight().equals("")){ %>
	    			 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	
	    			  <label>Wt.:<%=barcode.getWeight()%></label>
	    			</div>
	    			<%} %>
	    			 <%-- <label>W/B:</label> <%=barcode.getWardname() %>,<%=barcode.getBedname() %> --%></p>
	    		<%if(!loginInfo.isBalgopal()){ %>
	    		
	    	        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	
	    		      
	    		      <label>Doctor: <%=barcode.getDiaryUser() %></label>
	    		      
                    </div>
	    			
	    			
	    			<%if(!DateTimeUtils.isNull(barcode.getAdmissiondate()).equals("")){ %>
	    			 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	
	    			 <label>DOA: <%=barcode.getAdmissiondate() %></label>
	    			  </div>
	    			 <%} %> 
	    			
	    			 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	
	    			<label>Mob:  <%=barcode.getMobNo() %> </label>
	    			</div>
	    		   <%} %>
	    			<%-- <p><svg id="barcode"></svg></p> --%>
	    			  <%if(!loginInfo.isAyushman()){ %>
	    			  
	    			   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	
	                         <img style="width: 400px;height: 100px;margin-left:-9px;" id="barcode" > 
	                         </div>
	                        <!-- <img style="width: 264px;height: 378px;" id="barcode"> -->
	                 <%}else{ %>
	                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
<%-- 	                     <svg id="barcode" style="width: 300px;"></svg>
 --%>	                      
                    
                    	<img style="width: 300px;height: 100px;margin-left:-9px;" id="barcode" > 
                    
 
                      </div>
	                      <!-- @Rahul origanal code -->
	                 <%} %> 	
	    			<%if(!loginInfo.getClinicUserid().equals("nelson")){ %>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
	    			<%}%>
	    		</div>
	    		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 setprintlbl paddinnil hidden">
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" style="width:500px;height:40px;"></img> --%>
	    			<%-- <p style="text-align:center;"><%=barcode.getClientid() %> , <%=barcode.getAge() %> , <%=barcode.getGender() %></p> --%>
	    <%if(!loginInfo.isBalgopal()){ %>
	    	<%if(!barcode.getIpdid().equals("")){ %>
	    			<p><label>IPD No:</label>  <%=barcode.getIpdid() %></p>
	    			<%} %>
	    <%} %>			
	    			<p><label>UHID:</label>  <%=barcode.getAbrivationid() %></p>
	    			<p><label>Name:</label>  <%=barcode.getClientName() %></p>
	    			<p><label>Age/Sex:</label>  <%=barcode.getAge1() %>,<%=barcode.getGender() %>, <%=barcode.getWhopay() %>
	    			
	    			<%if(!barcode.getWeight().equals("")){ %>
	    			&nbsp;<label>Wt.:</label><%=barcode.getWeight()%>
	    			<%} %>
	    			 <%-- <label>W/B:</label> <%=barcode.getWardname() %>,<%=barcode.getBedname() %> --%></p>
	    		<%if(!loginInfo.isBalgopal()){ %>
	    			<p><label>Doctor:</label> <%=barcode.getDiaryUser() %></p>
	    			<p><%if(!DateTimeUtils.isNull(barcode.getAdmissiondate()).equals("")){ %><label>DOA:</label> <%=barcode.getAdmissiondate() %>&nbsp;<label><%} %>Mob:</label>  <%=barcode.getMobNo() %></p>
	    		<%} %>
	    			<%-- <p><svg id="barcode"></svg></p> --%>
	    			<%if(loginInfo.isAyushman()){ %>
	                    <p> <img style="width: 378px;height: 227px;" id="barcode"> </p>
	                     <!-- <img style="width: 264px;height: 378px;" id="barcode"> -->
	               <%}else{ %>
	                     <svg id="barcode"></svg> <!-- @Rahul origanal code -->
	               <%} %> 	
	    			<%if(!loginInfo.getClinicUserid().equals("nelson")){ %>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
	    			<%}%>
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
      	
       			
	