<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />

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
 @media print
{
body {
    font-size: 9px !important;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 9px !important;
    border-right: none !important;
    border-left: none !important;
}

.settopbac {
    background-color: #ddd !important;
}
.totalbor {
    background-color: #f5f5f5 !important;
}

    .print_special { border: none !important; } 
    label {
    	font-size: 9px !important;
	}
	p {
	    margin: 0 0 2.5px !important;
	    font-size: 9px !important;
	}
	


.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #ccc !important;
    color: #000 !important;
    font-size: 9px !important;
}


}
        
    </style>
    <SCRIPT type="text/javascript" >
    var todayDate = new Date().getDate();
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
		$("#issue_expected_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			/* minDate : '30-12-1880', */
			changeMonth : true,
			changeYear : true,
			minDate: new Date(new Date().setDate(todayDate))
		});
		$("#issue_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		/* document.addEventListener("contextmenu", function(e){
	    	e.preventDefault();
	    }, false);  */
		   
	});
</SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<script type="text/javascript" src="common/js/pagination.js"></script>
     
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
</head>
<body id='demo-element' oncontextmenu="return false;">

<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;" >
<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<div class="row details mainheader">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<h4>Job Work Dashboard </h4>
	</div>
</div>
<div class="hidden-print">
	<ul class="breadcrumb">
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
<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
	<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
        <div class="col-md-12">
           <div class="form-inline">
				<s:form action="jobworkdashboardProduct" theme="simple">
					<div class="form-group" style="width: 12%;">
						<s:textfield id="searchText" name="searchtext"  cssClass="form-control" placeholder="Search by VOUCHER NO." />
					</div>
					<div class="form-group" style="width: 7%;">
						<s:textfield id="fromdate" name="fromdate"  cssClass="form-control" placeholder="From Date" style="width:100%;"/>
					</div>
					<div class="form-group" style="width: 7%;">
						<s:textfield  name="todate" id="todate"  cssClass="form-control" placeholder="To Date" style="width:100%;"/>
					</div>

					<div class="form-group" style="width: 12%;">
						<s:select cssClass="form-control chosen-select" list="#{'':'Select Status', '0':'Issued', '1':'Receipt', '2':'Pending', '3':'Cancelled'}" name="filter_status" />
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Go</button>
						<a href="#" class="btn btn-warning" onclick="openIssueForJobWork()">Issue For Job Work</a>
						<a href="#" class="btn btn-success" onclick="openReceiptForJobWork()" >Receipt For Job Work</a>
					</div>
					<div class="form-inline" style="float: right;margin-top: 5px;text-transform: uppercase;">
						<div class="checkbox">
						  <label>
						    <i class="fa fa-square" aria-hidden="true" style="color:#ec971f;"></i> Issue
						  </label><!-- style="color:#fff;" -->
						</div>|
						<div class="checkbox">
						  <label>
						    <i class="fa fa-square" aria-hidden="true" style="color:#7fcc80;"></i> Receipt
						  </label>
						</div>|
						<div class="checkbox">
						  <label>
						    <i class="fa fa-square" aria-hidden="true" style="color:rgb(72, 204, 184);"></i> Pending
						  </label>
						</div>|
						<div class="checkbox">
						  <label>
						    <i class="fa fa-square" aria-hidden="true" style="color: #FF5733;"></i> Cancelled
						  </label>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div> 
	<div class="">
		<table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
	    	<thead>
	            <tr>
	            	<th style="width: 1%;">SR.</th>
	            	<th style="width: 7%;">Voucher No.</th>
	            	<th style="width: 7%;">Voucher Date</th>
	                <th style="width: 10%;">Issue By</th>
	                <th style="width: 10%;">Issue Date</th>
	                <th style="width: 8%;">Vendor</th>
	               	<th style="text-align: center;width: 7%;">Status</th>
	            	<th style="text-align:center;width: 1%;">Print</th>
	            	<th style="text-align:center;width: 1%;">Cancel</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<%int transfercount=0; %>
	            <s:iterator value="parenttransferlist">
	            	<tr>
	            		<td><%=(++transfercount) %></td>
	            		<td><s:property value="id"/></td>
	            		<td><s:property value="dateTime"/></td>
	            		<td><s:property value="userid"/></td>
	            		<td><s:property value="date"/></td>
	            		<td><s:property value="vendor"/></td>
	            		<s:if test="status==0">
              				<td style="text-align:center;background-color: #ec971f;">ISSUED</td> 
              			</s:if>
              			<s:elseif test="status==1">
              				<td style="text-align:center;background-color: #7fcc80;">RECEIPT</td>
              			</s:elseif>
              			<s:elseif test="status==2">
              				<td style="text-align:center;background-color: rgb(72, 204, 184);">PENDING</td>
              			</s:elseif>
              			<s:elseif test="status==3">
              				<td style="text-align:center;background-color: #FF5733;">CANCELLED</td>
              			</s:elseif>
              			<td style="text-align: center;">
              				<a href="#" onclick="openBlankPopup('jobworkprintProduct?id=<s:property value="id"/>')" ><i class="fa fa-print"></i></a>
              			</td>
              			<td style="text-align: center;">
              				<s:if test="status==0">
              					<a href="#" onclick="deleteJobwork(<s:property value="id"/>)"><i class="fa fa-trash text-danger"></i></a>
              				</s:if>
              			</td>
	            	</tr>
	            </s:iterator>                  
	        </tbody>
	     </table>
	</div>
</div>
<s:form action="jobworkdashboardProduct" name="paginationForm" id="paginationForm" theme="simple">
	 <s:hidden name="fromdate"></s:hidden>
     <s:hidden name="todate"></s:hidden>
     <s:hidden name="searchtext"></s:hidden>
     <s:hidden name="filter_status"></s:hidden>
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" />
				Records
			</label>
		</div>
		<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
	</div>
</s:form> 
	
</div>

<!-- Modal -->
<div id="deletemodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="parent_id">
        	<textarea rows="3"  class="form-control" id="delete_reason" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="deleteJobWork1()"  value="Ok">
      </div>
    </div>

  </div>
</div>



       <!--Indent Rquest Modal -->
<div id="ireq" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" onclick="closeissuejobwork()">&times;</button>
        <h4 class="modal-title">Issue For Job Work</h4>
      </div>
      
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="background-color: rgba(22, 160, 133, 0.07);padding-top: 9px;">
        		<div class="col-lg-1 col-md-1 col-xs-1" style="padding: 0;">
        			<div class="form-group">
        				<label>Issue Date</label>
        				<input type="text" readonly="readonly" name="issue_date" id="issue_date" class="form-control" placeholder="Select Date">
        			</div>
        		</div>
        		<div class="col-lg-2 col-md-2 col-xs-2">
        			<div class="form-group">
        				<label>Select Supplier</label>
        				<s:select list="vendorList" theme="simple" cssClass="form-control chosen-select" id="issue_vendorid" name="issue_vendorid" listKey="id" listValue="name" headerKey="0" headerValue="Select Supplier" ></s:select>
        			</div>
        		</div>
        		<div class="col-lg-2 col-md-2 col-xs-2">
        			<div class="form-group">
        				<label>Select Product Type</label>
        				<select name="issue_isinventory" id="issue_isinventory" onchange="setinventorynoninventory(this.value)" class="form-control chosen-select">
						    <option value="">Select</option>
						    <option value="0">Inventory</option>
						    <option value="1">Non-Inventory</option>
						</select>
        			</div>
        		</div>
        		<div class="col-lg-3 col-md-3 col-xs-3 hidden" id="issueproductselect">
        			<div class="form-group">
        				<label>Select Product</label>
        				<select id="issue_catalogueid" name="issue_catalogueid" class="form-control chosen-select">
						    <option value="0">Select</option>
						</select>
        			</div>
        		</div>
        		<div class="col-lg-3 col-md-3 col-xs-3" id="issueproductenter">
        			<div class="form-group">
        				<label>Enter Product</label>
        				<textarea name="issue_productname" rows="4" class="form-control" id="issue_productname"></textarea>
        			</div>
        		</div>
        		<div class="col-lg-1 col-md-1 col-xs-2">
        			<div class="form-group">
        				<label>Quantity</label>
        				<input type="number" min="0" name="issue_qty" id="issue_qty" class="form-control" placeholder="Qty" >
        			</div>
        		</div>
        		
        		<div class="col-lg-2 col-md-2 col-xs-2" >
        			<div class="form-group">
        				<label>Expected Date</label>
        				<input type="text" readonly="readonly" name="issue_expected_date" id="issue_expected_date" class="form-control" placeholder="Select Date">
        				
        			</div>
        		</div>
        		<div class="col-lg-1 col-md-1 col-xs-2">
        			<div class="form-group" style="margin-top:22px;">
        				<a href="#" class="btn btn-success" onclick="addissuejobwork()">Add</a>
        			</div>
        		</div>
        	</div>
        </div>
        
	<div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
        	<table class="table my-table xlstable table-striped table-bordered" id="indenttable" style="width: 100%;">
                            <thead>
                                <tr>
                                	<th style="width: 1%;">Sr.no</th>
                                	<th style="width: 9%;">Product Type</th>
                                	<th style="width: 18%;">Product Name</th>
                                	<th style="width: 2%;">Quantity</th>
                                	<th style="width: 7%;">Expected Date</th>
                                	<th style="width: 25%;">Issue Remark</th>
                                	<th style="width: 1%;"></th>
                                </tr>
                            </thead>
                            <tbody id="jobworkissuebody">
                              	<tr></tr> 
                            </tbody>
                        </table>
        	
        	</div>
        </div>
      </div>
      
     
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="saveIssueJobWork()" style="margin-top: 20px;">Issue</button>
      </div>
    </div>

  </div>
</div>            

      <!--Indent Rquest Modal -->
<div id="ireceipt" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
	<s:form theme="simple" action="savereceiptjobworkProduct" id="receiptform">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Receipt For Job Work</h4>
      </div>
      
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="background-color: rgba(22, 160, 133, 0.07);padding-top: 9px;">
        		<div class="col-lg-4 col-xs-4 col-md-12">
        			<div class="form-group">
        				<label>Select Supplier</label>
        				<s:select list="vendorList" theme="simple" cssClass="form-control chosen-select" id="receipt_vendorid" name="receipt_vendorid" listKey="id" listValue="name" headerKey="0" headerValue="Select Supplier" ></s:select>
        			</div>
        		</div>
        		<div class="col-lg-4 col-xs-4 col-md-12">
        			<div class="form-group" style="margin-top:22px;">
        				<a href="#" class="btn btn-success" onclick="checkreceiptjobwork()">Check Issued</a>
        			</div>
        		</div>
        	</div>
        </div>
        
	<div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
        	<table class="table my-table xlstable table-striped table-bordered" id="indenttable" style="width: 100%;">
                            <thead>
                                <tr>
                                	<th style="width: 1%;">Sr.</th>
                                	<th style="width: 1%;">Voucher No.</th>
                                	<th style="width: 8%;">Voucher Date</th>
                                	<th style="width: 5%;">Issue by</th>
                                	<th style="width: 8%;">Issue Date</th>
                                	<th style="width: 9%;">Product Type</th>
                                	<th style="width: 15%;">Product Name</th>
                                	<th style="width: 2%;">Qty</th>
                                	<th style="width: 8%;">Expected Date</th>
                                	<th style="width: 15%;">Issued Remark</th>
                                	<th style="width: 10%;">Status</th>
                                	<th style="width: 16%;">Receipt Remark</th>
                                </tr>
                            </thead>
                            <tbody id="jobworkreceiptbody">
                              	<tr></tr>
                              	
                            </tbody>
                        </table>
        	
        	</div>
        </div>
      </div>
      
      
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="saveReceiptJobWork()" style="margin-top: 20px;">RECEIPT</button>
      </div>
    </div>
</s:form>
  </div>
</div>             

 

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
    function printDiv2(divID) {
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