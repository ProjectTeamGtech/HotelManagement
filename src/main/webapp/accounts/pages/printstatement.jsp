<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<h2>ANKERSIDE PHYSIOTHERAPY CLINIC</h2>
			<h3>B. KALIRAY MCSP, SRP</h3>
			<h4>3 Bond Street, Bond Gate, Nuneaton. CV11 4DA</h4>
			<h5>Tel/Fax: 024 7664 1214</h5>
			<h6>E: Ankerside.physio@xlninternet.co.uk W:
				www.ankersidephysio.co.uk</h6>
			<br> <br>

			<table class="table table-bordered">
				<tr>
					<td align="center" colspan="2"><h4>Statement Of Account</h4></td>
				</tr>
				<tr>
					<td><label>Mr Steven Guise</label><br> <label>25
							Melbourne Close</label><br> <label>NUNEATON</label><br> <label>CV11
							4RX</label><br></td>
					<td>
						<table class="table table-bordered">
							<tr>
								<td><label>Account No</label></td>
								<td>02796554023</td>

							</tr>
							<tr>
								<td><label>Date</label></td>
								<td>14/07/2014</td>

							</tr>
							<tr>
								<td><label>Page</label></td>
								<td>1</td>

							</tr>
						</table>
					</td>
				</tr>
			</table>

			<br> <br>

			<table class="table table-bordered">
				<thead>
					<tr class="text-center">
						<th>Date</th>
						<th>Transaction</th>
						<th>Payee</th>
						<th>Status</th>
						<th>Credit</th>
						<th>Debit</th>
						<th>Discount</th>
						<th>Balance</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="chargeProcessingList.size!=0">
						<s:iterator value="chargeProcessingList" status="rowstatus">
							<tr id="<s:property  value="id" />">
								<%-- <s:if test="#rowstatus.even == true">
								<tr class="ac_odd" id="<s:property  value="id" />">
							</s:if> --%>
								<td><s:property value="date" /></td>
								<td>Invoice <br> (0000<s:property value="id" />)<br>

								</td>
								<td><s:property value="payby" /></td>
								<s:if test="balance == 0">
									<td>Paid</td>
								</s:if>

								<s:else>
									<s:url action="paymentProcessingAccount"
										id="paymentProcessingAccount">
										<s:param name="payby" value="%{payby}" />
										<s:param name="numberOfChages" value="%{numberOfChages}" />
										<s:param name="creditCharge" value="%{creditCharge}" />
										<s:param name="invoiceid" value="%{id}" />
										<s:param name="debitamt" value="%{debitAmount}" />
										<s:param name="balance" value="%{balance}" />

										<s:param name="clientId" value="%{clientId}" />

									</s:url>
									<td><s:a href="%{paymentProcessingAccount}">Record Payment</s:a></td>

								</s:else>
								<td><s:property value="creditCharge" /></td>
								<td><s:property value="debitAmountx" /></td>
								<td><s:property value="discountx" /></td>
								
								<td><s:property value="balancex" /></td>


							</tr>

						</s:iterator>

					</s:if>
				</tbody>

			</table>

			<br> <br>

			<div class="row">
				<div class="col-lg-12 text-right">
					<div class="col-lg-9 col-md-8 col-sm-6 col-xs-12">
					Balance Due
					</div>
					<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
					<input class="form-control" type="text" value="<%=Constants.getCurrency(loginfo)%>0.00">
						
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

