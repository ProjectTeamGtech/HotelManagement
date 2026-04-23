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


   <SCRIPT type="text/javascript">
   
      function sortEmployees(id) {
      
          document.getElementById("b1").value=id;
          document.sortemployee.submit();
      } 
     
     
   </SCRIPT>
 

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

             <div class="container-fluid">
					<a>
                        <h3><strong>Employee</strong> Report</h3>
                    </a>
                    </div>
                    
            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <div class="">
                            <table style="margin:0 auto;" border="0" cellpadding="0" cellspacing="0" height="46" width="100%">
                                <tbody style="background-color: rgb(242, 242, 242);">
                                    <tr>
                                        <td width="33"></td>
                                        <td>
                                            <b>Branch Name :</b>
                                           <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" onchange="sortEmployees(this.value)" listValue="branch" cssClass="selectstyle">
                                           </s:select>
                                        </td>
                                        <td width="32">
                                            
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            
                        </div>
                        <br />
                        <table align="right">
                            <tbody>
                                <tr>
                                    <td colspan="4" align="right">
                                        <a href="" onclick="window.print()" style="text-decoration:none;">
                                            <img src="_assets/img/print3.jpeg" alt="Print Holiday Details"  onclick="window.print()" border="0">
                                        </a>&nbsp;&nbsp;
                                        <a href="#" style="text-decoration:none;cursor: pointer;">
                                            <img src="_assets/img/excel.jpeg"   border="0">
                                        </a>&nbsp;&nbsp;
                                        <a href="#" style="text-decoration:none;cursor: pointer;">
                                            <img src="_assets/img/pdf1.jpeg" alt="Pdf holiday Download" border="0">
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table my-table xlstable table-bordered" style="width: 60%;">
                            <thead>
                                <tr>
                                    <th>Sr.No</th>
                                    <th>Emp Code</th>
                                    <th>Name</th>
                                    <th>Date Of Joining</th>
                                    <th>Designation</th>
                                    <th>Location Name</th>
                                    <th>Qualification</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%int i=0; %>
                                
                                <s:iterator value="employeelist">
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="empcode"/></td>
                                    <td><s:property value="name"/></td>
                                    <td><s:property value="date_join"/></td>
                                    <td><s:property value="designation"/></td>
                                    <td><s:property value="department"/></td>
                                    <td><s:property value="qualification"/></td>
                                </tr>
                                </s:iterator>
                            </tbody>

                        </table>
                       
                    </div>
                </div>

            </div>

        </section>
        <!--/ CONTENT -->


      <form action="employeeReportPayroll" name="sortemployee">
      
        <input type="hidden" name="branch" id="b1">
      </form>
 


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
