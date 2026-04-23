<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Inventory.eu.entity.Product"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>

<link rel="stylesheet" type="text/css" href="common/tablesortnew/dataTables.bootstrap.css" />

<script type="text/javascript" src="common/tablesortnew/jquery.dataTables.js"></script>
    <script type="text/javascript" src="common/tablesortnew/dataTables.bootstrap.js"></script>
    
     <script>
	  $(document).ready(function() {
	      $('#tablesort').DataTable();
	  });
	 </script>

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

	<% ArrayList<Product> graphSupplierReport=(ArrayList<Product>)session.getAttribute("graphSupplierReport"); %>

						<div class="row details mainheader hidden">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Sale Report</h4>
								</div>
							</div>
							
							
							<div class="row ">
								
							</div>
							
							
							
							<div class="">
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
									<div class="col-lg-6 col-xs-6 col-sm-6 col-md-6" style="border-right: 1px solid #ddd;">
										<div id="container" style="min-width: 310px; height: 500px; max-width: 600px; margin: 0 auto"></div>
										
									</div>
									<div class="col-lg-6 col-xs-6 col-sm-6 col-md-6" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<a style="display: none;" href="#" onclick="opencPopup('getPatientRecordEmr')" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Create
										Prescription </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form action="supplierledgerreportProduct" cssClass="form-inline search" theme="simple" method="post">
										  
										  
										    <s:textfield name="fromdate" id="fromdate"  cssClass="form-control" placeholder="From Date" cssStyle="width:10%" />
										    <s:textfield name="todate"  id="todate" cssClass="form-control" placeholder="To Date" cssStyle="width:10%" />
										   
											<select class="form-control choosen">
											  <option>Report</option>
											  <option>Daily</option>
											  <option>Weekly</option>
											  <option>Monthly</option>
											</select>
										  
										  <input type="submit" value="GO" class="btn btn-primary" />
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										</s:form>
										</div>
										</div>
								</div>
								<div class="col-lg-12 col-md-12">
									<table class="table table-bordered" cellspacing="0" width="100%" id="tablesort">
                                <thead>
                                <tr class="tableback">
                                        <th>Supplier Name</th>
                                        <th>Contact</th>
                                        <th class="text-right">Debit</th>
                                        <th class="text-right">Credit</th>
                                        <th class="text-right">Balance</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                	  <tbody>
                                	  
                                	 <s:iterator value="supplierProcurementList">
                                    <tr>
                                        <td><s:property value="vendor"/></td>
                                        <td><s:property value="contact"/></td>
										<td class="text-right">Rs.<s:property value="total"/></td>                                       
                                        <td class="text-right"><span style="color:red;">Rs.<s:property value="paymentAmount"/></span></td>
                                         <td class="text-right"><span style="color:green;">Rs.<s:property value="balance"/></span></td>
                                        <s:if test="paymentAmount>0">
                                        	<td><a href="#" onclick="viewSupplierHist(<s:property value="vendor_id"/>)" >View</a></td>
                                        </s:if>
                                        <s:else>
                                        	 <td></td>
                                        </s:else>
                                        
                                    </tr>  
                                        
                                    
                                    </s:iterator>
                                    
                                    
                                    
                                    <!--
                                    <tr>
                                        <td>Vrun Sharma pharma</td>
                                        <td>Plot no.10, khadgaon Road, wadi </td>
                                        <td>Nagpur</td>
                                        <td>arun.pharma@gmail.com</td>
                                        <td>9568245625</td>
                                        <td class="text-right"><span style="color:green;">Rs.550.00</span></td>
                                        <td class="text-right"><span style="color:red;">- Rs.1250.00</span></td>
                                        <td class="text-right">Rs.4501</td>
                                        <td><a href="#" data-toggle="modal" data-target="#history">View</a></td>
                                    </tr>
                                    <tr>
                                        <td>Vijay Gupta pharma</td>
                                        <td>Plot no.10, khadgaon Road, wadi </td>
                                        <td>Nagpur</td>
                                        <td>arun.pharma@gmail.com</td>
                                        <td>9568245625</td>
                                        <td class="text-right"><span style="color:red;">- Rs.1050.00</span></td>
                                        <td class="text-right"><span style="color:green;">Rs.1550.00</span></td>
                                        <td class="text-right">Rs.4501</td>
                                        <td><a href="#" data-toggle="modal" data-target="#history">View</a></td>
                                    </tr>
                                    
                                --></tbody>
                            </table>
								</div>
										
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
							        <h4 class="modal-title" id="headtitle">Arun Khunde pharma | Nagpur | 9568245625</h4>
							      </div>
							      <div class="modal-body">
							        <div id="page_printer" class="minhesigh">
							        <h4 class="modal-title visible-print hidden-md hidden-lg hidden-sm" id="disptitle" >Arun Khunde pharma | Nagpur | 9568245625</h4>
							            <div id="allTable">
							         	<b style="color: #e69e18;font-weight: normal;">Invoice No: 12345 | Date: 01-01-2017 | Memo: Credit</b>
							        		<div class="">
					                            <table class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                        <th style="width: 6%;">Code</th>
					                                        <th style="width: 6%;text-align:center">Qty</th>
					                                        <th style="width: 6%;text-align:center">pack</th>
					                                        <th style="width: 38%;">Product Name</th>
					                                        <th style="width: 4%;">Mfg</th>
					                                        <th style="width: 6%;">Batch No</th>
					                                        <th style="width: 6%;text-align:right">Price</th>
					                                        <th style="width: 6%;text-align:right">Total</th>
					                                    </tr>
					                                </thead>
					                                
					                                	  <tbody>
					                                    <tr>
					                                        <td>1542</td>
					                                        <td class="text-center">6</td>
					                                        <td class="text-center">1</td>
					                                        <td>CARDACE TAB (2.5 MG)</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td class="text-right">Rs.50</td>
					                                        <td class="text-right">Rs.100.00</td>
					                                    </tr>
					                                   <tr>
					                                        <td>4525</td>
					                                       	<td class="text-center">6</td>
					                                        <td class="text-center">2</td>
					                                        <td>TAMFLOW S</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td class="text-right">Rs.50</td>
					                                        <td class="text-right">Rs.100.00</td>
					                                    </tr>
					                                    <tr>
					                                        <td>5463</td>
					                                        <td class="text-center">6</td>
					                                        <td class="text-center">1</td>
					                                        <td>CARDACE TAB (2.5 MG)</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td class="text-right">Rs.50</td>
					                                        <td class="text-right">Rs.100.00</td>
					                                    </tr>
					
					                                </tbody>
					                                
					                              
					                            </table>
					                           <div class="" style="border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;">
					                           
					                            <div class="text-right">
					                            	<div class="" style="">
					                            	<h4 style="font-size: 13px;">Sub Total : Rs.300.00</h4>
					                            	<h4 style="color: #868686;font-size: 13px;">Discount(%) : Rs.00.00</h4>
					                            	<h4 style="font-size: 13px;">Vat : Rs.20.80</h4>
					                            	<h4 style="font-weight: bold;font-size: 13px;color:green;">Total : Rs.320.80</h4>
					                            </div>
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
							Highcharts.chart('container', {
						    chart: {
						        type: 'column'
						    },
						    title: {
						        text: 'Supplier Wise Report'
						    },
						    xAxis: {
						        categories: [
						         
						         <%for(Product product: graphSupplierReport){ %> 
						            '<%=product.getVendor()%>',
						          <%}%>  
						        ],
						        crosshair: true
						        
						    },
						    yAxis: {
						        min: 0,
						        title: {
						            text: 'Amount'
						        }
						    },
						    tooltip: {
						        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
						        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
						            '<td style="padding:0"><b>Rs.{point.y:.1f}</b></td></tr>',
						        footerFormat: '</table>',
						        shared: true,
						        useHTML: true
						    },
						    
						    plotOptions: {
						        column: {
						            pointPadding: 0.2,
						            borderWidth: 0
						        }
						    },
						    series: [{
						        name: 'Balance',
						        data: [
						           <%for(Product product: graphSupplierReport){%>
						                <%=product.getBalance()%>,
						           <%}%>
						        ]
						
						    }, {
						        name: 'Credit',
						        data: [
						          <%for(Product product: graphSupplierReport){%>
						                <%=product.getPaymentAmount()%>,
						           <%}%>
						        ]
						
						    }, {
						        name: 'Total',
						        data: [<%for(Product product: graphSupplierReport){%>
						                <%=product.getTotal()%>,
						           <%}%>]
						
						    }]
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