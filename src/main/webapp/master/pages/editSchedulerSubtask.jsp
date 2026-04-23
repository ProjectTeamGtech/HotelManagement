<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Edit Scheduler Subtask Details</h4>
								</div>
							</div>

<s:form action="updateSchedulerSubtask" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				<s:hidden name="id"></s:hidden>
			    <label>Select Task</label><label class="text-danger">*</label>
			    <s:select list="schedulerlist" id="parentid" headerKey="0" headerValue="Select Task" name="parentid" listKey="id" listValue="taskname" cssClass="form-control"></s:select>			   
				<label>Sub-Task</label><label class="text-danger">*</label>
				<s:textfield id="subtask" name="subtask"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Subtask" placeholder="Enter Subtask"/>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="update" onclick="return saveSchedulerSubtask()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="SchedulerSubtask" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
