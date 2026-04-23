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
    border-bottom: 1px solid #ddd;
    background-color: rgba(204, 204, 204, 0.24);
    margin-top: 10px;
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
.blankbx{
	background-color: #f3f9d4 !important;
}

    </style>
    
	<SCRIPT type="text/javascript" src="pharmacy/js/pharmacy.js"></SCRIPT>
	<SCRIPT type="text/javascript">
	  
	    function updatemdicine(){
	    
	    	document.getElementById("creditform").submit();      
	    }
	   
	</SCRIPT>    
    
</head>
<body>
							<div class="row details mainheader hidden">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Update Sale</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 15px;">
									<div class="col-lg-4 col-sm-4 col-md-4 col-xs-4">
										<img src="emr/img/updsale.jpg" class="img-responsive"></img>
									</div>
									<div class="col-lg-8 col-sm-8 col-md-8 col-xs-8" style="padding: 2px;padding-bottom: 10px;">
									<form action="creditPharmacy" method="post" id="creditform">
									<s:hidden name="billno"></s:hidden>
									<s:hidden name="paymode" id="paymode"></s:hidden>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: #dff0d8;padding: 14px 0px 0px 11px;border: 2px solid #cecece;padding-bottom: 0px;">
								         <div class="form-inline">
								          <div class="form-group">
								           <p style="font-size: 13px;margin: 0px 0px 2px;">Add New Product :</p> 
								          </div>
								          <div class="form-group">
								           <s:select list="medicineList" listKey="id" id="newmedicine" listValue="name" headerKey="0" headerValue="select product" name="mdicinename" cssClass="form-control showToolTip chosen">
								            </s:select> 
								          </div>
								           <div class="form-group">
								                <input type="text" id="qty" class="form-control" placeholder="Qty" />
								          </div>
								          <a href="#" onclick="addnewRow('mytable')" title="Add New"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a>
								         
								         </div>
         								</div>
									
									<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
									<div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">B.NO</label>
										    <p><s:property value="billno"/></p>
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label>
										    <s:textfield cssClass="form-control" name="fullname" />
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">MOBILE NO</label>
										    <s:textfield cssClass="form-control" name="mobile" />
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">LOCATION</label>
											    <s:textfield cssClass="form-control" name="wardname"/>
									  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DATE</label>
										    <s:textfield cssClass="form-control" name="dateTime"/>
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DR. NAME</label>
										      <s:textfield cssClass="form-control" name="practitionerName"  />
								  		</div>
									</div>
									
                                </div>
									<div>
										<table id="mytable" class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="tableback">  
                                        <th style="width: 31%;">Name of Drug</th>
                                        <th style="width: 6%;">Req. Qty</th>
                                        <th style="width: 12%;">Avail/Batch No/Exp.</th>
                                        <th style="width: 3%;">Sale</th>
                                        <th style="width: 8%;" class="text-right">Unit Price</th>
                                        <th style="width: 8%;" class="text-right">Total</th>
                                        <th style="width: 3%;"></th>
                                    </tr>
                                </thead>
                                	<tbody>
                                	<%int i=0; %>
                                	<s:iterator value="priscriptionlist">
                                    <tr>
                                        <input type="hidden" name="medicines[<%=i%>].id" value="<s:property value="id"/>" />
                                        <td style="border-bottom: none !important;"><s:property value="mdicinenametxt"/></td>
                                        <td style="border-bottom: none !important;"><s:property value="reqqty"/></td> <input type="hidden" id="req<%=i%>" name="medicines[<%=i%>].reqqty" value="<s:property value="reqqty"/>"/>
                                        <td style="border-bottom: none !important;">
                                        <s:if test="sstatus==1">
                                          <label style="color: red"><s:property value="available"/></label> 
                                        </s:if>
                                        <s:else>
                                         <s:property value="available"/>
                                        </s:else>
                                        /
                                            <s:property value="batch_no"/>
                                        /<s:property value="expiry_date"/></td>
                                        <td style="border-bottom: none !important;"><input type="text" value="<s:property value="saleqty"/>" onchange="calsubTotal()" name="medicines[<%=i%>].saleqty" id="sale<%=i%>" class="form-control"></td>
                                        <td style="border-bottom: none !important;" class="text-right">Rs.<s:property value="mrp"/></td><input type="hidden" value="<s:property value="mrp"/>" id="mrp<%=i%>"/> 
                                        <td style="border-bottom: none !important;" class="text-right">Rs.<label id="totalmrp<%=i%>"><s:property value="total"/></label></td>
                                          <s:if test="sstatus==1">
                                            <td class="text-center"><a href="#" onclick="requestStock(<s:property value="mdicinenameid"/>,<%=i%>)" title="Request Stock"><i class="fa fa-cart-plus" aria-hidden="true" style="font-size: 17px;padding-top: 2px;"></i></a></td>
                                          </s:if>
                                          <s:else>
                                           <td style="border-bottom: none !important;" class="text-center"><a href="#" onclick="deleteRow(this)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td>
                                          </s:else>
                                    
                                          <input type="hidden" id="tmedicineid"+i+"" name="medicines[<%=i%>].mdicinenameid" value="<s:property value="mdicinenameid"/>" />    
                                    </tr>
                                    
                                    <%i++; %>
                                    </s:iterator>
                                    <input type="hidden" id="tempcount" value="<%=i%>" />
                                    
                                    <s:hidden name="selectedid" ></s:hidden>
                                    
                                    
                                </tbody>
                            </table>
						   </div>
									
                            <div class="col-lg-12 col-md-12 col-xs-12" style="border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;">
                            <div class="col-lg-6 col-md-6 col-xs-6" style="padding-left: 0px;">
                            	<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 9px;">
                            	<textarea class="form-control" rows="3" placeholder="write note" name="notes" style="height: 60px !important;margin-top: 15px;"></textarea>
                            </div>
                             </div>
                            <div class="col-lg-6 col-md-6 col-xs-6 text-right">
                            	<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 3px;">
                            	<h4 style="font-size: 15px;">Sub Total : Rs.<label id="subtotal"><s:property value="subtotal"/></label></h4>
                            	<h4 style="color: #868686;font-size: 15px;">Discount <select id="distype" onchange="perorrs()" class="form-control" style="width: 10%;"><option value="0">%</option><option value="1">Rs.</option></select> : <input type="text" id="discsmt"  onchange="calDiscount(this.value)" class="form-control" value="<s:property value="discount"/>" style="width: 17%;"><br><small>Rs.<label id="tdisc"><s:property value="discount"/></label></small></h4>
                            	<h4 class="hidden" style="color: #868686;font-size: 15px;">Refund: Rs.00.00</h4>
                            	<h4 style="font-size: 15px;">Vat : Rs.<label id="vat"><s:property value="vat"/></label></h4> <s:hidden id="tvat" name="vat"/> <s:hidden id="tdiscount" name="discount" />
                            	<h4 style="font-weight: bold;font-size: 15px;color:green;">Total : Rs.<label id="total"><s:property value="total"/></label> <s:hidden name="total" id="ttotal"/> </h4> 
                            	
                            </div>
                            </div>
							</div>
                            
                            </form>
                            <div class="col-lg-12 col-md-12 col-xs-12 text-right" style="padding-top:10px;">
                            	<a type="button" class="btn btn-primary"  title="Online" >Online</a>
                            	<a type="button" class="btn btn-primary"  title="Credit" onclick="reorderbill('Credit')">Credit</a>
                            	<a type="button" class="btn btn-primary"  title="Estimate" onclick="">Estimate</a>
	                            <a type="button" class="btn btn-primary"  title="Cash Payment" onclick="reorderbill('Cash')">Cash</a>
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

</body>
</html>