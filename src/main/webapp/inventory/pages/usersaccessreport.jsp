<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" href="pharmacy/searchexport/dataTables.bootstrap.css">
		<link rel="stylesheet" href="pharmacy/searchexport/buttons.bootstrap.css">
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
.marboset{
	
}
.setborba {
    background-color: #efefef !important;
    padding-top: 5px !important;
}
 b, strong {
    font-weight: 900;
}
.topback2 {
    background-color: #f5f5f5;
    padding: 10px 0px 10px 0px;
}
</style>

</head>
<body>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										 <form theme="simple" cssClass="form-inline search" action="">
											
											<div class="col-lg-12 col-md-12 col-sm-12">
											<span class="text-uppercase"><b>Users Access Report</b></span>
										    <input name="fromdate" id="fromdate" class="form-control hidden" style="width:10%" placeholder="From Date" />
										    <input name="todate"  id="todate" class="form-control hidden" style="width:10%" placeholder="To Date" />
											<%-- <select class="form-control">
											  <option>Report</option>
											  <option>Daily</option>
											  <option>Weekly</option>
											  <option>Monthly</option>
											</select> --%>
										  <button type="submit" class="btn btn-primary hidden">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary hidden" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <button type="button" class="btn btn-warning pull-right hidden" onclick="printDiv('page_printer');" style="margin-right: 5px;">Print</button>
										  <a href="#" onclick="#" class="btn btn-primary hidden ">Export to excel</a>
										
											</div>
										</form>	

										</div>
										</div>
								</div>
								
					<div id="page_printer">
						<div class="row hidden">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden-lg hidden-md visible-print" style="border-bottom: 1px solid #ddd;" id="letterHead">
									<h3 style="margin: 0px;">SHREE NARAYANA HOSPITAL</h3>
									<h5 style="margin: 0px;">(A Unit of Hospital Chhattisgarh Pvt.Ltd)</h5>
									<h5 style="margin: 0px;">Near Ganj Mandi, Behind Sector - 5, Devendra Nagar, Pandri, Raipur,</h5>
									<h5 style="margin: 0px;">Phone:0771-3001234,35,36</h5>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
				<h3 class="text-uppercase" style="margin: 5px 0px 0px 0px;"><b>Bin Card</b></h3>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;margin-bottom: 15px;" id="">
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset"><b>Location :</b>&nbsp;<span>SNH - MEDICAL STORE</span></p>
					<p class="marboset"><b>Item Name :</b>&nbsp;<span>ACILLOC 150MG</span></p>
					<p class="marboset"><b>Form Date :</b>&nbsp;<span>01-06-2017</span></p>
					<p class="marboset"><b>Unit :</b>&nbsp;<span>NUMBERS</span></p>
					
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<p class="marboset"><b>Item Group :</b>&nbsp;<span>MEDICAL GROUP -SNH</span></p>
					<p class="marboset"><b>Item Code :</b>&nbsp;<span>MGP-A-022</span></p>
					<p class="marboset"><b>To Date :</b>&nbsp;<span>18-08-2017</span></p>
				</div>
			</div>
							        	
	</div>
</div>
<div class="">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
		<table class="table table-bordered table-striped" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
			<thead>
				<tr>
					<!-- <th>Sr No</th> -->
					<th>User Name</th>
					<th>Location</th>
					<th>Access List</th>
					<th>Status</th>
					<th>Access Given Date Time</th>
					<th>Access Given By</th>
					
				</tr>
			</thead>
			<tbody>
				<s:iterator value="usersaccess">
				<tr>
				    <%-- <td><s:property value="srno"/></td> --%>
					<td><s:property value="firstname"/></td>
					<td><s:property value="apmlocation"/></td>
					<td><s:property value="accessList"/></td>
					
					<s:if test="accessStatus=='Disable'">
					  <td style="color:red;"><s:property value="accessStatus"/></td>
					</s:if>
					<s:else>
					   <td style="color:green;"><s:property value="accessStatus"/></td>
					</s:else>
					
					<td><s:property value="accessListDT"/></td>
					<td><s:property value="accessListBy"/></td>
				</tr>
					</s:iterator>	
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
<script>
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

</body>
</html>