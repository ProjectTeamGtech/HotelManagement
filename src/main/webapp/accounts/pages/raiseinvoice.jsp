<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>


<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript" src="diarymanagement/js/paynow.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Accounts</a></li>
			<li><a href= "/APM/inputAccounts">Process Charges</a> </li>
			<li class="active"> Create & Submit Invoice</li>
		</ol>
	</div>
</div>	
<h2>Raise Invoice for <s:property value="client"/></h2><br><br>


	
	
		
			<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				
				
			%>							       
		<s:form action="createinvoiceAccounts" theme="simple" id="raiseinvoicefrm">
			<s:hidden name="client" id="client"/>
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payby" id="payby"/>
			<s:hidden name="creditCharge" id="creditCharge"/>
			<s:hidden name="totalassesment" id="totalassesment"/>
			<s:hidden name="practitionerId" id="practitionerId"/>
			<s:hidden name="practitionerName" id="practitionerName"/>
			<s:hidden name="invoiceid" id="invoiceid"/>
			<s:hidden name="debitTotal" id="hdndebittotal" value="%{creditCharge}"/>
			
									        
			<div>
			<input type="text" id="invoiceDate" name="invoiceDate" class="text ui-widget-content ui-corner-all"  value="<%=temp[0] %>" ></input></div>
			
			<br>
			<div style="font-weight: bold; font-size: 12px;">Invoice can be raise as below</div>
			<br>
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-hover table-condensed table-bordered">
					<thead>
				<tr>
					<th>Invoice No.</th>
					<th>To</th>
					<th>Invoicee</th>
					<th>No. of Charges</th>
					<th>Total</th>
					<th>Location</th>
					
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>Not Invoiced</td>
					<s:if test="payby == 0">
						<td>Client</td>
						<td><s:property value="client"/></td>
					</s:if>
					<s:else>
						<td>Third Party</td>
						<td><s:property value="insuranceCompany"/></td>
					</s:else>
					
					
					<td><s:property value="numberOfChages"/></td>
					<td><%=Constants.getCurrency(loginfo)%><s:property value="creditCharge"/></td>
					<td></td>
				</tr>
				</tbody>
			</table>
			</div>
			</div>
			</div>
			<br>
			<div style="font-weight: bold; font-size: 12px;">Following Charges can be included in selected invoices</div>
			<hr/>
				<br>
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-hover table-condensed table-bordered">
					<thead>
					<tr>
						<th>Select</th>
						<th>Date</th>
						<th>Client</th>
						<th>Description</th>
						<th>Total</th>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="assesmentList">
		
						<tr>
							<td><input type="checkbox" name="chid" id="<s:property value="id"/>" value="<s:property value="id"/>" checked="checked" onclick="setTotalCharges('<s:property value="charges"/>','<s:property value="id"/>');"></td>
							<td><s:property value="commencing"/></td>
							<td><s:property value="clientName"/></td>
							<td><s:property value="appointmentType"/></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="charges"/></td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="4"><b>Total</b></td>
						<td id="totaltdid"><%=Constants.getCurrency(loginfo)%><s:property value="creditCharge"/></td>
						<input type="hidden" name="hdntotal" id="hdntotal" value="<s:property value="creditCharge"/>"/>
					</tr>
					</tbody>
				</table>
				</div>
				</div>
				</div>
				
				
				<br>
				
				<div>
					 <div>Note :</div> <s:textarea name="submitInvoiceNotes" id="submitInvoiceNotes" rows="5" cols="40"></s:textarea>
					
				</div>
				<br>
				
				<div><input type="button" value=" Raise Invoice " onclick="raiseInvoice()"></div>
			
		
				
				
			</s:form>
			
			
	