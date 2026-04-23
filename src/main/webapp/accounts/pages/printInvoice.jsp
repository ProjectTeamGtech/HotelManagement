<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<%@page import="com.apm.main.common.constants.Constants"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Accounts</a></li>
			<li><a href= "/APM/Accounts">Process Charges</a> </li>
			<li><a href = "/APM/raisesubmitinvoiceCharges">Create & Submit Invoice</a></li>
			<li class="active">Print</li>
		</ol>
	</div>
</div>	



<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
		<div style="font-size: 20px; font-weight: bold;">ANKERSIDE PHYSIOTHERAPY CLINIC</div>
		<div style="font-size: 18px; font-weight: bold;">B. KALIRAY MCSP, SRP </div>
		<div style="font-size: 18px; font-weight: bold;">3 Bond Street, Bond Gate, Nuneaton. CV11 4DA </div>
		<div style="font-size: 16px; font-weight: normal;">Tel/Fax: 024 7664 1214 </div>
		<div style="font-size: 16px; font-weight: normal;">E: Ankerside.physio@xlninternet.co.uk W: www.ankersidephysio.co.uk </div>
		<br><br>
		
		<div style="font-size: 14px; font-weight: normal; padding-left: 20px;"><s:property value="client"/> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<center>	<font style="font-size: 20px; font-weight: bold; ">Invoice</font></center>
		</div>
		<br><br><br><br>
		
		
		<br>
		
		
			<table cellpadding="0" cellspacing="0" class="prew-table" style="width: 100%;  border="0">
			
			<tr>
				<th>Invoice No: 0000<s:property value="invoiceid"/></th>
				<th>Date: <s:property value="commencing"/></th>
				<th>Account No : 0000<s:property value="clientId"/></th>
				<th>Unit Cost</th>
				<th>Qty</th>
				<th>Total</th>
				
			</tr>
			<tr>
				<td colspan="6">
					<table width="50%">
						<tr>
							<td style="font-weight: bold;">Client</td>
							<td><s:property value="client"/></td>
						</tr>
						<tr>
							<td style="font-weight: bold;">Client Address</td>
							<td><s:property value="address"/></td>
						</tr>
						<tr>
							<td style="font-weight: bold;">D.O.B</td>
							<td><s:property value="dob"/></td>
						</tr>
						<tr>
							<td style="font-weight: bold;">Contact No.</td>
							<td><s:property value="mobno"/></td>
						</tr>
						
						<tr>
							<td style="font-weight: bold;">Email ID</td>
							<td><s:property value="email"/></td>
						</tr>
						
						<s:if test="payby=='Third Party'">
							<tr>
								<td style="font-weight: bold;">Policy No.</td>
								<td><s:property value="policyNo"/></td>
							</tr>
						</s:if>
					</table>
				</td>
			</tr>
			
			<s:iterator value="assesmentList">
			<tr>
				<td><s:property value="commencing"/></td>
				<td><s:property value="appointmentType"/></td>
				<td></td>
				<td><%=Constants.getCurrency(loginfo)%><s:property value="charges"/></td>
				<td>1.00</td>
				<td><%=Constants.getCurrency(loginfo)%><s:property value="charges"/></td>
			</tr>
			<tr>
				<td>Practitioner</td>
				<td><s:property value="practitionerName"/></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
			<td colspan="6"><hr></td>
			</tr>
			</s:iterator>
			
			
			<tr>
				<td colspan="6"><hr></td>
			</tr>
			<tr>
				<td colspan="6" align="right" style="font-weight: bold; padding-right: 50px; ">
					Total
				</td>
			</tr>
			<tr>
				<td colspan="6" align="right" style="font-weight: bold; padding-right: 50px; "><%=Constants.getCurrency(loginfo)%><s:property value="totalAmount"/></td>
			</tr>
			
			
			
			
			<tr>
				<td colspan="4" align="right" style="font-weight: bold; ">
					Total Payment Received
				</td>
				<td colspan="6" align="right" style="font-weight: bold; padding-right: 50px; "><%=Constants.getCurrency(loginfo)%><s:property value="creditAmt"/></td>
			</tr>
			
			
			
			<tr>
				<td colspan="4" align="right" style="font-weight: bold; ">
					Balance Outstanding
				</td>
				<td colspan="6" align="right" style="font-weight: bold; padding-right: 50px; "><%=Constants.getCurrency(loginfo)%><s:property value="debitAmounnt"/></td>
			</tr>
		</table>		
		
		<div>
		
		</div>
	</div>
</div>



</br></br>