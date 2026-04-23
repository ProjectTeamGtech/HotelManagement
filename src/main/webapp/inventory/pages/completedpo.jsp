<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->



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
    </style>
</head>





<body id="his" class="appWrapper sidebar-xs-forced">

  
        <section id="">

            <div class="">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-6">
                            Manage Inventory | Purchase Order | Completed Purchase orders
                        </div>
                        <div class="col-md-6 text-right">
                            <a href="createpo.html" type="button" class="btn btn-primary">Create PO</a>
                        </div>
                    </div>
                    <div class="topheadbaxck">
                        <form class="form-inline">
                            <div class="form-group" style="padding: 0px 0px 0px 15px;">
                                <input class="form-control" id="exampleInputName2" type="text" placeholder="Supplier Name">
                            </div>
                            <div class="form-group">
                                <input class="form-control" id="exampleInputEmail2" type="email" placeholder="Date">
                            </div>
                            <div class="form-group">
                                <select name="ctl00$CPHcontent$ddlReportType" class="form-control" id="CPHcontent_ddlReportType">
                                    <option value="Select Status">Select Status</option>
                                    <option value="Completed">Completed</option>
                                    <option value="TDeleted">Deleted</option>

                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Filter</button>
                            <button type="submit" class="btn btn-warning">Reset</button>
                        </form>
                    </div> 
                    <div class="">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Date</th>
                                    <th>Supplier Name</th>
                                    <th>Product Name</th>
                                    <th>Amount</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>GRPO201402140004</td>
                                    <td>2/14/2014</td>
                                    <td>General Surgical Company Pvt Ltd</td>
                                    <td>Assure</td>
                                    <td>Rs.129000</td>
                                    <td>Complete</td>

                                </tr>
                                <tr>
                                    <td>GRPO201402140004</td>
                                    <td>2/14/2014</td>
                                    <td>General Surgical Company Pvt Ltd</td>
                                    <td>Assure</td>
                                    <td>Rs.129000</td>
                                    <td>Deleted</td>

                                </tr>
                            </tbody>

                        </table><br />
                        
                        
                    </div>

                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->








    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="addvendor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add Manufacture</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Name<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Address :</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="3"></textarea>
                                </div>
                            </div>
                           
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Email ID<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Brand Name<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <a href="" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addbrand">Add Brand</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Mobile (Primary)<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Mobile (Secondory) :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Min Delivery Time<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Add/Edit</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="addbrand" class="modal fade" data-backdrop-limit="1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add Brands</h4>
                </div>
                <div class="modal-body">
                    <div class="input-group martop10">
                        <input id="txtmainmenus" class="form-control" style="width:400px;" value="" type="text">
                        <div class="input-group-btn" style="width:0% !important">
                            <button class="btn btn-success btn-Addmenu" id="btnAddmenu">Add Brand</button>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 15px;">
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <p>1. Abc</p>
                            <p>4. Abc</p>
                            <p>7. Abc</p>
                            <p>10. Abc</p>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <p>2. Abc</p>
                            <p>5. Abc</p>
                            <p>8. Abc</p>
                            <p>11. Abc</p>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <p>3. Abc</p>
                            <p>6. Abc</p>
                            <p>9. Abc</p>
                            
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>


    <script>
        $('#addbrand').on('show', function () {
            $('#addvendor').css('opacity', .5);
            $('#addvendor').unbind();
        });
        $('#addbrand').on('hidden', function () {
            $('#addvendor').css('opacity', 1);
            $('#addvendor').removeData("modal").modal({});
        });
    </script>
</body>
</html>
