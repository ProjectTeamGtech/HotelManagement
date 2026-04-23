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
		
		$("#dateOfReturn").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		
		
		
		$("#expected_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			/* minDate : '30-12-1880', */
			changeMonth : true,
			changeYear : true,
			minDate: new Date(new Date().setDate(todayDate))
		});
		 document.addEventListener("contextmenu", function(e){
	    		e.preventDefault();
	    		}, false); 
		   
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
		<div class="row <!-- details mainheader -->">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
				 	<img src="manasmaindashboard/images/Indent_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
				 	<h4>Gate Pass Dashboard</h4>
				</div>
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
		<!-- <input type="button" value="hhh" id='go-button' onclick="dhee()"> -->
		<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
        	<div class="col-md-12">
            	<div class="form-inline">
					<s:form action="gatepassdashboardProduct" theme="simple">
						<div class="form-group" style="width: 12%;">
							<s:textfield id="searchText" name="searchtext"  cssClass="form-control" placeholder="Search by Sequence" />
						</div>
						<div class="form-group" style="width: 7%;">
							<s:textfield id="fromdate" name="fromdate" readonly="true" cssClass="form-control" placeholder="From Date" style="width:100%;"/>
						</div>
						<div class="form-group" style="width: 7%;">
							<s:textfield  name="todate" id="todate" readonly="true"  cssClass="form-control" placeholder="To Date" style="width:100%;"/>
						</div>
						<div class="form-group">
	        				<s:select list="#{'0':'Returnable','1':'Non Returnable'}" name="gatePassTypeFilter" headerKey="" headerValue="Select Gate Pass Type" cssClass="form-control chosen-select"></s:select>
        				</div>
        				<div class="form-group">
	        				<s:select list="vendorList" id="vendorlist" name="vendor_id" listKey="id" listValue="data" headerKey="0" headerValue="Select Vendor" cssClass=" chosen-select"></s:select>
        				</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Go</button>
						  	<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#ireq">Generate Gate Pass</a>
						</div>
						<input type="button" onclick="printReport()" value="Download Excel" class="btn btn-warning hidden" />
					</s:form>
				</div>
			</div>
        </div> 
        <div class="">
        	<table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
            	<thead>
                	<tr>
                    	<th class="manastableheader">SR.</th>
                    	<th class="manastableheader">Sequence</th>
                        <th class="manastableheader">Gate Pass Type</th>
                        <th class="manastableheader">Date</th>
                        <th class="manastableheader">Date Time</th>
                        <th class="manastableheader">Vehicle Number</th>
                        <th class="manastableheader">Approximate Date Of Return</th>
                        <th class="manastableheader">Approximate value Rs</th>
                        <th class="manastableheader">Store</th>
                        <th class="manastableheader">Supplier</th>
                        <th class="manastableheader">Vendor Code</th>
                        <th class="manastableheader">Print</th>
                    </tr>
                </thead>
                <tbody>
                	<%int count=0; %>
                    <s:iterator value="gatePassList">
                    	<tr>
                    		<td><%=(++count) %></td>
                    		<td><s:property value="sequence"/></td>
                    		<td>
                    			<s:if test="gatePassType==0">
                    				Returnable
                    			</s:if>
                    			<s:else>
                    				Non Returnable
                    			</s:else>
                    		</td>
                    		<td><s:property value="date"/></td>
                    		<td><s:property value="dateTime"/></td>
                    		<td><s:property value="vehicleNumber"/></td>
                    		<td><s:property value="dateOfReturn"/></td>
                    		<td><s:property value="approximateValue"/></td>
                    		<td><s:property value="locationName"/></td>
                    		<td><s:property value="supplierId"/></td>
                    		<td><s:property value="Vendorcode"/></td>
                    		<td>
                    			<a class="baksetp1" href="#" onclick="openBlankPopup('gatepassprintProduct?id=<s:property value="id"/>')" > Print</a>
                    		</td>
                    	</tr>
                    </s:iterator>
                 </tbody>
              </table>
        </div>
	</div>
	
	<s:form action="gatepassdashboardProduct" name="paginationForm" id="paginationForm" theme="simple">			    
	     <s:hidden name="fromdate"></s:hidden>
	     <s:hidden name="todate"></s:hidden>
	     <s:hidden name="searchtext"></s:hidden>
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



       <!--Indent Rquest Modal -->
<div id="ireq" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Generate Gate Pass</h4>
      </div>
      <s:form theme="simple" action="savegatepassProduct" id="indentform">
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="background-color: rgba(22, 160, 133, 0.07);padding-top: 9px;">
        		<input type="hidden" name="allproductid" id="allproductid">
        		<%-- <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Supplier/Vendor</label>
        				<s:select list="#{'0':'Returnable','1':'Non Returnable'}" id="gatePassType" name="gatePassType" headerKey="0" headerValue="Select Gate Pass Type" cssClass="form-control chosen-select"></s:select>
        			</div>
        		</div> --%>
        		<input type="hidden" id="isFromGatePass">
        		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Gate Pass Type</label>
        				<s:select list="#{'0':'Returnable','1':'Non Returnable'}" id="gatePassType" name="gatePassType" headerKey="" headerValue="Select Gate Pass Type" cssClass="form-control chosen-select"></s:select>
        			</div>
        		</div>
        		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Date</label>
        				<input type="text" readonly="true" name="date" id="date" class="form-control" placeholder="Date" >
        			</div>
        		</div>
        		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Vehicle Number</label>
        				<input type="text" name="vehicleNumber" id="vehicleNumber" class="form-control" placeholder="Vehicle Number" >
        			</div>
        		</div>
        		
        		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Vendor Code</label>
        				<input type="text" name="vendorcode" id="vendorcode" class="form-control" placeholder="Vendor Code" >
        			</div>
        		</div>
        		<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Approx. Date Of Return</label>
        				<input type="text" readonly="true" name="dateOfReturn" id="dateOfReturn" class="form-control" placeholder="Approx. date of return" >
        			</div>
        		</div>
        		<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Approximate value Rs.</label>
        				<input type="text" name="approximateValue" id="approximateValue" class="form-control" placeholder="Approx. Value" >
        			</div>
        		</div>
        		
        	</div>
        	<div class="col-lg-12 col-xs-12 col-md-12" style="background-color: rgba(22, 160, 133, 0.07);padding-top: 9px;">
        		<input type="hidden" name="allproductid" id="allproductid">
        		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Select Store</label>
        				<s:select list="warehouseList" id="warehouse_id" name="warehouse_id" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="setProductofStore(this.value)" cssClass="form-control chosen-select"></s:select>
        			</div>
        		</div>
        		<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
        			<div class="form-group" id="supplierdiv" >
        				<label>Select Supplier</label>
        				<select id="supplierId" class="form-control chosen-select">
						    <option value="0">Select</option>
						</select>
        			</div>
        		</div>
        		<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
        			<div class="form-group" id="proddiv" >
        				<label>Select Product</label>
        				<select name="" id="product_id" class="form-control chosen-select">
						    <option value="0">Select</option>
						</select>
        			</div>
        		</div>
        		
        		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
        			<div class="form-group">
        				<label>Quantity</label>
        				<input type="number" min="0" name="qty" id="qty" class="form-control" placeholder="Qty" >
        			</div>
        		</div>
        		<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
        			<div class="form-group" style="margin-top:22px;">
        				<a href="#" class="btn btn-success" onclick="addNewGatePassProduct('indenttable')">Add</a>
        			</div>
        		</div>
        		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
        		</div>
        	</div>
        </div>
        
	<div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
	        	<table class="table my-table xlstable table-striped table-bordered" id="indenttable" style="width: 100%;">
	                 <thead>
	                     <tr>
	                     	<th style="width: 1%;">Sr.no</th>
	                     	<th style="width: 18%;">Product Name</th>
	                     	<th style="width: 6%;">Quantity</th>
	                     	<th style="width: 25%;">Comments</th>
	                     	<th style="width: 3%;"></th>
	                     </tr>
	                 </thead>
	                 <tbody id="indentbody">
	                   	<tr></tr> 
	                 </tbody>
	             </table>
        	</div>
        </div>
      </div>
      
      </s:form>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="saveGatePass()" style="margin-top: 20px;">Save</button>
      </div>
    </div>

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