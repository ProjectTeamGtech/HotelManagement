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
</style>
</head>
<body>
<section>
<div class="">
							<div class="row ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<a style="display: none;" href="#" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Blood
										Bank </a> 
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
                                                <th>Organized By</th>
                                                <th>Camp Description</th>
                                                <th>Location</th>
                                                <th>Camp Date</th>
                                                <th>Camp Time</th>
                                                <th>Employee Name</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <s:iterator value="blooddonorsList">
                                            <tr>
                                                <td>Ayush Blood Camp</td>
                                                <td>Camp organised by AKDC, for Small Children</td>
                                                <td>AKDC Hospital</td>
                                                <td>15/08/2016</td>
                                                <td>11:00 AM To 05:00 PM</td>
                                                <td>Kamal Nagrale</td>
                                                <td><a href="#" onclick=""><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;<a href="" onclick="" ><i class="fa fa-trash text-danger"></i></a></td>
                                                
                                            </tr>
                                            </s:iterator>
                                        </tbody>
                                    </table>
										</div>
										<div class="col-lg-4" style="padding-right:0px;">
											<h4>Camp Details</h4>
										</div>
								</div>
							</div>
						</div>

</section>
</body>
</html>