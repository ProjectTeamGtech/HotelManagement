<%@taglib uri="/struts-tags" prefix="s"%>

<script src="master/js/sharecharge.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<style>
	.form-group {
	    margin-bottom: 5px;
	}
	.baccolors{
		background-color: #f4f4f4;
	    min-height: 430px;
	    padding-top: 10px;
	}
	input[type="checkbox"] {
    margin-right: 4px;
}
</style>

<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Share Charges </h4>
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="col-lg-12 col-md-12 topback2">

	<div class="col-lg-3 col-md-2">
	<label>Select Master</label>
	<s:select list="masterlist" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
	</div>
	<div class="col-lg-6 col-md-8">

	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>


<s:form action="updateSharecharge" id="tpchargeform" theme="simple">
<div class="">
			<div class="">
				<div class="row">
					<div class="col-lg-12" id = "tpchargeTable">			
						<div class="col-lg-3 col-md-3 col-xs-12 col-sm-3 baccolors">
							<div class="form-group">
							    <label for="exampleInputEmail1">Select ChargeType</label>
							     <s:select list="newChargeTypeList" listKey="name" listValue="name" name="chargeType" id="chargeType" title="select Charge Type"
											  cssClass="form-control" onchange="getusers(this.value)" headerKey="0" headerValue="Select Charge Type" > </s:select>
							  </div>
						</div>
						<div class="col-lg-9 col-md-9 col-xs-12 col-sm-9" style="padding-top:10px;">
							<div class="form-group">
							    <label for="exampleInputEmail1">Select Users</label>
							    <div id="scroll" >
												<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;border-top: 1px solid #ddd;padding-top: 2px;">
														<div class="col-lg-8 col-md-8 col-sm-8" style="padding:0px;">
														 		<div class="form-group">
																<!--<input class="case" type="checkbox"   id="ch<s:property value="id"/>" name="ch<s:property value="id"/>" value="<s:property value="id"/>">
																<s:property value="fullname"/>
															--></div>
														 </div>
														
												</div>
										</div>
							  </div>
							
						</div>
								
						
						<div class="form-group hidden">
								<a onclick="" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
					</div>
			</div>
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-12 col-md-12 text-right">
			<input type="button" class="btn btn-primary" value="Save" onclick="updateShareCharges()" />
			<a href="backInvestigationNameMaster" class="btn btn-primary">Back</a>
		</div>
	</div>
	<s:token></s:token>
</s:form>

<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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

<script>
$(function () {
    $('#scroll').slimScroll({
        height: '400px',
        railVisible: true,
		alwaysVisible: true
    });
   
});

</script>



