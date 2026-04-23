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
    
    <SCRIPT type="text/javascript">

     window.onload= function(){
     
                $("#date").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

			   });
			   
     }

</SCRIPT>
    
    
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
        
    </SCRIPT>
    
    
    
    
</head>
   <body>
							<div class="row details mainheader hidden">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Update Sale</h4>
								</div>
							</div>
							<div class="">
							
							<%
								LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   					%>
							
							<form action="opdipdsalePharmacy" target="_blank" method="post" id="creditform">
							<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
							<div class="col-lg-12 col-md-12 col-sm-12 searchtab">
							<div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
								<label for="inputEmail3" class="text-left">Search :</label>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-8 col-xs-3">
							
							<div class="form-group">
								<input type="text" name="inhousepid" id="inhousepid" class="form-control" onclick="showInhousePatientPopup()"  placeholder="Search Patient" style="width: 100%;">
			                </div>	
								  		
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: left;margin-top: -4px; display: inline-flex;">
								<input type="button" value="Refresh" class="btn btn-primary"  onclick="clearAll()" />
								
								<ul class="vertical default_list" id="" style="list-style: none;">
								     <s:if test="tp_bill_access==1">
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="istp" checked="checked" onclick="calsubTotal()" class="tp" ><i></i> Third Party</label></li>								     
								     </s:if>
								     <s:else>
								           <input type="checkbox" id="istp" class="hidden" class="tp" >
								     </s:else>
								</ul>
							</div>
							
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: right;">
								<span><i class="fa fa-square" aria-hidden="true" style="color: #e05d6f;"></i> Narcotics &nbsp; | &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: #e69522;"></i> H1</span>
							</div>
							
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;padding-top: 8px;">
								<div class="col-lg-1 col-md-1 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">B.NO</label>
										    <p>-</p>
										    <input type="hidden" id="extpid" name="extpid" />
										    <input type="hidden" id="tpid" value="0" name="tpid" />
										    <input type="hidden" id="tpnameid" name="tpnameid" />
										    <input type="hidden" id="ipdopd" name="ipdopd" />
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label>
										    <s:textfield cssClass="form-control" name="fullname" id="fullname" cssStyle="text-transform: uppercase;"/>
								  		</div>
								  		
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">MOBILE</label>
										    <s:textfield cssClass="form-control" name="mobno" id="mobile" maxlength="10"/>
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">ADDRESS</label>
											    <s:textfield cssClass="form-control" name="wardname" id="wardname" cssStyle="text-transform: uppercase;"/>
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
										      --><s:textfield cssClass="form-control" name="practitionerName" id="doctor"  cssStyle="text-transform: uppercase;"/>
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
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 padsets" style="background-color: #dff0d8;padding: 14px 0px 0px 11px;padding-bottom: 0px;">
								         <div class="form-inline disinset">
								         <div class="form-group hidden-xs" style="">
								                <input type="text" id="barcode" readonly="readonly" onchange="addBarcodeRequest(this.value)" class="form-control" placeholder="Barcode Here" autofocus="autofocus" style="width:100%;" />
								          </div>
								          <div class="form-group hidden-xs">
								           <p style="font-size: 13px;margin: 0px 0px 2px;">Product :</p> 
								          </div>
								          
								          <div class="form-group set100">
								           <s:select list="inventoryPriscList" listKey="id" id="newmedicine" listValue="genericname" headerKey="0" headerValue="select product" name="mdicinename" cssClass="form-control chosen-select set100 widthsets">
								            </s:select> 
								          </div>
								          <div class="form-group qtyset" style="width: 5%;">
								                <input type="text" id="qty" class="form-control" placeholder="Qty" style="width:100%;"/>
								          </div>
								          <a href="#" onclick="addnewRow('mytable')" title="Add New" class="plusbtnse"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a>
								         
								         </div>
         								</div>
                                
                                
									<div>
										<table id="mytable" class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="tableback">  
                                        <th style="width: 25%;">Name of Drug</th>
                                        <th style="width: 4%;">Req. Qty</th>
                                        <th style="width: 10%;">HSN No</th>
                                        <th style="width: 12%;">Avail/Batch No/Exp.</th>
                                        <th style="width: 4%;">Sale</th>
                                        <th style="width: 5%;" class="text-right">Unit Price</th>
                                        <th class="" style="width: 7%;text-align: center;">Shelf</th>
                                        <th style="width: 8%;" class="text-right">Total</th>
                                        <th style="width: 3%;"></th>
                                        <th style="width: 2%;"></th>
                                    </tr>
                                </thead>
                                	<tbody id="innerTableBody">
                                	<!--
                                	<s:iterator value="priscriptionlist">
                                    <tr>
                                        <td class="settdback" style="border: none !important;"><s:property value="mdicinenametxt"/></td>
                                        <td class="settdback" style="border: none !important;">
                                        <s:if test="available<=0">
                                          <label style="color: red"><s:property value="available"/></label> 
                                        </s:if>
                                        <s:else>
                                         <s:property value="available"/>
                                        </s:else>
                                        /
                                        <s:property value="batch_no"/>
                                        /<s:property value="expiry_date"/></td>
                                        <td class="settdback" style="border: none !important;background-color: #fff !important;color: #555 !important;"><input type="text" onchange="calsubTotal()" name="medicines].saleqty" id="sale" class="form-control"></td>
                                        <td class="settdback" style="border: none !important;background-color: #fff !important;color: #555 !important;" class="text-right">Rs.<s:property value="mrp"/></td><input type="hidden" value="<s:property value="mrp"/>" id="mrp"/> 
                                        <td class="settdback" style="border: none !important;background-color: #fff !important;color: #555 !important;" class="text-right">Rs.<label id="totalmrp>">00.00</label></td>
                                          <s:if test="sstatus==1">
                                            <td class="settdback" style="border: none !important;background-color: #fff !important;color: #555 !important;" class="text-center"><a href="#" title="Request Stock"><i class="fa fa-cart-plus" aria-hidden="true" style="font-size: 17px;padding-top: 2px;"></i></a></td>
                                          </s:if>
                                          <s:else>
                                           <td class="settdback" style="border: none !important;background-color: #fff !important;color: #555 !important;" style="border-bottom: none !important;" class="text-center"><a href="#" onclick="deleteRow(this)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td>
                                          </s:else>
                                    
                                    </tr>
                                    
                                    </s:iterator>
                                    <s:if test="inventoryPriscList.size<1">
                                         <tr></tr>
                                    </s:if>
                                      -->
                                
                                    
                                    
                              
                                 <tr></tr>
                                </tbody>
                                    <input type="hidden" id="tempcount" value="0" />
                                    <s:hidden name="selectedid" ></s:hidden>
                            </table>
						   </div>
								
                           
                            
									</div>
									<div class="col-lg-3 col-sm-12 col-md-3 col-xs-12">
										<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;border-bottom: 1px solid #ddd;" id="baldiv">
											<h3 style="color:#d9534f;">Balance : <span style="float: right;" >Rs.<span id="rebalance">00.00</span> <input type="hidden" value="0" id="prebalance" /> </span></h3>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 3px;text-align: right;padding:0px;">
			                            	<!--<h4><a href="#" title="clear Balance" onclick="clearbaldb()" class="btn btn-success btn-sm pull-left">clear balance</a>Sub Total : Rs.<span id="subtotal">00.00</span></h4>
			                            	
			                            	--><h4><a href="#" title="clear Balance" onclick="clearBalanceTemp()" class="btn btn-success btn-sm pull-left">clear balance</a>Sub Total : Rs.<span id="subtotal">00.00</span></h4>
			                            	
			                            	<%if(loginInfo.isDisscount() || loginInfo.getUserType()==2) {%>
			                            		<h4 style="color: #868686;">Discount <select id="distype" onchange="perorrs()" class="form-control" style="width: 20%;"><option value="1">Rs.</option><option value="0">%</option></select> : <input type="text" id="discsmt" onchange="calDiscount(this.value)" class="form-control" value="0" style="width: 20%;"><br><small>Rs.<label id="tdisc">00.00</label></small></h4>
			                            	<%} else { %>
			                            	
			                            	   <h4 class="hidden" style="color: #868686;">Discount <select id="distype" onchange="perorrs()" class="form-control" style="width: 20%;"><option value="1">Rs.</option><option value="0">%</option></select> : <input type="text" id="discsmt" onchange="calDiscount(this.value)" class="form-control" value="0" style="width: 20%;"><br><small>Rs.<label id="tdisc">00.00</label></small></h4>
			                            	<%} %>
			                            	
			                            	<h4 class="hidden" style="color: #868686;">Refund: Rs.00.00</h4>
			                            	<h4 class="hidden">Vat : Rs.<label id="vat">00.00</label></h4> <input type="hidden" id="tvat" name="vat"/> <input type="hidden" id="tdiscount" name="discount" />
			                            	<h4 class="">CGST : Rs.<label id="cgst">00.00</label></h4> <input type="hidden" id="tcgst" name="cgst"/> 
			                            	<h4 class="">SGST : Rs.<label id="sgst">00.00</label></h4> <input type="hidden" id="tsgst" name="sgst"/> 
			                            	<h4 style="font-weight: bold;color:green;">Gross Total : Rs.<span id="grosstotalspan">00.00</span> <input type="hidden" name="grosstotal" id="grosstotal"/> <input type="hidden" name="grosssubtotal" id="grosssubtotal"/> </h4>
			                            	<h4 style="font-weight: bold;color:green;">Net Total : Rs.<span id="total">00.00</span> <input type="hidden" name="total" id="ttotal"/> </h4> 
			                            	<h4 class="hidden" style="color: #868686;">Balance : <input type="text" name="balance" id="balance" class="form-control" value="0" style="width: 20%;"></h4>
			                            	<h4 class=""><small>Total with balance: Rs.<span id="baltotal"></span></small></h4>
	                            		</div>
	                            		<div class="col-lg-12 col-md-12 col-xs-12 hidden-xs returfed">
			                            <div class="form-inline">
											  <div class="form-group" style="width: 49%">
											    <label for="exampleInputName2">Received Rs.</label>
											    <s:textfield onkeyup="getRemainAmt(this.value)" id="received" name="payamt" cssClass="form-control" cssStyle="width: 43%;background-color: rgba(165, 42, 42, 0.07);" />
											  </div>
											  <div class="form-group" style="width: 49%;">
											    <label for="exampleInputEmail2">Return Rs.</label>
											    <input id="returnamt" type="text" class="form-control" style="width: 45%;background-color: rgba(165, 42, 42, 0.07);">
											  </div>
											</div>
											
			                             </div>
			                             <div class="col-lg-12 col-md-12 col-xs-12 text-right" style="padding:0px;">
			                             <div class="form-group">
			                             	<s:textarea theme="simple" cssClass="form-control"  name="notes" rows="3" placeholder="Write Note" cssStyle="height: 50px !important;"/>
			                             </div>
	                            
	                            
	                            <div class="form-group" >
	                            	<select class="form-control" style="width: 100%;"  id="paytype" onchange="setPaymode(this.value)" style="background-color: rgba(255, 140, 0, 0.36);">
	                            	<option value="Cash">CASH</option>
	                            	<option value="Card">CARD</option>
	                            	<option value="Cheque">CHEQUE</option>
	                            	<option value="NEFT">NEFT</option>
	                            	<option value="Credit">CREDIT</option>
	                            	<option value="Estimate">ESTIMATE</option>
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
	                            	<a type="button" class="btn btn-primary" onclick="newsale()">Save 11 & Print</a>
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
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/ipd/pages/ipdPatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
  </script>



</body>
</html>