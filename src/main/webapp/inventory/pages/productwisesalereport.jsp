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
    <script type="text/javascript" src="thirdParties/js/tpcharges.js"></script>
    
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
						name: "Product Wise Sale Report",
						filename: "productwisesalereport",
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
									<h4 class="text-uppercase"><b style="font-weight: 900;">PRODUCT WISE SALE REPORT</b></h4>
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
		 <s:form theme="simple" cssClass="form-inline search" action="productwisereportProduct">
			<%-- <div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0px;">
			   	   
			   	<s:select list="productList" cssClass="form-control chosen-select" listKey="id" listValue="product_name" name="product_name" headerKey="0" headerValue="All" ></s:select> 
				<s:textfield  name="product_name"  cssClass="form-control"  placeholder="Search By Product Name "  />
			</div> --%>
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
				<div class="form-inline">
					<div class="form-group hidden">
                   		<span class="text-uppercase"><b style="font-weight: 900;">Product Wise Sale Report</b> &nbsp; - &nbsp;</span>
                    </div>
					<div class="form-group">
						<s:textfield  name="product_name"  cssClass="form-control"  placeholder="Search By Product Name "  />
					</div>
					<div class="form-group">
				    	<s:textfield name="fromdate" id="fromdate" readonly="true" cssClass="form-control"  placeholder="From Date" />
				    </div>
				    <div class="form-group">
				    	<s:textfield name="todate"  id="todate" readonly="true" cssClass="form-control"  placeholder="To Date" />
					</div>
					<div class="form-group">
						<s:select list="reportlocationList" theme="simple" name="reportlocation"  cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
					     	</s:select> 
					</div>
					<div class="form-group">
  							<s:select  cssClass="form-control chosen-select" list="#{'0':'Product Type', 'Regular':'Regular','H1':'H1','Narcotics':'Narcotics','High Risk Medicine':'High Risk Medicine','Vaccination':'Vaccination'}" name="product_type" />
					</div>
					 <div class="form-group">
										  <s:select list="#{'0':'All','2':'Self','1':'Third Party','3':'self free'}"  onchange="gettplist(this.value)"  name="payee" id="payee" cssClass="form-control"/>
										  </div>
										  <div class="form-group">
										  <div id="dstp">
										  <s:select list="ThirdPartyTypeList" listKey="id" listValue="companyName" id="tpid" headerKey="0" headerValue="Select Payer" name="tpid" title="select Third Party"
										     cssClass="form-control"   > </s:select>
										</div>
										</div>
					<div class="form-group">
						  <button type="submit" class="btn btn-primary">Go</button>
						  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
						  <!-- <button type="button" class="btn btn-warning pull-right" onclick="printDiv('page_printer');" style="margin-right: 5px;">Print</button>
						   --><!-- <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a> -->
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
		<center><h3>Product Wise Sale Report </h3> </center>	
	</div>
	<table class="table table-bordered xlstable" cellspacing="0" width="100%"  style="margin-bottom: 0px;">
          <thead>
     <tr class="loc">
     				<td style="width: 3%;">Sr.No</td>
              		<td style="width: 10%;">Product Name</td>
              		<td style="width: 7%;">Generic Name</td>
              		<td style="width: 10%;">Supplier Name</td>
                  	<td style="width: 6%;">Date</td>
                  	<td style="width: 4%;">Bill No.</td>
                  	<td style="width: 2%;">Sale</td>
                  	<td style="width: 2%;">Return</td>
                  	<td style="width: 1%;text-align:right;">MRP</td>
					<td style="width: 5%;text-align:right">Sale Price</td>
					<td style="width: 3%;text-align:right;">Rate</td>
					<td style="width: 3%;text-align:right;">Margin</td>
					<td style="width: 3%;text-align:right;">Total</td>
                  	<td style="width: 5%;">Batch No</td>
                  	<td style="width: 6%;">Expiry</td>
                  	<td style="width: 2%;">GST</td>
                    <td style="width: 3%;">CGST</td>
                    <td style="width: 3%;">SGST</td>
                    <td style="width: 4%;">Discount</td>
                  	<td style="width: 7%;">User Name</td>
                  	<td style="width: 8%;">Patient Name</td>
                  	<td style="width: 8%;">Uhid</td>
                  	<td style="width: 8%;">Location</td>
                  	<td style="width: 8%;">Doctor Name</td>
                  	<th style="width: 6%;">Payer Type</th>
              </tr>
          </thead>
                              
            <tbody>
            	<%int i=0; %>
                <s:iterator value="itemWiseListReport">
                <tr>
                	<td><%=(++i) %></td>
                	<td><s:property value="product_name"/></td>
                    <td><s:property value="genericname"/></td>
                    <td><s:property value="vendor"/></td>
                    <td><s:property value="date"/></td>
                    <td><s:property value="billno"/></td>
                    <td><s:property value="qty"/></td>
                    <td><s:property value="returnQty"/></td>
                    <td style="width: 1%;text-align:right;" class="text-right">Rs.<s:property value="new_mrp"/></td>
					<td style="width: 5%;text-align:right" class="text-right">Rs.<s:property value="mrp"/></td>
					<td style="width: 3%;text-align:right;" class="text-right">Rs.<s:property value="purchase_price"/></td>
					<td style="width: 3%;text-align:right;" class="text-right">Rs.<s:property value="margin"/></td>
					<td style="width: 3%;text-align:right;" class="text-right">Rs.<s:property value="totalAmt"/></td>
                    <td><s:property value="batch_no"/></td>
                    <td><s:property value="expiry_date"/></td>
                    <td><s:property value="vat"/>%</td>
                    <td><s:property value="cgst"/></td>
                    <td><s:property value="sgst"/></td>
                    <td><s:property value="discount"/></td>
                    <td><s:property value="fullname"/></td>
                    <td><s:property value="clientname"/></td>
                    <td><s:property value="Abrivationid"/></td>
                    <td><s:property value="locationName"/></td>
                    <td><s:property value="doctor"/></td>
                    <td><s:property value="Third_party_name" /></td>
                </tr>
                </s:iterator>
            </tbody>
            <tr style="background-color: gainsboro;color: black;">
            
              <td></td>
             <td>Total</td>
             <td></td>
             <td></td>
             <td></td>
             <td></td>
             <td><s:property value="totalqty"/></td>
            	<td><s:property value="retrunqtycount"/></td>
             <td></td>
            	<td></td>
             <td></td>
             <td></td>
             <td></td>
             <td></td>
             <td></td>
             <td></td>
               <td></td>
             <td></td>
             <td></td>
             <td></td>
             	<td></td>
					                                <td></td>
					                                <td></td>
            </tr>
        </table>
    </div>



</div>	
								
<s:form action="productwisereportProduct" name="paginationForm" id="paginationForm" theme="simple">
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