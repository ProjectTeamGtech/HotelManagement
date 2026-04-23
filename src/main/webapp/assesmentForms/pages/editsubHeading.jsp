<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/assessment.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="AssesmentMasterForms">All Assessment Field List</a></li>
			<li class="active">Edit Assessment Sub Heading </li>
		</ol>
	</div>
</div>


<s:form action="updateSubHeadingAssesmentMasterForms" theme="simple" method="post" enctype="multipart/form-data"> 
<div class="panel panel-primary">
			<div class="panel-body">	
	<div class="col-lg-12">
			<div class="row">					
				<div class="col-lg-2"></div>
				<div class="col-lg-2">
					<label>Assessment SubHeading Name :</label>
				</div>
				<div class="col-lg-5">
					<s:textfield name="headingName" id="headingName" cssClass="form-control showToolTip headingName" data-toggle="tooltip"/>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<br/><br/>
		<s:hidden name = "id" ></s:hidden>	
		<div class="row">		
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<s:submit value="Update" cssClass="btn btn-primary" onclick="return editValidateSH();"></s:submit>
			</div>
			<div class="col-lg-2"></div>
		</div>
		<br/><br/>
			
			</div>
		</div>
	</div>
			</s:form>