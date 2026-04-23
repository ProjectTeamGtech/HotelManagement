<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->

<%@taglib prefix="s" uri="/struts-tags"%>
<html class="no-js" lang="">
<!--<![endif]-->

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <SCRIPT type="text/javascript" src="payroll/js/allowances.js"></SCRIPT>


    <!-- ==========================================
    ================= Modernizr ===================
    =========================================== -->
    <script src="assets/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    <!--/ modernizr -->


    <script type="text/javascript">
    
         function convert(text) {
        	   
        	 var data=text.toUpperCase();
        	 document.getElementById("allowance_name").value=data;
        	 
         }
    
    </script>

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
            padding: 0px 0px 0px 7px !important;
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
        ================= HEADER Content ===================
        ================================================ -->
       
        <!--/ HEADER Content  -->
        <!-- =================================================
        ================= CONTROLS Content ===================
        ================================================== -->
        <div id="controls">
        <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Allowances</strong> Setting</h4>
                    </a>
                    </div>
            
        <section id="">
            <div class="page page-sidebar-xs-layout">
					
                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <div class="row col-lg-12 col-md-12">
                            <a href="#" onclick="addPopup()" type="button" class="btn btn-primary">Add</a>
                        </div>
                        <br /><br />
                        <table class="table my-table xlstable table-bordered" style="width: 40%;">
                            <thead>
                                <tr>
                                    <th>Sr. No.</th>
                                    <th>Allowances name</th>
                                    <th>Allowances Type</th>
                                    <th>Value (in % / value)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                               <s:iterator value="listallowances">
                             
                                <tr>
                                    <td><s:property value="id" /></td>
                                    <td><s:property value="allowance_name" /></td>
                                    <td><s:property value="allowanceType" /></td>
                                    <td><s:property value="value" /></td>
                                    <td><a href="#" onclick="editAllowance(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
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
    <div class="modal fade" id="addallow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <s:form theme="simple" action="addPayrollAllowance" name="allowanceform">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Allowances Details</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form>
                        	<div class="form-group">
							    <label for="exampleInputEmail1">Allowances Name</label>
							     <input type="text" name="allowance_name" id="allowance_name" class="form-control" onchange="convert(this.value)" ><label style="color:red" id="errallowance_name"></label>
						  	</div>
						  	<div class="form-group">
							    <label for="exampleInputEmail1">Allowances Type</label>
							      <select name="allowanceType" id="allowanceType" class="selectstyle form-control">
                                        <option value="Percentage">Percentage</option>
                                        <option value="Fixed">Fixed</option>
                                    </select>
						  	</div>
						  	<div class="form-group">
							    <label for="exampleInputEmail1">Value (%/value)</label>
							     <input type="text" name="value" id="value" class="form-control"><label style="color:red" id="errvalue"></label>
							     <s:hidden id="id" name="id"/>
						  	</div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-primary" value="Add/Update" onclick="addEditAllowance()"/>
                </div>
            </div>
        </div>
       </s:form>    
    </div>
</body>
</html>
