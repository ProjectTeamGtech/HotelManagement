<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
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

<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<SCRIPT type="text/javascript">

	function printReport() {
				
				  $("#tablesort").table2excel({
					exclude: ".noExl",
					name: "Profit Report",
					filename: "ProfitReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }
	
	</SCRIPT>
	

</head>
<body>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
											<div class="col-lg-9 col-md-9 col-sm-9">
													<form class="form-inline search">
										    <input type="text" name="fromdate" value="30-04-2017" id="fromdate" class="form-control hasDatepicker hidden" style="width:10%" placeholder="From Date">
										    <input type="text" name="todate" value="30-05-2017" id="todate" class="form-control hasDatepicker hidden" style="width:10%" placeholder="To Date">
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <button type="button" class="btn btn-warning pull-right" onclick="printDiv('page_printer');" style="margin-right: 5px;">Print</button>
										  <a href="#" onclick="printReport()" class="btn btn-primary">Export to excel</a>
										</form>
											</div>



										</div>
										</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12">
								<div id="page_printer">
								
									<table class="table table-bordered" id="tablesort" cellspacing="0" width="100%" id="" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<th>Sr.No</th>
					                                    	<th>Bill .No</th>
					                                    	<th>Patient Name</th>
					                                    	<th>Bill Amount</th>
					                                    	<th>Purchase Amount</th>
					                                    	<th>Discount</th>
					                                    	<th>GST</th>
					                                    	<th>Profit</th>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                   <% int i=0; %> 	
					                                   <s:iterator value="profitlossreportList"> 	
					                                    <tr>
					                                    	<td><%=++i %></td>
					                                    	<td><s:property value="billno" /></td>
					                                    	<td><s:property value="name" /></td>
					                                    	<td><s:property value="totalamt" /></td>
					                                    	<td><s:property value="purchase_price" /></td>
					                                    	<td><s:property value="discount" /></td>
					                                    	<td><s:property value="vat" /></td>
					                                    	<td><s:property value="profit" /></td>
					                                    </tr>
					                                    </s:iterator>
					                                    </tbody> 
					                                
					                            </table>
					                            
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