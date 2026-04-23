<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.pnew{
    width: 3%;

}
.pview{
    width: 4%;

}
.page{
    width: 7%;
}
.mainheader {
    background-color: #43b9be !important;
}.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}

</style>
</head>
<body>
<section>
			<!-- page start-->
						<div class="">
							<div class="row ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<a style="display: none;" href="#" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Blood
										Bank </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<div class="form-inline search" name="searchform">
										
										  <div class="form-group">
										    <s:textfield name="searchText" id="searchText" cssClass="form-control" placeholder="Donor Name Search.."/>
										  </div>
										  <div class="form-group">
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
										    <select class="form-control" name="blood_group">
		                                        <option value="">Select</option>
		                                        <option value="Yet to donate">Yet to donate </option>
		                                        <option value="Regular Donor">Regular donor </option>
		                                        <option value="On need Basis">On need basis </option>
		                                        <option value="On need Basis">Camp </option>
		                                    </select>
										  </div>
										  <div class="form-group">
										    <select class="form-control" name="blood_group">
		                                        <option value="">Select Days</option>
		                                        <option value="Above 180 Days">Above 180 Days </option>
		                                        <option value="Below 180 Days">Below 180 Days </option>
		                                    </select>
										  </div>
										  
										 
										  <button type="submit" class="btn btn-primary" onclick="searchDonor()">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" type="button" class="btn btn-primary hidden" onclick="adddonorRow('donortable')"><i class="fa fa-plus-circle"></i> Add Blood Donor</a>
										</div>
										</div>
										</div>
										<div class="" style="">
                                    <table class="table my-table xlstable table-bordered" style="width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>Reg.No</th>
                                                <th>Reg.Date</th>
                                                <th>Donor Type</th>
                                                <th>Donor Name</th>
                                                <th>Contact</th>
                                                <th>Blood Group</th>
                                                <th>City</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                          
                                        </tbody>
                                    </table>
										</div>
								</div>
							</div>
						</div>
			<!-- page end-->
		</section>

</body>
</html>