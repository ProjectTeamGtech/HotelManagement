<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<script type="text/javascript" src="common/js/pagination.js"></script>

<style>
	.mainheader {
    background-color: #339966 !important;
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
							<div class="row ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<div class="form-inline search">
										  <div class="form-group">
										    <input type="text" name="searchText" value="" class="form-control" placeholder="Search ID">
										  </div>
										   <div class="form-group" style="width:7%;">
										    <input type="text" name="searchText" value="" class="form-control" placeholder="Date" style="width:100%;">
										  </div>
										<%-- <div class="form-group">
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
										</div> --%>
										  
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="#" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="inputCasualty"  class="btn btn-primary pull-right" title="New Patient">New Patient</a>
										</div>

										</div>
										</div>
									
										<table class="table table-bordered text-uppercase" width="100%">
											<thead>
												<tr class="tableback">
													<!-- <th style="width: 3%;">Sr.No</th> -->
													<!--<th style="width: 5%;">Yearly No</th>
													-->
													
													<th style="width: 2%;">ID</th>
													<th style="width: 12%;" class="pdate">Admission Date | Time</th>
													<th style="width: 20%;" class="pnamewidth">Patient Details</th>
													<th style="width: 24%;" class="pdate">Prov. Dignosis</th>
													<th style="width: 12%;">Admission Type</th>
													<th style="width: 13%">Doctor Incharge</th>
													<th style="width: 5%;">MLC</th>
													<!--<th style="width: 8%;">Refered By</th>
													--><th style="width:10%;">Discharge</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
											<%int cnt = 1; %>
											<s:iterator value="casualtyList">
											<tr>
												<%-- <td><%=cnt %></td> --%>
												<!--<td></td>
												-->
												<td><s:property value="id"/></td>
												<td><s:property value="date"/></td>
												<td><s:property value="fname"/> | <s:property value="age"/> | <s:property value="gender"/></td>
												<td><s:property value="provdiag"/></td>
												<td><s:property value="recbelonging"/></td>
												<td><s:property value="drincharge"/></td>
												<td><s:property value="mlc"/></td>
												<!--<td><s:property value="refby"/></td>
												--><td><s:property value="typeofdis"/></td>
												<td><a href="editCasualty?id=<s:property value="id"/>" title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
												<td class="hidden"> &nbsp; | &nbsp; <a href="#" class="ottrans" style=" color: #ffffff !important;" title="Shifted To OT">OT</a> &nbsp; | &nbsp; <a href="#" class="opdtrans" style=" color: #ffffff !important;" title="Shifted To OPD">OPD</a></td>
											</tr>
											<%cnt++; %>
											</s:iterator>
											</tbody>
										</table>
										
										<s:form action="Casualty" name="paginationForm" id="paginationForm" theme="simple">
										
											<div class="col-lg-12 col-md-12" style="padding:0px;">
												<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
													Total:<label class="text-info"><s:property value="totalRecords" /></label>
												</div>
												<%@ include file="/common/pages/pagination.jsp"%>
											</div>
										
										</s:form>
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