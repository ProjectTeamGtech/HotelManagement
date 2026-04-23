<!doctype html>
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
   
    <SCRIPT type="text/javascript" src="inventory/js/manageinventory.js"></SCRIPT>
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

    <div id="wrap" class="animsition">
        

            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-6">
                            Manage Inventory | Vendors
                        </div>
                        <div class="col-md-6 text-right">
                            <a href="#" type="button" class="btn btn-primary" onclick="vendorPopup()" >Add Vendor</a>
                        </div>
                    </div> 
                    <div class="">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Manufac_Name</th>
                                    <th style="width: 36%;">Address</th>
                                    <th>Email</th>
                                    <th>Brand Name</th>
                                    <th>Mob_No</th>
                                    <th>Phone</th>
                                    <th>Delivery Time</th>
                                    <th>Edit</th>
                                </tr>
                            </thead>
                            <tbody>
                               <s:iterator value="vendorlist">
								<tr>
								<td class="text-center"><s:property value="id" /></td>
								<td class="text-center"><s:property value="name"/></td>
								<td class="text-center"><s:property value="address"/></td>
								<td class="text-center"><s:property value="email"/></td>
								<td class="text-center"><s:property value="brand_name"/></td>
								<td class="text-center"><s:property value="mobile_pri"/></td>
								<td class="text-center"><s:property value="phone1"/></td>
								<td class="text-center"><s:property value="min_delivery_time"/></td>
								<td class="text-center"><a href="#" onclick="editVendor(<s:property value="id"/>)" class="text-primary">
										<i class="fa fa-edit"></i>
									</a>
								</td>
							</tr>
						</s:iterator>
                            </tbody>

                        </table><br />
                        
                        
                    </div>

                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->






    </div>


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
                        <form class="form-horizontal" name="vendorform" method="post">
                            <div class="form-group">
                                <input type="hidden" name="id" id="id"> 
                               <input type="hidden" name="index" value="test" id="index"> 
                                <label for="inputEmail3" class="col-sm-4 control-label">Name<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" id="name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Address :</label>
                                <div class="col-sm-8">
                                    <textarea name="address" id="address" class="form-control" rows="3"></textarea>
                                </div>
                            </div>
                           
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Email ID<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="email" id="email" name="email" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Brand Name<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="text" name="brand_name" id="brand_name" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Mobile<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="text" name="mobile_pri" class="form-control" id="mobile_pri">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Phone<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="text" name="phone1" class="form-control" id="phone1">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Min Delivery Time<span class="red">*</span> :</label>
                                <div class="col-sm-8">
                                    <input type="text" name="min_delivery_time" id="min_delivery_time" class="form-control" >
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addEditVendor()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>




</body>
</html>
