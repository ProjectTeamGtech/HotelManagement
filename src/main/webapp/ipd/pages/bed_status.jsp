<!DOCTYPE html>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<link rel="icon" href="assets/img/favicon.ico">
<title>Advance Practice Management</title>
<link
	href="_assets/js/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/cupertino/jquery-ui.min.css"
	rel="stylesheet" />
<!-- Bootstrap core CSS -->

<link href="_assets/js/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<!--external css-->
<link href="_assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link href="_assets/js/smartmenus-0.9.7/css/sm-clean/sm-clean.css"
	rel="stylesheet" />
<link href="_assets/js/smartmenus-0.9.7/css/sm-core-css.css"
	rel="stylesheet" />
<!-- Custom styles for this template -->

<link href="_assets/css/style-responsive.css" rel="stylesheet">

<!-- <link
	href="_assets/css/priscription/bootstrap-3.3.1-dist/dist/css/bootstrap.min.css"
	rel="stylesheet" /> -->

	
	
	<script type="text/javascript" src="ipd/js/bedstatus.js"></script>

<style>
.bedsearch {
	margin-left: -19px;
}

.topbane6 {
    margin-top: -42px !important;
}
.addbtn{
        margin-left: 0px;
    margin-bottom: 10px;
}
</style>
</head>

<body>

	<section>

		<section>

			<!-- page start-->
			<div class="">
<%
											LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
										%>

				<div class="">
					<div class="">
						<div class="row details">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

								<h4>Bed Status</h4>

							</div>
						</div>
						<div class="row ">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">

								<div class="">
								<%if(loginInfo.getSuperadminid()==1){ %>
									<div class="col-md-12 col-lg-12 topback2">
										
										<!-- <form class="form-inline">
										
											<div class="form-group">
											
												<input type="text" class="form-control botlnea"
													id="exampleInputName2" placeholder="Search..">

											</div>

										</form> -->
											<div class="col-lg-1 col-md-1">
											 
													<a href="Bed" class="btn btn-primary">Add Bed</a>
											
										</div>
									</div>
									 <% }%>
									<table class="table table-bordered" cellspacing="0"
										width="100%">
										<thead>
											<tr class="tableback">

												<th>Ward</th>
												<th>Section</th>
												<th>Bed No</th>
												<th>Bed Name</th>
												<th>Status</th>
												<th>Active/Inactive</th>

											</tr>
										</thead>


										<tbody>
											<s:iterator value="bedlist">
												<tr>

													<td><s:property value="wardname" /></td>
													<td><s:property value="sectionname" /></td>
													<td><s:property value="id" /></td>
													<td><s:property value="bedname" /></td>
													<td>
														<s:if test="status==1">
															Booked (<s:property value="clientname"/>)
														</s:if>
														<s:else>
															Available
														</s:else>
													</td>
													<td>
														
														<s:if test="status==0">
													
														<s:if test="bedstatus==0">
														<%-- 
														href="deleteBed?selectedid=<s:property value="id"/>"
														title="Delete"><i class="fa fa-trash-o"></i></a> --%>
															<a href="" onclick="changeBedtatus(<s:property value="id"/>,<s:property value="bedstatus"/>)"> <img src="common/img/inactive.png" style="width: 50px;"> </a>
														</s:if>
														<s:else>
															<a href="" onclick="changeBedtatus(<s:property value="id"/>,<s:property value="bedstatus"/>)"> <img src="common/img/active.png" style="width: 50px;"> </a>
														</s:else>
														
														</s:if>
														<s:else>
															Cant't Change
														</s:else>
														
													<%-- 	 <a
														href="editBed?selectedid=<s:property value="id"/>"
														title="Edit" /><i class="fa fa-pencil-square-o"></i> --%>
														
														</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>


								</div>

							</div>
						</div>
					</div>
				</div>


			</div>
			<!-- page end-->
		</section>
	</section>
	<!-- /MAIN CONTENT -->
	<!--main content end-->




	





</body>
</html>
