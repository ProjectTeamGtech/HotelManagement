<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Standard Charge</h4>
								</div>
							</div>
							
							
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="updateStandardcharges" id="standardChargeForm" theme="simple">
<div class="row">
	<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "investigationnameTable" width="100%" >
								<thead>
									<tr>
										<th align="center">Ward</th>
										<th align="center">Pay By</th>
										<th align="center">Charge Name</th>			
										<th align="center">Amount</th>			
																
									</tr>
									 <s:hidden name="id"/> 
								</thead>
								<tbody> 
									<tr>
									   
										<td><s:select theme="simple" list="wardList" name="wardid" headerValue="Select Ward" listKey="id" listValue="wardname" cssClass="form-control" />
										</td>
										<td><s:select theme="simple" list="paybyList" name="payby" headerValue="Select Payby" cssClass="form-control" />
										</td>								
										<td><s:textfield name="name" placeholder="enter charge name" id="name" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><s:textfield name="charge" placeholder="enter amount" id="charge" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary"  value="Update" />
			<s:reset cssClass="btn btn-primary" />
			<a href="Standardcharges" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
		</div>
 	</div>
 </div>
 
</s:form>

