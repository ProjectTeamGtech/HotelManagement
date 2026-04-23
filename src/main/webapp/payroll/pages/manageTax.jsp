<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->

<%@taglib prefix="s" uri="/struts-tags" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="payroll/js/taxation.js"></script>



   
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
    </style>


</head>





<body id="his" class="appWrapper sidebar-xs-forced">

    <div id="wrap" class="animsition">
        <!-- ===============================================
        <!--/ CONTROLS Content -->
        <!-- ====================================================
        ================= CONTENT ===============================
        ===================================================== -->
        <section id="">
                       <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Tax</strong> Setting</h4>
                    </a>
                    </div>
                       
            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <div class="row col-lg-12 col-md-12">
                            <a href="" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addtax">Add</a>
                        </div>
                        <br /><br />
                        <table class="table my-table xlstable table-bordered" style="width: 50%;">
                            <thead>
                                <tr>
                                    <th>Sr. No.</th>
                                    <th style="text-align: right;">From Amount</th>
                                    <th style="text-align: right;">To Amount</th>
                                    <th>Gender</th>
                                    <th>Tax(in %)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                               <s:iterator value="taxlist">
                                <tr>
                                    <td><s:property value="id"/></td>
                                    <td class="text-right"><s:property value="fromamount"/></td>
                                    <td class="text-right"><s:property value="toamount"/></td>
                                    <td><s:property value="gender"/></td>
                                    <td class="text-right"><s:property value="tax"/></td>
                                    <td><a href="#" onclick="edittax(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
                                </tr>
                                </s:iterator>
                            </tbody>

                        </table>
                    </div>

                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->






    </div>


    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="addtax" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Tax Setting</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <s:form theme="simple" name="taxform">
                         	<div class="form-group">
							    <label for="exampleInputEmail1">From Amount</label>
							    <input type="text" name="fromamount" class="form-control" id="fromamount"><label id="errfromamount" style="color:red"></label>
						  	</div>
						  	<div class="form-group">
							    <label for="exampleInputEmail1">To Amount</label>
							    <input type="text" name="toamount" class="form-control" id="toamount"><label id="errtoamount" style="color:red"></label>
						  	</div>
						  	<div class="form-group">
							    <label for="exampleInputEmail1">Gender</label><br>
							     	<label class="radio-inline">
                                        <input type="radio" name="gender" id="male" value="Male"> Male
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="female" value="Female"> Female
                                    </label>
                                    <label id="errgender" style="color:red"></label>
						  	</div>
                           <div class="form-group">
							    <label for="exampleInputEmail1">Tax (in %)</label>
							     <input type="text" class="form-control" id="tax" name="tax"><label id="errtax" style="color:red"></label>
							     <s:hidden id="id" name="id" />
						  	</div>
                        </s:form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="addUpdateTax()" class="btn btn-primary">Add/Update</button>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
