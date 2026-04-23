<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="diarymanagement/js/paynow.js"></script>


<h2 class="title" style="margin-left: 46px">Raise Invoice Against Charges for <s:property value="firstname"/></h2><br><br>

<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
	
	
		
			<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				
				
			%>							       
		<s:form action="createinvoiceCharges" theme="simple" id="raiseinvoicefrm">
			<s:hidden name="client" id="client"/>
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payby" id="payby"/>
			<s:hidden name="creditCharge" id="creditCharge"/>
			<s:hidden name="totalassesment" id="totalassesment"/>
			<s:hidden name="practitionerId" id="practitionerId"/>
			<s:hidden name="practitionerName" id="practitionerName"/>
			<s:hidden name="invoiceid" id="invoiceid"/>
			<s:hidden name="debitTotal" id="hdndebittotal" value="%{debitTotal}"/>
			
									        
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
					<td>Not Invoiced</td>
					<s:if test="payby == 'Client'">
						<td>Client</td>
						<td><s:property value="firstname"/></td>
					</s:if>
					<s:else>
						<td>Third Party</td>
						<td><s:property value="insuranceCompany"/></td>
					</s:else>
					
					
					<td><s:property value="numberOfChages"/></td>
					<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotal"/></td>
					<td><s:property value="location"/></td>
				</tr>
				
			</table>
			<br>
			<div style="font-weight: bold; font-size: 12px;">Following Charges can be included in selected invoices</div>
			<hr/>
				<br>

				
				
				
				
				
				
				
					<table id="assementtable" cellpadding="0"  cellspacing="0" class="my-table" style="width: 100%;margin-left: 29px">
					<col width="10%">
					<col width="12%">
					<col width="8%">
					<col width="30%">
					<col width="8%">
					<col width="8%">
					<col width="8%">
					
						<tr>
							<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Sr.No.</th>
							<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Date</th>
							<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Transaction</th>
							<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0; text-align: center;">Description</th>
							<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Debit</th>
							<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Credit</th>
							<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Balance</th>
						
						</tr>
						
							<%int c1=1; String tcnt=""; %>
						<s:iterator value="accountList">
							<%if(c1<=9) {tcnt = "0"+c1;}else{tcnt = ""+c1+"";}%>
							<tr>
								<td><%=tcnt%> <input type="checkbox" name="chid" id="<s:property value="invoiceid"/>" value="<s:property value="invoiceid"/>" checked="checked" onclick="setTotalCharges('<s:property value="debitAmount"/>','<s:property value="invoiceid"/>');"></td>
								<td><s:property value="commencing"/></td>
								<s:if test="chargeType=='Invoice'">
									<td><s:property value="chargeType"/><br>
									
										<a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><img style="margin-left: 20%;" width="20" height="15" align="middle" src="common/images/Arrows-Down-icon.png"/></a>
									</td>
								</s:if>
								<s:else>
									<td><s:property value="chargeType"/><br>
									
										<a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><img style="margin-left: 20%;" width="20" height="15" align="middle" src="common/images/Arrows-Down-icon.png"/></a>
									</td>
								</s:else>
								<td><s:property value="appointmentType"/><br>
									 Treatment: <s:property value="treatmentEpisodeName"/><br>
									 Practitioner: <s:property value="practitionerName"/>
								</td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalAmount"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="payAmount"/></td>
								<td style="color: red;"><%=Constants.getCurrency(loginfo)%><s:property value="debitAmount"/></td>
							</tr>
							<tr id = "hiddenDetailsDiv<s:property value="invoiceid"/>" style="display: none">
									<td colspan="7" id = "hiddenDetailsDiv1<s:property value="invoiceid"/>"> </td>
							</tr> 	
							<%c1 = c1+1;%>
						</s:iterator>
					
							<tr>
								<td colspan="3"></td>
								<td style="text-align: center;"><b>Total</b></td>
								<td id="totaltdid"><b><%=Constants.getCurrency(loginfo)%> <s:property value="debitTotal"/></b></td>
								<td><b><%=Constants.getCurrency(loginfo)%> <s:property value="creditTotal"/></b></td>
								<td id="balancetotalid" style="color: red"><b><%=Constants.getCurrency(loginfo)%> <s:property value="balanceTotal"/></b></td>
								
								<input type="hidden" name="hdntotal" id="hdntotal" value="<s:property value="debitTotal"/>"/>
							</tr>
							
							
					
					</table>
				
				
				<br>
				<div><input type="button" value=" Raise Invoice " onclick="raiseInvoice()"></div>
			
		
				
				
			</s:form>
			
			
	</div>
</div>