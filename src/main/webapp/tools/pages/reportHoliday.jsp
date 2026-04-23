<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->

<%@ taglib uri="/struts-tags" prefix="s" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
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
            min-height: 650px;
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
    </style>


</head>





<body id="his" class="appWrapper sidebar-xs-forced">

      

        <section id="">
            <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Holiday</strong> Report</h4>
                    </a>
                    </div>

            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <br />
                        <table align="right">
                            <tbody>
                                <tr>
                                    <td colspan="4" align="right">
                                        <a href="ManageHoliday.html" style="text-decoration:none;">
                                            <img src="_assets/img/set3.jpeg"  alt="Holiday Settings" border="0">
                                        </a>&nbsp;&nbsp;
                                        <a href="" onclick="window.print()" style="text-decoration:none;">
                                            <img src="_assets/img/print3.jpeg" alt="Print Holiday Details" onclick="window.print()" border="0">
                                        </a>&nbsp;&nbsp;
                                        <a href="#" style="text-decoration:none;cursor: pointer;">
                                            <img src="_assets/img/excel.jpeg" alt="Excel holiday Download" border="0">
                                        </a>&nbsp;&nbsp;
                                        <a href="#" style="text-decoration:none;cursor: pointer;">
                                            <img src="_assets/img/pdf1.jpeg" alt="Pdf holiday Download"  border="0">
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table my-table xlstable table-bordered" style="width: 40%;">
                            <thead>
                                <tr>
                                    <th>Sr. No.</th>
                                    <th>Date</th>
                                    <th>Events</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%int i=0; %>
                                <s:iterator value="holidayList">
                             
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="date"/></td>
                                    <td><s:property value="event"/></td>
                                </tr>
                                </s:iterator>
                            </tbody>

                        </table>
                       
                    </div>
                </div>

            </div>

        </section>
        <!--/ CONTENT -->








    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="adddeduc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Deduction Details</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Name</label>
                                <div class="col-sm-7">
                                    <input type="email" class="form-control" id="inputEmail3">
                                    <small>(Enter PF or ESI or others)</small>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Deduction Type</label>
                                <div class="col-sm-7">
                                    <select name="otstatus" class="selectstyle form-control">
                                        <option value="Percentage">Percentage</option>
                                        <option value="Fixed">Fixed</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Amount</label>
                                <div class="col-sm-7">
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





</body>
</html>
