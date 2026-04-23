<%@taglib uri="/struts-tags" prefix="s"%>
<script src="master/js/ipdformsetting.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Edit Ipd Form Setting</h4>
								</div>
							</div>

<s:form action="updateIpdformsetting" id="ipdformsetting" theme="simple">

  <s:hidden name="fields" id="fields"></s:hidden>
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "investigationnameTable" width="100%" >
								<thead>
									<tr>
										
										<th style="width: 30%" align="center">Select Department</th>
										<th align="center">Select Fields</th>			
									</tr>
								</thead>
								<tbody>
									<tr>
										
										<td  style="width: 30%">
										<s:select list="specializationList" listKey="id" listValue="name" name="specialization" title="Select Department"
										cssClass="form-control chosen-select"  > </s:select>
										
										</td>									
										<td>
										    <div>
										        <s:iterator value="ipdformfieldList">
										          
										            <s:if test="status==1">
														 <input type="checkbox" checked="checked" class="case" value="<s:property value="id"/>" />   <s:property value="name"/> <br>										            
										            </s:if>
										            <s:else>
										              <input type="checkbox" class="case" value="<s:property value="id"/>" />   <s:property value="name"/> <br>
										            </s:else>
										        </s:iterator>      
										        
										    </div>
										</td>	
										
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group hidden">
								<a onclick="addRow('investigationnameTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRow('investigationnameTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
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
			<input type="button" class="btn btn-primary" value="Save" onclick="saveData()" />
			<s:reset cssClass="btn btn-primary" />
			<a href="Ipdformsetting" class="btn btn-primary">Back</a>
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