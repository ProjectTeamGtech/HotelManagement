<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="reconciliation/sweetalert/sweetalert.css" rel="stylesheet" />
<style>
	.balanceamt{
		margin-top: 5px;
	    color: green;
	    font-weight: bold;
	}
	.cionset{
		width: 24%;
	    margin-left: auto;
	    margin-right: auto;
	}
	.backcolorf{
		background-color: #ececec;
		padding: 10px 0px 0px 0px;
		border: 2px solid #555;
	}
	.backcolorf1{
		background-color: #687592;
		padding: 10px 0px 0px 0px;
		border: 2px solid #555;
		color: #fff;
	}
	.totalall{
		font-size: 17px;
	    font-weight: bold !important;
	    color: #000;
	    border-top: 4px double #555;
	}
	.heightset{
		min-height: 129px;
	}
	.tablestbor{
		background-color: #fff;
	    border: 11px solid #fff;
	}
	.topback2 {
	    background-color: #f5f5f5;
	    padding: 10px 0px 0px 0px;
	}
	.bomincoulab{
		font-size: 16px;
	}
	.balanceamtred{
		margin-top: 5px;
	    color: red;
	    font-weight: bold;
	}
	.rightte{
		font-size: 16px;
    	margin-top: 0px;
	}
	.btnsetre{
		height: 48px !important;
	    width: 100%;
	    font-size: 20px;
	}
	ul {
	    list-style: none;
	    padding: 0;
	    margin: 0;
	}
	li {
	    display: list-item;
	    text-align: -webkit-match-parent;
	}
	h3, .h3 {
    	font-size: 19px;
	}
	.fontseb{
	   
	}

</style>
</head>
<body>
 <!--Account Reconciliation Code Here -->
                    <div class="">
                        <div class=" col-lg-12 col-md-12 col-sm-12 topback2">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         	<div class="form-group">
							    <label for="exampleInputEmail1">Last Reconciliation Date</label><br>
							    <span> 15/11/2016</span>
							</div>
							
                         </div>
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         		<div class="form-group">
							    <label for="exampleInputEmail1">Reconcile upto</label><br>
							    <input type="text" class="form-control" value="25/11/2016">
							</div>
                         </div>
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2"></div>
                         <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-right">
                         <div class="form-horizontal">
						  <div class="form-group">
						    <label for="inputEmail3" class="col-sm-10 control-label bomincoulab">Opening Balance :</label>
						    <div class="col-sm-2">
						      <h3 class="balanceamt"> Rs.500</h3>
						    </div>
						    <div class="col-xs-12 col-sm-12">
						    	<small>(Amount will be closing balance of previous day. It is the actual cash in hand)</small>
						    </div>
						  </div>
						</div>
                         </div>
                         
                        </div>
                    </div>
                    
                    
                    
                    <div class="" style="background-color: #f5f5f5;">
                        <div class=" col-lg-12 col-md-12 col-sm-12" style="padding-bottom: 15px;    margin-top: 15px;">
                         <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="padding-left:0px;">
                         <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolorf">
	                         <div class="heightset">
	                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                         		<img src="reconciliation/payrecived.png" class="img-responsive cionset"></img>
	                         		<h3 class="text-center">Payment Received</h3>
	                         	</div>
	                         </div>
	                         <div class="">
	                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-right:0px;padding-left:0px;">
	                         		<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <thead>
                                    <tr class="tableback">
                                        <th>Payment Mode</th>
                                        <th class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Cash</td>
                                        <td class="text-right">Rs.5000</td>
                                    </tr>
                                    <tr>
                                        <td>Cheque</td>
                                        <td class="text-right">Rs.5000</td>
                                    </tr>
                                    <tr>
                                        <td>Debit Card/ Credit Card</td>
                                        <td class="text-right">Rs.10000</td>
                                    </tr>
                                    <tr>
                                        <td>Demand Draft/ Pay Order</td>
                                        <td class="text-right">Rs.3000</td>
                                    </tr>
                                     <tr>
                                        <td>Other</td>
                                        <td class="text-right">Rs.00</td>
                                    </tr>
                                    <tr class="totalall">
                                        <td class="fontseb">Payments Received</td>
                                        <td class="text-right fontseb">Rs.23000</td>
                                    </tr>
                                </tbody>
                            </table>
	                         	</div>
	                         </div>
                         </div> 
                         </div>
                         
                         
                         <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="padding-right:0px;padding-left:0px;">
                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolorf">
	                         <div class="heightset">
	                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                         		<img src="reconciliation/expensis.png" class="img-responsive cionset"></img>
	                         		<h3 class="text-center">Expenses Incurred</h3>
	                         	</div>
	                         </div>
	                         <div class="">
	                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-right:0px;padding-left:0px;">
	                         		<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <thead>
                                    <tr class="tableback">
                                        <th>Expense Type</th>
                                        <th class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Taxi</td>
                                        <td class="text-right">Rs.200</td>
                                    </tr>
                                    <tr>
                                        <td>Bank Charges</td>
                                        <td class="text-right">Rs.800</td>
                                    </tr>
                                    <tr>
                                        <td>Advance Refund</td>
                                        <td class="text-right">Rs.2000</td>
                                    </tr>
                                    <tr>
                                        <td>Dinner</td>
                                        <td class="text-right">Rs.1000</td>
                                    </tr>
                                     <tr>
                                        <td>Other</td>
                                        <td class="text-right">Rs.00</td>
                                    </tr>
                                    <tr class="totalall">
                                        <td class="fontseb">Total Expenses</td>
                                        <td class="text-right fontseb">Rs.4000</td>
                                    </tr>
                                </tbody>
                            </table>
	                         	</div>
	                         </div>
                         </div> 
                         </div>
                         
                         <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="padding-right:0px;">
                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolorf">
	                         <div class="heightset">
	                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                         		<img src="reconciliation/bankdeposite.png" class="img-responsive cionset"></img>
	                         		<h3 class="text-center">Bank Deposit</h3>
	                         	</div>
	                         </div>
	                         <div class="">
	                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-right:0px;padding-left:0px;">
	                         		<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <thead>
                                    <tr class="tableback">
                                        <th>Deposit Mode</th>
                                        <th class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Cash</td>
                                        <td class="text-right">Rs.500</td>
                                    </tr>
                                    <tr>
                                        <td>Cheque</td>
                                        <td class="text-right">Rs.5000</td>
                                    </tr>
                                    <tr>
                                        <td>Debit Card/ Credit Card</td>
                                        <td class="text-right">Rs.10000</td>
                                    </tr>
                                    <tr>
                                        <td>Demand Draft/ Pay Order</td>
                                        <td class="text-right">Rs.500</td>
                                    </tr>
                                     <tr>
                                        <td>Other</td>
                                        <td class="text-right">Rs.00</td>
                                    </tr>
                                    <tr class="totalall">
                                        <td class="fontseb">Bank Deposit</td>
                                        <td class="text-right fontseb">Rs.16000</td>
                                    </tr>
                                </tbody>
                            </table>
	                         	</div>
	                         </div>
                         </div> 
                         </div>
                         
                         
                         
                         <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="padding-right:0px;">
                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolorf1">
	                         <div class="heightset">
	                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                         		<img src="reconciliation/reconcialiaton.png" class="img-responsive cionset"></img>
	                         		<h3 class="text-center">Reconciliation From <br><small style="color:#fff;">16/11/2016 to 24/11/2016</small></h3>
	                         	</div>
	                         </div>
	                         <div class="">
	                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-right:0px;padding-left:0px;">
	                         		<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <thead>
                                    <tr class="tableback">
                                        <th>Deposit Mode</th>
                                        <th class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Cash</td>
                                        <td class="text-right">Rs.5000</td>
                                    </tr>
                                    <tr>
                                        <td>Cheque</td>
                                        <td class="text-right">Rs.5000</td>
                                    </tr>
                                    <tr>
                                        <td>Debit Card/ Credit Card</td>
                                        <td class="text-right">Rs.10000</td>
                                    </tr>
                                    <tr>
                                        <td>Demand Draft/ Pay Order</td>
                                        <td class="text-right">Rs.3000</td>
                                    </tr>
                                     <tr>
                                        <td>Other</td>
                                        <td class="text-right">Rs.00</td>
                                    </tr>
                                    <tr class="totalall">
                                        <td class="fontseb">Bank Deposit</td>
                                        <td class="text-right fontseb">Rs.23000</td>
                                    </tr>
                                </tbody>
                            </table>
	                         	</div>
	                         </div>
                         </div> 
                         </div>
                        </div>
                    </div>
                
                    
                    
                    
                    
                    <div class="">
                        <div class=" col-lg-12 col-md-12 col-sm-12" style="border-top: 1px solid #dedede;">
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8"></div>
                         <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="padding: 0px 0px 0px 0px;">
                         		<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td><label for="exampleInputEmail1" class="bomincoulab">Opening Balance</label></td>
                                        <td class="text-right"><label class="balanceamt rightte"> Rs.500 </label></td>
                                    </tr>
                                    <tr>
                                       	<td><label for="exampleInputEmail1" class="bomincoulab">Payments Received</label></td>
                                        <td class="text-right"><label class="balanceamt rightte"><i class="fa fa-plus-circle" aria-hidden="true"></i> Rs.23000 </label></td>
                                    </tr>
                                    <tr>
                                    	<td><label for="exampleInputEmail1" class="bomincoulab">Bank Deposited</label></td>
                                        <td class="text-right"><label class="balanceamtred rightte"><i class="fa fa-minus-circle" aria-hidden="true"></i> Rs.16000 </label></td>
                                    </tr>
                                    <tr>
                                    	<td><label for="exampleInputEmail1" class="bomincoulab">Total Expenses</label></td>
                                        <td class="text-right"><label class="balanceamtred rightte"><i class="fa fa-minus-circle" aria-hidden="true"></i> Rs.4000 </label></td>
                                    </tr>
                                     <tr>
                                       	<td><label for="exampleInputEmail1" class="bomincoulab">(Hospital Name) A/c Credited</label></td>
                                        <td class="text-right"><label class="balanceamt rightte"><i class="fa fa-plus-circle" aria-hidden="true"></i> Rs.2000 </label></td>
                                    </tr>
                                    <tr>
                                    	<td><label for="exampleInputEmail1" class="bomincoulab">(Hospital Name) A/c Debited</label></td>
                                        <td class="text-right"><label class="balanceamtred rightte"><i class="fa fa-minus-circle" aria-hidden="true"></i> Rs.1000 </label></td>
                                    </tr>
                                    
                                    <tr style="border-top: 4px solid #555;">
                                    	<td><label for="exampleInputEmail1" class="bomincoulab">Closing Balance</label></td>
                                        <td class="text-right"><label class="balanceamt rightte"> Rs.4500 </label></td>
                                    </tr>
                                </tbody>
                            </table>
                            
                            <ul class="examples">
                                   <li class="success">
                                      <div class="ui">
                                          <button type="submit" class="btn btn-primary btnsetre">Reconcile</button>
                                      </div>
                                   </li>
                                </ul>
                            
                         </div>
                        </div>
                    </div>
                    
                    
                    
                    
                    <script src="reconciliation/sweetalert/sweetalert-dev.js"></script>
                     <script>
				        document.querySelector('ul.examples li.success button').onclick = function () {
				           swal({
								  title: "Are you sure?",
								  text: "Please Check Bank Statement & Then Reconcile :)",
								  type: "warning",
								  showCancelButton: true,
								  confirmButtonColor: "#DD6B55",
								  confirmButtonText: "Yes",
								  cancelButtonText: "No",
								  closeOnConfirm: false,
								  closeOnCancel: false
								},
								function(isConfirm){
								  if (isConfirm) {
								    swal("Sucessfully Reconcilied!", "", "success");
								  } else {
								    swal("Cancelled", "", "error");
								  }
								});
				        };
				    </script>
</body>
</html>