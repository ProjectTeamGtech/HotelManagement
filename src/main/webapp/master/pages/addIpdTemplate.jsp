<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add IPD Template</h4>
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
 
 $(document).ready(function() {

		$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true


		});
		
		$("#toDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

	});	

</script>
<s:form action="saveIpdtemplate" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-12 col-md-12">
		<div class="panel panel-primary">
			<div class="panel-body">
			
			<div class="col-lg-12 col-xs-12 col-sm-12" style="padding: 0px;">
					<div class="col-lg-3 col-xs-3 col-sm-3">
						<div class="form-group">
							<label>Select Speciality <span class="text-danger">*</span></label>
							<s:select cssClass="form-control chosen-select" id="department" name="department" list="departmentList" headerKey="0" headerValue="Select Speciality" />
						</div>
					</div>
					<div class="col-lg-3 col-xs-3 col-sm-3">
						<div class="form-group">
							<label>Select IPD Template <span class="text-danger">*</span></label>
							<s:select list="ipd_template_names" name="template_nameid" listKey="id"  cssClass="showToolTip form-control chosen-select" listValue="name"></s:select>
						</div>
					</div>
					<div class="col-lg-6 col-xs-6 col-sm-6">
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
						
						<div class="form-group pull-right col-lg-6 col-xs-6 col-sm-3 onoffswitch greensea">
						<input type="checkbox" class="onoffswitch-checkbox" name="showall" >Show to all
						</div>
						<div class="form-group">
							<s:reset cssClass="btn btn-primary" />
						</div>
						<div class="form-group">
							<a href="Ipdtemplate"
				class="btn btn-primary">Back</a>
						</div>
						</div>
						
					</div>
					
				</div>
			
			<div class="col-lg-12 col-xs-12 col-sm-12">
				<div class="panel-body">
				<div class="form-group">
							<label>Ipd Template Text <span class="text-danger">*</span></label>
							<textarea style="height: 450px;" rows="10" cols="8" id="template_text" name="template_text"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Ipd Template Text" placeholder="Enter Ipd Template Text" ></textarea>
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
