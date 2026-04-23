<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->

<%@ taglib prefix="s" uri="/struts-tags" %>

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
           
        }
        .miheight {
            min-height: 650px;
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
            padding: 0px 7px 0px 7px !important;
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
        p {
            margin: 7px 0 0px;
        }
        .panel-default > .panel-heading {
            color: #fff !important;
            background-color: #f5f5f5;
            border-color: #ddd;
            background: #6699CC !important;
        }
        h4 {
            margin-top: 8px;
            margin-bottom: -10px;
        }
        .payslro {
            background-color: rgb(237, 237, 237);
            font-size: 16px;
            text-align: center;
            margin-top: 15px;
            margin-bottom: 15px;
        }
        .backti {
            background-color: rgb(239, 239, 239);
            font-size: 17px;
        }
        .printicon {
            text-align: right;
            margin-top: -23px;
            margin-bottom: 10px;
        }
        .martop100 {
            margin-top: 100px;
        }
        .mainhead{
        text-align: center;
    background-color: #424a5d;
    color: white;
    font-size: large;
        }
         .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
}
    </style>


</head>





<body id="his" class="appWrapper">

    <div id="wrap" class="animsition">
      
     
        <!--/ CONTROLS Content -->
        <!-- ====================================================
        ================= CONTENT ===============================
        ===================================================== -->
        <section id="">

            <div class="page page-sidebar-xs-layout">
            <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="padding-left:0px;padding-right:0px;">
				
				
			</div>
			
			
			 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8" style="padding-left:0px;padding-right:0px;">
				
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp" %>
			
				
			</div>
			
			
			
			 <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="padding-left:0px;padding-right:0px;">
				
				
			</div>
			
			
			
			
			
			
			
			
			

                <div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">

                        <div class="pagecontent">



                            <div class="add-nav">
                               
                                <div role="tabpanel">

                                    <div class="tab-content">
                                        <!-- tab in tabs -->
                                        <div role="tabpanel" class="" id="details">



                                            <!-- row -->
                                            <div class="row">
                                                <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 hidden-print"></div>
                                                <!-- col -->
                                                <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">


                                                    <!-- tile -->
                                                    <section class="">


                                                        <!-- tile body -->
                                                        <div class="tile-body">


                                          	                  <!-- row -->
                                                            <div class="row">


                                                            </div>
                                                          
                                                            <!-- /row -->
                                                            <!-- row -->
                                                            <div class="row b-t pt-20">
                                                                <div class="col-md-5 ">

                                                                    <ul class="list-unstyled text-default lt mb-20 paysliptdfont">
                                                                        <li class="paysliptdfont">
                                                                        <label>Employee Name : </label>
                                                                        <s:property value="emp_name"/>
                                                                        </li>
                                                                       <li class="paysliptdfont">
                                                                       <label>Employee Number : </label>
                                                                       <s:property value="emp_id"/>
                                                                       </li>
                                                                        <li class="paysliptdfont">
                                                                        <label>Employee Role : </label>
                                                                        <s:property value="emp_role"/>
                                                                        </li>
                                                                    </ul>

                                                                </div>
                                                                <!-- /col -->

                                                            </div>
                                                            <!-- /row -->


                                                        </div>
                                                        <!-- /tile body -->

                                                    </section>
                                                    <!-- /tile -->
                                                    <!-- tile -->
                                                    <div class="mainhead">
                                                             Pay Slip For <s:property value="Monthandyear"/>
                                                            </div>
                                                    <section class="tile tile-simple">

                                                        <!-- tile body -->
                                                        <div class="">
                                                            <div class="col-lg-6 col-md-6 padingleftrightzeroi">
                                                            
                                                            
                                                                <table width="100%">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td width="60%" valign="top" align="center">
                                                                                <div>

                                                                                    <table width="90%" border="0" cellspacing="0" cellpadding="0" valign="top" align="right" class="table">
                                                                                        <tbody>
                                                                                            <tr class="">
                                                                                                <td class="payslipbackti" colspan="2" style="text-align:center;">EARNINGS</td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">Emoluments</td>
                                                                                                <td class="text-right paysliptdfont">Amount (Rs.)</td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">Basic</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="salaryTotal"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">HRA</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="hra"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">Medical Allowance</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="medical_allowance"/></td>
                                                                                            </tr> 
                                                                                            <tr class="alt_tr">
                                                                                                <td class="borright paysliptdfont">Conveyance</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="conveyance"/></td>
                                                                                            </tr>
                                                                                              <tr class="alt_tr">
                                                                                                <td class="borright paysliptdfont">Washing</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="washing"/></td>
                                                                                            </tr>
                                                                                           <%--    <tr class="alt_tr">
                                                                                                <td class="borright">Vehicle Pass</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="vehiclepass"/></td>
                                                                                            </tr> --%>
                                                                                              <tr class="alt_tr">
                                                                                                <td class="borright paysliptdfont">Per. Development Allow.</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="perdevallow"/></td>
                                                                                            </tr>
                                                                                        <!--     <tr>
                                                                                            
                                                                                                <td class="borright">QPLC*</td>
                                                                                                <td class="text-right"><i class="fa fa-rupee"></i> 0</td>
                                                                                            </tr> -->

                                                                                            <tr class="alt_tr">
                                                                                                <td class="borright paysliptdfont">Gross Pay (A)</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="gross_pay"/></td>
                                                                                            </tr>

                                                                                        </tbody>
                                                                                    </table>

                                                                                </div>
                                                                            </td>

                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <div class="col-lg-6 col-md-6">
                                                                <table width="100%">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td width="60%" valign="top" align="center">
                                                                                <div>

                                                                                    <table width="90%" border="0" cellspacing="0" cellpadding="0" valign="top" align="right" class="table">
                                                                                        <tbody>
                                                                                            <tr class="payslipbackti">
                                                                                                <td class="payslipbackti" colspan="2" style="text-align:center;">DEDUCTIONS</td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">Common Deduction</td>
                                                                                                <td class="text-right paysliptdfont">Amount (Rs.)</td>
                                                                                            </tr>
                                                                                         <%--    <tr>
                                                                                                <td class="borright">Loan (if any)</td>
                                                                                                <td class="text-right"><i class="fa fa-rupee"></i> <s:property value="loan"/></td>
                                                                                            </tr> --%>
                                                                                           
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">Professional Tax (if any)</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="prefessional_tax"/></td>
                                                                                            </tr>
                                                                                             <tr>
                                                                                                <td class="borright paysliptdfont">Employee P.F.</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="emp_pf"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">Employee ESI</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="emp_esi"/></td>
                                                                                            </tr> 
                                                                                           <%--  <tr class="alt_tr">
                                                                                                <td class="borright"> Leave <s:property value="no_sundays"/> Days</td>
                                                                                                <td class="text-right"><i class="fa fa-rupee"></i> <s:property value="leave"/></td>
                                                                                            </tr> --%>
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">Income Tax</td>
                                                                                                <td class="text-right"><i class="fa fa-rupee"></i> <s:property value="taxable"/></td>
                                                                                            </tr>
                                                                                             <tr>
                                                                                                <td class="borright paysliptdfont">Deduction</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="other_deduction"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="borright paysliptdfont">Total Deductions (B)</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="deductions"/></td>
                                                                                            </tr>

                                                                                            <tr class="alt_tr" style="background-color: rgb(239, 239, 239);">
                                                                                                <td class="borright paysliptdfont">NET Salary</td>
                                                                                                <td class="text-right paysliptdfont"><i class="fa fa-rupee"></i> <s:property value="netpay"/></td>
                                                                                            </tr>

                                                                                        </tbody>
                                                                                    </table>

                                                                                </div>
                                                                            </td>

                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <div class="col-lg-12 col-md-12  padingleftrightzeroi">
                                                            <br>  <br>  <br>
                                                                <div class="col-lg-8 col-md-8 paysliptdfont padingleftrightzeroi">
                                                                    <b class="paysliptdfont">Note:</b>
                                                                    <p class="paysliptdfont">Unpaid leaves are deducted from Salary excluding QPLC</p>
                                                                    <p class="paysliptdfont"><span class="red">*</span>Quarterly Performance Link Cumulative (Paid Quarterly)</p>
                                                                </div>
                                                                <div class="col-lg-4 col-md-4" style="text-align: right;">
                                                                    <p class="paysliptdfont">Stamp & Signature</p>
                                                                </div>
                                                            </div>
                                                            

                                                        </div>
                                                        <!-- /tile body -->

                                                    </section>
                                                    <!-- /tile -->

												<!-- <a type="button"  class="btn btn-primary savebigbtn hidden-print" style="float: right;"  title="Print" onclick=""><i class="fa fa-print"></i></a> -->
												<input type="button"  class="btn btn-primary savebigbtn hidden-print" style="float: right; margin-right: 4px;width: 15%" value="Print Payslip" onclick="printpage()"/></a>
                                                </div>
                                                <!-- /col -->
                                                <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 hidden-print"></div>
                                            </div>
                                            <!-- /row -->

                                        </div>
                                    </div>
                                </div>
                            </div>



                        </div>



                        
                    </div>

                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->






    </div>


    <!--Edit Modal-->
    <!-- Allowances Modal -->
    <div class="modal fade" id="Allowances" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Allowances</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <table width="100%">
                            <tbody>
                                <tr>
                                    <td width="60%" valign="top" align="center">
                                        <div>

                                            <table width="90%" border="0" cellspacing="0" cellpadding="0" valign="top" align="right" class="table">
                                                <tbody>
                                                    <tr>
                                                        <td class="borright">DA on TA ( fixed)</td>
                                                        <td><input type="text" value="0.00" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright">SPECIAL PAY ( fixed)</td>
                                                        <td><input type="text" value="700.00" /></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright">PERSONAL PAY ( fixed)</td>
                                                        <td><input type="text" value="0.00" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright">TRANSPORT ALLOWANCE ( fixed)</td>
                                                        <td><input type="text" value="400.00" /></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright">HRA ( fixed)</td>
                                                        <td><input type="text" value="0.00" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright">DA ( fixed)</td>
                                                        <td><input type="text" value="0.00" /></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright">NPA ( fixed)</td>
                                                        <td><input type="text" value="0.00" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright">GRADE PAY ( fixed)</td>
                                                        <td><input type="text" value="0.00" /></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright">BASIC PAY ( fixed)</td>
                                                        <td><input type="text" value="0.00" /></td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>

                                        </div>
                                    </td>
                                    
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Ok</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Deduction Modal -->
    <div class="modal fade" id="Deduction" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Deduction</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <table width="100%">
                            <tbody>
                                <tr>
                                    <td width="60%" valign="top" align="center">
                                        <div>

                                            <table width="90%" border="0" cellspacing="0" cellpadding="0" valign="top" align="right" class="table">
                                                <tbody>
                                                    <tr>
                                                        <td class="borright">Employee's PF (12.00%)</td>
                                                        <td><input type="text" value="396.00" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright">Employee's ESI (2.00%)</td>
                                                        <td><input type="text" value="700.00" /></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright">canteen ( fixed)</td>
                                                        <td><input type="text" value="230.00" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright">leave ( fixed)</td>
                                                        <td><input type="text" value="100.00" /></td>
                                                    </tr>
                                                    <tr class="alt_tr">
                                                        <td class="borright">Some Fixed Deduction ( fixed)</td>
                                                        <td><input type="text" value="300.00" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="borright">My Deductions ( fixed)</td>
                                                        <td><input type="text" value="30.00" /></td>
                                                    </tr>
                                                   
                                                </tbody>
                                            </table>

                                        </div>
                                    </td>

                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Ok</button>
                </div>
            </div>
        </div>
    </div>


</body>
</html>
