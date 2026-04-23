
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
    font-size: 15px;
   /*  font-weight: bold; */
    padding-left: 10px;
}
			.paddniltopase{
				padding-top:50px;
			}
			label {
    margin-bottom: 1px;
    font-size: 20px;
    font-style: inherit;
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
 
      
   /*    window.onload = function(){
      
             $('#myModal').modal( "show");       
      }; */
      
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
		  height: 50,
		  width:1,
		  displayValue:true
		});
});
</script>
		
<body  oncontextmenu="return false;" id="printSetting">
  <%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
  
    	<%if(session.getAttribute("totalPatientLabelList")!=null)%><% {%>
    			<%ArrayList<Client>totalBarcodeList = (ArrayList<Client>)session.getAttribute("totalPatientLabelList"); %>
    			<%for(Client barcode : totalBarcodeList) %><% {%>
    			<%if(barcode.getIpdid()==null) {
    			
    				barcode.setIpdid("");
    			
    			}%>
    			<input type="hidden" id="imagedata" value="<%=barcode.getAbrivationid()%>">
    			<div class="" style="width: 500px;height: 189px;" align="left">
    			
    		  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding-top: 20px;margin-left: -30px;">	 
    		   <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 ">   			
                      <label>Name: </label>
                      </div>
                      <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10 "  style="margin-left: -15px;">
                       <label><%=barcode.getClientName() %></label>
                       </div>
              </div>
               
               
               <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding-top: 6px;margin-left: -30px;">	
                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 ">
	    			 <label>Age/Gender:  </label> <%-- <%=barcode.getWhopay() %> --%>
	    			</div>
	    			  <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 " style="margin-left: -35px;">
	    			  <label><%=barcode.getAge1() %>,<%=barcode.getGender() %></label>
	    			</div>
	    	  </div>  
	    	   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding-top: 6px;margin-left: -30px;">	
                <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 ">
	    			 <label>DOB:</label> <%-- <%=barcode.getWhopay() %> --%>
	    			</div>
	    			  <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 " style="margin-left: -60px;">
	    			  <label><%=barcode.getDob() %></label>
	    			</div>
	    	  </div>  
	    	  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding-top: 6px;margin-left: -30px;">	    			
                     <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 ">
                      <label>Address: </label>
                      
                      </div>
                      <div class="col-lg-9 col-md-9 col-xs-9 col-sm-9 " style="margin-left: -20px;">
                      
                      <label> <%=barcode.getAddress() %></label>
                      </div>
              </div>
	    	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding-top: 6px;margin-left: -30px;">
	    	 <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10 ">
	    	     <label>Contact Number: <%=barcode.getMobNo() %></label>
	    	    <!--  </div>
	    	       <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4"> -->
	    	       
	    	       </div>
	    	 </div>
    		  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding-top: 6px;margin-left: -30px;">
	    			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 ">
	    	          <label>UHID:  </label>
	    	          </div>
	    	            <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="margin-left: -20px;">
	    	                <label><%=barcode.getAbrivationid() %></label> 
	    	            </div>
	    		
	    	 </div>
	    		 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="margin-left: -30px;">
	             
	                         <img style="width: 330px;height:65px;margin-left:-9px; " id="barcode"> 
	                        </div>
	    			  <%-- <%if(loginInfo.isAyushman()){ %>
	    			  
	    			 
	                        <!-- <img style="width: 264px;height: 378px;" id="barcode"> -->
	                 <%}else{ %>
	                      <svg id="barcode" ></svg> <!-- @Rahul origanal code -->
	                 <%} %> 	 --%>
	    		<%-- 	<%if(!loginInfo.getClinicUserid().equals("nelson")){ %>
	    			<%if(barcode.getEmergencyContNo()!=null){ %>
	    				<%if(!barcode.getEmergencyContNo().equals("")){ %>
	    					<p><label>Emg:</label> <%=barcode.getEmergencyContNo() %></p>
	    				<%} %>
	    			<%} %>
	    			<%}%> --%>
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
<div id="myModal" class="modal fade hidden-print " role="dialog" >
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
    	
    		<div class="col-lg-12 col-md-12 hidden-print" style="margin-bottom:10px;padding: 0px;">
		                            <div class=""><br>
		                           
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;text-align: right;">
		                                <div class="form-check" style="margin-left: 74px;">
								           <input class="form-check-input" type="checkbox" id="flexCheckChecked" onclick="thermalPrint(this)"> <label class="form-check-label" for="flexCheckChecked">
									           Thermal Print </label>
							                    </div>
<!-- 			                                <a href="#" onclick="sendmail();" class="btn btn-primary savebtn savebigbtn hidekar" style="line-height: 45px;" title="Send Email">Email</i></a>
 -->	                                  		<a href="#" onclick="printpage();" class="btn btn-primary" style="" title="Print">Print</a>
		                                </div>
		                              
		                            </div>
		                        </div>
</body>
<script>
function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}
</script>
      	
      	
      	 
<script>
function thermalPrint(val){
	if(val.checked){
		document.getElementById('printSetting').className="col-lg-10 col-md-10 col-xs-12 thermal";
		document.getElementById('hospital_heading').className="thermal_heading";
	}else{
		document.getElementById('printSetting').className="";	
		document.getElementById('hospital_heading').className="";
	}
}
    function showhide()
     {
           var div = document.getElementById("newpost");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }
  </script>
       			
	