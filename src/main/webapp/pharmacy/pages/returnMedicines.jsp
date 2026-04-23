<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 <%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link href="common/css/printpreview.css" rel="stylesheet" />
    
   
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
    border: none !important;
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
    border:none !important; 
}
label {
    font-size: 12px;
}
.searchtab{
	padding:0px;border-bottom: 1px dashed #ddd;padding-bottom: 0px;    padding-bottom: 2px;padding-top: 4px;
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
 


<SCRIPT type="text/javascript">
	  
	    function updatemdicine(){
	    
	    	document.getElementById("creditform").submit();      
	    }
	    
	    window.onload = function () {
	    	 document.addEventListener("contextmenu", function(e){
	    			e.preventDefault();
	    			}, false); 
	    	setreturntype();
	    	
	    };
	    
	    function setreturntype(){
		    var val = document.getElementById("returnmode").value;  
		    if(val=="Cash"){
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	            var selectobject=document.getElementById("returnmode");
				 for (var i=0; i<selectobject.length; i++){
				 if (selectobject.options[i].value == 'Credit' )
				    selectobject.remove(i);
				 }
	      } else if(val=="Card"){
	      
	            document.getElementById("card").className="form-control";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	            document.getElementById("card").focus();
	            var selectobject=document.getElementById("returnmode");
				 for (var i=0; i<selectobject.length; i++){
				 if (selectobject.options[i].value == 'Credit' )
				    selectobject.remove(i);
				 }
	      } else if(val=="Cheque"){
	      
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control";
	            document.getElementById("chequeno").focus();
	            var selectobject=document.getElementById("returnmode");
				 for (var i=0; i<selectobject.length; i++){
				 if (selectobject.options[i].value == 'Credit' )
				    selectobject.remove(i);
				 }
	      }  
	      else if(val=="NEFT"){
	             document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control";
	            document.getElementById("chequeno").className="form-control hidden";
	            document.getElementById("neftid").focus();
	            var selectobject=document.getElementById("returnmode");
				 for (var i=0; i<selectobject.length; i++){
				 if (selectobject.options[i].value == 'Credit' )
				    selectobject.remove(i);
				 }
	      } else if(val=="Credit") {
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	           /*  document.getElementById("received").value=0; */
	      		
	            var selectobject=document.getElementById("returnmode");
				var flagreturntest=false;
				for (var i=0; i<selectobject.length; i++){
					 if (selectobject.options[i].value == 'Credit' ){
						 flagreturntest=true;
					 }
				}
				 if(!flagreturntest){
					 var opt = document.createElement('option');
					 opt.value = 'Credit';
					 opt.innerHTML = 'CREDIT';
					 selectobject.appendChild(opt);
				 }
				 $('#returnmode option[value!="Credit"]').remove();
				 
	      }else if(val=="Hospital" ) {
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	            document.getElementById("received").value=0;
	      		
	            var selectobject=document.getElementById("returnmode");
				 for (var i=0; i<selectobject.length; i++){
				 if (selectobject.options[i].value == 'Credit' )
				    selectobject.remove(i);
				 }
	      } else {
	    	   var selectobject=document.getElementById("returnmode");
				 for (var i=0; i<selectobject.length; i++){
				 if (selectobject.options[i].value == 'Credit' )
				    selectobject.remove(i);
				 }
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	      }
	    	calRefundTotaltemp();
	    }
	</SCRIPT>    
    
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
							<form action="creditPharmacy" method="post" id="creditform">
							<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
							<div class="col-lg-12 col-md-12 col-sm-12 searchtab">
							<div class="col-lg-1 col-md-1 col-sm-2 col-xs-4">
								<label for="inputEmail3" class="text-left">Search :</label>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-8 col-xs-4">
							<div class="form-group">
										     	<input type="text" readonly="readonly" name="inhousepid" id="inhousepid" class="form-control" onclick="showInhousePatientPopup()"  placeholder="Search Patient" style="width: 100%;">
								  		</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-2 col-xs-4"></div>
							</div>
							
							
							<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;padding-top: 8px;">
									<div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">BILL NUMBER</label>
										    <p><s:property value="billno"/></p>
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label>
										    <s:textfield cssClass="form-control" name="fullname"  readonly="true"/>
										    <s:hidden id="opdid" name="opdid"></s:hidden>
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">MOBILE NO</label>
										    <s:textfield cssClass="form-control" name="mobno" readonly="true"/>
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">LOCATION</label>
											    <s:textfield cssClass="form-control" name="wardname" readonly="true"/>
									  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DR. NAME</label>
										    <s:hidden id="phar_practid" name="phar_practid"></s:hidden>
										      <s:textfield  cssClass="form-control" name="practitionerName" readonly="true" />
								  		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DATE</label>
										    <s:textfield cssClass="form-control" name="dateTime" readonly="true"/>
								  		</div>
									</div>
							</div>
							
									
									
									
                                </div>
							
							
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									
									<div class="col-lg-9 col-sm-9 col-md-9 col-xs-9 rightset">
									
									<s:hidden name="billno"></s:hidden>
									<s:hidden name="paymode" id="paymode"></s:hidden>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="background-color: #dff0d8;padding: 14px 0px 0px 11px;padding-bottom: 0px;">
								         <div class="form-inline">
								          <div class="form-group">
								           <p style="font-size: 13px;margin: 0px 0px 2px;">Medicine :</p> 
								          </div>
								          <div class="form-group">
								          <%--  <s:select list="medicineList" listKey="id" id="newmedicine" listValue="name" headerKey="0" headerValue="select medicine" name="mdicinename" cssClass="form-control showToolTip chosen">
								            </s:select>  --%>
								          </div>
								            <div class="form-group hidden">
								                <input type="text" id="qty" class="form-control" placeholder="qty" />
								          </div>
								          <a href="#" onclick="addnewRow('mytable')" title="Add New"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a>
								         
								         </div>
         								</div>
									
									 <s:if test="priscriptionlistnew.empty">
									 	<div >
		                            </s:if>
		                            <s:else>
		                            	<div  class="1stlist">
		                            </s:else>
									
										<table id="mytable" class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="tableback" style="border: 1px solid white;"> 
                                    	<th style="width: 2%;border: 1px solid white;" >Sr. No</th>
                                        <th style="width: 31%;border: 1px solid white;">Name of Drug</th>
                                        <th style="width: 6%;border: 1px solid white;" class="">Sold Qty</th>
                                        <th style="width: 6%;border: 1px solid white;">Available Qty</th>
                                        <th style="width: 6%;border: 1px solid white;">Batch No</th>
                                        <th style="width: 6%;border: 1px solid white;">Expiry Date</th>
                                        <th style="width: 6%;border: 1px solid white;">P.Shelf</th>
                                        <th style="width: 6%;border: 1px solid white;">S.Shelf</th>
                                        <th style="width: 3%;border: 1px solid white;">Sale</th>
                                        <th style="width: 3%;border: 1px solid white;">Return</th>
                                        <th style="width: 8%;border: 1px solid white;" class="text-right">Unit Price</th>
                                        <th style="width: 8%;border: 1px solid white;" class="text-right">Total</th>
                                        <th style="width: 3%;border: 1px solid white;"></th>
                                    </tr>
                                </thead>
                                	<tbody class="notranslate">
                                	<%int i=0; %>
                                		<%int s=0; %>
                                	<s:iterator value="priscriptionlist">
                                    <tr style='color:<s:property value="color"/>'>
                                   		<td style="text-align: center;"> <%=++s %></td>
                                        <input type="hidden" name="medicines[<%=i%>].id" value="<s:property value="id"/>" /><input type="hidden" class="pclass" value="<%=i%>" />
                 						
                 						<td style="border-bottom: none !important;"><s:property value="mdicinenametxt"/> <input type="hidden" name="medicines[<%=i%>].product_id" value="<s:property value="product_id"/>" />  </td>
                                        <td style="border-bottom: none !important;text-align: center;"><s:property value="reqqty"/></td> <input type="hidden" id="req<%=i%>" name="medicines[<%=i%>].reqqty" value="<s:property value="reqqty"/>"/>
                                       
                                        <td style="border-bottom: none !important;text-align: center;">
                                        <input type="hidden" id="discper<%=i%>"  name='medicines[<%=i%>].indidiscount' value="<s:property value="discper"/>"/>
                                        <s:if test="sstatus==1">
                                          <label style="color: red;"><s:property value="returnedqty"/></label> 
                                        </s:if>
                                        <s:else>
                                         <%-- <s:property value="saleqty"/> --%> <s:property value="returnedqty"/>
                                        </s:else>
                                        </td>
                                        <td style="text-align: center;"><s:property value="batch_no"/></td>
                                        <td style="text-align: center;"><s:property value="expiry_date"/></td>
                                        <td style="border-bottom: none !important;text-align: center;"><s:property value="shelf"/></td>
                                        <td style="border-bottom: none !important;text-align: center;"><s:property value="secondary_shelf"/></td>
                                        <td style="text-align: center;"><%-- <s:property value="saleqty"/> --%> <s:property value="saleqty"/></td>
                                        <td style="border-bottom: none !important;"> <input type="number" min="0" name="medicines[<%=i%>].returnqty" value="0" class="form-control" id="returnqty<%=i%>" onchange="calRefundTotaltemp()" style="height: 20px !important;"/> <input type="hidden" value="<s:property value="saleqty"/>"  name="medicines[<%=i%>].saleqty" id="sale<%=i%>" class="form-control"><input type="hidden" id="tempsale<%=i%>" value="<s:property value="returnedqty"/>" /></td>
                                        <td style="border-bottom: none !important;" class="text-right">Rs.<s:property value="mrp"/></td><input type="hidden" value="<s:property value="mrp"/>" id="mrp<%=i%>" name="medicines[<%=i%>].mrp"/> 
                                        <td style="border-bottom: none !important;" class="text-right">Rs.<label id="totalmrp<%=i%>"><s:property value="total"/></label></td>
                                          <s:if test="sstatus==1">
                                            <td style="border-bottom: none !important;" class="text-center"><a href="#" onclick="deleteRowtemp(this)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a>  
                                             <a href="#" onclick="requestStock(<s:property value="mdicinenameid"/>,<%=i%>)" title="Request Stock"><i class="fa fa-cart-plus" aria-hidden="true" style="font-size: 17px;padding-top: 2px;"></i></a></td>
                                          </s:if>
                                          <s:else>
                                           <td style="border-bottom: none !important;" class="text-center"><a href="#" onclick="deleteRowtemp(this)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td>
                                          </s:else>
                                    
                                          <input type="hidden" id="tmedicineid<%=i%>" name="medicines[<%=i%>].mdicinenameid" value="<s:property value="mdicinenameid"/>" />    
                                    	  <input type="hidden" id="prodd<%=i%>" value="<s:property value="available"/>" />
                                    	  <input type="hidden" id="vat<%=i%>" value="<s:property value="vat"/>" />
          
                                    </tr>
                                    
                                    <%i++; %>
                                    </s:iterator>
                                    <input type="hidden" id="tempcount" value="<%=i%>" />
                                    
                                    <s:hidden name="selectedid" ></s:hidden>
                                    
                                </tbody>
                            </table>
						   </div>
									
                            
                            <s:if test="priscriptionlistnew.empty">
                            	
                            	
                            </s:if>
                            <s:else>
                            	<div class="2ndlist">
	                            	<table  class="table table-bordered " cellspacing="0" width="100%" style="margin-bottom: 0px;">
		                                <thead>
		                                    <tr class="tableback"> 
		                                    	<th style="width: 2%;">Sr</th>
		                                        <th style="width: 31%;">Name of Drug</th>
		                                        <th style="width: 3%;">Return Qty</th>
		                                    </tr>
		                                </thead>
	                                	<tbody class="notranslate">
	                                		<%int countsss =0; %>
	                                		<s:iterator value="priscriptionlistnew">
	                                			<tr>
		                                			<td><%=(++countsss) %></td>
		                                			<td><s:property value="mdicinenametxtnew"/></td>
		                                			<td><s:property value="returnedqty"/></td>
	                                			</tr>
	                                		</s:iterator>
	                                	</tbody>
	                            	</table>
                            	</div>
                            </s:else>
								
						   
                            
                            
                            
						  </div>
						  <div class="col-lg-3 col-sm-3 col-md-3 col-xs-3">
										<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;border-bottom: 1px solid #ddd;" id="baldiv">
											<h3 style="color:#d9534f;">Balance : <span style="float: right;" >Rs.<span class="notranslate" id="rebalance"><s:property value="balance"/></span> <input type="hidden" name="balance" id="prebalance" /> </span></h3>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 3px;text-align: right;padding:0px;"><s:hidden id="subbtotal" name="subbtotal" value="0"></s:hidden>
			                            	<h4 style="font-size: 15px;"><a href="#" title="clear Balance" onclick="clearpatientbaldb()" class="btn btn-success btn-sm pull-left hidden">clear balance</a>Sub Total : Rs.<span id="subtotal" class="notranslate"><s:property value="subtotal"/></span></h4>
			                            	<%-- <h4 class="hidden" style="color: #868686;font-size: 15px;">Discount <select id="distype" onchange="perorrs()" class="form-control" style="width: 10%;"><option value="0">%</option><option value="1">Rs.</option></select> : <input type="text" id="discsmt"  onchange="calDiscount(this.value)" class="form-control" value="<s:property value="discount"/>" style="width: 17%;"><br><small>Rs.<label id="tdisc"><s:property value="discount"/></label></small></h4> --%>
			                            	<h4 style="color: #868686;font-size: 15px;">Discount <input type="hidden" id="distype" value="0"> <s:hidden id="discsmt" name="discper"></s:hidden>  <small><label ><s:property value="discper"/></label>%</small></h4>
			                            	<h4 style="color: #868686;font-size: 15px;" >Refund: Rs.<span class="notranslate" id="refund">00.00</span> <s:hidden id="trefund" name="refundamt"></s:hidden> </h4>
			                            	<h4 class="hidden" style="font-size: 15px;">Vat : Rs.<label id="vat" class="notranslate"><s:property value="vat"/></label></h4> <s:hidden id="tvat" name="vat"/> <s:hidden id="tdiscount" name="discount" />
			                            	<h4 class="" style="font-size: 15px;">CGST : Rs.<label class="notranslate" id="tcgst"></label><s:hidden id="cgst" name="cgst"/> </h4> 
			                            	<h4 class="" style="font-size: 15px;">SGST : Rs.<label class="notranslate" id="tsgst"></label><s:hidden id="sgst" name="sgst"/> </h4> 
			                            	<h4 style="font-weight: bold;color:green;">Gross Total : Rs.<span class="notranslate" id="grosstotalspan">00.00</span> <input type="hidden" name="grosstotal" id="grosstotal"/> <input type="hidden" name="grosssubtotal" id="grosssubtotal"/> </h4>
			                            	<h4 style="font-weight: bold;font-size: 15px;color:green;">Net Total : Rs.<span class="notranslate" id="total"><s:property value="total"/></span> <s:hidden name="total" id="ttotal"/> </h4>
			                            	<s:textarea theme="simple" cssClass="form-control"  name="notes" rows="3" placeholder="Write Note" cssStyle="margin-top: 15px;height: 50px !important;"/>
			                            	<h4 class="notranslate">
			                            	  <s:if test="returnmode=='Hospital'">
			                            	       <s:select cssClass="form-control" list="#{'Hospital':'HOSPITAL'}" theme="simple" cssStyle="width: 100%;" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)" style="background-color: rgba(255, 140, 0, 0.36);" />
			                            	  </s:if>
			                            	  <s:else>
			                            	  		<s:select cssClass="form-control" list="#{'0':'Select Mode','Cash':'CASH','Credit':'CREDIT','Hospital':'HOSPITAL','Card':'CARD','Cheque':'CHEQUE','NEFT':'NEFT','Online Payment':'ONLINE PAYMENT'}" theme="simple" cssStyle="width: 100%;" name="returnmode" id="returnmode" onchange="setRetunPaymode(this.value)" style="background-color: rgba(255, 140, 0, 0.36);" />
			                            	  </s:else>
			                            	   
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
			                            	
			                            	
			                            	<h4 class="hidden"><small>Total with balance: Rs.<span id="baltotal" class="notranslate"></span></small></h4> 
			                            	 
	                            
			                            </div>
										<div class="col-lg-12 col-md-12 col-xs-12 text-right" style="padding-top:10px;padding:0px;">
                            	<!--<a type="button" class="btn btn-primary"  title="Online" >Online</a>
                            	--><a type="button" class="btn btn-primary hidden"  title="Credit" onclick="credit()">Credit</a>
	                            <a type="button" class="btn btn-primary btn-lg" style="height: 45px !important;line-height: 30px;" title="Cash Payment" onclick="refundMedicine()" id="returnbtn">Return</a>
                            </div>
									</div>
									</form>
								</div>
							
		
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
    	<span>Amount<h4 id="npopup"></h4> </span>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="checkSingleReturnmOde()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>				
							
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
<script>
	jQuery(document).ready(function(){
	jQuery(".chosen").chosen();
});
</script>
<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

<script>
				             $(function() {
								  $('.1stlist').slimScroll({
								   		height : '370px',
								   		railVisible: true,
										alwaysVisible: true
								  });
					             $('.2ndlist').slimScroll({
								   		height : '145px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>

</body>
</html>