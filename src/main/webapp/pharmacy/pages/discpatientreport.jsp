<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>  

<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">


<style>
	hr {
    margin-top: 8px;
    margin-bottom: 8px;
}
.imflag{
	display: inline-block;
    width: 88%;
}
.bg-lightred {
    background-color: #e05d6f !important;
}
</style>

</head>
<body>
						<div class="row details mainheader hidden">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Sale Report</h4>
								</div>
							</div>
							
							
							<div class="row ">
								
							</div>
							
							
							
							<div class="">
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4" style="border-right: 1px solid #ddd;">
										<div id="container" style="min-width: 310px; height: 300px; max-width: 600px; margin: 0 auto"></div>
										<div id="container1" style="min-width: 310px; max-width: 800px; height: 200px; margin: 0 auto"></div>
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="background-color: #f9f9f9;padding: 18px;">
											<div class="col-lg-6 col-md-6 col-xs-6">
											<h4 class="text-left"><b>Opening Balance :</b></h4>
											<h4 class="text-left"><b>Total Collection :</b></h4>
											<hr>
											<h4 class="text-left"><b>Closing Balance :</b></h4>
										</div>
										<div class="col-lg-6 col-md-6 col-xs-6">
											<h4 class="text-right"><b style="color:red;">Rs.1500.00</b></h4>
											<h4 class="text-right"><b style="color:green;">Rs.10500.00</b></h4>
											<hr>
											<h4 class="text-right"><b style="color:green;">Rs.12000.00</b></h4>
										</div>
										</div>
									</div>
									<div class="col-lg-8 col-xs-8 col-sm-8 col-md-8" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<a style="display: none;" href="#" onclick="opencPopup('getPatientRecordEmr')" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Create
										Prescription </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form class="form-inline search">
										  <div class="form-group" style="width: 20%;">
										   <input type="text" name="searchText" value="" class="form-control" style="width:100%;" placeholder="Search Patient / ID / Bill No">
										  </div>
										  
										    <input type="text" value="05-01-2017" class="form-control" placeholder="From Date" style="width:10%">
										    <input type="text" value="05-01-2017" class="form-control" placeholder="To Date" style="width:10%">
										    <select class="form-control">
											  <option>Select</option>
											  <option>IPD</option>
											  <option>OPD</option>
											</select>
										  
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										<form>
										</div>
										</div>
								</div>
										<table class="table table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr class="tableback">
                                		<th style="width:3%;"></th>
                                		<th>Date of Admission</th>
                                		<th>Date of Discharge</th>
                                        <th>Patient Name</th>
                                        <th>Sex/Age</th>
                                        <th>Location</th>
                                        <th>Contact</th>
                                        <th class="text-right">Total Amount</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                	  <tbody>
                                    <tr>
                                    	<td><img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag"></img></td>
                                    	<td>1/03/2017 | 12:14:03</td>
                                    	<td>10/03/2017 | 12:14:03</td>
                                        <td>Mrs. Khushi Gupta</td>
                                        <td>Female/20</td>
                                        <td>GENRAL/18</td>
                                        <td>9568245625</td>
                                        <td class="text-right">Rs.7254.00</td>
                                        <td><a href="#" data-toggle="modal" data-target="#history">history</a></td>
                                    </tr>
                                    
                                    <tr>
                                    	<td><img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag"></img></td>
                                    	<td>1/03/2017 | 12:14:03</td>
                                    	<td>10/03/2017 | 12:14:03</td>
                                        <td>Mrs. Arun Varma</td>
                                        <td>Female/25</td>
                                        <td>GENRAL/18</td>
                                        <td>9568245625</td>
                                        <td class="text-right">Rs.1250.00</td>
                                        <td><a href="#" data-toggle="modal" data-target="#history">history</a></td>
                                    </tr>
                                </tbody>
                            </table>
									</div>
								</div>
							</div>
							
							
							<!-- Patient History Modal -->
							<div id="history" class="modal fade" role="dialog">
							  <div class="modal-dialog">
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title">Mrs. Khushi Gupta | Female/20 | GENRAL/18 | 9568245625</h4>
							      </div>
							      <div class="modal-body">
							        <div id="page_printer" class="minhesigh">
							        <h4 class="modal-title visible-print hidden-md hidden-lg hidden-sm">Mrs. Khushi Gupta | Female/20 | GENRAL/18 | 9568245625</h4>
							        	<b style="color: #e69e18;font-weight: normal;">Prescription Date: 01-01-2017</b>
							        		<div class="">
					                            <table class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                       
					                                        <th style="width: 38%;">Name of Drug</th>
					                                        <th style="width: 4%;">Pkg</th>
					                                        <th style="width: 4%;">Mfg</th>
					                                        <th style="width: 6%;">Batch No</th>
					                                        <th style="width: 8%;">Exp. Dt</th>
					                                        <th style="width: 4%;">Qty</th>	 
					                                        <th style="width: 6%;" class="text-right">Amount</th>
					                                    </tr>
					                                </thead>
					                                
					                                	  <tbody>
					                                	  
					                                    <tr>
					                                        
					                                        <td>CARDACE TAB (2.5 MG)</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					                                   <tr>
					                                        <td>TAMFLOW S</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					                                    <tr>
					                                        <td>TAMFLOW S</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					
					                                </tbody>
					                                
					                                	 
					                                   
					                                
					                              
					                            </table>
					                            <div class="" style="border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;">
					                           
					                            <div class="text-right">
					                            	<div class="" style="">
					                            	<h4 style="font-size: 13px;">Sub Total : Rs.482.00</h4>
					                            	<h4 style="color: #868686;font-size: 13px;">Discount(%) : Rs.30.00</h4>
					                            	<h4 style="font-size: 13px;">Vat : Rs.100.00</h4>
					                            	<h4 style="font-weight: bold;font-size: 13px;color:green;">Total : Rs.582.00</h4>
					                            </div>
					                            </div>
					                            
											</div>
					                        </div>
							        	
							        	<b style="color: #e69e18;font-weight: normal;">Prescription Date: 04-01-2017</b>
							        	<div class="">
					                            <table class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                        
					                                        <th style="width: 38%;">Name of Drug</th>
					                                        <th style="width: 4%;">Pkg</th>
					                                        <th style="width: 4%;">Mfg</th>
					                                        <th style="width: 6%;">Batch No</th>
					                                        <th style="width: 8%;">Exp. Dt</th>
					                                        <th style="width: 4%;">Qty</th>	  
					                                        <th style="width: 6%;" class="text-right">Amount</th>
					                                    </tr>
					                                </thead>
					                                
					                                	  <tbody>
					                                	  
					                                    <tr>
					                                        
					                                        <td>CARDACE TAB (2.5 MG)</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					                                   <tr>
					                                        <td>TAMFLOW S</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					                                    <tr>
					                                        <td>TAMFLOW S</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					
					                                </tbody>
					                                
					                                	 
					                                   
					                                
					                              
					                            </table>
					                            <div class="" style="border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;">
					                           
					                            <div class="text-right">
					                            	<div class="" style="">
					                            	<h4 style="font-size: 13px;">Sub Total : Rs.482.00</h4>
					                            	<h4 style="color: #868686;font-size: 13px;">Discount(%) : Rs.30.00</h4>
					                            	<h4 style="font-size: 13px;">Vat : Rs.100.00</h4>
					                            	<h4 style="font-weight: bold;font-size: 13px;color:green;">Total : Rs.582.00</h4>
					                            </div>
					                            </div>
					                            
											</div>
					                        </div>
							        </div>
							      </div>
							      <div class="modal-footer">
							      	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							        <button type="button" class="btn btn-primary" onclick="printDiv('page_printer');">Print</button>
							      </div>
							    </div>
							
							  </div>
							</div>
							
							
							
							<script src="dtr/assets/js/vendor/hichart/highcharts.js"></script>
							<script src="dtr/assets/js/vendor/hichart/exporting.js"></script>
							
							
							<script>
								$(function () {

								    $(document).ready(function () {
								
								        // Build the chart
								        Highcharts.chart('container', {
								            chart: {
								                plotBackgroundColor: null,
								                plotBorderWidth: null,
								                plotShadow: false,
								                type: 'pie'
								            },
								            title: {
								                text: 'Discharge Patients Report'
								            },
								            tooltip: {
								                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
								            },
								            plotOptions: {
								                pie: {
								                    allowPointSelect: true,
								                    cursor: 'pointer',
								                    dataLabels: {
								                        enabled: false
								                    },
								                    showInLegend: true
								                }
								            },
								            series: [{
								                name: 'Discharge',
								                colorByPoint: true,
								                data: [{
								                    name: 'OPD Patient',
								                    y: 10.33
								                }, {
								                    name: 'IPD Patient',
								                    y: 56.03
								                }]
								            }]
								        });
								    });
								});
							</script>
							
							
							
				<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '450px'
								    });
								});
							</script>
							
<script>
    function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
    //document.body.innerHTML = oldPage;

    //Print Page
    setTimeout(function () {
        print_page();
    }, 2000);

    function print_page() {
        window.print();
    }

    //Restore orignal HTML
    setTimeout(function () {
        restore_page();
    }, 3000);

    function restore_page() {
        document.body.innerHTML = oldPage;
    }
}
	</script>
</body>

</html>