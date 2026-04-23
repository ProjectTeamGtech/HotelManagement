<%@taglib uri="/struts-tags" prefix="s" %>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>

<link href="common/css/datePicker1.css" rel="stylesheet"/>
<script src="common/js/date.js" type="text/javascript"></script>
<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
<script src="common/js/CalenderPicture.js" type="text/javascript"></script>

<script type="text/javascript" src="diarymanagement/js/paynow.js"></script>



<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
	
		<h2 class="title" style="margin-left: 46px">Raise Invoice for <s:property value="user"/></h2><br><br>
		
			<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				
				
			%>							       
		<s:form action="invoiceCompleteApmt" theme="simple">
			<s:hidden name="user" id="user"/>
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payBuy" id="payBuy"/>
			<s:hidden name="chargeTotal" id="chargeTotal"/>
			<s:hidden name="totalassesment" id="totalassesment"/>
			<s:hidden name="practitionerId" id="practitionerId"/>
			<s:hidden name="practitionerName" id="practitionerName"/>
									        
			<div><input id="invoiceDate" class="text ui-widget-content ui-corner-all" type="text" value="<%=temp[0] %>" name="invoiceDate"></input></div>
			<br>
			<div style="font-weight: bold; font-size: 12px;">Invoice can be raise as below</div>
			<br>
			<table width="100%" cellpadding="0" cellspacing="0" class="my-table">
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="15%"/>
				<col width="10%"/>
				<col width="10%"/>
				<tr>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Include</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">To</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Invoicee</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">No. of Charges</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Total</th>
					<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Location</th>
					
				</tr>
				<tr>
					<td>1</td>
					<s:if test="payBuy == 0">
						<td>Client</td>
						<td><s:property value="user"/></td>
					</s:if>
					<s:else>
						<td>Third Party</td>
						<td><s:property value="insuranceCompany"/></td>
					</s:else>
					
					
					<td><s:property value="numberOfChages"/></td>
					<td>$<s:property value="chargeTotal"/></td>
					<td></td>
				</tr>
				
			</table>
			<br>
			<div style="font-weight: bold; font-size: 12px;">Following Charges can be included in the Invoice</div>
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
							<td><input type="checkbox" name="chid" id="<s:property value="id"/>" value="<s:property value="id"/>" checked="checked"></td>
							<td><s:property value="commencing"/></td>
							<td><s:property value="user"/></td>
							<td><s:property value="apmtType"/></td>
							<td>$<s:property value="charges"/></td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="4"><b>Total</b></td>
						<td>$<s:property value="chargeTotal"/></td>
					</tr>
					
				</table>
			
			<br>
				<div style="font-size: 12px;">Other details for this charge (Hard coded need to work)</div>
				<div style="font-size: 12px;">Practitioner</div>
				<div><input type="text" name="practName" id="practName" value="<s:property value="practitionerName"/>"></div>
				<table class="my-table" style="font-size: 12px;" >
				
					<tr>
						<th>Charge Type</th>
						<th>Treatment</th>
					</tr>
					<tr>
						<td>Net</td>
						<td>$20</td>
					</tr>
					<tr>
						<td>Vat</td>
						<td>$0</td>
					</tr>
					<tr>
						<td>TOTAL</td>
						<td>$20</td>
					</tr>
				</table>
				
				<div><input type="submit" value=" Create Invoice ">
				
				</div>
			</s:form>
			
			
			
	</div>
</div>