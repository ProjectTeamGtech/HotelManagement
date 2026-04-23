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
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
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
    
   	<s:if test="filter_status==0">
		<SCRIPT type="text/javascript" >
			function printReport() {
			  	$("#page_printer").table2excel({
					exclude: ".noExl",
					name: "Job Work Issue Register",
					filename: "Job Work Issue Register",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
	 		}
		</script>
	</s:if>
	<s:elseif test="filter_status==1">
		<SCRIPT type="text/javascript" >
			function printReport() {
			  	$("#page_printer").table2excel({
					exclude: ".noExl",
					name: "Job Work Receipt Register",
					filename: "Job Work Receipt Register",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
	 		}
		</script>
	</s:elseif>
	<s:elseif test="filter_status==2">
		<SCRIPT type="text/javascript" >
			function printReport() {
			  	$("#page_printer").table2excel({
					exclude: ".noExl",
					name: "Job Work Pending Register",
					filename: "Job Work Pending Register",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
	 		}
		</script>
	</s:elseif>
	<s:elseif test="filter_status==3">
		<SCRIPT type="text/javascript" >
			function printReport() {
			  	$("#page_printer").table2excel({
					exclude: ".noExl",
					name: "Job Work Cancelled Register",
					filename: "Job Work Cancelled Register",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
	 		}
		</script>
	</s:elseif>
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
		<s:if test="filter_status==0">
			<h4>Job Work Issue Register</h4>
		</s:if>
		<s:elseif test="filter_status==1">
			<h4>Job Work Receipt Register</h4>
		</s:elseif>
		<s:elseif test="filter_status==2">
			<h4>Job Work Pending Register</h4>
		</s:elseif>
		<s:elseif test="filter_status==3">
			<h4>Job Work Cancelled Register</h4>
		</s:elseif>
	</div>
</div>

<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
	<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
        <div class="col-md-12 hidden-print">
           <div class="form-inline">
				<s:form action="jobworkissuereportProduct" theme="simple">
					<div class="form-group" style="width: 15%;">
						<s:textfield id="searchText" cssStyle="width:100%;" name="searchtext"  cssClass="form-control" placeholder="Search by VOUCHER NO." />
					</div>
					<div class="form-group" style="width: 7%;">
						<s:textfield id="fromdate" name="fromdate"  cssClass="form-control" placeholder="From Date" style="width:100%;"/>
					</div>
					<div class="form-group" style="width: 7%;">
						<s:textfield  name="todate" id="todate"  cssClass="form-control" placeholder="To Date" style="width:100%;"/>
					</div>

					<div class="form-group hidden" style="width: 12%;">
						<s:select cssClass="form-control chosen-select" list="#{'0':'Issued', '1':'Receipt', '2':'Pending', '3':'Cancelled'}" name="filter_status" />
					</div>
					<div class="form-group" style="width: 12%;">
						<s:select list="vendorList" cssStyle="width: 100%;" theme="simple" cssClass="form-control chosen-select"  name="vendor" listKey="id" listValue="name" headerKey="0" headerValue="Select Supplier" ></s:select>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Go</button>
						<a type="button" id="btnxls" title="Save As XLS" onclick="printReport()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
						<a type="button" class="btn btn-primary"  title="Print" onclick="print()"><i class="fa fa-print"></i></a>
					</div>
				</s:form>
			</div>
		</div>
	</div> 
	<div class="" id="page_printer">
	<%int testfilter=0; %>
		<table class="table table-striped table-bordered" style="width:100%;" id="prodtableid">
          <thead>
              <tr>
              	<th style="width: 1%;">Sr.</th>
              	<th style="width: 10%;">Supplier</th>
              	<th style="width: 1%;">Voucher No.</th>
              	<th style="width: 6%;">Voucher Date</th>
              	<th style="width: 5%;">Issue by</th>
              	<th style="width: 6%;">Issue Date</th> 
              	<th style="width: 7%;">Product Type</th>
              	<th style="width: 10%;">Product Name</th>
              	<th style="width: 2%;">Quantity</th>
              	<th style="width: 8%;">Expected Date</th>
              	<th style="width: 15%;">Issued Remark</th>
              	<s:if test="filter_status==0">
              		<%testfilter=0; %>
              	</s:if>
              	<s:elseif test="filter_status==1">
              		<%testfilter=1; %>
              		<th style="width: 10%;">Status</th>
	              	<th style="width: 10%;">Receipt Remark</th>
	              	<th style="width: 5%;">Receipt by</th>
	              	<th style="width: 8%;">Receipt Date</th>
              	</s:elseif>
              	<s:elseif test="filter_status==2">
              		<%testfilter=2; %>
              		<th style="width: 10%;">Status</th>
	            </s:elseif>
	            <s:elseif test="filter_status==3">
	            	<%testfilter=3; %>
              		<th style="width: 10%;">Cancel by</th>
              		<th style="width: 10%;">Cancel Datetime</th>
              		<th style="width: 10%;">Cancel Remark</th>
	            </s:elseif>
              	
              </tr>
             
             </thead>
             <tbody>
             	  <%int count=0; %>
                  <s:iterator value="parenttransferlist">
                  	<tr>
	                  	<td><%=(++count) %></td>
	                  	<td><s:property value="vendor"/></td>
	                  	<td><s:property value="parentid"/></td>
	                  	<td><s:property value="dateTime"/></td>
	                  	<td><s:property value="userid"/></td>
	                  	<td><s:property value="date"/></td> 
	                  	<s:if test="isinventory==0">
	                  		<td>Inventory</td>
	                  	</s:if>
	                  	<s:else>
	                  		<td>Non-Inventory</td>
	                  	</s:else>
	                  	<td><s:property value="product_name"/></td>
	                  	<td style="text-align: center;"><s:property value="qty"/></td>
	                  	<td><s:property value="expectedDate"/></td>
	                  	<td><s:property value="remark"/></td>
	                  	<%if(testfilter==0){ %>
	                  	
	                  	<%}else if(testfilter==1){ %>
	                  		<td>
			                  	<s:if test="status==0">
			                  		-
			                  	</s:if>
			                  	<s:elseif test="status==1">
			                  		Repaired
			                  	</s:elseif>
			                  	<s:elseif test="status==2">
			                  		Un-Repaired
			                  	</s:elseif>
		                  	</td>
		                  	<td><s:property value="comment"/></td>
		                  	<td><s:property value="receipt_userid"/></td>
		                  	<td><s:property value="receipt_datetime"/></td>
	                  	<%}else if(testfilter==2){ %>
	                  		<td>Pending</td>
	                  	<%}else if(testfilter==3){ %>
	                  		<td><s:property value="cancel_userid"/></td>
		                  	<td><s:property value="cancel_date"/></td>
		                  	<td><s:property value="cancel_notes"/></td>
	                  	<%} %>
	                  	
                  	</tr>
                  </s:iterator>
             </tbody>
         </table>
	</div>
</div>
<s:form action="jobworkissuereportProduct" name="paginationForm" id="paginationForm" theme="simple">
	 <s:hidden name="fromdate"></s:hidden>
     <s:hidden name="todate"></s:hidden>
     <s:hidden name="searchtext"></s:hidden>
     <s:hidden name="filter_status"></s:hidden>
     <s:hidden name="vendor"></s:hidden>
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left hidden-print" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" />
				Records
			</label>
		</div>
		<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
	</div>
</s:form> 
	
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