<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style tyle="text/css">
@media print {
	body {
		font-size: 12px !important;
	}
	h5,.h5 {
		font-size: 8px !important;
	}
}

</style>
<style>
	.chosen-container {
    width: 100% !important;
}
	 b, strong {
    font-weight: 900;
}   
</style>

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
</style>

<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<SCRIPT type="text/javascript">

	function printReport() {
				
				  $("#page_printer").table2excel({
					exclude: ".noExl",
					name: "Indent Report",
					filename: "Indent_Report",
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
  

</SCRIPT>

</head>
<body>
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
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print"
	style="padding: 0px;">
<div class="col-lg-12 col-md-12 paddingnil">
	<s:form theme="simple" cssClass="form-inline search" action="indentreportProduct">
	<div class="col-lg-12 col-md-12 topback2">
	
	<!-- div class="col-lg-10 col-md-10"> -->
		<form class="form-inline search">
		<div class="form-group">
			<span class="text-uppercase"><b>Indent  Report</b> &nbsp; - &nbsp;</span>
		</div>
	<div class="form-group">
		<s:textfield name="searchtext"  cssClass="form-control" placeholder="BY ID" />
	</div>
	<div class="form-group" style="width:9%">
		<s:textfield name="fromdate" id="fromdate" readonly="true"  cssClass="form-control" placeholder="From Date" cssStyle="width:100%;"/>
	</div>
	<div class="form-group" style="width:9%">
		<s:textfield name="todate" id="todate" readonly="true" cssClass="form-control" placeholder="To Date" cssStyle="width:100%;"/>
	</div>
	<%--  <div class="form-group">
		<s:select  cssClass="form-control"  list="#{'0':'Request' ,'1':'Direct', '2':'Return'}" name="filter_status" />
	</div> --%>
	 <div class="form-group" style="width:15%">
		<s:select headerKey="0" headerValue="To Requested Warehouse" cssClass="form-control chosen-select" list="locationlist" name="location_filter" listKey="id" listValue="name"/>
	</div> 
	 <div class="form-group" style="width:15%">
		<s:select headerKey="0" headerValue="Select User" cssClass="form-control chosen-select" list="userlist" name="userwise" listKey="userid" listValue="fullname"/>
	</div> 
	<div class="form-group">
						  			<s:select headerKey="10" headerValue="Select Status" cssClass="form-control " list="#{'0':'Request', '1':'Approved', '2':'Rejected', '3':'Delivered' , '4':'Received', '5':'PO Created', '6':'Pending','7':'Ready To Transfer','8':'Direct Transfer','9':'Return'}" name="filter_status" />		
								</div>
	<div class="form-group">
		<button type="submit" class="btn btn-primary">Go</button>
		<a type="button" id="btnxls" title="Save As XLS" onclick="printReport()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
		&nbsp;<a href="#" onclick="printpage();" class="btn btn-primary hidden-print"><i class="fa fa-print"></i></a>
	</div>
	
	</form>
	<!-- </div> -->
	<!-- 
	<div class="col-lg-2 col-md-2">
	
	</div> -->
	

	
	</div>
	</s:form>
</div>

</div>
<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
<div id="page_printer">
<h4 class="hidden-lg hidden-md hidden-sm hidden-xs visible-print"
	style="text-align: center; border-bottom: 4px double #ddd; line-height: 30px;"><b>Indent  Report</b></h4>

	<table class="table table-bordered table-striped" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
		<thead>
			<tr class="tableback">
				<!-- <th class="thcoi" style="width: 4% !important;">Sr.No</th>
				<th class="thcoi" style="width: 5% !important;">ID</th>
                <th class="thcoi" style="width: 12% !important;">Request Date</th>
                <th class="thcoi" style="width: 17% !important;">Transfer From</th>
                <th class="thcoi" style="width: 11% !important;">Req/Tran</th>
                <th class="thcoi" style="width: 17% !important;">Transfer To</th>
                <th class="thcoi" style="width: 10% !important;">Issued Date</th>
                <th class="thcoi" style="width: 10% !important;">Userid</th>
                <th class="thcoi" style="text-align: center;width: 17% !important;">Indent No</th>
                 <th class="thcoi" style="text-align: center;width: 17% !important;"></th> -->
               
               <th class="thcoi" >Sr.No</th>
				<th class="thcoi" >ID</th>
                <th class="thcoi" >Request Date</th>
                <th class="thcoi" >Transfer From</th>
                <th class="thcoi" >Req/Tran</th>
                <th class="thcoi" >Transfer To</th>
                <th class="thcoi" >Issued Date</th>
                <th class="thcoi" >Userid</th>
                <th class="thcoi" style="text-align: center;">Indent No</th>
                 <th class="thcoi" style="text-align: center;"></th>
               
             </tr>
		</thead>
		<tbody>
			
			<%
				int i = 0;
			%>
			<s:iterator value="parenttransferlist">
				<tr>
					<td><b><%=(++i)%></b></td>
					<td><b><s:property value="parentid"/></b></td>
					<td><b><s:property value="request_date"/></b></td>
					<td><b><s:property value="from_location"/></b></td>
					<td>
					<b>
						<s:if test="req_or_transfer==0">
              				REQUEST
              			</s:if>
              			<s:elseif test="req_or_transfer==1">
              				DIRECT
              			</s:elseif>
              			<s:else>
              				RETURN
              			</s:else>
              		</b>
              		</td>
              		<td>
              		<b>
              			<s:if test="req_or_transfer==0">
              				<s:property value="from_location"/>
              			</s:if>
              			<s:elseif test="req_or_transfer==1">
              				<s:property value="to_location"/>
              			</s:elseif>
              			<s:else>
              				<s:property value="to_location"/>
              			</s:else>
              			</b>
              		</td>
					
              		<td>
              		<b>
              			<s:if test="req_or_transfer==0">
              				
              			</s:if>
              			<s:elseif test="req_or_transfer==1">
              				<s:property value="issued_date"/>
              			</s:elseif>
              			<s:else>
              				<s:property value="issued_date"/>
              			</s:else>
              			</b>
              		</td>
              		<td>
              		<b>
              			<s:property value="userid"/>
              			</b>
              		</td>
					<td style="text-align: center;">
					<b>
						<s:if test="req_or_transfer==0">
							<a href="#" onclick="openDisplayPopup('deliverPrintProduct?id=<s:property value="parentid"/>&status=0&mainstatus=6')" ><s:property value="parentid"/></a>
              			</s:if>
              			<s:elseif test="req_or_transfer==1">
              				<a href="#" onclick="openDisplayPopup('deliverPrintDirectProduct?id=<s:property value="parentid"/>&mainstatus=6')" ><s:property value="parentid"/></a>
              			</s:elseif>
              			<s:else>
              				<a href="#" onclick="openDisplayPopup('deliverPrintDirectProduct?id=<s:property value="parentid"/>&mainstatus=6')" >0</a>
              			</s:else>
						</b>	
              		</td>
              		<%-- <td style="text-align: center;">	
              			<s:if test="req_or_transfer==0">
              			</s:if>
              			<s:elseif test="req_or_transfer==1">
              				<a href="#" onclick="openDisplayPopup('deliverPrintDirectProduct?id=<s:property value="parentid"/>&mainstatus=6')" ><s:property value="transfer_indentno"/></a>
              			</s:elseif>
              			<s:else>
              				<a href="#" onclick="openDisplayPopup('deliverPrintDirectProduct?id=<s:property value="parentid"/>&mainstatus=6')" >0</a>
              			</s:else>
							              		
              		</td> --%>
              		<td></td>
				</tr>
				<tr>
				<td></td>
				<td colspan="9">
				<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Indent Request :-</h5>
					<!-- <table class="table table-striped table-bordered" style="width:100%;"> -->
			         <thead>
			           <tr>
			          <!-- class="thcoi" -->
			            <%-- <th  style="width: 2%;">Sr.no</th>
			            <th style="width: 4%;">ID</th>
			            <th style="width: 20%;">Product Name</th>
			            <th style="width: 4%;">Avail Qty</th>
			             <th style="width: 4%;">Req Qty</th>
			            <th class="hidden" style="width: 10%;">Sale Price</th>
			            <th class="hidden" style="width: 7%;">Price</th>
			            <th style="width: 4%;">Approved Qty</th>
			            <th style="width: 6%;">Expected Date</th>
			            <th style="width: 18%;">Business Reason </th>
			           	<s:if test="mainstatus==4">
			            	<th style="width: 9%;">Status</th>
			            </s:if>
			            <th style="width: 9%;">Transfer Date</th>
			            <th style="width: 6%;">Trans. Qty</th>
			            --%>
			            
			            <th  >Sr.no</th>
			            <th >ID</th>
			            <th >Product Name</th>
			            <th >Avail Qty</th>
			             <th >Req Qty</th>
			            <th class="hidden" >Sale Price</th>
			            <th class="hidden" >Price</th>
			            <th >Approved Qty</th>
			            <th >Expected Date</th>
			            <th >Business Reason </th>
			           	<s:if test="mainstatus==4">
			            	<th >Status</th>
			            </s:if>
			            <th >Transfer Date</th>
			            <th >Trans. Qty</th>
			            
			            </tr>
			          </thead>
			          <tbody>
			          	<%int k=0; %>
			          	<s:iterator value="requestedmedicineList">
			          	<tr>
			          		<td><%=++k%></td>
			          		<td><s:property value="product_id"/></td>
			          		<td><s:property value="product_name"/></td>
			          		<td><s:property value="avail_qty"/></td>
			          		<td><s:property value="requested_qty"/></td>
			          		<td class="hidden"><s:property value="unit"/></td>
			          		<td><s:property value="stock"/></td>
			          		<td><s:property value="expectedDate"/></td>
			          		<td><s:property value="comment"/>  
			            		<s:if test="mainstatus!=4">
			          				 <s:if test="status==0">
			          					
			          					<s:if test="cancel_req==0">
			            					<span><b>- NA</b></span>
			            				</s:if>
			            				<s:else>
			            					<span><b>Cancelled</b></span>
			            				</s:else>
			          				</s:if> 
			          			</s:if>
			          		</td>
			          		<s:if test="mainstatus==4">
			          			<td>
			            			<s:if test="status==0">
			            				<s:if test="cancel_req==0">
			            					<span><b>Not Available</b></span>
			            				</s:if>
			            				<s:else>
			            					<span><b>Cancelled</b></span>
			            				</s:else>
			          					
			          				</s:if>
			          				<s:else>
			          					<span><b>Transfered</b></span>
			          				</s:else>
			          				</td>
			            		</s:if>
			            	<td><s:property value="transfer_date"/></td>
			            	<td><s:property value="totaltransferqt"/></td>
			            	
			          	</tr>
			          	</s:iterator>
			          	</tbody>
			         <!-- </table> -->
				</td>
				</tr>
				<s:if test="%{checkmedicinelist.isEmpty()}">
				
				</s:if>
				<s:else>
					<tr>
					<td></td>
					<td colspan="9">
						<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Indent Transfer Status :-</h5>
						<!-- <table class="table table-striped table-bordered" style="width:100%;" id="prodtableid"> -->
				          <thead>
				           <tr>
				            <th >Sr.no</th>
				            <th >HSN Code</th>
				            <th  class="hidden">Id</th>
				            <th >Product Name</th>
				            <th >Batch No</th>
				            <th >Exp Date</th>
				            <th >Issue Qty</th>
				            <th style="width: 0%;text-align: right;" class="hidden">Unit Rate</th>
				            <th style="width: 0%;text-align: right;" class="hidden">Amount</th>
				            <th style="width: 0%;text-align: right;" class="hidden">MRP</th>
				            <th style="width: 0%;text-align: right;" class="hidden">MRP Amount</th>
				           	<th >Location/Date</th>
				           	<th colspan="3"></th>
				            </tr>
				          </thead>
				          <tfoot style="color: green;">
				          	<tr>
				          		<td></td>
				          		<td></td>
				          		<td></td>
				          		<td class="hidden"></td>
				          		<td></td>
				          		<td></td>
				          		<td class="hidden" style="font-weight: bold;">Total</td>
				          		<td></td>
				          		<td class="hidden" style="text-align: right;font-weight: bold;"><s:property value="total_amount"/></td>
				          		<td></td>
				          		<td class="hidden" style="text-align: right;font-weight: bold;"><s:property value="totolmrp"/></td>
				          		<td></td>
				          		<td colspan="3"></td>
				          	</tr>
				          </tfoot>
				          
				          <tbody>
				          	<%int m=0; %>
				          	<s:iterator value="checkmedicinelist">
				          	<tr>
				          		<td><%=++m%></td>
				          		<td><s:property value="hsnno"/></td>
				          		<td class="hidden"><s:property value="product_id"/></td>
				          		<td><s:property value="product_name"/></td>
				          		<td><s:property value="batch_no"/></td>
				          		<td><s:property value="expiry_date"/></td>
				          		<td><s:property value="stock"/></td>
				          		<td style="text-align: right;"class="hidden" ><s:property value="sale_price"/></td>
				          		<td style="text-align: right;" class="hidden"><s:property value="amountno"/></td>
				          		<td style="text-align: right;" class="hidden"><s:property value="mrp"/></td>
				          		<td style="text-align: right;" class="hidden"><s:property value="amountmrp"/></td>
				          		<td><s:property value="from_location"/>/<s:property value="transfer_date"/></td>
				          		<td colspan="3"></td>
				          	</tr>
				          	</s:iterator>
				          	
				          	</tbody> 
				         <!-- </table> -->
	         		</td>
				</tr>
				</s:else>
				<tr>
				<td colspan="9"></td>
				</tr>
				
				
			</s:iterator>
			
			
			
		</tbody>
	</table>
	
	<s:form action="indentreportProduct" name="paginationForm" id="paginationForm" theme="simple">
							    							<s:hidden name="fromdate"></s:hidden>
							    							<s:hidden name="todate"></s:hidden> 
							    							<s:hidden name="filter_status"></s:hidden> 
							    							<s:hidden name="location_filter"></s:hidden> 
							    							<s:hidden name="searchtext"></s:hidden> 
							    							<s:hidden name="userwise"></s:hidden>
															<div class="col-lg-12">
																<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
																	Total:<label class="text-info"><s:property value="totalRecords" /> Records
																</label>
																</div>
															<%@ include file="/common/pages/pagination.jsp"%>
															</div>
													</s:form>    

</div>



</div>

<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/chosen_v1.1.0/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
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