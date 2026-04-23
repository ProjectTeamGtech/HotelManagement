<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<script type="text/javascript" src="common/JsBarcode.all.min.js"> </script>
<%@taglib uri="/struts-tags" prefix="s" %>



<%@page import="java.util.ArrayList"%>
<%LoginInfo loginInfo= LoginHelper.getLoginInfo(request); %>
		<style>
		p {
    margin: 0 0 0px;
    font-size: 17px;
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
      /* @media print
   {
      p {
    margin: 0 0 0px;
    font-size: 20px;
}
.setprintlbl{
	margin-bottom: 17px;
}
.setprintlbl1{
	margin-bottom: 20px;
	padding: 0px 0px 0px 30px;
}
   }      */
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
    			<%String howmany=(String) session.getAttribute("howmany"); %>
    			<%for(Client barcode : totalBarcodeList) %><% {%>
    			<%if(barcode.getIpdid()==null) {
    			
    				barcode.setIpdid("");
    			
    			}%>
    			<input type="hidden" id="imagedata" value="<%=barcode.getAbrivationid()%>">
    			 <%if(howmany.equals("0")){ %>
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
	    			 <label>DOA:<%=barcode.getAdmissiondate() %></label> 
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
	                      <%-- <svg id="barcode" style="width: 300px;"></svg> --%> <!-- @Rahul origanal code -->
	                      
	                       <img style="width: 300px;height: 100px;margin-left:6px;" id="barcode" > 
	                 <%} %> 	
	    			<%if(!loginInfo.getClinicUserid().equals("nelson")){ %>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
	    			<%}%>
	    		</div>
    			<%} else { %>
    			    <%if(loginInfo.isTotehosp()){ %>
    			        <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 setprintlbl">
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img> --%>
	    			<%-- <p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p> --%>
	    				<%if(!barcode.getIpdid().equals("")){ %>
	    			<p><label>IPD No:</label>  <%=barcode.getIpdid() %></p>
	    			<%} %>
	    			<p><label>UHID:</label>  <%=barcode.getAbrivationid() %></p>
	    			<p><label>Name:</label>  <%=barcode.getClientName() %></p>
	    			<p><label>Age/Sex:</label>  <%=barcode.getAge1() %>,<%=barcode.getGender() %>, <%=barcode.getWhopay() %>
	    			
	    			<%if(!barcode.getWeight().equals("")){ %>
	    			&nbsp;<label>Wt.:</label><%=barcode.getWeight()%>
	    			<%} %>
	    			 <%-- <label>W/B:</label> <%=barcode.getWardname() %>,<%=barcode.getBedname() %> --%></p>
	    			<p><label>Doctor:</label> <%=barcode.getDiaryUser() %></p>
	    			<p><%if(!DateTimeUtils.isNull(barcode.getAdmissiondate()).equals("")){ %><label>DOA:</label> <%=barcode.getAdmissiondate() %>&nbsp;<label><%} %>Mob:</label>  <%=barcode.getMobNo() %></p>
	    			<p><svg id="barcode"></svg></p>
	    			<%if(!loginInfo.getClinicUserid().equals("nelson")){ %>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
	    			<%}%>
    			</div>
    			
    			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 setprintlbl">
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img> --%>
	    			<%-- <p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p> --%>
	    				<%if(!barcode.getIpdid().equals("")){ %>
	    			<p><label>IPD No:</label>  <%=barcode.getIpdid() %></p>
	    			<%} %>
	    			<p><label>UHID:</label>  <%=barcode.getAbrivationid() %></p>
	    			<p><label>Name:</label>  <%=barcode.getClientName() %></p>
	    			<p><label>Age/Sex:</label>  <%=barcode.getAge1() %>,<%=barcode.getGender() %>, <%=barcode.getWhopay() %>
	    			
	    			<%if(!barcode.getWeight().equals("")){ %>
	    			&nbsp;<label>Wt.:</label><%=barcode.getWeight()%>
	    			<%} %>
	    			 <%-- <label>W/B:</label> <%=barcode.getWardname() %>,<%=barcode.getBedname() %> --%></p>
	    			<p><label>Doctor:</label> <%=barcode.getDiaryUser() %></p>
	    			<p><%if(!DateTimeUtils.isNull(barcode.getAdmissiondate()).equals("")){ %><label>DOA:</label> <%=barcode.getAdmissiondate() %>&nbsp;<label><%} %>Mob:</label>  <%=barcode.getMobNo() %></p>
	    			<p><svg id="barcode"></svg></p>
	    			<%if(!loginInfo.getClinicUserid().equals("nelson")){ %>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
	    			<%}%>
    			</div>
    			    
    			  <%} else { %>
    			  
    			<div class="container">   
    	
    		
    		  
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img> --%>
	    			<%-- <p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p> --%>
	    		<%if(!loginInfo.isBalgopal()){ %>
	    				<%if(!barcode.getIpdid().equals("")){ %>
	    				
	    				<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="">
	    			       <label>IPD No: <%=barcode.getIpdid() %></label> 
	    			       </div>
	    		    <%} %>
	    	   <%} %>
	    	   
	    	   <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="">
	    		<label>UHID:  <%=barcode.getAbrivationid() %></label>
                </div>
                 <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="">
	    			<label>Name:  <%=barcode.getClientName() %></label>
	    		 </div>
	    		 
	    		  <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="">
	    		  <label>Age/Sex:  <%=barcode.getAge1() %>,<%=barcode.getGender() %>, <%=barcode.getWhopay() %></label>
	    		</div>
	    			<%if(!barcode.getWeight().equals("")){ %>
	    			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">	
	    			<label>Wt.:<%=barcode.getWeight()%></label>
	    			</div>
	    			<%} %>
	    			 <%-- <label>W/B:</label> <%=barcode.getWardname() %>,<%=barcode.getBedname() %> --%></p>
	    		<%if(!loginInfo.isBalgopal()){ %>
	    		 <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="">
	    			<label>Doctor: <%=barcode.getDiaryUser() %></label>
	    		</div>
	    		  
	    			<%if(!DateTimeUtils.isNull(barcode.getAdmissiondate()).equals("")){ %>
	    			 <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="">
	    			<label>DOA: <%=barcode.getAdmissiondate() %></label>
	    			</div>
	    			<%} %>
	    			
	    			 <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="">
	    			<label>Mob:  <%=barcode.getMobNo() %></label>
	    		  </div>
	    		<%} %>
	    			<%-- <p><svg id="barcode"></svg></p> --%>
	    			<%if(!loginInfo.isAyushman()){ %>
	    			<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="">
	                       <img style="width: 378px;height: 227px;" id="barcode"> 
	                </div>
	                        <!-- <img style="width: 264px;height: 378px;" id="barcode"> -->
	                 <%}else{ %>
	                     <%-- <svg id="barcode" style="width: 300px;"></svg> <!-- @Rahul origanal code --> --%>
	                      <img style="width: 300px;height: 100px;margin-left:6px;" id="barcode" > 
	                 <%} %> 	
	    			<%if(!loginInfo.getClinicUserid().equals("nelson")){ %>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
	    			<%}%>
	    			
    			</div>
    			<br><br>
    			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 setprintlbl hidden">
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img> --%>
	    			<%-- <p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p> --%>
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
	                         <img style="width: 378px;height: 264px;" id="barcode"> 
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
    			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 setprintlbl1 hidden">
	    			<%-- <img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img> --%>
	    			<%-- <p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p> --%>
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
	    			 <%-- <label>W/B:</label> <%=barcode.getWardname() %>,<%=barcode.getBedname() %> --%>
	    			 </p>
	    			<%if(!loginInfo.isBalgopal()){ %>
	    			<label>W/B:</label> <%=barcode.getWardname() %>,<%=barcode.getBedname() %>
	    			<p><label>Doctor:</label> <%=barcode.getDiaryUser() %></p>
	    			<p><%if(!DateTimeUtils.isNull(barcode.getAdmissiondate()).equals("")){ %><label>DOA:</label> <%=barcode.getAdmissiondate() %>&nbsp;<label><%} %>Mob:</label>  <%=barcode.getMobNo() %></p>
	    			<%} %> 
	    			<%-- <p><svg id="barcode"></svg></p> --%>
	    			<%if(loginInfo.isAyushman()){ %>
	                         <img style="width: 378px;height: 227px;" id="barcode"> 
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
    			
    			
    			<%-- <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
	    			<img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img>
	    			<p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p>
	    			<p style="text-align:center;"><%=barcode.getClientName() %> , <%=barcode.getAge() %> , <%=barcode.getGender() %></p>	
	    			<p style="text-align:center;"><%=barcode.getClientid() %> , <%=barcode.getDiaryUser() %></p>
	    			<p style="text-align:center;"><%=barcode.getWhopay() %> , <%=barcode.getMobNo() %></p>
	    			<p style="text-align:center;"><%=barcode.getAdmissiondate() %> , <%=barcode.getWardname() %> , <%=barcode.getBedname() %></p>
    			</div>
    			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
	    			<img src="barcode/<%=barcode.getImageName() %>" class="img-responsive setimg"></img>
	    			<p><%=barcode.getClientName() %>,<%=barcode.getAge() %></p>	
	    			<p><%=barcode.getGender() %>,<%=barcode.getWhopay() %>,<%=barcode.getMobNo() %></p>
	    			<p style="text-align:center;"><%=barcode.getClientName() %> , <%=barcode.getAge() %> , <%=barcode.getGender() %></p>	
	    			<p style="text-align:center;"><%=barcode.getClientid() %> , <%=barcode.getDiaryUser() %></p>
	    			<p style="text-align:center;"><%=barcode.getWhopay() %> , <%=barcode.getMobNo() %></p>
	    			<p style="text-align:center;"><%=barcode.getAdmissiondate() %> , <%=barcode.getWardname() %> , <%=barcode.getBedname() %></p>
    			</div> --%>
    			<%} %>
    		 <%} %>
    
    	
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
        <button type="button" onclick="myFunction()" class="btn btn-primary hidden">Print</button>
      </div>
    </div>

  </div>
  
  <s:form action="patientlabelIpd" id="frombarcode" theme="simple">
    <s:hidden name="howmany" id="howmany"></s:hidden>
    <s:hidden name="selectedid" ></s:hidden>
        
  </s:form>
  
  
  
</div>
    	
<script>
function myFunction() {
    window.print();
}
</script>
      	
 
		
		
	
				
				
				
				
				
				
				
       			
       			
	