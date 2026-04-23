<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="plugin/checkbox/style.css">

<style>
	.pnew{
    width: 3%;

}
.pview{
    width: 4%;

}
body {
    background-color: #fff;
}
.form-control {
    transition: none;
    border: 1px solid #e4e4e4;
    color: #222222;
    font-size: 12px;
    font-weight: normal;
    height: 25px;
    letter-spacing: 0.04em;
    margin-bottom: 0px;
    padding: 0px;
    width: 100%;
    background-color: #fff;
}
.rightborhe{
	border-right: 2px solid #efefef;
    min-height: 450px;
}
.page{
    width: 7%;
}

.mainheader {
    background-color: #43b9be !important;
}.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
.ipdtrans{
	    background-color: #a94442;
    color: #fff;
        padding: 3px 11px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}


.ottrans{
	    background-color: #f0ad4e;
    color: #fff;
    padding: 3px 13px 3px 5px;
    
    border-radius: 4px;
    font-size: 11px;
}
.opdtrans{
  background-color: #8a6d3b;
    color: #fff;
       padding: 3px 5px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}
.shifetdca{
	color: #555 !important;
    background-color: #efefef;
    padding: 3px 5px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}
.stockheight{
	    height: 16px;
}
.checkbox {
    position: relative;
    display: block;
    min-height: 13px;
    margin-top: 0px;
    margin-bottom: 0px;
}
.bgreen{
	    background-color: rgba(22, 160, 133, 0.12);
    padding: 10px 15px 15px 15px;
    min-height: 443px;
}
.list-group.no-border .list-group-item {
    border-width: 1px 0;
    border-left: 3px solid;
}
.list-group-item {
    position: relative;
    display: block;
    padding: 10px 11px;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid #ddd;
}
.rightborder{
	    border-right: 4px solid #ddd;
	    min-height: 215px;
}


.checkbox-custom-alt > i {
    width: 16px;
    height: 16px;
    background-color: transparent;
    border: 2px solid #dfdfdf;
    margin-right: 6px;
    margin-left: -18px;
}
.checkbox-custom-alt input:checked + i:before {
    top: 1px;
    left: 1px;
    width: auto;
    height: auto;
    background-color: transparent;
    opacity: 1;
}
.bg-lightred {
    background-color: #e05d6f !important;
    color: white !important;
}
.table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    border-top: none; 
}
.checkbox-custom, .checkbox-custom-alt {
    padding-left: 13px;
    cursor: pointer;
}
.imflag {
    display: inline-block;
    width: 6%;
    margin-right: 5px;
}
.imflag1 {
    display: inline-block;
    width: 10%;
}
.blink_me {
  animation: blinker 1s linear infinite;
  color:red;
}

@keyframes blinker {  
  50% { opacity: 0; }
}
</style>

</head>
<body>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<div Class="form-inline">
											
										  <div class="form-group">
										    <input name="searchText" class="form-control" placeholder="Search Name/ID"/>
										  </div>
										  <div class="form-group">
										    <input name="searchText" class="form-control" placeholder="Search Date"/>
										  </div>
										  
										  <div class="form-group">
										  <select class="form-control" name="blood_group">
		                                        <option value="">Select Blood Group</option>
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
										   
										  <button type="submit" class="btn btn-primary" onclick="searchDonor()">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										</div>
										</div>
										<div class="col-lg-12 col-md-12 col-md-12" style="padding: 0px;">
										<div class="col-lg-9 col-md-9 col-xs-9" style="padding: 0px;border-right: 1px dashed #ddd;min-height: 500px;">
											<table class="table my-table xlstable table-bordered" style="width: 100%;">
                                        <thead>
                                            <tr>
                                            	<th style="width: 3%;">Sr.No</th>
                                            	<th style="width: 12%;">Date | Time</th>
                                                <th style="width: 11%;">Patient Details</th>
                                                <th style="width: 4%;">Group</th>
                                                <th style="width: 41%;">Components Request</th>
                                                <th style="width: 24%;"></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                           		<td>01</td>
                                           		<td>22/05/2017 | 12:11 PM</td>
                                           		<td>Praful ghagre</td>
                                           		<td>O+</td>
                                           		<td><span style="color: brown;">PRC</span> 2(BTL) | <span style="color: cornflowerblue;">FFP</span> 1(BTL) | <span style="color: fuchsia;">PC</span> 3(BTL) | <span style="color: lightcoral;">SDP</span> 2(BTL) | <span style="color: seagreen;">CRYO</span> 1(BTL) | <span style="color: darkorange;">WB</span> 3(BTL) </td>
                                           		<td>
                                           			<div>
												        <input id="checkbox-2" class="checkbox-custom" name="checkbox-2" type="checkbox" onclick="disableCHK()" checked>
												        <label id="lblCHK2" for="checkbox-2" class="checkbox-custom-label">Abhinav Parmar | 22/05/2017 16:42 PM</label>
												      </div>
                                           		</td>
                                           		<td><a href="#" onclick="openPopup('bloodinvoiceBloodbank')" >View</a></td>
                                           </tr>
                                           <tr>
                                           		<td>02</td>
                                           		<td>22/05/2017 | 12:11 PM</td>
                                           		<td>Praful ghagre</td>
                                           		<td>O+</td>
                                           		<td><span style="color: brown;">PRC</span> 2(BTL) | <span style="color: cornflowerblue;">FFP</span> 1(BTL) | <span style="color: fuchsia;">PC</span> 3(BTL) | <span style="color: lightcoral;">SDP</span> 2(BTL) | <span style="color: seagreen;">CRYO</span> 1(BTL) | <span style="color: darkorange;">WB</span> 3(BTL) </td>
                                           		<td>
                                           			<div>
												        <input id="checkbox-2" class="checkbox-custom" name="checkbox-2" type="checkbox" onclick="disableCHK()" checked>
												        <label id="lblCHK2" for="checkbox-2" class="checkbox-custom-label">Abhinav Parmar | 22/05/2017 16:42 PM</label>
												      </div>
                                           		</td>
                                           		<td><a href="#" onclick="openPopup('stockcheckBloodbank')" >check</a></td>
                                           </tr>
                                           
                                           <tr>
                                           		<td>03</td>
                                           		<td>22/05/2017 | 12:11 PM</td>
                                           		<td>Praful ghagre</td>
                                           		<td>O+</td>
                                           		<td><span style="color: brown;">PRC</span> 2(BTL) | <span style="color: cornflowerblue;">FFP</span> 1(BTL) | <span style="color: fuchsia;">PC</span> 3(BTL) | <span style="color: lightcoral;">SDP</span> 2(BTL) | <span style="color: seagreen;">CRYO</span> 1(BTL) | <span style="color: darkorange;">WB</span> 3(BTL) </td>
                                           		<td>
                                           			<div>
												        <input id="checkbox-3" class="checkbox-custom" name="checkbox-3" type="checkbox" onclick="disableCHK1()">
												        <label id="lblCHK3" for="checkbox-3" class="checkbox-custom-label">Processing</label>
												      </div>
                                           		</td>
                                           		<td><a href="#" data-toggle="modal" data-target="#rejectedsam">Reject</a></td>
                                           </tr>
                                        </tbody>
                                    </table>
										</div>
										<div class="col-lg-3 col-md-3 col-xs-3">
											<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
										</div>
											
										</div>
								</div>
							</div>
							
							
							<!-- Modal -->
							<div id="rejectedsam" class="modal fade" role="dialog">
							  <div class="modal-dialog modal-sm">
							
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title">Rejected</h4>
							      </div>
							      <div class="modal-body">
							        <div class="col-lg-12 col-md-12 col-xs-12">
							        	<div class="form-group">
										    <label for="email"><i class="fa fa-tint" style="color:red;" aria-hidden="true"></i> Praful Ghagre</label>
										    <span class="pull-right">21 | Genral</span>
										</div>
							        	<div class="form-group">
										    <label for="email">Reason for rejection</label>
										   <textarea class="form-control" rows="3"></textarea>
										  </div>
							        </div>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-primary">Rejected</button>
							      </div>
							    </div>
							
							  </div>
							</div>
							
							
							
							
								<script src="dtr/assets/js/vendor/hichart/highcharts.js"></script>
							<script src="dtr/assets/js/vendor/hichart/exporting.js"></script>
							
							<script>
									function disableCHK()
				                	{
					                	document.getElementById("lblCHK2").textContent="Abhinav Parmar | 22/05/2017 16:42 PM";
					                	document.getElementById("checkbox-2").disabled = true;
				                	}
				                	function disableCHK1()
				                	{
					                	document.getElementById("lblCHK3").textContent="Abhinav Parmar | 22/05/2017 16:42 PM";
					                	document.getElementById("checkbox-3").disabled = true;
				                	}
							</script>
							
							
							<script>
								// Create the chart
Highcharts.chart('container', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Todays Report'
    },
    xAxis: {
        type: 'category'
    },
    yAxis: {
        title: {
            text: 'Total Cross Matching'
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
                format: '{point.y:.1f}%'
            }
        }
    },

    tooltip: {
        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
    },

    series: [{
        name: 'Cross Matching',
        colorByPoint: true,
        data: [{
            name: 'Accept',
            y: 56.33,
            drilldown: 'Accept'
        }, {
            name: 'Reject',
            y: 24.03,
            drilldown: 'Reject'
        }]
    }],
    drilldown: {
        series: [{
            name: 'Microsoft Internet Explorer',
            id: 'Microsoft Internet Explorer',
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
            name: 'Chrome',
            id: 'Chrome',
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
            name: 'Firefox',
            id: 'Firefox',
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
            name: 'Safari',
            id: 'Safari',
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
        }, {
            name: 'Opera',
            id: 'Opera',
            data: [
                [
                    'v12.x',
                    0.34
                ],
                [
                    'v28',
                    0.24
                ],
                [
                    'v27',
                    0.17
                ],
                [
                    'v29',
                    0.16
                ]
            ]
        }]
    }
});
							</script>
							
							
							

</body>
</html>