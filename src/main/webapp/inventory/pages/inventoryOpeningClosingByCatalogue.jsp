<%@taglib uri="/struts-tags" prefix="s" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
 <script type="text/javascript" src="common/js/pagination.js"></script>
 <SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
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
		
		var val = document.getElementById("filter_bydate").value;
		setDateRange(val);
	});
	
	function setDateRange(val) {
		var preYear = val.split("-")[0];
		var curYear = val.split("-")[1];
		var minYear = "01-04-"+preYear;
		var maxYear = "31-03-"+curYear;
		$('#fromdate').datepicker('option', 'minDate', minYear);
		$('#fromdate').datepicker('option', 'maxDate', maxYear);
		$('#todate').datepicker('option', 'minDate', minYear);
		$('#todate').datepicker('option', 'maxDate', maxYear);
		
		/* $('#fromdate').datepicker('setEndDate', maxYear);//1st set  end date
		$('#fromdate').datepicker('setStartDate', minYear);// */
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
	 function changeClassOfTr(val,id){
			if(val==true){
				document.getElementById("trHiddenId"+id).className="";
			}else{
				document.getElementById("trHiddenId"+id).className="hidden-print";
			}
		}
	
	
	
	
	
	
	
</script>
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

									<div class="col-lg-12 col-md-12 topback2 hidden-print">
										<!-- <div class="col-md-12"> -->
				                           <form class="form-inline" name="inventoryOpeningClosingBycatalogueProduct" method="post" id="myform" >
				                          		<div class="form-group">
					                           		<span class="text-uppercase"><b>Inventory Opening Closing By Catalogue Wise Report</b> &nbsp; - &nbsp;</span>
					                           	</div>
				                          		<br>
				                          		<s:hidden name="iscalculationbase"></s:hidden>
				                          		<div class="form-group" style="width:7%;">
													<s:select cssClass="form-control" list="#{'2015-2016':'2015-2016','2016-2017':'2016-2017','2017-2018':'2017-2018','2018-2019':'2018-2019','2019-2020':'2019-2020','2020-2021':'2020-2021','2021-2022':'2021-2022','2022-2023':'2022-2023'}" onchange="setDateRange(this.value)" id="filter_bydate" name="filter_bydate" />
												</div> 
				                           		<div class="form-group" style="width:9%;">
												  	<s:textfield cssClass="form-control" readonly="true" id="fromdate" name="fromdate" placeholder="From Date" cssStyle="width:100%;"/>
												</div>
												<div class="form-group" style="width:9%;">
												  	<s:textfield cssClass="form-control" readonly="true" id="todate" name="todate" placeholder="To Date" cssStyle="width:100%;"/>
												</div>
												<div class="form-group" style="width:9%;">
												  	<s:textfield cssClass="form-control" id="searchbyprodname" name="searchbyprodname" placeholder="Product name" cssStyle="width:100%;"/>
												</div>
												<div class="form-group" style="width:15%;">
													<s:select list="locationListPharmacy"  name="location_filter" id="location_filter" cssStyle="width:0px !important" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
										     		</s:select> 	  
												</div>
												
												<%-- <div class="form-group" style="width:15%;">
													<s:select list="categoryList"  name="category_id" listKey="id" listValue="name" cssStyle="width:0px !important" cssClass="form-control chosen-select" id="categoryid" headerKey="0" headerValue="All" /> 
												</div> --%>
												<%-- <div class="form-group" style="width:10%;">
													<s:select cssClass="form-control chosen-select" cssStyle="width:0px !important" list="#{'1':'Inventory Opening Closing', '2':'Opeining'}" name="filter_status" /> 
												</div> --%>
												
												<div class="form-group" >
												  	<button type="submit" class="btn btn-primary">Go</button>
													<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
													<a href="#" onclick="printpage();" class="btn btn-primary hidden-print"><i class="fa fa-print"></i></a>
												
												</div>
												<div class="form-group" >
													<span style="color:red;">Note: Due to heavy data it takes time for first time loading. </span>
												</div>
												<s:hidden name="countval" id="countval"/>
													
				                           </form>
				                       <!--  </div> -->
				                       
				                       <!--  <div class="col-md-2 text-right" style="padding:0px;">
				                        	
				                        </div> -->
									

										</div>
										
										<div class="col-lg-12 col-md-12" style="padding: 0px;">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-sm hidden-md visible-print" style="padding: 0px;">
											<center><h3>Inventory Opening Closing By Catalogue Wise Report </h3> </center>	
										</div>
												<table class=" table-bordered" cellspacing="0" width="100%" style="background-color: antiquewhite;color: black;">
													 <tr class="tableback">
													 		<td style="width: 3%;margin: 0;padding: 0;outline: 0;"></td>
					                                    	<td style="width: 6%;margin: 0;padding: 0;outline: 0;"></td>
					                                        <td style="width: 7%;margin: 0;padding: 0;outline: 0;"></td>
					                                        <td style="width: 10%;margin: 0;padding: 0;outline: 0;"></td>
					                                        <td style="width: 6%;margin: 0;padding: 0;outline: 0;"></td>
					                                        <td style="width: 7%;text-align: right;margin: 0;padding: 0;outline: 0;"><s:property value="totalopeningstock"/></td>
					                                        <td style="width: 9%;text-align: right;margin: 0;padding: 0;outline: 0;"><s:property value="totalopeingstockvalue"/></td>
					                                        <td style="width: 4%;text-align: right;margin: 0;padding: 0;outline: 0;"><s:property value="totalqtyin"/></td>
					                                        <td style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;"><a href="#" onclick="calofInventoryOpeningInvalue()"><s:property value="totalqtyinvalue"/></a></td>
					                                        <td style="width: 2%;text-align: right;margin: 0;padding: 0;outline: 0;"><!-- <a href="#" onclick="calofInventoryOpening()"> --><s:property value="totalqtyout"/><!-- </a> --></td>
					                                        <!-- <a href="#" onclick="calofInventoryOpening()"><s:property value="totalstockvalue"/></a>  -->
					                                        <td style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;"><a href="#" onclick="calofInventoryOpeningOutward()"><s:property value="totalstockvalue"/></a></td>
					                                        <s:if test="iscalculationbase==0">
					                                        	<td style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;"><a href="#" onclick="calofInventoryOpening()"><s:property value="totalssaleprice"/></a></td>
					                                        </s:if>
					                                       
					                                        <td style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;"><s:property value="totalunknownqty"/></td>
					                                        <td style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;"><s:property value="totalunknownvalue"/></td>
					                                        <td style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;">
					                                        	<a href="#" onclick="totalclosingstockpop()"><s:property value="totalclosingstock"/></a>
					                                        		
					                                        </td>
					                                        <td style="width: 9%;text-align: right;margin: 0;padding: 0;outline: 0;"><s:property value="totalclosingvalue"/></td>
					                                         <td style="width: 4%;margin: 0;padding: 0;outline: 0;"><a href="#" onclick="openBlankPopup('bincardreportnewProduct?catalogueid=<s:property value="catalogueid"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&isfromoepning=1')">Click</a></td>
					                                         <td style="width: 4%;margin: 0;padding: 0;outline: 0;"><a href="#" onclick="openBlankPopup('itemwisereportProduct?catalogueid=<s:property value="catalogueid"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&isfromoepning=1')">Click</a></td>
					                                	</tr>
												</table> 
										
											<table class="table table-bordered xlstable" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
					                           <thead>
				                          			
					                                    <tr class="tableback">
					                                    	<tr class="tableback">
					                                    	<th style="width: 3%;margin: 0;padding: 0;outline: 0;">
					                                    	<input type="checkbox" class="hidden-print" onclick="selectallCheckBox(this)">Sr.</th>
					                                    	<th style="width: 6%;margin: 0;padding: 0;outline: 0;">Prod. Code</th>
					                                        <th style="width: 7%;margin: 0;padding: 0;outline: 0;">Category</th>
					                                        <th style="width: 10%;margin: 0;padding: 0;outline: 0;">Product Name</th>
					                                        <th style="width: 6%;margin: 0;padding: 0;outline: 0;">MFG</th>
					                                        <!-- <th style="width: 8%;">Location</th> -->
					                                        <th style="width: 7%;text-align: right;margin: 0;padding: 0;outline: 0;">Opening Stock</th>
					                                        <th style="width: 9%;text-align: right;margin: 0;padding: 0;outline: 0;">Opening Stock Value</th>
					                                        <th style="width: 4%;text-align: right;margin: 0;padding: 0;outline: 0;">Qty In</th>
					                                        <th style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;">Inward Value</th>
					                                        <th style="width: 4%;text-align: right;margin: 0;padding: 0;outline: 0;">Qty Out</th>
					                                        <th style="width: 7%;text-align: right;margin: 0;padding: 0;outline: 0;">Outward Value</th>
					                                        <s:if test="iscalculationbase==0">
					                                        	 <th style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;">Sale Value</th>
					                                        </s:if>
					                                       
					                                        <th style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;">Unknown Qty</th>
					                                        <th style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;">Unknown Value</th>
					                                        <th style="width: 6%;text-align: right;margin: 0;padding: 0;outline: 0;">Closing Stock</th>
					                                        <!-- <th style="width: 8%;">Unit Pur Price</th> -->
					                                        <th style="width: 9%;text-align: right;margin: 0;padding: 0;outline: 0;">Closing Stock Value</th>
					                                    	<th style="width: 4%;margin: 0;padding: 0;outline: 0;">Bin Card</th>
					                                    	<th style="width: 4%;margin: 0;padding: 0;outline: 0;">Item Wise Sale Report</th>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                <%int counting=1; %>
					                                <s:iterator value="openingstockList">
					                                   <tr  class="hidden-print"  id="trHiddenId<%=(counting) %>">
					                                   	<%-- <td><%=(++counting) %></td> --%>
					                                   	<td>
					                                   	<input type="checkbox" class="hidden-print case" onclick="changeClassOfTr(this.checked,<%=(counting) %>)" value="<%=(counting) %>"  />
					                                   	<s:property value="pro_code"/></td>
					                                    <td><s:property value="category"/></td>
					                                   	<td><s:property value="product_name"/></td>
					                                   	<td><s:property value="mfg"/></td>
					                                   <%-- 	<td><s:property value="locationName"/></td> --%>
					                                   	<td style="text-align: right;"><s:property value="openingstock"/></td>
					                                   	<td style="text-align: right;"><s:property value="openingstockvalue"/></td>
					                            		<td style="text-align: right;"><s:property value="purchaseqty"/></td>
					                            		<td style="text-align: right;"><s:property value="qtyinvalue"/></td>
					                                   	<td style="text-align: right;"><s:property value="sale"/></td>
					                                   	<td style="text-align: right;"><s:property value="salevalue"/></td>
					                                   	 <s:if test="iscalculationbase==0">
					                                        	 <td style="text-align: right;"><s:property value="sale_price"/></td>
					                                     </s:if>
					                                   	
					                                   	<td style="text-align: right;"><s:property value="unknownqty"/></td>
					                                   	<td style="text-align: right;"><s:property value="unknownvalue"/></td>
					                                   	<td style="text-align: right;"><s:property value="closingstock"/></td>
					                            		<%-- <td><s:property value="unitprices"/></td> --%>
					                                   	<td style="text-align: right;"><s:property value="sv"/></td>
					                                   	<td ><a href="#" onclick="openBlankPopup('bincardreportnewProduct?catalogueid=<s:property value="catalogueid"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&isfromoepning=1')">Click</a></td>
					                                   	<td ><a href="#" onclick="openBlankPopup('itemwisereportProduct?catalogueid=<s:property value="catalogueid"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&isfromoepning=1')">Click</a></td>
					                                   </tr>
					                                   <%counting++; %>
					                                   </s:iterator>
					                                </tbody>
				                          		    
					                                
					                            </table><br>
					                            <%-- <center>pages:<s:iterator value="countlist">
					                            
					                           <button class="btn btn-primary" onclick='setpglimit(<s:property value="limit"/>)' ><s:property value="limit"/></button>&nbsp;&nbsp;
					                         
					                            </s:iterator> 
					                            </center>--%>
					                            
					             			<%-- <s:if test="iscalculationbase==0"> --%>
					                            <s:form action="inventoryOpeningClosingBycatalogueProduct" name="paginationForm" id="paginationForm" theme="simple">
							    				     <s:hidden name="fromdate"></s:hidden>
												     <s:hidden name="todate"></s:hidden>
												     <s:hidden name="isfromcathlab"></s:hidden>
												     <s:hidden name="filter_status"></s:hidden>
												     <s:hidden name="searchbyprodname"></s:hidden>
												     <s:hidden name="location_filter"></s:hidden>
												     <s:hidden name="category_id"></s:hidden>
													 <s:hidden name="pagerecords"></s:hidden>
													 <s:hidden name="page_records"></s:hidden>
													 <s:hidden name="iscalculationbase"></s:hidden>
													 <s:hidden name="filter_bydate"></s:hidden>
													<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
														Total:<label class="text-info"><s:property value="totalRecords" />
															Records
														</label>
													</div>
													<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
												</s:form>
											<%-- </s:if>  --%>
										</div>
										
		<div class="modal fade popoverpop" id="salepricetotalmodel" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Sale Value Total</h5>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12">
						
						<div class="col-lg-12 col-md-12 col-xs-12">
								<div class="form-group">
								    <label for="exampleInputEmail1">Pharmacy Sale Without Discount Remove:</label>
								   	<label id="pharmacysalepricediv"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Direct Transfer Sale Price:</label>
								   	<label id="directsalepricediv"></label>
								  </div>
								  
								   <div class="form-group">
								    <label for="exampleInputEmail1">Indent Sale Price:</label>
								   	<label id="requestsalepricediv"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Return To Store:</label>
								   	<label id="returnsalepricediv"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Supplier Return:</label>
								   	<label id="returnsupliersalepricediv"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Consume:</label>
								   	<label id="consumesalepricediv"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Adjustment Process Out:</label>
								   	<label id="adjustsalepricediv"></label>
								  </div>
						</div>
								
						</div>
					
					</div>
                      
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Ok</button>
				</div>
			
			</div>
		</div>
	</div>
	
	<div class="modal fade popoverpop" id="outwardvaluetotalmodel" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Outward Value Total</h5>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12">
						
						<div class="col-lg-12 col-md-12 col-xs-12">
								<div class="form-group">
								    <label for="exampleInputEmail1">Pharmacy Sale Without Discount Remove:</label>
								   	<label id="pharmacysalepricediv1"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Direct Transfer Sale Price:</label>
								   	<label id="directsalepricediv1"></label>
								  </div>
								  
								   <div class="form-group">
								    <label for="exampleInputEmail1">Indent Sale Price:</label>
								   	<label id="requestsalepricediv1"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Return To Store:</label>
								   	<label id="returnsalepricediv1"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Supplier Return:</label>
								   	<label id="returnsupliersalepricediv1"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Consume:</label>
								   	<label id="consumesalepricediv1"></label>
								  </div>
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Adjustment Process Out:</label>
								   	<label id="adjustsalepricediv1"></label>
								  </div>
						</div>
								
						</div>
					
					</div>
                      
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Ok</button>
				</div>
			
			</div>
		</div>
	</div>
	
	<div class="modal fade popoverpop" id="qtyinvaluetotalmodel" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Inward Value Total</h5>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12">
						
						<div class="col-lg-12 col-md-12 col-xs-12">
								<div class="form-group">
								    <label for="exampleInputEmail1">GRN Purchase Price:</label>
								   	<label id="purchasevaluediv"></label>
								  </div>
								  <div class="form-group">
								    <label for="exampleInputEmail1">GRN Free Qty Purchase Price:</label>
								   	<label id="purchasefreevaluediv"></label>
								  </div>
								<div class="form-group">
								    <label for="exampleInputEmail1">Pharmacy Return Without Discount Remove:</label>
								   	<label id="pharmacyreturnvaluediv"></label>
								  </div>
								  
								  <div class="form-group hidden">
								    <label for="exampleInputEmail1">Direct Transfer Purchase Price:</label>
								   	<label id="directtranfervaluediv"></label>
								  </div>
								  
								   <div class="form-group hidden">
								    <label for="exampleInputEmail1">Indent Purchase Price:</label>
								   	<label id="requesttransfervaluediv"></label>
								  </div>
								  
								  <div class="form-group hidden">
								    <label for="exampleInputEmail1">Return To Store:</label>
								   	<label id="returntostorevaluediv"></label>
								  </div>
								  
								  
								  <div class="form-group">
								    <label for="exampleInputEmail1">Adjustment Process IN:</label>
								   	<label id="adjustmentvaluediv"></label>
								  </div>
						</div>
								
						</div>
					
					</div>
                      
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Ok</button>
				</div>
			
			</div>
		</div>
	</div>
	
	
	<div class="modal fade popoverpop" id="closing_stock_popup" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Closing Stock</h5>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12">
						
						<div class="col-lg-12 col-md-12 col-xs-12">
								<div class="form-group">
								    <label for="exampleInputEmail1">Opening Stock(+):</label>
								   	<label><s:property value="totalopeningstock"/></label>
								  </div>
								  <div class="form-group">
								    <label for="exampleInputEmail1">Qty In(+):</label>
								   	<label><s:property value="totalqtyin"/></label>
								  </div>
								<div class="form-group">
								    <label for="exampleInputEmail1">Qty Out(-):</label>
								   	<label><s:property value="totalqtyout"/></label>
								  </div>
								  <div class="form-group">
								    <label for="exampleInputEmail1">Unknown Qty(+):</label>
								   	<label><s:property value="totalunknownqty"/></label>
								  </div>
								   <div class="form-group">
								    <label for="exampleInputEmail1">Closing Stock:</label>
								   	<label><s:property value="totalclosingstock"/></label>
								  </div>
						</div>
								
						</div>
					
					</div>
                      
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Ok</button>
				</div>
			
			</div>
		</div>
	</div>


</body>
<%-- <script>
function setpglimit(val){
	document.getElementById('countval').value=val;
	document.getElementById("myform").submit();
}
</script> --%>


</html>