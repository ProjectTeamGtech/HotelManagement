<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>


<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 450}).panelInstance('other_template_text');
	        	 $('.nicEdit-panelContain').parent().width('100%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });

</script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Edit Other Template</h4>
								</div>
							</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updateOtherTemplate" id="master_form" theme="simple">
<s:hidden name="id" id="id"/>
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
					title="Enter Title" placeholder="Enter Title" style="width: 100%;"/>
						</div>
						<div class="form-group pull-right">
							<s:submit cssClass="btn btn-primary" value="Update" />
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
							<s:textarea rows="10" cols="8" id="other_template_text" name="other_template_text"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Other Template Text" placeholder="Enter Other Template Text" > </s:textarea>
						</div>
				
			</div>
			</div>
				
			</div>
			
			
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>


	<s:token></s:token>
</s:form>


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
