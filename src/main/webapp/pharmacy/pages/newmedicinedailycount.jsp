<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js">
	
</script>

<script>
	function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "report",
			filename : "productcount",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}

	$(document).ready(function() {

		$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		});
		
		document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false); 
	});
</script>
</style>

 <style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style>
<div class="">
	<div class="row details">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

			<h4>Daily Product Count Report</h4>

		</div>

	</div>
	<%-- <div class="hidden-print">
		<ul class="breadcrumb">
			&nbsp;
			<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
			<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
				<%if(breadcrumbs.isIscurrent()){ %>
					<li><%=breadcrumbs.getShowingname()%></li>
				<%}else{ %>
					<%if(breadcrumbs.getOn()){ %>
						<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
					<%}else{ %>
						<li><%=breadcrumbs.getName()%></li>
					<%} %>
				<%} %>
				
			<%} %>
		</ul>
	</div> --%>
	<div class="">
		<s:form action="newmedicinedailycountPharmacy" theme="simple"
			id="invoicerportfrm">
			<s:hidden name="order" id="order" />
			<s:hidden name="orderby" id="orderby" />
			<div class="col-lg-12 col-md-12 topback2 hidden-print">

				<div class="form-inline">





					<div class="form-group" style="width: 10%;">
						<s:textfield readonly="true" name="fromdate" id="fromDate"
							cssClass="form-control" theme="simple" style="width:100%;"
							placeholder="from date"></s:textfield>
					</div>
					<div class="form-group" style="width: 10%;">
						<s:textfield readonly="true" name="todate" id="toDate"
							cssClass="form-control" theme="simple" style="width:100%;"
							placeholder="to date"></s:textfield>
					</div>
					
					<div class="form-group">
						<s:select list="locationListPharmacy" name="location"
							cssClass="chosen-select" listKey="id" listValue="name"
							headerKey="" headerValue="All Loactions"></s:select>
					</div>
					<div class="form-group">
						<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
						<a type="button" class="btn btn-primary" title="Print"
							onclick="printpage()"><i class="fa fa-print"></i></a> <a
							type="button" id="btnxls" title="Save As XLS"
							onclick="printExcel()" class="btn btn-primary"><i
							class="fa fa-file-excel-o"></i></a>
					</div>


				</div>
			</div>
		</s:form>
	</div>
	<div class="">
		<table class="my-table xlstable" id="example" style="width: 100%" >
			<thead>
			<tr>
				<th>Sr.</th>
				<th>Product</th>
				<th>Batch No.</th>
				<th>Expiry Date</th>
				<th>MRP</th>
				<th>Purchase Price</th>
				<th>Sale Price</th>
				<th>Sale Qty</th>
				<th>Total Amount</th>
				<th>Return Qty</th>
				<th>Stock Qty</th>
			</tr>
			</thead>
			<%
				int i = 0;
			%>
			<s:iterator value="doctorreportList">
				<tr>
					<td><%=++i%></td>


					<td><s:property value="productname" /></td>
					<td><s:property value="batch_no" /></td>

					<td><s:property value="date" /></td>

					<td><s:property value="mrp" /></td>
					<td><s:property value="purchase_price" /></td>
					<td><s:property value="sale_price" /></td>
					<td><s:property value="count" /></td>
					<td><s:property value="totalid" /></td>
					<td><s:property value="returnqty" /></td>
					<td><s:property value="check_stock_available" /></td>
				</tr>
			</s:iterator>
		</table>
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
			width : "95%"
		}
	}
	for ( var selector in config) {
		$(selector).chosen(config[selector]);
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
	
<script type="text/javascript"
	src="pharmacy/searchexport/buttons.html5.js"></script>
<script type="text/javascript"
	src="pharmacy/searchexport/buttons.colVis.js"></script>


