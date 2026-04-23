<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript">
	/* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	            )
	        ); });  */
	        
	        bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('declarationNotes');
	        	 $('.nicEdit-panelContain').parent().width('800px');
	        	 $('.nicEdit-panelContain').parent().next().width('800px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });
</script>




<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Declaration</h4>
								</div>
							</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error"/></span>
	</div>
</div>

<s:form action="saveDeclaration" id="declaration_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary" style="width: 850px;height: 637px;">
			<div class="panel-body">
				<label>Title</label>
				<s:textfield id = "title" name = "title" theme="simple" cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Title" placeholder="Enter Title"/>
				<br>
				<label>Declaration</label><label class="text-danger">*</label>
				<textarea style="height: 250px;" rows="10" cols="10" id="declarationNotes" name="declarationNotes"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Declaration" placeholder="Enter Declaration" ></textarea>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>




	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" />
			<s:reset cssClass="btn btn-primary" />
			<a href="backDeclaration" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>


<br><br>
