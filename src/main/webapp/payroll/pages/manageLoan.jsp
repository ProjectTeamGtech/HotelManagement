	<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->


<%@ taglib prefix="s" uri="/struts-tags"%>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT> 
    
    
    <script type="text/javascript">
    
    
    $(document).ready(function() {

		 $("#date_format").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1980',
			changeMonth : true,
			changeYear : true

		 });
	 });
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
    
        <section id="">
        
            
            <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Loan</strong> Setting</h4>
                    </a>
                    </div>

            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <div class="row col-lg-12 col-md-12">
                            <a href="#" type="button" class="btn btn-primary" onclick="addLoanPopup()" >Add</a>
                        </div>
                        <br /><br />
                        <table class="table my-table xlstable table-bordered" style="width: 60%;">
                            <thead>
                                <tr>
                                    <th>Sr. No.</th>
                                    <th>Employee Name</th>
                                    <th>Branch Name</th>
                                    <th style="text-align:right;">Amount</th>
                                    <th>Date</th>
                                    <th>Instalments</th>
                                    <th>Deduction</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <% int i=0; %>
                               <s:iterator value="loanlist">
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="name"/></td>
                                    <td><s:property value="branch"/></td>
                                    <td class="text-right"><s:property value="amount"/></td>
                                    <td><s:property value="date_format"/></td>
                                    <td><s:property value="installments"/></td>
                                    <td><s:property value="deduction"/></td>
                                    <td><a href="#" onclick="editLoan(<s:property  value="id"/>)"><i class="fa fa-edit"></i></a></td>
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
    <div class="modal fade" id="addadvance" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Advances Details</h4>
                </div>
                <div class="modal-body">
                    <div class="">
                        <s:form theme="simple" name="loanform">
                        <div class="col-lg-6 col-md-6">
                        <div class="form-group">
							    <label for="exampleInputEmail1">Employee Name</label>
							     <s:select name="name" id="name" list="employeelist" listKey="emp_id" listValue="name" cssClass="form-control">
                                            
                                        </s:select>
                                        <label id="errname" style="color:red"></label>
                                        <a href="PayrollEmployee" class="btn btn-primary" title="Add Employee"><i class="fa fa-plus-square"></i></a>
							 </div>
							 <div class="form-group">
							    <label for="exampleInputEmail1">Branch</label>
							     <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" listValue="branch" cssClass="form-control">
                                            
                                    </s:select>
                                    <label id="errbranch" style="color:red"></label>
							 </div>
							 <div class="form-group">
							    <label for="exampleInputEmail1">Amount</label>
							     <input type="text" name="amount" id="amount" class="form-control">
                                    <label id="erramount" style="color:red"></label>
							 </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                        <div class="form-group">
							    <label for="exampleInputEmail1">Date</label>
							     <input type="text" name="date_format" id="date_format" class="form-control" >
                                    <label id="errdate_format" style="color:red"></label>
							 </div>
							  <div class="form-group">
							    <label for="exampleInputEmail1">No. Of Instalments</label>
							      <input type="text" name="installments" class="form-control" id="installments">
                                    <label id="errinstallments" style="color:red"></label>
							 </div>
							 <div class="form-group">
							    <label for="exampleInputEmail1">Deduction</label>
							      <label class="radio-inline">
                                        <input type="radio" name="deduction" id="yesded" value="Yes"> Yes
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="deduction" id="noded" value="No"> No
                                    </label>
							 </div>
                        </div>
                         	
							 
                            <s:hidden id="id" name="id"></s:hidden>
                        </s:form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="addUpdateLoan()" class="btn btn-primary">Add/Update</button>
                </div>
            </div>
        </div>
    </div>


    

</body>
</html>
