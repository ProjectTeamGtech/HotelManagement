<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>
<script type="text/javascript" src="master/js/unit.js"></script>
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>All Unit List</h4>
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







		<div class="col-lg-1 col-md-1">
			<!-- <a class="btn btn-primary" href="#" onclick="opencPopup('addSitting')"><i
				class="fa fa-plus"></i> Add</a> -->
				<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#ireq">Add</a>
				<!-- <a href="#" class="btn btn-primary" onclick="openAddPopup()">Add</a> -->
				
				
    </div>
		<div class="col-lg-3 col-md-2">
			
		</div>
		<div class="col-lg-1 col-md-1">
			
			</div>
	<s:form action="Unit">	
		<div class="col-lg-3 col-md-2">
			<s:textfield theme="simple" name="searchText" placeholder="Search By Unit"  cssClass="form-control" />
			
		</div>
		<div class="col-lg-1 col-md-1">
			<input type="submit" value="Go" class="btn btn-primary"/>
		</div>
	</s:form>
	</div>
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
						<th class="text-center">Unit</th>
						<th class="text-center">Edit</th> 
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="unitlist!=null">
						<s:iterator value="unitlist" status="rowstatus">
								<tr>
								<td class="text-center"><s:property value="id" /></td>
								<td class="text-center"><s:property value="unitMeasurement" /></td>
								<!--<td class="text-center"><a href="editState?id=<s:property value="id"/>" class="text-primary">
										<i class="fa fa-edit"></i>
									</a>
								</td>
								-->
<%-- 								 <td class="text-center"><a href="#" onclick="opencPopup('editSitting?selectedid=<s:property value="id" />')"><i class="fa fa-edit"></i></a></td>
 --%>								<td class="text-center"><input type="button" value="Edit" onclick="editUnitDetails(<s:property value="id"/>)" class="btn btn-sm btn-primary"></td> 
                                     <td class="text-center"><input type="button" value="Delete" onclick="deleteUnitData(<s:property value="id"/>)" class="btn btn-sm btn-primary"></td> 
								<%-- <td class="text-center"><a href="deleteSitting?selectedid=<s:property value="id"/>" onclick="return ConfirmDelete()" class="text-danger"> --%>
									<!-- 	<i class="fa fa-trash-o"></i> -->
								</a></td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Category List found!!
					</s:else>
			</table>
		</div>
	</div>
</div>
						
<%-- <s:form action="State" name="paginationForm" id="paginationForm" theme="simple">
<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form> --%>
											
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
	<!-- Add Unit -->					
						
						
<div id="ireq" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Unit</h4>
        
      </div>
      
      
      
<div class="modal-body">
      
  <div class="row">
	 <div class="col-lg-3 col-md-2"></div>
	   <div class="col-lg-6 col-md-8">
		 <div class="panel panel-primary">
			<div class="panel-body">
			
			    <label>Unit</label><label class="text-danger">*</label>
				<s:textfield id="unitMeasurement" name="unitMeasurement"
				     
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Unit " placeholder="Enter Unit"/>
					
					
			</div>
		</div>
	</div>
	
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
	
		<div class="col-lg-5 col-md-4"></div>
		
		<div class="col-lg-6 col-md-8">
			
		</div>
		<div class="col-lg-3 col-md-2"></div>
		
	</div>
	
	<s:token></s:token>
	
</div>

                       <%-- <div class="modal-footer">
                                 <s:submit cssClass="btn btn-primary" value="Save"/>  --%>
        
            <div class="modal-footer">
                     <button type="button" class="btn btn-primary"
							   onclick="saveunit()" style="margin-top: 30px;">Save</button>
		    </div>
         
		
        </div>
     </div>
 </div>


<!-- Edit Unit -->


<div id="editunit" class="modal fade" role="dialog">

	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Edit Unit</h4>

			</div>

   <div class="modal-body">

				<div class="row">
					<div class="col-lg-3 col-md-2"></div>
					   <div class="col-lg-6 col-md-8">
						  <div class="panel panel-primary">
							 <div class="panel-body">

								<s:hidden id="id"></s:hidden>

                                <label>Unit</label><label class="text-danger">*</label>
								<s:textfield id="unit" name="unitMeasurement"
									cssClass="showToolTip form-control" data-toggle="tooltip"
									title="Enter Unit"
									placeholder="Enter Unit"/>
									
							</div>
						</div>
					</div>

					  <div class="col-lg-3 col-md-2"></div>
			   </div>

				<div class="row">

					<div class="col-lg-3 col-md-2"></div>
                    <div class="col-lg-6 col-md-8"></div>
					<div class="col-lg-3 col-md-2"></div>

				</div>

				<s:token></s:token>
	</div>

			<%-- <div class="modal-footer">
        <s:submit cssClass="btn btn-primary" value="Save"/>  --%>

                  <div class="modal-footer">
          
			           <button type="button" class="btn btn-primary"
				               onclick="updateunit()" style="margin-top: 15px;">Update</button>
				    
		         </div>
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
						
