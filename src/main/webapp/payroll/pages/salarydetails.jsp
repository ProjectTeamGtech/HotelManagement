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
    
    <SCRIPT type="text/javascript">

    window.onload = function(){
              
         $("#expiry_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
         var year=document.getElementById("selectyr").value;
     	document.getElementById("year").value=year;
    };
    
   	
         
   
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
					filename: "stcokstatusList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
     


</SCRIPT>
    
    
    <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
      <script type="text/javascript" src="common/js/pagination.js"></script>
      <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>
<body>

<div class="col-md-12">
									
								
						<h5><span class="text-uppercase"><b>Salary Details</b> &nbsp;  &nbsp;</span></h5>		 
 <s:form action="salarydetailsPayrollMaster" theme="simple" > 
  <s:hidden name="selectedyear" id="selectyr"/>
                   
							  <div class="col-lg-12 col-md-12 col-sm-12" style="margin-bottom: 13px;"> 
							<div class="form-inline">
							<div class="form-group">
							<label for="exampleInputName2">Month</label>
							<s:select cssClass="form-control"
										list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
										theme="simple" id="month" name="month" style="width: 45%" />
							</div>
							<div class="form-group">
							<label for="exampleInputName2">Year</label> <select name="year"
										id="year" class="form-control" style="width: 68%">
										<%
											for (int k = 1980; k <= 2020; k++) {
										%>
							<option value="<%=k%>"><%=k%></option>
										<%
											}
										%>
							</select>
							</div>
							

								&emsp;
							<div class="form-group">
							<input type="submit" class="btn btn-primary" value="Go">
							</div>
							</div>
						</div>
 					         </s:form>											
 						
                        <table class="my-table tablestock"  style="width: 100%;font-size: 8px; border: solid 1px #dfd8d4;">
                            <thead>
                                <tr>
                                   
                                  
                                    <th>Emp ID</th>
                                    <th>Name</th>
                                     <th>Designation</th>
                                    <th>Gross</th>
                                    <th>Basic</th>
                                    <th>HRA</th>
                                    <th>Convayence</th>
                                    <th>Washing</th>
                                    <th>Per Dev Allow</th>
                                    <th>PF</th>
                                    <th>PT</th>
                                     <th>Paid Days</th>
                                    <th>Income Tax</th>
                                   <th>Veh.Pass</th>
                                   <th>Other Ded</th>
                                   <th>Totl Ded</th>
                                   <th>Net Sal</th>
                                    <th>Deposit</th>
                                     <th>Sal After Dep</th>
                                      <th>Recovery</th>
                                       <th>Ac.No</th>
                                      <!--  <th>Edit</th> -->
                                         <th>Print</th> 
                                    
                                </tr>
                            </thead>
                            
                            <tbody>
                               <s:iterator value="salarydetaillist">
                                <tr>
                       
                                    <td><s:property value="emp_id"/></td>
                                    <td><s:property value="emp_name"/></td>
                                    <td><s:property value="designation"/></td>
                                    <td><s:property value="gross_pay"/></td>
                                    <td><s:property value="basic"/></td>
                                    <td><s:property value="hra"/></td>
                                    <td><s:property value="conveyance "/></td>
                                    <td><s:property value="washing "/></td>
                                    <td><s:property value="perdevallow"/></td>
                                    <td><s:property value="emp_pf"/></td>
                                    <td><s:property value="prefessional_tax"/></td>
                                    <td><s:property value=" "/></td>
                                    <td><s:property value="taxable"/></td>
                                    <td><s:property value="vehiclepass"/></td>
                                    <td><s:property value="other_deduction"/></td>
                                    <td><s:property value="total_deduction"/></td>
                                    <td><s:property value="netpay"/></td>
                                    <td><s:property value=" "/></td>
                                     <td><s:property value=" "/></td>
                                     <td><s:property value=" "/></td> 
                                       <td><s:property value=" "/></td> 
                                    <td><s:property value="bank_account"/></td>
                                   
                                       <%-- <td><a class="btn btn-primary" href="#" onclick="opencPopup('payslipPayrollincrement?emp_id=<s:property value="emp_id" />&month=<s:property value="month" />')">
                                     <i class="fa fa-pencil"></i>
                                     </a></td> --%>
                                    
                                     <td><a class="btn btn-primary" href="#" onclick="opencPopup('payslipPayrollincrement?emp_id=<s:property value="emp_id" />&month=<s:property value="selectedmonth" />&year=<s:property value="selectedyear" />')">
                                     <i class="fa fa-print"></i>
                                     </a></td>
                                </tr>
                                </s:iterator>
                            </tbody>

                        </table>
                        
                        </div>
                   
               
              
</body>
</html>