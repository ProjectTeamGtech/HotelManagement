<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
.maintite {
    border-bottom: 1px solid #ddd;
    line-height: 22px;
    color: brown;
}
</style>
</head>
<body>
<section>
<div class="">
							<div class="row ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form class="form-inline">
										
										   <div class="form-group">
										    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="Search Date">
										  </div>
										   <button type="submit" class="btn btn-primary" onclick="searchDonor()">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										</form>
										</div>
										</div>
										<div class="col-lg-8" style="padding-left:0px;border-right: 1px solid #efefef;min-height: 500px;">
											<table class="table my-table xlstable table-bordered" style="width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>Emp Code</th>
                                                <th>Emp Name</th>
                                                <th>Designation</th>
                                                <th style="width: 40%;">Location</th>
                                                <th>City</th>
                                                <th>Mobile No</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <s:iterator value="blooddonorsList">
                                            <tr>
                                                <td>01</td>
                                                <td>Abhinav Parmar</td>
                                                <td>Technician</td>
                                                <td>20,Pandey Layout, Khamla</td>
                                                <td>Nagpur</td>
                                                <td>+91-8465456521</td>
                                                <td><a href="#" onclick=""><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;<a href="" onclick="" ><i class="fa fa-trash text-danger"></i></a></td>
                                                
                                            </tr>
                                            </s:iterator>
                                        </tbody>
                                    </table>
										</div>
										
										
										<div class="col-lg-4">
											<h5 class="maintite"><b>EMPLOYEE DETAIL</b></h5>
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-2 col-md-2 col-sm-2" style="padding-left: 0px;">
												<div class="form-group">
													<lable>Code</lable>
													<input type="text" class="form-control">
												</div>
												</div>
												<div class="col-lg-6 col-md-6 col-sm-6" style="padding-right: 0px;">
												<div class="form-group">
													<lable>Employee Name</lable>
													<input type="text" class="form-control">
												</div>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4" style="padding-right: 0px;">
												<div class="form-group">
													<lable>Designation</lable>
													<select class="form-control" name="donor_state">
													    <option value="Technician">Technician</option>
													    <option value="Computer Oppretor">Computer Oppretor</option>
												    </select>
												</div>
												</div>
												
												
											</div>
											
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="form-group">
													<lable>Location</lable>
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-3 col-md-3 col-sm-3" style="padding-left: 0px;">
											<div class="form-group">
													<lable>Post Code</lable>
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4">
											<div class="form-group">
													<lable>City</lable>
													<select class="form-control" name="donor_state">
													    <option value="Nagpur">Nagpur</option>
													    <option value="Raipur">Raipur</option>
												    </select>
												</div>
											</div>
											<div class="col-lg-5 col-md-5 col-sm-5" style="padding-right: 0px;">
												<div class="form-group">
													<lable>Mobile No</lable>
													<input type="text" class="form-control">
												</div>
											</div>
											</div>
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-6 col-md-6 col-sm-6" style="padding-left: 0px;">
												<div class="form-group">
													<lable>Email</lable>
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-6" style="padding-right: 0px;">
												<div class="form-group">
													<lable>Website</lable>
													<input type="text" class="form-control">
												</div>
											</div>
											</div>
											
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;text-align: right;">
												<a href="#" class="btn btn-primary">Update</a>
											</div>
										</div>
								</div>
							</div>
						</div>
				<script src="bloodbank/js/combodate.js"></script> 
				
				<script>
					$(function(){
						$('#datetime12').combodate(); 
						$('#datetime123').combodate();  
					});
				</script>
						
						

</section>
</body>
</html>