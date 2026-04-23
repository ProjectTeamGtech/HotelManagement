<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript" src="diarymanagement/js/paynow.js"></script>


<h4>Record Payment for <s:property value="client"/></h4><br><br>


	
	
		
			<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				
				
			%>							       
		<s:form action="payAccounts" theme="simple" id="paymentfrm">
			<s:hidden name="client" id="client"/>
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payby" id="payby"/>
			<s:hidden name="creditCharge" id="creditCharge"/>
			<s:hidden name="totalassesment" id="totalassesment"/>
			<s:hidden name="practitionerId" id="practitionerId"/>
			<s:hidden name="practitionerName" id="practitionerName"/>
			<s:hidden name="invoiceid" id="invoiceid"/>
			
			<div class = "row">						        
			<div class="col-lg-3">
			<input type="text" id="invoiceDate" name="invoiceDate"  class="form-control"  value="<%=temp[0] %>" ></input></div>
			</div>
			
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
					<td><s:property value="invoiceid"/></td>
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
				<table class="table table-hover table-condensed table-bordered " id="assementtable">
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
							<td><input disabled="disabled" type="checkbox" name="chid" id="<s:property value="id"/>" value="<s:property value="id"/>" checked="checked"></td>
							<td><s:property value="commencing"/></td>
							<td><s:property value="clientName"/></td>
							<td><s:property value="appointmentType"/></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="charges"/></td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="4"><b>Total</b></td>
						<td><%=Constants.getCurrency(loginfo)%><s:property value="creditCharge"/></td>
					</tr>
					</tbody>
				</table>
			</div>
			</div>
			</div>
			<br>
			<div style="font-weight: bold; font-size: 12px;">Pay this Invoice ( Debit: &nbsp;&nbsp;&nbsp;<%=Constants.getCurrency(loginfo) %><s:property value="debitAmt"/>, &nbsp;&nbsp;&nbsp; Credit: &nbsp;&nbsp;&nbsp;<%=Constants.getCurrency(loginfo) %><s:property value="creditAmt"/>)</div>
			<hr/>
			<br>
			
			
	<div class="row">
		<div class="col-lg-3">
			
			
				<label>Payment Mode</label><label class="text-danger">*</label> 
				<select name="howpaid" id="howpaid" class="form-control">
					<option value="0">Select</option>
					<option value="BACS">BACS</option>
					<option value="Cheque">Cheque</option>
					<option value="C/Card">C/Card</option>
					<option value="Cash">Cash</option>
					<option value="D/Card">D/Card</option>
					<option value="D/D">D/D</option>
					<option value="Other">Other</option>
					<option value="S/O">S/O</option>
				</select>
				
				
				<label>Payment Amount <%=Constants.getCurrency(loginfo)%></label>
				 <s:textfield name="payAmount" id="payAmount"  value="%{debitAmt}" cssStyle="width:10%" cssClass="form-control"/>
		</div>
		</div>		
				
	</br>		
				<input type="button" class="btn btn-primary" value=" Pay Now " onclick="givPayment()">
		
	
			
			
			
			
			
				<%-- <div style="font-size: 12px;">Other details for this charge(Hard code need to work) </div>
				<div style="font-size: 12px;">Practitioner</div>
				<div><input type="text" name="practName" id="practName" class="text ui-widget-content ui-corner-all" value="<s:property value="practitionerName"/>" readonly="readonly"></div>
				<table class="my-table" style="font-size: 12px;" width="25%">
					<tr>
						<th>Charge Type</th>
						<th>Treatment</th>
					</tr>
					<tr>
						<td>Net</td>
						<td><%=Constants.getCurrency(loginfo)%>20</td>
					</tr>
					<tr>
						<td>Vat</td>
						<td><%=Constants.getCurrency(loginfo)%>0</td>
					</tr>
					<tr>
						<td>TOTAL</td>
						<td><%=Constants.getCurrency(loginfo)%>20</td>
					</tr>
				</table> --%>
				
				
				<s:token/>
				
			</s:form>
			
			
	</div>
</div>