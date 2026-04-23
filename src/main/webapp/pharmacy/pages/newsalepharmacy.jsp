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
<%boolean isregionallanguage= false; %>
<%
	if(!loginInfo.getRegional_lang().equals("en")){
		isregionallanguage = true;
	}
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
   /*  select:focus { 
        outline: none !important;
        border-color: #719ECE;
        box-shadow: 0 0 10px #719ECE;
        background-color: yellow;
    } */
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
            border-top: 0px solid #c5c5c5 !important;
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
     <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
    
    <SCRIPT type="text/javascript">

    $(document).ready(function() {

		$("#date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		 $("#dtp_fromdate").datepicker({

				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true

			});
		   $("#dtp_todate").datepicker({

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
 
</SCRIPT>
    
    <script>
	    var timeout = null;
	    function doDelayedSearch(val) {
	      if (timeout) {  
	        clearTimeout(timeout);
	      }
	      timeout = setTimeout(function() {
	    	  checkBarcodeRequest(val); //this is your existing function
	      }, 500);
	    }
    </script>
    <SCRIPT type="text/javascript" src="pharmacy/js/pharmacy.js"></SCRIPT> 
    <SCRIPT type="text/javascript">
     
            window.onload =function(){ 
            
                 var istp= document.getElementById("istp").checked;
				   if(istp==true){
				   		document.getElementById("tpid").value="1";
				   		document.getElementById("paytype").value="Credit";
				   		document.getElementById("paytype").disabled=true;
				   		document.getElementById("received").value=0;
				   		
				   } else {
				      document.getElementById("tpid").value="0";
				      document.getElementById("paytype").value="Cash";
				   		document.getElementById("paytype").disabled=false;
				   }
                     
            };
            
            
            function sessionAddedMedicine() {
				var test= false;
				if(document.getElementById("location")){
					var loctioid = document.getElementById("location").value;
					if(loctioid==''){
						test= true;
						
					}else if(loctioid=='0'){
						test= true;
						
					}
				}
				if(test){
					jAlert('error', "Please select location from top!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
				}else{
					myWindow = window.open("sessionAddedPharMedPharmacyAjax", "", "width=800, height=600, addressbar=no,");
				}
			     
			}
        
    </SCRIPT>
    
    
    
    
</head>
   <body onload="setTimeout('autoClick();',1000);" >
   <div class="hidden" id="google_translate_element"></div>
   
   		
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
							
							
							
							<form action="newpatientsalePharmacyAjax" method="post" id="creditform" >
								<%if(loginInfo.isMax_phar_discount() || loginInfo.getSuperadminid()==1){ %>
									<input type="hidden" name="max_phar_discount" value="1" id="max_phar_discount">
								<%}else{ %>
									<input type="hidden" name="max_phar_discount" value="0" id="max_phar_discount">
								<%} %>
								<%if(loginInfo.isPhar_batchwise_sale()){ %>
									<input type="hidden" name="phar_batchwise_sale" value="1" id="phar_batchwise_sale">
								<%}else{ %>
									<input type="hidden" name="phar_batchwise_sale" value="0" id="phar_batchwise_sale">
								<%} %>
								<input type="hidden" name="newclientid" id="newclientid">
								<input type="hidden" name="issalepriscdashboardakash" id="issalepriscdashboardakash">
								<input type="hidden" id="returnpage" value="0" />
								<input type="hidden" name="newispharmacy" id="newispharmacy">
								<s:hidden name="hdnphclientid" id="hdnphclientid"/>
	       						<s:hidden name="hdnispharmacy" id="hdnispharmacy"/>
	       						<s:hidden id="creditprebalance" value="0"></s:hidden>
	       						<s:hidden name="isdirectsale" value="1" />
	       						<input type="hidden" id="creditlimit" value="<%=loginInfo.getCreditlimit() %>">
	       						<%if(loginInfo.isCreditlimitaccess()){ %>
	       							<s:hidden id="creditbalaccess" value="1"></s:hidden>
	       						<%}else{ %>
	       							<s:hidden id="creditbalaccess" value="0"></s:hidden>
	       						<%} %>
	       						
	       						<% String clinicid=loginInfo.getClinicid1(); %>
		                          <input type="hidden" id="hiddenclinicid" value="<%=clinicid%>">
	       						
							<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
							<div class="col-lg-12 col-md-12 col-sm-12 searchtab">
							 <div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
								<label for="inputEmail3" class="text-left">Search :</label>
							</div> 
							<div class="col-lg-6 col-md-6 col-sm-8 col-xs-3">
								<div class="col-lg-8 col-md-8 col-sm-8">
									<input type="text" name="inhousepid" id="inhousepid" class="form-control" onclick="showInhousePatientPopup();"   placeholder="Search Patient" style="width: 100%;" autofocus="autofocus">
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4" style="display: inline-flex;">
									<a class="btn btn-primary" href="#" onclick="openBlankPopup('salepriscPharmacy')"  title="New Sale" >New Sale</a>
									&nbsp;
									<a class="btn btn-primary" href="#" onclick="openpatientpreviousbill()" title="Patient Previous Bill" >Patient History</a>
									&nbsp;
									
									<%if(loginInfo.isSession_added_med() || loginInfo.getSuperadminid()==1){ %>
										<%if(isregionallanguage){ %>
											<input type="button" class="btn btn-primary" onclick="sessionAddedMedicine()" value="Already added Product">
										<%}else{ %>
											<input type="button" class="btn btn-primary" onclick="sessionAddedMedicine()" value="Session Product">
										<%} %>
									<%} %>
								</div>
								
								
								<%if(loginInfo.getSuperadminid()==1){ %>
									<input type="hidden" id="showpurpriceinbody" value="1">
								<%}else{ %>
									<input type="hidden" id="showpurpriceinbody" value="0">
								<%} %>
								
								
							</div>
							<!-- <div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
								<a class="btn btn-primary btn-lg" href="salepriscPharmacy"  title="New Sale" >New Sale</a>
							</div>  -->
							
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2" style="text-align: left;margin-top: -4px; display: inline-flex;">
								<input type="button" value="Refresh" class="btn btn-primary hidden"  onclick="clearAll()" />&nbsp;&nbsp;&nbsp;
								<a class="btn btn-success hidden" href="#" onclick="openBlankPopup('salepriscPharmacy')" title="New Sale" >New Sale</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<ul class="vertical default_list" id="" style="list-style: none;">
								     <s:if test="tp_bill_access==1">
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="istp" onclick="calsubTotal()" class="tp" ><i></i> TP Patient</label></li>								     
								     </s:if>
								     <s:else>
								           <input type="checkbox" id="istp" class="hidden" class="tp" >
								     </s:else>
								</ul>
							</div>
							
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: right;">
								<span class="payepharmacyi notranslate"> Payee:<span id="payee"></span></span>
								<span style="font-size: 11px;"><i class="fa fa-square" aria-hidden="true" style="color: #e05d6f;"></i> Narcotics 
										| <i class="fa fa-square" aria-hidden="true" style="color: #e69522;"></i> H1
										|  <i class="fa fa-square" aria-hidden="true" style="color: #9381cc;"></i> High Risk
										|  <i class="fa fa-square" aria-hidden="true" style="color: #e0acdafc;"></i> Vaccination
										</span>
							</div>
							
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;padding-top: 8px;">
								<div class="col-lg-1 col-md-1 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">UH ID</label>
										     <p style="font-weight: bold;font-size: 13px;" id="abbbbrivationid"></p>
										    <p style="font-weight: bold;font-size: 13px;"><s:property value="abrivationid"/> <s:hidden name="billno" /></p>
										    <input type="hidden" id="extpid" name="extpid" />
										     <input type="hidden" id="tpid" value="0" name="tpid" />
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label>
										    <%if(loginInfo.isPhar_patient_edit() || loginInfo.getUserType()==2){ %>
										    	<a href="#" style="color: #d9534f;" class="hidden" id="pharpatienteditatag" onclick="editpharmacypatient()"> <i class="fa fa-edit" aria-hidden="true" style="color: #d9534f;"></i></a>
										    <%} %>
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
										      -->
										      <%-- <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'reference')" cssClass="form-control" name="practitionerName" id="doctor"  cssStyle="text-transform: uppercase;"/>
								  			  <s:hidden id="phar_practid" name="phar_practid"></s:hidden> --%>
								  			  <s:hidden id="doctor" name="practitionerName"></s:hidden>
								  			  <s:select list="pharmacydrlist" onchange="setpractnameinsale(this.value)" listKey="id" name="phar_practid" id="phar_practid" listValue="name"  headerKey="0" headerValue="Select Doctor" cssClass="form-control chosen-select"></s:select>
								  			  <s:hidden id="normalsaledashbbbord"></s:hidden>
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
									         <div class="form-group hidden-xs" style="width: 8%;">
									               <!--  <input type="text" id="barcode" oninput="checkBarcodeRequest(this.value)" class="form-control " placeholder="Barcode Here"  style="width:100%;" /> -->
									          		<input type="text" id="barcode" onkeyup="doDelayedSearch(this.value)" class="form-control " placeholder="Barcode Here"  style="width:100%;" />
									         </div>
									         <!-- <div class="form-group hidden">
									           		<p style="font-size: 13px;margin: 0px 0px 2px;">Product :</p> 
									         </div> -->
									         <%if(loginInfo.isPhar_batchwise_sale()){ %>
												 <div class="form-group notranslate " id="cataloguewiselistid" style="width: 35% !important"  onfocus="myfun(this.id)"> 
										           	 <s:select list="inventoryPriscList" listKey="id" onchange="getProductListForSale(this.value)"  id="newcataloguesaleid" listValue="genericname" headerKey="0" headerValue="Select Product" name="mdicinename" cssClass="form-control chosen-select">
										           	 </s:select> 
										         </div>
									             <div class="form-group notranslate" id="plist" style="width: 30% !important" onfocus="myfun(this.id)"  >
											         <s:select list="inventoryPriscList" listKey="id"  id="newmedicine" listValue="genericname" headerKey="0" headerValue="Select Batchwise Product" name="mdicinename" cssClass="form-control chosen-select" >
											         </s:select> 
										         </div>
											 <%}else{ %>
											 	<div class="form-group">
									           		<p style="font-size: 13px;margin: 0px 0px 2px;">Product :</p> 
									         	</div>
												 <div class="form-group notranslate" id="plist" style="width: 50% !important">
											         <s:select list="inventoryPriscList" listKey="id"  id="newmedicine" listValue="genericname" headerKey="0" headerValue="Select Product" name="mdicinename" cssClass="form-control chosen-select">
											         </s:select> 
										         </div>
											 <%} %>
									         
									         <div class="form-group" style="width: 5%;padding-left: 2px;" id="quanty" >
									             	<input  type="text" id="qty" style="width: 100%;" class="form-control" placeholder="Qty" onfocus="func(this.id)" />
									         </div>&emsp;
									         <div class="form-group hidden-xs" style="width: 1%;padding-bottom: 5px;">
									           	 	<a href="#" onclick="addsalenewRowJson('mytable')" title="Add New" class="btn btn-primary" >ADD</a>
									           	 	<!-- <i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i> -->
									         </div>
									     </div>
         							</div>
                                
                                
									<div class="minhesigh">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<table id="mytable" class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="">
                                    	<%if(isregionallanguage){ %>
											<th  class="text-center pharmacytableheadingfont"  style="width: 2%;">Sr. No </th>
										<%}else{ %>
											<th  class="text-center pharmacytableheadingfont"  style="width: 2%;">Sr. No </th>
										<%} %>
                                      
                                        <th class="pharmacytableheadingfont" style="width: 25%;">Name of Drug</th>
                                        <th class="text-center pharmacytableheadingfont" style="width: 5%;">Req. Qty</th>
                                       <!--  <th style="width: 12%;">Avail/Batch No/Exp. </th> -->
                                        <th class="text-center pharmacytableheadingfont"  style="width: 5%;">Available</th>
                                        <th class="text-center pharmacytableheadingfont"  style="width: 5%;">Batch No</th>
                                        <th class="text-center pharmacytableheadingfont"  style="width: 5%;">Expiry</th>
                                        <th class="text-center pharmacytableheadingfont" style="width: 4%;">Sale</th>
                                        <%if(loginInfo.isInfinyte()){ %>
                                        <th class="text-center pharmacytableheadingfont" style="width: 4%;">Free Qty</th>
                                        <th class="text-center pharmacytableheadingfont" style="width: 4%;">Discount %</th>
                                        <%} %>
                                        <%if(loginInfo.getSuperadminid()==1 || loginInfo.isPprice_sale()){ %>
											<th class="text-center pharmacytableheadingfont" style="width: 4%;">P. Price</th>
										<%} %>
                                        <th class="text-center pharmacytableheadingfont" style="width: 7%;" class="text-right">Unit Price</th>
                                        <th class="pharmacytableheadingfont" class="" style="width: 4%;text-align: center;">P.Shelf</th>
                                        <th class="pharmacytableheadingfont" class="" style="width: 4%;text-align: center;">S.Shelf</th>
                                        <th class="pharmacytableheadingfont" style="width: 8%;" class="text-right">Total</th>
                                        <th class="pharmacytableheadingfont" style="width: 3%;"></th>
                                        <th class="pharmacytableheadingfont" style="width: 2%;"></th>
                                    </tr>
                                </thead>
                                	<tbody id="innerTableBody" class="notranslate">
                                	 
                                    
                                    
                              
                                 <tr></tr>
                                </tbody>
                                    <input type="hidden" id="tempcount" value="0" />
                                    <s:hidden name="selectedid" ></s:hidden>
                            </table>
						   </div>
							</div>	
								
								
                           
                            
									</div>
									<div class="col-lg-3 col-sm-12 col-md-3 col-xs-12">
										<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;border-bottom: 1px solid #ddd;" id="baldiv">
											<h3 style="color:#d9534f;">Balance : <span style="float: right;" >Rs.<span class="notranslate" id="rebalance">00.00</span> <input type="hidden" value="0" id="prebalance" /> </span></h3>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 3px;text-align: right;padding:0px;">
			                            	<!--<h4><a href="#" title="clear Balance" onclick="clearbaldb()" class="btn btn-success btn-sm pull-left">clear balance</a>Sub Total : Rs.<span id="subtotal">00.00</span></h4>
			                            	
			                            	--><h4 style="color: forestgreen;"><a href="#" title="clear Balance" onclick="clearBalanceTemp()" class="btn btn-success btn-sm pull-left hidden">Clear Balance</a>Sub Total : Rs.<span id="subtotal">00.00</span></h4>
			                            	
			                            	<%if(loginInfo.isDisscount() || loginInfo.getUserType()==2) {%>
			                            		<h4 style="color: #868686;">Discount <select id="distype" name="discounttype" onchange="perorrs()" class="form-control" style="width: 20%;"><option value="0">%</option><option value="1">Rs.</option></select> : <input type="number" id="discsmt" onchange="calDiscount(this.value)" name="salediscount" class="form-control" value="0" style="width: 20%;"><br><small>Rs.<label class="notranslate" id="tdisc">00.00</label></small></h4>
			                            	<%} else { %>
			                            	
			                            	   <h4 class="hidden" style="color: #868686;">Discount <select id="distype" name="discounttype" onchange="perorrs()" class="form-control" style="width: 20%;"><option value="0">%</option><option value="1">Rs.</option></select> : <input type="number" id="discsmt" name="salediscount" onchange="calDiscount(this.value)" class="form-control" value="0" style="width: 20%;"><br><small>Rs.<label class="notranslate" id="tdisc">00.00</label></small></h4>
			                            	<%} %>
			                            	<s:hidden id="subbtotal" name="subbtotal" value="0"></s:hidden>
			                            	<s:hidden id="discinper" name="discinper" value="0"></s:hidden>
			                            	<h4 class="hidden" style="color: #868686;">Refund: Rs.00.00</h4>
			                            	<h4 class="hidden">Vat : Rs.<label class="notranslate" id="vat">00.00</label></h4> <input type="hidden" id="tvat" name="vat"/> <input type="hidden" id="tdiscount" name="discount" />
			                            	CGST : Rs.<label class="notranslate" id="cgst">00.00</label> <input type="hidden" id="tcgst" name="cgst"/> 
			                            	<div class="clearfix"></div>
			                            	SGST : Rs.<label class="notranslate" id="sgst">00.00</label> <input type="hidden" id="tsgst" name="sgst"/> 
			                            	<h4 style="font-weight: bold;color:green;">Gross Total : Rs.<span class="notranslate" id="grosstotalspan">00.00</span> <input type="hidden" name="grosstotal" id="grosstotal"/> <input type="hidden" name="grosssubtotal" id="grosssubtotal"/> </h4> 
			                            	<h4 style="font-weight: bold;color:green;">Net Total : Rs.<span class="notranslate" id="total">00.00</span> <input type="hidden" name="total" id="ttotal"/> </h4> 
			                            	<h4 class="hidden" style="color: #868686;">Balance : <input type="text" name="balance" id="balance" class="form-control" value="0" style="width: 20%;"></h4>
			                            	<h4 class=""><small>Total with balance: Rs.<span class="notranslate" id="baltotal"></span></small></h4>
	                            		</div>
	                            		<div class="col-lg-12 col-md-12 col-xs-12 hidden-xs returfed">
			                            	<div class="form-inline">
											  <div class="form-group" style="width: 49%">
											    <label for="exampleInputName2">Received Rs.</label>
											   <%--  <s:textfield  onkeyup="getRemainAmt(this.value)" id="received" name="payamt" cssClass="form-control" cssStyle="width: 43%;background-color: rgba(165, 42, 42, 0.07);" /> --%>
											  	<input type="number"  onkeyup="getRemainAmt(this.value)" id="received" min="0" name="payamt" class="form-control" style="width: 43%;background-color: rgba(165, 42, 42, 0.07);">
											  </div>
											  <div class="form-group" style="width: 49%;">
											    <label for="exampleInputEmail2">Return Rs.</label>
											    <input id="returnamt" type="number" class="form-control" style="width: 45%;background-color: rgba(165, 42, 42, 0.07);">
											  </div>
											</div>
											
			                             </div>
			                             <div class="col-lg-12 col-md-12 col-xs-12 text-right" style="padding:0px;">
			                             <div class="form-group">
			                             	<s:textarea theme="simple" cssClass="form-control"  name="notes" rows="3" placeholder="Write Note" cssStyle="height: 50px !important;"/>
			                             </div>
	                            
	                            
	                            <div class="form-group notranslate" >
	                            	<select class="form-control pharmacyddinput" style="width: 100%;background-color: rgba(255, 140, 0, 0.36);"  id="paytype" onchange="setPaymode(this.value)" >
	                            	<option value="Cash">CASH</option>
	                            	<option value="Card">CARD</option>
	                            	<option value="Cheque">CHEQUE</option>
	                            	<option value="NEFT">NEFT/RTGS</option>
	                            	<option value="Credit">CREDIT</option>
	                            	<!-- <option value="Estimate">ESTIMATE</option> -->
	                            	<option value="Hospital">HOSPITAL</option>
	                            	<option value="Online Payment">ONLINE PAYMENT</option>
	                            </select>
	                            </div>
	                             <div class="form-group">
	                            	<input type="text" id="card" name="card" placeholder="Enter 4 Digit No" class="form-control hidden"  />
	                            </div>
	                             <div class="form-group">
	                            	<input type="number" id="chequeno" name="chequeno" placeholder="Enter Cheque No" class="form-control hidden"  />
	                            </div>
	                             <div class="form-group">
	                            	<input type="text" id="neftid" name="neftid" placeholder="Enter Transaction ID" class="form-control hidden"  />
	                            </div>
	                            
	                            <div class="form-group">
	                            	<a type="button" class="btn btn-primary savenprintlg" id="salebutton" onclick="newsale()">Save & Print</a>
	                            </div>
	                            
			                             <span class="hidden" style="float: left;font-size: 15px;color: darkblue;">Item : 0</span>
                            	<a type="button" class="btn btn-primary hidden"  title="Online Payment" onclick="newsale('Online')">Online</a>
                            	<!--<a type="button" class="btn btn-primary"  title="Credit" onclick="credit()">Credit</a>-->
                            	
                            	<a type="button" class="btn btn-info hidden"  title="Estimate" onclick="newsale('Estimate')" style="float: left;">Estimate</a>
	                            <a type="button" class="btn btn-primary hidden"  title="Cash Payment" onclick="newsale('Cash')">Save & Print 11</a>
	                            <a type="button" class="btn btn-warning hidden"  title="Card Payment" onclick="showCard()">Card Payment</a>
	                            
	                            
                            </div>
                           <div class="col-lg-12 col-xs-12 col-md-12 text-right" style="margin-top: 15px;padding:0px;">
                            <div id="divcard" class="hidden">
                            	<div class="form-inline">
								  <div class="form-group">
								    <label class="sr-only" for="exampleInputEmail3">Email address</label>
								    
								  </div>
								  
								  <a type="button" class="btn btn-warning"  onclick="newsale('Card')" style="margin-top: -15px;"> Save & Print</a>
								</div>
                            	 <br>
                            	
                            	
                            </div>
                            </div>
										
									</div>
									 </form>
									
									
								</div>
							
							 
							 <div class="footerDrawer">
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
        <button type="button" class="close hidden" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
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
<div id="deletedisc" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure </h4>
      </div>
      <div class="modal-body">
    	<span>  Pay mode<h4 class="notranslate" id="paymodepopup"></h4></span>
    	<span>  Discount<h4 id="discountconfirm"></h4></span>
      	<span> 	Amount<h4 id="npopup"></h4> </span>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="checkCashmOde()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>
		
		
		
		
<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.61);">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close hidden" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
      </div>
      <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12">
      		<div class="form-group">
      			<label>Patient Full Name</label><label style="color: red">*</label>
      			<input autofocus type="text" name="adfullname" id="adfullname" class="form-control" onkeyup="getHisPatientNameData(this.value)" list="brow">
      			<datalist id="brow">
				</datalist>  
      		</div>
      		<%if(!loginInfo.isParihar()){ %>
      		<div class="form-group">
      			<label>Mobile No</label>
      			<input type="number" name="admobile" id="admobile" class="form-control" maxlength="10">
      		</div><%} %>
      		<div class="form-group">
      			<label>Address</label><label style="color: red">*</label>
      			 <textarea class="form-control" rows="5" name="adaddress" id="adaddress" ></textarea>
      		</div>
      		<%if(!loginInfo.isParihar()){ %>
      		<div class="form-group" id="phardocadddiv">
      			<label>Doctor Name</label>
      			<!-- <input type="text" name="addoctor" id="addoctor" class="form-control"> -->
      			<s:select list="pharmacydrlist" listKey="id" name="addoctor" id="addoctor" listValue="name" headerKey="0" headerValue="Select Doctor" cssClass="form-control chosen-select"></s:select>
      		</div>
      		<div class="form-group" id="phardocadddiv">
      			<a href="#" onclick="addnewDrInSystem()" class="btn btn-success">Add New Doctor</a>
      		</div>
      		<div class="hidden" id="phardocadddocdiv">
	      		<div class="form-group">
	      			<label>Initial</label>
	      			<input type="text" readonly="readonly" class="form-control" id="phardoc_inital" value="Dr." >
	      		</div>
	      		
	      		<div class="form-group">
	      			<label>First Name</label>
	      			<input type="text" id="phardoc_firstname"  class="form-control" >
	      		</div>
	      		
	      		<div class="form-group">
	      			<label>Last Name</label>
	      			<input type="text" id="phardoc_lastname"  class="form-control" >
	      		</div>
	      		
	      		<div class="form-group">
	      			<label>Address</label>
	      			<input type="text" id="phardoc_address"  class="form-control" >
	      		</div>
	      		
	      		<div class="form-group">
	      			<label>Qualification</label>
	      			<input type="text" id="phardoc_qualification"  class="form-control" >
	      		</div>
	      		
	      		<div class="form-group">
	      			<a href="#" onclick="savenewDrInSystem()" class="btn btn-success">Add New Doctor</a>
	      			<a href="#" onclick="closenewDrInSystem()" class="btn btn-success">Close</a>
	      		</div>
      		</div>
      		<div class="form-group">
	      		   <label>GSTIN</label>
	      			<input type="text" id="gstin"  class="form-control" >
	      		</div>
	      	<div class="form-group">
	      		   <label>D.L. No</label>
	      			<input type="text" id="dlno"  class="form-control" >
	      		</div>
	      		<%} %>
      	</div>
      
      <div class="modal-footer">
      <button type="button" id="addpharpatientbtnid" class="btn btn-success" onclick="savePharmaClient()" >Save</button>
        
      </div>
    </div>
  </div>
</div>
</div>


<div id="detailspreviousbill" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Bill Transaction</h4>
      </div>
      <div class="modal-body">
      <div class="minhesigh">
      
       <h4 class="modal-title">Patient Name - <SPAN id='dtp_patientname'></SPAN></h4>
       <div style="padding-bottom: 5px;padding-top: 5px; ">
       <label>From Date :</label>
       <input type="text" placeholder="From Date" readonly="readonly"   id="dtp_fromdate" class="form-control" style="width: 11%;border-color : #4b88bd" >
         &nbsp;&nbsp;&nbsp;
        <label>To Date :</label>
       <input type="text" placeholder="To Date" readonly="readonly"  id="dtp_todate" class="form-control" style="width: 11%;border-color : #4b88bd">
       &nbsp;&nbsp;&nbsp;
       <input type="button" value="Go" class="btn btn-success" onclick="openpatientpreviousbill()">
      
       </div>
      	<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;" >
			
         <table class="table table-responsive" >
          <thead>
           <tr>
            <th>Sr</th>
            <th>Bill Type</th>
            <th>Bill No.</th>
            <th>Bill Date</th>
            <th>Payment Type</th>
            <th style="text-align:right;">Total Disc</th>
            <th style="text-align:right;">Total Amt</th>
            <th style="text-align:right;">Paid Amt</th>
            <th style="text-align:right;">Balance</th>
            <th style="text-align:right;">Return Amt</th>
            <th style="text-align:right;">Action</th>
           </tr>
          </thead>
         
          <tbody id="dtp_tbody">
           
          </tbody>
         </table>
        </div>
      </div>
      </div>
      <div class="modal-footer" >
      	
        
      </div>
    </div>

  </div>
</div>


<div class="modal fade" id="editPatientDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.61);">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close hidden" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Edit Patient</h4>
      </div>
      <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12">
      		<div class="form-group">
      			<label>Patient Full Name</label>
      			<input type="text" id="editfullname" class="form-control">
      			<input type="hidden" id="editpclinetid">
      		</div>
      		<div class="form-group">
      			<label>Mobile No</label>
      			<input type="number" id="editmobile" class="form-control" maxlength="10">
      		</div>
      		<div class="form-group">
      			<label>Address</label>
      			 <textarea class="form-control" rows="5" id="editaddress" ></textarea>
      		</div>
      		<div class="form-group">
      			<label>Doctor Name</label>
      			<!-- <input type="text" name="addoctor" id="addoctor" class="form-control"> -->
      			<s:select list="pharmacydrlist" listKey="id" id="editdoctor" listValue="name" headerKey="0" headerValue="Select Doctor" cssClass="form-control chosen-select"></s:select>
      		</div>
      		
      		
      	</div>
      
      <div class="modal-footer">
     	 <button type="button" id="addpharpatientbtnid" class="btn btn-success" onclick="updatePharmaClient()" >Update</button>
      </div>
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
								
								function confirmpop(){
									$('#deletedisc').modal( "show" );
								}
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