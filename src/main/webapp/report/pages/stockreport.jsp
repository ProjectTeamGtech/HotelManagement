<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    
    
    <style>
    .ak{
    font-size: 17px !important;
    margin-right: 30px !important;
    }
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
        input[type="checkbox"] {
		    margin: 0px 0 0 !important;
		    margin-top: 1px \9;
		    line-height: normal;
		    /* padding-top: 15px; */
		    position: absolute !important;
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
        .table>tbody>tr>td, .table>tfoot>tr>td {
		    padding: 2px 5px 0px 5px !important;
		}
    </style>


    <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
        .addcatego {
            float: right;
            margin-top: -40px;
            margin-right: 30px;
        }
        .sort{
        width: 15%;padding-top: 2px;
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
    </style>
    
    <SCRIPT type="text/javascript">
    
    			function submitForm(){
    			
    					document.getElementById("myform").submit();
    			}
    		
    </SCRIPT>
    
    <SCRIPT type="text/javascript">

    $(document).ready(function() {
              
         $("#expiry_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
         
         $("#fromdate").datepicker({

 			dateFormat : 'dd-mm-yy',
 			yearRange: yearrange,
 			minDate : '30-12-1880',
 			changeMonth : true,
 			changeYear : true

 		});
		             
    });
  
    function printReport() {
				
				  $("#tablesort").table2excel({
					exclude: ".noExl",
					name: "Stock Status",
					filename: "CatalogueReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }
    
    
    function printProductExcel() {

        $(".xlsproduct").table2excel({
					exclude: ".noExl",
					name: "Product List",
					filename: "ProductList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
    function printStockReportExcel() {

        $(".lokesh").table2excel({
					exclude: ".noExl",
					name: "Stock Status Report",
					filename: "stcokstatusList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
     


</SCRIPT>
    
    
    <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
      <script type="text/javascript" src="common/js/pagination.js"></script>
      <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>




<body id="his" class="appWrapper sidebar-xs-forced">
		 						<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
    
        <section id="">

            <div class="" >
                <div class="">
	                <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
	                	 <div class="form-inline">
	                	 	<div class="form-group">
	                           		<s:if test="isfromcathlab==1">
										<span class="text-uppercase"><b>Cathlab Stock Report</b> &nbsp;  &nbsp;</span>
									</s:if>
									<s:else>
										<span class="text-uppercase"><b>Stock Report</b> &nbsp;  &nbsp;</span>
									</s:else>
	                           </div>
	                	 </div>
	                </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                       <div class="form-inline">
                           <s:form theme="simple" action="stockreportReport">
                           		<s:hidden name="isfromcathlab"></s:hidden>
	                            <div class="form-group">
								  	<s:textfield name="searchtext" placeholder="Search Product/Generic Name" theme="simple" cssClass="form-control"></s:textfield>
								</div>
	                           <div class="form-group" style="width: 80px;">
									<s:textfield id="fromdate" name="fromDate" readonly="true" cssStyle="width: 100%;" cssClass="form-control" placeholder="Date" />
								</div>
                           		<div class="form-group">
									<s:select list="locationlist" headerKey="0" headerValue="Select Location" theme="simple" cssStyle="width: 75%;" name="location" cssClass="form-control chosen-select" listKey="id" listValue="name" >
									</s:select> 
								</div>
								<div class="form-group">
								  	<%-- <s:select  cssClass="form-control chosen-select" list="#{'1':'By Catalogue Wise', '2':'By Product-Batch Wise', '3':'By Product Wise'}" name="report_filter" /> --%>
									<s:select  cssClass="form-control chosen-select" list="#{'1':'By Catalogue Wise', '3':'By Product Wise'}" name="report_filter" />
								</div>
								<div class="form-group hidden" style="width: 121px;">
								  	<s:select cssClass="form-control chosen-select" list="#{'1':'Order by name', '2':'Order by stock', '3':'Order by MRP'}" name="orderby" />
								</div>
								
								<div class="form-group">
								  	<s:select  cssClass="form-control chosen-select" list="#{'2':'With 0 Stock', '1':'Without 0 Stock', '3':'Only 0 Stock'}" name="withstock_filter" />
								</div>
								<div class="form-group">
								  	<%-- <s:select  cssClass="form-control chosen-select" headerKey="0" headerValue="Select Sub Cat" listKey="id" listValue="name" list="subcategoryList"  name="subcat_filter" /> --%>
									<s:select list="categoryList"  name="cat_filter" listKey="id" listValue="name" cssClass="form-control chosen-select" id="categoryid" headerKey="0" headerValue="Select Catagory"/>
								</div>
								<div class="form-group hidden" style="width: 103px;">
								  	<s:select  cssClass="form-control chosen-select" list="#{'':'Select Order','desc':'Descending', 'asc':'Ascending'}" name="order_filter" />
								</div>
								
								<div class="form-group">
								  	<button type="submit" class="btn btn-primary">Go</button>
								</div>
								<div class="form-group">
									<!-- <a href="#" class="btn btn-success"  onclick="printStockReportExcel()" title="Download CSV file" style="line-height: 14px;"> Download Excel</a> -->
									<a type="button" id="btnxls" title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
								</div>
								<div class="form-group">
									<!-- <button type="button" onclick="printDiv('page_printer')" class="btn btn-warning">Print</button> -->
									<a type="button" class="btn btn-primary" title="Print" onclick="printDiv('page_printer')"><i class="fa fa-print"></i></a>
									
								</div>
                           	</s:form>
                           		
                           </div>
                      
                    </div> 
                    <div class="col-lg-12" id="page_printer" style="padding: 0px;">
                    			<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
								<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
													<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
											 <link href="common/css/printpreview.css" rel="stylesheet" />
										<%@ include file="/accounts/pages/letterhead.jsp" %>
										</div>
									</div>
								</div>
								<div class="print-visible hidden-md hidden-lg" >
									<s:if test="isfromcathlab==1">
										<span class="text-uppercase"><b>Cathlab Stock Report</b> &nbsp; - &nbsp;</span>
									</s:if>
									<s:else>
										<span class="text-uppercase"><b>Stock Report</b> &nbsp; - &nbsp;</span>
									</s:else>
									
								</div>
								<div class="text-right">
									 <s:if test="isfromcathlab==1">
									 	<span class="ak">Total MRP :<s:property value="totalmrp"/></span>
									 	<span class="ak">Total Purchase Price :<s:property value="totalpurchaseprice"/></span>
									 	<span class="ak">Total Sale Price :<s:property value="totalsaleprice"/></span>
									 	<span class="ak">Total Quantity :<s:property value="totalqty"/></span>
										<span class="ak">Total :<s:property value="ttl"/></span>
									</s:if>
									<s:else>
										<span class="ak">Total MRP :<s:property value="totalmrp"/></span>
									 	<span class="ak">Total Purchase Price :<s:property value="totalpurchaseprice"/></span>
									 	<span class="ak">Total Sale Price :<s:property value="totalsaleprice"/></span>
									 	<span class="ak">Total Quantity :<s:property value="totalqty"/></span>
										<span class="ak">Total :<s:property value="ttl"/></span>
										<span class="ak">Sale Valuation :<s:property value="totalsalevaluation"/></span>
									</s:else>
								</div>
                        <table class="table my-table xlstable table-striped table-bordered tablestock lokesh" id="tablesort" style="width: 100%;">
                            <s:if test="isfromcathlab==0">
                            	<thead>
                               <tr>
                               		<td class="my-table th" >Sr.No</td>
                                    <td class="my-table th" >ID</td>
                                    <td class="my-table th" >Product Name</td>
                                    <td class="my-table th" >Product code</td>
                                     <td class="my-table th" >Supplier Name</td>
                                    <td class="my-table th" >MFG</td>
                                    <td class="my-table th" >Generic Name</td>
                                    <td class="my-table th" >Catagory</td>
                                    <td class="my-table th" >Sub Catagory</td>
                                    <td class="my-table th" >Pack</td>
                                    <td class="my-table th" >Location</td>
                                    <td class="my-table th" style="text-align:right;">MRP</td>
                                    <td class="my-table th" style="text-align:right;">Purchase Price</td>
                                    <td class="my-table th" style="text-align:right;">Sale Price</td>
                                    <td class="my-table th" >GST</td>
                                    <td class="my-table th" >Expiry Date</td>
                                    <td class="my-table th" >Batch No</td>
                                    <td class="my-table th" style="text-align:right;">Qty</td>
                                    <td class="my-table th" style="text-align:right;">Unit Price</td>
                                    <td class="my-table th" style="text-align:right;">GST AMT</td>
                                    <td class="my-table th" style="text-align:right;">Valuation</td>
                                    <td class="my-table th" style="text-align:right;">Cost Including GST</td>
                                </tr>
                            </thead>
                             <tfoot>
					          	
					          </tfoot>
                            <tbody>
                            <%int i=0; %>
                               <s:iterator value="stocklist">
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++i) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><a href="#" onclick="openBlankPopup('bincardreportnewProduct?catalogueid=<s:property value="catalogueid"/>&isfromstockreport=1')"><s:property value="product_name"/></a></td>
                                    <td><s:property value="pro_code"/></td>
                                    <td><s:property value="vendor"/></td>
                                    <td><s:property value="mfg"/></td>
                                    <td><s:property value="genericname"/></td>
                                    <td><s:property value="category"/></td>
                                    <td><s:property value="subcategory"/></td>
                                    <td><s:property value="pack"/></td>
                                    <td><s:property value="locationName"/></td>
                                    <td class="text-right">Rs.<s:property value="mrp"/></td>
                                    <td class="text-right">Rs.<s:property value="purchase_price"/></td>
                                    <td class="text-right">Rs.<s:property value="sale_price"/></td>
                                    <td><s:property value="vat"/></td>
                                    <td><s:property value="expiry_date"/></td>
                                    <td><s:property value="batch_no"/></td>
                                    <td class="text-right"><s:property value="stock"/></td>
                                    <td class="text-right"><s:property value="unitprice"/></td>
                                    <td style="text-align: right;"><s:property value="vatamt"/></td>
                                    <td style="text-align: right;"><s:property value="valuation"/></td>
                                    <td style="text-align: right;"><s:property value="salevaluation"/></td>
                                </tr>
                                </s:iterator>
                            </tbody>
                            	 <tr style="color: black;">
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
					          		<td style="text-align: right;">Rs.<s:property value="totalmrp"/></td>
					          		<td style="text-align: right;">Rs.<s:property value="totalpurchaseprice"/></td>
					          		<td style="text-align: right;">Rs.<s:property value="totalsaleprice"/></td>
					          		<td></td>
					          		<td></td>
					          		<td style="text-align: right;"><s:property value="totalqty"/></td>
					          		<td></td>
					          		<td></td>
					          		   <td class="text-right"><%-- <s:property value="totalcountpurprice"/> --%></td>
					          		   <td></td>
					          		
					          	</tr>
                            </s:if>
                            <s:else>
                            	<thead>
                               <tr >
                               		<td class="my-table th" style="width: 3%;">Sr.No</td>
                                    <td class="my-table th" style="width: 8%;">Catagory</td>
                                    <td class="my-table th" style="width: 8%;">Sub Catagory</td>
                                    <td class="my-table th" style="width: 22%;">Supplier Name</td>
                                    <td class="my-table th" style="width: 5%;">Product Code</td>
                                    <td class="my-table th" style="width: 22%;">Product Name</td>
                                    <td class="my-table th" style="width: 7%;">MFG</td>
                                    <td class="my-table th" style="width: 5%;">Min Order</td>
                                    <td class="my-table th" style="width: 8%;">Max Order</td>
                                    <td class="my-table th" style="width: 2%;">Pack</td>
                                    <td class="my-table th" style="width: 7%;">GST</td>
                                    <td class="my-table th" style="text-align:right;width: 5%;">MRP</td>
                                    <td class="my-table th" style="text-align:right;width: 8%;">Purchase Price</td>
                                    <td class="my-table th" style="text-align:right;width: 7%;">Sale Price</td>
                                	<td class="my-table th text-right" style="width: 7%;">Qty</td>
                                	<td class="my-table th" style="text-align:right;width: 3%;">Unit Price</td>
                                	<td class="my-table th" style="text-align:right;width: 4%;">Valuation</td>
                                	<td class="my-table th" style="text-align:right;width: 4%;">Cost Including GST</td>
                                </tr>
                            </thead>
                           
                            <tbody>
                            <%int i=0; %>
                               <s:iterator value="stocklist">
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++i) %></td>
                                	<td><s:property value="category"/></td>
                                    <td><s:property value="subcategory"/></td>
                                    <td><s:property value="vendor"/></td>
                                	<td><s:property value="pro_code"/></td>
                                	<td><s:property value="product_name"/></td>
                                    <td><s:property value="mfg"/></td>
                                    <td><s:property value="minorder"/></td>
                                    <td><s:property value="maxorder"/></td>
                                   
                                    <td><s:property value="pack"/></td>
                                     <td><s:property value="vat"/></td>
                                    <td class="text-right"><s:property value="mrp"/></td>
                                    <td class="text-right"><s:property value="purchase_price"/></td>
                                    <td class="text-right"><s:property value="sale_price"/></td>
                                    <td class="text-right"><s:property value="stock"/></td>
                                    <td class="text-right"><s:property value="unitprice"/></td>
                                    <td class="text-right"><s:property value="valuation"/></td>
                                    <td style="text-align: right;"><s:property value="salevaluation"/></td>
                                </tr>
                                </s:iterator>
                            </tbody>
                            </s:else>
                            
                        </table><br/>
                                             
                    </div>
                </div>
            </div>
        </section>
<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>
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
     $(document).ready(function() {
    var table = $('#tablesort').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'colvis' ],
        
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
