<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Other Template</h4>
								</div>
							</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 450}).panelInstance('other_template_text');
	        	 $('.nicEdit-panelContain').parent().width('900px');
	        	 $('.nicEdit-panelContain').parent().next().width('900px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });

</script>
<s:form action="saveOtherTemplate" id="master_form" theme="simple">
<div class="row">
	
	<div class="col-lg-12 col-md-12">
		<div class="panel panel-primary">
			<div class="panel-body">
			
			<div class="col-lg-12 col-xs-12 col-sm-12" style="padding: 0px;">
			  <%String action=request.getParameter("action");%>
			   <%if(!action.equals("1")){ %>
					<div class="col-lg-4 col-xs-4 col-sm-4">
						<div class="form-group">
							<label>Select Discipline <span class="text-danger">*</span></label>
							 <s:select list="disciplineList" name="discipline_id" listKey="id"  cssClass="showToolTip form-control chosen-select" listValue="discipline"></s:select>
						</div>
					</div>
					<%} %>
					<input type="hidden" id="action" name="action" value="<%=action%>">
					<div class="col-lg-8 col-xs-8 col-sm-8">
					<label>Title <span class="text-danger">*</span></label>
						<div class="form-inline">
							<div class="form-group" style="width: 50%;">
							
							<s:textfield id="title" name="title"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Title" placeholder="Enter Title" onkeyup="initialCap(this)" style="width: 100%;"/>
						</div>
						<div class="form-group pull-right">
							<s:submit cssClass="btn btn-primary" value="Save" />
						</div>
						<div class="form-group">
							<s:reset cssClass="btn btn-primary" />
						</div>
						<div class="form-group">
							<a href="OtherTemplate" class="btn btn-primary">Back</a>
						</div>
						</div>
						
					</div>
					
				</div>
			<div class="col-lg-12 col-xs-12 col-sm-12">
				<div class="panel-body">
				<div class="form-group">
							<label>Other Template Text <span class="text-danger">*</span></label>
							<textarea style="height: 250px;" rows="10" cols="8" id="other_template_text" name="other_template_text"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Other Template Text" placeholder="Enter Other Template Text" ></textarea>
						</div>
				
			</div>
			</div>
			
			    
			</div>
		</div>
	</div>
	
</div>
	
	<s:token></s:token>
</s:form>
 <%String action=request.getParameter("action");%>
 <%if(action.equals("1")){ %>
<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-left" style="width: 5%;">Title</th>
						<th class="text-center" style="width: 5%;">Edit</th>
						<th class="text-center" style="width: 5%;">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="payrolltemplatelist.size!=0">
						<s:iterator value="payrolltemplatelist">
							<tr>
								<td><s:property value="title" /></td>
								<td class="text-center"><a href="#" onclick="opencPopup('editOtherTemplate?selectedid=<s:property value="id"/>&action=1')"><i class="fa fa-edit"></i></a></td>
								<td class="text-center"><a
									href="deleteOtherTemplate?selectedid=<s:property value="id"/>&action=1"
									onclick="return confirmedDelete()" class="text-danger"
									style="width: 5%;"> <i class="fa fa-trash-o"></i>
								</a></td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Other Template found!!
					</s:else>
			</table>
		</div>
	</div>
</div>
<%} %>

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
