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
		$("#expected_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
	});
    
    </SCRIPT>
    
     <script type="text/javascript" src="common/js/pagination.js"></script>
     <script type="text/javascript" src="accounts/js/additionalCharges.js"></script>
     <script type="text/javascript" src="accounts/js/chargeAccountProcessing.js"></script>
</head>
<body>

<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
	<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
									
								</div>
								<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
								<h4>SMS Monthly Count </h4>
								</div>
								</div>
							</div>
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                       <div class="col-md-12">
                          <div class="form-inline">
						  	<s:form action="smscountdashboardClinicRegistration" theme="simple">
						  		<div class="form-group" style="width: 9%;">
						  			<s:select cssClass="form-control chosen-select" list="#{'00':'Select Month', '01':'JAN', '02':'FEB', '03':'MAR', '04':'APR','05':'MAY','06':'JUN', '07':'JUL', '08':'AUG', '09':'SEP', '10':'OCT','11':'NOV','12':'DEC'}" id="month_filter" name="month_filter" />
						  		</div>
						  		<div class="form-group" style="width: 5%;">
						  			<s:select cssClass="form-control chosen-select" list="#{'2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022','2023':'2023','2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028','2029':'2029','2030':'2030'}" id="year_filter" name="year_filter" />
						  		</div>
						  		<div class="form-group" style="width: 3%;">
						  		<button type="submit" class="btn btn-primary">Go</button>
						  		</div>
						  		<!-- <input type="button" onclick="printReport()" value="Download Excel" class="btn btn-warning hidden" /> -->
						   </s:form>
						   </div>
						  
                       </div>
                    </div> 
                    <div class="">
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr>
                                	<th style="width: 5%;">Sr. No</th>
                                	<th style="width: 5%;">Month</th>
                                	<th style="width: 5%;">Year</th>
                                	<th style="width: 5%;">Count</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%int i=1; %>
                              <s:iterator value="smscountmonthlylist">
              					<tr>
              						<td><%=i%></td>
              						<td><s:property value="month" /></td>
              						<td><s:property value="year" /></td>
              						<td><s:property value="counts" /></td>
              					</tr>
              					<%i++; %>
              				</s:iterator>
                            </tbody>
                        </table>
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