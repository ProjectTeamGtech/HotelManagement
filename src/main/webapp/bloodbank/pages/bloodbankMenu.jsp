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

<style>
.fa-plus:before {
    display:none;
}
.hz-menu #navigation > li.dropdown > ul {
    position: absolute;
    left: 0;
    top: 100%;
    width: auto;
    min-width: 190px !important;
    background-color: #424A5D;
}
#navigation .dropdown > ul li > a {
    padding: 3px 0px 3px 15px !important;
}
.maintite{
	    border-bottom: 1px solid #ddd;
    line-height: 22px;
        color: brown;
}
.stbacpa{
	    padding-right: 0px;
    padding-top: 10px;
}


</style>

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
                                    
                                    <!-- ===================================================
                            ================= NAVIGATION Content ===================
                            ==================================================== -->
                                    <ul id="navigation">
                                    <!-- <li><a href="requestbloodBloodbank" title="Blood Request"><i class="fa fa-tint" aria-hidden="true"></i> <span>Request</span></a></li>
                                    <li>
                                    	<a href="#" title="Masters"><i class="fa fa-file-text-o" aria-hidden="true"></i> <span>Masters</span></a>
                                    	<ul>
                                    		<li><a href="donormasterBloodbank"><i class="fa fa-caret-right"></i> Donor Master</a></li>
                                    		<li><a href="campmasterBloodbank"><i class="fa fa-caret-right"></i> Camp Master</a></li>
                                    		<li><a href="employeemasterBloodbank"><i class="fa fa-caret-right"></i> Employee Master</a></li>
                                            <li><a href="hospitalmasterBloodbank"><i class="fa fa-caret-right"></i> Hospital Master</a></li>
                                            --><!--<li><a href="doctormasterBloodbank"><i class="fa fa-caret-right"></i> Doctor Master</a></li>
                                            <li><a href="suppliermasterBloodbank"><i class="fa fa-caret-right"></i> Supplier Master</a></li>
                                       </ul>	
                                    </li>
                                    <li>
                                    	<a href="#" data-toggle="modal" data-target="#newregister" title="Registration"><i class="fa fa-user" aria-hidden="true"></i> <span>Registration</span></a>
                                    </li>
                                    <li><a href="accountingBloodbank" title="Accounting"><i class="fa fa-bar-chart" aria-hidden="true"></i> <span>Account</span></a></li>
                                    <li>
                                    	<a href="bloodstockBloodbank" title="Inventory"><i class="fa fa-cubes" aria-hidden="true"></i> <span>Inventory</span></a>
                                    </li>
                                    <li>
                                    	<a href="#" title="Reports"><i class="fa fa-bar-chart" aria-hidden="true"></i> <span>Reports</span></a>
                                    	<ul>
                                            <li><a href="#"><i class="fa fa-caret-right"></i> Daily Stock Report</a></li>
                                            <li><a href="#"><i class="fa fa-caret-right"></i> Issue Bottle</a></li>
                                            <li><a href="#"><i class="fa fa-caret-right"></i> Issue Report</a></li>
                                            <li><a href="#"><i class="fa fa-caret-right"></i> InUse Report</a></li>
                                            <li><a href="#"><i class="fa fa-caret-right"></i> Patient Report</a></li>
                                            <li><a href="#"><i class="fa fa-caret-right"></i> Party Wise Report</a></li>
                                            <li><a href="#"><i class="fa fa-caret-right"></i> Receipt Report</a></li>
                                            <li><a href="#"><i class="fa fa-caret-right"></i> Test Report</a></li>
                                       </ul>	
                                    </li>
                                    <li><a href="procurementbloodBloodbank" title="Procurement"><i class="fa fa-inr" aria-hidden="true"></i> <span>Procurement</span></a></li>
                                     <li><a href="testcomponentBloodbank" title="Test/Component"><i class="fa fa-flask" aria-hidden="true"></i> <span>Test/Component</span></a></li>
                                    <li><a href="crossmatchingBloodbank" title="Cross Matching"><i class="fa fa-exchange" aria-hidden="true"></i> <span>Cross Matching</span></a></li>
                                    <li><a href="settingBloodbank" title="Setting"><i class="fa fa-cog" aria-hidden="true"></i> <span>Setting</span></a></li>
                                     -->
                                   
                                    	<li><a href="requestbloodBloodbank" title="Dashboard"><i class="fa fa-caret-square-o-right" aria-hidden="true"></i> <span>Dashboard</span></a></li>
                                        <li class=""><a href="listProduct?isfromcathlab=2" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a></li>
                                        <li class="hidden"><a href="donorlistBloodbank" title="Donar List"><i class="fa fa-user" aria-hidden="true"></i> <span>Donars List</span></a></li>
                                        <li class="hidden"><a href="donatepatientBloodbank" title="Donate Blood To Patient"><i class="fa fa-hospital-o" aria-hidden="true"></i> <span>Donate Blood</span></a></li>
                                        <li class="hidden"><a href="banklistBloodbank" title="Blood Banks"><i class="fa fa-tint" aria-hidden="true"></i> <span>Blood Banks</span></a></li>
                                        <li class="hidden"><a href="instockBloodbank" title="Stock"><i class="fa fa-tint" aria-hidden="true"></i> <span>Stock</span></a></li>
                                        <li class="hidden"><a href="bloodcampBloodbank" title="Camp Details"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span>Camp Details</span></a></li>
                                    </ul>
                                    <!--/ NAVIGATION Content -->

                                </div>
                            </div>
                        </div>
                    </div>

                </div>
               
                 

            </aside>
            
			<!-- Donor Regsitration Modal -->
			<div id="newregister" class="modal fade" role="dialog">
			  <div class="modal-dialog modal-lg">
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Donor Registration</h4>
			      </div>
			      <div class="modal-body">
			        <div class="row">
			        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			        		<div class="col-lg-6 col-md-6 col-xs-6" style="border-right: 1px dashed #ddd;min-height: 184px;background-color: rgba(239, 239, 239, 0.5);">
			        			<h5 class="maintite"><b>DONOR REGISTRATION</b></h5>
			        			<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
			        				<div class="col-lg-3 col-md-3 col-xs-3" style="padding-left: 0px;">
			        					  <div class="form-group">
										    <label for="email">Batch No</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				<div class="col-lg-3 col-md-3 col-xs-3" style="padding-left: 0px;">
			        					  <div class="form-group">
										    <label for="email">Segment No</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				<div class="col-lg-3 col-md-3 col-xs-3" style="padding-left: 0px;">
			        					  <div class="form-group">
										    <label for="email">Volume</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				<div class="col-lg-3 col-md-3 col-xs-3" style="padding: 0px;">
			        					<div class="form-group">
										    <label for="email">Reg.Date</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				
			        			</div>
			        			
			        			<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
			        				<div class="col-lg-6 col-md-6 col-xs-6" style="padding: 0px;">
			        					  <div class="form-group">
										    <label for="email">Donor Name</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				<div class="col-lg-6 col-md-6 col-xs-6" style="padding-right: 0px;">
			        					<div class="form-group">
										    <label for="email">Existing Name</label>
										    <select class="form-control" name="donor_state">
											    <option value="Select Donor">Select Existing Donor</option>
											    <option value="Ruchita Ghugal">Ruchita Ghugal</option>
											    <option value="Abhinav Parmar">Abhinav Parmar</option>
										    </select>
										  </div>
			        				</div>
			        				
			        			</div>
			        			
			        			
			        		</div>
			        		<div class="col-lg-6 col-md-6 col-xs-6" style="background-color: beige;">
			        		<h5 class="maintite"><b>PATIENT DETAILS</b></h5>
			        			<div class="col-lg-12 col-md-12 col-xs-12 stbacpa">
			        				<div class="col-lg-6 col-md-6 col-xs-6" style="padding: 0px;">
			        					  <div class="form-group">
										    <label for="email">Patient Name</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				<div class="col-lg-3 col-md-3 col-xs-3" style="padding-right: 0px;">
			        					<div class="form-group">
										    <label for="email">Gender</label>
										    <select class="form-control" name="donor_state">
											    <option value="Male">Male</option>
											    <option value="Female">Female</option>
										    </select>
										  </div>
			        				</div>
			        				
			        				<div class="col-lg-3 col-md-3 col-xs-3">
			        					<div class="form-group">
										    <label for="email">Blood Group</label>
										    <select class="form-control" name="blood_group">
										    	<option value="0">Select</option>
										    	<option value="O+">O+</option>
										    	<option value="O-">O-</option>
										    	<option value="A+">A+</option>
										    	<option value="A-">A-</option>
										    	<option value="B+">B+</option>
										    	<option value="B-">B-</option>
										    	<option value="AB+">AB+</option>
										    	<option value="AB-">AB-</option>
										    </select>
										  </div>
			        				</div>
			        				
			        			</div>
			        			
			        			<div class="col-lg-12 col-md-12 col-xs-12 stbacpa">
			        			<div class="col-lg-4 col-md-4 col-xs-4" style="padding-left: 0px;">
			        					  <div class="form-group">
										    <label for="email">Ward / Bed</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				<div class="col-lg-4 col-md-4 col-xs-4" style="padding: 0px;">
			        					  <div class="form-group">
										    <label for="email">Incharge Doctor</label>
										    <select class="form-control" name="donor_state">
											    <option value="Select Doctor">Select Doctor</option>
											    <option value="Ruchita Ghugal">Ruchita Ghugal</option>
											    <option value="Abhinav Parmar">Abhinav Parmar</option>
										    </select>
										  </div>
			        				</div>
			        				<div class="col-lg-4 col-md-4 col-xs-4">
			        					<div class="form-group">
										    <label for="email">Relation With Patient</label>
										   	<input type="text" class="form-control">
										  </div>
			        				</div>
			        			</div>
			        		</div>
			        	</div>
			        </div>
			        <div class="col-lg-12 col-md-12 col-xs-12" style="padding-top: 15px;border-top: 1px dashed #ddd;">
			        				<div class="col-lg-2 col-md-2 col-xs-2" style="padding-left: 0px;">
			        					  <div class="form-group">
										    <label for="email">Expiry Date</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				<div class="col-lg-2 col-md-2 col-xs-2" style="padding-left: 0px;">
			        					  <div class="form-group">
										    <label for="email">Blood Condition</label>
										    <input type="text" class="form-control">
										  </div>
			        				</div>
			        				<div class="col-lg-2 col-md-2 col-xs-2" style="padding-left: 0px;">
			        					<div class="form-group">
										    <label for="email">Donor Type</label>
										    <select class="form-control" name="donor_state">
											    <option value="0">Select</option>
											    <option value="Yet to Donate">Yet to Donate</option>
											    <option value="Regular Donor" selected="selected">Regular Donor</option>
											    <option value="On Need Basis">On Need Basis</option>
											    <option value="Camp">Camp</option>
										    </select>
										  </div>
			        				</div>
			        				<div class="col-lg-2 col-md-2 col-xs-2" style="padding-left: 0px;">
			        					<div class="form-group">
										    <label for="email">Camp</label>
										    <select class="form-control" name="donor_state">
											    <option value="0">Select</option>
											    <option value="ABC">ABC</option>
										    </select>
										  </div>
			        				</div>
			        				<div class="col-lg-2 col-md-2 col-xs-2" style="padding-left: 0px;">
			        					  <div class="form-group">
										    <label for="email">Technician Name</label>
										    <select class="form-control" name="donor_state">
											    <option value="Select">Select</option>
											    <option value="Ruchita Ghugal">Ruchita Ghugal</option>
											    <option value="Abhinav Parmar">Abhinav Parmar</option>
										    </select>
										  </div>
			        				</div>
			        				<div class="col-lg-2 col-md-2 col-xs-2" style="padding-right: 0px;">
			        					  <div class="form-group">
										    <label for="email">Helping Staff Name</label>
										    <select class="form-control" name="donor_state">
											    <option value="Select">Select</option>
											    <option value="Ruchita Ghugal">Ruchita Ghugal</option>
											    <option value="Abhinav Parmar">Abhinav Parmar</option>
										    </select>
										  </div>
			        				</div>
			        			</div>
			        <div class="">
			        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
			        		<h5 class="maintite" style="margin-left: 15px;"><b>DONOR DETAIL</b></h5>
			        		<div class="col-lg-1 col-md-1 col-sm-2">
			        			<div class="form-group">
									<label for="email">Age</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        		<div class="col-lg-1 col-md-1 col-sm-2" style="padding-left: 0px;">
			        			<div class="form-group">
									<label for="email">Gender</label>
									<select class="form-control" name="donor_state">
											    <option value="Male">Male</option>
											    <option value="Female">Female</option>
										    </select>
								</div>
			        		</div>
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">Contact</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        		<div class="col-lg-4 col-md-4 col-sm-2">
			        			<div class="form-group">
									<label for="email">Email ID</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        		<div class="col-lg-4 col-md-4 col-sm-4">
			        			<div class="form-group">
									<label for="email">Address</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        	</div>
			        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: rgba(239, 239, 239, 0.5);padding-top: 10px;">
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">Blood Group</label>
									<select class="form-control" name="blood_group">
										    	<option value="0">Select</option>
										    	<option value="O+">O+</option>
										    	<option value="O-">O-</option>
										    	<option value="A+">A+</option>
										    	<option value="A-">A-</option>
										    	<option value="B+">B+</option>
										    	<option value="B-">B-</option>
										    	<option value="AB+">AB+</option>
										    	<option value="AB-">AB-</option>
										    </select>
								</div>
			        		</div>
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">No.Of Times</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">Last Date</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">Where</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        		<div class="col-lg-4 col-md-4 col-sm-4">
			        			<div class="form-group">
									<label for="email">Any illness in past 3yr</label>
									<select class="form-control" name="blood_group">
										    	<option value="Jaundice">Jaundice</option>
										    	<option value="Malaria">Malaria</option>
										    	<option value="Typhoid">Typhoid</option>
										    	<option value="TT vaccine">TT vaccine</option>
										    	<option value="Dog bite">Dog bite</option>
										    	<option value="chicken pox">Chicken Pox</option>
										    	<option value="Other">Other</option>
										    </select>
								</div>
			        		</div>
			        	</div>
			        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: rgba(239, 239, 239, 0.5);">
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">HIV Result</label>
									<select class="form-control" name="blood_group">
										    	<option value="Yes">Yes</option>
										    	<option value="No">No</option>
										    </select>
								</div>
			        		</div>
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">Donor Weight</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">B.P</label>
									<select class="form-control" name="blood_group">
										    	<option value="124/87">124/87</option>
										    </select>
								</div>
			        		</div>
			        		<div class="col-lg-2 col-md-2 col-sm-2">
			        			<div class="form-group">
									<label for="email">H.B</label>
									<select class="form-control" name="blood_group">
										    	<option value="13.2">13.2</option>
										    </select>
								</div>
			        		</div>
			        		<div class="col-lg-4 col-md-4 col-sm-4">
			        			<div class="form-group">
									<label for="email">Reason of Declining Donation</label>
									<input type="text" class="form-control">
								</div>
			        		</div>
			        		
			        	</div>
			        </div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary" style="margin-top: 15px;">Register</button>
			      </div>
			    </div>
			
			  </div>
			</div>
    

</body>
</html>