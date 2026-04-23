<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
	.mainheader {
    background-color: #339966 !important;
}
.ipdtrans{
	    background-color: #a94442;
    color: #fff;
        padding: 3px 11px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}

.ottrans{
	    background-color: #f0ad4e;
    color: #fff;
    padding: 3px 13px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}
.opdtrans{
  background-color: #8a6d3b;
    color: #fff;
       padding: 3px 5px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}
.shifetdca{
	color: #555 !important;
    background-color: #efefef;
    padding: 3px 5px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}
</style>

</head>
<body>
<section>

			<!-- page start-->
			
				
					
						<div class="">
							<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
									<img src="dashboardicon/casualty.png" class="img-responsive prescripiconcircle">
								</div>
								
								<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
								<h4>Casualty Dashboard </h4>
								</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<div class="form-inline search">
										  <div class="form-group">
										    <input type="text" name="searchText" value="" class="form-control" placeholder="Search ID">
										  </div>
										  <div class="form-group">
											<input type="text" name="searchText" value="" class="form-control" placeholder="from date">
										</div>
										<div class="form-group">
											<select class="form-control">
											  <option>Select</option>
											  <option>IPD</option>
											  <option>OT</option>
											  <option>OPD</option>
											</select>
										</div>
										<div class="form-group">
											<select class="form-control">
											  <option>Status</option>
											  <option>Not Yet</option>
											  <option>Discharge</option>
											  <option>Shifted</option>
											</select>
										</div>
										  
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="#" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" onclick="openPopup('newpatientDiaryMangent')" class="btn btn-primary pull-right" title="New Patient">New Patient</a>
										</div>

										</div>
										</div>
									
										<table class="table table-bordered" width="100%">
											<thead>
												<tr class="tableback">
													<th style="width: 3%;">Sr.No</th>
													<th style="width: 5%;">Yearly No</th>
													<th style="width: 12%;" class="pdate">Date | Time</th>
													<th style="width: 6%;">Reg.No</th>
													<th style="width: 16%;" class="pnamewidth">Patient Name</th>
													<th style="width: 7%;" class="page">Age / Gender</th>
													<th style="width: 10%;" class="pdate">Prov. Dignosis</th>
													<th style="width: 6%;">Belonging</th>
													<th style="width: 9%">Doctor Incharge</th>
													<th style="width: 9%;">MLC/Empannelled</th>
													<th style="width: 8%;">Refered By</th>
													<th style="width: 7%;">Discharge</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
											<tr>
												<td>1</td>
												<td>1701</td>
												<td>03/03/2017 | 11:42 PM</td>
												<td>030320171</td>
												<td>Mr.Praful ghagre, Nagpur</td>
												<td>26 / Male</td>
												<td>pt directly shift</td>
												<td><a class="ipdtrans" style=" color: #ffffff !important;" title="Shifted To IPD">IPD</a></td>
												<td>Dr.Sukhdeve</td>
												<td>No</td>
												<td>Dr.Ookalkar</td>
												<td><i class="fa fa-circle" aria-hidden="true" style="color: red;"></i> Not Yet</td>
												<td><a href="#" title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
												<td class="hidden"> &nbsp; | &nbsp; <a href="#" class="ottrans" style=" color: #ffffff !important;" title="Shifted To OT">OT</a> &nbsp; | &nbsp; <a href="#" class="opdtrans" style=" color: #ffffff !important;" title="Shifted To OPD">OPD</a></td>
											</tr>
											<tr>
												<td>2</td>
												<td>1702</td>
												<td>03/03/2017 | 11:42 PM</td>
												<td>030320171</td>
												<td>Mr.Praful ghagre, Nagpur</td>
												<td>26 / Male</td>
												<td>pt directly shift</td>
												<td><a href="#" class="ottrans" style=" color: #ffffff !important;" title="Shifted To OT">OT</a></td>
												<td>Dr.Sukhdeve</td>
												<td>Yes</td>
												<td>Dr.Ookalkar</td>
												<td><i class="fa fa-circle" aria-hidden="true" style="color: #6699cc;"></i> Shifted</td>
												<td><a href="#" title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
												<td class="hidden"><a href="#" class="opdtrans" style=" color: #ffffff !important;" title="Shifted To OPD">OPD</a></td>
											</tr>
											<tr>
												<td>3</td>
												<td>1703</td>
												<td>03/03/2017 | 11:42 PM</td>
												<td>030320171</td>
												<td>Mr.Praful ghagre, Nagpur</td>
												<td>26 / Male</td>
												<td>pt directly shift</td>
												<td><a href="#" class="opdtrans" style=" color: #ffffff !important;" title="Shifted To OPD">OPD</a></td>
												<td>Dr.Sukhdeve</td>
												<td>Not Required</td>
												<td>Dr.Ookalkar</td>
												<td><i class="fa fa-circle" aria-hidden="true" style="color: green;"></i> Discharge</td>
												<td><a href="#" title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
												<td class="hidden"> &nbsp; | &nbsp; <a href="#" class="ottrans" style=" color: #ffffff !important;" title="Shifted To OT">OT</a> &nbsp; | &nbsp; <a href="#" class="opdtrans" style=" color: #ffffff !important;" title="Shifted To OPD">OPD</a></td>
											</tr>
											</tbody>
										</table>
								</div>
							</div>
						</div>
					
 
			<!-- page end-->
		</section>
		
		<!-- Modal -->
<div id="addpatient" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add New Patient</h4>
      </div>
      <div class="modal-body">
       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
       <div class="col-lg-2 col-md-2 col-xs-12 col-sm-2">
       	<div class="form-group">
			    <label for="exampleInputEmail1">Patient ID</label>
			    <input type="text" class="form-control" id="exampleInputEmail1" value="030320172">
			  </div>
       </div>
       <div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
       		<div class="form-group">
			    <label for="exampleInputEmail1">Patient Name</label>
			    <input type="text" class="form-control" id="exampleInputEmail1">
			  </div>
       </div>
       <div class="col-lg-2 col-md-2 col-xs-12 col-sm-2">
       		<div class="form-group">
			    <label for="exampleInputEmail1">Age</label>
			    <input type="text" class="form-control" id="exampleInputEmail1">
			  </div>
       </div>
       <div class="col-lg-2 col-md-2 col-xs-12 col-sm-2">
       		<div class="form-group">
			    <label for="exampleInputEmail1">Gender</label>
			    	<select class="form-control">
						<option>Male</option>
						<option>Female</option>
					</select>
			  </div>
       </div>
        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
       		<div class="form-group">
			    <label for="exampleInputEmail1">Consultation Note</label>
			    <textarea class="form-control" rows="5"></textarea>
			  </div>
       </div>
			  
       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>

  </div>
</div>
</body>
</html>