<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="common/tablesortnew/dataTables.bootstrap.css" />
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
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

<script type="text/javascript" src="common/tablesortnew/jquery.dataTables.js"></script>
 <script type="text/javascript" src="common/js/pagination.js"></script>
    <script type="text/javascript" src="common/tablesortnew/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
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
  
	  
	  function printReport() {
			
		  $("#page_printer").table2excel({
			exclude: ".noExl",
			name: "Item Wise Sale Report",
			filename: "Item_Wise_Sale_Report",
			fileext: ".xls",
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});          
   }
	  
</SCRIPT>
<script type="text/javascript">
	function submitform(){
		$('#dashboardloaderPopup').modal( "show" );
	}
</script>
</head>
<body>
				<div class="row details mainheader hidden-print">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Medicine WISE SALE REPORT</h4>
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
										 <s:form theme="simple" cssClass="form-inline search" action="medicinesalePharmacy">
											<%-- <div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0px;">
											   	<s:select list="productList" cssClass="form-control chosen-select" listKey="id" listValue="product_name" name="product_name" headerKey="0" headerValue="All" ></s:select> 
												<s:textfield  name="product_name"  cssClass="form-control"  placeholder="Search By Product Name "  />
											</div> --%>
											<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
												<div class="form-inline">
												<%-- <div class="form-group">
							                           		<span class="text-uppercase"><b style="font-weight: 900;">Item Wise Sale Report</b> &nbsp; - &nbsp;</span>
							                           </div> --%>
												 <%-- <div class="form-group">
												
												    	<s:textfield  name="product_name"  cssClass="form-control"  placeholder="Search By Product Name "  />
												    </div> --%>
													<div class="form-group">
												    	<s:textfield name="fromdate" readonly="true" id="fromdate" cssClass="form-control"  placeholder="From Date" />
												    </div>
												    <div class="form-group">
												    	<s:textfield name="todate" readonly="true"  id="todate" cssClass="form-control"  placeholder="To Date" />
													</div>
													 <div class="form-group">
										                <s:select list="#{'0':'All','2':'Self','1':'Third Party'}"  onchange="gettplist(this.value)"  name="payee" id="payee" cssClass="form-control"/>
										             </div>
										             <div class="form-group">
										               <div id="dstp">
										                <s:select list="ThirdPartyTypeList" listKey="id" listValue="companyName" id="tpid" headerKey="0" headerValue="Select Payer" name="tpid" title="select Third Party"
										                     cssClass="form-control"   > </s:select>
										                </div>
										             </div>
													<%-- <div class="form-group">
														<s:select list="reportlocationList" theme="simple" name="reportlocation"  cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
													     	</s:select> 
													</div>  --%>
													<%-- <div class="form-group">
								  							<s:select  cssClass="form-control chosen-select" list="#{'0':'Product Type', 'Regular':'Regular','H1':'H1','Narcotics':'Narcotics','High Risk Medicine':'High Risk Medicine','Vaccination':'Vaccination'}" name="product_type" />
													</div> --%>
													<div class="form-group">
														  <button type="submit" onclick="submitform()" class="btn btn-primary">Go</button>
														  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
														  <a type="button" id="btnxls" title="Save As XLS" onclick="printReport()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
														 <!--  <button type="button" class="btn btn-warning pull-right" onclick="printDiv('page_printer');" style="margin-left: 5px;"><i class="fa fa-print"></i></button> -->
														  <!-- <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a> -->
														  &nbsp;<a href="#" onclick="printpage();" class="btn btn-primary hidden-print"><i class="fa fa-print"></i></a>
													</div>
												</div>
											</div>
										</s:form>	

										</div>
										</div>
								</div>
								<div id="page_printer">
								<h5 class="hidden-lg hidden-md visible-print"><span class="text-uppercase"><b>Item Wise Sale Report</b> &nbsp; -  &nbsp; Fromdate:&nbsp;<s:property value="fromdate"/> &nbsp; - &nbsp;Todate:&nbsp;<s:property value="todate"/></span></h5>
								
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
											<table>
												<tr>
												</tr>
											</table>
											<%-- <h4 style="color: chocolate;"><b><s:property value="product_name"/> <small>(<s:property value="genericname"/>)</small></b><span class="hidden" style="color: #555;">Opening Stock: <s:property value="stock"/></span> &nbsp; | &nbsp; <span style="color: #555;">Supplier Name: <s:property value="vendor"/></span></h4> --%>
											<table class="table table-bordered xlstable" cellspacing="0" width="100%"  style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="loc">
					                                        <td hidden style="width: 6%;">Date</td>
					                                        <td style="width: 4%;">total sale</td>
					                                        <td style="width: 1%;">Medicine Nmae</td>
					                                        <td style="width: 3%;">Third Party Name</td>
					                                        

					                                       
					                                        
					                               
					                                    </tr>
					                                </thead>
					                                 
					                                <tbody>
					                                    <s:iterator value="MedicinesaleList">
					                                    <tr>
					                                        <td hidden style="width: 6%;"><s:property value="Commencing"/></td>
					                                        <td style="width: 4%;"><s:property value="Totalqty"/></td>
					                                        <td style="width: 3%;"><s:property value="Product_name"/></td>
					                                        <td style="width: 1%;"><s:property value="Tpname"/></td>
					                                        
					                                        
					                                    </tr>
					                                   
					                                    
					                                    </s:iterator>
					                                </tbody>
					                                
					                         
					                                </tr>
					                            </table>
					                            </div>
								
								
								
								</div>	
								
								<s:form action="itemwisereportProduct" name="paginationForm" id="paginationForm" theme="simple">
							         <s:hidden name="fromdate"></s:hidden>
								     <s:hidden name="todate"></s:hidden>
								     <s:hidden name="reportlocation"></s:hidden>
								     <s:hidden name="product_name"></s:hidden>
								      <s:hidden name="product_type"></s:hidden>
										<div class="col-lg-12 col-md-12 col-xs-12 hidden-print" style="padding:0px;">
										<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
											Total:<label class="text-info"><s:property value="totalRecords" />
												Records
											</label>
										</div>
										<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
									</div>
								</s:form> 

<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
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
	
	<%-- <script>
	function printExcel() {

	       $(".xlstable").table2excel({
						exclude: ".noExl",
						name: "ItemWiseSaleReport",
						filename: "ItemWiseSaleReport",
						fileext: ".xls",
						exclude_img: true,
						exclude_links: true,
						exclude_inputs: true
					});
	   }
	</script> --%>
	
</body>
</html>