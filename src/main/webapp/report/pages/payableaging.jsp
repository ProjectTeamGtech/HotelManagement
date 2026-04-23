	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style type="text/css">
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

/*  @media print
{
   .dataTables_filter, .dataTables_info { display: none; }
   .dataTables_wrapper .fg-toolbar { display: none; }
   
} */

</style>

<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<SCRIPT type="text/javascript">

	function printReport() {
				
				  $(".tablexls").table2excel({
					exclude: ".noExl",
					name: "PAYABLE AGING REPORT",
					filename: "payableAgingReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }

/* 	$(document).ready(function() {
              
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
		             
	}); */
	/* $(document).ready(function() {
		document.getElementById("tablesort_filter").className ="hidden-print dataTables_filter";
	});  */
	
</SCRIPT>

</head>
<body>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
	style="padding: 0px;">
<div class="col-lg-12 col-md-12 paddingnil">
	<s:form theme="simple" cssClass="form-inline search" action="payableagingReport">
	<div class="col-lg-12 col-md-12 topback2">
	
	<!-- div class="col-lg-10 col-md-10"> -->
		<form class="form-inline search">
		<div class="form-group">
			<span class="text-uppercase"><b>Payable Aging Report</b> &nbsp; - &nbsp;</span>
		</div>
		 <div class="form-group">
			<s:select list="locationlist" name="location" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
	     	</s:select> 
	     </div>
	     <div class="form-group">
			<button type="submit" class="btn btn-primary">Go</button>
		 </div>
		<%-- <div class="form-group" style="width:15%">
			<s:select name="department" list="departmentlist" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="All department" theme="simple" ></s:select>
		</div> --%>
	<%-- <div class="form-group">
		<s:select cssClass="form-control" list="#{'0':'by quantity', '1':'by valuation', '2':'Delivered'}" name="filteroforder" />
	</div> --%>
	<%-- <div class="form-group" style="width:15%">
		<s:select name="product" list="productlist" listKey="id" listValue="prod_name" cssClass="form-control chosen-select" headerKey="0" headerValue="All Product" theme="simple" ></s:select>
	</div> --%>
	<%-- <div class="form-group" style="width:15%">
		<s:select name="department" list="departmentlist" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="All department" theme="simple" ></s:select>
	</div> --%>
	<%-- <div class="form-group">
		<s:select cssClass="form-control" list="#{'0':'Select','1':'Non Moving', '2':'Slow Moving', '3':'Fast Moving'}" name="filteroforder" />
	</div> --%>
	
	<!--  <div class="form-group">
		<button type="submit" class="btn btn-primary">Go</button>
	</div> -->
	<div class="form-group pull-right">
		<!-- <a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Download Excel</a> -->
		<a href="#"  onclick="printReport()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
	</div>
	<div class="form-group pull-right" style="margin-right: 15px">
		<a href="#" class="btn btn-warning" onclick="printDiv5('printableArea')">Print</a>
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
<div class="col-lg-12 col-md-12 col-xs-12" id="printableArea" style="padding: 0px;">


							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
	<h5 class="hidden-lg hidden-md visible-print"><span class="text-uppercase"><b>Payable Aging Report</b> &nbsp; -  &nbsp; </span></h5>
<div id="page_printer">
	<table class="table table-bordered table-striped tablexls" cellspacing="0" width="100%" id="tablesort" style="margin-bottom: 0px;">
		<!-- <tr>
			<td colspan="5" class="text-uppercase print-visible hidden-md hidden-lg"><b>Payable Aging Report</b> </td>
		</tr> -->
		<thead>
			<tr class="tableback">
				<td class="thcoi" style="width: 1%">Sr.no</td>
				<td class="thcoi" style="width: 15% ">GRN No</td>
				<td class="thcoi" style="width: 50% ">Supplier Name</td>
				<td class="thcoi" style="width: 15% ">Invoice Date</td>
				<td class="thcoi" style="width: 15% ">Warehouse</td>
			 </tr>
		</thead>
		<tbody>
			
			<%
				int i = 0;
			%>
			<s:iterator value="paybleaginglist">
				<tr>
					<td><%=(++i)%></td> 
					<%-- <td><s:property value="procurementid"/></td> --%>
					<td><s:property value="proSeqNo"/></td> 
					<td><s:property value="vendor"/></td>
					<td><s:property value="voucherdate"/></td>
					<td><s:property value="location"/></td>
				</tr>
				
			</s:iterator>
		</tbody>
	</table>

</div>



</div>

<%-- <script src="common/chosen_v1.1.0/chosen.jquery.js"
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


 --%>
<%-- <script>
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
	</script> --%>
	
	<script>
	function printDiv5(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>
	<script>
     $(document).ready(function() {
    var table = $('#tablesort').DataTable( {
        lengthChange: false,
        searching: false, 
        buttons: [ 'excel', 'colvis' ]
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