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
		font-size: 12px;
	}
	h5,.h5 {
		font-size: 8px;
	}
}

</style>
<style>
	.chosen-container {
    width: 100%;
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
            background: rgb(102, 153, 204) none repeat scroll 0% 0%;
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
            min-height: auto;
        }
        .miheight {
            min-height: auto;
        }
        .my-table th {
            background-color: #424A5D;
            color: #fff;
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
            padding: 1px 7px 1px 7px;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100%;
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
     background-color: #dd4b39;
     border-radius: 50px;
}
     .setborba{
	background-color: #efefef;
    padding-top: 5px;
}
 .dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus {
    background-image: linear-gradient(to bottom, #777 0, #777 100%);
    
}
.dropdown-menu {
    padding: 0px 0;
    margin: 0px 0 0;
}
ul.dt-button-collection.dropdown-menu>* {
    -webkit-column-break-inside: avoid;
    break-inside: avoid;
    border-bottom: 1px solid rgba(0, 0, 0, 0.5);
}
</style>

<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<SCRIPT type="text/javascript">

	function printReport() {
				
				  $(".table").table2excel({
					exclude: ".noExl",
					name: "Indent Report",
					filename: "IndentReport",
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
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
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
	<s:form theme="simple" cssClass="form-inline search" id="transferreportformid" action="indentnewreportProduct">
	<div class="col-lg-12 col-md-12 topback2">
	
	<!-- div class="col-lg-10 col-md-10"> -->
		<form class="form-inline search">
		<div class="form-group">
			<span class="text-uppercase"><b>indent new report</b> &nbsp; - &nbsp;</span>
		</div>
		
	<div class="form-group">
		<s:textfield name="searchtext"  cssClass="form-control" placeholder="BY ID/Product Name" />
	</div>
	<div class="form-group" style="width:9%">
		<s:textfield name="fromdate" id="fromdate" readonly="true" cssClass="form-control" placeholder="From Date" cssStyle="width:100%;"/>
	</div>
	<div class="form-group" style="width:9%">
		<s:textfield name="todate" id="todate" readonly="true"  cssClass="form-control" placeholder="To Date" cssStyle="width:100%;"/>
	</div>
	<div class="form-group">
		<s:select  cssClass="form-control"  list="#{'0':'Request','1':'Direct', '2':'Return'}" name="filter_status" />
	</div>
	<div class="form-group" >
		<%-- <s:select headerKey="0" headerValue="From location" cssClass="form-control chosen-select" list="locationlist" name="transferedlocation" listKey="id" listValue="name"/> --%>
		<s:select headerKey="0" cssStyle="width:55%" headerValue="From location" cssClass="form-control chosen-select" list="locationlist" name="location_filter" listKey="id" listValue="name"/>
	</div>
	<div class="form-group hidden" style="width:15%">
		<s:select headerKey="0" headerValue="To location" cssClass="form-control chosen-select" list="locationlist" name="transferedlocation" listKey="id" listValue="name"/>
	</div>
	<div class="form-group hidden" style="width:15%">
		<s:select headerKey="0" headerValue="Select User" cssClass="form-control chosen-select" list="userlist" name="userwise" listKey="userid" listValue="fullname"/>
	</div>
	<div class="form-group">
		<!-- <button type="submit" class="btn btn-primary">Go</button> -->
		<a href="#" class="btn btn-primary" onclick="submitIndentStatementReport()">Go</a>
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
	style="text-align: center; border-bottom: 4px double #ddd; line-height: 30px;"><b>Indent New Report</b></h4>

	<table class="table table-bordered table-striped" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
		<thead>
			<tr class="tableback">
				<th class="thcoi" style="width: 1%;">Sr.No</th>
				<th class="thcoi" style="width: 3%;">Indent No.</th>
				<th class="thcoi" style="width: 3%;">Req. Type</th>
                <th class="thcoi" style="width: 3%;">
                	<s:if test="filter_status==2">
                		Return Date
                	</s:if>
                	<s:else>
                		Request Date
                	</s:else>
                </th>
                <th class="thcoi" style="width: 4%;">
                	<s:if test="filter_status==2">
                		Return To Depart.
                	</s:if>
                	<s:else>
                		Request Depart.
                	</s:else>
                </th>
                <th class="thcoi" style="width: 2%;">Req.Approved by</th>
                <th class="thcoi" style="width: 2%;">Category</th>
                <th class="thcoi" style="width: 2%;">Sub-Category</th>
                <th class="thcoi" style="width: 4%;">Product Name</th>
                <th class="thcoi" style="width: 2%;">Req. Qty</th>
               <!--  <th class="thcoi" style="width: 17%;">Issue No.</th> -->
                <th class="thcoi" style="width: 4%;">
                	<s:if test="filter_status==2">
                		Return From Date.
                	</s:if>
                	<s:else>
                		Issue Date
                	</s:else>
                </th>
                <th class="thcoi" style="width: 2%;">
                	<s:if test="filter_status==2">
                		Return From Depart.
                	</s:if>
                	<s:else>
                		Issue Depart.
                	</s:else>
                </th>
                <!-- <th class="thcoi" style="width: 17%;">Issue Approved by</th> -->
                <th class="thcoi" style="width: 2%;">
                	<s:if test="filter_status==2">
                		Return Qty
                	</s:if>
                	<s:else>
                		Issue Qty
                	</s:else>
                	
                </th>
                <th class="thcoi" style="width: 2%;">Valuation</th>
                <th class="thcoi hidden-print" style="width: 2%;">Print</th>
              </tr>
		</thead>
		<tbody>
		<%int srno=0; %>
				<s:iterator value="parenttransferlist">
				<tr>
					<td><%=(++srno) %></td>
					<td><s:property value="id"/></td>
	                <td>
	                <s:if test="filter_status==0">
	                	Request
	                </s:if>
	                </td>
	                <td><s:property value="request_date"/></td>
	                
	                <td><s:property value="from_location"/></td>
	                <td><s:property value="admin_approve_userid"/></td>
	                <td><s:property value="category"/></td>
	                <td><s:property value="subcategory"/></td>
	                <td><s:property value="product_name"/></td>
	                <td><s:property value="req_qty"/></td>
	             <!--    <td class="thcoi" style="width: 17% ;">Issue No.</td> -->
	                <td><s:property value="transfer_date"/></td>
	                <td><s:property value="issue_depart"/></td>
	                <!-- <td class="thcoi" style="width: 17% ;">Issue Approved by</td> -->
	                <td><s:property value="issueqty"/></td>
	                <td><s:property value="valuation"/></td>
	                <td>
	                <a href="#" onclick="openBlankPopup('deliverPrintDirectProduct?id=<s:property value='id'/>')"><i class="fa fa-print"></i></a>
	             <tr>
	             
				</s:iterator>
		</tbody>
	</table>
	
	<s:form action="indentnewreportProduct" name="paginationForm" id="paginationForm" theme="simple">
							    							<s:hidden name="fromdate"></s:hidden>
							    							<s:hidden name="todate"></s:hidden> 
							    							<s:hidden name="filter_status"></s:hidden> 
							    							<s:hidden name="location_filter"></s:hidden> 
							    							<s:hidden name="searchtext"></s:hidden> 
							    							<s:hidden name="userwise"></s:hidden>
							    							<s:hidden name="transferedlocation"></s:hidden>
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