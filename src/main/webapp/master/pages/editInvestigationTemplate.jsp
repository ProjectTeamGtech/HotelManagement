<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Investigation Template</h4>
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
	        	 new nicEditor({maxHeight : 450}).panelInstance('template_text');
	        	 $('.nicEdit-panelContain').parent().width('100%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });

</script>
<s:form action="updateInvestigationTemplate" id="master_form" theme="simple">
<div class="row">
	
	<div class="col-lg-12 col-md-12">
		<div class="panel panel-primary">
			<div class="panel-body">
			
			<div class="col-lg-12 col-xs-12 col-sm-12" style="padding: 0px;">
					<div class="col-lg-4 col-xs-4 col-sm-4">
						<div class="form-group">
							<label>Select Investigation Section <span class="text-danger">*</span></label>
							 <s:select list="invsSectionList" listKey="id" listValue="name" name="invs_section_id" title="select investigation section"
										cssClass="form-control"  > </s:select>
						</div>
					</div>
					<div class="col-lg-8 col-xs-8 col-sm-8">
					<label>Investigation Template Name <span class="text-danger">*</span></label>
						<div class="form-inline">
							<div class="form-group" style="width: 50%;">
							
							<s:textfield id="title" name="title"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Template Name" placeholder="Enter Template Name" onkeyup="initialCap(this)" style="width: 100%;"/>
						</div>
						<div class="form-group pull-right">
							<s:submit cssClass="btn btn-primary" value="Update" />
						</div>
						<div class="form-group">
							<s:reset cssClass="btn btn-primary" />
						</div>
						<div class="form-group">
							<a href="InvestigationTemplate" class="btn btn-primary">Back</a>
						</div>
						</div>
						
					</div>
					
				</div>
				
				<div class="col-lg-12 col-xs-12 col-sm-12">
				<div class="panel-body">
				<div class="form-group">
							<label>Template Text <span class="text-danger">*</span></label>
							<s:textarea style="height: 450px;"  rows="10" cols="8" id="template_text" name="template_text"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Ipd Template Text" placeholder="Enter Investigation Template Text" ></s:textarea>
				<s:hidden id="id" name="id" />
						</div>
				
			</div>
			</div>
			
			</div>
		</div>
	</div>
	
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
