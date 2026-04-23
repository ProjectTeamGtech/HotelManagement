<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
	type="text/css" />
<link href="plugin/monthyear/MonthPicker.min.css" rel="stylesheet"
	type="text/css" />
<script src="plugin/monthyear/jquery-ui.min.js"></script>
<script src="plugin/monthyear/jquery.maskedinput.min.js"></script>
<script src="plugin/monthyear/MonthPicker.min.js"></script>


<style>
.topheadbaxck {
	background-color: rgb(239, 239, 239);
	padding: 8px 0px;
}

.table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
	font-size: 14px;
}
.label {
    color: #000;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
	line-height: 40px;
}
ul, ol {
    margin-top: 0;
    margin-bottom: 8.5px;
    list-style: none;
    padding: 0px 0px 0px 15px;
    font-size: 20px;
}

.chosen-container {
	width: 100% !important;
}
</style>

<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>

<script type="text/javascript">
function validSubmit(){
	
	var logo = document.getElementById('userImage').value;
	var vendorid= document.getElementById('vendorid').value; 
	//document.getElementById("tnameError").innerHTML = "";
	
	if(vendorid=='' || vendorid=='0'){
		 alert('Please select vendor');
		 return false;
	}
	else if(logo == ""){		
		 alert('Please Upload File.');
		 return false;
	}	
    else
    {
    	return true;
    }
	
}

</script>


<SCRIPT type="text/javascript">
	window.onload = function() {

		$("#expiry").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#voucherdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#security_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		setState();

	}
</SCRIPT>


</head>
<body>
	<%
		LoginInfo loginfo = LoginHelper.getLoginInfo(request);
	%>
	<div class="row details">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<h4>Goods Received with ODS file</h4>
		</div>
	</div>
	<div class="">
		<div class="" style="background-color: rgba(239, 239, 239, 0.42); padding: 9px; border: 1px dashed #ddd;">

			

			<div>
				<s:form action="uploadgrnProcurement" method="post"
					enctype="multipart/form-data">
					<s:actionerror cssStyle="color:red" />
					 <s:actionmessage cssStyle="color:green" />
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<div class="form-group" id="vendid">
					<s:select theme="simple" id="vendorid" list="vendorList"
						listKey="id" listValue="name" headerKey="0"
						headerValue="Select Supplier" name="vendor_id"
						cssClass="form-control chosen-select" />
				</div>
				
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<s:file name="userImage" id="userImage" label="Choose File" />
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<s:submit cssClass="btn btn-primary" onclick="return validSubmit()" />
			</div>
				</s:form>
			</div>
		</div>



	</div>







	<!-- View Product Details Modal -->
	<div id="viewdetailspro" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						Previous Invoice Details For - <span id="tabdata"> ALCOMAX
							TAB </span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
						<table class="table table-striped table-bordered"
							style="width: 100%;">
							<thead>
								<!-- <tr>
									<th style="width: 27%;">Supplier</th>
									<th style="width: 9%;">PO Date</th>
									<th style="width: 7%;">Quantity</th>
									<th
										style="width: 7%; background-color: brown; text-align: right;">Price</th>
									<th style="width: 7%;">Dis</th>
									<th style="width: 7%;">GST</th>
								</tr> -->
								<tr>
										<th style="width: 27%;">Supplier</th>
										<th style="width: 9%;">Date</th>
										<th style="width: 7%;">GRN</th>
										<th style="width: 7%;">Invoice No.</th>
										<th style="width: 7%;">GST</th>
										<th style="width: 7%;">Quantity</th>
										<th style="width: 7%;">Free Qty</th>
										<th style="width: 7%; background-color: brown; text-align: right;">MRP</th>
										<th style="width: 7%; background-color: brown; text-align: right;">Sale Price</th>
										<th style="width: 7%; background-color: brown; text-align: right;">Purchase Price</th>
										<th style="width: 7%;">Dis.</th>
										<th style="width: 7%;">Total</th>
									</tr>
							</thead>
							<tbody id="innerProduct">
								<!--<tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           <tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           <tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           <tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           <tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           -->
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>






	<!-- Add New Product Modal -->
	<div id="addnewproduct" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="suppliername">Supplier Name</h4>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12">
						<div class="from-group">
							<label>Generic Name</label> <input type="text"
								class="form-control" id="genericname" required="required">
						</div>

					</div>
					<div class="col-lg-12 col-md-12" style="margin-top: 10px;">

						<div class="from-group">
							<label>Product Name</label> <input type="text"
								class="form-control" id="prodname" required="required">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveProductProcurement()" style="margin-top: 15px;">Save</button>
				</div>
			</div>

		</div>
	</div>






	<script src="common/chosen_v1.1.0/chosen.jquery.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		var config = {
			'.chosen-select' : {},
			'.chosen-select-deselect' : {
				allow_single_deselect : true
			},
			'.chosen-select-no-single' : {
				disable_search_threshold : 10
			},
			'.chosen-select-no-results' : {
				no_results_text : 'Oops, nothing found!'
			},
			'.chosen-select-width' : {
				width : "100%"
			}
		}
		for ( var selector in config) {
			$(selector).chosen(config[selector]);
		}

		// Apply an input mask which mkase sure the user 
		// limits the user to typing a month in the 
		//fromat specified in the MonthFormat option.
		$('#DigitalBush').MonthPicker({
			UseInputMask : true
		});
		$('#DigitalBushBoth').MonthPicker({
			UseInputMask : true,
			ValidationErrorMessage : 'Invalid Date!'
		});
	</script>

</body>
</html>