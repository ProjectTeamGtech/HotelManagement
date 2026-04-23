<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


</head>
<body>
<div class="row">
								<div class="col-lg-12 col-md-12 col-xs-12">
									
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form class="form-inline search" action="reportpriscPharmacy" method="post">
										  <div class="form-group">
										   <select class="form-control" name="report">
											  <option value="0">Select Supplier</option>
											  <option value="1">(Supplier 1)</option>
											  <option value="2">(Supplier 1)</option>
											</select>
										  </div>
										    <input type="text" name="fromdate" class="form-control" style="width:10%" placeholder="From Date">
										    <input type="text" name="todate" class="form-control" style="width:10%" placeholder="To Date">
											<select class="form-control" name="report">
											  <option value="0">30 Days</option>
											  <option value="1">60 Days</option>
											  <option value="2">90 Days</option>
											</select>
											<select class="form-control" name="report">
											  <option value="0">Use</option>
											  <option value="1">Dead Stock</option>
											</select>
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										</form></div>
										</div>
								</div>
										<table class="table table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr class="tableback">
                                		<th style="width:3%;"></th>
                                        <th>Expiry Date</th>
                                        <th>Days</th>
                                        <th>Product Name</th>
                                        <th>Qty</th>
                                        <th class="text-right">Total Cost</th>
                                        <th>Stock Status</th>
                                        <th>Supplier Name</th>
                                        <th>Contact No</th>
                                       
                                    </tr>
                                </thead>
                                	  <tbody>
	                                     <tr>
		                                     <td>1</td>
		                                     <td style="color:red;">02-04-2017</td>
		                                     <td>15 Days</td>
		                                     <td>paricitamol 500 (paricitamol)</td>
		                                     <td>60</td>
		                                     <td class="text-right">Rs.2500</td>
		                                     <td>Use</td>
		                                     <td>Kamal Pharma</td>
		                                     <td>8465456215</td>
		                                     <td><a href="#">return</a></td>
	                                    </tr>
	                                    <tr>
		                                     <td>2</td>
		                                     <td style="color:red;">30-03-2017</td>
		                                     <td>30 Days</td>
		                                     <td>Saridon 500 (Saridon)</td>
		                                     <td>20</td>
		                                     <td class="text-right">Rs.2500</td>
		                                     <td style="color: chocolate;">Dead Stock</td>
		                                     <td>Kamal Pharma</td>
		                                     <td>8465456215</td>
		                                     <td><a href="#">return</a></td>
	                                    </tr>
                                    </tbody>
                            </table>
									
								</div>
							</div>
							
							<script src="dtr/assets/js/vendor/hichart/highcharts.js"></script>
							<script src="dtr/assets/js/vendor/hichart/exporting.js"></script>
							
							
							<script>
								
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
            text: 'Expiry Product Report'
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
            name: 'Product',
            colorByPoint: true,
            data: [{
                name: 'Product Use',
                y: 56.33
            }, {
                name: 'Dead Stcok',
                y: 24.03,
                sliced: true,
                selected: true
            }]
        }]
    });
});
							
							</script>
							


</body>
</html>