<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="emr/js/templateInvestigation.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>View/Edit Investigation Payment List</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>



<div class="col-lg-12 col-md-12">
	
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	
</div>

<script>
	$(document).ready(function() {

		$("#fromDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true
		});
	});
	
</script>


<div class="col-lg-12 col-md-12 topback2">
	<div class="col-lg-3 col-md-3">
			<label>Patient</label>
			<div class="input-group" style="width: 100%;">
			<s:hidden name="clientId" id = "clientId" ></s:hidden>
			<s:textfield  name="client" id="client" readonly="true" cssClass="form-control" onclick="showPopUp()"></s:textfield> 
			
		</div>
		</div>
		<div class="col-lg-1 col-md-1" style="margin-top: 21px;">
			<s:submit value="Go!" cssClass="btn btn-primary pull-left"></s:submit> 	
		</div>
		<div class="col-lg-2 col-md-2">
			<label>From Date</label>
			<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
	
		<div class="col-lg-2 col-md-2">
			<label>To Date:</label>
			<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		<div class="col-lg-2 col-md-2" style="margin-top: 21px;"></div>
		<div class="col-lg-2 col-md-2" style="margin-top: 21px;">
			<a href="#" onclick="showInvestigationPopup()" class="btn btn-primary pull-right"><i class="fa fa-plus"></i> Create New  Template</a>
		</div>
	
</div>
<s:form action="graphInvestigation"  theme="simple"  id="invstgraphfrm"  target="formtarget">
<s:hidden name="selectedinvsttype" id="selectedinvsttype"/>
<s:hidden name="selectedtemplateid" id="selectedtemplateid"/>
<s:hidden name="selectedclientid" id="selectedgraphclientid"/>
<s:hidden name="fromDate" id="grfromDate"/>
<s:hidden name="toDate" id="grtoDate"/>
</s:form>
<br/><br><br>	


<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div>
			<table id="results" class="table table-hove table-striped table-bordered">
				
				<tbody>
			
				<s:if test="clientList.size!=0">
					<thead>
						<tr>
							<th>Patient Name</th>
						</tr>
					</thead>
					<tbody>
					  <% int i=0; %>
						<s:iterator value="clientList" status="rowstatus">
							<tr>
								<td><s:property value="clientName" />  <i class="fa fa-arrow-down" onclick="showhidepaymentinvstigationdetails('tst<s:property value="clientid"/>')"></i>
								
								</td>
							</tr>
							
							<!-- Investigation Test -->	
													<tr style="display: none;" id="tst<s:property value="clientid"/>">
														<td>
															   
															  <table id="results" class="table table-hove table-striped table-bordered">
																<tbody>
																      <thead>
																       <tr>
																       
																          <th>Department</th>
																       </tr>
																       </thead>
																       <tr>
																         <td><s:property value="department"/> <i class="fa fa-arrow-down" onclick="showhidepaymentinvstigationdetails('dept<s:property value="clientid"/>')"></i> </td>
																       </tr>
																       
																       <tr style="display: none;" id="dept<s:property value="clientid"/>">
																             <td>
																							
																				<table id="results" class="table table-hove table-striped table-bordered">
																					<thead>
																						<tr>
																							<th>Investigation Test</th>
																								</tr>
																								</thead>
																									<tbody>
																  
																										<s:iterator value="invetigationList">
																											<tr>
																											<td><s:property value="name" /><i class="fa fa-arrow-down" onclick="showhidepaymentinvstigationdetails('tnt<s:property value="id"/>')"></i></td> 
																			
																									 <!-- Investigation Names -->
																									  <tr style="display:none" id="tnt<s:property value="id"/>" >
																									  <td>
																									    <table id="" width="50%" class="table table-hove table-striped table-bordered">
																									          <thead>
																									               <tr>
																									               		 <th><input type="checkbox" onclick="selectAll(<%=i%>)" />Investigation Name</th>
																									               		 <th>Charges</th>	
																									               </tr>
																									          </thead>
																									          
																									          <tbody>
																									             <s:iterator value="investiNames">
																									             
																									                <tr>
																									                 <td> <input type="checkbox" class="case<%=i%>" value="<s:property value="id"/>" >  <s:property value="name"/> </td>
																									                 <td> <input type="text" id="charge<s:property value="id"/>" value="<s:property value="charge"/>" class="form-control" /></td>
																									                </tr>
																									             </s:iterator>
																									             <tr>
																									              <td>
																									                <select id="user<%=i%>" class="form-control" >
																									                  <option value="0">Select User</option>
																									                  <s:iterator value="investigationUsers">
																									                     <option value="<s:property value="id"/>"><s:property value="fullname"/></option>
																									                  
																									                  </s:iterator>
																									                </select>
																									              </td>
																									               <td><input type="button" id="btn<s:property value="clientid"/>" value="Create Charge" class="btn btn-primary" onclick="createcharge(<s:property value="clientid"/>,<s:property value="invsparentid"/>,<%=i%>)" ></td>
																									              <td><input type="button" id="btn<s:property value="clientid"/>" value="Record Payment" class="btn btn-primary" onclick="savecharge(<s:property value="clientid"/>,<s:property value="invsparentid"/>,<%=i%>)" ></td>
																									             </tr>
																									          </tbody>
																									          
																									  	 </table>
																									  </td>
																			  
																			
																		</tr>
																		<%i++; %>
																	</s:iterator>
																	
																</tbody>
															</table>
																             
																             
									        </td>
									           
								   </tr>
																       
																</tbody>
															</table>
														</td>
													</tr>
													
							</s:iterator>
						</tbody>
					</s:if>
				</tbody>
			</table>
		</div>
	</div>
</div>

											
<!-- Add Investigation Popup -->
	
		<!-- add invesgtigation Modal -->
	 <div class="modal fade" id="investigationpopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Create Investigation For <b class="pname" id="invstcmyModalLabel">NAME: </b></h5>
				</div>
				<div class="modal-body" id="investi">
						
						
					<%@ include file="/emr/pages/addtemplateInvestigation.jsp" %>
				    
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveTemplateInvestigation()">Save</button>
						
						<!-- <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)">Save & Print</button> -->

					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
											
										</div>
									</div>
								</div>
							</div>
						</div>






