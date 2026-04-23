
<%-- <%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src=""></script>

<script src="notification/js/bootstrap-select.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="inventory/js/addproduct.js"></script>
<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style>
.newbtnyes {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #38a538 !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.newbtnno {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #e22626 !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.newbtn {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #15536E !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.btn {
	background-color: #15536E !important;
	border-radius: 0.50rem;
}

.table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
	background-color: #c7e7e8;
}

@media (max-device-width: 480px) {
	.mobile-bottom-padding{
		padding-bottom: 12px;
	}
	.mobile-bottom-margin{
		margin-bottom: 5px;
	}
}
</style>



<script>
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

	});

	function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "PRO Patients",
			filename : "PROPatientsReport",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}
</script>


<%
	LoginInfo loginfo = LoginHelper.getLoginInfo(request);
%>
<div class="reportprint">
	<div class="">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="background-color: #41cfd6;">
				<!-- <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12"
					style="display: -webkit-inline-box; padding: 0px;">
					<h4 style="font-size: 17px; font-weight: 600; color: white">Ambulance Report</h4>
				</div> -->
		
			</div>
		</div>
		<div class="row ">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<div>


					<div class="col-lg-12 col-md-12">
						<s:hidden name="message" id="message"></s:hidden>
						<s:if test="hasActionMessages()">
							<script>
								var msg = " "
										+ document.getElementById('message').value;
								showGrowl('', msg, 'success', 'fa fa-check');
							</script>



						</s:if>
					</div>




					
					<s:form action="approvedlimitqtyProduct">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2  hidden-print"
							style="display: flex; flex-wrap: wrap;z-index: 99">
							<img src="manasmaindashboard/images/Manas_Yuvicare_Logo.svg" style="height: 56px;width: 89px;">

							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
								<label>From Date : </label>

								<s:textfield readonly="true" name="Fromdate" id="fromDate"
									cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
							</div>

							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
								<label>To Date : </label>
								<s:textfield readonly="true" name="Todate" id="toDate"
									cssClass="form-control" theme="simple" placeholder="To date"></s:textfield>

							</div>


							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 ">
								<label>Search by Driver Name : </label>
								<s:textfield theme="simple" name="searchText"
									placeholder="Search By name" cssClass="form-control" />
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label></label><br> <input type="submit" value="Go"
									class="btn btn-primary active" /> <a type="button"
									class="btn btn-primary" title="Print" onclick="printpage()"><i
									class="fa fa-print"></i></a> <a type="button" id="btnxls"
									title="Download Excel" onclick="printExcel()"
									class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
							</div>
							</div>
							</s:form>
							
<div class="row">
<div class="col-lg-12">
<div class="">
<table id="results"
	class="table table-hove table-bordered table-striped table-condensed">
	<thead>
		<tr>
			<th class="text-center">Date</th>
			<th class="text-center">Product Name</th>
			<th class="text-center">Requested Qty</th>
			<th class="text-center">Requested Userid</th>
			<th class="text-center">Status</th>
			
			
		</tr>
	</thead>
	 <tbody>
		<s:if test="Requestlimitqtylist!=null">
			<s:iterator value="Requestlimitqtylist" status="rowstatus">
				<tr>
					<td class="text-center"><s:property value="DateTime" /></td>
					<td class="text-center"><s:property value="Product_name" /></td>
					<td class="text-center"><s:property value="Req_qty" /></td>
					<td class="text-center"><s:property value="Req_userid"/></td>
				    <td class="text-center">
										<s:if test="status==0">
							           <input onclick="approvelimitqty(<s:property value="Indent"/>,<s:property value="Product_id"/>)" type='button' value='Approve' class='btn btn-primary' >
											
										 </s:if> 
										<s:else>
											Approved
										</s:else>
									</td>
				   
				    
					
					
				</tr>
			</s:iterator>
		</s:if>
	</tbody> 
</table>
</div>
</div>
</div>
							</div>
							</div>
							</div>
							</div>
							</div> --%>



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
						  	<s:form theme="simple" cssClass="form-inline search" action="vendorratereportProcurement">
						  		<div class="form-group">
							         <span class="text-uppercase"><b style="font-weight: 900;">Vendor Rate report</b> &nbsp; - &nbsp;</span>
							    </div>
							    <div class="form-group" style="width: 13%;">
									<s:textfield  name="product"  cssClass="form-control"  placeholder="Search By Product Name " />		
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
					                                        <th style="width: 7%;">Product Name </th>
					                                        <th style="width: 7%;">Vendor Name </th>
					                                        <th style="width: 7%;">Price</th>
					                                        <th style="width: 7%;">Date </th>
					                                       
					                                     </tr>
					                                </thead>
					                                <tbody>
					                                    <%int x=0; %>
					                                    <s:iterator value="VendorrateList">
					                                     
					                                     <td><%=(++x) %></td>
					                                    <td style="font-weight: 900;"><s:property value="Product_id"/></td>
					                                    <td></td>
					                                    <td></td>
					                                    <td></td>
					                                    <s:iterator value="Vendorlistbyproduct">
					                                    <tr>
					                                        <td></td>
					                                        <td></td>
					                                        <td style="font-weight: 900;"><s:property value="Vendor_id"/></td>
					                                        <td></td>
					                                        <td></td>
                                                        </tr>
					                                        
					                                  
					                                    <s:iterator value="Ratelistbyvendor">
					                                      <tr>
					                                         <td></td>
					                                         <td></td>
					                                         <td></td>
					                                        <td><s:property value="Purchase_date"/></td>
					                                        <td><s:property value="Date"/></td>
					                                       </tr> 
                                                       </s:iterator>     
					                                  
					                                     
					                                      
					                                 </s:iterator>
					                                 
					                            </s:iterator>  
					                                </tbody> 
					                            </table>
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
	
</body>
</html>
							
							