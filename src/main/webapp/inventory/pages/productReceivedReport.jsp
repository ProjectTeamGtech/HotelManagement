<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
 <%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- <link rel="stylesheet" href="pharmacy/searchexport/dataTables.bootstrap.css">
		<link rel="stylesheet" href="pharmacy/searchexport/buttons.bootstrap.css">  -->


<style tyle="text/css">
@media print {
	body {
		font-size: 12px !important;
	}
	h5,.h5 {
		font-size: 8px !important;
	}
}

</style>
<style>
	.chosen-container {
    width: 100% !important;
}
</style>

<style>
	 .padright {
            padding-left: 40px;
        }
        .table.table {
            color: RGBA(85, 85, 85, 0.85);
            background-color: #fff;
        }

        .comtitle {
            font-size: 13px;
            background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
            color: rgb(255, 255, 255);
        }

        .marbot25 {
            margin-bottom: 25px;
        }

        .editcompany {
            float: right;
            font-size: 17px;
            color: #fff;
        }

        .borright {
            border-right: 1px dashed rgb(192, 192, 192);
        }

        .buildinglogo {
            width: 60%;
            margin-top: 30px;
        }
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
            border-top: 0;
            min-height: auto !important;
        }
        .miheight {
            min-height: auto !important;
        }
        .my-table th {
            background-color: #424A5D;
            color: #fff !important;
            border-bottom: 1px solid #DFD8D4;
            border-right: 1px solid #DFD8D4;
            border-top: 1px solid #DFD8D4;
            padding: 3px 3px 4px 5px;
            text-align: left;
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 1px 7px 1px 7px !important;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
                display: inline-block;
            }
        .sidebar-xs #header .branding {
            width: 100%;
            padding-top: 7px;
            text-align: center;
        }
        .theight {
            height: 21px;
        }

        .contain {
  width: 500px;
  margin: 0 auto;
  position: relative;
  padding: 20px;
}
.notificationicon {
 float:right;
}


.topheadbaxck {
    background-color: #f5f5f5;
    padding: 10px 10px 10px 10px;
}

.notificationicon.on i {
  font-size: 18px;
}
.notifications {
  max-width: 300px;
  width: 300px;
  background: #fff;
  border: 1px solid #ccc;
  padding: 0px;
  box-shadow: 0px 0px 20px #666;
  position: absolute;
  opacity: 1;
  top: 25px;
  transition: .2s;
  opacity: 0;
  right: 5px;
}

.notifications.open {
  opacity: 1;
  transition: .2s;
}
.notifications li {
 
  list-style-type: none;
}
.notifications li.titlebar {
  border-bottom: 1px solid #ccc;
  color: #666;
  font-size: 12px;
  cursor: inherit;
  padding: 2px 5px;
}
.notifications li.titlebar:hover {
  background: #fff;
}
.notifications li.titlebar .settings {
  float: right;
  cursor: pointer;
}
.notifications li.seeall {
  text-align: center;
  font-size: 12px;
  min-height: 30px;
  text-transform: uppercase;
  position: relative;
  border-top: 1px solid #ccc;
}
.notifications li.seeall a {
  background: #f5f5f5;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  color: #007fff;
  padding-top: 8px;
}
.notifications .notifbox {
  max-height: 300px;
  overflow: auto;
}
.notifications .notifbox li {
  color: #666;
}
.notifications .notifbox li a {
  color: #666;
}
.notifications .notifbox li.unread {
  background: #e5f2ff;
}
.notifications .notifbox li.notif {
  min-height: 40px;
  border-bottom: 1px solid #ccc;
  position: relative;
}
.notifications .notifbox li.notif:last-child {
  border-bottom: none;
}
.notifications .notifbox li.notif .imageblock {
  width: 70px;
  position: absolute;
  left: 5px;
}
.notifications .notifbox li.notif .imageblock .notifimage {
  height: 60px;
}
.notifications .notifbox li.notif .messageblock {
    padding: 8px;
}
.notifications .notifbox li.notif .messageblock .message a {
  color: #007fff;
}
.notifications .notifbox li.notif .messageblock .messageaction {
  min-height: 30px;
  margin-bottom: 5px;
}
.notifications .notifbox li.notif .messageblock .messageaction .button {
  font-weight: normal;
  text-transform: uppercase;
}
.notifications .notifbox li.notif .messageblock .messageaction .button.success,
.notifications .notifbox li.notif .messageblock .messageaction .button.alert {
  color: #fff;
}
.notifications .notifbox li.notif .messageblock .messageinfo {
  font-size: 13px;
  color: #999;
}
.badge {
  position: absolute;
  top: 30px;
  right: -20px;
}    
.title {
    display: inline;
    float: none;
    margin-left: 17px;
   color: #fff;
    font-size: 12px;
}
.btnbackset{
 background-color: #efefef;
 padding: 6px;
}
     .count {
    position: absolute;
    top: -2px;
    right: 11px;
    text-align: center;
    font-size: 9px;
    padding: 2px 3px;
    line-height: .9;
     background-color: #dd4b39 !important;
     border-radius: 50px;
}
     .setborba{
	background-color: #efefef !important;
    padding-top: 5px !important;
}
 .dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus {
    background-image: linear-gradient(to bottom, #777 0, #777 100%) !important;
    
}
.dropdown-menu {
    padding: 0px 0 !important;
    margin: 0px 0 0 !important;
}
ul.dt-button-collection.dropdown-menu>* {
    -webkit-column-break-inside: avoid;
    break-inside: avoid;
    border-bottom: 1px solid rgba(0, 0, 0, 0.5) !important;
}
	 b, strong {
    font-weight: 900;
}
.topback2 {
    background-color: #f5f5f5;
    padding: 10px 0px 10px 15px;
}
</style>


<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<SCRIPT type="text/javascript">

	function printReport() {
				
				  $(".table").table2excel({
					exclude: ".noExl",
					name: "Product Received Report",
					filename: "productReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }
	
	function showHideDetails(id) {
		
		if(document.getElementById('data'+id).style.display==''){
			document.getElementById('data'+id).style.display='none';
		}else{
			document.getElementById('data'+id).style.display='';
		}
	}
	

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
  <script type="text/javascript" src="common/js/pagination.js"></script>
</head>
<body>

<div class="print-visible hidden-md hidden-lg letterheadhgt" >
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print"
	style="padding: 0px;">
<div class="col-lg-12 col-md-12 paddingnil">
<s:form theme="simple" cssClass="form-inline search" action="productReceivedReportProduct">
	<div class="col-lg-12 col-md-12 topback2">
	



	<div class="col-lg-9 col-md-9 col-sm-9 hidden-print" style="padding: 0px;">
		<div class="form-group">
			<span class="text-uppercase"><b>GRN Report</b> &nbsp; - &nbsp;</span>
		</div>
		<div class="form-group" style="width: 25%;">
			<s:select list="vendorList" cssClass="form-control chosen-select" listKey="id" listValue="data" name="vendor_id" headerKey="0" headerValue="Select Supplier"></s:select>
		</div>
		<div class="form-group">
			<s:select cssClass="form-control" list="#{'Purchase Date':'Purchase Date', 'Invoice Date':'Invoice Date'}" theme="simple" name="purchaseinvoice" />  
		</div>
		<div class="form-group" style="width:10%;">
			<s:textfield name="fromdate" id="fromdate" readonly="true" cssClass="form-control" cssStyle="width:100%" placeholder="From Date" />
		</div>
		<div class="form-group" style="width:10%;">
			<s:textfield name="todate" id="todate" readonly="true" cssClass="form-control" cssStyle="width:100%" placeholder="To Date" />
		</div>
		<div class="form-group">
			<%-- <s:select  cssClass="form-control"  list="#{'0':'Location','32':'CDS','36':'CMS'}" name="location_filter"/> --%>
			<s:select list="warehouseList" id="warehouse_id" name="location_filter" listKey="id" listValue="name" headerKey="0" headerValue="Select Store" cssClass="form-control chosen-select"></s:select>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary">Go</button>
		</div>
	   <div class="form-group">
			<button type="button" onclick="printReport()" class="btn btn-primary">Download xls</button>
		</div>
		 <div class="form-group">
			<button type="button" onclick="printpage()" class="btn btn-warning">Print</button>
		</div>
		
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 text-right hidden-print">
	<div class="form-inline">
		<span style="font-size: 15px;color: green;">[Total GRN :<s:property value="totalgrn"/>] &nbsp; &nbsp;</span> &nbsp; &nbsp;
		<span style="float: right;font-size: 15px;color: green;">Total :<s:property value="total_amount"/></span>
		<span style="float: right;font-size: 15px;color: green;">Sub-Total :<s:property value="totalsubtotal"/></span>
		<span style="float: right;font-size: 15px;color: green;">Vat Total :<s:property value="totalvat"/></span>
	</div>
	</div>

	</div>
</s:form></div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
<div id="page_printer">
<h4 class="hidden-lg hidden-md hidden-sm hidden-xs visible-print"
	style="text-align: center; border-bottom: 4px double #ddd; line-height: 30px;"><b>GRN Report</b></h4>



			<%
				int i = 0;
			%>
	<table class="table table-bordered table-striped" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
		<thead>
			<tr class="tableback">
				<th style="width: 5% !important;">Sr.No</th>
				<th style="width: 23% !important;">Supplier Name</th>
				<th style="width: 7% !important;;">Purchase Date</th>
				<th style="width: 9% !important;;">Invoice No</th>
				<th style="width: 7% !important;;">Invoice Date</th>
				<th style="width: 7% !important;;">Location</th>
				<th style="width: 6% !important;;text-align: right">Sub-Total</th>
				<th style="width: 5% !important;;text-align: right">CGST</th>
				<th style="width: 5% !important;;text-align: right">SGST</th>
				<th style="width: 5% !important;;text-align: right">IGST</th>
				<th style="width: 5% !important;;text-align: right">NET Amt</th>
			</tr>
			<tr></tr>
		</thead>
		<tbody>
		    <% int j=0; %>
			<s:iterator value="productWiseReport">
						<tr>
							<td><%=(++j)%></td>
							<td><s:property value="vendor" /></td>
							<td><s:property value="date" /></td>
							<td><i title="View Details" class="fa fa-arrow-down" style="cursor: pointer;" onclick="showHideDetails(<s:property value="id"/>)"></i><a href="#" onclick="openPopup('grnprintProcurement?id=<s:property value="id"/>')" > <s:property value="voucherno" /></a></td>
							<td><s:property value="voucherdate" /></td>
							<td><s:property value="location" /></td>
							<td class="text-right"><s:property value="subTotal" /></td>
							<td class="text-right"><s:property value="cgst" /></td>
							<td class="text-right"><s:property value="sgst" /></td>
							<td class="text-right"><s:property value="igst" /></td>
							<td class="text-right"><s:property value="totalamt" /></td>
						</tr>
						<%-- <tr id="data<s:property value="id"/>" style="display: none">
							<td colspan="14">
								<table class="table table-hover table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>Sr.No</th>
											<th>Product Name</th>
											<th>Batch No</th>
											<th>Expiry Date</th>
											<th>MRP</th>
											<th>Rate</th>
											<th>Qty</th>
											<th>Pack</th>
											<th>GST</th>
											<th>CGST</th>
											<th>SGST</th>
											<th>Amount</th>
										</tr>
									</thead>
									<tbody> 
									       <% i=0; %>
									       <s:iterator value="innerProductList">
											 <tr>
												<td><%=++i %></td>
												<td><s:property value="product_name"/></td>
												<td><s:property value="batch_no"/></td>
												<td><s:property value="expiry_date"/></td>
												<td><s:property value="mrp"/></td>
												<td><s:property value="purchase_price"/></td>
												<td><s:property value="qty"/></td>
												<td><s:property value="pack"/></td>
												<td><s:property value="vat"/>%</td>
												<td><s:property value="cgst"/></td>
												<td><s:property value="cgst"/></td>
												<td><s:property value="total"/></td>
											 </tr>
										  </s:iterator>
									</tbody>
								</table>
							</td>
						</tr> --%>
			</s:iterator>
		</tbody>
	</table>
</div>

    

</div>
<br>
<s:form action="productReceivedReportProduct" name="paginationForm" id="paginationForm" theme="simple">
							     <s:hidden name="searchtext"></s:hidden>
							     <s:hidden name="vendor_id"></s:hidden>
							     <s:hidden name="fromdate"></s:hidden>
							     <s:hidden name="todate"></s:hidden>
							     <s:hidden name="location_filter"></s:hidden>
							     <s:hidden name="purchaseinvoice"></s:hidden>
							     	<div class="col-lg-12 hidden-print" style="margin-top: 15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" />
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
	</s:form> 
<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/chosen_v1.1.0/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
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
	<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>
<%-- <script>
     $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'colvis' ]
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>



<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>  --%>
</body>
</html>