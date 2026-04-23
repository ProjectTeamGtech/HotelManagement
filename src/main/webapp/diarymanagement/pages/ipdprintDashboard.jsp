
<%@page import="com.apm.DiaryManagement.eu.entity.NotAvailableSlot"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s" %>		


<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="accounts/js/printpreview.js"></script>

 <%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<style>

.topback2 {
    background-color: #f5f5f5;
    padding: 10px 10px 10px 10px;
    overflow: hidden;
}

.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 195px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 12px;
    font-weight: bold;
}
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
.titleset{
	margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
}
label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: bold;
}
td, th {
    padding: 0px 5px 0px 5px !important;
    border-right: 1px solid #eee !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
}
.setotas{
	  padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 12px;
}
p {
    margin: 0 0 2.5px !important;
}
.btn{
background-color: #15536E !important;
border-radius: 0.50rem;
}

.ui-datepicker{
width: 18%;
}

.tablebord{
	width: 50%;
	max-width: 50%;
	margin-bottom: 17px
	
</style>
<script>

function showopdpreview(){
	document.getElementById('opdpreviewfrm').submit();
}

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
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Preview List",
					filename: "OPD_Preview",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}

</script>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-md visible-print">
		  		<div class="row letterheadhgt" style="height: 135px;">
					<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							<link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
				</div>
				</div>
<s:form action="getAllPrintDataforipdNotAvailableSlot?action=dashboard" id="opdpreviewfrm">
<s:hidden name="printCommencing" id="printCommencing"/>
<s:hidden name="printLocation" id="printLocation"/>
<div class="row">
	<div class="col-lg-12 " style="padding: 0">	
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: #41cfd6;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<h4 style="font-size: 17px;font-weight: 600;color: white">IPD Preview</h4>
										</div>
									</div>
	<div class="row hidden-print">			
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">		
			
			<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
					<label>Doctor List</label>
				    <s:select cssClass="form-control  chosen-select" id="diaryUser" name="previewdiaryuser" list="userList" listKey="id"
					listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select Practitioner"/>
			</div>
					
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
					<label>From Date</label>
					<s:textfield readonly="true" name="fromDate" id="fromDate" cssClass="form-control" theme="simple" placeholder="from date" style="width: 100%;"></s:textfield>
			</div>
					
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
			<label>To Date</label>
					<s:textfield readonly="true" name="toDate" id="toDate" cssClass="form-control" theme="simple" placeholder="to date" ></s:textfield>
						
						
			</div>
					
			<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
			<label>  &emsp; &emsp; &emsp;</label><br>
				<a href="#" class="btn btn-primary" onclick="showopdpreview()">Go</a>
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()"   class="btn btn-primary" value="Excel"><i class="fa fa-file-excel-o"></i></a>
				<a href="#" class="btn btn-primary" onclick="printpage()"><i class="fa fa-print"></i></a>
			</div>
		
		</div>			
	</div>				
			<%if(letterreq.isSaimed()){ %>
	<div class="row">
		<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12 ">
						<table class="tablebord table-hover table-condensed table-bordered" style="border: black 1px solid; margin-top:2%;">
							<thead>
								<tr>
									<td>Department</td>
									<td>Male</td>
									<td>Female</td>
									<td style="text-align: center;">Total Count</td>
								</tr>
							</thead>
							<tbody>
								 <s:iterator value="deptwisegenderlist">
									
										<tr>
											<td><s:property value="DisciplineName"/></td>
										    <td style="text-align: center;"><s:property value="malecount"/></td>
										     <td style="text-align: center;"><s:property value="femalecount"/></td>
										      <td style="text-align: center;"><s:property value="gendercount"/></td>
										</tr>
									
								</s:iterator> 
							</tbody>
							
						</table>
					</div>
			<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12 ">
			</div>
	</div>
	<%} %>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;padding-bottom: 10px;">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0">
			<label>Total Count of OPD</label> :- <s:property value="totalopdcount"/>
		</div>

	<div class="form-inline">
		<s:iterator value="opdappointmenttype">
			<div class="form-group">
				<label><s:property value="name"/>:</label>
				<span><s:property value="result"/></span>&emsp;&emsp;
			</div>
		</s:iterator>
		
	</div>

				<%-- <table >
				<tbody>
				   <tr>
                                                                    <td><b>Total OPD</b></td>
                                                                    <td><s:property value="totalopdseen"/></td>
                                                                </tr>
                                                                
                                                               <s:iterator value="opdappointmenttype">
                                                               
                                                                <tr>
                                                                    <td> <s:property value="name"/></td>
                                                                </tr>
                                                                <tr>
                                                                <td><s:property value="result"/></td>
                                                                </tr>
                                                               </s:iterator>
                  </tbody>
           </table> --%>
				</div>
		</div>
</div>
</s:form>

<s:if test="actionType!='dashboard'">
	
<table width="1000%" align="center" style="background-color: rgb(216, 212, 212)">
	<tr>
	<s:if test="actionType=='week'">
		<td style="font-size: 20px;"><span style="font-weight:bold;">Practitioner : </span> <s:property value="diaryUser"/></td>
	</s:if>
	<s:if test="actionType=='day'">
		<td style="font-size: 20px; font-weight: bold;">Daily Schedule For <s:property value="commencing"/></td>
	</s:if>	
		
	</tr>
	<s:if test="actionType=='day'">
		<tr>
			<td style="font-weight: bold; font-size: 18px;">Practitioner : <s:property value="diaryUser"/><span style="margin-left:70px;font-size:14px;">Location : <s:property value="locationName"/></span>
			<br><br><span style="font-size: 15px;font-weight: bold;">Daily Notes:</span></td>
			
			
		</tr>
		
		
	</s:if>
	
</table>

</s:if>
	<%ArrayList<NotAvailableSlot> practitionerApmtList=(ArrayList<NotAvailableSlot>)session.getAttribute("practitionerApmtList");%>
	<div class="row">
		<div class="col-lg-12">
			<s:actionmessage cssClass="messageDiv" />
			<div class="table-responsive">
				<table class="table-bordered table  xlstable"   style="font-size: 14px;">
					<thead>
						<tr>
						
					
							<th>Sr.</th>
							<s:if test="actionType!='day'">
								<th>Date</th>
							</s:if>
							<th hidden>Time</th>
							<th hidden>UHID</th>
							<th>Registration Id</th>
							<th>Patient</th>
							<th>Age/Gender</th>
							<%if(loginInfo.isAyushman()){%>
							<th>Mob No</th>
							<%} %>
					<%if(loginInfo.isKalmegha()){%>
							<th>Address</th>
					<%} %>	
							<th>Doctor Name</th>
					<%if(loginInfo.isBalgopal()){%>
					        <th>DOB</th>
					<%} %>	
					<%if(!loginInfo.isBalgopal()){%>
							<th>Note</th>
							<th>Debit</th>
							<th>Discount</th>
							<th>Payment</th>
						<%} %>	
							<th>Department</th>
							<th>Address</th>
							<%if(loginInfo.isBams1()){ %>
							<th>Ipd No.</th>
							<%} %>
							<th>Opd No.</th>
							
							<%if(loginInfo.isBams1()){ %>
							<th>Symptoms</th>
							<th>Treatment</th>
							<th>Punchkarma</th>
							<th>Karma</th>
							<th>Procedure</th>
							<th>Investigation</th>
							<%} %>
							<%if(loginInfo.isMbbs()){ %>
							<th>Print</th>
							<%} %>
					        
							
							
							
							
						</tr>
					</thead>
					<tbody>
					
					
					
					
					<%int i=1; %>
						
							<%for(NotAvailableSlot notAvailableSlot:practitionerApmtList){ %>
								<tr id="<%=notAvailableSlot.getId()%>">
									
										<td><%=i %></td>
								
									<%if(!notAvailableSlot.getActionType().equals("day")) {%>
										<td><%=notAvailableSlot.getCommencing() %></td>
									<%} %>
										<td hidden><%=notAvailableSlot.getSTime() %></td>
									<%if(notAvailableSlot.getStatus().equals("0")){ %>
										<td hidden><%=notAvailableSlot.getUhid() %></td>
										<%if(loginInfo.isKalmegha()){%>	
										 <td><%=notAvailableSlot.getAbrivationid() %></td>
									 <%} %>
									 <%if(!loginInfo.isKalmegha()){%>	
										 
										   <%if(notAvailableSlot.getOldclientid()==null) {%>
										      <td><%=notAvailableSlot.getAbrivationid() %></td>
										  <%}else{ %>
										
										     <td><%=notAvailableSlot.getOldclientid() %></td>
										
									 <%} }%>	
										
										<td><%=notAvailableSlot.getClient() %></td>
										<td><%=notAvailableSlot.getAgegender() %></td>
										<%if(loginInfo.isAyushman()){%>	
										<td><%=notAvailableSlot.getMobno() %></td>
									<%} %>
									<%if(loginInfo.isKalmegha()){%>	
										<td><%=notAvailableSlot.getAddress() %></td>
									<%} %>
										<td><%=notAvailableSlot.getDiaryUser() %></td>
										
										<td hidden><%=notAvailableSlot.getNotes() %></td>
										
								
								<%}else if(notAvailableSlot.getStatus().equals("1")){%>
									
										<td><%=notAvailableSlot.getClient() %></td>
										<td><%=notAvailableSlot.getAgegender() %></td>
									<%if(notAvailableSlot.getStatus().equals("0")){ %>
										<td><s:property value="apmtType" /></td>
									<%}else{ %>
								
										<td><%=notAvailableSlot.getApmtType()%>></td>
									
										<td><%=notAvailableSlot.getNotes() %></td>
										
										
								<%} }else{%>
									
										<td></td>
										<td></td>
										<td></td>
									<%} %>
								<%if(loginInfo.isBalgopal()){%>
								    <td><%=notAvailableSlot.getDob() %></td>
								<%} %>
								<%if(!loginInfo.isBalgopal()){%>
								    <td><%=notAvailableSlot.getNotes() %></td>
									<td><%=notAvailableSlot.getDebit() %></td>
									<td><%=notAvailableSlot.getDiscount()%></td>
									<td><%=notAvailableSlot.getPayment() %></td>
								<%} %>
									
								    <td><%=notAvailableSlot.getDepartmentname() %></td>
								    <td><%=notAvailableSlot.getAddress() %></td>
								    <%if(loginInfo.isBams1()) {%>
									<td style="cursor: pointer;" onclick="showdischargeprintpage('<%=notAvailableSlot.getClientId() %>')"><%=notAvailableSlot.getIpdid() %></td>
									<%} %>
									<td style="cursor: pointer;" onclick="openPopup('printConsulatationFormCommonnew?clientId=<%=notAvailableSlot.getClientId() %>&apmtId=<%=notAvailableSlot.getId() %>')"><%=notAvailableSlot.getOpdAbbrId() %></td>
									
									
									
									<%if(loginInfo.isBams1()) {%>
									<td><%=notAvailableSlot.getChief_complains() %></td>
									<td><%=notAvailableSlot.getPriscription() %></td>
									<td><%=notAvailableSlot.getPunchkarma() %></td>
									<td><%= notAvailableSlot.getKarma() %></td>
									<td><%= notAvailableSlot.getProcedurebams()%></td>
									<td><%=notAvailableSlot.getInvestigation() %></td>
									<%} %>
									<%if(loginInfo.isMbbs()){ %>
									<td style="font-size: 13px;"><i class='fa fa-print fa-1x' onclick="openPopup('printConsulatationFormCommonnew?clientId=<%=notAvailableSlot.getClientId() %>&apmtId=<%=notAvailableSlot.getId() %>')"></i></td>
									<%} %>
									
									<%i++; %>
									<%} %>
					</tbody>
					<%-- <tbody>
					<%int i=1; %>
						<s:if test="practitionerApmtList.size!=0">
							<s:iterator value="practitionerApmtList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									
										<td><%=i %></td>
									<s:if test="actionType!='day'">
										<td><s:property value="commencing" /></td>
									</s:if>
										<td hidden><s:property value="sTime" /></td>
									<s:if test="status==0">
										<td hidden><s:property value="uhid" /></td>
										<%if(loginInfo.isKalmegha()){%>	
										 <td><s:property value="abrivationid" /></td>
									 <%} %>
									 <%if(!loginInfo.isKalmegha()){%>	
										   <s:if test="oldclientid==null ">
										      <td><s:property value="abrivationid" /></td>
										   </s:if>
										<s:else>
										     <td><s:property value="oldclientid" /></td>
										</s:else>
									 <%} %>	
										
										<td><s:property value="client" /></td>
										<td><s:property value="agegender" /></td>
									<%if(loginInfo.isKalmegha()){%>	
										<td><s:property value="address" /></td>
									<%} %>
										<td><s:property value="diaryUser" /></td>
										
										<td hidden><s:property value="notes" /></td>
										
									</s:if>
									<s:elseif  test="status==1">
										<td><s:property value="client" /></td>
										<td><s:property value="agegender" /></td>
									<s:if test="status==0">
										<td><s:property value="apmtType" /></td>
									</s:if>
									<s:else>
										<td><s:property value="apmtType" /></td>
									</s:else>
										
										<td><s:property value="notes" /></td>
										
										
										
									</s:elseif>
									<s:else>
										<td></td>
										<td></td>
										<td></td>
									</s:else>
								<%if(loginInfo.isBalgopal()){%>
								    <td><s:property value="notes" /></td>
									<td><s:property value="debit" /></td>
									<td><s:property value="discount" /></td>
									<td><s:property value="payment" /></td>
								<%} %>
									
								    <td><s:property value="departmentname"/></td>
								    <td><s:property value="address"/></td>
									<td style="cursor: pointer;" onclick="showdischargeprintpage('<s:property value="ClientId"/>')"><s:property value="Ipdid"/></td>
									<td style="cursor: pointer;" onclick="openPopup('printConsulatationFormCommonnew?clientId=<s:property value="ClientId"/>&apmtId=<s:property value="Id"/>')"><s:property value="OpdAbbrId"/></td>
									<td><s:property value="diagnosis"/></td>
									<td><s:property value="Chief_complains"/></td>
									<td><s:property value="Priscription"/></td>
									
									<%if(loginInfo.isBams1()) {%>
									<td></td>
									<%} %>
									<td><s:property value="Investigation"/></td>
									</tr>
									<%i++; %>
								</s:iterator>
								
								
								
							</s:if>
						</tbody> --%>
					</table>
				</div>
			</div>
			
		</div>
		
		
		<div class="row" >
			
				
				</div><br>
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