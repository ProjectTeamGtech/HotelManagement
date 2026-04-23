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
   <!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<!-- New theme 30 01 2018 -->
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
 .table>tbody>tr>td, .table>tfoot>tr>td {
    font-size: 13px !important;
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
    
    <SCRIPT type="text/javascript" src="pharmacy/js/pharmacy.js"></SCRIPT> 
    
    <script>
    
window.onload = function () {
	  document.addEventListener("contextmenu", function(e){
		e.preventDefault();
		}, false); 
	
	addtempsaledata();

}
</script>
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
    
    </script>
    
</head>
   <body >
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
							
							
							
							<form action="newpatientsalePharmacyAjax"  method="post" id="creditform">
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
							<input type="hidden" name="newispharmacy" id="newispharmacy">
							<s:hidden name="hdnphclientid" id="hdnphclientid"/>
							<s:hidden name="priscid" id="priscid"/>
       						<s:hidden name="hdnispharmacy" id="hdnispharmacy"/>
							<s:hidden name="tpid" value="0" id="tpid"></s:hidden>
							<s:hidden name="oldparentid" id="oldparentid"/>
							<s:hidden name="newparentid" id="newparentid"/>
							
							<s:hidden name="isdirectsale" value="0" />
							<s:hidden name="phar_ipdid" ></s:hidden>
							<s:hidden name="phar_wardid"/>
							<s:hidden name="phar_bedid"/>
							
								<s:hidden id="creditprebalance" value="0"></s:hidden>
	       						<input type="hidden" id="creditlimit" value="<%=loginInfo.getCreditlimit() %>">
	       						<%if(loginInfo.isCreditlimitaccess()){ %>
	       							<s:hidden id="creditbalaccess" value="1"></s:hidden>
	       						<%}else{ %>
	       							<s:hidden id="creditbalaccess" value="0"></s:hidden>
	       						<%} %>
							
							<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
							<div class="col-lg-12 col-md-12 col-sm-12 searchtab">
							<div class="col-lg-1 col-md-1 col-sm-2 col-xs-3 hidden">
								<label for="inputEmail3" class="text-left">Search :</label>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-8 col-xs-3 hidden">
								<div class="form-group">
									<input type="text" readonly="readonly" name="inhousepid" id="inhousepid" class="form-control" onclick="showInhousePatientPopup()"  placeholder="Search Patient" style="width: 100%;">   	
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-8 col-xs-4">
								<span>Token No.: TN-<s:property value="tokenno"/></span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<%if(loginInfo.isSession_added_med() || loginInfo.getSuperadminid()==1){ %>
										<input type="button" class="form-group btn btn-info" onclick="sessionAddedMedicine()" value="Already added product log">
								<%} %>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: left;margin-top: -4px;">
								<input type="button" value="Refresh" class="btn btn-primary hidden"  onclick="clearAll()" />  
								<input type="button" value="Edit" class="btn btn-primary hidden"  onclick="enabledAll()" />
								
								<ul class="vertical default_list" id="" style="list-style: none;margin-left: 35px;margin-top: -23px;">
		           					  <s:if test="tp_bill_access==1">
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="istp" onclick="calsubTotal()" class="tp" ><i></i> Third Party</label></li>								     
								     </s:if>
								      <s:else>
								        <input type="checkbox" id="istp" class="hidden" class="tp" >
								     </s:else>
								</ul>
								
								
							</div>
							
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: right;">
								<span class="payepharmacyi notranslate"> Payee: <s:property value="whopay"/></span>
								
								<span><i class="fa fa-square" aria-hidden="true" style="color: #e05d6f;"></i> Narcotics &nbsp; | &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: #e69522;"></i> H1</span>
								
							</div>
								<%-- <div>
									<span>Token No.: TN-<s:property value="tokenno"/></span>
									<%if(loginInfo.isSession_added_med() || loginInfo.getSuperadminid()==1){ %>
											<input type="button" class="form-group btn btn-info" onclick="sessionAddedMedicine()" value="Session Added Product">
									<%} %>
								</div> --%>
							
							
							
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;padding-top: 8px;">
								<div class="col-lg-1 col-md-1 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">UH ID</label>
										    <p style="font-weight: bold;font-size: 13px;"><s:property value="abrivationid"/> <s:hidden name="billno" /></p>
										    <input type="hidden" id="extpid" name="extpid" />
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label>
										    <s:textfield readonly="true" cssClass="form-control" name="fullname" id="fullname" />
								  		</div>
								  		
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">MOBILE</label>
										    <s:textfield readonly="true" cssClass="form-control" name="mobno" id="mobile" />
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">LOCATION</label>
											    <s:textfield readonly="true" cssClass="form-control" name="wardname" id="wardname" />
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
										      --><s:textfield readonly="true" cssClass="form-control" name="practitionerName" id="doctor"  />
								  			<s:hidden id="phar_practid" name="phar_practid"></s:hidden>
								  		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DATE</label>
										    <s:textfield cssClass="form-control" readonly="true" name="dateTime" />
								  		</div>
									</div>
							</div>
								<%if(loginInfo.getSuperadminid()==1){ %>
									<input type="hidden" id="showpurpriceinbody" value="1">
								<%}else{ %>
									<input type="hidden" id="showpurpriceinbody" value="0">
								<%} %>
								
								
									
									
									
									
                                </div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									
									<div class="col-lg-9 col-sm-12 col-md-9 col-xs-12 rightset">
									<s:hidden id="isnewcheckavailability" name="isnewcheckavailability"></s:hidden>
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
								                <!-- <input type="text" id="barcode" onchange="checkBarcodeRequest(this.value)" class="form-control" placeholder="Barcode Here" autofocus="autofocus" style="width:100%;" /> -->
								          		<input type="text" id="barcode" onkeyup="doDelayedSearch(this.value)" class="form-control " placeholder="Barcode Here" autofocus="autofocus"  style="width:100%;" />
								          </div>
								          <!-- <div class="form-group hidden-xs">
								           <p style="font-size: 13px;margin: 0px 0px 2px;">Product :</p> 
								          </div> -->
								          
								          <%if(loginInfo.isPhar_batchwise_sale()){ %>
											  <div class="form-group notranslate" id="cataloguewiselistid" style="width: 22% !important">
									           	<s:select style='width:100% !important' list="inventoryPriscList" listKey="id" onchange="getProductListForSale(this.value)"  id="newcataloguesaleid" listValue="genericname" headerKey="0" headerValue="Select Product" name="mdicinename" cssClass="form-control chosen-select">
									            </s:select> 
										      </div>
									          <div class="form-group notranslate" id="plist" style="width: 50% !important">
										           <%-- 	<s:select list="inventoryPriscList" listKey="id"  id="newmedicine" listValue="genericname" headerKey="0" headerValue="Select Batchwise Product" name="mdicinename" cssClass="form-control chosen-select">
										            </s:select>  --%>
									                <select style='width:100% !important' class="form-control chosen-select" name="mdicinename" id="newmedicine">
										            	<option value="0">Select Batchwise Product</option>
										            </select>
										      </div>
										  <%}else{ %>
											 	 
									            <div class="form-group notranslate" id="plist" style="width: 60% !important">
										           <s:select list="inventoryPriscList" listKey="id" style="width: 100% !important"   id="newmedicine" listValue="genericname" headerKey="0" headerValue="Select Product" name="mdicinename" cssClass="form-control chosen-select">
										            </s:select> 
										        </div>
										  <%} %>
								          
								          
								          <div class="form-group qtyset" style="width: 5%;">
								                <input type="text" id="qty" class="form-control" placeholder="Qty" style="width:100%;"/>
								          </div>
								          <div class="form-group hidden-xs" style="width: 1%;">
									           	 	<a href="#" onclick="addsalenewRowJson('mytable')" title="Add New" class="plusbtnse"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a>
									      </div>
								          <!-- <a href="#" onclick="addnewRowIpd('mytable')" title="Add New" class="plusbtnse"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a> -->
								         <!-- <a href="#" onclick="addsalenewRowJson('mytable')" title="Add New" class="plusbtnse"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a> -->
								         </div>
         								</div>
									
									
                                
									<div class="minhesigh">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<table id="mytable" class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="">  
                                       	<th class="pharmacytableheadingfont" style="width: 2%;">Sr. No</th>
                                        <th class="pharmacytableheadingfont" style="width: 25%;">Name of Drug</th>
                                        <th class="text-center pharmacytableheadingfont" style="width: 5%;">Req. Qty</th>
                                        <th class="text-center pharmacytableheadingfont" style="width: 5%;">Available</th>
                                        <th class="text-center pharmacytableheadingfont" style="width: 5%;">Batch No</th>
                                        <th class="text-center pharmacytableheadingfont" style="width: 5%;">Expiry</th>
                                        <th class="text-center pharmacytableheadingfont" style="width: 4%;">Sale</th>
                                        <%if(loginInfo.getSuperadminid()==1 || loginInfo.isPprice_sale()){ %>
											<th class="text-center pharmacytableheadingfont" style="width: 4%;">P. Price</th>
										<%} %>
                                        <th class="text-center pharmacytableheadingfont" style="width: 5%;" class="text-right">Unit Price</th>
                                        <th class="pharmacytableheadingfont" style="width: 4%;text-align: center;">P.Shelf</th>
                                        <th class="pharmacytableheadingfont" style="width: 4%;text-align: center;">S.Shelf</th>
                                        <th class="pharmacytableheadingfont" style="width: 8%;" class="text-right">Total</th>
                                        <th class="pharmacytableheadingfont" style="width: 3%;text-align: center;">
                                        	<a href='#' onclick="deletemultiplepriscproduct()"><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a>
                                        </th>
                                       <!--  <th class="pharmacytableheadingfont" style="width: 2%;"></th> -->
                                    </tr>
                                </thead>
                                	<tbody id="innerTableBody" class="notranslate">
                                	<%int i=0; %>
                                	<%int s=0; %>
                                	<%-- 
                                	<s:iterator value="priscriptionlist">
                                    <s:if test="ostatus==0">
                                          
                                             <tr>
                                         <td><%=++s %></td>
                 						<td><s:property value="mdicinenametxt"/>
                 						  <input class="text-center pclass" type="hidden"  value="<s:property value="chargetempid"/>" />
                 						   <input class="text-center" id="countforcal<s:property value="chargetempid"/>" type="hidden"  value="<s:property value="countforcal"/>" />
                 						 <input class="text-center " type="hidden" name="product_id<s:property value="chargetempid"/>" value="<s:property value="product_id"/>" id="product_id<s:property value="chargetempid"/>" />  
                 						
                 						 <input class="text-center " type="hidden" name="medicineid<s:property value="chargetempid"/>" value="<s:property value="id"/>" />
                 						</td>
                                        <td class="text-center "  id="labelreq<s:property value="chargetempid"/>"><s:property value="required"/></td> <input type="hidden" id="req<s:property value="chargetempid"/>" name="reqqty<s:property value="chargetempid"/>" value="<s:property value="required"/>"/>
                                        <td class="text-center">
                                        <s:if test="sstatus==1">
                                          <label style="color: red"><s:property value="available"/></label> 
                                        </s:if>
                                        <s:else>
                                         <s:property value="available"/>
                                        </s:else>
                                        <input type="hidden" id="pur_price<s:property value="chargetempid"/>" value="<s:property value="purchase_price"/>" />
                                        <input type="hidden" id="pack<s:property value="chargetempid"/>" value="<s:property value="pack"/>" />
                                         <input type="hidden" name="id<s:property value="chargetempid"/>" value="<s:property value="product_id"/>" />
                                        </td>
                                        <td class="text-center"> <s:property value="batch_no"/> </td>
                                        <td class="text-center"> <s:property value="expiry_date"/> </td>	
                                        
                                        <td ><input type="number" value="<s:property value="required"/>" onchange="calsubTotal()" name="medicines[<%=i%>].saleqty" id="sale<%=i%>" class="form-control" style="height: 20px !important;background-color: rgba(255, 225, 225, 0.59);text-align:center;;"><input type="hidden" value="<s:property value="sale_price"/>" id="sale_price<%=i%>" /></td>
                                        <td ><input type="number" value="<s:property value="saleqty"/>" onchange="calsubTotal()" name="medicines[<%=i%>].saleqty" id="sale<%=i%>" class="form-control" style="height: 20px !important;background-color: rgba(255, 225, 225, 0.59);text-align:center;;"><input type="hidden" value="<s:property value="sale_price"/>" id="sale_price<%=i%>" /></td>
                                        <td ><input type="number" value="<s:property value="saleqty"/>" onchange="changeQtyFromSale(<s:property value="chargetempid"/>,<s:property value="chargesessionid"/>,<s:property value="chargetempid"/>)" name="saleqty<s:property value="chargetempid"/>" id="sale<s:property value="chargetempid"/>" class="form-control" style="height: 20px !important;background-color: rgba(255, 225, 225, 0.59);text-align:center;;"><input type="hidden" value="<s:property value="sale_price"/>" id="sale_price<s:property value="chargetempid"/>" /></td>
                                        <td > <input type="text" name="sale_price<s:property value="chargetempid"/>" readonly="readonly" class="form-control" value="<s:property value="mrp"/>" id="mrp<s:property value="chargetempid"/>" onchange="calsubTotal()" style="height: 20px !important;background-color: rgba(255, 225, 225, 0.59);text-align:center;;"/> <input type="hidden" id="tempsale<s:property value="chargetempid"/>" value="<s:property value="saleqty"/>" /></td>
                                        <td  class="text-center "><s:property value="shelf"/></td> 
                                        <td  class="text-right">Rs.<label id="totalmrp<s:property value="chargetempid"/>"><s:property value="total"/></label></td>
                                          <s:if test="sstatus==1">
                                            <!--  <td  class="text-center "><a href="#" onclick="deleteRow(this)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td> -->
                                             <td  class="text-center "><a href="#" onclick="deleteRowFromSession(this,<s:property value="chargesessionid"/>,<s:property value="chargetempid"/>,<s:property value="chargetempid"/>)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td>
                                            <td class="text-center "><a href="#" onclick="requestStock(<s:property value="product_id"/>,<s:property value="chargetempid"/>)" title="Request Stock"><i class="fa fa-cart-plus" aria-hidden="true" style="font-size: 17px;padding-top: 2px;"></i></a></td>
                                          </s:if>
                                          <s:else>
                                           <!-- <td  class="text-center"><a href="#" onclick="deleteRow(this)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td> -->
                                          	<td  class="text-center"><a href="#" onclick="deleteRowFromSession(this,<s:property value="chargesessionid"/>,<s:property value="chargetempid"/>,<s:property value="chargetempid"/>)" ><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td>
                                          </s:else>
                               
                                    	  <input type="hidden" id="prodd<s:property value="chargetempid"/>" value="<s:property value="available"/>" />
                                    	  <input type="hidden" id="vat<s:property value="chargetempid"/>" value="<s:property value="vat"/>" />
          
                                    </tr>
                                    </s:if>
                                    <%i++; %>
                                    </s:iterator> --%>
                                 <tr></tr>
                                </tbody>
                                    <input type="hidden" id="tempcount" value="<%=i%>" />
                                    <s:hidden name="selectedid" ></s:hidden>
                            </table>
                            </div>
						   </div>
								
								
								<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 minhesigh" >
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									<table class="table table-bordered" cellspacing="0" width="100%">
		                                <thead>
		                                <tr class="tableback">
		                                		<th class="pharmacytableheadingfont" width="2%" style="background-color: #4e7894;color: #fff;">Sr. No </th>
		                                        <th class="pharmacytableheadingfont" width="40%" style="background-color: #4e7894;color: #fff;">Request Product: 
		                                        <s:if test="ipdid!=0">IPD</s:if>
		                                        <s:else>OPD</s:else>/ 
		                                        <s:property value="invoiceTime" />
		                                        </th>
		                                        <th class="pharmacytableheadingfont" width="30%"  style="background-color: #4e7894;color: #fff;">Molecules</th>
		                                        <th class="pharmacytableheadingfont" style="background-color: #4e7894;color: #fff;">Dosage</th>
		                                        
		                                        <th width="10%" class="text-center pharmacytableheadingfont" style="background-color: #4e7894;color: #fff;">Days</th>
		                                        <th width="10%" class="text-center pharmacytableheadingfont" style="background-color: #4e7894;color: #fff;">Qty</th>
		                                    </tr>
		                                </thead>
                                	  <tbody class="notranslate">
                                	  <%int q=0; %>
                                	  <s:iterator value="priscriptionlist">
                                	 
                                    	<s:if test="ostatus==1">
                                    	  <tr style="color:red;">
                                    	   <td><%=++q %></td>
	                                        <td><s:property value="mdicinenametxtnew"/></td>
	                                        
	                                        <td><s:property value="molecules"/></td>
	                                        <td><s:property value="priscdose"/></td>
	                                        <td class="text-center"><s:property value="priscdays"/></td>
	                                        <td class="text-center"><s:property value="required"/></td>
                                    	  </tr>
                                    	</s:if>
                                    	<s:else>
                                    		<s:if test="repeatstatus==1">
	                                    		<tr><td><%=++q %></td>
			                                        <td><s:property value="mdicinenametxtnew"/></td>
			                                        <td></td>
			                                        <td><s:property value="priscdose"/></td>
			                                        <td class="text-center"><s:property value="priscdays"/></td>
			                                        <td class="text-center"><s:property value="required"/></td>
	                                    	  	</tr>
                                    		</s:if>
                                    	</s:else>
                                    </s:iterator>
	                                   <!--  <tr>
	                                        <td>GEN- ACKNOWLEDGEMENT FOR GIVING DISCHARGE SUMMERY ()</td>
	                                        <td>1-0-0</td>
	                                        <td>5</td>
	                                        <td>5</td>
	                                    </tr> -->
	                                   
                                    	<!--  <tr>
	                                        <td>GEN- ALKAMAX MB6 SYP 100ML ()</td>
	                                        <td>1-0-0</td>
	                                        <td>5</td>
	                                        <td>5</td>
                                    	</tr> -->
                                </tbody>
                               
                               
                               
                       
                                
                              
                            </table>
                            </div>
								</div>
                           
                            
									</div>
									<div class="col-lg-3 col-sm-12 col-md-3 col-xs-12">
										<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;border-bottom: 1px solid #ddd;" id="baldiv">
											<h3 style="color:#d9534f;">Balance : <span style="float: right;" >Rs.<span id="rebalance" class="notranslate"><s:property value="phar_pre_balance"/></span> <input type="hidden" value="<s:property value="phar_pre_balance"/>" id="prebalance" /> </span></h3>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 3px;text-align: right;padding:0px;">
			                            	<!--<h4><a href="#" title="clear Balance" onclick="clearbaldb()" class="btn btn-success btn-sm pull-left">clear balance</a>Sub Total : Rs.<span id="subtotal">00.00</span></h4>
			                            	
			                            	--><h4><a href="#" title="clear Balance" onclick="clearBalanceTemp()" class="btn btn-success btn-sm pull-left hidden">clear balance</a>Sub Total : Rs.<span id="subtotal">00.00</span></h4>
			                            	
			                            	<%if(loginInfo.isDisscount() || loginInfo.getUserType()==2) {%>
			                            		<h4 style="color: #868686;">Discount 
			                            		<select id="distype" name="discounttype" onchange="perorrs()" class="form-control" style="width: 20%;"> <option value="0">%</option><option value="1">Rs.</option></select>
			                            		 : 
			                            		 <s:if test="discountpatienttype==1">
			                            		 	<input type="number" id="discsmt" name="salediscount" onchange="calDiscount(this.value)" class="form-control" value="<%=loginInfo.getIpddiscount() %>" style="width: 20%;">
			                            		 </s:if>
			                            		 <s:else>
			                            		 	<input type="number" id="discsmt" name="salediscount" onchange="calDiscount(this.value)" class="form-control" value="<%=loginInfo.getOpddiscount() %>" style="width: 20%;">
			                            		 </s:else>
			                            		 
			                            		 <br><small>Rs.<label id="tdisc">00.00</label></small></h4>
			                            	<%} else { %>
			                            	
			                            	   <h4 class="hidden" style="color: #868686;">Discount 
			                            	   <select id="distype" name="discounttype" onchange="perorrs()" class="form-control" style="width: 20%;"><option value="0">%</option><option value="1">Rs.</option> </select> : 
			                            	   
			                            	   	 <s:if test="discountpatienttype==1">
			                            		 	<input type="number" id="discsmt" name="salediscount" onchange="calDiscount(this.value)" class="form-control" value="<%=loginInfo.getIpddiscount() %>" style="width: 20%;">
			                            		 </s:if>
			                            		 <s:else>
			                            		 	<input type="number" id="discsmt" name="salediscount" onchange="calDiscount(this.value)" class="form-control" value="<%=loginInfo.getOpddiscount() %>" style="width: 20%;">
			                            		 </s:else>
			                            	   
			                            	   
			                            	   <br><small>Rs.<label id="tdisc">00.00</label></small></h4>
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
											    <label class="labelfontsize" for="exampleInputName2">Received Rs.</label>
											    <%-- <s:textfield onkeyup="getRemainAmt(this.value)" id="received" name="payamt" cssClass="form-control" cssStyle="width: 43%;background-color: rgba(165, 42, 42, 0.07);" /> --%>
											  <input type="number"  onkeyup="getRemainAmt(this.value)" id="received" min="0" name="payamt" class="form-control" style="width: 43%;background-color: rgba(165, 42, 42, 0.07);">
											  </div>
											  <div class="form-group" style="width: 49%;">
											    <label class="labelfontsize" for="exampleInputEmail2">Return Rs.</label>
											    <input id="returnamt" type="number" class="form-control" style="width: 45%;background-color: rgba(165, 42, 42, 0.07);">
											  </div>
											</div>
											
			                             </div>
			                             <div class="col-lg-12 col-md-12 col-xs-12 text-right" style="padding:0px;">
			                             <div class="form-group">
			                             	<s:textarea theme="simple" cssClass="form-control " id="notes"  name="edit_note" rows="3" placeholder="Write Note" cssStyle="height: 50px !important;"/>
			                             </div>
	                            
	                            
	                            <div class="form-group notranslate" >
	                            	<select class="form-control pharmacyddinput"  id="paytype" onchange="setPaymode(this.value)" style="background-color: rgba(255, 140, 0, 0.36);">
	                            	<option value="Cash">CASH</option>
	                            	<option value="Card">CARD</option>
	                            	<option value="Cheque">CHEQUE</option>
	                            	<option value="NEFT">NEFT</option>
	                            	<option value="Credit">CREDIT</option>
	                            	<!-- <option value="Estimate">ESTIMATE</option> -->
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
	                            <a type="button" class="btn btn-primary hidden"  title="Cash Payment" onclick="newsale('Cash')">Save & Print</a>
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
<% String clinicid=loginInfo.getClinicid1(); %>
		<input type="hidden" id="hiddenclinicid" value="<%=clinicid%>">							                             <!-- Modal for confirmiing pay - lokesh -->
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
		    <span> Amount<h4 id="npopup"></h4> </span>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="checkCashmOde()" data-dismiss="modal" value="Ok">
         
        
      </div>
    </div>

  </div>
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
		
		
		
							<script>
								$(document).ready(function() {
								   calsubTotal();
								 
								
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
  </script>
<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '218px',
								        railVisible: true,
										alwaysVisible: true
								    });
								});
							</script>
							


</body>
</html>