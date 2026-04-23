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
    <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
<script type="text/javascript" src="common/js/pagination.js"></script>

    <script type="text/javascript">
    
    
    $(document).ready(function() {

		 $("#date_join").datepicker({

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
.thcolor{
background-color: #339966 !important;
}

    </style>


</head>





<body id="his" class="appWrapper sidebar-xs-forced">

    <div id="wrap" class="animsition">
        <!-- ===============================================
        ================= HEADER Content ===================
        ================================================ -->
    
        
           
           <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Employee List</strong></h4>
                    </a>
                    </div>
                    
                    
                                       
            <div class="page page-sidebar-xs-layout col-lg-12">
 		
 		
                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 ">
                    
                    <form action="PayrollEmployee" method="get">	
                   <div class="form-inline">
                    <div class="form-group" style="width: 15%">
                    <s:select name="department" id="department" cssClass="form-control chosen-select" 
                                              list="departmentlist" listKey="dept_id" listValue="department" 
                                              headerKey="0" headerValue="Select Department" />
					</div>
					<%-- <div class="form-group">
                    <s:select name="department" id="department" cssClass="form-control chosen-select" 
                                              list="departmentlist" listKey="dept_id" listValue="department" 
                                              headerKey="0" headerValue="Select Department" />
					</div> --%>
					<div class="form-group">
						<input type="submit" value="Go" class="btn btn-primary thcolor"/>
					</div>
					<div class="form-group">
					 <a href="#" type="button" class="btn btn-primary thcolor" onclick="openPopup('addemployeePayrollEmployee')" >Add New Employee</a>
					</div>
                    </div><br>
                   <div class="col-lg-4 col-md-4" style="margin-left: -16px;">
			<s:textfield theme="simple" name="searchText" placeholder="Search By  Name"  cssClass="form-control" onkeyup="searchempname(this.value)" autocomplete="off"/>
			
		</div><br><br>
				
		
		
		<%--  <div class="col-lg-3 col-md-2">
			<s:textfield theme="simple" name="searchText" placeholder="Search By  Name"  cssClass="form-control" />
			
		</div> --%>
	</form>
                        <div class="">
                           
                            
                        </div>
      
                  </div>
                  
                  <div class="col-lg-3 col-md-2">
			
		</div>
		<div class="col-lg-1 col-md-1">
			
			</div>
	
   <br /><br />
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                   <th class="thcolor">Sr. No.</th> 
                                    <th class="thcolor">Emp Id</th>
                                    <th class="thcolor">Name</th>
                                    <th class="thcolor">Hospital Name</th>
                                    <th class="thcolor">Department</th>
                                    <th class="thcolor">Designation</th>
                                    <th class="thcolor">D.O.J</th>
                                    <th class="thcolor"></th>
                                </tr>
                            </thead>
                            <tbody id="tblsr">
								<%-- <%int i=0; %>	   --%>                          
                                <s:iterator value="employeelist">
                                <tr>
                                    <%-- <td><%=(++i) %></td> --%>
                                    <td><s:property value="emp_id"/></td>
                                     <td><s:property value="empcode"/></td> 
                                     <%-- <td><s:property value="emp_id"/></td> --%> 
                                    <td><s:property value="name"/></td>
                                    <td><s:property value="branch"/></td>
                                    <td><s:property value="department"/></td>
                                    <td><s:property value="designation"/></td>
                                    <td><s:property value="date_join"/></td>
                                    <td><a href= "editPayrollEmployee?selectedid=<s:property value="emp_id"/>"><i class="fa fa-edit"></i></a></td>
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
     <s:form action="PayrollEmployee" name="paginationForm" id="paginationForm" theme="simple">
     <s:hidden name="searchText"></s:hidden>
<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>
	

    <!--Edit Modal Employee-->
    <!-- Modal -->
    <div class="modal fade" id="addemployee" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Employee Details</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <s:form name="employeeform" theme="simple">
                        <div class="col-lg-6 col-md-6">
                        <%-- <div class="col-lg-2 col-md-2 form-group" style="padding: 0px;">
					<s:select id="initial" name="initial" list="initialList" headerKey=""  title="Select Initial" cssClass="form-control showToolTip" data-toggle="tooltip" />
				</div> --%>
				
				
				<div class="form-group">
                                <label for="inputEmail3" class="control-label">Emp. Code</label>
                                    <input type="text" class="form-control" id="empcode" name="empcode">
                            </div>
                            
                            
                        <div class="form-group">
                                <label for="inputEmail3" class="control-label">Name</label>
                                    <input type="text" name="name" id="name" class="form-control" >
                            </div>
                            <%-- <div class="form-group">
                                <label for="inputEmail3" class="control-label">Select Branch</label>
                                     <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" listValue="branch" cssClass="selectstyle form-control">
                                    </s:select>
                            </div> --%>
                             <div class="form-group">
                                <label for="inputEmail3" class="control-label">Designation</label>
                                    <input type="text" name="designation" class="form-control" id="designation">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Qualification</label>
                                    <input type="text" class="form-control" id="qualification" name="qualification">
                            </div>
                          <!--  <div class="form-group">
                                <label for="inputEmail3" class="control-label">Enter Password</label>
                                    <input type="password" name="password" id="password"  class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Confirm Password</label>
                                    <input type="password" name="cnfpassword" class="form-control">
                            </div> -->
                        </div>
                        <div class="col-lg-6 col-md-6">
                        <%--  <div class="form-group">
                                <label for="inputEmail3" class="control-label">Select Company</label>
                                    <s:select name="company" id="company" list="companylist" listKey="comp_id" listValue="company" cssClass="selectstyle form-control">
                                    </s:select>
                            </div> --%>
                            <%-- <div class="form-group">
                                <label for="inputEmail3" class="control-label">Select Department</label>
                                     <s:select name="department" id="department" list="departmentlist" listKey="dept_id" listValue="department" cssClass="selectstyle form-control">
                                    </s:select>
                            </div> --%>
                             <div class="form-group">
                                <label for="inputEmail3" class="control-label">Date of Joining</label>
                                    <input type="text" name="date_join" class="form-control" id="date_join">
                            </div>
                             
                                 <div class="form-group">
                                <label for="inputEmail3" class="control-label">Age</label>
                                    <input type="text" class="form-control" id="dob" name="dob">
                            </div>
                                <div class="form-group">
                                <label for="inputEmail3" class="control-label">Current Address</label>
                                    <input type="text" class="form-control" id="currentaddress" name="currentaddress">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Permanent Address</label>
                                    <input type="text" class="form-control" id="permanentaddress" name="permanentaddress">
                            </div>
                              <div class="form-group">
                                <label for="inputEmail3" class="control-label">Contact No.</label>
                                    <input type="text" class="form-control" id="contact" name="contact">
                            </div>
                             <div class="form-group">
                                <label for="inputEmail3" class="control-label">PAN No.</label>
                                    <input type="text" class="form-control" id="panno" name="panno">
                            </div>
                          <!--    <div class="form-group">
                                <label for="inputEmail3" class="control-label">Aadhar No.</label>
                                    <input type="text" class="form-control" id="aadharno" name="aadharno">
                            </div> -->
                             
                        </div>
                            
                           
                            
                            
                           
                           
                            
                           
                           
                            
                            <s:hidden id="emp_id" name="emp_id"></s:hidden>
                        </s:form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="addUpdateEmployee()">Add/Update</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
