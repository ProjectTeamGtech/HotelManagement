<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.cash{
	padding-bottom: 15px;
    background-color: indianred;
    padding-top: 15px;
    color: #fff;
}
.backwhite{
	    background-color: #fff;
}
.btn-lg {
    height: 35px !important;
}
.topbg{
	padding-left: 0px;
    padding-right: 0px;
    background-color: #efefef;
    padding: 20px 0px 20px 0px;
}
.topbg1{
    background-color: #efefef;
    padding-top: 20px;
    padding-bottom: 20px;
}
.checkmainback{
	background-color: #5bbaa7;
    padding-top: 45px;
    padding-bottom: 45px;
}
.formborbot{
	border: 0px !important;
    border-bottom: 2px solid #dddddd !important;
    background-color: transparent !important;
}
hr {
    border-top: 2px solid #ddd !important;
    width:96% !important;
}
.enterchamt{
	background-color: #fff !important;
    border: none !important;
    height: 38px !important;
}
.rupsymbol{
	background-color: #fff;
    border: none;
    font-size: 18px;
}
.borderboset{
	    margin-top: 20px;
    border-bottom: 1px solid #ddd;
    padding-bottom: 15px;
}
.thfont{
	    font-size: 18px;
    font-weight: bold !important;
}

.setbofocash{
	    border: 2px solid #e0e0e0;
}
.appWrapper.aside-fixed #sidebar {
    min-height: 666px !important;
}
.marbot0set{
	    margin-bottom: 0px;
}
.borrights{
	border-right: 1px solid #efefef;
    min-height: 450px;
}
</style>
</head>
<body>    
                    
                    <div class=" col-lg-12 col-md-12 col-sm-12 topback2">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         	<div class="form-group marbot0set">
							    <label for="exampleInputEmail1">Bank Deposit</label><br>
							   	<select class="form-control">
								  <option>Cash</option>
								  <option>Cheque/DD</option>
								</select>
							</div>
                         </div>
                         <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10"></div>
                        </div>
                        
                        
                        <div class=" col-lg-12 col-md-12 col-sm-12 hidden borderboset">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         	<img src="accounts/images/cashdeposit.png" class="img-responsive" style="width: 80%;"></img>
                         </div>
                         <div class="col-lg-10 col-sm-10 col-md-10 col-xs-10" style="padding-left:0px;padding-right:0px;">
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Date</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Bank</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Deposited by</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Amount</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="margin-top: 22px;">
                         		<div class="form-group">
								   <a href="" class="btn btn-primary">Save</a>
								  </div>
                         	</div>
                         	
                         </div>
                         <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 hidden" style="padding-left:0px;padding-right:0px;">
                         
                         	<table class="table table-bordered table Responsive" style="width: 40%;">
                         	<tbody>
                         		<tr>
                         			<th class="thfont" style="width: 35%;">Date</th>
                         			<td><input type="text" class="form-control setbofocash" id="exampleInputName2" placeholder="Select Date"></td>
                         		</tr>
                         		<tr>
                         			<th class="thfont">Bank</th>
                         			<td><input type="text" class="form-control setbofocash" id="exampleInputName2" placeholder="ABC Bank...."></td>
                         		</tr>
                         		<tr>
                         			<th class="thfont">Amount</th>
                         			<td><input type="text" class="form-control setbofocash" id="exampleInputName2" placeholder="Rs.XXXXX"></td>
                         		</tr>
                         		<tr>
                         			<th class="thfont">Deposited By</th>
                         			<td><input type="text" class="form-control setbofocash" id="exampleInputName2" placeholder="ABC XYZ"></td>
                         		</tr>
                         	</tbody>
							</table>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 15px;padding-left:0px;">
	                         	<a href="" class="btn btn-primary btn-lg">Save</a>
	                         	<a href="" class="btn btn-primary btn-lg">Cancel</a>
	                         </div>
                         </div>
                        </div>
                        
                        
                        
                        
                        
                        <div class=" col-lg-12 col-md-12 col-sm-12" style="margin-top: 20px;border-bottom: 1px solid #efefef;">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         	<img src="accounts/images/cheque.png" class="img-responsive" style="width: 80%;"></img>
                         </div>
                         
                         <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding-left:0px;padding-right:0px;">
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Date</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Cheque/DD No</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Cheque/DD Date</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Bank Name</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Bank (Deposited With)</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Deposited By</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Amount</label>
								    <input type="text" class="form-control" id="exampleInputEmail1">
								  </div>
                         	</div>
                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="margin-top: 22px;">
                         		<div class="form-group">
								  <a href="" class="btn btn-primary">Save</a>
								</div>
                         	</div>
                         
                         </div>
                         
                         
                         <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 hidden" style="padding-left:0px;padding-right:0px;">
                         
                         <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topbg">
	                         <div class="col-lg-6 col-md-6 col-sm-6">
	                         	<div class="form-inline">
									  <div class="form-group" style="width:100%;">
									    <label for="exampleInputName2">Bank Name :</label>
									    <input type="text" class="form-control formborbot" id="exampleInputName2" placeholder="Enter Bank Name" style=" width: 50%;">
									  </div>
									 
								</div>
	                         </div>
	                         <div class="col-lg-6 col-md-6 col-sm-6 text-right">
	                         	<div class="form-inline">
									  <div class="form-group">
									    <label for="exampleInputName2">Date :</label>
									    <input type="text" class="form-control formborbot" id="exampleInputName2" placeholder="Select Date">
									  </div>
								</div>
	                         </div>
                         	</div>
                         	<br>
                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 checkmainback">
	                         	<div class="form-inline">
									  <div class="form-group" style="width:100%;padding-bottom: 15px;">
									    <label for="exampleInputName2">Bank (Deposited with) :</label>
									    <input type="text" class="form-control formborbot" id="exampleInputName2" style="width:85%">
									  </div>
								</div>
								<hr>
								<div class="col-lg-9 col-md-9 col-sm-9" style="padding-left:0px;">
	                         	<hr>
		                         </div>
		                         <div class="col-lg-3 col-md-3 col-sm-3 pull-right" style="padding-right:0px;">
			                         <div class="input-group">
								      <div class="input-group-addon rupsymbol"><i class="fa fa-inr" aria-hidden="true"></i></div>
								      <input type="text" class="form-control enterchamt" id="exampleInputAmount" placeholder="Enter Amount" style="width: 91%;">
								    </div>
		                         	
		                         </div>
                         	</div>
                         	
                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topbg1">
                         	 	<div class="col-lg-6 col-md-6 col-sm-6" style="padding-left:0px;">
		                         	<div class="form-inline">
									  <div class="form-group">
									    <label for="exampleInputName2">Cheque Date :</label>
									    <input type="text" class="form-control formborbot" id="exampleInputName2">
									  </div>
									  <div class="form-group">
									    <label for="exampleInputName2">Cheque No: </label>
									    <input type="text" class="form-control formborbot" id="exampleInputName2">
									  </div>
								</div>
		                         </div>
		                          <div class="col-lg-6 col-md-6 col-sm-6 text-right" style="padding-right:0px;">
		                         	<div class="form-inline">
									  <div class="form-group">
									    <label for="exampleInputName2">Deposited by :</label>
									    <input type="text" class="form-control formborbot" id="exampleInputName2">
									  </div>
								</div>
		                         </div>
                         	</div>
                         	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 15px;padding-left:0px;">
	                         	<a href="" class="btn btn-primary btn-lg">Save</a>
	                         	<a href="" class="btn btn-primary btn-lg">Cancel</a>
	                         </div>
                         </div>
                        </div>
                        
                        
                        
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;">
                        	<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7 borrights" style="padding-left: 5px;padding-right: 5px;">
                        	<center><h3>Cheques Details</h3></center>
                        	<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <thead>
                                    <tr class="tableback">
                                    	<th>Date</th>
                                    	<th>Cheque/DD No.</th>
                                        <th>Cheque/DD Date</th>
                                        <th>Bank Name</th>
                                        <th>Bank (Deposited with)</th>
                                        <th>Deposited by</th>
                                        <th class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                     	<td>20/11/2016</td>
                                        <td>345874</td>
                                        <td>22/11/2016</td>
                                        <td>ICICI Bank</td>
                                        <td>SBI Bank</td>
                                        <td>Arun Getly</td>
                                        <td class="text-right">Rs. 2540</td>
                                    </tr>
                                     <tr>
                                       <td>22/11/2016</td>
                                        <td>345874</td>
                                        <td>24/11/2016</td>
                                        <td>ICICI Bank</td>
                                        <td>SBI Bank</td>
                                        <td>Arun Getly</td>
                                        <td class="text-right">Rs. 5040</td>
                                    </tr>
                                </tbody>
                            </table>
                        	</div>
                        	<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
                        		<div id="report" style="width:100%; height: 400px; margin: 0 auto"></div>
                        	</div>
                        </div>
                        
                        
                        
                        <script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
						<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>
                        <script>
                        	$(function () {
							    Highcharts.chart('report', {
							        chart: {
							            type: 'column'
							        },
							        title: {
							            text: 'Cheque Details'
							        },
							        subtitle: {
							            text: ''
							        },
							        xAxis: {
							            type: 'category',
							            labels: {
							                rotation: -45,
							                style: {
							                    fontSize: '13px',
							                    fontFamily: 'Verdana, sans-serif'
							                }
							            }
							        },
							        yAxis: {
							            min: 0,
							            title: {
							                text: 'Amount'
							            }
							        },
							        legend: {
							            enabled: false
							        },
							        tooltip: {
							            pointFormat: '<b>Rs.{point.y:.1f}</b>'
							        },
							        series: [{
							            name: 'Amount',
							            data: [
							                ['20/11/2016<br>ICICI Bank', 2540],
							                ['22/11/2016<br>ICICI Bank', 5040],
							            ],
							            dataLabels: {
							                enabled: true,
							                rotation: -90,
							                color: '#FFFFFF',
							                align: 'right',
							                format: '{point.y:.1f}', // one decimal
							                y: 10, // 10 pixels down from the top
							                style: {
							                    fontSize: '13px',
							                    fontFamily: 'Verdana, sans-serif'
							                }
							            }
							        }]
							    });
							});
                        </script>
                        
                        
</body>
</html>