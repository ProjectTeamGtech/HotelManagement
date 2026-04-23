<!DOCTYPE html>
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
<link href="_assets/css/style.css" rel="stylesheet">
<link href="_assets/css/style-responsive.css" rel="stylesheet">

<link
	href="_assets/css/priscription/bootstrap-3.3.1-dist/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="_assets/css/priscription/Notification.css"
	rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" />
	
 <script type="text/javascript" src="common/js/pagination.js"></script>	

<style>
.bedsearch {
	margin-left: -19px;
}
</style>
</head>

<body>

	<section>

		<section class="wrapperfixed">

			<!-- page start-->
			<div class="row mt">


				<div class="panel panel-primary topbane6">
					<div class="panel-body">
						<div class="row details">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

								<h4>Diagnosis List</h4>

							</div>
						</div>
						<div class="row ">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">

								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="row martop10">
										
										<!-- <form class="form-inline">
										
											<div class="form-group">
											
												<input type="text" class="form-control botlnea"
													id="exampleInputName2" placeholder="Search..">

											</div>

										</form> -->
											<div class="col-lg-1 col-md-1 ">
										</div>
									</div>
									<table class="table table-bordered" cellspacing="0"
										width="100%">
										<thead>
											<tr class="tableback">

												<th>Diagnosis Name</th>
												<th>Problem</th>
												<th>Intervention</th>
											</tr>
										</thead>


										<tbody>
											<s:iterator value="diagnosislist">
												<tr>

													<td><s:property value="name" /></td>
													<td><s:property value="problem_name" /></td>
													<td><s:property value="intervention" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>


              <s:form action="listdiagnosisDiagnosis" name="paginationForm" id="paginationForm" theme="simple">
				<div class="col-lg-12 col-md-12" style="padding:0px;margin-top:15px">
					<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
						Total:<label class="text-info"><s:property value="totalRecords" /></label>
					</div>
					<%@ include file="/common/pages/pagination.jsp"%>
				</div>
			</s:form>
			</div>
			<!-- page end-->
		</section>
	</section>
	<!-- /MAIN CONTENT -->
	<!--main content end-->
	
	

</body>
</html>
