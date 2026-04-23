<%@taglib uri="/struts-tags" prefix="s" %>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<link href="common/css/datePicker1.css" rel="stylesheet"/>
<script src="common/js/date.js" type="text/javascript"></script>
<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
<script src="common/js/CalenderPicture.js" type="text/javascript"></script>




<script type="text/javascript">
$(document).ready(function(){
	//document.getElementById('address').disabled = true
	$( "#invoiceDate" ).datepicker({
		 
		 dateFormat:'dd-mm-yy',
		 yearRange: yearrange,
		 minDate : '30/12/1880',
		 changeMonth: true,
	     changeYear: true
		 
			 
	 });
});
	function setInvoice(){
	
		if(document.getElementById('issued').checked==true){
			document.getElementById('invoiceDate').disabled = true
			//document.getElementById('address').disabled = true
		}else{
			document.getElementById('invoiceDate').disabled = '';
			//document.getElementById('address').disabled = '';
		}
	}
	
	
	
</script>

  <%String compApmtcurrentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String compApmttemp[] = compApmtcurrentDate.split(" ");
		String compApmttemp1[] = compApmttemp[0].split("-");
		String compApmtdate = compApmttemp1[0] + "-" + compApmttemp1[1] + "-" + compApmttemp1[2];
		
	%>

	<s:form action="previewAccounts" theme="simple" id="previewfrm" target="blank">
	
		
		<table width="100%" cellspacing="0" cellpadding="0">
			
			<tr>
				
				<td>
					<s:hidden name= "compApmtInvoiceId" id = "compApmtInvoiceId"/>
					



					<input type="hidden" id = "payby" name= "payby">
					<input type="hidden" id = "invoiceid" name= "invoiceid">
					<input type="hidden" id = "debitamt" name= "debitamt">
					<input type="hidden" id = "creditamt" name= "creditamt">
					<input type="hidden" id = "discountany" name= "discountany">
					
					<span>Client:</span><s:textfield  name="compApmtClient" id="compApmtClient" readonly="true" cssClass="text ui-widget-content ui-corner-all"/> 
				</td>
				<td>
					Total: <s:textfield  name="creditCharge" id="creditCharge" cssStyle="width:40%"  readonly="true" cssClass="text ui-widget-content ui-corner-all"/>
				</td>
				<td>
					Date: <input type="text" name="invoiceDate" id="invoiceDate" style="width:40%" class="text ui-widget-content ui-corner-all" value="<%=compApmtdate%>"/>
				</td>
				
			</tr>
		</table>
		
		<div><hr></div>
		<table id="selfChargeDesk1"  class="my-table"  >
			
				<tr>
					<th>Id</th>
					<th>Appointment Type</th>
					<th>Charge</th>
				</tr>
			</table>
			
			<div><hr></div>
				 <table width="40%" style="border: 1px solid #e69623;  padding: 1em; margin-left: 28px; " >
			
			
			<tr>
				<td>Payment Mode</td>	
				<td align="center">:</td>
				<td>
					<select name="howpaid" id="howpaid" style=" margin-bottom:12px; width:86%; padding: .4em;" class="text ui-widget-content ui-corner-all">
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
				</td>
			</tr>
			<tr>
				<td>Payment Amount</td>	
				<td align="center">:</td>
				<td> <s:textfield name="totalamount" theme="simple" id="totalamount" readonly="true" value="%{creditCharge}" cssStyle="width:86%" cssClass="text ui-widget-content ui-corner-all"/></td>
			</tr>
			<tr>
				<td>Discount(if any)</td>	
				<td align="center">:</td>
				<td><s:textfield name="discount" theme="simple" id="discount" cssStyle="width:86%" cssClass="text ui-widget-content ui-corner-all" onchange="setAmountDue()"/></td>
			</tr>
			
			<tr>
				<td>Amount Due</td>	
				<td align="center">:</td>
				<td><s:textfield name="payAmount" theme="simple" id="payAmount" cssStyle="width:86%" cssClass="text ui-widget-content ui-corner-all"/></td>
			</tr>
			
		</table>
	
		
		
		
		
		</div>
		<!-- <div><hr></div>
		<br>
		<div><b>Preview or pirnt your invoices</b><input type="submit" value="Print/Preview"></div> -->
	</s:form>
	

