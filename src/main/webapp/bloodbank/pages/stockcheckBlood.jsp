<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
	.rightset {
    padding: 0px;
    padding-bottom: 10px;
    border-right: 1px solid #ddd;
    min-height: 440px;
}
.panamse {
    padding: 0px;
    padding-top: 10px;
    background-color: #f7f7f7;
    margin-top: 0px;
}
</style>

</head>
<body>

<div class="col-lg-12 col-md-12 col-sm-12 panamse" style="padding:0px;padding-top: 8px;">
									<div class="col-lg-1 col-md-1 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">B.NO</label>
										    <p>-</p>
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label>
   	 										<input type="text" name="fullname" value="Mr. Satish Vanajari" id="fullname" class="form-control">
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">MOBILE NO</label>
    										<input type="text" name="mobno" value="07276810817" id="mobno" class="form-control">
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">LOCATION</label>
    											<input type="text" name="wardname" value="21/Genral" id="wardname" class="form-control">
									  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DR. NAME</label>
    										<input type="text" name="practitionerName" value="Dr. Girish Wankhede" id="practitionerName" class="form-control">
								  		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DATE</label>
    										<input type="text" name="dateTime" value="22/05/2017" id="dateTime" class="form-control">
								  		</div>
									</div>
							</div>
							<div class="col-lg-9 col-sm-9 col-md-9 col-xs-9 rightset">
									
									<input type="hidden" name="billno" value="58" id="billno">
									<input type="hidden" name="paymode" value="" id="paymode">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: #dff0d8;padding: 7px 3px 7px 11px;">
								         <div class="form-inline">
								          <div class="form-group">
								           <p style="font-size: 13px;margin: 0px 0px 2px;">Blood Bag :</p> 
								          </div>
								          <div class="form-group">
								                <select class="form-control" name="blood_group">
		                                        <option value="O+">O+ </option>
		                                        <option value="O-">O- </option>
		                                        <option value="A+">A+ </option>
		                                        <option value="A-">A- </option>
		                                        <option value="B+">B+ </option>
		                                        <option value="B-">B- </option>
		                                        <option value="AB+">AB+ </option>
		                                        <option value="AB-">AB- </option>
		                                    </select>
								          </div>
								          <div class="form-group">
											<select name="mdicinename" id="newmedicine" class="form-control showToolTip chosen-search">
											    <option value="0">Select Component</option>
											    <option value="PRC">PRC</option>
											    <option value="FPP">FPP</option>
											    <option value="PC">PC</option>
											    <option value="SDP">SDP</option>
											    <option value="CRYO">CRYO</option>
											    <option value="WB">WB</option>
											</select>
								          </div>
								            
								          <a href="#" onclick="addnewRow('mytable')" title="Add New" style="padding-top: 1px;margin-left: 3px;position: absolute;"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a>
								         
								         </div>
         								</div>
									
									
									<div>
										<table id="mytable" class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="tableback">  
                                        <th style="width: 8%;">Blood Group</th>
                                        <th style="width: 6%;">Component</th>
                                        <th style="width: 6%;">Req. Qty</th>
                                        <th style="width: 5%;">Avail</th>
                                        <th style="width: 6%;">Serial No</th>
                                        <th style="width: 5%;">Expiry</th>
                                        <th style="width: 3%;">Sale</th>
                                        <th style="width: 8%;" class="text-right">Unit Price</th>
                                        <th style="width: 8%;" class="text-right">Total</th>
                                        <th style="width: 3%;"></th>
                                    </tr>
                                </thead>
                                	<tbody>
                                    <tr>
                 						<td style="border-bottom: none !important;">O+ </td>
                                        <td style="border-bottom: none !important;">PRC</td> 
                                        <td style="border-bottom: none !important;">3 BTL</td>
                                        <td style="border-bottom: none !important;">6 BTL</td>
                                        <td style="border-bottom: none !important;">DSD562</td>
                                        <td style="border-bottom: none !important;">Nov-2017</td>
                                        <td style="border-bottom: none !important;"> <input type="text" name="medicines[0].returnqty" class="form-control" id="returnqty0" onchange="calRefundTotaltemp()" style="height: 20px !important;" value="3"> <input type="hidden" value="10" name="medicines[0].saleqty" id="sale0" class="form-control"><input type="hidden" id="tempsale0" value="10"></td>
                                        <td style="border-bottom: none !important;" class="text-right">Rs.120.00</td><input type="hidden" value="12.00" id="mrp0" name="medicines[0].mrp"> 
                                        <td style="border-bottom: none !important;" class="text-right">Rs.<label id="totalmrp0">360.00</label></td>
                                        <td style="border-bottom: none !important;" class="text-center"><a href="#" onclick="deleteRowtemp(this)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td>
                                    </tr>
                                    <tr>
                 						<td style="border-bottom: none !important;">O+ </td>
                                        <td style="border-bottom: none !important;">FPP</td> 
                                        <td style="border-bottom: none !important;">3 BTL</td>
                                        <td style="border-bottom: none !important;">6 BTL</td>
                                        <td style="border-bottom: none !important;">DSD562</td>
                                        <td style="border-bottom: none !important;">Nov-2017</td>
                                        <td style="border-bottom: none !important;"> <input type="text" name="medicines[0].returnqty" class="form-control" id="returnqty0" onchange="calRefundTotaltemp()" style="height: 20px !important;" value="3"> <input type="hidden" value="10" name="medicines[0].saleqty" id="sale0" class="form-control"><input type="hidden" id="tempsale0" value="10"></td>
                                        <td style="border-bottom: none !important;" class="text-right">Rs.120.00</td><input type="hidden" value="12.00" id="mrp0" name="medicines[0].mrp"> 
                                        <td style="border-bottom: none !important;" class="text-right">Rs.<label id="totalmrp0">360.00</label></td>
                                        <td style="border-bottom: none !important;" class="text-center"><a href="#" onclick="deleteRowtemp(this)"><i class="fa fa-minus-circle" style="color:#c50404;font-size: 17px;padding-top: 2px;" aria-hidden="true"></i></a></td>
                                    </tr>
                                </tbody>
                            </table>
						   </div>
									
                            
                            
                            
                            
						  </div>
						  <div class="col-lg-3 col-sm-3 col-md-3 col-xs-3">
										<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;border-bottom: 1px solid #ddd;" id="baldiv">
											<h3 style="color:#d9534f;">Balance : <span style="float: right;">Rs.<span id="rebalance">00</span> <input type="hidden" name="balance" id="prebalance"> </span></h3>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding-right: 3px;text-align: right;padding:0px;">
			                            	<h4 style="font-size: 15px;"><a href="#" title="clear Balance" onclick="clearpatientbaldb()" class="btn btn-success btn-sm pull-left">clear balance</a>Sub Total : Rs.<span id="subtotal">720</span></h4>
			                            	<h4 style="color: #868686;font-size: 15px;">Discount <select id="distype" onchange="perorrs()" class="form-control" style="width: 17%;"><option value="1">Rs.</option><option value="0">%</option></select> : <input type="text" id="discsmt" onchange="calDiscount(this.value)" class="form-control" value="00" style="width: 17%;"><br><small>Rs.<label id="tdisc">00</label></small></h4>
			                            	<h4 class="hidden" style="color: #868686;font-size: 15px;">Refund: Rs.<span id="refund">00.00</span> <input type="hidden" name="refundamt" value="0" id="trefund"> </h4>
			                            	<h4 class="hidden" style="font-size: 15px;">Vat : Rs.<label id="vat">3.58</label></h4> <input type="hidden" name="vat" value="3.58" id="tvat"> <input type="hidden" name="discount" value="50.0" id="tdiscount">
			                            	<h4 style="font-weight: bold;font-size: 15px;color:green;">Net Total : Rs.<span id="total">720.00</span> <input type="hidden" name="total" value="720.00" id="ttotal"> </h4>
			                            	<h4 class="hidden"><small>Total with balance: Rs.<span id="baltotal"></span></small></h4> 
			                            </div>
										<div class="col-lg-12 col-md-12 col-xs-12 text-right" style="padding-top:10px;padding:0px;">
                            	<!--<a type="button" class="btn btn-primary"  title="Online" >Online</a>
                            	--><a type="button" class="btn btn-primary" title="Credit" onclick="credit()">Credit & Print</a>
                            	<a type="button" class="btn btn-warning" title="Credit" onclick="openPopup('bloodinvoiceBloodbank')">Save & Print</a>
	                            <a type="button" class="btn btn-primary hidden" title="Cash Payment" onclick="refundMedicine('Cash')">Return</a>
                            </div>
                            
									</div>
							

</body>
</html>