<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="diarymanagement/js/paynow.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<script>

  $(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
 		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
 		
		
	});
  
  
  
  bkLib.onDomLoaded(function() {     
	  
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 120,minHeight : 120}).panelInstance('submitInvoiceNotes');
	  
  });
</script>
<div class="">
	<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Create & Submit Invoice</h4>

									</div>
								</div>
</div>	
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;">

<s:form action="submitinvoiceCharges" theme="simple" id="raiseinvoicefrm">
<div class="col-lg-4 col-md-4 col-sm-4" style="padding-left:0px;">
<h4>Submit Invoice  for <b><s:property value="initial"/> <s:property value="firstname"/> <s:property value="lastname"/></b> </h4>

<div class="radio-inline">
  <label style="line-height: 22px;">
    <input type="radio" name="invoicepstype" id="pr" value="0" checked="true"/>
    Primary
  </label>
</div>
<div class="radio-inline">
  <label style="line-height: 22px;">
    <input type="radio" name="invoicepstype" id="se" value="1"/>
    Secondary
  </label>
</div>
<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				
				
			%>							       
			<s:hidden name="ispreiousipdid"></s:hidden>
			<s:hidden name="ipdidnew"></s:hidden>
			<s:hidden name="client" id="client" />
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payby" id="payby"/>
			<s:hidden name="creditCharge" id="creditCharge"/>
			<s:hidden name="totalassesment" id="totalassesment"/>
			<s:hidden name="practitionerId" id="practitionerId"/>
			<s:hidden name="practitionerName" id="practitionerName"/>
			<s:hidden name="invoiceid" id="invoiceid"/>
			<s:hidden name="debitTotalx" id="hdndebittotal" value="%{debitTotal}"/>
			<s:hidden name="location" id="loaction"/>
			<s:hidden name="selectedCharges"/>
			<div class="row">
			<div class="col-lg-6 col-md-6">
				<lable>Date</lable>
				
				<%if(loginfo.isInvoice_date_modify()){ %>
				<input type="text"  id="invoiceDate"  name="invoiceDate" class="form-control"  value="<%=temp[0] %>" ></input>
				<% }else{%>
				<input type="text"   readonly="readonly"  class="form-control"  value="<%=temp[0] %>" ></input>
				<input type="hidden" name="invoiceDate" id="invoiceDate" value="<%=temp[0] %>">
				<%} %>
				
				</div>
				
			</div>
</div>
<div class="col-lg-4 col-md-4 col-sm-4">

</div>	
<div class="col-lg-4 col-md-4 col-sm-4">

</div>
</div>	
			
			</div>
			<br>
		<%-- 	<div style="font-weight: bold; font-size: 12px;">Invoice can be raise as below</div>
			<br>
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-hover table-condensed table-bordered">
					<thead>
				<tr>
					<th>Status</th>
					<th>To</th>
					<th>Invoicee</th>
					<th>No. of Charges</th>
					<th>Total</th>
					<th>Location</th>
					
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>Invoiced</td>
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
					<td><s:property value="locationName"/></td>
				</tr>
				</tbody>
			</table>
			</div>
			</div>
			</div> --%>
			
			<div class="col-lg-12 col-md-12">
				<div class="col-lg-12 col-md-12" style="margin-top: 12px;padding: 12px 0px 0px 0px;">
				<div style="font-weight: bold; font-size: 12px;">Create Invoice For Below Charges</div>
					<div>
						<table id="assementtable" class="table table-condensed table-bordered">
					<thead>
						<tr>
							<th>Sr.No.</th>
							<th>Date</th>
							<th>Transaction</th>
							<th style="text-align: center;">Description</th>
							<th>Debit</th>
							<th>Credit</th>
							<th>Balance</th>
						
						</tr>
						</thead>
						<tbody>
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
								<s:if test="policyExcess == 1">
									<td>Policy Excess<br>
									
										<a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><img style="margin-left: 20%;" width="20" height="15" align="middle" src="common/images/Arrows-Down-icon.png"/></a>
									</td>
								</s:if>
								<s:else>
									<td><s:property value="chargeType"/><br>
									
										<a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><img style="margin-left: 20%;" width="20" height="15" align="middle" src="common/images/Arrows-Down-icon.png"/></a>
									</td>
									</s:else>
								</s:else>
								<s:if test="apmtChargeType==0">
									<td><s:property value="appointmentType"/><br>
										 Treatment: <s:property value="treatmentEpisodeName"/><br>
										 Practitioner: <s:property value="practitionerName"/>
									</td>
								</s:if>
								<s:else>
									<td>
										Additional Charge(<s:property value="appointmentType"/>)
									</td>
								</s:else>
								
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></td>
								<td class="text-danger"><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx"/></td>
							</tr>
							<tr id = "hiddenDetailsDiv<s:property value="invoiceid"/>" style="display: none">
									<td colspan="7" id = "hiddenDetailsDiv1<s:property value="invoiceid"/>"> </td>
							</tr> 	
							<%c1 = c1+1;%>
						</s:iterator>
					
							<tr>
								<td colspan="3"></td>
								<td style="text-align: center;"><b>Total</b></td>
								<td id="totaltdid"><b><%=Constants.getCurrency(loginfo)%> <s:property value="debitTotalx"/></b></td>
								<td><b><%=Constants.getCurrency(loginfo)%> <s:property value="creditTotalx"/></b></td>
								<td id="balancetotalid" class="text-danger"><b><%=Constants.getCurrency(loginfo)%> <s:property value="balanceTotalx"/></b></td>
								
								<input type="hidden" name="hdntotal" id="hdntotal" value="<s:property value="debitTotal"/>"/>
							</tr>
							
							
					</tbody>
					</table>
				</div>
				</div>
				</div>
				<br>
				<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;">
					<div class="col-lg-4 col-md-4">
					    <%LoginInfo info= (LoginInfo)session.getAttribute("logininfo"); %>
						<input type="hidden" id="lmhac" value="<%=loginfo.isLmh()%>">
					</div>
					<div class="col-lg-2 col-md-2">
					    <label for="exampleInputEmail1"><%=loginfo.getPractitonername_field() %> List</label>
					    <s:select name="doctorid" id="doctorid" list="userList" cssClass="form-control chosen-select" listKey="id" listValue="diaryUser" headerKey="0" headerValue="Select Doctor"/>
					</div>
					<div class="col-lg-2 col-md-2" style="padding-top: 16px; display: none;" id="tfromdate">
					    <s:textfield id="fromdate" name="fromDate" cssClass="form-control" placeholder="From Date"></s:textfield>
					</div>
					<div class="col-lg-2 col-md-2" style="padding-top: 16px; display: none;" id="ttodate" >
					    <s:textfield id="todate" name="toDate" cssClass="form-control" placeholder="To Date"></s:textfield>
					</div>
				<div class="col-lg-2 col-md-2 pull-right" style="padding-left: 0px;margin-bottom: 10px;">
					<label for="exampleInputEmail1">Invoice Type</label>
						<s:select name="invcetype" id="invcetype" onchange="addfrmtdate(this.value)" list="invoiceTypeLis" cssClass="form-control"
							listKey="id" listValue="name" headerKey="0" headerValue="Invoice Type"/>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
				
				
					 <div>
					 <label>Note :</label></div> <s:textarea cssClass="form-control" name="submitInvoiceNotes" id="submitInvoiceNotes" rows="6" cols="50"></s:textarea>
					
				</div>
				<br>
				
				<div class="">
				<div class="col-lg-12 col-md-12 text-right">
					<input type="button" class="btn btn-primary"  value="Create Invoice" onclick="raiseInvoice()">
				</div>
			</div>
			
		
				<br>
				
			</s:form>


</div>


<div id="finalconfirmpopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Please Confirm Selected Information </h4>
      </div>
      <div class="modal-body">
    	<span> 	Invoice Type:<h4 id="finalconfirmInvtype"></h4> </span>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-info" onclick="submitFinalConfirm2()" data-dismiss="modal" value="Ok">
         <input type="button" class="btn btn-success  close" onclick=""  style="font-size: inherit;"  data-dismiss="modal" value="Cancel">
      </div>
    </div>

  </div>
</div>
			
	