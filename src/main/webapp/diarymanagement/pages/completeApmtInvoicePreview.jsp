<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="accounts/js/accounts.js"></script>

<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
		<div style="font-size: 20px; font-weight: bold;">You Enter Your Details Here</div>
		<div style="font-size: 18px; font-weight: bold;">Use the LeaterHead Setup Option </div>
		<div style="font-size: 18px; font-weight: bold;">of APM Tools Menu </div>
		<div style="font-size: 16px; font-weight: normal;">Your name and address can be entered </div>
		<div style="font-size: 16px; font-weight: normal;">along with your contact details </div>
		<br><br>
		
		<div style="font-size: 14px; font-weight: normal; padding-left: 20px;"><s:property value="client"/> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font style="font-size: 20px; font-weight: bold; padding-left: 500px;">Receipt</font>
		</div>
		<br><br><br><br>
		
		<div style="font-size: 11px; font-weight: normal; padding-left:550px;">
			Unit Cost &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 Qty &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 Total
		</div>
		<br>
		<div style="font-size: 14px; font-weight: normal; padding-left: 20px; background-color: rgb(192, 182, 182)">
			<font style="font-weight: bold;">Invoice No</font> 0000<s:property value="invoiceid"/>
			&nbsp;&nbsp;&nbsp;
			<font style="font-weight: bold;">Date</font> <s:property value="invoiceDate"/>
			&nbsp;&nbsp;&nbsp;
			<font style="font-weight: normal;">Account No : 50109</font>
		</div>
		
		<table width="80%" cellpadding="0" cellspacing="0" style="font-size: 11px; padding-left: 20px;">
			<col width="3%">
			<col width="10%">
			<col width="2%">
			<col width="2%">
			<col width="2%">
			
			<s:iterator value="invoiceAssesmentList">
			<tr>
				<td><s:property value="commencing"/></td>
				<td><s:property value="appointmentType"/></td>
				<td>$<s:property value="charges"/></td>
				<td>1.00</td>
				<td>$<s:property value="charges"/></td>
			</tr>
			<tr>
				<td>Practitioner</td>
				<td><s:property value="practitionerName"/></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			</s:iterator>
			
			
			
			
			<tr>
				<td colspan="5" align="right" style="font-weight: bold; padding-right: 100px;">
					Total
				</td>
			</tr>
			<tr style="height: 8px;"></tr>
			<tr>
				<td colspan="5" align="right" style="font-weight: bold; padding-right: 100px;">
					$<s:property value="creditCharge"/>
				</td>
			</tr>
			<tr style="height: 8px;"></tr>
			<tr>
				<td colspan="3" align="right" style="font-weight: bold; ">
					Total Payment Recieved
				</td>
				<td colspan="4" align="right" style="font-weight: bold; padding-right: 100px; ">$<s:property value="paidAmount"/>.00</td>
			</tr>
			<tr style="height: 8px;"></tr>
			
			<tr>
				<td colspan="3" align="right" style="font-weight: bold; ">
					Balance Outstanding
				</td>
				<td colspan="4" align="right" style="font-weight: bold; padding-right: 100px; ">$<s:property value="debitAmounnt"/>.00</td>
			</tr>
		</table>
		
		
		
	
	</div>
</div>