<%@taglib uri="/struts-tags" prefix="s"%>
<div class="row">
<div class="col-lg-12 col-md-12">
	<div class="input-group">
		<input type="text" name="searchText" id="searchText"
			placeholder="Search by first,last name" class="form-control">
			 <span
			class="input-group-btn">
			<button class="btn btn-primary" type="button"
				onclick="searchEmr()">Go!</button>
		</span>
	</div>
	<!-- /input-group -->
</div>
</div>
<!-- /.col-lg-6 -->
<br />

<input type="hidden" id="client" name="client">
<input type="hidden" id="clientId" name="clientId">
<div class="table-responsive" id="allConstation">

</div>


