<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">

<style>
	hr {
    margin-top: 8px;
    margin-bottom: 8px;
}
.imflag{
	display: inline-block;
    width: 88%;
}
.bg-lightred {
    background-color: #e05d6f !important;
}
</style>
</head>
<body>

<div class="">
							
								<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 15px;padding:0px;">
									
									<div class="col-lg-6 col-xs-6 col-sm-6 col-md-6" style="border-right: 1px solid #ddd;min-height: 500px;">
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
										<h4><b>Profile Setting</b> <small style="color: #f0ad4e;">(Header)</small></h4>
										
											<div class="col-lg-8 col-xs-8 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Blood Bank Name</label>
												    <input type="text" class="form-control">
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">L.NO</label>
												    <input type="text" class="form-control">
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12 hidden">
												<div class="form-group">
												    <label for="exampleInputEmail1">TIN NO</label>
												   <input type="text" class="form-control">
												  </div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-10 col-xs-10 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Address</label>
												    <input type="text" class="form-control">
												  </div>
											</div>
											<div class="col-lg-2 col-xs-2 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">City</label>
												   <input type="text" class="form-control">
												  </div>
											</div>
										</div>
										
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-4 col-xs-4 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Website</label>
												    <input type="text" class="form-control">
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Email</label>
												    <input type="text" class="form-control">
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Contact</label>
												   <input type="text" class="form-control">
												  </div>
											</div>
										</div>
										
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
										<h4><small style="color: #f0ad4e;">(Footer - Instruction)</small></h4>
										
											<div class="col-lg-6 col-xs-6 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Instruction No 1</label>
												     <input type="text" class="form-control">
												  </div>
											</div>
											<div class="col-lg-6 col-xs-6 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Instruction No 2</label>
												     <input type="text" class="form-control">
												  </div>
											</div>
											
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-6 col-xs-6 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Instruction No 3</label>
												    <input type="text" class="form-control">
												  </div>
											</div>
											<div class="col-lg-6 col-xs-6 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Instruction No 4</label>
												   <input type="text" class="form-control">
												  </div>
											</div>
											
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-12 col-xs-12 col-xs-12 text-right" style="padding-left: 0px;">
												<button type="submit" class="btn btn-primary">Update</button>
											</div>
										</div>
									</div>
								 
								
								
									<div class="col-lg-6 col-xs-6 col-sm-6 col-md-6" style="padding: 0px;">
										<div class="col-lg-12 col-md-12 col-xs-12">
										<h4><b>User Setting</b><div class="pull-right"><a href="#" onclick="addNewuser('mytable')" >Add User</a></div></h4>
											<table class="table my-table xlstable" id="mytable" style="width: 100%;">
									<thead>
										<tr>
											<th style="width: 3%;">Sr.</th>
											<th style="width: 20%;">User Name</th>
											<th style="width: 15%;">Contact No</th>
											<th style="width: 15%;">User_ID</th>
											<th style="width: 14%;">Password</th>
											<th style="width: 2%;">Status</th>
											<th>Access</th>
											<th></th>
										</tr>
									</thead>
								 <tbody>
								
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td><a href="#" data-toggle="modal" data-target="#accessset">Setting</a></td>
											<td class="text-center"><a href="#"><i class="fa fa-trash-o" style="color:red;"></i></a></td>
										</tr>
											
									</tbody>
								</table>
											
										</div>
									</div>
								</div>
								
								
								
							<!-- Setting Access -->
							<div id="accessset" class="modal fade" role="dialog">
							  <div class="modal-dialog modal-sm">
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title">Access Setting</h4>
							      </div>
							      <div class="modal-body">
							      <div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
							      		<ul class="settings" style="padding: 0px;list-style: none;">
							      		
                                    <li>
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Create Sale Bill</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline2" checked="">
                                                    <label class="onoffswitch-label" for="show-offline2">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Give Discount</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-fullname3">
                                                    <label class="onoffswitch-label" for="show-fullname3">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                    <li class="">
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Catelogue Update</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-history4" checked="">
                                                    <label class="onoffswitch-label" for="show-history4">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Profit & Loss Account</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-location5" checked="">
                                                    <label class="onoffswitch-label" for="show-location5">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                    <li class="hidden">
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Create Purchase Order</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-notifications">
                                                    <label class="onoffswitch-label" for="show-notifications">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                                
                                
							      </div>
							      </div>
							      <div class="modal-footer">
							      	<button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
							      </div>
							    </div>
							
							  </div>
							</div>



</body>
</html>