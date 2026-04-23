<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@page import="java.util.*"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="report/js/chargesReport.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>


<style>
.titsetas{
	font-size: 14px;
    font-weight: bold;
    line-height: 26px;
    }
   .dropdown-menu {
    top: 32px !important;
    left: auto !important;
    min-width: auto !important;
    padding: 2px 0 !important;
}
.dropdown-menu>li>a {
    display: block;
    padding: 0px 8px;
}
    
    @media print
   {
      h4, .h4 {
    	font-size: 9px !important;
	}
	p {
    	font-size: 8px !important;
	}
	.titsetas {
	    font-size: 9px !important;
	    font-weight: bold;
	    line-height: 26px;
	}
	.table>thead>tr>th {
	    background-color: #777 !important;
	    color: #fff !important;
	    font-size: 9px !important;
	}
   }
</style>


<script>

    
 function printExcel() {

       $("#xlstable").table2excel({
					exclude: ".noExl",
					name: "Department Wise Payment Report",
					filename: "paymentReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});

   }


	$(document).ready(function() {
		var data = document.getElementById('selectedmodality1').value;
		if(data!=0){
			var temp = data.split(',');
				for(i=0;i<temp.length;i++){
					if(i!=0){
						document.getElementById('p'+temp[i]).checked=true;
					}
					
				}
			} 
		
	

		$("#fromDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true
		});
	});
	
	
	function setSorting(column,order){
		if(order=='asc'){
			order = 'desc';
		}else{
			order = 'asc';
		}
		document.getElementById('orderby').value = column;
		document.getElementById('order').value = order;
		document.getElementById('paymentreportfrm').submit();
	}
	
 	var selectedmodality = 0;
	function setActionForAll(){
	   $('.pacss').each(function() { 
			  
			 if(this.checked==true) {
			 	selectedmodality = selectedmodality + ',' + this.value;
			 }              
		});
		
		document.getElementById('selectedmodality1').value = selectedmodality;
		document.getElementById('orderby').value = 'date'; 
		$("#dashboardloaderPopup").modal('show');
		document.getElementById('paymentreportfrm').submit();
	} 
	
	  bkLib.onDomLoaded(function() {
	        
	      	 new nicEditor().panelInstance('paymentReportEmailBody');
	      	 $('.nicEdit-panelContain').parent().width('500px');
	      	 $('.nicEdit-panelContain').parent().next().width('500px');
	      	 
	      	 $('.nicEdit-main').width('100%');
	      	 $('.nicEdit-main').height('80px');
	    });


	
	  function setActionForAllUser(){
		  
		 $('.pacss').each(function() { 
			  
				 if(this.checked==true) {
				 	selectedmodality = selectedmodality + ',' + this.value;
				 }              
			});
			
			document.getElementById('selectedmodality1').value = selectedmodality; 
			document.getElementById('orderby').value = 'date';
			document.getElementById('actiontype').value = '1';
			document.getElementById('paymentreportfrm').submit(); 
	  }
	  
	  function gettplistNew(patientType) {
			var dataObj={
				"patientType":""+patientType+"",
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "getPayerListBookAppointmentAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				  var tpList= data.tpList;
				  document.getElementById("dstp").innerHTML= data.tpList;
				  $("#tpid").trigger("chosen:updated");
				  $(".chosen-select").chosen({allow_single_deselect: true});
				  resetChargeAndWard();
				  
			   },
			   error : function(result) {
				   jAlert('error', "Something wrong!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			});
		}
	
</script>




 
 					
						
						
						
						<div class="reportprint">
							<div class="">
								<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
									<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
											 <link href="common/css/printpreview.css" rel="stylesheet" />
										<%@ include file="/accounts/pages/letterhead.jsp" %>
										</div>
									</div>
								</div>
								<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Department Wise Payment Report</h4>
									</div>
								</div>
								<div class="print-visible hidden-md hidden-lg">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Department Wise Payment Report</h4>
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											<s:form action="paymentagainstinvoicefornkpphysioChargesRpt" id="paymentreportfrm" theme="simple">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
<s:hidden name="selectedpayer" id="selectedmodality1"/> 
<s:hidden name="actiontype" id="actiontype"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			<div class="form-group hidden">
				<%if(loginfo.getJobTitle().equals("Admin")){%>
				<s:select name="invoicecategory" id="invoicecategory" 
					list="#{'2':'Select Category','0':'Primary','1':'Secondary' }"
					cssClass="form-control"></s:select>
				<%} %>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
			</div>
			<div class="form-group">
				<s:select list="invoiceTypeLis" id="selectedmodality" name="selectedmodality" listKey="id" listValue="name" headerKey="0" headerValue="Select Invoice Type" cssClass="form-control chosen-select"></s:select>	
			</div>
			<div class="form-group" >
				<s:select name="chargeType" id="chargeType" list="chargeTypeList" listKey="typeName" listValue="typeName"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="0" headerValue="Select Charge Type"  ></s:select>
			</div>
			<div class="form-group hidden" >
									<s:select cssClass="form-control showToolTip chosen-select"
										id="condition" name="condition" list="departmentList"
										listKey="id" listValue="name" headerKey="0"
										theme="simple" headerValue="Select Department" />
			</div>
			
			<div class="form-group">
				<s:select name="payby" id="payby" 
				list="#{'0':'Select Patient Type','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control chosen-select"  onchange="gettplistNew(this.value)" ></s:select>
			</div>
			<div class="form-group">
				<div id="dstp">
				      <!-- @rahul commented beacuase multiple checkbox for vspm multiple payer search --> 
					<%-- <s:select list="thirdPartyListNew" listKey="id" listValue="companyName" id="tpid" headerKey="0" headerValue="Select Payer" name="tpid" 
					cssClass="form-control chosen-select"   > </s:select> --%>
                    <!-- new code for checkbox for vspm multiple payer search -->
					<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="width:100%;">Select Payer <span class="caret"></span></button>
									<ul id="tpid" class="dropdown-menu">
											<s:iterator value="thirdPartyListNew">
												<li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" class="pacss" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="companyName"/></span></a></li>
											 </s:iterator>
									 </ul>
								
				</div>
			</div>
			<%if(!letterreq.isLmh()){%>
			<div class="form-group">
				<div id="dstp">
					  <s:select list="Outsourcelist"  name="Outsourcename" listKey="id" listValue="Outsourcename" cssClass="form-control chosen-select dato" id="Outsourcename" headerKey="0" 
					headerValue="Select Outsource" />	
				</div>
			</div>
			<%} %>
			<div class="form-group hidden">
				<%-- <s:select name="howpaid" id="howpaid" 
				list="#{'0':'Show All Payment','BACS':'BACS','Cheque':'Cheque','C/Card':'C/Card','Cash':'Cash','D/D':'D/D','Other':'Other','S/O':'S/O','prepayment':'Pre-Payment'}"
				cssClass="form-control" ></s:select> --%>
				<s:select name="howpaid" id="howpaid" 
				list="#{'0':'Select Payment','Cash':'Cash','Cheque':'Cheque','NEFT':'NEFT','D/Card':'D/Card','UPI':'UPI'}"
				cssClass="form-control" ></s:select>
			</div>
			
			<%-- <div class="form-group">
				<%if(loginfo.getJobTitle().equals("Admin")||loginfo.isPaymentReport()){%>
				<s:select list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			<%}else{ %>
				<s:select disabled="true" list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			<%} %>
			</div> --%>
			
			<div class="form-group hidden">
				<s:select name="sortfilter" id="sortfilter" 
				list="#{'0':'Sort by Invoice type','1':'Sort by Invoice id','2':'Sort by Receipt No.'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			<div class="form-group ">
	                   						 <s:select cssClass="form-control showToolTip chosen-select"
												id="diaryUsersss" name="diaryUser" list="departmentList" listKey="id"
												listValue="discipline" headerKey="0" theme="simple"
												headerValue="Select Department " />	 
	                   						 <%-- <s:select cssClass="form-control showToolTip chosen-select"
												id="diaryUsersss" name="diaryUser" list="userList" listKey="id"
												listValue="diaryUser" headerKey="0" theme="simple"
												headerValue="Select Department " />	  --%>
	       </div>
			<div class="form-group">
				<h4 class="hidden" style="margin-top:0px;margin-bottom:0px;color: green;"><a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="chargeid"/>','<s:property value="chargeid"/>','<s:property value ="payby"/>');">Expand All</a> &nbsp; | &nbsp; <a href="#">Collapse All</a></h4>
				<input type="button" value="Go"  class="btn btn-primary" onclick="setActionForAll()">
				<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
				<!-- <a type="button" title="Save as PDF" onclick="return saveAsPdfPaymentReport();" class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a> -->
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
				<!-- <input type="button" value="All User Print"  class="btn btn-primary" onclick="setActionForAllUser()"> -->
			</div>
			
			<%-- <div class="form-group" style="padding-left: 50px">
				<h4 ><span class="titsetas">Cash Collected :-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="totalcashcollect"/></span></h4>
			</div>
			<div class="form-group" style="padding-left: 10px">	
				<h4 ><span class="titsetas">Discount Requested Pending:-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="requestdiscamt"/></span></h4>
			</div>
			<div class="form-group" style="padding-left: 10px" >	
				<h4 ><span class="titsetas">Discount Approved Pending:-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="approvediscamt"/></span></h4>
			</div>
			<div class="form-group" style="padding-left: 10px" >	
				<h4 ><span class="titsetas">Discount Applied :-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="appliedDisc"/></span></h4>
			</div>
			<div class="form-group" style="padding-left: 10px" >	
				<h4 ><span class="titsetas">Invoice Amt. :-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="invoicettotal"/></span></h4>
			</div> --%>
		</div>
		
		</div>
		
			
			
			
			
		</div>
		
		
     	<% Map<Integer, ArrayList<Accounts>> chargeinvlist=(Map<Integer, ArrayList<Accounts>>) session.getAttribute("chargeinvlist"); %>
     	
				<table class="table" id="xlstable" style="width: 100%;border-top: 1px solid #ddd;">
					<thead>
						<tr>
							<!-- <th>Receipt No.</th> -->
							<th>Sr. No.</th>
							<th>Invoice Date</th>
							<th>Invoice No</th>
							<th>Payment Date</th>
							<th>Invoice Type</th>
							
							<!-- <th></th> -->
							
							<th>UHID</th>
							<th>Patient Name</th>
							<s:if test="payby=='Client'">
								<th>Patient Type <a href="#" onclick="setSorting('firstname','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							</s:if>
							<s:else>
								<th>Patient Type <a href="#" onclick="setSorting('company_name','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							</s:else>
							<th>Payer</th>
							<th>Pay By</th>
							<th>Department</th>
							<!-- <th>Practitioner name</th> -->
							
							<th>Charge Type</th>
							
							
							<th>Payment <a href="#" onclick="setSorting('paymode','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							<th style="text-align: right;">Invoice Amt</th>
							<th style="text-align: right;">Disc.</th>
							<th style="text-align: right;">Net Payable Amt</th>
							<th style="text-align: right;">Paid Amt</th>
							<th style="text-align: right;">Balance</th>
							<th style="text-align: right;">Refund</th>
							<%if(!letterreq.isLmh()) {%>
							<th style="">Outsource</th>
							<%} %>
							<th style="text-align: right;">Chargename</th>
							<th style="text-align: right;">Code</th>
						</tr>
					</thead>
					<%String line=""; %>
					<%int i=1; %>
					
					<tbody>
					  
						<%for(ArrayList<Accounts> value:chargeinvlist.values()){ 
						for(Accounts s1: value){
						
						%>
					<%String whopay=s1.getWhoPay(); %>
					<tr>
					<td><%=i++ %></td>
					<td><%=s1.getDate() %></td>
					<td>
							<%=s1.getInvoiceid() %>
											</td>
					<td><%=s1.getPaymentdate() %></td>
					<td><%=s1.getInvoicenameid() %></td>
					<td><%=s1.getAbrivationid() %></td>
					<td><%=s1.getClientName() %></td>
					
				
					<td><%=s1.getInvoicee() %></td>
					<td><%=s1.getThirdParty()%></td>
					<td><%=s1.getPayby() %></td>
					<td><%=s1.getLocationName() %></td>
					<td><%=s1.getMasterchargetype() %></td>
					<td><%=s1.getPaymentmode() %></td>
					<td><%=s1.getDebitAmount() %></td>
					<td><%=s1.getDiscamt() %></td>
					
					<td><%=s1.getNetPayableAmt() %></td>
					<td><%=s1.getPayAmount() %></td>
					<td><%=s1.getBalAmt() %></td>
					<%-- <td><%=s1.getRefundAmountx() %></td> --%>
					<td><%=s1.getOriginal_pay() %></td>
					
					<%if(!letterreq.isLmh()) {%>
					<td><%=s1.getOutsource() %></td>
					<%} %>
					<td><%=s1.getChargename() %></td>
					<td><%=s1.getCode() %></td>
					</tr>
					
					<%}} %>
					
					</tbody>
				<%-- 	<tbody>
					
						<s:if test="paymentList.size!=0">
							<s:iterator value="paymentList" status="rowstatus">
							<s:if test="cancelsts==1">
							<% line="text-decoration: line-through;"; %>
							</s:if>
							
								<tr style="border-top: 1px solid #ddd;background-color: beige;">
									<td><s:property value="newsr" /><%if(loginfo.isSeq_no_gen()&&(loginfo.getClinicUserid().equals("aureus")||loginfo.getClinicUserid().equals("nelson"))){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<td><%=(++i) %></td>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicedate" /></td>
									</s:if>
									<s:else>
										<td><s:property value="invoicedate" /></td>
									</s:else>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><%if(loginfo.isSeq_no_gen()){%><s:property value="bghseqId"/><%}else{%><s:property value="invoiceid"/> <%} %></td>
									</s:if>
									<s:else>
										<td><%if(loginfo.isSeq_no_gen()){%><s:property value="bghseqId"/><%}else{%><s:property value="invoiceid"/> <%} %></td>
									</s:else>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="date" /></td>
									</s:if>
									<s:else>
										<td><s:property value="date" /></td>
									</s:else>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicenameid" /></td>
									</s:if>
									<s:else>
										<td><s:property value="invoicenameid" /></td>
									</s:else>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicenameid"/> // <s:property value="userid"/></td>
									</s:if>
									<s:else>
										<td><s:property value="invoicenameid"/> /<%if(loginfo.isSeq_no_gen()){%><s:property value="bghseqId"/><%}else{%><s:property value="invoiceid"/> <%} %>/ <s:property value="userid"/></td>
									</s:else>
									
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="abrivationid" /></td>
									</s:if>
									<s:else>
										<td><s:property value="abrivationid" /></td>
									</s:else>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="ClientName" />[<s:property value="Invoiceid"/>]</td>
									</s:if>
									<s:else>
										<td><s:property value="ClientName" /></td>
									</s:else>
									
									<s:if test="whoPay=='Client'">
										<!--<td><s:property value="invoicee" /> (Self)</td>
										
									--> 
										<td>Self</td>
									</s:if>
									<s:else>
										<td><s:property value="invoicee" /> Third Party</td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="payer" /></td>
									</s:if>
									<s:else>
										<td><s:property value="payer" /></td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="locationName" /></td>
									</s:if>
									<s:else>
										<td><s:property value="locationName" /></td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="practitionerName" /></td>
									</s:if>
									<s:else>
										<td><s:property value="practitionerName" /></td>
									</s:else>
									
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="masterchargetype" /></td>
									</s:if>
									<s:else>
										<td><s:property value="masterchargetype" /></td>
									</s:else>
									
									
									
									
									
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="paymentmode" /><s:if test="paymentNote==''"> </s:if><s:else>(<s:property value="paymentNote"/>)</s:else></td>
									</s:if>
									<s:else>
										<td><s:property value="paymentmode" /><s:if test="paymentNote==''"> </s:if><s:else>(<s:property value="paymentNote"/>)</s:else></td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="invAmt" /></b></td>
									</s:if>
									<s:else>
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="invAmt" /></b></td>
									</s:else>
										
									<s:if test="cancelsts==1">
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="discAmt" /></b></td>
									</s:if>
									<s:else>
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="discAmt" /></b></td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="netPayableAmt" /></b></td>
									</s:if>
									<s:else>
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="netPayableAmt" /></b></td>
									</s:else>
										
									<s:if test="cancelsts==1">
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="amountx" /></b></td>
									</s:if>
									<s:else>
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="amountx" /></b></td>
									</s:else>
										
									<s:if test="cancelsts==1">
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="balAmt" /></b></td>
									</s:if>
									<s:else>
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="balAmt" /></b></td>
									</s:else>
									
									
									
									<s:if test="cancelsts==1">
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="refundAmountx" /></b></td>
									</s:if>
									<s:else>
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="refundAmountx" /></b></td>
									</s:else>
								</tr>
								
								<s:iterator value="masterAssessmentList">	
									 <tr class="totalbor">
									 	<td style="border: none;" colspan="7"></td>
									 	<td style="border: none;"><b style="color: #a94442;"><s:property value="name"/></b></td>
									 	<td style="border: none;color: #999;">Charge Name</td>
		                                <td style="border: none;color: #999;">Unit Charges</td>	
		                                <td style="border: none;color: #999;">Quantity</td>
		                                <td style="border: none;color: #999;">Charge Discount</td>	
		                                <td style="border: none;color: #999;">Charges</td>
		                            </tr>
		                             <s:iterator value="assesmentList">
		                             <tr>
		                             	<td style="border: none;" colspan="7"></td>
		                             	<td style="border: none;"></td>
		                             	<td style="border: none;color: #333;"><s:property value="appointmentType"/></td>
									 	<td style="border: none;"><s:property value="Unitcharge"/></td>
		                                <td style="border: none;"><s:property value="Quantity"/></td>
		                                <td style="border: none;"><s:property value="Chargedisc"/></td>
		                                <td style="border: none;"><s:property value="Charges"/></td>
		                             </tr>
								 </s:iterator>	
							 
	                           </s:iterator>
	                           
								<tr class="hidden">
	                             	<td style="border: none;"></td>
								 	<td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;color: #333;text-align:right;">Total : </td>
	                                <td style="border: none;text-align: right;"><s:property value="totalamount"/></td>
	                             </tr>
							</s:iterator>
						</s:if>
					</tbody>
					 --%>
					<tfoot>
						<tr class="">
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
                         	<td style="border: none;color: #333;text-align:right;" >Total : </td>
                            <td style="border: none;text-align: right;"><s:property value="totalamount"/></td>
                         	<td style="border: none;color: #333;text-align:right;"></td>
	                    </tr>
					</tfoot>

				</table>
	
</s:form>

<!-- Modal Email-->
<div class="modal fade" id="sendEmailPaymentRptPopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				
					</div>
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "paymentReportEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "paymentReportccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "paymentReportSubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="paymentReportEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfPaymentReportMailId" style="display: none;">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="sendPaymentReportMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>		




<div class="modal fade" id="notepopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Create Payment Note For : <a style="background-color: yellow;"><%=loginfo.getUserId() %></a></h4>
			</div>
			
			<div class="modal-body" style="padding-left: 20px;padding-right: 20px;">
			
			<h4><b>Notes : </b></h4>
			<textarea class='form-control' rows="6" id='bodynote'><s:property value="paymentReportNote"/></textarea>
			</div>
			<div class="modal-footer" style="padding-left: 20px;padding-right: 20px;">
			<input type="button" class='btn btn-warning' value="Save Note" onclick="saveNotesFun()">
			</div>
		
		</div>
	</div>
</div>	
											

										</div>
									</div>
								</div>
							</div>
							
<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
 
 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
    function setpaymentreportNote(){
    	 $('#notepopup').modal( "show" );
    }
    
    function saveNotesFun(){
    	
    	var v= $('#bodynote').val();
    	if(v==''){
    		   jAlert('error', 'Please Add Notes.', 'Error Dialog');
   			
   			setTimeout(function() {
   				$("#popup_container").remove();
   				removeAlertCss();
   			}, alertmsgduration);
    	}else{
    		savePaymentNoteReport();
    	}
    }
    
    
    function savePaymentNoteReport(){	
    	$('#baselayout1loaderPopup').modal( "show" );
    	var v= $('#bodynote').val();
	 var dataObj= {
			"data":""+v+"", 
		
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "savepaymentreportnotesCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
	
	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	
	 $('#baselayout1loaderPopup').modal( "hide" );
	 $('#notepopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
		 window.location.reload();
}
  </script>

	

