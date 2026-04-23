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



    <script type="text/javascript" src="payroll/js/payrollmaster.js"></script>
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
       
        <section id="">

            <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Bank</strong> Setting</h4>
                    </a>
                    </div>
            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <div class="row col-lg-12 col-md-12">
                            <a href="#" type="button" class="btn btn-primary" onclick="addBankPopup()">Add</a>
                        </div>
                        <br /><br />
                        <table class="table my-table xlstable table-bordered" style="width: 40%;">
                            <thead>
                                <tr>
                                    <th>Sr. No.</th>
                                    <th>Emp Name</th>
                                    <th>Bank Name</th>
                                    <th>Bank Addresss</th>
                                    <th>Account No</th>
                                    <th>IFSC Code</th>
                                    
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="banklist">
                                <tr>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="name"/></td>
                                    <td><s:property value="bank"/></td>
                                    <td><s:property value="bankaddress"/></td>
                                    <td><s:property value="bank_account"/></td>
                                    <td><s:property value="ifsccode"/></td>
                                     
                                    <td><a href="#" onclick="editBank(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
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
    <div class="modal fade" id="addbank" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Bank Setting</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <s:form theme="simple" action="" name="bankform">
                         	<div class="form-group">
							    <label for="exampleInputEmail1">Employee Name</label>
							     <s:select name="name" id="name" list="employeelist" listKey="emp_id" listValue="name" cssClass="form-control chosen-select">
                                            
                                        </s:select>
						  	</div>
						  	<div class="form-group">
							    <label for="exampleInputEmail1">Bank Name</label>
							     <input type="text" name="bank" id="bank" class="form-control" ><label id="errbank" style="color:red"></label>
						  	</div>
						  	<div class="form-group">
							    <label for="exampleInputEmail1">Bank Address</label>
							     <input type="text" name="bankaddress" id="bankaddress" class="form-control" ><label id="errbankaddress" style="color:red"></label>
						  	</div>
						  	<div class="form-group">
							    <label for="exampleInputEmail1">Account No.</label>
							     <input type="text" name="bank_account" id="bank_account" class="form-control" ><label style="color:red" id="errbank_account"></label>
						  	</div>
						  	<div class="form-group">
							    <label for="exampleInputEmail1">IFSC Code</label>
							     <input type="text" name="ifsccode" id="ifsccode" class="form-control"><label id="errifsccode" style="color:red"></label>
						  	</div>
                            <s:hidden name="id" id="id"></s:hidden>
                        </s:form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="addUpdateBank()" class="btn btn-primary">Add/Update</button>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
