<%@page import="com.a.a.a.g.m.l"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="accounts/js/modifyInvoice.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>


<script>


window.onload = function () {
	
	
}
</script>

<style>
.my-custom-scrollbar {
position: relative;
height: 210px;
overflow: auto;
}
.table-wrapper-scroll-y {
display: block;
}
</style>
<script>
$(window).on('load', function() {
	$('#dashboardloaderPopup').modal('hide');
});
		
  $(document).ready(function() {
	  $('#dashboardloaderPopup').modal('show');
		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#invoiceDate").datepicker({

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
 		currencySign = '<%=Constants.getCurrency(loginfo)%>';	
		
	});
  
  
 bkLib.onDomLoaded(function() {     
	  
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 120,minHeight : 120}).panelInstance('submitInvoiceNotes');
	/*   if(document.getElementById('chargeNote1')){
		  new nicEditor({buttonList : [],minWidth:1000}).panelInstance('chargeNote1');  
	  }
	   */
  });
</script>

 <script type="text/javascript" src="accounts/js/chargeAccountProcessing.js"></script> 

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Modify Invoice</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<s:form action="updatesaveProcessingAccount" theme="simple" id="modifyinvoicefrm">
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payby" id="payby"/>
			<s:hidden name="invoiceid" id="invoiceid"/>
			<s:hidden name="totalassesment" id="totalassesment"/>
		
		    <s:hidden name="tpid" id="tpid"></s:hidden>
			<input ty>
			
			<s:hidden name="paidamt" id="paidamt"/>
			<s:hidden name="paymentdone" id="paymentdone"/>
			<input type="hidden" id="deletechargeamt" value="0">
		<div class="row">
		<div class="col-lg-2 col-md-2">
			<!-- <input type="text" id="invoiceDate" name="invoiceDate" class="form-control"   ></input></div> -->
			<s:textfield name="invoiceDate" id="invoiceDate" cssClass="form-control" theme="simple" style='margin-top:15px;'/>
			</div>
			<div class="col-lg-2 col-md-2">
			<!-- <input type="text" id="invoiceDate" name="invoiceDate" class="form-control"   ></input></div> -->
			 
			 <s:select list="#{'Client':'Self','Third Party':'Third Party'}" onchange="checkTp(this.value)" name="invoicepayby" cssClass="form-control" id="invoicepayby" style='margin-top:15px;'/>
			      
			</div>
			<div class="col-lg-2 col-md-2 hidden" id="tptype">
			<!-- <input type="text" id="invoiceDate" name="invoiceDate" class="form-control"   ></input></div> -->
			  
			    <s:textfield cssClass="form-control" disabled="true" name="tpname" id="tpname" style='margin-top:15px;'></s:textfield>				      
			</div>
			<div class="col-lg-2 col-md-2" >
			<!-- <input type="text" id="invoiceDate" name="invoiceDate" class="form-control"   ></input></div> -->
			    <input type="button" class="btn btn-primary" value="Edit <%=loginfo.getPatientname_field() %>" style='margin-top:15px;' onclick="opencPopup('editClient?selectedid=<s:property value="clientId"/>') "/>				      
			</div>
		</div>
		
			
			<br>
			<div style="font-weight: bold; font-size: 12px;">Modify Invoice No 0000<s:property value="invoiceid"/>
				<!-- <input type="button" value="Add More Charges" style="margin-left: 20px;" class="btn btn-primary">  -->
			</div>
			<div class="col-lg-12 col-md-12">
			<div class="col-lg-4 col-md-4" style="margin-left: -31px;">
			<s:select list="userList" listKey="id" listValue="diaryUser" headerKey="0" headerValue="All Practitioner" name="doctorid" cssClass="form-control chosen-select" ></s:select>
			
			</div>
			<div class="col-lg-6 col-md-6">
			</div>
				<br><%int count=1;%>
					
				</div>
				</div>
			<div class="row">
			<marquee  behavior="alternate" scrolldelay="80" scrollamount="2">
			<h4 style="color: #f50909"><strong>Please Scroll Down to See all Charges.</strong></h4>
			<h4 style="color: #f50909"><strong>After Update Invoice Please Click on Save Button for Completion of Process</strong></h4>
			</marquee>
				<div class="col-lg-12">
					<div class="table-wrapper-scroll-y my-custom-scrollbar" >
						<table class="table table-bordered" id="assementtable" style="width:100% !important;">
					<thead>
					<tr>
						<th>Sr.No.</th>
						<th>Charge No</th>
						<th>Amount</th>
						<th>Ward</th>
						<th style="text-align: center;">Modify Charge</th>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="assesmentList">
						<tr>
							<td><%=count%> <input type="checkbox" class="case" id="ch-<%=count %>" name="ch-<%=count %>" checked="checked" value="<s:property value="invoiceNo"/>"></td>
							<td>0000<s:property value="invoiceNo"/> (<s:property value="date"/>) 
							<a href="javascript: void(0);" onclick="showInnerSubDiv('hiddenDetailsDiv<s:property value="invoiceNo"/>','<s:property value="invoiceNo"/>');"><i class="fa fa-arrow-down"></i></a>
							
							</td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></td>
							<td>
							<s:if test="ward==''">
							</s:if>
							<s:else>
							<s:hidden name="assesmentid" id="assesmentid<%=count%>"/>
						<%-- 	<s:select name="ward" list="wardlist" listKey="id" listValue="wardname" cssClass="form-control " headerKey="0" headerValue="All Ward" theme="simple" onchange = "changeWardininvoiceAss('<%=count%>',this.value)" ></s:select> --%>
							<%if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){ %>
							<select class="form-control" onchange = "changeWardininvoiceAssByInv('<s:property value="invoiceNo"/>',this.value)">
							<option value="0">All Wards</option>
							<s:iterator value="wardlist">
							<s:if test="ward==id">
							<option selected='selected' value='<s:property value="id"/>'><s:property value="wardname"/></option>
							</s:if>
							<s:else>
							<option value='<s:property value="id"/>'><s:property value="wardname"/></option>
							</s:else>
							</s:iterator>
							</select>
							<%}else{ %>
							
							<%} %>
							</s:else>
							</td>
							<%-- <td><a href="#" onclick="modifyChargePopup('<s:property value="invoiceNo"/>')">Modify Charge</a></td> --%>
							<td style="text-align: center;"><input type="button" class="btn btn-info" value="Modify Charge" onclick="modifyChargePopup('<s:property value="invoiceNo"/>')"></td>
						</tr>
						<tr id = "hiddenDetailsDiv<s:property value="invoiceNo"/>" style="display: none">
							<td colspan="4" id = "hiddenDetailsDiv1<s:property value="invoiceNo"/>"> </td>
						</tr>
					<%count++;%>
					</s:iterator>
					<tr>
						<td colspan="2"><b>Total</b></td>
						<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmt"/></td>
						<td></td>
						<td></td>
					</tr>
					<s:hidden name="isforrefund" id="isforrefund"></s:hidden>
				</tbody>
				</table>
				</div>
				</div>
				</div>
			
			<br>
			<%-- <div style="font-weight: bold; font-size: 12px;">Pay this Invoice ( Debit: &nbsp;&nbsp;&nbsp;<%=Constants.getCurrency(loginfo) %><s:property value="debitAmt"/>, &nbsp;&nbsp;&nbsp; Credit: &nbsp;&nbsp;&nbsp;<%=Constants.getCurrency(loginfo) %><s:property value="creditCharge"/>)</div>
			<hr/>
			<br> --%>
			
			
<%-- 	<div class="row">
		<div class="col-lg-3">
			
			
				<label>Payment Mode</label><label class="text-danger">*</label> 
				<select name="howpaid" id="howpaid" class="form-control" onchange="totalCashDesk(this.value,'<s:property value="clientId"/>');">
					<option value="0">Select</option>
					<option value="BACS">BACS</option>
					<option value="Cheque">Cheque</option>
					<option value="C/Card">C/Card</option>
					<option value="Cash">Cash</option>
					<option value="D/Card">D/Card</option>
					<option value="D/D">D/D</option>
					<option value="Other">Other</option>
					<option value="S/O">S/O</option>
					<option value="ccd">Collect from CashDesk</option>
				</select>
				
				
				<label>Payment Amount <%=Constants.getCurrency(loginfo)%></label>
				 <s:textfield name="payAmount" id="totalamount"  value="%{balance}"  cssClass="form-control"/>
				<s:hidden id = "totalamt" name="balance"></s:hidden>
				<label>Discount(if any) % </label>
				<s:if test="discount > 0">
				<s:textfield name="discount" disabled="true" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
				
				</s:if>
				<s:else>
				<s:textfield name="discount" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
				
				</s:else>
				<label>Amount Due<%=Constants.getCurrency(loginfo) %></label>
				<s:textfield name="amount" id="payAmount" value="%{balance}"
				cssClass="form-control" />
				<s:hidden id="ccdamt" name="ccdamt"></s:hidden>
				</div>
			</div> --%>
			
			
			<div class="col-lg-12 col-md-12">
			
			           <s:if test="invcetype=='HD'">
			           <div class="form-inline">
			           <div class="form-group">
				           <label>Date From</label>
				           <s:textfield name="hdfromDate" id="fromdate" cssClass="form-control"></s:textfield>
			           </div>
			           <div class="form-group">
				           <label>To</label>
				           <s:textfield name="hdtoDate" id="todate" cssClass="form-control"></s:textfield>
			           </div>
			           </div>
			                   
			                   
			           </s:if>
			</div>
			
			
			<br>
			<div class="col-lg-12 col-md-12 row">
				
					<label>Note :</label><s:textarea cssClass="form-control" name="submitInvoiceNotes" id="submitInvoiceNotes" rows="5" cols="40"></s:textarea>
				
				
				</div>
			<div class="col-lg-12 col-md-12 row">
				<div class="form-inline">
				<div class="form-group" style=" margin-left: 677px;">
					<input type="button" class="btn btn-lg btn-primary" value="Save" onclick="modifysave()" style="float: right; margin-top: 15px; padding-top: 3px" id="modifysavebtn">
				</div>
					
				</div>
				</div>
			
				
				
				
			<s:token></s:token>
				
			</s:form>
											

											
										</div>
									</div>
								</div>
							</div>
						</div>



	
	
		
									       
		
			
		
		
		<!-- Complete Appointment Popup  -->

	<div class="modal fade" id="modifychargePopupDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal" onclick="goreferesh()">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Complete Appointment</h4>
				</div>
				<div class="modal-body">
				<%-- <div id="visitingconsltantdiv">
				<s:hidden id="consultantdr" value="0"></s:hidden>
				</div> --%>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"  id="updtebtnmodifyinvoice"
						onclick="createChargeAndUpdateAccount('Charge')">Update
						Invoice</button>
					<!--  <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="createInvoice('Invoice')">Invoice Now / Pay Later</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="submitInvoice()">Submit Invoice</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="paynowForInvoice()">Record Payment</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="paynowForInvoice()">Cash Desk</button> -->
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
				<input type="hidden" name="compulsaryconsultant" id="compulsaryconsultant" value="0">
					<%@ include file="/diarymanagement/pages/modifyChargePopup.jsp"%>
				</div>
				
				
			</div>
		</div>
	</div>	
<!-- Loader -->
	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>
	<!--End loader  -->	
	