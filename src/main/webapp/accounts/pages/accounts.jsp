<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>



<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script>

function setSelectAll(){
	
	if(document.getElementById('selectAlls').checked==true){
		document.getElementById('hdnSelectAll').value = true;
	}else{
		document.getElementById('hdnSelectAll').value = false;
	}
	$("#dashboardloaderPopup").modal('show');
	document.getElementById('category_form').submit();
}


$(function() {
		$( "#clientSearchDiv").dialog({
			autoOpen: false,
			resizable: true,
			height: 500,
			width: 650,
			modal: true,
			buttons: {
				
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			}
		});
		$( "#transactionPopup").dialog({
			autoOpen: false,
			resizable: true,
			height: 500,
			width: 650,
			modal: true,
			buttons: {
				
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			}
		});
});

window.onload = function(){
	 var clinic=document.getElementById("clinicuser").value;
     if(clinic=='true'){
    	 document.getElementById('selectAlls').checked==true
    	 document.getElementById('hdnSelectAll').value = true;
	}
	
	var id = document.getElementById('hdnSelectedID').value;
	$(document.getElementById(id)).css('background-color', 'rgb(243, 215, 242)');
	
	if(document.getElementById('hdnSelectAll').value=='true'){
		$("#selectAlls").attr("checked", true);
		
		<%ArrayList<Accounts>allChargeList = (ArrayList<Accounts>)session.getAttribute("allChargeList");%>
		<%if(allChargeList!=null){%>
		
			<%for(Accounts accounts : allChargeList){%>
		
				$("#ch-"+<%=accounts.getInvoiceid()%>).attr("checked", true);
				if(clinic=='true'){
				setTempChargeInvoiceAjax("ch-"+<%=accounts.getInvoiceid()%>)
				}
			<%}%>
		
		<%}%>
		
		
	}else{
		document.getElementById('selectAlls').checked = false;
		
	}
	
	

	    $("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		/* if(clinic=='true'){
			document.getElementById('category_form').submit();
	} */
		
}


function resetAllSelect(){
	$("#selectAlls").attr("checked", false);
	document.getElementById('hdnSelectAll').value = false;
	
	<%if(allChargeList!=null){%>
	
		<%for(Accounts accounts : allChargeList){%>
	
			$("#ch-"+<%=accounts.getInvoiceid()%>).attr("checked", false);

		<%}%>
	
	<%}%>
	
}
</script>
<%String chechdatahide="";
if(loginfo.isCreate_invoice_selected_charges()){
	/* chechdatahide="hidden"; */
}
%>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Process Charges </h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<span class="error"><s:actionerror id="server_side_error"/></span>
		<input type="hidden" value="<%=loginfo.isCreate_invoice_selected_charges()%>" id="clinicuser">
		<s:form action="Accounts"  theme="simple" id="category_form"> 
			<s:hidden name="hdnSelectedID" id="hdnSelectedID"/>
			<s:hidden name="hdnSelectAll" id="hdnSelectAll"/>
			
		
	
	<div class="col-lg-12 col-md-12 topback2">
		<div class="col-lg-2 col-md-2">
			<label><%=loginfo.getPatientname_field() %></label>
			 <div class="input-group" style="width:100%;">
                
				<%-- <s:textfield name="client" id="client"  readonly="true"
				cssClass="form-control" onchange = "setData()" onclick="showPopUp();"  data-toggle="modal" data-target="#clientSearch" aria-describedby="basic-addon1"/> --%>          </div><!-- /input-group -->
			<s:textfield name="client" id="client"  readonly="true"
				cssClass="form-control"/>
			<s:hidden name="clientId" id="clientId"></s:hidden>
		</div>
		<div class="col-lg-2 col-md-2">
			<label>Show Payed by</label>
			<s:select name="payby" id="payby"
				list="#{'All':'All','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control chosen-select" onchange="goAccountSubmit()"></s:select>
		</div>
		<div class="col-lg-2 col-md-2">
				
			<label>Show Referral Department</label>
			<s:select id="location" name="location" listKey="id"
				headerValue="All" headerKey="All" listValue="location"
				list="locationList" value="location" cssClass="form-control chosen-select" onchange="goAccountSubmit()"></s:select>
		</div>
		
		<div class="col-lg-2 col-md-2">
			<label>Show Transaction</label>
			<s:select name="transactionType"
				list="#{'All':'All','Charge':'Charge','DNA':'DNA'}"
				cssClass="form-control chosen-select" onchange="goAccountSubmit()"></s:select>
		</div>
		<div class="col-lg-2 col-md-2">
			<label>Show TP</label>
			<s:select id="thirdParty" name="thirdParty" listKey="id"
				listValue="thirdParty" headerValue="All" headerKey="All"
				list="thirdPartyList" cssClass="form-control chosen-select" onchange="goAccountSubmit()"></s:select>
		</div>
		<div class="col-lg-2 col-md-2">
			<label>Charge Type</label>
			<s:select name="raiseChargeType"
				list="#{'All':'All','0':'Appointment Charge','1':'Additional Charge'}"
				cssClass="form-control chosen-select" onchange="goAccountSubmit();"></s:select>
		</div>
		<div class="col-lg-2 col-md-2">
			<label>From Date</label>
			<s:textfield name="fromDate" id="fromdate" cssClass="form-control" ></s:textfield>
		</div>
		<div class="col-lg-2 col-md-2">
			<label>To Date</label>
			<s:textfield name="toDate" id="todate" cssClass="form-control" ></s:textfield>
		</div>
		
		
		<div class="col-lg-2 col-md-2 ">
			<label>Select Medicine Bill</label>
			<s:select cssClass="form-control" list="allBillList" onchange="addpharmacybill(this.value)" headerKey="0" headerValue="Select Bill" listKey="id" listValue="notes" />
			
		    <!--<div id="scroll">
			<s:iterator value="allBillList">
				<input class="case" type="checkbox"  id="ch<s:property value="id"/>" name="ch<s:property value="id"/>" value="<s:property value="id"/>">
				<s:property value="notes"/><br>
			</s:iterator>
			</div>
		  
		-->
		</div>
		<div class="col-lg-2 col-md-2">
			<label>IPDs</label>
			<s:select  name="ipdidnew" listKey="ipdid"
				headerValue="All Ipd" headerKey="" listValue="ipdseqno"
				list="ipdseqlist"  cssClass="form-control" id="ipdidnew" onchange="goAccountSubmit();"></s:select>
		</div>
		<div class="col-lg-2 col-md-2">
			<label class="width-full" style="font-weight: normal">.</label> <input
				type="submit" value="Go" class="btn btn-primary">
		</div>
		
	   <s:hidden name="billno" id="billno"></s:hidden>
		<input type="hidden" id="autoselect" name="autoselect" value='<s:property value="autoselect"/>'>
	</div>
	
	
	
	<s:if test="clientId!=0">
	<div class="">
		<div class="col-lg-6 col-md-6" style="margin-top: 15px;">
				<b><%=loginfo.getPatientname_field() %> Details:</b>
				<br> UHID:
			<s:property value="abrivationid"/>
				<br>
				Name:-<s:property value="client" /> <br>
				Address:-<s:property value="address" /><br>
				Postcode:-<s:property value="postcode" /><br>
				Contact No:-<s:property value="mobno" /><br>
				Email:-<s:property value="email" />
		</div>
		<div class="col-lg-6 col-md-6" style="margin-top: 15px;">
			<b>Third Party Details</b><br />
			Name:-<s:property value="insuranceCompany" /><br>
			Address:-<s:property value="thirdPartyAddress" /><br>
			Postcode:-<s:property value="thirdPartyPostcode" /><br>
			Contact No:-<s:property value="thirdPartyContacttno" /><br>
			Email:-<s:property value="thirdPartyemail" />
			
		</div>
	</div>
	</s:if>
	<br />
		
		</s:form>
		
		
<s:actionmessage cssClass = "messageDiv"/>
		<div><hr/></div>
		<s:if test="accountList!=null">
			<div class="">
				<div class="col-lg-12">
					<div class="">
					<table class="table table-hover table-condensed table-bordered" id = "chargestbl1">
						<thead>
							<tr>	
								<s:if test="raiseChargeType == 1">
									<th>
										<!-- <i class="fa fa-user"></i> Sr.No.<input type="checkbox" name="selectAlls" id="selectAlls" onclick="setSelectAll()"> -->
										<i class="fa fa-user"></i> Sr.No.<input type="checkbox" name="selectAlls" id="selectAlls" onclick="selectAllCheckBox()">
									</th>
								</s:if>
								<s:else>
									<s:if test="payby1!='All' && location!='All'">
										<th> 
											<%-- Sr.No.<input type="checkbox" name="selectAlls" id="selectAlls" onclick="setSelectAll()" class="<%=chechdatahide%>"> --%>
											Sr.No.<input type="checkbox" name="selectAlls" id="selectAlls" onclick="selectAllCheckBox(this.checked)" class="<%=chechdatahide%>">
										</th>
									</s:if>
									<s:else>
										<th> 
											<%-- Sr.No.<input style="display: none;" type="checkbox" name="selectAlls" id="selectAlls" onclick="setSelectAll()" class="<%=chechdatahide%>"> --%>
											Sr.No.<input style="display: none;" type="checkbox" name="selectAlls" id="selectAlls" onclick="selectAllCheckBox()" class="<%=chechdatahide%>">
										</th>
									</s:else>
								</s:else>
								<th title="Sort by Date" class="text-center sortable <s:if test="#attr.pagination.sortColumn.equals('commencing')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
								<a href="#" onclick="fnPagination(6,'commencing');" style="color:white;"> Date</a></th>
								<th> Transaction</th>
								<th> Payee</th>
		                        <th> Location</th>
								<!-- <th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Status</th> -->
								<th style="text-align: center;"> Description</th>
								<th> Debit</th>
								<th> Credit</th>
		                        <th> Balance</th>		
						
								<!-- <th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Preview</th> -->
									<!-- <th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Details</th> -->
							</tr>
						</thead>
					<tbody>
					<%int c1=1; String tcnt=""; %>
					<s:iterator value="accountList">
						<%if(c1<=9) {tcnt = "0"+c1;}else{tcnt = ""+c1+"";}%>
						
							<tr  id="<s:property value="invoiceid"/>">
						<%-- 	<s:if test="apmtChargeType == 1">
												<s:if test="payby==0 && chargesInvoiced==false && payAmount==0">
												<td><%=tcnt%> <input onclick="setTempChargeInvoiceAjax(this.id)"  type="checkbox" id="ch-<s:property value="invoiceid"/>"/></td>
											</s:if>
											<s:elseif test="payby==1 && chargesInvoiced==false">
												<td><%=tcnt%> <input onclick="setTempChargeInvoiceAjax(this.id)"  type="checkbox" id="ch-<s:property value="invoiceid"/>"/></td>	 
											 </s:elseif>
											<s:else>
												<td><%=tcnt%> </td>
											</s:else>
											
								</s:if>	
								
									
								<s:else> --%>
									<s:if test="selectedPayby!='All' && selectedLocation!='All'">
										<s:if test="payby==0 && chargesInvoiced==false && payAmount==0">
											<%-- <td><%=tcnt%> <input onclick="setTempChargeInvoiceAjax(this.id)"  type="checkbox" id="ch-<s:property value="invoiceid"/>" class="<%=chechdatahide%>"/></td> --%>
											<td><%=tcnt%> <input  type="checkbox" id="ch-<s:property value="invoiceid"/>" value="<s:property value="invoiceid"/>" class="selectClass"/></td>
										</s:if>
										<s:elseif test="payby==1 && chargesInvoiced==false">
											<%-- <td><%=tcnt%> <input onclick="setTempChargeInvoiceAjax(this.id)"  type="checkbox" id="ch-<s:property value="invoiceid"/>" class="<%=chechdatahide%>"/></td>	  --%>
											<td><%=tcnt%> <input  type="checkbox" id="ch-<s:property value="invoiceid"/>" value="<s:property value="invoiceid"/>" class="selectClass"/></td>	 
										</s:elseif>
										<s:else>
											<td><%=tcnt%> </td>
										</s:else>
									</s:if>
									<s:else>
										<td><%=tcnt%> </td>
									</s:else>
								<%-- </s:else> --%>
								
							
								
								
								
								<s:if test="chargeType=='Submit' && debitAmount !=0">
								<script type="text/javascript">
										$(document.getElementById('<s:property value="invoiceid"/>')).css('color', 'orange');
									</script>
								</s:if>
								
								<s:if test="chargeType=='Submit' && debitAmount ==0">
								<script type="text/javascript">
										$(document.getElementById('<s:property value="invoiceid"/>')).css('color', 'green');
									</script>
								</s:if>
								
								<s:if test="chargeType=='Charge' || chargeType=='Invoice' " >
								<script type="text/javascript">
										$(document.getElementById('<s:property value="invoiceid"/>')).css('color', '#626262;');
									</script>
								</s:if>
								
								<s:if test="chargesInvoiced==true" >
								<script type="text/javascript">
										$(document.getElementById('<s:property value="invoiceid"/>')).css('color', 'Brown');
									</script>
								</s:if>
								
								
								
								
								<td><s:property value="commencing"/></td>
								
								<s:if test="chargeType=='Invoice'">
									<td><s:property value="chargeType"/><br>(<s:property value="invoiceid"/>)<br>
									
										<a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><i class="fa fa-arrow-down"></i></a>
									</td>
									
								</s:if>
								<s:elseif test="chargeType=='Submit'">
									<td>Submitted <br> <s:property value="invoiceid"/>)<br>
									
										<a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><i class="fa fa-arrow-down"></i></a>
									</td>
								</s:elseif>
								<s:else>
									<s:if test="policyExcess == 1">
									<td><%=Constants.POLICYEXCESS%>(<s:property value="invoiceid"/>)<br>
									
										<a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><i class="fa fa-arrow-down"></i></a>
									</td>
									</s:if>
									<s:else>
									<td><s:property value="chargeType"/>(<s:property value="invoiceid"/>)<br>
									
										<a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><i class="fa fa-arrow-down"></i></a>
									</td>
									</s:else>
								</s:else>
								
								<td><s:property value="whoPay"/></td>
								<td><s:property value="locationName"/></td>
								
								
								<s:url action="paymentAccounts" id="paymentAccounts">
									<s:param name="payby" value="%{payby}"/>
									<s:param name="client" value="%{client}"/>
									<s:param name="numberOfChages" value="%{numberOfChages}"/>
									<s:param name="creditCharge" value="%{creditCharge}"/>
									<s:param name="invoiceid" value="%{invoiceid}"/>
									<s:param name="invoiceid" value="%{invoiceid}"/>
									<s:param name="practitionerName" value="%{practitionerName}"/>
									<s:param name="clientid" value="%{clientid}"/>
									<s:param name="debitamt" value="%{debitAmount}"/>
									<s:param name="creditamt" value="%{payAmount}"/>
								</s:url>
								<%-- <s:if test="debitAmount>0">
									<s:if test="chargeType=='Charge'">
										<s:url action="raiseinvoiceAccounts" id="raiseinvoiceAccounts">
											<s:param name="payby" value="%{payby}"/>
											<s:param name="client" value="%{client}"/>
											<s:param name="numberOfChages" value="%{numberOfChages}"/>
											<s:param name="creditCharge" value="%{creditCharge}"/>
											<s:param name="invoiceid" value="%{invoiceid}"/>
											<s:param name="practitionerName" value="%{practitionerName}"/>
											<s:param name="clientid" value="%{clientid}"/>
											<s:param name="debitamt" value="%{debitAmount}"/>
											<s:param name="creditamt" value="%{payAmount}"/>
										</s:url>
										<td><s:a href="%{raiseinvoiceAccounts}">Create Invoice</s:a></td>
									</s:if>
									<s:elseif test="chargeType=='Invoice'">
										<s:url action="raisesubmitinvoiceAccounts" id="raisesubmitinvoiceAccounts">
											<s:param name="payby" value="%{payby}"/>
											<s:param name="client" value="%{client}"/>
											<s:param name="numberOfChages" value="%{numberOfChages}"/>
											<s:param name="creditCharge" value="%{creditCharge}"/>
											<s:param name="invoiceid" value="%{invoiceid}"/>
											<s:param name="practitionerName" value="%{practitionerName}"/>
											<s:param name="clientid" value="%{clientid}"/>
											<s:param name="debitamt" value="%{debitAmount}"/>
											<s:param name="creditamt" value="%{payAmount}"/>
										</s:url>
										<td><s:a href="%{raisesubmitinvoiceAccounts}">Submit Invoice</s:a></td>
									</s:elseif>
									<s:else>
										<s:if test="payby==0 && chargesInvoiced==false">
											<td><s:a href="%{paymentAccounts}">Record Payment</s:a></td>
										</s:if>
										<s:else>
											<td>Submitted</td>
										</s:else>
										
									</s:else>
									
								</s:if>
								<s:else>
									<td>Paid</td>
								</s:else>
								 --%>
								
								<s:if test="apmtChargeType == 1">
								<td><s:property value="appointmentType"/><br>
									 
								</td>
								</s:if>
								<s:else>
								<td><s:property value="appointmentType"/><br>
									 Treatment: <s:property value="treatmentEpisodeName"/><br>
									 Practitioner: <s:property value="practitionerName"/>
								</td>
								</s:else>
								
								<%if(loginfo.getClinicid1().equals("dentalcl")){ %>
								
								<td colspan="1"><span><%=Constants.getCurrency(loginfo)%></span><input type="text" style="width: 80%;" class="form-control"  id="debit" name="debit" onchange="changedebitamt(this.value,'<s:property value ="invoiceid"/>')" value="<s:property value="totalAmountx"/>"></td>
								
								<%}else{ %>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></td>
								<%} %>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></td>
								<td class="text-danger"><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx"/></td>
								<s:url action="previewAccounts" id="previewAccounts">
									<s:param name="payby" value="%{payby}"/>
									<s:param name="invoiceid" value="%{invoiceid}"/>
									<s:param name="debitamt" value="%{debitAmount}"/>
									<s:param name="creditamt" value="%{payAmount}"/>
								</s:url>
								<%-- <td><s:a href="%{previewAccounts}" target="blank">Preview</s:a></td> --%>
								<%-- <td><a href="#" onclick="showTransactionPopup('<s:property value ="payby"/>','<s:property value ="invoiceid"/>','<s:property value ="debitAmount"/>','<s:property value ="payAmount"/>','<s:property value ="clientid"/>','<s:property value ="client"/>')">View Details</a></td> --%>
								
							</tr>
							
							       
									
									<tr id = "hiddenDetailsDiv<s:property value="invoiceid"/>" style="display: none">
											<td colspan="8" id = "hiddenDetailsDiv1<s:property value="invoiceid"/>"> </td>
									</tr> 
								
									
									
							
							
						
						
						<%c1 = c1+1;%>
						
					</s:iterator>
					 			<tr>
									<td colspan="5"></td>
									<td style="text-align: center;"><b>Total</b></td>
									<td><b><%=Constants.getCurrency(loginfo)%> <s:property value="debitTotalx"/></b></td>
									<td><b><%=Constants.getCurrency(loginfo)%> <s:property value="creditTotalx"/></b></td>
									<td class="text-danger"><b><%=Constants.getCurrency(loginfo)%> <s:property value="balanceTotalx"/></b></td>
									
								</tr>	
								</tbody>
							</table>
							</div>
						</div>
					</div>
					
					
				
		</s:if>
		
		

	<div class="">
		<div class="col-lg-12 col-md-12 text-right">
			<div class="form-inline">
				<div class="form-group">
					<s:form action="raisesubmitinvoiceCharges" id = "raisesubmitinvoiceCharges">
						<s:hidden name="initial" value="%{initial}"/>
						<s:hidden name="firstname" value="%{firstname}"/>
						<s:hidden name="lastname" value="%{lastname}"/>
						<s:hidden name="insuranceCompany" value="%{insuranceCompany}"/>
						<s:hidden name="hdnSelectAll" id="hdnSelectAll"/>
						<s:hidden name="payby" id="payby" value="%{payby}"/>
						<s:hidden name="location" id="location" value="%{location}"/>
						<s:hidden name="clientId" id="clientId" value="%{clientId}"/>
						<s:hidden id = "apmtChargeType" value="%{apmtChargeType}"></s:hidden>
						<s:hidden name="ipdidnew"></s:hidden>
						<s:hidden name="selectedCharges" id="selectedCharges1"></s:hidden>
						<%-- <s:hidden id="autoselect" name="autoselect"></s:hidden> --%>
						<input type="hidden" id="autoselect" name="autoselect" value='<s:property value="autoselect"/>'>
						<input type="button" class="btn btn-primary" value=" Create & Submit Invoice " onclick="raisesubmitinvoiceChargesChecked()">
					</s:form>
				</div>
				<div class="form-group">
					<s:form action="casdeskCharges" id = "casdeskCharges">
						<s:hidden name="initial" value="%{initial}"/>
						<s:hidden name="firstname" value="%{firstname}"/>
						<s:hidden name="lastname" value="%{lastname}"/>
						<s:hidden name="insuranceCompany" value="%{insuranceCompany}"/>
						<s:hidden name="hdnSelectAll" id="hdnSelectAll"/>
						<s:hidden name="payby" id="payby" value="%{payby}"/>
						<s:hidden name="location" id="location" value="%{location}"/>
						<s:hidden name="clientId" id="clientId" value="%{clientId}"/>
						<s:hidden name="ipdidnew"></s:hidden>
						<input type="hidden" id="autoselect" name="autoselect" value='<s:property value="autoselect"/>'>
						<s:hidden name="selectedCharges" id="selectedCharges"></s:hidden>
						<%-- <s:hidden name="selectedinvcharge" id="selectedinvcharge"></s:hidden> --%>
						<%if(loginfo.isCash_desk() || loginfo.getUserType()==2){ %>
							<input type="button" class="btn btn-primary" value=" Cash Desk " onclick="casdeskChargesChecked()">
						<%} %>
					</s:form>
				</div>
			</div>
		</div>	
	
	


<s:if test="accountList!=null">
	
<s:form action="Accounts" name="paginationForm" id="paginationForm" theme="simple">
		<div class="col-lg-12 col-md-12" style="margin-top: 15px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">Total:<b class="text-info"><s:property value="totalRecords" /></b></div>		 
						<%@ include file="/common/pages/pagination.jsp"%>
		</div>
				
				<s:hidden name="fromDate"></s:hidden>
				<s:hidden name="toDate"></s:hidden>
	</s:form>
</s:if>


<div class="">
				<div class="col-lg-12">
					<span style="color:red;">Note: To Create Invoice, First, You Select <%=loginfo.getPatientname_field() %>, Payed By and Referral Location and then Select Charges Checkbox next to the charges you want to include in the Invoice to be included in the Invoice by enabling the checkbox </span>
				</div>
		</div>

<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"> <%=loginfo.getPatientname_field() %> Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>	


<div id="transactionPopup" title="Transactions List" style="display: none">
	<table id="transactionList" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;" >
					<tr>
						<%int count1=1; %>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Sr.No</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Date</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Payment mode</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Amount</th>
						
					
						
					</tr>
					<s:if test="transactionList.size!=0">
						<s:iterator value="transactionList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									<td><%=count1%></td>
									<td><s:property  value="date" /></td>
									<td><s:property  value="paymentmode" /></td>
									<td><s:property  value="amount" /></td>
									
									
							</tr>
						<%count1 = count1+1; %>
						</s:iterator>
					</s:if>
					
							
				</table>
</div>	

											

											
										</div>
									</div>
								</div>
							</div>
						</div>

<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
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

		