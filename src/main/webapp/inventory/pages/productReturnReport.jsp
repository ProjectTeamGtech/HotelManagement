<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style tyle="text/css">
  
   @media print
   {
      body{
      	font-size:12px !important;
      }
      h5, .h5 {
	    font-size: 8px !important;
	}
   }
  
</style>
</head>
<body>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
											<div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0px;">
												<select class="form-control chosen-select">
												  <option>Select Supplier</option>
												  <option>Supplier 1</option>
												  <option>Supplier 2</option>
												  <option>Supplier 3</option>
												</select>
											</div>
											<div class="col-lg-9 col-md-9 col-sm-9">
													<form class="form-inline search">
										    <input type="text" name="fromdate" value="30-04-2017" id="fromdate" class="form-control hasDatepicker" style="width:10%" placeholder="From Date">
										    <input type="text" name="todate" value="30-05-2017" id="todate" class="form-control hasDatepicker" style="width:10%" placeholder="To Date">
											<select class="form-control">
											  <option>Report</option>
											  <option>Daily</option>
											  <option>Weekly</option>
											  <option>Monthly</option>
											</select>
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <button type="button" class="btn btn-warning pull-right" onclick="printDiv('page_printer');" style="margin-right: 5px;">Print</button>
										  <a href="#" onclick="#" class="btn btn-primary hidden">Export to excel</a>
										</form>
											</div>
											
										



										</div>
										</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12">
								<div id="page_printer">
								<h4 class="hidden-lg hidden-md hidden-sm hidden-xs visible-print" style="text-align: center;border-bottom: 4px double #ddd;line-height: 30px;"><b>Product Return Summary</b></h4>
								<h4 style="color: chocolate;"><b>MRS Ganesh Pharma</b></h4>
								
								<h5 style="color: chocolate;margin-bottom: 5px;"><span style="color: #555;">Product Return: 2</span> &nbsp; | &nbsp; <span style="color: #555;">Return Date: 01/06/2017</span> &nbsp; | &nbsp; <span style="color: #e05d6f;">Voucher No: ASD123</span></h5>
									<table class="table table-bordered" cellspacing="0" width="100%" id="" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<th style="width: 5%;">Sr.No</th>
					                                    	<th style="width: 7%;">UserID</th>
					                                        <th style="width: 33%;">Product Name</th>
					                                        <th style="width: 6%;">Batch</th>
					                                        <th style="width: 6%;">Expiry</th>
					                                        <th style="width: 6%;">Mfg</th>
					                                        <th style="width: 6%;text-align:right">MRP</th>
					                                        <th style="width: 3%;text-align:center">VAT(%)</th>
					                                        <th style="width: 6%;text-align:right">Rate</th>
					                                        <th style="width: 5%;text-align:center">Ret.Qty</th>
					                                        <th style="width: 6%;text-align:right">Total</th>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                    <tr>
					                                    	<td>1</td>
					                                    	<td>Admin</td>
					                                        <td>Product Name1</td>
					                                        <td>ADS523</td>
					                                        <td>02/08/2019</td>
					                                        <td>ADD2223</td>
					                                        <td class="text-right">Rs.50.00</td>
					                                        <td style="text-align:center">6</td>
					                                        <td class="text-right">Rs.100.00</td>
					                                        <td style="text-align:center">2</td>
					                                        <td class="text-right">Rs.200.00</td>
					                                    </tr>
					                                    <tr>
					                                    	<td>2</td>
					                                    	<td>Admin</td>
					                                        <td>Product Name2</td>
					                                        <td>ADS523</td>
					                                        <td>02/08/2019</td>
					                                        <td>ADD2223</td>
					                                        <td class="text-right">Rs.50.00</td>
					                                        <td style="text-align:center">6</td>
					                                        <td class="text-right">Rs.100.00</td>
					                                        <td style="text-align:center">2</td>
					                                        <td class="text-right">Rs.200.00</td>
					                                    </tr>
					                                </tbody>
					                            </table>
					                            
					                            <div class="col-lg-12 col-md-12 col-xs-12" style="text-align: right;padding: 0px;">
					                            	<h5>Subtotal : Rs.<span>400.0</span> &nbsp; | &nbsp; Discount : Rs.<span>0.00</span> &nbsp; | &nbsp; VAT : Rs.<span>36.00</span> &nbsp; | &nbsp; <b style="color: green;">NET Payble Amount :</b> <span style="color: green;">Rs.436.00</span></h5>
									         	</div>
									         	
									         	<h5 style="color: chocolate;margin-bottom: 5px;"><span style="color: #555;">Product Return: 2</span> &nbsp; | &nbsp; <span style="color: #555;">Return Date: 01/06/2017</span> &nbsp; | &nbsp; <span style="color: #e05d6f;">Voucher No: ASD123</span></h5>
									<table class="table table-bordered" cellspacing="0" width="100%" id="" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<th style="width: 5%;">Sr.No</th>
					                                    	<th style="width: 7%;">UserID</th>
					                                        <th style="width: 33%;">Product Name</th>
					                                        <th style="width: 6%;">Batch</th>
					                                        <th style="width: 6%;">Expiry</th>
					                                        <th style="width: 6%;">Mfg</th>
					                                        <th style="width: 6%;text-align:right">MRP</th>
					                                        <th style="width: 3%;text-align:center">VAT(%)</th>
					                                        <th style="width: 6%;text-align:right">Rate</th>
					                                        <th style="width: 5%;text-align:center">Ret.Qty</th>
					                                        <th style="width: 6%;text-align:right">Total</th>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                    <tr>
					                                    	<td>1</td>
					                                    	<td>Admin</td>
					                                        <td>Product Name1</td>
					                                        <td>ADS523</td>
					                                        <td>02/08/2019</td>
					                                        <td>ADD2223</td>
					                                        <td class="text-right">Rs.50.00</td>
					                                        <td style="text-align:center">6</td>
					                                        <td class="text-right">Rs.100.00</td>
					                                        <td style="text-align:center">2</td>
					                                        <td class="text-right">Rs.200.00</td>
					                                    </tr>
					                                    <tr>
					                                    	<td>2</td>
					                                    	<td>Admin</td>
					                                        <td>Product Name2</td>
					                                        <td>ADS523</td>
					                                        <td>02/08/2019</td>
					                                        <td>ADD2223</td>
					                                        <td class="text-right">Rs.50.00</td>
					                                        <td style="text-align:center">6</td>
					                                        <td class="text-right">Rs.100.00</td>
					                                        <td style="text-align:center">2</td>
					                                        <td class="text-right">Rs.200.00</td>
					                                    </tr>
					                                </tbody>
					                            </table>
					                            <div class="col-lg-12 col-md-12 col-xs-12" style="text-align: right;padding: 0px;">
					                            	<h5>Subtotal : Rs.<span>400.0</span> &nbsp; | &nbsp; Discount : Rs.<span>0.00</span> &nbsp; | &nbsp; VAT : Rs.<span>36.00</span> &nbsp; | &nbsp; <b style="color: green;">NET Payble Amount :</b> <span style="color: green;">Rs.436.00</span></h5>
									         	</div>
					                            </div>
					                            
					                            
					                            
								</div>
								
								<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
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