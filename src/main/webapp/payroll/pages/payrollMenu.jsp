<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YUVICARE</title>

<link rel="icon" href="common/BootstrapNew/img/favicon.ico">
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

<aside id="sidebar" class="miheight">


                <div id="sidebar-wrap">

                    <div class="panel-group slim-scroll" role="tablist">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#sidebarNav">
                                        Navigation <i class="fa fa-angle-up"></i>
                                    </a>
                                </h4>
                            </div>
                            <div id="sidebarNav" class="panel-collapse collapse in" role="tabpanel">
                                <div class="panel-body">



                             <%
                               LoginInfo  loginInfo=LoginHelper.getLoginInfo(request);
                             
                               if(loginInfo.getUserType()==2){
                            	  
                             %>

                        
                                    <!-- ===================================================
                            ================= NAVIGATION Content ===================
                            ==================================================== -->
                                    <ul id="navigation">
                                        <!--<li><a href="Payrolldashboard.html"><i class="fa fa-dashboard"></i> <span>Home</span></a></li>
                                       --><li>
                                            <a href="#"><i class="fa fa-building"></i> <span>Company</span></a>
                                            <ul>
                                                <li><a href="managecompanyPayrollDashBoard"><i class="fa fa-caret-right"></i> Manage Company</a></li>
                                                <li><a href="managebranchesPayrollDashBoard"><i class="fa fa-caret-right"></i> Manage Branch</a></li>
                                                <li><a href="PayrollDepartment"><i class="fa fa-caret-right"></i> Manage Department</a></li>
                                                <li><a href="PayrollAllowance"><i class="fa fa-caret-right"></i> Manage Allownses</a></li>
                                                <li><a href="Deduction"><i class="fa fa-caret-right"></i> Manage Deduction</a></li>
                                                <li><a href="Tax"><i class="fa fa-caret-right"></i> Manage Tax</a></li>
                                                <li><a href="Bank"><i class="fa fa-caret-right"></i> Manage Bank</a></li>
                                                <li><a href="loanPayrollMaster"><i class="fa fa-caret-right"></i> Manage Loan</a></li>
                                                <li><a href="holidaysPayrollMaster"><i class="fa fa-caret-right"></i> Manage Holiday</a></li>
                                                <li><a href="leavePayrollMaster"><i class="fa fa-caret-right"></i> Manage Leave</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-user"></i> <span>Employee</span></a>
                                            <ul>
                                                <li><a href="PayrollEmployee"><i class="fa fa-caret-right"></i> Manage Employee</a></li>
                                                <li><a href="Payrollincrement"><i class="fa fa-caret-right"></i> Salary Master</a></li>
                                                <li><a href="Attendance"><i class="fa fa-caret-right"></i> Manage Attendence</a></li>
                                              <li><a href="salaryPayrollMaster"><i class="fa fa-caret-right"></i> Manage Salary</a></li>
                                                <!-- <li><a href="salaryPayrollMaster"><i class="fa fa-caret-right"></i> Manage Salary</a></li>
                                                 <li><a href="salaryPayrollMaster"><i class="fa fa-caret-right"></i> Manage Salary</a></li> -->
                                                  <li><a href="salarydetailsPayrollMaster"><i class="fa fa-caret-right"></i> Salary Details</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-bar-chart"></i> <span>Reports</span></a>
                                            <ul>
                                                <li><a href="holidayReportPayroll"><i class="fa fa-caret-right"></i> Holiday Report</a></li><!--
                                                <li><a href=""><i class="fa fa-caret-right"></i> Feedback Report</a></li>
                                                --><li><a href="loanReportPayroll"><i class="fa fa-caret-right"></i> Loan Report</a></li>
                                                <li><a href="employeeReportPayroll"><i class="fa fa-caret-right"></i> Employee List Report</a></li>
                                                <li><a href="attendanceReportPayroll"><i class="fa fa-caret-right"></i> Attendence Report</a></li>
                                                <li><a href="allowancedeductionReportPayroll"><i class="fa fa-caret-right"></i> Allownce Deduction Report</a></li>
                                                <li><a href="salaryReportPayroll"><i class="fa fa-caret-right"></i> Salary Report</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                    <!--/ NAVIGATION Content -->

                                   <%} else { %>
                                        <ul id="navigation">
                                         <li>
                                            <a href="#"><i class="fa fa-bar-chart"></i> <span>Reports</span></a>
                                            <ul>
                                                <li><a href="empholidayReportPayroll"><i class="fa fa-caret-right"></i> Holiday Report</a></li>
                                                <li><a href=""><i class="fa fa-caret-right"></i> Feedback Report</a></li>
                                                <li><a href="emploanReportPayroll"><i class="fa fa-caret-right"></i> Loan Report</a></li>
                                                <li><a href="weeksheetAttendance"><i class="fa fa-caret-right"></i> Week Sheet</a></li>
                                                <li><a href="empattendanceReportPayroll"><i class="fa fa-caret-right"></i> Attendence Report</a></li>
                                                <li><a href="empsalaryReportPayroll"><i class="fa fa-caret-right"></i> Salary Report</a></li>
                                            </ul>
                                        </li>
                                        </ul>
                                   <%} %>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>


            </aside>

    

</body>
</html>