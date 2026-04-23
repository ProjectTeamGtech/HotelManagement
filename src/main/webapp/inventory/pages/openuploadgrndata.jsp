<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
	type="text/css" />
<link href="plugin/monthyear/MonthPicker.min.css" rel="stylesheet"
	type="text/css" />
<script src="plugin/monthyear/jquery-ui.min.js"></script>
<script src="plugin/monthyear/jquery.maskedinput.min.js"></script>
<script src="plugin/monthyear/MonthPicker.min.js"></script>


<style>
.topheadbaxck {
	background-color: rgb(239, 239, 239);
	padding: 8px 0px;
}

.table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
	font-size: 14px;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
	line-height: 40px;
}

.chosen-container {
	width: 100% !important;
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

<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>


<SCRIPT type="text/javascript">
	window.onload = function() {
		document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false); 
	}
</SCRIPT>


</head>

<script type="text/javascript">
function validSubmit(){
	
	var logo = document.getElementById('userImage').value;
	var vendorid= document.getElementById('vendorid').value; 
	var warehouse= document.getElementById('warehouse').value; 
	//document.getElementById("tnameError").innerHTML = "";
	if(warehouse=='0'){
		 alert('Please select warehouse');
		 return false;
	}else if(vendorid=='' || vendorid=='0'){
		 alert('Please select vendor');
		 return false;
	}
	else if(logo == ""){		
		 alert('Please Upload File.');
		 return false;
	}	
    else
    {
    	return true;
    }
	
}

function printStockReportExcel() {
    $(".tablestock").table2excel({
				exclude: ".noExl",
				name: "GRNTemplate",
				filename: "GRNTemplate",
				fileext: ".xls",
				exclude_img: true,
				exclude_links: true,
				exclude_inputs: true
		});
     }

</script>
 <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<body>
	<%
		LoginInfo loginfo = LoginHelper.getLoginInfo(request);
	%>
	<div class="row">
			<div class="hidden-print">
				<ul class="breadcrumb">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
					<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
						<%if(breadcrumbs.isIscurrent()){ %>
							<li><%=breadcrumbs.getShowingname()%></li>
						<%}else{ %>
							<%if(breadcrumbs.getOn()){ %>
								<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getShowingname()%></a></li>
							<%}else{ %>
								<li><%=breadcrumbs.getShowingname()%></li>
							<%} %>
						<%} %>
						
					<%} %>
				</ul>
			</div>
	</div>
	<div class="row details">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

			<h4>GRN Upload</h4>

		</div>
	</div>
	
							
	<s:form theme="simple" action="uploadexcelgrndataProcurement" id="formsubmit"  method="post"
					enctype="multipart/form-data">
		<div class="" style="background-color: rgba(239, 239, 239, 0.42); padding: 9px; border: 1px dashed #ddd;">
			<s:actionerror cssStyle="color:red" />
					 <s:actionmessage cssStyle="color:green" />
			<div class="form-inline">
				 <div class="form-group" style="width: 10%;">
					<s:select list="warehouseList" id="warehouse"  name="warehouse"
						listKey="id" listValue="name" headerKey="0" headerValue="Select Warehouse"
						cssClass="form-control chosen-select"></s:select>
				</div>
				<div class="form-group"  style="width: 20%;">
					<s:select theme="simple" id="vendorid"  name="vendor_id"
						 list="vendorList"
						listKey="id" listValue="name" headerKey="0"
						headerValue="Select Supplier"
						cssClass="form-control chosen-select" />
				</div>
				
				<div class="form-group">
					<s:file name="userImage" id="userImage" label="Choose File" />
				</div> 
				
				<div class="form-group">
					<s:submit cssClass="btn btn-primary" onclick="return validSubmit()" /> 
					<a type="button"  title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary">Download Template</a>
					
				</div>
				
			</div>
				
	</div>
	</s:form>
<div class="">
                        <table class="table my-table table-striped xlstable table-bordered tablestock" id="example" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 25%;">Product Name</th>
                                    <th style="width: 8%;">Pack</th>
                                    <th style="width: 8%;text-align:right;">MRP</th>
                                    <th style="width: 8%;text-align:right;">Sale Price</th>
                                    <th style="width: 8%;text-align:right;">Purchase Price</th>
                                    <th style="width: 4%;text-align:right;">GST</th>
                                    <th style="width: 10%;">Manufacturer</th>
                                    <th style="width: 8%;">Hsnno</th>
                                    <th style="width: 10%;">Quantity</th>
                                    <th style="width: 10%;">Batch No</th>
                                    <th style="width: 8%;">Expiry Date</th>
                                    <th style="width: 4%;">Free Qty</th>
                               </tr>
                                
                            </thead>
                            <tbody>
                              <tr>
                                    <td>XYZ</td>
                                    <td>1</th>
                                    <td style="text-align:right;">100</th>
                                    <td style="text-align:right;">100</th>
                                    <td style="text-align:right;">70</th>
                                    <td style="text-align:right;">5</th>
                                    <td >crosin</th>
                                    <td >44556633</th>
                                    <td >10</th>
                                    <td >5252</th>
                                    <td >11/2020</th>
                                    <td style="text-align:right;">10</th>
                               </tr>
                               
                               <tr>
                                    <td>ABC</td>
                                    <td>1</th>
                                    <td style="text-align:right;">100</th>
                                    <td style="text-align:right;">100</th>
                                    <td style="text-align:right;">70</th>
                                    <td style="text-align:right;">5</th>
                                    <td >crosin</th>
                                    <td >44556633</th>
                                    <td >10</th>
                                    <td >6633</th>
                                    <td >11/2020</th>
                                    <td style="text-align:right;">10</th>
                               </tr>
                            </tbody>
                        </table>
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




			
</body>
<script src="common/chosen_v1.1.0/chosen.jquery.js"
				type="text/javascript"></script>
			<script type="text/javascript">
				var config = {
					'.chosen-select' : {},
					'.chosen-select-deselect' : {
						allow_single_deselect : true
					},
					'.chosen-select-no-single' : {
						disable_search_threshold : 10
					},
					'.chosen-select-no-results' : {
						no_results_text : 'Oops, nothing found!'
					},
					'.chosen-select-width' : {
						width : "100%"
					}
				}
				for ( var selector in config) {
					$(selector).chosen(config[selector]);
				}

				// Apply an input mask which mkase sure the user 
				// limits the user to typing a month in the 
				//fromat specified in the MonthFormat option.
				$('#DigitalBush').MonthPicker({
					UseInputMask : true
				});
				$('#DigitalBushBoth').MonthPicker({
					UseInputMask : true,
					ValidationErrorMessage : 'Invalid Date!'
				});
			</script>
</html>