<%@page import="com.apm.Registration.eu.entity.Currency"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/commonAppointmentView.js"></script>
<script>


 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "OPD TAT Report",
					filename: "OPDTATReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {
	
	for( j=1;j<=14;j++){
		
		$("#dueDate"+j).datepicker({
			
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#followDate"+j).datepicker({
			
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});}
		
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
	
	 var table = $('#sss').DataTable( {
	        lengthChange: false,
	        buttons: [ ]
	    } );
	 
	    table.buttons().container()
	        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
});
</script>
<style>
.text-center {
	text-align: center;
}
</style>
<div class="">
	<div class="print-visible hidden-md hidden-lg letterheadhgt"
		style="height: 135px;">
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div>
	<div class="row details">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="display: -webkit-inline-box; padding: 0px;">
				<img src="manasmaindashboard/images/Vaccination_Icon.svg"
					style="filter: brightness(5); margin-left: 7px;">
				<h4>Vaccination / Antinatal Dashboard</h4>
			</div>


		</div>
	</div>

	<div class="row ">

		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<s:form action="vaccinationdashboardFinder" theme="simple"
				id="vaccinationfrm">
				<div class="col-lg-12 col-md-12 topback2 hidden-print">
					<div class="form-inline">

						<div class="form-group" style="width: 13%;">
							<s:textfield cssClass="form-control" name="searchText"
								placeholder="Serach by UHID,NAME"></s:textfield>
						</div>

						<div class="form-group" style="width: 7%;">FROM DATE</div>
						<div class="form-group" style="width: 7%;">
							<s:textfield readonly="true" name="fromDate" id="fromDate"
								cssClass="form-control" theme="simple" style="width:100%;"
								placeholder="from date"></s:textfield>
						</div>
						<div class="form-group" style="width: 5%;">TO DATE</div>
						<div class="form-group" style="width: 7%;">
							<s:textfield readonly="true" name="toDate" id="toDate"
								cssClass="form-control" theme="simple" style="width:100%;"
								placeholder="to date"></s:textfield>
						</div>
						<div class="form-group" style="width: 10%;">
							<%-- <s:select name="diaryUser" list="vaccuserList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select> --%>
							<s:select list="#{'':'Immunization','1':'Antinatal'}"
								cssClass="form-control chosen-select" name="vaccineType"
								theme="simple"></s:select>
						</div>

						<div class="form-group" style="width: 10%;">
							<s:select list="#{'':'All','1':'Complete','2':'Pending'}"
								cssClass="form-control chosen-select" name='status'
								theme="simple"></s:select>
						</div>
						<div class="form-group" style="width: 15%;">
							<s:select style="width:100%;" list="vaccineMasterList"
								cssClass="form-control chosen-select" listKey="id"
								listValue="vacinname" headerKey="" headerValue="Select Vaccine"
								name="vaccineMasterId"></s:select>
						</div>
						<div class="form-group hidden" style="width: 18%;">
							<input type="text" class="form-control" id='ptname'
								placeholder="Select Patient" readonly="readonly"
								style="width: 100%" onclick="showAllPatientPopUp()">
						</div>
						<s:hidden name='clientId' id='clientIds'></s:hidden>
						&nbsp;&nbsp;&nbsp;


						<div class="form-group">
							<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
							<a type="button" class="btn btn-primary" title="Print"
								onclick="printpage()"><i class="fa fa-print"></i></a> <a
								type="button" id="btnxls" title="Save As XLS"
								onclick="printExcel()" class="btn btn-primary"><i
								class="fa fa-file-excel-o"></i></a> <a href="#"
								class="btn btn-primary" title="Refresh" onclick="setrefresh()"><i
								class="fa fa-refresh"></i></a>
						</div>

					</div>
				</div>
			</s:form>
			<%
				int i = 0;
			%>
			<table class="my-table xlstable" style="width: 100%" id='sss'>
				<thead>
					<tr bgcolor="#4E7894">
						<td style="color: #eee;" class="manastableheader">Sr. No.</td>
						<td style="color: #eee" class="text-center manastableheader">Due
							Date</td>
						<td style="color: #eee" class="text-center manastableheader">
							UHID</td>
						<td style="color: #eee" class="text-center manastableheader">
							Patient Name</td>
						<td style="color: #eee" class="text-center manastableheader">
							Vaccination Name</td>
						<td style="color: #eee" class="text-center manastableheader">Age/Gender</td>
						<td style="color: #eee;" class="text-center manastableheader">Duration
							(completed weeks/months/years)</td>
						<td style="color: #eee;" class="text-center manastableheader">Status</td>
						<td style="color: #eee;" class="text-center manastableheader">Given
							Date</td>
						<%if(loginInfo.isSjivh()){ %>
						<td style="color: #eee;" class="text-center manastableheader">FollowUP
							Date</td>
						<%} %>	
						<td style="color: #eee;" class="text-center manastableheader">SMS</td>
						<td style="color: #eee;" class="text-center manastableheader">Vaccination
							Consumption</td>
						<td style="color: #eee;" class="text-center manastableheader">Chart</td>

					</tr>
				</thead>
				<tbody>
					<s:if test="vaccinationlist.size!=0">
						<s:iterator value="vaccinationlist">
							<tr>

								<td><%=(++i)%>/-<s:property value="vaccinationid" /></td>
								<%if(loginInfo.isSjivh()){ %>
								<td class="text-center">
								<s:if test="Datecolor==1">
								<input type="text"  readonly="readonly" style="background-color: yellow;" id="dueDate<%=i%>" name="dueDate<%=i%>" value="<s:property value="duedate" />" onchange="updateduedate(this.value,this.id,'<s:property value="mastername" />','<s:property value="ClientId" />','0')">
								</s:if>
								<s:else>
								<input type="text"  readonly="readonly" id="dueDate<%=i%>" name="dueDate<%=i%>" value="<s:property value="duedate" />" onchange="updateduedate(this.value,this.id,'<s:property value="mastername" />','<s:property value="ClientId" />','0')">
								
								</s:else>
								</td>
								<%}else{ %>
								<td class="text-center"><s:property value="duedate" /></td>
								<%} %>
								<td class="text-center"><s:property value="uhid" /></td>
								<%if(loginInfo.isSjivh()){ %>
								<td class="text-center"><s:property value="fullname" /></td>
								<%}else{ %>
								<td class="text-center"><s:property value="clientName" /></td>
								<%} %>
								<td class="text-center"><s:property value="mastername" /></td>
								<td class="text-center"><s:property value="agegender" /></td>
								<td class="text-center"><s:property
										value="vaccinationduration" /></td>
								<td class="text-center">
									<s:if test="givendate!=''">
										Complete
									</s:if> <s:else>
										Pending
									</s:else>
								</td>
								<td class="text-center"><s:property value="givendate" /></td>
								<%if(loginInfo.isSjivh()){ %>
								<s:if test="consumeid!=0 && FollowupId==0">
								<td class="text-center"><input type="text" readonly="readonly" style="background-color: gray;" id="followDate<%=i%>" name="followDate<%=i%>"  onchange="updateFollowup('<s:property value="clientId"/>',this.value,'<s:property value="mastername" />','0')"></td>
							    </s:if>
							      <s:elseif test="FollowupId!=0">
							      	<td class="text-center"><input type="text"  disabled="disabled" style="background-color: gray;" id="followDate<%=i%>" name="followDate<%=i%>"  onchange="updateFollowup('<s:property value="clientId"/>',this.value,'<s:property value="mastername" />','0')"></td>
							      
							      </s:elseif>
							    <s:else>
							    
							    <td class="text-center"><input type="text" readonly="readonly"  id="followDate<%=i%>" name="followDate<%=i%>"  onchange="updateFollowup('<s:property value="clientId"/>',this.value,'<s:property value="mastername" />','0')"></td>
							    
							    </s:else>
							     <%} %>
								<td class="text-center">
									<s:if test="smsflag==1">
										Sent
									</s:if> 
									<s:else>
										<%
											if (loginInfo.isManual_sms_vaccine()) {
										%>
										<s:if test="givendate==''">
											<a class='btn btn-primary'
												onclick="sendVaccineSMS(<s:property value="vaccinationid"/>)">Send
												SMS</a>
										</s:if>
										<%
											} else {
										%>
										<span>No access</span>
										<%
											}
										%>
									</s:else>
								</td>
								<td style="text-align: center;">
									<s:if test="givendate!=''">
										<s:if test="consumption_status==0">
											<%-- <a href="#" class="btn btn-primary"
												onclick="openConsumePopup(<s:property value="vaccinationid"/>)">Consume</a>  --%>
											<a href="#" class="btn btn-primary"
												onclick="openMultiConsumePopup(<s:property value="clientId"/>)">Consume</a> 
											
										</s:if>
										<s:elseif test="consumption_status==1">
											<%-- <a href="#" class="btn btn-primary"
												onclick="showAddchargePopupConsume(<s:property value="vaccinationid"/>,<s:property value="clientId"/>)">Add
												Charge</a> --%>
											<a href="#" class="btn btn-primary"
												onclick="openMultiAddChargePopup(<s:property value="clientId"/>)">Add
												Charge</a>
												
												
										</s:elseif>
										<s:elseif test="status=='Charge Created'">
											<a onclick="openPopup('Accounts?clientId=<s:property value='clientId'/>')"
												href="#" class='btn btn-success'>Charge Added</a>
										</s:elseif>
										<s:elseif test="status=='Invoice Created'">
											<a onclick="openPopup('viewInvoiceCharges?invoiceid=<s:property value='invoiceid'/>&action=show&discount=0.0&payby=<s:property value='payBy'/>&paymentreciptreport=1')"
												href="#" class='btn btn-success'>Invoice Created</a>
										</s:elseif>
										<s:elseif test="status=='Paid'">
											<a onclick="openPopup('viewInvoiceCharges?invoiceid=<s:property value='invoiceid'/>&action=show&discount=0.0&payby=<s:property value='payBy'/>&paymentreciptreport=1')"
												href="#" class='btn btn-success'>Paid</a>
										</s:elseif>
									</s:if> 
									<s:else>
										<span>Yet to be given</span>
									</s:else></td>

								<td style="text-align: center;"><s:if
										test="vaccineType==''||vaccineType==null">
										<a href='#'
											onclick="openPopup('newimmunizationchartSlotAvailable?clientId=<s:property value='clientId'/>')"><i
											class="fa fa-calendar" aria-hidden="true"></i></a>
									</s:if> <s:elseif test="vaccineType==1">
										<a href='#'
											onclick="openPopup('newimmunizationchartSlotAvailable?clientId=<s:property value='clientId'/>&type=1')"><i
											class="fa fa-calendar" aria-hidden="true"></i></a>
									</s:elseif></td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
			</table>
		</div>
	</div>

	<div id="consumemodel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Vaccination Consumption</h4>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<input type="hidden" id="consume_clientid"> <input
							type="hidden" id="consume_vaccinationid">
						<p style="font-size: 15px; color: green;">Patient Name:</p>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12">
						<p style="font-size: 15px;" id="consume_clientname"></p>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12">
						<p style="font-size: 15px; color: green;">Vaccination:</p>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12"
						style="border-bottom: 1px solid #ddd; margin-bottom: 15px;">
						<p style="font-size: 15px;" id="consume_vaccinationname"></p>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12"
						style="margin-top: 10px;">
						<div class="form-group" id="consume_medlist"></div>
					</div>
					<div class="modal-footer" style="padding-top: 30px;">
						<input type="button" class="btn btn-danger" id="consumemodelbtn"
							onclick="consumevaccination()" value="Consume">
					</div>

				</div>

			</div>
		</div>
	</div>

	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);"
		id="dashboardloaderPopup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif"
						class="img-responsive middlelogo"
						style="margin-left: auto; margin-right: auto;"></img>

				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="addchargepopupconsume" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">
						Add Charges</span>
					</h4>
				</div>
				<div class="modal-body">

					<%-- <%@ include file="/ipd/pages/addcharges.jsp"%> --%>
					<jsp:include page="/ipd/pages/invaddcharge.jsp" />
				</div>

				<s:form action="estimateCharges" theme="simple" id="estimatefrm"
					target="formtarget">
					<s:hidden name="clientId" id="estimateclientid" />
					<s:hidden name="actionType" value="0" />
				</s:form>

				<div class="modal-footer">
					<%-- <%if(loginfo.isCash_desk() || loginfo.getUserType()==2){ %>
				<button type="button" class="btn btn-primary" 
							onclick="createChargeAndUpdateAccount('cash')">Cash Desk</button>
				<%} %>
					<button type="button" class="btn btn-primary" 
							onclick="createestimate()">View
							Estimate</button> --%>


					<button type="button" class="btn btn-primary"
						onclick="createChargeAndUpdateAccount('Charge')">Create
						Charge</button>
					<button type="button" class="btn btn-primary"
						onclick="createChargeAndUpdateAccount('cash')">Cash Desk</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<s:form action="invstcashAdditional" id="cashdeskfrm1">

		<s:hidden name="clientId" id="cashClientid" />
		<s:hidden name="thirdParty" id="cashthirdparty" />
		<s:hidden name="location" id="cashLocationid" />
		<s:hidden name="client" id="cashclientname" />
		<s:hidden name="payby" id="cashPayby"></s:hidden>
		<s:hidden name="creditDebitCharge" id="creditDebitCharge" value="0" />
		<s:hidden name="appointmentid" id="cashAppointmentid" />
		<s:hidden name="invoiceid" id="cashinvoiceid" />
		<input type="hidden" name="isfromvaccination" value="1">




	</s:form>
	<%-- 
<s:form action="opdtatreportReport" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromDate"></s:hidden>
							     <s:hidden name="toDate"></s:hidden>
							      <s:hidden name="diaryUser"></s:hidden>
								<div class="col-lg-12 col-md-12" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form>  --%>
</div>










<!-- Modal All Client Search Div -->
<div class="modal fade popoverpop" id="clientSearchDiv" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="z-index: 10005">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					style="margin-top: -7px !important">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id=""><%=loginInfo.getPatientname_field()%>
					Search
				</h4>
			</div>
			<div class="modal-body">
				<jsp:include page="/diarymanagement/pages/allClient.jsp"></jsp:include>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary hidden"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<div id="multiconsumemodel" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Vaccination Consumption</h4>
			</div>
			<div class="modal-body">
			<form action="savemultipleconsumedataBookAppointmentAjax" id="multiVaccineForm">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-xs-12">
						<input type="hidden" id="multiconsume_clientid" name="multiconsume_clientid">
						<input type="hidden" id="multipleVaccineIds" name="multipleVaccineIds">
						<p style="font-size: 15px; color: green;">Patient Name:</p>
						<p style="font-size: 15px;" id="multiconsume_clientname">Test
							User</p>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<p style="font-size: 15px; color: green;">Abrivation:</p>
						<p style="font-size: 15px;" id="multiconsume_abrivation">BG12345464585</p>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 10px;">
					<table class="my-table xlstable" style="width: 100%">
						<thead>
							<tr>
								<th style="width: 5%;">Sr.</th>
								<th style="width: 5%;"><input type="checkbox"
									onclick="selectAllVaccination(this.checked)"></th>
								<th style="width: 30%;">Vaccination Name</th>
								<!-- <th style="width: 10%;">Due Date</th> -->
								<th style="width: 10%;">Given Date</th>
								<th style="width: 35%;">Product List</th>
								<th style="width: 5%;">Quantity</th>
							</tr>
						</thead>
						<tbody id="multiconsumetbody">

						</tbody>

					</table>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 10px;">
					<div class="form-group" id="multiconsume_medlist"></div>
				</div>
				<div class="modal-footer" style="padding-top: 30px;">
					<input type="button" class="btn btn-danger" id="consumemodelbtn"
						onclick="consumeMultipleVaccination()" value="Consume">
				</div>
			</form>
			</div>

		</div>
	</div>
</div>

<div id="multiaddmodel" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Vaccination Against Add Charge</h4>
			</div>
			<div class="modal-body">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-xs-12">
						<input type="hidden" id="multiadd_clientid" name="multiadd_clientid">
						<input type="hidden" id="multipleaddIds" name="multipleaddIds">
						<p style="font-size: 15px; color: green;">Patient Name:</p>
						<p style="font-size: 15px;" id="multiadd_clientname">Test
							User</p>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<p style="font-size: 15px; color: green;">Abrivation:</p>
						<p style="font-size: 15px;" id="multiadd_abrivation">BG12345464585</p>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 10px;">
					<table class="my-table xlstable" style="width: 100%">
						<thead>
							<tr>
								<th style="width: 5%;">Sr.</th>
								<th style="width: 5%;"><input type="checkbox" id="multaddallcheck"
									onclick="selectAllAddVaccination(this.checked)"></th>
								<th style="width: 30%;">Vaccination Name</th>
								<th style="width: 10%;">Given Date</th>
								<th style="width: 35%;">Product Name</th>
								<th style="width: 5%;">Quantity</th>
							</tr>
						</thead>
						<tbody id="multiaddtbody">

						</tbody>

					</table>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 10px;">
					<div class="form-group" id="multiadd_medlist"></div>
				</div>
				<div class="modal-footer" style="padding-top: 30px;">
					<input type="button" class="btn btn-danger" id="addmodelbtn"
						onclick="consumeAddVaccination()" value="Add Charge">
				</div>
			</div>

		</div>
	</div>
</div>

<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/chosen_v1.1.0/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
  
  function setClientName(cid,x,y,z,a){
	  var name = document.getElementById("firstnameid"+cid).value;
	  document.getElementById("ptname").value=name.split('/').join(" ");
	  document.getElementById("clientIds").value=cid;
	  $('#clientSearchDiv').modal( "hide" );
}


  
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

<script type="text/javascript"
	src="pharmacy/searchexport/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
<%--   <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script> --%>
<%--   <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script> --%>

<script>

     $(document).ready(function() {
   alert()
} );
    </script>




