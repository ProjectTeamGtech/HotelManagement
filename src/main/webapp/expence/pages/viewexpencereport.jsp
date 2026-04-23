<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <link rel="icon" href="assets/img/favicon.ico">
    <title>Advance Practice Management</title>
  

	<script type="text/javascript" src="expence/js/expencelist.js"></script>
   
    
   
    <!--Bootstrap select css-->
    <link href="_assets/css/priscription/bootstrap-select.css" rel="stylesheet" />

    <style>
        .wrapperfixed {
            display: inline-block;
            margin-top: 35px;
            padding: 0px 0px 0px;
            width: 100%;
            height: 600px;
        }
        .pharna {
            margin-left: 0px;
        }
        .details {
           
            margin-top: -39px;
            margin-bottom: 10px !important;
            color: #FFF;
        }
       
        .martop2 {
            margin-top: 4px;
        }
        .addvoucher {
            margin-top: 6px;
            float: left;
        }
        .amtwidth {
            width: 35%;
        }
        .form-horizontal .control-label {
            text-align: right;
        }
        .vno {
            width: 85px;
        }
        .marlftmin4 {
            margin-left: -4px;
        }
        .payrece {
            background-color: rgba(230, 230, 230, 0.69);
            padding: 0px 10px 10px 17px;
            margin-top: -11px;
        }
        
        .serheight {
            height: 31px;
        }
        .searbiox {
            margin-left: 15px;
            margin-top: -10px;
        }
        .martops{
            margin-top:-10px;
        }
        .datecol1 {
            width: 11%;
        }
        .satuscol2 {
            width: 59%;
        }
        .merchantcol3 {
            width: 31%;
        }
        .paidcol4 {
            width: 7%;
        }
        .categorycol5 {
            width: 24%;
        }
        .tdline {
            padding: 4px 3px 8px 5px !important;
        }
        .form-control {
            background-color: #FFF !important;
        }
        .padleamout {
            padding-left: 0px;
            padding-right: 10px;
        }
        .padnil {
            padding-left: 0px;
            padding-right: 0px;
        }
        hr {
            margin-top: 5px;
            margin-bottom: 10px;
            border: 0;
            border-top: 1px solid #efefef;
        }
    </style>


</head>

<body>

   
        <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
        <!--main content start-->
        <section >
            <section class="wrapperfixed">

                <!-- page start-->
                <div class="row mt">
                    <aside>
                        <div class="">
                            <div class="col-lg-12 col-md-12">
                                <div class="row details">
                                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                        <h4>Expenses Reports</h4>
                                    </div>
                                </div>
                               
                                            <table class="table table-bordered marleft10" cellspacing="0" width="80%">
                                                <thead>
                                                    <tr class="tableback">
                                                      
                                                        <th class="datecol1">Date</th>
                                                        <th class="satuscol2">Name</th>
                                                        <th class="paidcol4 text-center">Print Report</th>
                                                       <th class="paidcol4 text-center">Download PDF</th>
                                                       <th class="paidcol4 text-center">Download Excel</th>
                                                      
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<s:if test="reportList.size>0">
                                                	<s:iterator value="reportList">
                                                    <tr>                                                       
                                                        <td><s:property value="lastmodified"/></td>
                                                        <td><s:property value="reportName"/></td>
                                                        <td class="text-center"><a href="printExpenceManagement?id=<s:property value="id"/>"><i class="fa fa-print"></i></a></td>
                                						<td class="text-center"><a href="printPdfExpenceManagement?id=<s:property value="id"/>" class="text-danger"><i class="fa fa-file-pdf-o"></i></a></td>
                                						<td class="text-center"><a href="printexcelExpenceManagement?id=<s:property value="id"/>" class="text-success"><i class="fa fa-file-excel-o"></i></a></td>
                                                    </tr>
                                                    </s:iterator>
                                                  </s:if>
                                                </tbody>
                                            </table>                                           
                                         
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </aside>
                </div>
                <!-- page end-->
            </section>
        </section><!-- /MAIN CONTENT -->