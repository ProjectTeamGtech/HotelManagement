<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Discipline</h4>
								</div>
							</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error"/></span>
	</div>
</div>

<s:form action="saveDiscipline" id="master_form" theme="simple">

<input type="hidden" id="disciplineErrorl1" name="disciplineErrorl1" />

<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				<label>Discipline</label><label class="text-danger">*</label>
				<s:textfield class="form-control is-invalid" id="discipline" name="discipline"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Discipline" placeholder="Enter Discipline"/>
					<label  id = "disciplineError" class="text-danger"></label>
			</div>
			
			<div class="panel-body">
				<label>Description</label><label class="text-danger">*</label>
				<s:textarea cols="40" rows="4" id="description" name="description"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Description" placeholder="Enter Description" />
			</div>
			
			<div class="panel-body">
				<label>Area</label>
				<s:textfield id="area" name="area"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Area" placeholder="Enter Area"/>
			</div>
			
			<div class="panel-body">
				<label>Floor</label>
				<s:textfield id="floor" name="floor"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Floor" placeholder="Enter Floor"/>
			</div>
			
			<div class="panel-body">
				<label>Room No</label>
				<s:textfield id="room_no" name="room_no"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Room No" placeholder="Enter Room No"/>
			</div>
			
			<div class="panel-body">
				<label>Show in Department List</label>
				<s:checkbox name="showdeptlist" cssStyle="margin-top: 2px;"/>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>




	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<%-- <s:submit cssClass="btn btn-primary" value="Save" onclick="validDiscipline()" /> --%>
			<input type="button" value="Save" onclick="validDiscipline()" style="width: 100px" class="btn btn-success active">
			<s:reset cssClass="btn btn-primary" />
			<a href="Discipline" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>



