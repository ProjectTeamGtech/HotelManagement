<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
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
<script type="text/javascript">

 var ipdid=0;
	var clientid=0;
	
</script>

<script type="text/javascript" src="bloodbank/js/bloodbank.js"></script>
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
										    <label class="sr-only" for="exampleInputEmail3">Email address</label>
										    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="Search Donor / Patient">
										  </div>
										  <div class="form-group">
										    <label class="sr-only" for="exampleInputPassword3">Password</label>
										    <select class="form-control" name="blood_group">
		                                        <option value="">Select Blood Group</option>
		                                        <option value="O+">O+ </option>
		                                        <option value="O-">O- </option>
		                                        <option value="A+">A+ </option>
		                                        <option value="A-">A- </option>
		                                        <option value="B+">B+ </option>
		                                        <option value="B-">B- </option>
		                                        <option value="AB+">AB+ </option>
		                                        <option value="AB-">AB- </option>
		                                    </select>
										  </div>
										   <div class="form-group">
										    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="Search Date">
										  </div>
										  
										   <button type="submit" class="btn btn-primary" onclick="searchDonor()">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" type="button" onclick="donatePatient('donatepatient')" class="btn btn-primary"><i class="fa fa-plus-circle"></i> Donate Patient</a>
										</form>
										
										</div>
										</div>
										<div class="col-lg-7" style="padding-left:0px;">
											<table class="table my-table xlstable table-bordered" id="donatepatient" style="width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>Donor Name</th>
                                                <th>Blood group</th>
                                                <th>Patient Name</th>
                                                <th>Ward / Bed no</th>
                                                <th class="hidden">Qty</th>
                                                <th>Donation Date / Time</th>
                                                <th>Action</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <s:iterator value="donorPatientList">
                                            <tr id="<s:property value="id"/>">
                                                <td><s:property value="name"/></td>
                                                <td><s:property value="blood_group"/></td>
                                                <td><s:property value="clientname"/></td>
                                                <td><s:property value="ward"/></td><!--
                                                <td>300 ml</td>
                                                --><td><s:property value="lastmodified"/></td>
                                                <td><a href="#" onclick="editDonorPatient(<s:property value="id"/>)"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;<a href="" onclick="" ><i class="fa fa-trash text-danger"></i></a></td>
                                                
                                            </tr>
                                            </s:iterator>
                                        </tbody>
                                    </table>
										</div>
								</div>
							</div>
						</div>

</section>
</body>
</html>