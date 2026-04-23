		<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s" %>
	
	<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
	<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
	<%LoginInfo letterreq = LoginHelper.getLoginInfo(request);%>
<%String logowidth="";
String bghlogo="";
 if(letterreq.isBalgopal()==true && !letterreq.isSaimed()){
	logowidth="logowidth";
	bghlogo="bghlogo";
} 
%>
<%-- <script>
window.onload =function(){ 
	 var clinic=document.getElementById("balgopal").value;
     if(clinic=='true'){
    	 if (window.matchMedia("(orientation: landscape)").matches) {
    		 document.getElementById("mainlogoclinic").className="col-lg-2 col-md-2 col-sm-2 col-xs-2 logoimg bghlogo1";
    		}
    	 
    	 if (window.matchMedia("(orientation: portrait)").matches) {
    			document.getElementById("mainlogoclinic").className="col-lg-2 col-md-2 col-sm-2 col-xs-2 logoimg bghlogo";
    		}
     }
}
</script> --%>
<script>
window.onload =function(){ 
	 var clinic=document.getElementById("vermanh").value;
    if(clinic=='true'){
    	document.getElementById("newpost").style.display="none";
    }
}
</script>


<style>
/* .img {
    float: left;
    width:  150px;
    height: 150px;
    background-size: cover;
} */
 .content p::before{
content:"";
height:120px;
width: 2px;
background: orange;
float: left;
margin-right: 20px;
}
		.logoste{
			width: 100%;
	    	margin-left:auto;
	    	margin-right:auto;
		}
		.logoste1{
			width: 100%;
	    	margin-left:auto;
	    	margin-right:auto;
	    	padding-right: 0px;
		}
		.bghlogo{
    width: 147px !important;
    margin-top: -33px !important;

		}
		.bghlogo1{
    width: 147px !important;
    margin-top: -33px !important;

		}
		@media print {
		
		.bghlogo{
    width: 13% !important;
    margin-top: -5px !important;

		}
		.bghlogo1{
    width: 18% !important;
    margin-top: -5px !important;
	padding-top: 10px !important;
		}
		    .logoste1 {
			    margin-top: 6px !important;
			}
			
			
			#ltterimg{  margin-left: -11px ;}
			
			.logowidth {
			    width: 150% !important;
			}
		}
@media (orientation: landscape) {
 .bghlogo{
    width: 9% !important;
    margin-top: -5px !important;
  }

 
	</style>
	
	
	
	<%if(letterreq.isVermanh()){ %>
		<style>
		 .letterheadhgt{
  height: 160px !important;
  }
		</style>
<%} %>
<%if(letterreq.isLmh()){ %>
<style>

.logoimg{
  margin-left: -15px;
  margin-top: -37px;
  width: 15%;
  }
  
 @media print {
 .logoimg{
   margin-left: -15px;
  margin-top: 0px;
  width: 16.66666667%;
  }
		</style>
<%} %>
   
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="ltterimg">
	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		<input type="hidden" value="<%=letterreq.isBalgopal() %>" id="balgopal">
		<input type="hidden" value="<%=letterreq.isVermanh() %>" id="vermanh">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 logoimg <%=bghlogo %>"  id="mainlogoclinic">
			<div id="newletter" class=" " style="width: 150px;height: 150px;">
			<%System.out.print(letterreq.getIslogo()); %>
			<% if(letterreq.getIslogo().equals("0")){%>
				<img class="img-responsive logoste hidden-print d-print-none" src="liveData/clinicLogo/<s:property value="clinicLogo"/>" />
			
				<% }else{%>
			
				<img class="img-responsive logoste1" src="liveData/clinicLogo/<s:property value="clinicLogo"/>" />
				<% }%>
				</div>
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
		   
			<label class="" style="font-weight: bold; font-size: 15px; margin-left: 120px;"><s:property value = "clinicOwner"/></label><br>
			<label style=" margin-left: 120px;"><%-- <s:property value = "Owner_qualification"/> --%>MBBS,DDV(Mumbai)</label><br>
			<label style=" margin-left: 120px;">Reg.No.:MMC-2014/05/2171</label><br>
			<label style=" margin-left: 120px;"><b>Consultant Dermatologist & Cosmetologist</b></label>
			
	</div>
	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 content">
		   <p>
			<b style="font-size: 15px;"><s:property value = "clinicOwner1"/></b><br>
			<%-- <s:property value = "Owner_qualification1"/> --%>
			MBBS,DCH(Mumbai),<br>
			DNB Pediatrics(Raipur),<br>
			Fellow Paediatric Critical Care(IAP)<br>
			Reg.No.:MMC-2018/05/2400<br>
			<b>Consultant Pediatric Intensivist & Neonatologist</b>
			</p>
			<!-- <div style=" border-left:2px solid maroon; height: 100px;margin-left: 10px;"></div> -->
	</div>
	</div>
			<%LoginInfo lll = LoginHelper.getLoginInfo(request); %>
<%-- 		<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8" id="lttertext"<%if(lll.isBalgopal() && !letterreq.isSaimed()){ %>style="text-align: center;"<%} %>>
	          <%if(letterreq.isKalmegha() || letterreq.isLmh()) {%>
	          <div class="clinicname" id="clinicnamenew">
	          <%}else{ %>
			<div class="clinicname" id="clinicnamenew" style="font-size: 25px;">
			<%} %>
			<% if(lll.isBalgopal() && !letterreq.isSaimed()){%>
				<%String balgopalcname = (String)session.getAttribute("balgopalcname"); %>
				<%balgopalcname= DateTimeUtils.isNull(balgopalcname); %>
				<%=balgopalcname.toString()%>
				
			<%}else{ %>
				<s:property value = "clinicName"/>
			<%} %>
			
			
			
			</div>
			<S:IF TEST="CLINICOWNER!='' ">
				<DIV CLASS="CLIQUALIF"><S:PROPERTY VALUE = "CLINICOWNER"/> <S:PROPERTY VALUE = "OWNER_QUALIFICATION"/> </DIV>
				<s:if test="clinicOwner!='' ">
				<div class="cliqualif" style="font-weight: lighter;"><s:property value = "clinicOwner"/> </div>
			</s:if>
			</S:IF>
			<div class="clicniaddress">
			<% if(lll.isBalgopal() && !letterreq.isSaimed()){%>
				<%String balgopaladdress = DateTimeUtils.isNull((String)session.getAttribute("balgopaladdress")); %>
				<%=balgopaladdress.toString()%>
			<%}else{ %>
			
				<s:iterator value="locationAdressList">
					<s:property value = "address"/> <br>
				</s:iterator>
			<% }%>
				
			</div>
			<% String mobileno=letterreq.getMobileno(); %>
			<div class="bottext">Phone:<s:property value ="landLine"/>,	<%if(letterreq.isBalgopal()){ %> Mobile: 7869920001<%} %><br>
			    Email: <s:property value = "clinicemail"/>,
				<s:property value = "owner_qualification"/>
				<s:if test="websiteUrl!='' ">
					<span style="white-space: nowrap;"> Website: <s:property value = "websiteUrl"/></span>
				</s:if>
				
		    </div>
		    
		</div>
		<%if (letterreq.isBalgopal() && !letterreq.isMatrusevasang()){ %>
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	<img class="img-responsive logoste1" src="liveData/clinicLogo/NABH.png" /> 
	</div>
	<%} %>
		<a href="#"  class="hidden-print d-print-none hidekar" onclick="showhidecliniclogo()" style="margin-right:10px;float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Logo</a>
	</div> --%>
	<script>
    function showhidecliniclogo()
     {
           var div = document.getElementById("newletter");
    if(div.className==""){
    	div.className="hidden";
    }else{
    	div.className="";
    }
    }
  </script>		
	