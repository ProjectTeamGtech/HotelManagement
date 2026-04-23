<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="ipd/js/addcharge.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Reference</h4>

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
	<!-- <div class="col-lg-6 col-md-8">
		<a class="btn btn-primary" href="#" style="margin-top:21px;" onclick="opencPopup('addReference')"><i
			class="fa fa-plus"></i> Add</a>
	</div> -->
	
	<div class="col-lg-6 col-md-8">
		<a class="btn btn-primary hidden" href="#" style="margin-top:21px;"data-toggle="modal" data-target="#addnewvisitingconsultdr"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	
	
	
	<!-- <div class="form-group" style="float: right;margin-left: 15px;">
														<a href="#" data-toggle="modal" data-target="#addnewvisitingconsultdr" title="Add New Consultant"><i class="fa fa-cog fa-2x" aria-hidden="true"></i></a>
													</div> -->
	<div class="col-lg-3 col-md-2"></div>
</div>
		<div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
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
						<th class="text-left sortable <s:if test="#attr.pagination.sortColumn.equals('name')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'name');" style="color:#fff;">Dr. Name</a></th>
						
						<th class="text-center">Specialization</th>
						<th class="text-center">Mobile No</th>
						<th class="text-center">Visiting Fee</th>
						<!-- <th class="text-center">Email</th> -->
						<th class="text-center">Visiting Consultant</th>
						<th class="text-center">Surgeon</th>
						<th class="text-center">Anesthesiologist</th>
						
						<th class="text-center"> Referred</th>
						<th class="text-center">MLC</th>
						<!-- <th class="text-center">Qualification</th> -->
						<th class="text-center hidden">Edit</th>
						
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="referenceList.size!=0">
						<s:iterator value="referenceList" status="rowstatus">
							<tr>
								<td><s:property value="reference" /></td>

								<s:hidden value="%{id}" name="id"></s:hidden>
								 <%-- <td class="text-center"><s:property value="name" /></td>  --%>
								<td class="text-center"><s:property value="speciality" /></td>
								<td class="text-center"><s:property value="mobileNo" /></td>
									<td class="text-center"><s:property value="fees" /></td>
								<%-- <td class="text-center"><s:property value="email" /></td> --%>
								<%-- <td class="text-center"><s:property value="isvisitingconsultant" /></td> --%>
								<td class="text-center">
                                <s:if test="visitingConsultant==1">YES</s:if>
								<s:else>No</s:else>
								</td>
								<%-- <td class="text-center"><s:property value="issurgeon" /></td> --%>
								<td class="text-center">
			                     <s:if test="surgeon==1">YES</s:if>
								<s:else>No</s:else>
								</td>
								<%-- <td class="text-center"><s:property value="isanesthesiologist" /></td> --%>
								<td class="text-center">
			                    <s:if test="anesthesiologist==1">YES</s:if>
								<s:else>No</s:else>
								</td>
							
								<%-- <td class="text-center"><s:property value="isrefered" /></td> --%>
								<td class="text-center">
								<s:if test="referred==1">YES</s:if>
								<s:else>No</s:else>
								</td>
								<td class="text-center">
								<s:if test="mlc==1">YES</s:if>
								<s:else>No</s:else>
								</td>
								<%-- <td class="text-center"><s:property value="mlcqualification" /></td> --%>
								<%-- <td class="text-center"><s:property value="qualification" /></td> --%>
								
								<td class="text-center hidden"></td>
								<s:url action="deleteReference" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
									</s:a></td>
							</tr>

						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> No Reference Found!!
					</h3>
				</s:else>
			</table>
		</div>
	</div>
	
	
</div>


<s:form action="Reference" name="paginationForm" id="paginationForm" theme="simple">
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>
											

											 <div class="modal fade" id="addnewvisitingconsultdr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add New Visiting Consultant</h4>
                </div>
                <div class="modal-body">
                	<div class="row">
                		<div class="col-lg-12 col-md-12 col-xs-12">
                		<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					  			<label for="exampleInputPassword1">System Doctor<span class="red">*</span></label>
			   			<s:select id="existingdr" name="existingdr" list="practionerlist" listKey="id" listValue="fullname"
								headerKey="0" headerValue="Select Existing Doctor"
								title="Select Existing Dr" cssClass="form-control showToolTip" onchange="checkdralreadypresent(this.value)" data-toggle="tooltip" />  
					  		</div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
						   		<label for="exampleInputPassword1">Name<span class="red">*</span></label>
				   				<s:textfield cssClass="form-control" id="consultantname" name="consultantname" onkeyup="searchexistingDr(this.value)" data-toggle="tooltip" placeholder="Consultant Name" title="Consultant Name"></s:textfield>
			   				</div>
			   				 <s:hidden name="referid" id="referid"></s:hidden>
			   				 <div class="scrolldoc">
			   				 	<table class="table">
		                            <thead> 
		                            </thead> 
		                            <tbody id="tabledrname"> 
					   				</tbody>
							   </table>
			   				 </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					   	<label for="exampleInputPassword1">Specialization<span class="red">*</span></label>
			   			<s:select id="diciplineName" name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
								headerKey="0" headerValue="Select Specialization"
								title="Select Specialization" cssClass="form-control" data-toggle="tooltip" />  
						</div>
					  	</div>
                	</div>
                	</div>
                	<div class="row">
                		<div class="col-lg-12 col-md-12 col-xs-12">
                		<div class="col-lg-3 col-xs-12 col-md-3 col-sm-3">
					  		<div class="form-group">
					   		<label for="exampleInputPassword1">Mobile No<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="mobileno" name="mobile" data-toggle="tooltip" placeholder="Mobile number" title="Mobile number"></s:textfield>
					   </div>
					   <div class="form-group">
					   		<label for="exampleInputPassword1">Qualification<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="mlcqualification" name="mlcqualification" data-toggle="tooltip" placeholder="Qualification" title="Qualification"></s:textfield>
					   </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					   		<label for="exampleInputPassword1">Email<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="emailid" name="emailid" data-toggle="tooltip" placeholder="Email" title="Email"></s:textfield>
					   	</div>
					   	<div class="form-group">
					   		<label for="exampleInputPassword1">Share %<span class="red">*</span></label>
					    	<s:textfield type="number" cssClass="form-control" id="sharepercentage" name="sharepercentage" data-toggle="tooltip" placeholder="Share Charges" title="Share Charges" value="70"></s:textfield>
					   </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
        					<div class="form-group" id="reftypediv">
        						<label for="exampleInputPassword1">Reference Share Type <span class="red">*</span></label>
     							<s:select name="refsharetype" id="refsharetype" 
    									list="#{'0':'%','1':'Rs'}" 
    									cssClass="form-control chosen-select"></s:select>
        					</div>
         <div class="form-group">
          <label for="exampleInputPassword1">Reference Share Amount<span class="red">*</span></label>
          <s:textfield type="number" cssClass="form-control" id="refshareammount" name="refshareammount" data-toggle="tooltip" placeholder="Share Amount" title="Share Ammount" value=""></s:textfield>
        </div>
        </div>
					  	<div class="col-lg-2 col-xs-12 col-md-2 col-sm-2">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">Fees<span class="red">*</span></label>
					    <s:textfield cssClass="form-control" id="visitingconfees" name="fees" data-toggle="tooltip" placeholder="Fees" title="Fees"></s:textfield>
					  </div> 
					  	</div>
					  	<div class="col-lg-3 col-xs-12 col-md-3 col-sm-3">
					  		<div class="form-group">
					    		<article>
									<ul class="vertical default_list" style="list-style: none;padding: 0px;">
									   <li id="isvisitingconsdiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="isvisitingcons" name="isvisitingcons"><i></i> Visiting Consultant</label></li>
									   <li id="issurgeondiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="issurgeon" name="issurgeon"><i></i> Surgeon</label></li>
									   <li id="isanesthesiologistdiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="isanesthesiologist" name="isanesthesiologist"><i></i> Anesthesiologist</label></li>
									   <li id="isreferreddiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="isreferred" name="isreferred"><i></i> Referred</label></li>
									   <li id="ismlcdiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="ismlc" name="ismlc"><i></i> MLC</label></li>
									 </ul>
								</article>
					  		</div> 
					  	</div>
                	</div>
                	</div>
                	
                	
                	
                	
                    <div class="col-lg-12 col-md-12 col-xs-12">
                    <!-- <form action="addnewvisitingIpdDashboard" id="newvisitdrform" method="post"> -->
					   <%-- <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding:0px;">
					   <div class="col-lg-3 col-md-3 col-xs-3" style="padding: 0px;">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">Initial</label>
					    <s:select id="initial" name="initial" list="initialList" headerKey="0"  title="Select Initial" cssClass="form-control showToolTip" data-toggle="tooltip" />
					  </div>
					  	</div>
					  	<div class="col-lg-4 col-md-4 col-xs-4" style="padding: 0px;">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">First Name</label>
					   <s:textfield name="firstname" id="firstname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="First Name" title="Enter First name"></s:textfield>
					  </div>
					  	</div>
					  	<div class="col-lg-5 col-md-5 col-xs-5" style="padding: 0px;">
					  		 <div class="form-group">
					    <label for="exampleInputPassword1">Last Name<span class="red">*</span></label>
					    <s:textfield name="lastname" id="lastname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Last Name" title="Enter Last Name"></s:textfield>
					  </div>
					  	</div>
					  </div> --%>
					<!-- </form> -->
                    </div>
                </div>
                
               <div class="modal-footer" id="updatebtnid">
                    <button type="button" onclick="addnewconsultantvalidate()" class="btn btn-primary">Save</button>
                </div> 
            </div>
        </div>
    </div>
										</div>
										
									</div>
								</div>
							</div>
						</div>

  <script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
			<script>
				$(function() {
					 $('.scrolldoc').slimScroll({
						height : '200px',
						railVisible: true,
						alwaysVisible: true
					});
				});
 				 
             </script>
	<script>
   $(document).ready(function () {
	    $(".disblebtnone").on("click", function() {
	        $(this).attr("disabled", "disabled");
	        doWork();
	   });
	});
   </script>
   
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
