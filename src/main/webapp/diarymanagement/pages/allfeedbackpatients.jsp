<%@taglib uri="/struts-tags" prefix="s"%>
<div class="col-lg-12 col-md-12" style="background-color: rgba(239, 239, 239, 0.6);padding: 12px 0px 10px 0px;text-transform: uppercase;margin-top: -5px;border: 2px solid #ddd;">
	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
		<div  class="input-group widthsixzero">
			<input title="Press Enter key to Search"  type="text" name="searchText" id="searchText1"
			placeholder="Search by first,last name,mob no" onchange="searchPatient()" class="form-control" style="margin-top: -1px;" autofocus="autofocus">
			 <%-- <span class="input-group-btn">
			<button class="btn btn-primary" type="button" onclick="searchPatient()">Go</button>
		</span>   --%>
		</div>
		<!-- /input-group -->
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12" style="text-align: right;">
	
		
	</div>
<div class="col-lg-12 col-md-12">
	<div class="input-group">
		
			 
	</div>
	<!-- /input-group -->
</div>
</div>
<!-- /.col-lg-6 -->
<br />

<input type="hidden" id="client" name="client">
<input type="hidden" id="clientId" name="clientId">
<div class="row">
	<div class="col-lg-12">
		<div class="patientlist">
		 <div class="minhesigh">
			<table id="allPatient" class="table table-hover table-striped" style="text-transform: uppercase;">
				
			</table>
		</div>
		</div>
	</div>
</div>


