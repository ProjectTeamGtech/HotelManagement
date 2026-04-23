<%@taglib uri="/struts-tags" prefix="s" %>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="Logo">Logo</a></li>
			<li class="active">Add Logo</li>
		</ol>
	</div>
</div>


<s:form action="saveLogo" method="post" enctype="multipart/form-data" >
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
	
		<div class="panel panel-primary">
			<div class="panel-body">
		
			<label>File Upload</label>
			<s:file name="userImage" id="userImage" label="User Image" />
			</div>
		</div>			
	</div>

		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit/>			
		<div class="col-lg-3 col-md-2"></div>
	</div>
			
</div>
	
	
</s:form>