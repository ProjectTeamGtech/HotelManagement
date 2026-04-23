<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
	.chosen-container {
    width: 100% !important;
}

</style>

</head>
<SCRIPT type="text/javascript" src="pharmacy/js/mdcinebarcode.js"></SCRIPT>
<body>
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4><i class="fa fa-barcode" aria-hidden="true"></i> &nbsp; Product Barcode</h4>
									</div>
								</div>
								<div class="col-lg-12 col-xs-12 col-sm-12" style="padding:0px;margin-top:10px;">
									<div class="col-lg-8 col-xs-8 col-sm-8" style="padding-left:0px;border-right: 1px solid #ddd;min-height: 550px;">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: #dff0d8;padding: 8px 0px 8px 10px;border: 2px solid #cecece;">
								         <div class="form-inline">
								         <div class="form-group" style="width: 20%;">
											 <s:select list="warehouseList" id="warehouse_id" name="warehouse_id" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="setproductofwarehouse(this.value)" cssClass="form-control chosen-select"></s:select>
								          </div>
								          <div class="form-group" style="width: 60%;" id="productdatadiv">
											  <%-- <s:select onchange="showprodpackajax(this.value)" list="inventoryPriscList" listKey="id" id="newmedicine" listValue="genericname" headerKey="0" headerValue="select medicine" name="mdicinename" cssClass="form-control chosen-select" cssStyle="width:100%;">
								            </s:select>  --%>
								            <select onchange="showprodpackajax(this.value)" id="newmedicine"  name="mdicinename"  class="form-control chosen-select">
								            	<option value="0">Select Product</option>
								            </select>
								          </div>
								          <div class="form-group" style="width: 10%;">
								                <input type="text" id="qty" class="form-control" placeholder="Qty" style="width:100%;">
								          </div>
								          
								          <a href="#" onclick="addtempbarcode()" title="Add New"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;position: absolute;    margin-left: 5px;"></i></a>
								          <div class="form-group hidden" style="padding-left: 40px;">
								         <a onclick="generatebarcodeofinventory(1)"><i class='fa fa-print'></i></a>
								         </div>
								         </div>
         								</div>
         								
         								<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 15px;margin-bottom: 15px;border-right: 1px solid #ddd;border-left: 1px solid #ddd;">
		         						<table class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
		                                	<tbody id="barcodedataid">
		                                	</tbody>
		                            	</table>
         								</div>
         								
         								<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" >
		         							<div class="form-inline" style="float: right;">
		         								<div class="form-group">
		         									<a onclick="generatebarcodeofinventory(1)"><input type="button" class="btn btn-primary" value="Generate Single Barcode"></a>
		         								</div>
		         								<div class="form-group">
		         									<a href="#" onclick="generatebarcodeofinventory(0)"><input type="button" class="btn btn-primary pull-right" value="Generate Multiple Barcode"></a>
		         								</div>
		         							</div>
         								</div>
         								
         								<!-- <a href="barcodePharmacy"><input type="button" class="btn btn-primary pull-right" value=" Generate Barcode "></a> -->
         								
         								
									</div>
									<div class="col-lg-4 col-xs-4 col-sm-4">
									
									</div>
								</div>
								
								<div>
									
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
</body>
</html>