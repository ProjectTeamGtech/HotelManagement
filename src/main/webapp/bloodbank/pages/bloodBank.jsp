<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->

<%@ taglib prefix="s"  uri="/struts-tags" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <SCRIPT type="text/javascript" src="bloodbank/js/bloodbank.js"></SCRIPT>

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
            
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 5px 7px 7px 7px !important;
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
        .tab-content .tab-pane {
            padding: 0px;
        }
        .padsearch {
            padding-top: 15px;
            padding-bottom: 30px;
        }
    </style>


 


</head>





<body id="his" class="appWrapper sidebar-xs-forced">

        <section id="content">


            <div class="page page-sidebar-xs-layout">

                <div class="pageheader padright">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">

                        <div role="tabpanel">

                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs tabs-dark" role="tablist">
                                <li role="presentation" class="active"><a aria-expanded="true" href="#Bmanage" aria-controls="Bmanage" role="tab" data-toggle="tab"><img src="bloodbank/image/Blood-Bank.png" style="width: 10%;"/> Blood Manage</a></li>
                                <li class="" role="presentation"><a aria-expanded="false" href="#Donarlist" aria-controls="Donarlist" role="tab" data-toggle="tab"><i class="fa fa-list"></i> Blood Donor List</a></li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">

                                <div role="tabpanel" class="tab-pane active" id="Bmanage">
                                    <div class="padsearch">
                                       <div class="col-lg-3 col-md-3 col-xs-3" style="padding-left: 0px;">
                                           <input type="text"  placeholder="Search here" class="form-control"/>
                                       </div>
                                       <div class="col-lg-1 col-md-1 col-xs-1" style="padding-left: 0px;">
                                            <a href="" type="button" class="btn btn-primary"><i class="fa fa-search"></i></a> 
                                        </div>
                                        <div class="col-lg-2 col-md-2 col-xs-2" style="padding-left: 0px;">
                                            <a href="#" type="button" class="btn btn-primary" onclick="addRow('bloodtable')"><i class="fa fa-plus-circle"></i> Add Blood Group</a> 
                                        </div>
                                        
                                    </div>
                                    <br />
                                    <table class="table my-table xlstable table-bordered" id="bloodtable" style="width: 24%;">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Blood Group</th>
                                                <th class="text-center">No of Bags</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <s:iterator value="bloodgroupList">
                                        
                                            <tr id="<s:property value="id"/>">
                                                <td class="text-center"><s:property value="blood_group"/></td>
                                                <td class="text-center"><s:property value="no_bags"/></td>
                                                <td class="text-center"><a href="#" onclick="editgroup(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>

                                            </tr>
                                            </s:iterator>
                                        
                                        </tbody>
                                       
                                    </table>
                                  
                                </div>

                                <div role="tabpanel" class="tab-pane" id="Donarlist">

                                    <div class="padsearch">
                                        <div class="col-lg-3 col-md-3 col-xs-3" style="padding-left: 0px;">
                                            <input type="text" placeholder="Search here" class="form-control" />
                                        </div>
                                        <div class="col-lg-2 col-md-2 col-xs-2" style="padding-left: 0px;">
                                            <a href="" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addDonor"><i class="fa fa-plus-circle"></i> Add Blood Donor</a>
                                        </div>
                                    </div>
                                    <br />
                                    <table class="table my-table xlstable table-bordered" style="width: 57%;">
                                        <thead>
                                            <tr>
                                                <th class="text-left">Name</th>
                                                <th class="text-center">Blood group</th>
                                                <th class="text-center">Age</th>
                                                <th class="text-center">Gender</th>
                                                <th class="text-center">Last Donation Date</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <s:iterator value="blooddonorsList">
                                            <tr>
                                                <td class="text-left"><s:property value="name" /></td>
                                                <td class="text-center"><s:property value="blood_group"/></td>
                                                <td class="text-center"><s:property value="age"/></td>
                                                <td class="text-center"><s:property value="gender"/></td>
                                                <td class="text-center"><s:property value="last_donation_date"/></td>
                                                <td class="text-center"><a href="#" onclick="editdonor(<s:property value="id"/>)"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;<a href="deletedonorBloodbank?id=<s:property value="id"/>" onclick="return cnfmDelete()" ><i class="fa fa-trash text-danger"></i></a></td>
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

        </section>
        <!--/ CONTENT -->




    <!--Edit Modal-->
    <div class="modal fade" id="editbgroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Blood Group</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal" name="bloodgroupform">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Blood Group*</label>
                                <div class="col-sm-7">
                                    <select id="blood_group" class="form-control validate[required]" name="blood_group">
                                        <option value="">Select Blood Group</option>
                                        <option value="O+">O+ </option>
                                        <option value="O-">O- </option>
                                        <option value="A+" selected="selected">A+ </option>
                                        <option value="A-">A- </option>
                                        <option value="B+">B+ </option>
                                        <option value="B-">B- </option>
                                        <option value="AB+">AB+ </option>
                                        <option value="AB-">AB- </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">No of Bags*</label>
                                <div class="col-sm-7">
                                    <input id="no_bags" class="form-control validate[required] text-input" name="no_bags" type="text">
                                </div>
                            </div>

                          <input type="hidden" id="bid" name="blood_group_id" >
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addUpdateBloodGroup()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>

    <!--Add Donor Modal-->
    <div class="modal fade" id="addDonor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Blood Donor</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form name="blooddonor_form" action="" method="post" class="form-horizontal" id="blooddonor_form">
                            <input name="action" value="edit" type="hidden">
                            <input name="blooddonor_id" value="2" type="hidden">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="first_name">Full Name<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="name" class="form-control validate[required,custom[onlyLetterSp]] text-input"  name="name" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="gender">Gender<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <label class="radio-inline">
                                        <input value="Male" class="tog validate[required]" name="gender" id="male" checked="checked" type="radio">Male
                                    </label>
                                    <label class="radio-inline">
                                        <input value="Female" class="tog validate[required]" name="gender" id="female" type="radio">Female
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="med_category_name">Age<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="age" class="form-control validate[required] text-input"  name="age" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label " for="phone">Phone<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="phone" class="form-control validate[,custom[phone]] text-input" name="phone" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label " for="email">Email<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <input id="email" class="form-control validate[required,custom[email]] text-input" name="email"  type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="bloodgruop">Blood Group<span class="require-field">*</span></label>
                                <div class="col-sm-7">
                                    <select id="bg1" class="form-control validate[required]" name="blood_group">
                                        <option value="">Select Blood Group</option>
                                        <option value="O+">O+ </option>
                                        <option value="O-">O- </option>
                                        <option value="A+">A+ </option>
                                        <option value="A-">A- </option>
                                        <option value="B+">B+ </option>
                                        <option value="B-">B- </option>
                                        <option value="AB+">AB+ </option>
                                        <option value="AB-">AB- </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="last_donet_date">Last Donation Date</label>
                                <div class="col-sm-7">
                                    <input id="last_donation_date" class="form-control  hasDatepicker" name="last_donation_date" type="text">
                                </div>
                            </div>

                           <input type="hidden" id="id" name="id">
                            
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addUpdateBloodDonor()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>


  
</body>
</html>
