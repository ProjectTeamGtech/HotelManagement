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

    
        <section id="">
            
             <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Leave</strong> Dashboard</h4>
                    </a>
                    </div>
            
    
        
            <div class="page page-sidebar-xs-layout">
                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                        <div class="">
                            <!-- <a href="" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addleave">Add</a> -->
                            <div style="float: right;">
                                <b>Branch Name :</b>
                                <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" listValue="branch" onchange="sortLeavesByBranch(this.value)" cssClass="selectstyle form-control">
                                    </s:select>
                            </div>
                        </div>
                        <br /><br />
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 5%">Sr. No.</th>
                                    <th style="text-align: center;">Employee Name</th>
                                    <th style="text-align: center;">Reason</th>
                                    <th style="text-align: center;">Type</th>
                                    <th style="text-align: center;">From Date</th>
                                    <th style="text-align: center;">To Date</th>
                                 <!--    <th style="text-align: center;">Approved By</th> -->
                                    <th style="text-align: center;">Approved Date</th>
                                    <th style="text-align: center;">Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                               <%int i=0; %>
                               <s:iterator value="leaveList">
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td style="text-align: center;"><s:property value="name"/></td>
                                    <td style="text-align: center;"><s:property value="leave_reason"/></td>
                                     <td style="text-align: center;"><s:property value="leave_type"/></td>
                                    <td style="text-align: center;"><s:property value="fromdate"/></td>
                                    <td style="text-align: center;"><s:property value="todate"/></td>
                                   
                                    <%-- <td style="text-align: center;"><s:property value="approvedby"/></td> --%>
                                     <td style="text-align: center;"><s:property value="approveddate"/></td>
                                      <%--  <td style="text-align: center;">
              					   		<s:if test="status==0">
              					   			Requested
              					   		</s:if>
              					   		<s:elseif test="status==1">
              					   			APPROVED</s:elseif>
              					   		
              					   		<s:elseif test="status==2">
              					   			REJECTED
              					   		</s:elseif>
              					   </td> --%>
              					   
              					   	<td style="text-align: center;"><s:if test="status==0">
              					   			Requested
              					   		</s:if> <s:elseif test="status==1">
              					   			APPROVED</s:elseif> <s:elseif test="status==3">
              					   			APPROVED BY HR</s:elseif> <s:elseif test="status==2">
              					   			REJECTED
              					   		</s:elseif></td>
                                <%--     <td><a href="#" onclick="editleave(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td> --%>
                                 
                                </tr>
                                </s:iterator> 
                            </tbody>

                        </table>
                    </div>

                    

                </div>
<%-- <s:form action="PayrollDashBoard" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromdate"></s:hidden>
							     <s:hidden name="todate"></s:hidden>
							     <s:hidden name="searchtext"></s:hidden>
							     <s:hidden name="filter_status"></s:hidden>
									<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" />
											Records
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s: form>  --%>
                 
  
            </div>


                  <form action="leavePayrollMaster" name="branchform">
                    <input type="hidden" value="" name="branch" id="keybranch" >
                  </form>

        </section>
        <!--/ CONTENT -->



    <!--Edit Modal-->
    <!-- Modal -->
<%--     <div class="modal fade" id="addleave" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Advances Details</h4>
                </div>
                <div class="modal-body">
                    <div class="">
                        <s:form theme="simple" name="leaveform">
                        <div class="col-lg-6 col-md-6">
                         <div class="form-group">
                                <label for="inputEmail3" class="control-label">Branch Name</label>
                                 <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" listValue="branch" cssClass="selectstyle form-control">
                                    </s:select>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Employee Name</label>
                                    <s:select name="name" id="name" list="employeelist" listKey="emp_id" listValue="name" cssClass="selectstyle form-control">
                                    </s:select>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Type</label>
                                    <select name="leave_type" id="leave_type" class="form-control">
                                        <option value="Monthly">Monthly</option>
                                        <option value="Yearly">Yearly</option>
                                    </select>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                        <div class="form-group">
                                <label for="inputEmail3" class=" control-label">Short Name</label>
                                    <input type="text" class="form-control" id="short_name" name="short_name">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">No. of Days</label>
                                    <input type="text" class="form-control" id="no_days" name="no_days">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Encashable</label>
                                    <select name="encashable" id="encashable" class="form-control">
                                        <option value="No">No</option>
                                        <option value="Yes">Yes</option>
                                    </select>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="control-label">Carry Over</label>
                                    <select name="carryover" id="carryover" class="form-control">
                                        <option value="No">No</option>
                                        <option value="Yes">Yes</option>
                                    </select>
                            </div>
                        </div>
                            <s:hidden name="id" id="id"></s:hidden>
                        </s:form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="addUpdateLeave()" class="btn btn-primary">Add/Update</button>
                </div>
            </div>
        </div>
    </div> --%>

</body>
</html>
