<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
    
    
 <%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
 <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
 <link href="common/css/printpreview.css" rel="stylesheet" />
 <link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />  	

<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
		   						

<script type="text/javascript">

document.addEventListener('readystatechange', event => {
	document.cookie = "googtrans=/en/<%=loginInfo.getRegional_lang()%>";
});

function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
}
</script>
<style type="text/css">
.goog-te-banner-frame.skiptranslate {
    display: none !important;
    }
body {
    top: 0px !important;
    min-height: 0px !important;
    }
</style>
<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>  
 


<title>Insert title here</title>
    <style>
    
    @media (max-width: 767px) {
    
    	.rightset {
		        min-height: 200px !important;
		}
		.padsets{
			padding: 50px 15px 0px 15px !important;
		}
		.disinset{
			display: inline-flex;
    		width: 100%;
		}
		.chosen-container {
		    height: 20px !important;
		    width: 100% !important;
		}
		.set100{
			width: 100% !important;
		}
		.qtyset{
			    width: 15% !important;
			    margin-left: 5px;
			    margin-top: -1px;
		}
		.plusbtnse{
			    margin-top: -3px;
    			margin-left: 5px;
		}
    }
        hr {
            margin-top: 0px;
            margin-bottom: 10px;
        }
        .marbot10 {
            margin-bottom: 10px;
        }
        .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
            padding: 1px !important;
            vertical-align: top;
            border-top: 0px solid #DDD !important;
             border: solid 1px #c5c5c5 !important;
        }
        .marleft16 {
            margin-left: -16px;
        }
        .docsig {
            float: right;
            margin-right: -30px;
        }
        .bg-lightred {
    background-color: #e05d6f !important;
}
        
     .rx{
     	width: 39%;
    	margin-bottom: 5px;
     }  
     .pristitle{
         margin-left: -51px;
    	 font-size: 14px;
     }
     .med21 {
    width: 30% !important;
}
.btn-lg, .btn-group-lg>.btn {
    padding: 8px 14px;
    font-size: 15px;
    line-height: 7px;
    border-radius: 0px;
}

.btn-md, .btn-group-lg>.btn {
    padding: 8px 14px;
    font-size: 17px;
    line-height: 7px;
    border-radius: 0px;
}
.hadsetma{
	padding-left: 0px;
    margin-top: 8px;
}
.mabset{
    margin-bottom: 12px;
}
.panamse {
    padding: 0px;
    padding-top: 10px;
    background-color: #f7f7f7;
    margin-top: 0px;
}
h4, .h4 {
    font-size: 16px;
}
.form-group {
    margin-bottom: 9px !important;
}
.print_special { border: 1px solid #43b9be; } 
@media print
{
    .print_special { border: none !important; } 
}
.bobotn{
    border-bottom: none !important;
}
.form-control {
    height: 26px !important;
    padding: 3px !important;
    margin-top: -4px;
}
.chosen-container {
    height: 20px !important;
}
.chosen-container-single .chosen-single {
    display: block;
    transition: none;
    border: none;
    color: #222222;
    font-size: 12px;
    font-weight: normal;
    height: 26px;
    letter-spacing: 0.04em;
    margin-bottom: 0px;
    padding: 0px 0px 0px 5px;
    width: 100%;
    line-height: 25px;
    background-color: #ffffff;
    border: 1px solid rgba(0, 0, 0, 0.1);
    margin-top: -5px;
}
.settdback{
	background-color: #fff !important;color: #555 !important;
}
.blankbx{
	background-color: #f3f9d4 !important;
}
.table > tbody > tr > td, .table > tfoot > tr > td {
    border: solid 1px #c5c5c5 !important; 
}
.rightset{
	    padding: 0px;
    padding-bottom: 10px;
    border-right: 1px solid #ddd;
    min-height: 440px;
}

.returfed{
	    border-top: 1px dashed #ddd;
    border-bottom: 1px solid #ddd;
    padding: 0px;
    padding-top: 13px;
    float: right;
    text-align: right;
}

.table.table {
    margin-top: 0 !important;
    margin-bottom: 0 !important;
    color: RGBA(85, 85, 85, 0.85);
   
}
label {
    font-size: 12px;
}
.searchtab{
	padding:0px;border-bottom: 1px dashed #ddd;padding-bottom: 0px;    padding-bottom: 2px;padding-top: 4px;
}
.balancetext{
	width: 45%;
    float: right;
    height: 31px !important;
    color: #d9534f;
    font-size: 18px;
    text-align: right;
}
.setwismar{
	float: right;
    width: 30%;
    margin-bottom: 0px !important;
    margin-top: 5px;
}

.footerDrawer {
  background-color: #eeeeee;
  width: 100%;
  position: fixed;
  bottom: 0;
}

.footerDrawer .open {
  background-color: #eeeeee;
  padding: 0px 5px 0px 5px;
  text-align: right;
  cursor:pointer;
  height: 15px;
}

.footerDrawer .content {
  height: 28px;
  background-color: #ffffff;
  display: none;
}
.widthsets {
    height: 20px !important;
    width: 650px !important;
}
.liset{
	border-bottom: 1px dashed #ddd;padding: 2px 0px 2px 0px;
}

    </style>
     <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
    
    <!--   16 Aug 2018  -->
    <%String retmultireturndash = (String)session.getAttribute("retmultireturndash"); %>
    <%String retmulticlientid = (String)session.getAttribute("retmulticlientid"); %>
    <%String retmultiispharmacy = (String)session.getAttribute("retmultiispharmacy"); %>
    <%String retmultihdnipdid = (String)session.getAttribute("retmultihdnipdid"); %>
    <%String isfromreturnnurse = (String)session.getAttribute("isfromreturnnurse"); %>
    <%
    	if(retmultireturndash!=null){
    		if(retmultireturndash.equals("")){
        		retmultireturndash="0";
        	}
    	}else{
    		retmultireturndash="0";
    	}
    	
    	if(retmultireturndash.equals("0")){
    		if(isfromreturnnurse!=null){
        		if(isfromreturnnurse.equals("")){
        			isfromreturnnurse="0";
            	}
        	}else{
        		isfromreturnnurse="0";
        	}
		}
    	
    %>
    
    <SCRIPT type="text/javascript">

    $(document).ready(function() {

		$("#date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		 document.addEventListener("contextmenu", function(e){
    		e.preventDefault();
    		}, false); 
		
	});
    
    <% 
    	if(retmultireturndash.equals("1") || isfromreturnnurse.equals("1")){
    %>
	    window.onload= function(){
	    	setInhousePatientInfo(<%=retmulticlientid%>,<%=retmultihdnipdid%>,<%=retmultiispharmacy%>);
		} 
	<%
    	}
	%>
</SCRIPT>
   <style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style> 
    
    <SCRIPT type="text/javascript" src="pharmacy/js/pharmacy.js"></SCRIPT> 
    <script>
	    var timeout = null;
	    function doDelayedReturnSaleSearch(val) {
	      if (timeout) {  
	        clearTimeout(timeout);
	      }
	      timeout = setTimeout(function() {
	    	  checkReturnsaleBarcodeRequest(val); //this is our existing function
	      }, 500);
	    }
    </script>
    
    
    
</head>
   <body>
							<div class="row details mainheader hidden">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Update Sale</h4>
								</div>
							</div>
							<div class="">
							<%-- <div class="hidden-print">
								<ul class="breadcrumb">
									&nbsp;
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getName()%></li>
										<%}else{ %>
											<%if(breadcrumbs.getOn()){ %>
												<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
											<%}else{ %>
												<li><%=breadcrumbs.getName()%></li>
											<%} %>
										<%} %>
										
									<%} %>
								</ul>
							</div> --%>
						
							
							<form action="newpatientsalePharmacy"  method="post" id="creditform">
							<input type="hidden" name="isfromreturndashboard" value="1">
							<input type="hidden" name="newclientid" id="newclientid">
							<input type="hidden" id="returnpage" value="1" />
							<input type="hidden" name="newispharmacy" id="newispharmacy">
							<s:hidden name="clientid" id="clientid"/>
       						<s:hidden name="ispharmacy" id="ispharmacy"/>
							<s:hidden name="hdnphclientid" id="hdnphclientid"/>
       						<s:hidden name="hdnispharmacy" id="hdnispharmacy"/>
       						
       						<s:hidden name="returnrequestid" id="returnrequestid"/>
       						<s:hidden name="returnchargeid" id="returnchargeid"/>
       						
       						<s:hidden id="pharmacymedreturn"></s:hidden>
       						<% if(retmultireturndash.equals("1") || isfromreturnnurse.equals("1")){%>
								 	<s:hidden id="ismultireturnprocess"/>
						    <%}%>
						    <% if(isfromreturnnurse.equals("1")){%>
								 	<s:hidden id="ismultireturnnurceprocess"/>
						    <%}%>
								
							<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
							<div class="col-lg-12 col-md-12 col-sm-12 searchtab">
							<div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
								<label for="inputEmail3" class="text-left">Search :</label>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-8 col-xs-3">
							<div class="form-inline">
								<div class="form-group">
									<%-- <s:select list="pharmapatientlist" theme="simple" name="epname" onchange="selectExternalPatient(this.value)" cssClass="form-control chosen-select" id="epname" listKey="id" listValue="allinfo" headerKey="0" headerValue="Select Existing Patient">
									</s:select>  --%>
									<% if(retmultireturndash.equals("1") || isfromreturnnurse.equals("1")){%>
										<input type="text"  name="inhousepid" id="inhousepid" readonly="readonly" class="form-control"   placeholder="Search Patient" style="width: 100%;"> 	
									<%}else{%>
										<input type="text" name="inhousepid" id="inhousepid" class="form-control" onclick="showInhousePatientPopup()"  placeholder="Search Patient" style="width: 380px;">
									<%}%>
								</div>
								<div class="form-group">
								<% if(retmultireturndash.equals("1") || isfromreturnnurse.equals("1")){%>
									 	
							    <%}else{%>
							    <select class="form-control pharmacyddinput notranslate" autofocus="autofocus" style="width: 100%;background-color: rgba(255, 140, 0, 0.36);" id="returnpaymodeselect" onchange="getModewiseMultiBillList(this.value)">
					                            		<option value="0">Select mode</option>
					                            		<!-- <option value="Cash">CASH</option>
					                            		<option value="Card">CARD</option>
					                            		<option value="Cheque">CHEQUE</option>
					                            		<option value="NEFT">NEFT/RTGS</option>
					                            		<option value="Credit">CREDIT</option>
					                            		<option value="Hospital">HOSPITAL</option> -->
					                            		<option value="other">OTHER</option>
					                            		<option value="Credit">CREDIT</option>
					                            		<option value="Hospital">HOSPITAL</option>
		                           	 				</select>
							    
							    <%} %>
									
								</div>
							</div>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: left;margin-top: -4px; display: inline-flex;">
								<input type="button" value="Refresh" class="btn btn-primary hidden"  onclick="clearAll()" />
								
								<ul class="vertical default_list" id="" style="list-style: none;">
								     <s:if test="tp_bill_access==1">
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="istp" checked="checked" onclick="calsubTotal()" class="tp" ><i></i> Third Party Patient</label></li>								     
								     </s:if>
								     <s:else>
								           <input type="checkbox" id="istp" class="hidden" class="tp" >
								     </s:else>
								</ul>
							</div>
							
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: right;">
							    <span class="payepharmacyi notranslate"> Payee: <span id="payee"></span></span>
								<span><i class="fa fa-square" aria-hidden="true" style="color: #e05d6f;"></i> Narcotics &nbsp; | &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: #e69522;"></i> H1</span>
							</div>
							
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;padding-top: 8px;">
								<div class="col-lg-1 col-md-1 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">BILL NUMBER</label>
										    <p>-</p>
										    <input type="hidden" id="extpid" name="extpid" />
										    <input type="hidden" id="tpid" value="0" name="tpid" />
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label> 
										    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'fullname')" cssClass="form-control" name="fullname" id="fullname" cssStyle="text-transform: uppercase;"/>
								  		</div>
								  		
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">MOBILE</label>
										    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'mobile')" cssClass="form-control" name="mobno" id="mobile" maxlength="10"/>
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">ADDRESS</label>
											    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'address')" cssClass="form-control" name="wardname" id="wardname" cssStyle="text-transform: uppercase;"/>
									  		</div>
									</div>
									
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DR. NAME</label>
										    	<!--<select class="form-control chosen hidden">
										    		<option>Select Doctor</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    	</select>
										      --><s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'reference')" cssClass="form-control" name="practitionerName" id="doctor"  cssStyle="text-transform: uppercase;"/>
								  				<s:hidden name="phar_practid" id="phar_practid"></s:hidden>
								  		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">Payee</label>
										    	<!--<select class="form-control chosen hidden">
										    		<option>Select Doctor</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    	</select>
										      --><s:textfield readonly="true" cssClass="form-control" id="payee" />
								  		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DATE</label>
										     <s:if test="back_date_access==1">
										    	<s:textfield cssClass="form-control" id="date" name="dateTime" /> 
										     </s:if> 
										     <s:else>
										    		 <s:textfield readonly="true" cssClass="form-control" id="date1" name="dateTime" />
										     </s:else>
										    
								  		</div>
									</div>
							</div>
								
								
									
									
									
									
                                </div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									
									<div class="col-lg-9 col-sm-12 col-md-9 col-xs-12 rightset">
									
									<s:hidden name="paymode" id="paymode"></s:hidden>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 padsets" style="background-color: #B8E9EB;padding: 14px 0px 0px 11px;padding-bottom: 0px;">
								         <div class="form-inline disinset">
								         <div class="form-group hidden-xs" style="width: 1%;">
									         	<s:if test="nonsystembarcode==true">
										         	<input type="checkbox" name="nonsystembarcode" id="nonsystembarcode">
										        </s:if>
									         	<s:else>
									         		<input type="checkbox" class="hidden"  name="nonsystembarcode" id="nonsystembarcode">
									         	</s:else>
								         </div>
								         <div class="form-group hidden-xs" style="">
								         		<% if(retmultireturndash.equals("1") || isfromreturnnurse.equals("1")){%>
								         			<!--When its return against nurse return then barcode readonly  -->
													<input type="text" id="barcode" readonly="readonly" class="form-control" placeholder="Barcode Here"  style="width:100%;" /> 	
												<%}else{%>
													<!--When its direct return then barcode is working  -->
													<input type="text" id="barcode"  onkeyup="doDelayedReturnSaleSearch(this.value)" class="form-control" placeholder="Barcode Here"  style="width:100%;" />
												<%}%>
								                
								         </div>
								          <div class="form-group hidden-xs">
								           <p style="font-size: 13px;margin: 0px 0px 2px;">Product :</p> 
								          </div>
								          
								          <div class="form-group set100 notranslate" id="plist">
								          	<% if(retmultireturndash.equals("1")){%>
									            <s:select list="inventoryPriscList" listKey="id" id="newmedicine" listValue="genericname" headerKey="0" headerValue="select product" name="mdicinename" cssClass="form-control chosen-select set100 widthsets">
									            </s:select>  
								            <%}else if(isfromreturnnurse.equals("1")){%>
								            	  <s:select list="inventoryPriscList" listKey="id" id="newmedicine" listValue="genericname"  name="mdicinename" cssClass="form-control chosen-select set100 widthsets">
									            </s:select>  
								            <%} %>
								          </div>
								          <div class="form-group qtyset" style="width: 5%;">
								          		<% if(isfromreturnnurse.equals("1")){%>
								                	<!-- <input type="text" id="qty" readonly="readonly" name="returnqty" class="form-control" placeholder="Qty" style="width:100%;"/> -->
								                	<s:textfield id="qty" readonly="true" name="returnqty" cssClass="form-control" placeholder="Qty" style="width:100%;"></s:textfield>
								                <%}else{%>
								                	<input type="text" id="qty" class="form-control" placeholder="Qty" style="width:100%;"/>
								                <%} %>
								          </div>
								          <!-- <a href="#" onclick="addnewreturnRow('mytable')" title="Add New" class="plusbtnse"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a> -->
								         <a href="#" onclick="addReturnProductRowJson('mytable')" title="Add New" class="plusbtnse"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a>
								         </div>
         								</div>
                                
                                
									<div class="minhesigh">
									  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<table id="mytable" class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="tableback"> 
                                     <th class="pharmacytableheadingfont" style="width: 2%;">Sr. No</th> 
                                        <th class="pharmacytableheadingfont" style="width: 25%;">Name of Drug</th>
                                        <th class="pharmacytableheadingfont" style="width: 5%;">Sale. Qty</th>
                                       <!--  <th style="width: 10%;">HSN No</th> -->
                                       <!--  <th style="width: 12%;">Avail/Batch No/Exp.</th> -->
                                         <th class="text-center pharmacytableheadingfont"  style="width: 5%;">Available</th>
                                        <th class="text-center pharmacytableheadingfont"  style="width: 5%;">Batch No</th>
                                        <th class="text-center pharmacytableheadingfont"  style="width: 5%;">Expiry</th>
                                        <th class="pharmacytableheadingfont" style="width: 4%;">Refund</th>
                                        <th style="width: 5%;" class="text-right pharmacytableheadingfont">Unit Price</th>
                                        <th class="pharmacytableheadingfont" style="width: 4%;text-align: center;">P.Shelf</th>
                                        <th class="pharmacytableheadingfont" style="width: 4%;text-align: center;">S.Shelf</th>
                                        <th style="width: 8%;" class="text-right pharmacytableheadingfont">Total</th>
                                        <th class="pharmacytableheadingfont" style="width: 3%;"></th>
                                        <th class="pharmacytableheadingfont" style="width: 2%;"></th>
                                    </tr>
                                </thead>
                                	<tbody id="innerTableBody" class="notranslate">
                                		<% if(isfromreturnnurse.equals("1")){%>
									    	<%int i=0; %>
	                                		<%int s=0; %>
		                                	<%-- <s:iterator value="priscriptionlist">
		                                    <tr>
		                                   		<td> <%=++s %></td>
		                                   		<td style=color:"+color+" "><s:property value="productname"/><input type="hidden" class="pclass" value="<%=i%>" /> <input type="hidden" name="medicines[<%=i%>].id" id="id<%=i%>"  value="<s:property value="id"/>" /> <input type="hidden" name="medicines[<%=i%>].product_id" id="product_id<%=i%>" value="<s:property value="product_id"/>" /> </td>
		                                   		<td id="labelreq<%=i%>"><s:property value="saleqty"/></td> <input type="hidden" name="medicines[<%=i%>].tempsale" value="<s:property value="returnqty"/>" id="tempsale<%=i%>" />
		                                   				<input type="hidden" id="pur_price<%=i%>" value="<s:property value="purchase_price"/>" /> <input type="hidden" value="<s:property value="pack"/>" id="pack<%=i%>"/> <input type="hidden" name="medicines[<%=i%>].billno" id="billno<%=i%>" value="<s:property value="billno"/>" /> 
		                                   		<td><s:property value="returnqty"/></td>
         	 									<td><s:property value="batch_no"/></td>
          										<td><s:property value="expiry_date"/></td>
		                                   		<td><input type="number" name="medicines[<%=i%>].returnqty" onchange="calRefundTotaltemp()" id="returnqty<%=i%>" value="<s:property value="qty"/>" class="form-control" style="height: 20px !important;background-color: rgba(255, 225, 225, 0.59);"></td>
		                                   		<td><input type="number" value="<s:property value="sale_price"/>" name="medicines[<%=i%>].sale_price" onchange="calRefundTotaltemp()" id="mrp<%=i%>" class="form-control" style="height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);" readonly/> <input type="hidden" value="<s:property value="sale_price"/>" id="sale_price<%=i%>" /> </td>
		                                   		<td style='text-align: center;text-transform: uppercase;'><s:property value="shelf"/> <input type='hidden' name='medicines[<%=i%>].vat' id='vat<%=i%>' value='<s:property value="vat"/>'> </td>
		                                   		<td  class="text-right">Rs.<label id="totalmrp<%=i%>">00.00</label>
		                                   				<input type="hidden" id="prodd<%=i%>" value="<s:property value="stock"/>"/>
		                                   				<input type="hidden" id="discper<%=i%>" name="medicines[<%=i%>].indidiscount" value="<s:property value="discount"/>"/>
		                                   		</td>
		                                   		<td class="text-center"></td>
		                                   		<!-- <td class="text-center"><a href="#" onclick=deleteRowtemp(this)><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td> -->
		                                   		<td class="text-center"></td>
		                                   		<input type="hidden" id="totalrefundrs<%=i%>" name="medicines[<%=i%>].totalrefundrs"  />
		                                   		<input type="hidden" id="tmedicineid<%=i%>" name="medicines[<%=i%>].mdicinenameid" value="<s:property value="medicineid"/>" />    
		                                    	<input type="hidden" id="vat<%=i%>" value="<s:property value="vat"/>" />
		                                    </tr>
		                                    <%i++; %>
		                                    </s:iterator> --%>
		                                    <s:iterator value="priscriptionlist">
		                                    <tr>
		                                   		<td> <%=++s %></td>
		                                   		<td style=color:"+color+" "><s:property value="productname"/>
			                                   		<input type="hidden" class="pclass" value="<s:property value="chargeid"/>" /> 
			                                   		<input type="hidden" name="id<s:property value="chargeid"/>" id="id<s:property value="chargeid"/>"  value="<s:property value="id"/>" /> 
			                                   		<input type="hidden" name="product_id<s:property value="chargeid"/>" id="product_id<s:property value="chargeid"/>" value="<s:property value="product_id"/>" /> 
		                                   		</td>
		                                   		<td id="labelreq<s:property value="chargeid"/>"><s:property value="saleqty"/></td> 
		                                   			<input type="hidden" name="tempsale<s:property value="chargeid"/>" value="<s:property value="returnqty"/>" id="tempsale<s:property value="chargeid"/>" />
		                                   				<input type="hidden" id="pur_price<s:property value="chargeid"/>" value="<s:property value="purchase_price"/>" /> 
		                                   				<input type="hidden" value="<s:property value="pack"/>" id="pack<s:property value="chargeid"/>"/> 
		                                   				<input type="hidden" name="billno<s:property value="chargeid"/>" id="billno<s:property value="chargeid"/>" value="<s:property value="billno"/>" /> 
		                                   		<td><s:property value="returnqty"/></td>
         	 									<td><s:property value="batch_no"/></td>
          										<td><s:property value="expiry_date"/></td>
		                                   		<td><input type="number" name="returnqty<s:property value="chargeid"/>" onchange="changeQtyFromReturn(<s:property value="returnchargeid"/>,<s:property value="chargeid"/>)" id="returnqty<s:property value="chargeid"/>" value="<s:property value="qty"/>" class="form-control" style="height: 20px !important;background-color: rgba(255, 225, 225, 0.59);"></td>
		                                   		<td><input type="number" value="<s:property value="sale_price"/>" name="sale_price<s:property value="chargeid"/>" onchange="calRefundTotaltemp()" id="mrp<s:property value="chargeid"/>" class="form-control" style="height: 20px !important;text-align: right;background-color: rgba(255, 225, 225, 0.59);" readonly/> <input type="hidden" value="<s:property value="sale_price"/>" id="sale_price<s:property value="chargeid"/>" /> </td>
		                                   		<td style='text-align: center;text-transform: uppercase;'><s:property value="shelf"/> <input type='hidden' name='vat<s:property value="chargeid"/>' id='vat<s:property value="chargeid"/>' value='<s:property value="vat"/>'> </td>
		                                   		<td style='text-align: center;text-transform: uppercase;'><s:property value="secondary_shelf"/> </td>
		                                   		<td  class="text-right">Rs.<label id="totalmrp<s:property value="chargeid"/>">00.00</label>
		                                   				<input type="hidden" id="prodd<s:property value="chargeid"/>" value="<s:property value="stock"/>"/>
		                                   				<input type="hidden" id="discper<s:property value="chargeid"/>" name="indidiscount<s:property value="chargeid"/>" value="<s:property value="discount"/>"/>
		                                   		</td>
		                                   		<td class="text-center"></td>
		                                   		<!-- <td class="text-center"><a href="#" onclick=deleteRowtemp(this)><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td> -->
		                                   		<td class="text-center"></td>
		                                   		<input type="hidden" id="totalrefundrs<s:property value="chargeid"/>" name="totalrefundrs<s:property value="chargeid"/>"  />
		                                   		<input type="hidden" id="tmedicineid<s:property value="chargeid"/>" name="mdicinenameid<s:property value="chargeid"/>" value="<s:property value="medicineid"/>" />    
		                                    	<input type="hidden" id="vat<s:property value="chargeid"/>" value="<s:property value="vat"/>" />
		                                    </tr>
		                                    <%i++; %>
		                                    </s:iterator>
								    	<%}%>
                                		<tr></tr>
                                	</tbody>
                                    <input type="hidden" id="tempcount" value="0" />
                                    <s:hidden name="selectedid" ></s:hidden>
                            </table>
						   </div>
						</div>		
								
								
                           
                            
									</div>
									<div class="col-lg-3 col-sm-3 col-md-3 col-xs-3">
										<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;border-bottom: 1px solid #ddd;" id="baldiv">
											<h3 style="color:#d9534f;">Balance : <span style="float: right;" >Rs.<span id="rebalance" class="notranslate"><s:property value="balance"/></span> <input type="hidden" name="balance" id="prebalance" /> </span></h3>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 3px;text-align: right;padding:0px;"><s:hidden id="subbtotal" name="subbtotal" value="0"></s:hidden>
			                            	<h4 style="font-size: 15px;"><a href="#" title="clear Balance" onclick="clearpatientbaldb()" class="btn btn-success btn-sm pull-left hidden">clear balance</a>Sub Total : Rs.<span id="subtotal" class="notranslate"><s:property value="subtotal"/></span></h4>
			                            	<h4 class="hidden" style="color: #868686;font-size: 15px;">Discount <select id="distype" onchange="perorrs()" class="form-control" style="width: 10%;"><option value="0">%</option><option value="1">Rs.</option></select> : <br><small>Rs.<label id="tdisc" class="notranslate"><s:property value="discount"/></label></small></h4>
			                            	<h4 style="color: #868686;font-size: 15px;" >Refund: Rs.<span class="notranslate" id="refund">00.00</span> <s:hidden id="trefund" name="refundamt"></s:hidden> </h4>
			                            	<h4 class="hidden" style="font-size: 15px;">Vat : Rs.<label class="notranslate" id="vat"><s:property value="vat"/></label></h4> <s:hidden id="tvat" name="vat"/> <s:hidden id="tdiscount" name="discount" />
			                            	<h4 class="" style="font-size: 12px;">CGST : Rs.<label class="notranslate" id="tcgst"></label><s:hidden id="cgst" name="cgst"/> </h4> 
			                            	<h4 class="" style="font-size: 12px;">SGST : Rs.<label class="notranslate" id="tsgst"></label><s:hidden id="sgst" name="sgst"/> </h4>
			                    			<h4><input type="text" id="discsmt" readonly="readonly" name="discperamt" class="form-control" style="width: 17%;"></h4>        	 
			                            	<h4 style="font-weight: bold;color:green;">Gross Total : Rs.<span class="notranslate" id="grosstotalspan">00.00</span> <input type="hidden" name="grosstotal" id="grosstotal"/> <input type="hidden" name="grosssubtotal" id="grosssubtotal"/> </h4>
			                            	<h4 style="font-weight: bold;font-size: 15px;color:green;">Net Total : Rs.<span class="notranslate" id="total"><s:property value="total"/></span> <s:hidden name="total" id="ttotal"/> </h4>
			                            	<s:textarea theme="simple" cssClass="form-control"  name="notes" rows="3" placeholder="Write Note" cssStyle="margin-top: 15px;height: 50px !important;"/>
			                            	<h4 class="notranslate">
			                            	
			                            	<% if(retmultireturndash.equals("1") || isfromreturnnurse.equals("1")){%>
			                            		<s:if test="paymodereturn=='Cash' || paymodereturn=='Card' || paymodereturn=='Cheque' || paymodereturn=='NEFT'">
			                            			<s:select cssClass="form-control pharmacyddinput" list="#{'0':'Select Mode','Cash':'CASH','Card':'CARD','Cheque':'CHEQUE','NEFT':'NEFT'}" theme="simple" cssStyle="width: 100%;background-color: rgba(255, 140, 0, 0.36);" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)"  />
			                            		</s:if>
			                            		<%-- <s:elseif test="paymodereturn=='Card'">
			                            			<s:select cssClass="form-control pharmacyddinput" list="#{'0':'Select Mode','Card':'CARD'}" theme="simple" cssStyle="width: 100%;background-color: rgba(255, 140, 0, 0.36);" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)"  />
			                            		</s:elseif>
			                            		<s:elseif test="paymodereturn=='Cheque'">
			                            			<s:select cssClass="form-control pharmacyddinput" list="#{'0':'Select Mode','Cheque':'CHEQUE'}" theme="simple" cssStyle="width: 100%;background-color: rgba(255, 140, 0, 0.36);" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)"  />
			                            		</s:elseif>
			                            		<s:elseif test="paymodereturn=='NEFT'">
			                            			<s:select cssClass="form-control pharmacyddinput" list="#{'0':'Select Mode','NEFT':'NEFT'}" theme="simple" cssStyle="width: 100%;background-color: rgba(255, 140, 0, 0.36);" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)"  />
			                            		</s:elseif> --%>
			                            		<s:elseif test="paymodereturn=='Credit'">
			                            			<s:select cssClass="form-control pharmacyddinput" list="#{'0':'Select Mode','Credit':'CREDIT'}" theme="simple" cssStyle="width: 100%;background-color: rgba(255, 140, 0, 0.36);" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)"  />
			                            		</s:elseif>
			                            		<s:elseif test="paymodereturn=='Hospital'">
			                            			<s:select cssClass="form-control pharmacyddinput" list="#{'0':'Select Mode','Hospital':'HOSPITAL'}" theme="simple" cssStyle="width: 100%;background-color: rgba(255, 140, 0, 0.36);" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)"  />
			                            		</s:elseif>
			                            	 
			                            	 <%}else{%>
												<s:if test="returnmode=='Hospital'">
			                            	       <s:select cssClass="form-control" list="#{'Hospital':'HOSPITAL'}" theme="simple" cssStyle="width: 100%;" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)" style="background-color: rgba(255, 140, 0, 0.36);" />
			                            	  	</s:if>
			                            	  	<s:else>
			                            	  		<s:select cssClass="form-control pharmacyddinput" list="#{'0':'Select Mode','Cash':'CASH','Credit':'CREDIT','Hospital':'HOSPITAL','Card':'CARD','Cheque':'CHEQUE','NEFT':'NEFT','Online Payment':'ONLINE PAYMENT'}" theme="simple" cssStyle="width: 100%;background-color: rgba(255, 140, 0, 0.36);" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)"  />
			                            	  	</s:else>			                            	 
			                            	 <%}%>
			                            	  
			                            	</h4>
			                            	<h4>
			                            		<input type="text" id="card" name="card" placeholder="Enter 4 Digit No" class="form-control hidden"  />
			                            	</h4>
			                            	<h4>
			                            		<input type="number" id="chequeno" name="chequeno" placeholder="Enter Cheque No" class="form-control hidden"  />
			                            	</h4>
			                            	<h4>
			                            		<input type="text" id="neftid" name="neftid" placeholder="Enter Transaction ID" class="form-control hidden"  />
			                            	</h4>
			                            	
			                            	
			                            	<h4 class="hidden"><small>Total with balance: Rs.<span class="notranslate" id="baltotal"></span></small></h4> 
			                            	 
	                            
			                            </div>
										<div class="col-lg-12 col-md-12 col-xs-12 " style="padding-top:10px;padding:0px;">
										<% if(retmultireturndash.equals("1")){%>
                            			 	<a href="#"  class="btn btn-primary btn-lg" title="Cash Payment" onclick="return refundMedicineNew()" style ="width:48%;padding: 32px 16px;" id="returnbtn">Return</a> 
                            			 	
                            			<%}else if(isfromreturnnurse.equals("1")){%>
                            				<!-- <button  class="btn btn-primary btn-lg" title="Cash Payment" onclick="refundMedicineNew()" style ="width:48%;padding: 32px 16px;" >Return Without Bill</button> -->
                            				 <a href="#" class="btn btn-primary btn-lg" title="Cash Payment" onclick="return refundMedicineNew()" style ="width:48%;padding: 32px 16px;" id="returnbtn">Return</a> 
                            				
                            			<%}else{%>
			 								<!--<a type="button" class="btn btn-primary"  title="Online" >Online</a>
			                            	--><a type="button" class="btn btn-primary hidden"  title="Credit" onclick="credit()">Credit</a>
				                           	<!--  <a type="button" class="btn btn-primary btn-lg savenprintlg" style="width:150px;height: 55px !important;line-height: 30px;" title="Cash Payment" onclick="refundMedicineNew()">Return Without Bill</a>
				                            <a type="button" class="btn btn-primary btn-lg savenprintlg" style="width:150px;height: 55px !important;line-height: 30px;" title="Cash Payment" onclick="refundMedicineNew()">Return With Bill</a> -->
			                            	 <a href="#"  class="btn btn-primary btn-lg" title="Cash Payment" onclick="return refundMedicineNew()" style ="width:48%;padding: 32px 16px;" id="returnbtn">Return</a>
				                            <!-- <button class="btn btn-primary btn-md" title="Cash Payment" onclick="refundMedicineWithBill()" style ="width:48% ;padding: 32px 16px;">Return With Bill</button> -->
			                            <%}%>
                            </div>
									</div>
									 </form>
									
									
								</div>
							
							 
							 <div class="footerDrawer hidden">
							  <div class="open"></div>
							  <div class="content">
							  	<div class="col-lg-12 col-sm-12 col-md-12" style="padding: 0px;">
							  		<div class="col-lg-4 col-sm-4 col-md-4" style="text-align: center;background-color: dimgray;color: #fff;border-right: 1px solid #ddd;">
							  			<h5 style="margin-bottom: 6px;"><span>Sale Price&nbsp; : &nbsp;</span><span>Rs.<label id="lblSalePrice">500</label></span></h5>
							  		</div>
								  	<div class="col-lg-4 col-sm-4 col-md-4" style="text-align: center;background-color: dimgray;color: #fff;border-right: 1px solid #ddd;">
								  		<h5 style="margin-bottom: 6px;"><span>Purchase Price&nbsp; : &nbsp;</span><span>Rs.<label id="lblPurPrice">500</label></span></h5>
								  	</div>
								  	<div class="col-lg-4 col-sm-4 col-md-4" style="text-align: center;background-color: dimgray;color: #fff;">
								  		<h5 style="margin-bottom: 6px;"><span>Margin&nbsp; : &nbsp;</span><span>Rs.<label id="lblMargin">500</label></span></h5>
								  	</div>
							  	</div>
							  </div>
							</div>
		
		
<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body" style="padding:0px;">
     
      	<%@ include file="/ipd/pages/ipdPatientList.jsp"%>
      
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
		
		
	<!-- Modal for confirmiing pay - lokesh -->
<div id="confirmreturnpopup" class="modal fade" role="dialog" tabindex="-1">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure </h4>
      </div>
      <div class="modal-body">
    	<span>Pay mode<h4 class="notranslate" id="paymodepopup"></h4></span>
    	<span>Discount<h4 id="discountconfirm"></h4></span>
      	<span>Amount<h4 id="npopup"></h4> </span>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="checkReturnmOde()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>		
			
<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.61);">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
      </div>
      <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12">
      		<div class="form-group">
      			<label>Patient Full Name</label>
      			<input type="text" name="adfullname" id="adfullname" class="form-control">
      		</div>
      		<div class="form-group">
      			<label>Mobile No</label>
      			<input type="number" name="admobile" id="admobile" class="form-control" maxlength="10">
      		</div>
      		<div class="form-group">
      			<label>Address</label>
      			 <textarea class="form-control" rows="5" name="adaddress" id="adaddress" ></textarea>
      		</div>
      		<div class="form-group">
      			<label>Doctor Name</label>
      			<input type="text" name="addoctor" id="addoctor" class="form-control">
      		</div>
      	</div>
      
      <div class="modal-footer">
      <button type="button" id="addpharpatientbtnid" class="btn btn-success" onclick="savePharmaClient()" >Save</button>
        <button type="button" class="btn btn-danger hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


		
		
							<script>
								$(document).ready(function() {
								  $('.footerDrawer .open').on('click', function() {
								    $('.footerDrawer .content').slideToggle();
								  });
								});
							</script>					 
							
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
    /* document.getElementById("istp").disabled = true; */
    
  </script>

<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '415px',
								        railVisible: true,
										alwaysVisible: true
								    });
								});
							</script>
							
					


</body>
</html>