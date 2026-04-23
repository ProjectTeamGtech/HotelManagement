<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="s" uri="/struts-tags" %>    
    
<html class="no-js" lang="">
<!--<![endif]-->



<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="_assets/images/favicon.ico" />
    <SCRIPT type="text/javascript" src="payroll/js/branches.js"></SCRIPT>
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
    
      <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Branch</strong> Setting</h4>
                    </a>
                    </div> 
   
 
        <section id="">

            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <div class="row col-lg-12 col-md-12">
                            <a href="" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addcbranch">Add</a>
                        </div>
                        <br /><br />
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>Sr. No.</th>
                                    <th>Branch</th>
                                    <th>Address</th>
                                    <th>City</th>
                                    <th>State</th>
                                    <th>Country</th>
                                    <th>Phone No.1</th>
                                    <th>Phone No.2</th>
                                    <th>Email ID</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                               <s:iterator value="branchesList">
                                <tr>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="branch"/></td>
                                    <td><s:property value="address"/></td>
                                    <td><s:property value="city"/></td>
                                    <td><s:property value="state"/></td>
                                    <td><s:property value="country"/></td>
                                    <td><s:property value="phone1"/></td>
                                    <td><s:property value="phone2"/></td>
                                    <td><s:property value="email"/></td>
                                    <td><a href="#" onclick="editbranch(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
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
    <div class="modal fade" id="addcbranch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Branch Details</h4>
                </div>
                <div class="modal-body">
                <div class="">
                <s:form theme="simple" action="addBranch" id="myform">
                <div class="col-lg-6 col-md-6 col-sm-6">
                				<div class="form-group">
								    <label for="exampleInputEmail1">Branch Name</label>
								     <input type="text" name="branch" class="form-control" id="branch"><label style="color:red" id="errbranch"></label>
								 </div>
								 <div class="form-group">
								    <label for="exampleInputEmail1">Branch City</label>
								     <input type="text" name="city" class="form-control" id="city"><label style="color:red" id="errcity"></label>
								 </div>
								 <div class="form-group">
								    <label for="exampleInputEmail1">Branch Country</label>
								     <input type="text" name="country" class="form-control" id="country"><label style="color:red" id="errcountry"></label>
								 </div>
								  <div class="form-group">
								    <label for="exampleInputEmail1">Email ID</label>
								     <input type="email" name="email" class="form-control" id="email"><label style="color:red" id="erremail"></label>
								 </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                				<div class="form-group">
								    <label for="exampleInputEmail1">Branch Address</label>
								      <input type="text" name="address" class="form-control" id="address"><label style="color:red" id="erraddress"></label>
								 </div>
								 <div class="form-group">
								    <label for="exampleInputEmail1">Branch State</label>
								     <input type="text" name="state" class="form-control" id="state"><label style="color:red" id="errstate"></label>
								 </div>
								 <div class="form-group">
								    <label for="exampleInputEmail1">Phone No.1</label>
								     <input type="text" name="phone1" class="form-control" id="phone1"><label id="errphone1" style="color:red"></label>
								 </div>
								  <div class="form-group">
								    <label for="exampleInputEmail1">Phone No.2</label>
								     <input type="text" name="phone2" class="form-control" id="phone2"><label style="color:red" id="errphone2"></label>
								 </div>
                </div>
                                <s:hidden id="id" name="id"/>
                            </s:form>
                
                </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addorEdit()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>    

</body>
</html>
