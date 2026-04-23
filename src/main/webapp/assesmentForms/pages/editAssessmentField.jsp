<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/assessment.js"></script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Edit Assessment Field</h4>
									</div>
								</div>
								<br>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

									<s:form action="updateAssesmentMasterForms" theme="simple" method="post" enctype="multipart/form-data"> 
<div class="">
<%-- <span style="color:red">Note : Assessment Field Name do not allow any special character</span><br> --%>	
			<div class="">	
	<div class="col-lg-12">
			<div class="row">					
				<div class="col-lg-2"></div>
				<div class="col-lg-2">
					<label>Assessment Field Name :</label>
				</div>
				<div class="col-lg-5">
					<s:textfield  name="filedname" maxlength="50" placeholder="Enter field name only 50 character"  id="filedname" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<br/><br/>
		<s:hidden name = "id" ></s:hidden>	
		<div class="row">		
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<s:submit value="Update" cssClass="btn btn-primary" onclick="return editValidate();"></s:submit>
			</div>
			<div class="col-lg-2"></div>
		</div>
		<br/><br/>
			
			</div>
		</div>
	</div>
			</s:form>
											

											
										</div>
									</div>
								</div>
							</div>
						</div>



