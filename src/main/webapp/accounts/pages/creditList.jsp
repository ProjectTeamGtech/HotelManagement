<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript" src="accounts/js/accounts.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>View Credit Account</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<span class="error"><s:actionerror id="server_side_error"/></span>
		
		<s:form action="creditListAdditional"  theme="simple" id="category_form"> 
			<s:hidden name="hdnSelectedID" id="hdnSelectedID"/>
			<s:hidden name="hdnSelectAll" id="hdnSelectAll"/>
			
		
	
	<div class="col-lg-12 col-md-12 topback2">
		<div class="col-lg-3 col-md-3">
			<label><%=loginfo.getPatientname_field() %></label>
			<s:textfield name="client" id="client"  readonly="true"
				cssClass="form-control" onchange = "setData()" onclick="showPopUp();"  data-toggle="modal" data-target="#clientSearch"/>
			<s:hidden name="clientId" id="clientId"></s:hidden>
		</div>
		
		<div class="col-lg-1 col-md-1">
			<label class="width-full" style="font-weight: normal">.</label> <input
				type="submit" value="Go" class="btn btn-primary">
				
		</div>
		<div class="col-lg-3 col-md-3">
		<a  href="creditAdditional?clientid=<s:property value="clientId" />"> <label class="width-full" style="font-weight: normal">.</label> <input
				type="button" value="Advance & Refund" class="btn btn-primary"></a>
		</div>
		<div class="col-lg-3 col-md-3">
		</div>
		<div class="col-lg-2 col-md-2">
			<label class="width-full" style="font-weight: normal">.</label>
			<a href="#" class="btn btn-primary" onclick="openRequestedRefundList()">Requested Refund</a>
		</div>
	
	</div>
	</s:form>
	
	
	
	
	<div class="row" style="z-index: 99999">
		<div class="col-lg-12">
			<s:actionmessage cssClass="messageDiv" />
			<div>
				<table class="table-bordered table-hover table-condensed width-full">
					<thead>
						<tr>
							<!-- <th>Date</th> -->
							<th title="Sort by Date" class="sortable <s:if test="#attr.pagination.sortColumn.equals('date')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
							<a href="#" onclick="fnPagination(6,'date');" style="color:white;">Date</a></th>
							<th>Payee</th>
							<th>Payment Mode</th>
							<th>Credit</th>
							<th>Debit</th>
							<th>Balance</th>
							<th>Delete</th>
							<th>Receipt</th>
							
						</tr>
					</thead>
					<tbody>
						<s:if test="creditList.size!=0">
							<s:iterator value="creditList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property value="commencing" /></td>
									<td><s:property value="payby" /></td>
									<s:if test="paymentmode=='prepayment'">
										<td>Credit Balance</td>
									</s:if>
									<s:else>
										<td><s:property value="paymentmode" />&nbsp;&nbsp;&nbsp;&nbsp;
										<s:if test="debitTotalx==0">
										<s:if test="refinvoiceid==0">
										<s:if test="todays">
										<a href="#" onclick="changepaymodeinadv(<s:property value="id"/>,'<s:property value="paymentmode" />')"><i class="fa fa-edit"></i></a>
										</s:if>
										</s:if>
										</s:if>
										</td>
									 </s:else>
									
									<td>
									<s:if test="invoiceid>0"><%=Constants.getCurrency(loginfo)%><s:property value="charges" />(Inv No.<s:property value="invoiceid"/> : <s:property value="cancelNotes"/>)
									</s:if>
									<s:else>
									<%=Constants.getCurrency(loginfo)%><s:property value="charges" />&nbsp;&nbsp;&nbsp;&nbsp;
									<%if(loginfo.isSaimed()){ %>
									<a href="#" onclick="changecreditinadv(<s:property value="id"/>,'<s:property value="charges" />','<s:property value="clientId" />')"><i class="fa fa-edit"></i></a>
									<%} %>
									</s:else>
									</td>
									<s:if test="advref==0">
										<s:if test="debitTotalx==0">
											<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" /> </td>
										</s:if>
										<s:else>
											<s:if test="refinvoiceid==0">
												<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" /></td>
											</s:if>
											<s:else>
												<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" />(Refund)</td>
											</s:else>
										</s:else>
									</s:if>
									<s:else>
										<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" /> (Refund)
											<s:if test="manualinvoiceid==0">
												
											</s:if>
											<s:else>
												[Invoice:<s:property value="manualinvoiceid"/>]
											</s:else>
										</td>
									</s:else>
									<td><%=Constants.getCurrency(loginfo)%><s:property value="balancex" /></td>
									<td><a href="#"><i class="fa fa-trash"></i></a></td>
									<td>
										<s:if test="debitTotalx==0">
											<a href="advprintCharges?id=<s:property  value="id" />">Print </a>
										</s:if>
										<s:else>
											<a href="refundprintCharges?id=<s:property  value="id" />">Print </a>
										</s:else>
									</td>
									
									
									</tr>
								</s:iterator>
								
								<%-- <tr>
									<td colspan="3">Total</td>
									
									
									
									<td><b><%=Constants.getCurrency(loginfo)%> <s:property value="creditTotalx"/></b></td>
									<td><b><%=Constants.getCurrency(loginfo)%> <s:property value="debitTotalx"/></b></td>
									<td class="text-danger"><b><%=Constants.getCurrency(loginfo)%> <s:property value="balanceTotalx"/></b></td>
									
									
								</tr>	 --%>
								
							</s:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	
	
	
	<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
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
	
	<!-- Modal for confirmiing pay - lokesh -->
<div id="changepymode" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Change PayMode</h4>
      </div>
      <div class="modal-body">
      <div>
      <input type="hidden" id="recpid">
    	  <label for="exampleInputEmail1">Payment Mode</label>
		    <select name="howpaid" id="howpaid" class="form-control" >
				<option value="0">Select</option>
				<option value="Cash">Cash</option>
				<option value="Cheque">Cheque</option>
				<option value="NEFT">NEFT</option>
				<option value="D/Card">D/Card</option>
				<option value="UPI">UPI</option>
				
			
			</select>
			 
		  </div>

      	</div>
      	
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="changetypeadvref()" data-dismiss="modal" value="Ok">
         
        
      </div>
    </div>

  </div>
</div>											

<div id="changecredit" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Change Credit</h4>
      </div>
      <div class="modal-body">
      <div>
      <input type="hidden" id="recpid1">
     
    	  <label for="exampleInputEmail1">Credit</label>
		   <input type="text" id="credit" name="credit" class="form-control">
	
		  </div>

      	</div>
      	
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="changecreditadvref()" data-dismiss="modal" value="Ok">
         
        
      </div>
    </div>

  </div>
</div>							
					
					
											
										</div>
									</div>
								</div>
							</div>
						</div>



		
		