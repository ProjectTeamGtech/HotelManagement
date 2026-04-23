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
			<li><a href = "/APM/raisesubmitinvoiceCharges">Create & Submit Invoice</a></li>
			<li class="active">Print</li>
		</ol>
	</div>
</div>	

<h2 class="title" style="margin-left: 46px">Submit Invoice for <s:property value="client"/></h2><br><br>

<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
	
	
		
			<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				
				
			%>							       
		<s:form action="submitinvoiceAccounts" theme="simple" id="raiseinvoicefrm">
			<s:hidden name="client" id="client"/>
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payby" id="payby"/>
			<s:hidden name="creditCharge" id="creditCharge"/>
			<s:hidden name="totalassesment" id="totalassesment"/>
			<s:hidden name="practitionerId" id="practitionerId"/>
			<s:hidden name="practitionerName" id="practitionerName"/>
			<s:hidden name="invoiceid" id="invoiceid"/>
			
									        
			<div class="menu" style="margin-top: -27px">
			<input type="text" id="invoiceDate" name="invoiceDate" class="text ui-widget-content ui-corner-all"  value="<%=temp[0] %>" ></input></div>
			
			<br>
			<div style="font-weight: bold; font-size: 12px;">Invoice can be raise as below</div>
			<br>
			<table width="100%" cellpadding="0" cellspacing="0" class="my-table">
				<tr>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Invoice No.</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">To</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Invoicee</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">No. of Charges</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Total</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Location</th>
					
				</tr>
				<tr>
					<td>0000<s:property value="invoiceid"/></td>
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
				
			</table>
			<br>
			<div style="font-weight: bold; font-size: 12px;">Following Charges can be included in selected invoices</div>
			<hr/>
				<br>
					<table width="100%" id="assementtable" cellpadding="0" cellspacing="0" class="my-table">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Select</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Date</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Client</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Total</th>
					</tr>
					<s:iterator value="assesmentList">
		
						<tr>
							<td><input disabled="disabled"  type="checkbox" name="chid" id="<s:property value="id"/>" value="<s:property value="id"/>" checked="checked" onclick="setTotalCharges('<s:property value="charges"/>','<s:property value="id"/>');"></td>
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
					
				</table>
				
				
				<br>
				
				<div>
					<s:textarea name="submitInvoiceNotes" id="submitInvoiceNotes" rows="5" cols="40"></s:textarea>
					
				</div>
				<br>
				<div>
				
					<input type="submit" value=" Submit Invoice " >
				</div>
			
		
				
				
			</s:form>
			
			
	</div>
</div>