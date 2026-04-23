<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    
    
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
        input[type="checkbox"] {
		    margin: 0px 0 0 !important;
		    margin-top: 1px \9;
		    line-height: normal;
		    /* padding-top: 15px; */
		    position: absolute !important;
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
        .table>tbody>tr>td, .table>tfoot>tr>td {
		    padding: 2px 5px 0px 5px !important;
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
     b, strong {
    font-weight: 900;
}   
    </style>
    
    <SCRIPT type="text/javascript">
    
    			function submitForm(){
    			
    					document.getElementById("myform").submit();
    			}
    		
    </SCRIPT>
    <script type="text/javascript">
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
 
    </script>
    <SCRIPT type="text/javascript">

 
    
	
      
  
    function printReport() {
				
				  $("#tablesort").table2excel({
					exclude: ".noExl",
					name: "Stock Status",
					filename: "CatalogueReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }
    
    
    function printProductExcel() {

        $(".xlsproduct").table2excel({
					exclude: ".noExl",
					name: "Product List",
					filename: "ProductList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
    function printStockReportExcel() {

        $(".tablestock").table2excel({
					exclude: ".noExl",
					name: "Stock Status Report",
					filename: "outsourcelist",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
     


</SCRIPT>
      <script type="text/javascript" src="common/js/pagination.js"></script>
      <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>




<body id="his" class="appWrapper sidebar-xs-forced">
		 						<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
    
        <section id="">

            <div class="" >
                <div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                       <div class="form-inline">
                           <s:form theme="simple" action="investoutsourceReport">
	                            <div class="form-group">
	                           		<span class="text-uppercase"><b>Investigation Out-Source Report</b> &nbsp; - &nbsp;</span>
	                           </div>
                           		
                           		<div class="form-group" style="width:7%">
									<s:textfield readonly="true" name="fromDate" id="fromdate" cssClass="form-control" placeholder="From Date" cssStyle="width:100%;" theme="simple"/>
								</div>
								<div class="form-group" style="width:7%">
									<s:textfield name="toDate" id="todate" cssClass="form-control" placeholder="To Date" cssStyle="width:100%;"/>
								</div>
                           		<div class="form-group"  style="width: 10%">
									<s:select name="outsourcelab"  style="width: 100%" list="outsrclablist" listKey="outsource_id" listValue="outsource_name"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="" headerValue="Select Lab"  ></s:select>
								</div>
								
								<div class="form-group"  style="width: 10%">
									<s:select name="clientId"  style="width: 100%" list="clientList" listKey="id" listValue="fullname"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="" headerValue="Select Patient"  ></s:select>
								</div>
								<div class="form-group"  style="width: 10%">
									<s:select name="invstName"  style="width: 100%" list="invsTypeList" listKey="id" listValue="name"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="" headerValue="Select Investigation"  ></s:select>
								</div>
								
								<div class="form-group"  style="width: 12%">
									<s:select name="department" id="department" 
								list="#{'0':'Select Department','5':'PathLab','7':'Radiology'}"
								cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
								</div>
								
								
								
								<div class="form-group">
								  	<button type="submit" class="btn btn-primary">Go</button>
								</div>
								<div class="form-group pull-right">
									<a href="#" class="btn btn-success"  onclick="printStockReportExcel()" title="Download CSV file" style="line-height: 14px;"> Download Excel</a>
								</div>
								<div class="form-group">
									<button type="button" onclick="printDiv('page_printer')" class="btn btn-warning">Print</button>
								</div>
                           	</s:form>
                           		
                           </div>
                      
                    </div> 
                    <div class="col-lg-12" id="page_printer">
                    			<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
								<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
													<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
											 <link href="common/css/printpreview.css" rel="stylesheet" />
										<%@ include file="/accounts/pages/letterhead.jsp" %>
										</div>
									</div>
								</div>
								
								<div class="print-visible hidden-md hidden-lg" >
									<span class="text-uppercase"><b>Investigation Out-Source Report</b> &nbsp; - &nbsp;</span>
								</div>
                        <table class="table my-table xlstable table-striped table-bordered tablestock" id="tablesort" style="width: 100%;">
                            <thead>
                               <tr>
                                 	<td class="my-table th" style="width: 2%;">Sr No</td>
                                    <td class="my-table th" style="width: 2%;">ID</td>
                                    <td class="my-table th" style="width: 10%;">Patient Name</td>
                                    <td class="my-table th" style="width: 2%;">UHID</td>
                                     <td class="my-table th" style="width: 8%;">Date</td>
                                    <td class="my-table th" style="width: 5%;">Recpt. Id.</td>
                                    <td class="my-table th" style="width: 22%;">Investigation Type</td>
                                    <td class="my-table th" style="width: 10%;">Out Source</td>
                                    <td class="my-table th" style="width: 5%;">Hand Over</td>
                                    <td class="my-table th" style="width: 5%;">Report Received</td>
                                   <!--  <td class="my-table th" style="text-align:right;width: 5%;">Charges</td> -->
                                    <td class="my-table th" style="text-align:right;width: 4%;">Amount To Paid</td>
                                    <td class="my-table th" style="text-align:right;width: 4%;">Amount </td>
                                    
                                    
                                </tr>
                            </thead>
                            
                            <tbody>
                            <%int i=1; %>
                               <s:iterator value="outsourcelist">
                                <tr>
                                <td><%=i %></td>
                                <%i++; %>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="fullname"/></td>
                                    
                                    <td><s:property value="Abrivationid"/></td>
                                     <td><s:property value="date"/></td>
                                    <td><s:property value="advoice"/></td>
                                    <td><s:property value="invsttype"/></td>
                                    <td><s:property value="outsource"/></td>
                                    <td><s:property value="handoverto"/></td>
                                    <td>
                                    	<s:if test="isreturnOS==0">
                                    		No
                                    	</s:if>
                                    	<s:else>
                                    		Yes
                                    	</s:else>
                                    </td>
                                    <%-- <td class="text-right">Rs.<s:property value="charge"/></td> --%>
                                     <td class="text-right">Rs.<s:property value="ammount"/></td>
                                    <td class="text-right">Rs.<s:property value="charge"/></td>
                                   
                                </tr>
                                </s:iterator>
                                <tr style="background-color: cyan !important">
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
                                <td><b>Total:</b></td>
                                <td align="right"><b>Rs. <s:property value="totalcharge"/></b></td>
                                </tr>
                            </tbody>

                        </table><br/>
                    </div>
                </div>
            </div>
        </section>

<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
<script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>
	
</body>
</html>
