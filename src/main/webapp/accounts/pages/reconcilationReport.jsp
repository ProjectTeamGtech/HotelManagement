<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
	.rightlineheight{
		border-right: 1px solid #efefef;
    	min-height: 550px;
	}
	.form-group {
    	margin-bottom: 0px;
	}
</style>
</head>
<body>
				<div class="">
                        <div class=" col-lg-12 col-md-12 col-sm-12 topback2">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         	<div class="form-group">
							    <label for="exampleInputEmail1">Date From</label><br>
							    <input type="text" class="form-control" value="25/11/2016">
							</div>
                         </div>
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         		<div class="form-group">
								    <label for="exampleInputEmail1">Date To</label><br>
								    <input type="text" class="form-control" value="25/11/2016">
								</div>
                         </div>
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         		
                         </div>
                         <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-right"></div>
                        </div>
                        
                        
                        
                        
                        <div class="col-lg-12 col-md-12 col-sm-12" style="padding-left: 0px;">
                        	<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 rightlineheight" style="padding-left: 0px;padding-right: 0px;">
                        		<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <thead>
                                    <tr class="tableback">
                                        <th style="width: 34%;">Reconcile Date</th>
                                        <th style="width: 26%;" class="text-right">Opening Bal.</th>
                                        <th style="width: 23%;" class="text-right">Amount Deposited</th>
                                        <th style="width: 15%;" class="text-right">Closing Bal.</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>01/10/2016 To 05/10/2016</td>
                                        <td class="text-right">Rs.500</td>
                                        <td class="text-right">Rs.12000</td>
                                        <td class="text-right">Rs.5000</td>
                                    </tr>
                                    <tr>
                                        <td>10/10/2016 To 15/10/2016</td>
                                        <td class="text-right">Rs.2000</td>
                                        <td class="text-right">Rs.18500</td>
                                        <td class="text-right">Rs.3000</td>
                                    </tr>
                                    <tr>
                                       <td>18/10/2016 To 20/10/2016</td>
                                        <td class="text-right">Rs.1500</td>
                                        <td class="text-right">Rs.30000</td>
                                        <td class="text-right">Rs.7000</td>
                                    </tr>
                                    <tr>
                                        <td>22/10/2016 To 25/10/2016</td>
                                        <td class="text-right">Rs.500</td>
                                        <td class="text-right">Rs.2000</td>
                                        <td class="text-right">Rs.2500</td>
                                    </tr>
                                     <tr>
                                        <td>28/11/2016 To 30/10/2016</td>
                                        <td class="text-right">Rs.800</td>
                                        <td class="text-right">Rs.15000</td>
                                        <td class="text-right">Rs.6000</td>
                                    </tr>
                                </tbody>
                            </table>
                        	</div>
                        	<div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                        		<div id="report" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
                        	</div>
                        </div>
                    </div>
                                        
                    
                    
                    
					<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
					<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>
                    
                    <script>
                    
                    	$(function () {
						    Highcharts.chart('report', {
						        chart: {
						            type: 'bar'
						        },
						        title: {
						            text: 'Reconciliation Reports'
						        },
						        subtitle: {
						            text: 'Graph View'
						        },
						        xAxis: {
						            categories: ['01/10/2016 To 05/10/2016', '10/10/2016 To 15/10/2016', '18/10/2016 To 20/10/2016', '22/10/2016 To 25/10/2016', '28/11/2016 To 30/10/2016'],
						            title: {
						                text: null
						            }
						        },
						        yAxis: {
						            min: 0,
						            title: {
						                text: 'Amount',
						                align: 'high'
						            },
						            labels: {
						                overflow: 'justify'
						            }
						        },
						        tooltip: {
						            valueSuffix: ' Rupees'
						        },
						        plotOptions: {
						            bar: {
						                dataLabels: {
						                    enabled: true
						                }
						            }
						        },
						        legend: {
						            layout: 'vertical',
						            align: 'right',
						            verticalAlign: 'top',
						            x: -40,
						            y: 80,
						            floating: true,
						            borderWidth: 1,
						            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
						            shadow: true
						        },
						        credits: {
						            enabled: false
						        },
						        series: [{
						            name: 'Opening Balance',
						            data: [500, 2000, 1500, 500, 800]
						        }, {
						            name: 'Amount Deposited',
						            data: [12000, 18500, 30000, 2000, 15000]
						        },{
						            name: 'Closing Balance',
						            data: [5000, 3000, 7000, 2500, 6000]
						        }
						        	
						        ]
						    });
						});
                    </script>
                    
</body>
</html>