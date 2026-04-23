<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="common/tablesortnew/dataTables.bootstrap.css" />

<script type="text/javascript" src="common/tablesortnew/jquery.dataTables.js"></script>
    <script type="text/javascript" src="common/tablesortnew/dataTables.bootstrap.js"></script>
    
     <script>
	  $(document).ready(function() {
	      $('#tablesort').DataTable();
	  });
	 </script>



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

<% double[] usedead= (double[]) session.getAttribute("usedead"); %>


<div class="row">
								<div class="col-lg-12 col-md-12 col-xs-12">
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4" style="border-right: 1px solid #ddd;">
										
														<div id="container" style="min-width: 310px; max-width: 600px; height: 400px; margin: 0 auto"></div>


									</div>
									<div class="col-lg-8 col-xs-8 col-sm-8 col-md-8" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form cssClass="form-inline search" theme="simple" action="expirymedicineProduct" method="post">
										  
										    <s:textfield name="fromdate" id="fromdate" cssClass="form-control" cssStyle="width:10%" placeholder="From Date" />
										    <s:textfield name="todate" id="todate" cssClass="form-control" cssStyle="width:10%" placeholder="To Date" /> 
											<s:select list="#{'30':'30 Days','60':'60 Days','90':'90 Days'}" cssClass="form-control" name="days"  />
											 
											<select class="form-control" name="report">
											  <option value="0">Use</option>
											  <option value="1">Dead Stock</option>
											</select>
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										</s:form>
										</div>
										</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12">
									<table class="table table-bordered" id="tablesort" cellspacing="0" width="100%">
                                <thead>
                                <tr class="tableback">
                                		<th style="width:3%;"></th>
                                        <th>Expiry Date</th>
                                        <th>Days</th>
                                        <th>Product Name</th>
                                        <th>Qty</th>
                                        <th>Stock Status</th>
                                        <th>Supplier Name</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                	  <tbody>
                                	   <%int i=0; %>
                                	   <s:iterator value="expiryMedicineReport">
	                                     <tr>
		                                     <td><%=(++i) %></td>
		                                     <td style="color:red;"><s:property value="expiry_date"/></td>
		                                     <td><s:property value="days"/></td>
		                                     <td><s:property value="product_name"/> (<s:property value="genericname"/>)</td>
		                                     <td><s:property value="stock"/></td>
		                                     <s:if test="status==1">
		                                      	<td>Use</td>
		                                     </s:if>
		                                     <s:else>
		                                     	<td style="color: chocolate;">Dead Stock</td>
		                                     </s:else>
		                                     <td><s:property value="vendor"/></td>
		                                     <td><a href="returntosupplierProcurement?pid=<s:property value="id"/>&voucherno=<s:property value="voucherno"/>"  >return</a></td>
	                                    </tr>
	                                    </s:iterator>
	                                    <!--<tr>
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
                                    --></tbody>
                            </table>
								</div>
							
									</div>
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
                y: <%=usedead[0]%>
            }, {
                name: 'Dead Stcok',
                y: <%=usedead[1]%>,
                sliced: true,
                selected: true
            }]
        }]
    });
});
							
							</script>
							


</body>
</html>