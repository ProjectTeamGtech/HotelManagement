<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="registration/js/clinic.js"></script>
<script type="text/javascript" src="registration/js/userprofile.js"></script>
<link rel="stylesheet" href="_assets/newtheme/css/main.css">

<link rel="stylesheet" href="mis/kpiplugin/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<style>
		.alert.alert-callout {
		    position: relative;
		    padding-left: 20px;
		    background: #ffffff;
		    color: #313534;
		    border-radius: 0px;
		    border-color: #5cb85c;
		    text-align: left;
		    margin-bottom: 0px;
		}
		.opacity-50 {
		    opacity: 0.5;
		    filter: alpha(opacity=50);
		}
		.text-xl {
		    font-size: 180%;
		}
		
		nav li {
		    border-bottom: 1px solid rgba(221, 221, 221, 0.33);
		    line-height: 27px;
		}
		#sidebar .settings .onoffswitch {
		     right: 0px;
		}
		.imgseth{
			width:10%;
		}
		h1, .h1 {
		    font-size: 15px !important;
		    font-weight: bold !important;
		    color: chocolate;
		    border-bottom: 1px solid;
		    line-height: 27px;
		}
		.onoffswitch-inner:after {
		    content: "";
		    padding-right: 9px;
		    background-color: rgb(169, 169, 169);
		    color: #999999;
		    text-align: right;
		}
	</style>

</head>
<body>
<div class="">
	<div class="">
		<div class=" ">
			<!-- <div class="col-lg-1 col-md-1"></div> -->
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 details">
				<h4>Access Setting </h4>
			</div>
			<!-- <div class="col-lg-1 col-md-1"></div> -->
		</div>
	<div class="row ">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<div>
<div class="col-lg-12 col-md-12 topback2">
	<div class="form-inline">
		<div class="form-group">
			 <s:select name="jobgroup" id="jobgroup" list="jobGroupList" listKey="id" listValue="name" title="Select Role Group"
					    headerKey="0" headerValue="Select Role Group" onchange="setJobTitleAjax(this.value)"
					    cssClass="form-control showToolTip chosen-select" data-toggle="tooltip">
			</s:select> 
		</div>
		<div class="form-group" id="jobtitlediv">
			<select name="jobtitle" id="jobtitle" class="form-control showToolTip chosen-select">
				<option value="0">Select</option>
			</select>
		<%-- 	<s:select list="jobtitlelist"
		cssClass="form-control showToolTip chosen-select" name="jobtitle" id="jobtitle" listKey="name" listValue="name" headerKey="0" headerValue="Select Job Title"></s:select> --%>
		</div>
		<div class="form-group">
			<s:select list="accessmodulelist" 
		cssClass="form-control showToolTip chosen-select" name="modulename" id="modulename"
		listKey="id" listValue="name" headerKey="0" headerValue="Select Module Name"></s:select>
		</div>
		
		<div class="form-group">
			<a class="btn btn-primary" href="#" onclick="setAllRoleAccess()">Go</a>
		</div>
		
		<div class="form-group">
			<form id="live-search" action="" class="styled" method="post">
				<input type="text" placeholder="Search" id="filter" value="" class="form-control" style="width: 100%;text-transform: uppercase;"/>
			</form>
		</div>
	</div>
</div>

<div class="row">
	<form action="updateRoleAccessClinicRegistration">
		<div class="col-lg-12 ">
		<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9" style="padding-left:0px;">
			<div class="setheight">
				<table id="results" class="table table-hover table-bordered table-striped table-condensed">
					<thead>
						<tr>
							<th style="width: 78%;">Roles Title</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody id="tbodydiv">
						<tr>
						
						</tr>
					</tbody>
					<input type="hidden" name="moduleid" id="moduleid">
					<input type="hidden" name="roleid" id="roleid">
				</table>
			</div>
		</div>
			
		</div>
		<div class="col-lg-12 col-md-12" style="margin-top: 15px;">
			<input type="submit" name="update" class="btn btn-primary" id="update" value="update">
		</div>
	</form>
	
	
	<!-- prototype created by abhi for access setting -->
	<div class="col-lg-1 col-md-1"></div>
	<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10 hidden" style="padding: 0px;">
			<div id="wrapper">
<div id="main">
    <div class=" clearfix">
        <div id="sidebar">
            <div id="nav-anchor"></div>
            <nav>
                <ul>
                    <li>
                    	<a href="#access1">1. OPD</a> 
                    		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline1">
                                                    <label class="onoffswitch-label" for="show-offline1">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access2">2. IPD</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline2">
                                                    <label class="onoffswitch-label" for="show-offline2">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access3">3. OT</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline3">
                                                    <label class="onoffswitch-label" for="show-offline3">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access4">4. Casualty</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline4">
                                                    <label class="onoffswitch-label" for="show-offline4">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access5">5. EMR</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline5">
                                                    <label class="onoffswitch-label" for="show-offline5">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access6">6. PAC's</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline6">
                                                    <label class="onoffswitch-label" for="show-offline6">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access7">7. Discharge</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline7">
                                                    <label class="onoffswitch-label" for="show-offline7">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access8">8. Pharmacy</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline8">
                                                    <label class="onoffswitch-label" for="show-offline8">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access9">9. Indent</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline9">
                                                    <label class="onoffswitch-label" for="show-offline9">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access10">10. Investigation</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline10">
                                                    <label class="onoffswitch-label" for="show-offline10">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access11">11. Blood Bank</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline11">
                                                    <label class="onoffswitch-label" for="show-offline11">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access12">12. Billing</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline12">
                                                    <label class="onoffswitch-label" for="show-offline12">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access13">13. MRD</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline13">
                                                    <label class="onoffswitch-label" for="show-offline13">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access14">14. Payroll</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline14">
                                                    <label class="onoffswitch-label" for="show-offline14">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access15">15. Expenses</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline15">
                                                    <label class="onoffswitch-label" for="show-offline15">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access16">16. Inventory</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline16">
                                                    <label class="onoffswitch-label" for="show-offline16">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access17">17. MIS</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline17">
                                                    <label class="onoffswitch-label" for="show-offline17">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                    	<a href="#access18">18. Quality</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline18">
                                                    <label class="onoffswitch-label" for="show-offline18">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                    <li>
                   		<a href="#access19">19. Housekeeping</a>
                   		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline19">
                                                    <label class="onoffswitch-label" for="show-offline19">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                   	</li>
                    <li>
                   		<a href="#access20">20. Dietary</a>
                   		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline20">
                                                    <label class="onoffswitch-label" for="show-offline20">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                   	</li>
                    <li>
                    	<a href="#access21">21. Marketing</a>
                    	<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline21">
                                                    <label class="onoffswitch-label" for="show-offline21">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                    </li>
                </ul>
            </nav>
        </div><!-- /sidebar -->
        
        
        <div id="content">
        
            <section id="access1">
                <h1>OPD</h1>
                <div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding:0px;border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
                	<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;">
                		<h4>Appointment Booking Access</h4>
                	</div>
                	<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;">
                		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline22">
                                                    <label class="onoffswitch-label" for="show-offline22">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                	</div>
                </div>
                <div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding:0px;border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
                	<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;">
                		<h4>Appointment Modify Access</h4>
                	</div>
                	<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;">
                		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline23">
                                                    <label class="onoffswitch-label" for="show-offline23">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                	</div>
                </div>
                <div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding:0px;border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
                	<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;">
                		<h4>Appointment Cancel Access</h4>
                	</div>
                	<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;">
                		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline24">
                                                    <label class="onoffswitch-label" for="show-offline24">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                	</div>
                </div>
                <div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding:0px;border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
                	<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;">
                		<h4>Request Prescription Access</h4>
                	</div>
                	<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;">
                		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline25">
                                                    <label class="onoffswitch-label" for="show-offline25">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                	</div>
                </div>
                <div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding:0px;border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
                	<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;">
                		<h4>Request Investigation Access</h4>
                	</div>
                	<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;">
                		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline26">
                                                    <label class="onoffswitch-label" for="show-offline26">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                	</div>
                </div>
                <div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding:0px;border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
                	<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;">
                		<h4>View Account Access</h4>
                	</div>
                	<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;">
                		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline26">
                                                    <label class="onoffswitch-label" for="show-offline26">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                	</div>
                </div>
                <div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding:0px;border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
                	<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;">
                		<h4>Modify Diagnosis Access</h4>
                	</div>
                	<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;">
                		<ul class="settings" style="list-style: none;margin-top: 7px;float: right;">
								<li style="border: none;">
                                        <div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline26">
                                                    <label class="onoffswitch-label" for="show-offline26">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
                	</div>
                </div>
            </section>
        
            <section id="access2">
               
            </section>
        
        	<section id="access3">
             
            </section>
            
            <section id="access4">
               
            </section>
            
            <section id="access5">
                
            </section>
            
        </div><!-- /#content -->
        
    </div>
</div>





</div>
		</div>
	
	<div class="col-lg-1 col-md-1"></div>
	
	
	</div>




						

											
										</div>
									</div>
								</div> 
							</div>
						</div>
						
						<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
	 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
  
  <script>
            $(window).load(function(){

                $('#select-all').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select input').prop('checked', false);
                    }
                });
            });
        </script>	
        
         <script>
         //Code By Abhi
				             $(function() {
								  $('.setheight').slimScroll({
								   		height : '220px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>		
             
             <script src="mis/kpiplugin/js/jquery.scrollto.js"></script>
<script>
$(document).ready(function(){
    
    /** 
     * This part does the "fixed navigation after scroll" functionality
     * We use the jQuery function scroll() to recalculate our variables as the 
     * page is scrolled/
     */
    $(window).scroll(function(){
        var window_top = $(window).scrollTop() + 12; // the "12" should equal the margin-top value for nav.stick
        var div_top = $('#nav-anchor').offset().top;
            if (window_top > div_top) {
                $('nav').addClass('stick');
            } else {
                $('nav').removeClass('stick');
            }
    });
    
    
    /**
     * This part causes smooth scrolling using scrollto.js
     * We target all a tags inside the nav, and apply the scrollto.js to it.
     */
    $("nav a").click(function(evn){
        evn.preventDefault();
        $('html,body').scrollTo(this.hash, this.hash); 
    });
    
    
    
    /**
     * This part handles the highlighting functionality.
     * We use the scroll functionality again, some array creation and 
     * manipulation, class adding and class removing, and conditional testing
     */
    var aChildren = $("nav li").children(); // find the a children of the list items
    var aArray = []; // create the empty aArray
    for (var i=0; i < aChildren.length; i++) {    
        var aChild = aChildren[i];
        var ahref = $(aChild).attr('href');
        aArray.push(ahref);
    } // this for loop fills the aArray with attribute href values
    
    $(window).scroll(function(){
        var windowPos = $(window).scrollTop(); // get the offset of the window from the top of page
        var windowHeight = $(window).height(); // get the height of the window
        var docHeight = $(document).height();
        
        for (var i=0; i < aArray.length; i++) {
            var theID = aArray[i];
            var divPos = $(theID).offset().top; // get the offset of the div from the top of page
            var divHeight = $(theID).height(); // get the height of the div in question
            if (windowPos >= divPos && windowPos < (divPos + divHeight)) {
                $("a[href='" + theID + "']").addClass("nav-active");
            } else {
                $("a[href='" + theID + "']").removeClass("nav-active");
            }
        }
        
        if(windowPos + windowHeight == docHeight) {
            if (!$("nav li:last-child a").hasClass("nav-active")) {
                var navActiveCurrent = $(".nav-active").attr("href");
                $("a[href='" + navActiveCurrent + "']").removeClass("nav-active");
                $("nav li:last-child a").addClass("nav-active");
            }
        }
    });
});

</script>
             		
</body>
</html>