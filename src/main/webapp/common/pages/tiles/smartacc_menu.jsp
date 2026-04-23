<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<!-- start sidebar menu -->
 			   <s:form class="form-inline" action="Smartaccount" id="smartaccfrm" theme="simple" >
					<s:hidden name="action" id="action"></s:hidden>
  				</s:form>
             <div class="sidebar-container">
 				<div class="sidemenu-container navbar-collapse collapse fixed-menu">
	                <div id="remove-scroll">
	                    <ul class="sidemenu  page-header-fixed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
	                        <li class="sidebar-toggler-wrapper hide">
	                            <div class="sidebar-toggler">
	                                <span></span>
	                            </div>
	                        </li>
	                        <li class="sidebar-user-panel">
	                             
	                        </li>
	                        <li class="nav-item start active open">
	                            <a href="#" onclick="donabhsubmit('smartaccdash')" class="nav-link "> <i class="fa fa-home"></i> <span class="title"> Home</span>
	                            </a>
	                        </li>
	                         
	                         
	                         
	                         
	                         
	                         
	                         
	                         
	                          
	                         
	                          
	                   
                       <li class="nav-item">
	                            <a href="Committee.html" class="nav-link nav-toggle">
	                                <i class="fa fa-calculator"></i>
	                                <span class="title">Transaction</span>
	                                <span class="arrow"></span>
	                                 
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <a href="ExpenceManagement" class="nav-link ">
	                                        <span class="title">Manage Voucher</span>
	                                    </a>
	                                </li>
	                                 
                                    
                                    <li class="nav-item">
	                                    <a href="javascript:;" class="nav-link nav-toggle">
	                                        Create Voucher
	                                        <span class="arrow"></span>
	                                    </a>
	                                    <ul class="sub-menu">
	                                         
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-credit-card"></i> Payments</a>
	                                        </li>
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
                                                        <i class="fa fa-file-o" ></i> Receipt</a>
	                                        </li>
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-hdd-o"></i> Contra</a>
	                                        </li>
                                            <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-hdd-o"></i> Journal</a>
	                                        </li> 
                                            <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-file-pdf-o"></i> Mange Assets</a>
	                                        </li>
                                            
	                                    </ul>

	                                </li>
	                                 
	                            </ul>
	                        </li>
                            
                            
                            
                            
                            <li class="nav-item">
	                            <a href="#" class="nav-link nav-toggle">
	                                <i class="fa fa-bank"></i>
	                                <span class="title">Banking</span>
	                                <span class="arrow"></span>
	                                 
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <a href="Gaps_Tracker_Units.html" class="nav-link ">
	                                        <span class="title"> Bank Reconciliation</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="Gaps_Tracker_Task.html" class="nav-link ">
	                                        <span class="title">Account Transfer</span>
	                                    </a>
	                                </li>
	                                 
	                            </ul>
	                        </li>
                            
                            
                            
                            <li class="nav-item">
                              
	                            <a href="Resource_Library.html" class="nav-link nav-toggle">
	                                <i class="fa fa-book"></i>
	                                <span class="title">Ledger</span>
	                                <span class="arrow"></span>
	                                 
	                            </a>
                                
                                
	                            <ul class="sub-menu">
                                
                                 
                            <li class="nav-item">
	                                    <a href="javascript:;" class="nav-link nav-toggle">
	                                         View Ledger
	                                        <span class="arrow"></span>
	                                    </a>
	                                    <ul class="sub-menu">
	                                         
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-book"></i>  View Credit Book</a>
	                                        </li>
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-eye"></i>  View Cash Book</a>
	                                        </li>
	                                        <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-eye"></i> View NEFT</a>
	                                        </li>
                                            <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-eye"></i> View Cheque</a>
	                                        </li> 
                                            <li class="nav-item">
	                                            <a href="#" class="nav-link">
	                                                <i class="fa fa-eye"></i> View Cards Ledger</a>
	                                        </li>
                                            
	                                    </ul>
	                                </li>
	                                 
                                     
	                                 
	                                 <li class="nav-item  ">
	                                    <a href="email_view.html" class="nav-link ">
	                                        <span class="title">Manage Ledger</span>
	                                    </a>
	                                </li>
                                    <li class="nav-item  ">
	                                    <a href="email_view.html" class="nav-link ">
	                                        <span class="title">Opening & Closing Report</span>
	                                    </a>
	                                </li>
                                     
                                     
	                            </ul>
	                        </li>
                            
                            
                            
                            
                            
                            
                            
                            <li class="nav-item">
                              
	                            <a href="Resource_Library.html" class="nav-link nav-toggle">
	                                <i class="fa fa-file"></i>
	                                <span class="title">Reports</span>
	                                <span class="arrow"></span>
	                                 
	                            </a>
                                
                                
	                            <ul class="sub-menu">
                                
                                 
                             
	                                 
                                     
	                                 
	                                 <li class="nav-item  ">
	                                    <a href="email_view.html" class="nav-link ">
	                                        <span class="title"> Unpaid Account</span>
	                                    </a>
	                                </li>
                                    <li class="nav-item  ">
	                                    <a href="email_view.html" class="nav-link ">
	                                        <span class="title">Sales Payment
</span>
	                                    </a>
	                                </li>
                                     <li class="nav-item  ">
	                                    <a href="email_view.html" class="nav-link ">
	                                        <span class="title">Income Stmt
</span>
	                                    </a>
	                                </li>
                                     <li class="nav-item  ">
	                                    <a href="email_view.html" class="nav-link ">
	                                        <span class="title"> Balance Sheet
</span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
                       
                       
                                 
	                             
	                              
	                             
	                             
	                              
	                    </ul>
	                </div>
                </div>
            </div>
            <!-- end sidebar menu --> 
</body>
</html>
