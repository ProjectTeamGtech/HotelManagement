<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="common/tablesortnew/dataTables.bootstrap.css" />


<style>
	.chosen-container {
    width: 100% !important;
}
</style>
    <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/tablesortnew/jquery.dataTables.js"></script>
    <script type="text/javascript" src="common/tablesortnew/dataTables.bootstrap.js"></script>

     <script>
	  $(document).ready(function() {
	      $('#tablesort').DataTable();
	  });
	 </script>
	 <script type="text/javascript">
	 function printExcel() {

	       $(".xlstable").table2excel({
						exclude: ".noExl",
						name: "Item Wise Stock Report",
						filename: "itemwisestockreport",
						fileext: ".xls",
						exclude_img: true,
						exclude_links: true,
						exclude_inputs: true
					});
	   }
	 </script>
	 <SCRIPT type="text/javascript">

    window.onload = function(){
    	
              
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
		             
    };
  

</SCRIPT>
</head>
<body>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px;">
										<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck" style="padding: 0px;" >
                       <div class="col-md-12" style="padding: 0px;">
                          <div class="form-inline">
						  	<s:form theme="simple" cssClass="form-inline search" action="itemwisestockreportProduct">
						  		<div class="form-group">
							         <span class="text-uppercase"><b style="font-weight: 900;">Item Wise Stock Report</b> &nbsp; - &nbsp;</span>
							    </div>
							    <div class="form-group" style="width: 13%;">
									<s:textfield  name="product_name"  cssClass="form-control"  placeholder="Search By Product Name " />		
								</div>
						  		<div class="form-group" style="width: 13%;">
						  			<s:textfield name="fromdate" readonly="true" id="fromdate" cssClass="form-control"  placeholder="From Date" />
						  		</div>
						  
						  		<div class="form-group" style="width: 13%;">
						  			<s:textfield name="todate" readonly="true" id="todate" cssClass="form-control"  placeholder="To Date" />
						  		</div>
						  		
						  		<div class="form-group">
					  				  <button type="submit" class="btn btn-primary">Go</button>
									  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
									  <button type="button" style="margin-left: 10px"  class="btn btn-warning pull-right" onclick="printDiv('page_printer');" >Print</button>
									  <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
								</div>
						  	
						   </s:form>
						   </div>
						  
                       </div>
                    </div> 
										
								</div>
								
								<%-- <s:iterator value="itemWiseListReport"> --%>
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
								<div id="page_printer">
									<h5 class="hidden-lg hidden-md visible-print"><span class="text-uppercase"><b>Item Wise Stock Report</b> &nbsp;  &nbsp;</span></h5>
								<%-- <h4 style="color: chocolate;"><b><s:property value="product_name"/> <small>(<s:property value="genericname"/>)</small></b><span class="hidden" style="color: #555;">Opening Stock: <s:property value="stock"/></span> &nbsp; | &nbsp; <span style="color: #555;">Supplier Name: <s:property value="vendor"/></span></h4> --%>
									<table class="table table-bordered xlstable" cellspacing="0" width="100%" id="" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<th style="width: 3%;">Sr.No</th>
					                                        <th style="width: 7%;">Batch No</th>
					                                        <th style="width: 7%;">Date</th>
					                                        <th style="width: 5%;">GRN NO</th>
					                                        <th style="width: 5%;">Invoice No</th>
					                                        <th style="width: 5%;">Product ID</th>
					                                        <th style="width: 15%;">Product Name</th>
					                                        <th style="width: 18%;">Supplier Name</th>
					                                        <th style="width: 5%;">Quantity</th>
					                                        <th style="width: 7%;">Exp Date</th>
					                                        <th style="width: 5%;text-align:right">MRP</th>
					                                        <th style="width: 5%;text-align:right">Unit Rate</th>
					                                        <th style="width: 3%;">GST</th>
					                                        <th style="width: 5%;text-align:right">Cal GST</th>
					                                       <th style="width: 10%;text-align:right">Net Rate</th>					                                    </tr>
					                                </thead>
					                                <tbody>
					                                    <%-- <s:iterator value="innerProductList"> --%>
					                                    <%int x=0; %>
					                                    <s:iterator value="itemWiseListReport">
					                                    <tr>
					                                    	<td><%=(++x) %></td>
					                                        <td><s:property value="batch_no"/></td>
					                                        <td><s:property value="date"/></td>
					                                        <td><s:property value="procurementid"/></td>
					                                        <td><s:property value="voucherno"/></td>
					                                        <td><s:property value="product_id"/></td>
					                                        <td><s:property value="prod_name"/></td>
					                                        <td><s:property value="vendor"/></td>
					                                        <td><s:property value="qty"/></td>
					                                        <td><s:property value="expiry_date"/></td>
					                                        <td style="text-align:right"><s:property value="mrp"/></td>
					                                        <td style="text-align:right"><s:property value="purchase_price"/></td>
					                                        <td style="text-align:right"><s:property value="grossVat"/>%</td>
					                                        <td style="text-align:right"><s:property value="vat"/></td>
					                                        <td style="text-align:right"><s:property value="netVal"/></td> 
					                                    </tr>
					                                    </s:iterator>
					                                </tbody>
					                            </table>
					                            </div>
								</div>
								<%-- </s:iterator> --%>

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