<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="emr/js/templateInvestigation.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<style>
.loktbl{
width: 100%;
padding: 4px;
border: 1px solid #266d71;
}

.loktbl th{
background-color: #a5dbde;
 padding: 4px;
 border: 1px solid #266d71;
 color: #114f52;
}
.loktbl td{
border: 1px solid #266d71;
    padding: 4px;
    color:#333;
    font-family:  'Open Sans', sans-serif !important;
    background: #ecfcff;
}
.xx{
margin-right: 10px;
}
</style>

<%String templateView="hidden",individualView="";

if(loginInfo.isAnalytics_template()){
	templateView="";
	individualView="hidden";
}
%>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4><!-- View/Edit Investigation Template List -->Analytics</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


<input type="hidden" name='setp' id='setp' value="0">
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
	
	
	function showlist(){
		document.getElementById('templatev').submit();
	}
</script>


			
<div class="col-lg-12 col-md-12 topback2">
	<div class="col-lg-3 col-md-3">
			<label>Patient</label>
			<div class="input-group" style="width: 100%;">
			<s:hidden name="clientId" id = "clientId" ></s:hidden>
			<s:textfield  name="client" id="client" readonly="true" cssClass="form-control" onclick="showPopUp()"></s:textfield> 
			
		</div>
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
		 <div class="col-lg-1 col-md-1 <%=templateView %>" style="margin-top: 21px;">
			<s:submit value="Go!" cssClass="btn btn-primary pull-left"></s:submit> 	
		</div>
		<div class="col-lg-1 col-md-1 <%=individualView %>" style="margin-top: 21px;">
			<input type="button" class='btn btn-primary' value='Show'  onclick="showlist()">
		</div>
	
		
		 <div class="col-lg-2 col-md-2 <%=templateView %>">
		 <br>
			<a href="#" onclick="showInvestigationPopup()" class="btn btn-primary pull-right"><i class="fa fa-plus"></i> Create New  Template</a>
		</div>
	
</div>
<s:form  action="templateInvestigation"  theme="simple"  id="templatev">
<s:hidden name="fromDate" />
<s:hidden name="toDate" />
<s:hidden name="clientId" id='clidinv'/>
</s:form>

<s:form action="graphInvestigation"  theme="simple"  id="invstgraphfrm"  target="formtarget">
<s:hidden name="selectedinvsttype" id="selectedinvsttype"/>
<s:hidden name="selectedtemplateid" id="selectedtemplateid"/>
<s:hidden name="selectedclientid" id="selectedgraphclientid"/>
<s:hidden name="fromDate" id="grfromDate"/>
<s:hidden name="toDate" id="grtoDate"/>
</s:form>
<s:form action="graphreportInvestigation"  theme="simple"  id="invstgraphfrm1"  >
<s:hidden name="selectedinvsttype" id="selectedinvsttype1"/>
<s:hidden name="selectedtemplateid" id="selectedtemplateid1"/>
<s:hidden name="investtemp" id="investtemp1"/>
<s:hidden name="selectedclientid" id="selectedgraphclientid1"/>
<s:hidden name="fromDate" id="grfromDate1"/>
<s:hidden name="toDate" id="grtoDate1"/>

</s:form>
<br/><br><br>	
<s:form action="graphInvestigation">
<div class="row ">
	<div class="col-lg-12">
		<div>
			<table id="results" class="table table-hove table-striped table-bordered  <%=templateView %>">
				
				<tbody>
			
				<s:if test="templateList.size!=0">
					<thead>
						<tr>
							<th>Template Name</th>
							<th>Show Graph</th>
							<th>Show Report</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="templateList" status="rowstatus">
							<tr>
								<td id="tempname"><s:property value="name" /> (<s:property value="date"/>) <i class="fa fa-arrow-down" onclick="showhideinvsttypedetails('t<s:property value="id"/>')"></i></td>
								<td>	<span><a href="#" onclick="showGreap(<s:property value="id"/>)"><img src="emr/img/showgraph.png" alt="Show Graph"></a></span></td>
								<td>	<span><a href="#" onclick="showGreap1(<s:property value="id"/>)"><img src="emr/img/showreportlogo.png" alt="Show Report"></a></span></td>
									
							
							</tr>
							
							<!-- Investigation Type -->
								<tr style="display: none;" id="t<s:property value="id"/>">
									<td>
										<table id="results" class="table table-hove table-striped table-bordered">
											<thead>
												<tr>
													<th>Investigation Type</th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="invstTypeList">
													<tr>
														<td><input class="casegraph<s:property value="templateid"/>" type="checkbox" name="ch<s:property value="id"/>" id="ch<s:property value="id"/>" value="<s:property value="name" />"/>
														<s:property value="name" /> <i class="fa fa-arrow-down" onclick="showhideinvsttestdetails('tst<s:property value="id"/>')"/></td>
													</tr>
													
													<!-- Investigation Test -->	
													<tr style="display: none;" id="tst<s:property value="id"/>">
														<td>
															<table id="results" class="table table-hove table-striped table-bordered">
																<thead>
																	<tr>
																		<th>Investigation Test</th>
																	</tr>
																</thead>
																<tbody>
																	<s:iterator value="testNameList">
																		<tr>
																			<td><s:property value="invstname" /></td>
																		</tr>
																	</s:iterator>
																</tbody>
															</table>
														</td>
													</tr>
													
										</s:iterator>
									</tbody>
								</table>
							</td>
							
						</tr>
					</s:iterator>
							
						
				</s:if>
				</tbody>
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> There is no template list
						found!!
					</h3>

				</s:else>

			</table>
		</div>
	</div>
	
</div>
</s:form>
<div class="col-lg-12 <%=individualView %>">
	<table class="loktbl <%=individualView %>">
	<tr>
	<th>Names</th>
	</tr>
	<s:iterator value="investigationList">
	<tr>
	<td><a href="investigationgraphCommonnew?invstId=<s:property value="id" />"><s:property value="name" /></a></td>
	</tr>
	</s:iterator>
	</table>
</div>	
	
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






