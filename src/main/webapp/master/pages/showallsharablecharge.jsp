<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Shared Charges</h4>
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="col-lg-12 col-md-12 topback2">
	<div class="col-lg-3 col-md-2">
			<s:select list="masterlist"
					cssClass="form-control showToolTip chosen-select" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)"></s:select>
		</div>
		
		<div class="col-lg-3 col-md-2">
			<!-- <a class="btn btn-primary" href="adddrsharechargeMaster" ><i
			class="fa fa-plus"></i> Add</a> -->
			 <a href="#" class="btn btn-warning" data-toggle="modal" data-target="#addsharechargepopup">Add</a>
		</div>
		<div class="col-lg-1 col-md-1">
			
			</div>
	<%-- <s:form action="showallMedicineTemplateMaster">	
		<div class="col-lg-3 col-md-2">
			<s:textfield theme="simple" name="searchText" placeholder="Search By Template Name"  cssClass="form-control" />
			
		</div>
		<div class="col-lg-1 col-md-1">
			<input type="submit" value="Go" class="btn btn-primary"/>
		</div>
	</s:form> --%>
	<!--<div class="col-lg-3 col-md-2">
	<label>Select Master</label>
	<s:select list="masterlist" name="mastername" listKey="id" listValue="name" 
	onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
	</div>
	<div class="col-lg-6 col-md-8">
			<a class="btn btn-primary" href="addState" style="margin-top:21px;"><i
			class="fa fa-plus"></i> Add</a>
			
	</div>
	<div class="col-lg-3 col-md-2"></div>
--></div>
<div class="row">
	<div class="col-lg-12">
		<s:hidden name="message" id="message"></s:hidden>
		<s:if test="hasActionMessages()">
			<script>
				var msg = " " + document.getElementById('message').value;
				showGrowl('', msg, 'success', 'fa fa-check');
			</script>
		</s:if>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Charge Name</th>
						<th class="text-center">User Name</th>
						<th class="text-center">Share Type</th>
						<th class="text-center">Amount</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
				<%int i=0; %>
				
				</tbody>
				
			</table>
		</div>
	</div>
</div>
						
<%-- <s:form action="" name="paginationForm" id="paginationForm" theme="simple">
<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>
			 --%>								
										</div>
									</div>
								</div>
							</div>
						</div>


<div class="modal fade" id="addsharechargepopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			<s:form action="savemedreqforpharPrescription" id="newmedreqform" theme="simple">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Share Charge</h4>
				</div>
				<div class="modal-body">
					 <div class="form-group">
			         	<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			          		<s:select cssClass="form-control showToolTip chosen-select" headerKey="0" headerValue="Select Charge" list="sharablechargelist" listKey="id" listValue="name" id="sharechargeid" name="sharechargeid" />
			          	</div>
			          	<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			          		<s:select cssClass="form-control showToolTip chosen-select" headerKey="0" headerValue="Select User" list="visitingConsultDoctors" listKey="id" listValue="fullname" id="shareuserid" name="shareuserid" />
			          	</div>
			           	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			           		<a href="#" class="btn btn-primary" onclick="addnewusercharge('sharechargetable')">Add</a>
			           	</div>
			        </div>
			      	<div class="clearfix" style="height: 25px;"></div>
			        	<div class="" style="overflow: scroll; width: 100%; height: 300px;">
			         		<table class="table my-table xlstable table-striped table-bordered" id="sharechargetable" style="width:100%;">
			          			<thead>
			           				<tr>
			           					<th>Sr No</th>
			           					<th>Charge name</th>
			            				<th>User name</th>
			            				<th>Share Type</th>
			            				<th>Share Amount</th>
			            			</tr>
			          			</thead>
			          			<tbody id="sharechargetbody">
			           
			          			</tbody>
			          		</table>
			        	</div>
			      </div>
				  <div class="modal-footer">
						<a href="#" class="btn btn-primary" onclick="savepriscbynurse()">Save</a>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				  </div>
				  </s:form>
			</div>
		</div>
	</div>
						
						
	 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>					
						


