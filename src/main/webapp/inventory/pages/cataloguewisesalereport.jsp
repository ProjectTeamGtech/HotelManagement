<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
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
<style>
	.loc{
		background-color: #6699cc;
		color: white;
	}
	@media print {
		.loc{
			background-color: #6699cc  !important;
			color: white;
		}
	}
</style>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/tablesortnew/jquery.dataTables.js"></script>
 <script type="text/javascript" src="common/js/pagination.js"></script>
    <script type="text/javascript" src="common/tablesortnew/dataTables.bootstrap.js"></script>
    
     <script>
	  $(document).ready(function() {
	      $('#tablesort').DataTable();
	  });
	 </script>
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
  
    function printExcel() {

	       $(".xlstable").table2excel({
						exclude: ".noExl",
						name: "Catalogue Wise Sale Report",
						filename: "cataloguewisesalereport",
						fileext: ".xls",
						exclude_img: true,
						exclude_links: true,
						exclude_inputs: true
					});
	   }
</SCRIPT>
</head>
<body>
<div class="row details mainheader hidden-print">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<h4 class="text-uppercase"><b style="font-weight: 900;">Catalogue Wise Sale Report</b></h4>
	</div>
</div>

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

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding: 0px;">
									
		<div class="col-lg-12 col-md-12 paddingnil">
		<div class="col-lg-12 col-md-12 topback2">
		 <s:form theme="simple" cssClass="form-inline search" action="cataloguewisesalereportProduct">
			<%-- <div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0px;">
			   	   
			   	<s:select list="productList" cssClass="form-control chosen-select" listKey="id" listValue="product_name" name="product_name" headerKey="0" headerValue="All" ></s:select> 
				<s:textfield  name="product_name"  cssClass="form-control"  placeholder="Search By Product Name "  />
			</div> --%>
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
				<div class="form-inline">
					<div class="form-group hidden">
	                           		<span class="text-uppercase"><b>Catalogue Wise Sale Report</b>  &nbsp; - &nbsp; </span>
	                </div>
					<div class="form-group">
						<s:textfield  name="product_name"  cssClass="form-control"  placeholder="Search By Product Name "  />
					</div>
					<div class="form-group">
				    	<s:textfield name="fromdate" id="fromdate" cssClass="form-control" readonly="true"  placeholder="From Date" />
				    </div>
				    <div class="form-group">
				    	<s:textfield name="todate"  id="todate" cssClass="form-control" readonly="true"  placeholder="To Date" />
					</div>
					<div class="form-group">
						<s:select list="reportlocationList" theme="simple" name="reportlocation"  cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
					     	</s:select> 
					</div>
					<div class="form-group">
  							<s:select  cssClass="form-control chosen-select" list="#{'0':'Product Type', 'Regular':'Regular','H1':'H1','Narcotics':'Narcotics','High Risk Medicine':'High Risk Medicine','Vaccination':'Vaccination'}" name="product_type" />
					</div>
					<div class="form-group">
						  <button type="submit" class="btn btn-primary">Go</button>
						  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
						  <!-- <button type="button" class="btn btn-warning pull-right" onclick="printDiv('page_printer');" style="margin-right: 5px;">Print</button> -->
						  <!-- <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a> -->
						  <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
						   <a href="#" onclick="printpage();" class="btn btn-primary hidden-print"><i class="fa fa-print"></i></a>
					</div>
				</div>
			</div>
		</s:form>	

		</div>
	</div>
</div>
<div id="page_printer">


<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-sm hidden-md visible-print" style="padding: 0px;">
		<center><h3>Catalogue Wise Sale Report </h3> </center>	
	</div>
	<table class="table table-bordered xlstable" cellspacing="0" width="100%"  style="margin-bottom: 0px;">
          <thead>
     <tr class="loc">
     				<td style="width: 3%;">Sr.No</td>
     				<td style="width: 6%;">From Date</td>
     				<td style="width: 6%;">To Date</td>
              		<td style="width: 10%;">Product Name</td>
              		<td style="width: 6%;">Product Type</td>
              		<td style="width: 6%;">HSN</td>
              		<td style="width: 6%;">Pack</td>
              		<td style="width: 6%;">MRP</td>
              		<td style="width: 6%;">Rate</td>
              		<td style="width: 6%;">Sale Price</td>
              		<td style="width: 6%;">GST</td>
              		<td style="width: 6%;">Sale Qty</td>
              		<td style="width: 6%;">Return Qty</td>
              		<td style="width: 6%;">Total Sale Amount</td>
              		<td style="width: 6%;">Total Return Amount</td>
              </tr>
          </thead>
                              
            <tbody>
            	<%int i=0; %>
                <s:iterator value="itemWiseListReport">
                <tr>
                	<td><%=(++i) %></td>
                	<td><s:property value="fromdate"/></td>
                    <td><s:property value="todate"/></td>
                    <td><s:property value="product_name"/></td>
                    <td><s:property value="prodtype"/></td>
                    <td><s:property value="hsnno"/></td>
                    <td><s:property value="pack"/></td>
                  	<td>Rs.<s:property value="mrp"/></td>
					<td>Rs.<s:property value="purchase_price"/></td>
					<td>Rs.<s:property value="sale_price"/></td>
                    <td><s:property value="vat"/>%</td>
                    <td><s:property value="saleqty"/></td>
                    <td><s:property value="returnqty"/></td>
                    <td><s:property value="salevalue"/></td>
                    <td><s:property value="salevaluation"/></td>
                    
                </tr>
                </s:iterator>
            </tbody>
          
        </table>
    </div>



</div>	
								
<s:form action="cataloguewisesalereportProduct" name="paginationForm" id="paginationForm" theme="simple">
        <s:hidden name="fromdate"></s:hidden>
     <s:hidden name="todate"></s:hidden>
     <s:hidden name="reportlocation"></s:hidden>
     <s:hidden name="product_name"></s:hidden>
       <s:hidden name="product_type"></s:hidden>
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" />
				Records
			</label>
		</div>
		<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
	</div>
</s:form> 

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