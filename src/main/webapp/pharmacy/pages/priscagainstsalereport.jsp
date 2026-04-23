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
    <SCRIPT type="text/javascript" >
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
		
		 document.addEventListener("contextmenu", function(e){
				e.preventDefault();
				}, false); 
	});
    
    </SCRIPT>
     <script type="text/javascript" src="common/js/pagination.js"></script>
     
</head>
<body>
<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
								<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
									
								</div> -->
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<h4>Prescription Against Sale Report </h4>
								</div>

								</div>
							</div>
							
							<%-- <div class="hidden-print">
											<ul class="breadcrumb">
												&nbsp;
												<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
												<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
													<%if(breadcrumbs.isIscurrent()){ %>
														<li><%=breadcrumbs.getShowingname()%></li>
													<%}else{ %>
														<%if(breadcrumbs.getOn()){ %>
															<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
														<%}else{ %>
															<li><%=breadcrumbs.getName()%></li>
														<%} %>
													<%} %>
													
												<%} %>
											</ul>
										</div> --%>
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                       <div class="col-md-12">
                          <div class="row ">
								<div class="">
									<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form action="priscagainstsalereportPharmacyAjax" theme="simple" id="priscform" cssClass="form-inline search">
										 <%--  <div class="form-group">
										    <s:textfield name="searchText" id="searchText" cssClass="form-control" placeholder="Search Prescription"/>
										  </div> --%>
										  <div class="form-group" style="width:7%;">
										    <s:textfield id="fromdate" name="fromdate" readonly="true" cssClass="form-control" placeholder="From Date" style="width:100%;"/>
										  </div>
										  <div class="form-group" style="width:7%;">
										    <s:textfield id="todate" name="todate" readonly="true" cssClass="form-control" placeholder="To Date" style="width:100%;"/>
										  </div>
										  <button type="submit" class="btn btn-primary">Go</button>
										</s:form>
										</div>
									</div>
									
										<table class="table table-bordered" width="100%">
											<thead>
												<tr class="tableback">
													<th class="pdate">Prescription Date</th>
													<th class="pnamewidth">Patient Name</th>
													<th class="page">UHID</th>
													<th class="page">Age / Gender</th>
													<th class="page">Ward / Bed</th>
													<th class="page">Bill Date</th>
													<th class="page">Bill No</th>
													<th class="page">Product Name</th>
													<th class="page">Req. Qty</th>
													<th class="page">Sale. Qty</th>
													<th class="page">Status</th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="priscriptionlist">
													<tr class="even pointer">
														<td class=" "><s:property value="date"/></td>
														<td class=" "><s:property value="clientname" /></td>
														<td class=" "><s:property value="abrivationid" /></td>
														<td class=" "><s:property value="ageandgender" /></td>
														<td class=" ">
															<s:if test="ipdid!=0">
																<s:property value="wardname" /> / <s:property value="bedname" />
															</s:if> 
														</td>
														<td class=" "><s:property value="dateTime" /></td>
														<td class=" "><s:property value="billno" /></td>
														<td class=" "><s:property value="productname" /></td>
														<td class=" "><s:property value="reqqty" /></td>
														<td class=" "><s:property value="saleqty" /></td>
														<td class=" ">
															<s:if test="saleqty>0">
																Sold
															</s:if>
															<s:else>
																-
															</s:else>
														</td>
														
													</tr>
												</s:iterator>
											</tbody>
										</table>
								</div>
							</div>
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
 </body>
</html>