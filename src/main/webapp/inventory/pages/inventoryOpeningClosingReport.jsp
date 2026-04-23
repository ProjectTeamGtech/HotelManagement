<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<%  LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%>


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
    padding: 10px 10px 10px 0px;
}
</style>

</head>
<body>

<script>

function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Lab report",
					filename: "inventoryOpeningClosing",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}


	
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
	
	function changeClassOfTr(val,id){
		if(val==true){
			document.getElementById("trHiddenId"+id).className="";
		}else{
			document.getElementById("trHiddenId"+id).className="hidden-print";
		}
	}
	
	 function selectallCheckBox(val){
		if (val.checked == true) {
			$('.case').each(function() { // loop through each checkbox
				this.checked = true; // deselect all checkboxes with class
				document.getElementById("trHiddenId"+this.value).className="";						// "checkbox1"
			});
		} else {
			$('.case').each(function() { // loop through each checkbox
				this.checked = false; // deselect all checkboxes with class
				document.getElementById("trHiddenId"+this.value).className="hidden-print";						// "checkbox1"
			});
		}
		
	}  
	
</script>
									<div class="col-lg-12 col-md-12 topback2 ">
										<div class="col-md-12">
				                           <form class="form-inline" name="inventoryOpeningClosingProduct" method="post" id="myform" >
				                          		<s:hidden name="isfromcathlab"></s:hidden>
				                          		<s:hidden name="ismonthlyreport"></s:hidden>
				                          		<s:if test="isfromcathlab==1">
				                          			<s:if test="ismonthlyreport==1">
				                          				<div class="form-group">
					                           				<span class="text-uppercase"><b>Cathlab Monthly Opening Closing Report</b> &nbsp; - &nbsp;</span>
					                           			</div>
				                          			</s:if>
				                          			<s:else>
				                          			<div class="form-group">
					                           			<span class="text-uppercase"><b>Detailed Opening Closing Report</b> &nbsp; - &nbsp;</span>
					                           		</div>
				                          			</s:else>
				                          		</s:if>
				                          		<s:else>
				                          			<s:if test="ismonthlyreport==1">
				                          				<div class="form-group">
					                           				<span class="text-uppercase"><b>Inventory Opening Closing Report</b> &nbsp; - &nbsp;</span>
					                           			</div>
				                          			</s:if>
				                          			<s:else>
				                          			<div class="form-group">
					                           			<span class="text-uppercase"><b>Detailed Inventory Opening Closing Report</b> &nbsp; - &nbsp;</span>
					                           		</div>
				                          			</s:else>
				                          			
				                          		</s:else>
					                            
				                           		<div class="form-group hidden-print" style="width:9%;">
												  	<s:textfield cssClass="form-control" readonly="true" id="fromdate" name="fromdate" placeholder="From Date" cssStyle="width:100%;"/>
												</div>
												<div class="form-group hidden-print" style="width:9%;">
												  	<s:textfield cssClass="form-control" readonly="true" id="todate" name="todate" placeholder="To Date" cssStyle="width:100%;"/>
												</div>
												<div class="form-group hidden-print" style="width:9%;">
													  	<s:textfield cssClass="form-control" id="searchbyprodname" name="searchbyprodname" placeholder="Product name" cssStyle="width:100%;"/>
												</div>
												<s:if test="isfromcathlab==0">
													<div class="form-group hidden-print" style="width:15%;">
														<s:select list="locationListPharmacy"  name="location_filter" cssStyle="width:0px !important" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
										     			</s:select> 	  
													</div>
													<div class="form-group hidden-print" style="width:15%;">
														<s:select list="categoryList"  name="category_id" listKey="id" listValue="name" cssStyle="width:0px !important" cssClass="form-control chosen-select" id="categoryid" headerKey="0" headerValue="Select Category" /> 
													</div>
													<div class="form-group hidden-print" style="width:10%;">
														<s:select cssClass="form-control chosen-select" cssStyle="width:0px !important" list="#{'1':'Inventory Opening Closing', '2':'Opening'}" name="filter_status" /> 
													</div>
													
												</s:if>
												
												 <%-- <div class="form-group">
												<s:select name="countval" list="countlist" listKey="limit" listValue="limitstr" cssClass="form-control chosen-select" headerKey="0" headerValue="first 1000 " theme="simple"  ></s:select>
												</div>  --%>
												<div class="form-group hidden-print" style="width:3%;">
												  	<button type="submit" class="btn btn-primary">Go</button>
												</div>
												<div class="form-group hidden-print" style="width:3%;">
													<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
												</div>
												<div class="form-group hidden-print" style="width:3%;">
													<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
												</div>
												
												<s:hidden name="countval" id="countval"/>
													
				                           </form>
				                        </div>
				                       
				                       <!--  <div class="col-md-2 text-right" style="padding:0px;">
				                        	
				                        </div> -->
									

										</div>
										<%int u=1; %>
										<div class="col-lg-12 col-md-12" style="padding: 0px;">
										<s:if test="isfromcathlab==1">
										</s:if>
										<s:else>
											<s:if test="ismonthlyreport==1">
												<table class=" table-bordered hidden-print" cellspacing="0" width="100%" style="background-color: antiquewhite;color: black;">
													<tr class="tableback">
		          											<td style="width: 2%"></td>
						                          			<td style="width: 5%"></td>
						                          			<td style="width: 5%">Total Opening Stock: <s:property value="opentotalcount"/></td>
						                          			<td style="width: 5%">Total Opening Stock Value: <s:property value="totalopeningval"/></td>
						                          			<td style="width: 5%">Total Closing Stock: <s:property value="totalqty"/></td>
						                          			<td style="width: 5%">Total Closing Stock Value: <s:property value="total_amt"/></td>
		          									</tr>
												</table> 
											</s:if>
											<s:else>
												<%-- <table class=" table-bordered" cellspacing="0" width="100%" id="example" style="background-color: antiquewhite;color: black;">
													<tr class="tableback">
					                          			<td style="width: 2%"></td>
					                          			<td style="width: 5%"></td>
					                          			<td style="width: 5%">Total Opening Stock: <s:property value="opentotalcount"/></td>
					                          			<td style="width: 5%">Total Opening Stock Value: <s:property value="totalopeningval"/></td>
					                          			<td style="width: 5%">Total Closing Stock: <s:property value="totalqty"/></td>
					                          			<td style="width: 5%">Total Closing Stock Value: <s:property value="total_amt"/></td>
					                          		</tr>
												</table> --%>
											</s:else>
										</s:else>
											<table class="table table-bordered xlstable" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
					                            <s:if test="isfromcathlab==1">
					                            <!--for cathlab  -->
				                          			<thead>
					                                    <tr class="tableback">
					                                    	<th style="width: 3%;">
					                                    	<input type="checkbox" class="hidden-print" onclick="selectallCheckBox(this)">Sr.No</th>
					                                    	<th style="width: 8%;">Date</th>
					                                    	<th >Supplier Name</th>
					                                    	<th style="width: 8%;">Category</th>
					                                        <th style="width: 8%;">Sub-Category</th>
					                                    	<th style="width: 18%;">Product Name</th>
					                                        <th style="width: 8%;">Product Code</th>
					                                        <th style="width: 8%;">MFG</th>
					                                        <th style="width: 8%;">Expiry Date</th>
					                                        <th style="width: 8%;">Batch No</th>
					                                        <th style="width: 8%;">Min Order</th>
					                                        <th style="width: 8%;">Max Order</th>
					                                         <td class="my-table th" style="text-align:right;width: 5%;">MRP</td>
                                    						<td class="my-table th" style="text-align:right;width: 8%;">Purchase Price</td>
                                                            <td class="my-table th hidden" style="text-align:right;width: 7%;">Sale Price</td>
					                                        <td class="my-table th" style="text-align:right;width: 7%;">Sale Price</td>
					                                         
					                                        <th style="width: 5%;">Opening Stock</th>
					                                        <th style="width: 5%;">Consume</th>
					                                        <th style="width: 5%;">Closing Stock</th>
					                                        <th style="width: 5%;">Stock Value</th>
					                                        
					                                	</tr>
					                                </thead>
					                                <tfoot style="color: green;">
          												<tr>
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
          													<td></td>
          													<td></td>
          													
          													<td>Total:</td>
          													<td><s:property value="total_amt"/></td>
          												</tr>
          											</tfoot>
					                                <tbody>
					                                
					                                <s:iterator value="openingstockList">
					                                   <tr class="hidden-print"  id="trHiddenId<%=(u) %>">
					                                   <%-- 	<td><%=(u) %></td> --%>
					                                   	<td>
					                                   	<input type="checkbox" class="hidden-print case" onclick="changeClassOfTr(this.checked,<%=(u) %>)" value="<%=(u) %>"  />
					                                   	<s:property value="date"/></td>
					                                   	<td><s:property value="vendor"/></td>
					                                   	<td><s:property value="category"/></td>
					                                   	<td><s:property value="subcategory"/></td>
					                                   	<td><s:property value="product_name"/></td>
					                                   	<td><s:property value="pro_code"/></td>
					                                   
					                                   	<td><s:property value="mfg"/></td>
					                                   	<td><s:property value="expiry_date"/></td>
					                                   	<td><s:property value="batch_no"/></td>
					                                   	<td><s:property value="minorder"/></td>
					                                   	<td><s:property value="maxorder"/></td>
					                                   	<td class="text-right">Rs.<s:property value="mrp"/></td>
                                    					<td class="text-right">Rs.<s:property value="purchase_price"/></td>
                                    					<td class="text-right hidden">Rs.<s:property value="sale_price"/></td>
					                                    <td class="text-right">Rs.<s:property value="sale_price"/></td>
					                                   
					                                   	<td><s:property value="openingstock"/></td>
					                                   	<td><s:property value="sale"/></td>
					                                   	<td><s:property value="closingstock"/></td>
					                                   <td><s:property value="unit"/></td>
					                                   </tr>
					                                   <%u++; %>
					                                 </s:iterator>
					                                </tbody>
				                          		</s:if>
				                          		<s:else>
				                          		<s:if test="ismonthlyreport==1">
				                    				<thead>
				                          			    <tr class="tableback">
					                                    	<th style="width: 7%;">
					                                    	<input type="checkbox" class="hidden-print" onclick="selectallCheckBox(this)">Product Code</th>
					                                        <th style="width: 9%;">Category</th>
					                                        <th style="width: 13%;">Product Name</th>
					                                        <th style="width: 7%;">MFG</th>
					                                        <th style="width: 8%;">Location</th>
					                                        <th style="width: 8%;">Opening Stock</th>
					                                        <th style="width: 8%;">Opening Stock Value</th>
					                                        <th style="width: 4%;">Qty In</th>
					                                        <th style="width: 4%;">Qty Out</th>
					                                        <%
		                                                       if (loginInfo.isBalgopal()) {
		                                                    %>
					                                        <th class="hidden" style="width: 8%;">Sale Value</th>
					                                        <%}else{ %>
					                                        <th  style="width: 8%;">Sale Value</th>
					                                        <%} %>
					                                        <th style="width: 8%;">Closing Stock</th>
					                                        <th style="width: 8%;">Unit Pur Price</th>
					                                        <%
		                                                        if (loginInfo.isBalgopal()) {
		                                                    %>
					                                           <th class="hidden" style="width: 7%;">Closing Stock Value</th>
					                                         <%}else{ %>
					                                           <th style="width: 7%;">Closing Stock Value</th>
					                                         <%} %>
					                                	</tr>
					                                </thead>
					                                <tbody>
					                                <s:iterator value="openingstockList">
					                                   <tr class="hidden-print" id="trHiddenId<%=(u) %>">
						                                   	<td>
						                                   	<input type="checkbox" class="hidden-print case" onclick="changeClassOfTr(this.checked,<%=(u) %>)" value="<%=(u) %>"  />
						                                   	<s:property value="pro_code"/></td>
						                                    <td><s:property value="category"/></td>
						                                   	<td><s:property value="product_name"/></td>
						                                   	<td><s:property value="mfg"/></td>
						                                   	<td><s:property value="locationName"/></td>
						                                   	<td><s:property value="openingstock"/></td>
						                                   	<td><s:property value="openingstockvalue"/></td>
						                            		<td><s:property value="purchaseqty"/></td>
						                                   	<td><s:property value="sale"/></td>
						                                   	<%
		                                                        if (loginInfo.isBalgopal()) {
		                                                     %>
						                                   	<td class="hidden"><s:property value="salevalue"/></td>
						                                   	<%}else{ %>
						                                   	<td><s:property value="salevalue"/></td>
						                                   	<%} %>
						                                   	<td><s:property value="closingstock"/></td>
						                            		<td><s:property value="unitprices"/></td>
						                            		<%
		                                                        if (loginInfo.isBalgopal()) {
		                                                    %>
						                                   	    <td class="hidden"><s:property value="sv"/></td>
						                                   	<%}else{ %>
						                                   		<td><s:property value="sv"/></td>
						                                   	<%} %>
					                                   </tr>
					                                   <%u++; %>
					                                  </s:iterator>
					                                </tbody>
				                          		</s:if>
				                          		<s:else>
				                          		      			<thead>
				                          			
					                                    <tr class="tableback">
					                                    	<!--<th style="width: 3%;">Sr.No</th> -->
					                                       <th style="width: 13%;">
					                                       <input type="checkbox" class="hidden-print" onclick="selectallCheckBox(this)">Product Name</th>
					                                        <th style="width: 3%;">Opening Stock</th>
					                                         <th style="width: 3%;">Opening Stock Value</th>
					                                        <!-- <th style="width: 2%;">Sale</th>
					                                        <th style="width: 2%;">Return</th>
					                                        <th style="width: 2%;">Indent Qty</th> -->
					                                         <th style="width: 4%;">Qty In</th>
					                                        <th style="width: 4%;">Qty Out</th>
					                                        <th style="width: 3%;">Closing Stock</th>
					                                         <th style="width: 3%;">Closing Stock Value</th>
					                                         <th style="width: 10%;">Supplier Name</th>
					                                         <th style="width: 5%;">MFG</th>
					                                         <th style="width: 8%;">EXP</th>
					                                         <th style="width: 5%;">Batch No.</th>
					                                         <th style="width: 5%;">MRP</th>
					                                         <th style="width: 5%;">MIN Order</th>
					                                         <th style="width: 5%;">MAX Order</th>
					                                         <th style="width: 5%;">Pur. Price</th>
					                                         <th style="width: 5%;">Sale Price</th>
					                                         <th style="width: 5%;">Pur Stock Qty</th>
					                                	</tr>
					                                </thead>
					                                <tbody>
					                                	<%-- <tfoot style="color: green;">
          												<tr>
          													<td style="width: 15%;">Total</td>
          													<td style="width: 5%;"><s:property value="opentotalcount"/></td>
          													<td style="width: 5%;"><s:property value="totalopeningval"/></td>
          													<td style="width: 8%;"></td>
          													<td style="width: 8%;"></td>
          													<td style="width: 5%;"><s:property value="totalqty"/></td>
          													<td style="width: 5%;"><s:property value="total_amt"/></td>
          													<td></td>
          													<td></td>
          													<td></td>
          													<td></td>
          													<td></td>
          													<td></td>
          													<td></td>
          													<td></td>
          												</tr>
          											</tfoot> --%>
					                                <s:iterator value="openingstockList">
					                                   <tr class="hidden-print" id="trHiddenId<%=(u) %>">
					                                   	<!--<td>1</td>
					                                   	--><td>
					                                   	<input type="checkbox" class="hidden-print case" onclick="changeClassOfTr(this.checked,<%=(u) %>)" value="<%=(u) %>"  />
					                                   	<s:property value="product_name"/></td>
					                                   	<td><s:property value="openingstock"/></td>
					                                   	<td><s:property value="openingstockvalue"/></td>
					                                   <%-- 	<td><s:property value="sale"/></td>
					                                   	<td><s:property value="returnstock"/></td>
					                                   	<td><s:property value="indentqty"/></td> --%>
					                                   	<td><s:property value="purchaseqty"/></td>
						                                   	<td><s:property value="sale"/></td>
					                                   	<td><s:property value="closingstock"/></td>
					                                   	<td><s:property value="unit"/></td>
					                                   	<td><s:property value="vendor"/></td>
          												<td ><s:property value="mfg"/></td>
          												<td ><s:property value="expiry_date"/></td>
          												<td ><s:property value="batch_no"/></td>
          												<td ><s:property value="mrp"/></td>
          												<td ><s:property value="minorder"/></td>
          												<td ><s:property value="maxorder"/></td>
          												
          												<td ><s:property value="purchase_price"/></td>
          												<td ><s:property value="sale_price"/></td>
          												<td ><s:property value="purchase_stock_qty"/></td>
          													
					                                   </tr>
					                                   <%u++; %>
					                                   </s:iterator>
					                                </tbody>
				                          	
					                                </s:else>
				                          		</s:else>
					                                
					                                
					                            </table><br>
					                           <%--  opencPopup('inventoryOpeningClosingProduct?limit=<s:property value="limit"/>') --%>
					                            <center class="hidden-print">pages:<s:iterator value="countlist">
					                            
					                           <button class="btn btn-primary" onclick='setpglimit(<s:property value="limit"/>)' ><s:property value="limit"/></button>&nbsp;&nbsp;
					                         
					                            </s:iterator>
					                            </center>
					                            	<s:form action="inventoryOpeningClosingProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromdate"></s:hidden>
							     <s:hidden name="todate"></s:hidden>
							     <s:hidden name="isfromcathlab"></s:hidden>
							     <s:hidden name="filter_status"></s:hidden>
							     <s:hidden name="searchbyprodname"></s:hidden>
							     <s:hidden name="location_filter"></s:hidden>
							     <s:hidden name="category_id"></s:hidden>
									<div class="col-lg-12 hidden-print" style="padding:0px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
									page :<label class="text-info"><s:property value="page" /></label>
									<br>
										Total:<label class="text-info"><s:property value="totalRecords" />
											Records
										</label>
									</div>
									<%-- <jsp:include page="/common/pages/pagination.jsp"></jsp:include> --%>
								</div>
							</s:form> 
										</div>


</body>
<script>
function setpglimit(val){
	document.getElementById('countval').value=val;
	document.getElementById("myform").submit();
}
</script>


</html>