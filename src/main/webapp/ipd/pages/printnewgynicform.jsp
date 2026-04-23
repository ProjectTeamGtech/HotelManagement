<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<% Bed ipd=(Bed)session.getAttribute("gynicnewbed"); %>
<%
	String check_indications = (String)session.getAttribute("check_indications");
	String check_coamplications = (String)session.getAttribute("check_coamplications");
	String check_institution = (String)session.getAttribute("check_institution");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admission Summary Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <link href="_assets/newtheme/css/main.css" rel="stylesheet" />
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">   
    <script type="text/javascript" src="ipd/js/admissionform.js"></script> 


    <style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
}
        .adformback {
            border: 1px solid;
            padding: 10px 0px 0px;
            margin-top: 0px;
            width: 98%;
            margin-left: 9px;
        }
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0px;
            text-align: right;
            font-size: 12px;
        }
        .marbot15 {
            margin-bottom: 15px;
        }
        .martop15 {
            margin-top: 15px;
        }
        .diagtitle {
            background-color: #000;
            color: #FFF;
            padding: 10px;
            font-weight: normal;
            padding-top: 12px !important;
        }
        .bednosele {
            width: 10%;
            margin-top: -40px;
        }
        .textareaheight{
        height: 50px !important;;
        }
       
       .paddtop{
        padding: 0px 0px 14px 2px;
    	}
        .widthtabhedth1{
        	width: 30%;
        }
        .widthtabhedth2{
        	width: 7%;
        }
        .admissionbackgreen {
		    width: 210px;
		}
		.form-group {
    		margin-top: 4px;
		}
		.pad8{
			padding-top: 8px;
		}
		.backgrey{
			        background-color: rgba(149, 222, 91, 0.19);
		}
		.pnameback{
			    background-color: #f5f5f5;
    			margin-top: -7px;
		}
		.panel-primary {
		    border-color: #339966;
		}
		.padsign{
			padding-top: 100px;
			padding-left:0px;
			padding-right:0px;
		}
		.help-block {
		    display: block;
		    margin-top: 0px !important;
		    margin-bottom: 0px !important;
		    color: #737373;
		}
		 .bordertopgreen1 {
    border-top: 2px solid #339966;
}
  .panel-primary {
      border-color: #339966;
  }
  .padsign{
   padding-top: 40px;
  }
  .help-block {
    display: block;
    margin-top: 0px;
    margin-bottom: 0px;
    color: #737373;
}
h3, .h3 {
    font-size: 16px;
    font-weight: bold;
    color: #6699cc;
    margin-top: 0px;
    margin-bottom: 5px;
}
.form-group {
    margin-bottom: 0px !important;
}

p {
    margin: 0 0 5.5px !important;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.settopbac {
    background-color: #ddd;
}
.totalbor {
    background-color: #f5f5f5;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 5px 2px 5px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 12px;
    border-right: none !important;
    border-left: none !important;
}

 @media print
{
    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
	}
	p {
	    margin: 0 0 5.5px !important;
	    font-size: 9px !important;
	}
	.form-group {
    margin-bottom: 0px !important;
}

.titleset {
    color: blank !important;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    font-weight: bold !important;
    line-height: 20px;
    padding: 2px 0px 0px 6px !important;
    background-color: #efefef !important;
    -webkit-print-color-adjust: exact !important; 
}
h4, .h4 {
    font-size: 12px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:12px !important;
	color: firebrick !important;
}

.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}
}
    </style>
    <style>

.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 135px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 12px;
    font-weight: bold;
}
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
.titleset{
	margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
    background-color: #efefef;
     
}
label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: bold;
    
}
td, th {
    padding: 0px 3px 0px 5px !important;
    border-right: 1px solid #eee !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
}
.form-group {
    margin-top: 0px;
}

</style>
<script type="text/javascript">
	$(document).ready(function() {
		var treatmentadvice_input = document.getElementById("treatmentadvice_input").value;
		if(treatmentadvice_input=='0'){
			document.getElementById("treatmentadvicediv").className="hidden";
		}
		var ps_input = document.getElementById("ps_input").value;
		if(ps_input=='0'){
			document.getElementById("psdiv").className="hidden";
		}
		
		var immunization_input = document.getElementById("immunization_input").value;
		if(immunization_input=='0'){
			document.getElementById("immunization_div").className="hidden";
		}
		
		var pv_input = document.getElementById("pv_input").value;
		if(pv_input=='0'){
			document.getElementById("pv_div").className="hidden";
		}
		var hrf_input = document.getElementById("hrf_input").value;
		if(hrf_input=='0'){
			document.getElementById("hrf_div").className="hidden";
		}
		
		var obstretichistory_input = document.getElementById("obstretichistory_input").value;
		if(obstretichistory_input=='0'){
			document.getElementById("obstretichistory_div").className="hidden";
		}
		
		var menstrual_input = document.getElementById("menstrual_input").value;
		if(menstrual_input=='0'){
			document.getElementById("menstrual_div").className="hidden";
		}
		
		var examination_input = document.getElementById("examination_input").value;
		if(examination_input=='0'){
			document.getElementById("examination_div").className="hidden";
		}
		
		var pa_input = document.getElementById("pa_input").value;
		if(pa_input=='0'){
			document.getElementById("pa_div").className="hidden";
		}
		
		var erlierinv_input = document.getElementById("erlierinv_input").value;
		if(erlierinv_input=='0'){
			document.getElementById("erlierinv_div").className="hidden";
		}
		if(document.getElementById("pressentation_input")){
			var pressentation_input = document.getElementById("pressentation_input").value;
			if(pressentation_input=='0'){
				document.getElementById("pressentationdiv").className="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden";
			}
		}
		
		
	});
	
</script>

<s:form action="decIpd" id="decfrmid">
	<s:hidden name="hdndecid" id="hdndecid"/>
	<s:hidden name="ipdid" id="ipdid"/>
</s:form>

</head>
<body>


<%

   String hstry="";
   String sysreview="";
   String obstretic_history="";
   String menstrual_history="";
   String substance_history="";
   
%>



<s:form action="updateIpd" theme="simple" id="ipdadmissionfrm">
<s:hidden name="treatmentepisodeid" id="treatmentepisodeid"/>
	<s:hidden name="id" id="id"/>
	
	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
		<div class=" letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
			
		</div>
	</div>
	
	<div class="">
	
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								
								
								<s:if test="formtype==1">
									
									<b class="setticolors">Obstretics Assessment Form</b>  
								
								</s:if>
								<s:elseif test="formtype==2">
										<b class="setticolors">Gynaecology Assessment Form</b>
								
								</s:elseif>
								<s:elseif test="formtype==3">
										<b class="setticolors">Infertility Assessment Form</b>
								
								</s:elseif>
								<s:elseif test="formtype==4">
										<b class="setticolors">Obstetrics Assessment Form</b>
								
								</s:elseif>
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
					</div>
					</div>
				</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">UHID </b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Patient Name</b>
							</div>
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Age/Gender</b>
							</div>
							
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="abrivationid"/></span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="client"/> </span>
							</div>
							
							 <div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="agegender" /> </span>
							</div>
							
						</div>
						
						
						
							
						
						
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Date</b>
							</div>
							<s:if test="contact!=''">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									  <b for="inputEmail3" class="control-label">Contact</b>
								</div>
							</s:if>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="date"/> </span> 
							</div>
							<s:if test="contact!=''">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									  <span>: <s:property value="contact"/></span>
								</div>
							</s:if>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="patientaddress"/> </span> 
							</div>
						</div>
						
					</div>
					</div>
				</div>
				
				<div class="">
				
					<div class="">
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding:10px 0px 0px 0px;">
					   <s:if test="%{allVisitReasonList.isEmpty()}">
					   </s:if>
					   <s:else>
					  
					   	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">REASON FOR VISIT </h4>
	                        </div>
	                        <div class="row ">
	                            <s:iterator value="allVisitReasonList">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	      <table class="">
	                        	      	<tr>
	                        	      		<td style="text-transform: uppercase;"><b><s:property value="reasonvisit"/></b></td>
	                        	      		<td></td>
	                        	      	</tr>
	                        	      	<s:if test="since!=''">
		                        	      	<tr>
		                        	      		<td><b>Since :</b></td>
		                        	      		<td><s:property value="since"/></td>
		                        	      	</tr>
	                        	      	</s:if>
	                        	      	<s:if test="notes!=''">
		                        	      	<tr>
		                        	      		<td><b>Note :</b></td>
		                        	      		<td><s:property value="notes"/></td>
		                        	      	</tr>
	                        	      	</s:if>
	                      	      		<%int resonother=0; %>
	                      	      		<s:if test="perceives==1">
	                      	      			<%resonother=1; %>
										</s:if>
										<s:if test="notperceives==1">
											<%resonother=1; %>
										</s:if>
										<s:if test="decreased==1">
											<%resonother=1; %>
										</s:if>
										<%if(resonother==1){ %>
											<tr>
		                        	      		<td></td>
		                        	      		<td>
		                        	      			<s:if test="perceives==1">
														<b>Perceives </b>&nbsp;
													</s:if>
													<s:if test="notperceives==1">
														<b>Not Perceives</b>&nbsp;
													</s:if>
													<s:if test="decreased==1">
														<b>Decreased</b>&nbsp;
													</s:if>
		                        	      		</td>
	                        	      		</tr>
										<%} %>
	                        	      
	                        	      </table>
	                        	</div>
	                        	
	                        	
	                        </div>
	                       
	                        </s:iterator>
					</div>
					 </s:else>
				</div>
			</div>
			<div id="hrf_div" class=""> 
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
            	<h4 class="text-left titleset" style="color:#6699cc;">HIGH RISK FACTOR</h4>
            </div>
			<div class=" " >
	        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                   	      <div class="form-group">
                   	      	 <%int hrf=0; %>
                   		  	 <s:if test="hrf_gdm==1">
                   		  	 	GDM
				             	<%hrf=1; %>
				             </s:if>
				             <s:if test="hrf_pih==1">
				             	<%if(hrf==1){ %>
				             		, PIH
				             	<%}else{ %>
				             		PIH
				             	<%} %>
				             	<%hrf=1; %>
				             </s:if>
				             <s:if test="hrf_boh==1">
				             	<%if(hrf==1){ %>
				             		, BOH
				             	<%}else{ %>
				             		BOH
				             	<%} %>
				             	<%hrf=1; %>
							 </s:if>
							 <s:if test="hrf_hypothyroid==1">
							 	<%if(hrf==1){ %>
							 		, HYPOTHYROID
				             	<%}else{ %>
				             		HYPOTHYROID
				             	<%} %>
							 	<%hrf=1; %>
							 </s:if>
							 <s:if test="hrf_hyperthyroid==1">
							 	<%if(hrf==1){ %>
				             		, HYPERTHYROID
				             	<%}else{ %>
				             		HYPERTHYROID
				             	<%} %>
							 	<%hrf=1; %>
							 </s:if>
							 <s:if test="hrf_prev_lscs==1">
							 	<%if(hrf==1){ %>
							 		, PREV LSCS
				             	<%}else{ %>
				             		PREV LSCS
				             	<%} %>
							 	<%hrf=1; %>
							 </s:if>
							 <s:if test="hrf_rh_negative==1">
							 	<%if(hrf==1){ %>
							 		, RH NEGATIVE
				             	<%}else{ %>
				             		RH NEGATIVE
				             	<%} %>
							 	<%hrf=1; %>
							 </s:if>
							 <s:if test="hrf_other==1">
							 	<%if(hrf==1){ %>
							 		<s:if test="hrf_otherval!=''">
							 			, <s:property value="hrf_otherval"/>
							 		</s:if>
							 	<%}else{ %>
							 		<s:if test="hrf_otherval!=''">
							 			<s:property value="hrf_otherval"/>
							 		</s:if>
							 		<%hrf=1; %>
				             	<%} %>
							 </s:if>
							 
							 <input type="hidden" id="hrf_input" value="<%=hrf%>">
                   		  </div>
                </div>
	        </div>	
	        </div>	
	        <div class="" id="obstretichistory_div">
	        	<%int obstretichistory=0; %>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding:10px 0px 0px 0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">OBSTRETIC HISTORY</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<%if(DateTimeUtils.isNull(ipd.getNulligravida()).equals("1")){ %>
	                        				<div class="form-group">
	                        				 	<label for="exampleInputName2" style="text-transform: uppercase;">Nulligravida </label> 
	                        				</div>
	                        				<%obstretichistory=1; %>
	                        			<%} %>
	                        			
	                        			<%if(DateTimeUtils.isNull(ipd.getNulligravida()).equals("1")){ %>
	                        				<div class="form-group">
	                        				 	<label for="exampleInputName2" style="text-transform: uppercase;">Currently Pregnant </label> 
	                        				</div>
	                        				<%obstretichistory=1; %>
	                        			<%} %>
	                        			
	                        			<%if(DateTimeUtils.isNull(ipd.getNulligravida()).equals("1")){ %>
	                        				<div class="form-group">
	                        				 	<label for="exampleInputName2" style="text-transform: uppercase;">IUD </label> 
	                        				</div>
	                        				<%obstretichistory=1; %>
	                        			<%} %>
	                        	</div>
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<%if(DateTimeUtils.isNull(ipd.getNulligravida()).equals("1")){ %>
		                        			<div class="form-group">
		                        				<span>Yes </span> 
		                        			</div>
		                        			<%obstretichistory=1; %>
	                        			<%} %>
	                        			<%if(DateTimeUtils.isNull(ipd.getNulligravida()).equals("1")){ %>
	                        				<div class="form-group">
	                        					<span>Yes </span> 
	                        				</div>
	                        				<%obstretichistory=1; %>
	                        			<%} %>
	                        			<%if(DateTimeUtils.isNull(ipd.getNulligravida()).equals("1")){ %>
	                        				<div class="form-group">
	                        					<span>Yes </span> 
	                        				</div>
	                        				<%obstretichistory=1; %>
	                        			<%} %>
	                        	</div>
	                        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px">
	                        			<div class="form-group">
	                        				<%if(!DateTimeUtils.numberCheck(ipd.getLive_boys()).equals("0")){ %>
		                        				 <label for="exampleInputName2">
		                        				 	Live Boys <span style="font-weight: normal;"><%=ipd.getLive_boys() %></span>
		                        				 </label>,
		                        				 <%obstretichistory=1; %>
	                        				 <%} %>
	                        				 <%if(!DateTimeUtils.numberCheck(ipd.getLive_girls()).equals("0")){ %>
		                        				 <label for="exampleInputName2">
		                        				 	Live Girls <span style="font-weight: normal;">: <%=ipd.getLive_girls() %></span>
		                        				 </label>,
		                        				  <%obstretichistory=1; %>
	                        				 <%} %>
	                        				 <%if(!DateTimeUtils.numberCheck(ipd.getStillbirths()).equals("0")){ %>
		                        				 <label for="exampleInputName2">
		                        				 	Still Births <span style="font-weight: normal;">: <%=ipd.getStillbirths() %></span>
		                        				 </label>, 
		                        				<%obstretichistory=1; %>
	                        				 <%} %>
	                        				 <%if(!DateTimeUtils.numberCheck(ipd.getAbortions()).equals("0")){ %>
		                        				 <label for="exampleInputName2">
		                        				 	Abortions <span style="font-weight: normal;">: <%=ipd.getAbortions() %></span>
		                        				 </label>, 
	                        				 	<%obstretichistory=1; %>
	                        				 <%} %>
	                        				 <%if(!DateTimeUtils.numberCheck(ipd.getDeath_children()).equals("0")){ %>
		                        				 <label for="exampleInputName2">
		                        				 	Death Child <span style="font-weight: normal;">: <%=ipd.getDeath_children() %></span>
		                        				 </label>  
		                        				<%obstretichistory=1; %>
	                        				 <%} %>
	                        			</div>
	                        			<div class="form-group">
	                        				<%if(!DateTimeUtils.numberCheck(ipd.getG()).equals("0")){ %>
		                        				  <label for="exampleInputName2">
		                        				  	G <span style="font-weight: normal;"><%=DateTimeUtils.numberCheck(ipd.getG())%></span>
		                        				  </label>,
	                        					<%obstretichistory=1; %>
	                        				<%} %>
	                        				<%if(!DateTimeUtils.numberCheck(ipd.getP()).equals("0")){ %>
	                        				  <label for="exampleInputName2">
	                        				  	P <span style="font-weight: normal;"><%=ipd.getP()%></span>
	                        				  </label>, 
	                        					<%obstretichistory=1; %>
	                        				<%} %>
	                        				<%if(!DateTimeUtils.numberCheck(ipd.getL()).equals("0")){ %>
	                        				  <label for="exampleInputName2">
	                        				  	L <span style="font-weight: normal;">: <%=ipd.getL() %></span>
	                        				  </label>, 
	                        				 <%obstretichistory=1; %>
	                        				<%} %>
	                        				<%if(!DateTimeUtils.numberCheck(ipd.getA()).equals("0")){ %>
	                        				  <label for="exampleInputName2">
	                        				  	A <span style="font-weight: normal;">: <%=ipd.getA() %> </span>
	                        				  </label>, 
	                        				  <%obstretichistory=1; %>
	                        				<%} %>
	                        				<%if(!DateTimeUtils.numberCheck(ipd.getD()).equals("0")){ %>
	                        				  <label for="exampleInputName2">
	                        				  	D <span style="font-weight: normal;">: <%=ipd.getD() %> </span>
	                        				  </label>  
	                        				  <%obstretichistory=1; %>
	                        				<%} %>
	                        			</div>
	                        	</div>
	                        	</div>
	                        	</div>
	                        	<%int i=0; %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	  <s:if test="gynicobsList.size>0">
	                        	  
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<h5 style="color: brown;">Sequence of Parity/Abortions</h5>
	                        				<table class="table" style="border: 1px solid #eee;width: 100%;">
	                        					<thead>
	                        						<tr>
	                        							<th>Sr.No</th>
	                        							<th>Year</th>
	                        							<th>Child</th>
	                        							<th>Type of Delivery</th>
	                        							<%if(check_indications.equals("1")){ %>
	                        								<th>Indications</th>
	                        							<%} %>
	                        							<%if(check_coamplications.equals("1")){ %>
	                        								<th>Complications</th>
	                        							<%} %>
	                        							<%if(check_institution.equals("1")){ %>
	                        								<th>Institutions</th>
	                        							<%} %>
	                        							
	                        						</tr>
	                        					</thead>
	                        					<tbody>
	                        					    <s:iterator value="gynicobsList">
	                        						<tr style="border-bottom: 1px solid #eee;">
	                        							<td style="width: 4%;padding-right: 15px;"> <%=i+1 %></td>
	                        							<td style="width: 5%;padding-right: 15px;"><s:property value="year"/></td>
	                        							<td style="width: 13%;padding-right: 15px;"><s:property value="type"/></td>
	                        							<td style="width: 14%;padding-right: 15px;">
															<s:property value="type_delivery"/>
	                        							</td>
	                        							<%if(check_indications.equals("1")){ %>
	                        								<td style="width: 20%;padding-right: 15px;"><s:property value="indications"/></td>
	                        							<%} %>
	                        							<%if(check_coamplications.equals("1")){ %>
	                        								<td style="width: 20%;padding-right: 15px;"><s:property value="coamplications"/></td>
	                        							<%} %>
	                        							<%if(check_institution.equals("1")){ %>
	                        								<td style="width: 20%;padding-right: 15px;"><s:property value="institution"/></td>
	                        							<%} %>
	                        						</tr>
	                        						<%i++; %>
	                        						</s:iterator>
	                        					</tbody>
	                        				</table>
	                        		</div>
	                        		</s:if>
	                        		 <s:if test="parity_abortion_notes!=''">
	                        		 <%obstretichistory=1; %>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Parity/Abortion Notes</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><s:property value="parity_abortion_notes"/> </span> 
	                        			</div>
	                        		</div>
	                        		</div>
	                        	</s:if>
	                        	</div>
	                        </div>
	                        
					</div>
					<input type="hidden" id="obstretichistory_input" value="<%=obstretichistory%>">
				</div>
				
				
				<div class="" style="padding-top: 10px;" id="menstrual_div">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
						<%int menstrual=0; %>
					    <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<h4 class="text-left titleset" style="text-transform: uppercase;color:#6699cc;">MENSTRUAL H/O</h4>
	                        	</div>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<%if(!DateTimeUtils.isNull(ipd.getLmp()).equals("")){ %>
	                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                        	     		<b>LMP :</b> <s:property value="lmp"/>
	                        	     	</div>
	                        	     	 <%menstrual=1; %>
	                        		<%} %>	 
	                        		<%if(!DateTimeUtils.isNull(ipd.getEdd()).equals("")){ %>
	                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                        	     		<b>EDD :</b> <s:property value="edd"/>
	                        	    	 </div>
	                        	    	 <%menstrual=1; %>
	                        		<%} %>	
	                        		<%if(!DateTimeUtils.isNull(ipd.getPog()).equals("")){ %>
	                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
		                        	     	<b>POG :</b> <s:property value="pog"/>
		                        	     </div>
		                        	     <%menstrual=1; %>
	                        		<%} %>	
	                        		<%if(!DateTimeUtils.numberCheck(ipd.getPmc()).equals("0")){ %>
	                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
		                        	     	<b>PMC :</b> <s:property value="pmc"/>
		                        	     </div>
		                        	     <%menstrual=1; %>
	                        		<%} %>	
	                        	</div>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<%if(!DateTimeUtils.isNull(ipd.getDating_usg_date()).equals("")){ %>
	                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                        	     		<b>Dating USG Date :</b> <s:property value="dating_usg_date"/>
	                        	     	</div>
	                        	     	<%menstrual=1; %>
	                        		<%} %>	 
	                        		<%if(!DateTimeUtils.isNull(ipd.getPog_dating_scan()).equals("")){ %>
	                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                        	     		<b>POG by Dating Scan :</b> <s:property value="pog_dating_scan"/>
	                        	    	 </div>
	                        	    	 <%menstrual=1; %>
	                        		<%} %>	
	                        	</div>
	                        	
	                        	<s:if test="usg_report!=''">
	                        		<%menstrual=1; %>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">USG Report</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><s:property value="usg_report"/> </span> 
	                        			</div>
	                        		</div>
	                        		</div>
	                        	</s:if>
	                        		
	                        		<input type="hidden" id="menstrual_input" value="<%=menstrual%>">
	                      </div>
					
					</div>
				</div>	
				
				<s:if test="surgical_ho!=''">
					<div class="" style="padding-top: 10px;">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
						
						    <div class="row ">
		                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        	      
		                        	      <h4 class="text-left titleset" style="text-transform: uppercase;color:#6699cc;">Surgical History</h4>
		                        	</div>
		                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        	      <%=ipd.getSurgical_ho() %>
		                        	</div>
		                        		
		                      </div>
						
						</div>
					</div>	
				</s:if>
				<div class="" style="padding-top: 10px;" id="immunization_div">
					<%int immunization_div=0; %>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
					
					    <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	      <h4 class="text-left titleset" style="text-transform: uppercase;color:#6699cc;">Immunization</h4>
	                        	</div>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        	    <s:if test="tt_dose1!=''">
			                        	    <div class="col-lg-3 col-xs-3 col-md-3"
											style="padding-left: 0px;">
												<div class="form-group">
													<label>Inj. TT 1st Dose:</label>
													<s:property value="tt_dose1"/>
													<%immunization_div=1; %>
												</div>
											</div>
										</s:if>
										<s:if test="tt_dose2!=''">
											<div class="col-lg-3 col-xs-3 col-md-3">
												<div class="form-group">
													<label>Inj. TT 2nd Dose:</label>
													<s:property value="tt_dose2"/>
													<%immunization_div=1; %>
												</div>
											</div>
										</s:if>
										<s:if test="influenza_vaccine!=''">
											<div class="col-lg-3 col-xs-3 col-md-3">
												<div class="form-group">
												<label>Influenza Vaccine:</label>
												<s:property value="influenza_vaccine"/>
												<%immunization_div=1; %>
												</div>
											</div>
										</s:if>
		                        	</div>
	                        	</div>
	                        	<input type="hidden" id="immunization_input" value="<%=immunization_div%>">
	                      </div>
					
					</div>
				</div>	
				<div class="" id="erlierinv_div">
					<%int erlierinv_div=0; %>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;padding-top: 10px;">
	                  	<h4 class="text-left titleset" style="color:#6699cc;text-transform: uppercase;">Earlier Investigations</h4>
	                </div>
					<div class="col-lg-12 col-xs-12 col-md-12" style="padding-bottom: 15px;">
						<div class="col-lg-8 col-xs-8 col-md-8" style="padding-bottom: 15px;">
							<table class=" " style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
							  		<%if(!(DateTimeUtils.isNull(ipd.getDate1()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getDate2()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getDate3()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getDate4()).equals(""))){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">Date</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDate1())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getDate1()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDate2())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getDate2()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDate3())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getDate3()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDate4())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getDate4()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
										</tr>
									<%} %>
									<%if(!(DateTimeUtils.isNull(ipd.getHb1()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getHb2()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getHb3()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getHb4()).equals(""))){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">Hb</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getHb1())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getHb1()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getHb2())%> </span>
												<%if(!DateTimeUtils.isNull(ipd.getHb2()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %> 
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getHb3())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getHb3()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getHb4())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getHb4()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											
										</tr>
									<%} %>
									<%if(!(DateTimeUtils.isNull(ipd.getFbs1()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getFbs2()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getFbs3()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getFbs4()).equals(""))){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">FBS</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getFbs1())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getFbs1()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getFbs2())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getFbs2()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getFbs3())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getFbs3()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getFbs4())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getFbs4()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											
										</tr >
									<%} %>
									<%if(!(DateTimeUtils.isNull(ipd.getDpbs1()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getDpbs2()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getDpbs3()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getDpbs4()).equals(""))){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">PPBS</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDpbs1())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getDpbs1()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDpbs2())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getDpbs2()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDpbs3())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getDpbs3()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDpbs4())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getDpbs4()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											
										</tr>
									<%} %>
									<%if(!(DateTimeUtils.isNull(ipd.getUrm1()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getUrm2()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getUrm3()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getUrm4()).equals(""))){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">U &lt; R M</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getUrm1())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getUrm1()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getUrm2())%> </span>
												<%if(!DateTimeUtils.isNull(ipd.getUrm2()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %> 
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getUrm3())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getUrm3()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getUrm4())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getUrm4()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											
										</tr>
									<%} %>
									<%if(!(DateTimeUtils.isNull(ipd.getTsh1()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getTsh2()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getTsh3()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getTsh4()).equals(""))){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">TSH</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getTsh1())%> </span>
												<%if(!DateTimeUtils.isNull(ipd.getTsh1()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getTsh2())%> </span>
												<%if(!DateTimeUtils.isNull(ipd.getTsh2()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %> 
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getTsh3())%> </span>
												<%if(!DateTimeUtils.isNull(ipd.getTsh3()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %> 
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getTsh4())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getTsh4()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											
										</tr>
									<%} %>
									<%if(!(DateTimeUtils.isNull(ipd.getIct1()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getIct2()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getIct3()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getIct4()).equals(""))){ %>
											<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
												<td class="tabletdwidthnborderzero">
													<label for="exampleInputName2" style="text-transform: uppercase;">ICT</label>
												</td>
												<td class="tabletdwidthnborderzero"> 
													<span> <%=DateTimeUtils.isNull(ipd.getIct1())%> </span>
													<%if(!DateTimeUtils.isNull(ipd.getIct1()).equals("")){ %>
														<%erlierinv_div=1; %>
													<%} %>
												</td>
												<td class="tabletdwidthnborderzero"> 
													<span> <%=DateTimeUtils.isNull(ipd.getIct2())%> </span> 
													<%if(!DateTimeUtils.isNull(ipd.getIct2()).equals("")){ %>
														<%erlierinv_div=1; %>
													<%} %>
												</td>
												<td class="tabletdwidthnborderzero"> 
													<span> <%=DateTimeUtils.isNull(ipd.getIct3())%> </span> 
													<%if(!DateTimeUtils.isNull(ipd.getIct3()).equals("")){ %>
														<%erlierinv_div=1; %>
													<%} %>
												</td>
												<td class="tabletdwidthnborderzero"> 
													<span> <%=DateTimeUtils.isNull(ipd.getIct4())%> </span>
													<%if(!DateTimeUtils.isNull(ipd.getIct4()).equals("")){ %>
														<%erlierinv_div=1; %>
													<%} %> 
												</td>
												
											</tr>
									<%} %>
									<%if(!(DateTimeUtils.isNull(ipd.getGtt1()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getGtt2()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getGtt3()).equals("") 
							  				&& DateTimeUtils.isNull(ipd.getGtt4()).equals(""))){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">GTT</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getGtt1())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getGtt1()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getGtt2())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getGtt2()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getGtt3())%> </span> 
												<%if(!DateTimeUtils.isNull(ipd.getGtt3()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getGtt4())%> </span>
												<%if(!DateTimeUtils.isNull(ipd.getGtt4()).equals("")){ %>
													<%erlierinv_div=1; %>
												<%} %> 
											</td>
											
										</tr>
							 	<%} %>
							</table>
						</div>
						<div class="col-lg-4 col-xs-4 col-md-4" style="padding-bottom: 15px;">
							<table class=" " style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
							  		<%if(!DateTimeUtils.isNull(ipd.getHv_1m()).equals("")){ %>
								  		<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero"> 
												<label for="exampleInputName2" style="text-transform: uppercase;">HV &lt; 1/M</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getHv_1m())%> </span> 
												<%erlierinv_div=1; %>
											</td>
										</tr>
									<%} %>
									<%if(!DateTimeUtils.isNull(ipd.getHbs_ag()).equals("")){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">HBs Ag</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getHbs_ag())%> </span> 
												<%erlierinv_div=1; %>
											</td>
										</tr>
									<%} %>
									<%if(!DateTimeUtils.isNull(ipd.getVdrl()).equals("")){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">VDRL</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getVdrl())%> </span> 
												<%erlierinv_div=1; %>
											</td>
										</tr >
									<%} %>
									<%if(!DateTimeUtils.isNull(ipd.getHb_srecta()).equals("")){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">SICKLING</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getHb_srecta())%> </span> 
												<%erlierinv_div=1; %>
											</td>
										</tr>
									<%} %>
									<%if(!DateTimeUtils.isNull(ipd.getHb_ac()).equals("")){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">HBA1C</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getHb_ac())%> </span> 
												<%erlierinv_div=1; %>
											</td>
										</tr>
									<%} %>
									<%if(!DateTimeUtils.isNull(ipd.getDuet_markess()).equals("")){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td>
												<label for="exampleInputName2" style="text-transform: uppercase;">Dual Marker</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getDuet_markess())%> </span> 
												<%erlierinv_div=1; %>
											</td>
										</tr>
									<%} %>
									<%if(!DateTimeUtils.isNull(ipd.getTriple()).equals("")){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">Triple Marker</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getTriple())%> </span> 
												<%erlierinv_div=1; %>
											</td>
										</tr>
									<%} %>
									<%if(!DateTimeUtils.isNull(ipd.getQuadrple_maicers()).equals("")){ %>
										<tr style="border-bottom: 1px solid #fbf1f1 !important;border-top: 1px solid #fbf1f1 !important;">
											<td class="tabletdwidthnborderzero">
												<label for="exampleInputName2" style="text-transform: uppercase;">Quradraple Marker</label>
											</td>
											<td class="tabletdwidthnborderzero"> 
												<span> <%=DateTimeUtils.isNull(ipd.getQuadrple_maicers())%> </span> 
												<%erlierinv_div=1; %>
											</td>
										</tr>
							 		<%} %>
							</table>
						</div>
							
				</div>
				<input type="hidden" id="erlierinv_input" value="<%=erlierinv_div%>">
			</div>
			<div class="" id="examination_div">
			<%int examination=0;%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
				<h4 class="text-left titleset" style="color:#6699cc;">EXAMINATION / VITALS</h4>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            	
       				<table class="table" style="border: 1px solid #eee;width: 100%;">
       					<thead>
       						<tr>
       							<th>Genral Condition</th>
       							<th>Temp</th>
       							<th>Pallor</th>
       							<th>Pedal Edema</th>
       							<th>Pulse</th>
       							<th>BMI</th>
       							<th>Height</th>
       							<th>Weight</th>
       							<th>Sys-BP</th>
       							<th>Dia-BP</th>
       						</tr>
       					</thead>
       					<tbody>
       					    <tr style="border-bottom: 1px solid #eee;">
       							<td style="">
       								<%if(!DateTimeUtils.numberCheck(ipd.getGen_condition()).equals("0")){ %>
       									<s:property value="gen_condition"/>
       									<%examination=1; %>
       								<%} %>
       									
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.numberCheck(ipd.getTemp()).equals("0")){ %>	
       									<s:property value="temp"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.numberCheck(ipd.getPallor()).equals("0")){ %>	
       									<s:property value="pallor"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.numberCheck(ipd.getPedal_edema()).equals("0")){ %>	
       									<s:property value="pedal_edema"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.isNull(ipd.getPulse()).equals("")){ %>	
       									<s:property value="pulse"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.isNull(ipd.getBmi()).equals("")){ %>	
       									<s:property value="bmi"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.isNull(ipd.getHeight()).equals("")){ %>	
       									<s:property value="height"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.isNull(ipd.getWeight()).equals("")){ %>	
       									<s:property value="weight"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.isNull(ipd.getSys_bp()).equals("")){ %>	
       									<s:property value="sys_bp"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       							<td style="">
       								<%if(!DateTimeUtils.isNull(ipd.getDia_bp()).equals("")){ %>	
       									<s:property value="dia_bp"/>
       								<%examination=1; %>
       								<%} %>
       							</td>
       						</tr>
       						 
       					</tbody>
       				</table>
       				<input type="hidden" id="examination_input" value="<%=examination%>">
            	
            </div>
            </div>  
            <div class="" id="pa_div">
            <%int pa_div=0; %>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;padding-top: 10px;">
				<h4 class="text-left titleset" style="color:#6699cc;">P/A</h4>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            		<% if(!DateTimeUtils.isNull(ipd.getWall_edema()).equals("")) { %>
         				 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
             				<label for="exampleInputName2">Abdominal Wall Edema : </label>
             			</div>
         			<%} %>
         			
             		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
             			<div class="form-group">
                			     <% if(DateTimeUtils.isNull(ipd.getWall_edema()).equals("0")) { %>
                				 	<span> Absent</span>
                				 	<%pa_div=1; %>
                				 <%} else if(DateTimeUtils.isNull(ipd.getWall_edema()).equals("1")) { %>
                				 	<span> Present</span>
                				 	<%pa_div=1; %>
                				 <%} %> 
                    		</div>
             		</div>
             		<s:if test="pa_soft==1">
             			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
             				<label for="exampleInputName2">Fundal Height : </label>
             			</div>
             		</s:if>
             		<s:else>
       			     	<s:if test="fundal_height!=''">
       			     		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
		             			<label for="exampleInputName2">Fundal Height : </label>
		             		</div>
		             		
       			     	</s:if>
               		</s:else>
             		
             		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
             			<div class="form-group">
               			     <s:if test="pa_soft==1">
               			     	Soft
               			     	<%pa_div=1; %>
               			     </s:if>
               			     <s:else>
               			     	<s:if test="fundal_height!=''">
               			     		<s:property value="fundal_height"/> weeks
               			     		<%pa_div=1; %>
               			     	</s:if>
               			     </s:else>
               			</div>
             		</div>
             		
             		<s:if test="external_ballotment==1">
             			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	             			<b>External Ballotment</b> - Present
	             		</div>
			        </s:if>
            </div>
            <input type="hidden" id="pa_input" value="<%=pa_div%>">
            <s:if test="pa_soft==0">
            	<s:if test="fundal_height!=''">
             	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="pressentationdiv">
	             	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	             			<label for="exampleInputName2">Presentation : </label>
	             	</div>
	             	<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
	             		<%int presentationn=0; %>
            			
		             	
	             		<s:if test="cephalic==1">
	             			<b>Cephalic</b> - <s:property value="cephalicVal"/>
	             			<%presentationn=1; %>
	             		</s:if>
	             		<s:if test="breech==1">
	             			<%if(presentationn==1){ %>
		             			, Breech
			             	<%}else{ %>
			             		Breech
			             	<%} %>
			             	<%presentationn=1; %>
	             		</s:if>
	             		<s:if test="baley_size==1">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Variable</b> - <s:property value="baley_sizeVal"/>
			             	<%}else{ %>
			             		<b>Variable</b> - <s:property value="baley_sizeVal"/>
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="transverse_fhs==1">
	             		 	<%if(presentationn==1){ %>
		             			, Transverse
			             	<%}else{ %>
			             		Transverse
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 
	             		 <s:if test="ps_fhs=='Prsent'">
	             		 	<%if(presentationn==1){ %>
		             			, <b>FHS </b>- Present
			             	<%}else{ %>
			             		<b>FHS</b> - Present
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="ps_fhs=='Absent'">
	             		 	<%if(presentationn==1){ %>
		             			,<b>FHS</b> - Absent
			             	<%}else{ %>
			             		<b>FHS</b> - Absent
			             	<%} %>
			             	<%presentationn=1; %>	
	             		 </s:if>
	             		 <s:if test="ps_fhs=='Regular'">
	             		 	<%if(presentationn==1){ %>
		             			, <b>FHS</b> - Regular
			             	<%}else{ %>
			             		<b>FHS</b> - Regular
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="ps_fhs=='Irregular'">
	             		  	<%if(presentationn==1){ %>
		             			, <b>FHS</b> - Irregular
			             	<%}else{ %>
			             		<b>FHS</b> - Irregular
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="liquor=='Adequate'">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Liquor</b> - Adequate
			             	<%}else{ %>
			             		<b>Liquor</b> - Adequate
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="liquor=='Reduced'">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Liquor</b> - Reduced
			             	<%}else{ %>
			             		<b>Liquor</b> - Reduced
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="liquor=='More'">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Liquor</b> - More
			             	<%}else{ %>
			             		<b>Liquor</b> - More
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="uterine_contractions=='Relaxed'">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Uterine Contractions</b> - Relaxed
			             	<%}else{ %>
			             		<b>Uterine Contractions</b> - Relaxed
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="uterine_contractions=='Irritable'">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Uterine Contractions</b> - Irritable
			             	<%}else{ %>
			             		<b>Uterine Contractions </b>- Irritable
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="uterine_contractions=='Acting'">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Uterine Contractions </b>- Acting
			             	<%}else{ %>
			             		<b>Uterine Contractions</b> - Acting
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 
	             	</div>
             	</div>
             	<input type="hidden" id="pressentation_input" value="<%=presentationn%>">
            </s:if>  	
  			</s:if>
			</div>
			<div class="" id="psdiv">
				<%int psdiv=0; %>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;padding-top: 10px;">
					<h4 class="text-left titleset" style="color:#6699cc;">P/S</h4>
	            </div>
					
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<s:if test="ps_cervix==1">
		             		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
		             			<label for="exampleInputName2">Cervix : </label>
		             		</div>
		             		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
		             			<div class="form-group">
		                			<s:property value="ps_cervixVal"/>	 
		                			<s:if test="ps_cervixVal!=''">
		                				<%psdiv=1; %>
		                			</s:if>    
		                    	</div>
		             		</div>
	             		</s:if>
	             		<s:if test="ps_vagine==1">
		             		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
		             			<label for="exampleInputName2">Vagina : </label>
		             		</div>
		             		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
		             			<div class="form-group">
		                			<s:property value="ps_vagineVal"/>	
		                			<s:if test="ps_vagineVal!=''">
		                				<%psdiv=1; %>
		                			</s:if>       
		                    	</div>
		             		</div>
	             		</s:if>
	             		<s:if test="ps_vault==1">
		             		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
		             			<label for="exampleInputName2">Vault : </label>
		             		</div>
		             		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
		             			<div class="form-group">
		                			<s:property value="ps_vaultVal"/>
		                			<s:if test="ps_vaultVal!=''">
		                				<%psdiv=1; %>
		                			</s:if>       
		                    	</div>
		             		</div>
	             		</s:if>
	             		<input type="hidden" id="ps_input" value="<%=psdiv%>">
	            </div>
            </div>
            <div id="pv_div" class="">
            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;padding-top: 10px;">
				<h4 class="text-left titleset" style="color:#6699cc;">P/V</h4>
            </div>
            
           			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	             		<%int presentationn=0; %>
            			
            			<s:if test="pv_dialated==1">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Dialated</b> - <s:property value="pv_dialatedcm"/> cm
			             	<%}else{ %>
			             		<b>Dialated</b> - <s:property value="pv_dialatedcm"/> cm
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
            			
		             	
	             		<s:if test="pv_trim==1">
	             			<%if(presentationn==1){ %>
		             			, <b>Firm</b>
			             	<%}else{ %>
			             		<b>Firm</b>
			             	<%} %>
			             	<%presentationn=1; %>
	             		</s:if>
	             		<s:if test="pv_unettacced==1">
	             			<%if(presentationn==1){ %>
		             			, Uneffeaced
			             	<%}else{ %>
			             		Uneffeaced
			             	<%} %>
			             	<%presentationn=1; %>
	             		</s:if>
	             		<s:if test="pv_ant==1">
	             		 	<%if(presentationn==1){ %>
		             			, ant
			             	<%}else{ %>
			             		ant
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="pv_tubular==1">		
	             		 	<%if(presentationn==1){ %>
		             			, Tubular
			             	<%}else{ %>
			             		Tubular
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="pv_just_ettacced==1">
	             		 	<%if(presentationn==1){ %>
		             			, Just Effaced
			             	<%}else{ %>
			             		Just Effaced
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="pv_mid_posits==1">
	             		 	<%if(presentationn==1){ %>
		             			, Mid Post
			             	<%}else{ %>
			             		Mid Post
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="pv_soft==1">
	             		 	<%if(presentationn==1){ %>
		             			,Soft
			             	<%}else{ %>
			             		Soft
			             	<%} %>
			             	<%presentationn=1; %>	
	             		 </s:if>
	             		 <s:if test="pv_ettacced==1">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Effaced</b> - <s:property value="effaced_text"/> %
			             	<%}else{ %>
			             		<b>Effaced</b> - <s:property value="effaced_text"/> %
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="pv_post==1">
	             		  	<%if(presentationn==1){ %>
		             			, Post
			             	<%}else{ %>
			             		Post
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 <s:if test="pv_station!=0">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Station</b> - <s:property value="pv_station"/>
			             	<%}else{ %>
			             		<b>Station</b> - <s:property value="pv_station"/>
			             		<%presentationn=1; %>
			             	<%} %>
		             	 </s:if>
		             	 <s:if test="pv_liquor!=0">
			             	<%if(presentationn==1){ %>
		             			, <b>Liquor</b> - <s:property value="pv_liquor"/>
			             	<%}else{ %>
			             		<b>Liquor</b> - <s:property value="pv_liquor"/>
			             		<%presentationn=1; %>
			             	<%} %>
		             	</s:if>
		             	<s:if test="pv_pelvis!=0">
			             	<%if(presentationn==1){ %>
		             			, <b>Pelvis</b> - <s:property value="pv_pelvis"/>
			             	<%}else{ %>
			             		<b>Pelvis</b> - <s:property value="pv_pelvis"/>
			             		<%presentationn=1; %>
			             	<%} %>
		             	</s:if>
		             	<s:if test="pv_position!=''">
		             		<%if(presentationn==1){ %>
	             			, <b>Position</b> - <s:property value="pv_position"/>
			             	<%}else{ %>
			             		<b>Position</b> - <s:property value="pv_position"/>
			             		<%presentationn=1; %>
			             	<%} %>
		             	</s:if>
		             	<s:if test="pv_os==1">
	             		 	<%if(presentationn==1){ %>
		             			, <b>OS</b> - <s:property value="pv_osVal"/> 
			             	<%}else{ %>
			             		<b>OS</b> - <s:property value="pv_osVal"/>
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 
	             		 <s:if test="pv_membrane==1">
	             		 	<%if(presentationn==1){ %>
		             			, <b>Membrane</b> - <s:property value="pv_MVal"/> 
			             	<%}else{ %>
			             		<b>Membrane</b> - <s:property value="pv_MVal"/>
			             	<%} %>
			             	<%presentationn=1; %>
	             		 </s:if>
	             		 
	             		 
	             		 
	             		 	<input type="hidden" id="pv_input" value="<%=presentationn%>">
	             		 
	             	</div>
	            </div>
           </div>
            
            
            <s:if test="diagonosis!=''">
            	<div class="" style="padding-top: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
					
					    <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	      
	                        	      <h4 class="text-left titleset" style="text-transform: uppercase;color:#6699cc;">DIAGNOSIS</h4>
	                        	</div>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	      <s:property value="diagonosis"/>
	                        	</div>
	                        		
	                      </div>
					
					</div>
				</div>	
            </s:if>
            
            <s:if test="additonal_notes!=''">
            	<div class="" style="padding-top: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
					
					    <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	      
	                        	      <h4 class="text-left titleset" style="text-transform: uppercase;color:#6699cc;">Additional Notes</h4>
	                        	</div>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	      <%=ipd.getAdditonal_notes() %>
	                        	</div>
	                        		
	                      </div>
					
					</div>
				</div>	
            </s:if>
            
           	<div class="" style="padding-top: 10px;" id="treatmentadvicediv">
           		<%int treatmentadvicediv=0; %>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
				
				    <div class="row ">
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        	      <h4 class="text-left titleset" style="text-transform: uppercase;color:#6699cc;">Treatment Advised</h4>
                        	</div>
                        	<s:if test="%{gynicPriscList.isEmpty()}">
                        	</s:if>
                        	<s:else>
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        		<div class="form-group">
											<table class="table table-bordered" id="priscTabletreat">
												<thead>
													<tr class="headings">
														<th style="width: 5%;">Sr.No</th>
														<th class="uppercaseirf">Medicine</th>
														<th>Dosage</th>
														<th>Days</th>
														<th>Notes</th>
														<th>Strength</th>
													</tr>
												</thead>
												<tbody id="gynicformtreat">
													<s:iterator value="gynicPriscList">
														<%treatmentadvicediv=1; %>
														<tr>
															<td><%-- <input type="number" class="form-control"
																name="dicpriscmed<s:property value="id"/>"
																value="<s:property value="dispriscsrno"/>"> --%>
																<s:property value="dispriscsrno"/>	
															</td>
															<td><s:property value="mdicinenametxt" /></td>
															<td>
																<%-- <select id="discpriscdose<s:property value="id"/>" name="discpriscdose<s:property value="id"/>" class="form-control chosen-select">
																	<s:iterator value="dosageList">
																		<s:if test="name==priscdose">
																			<option value="<s:property value="name" />" selected="selected"><s:property value="name" /></option>
																		</s:if>
																		<s:else>
																			<option value="<s:property value="name" />"><s:property value="name" /></option>
																		</s:else>
																	</s:iterator>
																</select> --%>
																 <s:property value="priscdose" /> 
															</td>
															<td>
																<%-- <input type="number" class="form-control"
																name="dicpriscdays<s:property value="id"/>"
																value="<s:property value="priscdays"/>"> --%>
																<s:property value="priscdays" />
															</td>
															<td><s:property value="dosenotes" /></td>
															<td><s:property value="strengthnew" /> </td>
															<%-- <td><s:property value="priscindivisualremark" /> </td> --%>
															
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>
                        	
                        	</div>
                        	</s:else>
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        	      <%=ipd.getTreatment_advice() %>
                        	      <s:if test="treatment_advice!=''">
                        	      		<%treatmentadvicediv=1; %>
                        	      </s:if>
                        	</div>
                        		
                      </div>
				
				</div>
				<input type="hidden" id="treatmentadvice_input" value="<%=treatmentadvicediv%>">
			</div>	
            
           
           	<%if(ipd.getInvestigation_advice()!=null){ %>
           		<%if(!(ipd.getInvestigation_advice().equals("") || ipd.getInvestigation_advice().equals("<br>"))){ %>
           			<div class="" style="padding-top: 10px;">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
						
						    <div class="row ">
		                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        	      
		                        	      <h4 class="text-left titleset" style="text-transform: uppercase;color:#6699cc;">Investigations Advised</h4>
		                        	</div>
		                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        	      <%=ipd.getInvestigation_advice() %>
		                        	</div>
		                        		
		                      </div>
						
						</div>
					</div>		
           		<%} %>
           		
           	<%} %>
           
				
				
						<div class="">
	                        <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padsign">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-left" style="padding:0px;">
										
										<span><s:property value="doctor_name"/><br>(<s:property value="qualification"/>)</span><br><span>[FOR <s:property value = "clinicName"/>]</span>
										
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-right" style="padding:0px;">
										<br>
										<span>Name & Signature of Patient / Attendant</span>
										<br><br>
										<span>Printed By: <s:property value="printedBy"/></span>
									</div>
								</div>
	                        <div class="col-lg-12 col-md-12 hidden-print" style="padding: 10px 0px 0px 0px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;">
		                                <a href="editnewgynicformCommonnew?selectedid=<s:property value = "id"/>&action=0" class="btn btn-primary savebtn savebigbtn" style="padding-top: 22px;">Edit</a>
		                                <input type="button" class="btn btn-primary savebtn savebigbtn"  value="Print" onclick="printpage()">
		                                </div>
		                            </div>
		                        </div>
	                        </div>
	                        
	                      </div>
				
				
	
	 
 
</s:form>

 	


<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
                            
                            <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                                <form>
								 <div class="form-group hidden">
                                            <label for="inputEmail" class="control-label">Hospital/Clinic</label>
                                                <p class="help-block"><%=ipd.getHosp() %></p>
                                        </div>
								</form>
                                </div>
                                
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="display:none">
                                                <div class="from-horizontal">


                                                    <div class="form-group col-lg-12 col-md-12 col-xs-12 col-sm-12" style="display:none">
                                                     <div class="col-xs-4 col-md-4 col-lg-4 col-sm-4">
			                                        <label for="inputEmail" class="control-label textright"><b>Suggested Opr.:</b></label>
			                                        </div>
                                                        
                                                        <div class="col-xs-8 col-md-8 col-lg-8 col-sm-8">
                                                              <%=ipd.getSuggestoper() %>
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-lg-12 col-md-12 col-xs-12 col-sm-12" style="display:none">
                                                     <div class="col-xs-4 col-md-4 col-lg-4 col-sm-4">
				                                         <label for="inputEmail" class="control-label textright"><b>Location:</b></label>
				                                        </div>
                                                       
                                                        <div class="col-xs-8 col-md-8 col-lg-8 col-sm-8">
                                                          <%=ipd.getDepartment() %>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                            </div>
                            </div>

</body>

<script>
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
					

</html>
