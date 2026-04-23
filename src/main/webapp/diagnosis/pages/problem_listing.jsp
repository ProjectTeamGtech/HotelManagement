<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<script type="text/javascript" src="diagnosis/js/problemlisting.js"></script>
<script type="text/javascript" src="ipd/js/addcharge.js"></script>
<script type="text/javascript">
window.onload=function(){
	showreport()
};
</script>
<style>
.bedsearch {
	margin-left: -19px;
}
.resub{
float: right;
    margin-right: 40px;
    margin-top: 20px;
}
.caktitle{
    background-color: #eeeeee;
}
.infoicon{
             	font-size: 19px !important;
    			line-height: 33px !important;
         }
</style>
</head>
<body>
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="col-lg-8 col-md-8 col-sm-8">
											<h4>Problem Listing and Care Plan</h4>
										</div>
										<div class="col-lg-4 col-md-4 col-sm-4">
										<div class="text-right">
										<a href="#" style="color:#fff;"><i class="fa fa-info-circle infoicon" aria-hidden="true"></i></a>
										</div>
										</div>
									</div>
								</div>
								<div class="row ">
									<div class="">
										<div>
														<div >
				<div class="col-lg-12 col-md-12">
					<div class="col-lg-6 col-md-6" style="padding-left: 0px;">
						<div class="panel panel-primary topbane6">
							<div class="panel-body">
								<div class="row ">
								<div id="inner-content-div">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
										<div class="">
											<div class="some-content-related-div">
											
											<table class="table table-bordered" cellspacing="0"
												width="100%">
												<thead>
													<tr class="tableback">
														<th><!-- <input type="checkbox" class="case" 
															onclick="selectAll()" /> --></th>
														<th>Diagnosis (Step 1 Choose Diagnosis)</th>
													</tr>
												</thead>
												<tbody>
													</tr>
													<s:iterator value="diagnosislist">
													<%-- 	<tr>
															<td><input type="checkbox" class="case"
																id="ch<s:property value="id"/>"
																name="ch<s:property value="id"/>"
																value="<s:property value="id"/>"
																onclick="showreport(this.value)" /></td>
															<td><s:property value="name" /></td>
														</tr> --%>
														 <tr>
              <s:if test="autodiagnosis==1"><td><input type="checkbox" checked="checked" class="case"
                id="ch<s:property value="id"/>"
                name="ch<s:property value="id"/>"
                value="<s:property value="id"/>"
                onclick="showreport(this.value)" /></td></s:if>
              <s:else><td><input type="checkbox" class="case"
                id="ch<s:property value="id"/>"
                name="ch<s:property value="id"/>"
                value="<s:property value="id"/>"
                onclick="showreport(this.value)" /></td></s:else>
               
               <td><s:property value="name" /></td>
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

						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">

							<%
								String ipdid = (String) session.getAttribute("ipdid");
								String clientid = (String) session.getAttribute("clientid");
								String practitionerid = (String) session.getAttribute("practitionerid");
								String condition = (String) session.getAttribute("condition");
							%>
							<s:form action="getPatientRecordEmr" id="consultform">

								<input type="hidden" name="clientname" value="<%=clientid%>" />
								<input type="hidden" name="diaryUser" id="diaryUser" />
								<input type="hidden" name="condition" id="condition" />

							</s:form>

							
						</div>
					</div>
					<div class="col-lg-6 col-md-6" style="padding-right: 0px;">
						<!--Second Table  -->
					<div class="panel panel-primary topbane6">
							<div class="panel-body">
								<div class="row">
								<div class="some-content-related-div">
											<div id="inner-content-div2">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose" id="gohere">
							</div>
							</div>
							</div>
								</div>
						
							</div>
							</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12">
					<div id="inner-content-div3">
					<div id="divspace">
					
					</div>
					</div>
					</div>
					</div>
					
				</div>
				<br>
			<div class="col-lg-12 col-md-12" style="margin-top: 20px;">
			<div class="col-lg-12 col-md-12" style="text-align: right;">
				<a class="btn btn-primary" onclick="submitData()">Insert into EMR</a>&nbsp;&nbsp;&nbsp;
				<a class="btn btn-primary" onclick="refreshData()"><i class="fa fa-refresh"></i></a>
			</div>
			</div>
			</div>
										
											

											
										</div>
									</div>
								</div>
							</div>
						</div>

	
		
	<!-- /MAIN CONTENT -->
	<!--main content end-->
	<script type="text/javascript" src="common/slimScroll/jquery.slimscroll.min.js"></script>
	<script>
	$(function(){
	    $('#inner-content-div').slimScroll({
	        height: '300px',
	        railVisible: true,
			alwaysVisible: true
	    });
	    $('#inner-content-div2').slimScroll({
	         height: '300px',
	         railVisible: true,
			 alwaysVisible: true
	    });
	    $('#inner-content-div3').slimScroll({
	         height: '230px',
	         railVisible: true,
			 alwaysVisible: true
	    });
	});
	</script>
	
	
</body>
</html>
