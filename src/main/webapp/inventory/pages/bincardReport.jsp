<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	p {
    margin: 0 0 1.5px;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
    line-height: 40px;
}
  
.topback2 {
    background-color: #f5f5f5;
    padding: 10px 10px 10px 0px;
}
</style>
 <SCRIPT type="text/javascript">

 $(document).ready(function() {
              
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

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding: 0px;">
									
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										
											<div class="col-md-12">
					                           <form theme="simple" class="form-inline search" action="bincardreportProduct">
						                            <div class="form-group">
							                           		<span class="text-uppercase"><b style="font-weight: 900;">BIN Card Report</b> &nbsp; - &nbsp;</span>
							                           </div>
												  	<div class="form-group" style="width:20%">
												  		<s:select list="productList" name="catalogueid" cssStyle="width:0px !important" cssClass="form-control chosen-select" listKey="id" listValue="product_name" headerKey="0" headerValue="Select Product" ></s:select>
												  	</div>
												  	<div class="form-group" style="width:10%">
												  		 <s:textfield name="fromdate" id="fromdate" readonly="true" cssClass="form-control" cssStyle="width:100%" placeholder="From Date" />
												  	</div>
												  	<div class="form-group" style="width:10%">
												  		 <s:textfield name="todate"  id="todate" readonly="true" cssClass="form-control" cssStyle="width:100%" placeholder="To Date" />
												  	</div>
												  	<div class="form-group" style="width:10%">
															<s:select headerKey="0" headerValue="Select location" cssStyle="width:0px !important" cssClass="form-control chosen-select" list="locationlist" name="location_filter" listKey="id" listValue="name"/>
													</div>
												  	
												  	<div class="form-group" style="width:10%">
															<s:select headerKey="0" headerValue="Select User" cssStyle="width:0px !important" cssClass="form-control chosen-select" list="userlist" name="userwise" listKey="userid" listValue="fullname"/>
													</div>
												  	<div class="form-group" style="width:10%">
												  		<button type="submit" class="btn btn-primary">Go</button>
												  		<a type="button" class="btn btn-primary"  title="Print" onclick="printDiv('page_printer')"><i class="fa fa-print"></i></a>
												  	</div>
												</form>
				                        </div>
				                       <!-- 
				                        <div class="col-md-2 text-right" style="padding:0px;">
				                        	
				                        </div> -->

										</div>
										</div>
								</div>
								
					<div id="page_printer">
						<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<!-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden-lg hidden-md visible-print" style="border-bottom: 1px solid #ddd;" id="letterHead"> -->
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden-lg hidden-md visible-print" style="border-bottom: 1px solid #ddd;" id="letterHead">
									<!-- <h3 style="margin: 0px;">SHREE NARAYANA HOSPITAL</h3>
									<h5 style="margin: 0px;">(A Unit of Hospital Chhattisgarh Pvt.Ltd)</h5>
									<h5 style="margin: 0px;">Near Ganj Mandi, Behind Sector - 5, Devendra Nagar, Pandri, Raipur,</h5>
									<h5 style="margin: 0px;">Phone:0771-3001234,35,36</h5> -->
									<div class="row letterheadhgt" style="height: 135px;">
					<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							<link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden-lg hidden-md visible-print">
				<h3 class="text-uppercase" style="margin: 5px 0px 0px 0px;"><b>Bin Card</b></h3>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;margin-bottom: 15px;margin-top: 5px;" id="">
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset"><b>Location :</b>&nbsp;<span><s:property value="locationName"/></span></p>
					<p class="marboset"><b>Item Name :</b>&nbsp;<span><s:property value="product_name"/></span></p>
					<p class="marboset"><b>Form Date :</b>&nbsp;<span><s:property value="fromdate"/></span></p>
					<p class="marboset"><b>Unit :</b>&nbsp;<span>NUMBERS</span></p>
					
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<p class="marboset"><b>Item Group :</b>&nbsp;<span>MEDICAL GROUP</span></p>
					<p class="marboset"><b>Item Code :</b>&nbsp;<span>-</span></p>
					<p class="marboset"><b>To Date :</b>&nbsp;<span><s:property value="todate"/></span></p>
				</div>
			</div>
							        	
	</div>
</div>
<div class="">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
		<table class="table table-responsive table-bordered" id="example">
			<thead>
				<tr>
					<th>Transaction Date</th>
					<th>Bill No/Indent No</th>
					<!-- <th>Bill No</th> -->
					<th>Transaction Type</th>
					<th>Inventory head</th>
					<th>Handover To</th>
					<th>Receipt Qty</th>
					<th>Issue Qty</th>
					<th>Balance Qty</th>
				</tr>
			</thead>
				
			<tfoot style="background-color: rgba(239, 239, 239, 0.48);color: green;">
				<tr>
					<td></td>
					<td></td>
					<!-- <td></td> -->
					<td></td>
					<td>TOTAL</td>
					<td></td>
					<td><s:property value="receiptqty"/></td>
					<td><s:property value="issueqty"/></td>
					<td><s:property value="balance"/></td>
				</tr>
			</tfoot>
			<tbody>
			   <s:iterator value="bincardReportList"> 
				<tr>
					<td><s:property value="date" /></td>
					<td>
					<a href="#" onclick="openPopup('<s:property value='printId' />')">
					<s:property value="docno" />
					</a>
					</td>
					<!-- <td></td> -->
					<td><s:property value="transtype" /></td>
					<td><s:property value="vendor" /></td>
					<td><s:property value="handover_to" /></td>
					<td><s:property value="qty" /></td>
					<td><s:property value="issueqty" /></td>
					<td><s:property value="balqty" /></td>
				</tr>
				</s:iterator>
				<%-- <tr style="background-color: rgba(239, 239, 239, 0.48);color: gray;">
					<td></td>
					<td></td>
					<!-- <td></td> -->
					<td></td>
					<td>Closing</td>
					<td></td>
					<td></td>
					<td></td>
					<td><s:property value="balance"/></td>
				</tr> --%>
			</tbody>
		</table>
	</div>
</div>
					</div>
								
								
								
								
								
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
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
	
<script type="text/javascript"
	src="pharmacy/searchexport/buttons.html5.js"></script>
<script type="text/javascript"
	src="pharmacy/searchexport/buttons.colVis.js"></script> --%>

<script>
    function showhide()
     {
           var div = document.getElementById("newpost");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }
  </script>
</body>
</html>