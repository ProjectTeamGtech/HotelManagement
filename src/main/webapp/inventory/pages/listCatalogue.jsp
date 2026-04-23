<!doctype html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<html class="no-js" lang="">
<!--<![endif]-->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="plugin/monthyear/MonthPicker.min.css" rel="stylesheet" type="text/css" />
    <script src="plugin/monthyear/jquery-ui.min.js"></script>
    <script src="plugin/monthyear/jquery.maskedinput.min.js"></script>
    <script src="plugin/monthyear/MonthPicker.min.js"></script>
    
    
    <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
    <style>
    tbody tr:hover {
    background-color: #e4dcdc !important;          
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
    <style type="text/css">
    	.tooltip {
    position: relative;
    display: inline-block;
    opacity: 1;
    z-index: 0;
    placement: 'right';
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 180px;
    text-align: justify;
    padding: 6px 9px;
    background-color:#555;
    color:#fff;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 1s;
}

.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 10%;
    margin-left: 37px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1 !important;
}
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

    <%int hadeditaccess=0; %>
    <SCRIPT type="text/javascript">
    
    			function submitForm(){
    			
    					document.getElementById("myform").submit();
    			}
    		
    </SCRIPT>
    
    <SCRIPT type="text/javascript">

    window.onload = function(){
    	/* $("#dashboardloaderPopup").modal('show'); */
    	      document.addEventListener("contextmenu", function(e){
    			e.preventDefault();
    			}, false);     
    	
    	if(document.getElementById("categoryid_chosen")){
    		document.getElementById("categoryid_chosen").style ='width: 80%;';
    	} 
    	if(document.getElementById("listProduct_product_type_chosen")){
    		document.getElementById("listProduct_product_type_chosen").style ='width: 80%;';
    	}
    	if(document.getElementById("listProduct_withstock_filter_chosen")){
    		document.getElementById("listProduct_withstock_filter_chosen").style ='width: 80%;';
    	}
    	if(document.getElementById("subcategoryid_chosen")){
    		document.getElementById("subcategoryid_chosen").style ='width: 80%;';
    	}
    	
        /*  $("#expiry_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		}); */
    	<%if(loginInfo.getJobTitle().equals("Admin")){ %>
    		<%hadeditaccess =1;%>
    		 $("#expiry_date").MonthPicker({
    		     UseInputMask: true,
    		     ValidationErrorMessage: 'Invalid Date!'
    		 }); 
    	<%}else{ %>
	    	<s:if test="edit_catalogue==1">
	    	 $("#expiry_date").MonthPicker({
			     UseInputMask: true,
			     ValidationErrorMessage: 'Invalid Date!'
			 }); 
	    		<%hadeditaccess =1;%>
	        </s:if>
	        <s:else>
	        	<%hadeditaccess =0;%>
	        </s:else>
    	<%} %>
    	
		             
    };
  
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

        $(".tablestock").table2excel({
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

<script>
	$(document).ready(function() {

		
		
		$("#issue_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#issue_date1").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
});	
	</SCRIPT>  
    
    <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
      <script type="text/javascript" src="common/js/pagination.js"></script>
      <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
      <script>
			var myWindow;
			function expiryproductwithin31days() {
			     myWindow = window.open("expiryproductpopupProduct", "", "width=800, height=600, addressbar=no,");
			}
			
			function minimumQtyProductList() {
				var test= false;
				if(document.getElementById("location")){
					var loctioid = document.getElementById("location").value;
					if(loctioid==''){
						test= true;
						
					}else if(loctioid=='0'){
						test= true;
						
					}
				}
				if(test){
					jAlert('error', "Please select location from top!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
				}else{
					myWindow = window.open("minimumqtypopupProduct", "", "width=800, height=600, addressbar=no,");
				}
			     
			}
			
	</script>
</head>




<body id="his" class="appWrapper sidebar-xs-forced" oncontextmenu="return false;">
		 						
    
        <section id="">

            <div class="">
                <div class="">
                			<div class="hidden-print">
								<ul class="breadcrumb">
									&nbsp;
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getName()%></li>
										<%}else{ %>
											<%if(breadcrumbs.getOn()){ %>
												<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
											<%}else{ %>
												<li><%=breadcrumbs.getName()%></li>
											<%} %>
										<%} %>
										
									<%} %>
								</ul>
							</div>
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                     
                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right">
                   
                    <!-- <div class="form-inline">
                   
                    </div> -->
	                       <div class="form-inline">
	                      	<%
							 	if (loginInfo.isLmh()) {
							 %> 
							 	<input type="hidden" id="lmhAccess" value="1"> 
							 <%
							 	} else {
							 %> 
							 	<input type="hidden" id="lmhAccess" value="0">
							 <%
							 	}
							 %>
	                      	<%if(loginInfo.isMulti_adjust_approve() || loginInfo.getSuperadminid()==1){ %>
                                  <a href="#" class="btn btn-primary" onclick="addToAdjustmentCart()">Add to Adjustment</a>
							  		<div id="multiadjustdiv" class="form-group">
							  			<%if(session.getAttribute("multiadjustcount")!=null){ %>
						  						<%  String adjustment_id =  (String)session.getAttribute("multiadjustcount"); %>
		       									<a href="#" data-toggle="modal" onclick="showAdjustmentCartPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=adjustment_id %> </span></a>
							  			<%}else{ %>
							  					<a href="#" ><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
							  			<%} %>
						  			</div>
					  			 &nbsp; 		
                            <%} %>
	                      	<%if(loginInfo.isMulti_edit_product() || loginInfo.getSuperadminid()==1){ %>
							  	<a href="#" class="btn btn-primary" onclick="addToEditCart()">Add to Edit</a>
						  		<div  id="multieditdiv" class="form-group">
						  			<%if(session.getAttribute("multieditcount")!=null){ %>
						  				<%  String multiedit_id =  (String)session.getAttribute("multieditcount"); %>
	        									<a href="#" data-toggle="modal" onclick="showEditCartPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=multiedit_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
		                      	&nbsp; 
	                      	 <%} %>
	                      	<s:if test="isfromcathlab==1">
	                      			<a href="#" class="btn btn-primary" onclick="addToCathTemp()">Add to BOM Kit</a>
						  			<div class="form-group" id="cathtempdiv">
						  				<%if(session.getAttribute("cathtempcount")!=null){ %>
						  					<%  String cathtemp_id =  (String)session.getAttribute("cathtempcount"); %>
         										<a href="#" data-toggle="modal" onclick="showCathTempPopUp()"> <span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Create Bom Kit with : <%=cathtemp_id %> </span></a>
						  				<%}else{ %>
						  					<a href="#" data-toggle="modal" data-target="#cathtempcart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Create Bom Kit with : 0 </span></a>
						  				<%} %>
						  			</div>
						  			&nbsp; &nbsp; 
						  			<div class="form-group">
						  				<a href="#" class="btn btn-primary" onclick="openPopup('bomkittemplateProduct')">  View Bom Kits </a>
						  			</div>
						  			&nbsp; &nbsp; 
	                      			
	                      			&nbsp; &nbsp;
						  			<div class="form-group hidden" id="issuediv" >
						  				<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#issuecart"><span >Issue BOM kit </span></a>
						  			</div>
						  	&nbsp; &nbsp; &nbsp; &nbsp;
						  	
						  	  <a href="#" class="btn btn-primary" onclick="addToReturnQue()">Product Return Que</a>
	                      	</s:if>
	                      	<s:elseif test="isfromcathlab==2">
	                      		
	                      	</s:elseif>
	                      	<s:else>
	                      	
	                       	<%if(loginInfo.getUserType()==2) {%>
	                        	<a href="#" class="btn btn-primary" onclick="addToCart()">Transfer Without Indent</a>
						  		<div class="form-group" id="cartdiv">
						  			<%if(session.getAttribute("tcount")!=null){ %>
						  				<%  String p_id =  (String)session.getAttribute("tcount"); %>
         									<a href="#" data-toggle="modal" onclick="showCartPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=p_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#cart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
	                        <%}else{ %>
							  	<s:if test="direct_transfer==1">
							  		<a href="#" class="btn btn-primary" onclick="addToCart()">Transfer Without Indent</a>
							  		<div class="form-group" id="cartdiv">
							  			<%if(session.getAttribute("tcount")!=null){ %>
							  				<%  String p_id =  (String)session.getAttribute("tcount"); %>
	         									<a href="#" data-toggle="modal" onclick="showCartPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=p_id %> </span></a>
							  			<%}else{ %>
							  				<a href="#" data-toggle="modal" data-target="#cart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
							  			<%} %>
							  		</div>
							  	</s:if>
						  	<%} %>
						  	&nbsp;
						  	<a href="#" class="btn btn-primary" onclick="addToReturn()">Return to Store</a>
						  		<div class="form-group" id="returndiv">
						  			<%if(session.getAttribute("returncount")!=null){ %>
						  				<%  String r_id =  (String)session.getAttribute("returncount"); %>
         									<a href="#" data-toggle="modal" onclick="showReturnPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=r_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#returncart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
						  	&nbsp; 
						  	<a href="#" class="btn btn-primary" onclick="addToIssue()">Add to Issue</a>
						  		<div class="form-group" id="issuediv">
						  			<%if(session.getAttribute("issuecount")!=null){ %>
						  				<%  String i_id =  (String)session.getAttribute("issuecount"); %>
         									<a href="#" data-toggle="modal" onclick="showIssuePopUp(0)"> <span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=i_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#issuecart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
						  	&nbsp; 
						  	<a href="#" class="btn btn-primary" onclick="addtopurchaseque()">Add To Purchase Que</a>
						  	  <a href="#" class="btn btn-primary" onclick="addToReturnQue()">Product Return Que</a>
						  	  </s:else>
						  	
						  	
						  </div>
                       </div>
                    	
                       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top:5px !important;padding: 0;">
                       <div class="form-inline">
                           <s:form theme="simple" action="listProduct">
                           		<s:hidden name="isfromcathlab" id="isfromcathlab"></s:hidden>
	                            <%-- <div class="form-group">
	                           		<span class="text-uppercase"><b>Stock Status</b> <!-- &nbsp; - &nbsp; --></span>
	                           </div> --%>
                           		<div class="form-group" style="width: 16%;">
								  	<s:textfield name="searchtext" cssStyle="width: 100%;" placeholder="Search Product/Generic Name" theme="simple" cssClass="form-control"></s:textfield>
								</div>
								<div class="form-group" style="width: 8%;">
									<s:select list="categoryList" onchange="getsubcatagoryinitemwiseNEW(this.value)"  name="category_id" listKey="id" listValue="name" cssClass="form-control chosen-select" id="categoryid" headerKey="0" headerValue="Select Category" />
								</div>
								
								<div class="form-group" id="subcategorydiv" style="width: 8%;">
									<s:select list="subcategoryList" theme="simple" cssClass="form-control chosen-select" id="subcategoryid" name="subcategoryid" listKey="id" listValue="name" headerKey="0" headerValue="Select Sub-Category" ></s:select>
								</div>
								
								<div class="form-group hidden">
								  	<s:select  cssClass="form-control chosen-select" list="#{'1':'By Catalogue Wise', '2':'By Product-Batch Wise'}" name="report_filter" />
								</div>
								<div class="form-group hidden">
								  	<s:select cssClass="form-control chosen-select" list="#{'1':'Order by name', '2':'Order by stock', '3':'Order by MRP'}" name="orderby" />
								</div>
								
								<div class="form-group" style="width: 8%;">
								  	<s:select  cssClass="form-control chosen-select" list="#{'1':'With 0 Stock', '2':'Without 0 Stock', '3':'Only 0 Stock'}" name="withstock_filter" />
								</div>
								<div class="form-group" style="width: 8%;">
								  	<s:select  cssClass="form-control chosen-select" list="#{'0':'Select Product Type', 'Regular':'Regular','H1':'H1','Narcotics':'Narcotics','High Risk Medicine':'High Risk Medicine','Vaccination':'Vaccination'}" name="product_type" />
								</div>
								
								<div class="form-group" style="width: 10%;">
								  	<s:select theme="simple" name="shelf_filter"  list="cellList" cssClass="form-control chosen-select" listKey="name"   listValue="name" headerKey="" headerValue="Select Shelf"   ></s:select>
								</div>
								
								<div class="form-group" style="width: 40%;">
								  	<button type="submit" class="btn btn-primary">Go</button>
								  	<a type="button"  title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
									<a type="button" class="btn btn-primary"  title="Print" onclick="printDiv('page_printer')"><i class="fa fa-print"></i></a>
									<a href="#" class="btn btn-primary" onclick="minimumQtyProductList()">Minimum Qty Product</a>
									<a href="#" class="btn btn-primary" onclick="expiryproductwithin31days()">Product Expiry Within 90 Days</a>
								</div>
								
								
								<!-- <div class="form-group pull-right">
												<a type="button"  title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
												<a href="#" class="btn btn-success"  onclick="printStockReportExcel()" title="Download CSV file" style="line-height: 14px;"> Download Excel</a>
											</div> -->
								<!-- <div class="form-group">
									<a type="button" class="btn btn-primary"  title="Print" onclick="printDiv('page_printer')"><i class="fa fa-print"></i></a>
									<button type="button" onclick="printDiv('page_printer')" class="btn btn-warning">Print</button>
								</div> -->
                           	</s:form>
                           		
                           </div>
                        </div>
                       </div>
                       <input type="hidden" value="<%=loginInfo.isLmh()%>" id="clinicId">
                                          <div class="" id="page_printer">
                    	<h5 class="hidden-lg hidden-md visible-print"><span class="text-uppercase"><b>Stock Status</b> &nbsp;  &nbsp;</span></h5>
                        <table class="table my-table tablestock  table-bordered" id="tablesort" style="width: 100%;">
                            <thead>
                               <tr>
                                    <!--<th><a href="#" onclick="deleteCheck()" class="hidden"><i class="fa fa-trash" style="color: #fff;"></i></a></th>
                                    -->
                                    <th style="width: 2%;">Sr.</th>
                                    <td style="width: 2%;" class="hidden-print">
                                    	<input type="checkbox" id="selectalliventorystockid" onclick="selectAllCheckBoxOfInventoryStock(this)"   />
                                    </td>
                                    <th style="width: 5%;" class="hidden">ID</th>
                                     <!-- <th>Location</th> -->
                                    <!-- <th>HSN Code</th>
                                    <th>GST</th>
                                    <th style="width: 8%;">Product Type</th>
                                    <th style="width: 13%;">Generic Name</th> -->
                                    
                                    <th style="width: 12%;">Product Name</th>
                                    <th style="width: 10%;">Generic Name</th>
                                    <th style="width: 12%;">Product Code</th>
                                     <%
		                            if (loginInfo.isSupplier_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <th style="width: 12%;">Vendor Name</th>
                                    <%
		                             }
		                            %>
                                    
                                     <s:if test="isfromcathlab==1">
                                    	 <th>Product Code</th>
                                    	 <th>Min Order</th>
                                    	 <th>Max Order</th>
                                    </s:if>
                                    <th style="width: 8%;">Sub Catagory</th>
                                    <th style="width: 2%;">Pack</th>
                                    <th style="width: 11%;">Location</th>
                                     <%
		                            if (loginInfo.isGst_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <th>GST</th>
                                    <%
		                             }
		                            %>
                                    <%
		                            if (loginInfo.isMrp_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <th style="text-align:right;width: 5%;">MRP</th>
                                     <%
		                             }
		                            %>
		                            
                                    <s:if test="isStoreLocation==1">
                                    	<th style="text-align:right;width: 8%;">Purchase Price</th>
                                    </s:if>
                                    <s:else>
                                    <%
		                            if (loginInfo.isPurchase_price_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <th style="text-align:right;width: 8%;">Purchase Price</th>
                                    <%
		                             }
		                            %>
                                    </s:else>
                                    
                                  <%if(loginInfo.isBalgopal()) {%>
                                    <%
		                            if (loginInfo.isSale_price_access() || loginInfo.getUserType() == 2) {
		                            %>
                                     <th style="text-align:right;width: 7%;">Stock Price</th>
                                   <%
		                             }
		                           %>
                                 <%}else{ %>
                                    <%
		                            if (loginInfo.isSale_price_access() || loginInfo.getUserType() == 2) {
		                            %>
                                     <th style="text-align:right;width: 7%;">Sale Price</th>
                                    <%
		                             }
		                            %>
		                         <%
		                           }
		                         %>  
		                            
                                    <th style="width: 7%;">MFG</th>
                                     <th style="width: 7%;">Expiry Date</th>
                                    <th style="width: 6%;">Batch No</th>
                                    <th style="text-align:right;width: 3%;">Qty</th>
                                    <th style="text-align:right;width: 4%;">Shelf</th>
                                    <th style="text-align:right;width: 4%;">S.Shelf</th>
                                 <%if(loginInfo.isBalgopal()) {%> 
                                       <th class="hidden" style="text-align:right;width: 4%;">S.V.</th>
                                   <%}else{ %>
                                     <%
		                              if (loginInfo.isSale_value_access() || loginInfo.getUserType() == 2) {
		                             %>
                                       <th style="text-align:right;width: 4%;">S.V.</th>
                                    <%
		                             }
		                            %>
		                        <%
		                         }
		                        %>   
		                            <%
		                            if (loginInfo.isSale_value_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <th style="text-align:right;width: 4%;">N.R.</th>
                                    <%
		                             }
		                            %>
                                    <th class="hidden-print">Edit</th> 
                                    <th style="text-align:right;" class="hidden-print">Adjust.</th> 
                                     <!-- <th>Delete</th> -->
                                </tr>
                            </thead>
                            
                            <tbody>
                            <%int counttss=0; %>
                               <s:iterator value="productList" >
                               
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++counttss) %></td>
                                    <td class="hidden-print">
                                    	<s:if test="isinadjustment==0">
                                   			 	<input type="checkbox" class="case" value="<s:property value="id"/>" class="form-control" />
                                    	</s:if>
                                    </td>
                                    <td class="hidden"><s:property value="id"/></td>
                                    <%-- <td><s:property value="location"/></td> --%> 
                                    <%-- <td><s:property value="hsnno"/></td>
                                    <td><s:property value="vat"/>%</td>
                                    <td><s:property value="medicine_type_name"/></td>
                                    <td style="text-transform: uppercase;"><s:property value="genericname"/></td> --%>
                                    
                                    <td><s:property value="product_name"/></td>
                                    <td><s:property value="genericname"/></td>
                                    <td><s:property value="product_code"/></td>
                                    <%
		                            if (loginInfo.isSupplier_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <td><s:property value="vendor"/></td>
                                    <%
		                             }
		                            %>
                                    <s:if test="isfromcathlab==1">
                                    	<td><s:property value="pro_code"/></td>
                                    	<td><s:property value="minorder"/></td>
                                    	<td><s:property value="maxorder"/></td>
                                    </s:if>
                                    
                                    <td><s:property value="subcategory"/></td>
                                    <td><s:property value="pack"/></td>
                                    <td><s:property value="locationName"/></td>
                                     <%
		                            if (loginInfo.isGst_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <td><s:property value="vat"/>%</td>
                                    <%
		                             }
		                            %>
                                    <%
		                            if (loginInfo.isMrp_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <td class="text-right">Rs.<s:property value="mrp"/></td>
                                    <%
		                             }
		                            %>
                                    <s:if test="isStoreLocation==1">
                                    	<td class="text-right">Rs.<s:property value="purchase_price"/></td>
                                    </s:if>
                                    <s:else>
                                    <%
		                            if (loginInfo.isPurchase_price_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <td class="text-right">Rs.<s:property value="purchase_price"/></td>
                                    <%
		                             }
		                            %>
                                    </s:else>
                                    <%
		                            if (loginInfo.isSale_price_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <td class="text-right">Rs.<s:property value="sale_price"/></td>
                                     <%
		                             }
		                            %>
                                    <td><s:property value="mfg"/></td>
                                    <td><s:property value="expiry_date"/></td>
                                    <td><s:property value="batch_no"/></td>
                                    <td class="text-right"><s:property value="stock"/></td>
                                    <td class="text-right"><s:property value="shelf"/></td>
                                    <td class="text-right"><s:property value="secondary_shelf"/></td>
                                    <%
		                            if (loginInfo.isSale_value_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <td class="text-right"><s:property value="purpriceqty"/></td>
                                     <%
		                             }
		                            %>
		                            <%
		                            if (loginInfo.isSale_value_access() || loginInfo.getUserType() == 2) {
		                            %>
                                    <td class="text-right"><s:property value="gstamount"/></td>
                                     <%
		                             }
		                            %>
                                    <td class="hidden-print" style="text-align: center;">
                                    <%if(loginInfo.isEdit_product() || loginInfo.getSuperadminid()==1){ %>
                                    	<s:if test="isinadjustment==0">
                                    		<a href="#" onclick="editStock(<s:property value="id"/>,1)"><i class="fa fa-pencil"></i></a>
                                    	</s:if>
                                    <%}else{ %>
	                                    <s:if test="isinadjustment==0">
	                                    	<a href="#" onclick="editStock(<s:property value="id"/>,0)"><i class="fa fa-pencil"></i></a> 
	                                    </s:if>
                                    <%} %>
                                        
                                    </td> 
                                   <td class="hidden-print" style="text-align: center;">
                                   		<%if(loginInfo.isAdjustmentaccess() || loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
	                                   		<s:if test="isinadjustment==0">
	                                   			<a href="#" onclick="getadjustmentdata(<s:property value="id"/>)"><i class="fa fa-balance-scale" aria-hidden="true"></i></a>
	                                   		</s:if>
                                   			 
                                   		<%} %>
                                   </td>
                                </tr>
                                
                                </s:iterator>
                            </tbody>

                        </table><br/>
                    </div>



<%--  <!-- <div class="hidden" id="page_printer"> -->
 <div style="width: 100%;font-size: 8px;display:none;" id="page_printer">
 						<h5><span class="text-uppercase"><b>Stock Status</b> &nbsp;  &nbsp;</span></h5>
                        <table class="my-table tablestock" id="productlistnew" style="width: 100%;font-size: 8px;">
                            <thead>
                                <tr>
                                   
                                  
                                    <th>ID</th>
                                    <th>Sub Catagory</th>
                                    <th>Product Name</th>
                                     <s:if test="isfromcathlab==1">
                                    	 <th>Product Code</th>
                                    	 <th>Min Order</th>
                                    	 <th>Max Order</th>
                                    </s:if>
                                    <th>Pack</th>
                                    <th>Location</th>
                                    <th>GST</th>
                                    <th>MRP</th>
                                    <th>Purchase Price</th>
                                    <th>Sale Price</th>
                                    <th>MFG</th>
                                    <th>Expiry Date</th>
                                    <th>Batch No</th>
                                    <th>Qty</th>
                                    <th>Shelf</th>
                                    <th>S.V.</th>
                                   
                                    
                                </tr>
                            </thead>
                            
                            <tbody>
                               <s:iterator value="productList">
                                <tr>
                       
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="subcategory"/></td>
                                    <td><s:property value="product_name"/></td>
                                     <s:if test="isfromcathlab==1">
                                    	<td><s:property value="pro_code"/></td>
                                    	<td><s:property value="minorder"/></td>
                                    	<td><s:property value="maxorder"/></td>
                                    </s:if>
                                    <td><s:property value="pack"/></td>
                                    <td><s:property value="locationName"/></td>
                                    <td><s:property value="vat"/>%</td>
                                    <td>Rs.<s:property value="mrp"/></td>
                                    <td>Rs.<s:property value="purchase_price"/></td>
                                    <td>Rs.<s:property value="sale_price"/></td>
                                    <td><s:property value="mfg"/></td>
                                    <td><s:property value="expiry_date"/></td>
                                    <td><s:property value="batch_no"/></td>
                                    <td><s:property value="stock"/></td>
                                     <td><s:property value="shelf"/></td>
                                     <td><s:property value="purpriceqty"/></td> 
                                </tr>
                                </s:iterator>
                            </tbody>

                        </table>
                    </div> --%>

					  <s:form action="listProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
									<div class="col-lg-12">
									<input type="hidden" name="prod_name" value='<s:property value="prod_name"/>'> 
									<s:hidden name="searchtext"></s:hidden>
									<s:hidden name="shelf_filter"></s:hidden>
									<s:hidden name="isfromcathlab"></s:hidden><s:hidden name="category_id"></s:hidden>
									<s:hidden name="withstock_filter"></s:hidden>
									<s:hidden name="product_type"></s:hidden>
									<s:hidden name="pagerecords"></s:hidden>
									<s:hidden name="subcategoryid"></s:hidden>
									<s:hidden name="page_records"></s:hidden>
									
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" />
											Records
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form>                   
                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->



<!-- Cart Modal -->
<div id="cart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="transferproductdataCatalogue" theme="simple" id="transferproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Transfer Stock</h4>
      </div>
      <div class="modal-body">
      <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="">
	      			<label>Issued Date : </label>
	      			<s:textfield readonly="true" name="issue_date" id="issue_date" placeholder="Select Date"
					cssClass="form-control" theme="simple" />
				</div>
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tabletrcount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 6%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 8%;">Batch No</th>
            <th style="width: 7%;">Exp Date</th>
            <th style="width: 13%;">Location</th>
            <th style="width: 8%;">Current Stock</th>
            <th style="width: 12%;">Transfer Location</th>
            <th style="width: 6%;">Transfer Qty</th>
            <th style="width: 2%;"></th>
           </tr>
          </thead>
          <tbody id="carttbody">
           
          </tbody>
          </table>
          <input type="hidden" name="tcount" id="tcount">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="comment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="divbtn">
       <button type="button" class="btn btn-primary" onclick="confirmTransfer()">Transfer</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>




   <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="editcat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Update Stock</h4>
                </div>
                <div class="modal-body" style="padding:0px;">
                    <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                    	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                    		<div class="form-group">
							    <label for="email">Generic Name:</label>
							    <input type="text" class="form-control" >
							  </div>
                    	</div>
                    	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                    		<div class="form-group">
							    <label for="email">Product Name</label>
							    <input type="text" class="form-control">
							  </div>
                    	</div>
                    	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                    		<div class="form-group">
							    <label for="email">MRP/Purchase Price</label>
							    <p>Rs.1200.00 / Rs.1150.00</p>
							  </div>
                    	</div>
                    	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                    			<div class="form-group">
							    <label for="email">Sale Price<small>(per unit)</small></label>
							    <input type="text" class="form-control">
							  </div>
                    	</div>
                    
                        <form class="form-horizontal" id="myform" action="updateprodstockProduct" method="post">
                        	<s:hidden name="searchtext"></s:hidden>
                        	<s:hidden name="shelf_filter"></s:hidden>
                        	<s:hidden name="category_id"></s:hidden>
                        	<s:hidden name="withstock_filter"></s:hidden>
                        	<s:hidden name="product_type"></s:hidden>
                            <input type="hidden" name="id" id="id" /> 
                            <s:hidden name="isfromcathlab"></s:hidden>
                            <div class="form-group hidden">
                                <label for="inputEmail3" class="col-sm-3 control-label">Section :</label>
                                <div class="col-sm-9">
                                    <select id="ddlSection" class="PopDDl" onchange="getCategory();"><option value="11">OT Equipment</option><option value="12">Other</option><option value="13">Medical Equipment</option><option value="14">Baby Care</option><option value="15">Healthy Wealthy</option><option value="16">Ready to Eat</option><option value="17">Cloth Care</option><option value="20">Furniture</option><option value="21">Medicine</option><option value="23">Exclusive Store1</option><option value="24">Exclusive2</option><option value="25">Exclusive2</option><option value="26">Exclusive3</option><option value="27">person care2</option><option value="28">ssssssss</option><option value="29">test</option><option value="30"></option><option value="31"></option><option value="32"></option><option value="33">Other</option></select>
                                </div>
                            </div>

                            <div class="form-group hidden">
                                <label for="inputEmail3" class="col-sm-3 control-label">Category :</label>
                                <div class="col-sm-9">
                                    <select id="ddlCatagory" class="PopDDl"><option value="4">Hospital Bed-ICU</option><option value="6">Hospital Bed-Fowler</option><option value="7">Hospital Bed-Plain</option><option value="8">Hospital Bed-Semi-Flower</option><option value="9">Obstetric Tables</option><option value="10">Trolly</option><option value="11">Stretchers</option><option value="12">Doctor Chair &amp; Stools</option><option value="209">Baby Crib</option><option value="210">Beds - Orthopaedic</option><option value="211">Beds Mattress</option><option value="220">STRETCHER TROLLEY</option><option value="221">INSTRUMENT TROLLEY</option><option value="222">EXAMINATION COUCH</option><option value="223">WHEEL CHAIR</option><option value="224">Medical Cabinets Cupboards</option><option value="225">Waiting Chairs &amp; Benches</option><option value="226">Office Conference, Coffee Tables</option></select>
                                </div>
                            </div>
                            
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding: 0px 10px 0px 10px;">
                            <div class="col-lg-3 col-md-3 col-xs-3 hidden">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Category</label>
										<s:select list="categoryList" listKey="id" onchange="getsubCategories(this.value)" listValue="name" headerKey="0" headerValue="Select Category" name="category_id"  id="category_id" cssClass="form-control" cssStyle="width:95%;" />
	                            	</div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                            		<div class="form-group" >
		                                <label for="inputEmail3" class="control-label">Sub Category</label>
		                                 <div id="subdiv">
		                                	<s:select theme="simple" name="subcategory_id" id="subcategory_id" list="subcategoryList" cssClass="form-control" listKey="id"  cssStyle="width:95%;" listValue="name" headerKey="0" headerValue="Select Sub Category"   ></s:select> 
		                                 </div>
	                            	</div>
                            	</div>
                            		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
	                                		<label for="inputEmail3" class="control-label">Product Name</label>
	                                		<input type="text" readonly="readonly" class="form-control" id="product_name" name="product_name" style="text-transform: uppercase;">
	                           		 	</div>
	                           		</div>
	                           		<div class="col-lg-12 col-md-12 col-xs-12">
	                            		<div class="col-lg-6 col-md-6 col-xs-6">
	                            			<div class="form-group" style="margin-right:2px;">
		                                		<label for="inputEmail3" class="control-label">PACK</label>
		                                		<input class="form-control" readonly="readonly" type="number" onchange="calSalePerUnitCatalogue()"  id="pack" name="pack"  />
		                            		</div>
	                            		</div>
	                            		<div class="col-lg-6 col-md-6 col-xs-6">
	                            			<div class="form-group" >
		                             			<label for="inputEmail3" class="control-label">GST</label>
		                             				<%if(hadeditaccess==1){ %>
		                             					<select name="gst" id="gst" class="form-control" style="width:95%;">
										    			<option value="0">0%</option>
										    			<option value="5">5%</option>
										    			<option value="12">12%</option>
										    			<option value="18">18%</option>
										    			<option value="28">28%</option>
													</select> 
		                             				<%}else{ %>
		                             					<input class="form-control" type="number"  id="gst" name="gst"  />
		                             				<%} %>
		                             				
													
		                            		</div>
	                            		</div>
	                            	</div>
	                            	
	                            	<div class="col-lg-12 col-md-12 col-xs-12">
	                            		<div class="col-lg-6 col-md-6 col-xs-6" >
	                            			<div class="form-group" style="margin-right:2px;">
		                                		<label for="inputEmail3" class="control-label">MRP</label>&nbsp;
		                                		<input class="form-control" type="number" onchange="calSalePerUnitCatalogue()"  id="mrp" name="mrp"  />
		                                		
		                            	</div>	
	                            		</div>
	                            		<div class="col-lg-6 col-md-6 col-xs-6">
	                            			<div class="form-group">
		                                		<label for="inputEmail3" class="control-label">Sale Price</label>
		                                		<input class="form-control" type="number" readonly="readonly"  id="sale_price" name="sale_price"  />
		                            		</div>
	                            		</div>
	                            	</div>
	                            	
	                            	<div class="col-lg-12 col-md-12 col-xs-12">
	                            		<div class="col-lg-6 col-md-6 col-xs-6" >
	                            			<div class="form-group">
		                            			<label for="inputEmail3" class="control-label">Pur.Price</label>
		                            			<input type="number" class="form-control" id="purchase_price" name="purchase_price" style="width:95%;">
	                               			</div>
	                            		</div>
	                            		<div class="col-lg-6 col-md-6 col-xs-6">
	                            			<div class="form-group">
		                                		<label for="inputEmail3" class="control-label">Barcode</label>
		                                		<input class="form-control" type="text" id="edit_barcode" name="barcode"  />
		                            		</div>
	                            		</div>
	                            	</div>
	                            	<div class="col-lg-12 col-md-12 col-xs-12">
		                            	<div class="form-group">
			                                <label for="inputEmail3" class="control-label">Batch Number</label>
			                                <input class="form-control" type="text"  id="batch_no" name="batch_no"  />
			                            </div>
			                            <div class="form-group">
			                                <label for="inputEmail3" class="control-label">Expiry Date</label>
			                                <!-- <input class="form-control" type="text"  id="expiry_date" name="expiry_date"  /> -->
			                                <input type="text" id="expiry_date" class="form-control" name="expiry_date"  required="required" placeholder="MM/YYYY" />    
			                            </div>
		                            	<div class="form-group hidden">
			                                <label for="inputEmail3" class="control-label">Stock</label>
			                                <input class="form-control" type="number"  id="stock" name="stock"  style="background-color: cornsilk;"/>
			                            </div>
			                           
		                         	</div>
		                         	<div class="col-lg-12 col-md-12 col-xs-12">
	                            		<div class="col-lg-6 col-md-6 col-xs-6" >
	                            			<div class="form-group">
		                            			<label for="inputEmail3" class="control-label">Primary Shelf</label>
			                                	<s:select theme="simple" name="shelf" id="shelfid" list="cellList" cssClass="form-control" listKey="name"  cssStyle="width:95%;" listValue="name" headerKey="" headerValue="Select Shelf"   ></s:select>
	                               			</div>
	                            		</div>
	                            		<div class="col-lg-6 col-md-6 col-xs-6">
	                            			<div class="form-group">
		                                		<label for="inputEmail3" class="control-label">Secondary Shelf</label>
			                                	<s:select theme="simple" name="secondary_shelf" id="secondary_shelfid" list="cellList" cssClass="form-control" listKey="name"  cssStyle="width:95%;" listValue="name" headerKey="" headerValue="Select Shelf"   ></s:select>
		                            		</div>
	                            		</div>
	                            	</div>
	                            	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
	                            		<div class="form-group">
			                                <label for="inputEmail3" class="control-label">Generic Name</label>
			                                <input type="text" readonly="readonly" name="genericname" id="genericname"  class="form-control"  style="text-transform: uppercase;width:95%;">
			                            </div>
	                            	</div>
	                            	<div class="col-lg-2 col-md-2 col-xs-3">
	                            		
	                            	</div>
                            	</div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="submitForm1()" class="btn btn-primary">Update</button>
                </div>
            </div>
        </div>
    </div>

     


<!--Return Cart Modal -->
<div id="returncart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="returnproductdataCatalogue" theme="simple" id="returnproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Return Stock</h4>
      </div>
      <div class="modal-body">
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tabletreturncount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 10%;">Location</th>
            <th style="width: 9%;">Current Stock</th>
            <th style="width: 13%;">Return Location</th>
            <th style="width: 10%;">Return Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="returncarttbody">
           
          </tbody>
          </table>
          <input type="hidden" name="returncount" id="returncount">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="returncomment" name="returncomment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="returnbtndiv">
     	 <button type="submit" class="btn btn-primary" onclick="return confirmReturn()">Return</button> 
      </div>
      
      </s:form>
    </div>

  </div>
</div>


<!-- Cart Modal -->
<div id="issuecart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="transferissueproductdataCatalogue" theme="simple" id="issueproductform"> 
   <input type='hidden' class='form-control' name="addchargetopatientstatus" id="addchargetopatientstatus">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Item Issue To Patient/User</h4>
      </div>
      <div class="modal-body">
         <div class="form-group">
         <s:hidden name="isfromcathlab"></s:hidden>
         	<s:if test="isfromcathlab==1">
         		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
         			
          			<input type='hidden' class='form-control' name="issuepatientid" value="0" id='issuepatientid'>
         			<input type='text' readonly="readonly" placeholder="Select Patient/User" class='form-control' id='issuepatientname' onclick='getpatientpopForIssue()'>
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          			<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select" list="cathtemplist" onchange="setcathTempValue(this.value)"  id="cathtempid" name="cathtempid" />
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		
          		</div>
         	</s:if>
         	<s:else>
         		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          			<s:select cssClass="form-control chosen-select" list="#{'0':'Patient', '1':'YUVICARE User', '2':'Department'}" onchange="setpatientuserlist(this.value)" id="hisuserfilter" name="hisuserfilter" />
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="issuechangediv">
          			<!-- <label>Patient:</label> -->
          			<input type='hidden' class='form-control' name="issuepatientid" value="0" id='issuepatientid'>
         			<input type='text' readonly="readonly" placeholder="Select Patient/User" class='form-control' id='issuepatientname' onclick='getpatientpopForIssue()'>
          		</div>
           		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="issueprocediv">
           			<select id="issueproceid" name="issueproceid" class="from-control chosen-select">
           				<option value="0">Select Procedure</option>
           			</select>
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="">
	      			<s:textfield readonly="true" name="issue_date" id="issue_date1" placeholder="Select Date"
					cssClass="form-control" theme="simple" />
				</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          			<%-- <s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select Department" list="issuedeptlist"  id="hisdepartmentfilter" name="hisdepartmentfilter" /> --%>
          		</div>
         	</s:else>
         
        </div>
      	<div class="clearfix" style="height: 15px;"></div>
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tableissuecount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 6%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 6%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 15%;">Location</th>
            <s:if test="isfromcathlab==1">
            </s:if>
            <s:else>
            	<th style="width: 7%;">Current Stock</th>
            </s:else>
           <!--  <th style="width: 13%;">Patient Name</th> -->
            <th style="width: 8%;">Transfer Qty</th>
            <th style="width: 3%;"></th>
           </tr>
          </thead>
          <tbody id="issuebody">
           
          </tbody>
          </table>
          <input type="hidden" name="issuecount" id="issuecount">
        </div>
        
        <div class="form-group">
			<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="issuecomment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="issuedivbtn">
      	 <s:if test="isfromcathlab==1">
      	 	<button type="button" class="btn btn-primary" onclick="confirmIssueCathLab()">Submit</button>
         </s:if>
         <s:else>
           <button type="button" class="btn btn-primary" onclick="confirmIssue()">Transfer</button>
         </s:else>
       
      </div>
      
      </s:form>
    </div>

  </div>
</div>

<!-- Cart Modal -->
<div id="cathtempcart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="savecathlabtemplateCatalogue" theme="simple" id="cathtempproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Create Cathlab Template</h4>
      </div>
      <div class="modal-body">
         <div class="form-group">
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select Procedure" list="procedurelist"  id="cathtempprocedure" name="cathtempprocedure" />
          	</div>
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		<input type='text'  placeholder="Template name" class='form-control' id='cathtemplatename' name="cathtemplatename">
          	</div>
          	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
          		
          	</div>
           	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
           		
          	</div>
           
          	
      	</div>
      	<div class="clearfix" style="height: 15px;"></div>
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tablecathcount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">Category</th>
            <th style="width: 9%;">Sub Category</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 9%;">Product Code</th>
            <th style="width: 8%;">Batch No</th>
            <th style="width: 8%;">MFG</th>
            <th style="width: 9%;">Exp Date</th>
            <th style="width: 9%;">Location</th>
            <th style="width: 4%;">Stock</th>
            <th style="width: 10%;">Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="cathempbody">
           
          </tbody>
          </table>
          <input type="hidden" name="cathtempcount" id="cathtempcount">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="cathtempcomment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="cathtempdivbtn">
       		<button type="button" class="btn btn-primary" onclick="confirmCathTemp()">Create Template</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>


<%-- <!--   06 Sep 2018 -->
<!-- Cart Modal -->
<div id="updatecathtempcart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="savecathlabtemplateCatalogue" theme="simple" id="updatecathtempproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Update Cathlab Template</h4>
      </div>
      <div class="modal-body">
         <div class="form-group">
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="updateprocedurediv">
          		
          	</div>
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		<input type='text'  placeholder="Template name" class='form-control' id='updatecathtemplatename' name="cathtemplatename">
          	</div>
          	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
          		
          	</div>
           	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
           		
          	</div>
           
          	
      	</div>
      	<div class="clearfix" style="height: 15px;"></div>
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tablecathcount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">Category</th>
            <th style="width: 9%;">Sub Category</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 9%;">Product Code</th>
            <th style="width: 8%;">Batch No</th>
            <th style="width: 8%;">MFG</th>
            <th style="width: 9%;">Exp Date</th>
            <th style="width: 9%;">Location</th>
            <th style="width: 4%;">Stock</th>
            <th style="width: 10%;">Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="updatecathempbody">
           
          </tbody>
          </table>
          <input type="hidden" name="cathtempcount" id="updatecathtempcount">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="updatecathtempcomment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="updatecathtempdivbtn">
       		<button type="button" class="btn btn-primary" onclick="confirmCathTemp()">Update Template</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div> --%>



<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%-- <%@ include file="/ipd/pages/ipdPatientList.jsp"%> --%>
        <%@ include file="/inventory/pages/patientlist.jsp"%>
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Add New Patient -->
	<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">			
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
				</div>
				<div class="modal-body addnewpat">
					<%-- <jsp:include page="/diarymanagement/pages/addPatientPage.jsp"/> --%>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="return savePatient()">Save</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" >Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Adjustment process -->
	 <div class="modal fade" id="adjustmentmodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Adjustment Product Process</h4>
                </div>
                <div class="modal-body" style="padding:0px;">
                    <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                    	    <form class="form-horizontal" id="adjustmyform" action="saveadjustmentdataProduct" method="post">
                    	    <s:hidden name="searchtext"></s:hidden>
                        	<s:hidden name="category_id"></s:hidden>
                        	<s:hidden name="withstock_filter"></s:hidden>
                        	<s:hidden name="product_type"></s:hidden>
                        	<s:hidden name="shelf_filter"></s:hidden>
                            <input type="hidden" name="id" id="adj_productid" /> 
                            <s:hidden name="isfromcathlab"></s:hidden>
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding: 0px 10px 0px 10px;">
                            		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
	                                		<label for="inputEmail3" class="control-label">Product Name :</label>
	                                		<!-- <input type="text" readonly="readonly" class="form-control" id="adj_productname"  style="text-transform: uppercase;"> -->
	                           		 		<p id="adj_productname"></p>
	                           		 	</div>
	                           		</div>
	                           		
	                           		
	                           		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
	                                		<a href="#" data-toggle="tooltip" data-placement="right" title="1.Excess Product Adjustment: If product are in excess then reduce stock.
	                                		2.Shortage Product Adjustment: If product are in shortage then add stock.
	                                		3.Defective Product Adjustment: If product are in defective then reduce stock.
	                                		4.Expired/Dead Product Adjustment: If product are in expired/dead then reduce stock.">
	                                		<label for="inputEmail3" class="control-label">Adjustment Type</label><label><span class="text-danger">*</span>
	                                		<%-- <span class="tooltip"><i class="fa fa-info-circle fa-x"></i><span class="tooltiptext" data-placement="left">
	                                		
	                                		 </span></span> --%>
	                                		</label></a>
	                                		<select  onchange="resetadjustmentfield()"  id="adjustment_type" name="adj_type" class="form-control chosen-select" style="width:95%;">
								    			<option value="0">Select Adjustment</option>
								    			<option value="1">Excess Product Adjustment</option>
								    			<option value="2">Shortage Product Adjustment</option>
								    			<option value="3">Defective Product Adjustment</option>
								    			<option value="4">Expired/Dead Product Adjustment</option>
								    		</select> 
	                           		 	</div>
	                           		</div>
	                           		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
	                                		<label for="inputEmail3" class="control-label">Stock</label>
	                                		<input type="text"  readonly="readonly" name="pre_stock"  class="form-control" id="adj_pre_stock"  style="text-transform: uppercase;">
	                           		 	</div>
	                           		</div>
	                           		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
	                                		<label for="inputEmail3" class="control-label">Change Qty</label><label><span class="text-danger">*</span></label>
	                                		<input type="number"  class="form-control" min="0" onchange="calculateChangeAdjData(this.value)" name="adj_change_stock_indi"  id="adj_change_stock_indi"  style="text-transform: uppercase;">
	                           		 	</div>
	                           		</div>
	                           		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
	                                		<label for="inputEmail3" class="control-label">Total Qty</label><label><span class="text-danger">*</span></label>
	                                		<input type="number" readonly="readonly" class="form-control" min="0" name="change_stock" id="adj_change_stock"  style="text-transform: uppercase;">
	                           		 	</div>
	                           		</div>
	                           		
	                           		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
											<label for="comment">Remark</label><label><span class="text-danger">*</span></label>
											<textarea class="form-control" rows="3" id="adj_comment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
										</div>
                            		</div>
	                           </div>
	                           		
	                           		
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="submitadjustmentadata()"  class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>
    
    
    <!-- Add to purchase order Modal -->
<div id="addtopurchaseordermodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="savetopurchasequeCatalogue" theme="simple" id="addtopurchaseorderform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add To Purchase Order Que</h4>
      </div>
      <div class="modal-body">
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" style="width:100%;">
	          <thead>
		           <tr>
			            <th style="width: 4%;">Sr.no</th>
			            <th style="width: 50%;">Product Name</th>
			            <th style="width: 25%;">Purchase For</th>
			            <th style="width: 10%;">Request Qty</th>
		           </tr>
	          </thead>
	          <tbody id="addtopurchasebody">
	           		
	          </tbody>
          </table>
        </div>
        
      </div>
      
      <div class="modal-footer" id="addtopurchaseorderbtn">
       <button type="button" class="btn btn-primary" onclick="confirmaddtopurchaseque()">Add to purchase Que</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>

<!-- Multi Adjustment Modal -->
<div id="multiadjustmentmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg" style="width: 95%;">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="savemultiadjustmentProduct" theme="simple" id="multiadjustmentform"> 
		<s:hidden name="searchtext"></s:hidden>
      	<s:hidden name="category_id"></s:hidden>
      	<s:hidden name="withstock_filter"></s:hidden>
      	<s:hidden name="product_type"></s:hidden>
   		<s:hidden name="shelf_filter"></s:hidden>
      <div class="modal-header">
	      <button type="button" class="close" data-dismiss="modal">&times;</button>
	      <h4 class="modal-title">Multiple Adjustment Process</h4>
      </div>
      <div class="modal-body">
        <div class="">
        	 <div style="color: red;">
        		<p><b>1.&nbsp;Excess Product Adjustment: If product are in excess then reduce stock.
        		&nbsp;2.&nbsp;Shortage Product Adjustment: If product are in shortage then add stock.</b></p>
        		<p><b>3.&nbsp;Defective Product Adjustment: If product are in defective then reduce stock.
        		&nbsp;4.&nbsp;Expired/Dead Product Adjustment: If product are in expired/dead then reduce stock.</b></p>
	         </div>
	         <div style="overflow: scroll;height: 400px !important;">
	         	  <table class="table my-table xlstable table-striped table-bordered" id="multiadjusttabletrcount" style="width:100%;">
		          <thead>
			         	<tr>
				            <th style="width: 4%;">Sr.no</th>
				            <th style="width: 5%;">HSN Code</th>
				            <th style="width: 22%;">Product Name</th>
				            <th style="width: 5%;">Batch No</th>
				            <th style="width: 6%;">Exp Date</th>
				            <th style="width: 17%;">Adjustment Type</th>
				            <th style="width: 4%;text-align: center;">Stock</th>
				            <th style="width: 5%;">Change Qty</th>
				            <th style="width: 5%;">Total Qty</th>
				            <th style="width: 21%;">Remark</th>
				            <th style="width: 2%;"></th>
			           	</tr>
		          </thead>
		          <tbody id="multiadjustmenttbody">
		           
		          </tbody>
		          </table>
		          <input type="hidden" name="tcount" id="multiadjustcount">
	         </div>
       
        </div>
        
      </div>
      
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" onclick="requestmultiadjustment()">Request Adjustment</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>

<!-- Multi Adjustment Modal -->
<div id="multieditmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg" style="width: 95%;">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="savemultieditProduct" theme="simple" id="multieditform"> 
		<s:hidden name="searchtext"></s:hidden>
      	<s:hidden name="category_id"></s:hidden>
      	<s:hidden name="withstock_filter"></s:hidden>
      	<s:hidden name="product_type"></s:hidden>
   		<s:hidden name="shelf_filter"></s:hidden>
      <div class="modal-header">
	      <button type="button" class="close" data-dismiss="modal">&times;</button>
	      <h4 class="modal-title">Multiple Edit Process</h4>
      </div>
      <div class="modal-body">
        <div class="">
        <div style="overflow: scroll;height: 400px !important;">
         <table class="table my-table xlstable table-striped table-bordered" id="multiedittabletrcount" style="width:100%;">
          <thead>
	         	<tr>
		            <th style="width: 1%;">Sr.no</th>
		            <th style="width: 10%;">Product Name</th>
		            <th style="width: 2%;">Pack</th>
		            <th style="width: 4%;">MRP</th>
		            <th style="width: 4%;">Sale Price</th>
		            <th style="width: 3%;">Pur. Price</th>
		            <th style="width: 4%;">Batch</th>
		            <th style="width: 3%;">Expiry Date</th>
		             <th style="width: 3%;">GST</th>
		            <th style="width: 2%;">P.Shelf</th>
		            <th style="width: 2%;">S.Shelf</th>
		            <th style="width: 10%;">Remark</th>
		            <th style="width: 1%;"></th>
	           	</tr>
          </thead>
          <tbody id="multiedittbody" style="">
           
          </tbody>
          </table>
          <input type="hidden" name="tcount" id="multieditcount">
          </div>
        </div>
        
      </div>
      
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" onclick="requestmultiedit()">Request Edit</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>


<!--  Confirm popup-->
<div id="chargeaddtopatient" class="modal fade" role="dialog" style="z-index: 10000 !important;">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body">
      <h4>Are you want to add as charge in patient account?</h4>
      <div class='form-inline'>
      <div class='form-group' style="padding-left: 37px;">
      <input type="button" class="btn btn-primary" value="Yes" onclick="sureaddcharge(true)">
      </div>
       <div class='form-group' style="padding-left: 129px;">
        <input type="button" class="btn btn-danger" value="No" data-dismiss="modal"  onclick="sureaddcharge(false)">
      </div>
      </div>
      </div>
     </div> 
</div>
</div>
    
	
<%-- <script>
$(function () 
		  $('[data-toggle="tooltip"]').tooltip()
		)
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script> --%>
	
</body>
</html>
