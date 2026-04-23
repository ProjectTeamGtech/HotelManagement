<%@taglib uri="/struts-tags" prefix="s"%>
<script src="master/js/investigationname.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Scheduler Sub-Task Name</h4>
								</div>
							</div>

<s:form action="saveSchedulerSubtask" id="master_form" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "subtaskTable" width="100%" >
								<thead>
									<tr>
										<!-- <th align="center">Delete</th> -->
										<th align="center">#</th>	
									    <th align="center">Select Task</th>
										<th align="center">Sub-Task</th>							
									</tr>
								</thead>
								<tbody>
									<tr>
										<!-- <td><INPUT type="checkbox" name="chk" title="Delete row" /></TD> -->										
										<td>1</td>
										<td><s:select list="schedulerlist" listKey="id" listValue="taskname" name="subtask_name[0].taskname" title="select task type"
										cssClass="form-control"  > </s:select>
										
										</td>									
										<td><input type="text" name="subtask_name[0].subtask" placeholder="enter subtask name" id="name" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										
																		
										
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addsubtaskRow('subtaskTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<!-- <a onclick="deleteRow('subtaskTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a> -->
								
								
			<%-- 	<label>Assessment Field Name</label><label class="text-danger">*</label>
				<s:textfield id="filedname" name="filedname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter filedname" placeholder="Enter filedname"/> --%>
					</div>
			</div>
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="saveSchedulerSubtask" />
			<s:reset cssClass="btn btn-primary" />
			<a href="SchedulerSubtask" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>








































<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add SubTask</h4>
								</div>
							</div>

<s:form action="saveSchedulerSubtask" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			    <label>Select Task</label><label class="text-danger">*</label>
			    <s:select list="schedulerlist" headerKey="0" headerValue="Select task" id="taskname" name="taskname" listKey="id" listValue="taskname"  cssClass="form-control"></s:select>			   
				
				<label>SubTask</label><label class="text-danger">*</label>
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
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return saveSchedulerSubtask()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="SchedulerSubtask" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>

 
 <table class="table table-striped table-hover table-condensed" id = "subtaskTable" width="100%" >
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Select Task</th>
										<th align="center">Sub-Task</th>			
																		
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<td>1</td>
										<td><s:select list="schedulerlist" listKey="id" listValue="taskname" name="subtask_name[0].id" title="select task type"
										cssClass="form-control"  > </s:select>
										
										</td>									
										<td><input type="text" name="subtask_name[0].subtaskname" placeholder="enter subtask name" id="name" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<!-- <td><input type="text" name="investigation_name[0].specimen" placeholder="enter specimen" id="specimen" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td style="display: none;"><input type="text" name="investigation_name[0].report_type" placeholder="enter reporttype" id="reporttype" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="investigation_name[0].unit" placeholder="enter unit" id="unit" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="investigation_name[0].normal_value" placeholder="enter normal value" id="normal_value" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><input type="text" name="investigation_name[0].charge" placeholder="enter charge" id="charge" class="form-control showToolTip filedname" data-toggle="tooltip"/></td> -->
									</tr>
								</tbody>
							</table>
 
 <div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return saveSchedulerSubtask()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="SchedulerSubtask" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
 
 
  --%>
  
  
  
  
  
  
  