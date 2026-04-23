<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.Inventory.web.form.ProductForm"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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
	
	.bomincoulab{
		font-size: 14px;
		margin-top: 0px;
	}
	.balanceamtred{
		margin-top: 5px;
	    color: red;
	    font-weight: bold;
	}
	.rightte{
		font-size: 14px;
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
	.backred{
		    background-color: #e05d6f;
    		color: #fff;
	}

</style>

<SCRIPT type="text/javascript">

	 $(document).ready(function(){
			   
			   $("#fromdate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
				$("#todate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
			   
      });


</SCRIPT>


</head>
<body>
		<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form action="profitlossProduct" method="post" class="form-inline search">
										  <div class="form-group">
										  	<s:textfield name="fromdate" id="fromdate" cssClass="form-control" placeholder="Select From Date" ></s:textfield>
										  </div>
										   <div class="form-group">
										  	<s:textfield name="todate" id="todate" cssClass="form-control" placeholder="Select To Date" ></s:textfield>
										  </div>
										   <div class="form-group hidden">
										  	<select class="form-control choosen">
											  <option>Report</option>
											  <option>Daily</option>
											  <option>Weekly</option>
											  <option>Monthly</option>
											</select>
										  </div>
										  <div class="form-group">
										  	<button type="submit" class="btn btn-primary">Go</button>
										  </div>
										  <div class="form-group">
										  	  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  </div>
										<form>
										</div>
										</div>
								</div>
								
								<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="margin-top: 15px;">
									<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding-left:0px;border-right: 1px solid #ddd;min-height: 500px;">
										<table class="table table-bordered" cellspacing="0" width="100%">
			                                <thead>
			                                <tr class="tableback">
			                                        <th style="font-size: 16px;">Particulars</th>
			                                        <th class="text-right" style="font-size: 16px;">Debit Amount</th>
			                                    </tr>
			                                </thead>
			                                	  <tbody>
			                                	  
                                    <tr>
                                        <td><label for="exampleInputEmail1" class="bomincoulab">Opening Stock</label></td>
                                        <td class="text-right"><label class="rightte"> Rs.<s:property value="openingstock" /> </label></td>
                                    </tr>
                                    <tr>
                                       	<td><label for="exampleInputEmail1" class="bomincoulab">Purchase Account</label></td>
                                        <td class="text-right"><label class="rightte">Rs.<s:property value="purchasestock" /> </label></td>
                                    </tr>
                                    <tr class="hidden">
                                       	<td><label for="exampleInputEmail1" class="bomincoulab">Expenses (Direct)</label></td>
                                        <td class="text-right"><label class="rightte">Rs.00.54 </label></td>
                                    </tr>
                                    <tr style="" class="hidden">
                                    	<td><label for="exampleInputEmail1" class="bomincoulab"></label></td>
                                        <td class="text-right"><label class="rightte" style="color: brown;"> Rs.164740.56 </label></td>
                                    </tr>
                                    <tr style="" class="hidden">
                                    	<td><label for="exampleInputEmail1" class="bomincoulab">Gross Loss</label></td>
                                        <td class="text-right"><label class="rightte"> Rs.17834.19 </label></td>
                                    </tr>
                                    
                                    <tr style="border-top: 4px solid #555;">
                                    	<td><label for="exampleInputEmail1" class="balanceamt bomincoulab">Total</label></td>
                                        <td class="text-right"><label class="balanceamt rightte"> Rs.<s:property value="totalDebit"/> </label></td>
                                    </tr>
                                </tbody>
		                            </table>
		                            <div class="col-lg-12 col-xs-12 col-md-12" style="background-color: green;color:#fff;text-align:center;">
		                            	<h4>NET PROFIT</h4>
		                            	<h4>Rs.<s:property value="netProfit"/></h4>
		                            </div>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="border-right: 1px solid #ddd;min-height: 500px;">
										<table class="table table-bordered" cellspacing="0" width="100%">
			                                <thead>
			                                <tr class="tableback">
			                                        <th style="font-size: 16px;">Particulars</th>
			                                        <th class="text-right" style="font-size: 16px;">Credit Amount</th>
			                                    </tr>
			                                </thead>
			                                	  <tbody>
                                    <tr>
                                        <td><label for="exampleInputEmail1" class="bomincoulab">Sales Account</label></td>
                                        <td class="text-right"><label class="rightte"> Rs. <s:property value="soldstock"/>  </label></td>
                                    </tr>
                                    <tr>
                                       	<td><label for="exampleInputEmail1" class="bomincoulab">Closing Stock</label></td>
                                        <td class="text-right"><label class="rightte">Rs. <s:property value="closingStock"/> </label></td>
                                    </tr>
                                    <tr class="hidden">
                                       	<td><label for="exampleInputEmail1" class="bomincoulab">Income (Direct)</label></td>
                                        <td class="text-right"><label class="rightte">Rs.00.00 </label></td>
                                    </tr>
                                    <tr class="hidden">
                                       	<td><label for="exampleInputEmail1" class="bomincoulab">Gross Loss</label></td>
                                        <td class="text-right"><label class="rightte">Rs.17834.19 </label></td>
                                    </tr>
                                    <tr class="hidden">
                                       	<td><label for="exampleInputEmail1" class="bomincoulab"></label></td>
                                        <td class="text-right"><label class="rightte" style="color: brown;">Rs.164740.56 </label></td>
                                    </tr>
                                    <tr class="hidden">
                                       	<td><label for="exampleInputEmail1" class="bomincoulab">Expenses (Indirect)</label></td>
                                        <td class="text-right"><label class="rightte">Rs.00.00 </label></td>
                                    </tr>
                                    <tr class="backred hidden">
                                       	<td><label for="exampleInputEmail1" class="bomincoulab" style="margin-bottom:0px !important;">Net Loss</label></td>
                                        <td class="text-right"><label class="rightte" style="margin-bottom:0px !important;">Rs.17834.19 </label></td>
                                    </tr>
                                    
                                    <tr style="border-top: 4px solid #555;">
                                    	<td><label for="exampleInputEmail1" class="balanceamt bomincoulab">Total</label></td>
                                        <td class="text-right"><label class="balanceamt rightte"> Rs.<s:property value="totalCredit"/> </label></td>
                                    </tr>
                                </tbody>
		                            </table>
		                            <div class="col-lg-12 col-xs-12 col-md-12" style="background-color: #d9534f;color:#fff;text-align:center;">
		                            	<h4>NET LOSS</h4>
		                            	<h4>Rs. <s:property value="netLoss"/></h4>
		                            </div>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
										<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
									</div>
								</div>
								
								<% ProductForm productForm= (ProductForm) session.getAttribute("productForm"); %>
								
								
								<script src="dtr/assets/js/vendor/hichart/highcharts.js"></script>
								<script src="dtr/assets/js/vendor/hichart/exporting.js"></script>
								
								<script>
									// Create the chart
									Highcharts.chart('container', {
									    chart: {
									        type: 'column'
									    },
									    title: {
									        text: 'PROFIT & LOSS ACCOUNT'
									    },
									    xAxis: {
									        type: 'category'
									    },
									    yAxis: {
									        title: {
									            text: 'Rupees'
									        }
									
									    },
									    legend: {
									        enabled: false
									    },
									    plotOptions: {
									        series: {
									            borderWidth: 0,
									            dataLabels: {
									                enabled: true,
									                format: 'Rs.{point.y:.1f}'
									            }
									        }
									    },
									    tooltip: {
									        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
									        pointFormat: '<span style="color:{point.color}">{point.name}</span>: Rs.<b>{point.y:.2f}</b><br/>'
									    },
									
									    series: [{
									        name: 'Accounts',
									        colorByPoint: true,
									        data: [{
									            name: 'Opening Account',
									            y: <%=productForm.getOpeningstock()%>,
									            drilldown: 'Opening Account'
									        }, {
									            name: 'Purchase Account',
									            y: <%=productForm.getPurchasestock()%>,
									            drilldown: 'Purchase Account'
									        }, {
									            name: 'Sales Account',
									            y: <%=productForm.getSoldstock()%>,
									            drilldown: 'Sales Account'
									        }, {
									            name: 'Closing Stock',
									            y: <%=productForm.getClosingStock()%>,
									            drilldown: 'Closing Stock'
									        }]
									    }],
									    drilldown: {
									        series: [{
									            name: 'Opening Account',
									            id: 'Opening Account',
									            data: [
									                [
									                    'v11.0',
									                    24.13
									                ],
									                [
									                    'v8.0',
									                    17.2
									                ],
									                [
									                    'v9.0',
									                    8.11
									                ],
									                [
									                    'v10.0',
									                    5.33
									                ],
									                [
									                    'v6.0',
									                    1.06
									                ],
									                [
									                    'v7.0',
									                    0.5
									                ]
									            ]
									        }, {
									            name: 'Purchase Account',
									            id: 'Purchase Account',
									            data: [
									                [
									                    'v40.0',
									                    5
									                ],
									                [
									                    'v41.0',
									                    4.32
									                ],
									                [
									                    'v42.0',
									                    3.68
									                ],
									                [
									                    'v39.0',
									                    2.96
									                ],
									                [
									                    'v36.0',
									                    2.53
									                ],
									                [
									                    'v43.0',
									                    1.45
									                ],
									                [
									                    'v31.0',
									                    1.24
									                ],
									                [
									                    'v35.0',
									                    0.85
									                ],
									                [
									                    'v38.0',
									                    0.6
									                ],
									                [
									                    'v32.0',
									                    0.55
									                ],
									                [
									                    'v37.0',
									                    0.38
									                ],
									                [
									                    'v33.0',
									                    0.19
									                ],
									                [
									                    'v34.0',
									                    0.14
									                ],
									                [
									                    'v30.0',
									                    0.14
									                ]
									            ]
									        }, {
									            name: 'Sales Account',
									            id: 'Sales Account',
									            data: [
									                [
									                    'v35',
									                    2.76
									                ],
									                [
									                    'v36',
									                    2.32
									                ],
									                [
									                    'v37',
									                    2.31
									                ],
									                [
									                    'v34',
									                    1.27
									                ],
									                [
									                    'v38',
									                    1.02
									                ],
									                [
									                    'v31',
									                    0.33
									                ],
									                [
									                    'v33',
									                    0.22
									                ],
									                [
									                    'v32',
									                    0.15
									                ]
									            ]
									        }, {
									            name: 'Closing Stock',
									            id: 'Closing Stock',
									            data: [
									                [
									                    'v8.0',
									                    2.56
									                ],
									                [
									                    'v7.1',
									                    0.77
									                ],
									                [
									                    'v5.1',
									                    0.42
									                ],
									                [
									                    'v5.0',
									                    0.3
									                ],
									                [
									                    'v6.1',
									                    0.29
									                ],
									                [
									                    'v7.0',
									                    0.26
									                ],
									                [
									                    'v6.2',
									                    0.17
									                ]
									            ]
									        }]
									    }
									});
								</script>


</body>
</html>