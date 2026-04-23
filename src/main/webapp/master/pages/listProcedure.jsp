<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>
<script type="text/javascript" src="master/js/Procedure.js"></script>
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>All Procedure List</h4>
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
	<s:form action="Procedure">	
		<div class="col-lg-3 col-md-2">
			<s:textfield theme="simple" name="searchtext" placeholder="Search By Procedure Name"  cssClass="form-control" />
			
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
<%int i=1; %>
<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">Sr No</th>
						<th class="text-center">Procedure Type</th>
						<th class="text-center">Procedure Name</th>
						<th hidden class="text-center">Charges</th>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="plist!=null">
						<s:iterator value="plist" status="rowstatus">
								<tr>
								<td class="text-center"><%=i++ %></td>
								<td hidden class="text-center"><s:property value="id" /></td>
								<td class="text-center"><s:property value="proceduretype" /></td>
								<td class="text-center"><s:property value="procedureName" /></td>
								
								<!--<td class="text-center"><a href="editState?id=<s:property value="id"/>" class="text-primary">
										<i class="fa fa-edit"></i>
									</a>
								</td>
								-->
<%-- 								 <td class="text-center"><a href="#" onclick="opencPopup('editSitting?selectedid=<s:property value="id" />')"><i class="fa fa-edit"></i></a></td>
 --%>								<td class="text-center"><input type="button" value="Edit" onclick="editprocedureDetails(<s:property value="id"/>)" class="btn btn-sm btn-primary"></td> 
                                     <td class="text-center"><input type="button" value="Delete" onclick="deleteProcedureData(<s:property value="id"/>)" class="btn btn-sm btn-primary"></td> 
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
						
											
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
	<!-- Add Procedure -->					
						
						
<div id="ireq" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Procedure</h4>
        
      </div>
      
      
<div class="modal-body">
      
  <div class="row">
	   <div class="col-lg-12 col-md-12">
		 <div class="panel panel-primary">
			<div class="panel-body">
			
			  
      			<div class="form-group">
	      			<label>Department List </label><label class="text-danger">*</label>
	      			<s:select list="departmentlist" name='department' id='dept' listKey="id"  listValue="discipline" headerKey="" headerValue="All Department" onchange="setDepartmentsitting(this.value)" cssClass="form-control chosen-select" ></s:select>
      			</div>
      		   	
			   <%--  <div class="form-group">
	      			<label>Sitting List</label><label class="text-danger">*</label>
	      			<s:select list="sittinglist" name="sitting" id="sid" listKey="sittingFollowup"  listValue="sittingFollowup" headerKey="" headerValue="All Sitting List" cssClass="form-control chosen-select" ></s:select>
               </div> --%>			
			     
			     <div class="form-group" id="proddiv" >
        				 <label>Select Sitting</label>
        				 <select name="sitting_id" id="sitting_id" class="form-control chosen-select">
						 <option value="0">Select</option> 
						</select>
        			</div>
        	
      
			     <div class="form-group" id="mas_div" >
        				 <label>Procedure MasterList</label>
        				 <select name="master_id" id="master_id" class="form-control chosen-select">
						 <option value="0">Select</option> 
						</select>
        			</div>
			     
			     
			     
			     
			     
			<%--  <div class="form-group">
	      			<label>Procedure Type </label><label class="text-danger">*</label>
	      			<s:select list="proceduretypelist" name="proceduretype" id="id" listKey="id"  listValue="name" headerKey="" headerValue="All Procedure" cssClass="form-control chosen-select" ></s:select>
             </div --%>			
			
		        <label>Procedure Name</label><label class="text-danger">*</label>
				<s:textfield id="procedureName" name="procedureName"
				     cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Procedure Name " placeholder="Enter Procedure Name"/>
					
				</div>
		    </div>
	     </div>
	 </div>
</div>

               <div class="modal-footer">
                      <button type="button" class="btn btn-primary"
							       onclick="saveprocedure()">Save</button>
		       </div>
         </div>
     </div>
 </div>


<!-- Edit procedure -->

<div id="editprocedure" class="modal fade" role="dialog">

	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Edit Procedure</h4>

			</div>
   		<div class="modal-body">
               <div class="row">
					<div class="col-lg-12 col-md-12"></div>
					   <div class="col-lg-12 col-md-8">
						  <div class="panel panel-primary">
							 <div class="panel-body">
								<s:hidden id="proid"></s:hidden>
			                 	    <div class="form-group">
				      				   <label>Procedure Type </label><label class="text-danger">*</label>
				      				   <s:select list="proceduretypelist" name="proceduretype" id="proceid" listKey="name"  listValue="name" headerKey="" headerValue="All Procedure" cssClass="form-control chosen-select" ></s:select>
			                  	    </div>			
			
		                            <label>Procedure Name</label><label class="text-danger">*</label>
				                    <s:textfield id="procedure" name="procedureName"
				                     cssClass="showToolTip form-control" data-toggle="tooltip"
					                 title="Enter Procedure Name " placeholder="Enter Procedure Name"/>
									
						   </div>
					  </div>
				 </div>
			  </div>
         </div>

		    <div class="modal-footer">
          
			           <button type="button" class="btn btn-primary"
				               onclick="updateprocedure()" style="margin-top: 15px;">Update</button>
				    
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
						
