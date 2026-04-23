<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="report/js/chargesReport.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

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
					name: "Payment Report",
					filename: "paymentReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});

   }


	$(document).ready(function() {
		var data = document.getElementById('selectedmodality').value;
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
		
		document.getElementById('selectedmodality').value = selectedmodality;
		document.getElementById('orderby').value = 'date';
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
			
			document.getElementById('selectedmodality').value = selectedmodality;
			document.getElementById('orderby').value = 'date';
			document.getElementById('actiontype').value = '1';
			document.getElementById('paymentreportfrm').submit(); 
	  }
	
</script>




 
 					
						
						
						
						<div class="reportprint">
							<div class="">
								<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Collection Report Detailed</h4>
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											<s:form action="paymentReportChargesRpt" id="paymentreportfrm" theme="simple">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
<s:hidden name="selectedmodality" id="selectedmodality"/>
<s:hidden name="actiontype" id="actiontype"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			<div class="form-group">
				<%if(loginfo.getJobTitle().equals("Admin")){%>
				<s:select name="invoicecategory" id="invoicecategory" 
					list="#{'2':'Select Category','0':'Primary','1':'Secondary' }"
					cssClass="form-control"></s:select>
				<%} %>
			</div>
			<div class="form-group">
				<s:select name="payby" id="payby" 
				list="#{'0':'Select Paid By','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control" ></s:select>
			</div>
			<div class="form-group">
				<%-- <s:select name="howpaid" id="howpaid" 
				list="#{'0':'Show All Payment','BACS':'BACS','Cheque':'Cheque','C/Card':'C/Card','Cash':'Cash','D/D':'D/D','Other':'Other','S/O':'S/O','prepayment':'Pre-Payment'}"
				cssClass="form-control" ></s:select> --%>
				<s:select name="howpaid" id="howpaid" 
				list="#{'0':'Select Payment','Cash':'Cash','Cheque':'Cheque','NEFT':'NEFT','D/Card':'D/Card','UPI':'UPI'}"
				cssClass="form-control" ></s:select>
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
				<%if(loginfo.getJobTitle().equals("Admin")||loginfo.isPaymentReport()){%>
				<s:select list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			<%}else{ %>
				<s:select disabled="true" list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			<%} %>
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="width:100%;">Select Invoice Type <span class="caret"></span></button>
												<ul class="dropdown-menu">
													<s:iterator value="invoiceTypeLis">
												 	 <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" class="pacss" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												  </s:iterator>
												 
												</ul>
			</div>
			<div class="form-group">
				<s:select name="sortfilter" id="sortfilter" 
				list="#{'0':'Sort by Invoice type','1':'Sort by Invoice id','2':'Sort by Receipt No.'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			<div class="form-group">
				<h4 class="hidden" style="margin-top:0px;margin-bottom:0px;color: green;"><a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="chargeid"/>','<s:property value="chargeid"/>','<s:property value ="payby"/>');">Expand All</a> &nbsp; | &nbsp; <a href="#">Collapse All</a></h4>
				<input type="button" value="Go"  class="btn btn-primary" onclick="setActionForAll()">
				<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
				<a type="button" title="Save as PDF" onclick="return saveAsPdfPaymentReport();" class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a>
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
				<input type="button" value="All User Print"  class="btn btn-primary" onclick="setActionForAllUser()">
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
		<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline" >
	
		<div class="form-group" >
			<input type="button" class='btn btn-success' value="Create Todays Note" onclick="setpaymentreportNote()">
		</div>
		<div class="form-group" >
			<input type="button" class='btn btn-info' value="View Todays Note" onclick="openPopup('paymentreportNotesChargesRpt')">
		</div>	
		</div>
		
		<div class="form-inline">
		<%if(loginfo.getClinicUserid().equals("aureus")){ %>
			<div class="form-group" style="padding-left: 50px" >	
				<h4 ><span class="titsetas">Invoice Amt. :-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="invoicettotal"/></span></h4>
			</div>
			<div class="form-group" style="padding-left: 10px">
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
		<%} %>
			
			
		</div>
		</div>
			
			
			
			
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12 topback2" style="text-align: center;padding: 4px 0px 9px 0px;background-color: rgba(217, 237, 247, ">
		<h3 class="text-center hidden-lg hidden-md hidden-sm visible-print" style="border-bottom: 4px double #ddd;line-height: 38px;margin:0px;">Payment Report</h3>
		
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">OPD</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="opdtotal"/></span></h4>
				<%-- <%if(loginfo.isBalgopal()){ %> --%>
				<br>
				<label>OPD Refund :</label><s:property value="opdref"/>
				
				<%-- <%} %> --%>
				
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">IPD</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="ipdtotal"/></span></h4>
			<%-- <%if(loginfo.isBalgopal()){ %> --%>
			<br>
			<label>IPD Refund :</label> <s:property value="ipdref"/>
			
			
			<%-- <%} %> --%>
			
			<br>
			<h4 class="hidden"><b>Balance:</b><%=Constants.getCurrency(loginfo) %><s:property value="balanceTotalx"/></h4>
			
			
		
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">Investigation</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="pathlabtotal"/></span></h4>
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">Registration</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="registrationTotal"/></span></h4>
				
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">Medicine + Others</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="mdcinetotal"/></span></h4>
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">Daycare</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="daycaretotal"/></span></h4>
			</div>
			<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">Advance</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="advreftotal"/></span></h4>
		<%-- <%if(loginfo.isBalgopal()){ %>
			<br><label>Advance :</label><s:property value="advtotal"/> <br>
			
		<%} %> --%>
			</div>
			<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">Refund</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="(totalref)"/></span></h4>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<h4 style="margin-top:0px;margin-bottom:0px;color: green;"><span class="titsetas">Total <a href="#" data-toggle="collapse" data-target="#detailview"><i class="fa fa-arrow-down" style="color: chocolate;" aria-hidden="true"></i></a></span> 
				<span><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx"/></span>
				- <span><%=Constants.getCurrency(loginfo) %><s:property value="totalref"/></span>
				= <span><%=Constants.getCurrency(loginfo) %><s:property value="calcdebittotal"/></span>
				</h4>
			</div>
		
		</div>
		
		<div id="detailview" class="collapse">
			<div class="col-lg-12 col-md-12 col-xs-12 topback2" style="padding: 0px 0px 9px 0px;">
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
					<s:iterator value="opdpaywiselist">
						<p style="margin: 0px;"><span><s:property value="paymentmode"/> </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></span></p>
					</s:iterator>
					
				</div>
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
					<s:iterator value="ipdpaywiselist">
						<p style="margin: 0px;"><span><s:property value="paymentmode"/> </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></span></p>
					</s:iterator>
				</div>
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
					<s:iterator value="pathlabpaywiselist">
						<p style="margin: 0px;"><span><s:property value="paymentmode"/> </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></span></p>
					</s:iterator>
				</div>
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
					<s:iterator value="mdcinepaywiselist">
						<p style="margin: 0px;"><span><s:property value="paymentmode"/> </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></span></p>
					</s:iterator>
				</div>
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
					<s:iterator value="advrefpaywiselist">
						<p style="margin: 0px;"><span><s:property value="paymentmode"/> </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></span></p>
					</s:iterator>
					<p style="margin: 0px;"><span>Ref</span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="totalref"/></span></p>
				</div>
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-left: 1px solid #ddd;">
					<p style="margin: 0px;">
						<span>Cash </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="cashtotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>Cheque </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="cheqtotal"/></span>
					</p>
					
					<%-- <p style="margin: 0px;">
						<span>D/Card </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="crtotal"/></span>
					</p> --%>
					<%-- <p style="margin: 0px;">
						<span>C/Card </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="drtotal"/></span>
					</p> --%>
					<p style="margin: 0px;">
						<span>D/Card </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="drtotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>NEFT </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="nefttotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>Other </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="othertotal"/></span>
					</p>
				</div>
			</div>
		</div>
		
		<div class="row hidden-print">
	 	<div class="col-lg-1 col-md-1" style="display: none" id = "previewPaymentRpt">
		<!-- 	<a href="liveData/chargesReport/PaymentReport.pdf" class="btn btn-primary" target = "blank">Preview</a> -->
			<input style="margin-top: 21px;" type="button" value="Send PDF in Mail" onclick="return openSendMailPaymentRptPopup();" class="btn btn-primary">
		</div>	 
		</div>
				<table class="table" id="xlstable" style="width: 100%;border-top: 1px solid #ddd;">
					<thead>
						<tr>
							<th>Receipt No.</th>
							<th>Invoice No.</th>
							<th>Invoice Date</th>
							<th>Date <a href="#" onclick="setSorting('date','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							<th>Patient Name</th>
							<th>Practitioner name</th>
							<s:if test="payby=='Client'">
								<th>Invoiced To <a href="#" onclick="setSorting('firstname','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							</s:if>
							<s:else>
								<th>Invoiced To <a href="#" onclick="setSorting('company_name','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							</s:else>
							<th>Payment <a href="#" onclick="setSorting('paymode','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							<%if(loginfo.getClinicUserid().equals("aureus")){ %>
								<th style="text-align: right;">Invoice Amt</th>
								<th style="text-align: right;">Disc.</th>
								<th style="text-align: right;">Refund</th>
								<th style="text-align: right;">Balance</th>
							<%} %>
							<th style="text-align: right;">Paid Amt</th>
						</tr>
					</thead>
					<%String line=""; %>
					<tbody>
						<s:if test="paymentList.size!=0">
							<s:iterator value="paymentList" status="rowstatus">
							<%-- <s:if test="cancelsts==1">
							<% line="text-decoration: line-through;"; %>
							</s:if> --%>
							
								<tr style="border-top: 1px solid #ddd;background-color: beige;">
									<td><s:property value="newsr" /><%if(loginfo.isSeq_no_gen()&&(loginfo.getClinicUserid().equals("aureus")||loginfo.getClinicUserid().equals("nelson"))){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicenameid"/> /<%if(loginfo.isSeq_no_gen()){%><s:property value="bghseqId"/><%}else{%><s:property value="invoiceid"/> <%} %>/ <s:property value="userid"/></td>
									</s:if>
									<s:else>
										<td><s:property value="invoicenameid"/> /<%if(loginfo.isSeq_no_gen()){%><s:property value="bghseqId"/><%}else{%><s:property value="invoiceid"/> <%} %>/ <s:property value="userid"/></td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicedate" /></td>
									</s:if>
									<s:else>
										<td><s:property value="invoicedate" /></td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="date" /></td>
									</s:if>
									<s:else>
										<td><s:property value="date" /></td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="ClientName" />|<s:property value="abrivationid" />[<s:property value="Invoiceid"/>]</td>
									</s:if>
									<s:else>
										<td><s:property value="ClientName" />&nbsp;|&nbsp;<s:property value="abrivationid" /></td>
									</s:else>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="practitionerName" /></td>
									</s:if>
									<s:else>
										<td><s:property value="practitionerName" /></td>
									</s:else>
									
									<s:if test="whoPay=='Client'">
										<!--<td><s:property value="invoicee" /> (Self)</td>
										
									--> 
									
										<td>(Self)</td>
									</s:if>
									<s:else>
										<td><s:property value="invoicee" /> (Third Party)</td>
									</s:else>
									
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="paymentmode" /><s:if test="paymentNote==''"> </s:if><s:else>(<s:property value="paymentNote"/>)</s:else></td>
									</s:if>
									<s:else>
										<td><s:property value="paymentmode" /><s:if test="paymentNote==''"> </s:if><s:else>(<s:property value="paymentNote"/>)</s:else></td>
									</s:else>
									<%if(loginfo.getClinicUserid().equals("aureus")){ %>
										<s:if test="cancelsts==1">
											<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="debitAmountx" /></b></td>
										</s:if>
										<s:else>
											<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="debitAmountx" /></b></td>
										</s:else>
										
										<s:if test="cancelsts==1">
											<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="discAmmount" /></b></td>
										</s:if>
										<s:else>
											<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="discAmmount" /></b></td>
										</s:else>
										
										<s:if test="cancelsts==1">
											<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="refundAmountx" /></b></td>
										</s:if>
										<s:else>
											<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="refundAmountx" /></b></td>
										</s:else>
										
										<s:if test="cancelsts==1">
											<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="balancex" /></b></td>
										</s:if>
										<s:else>
											<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="balancex" /></b></td>
										</s:else>
									<%} %>
									<s:if test="cancelsts==1">
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="amountx" /></b></td>
									</s:if>
									<s:else>
										<td style="color:green;text-align: right;"><b><%=Constants.getCurrency(loginfo) %><s:property value="amountx" /></b></td>
									</s:else>
								</tr>
								<s:iterator value="MasterassesmentList">	
								 <tr class="totalbor">
								 	<td style="border: none;"></td>
	                                <td style="border: none;"></td>	
	                                <td style="border: none;"></td>
	                                <td style="border: none;"></td>	
	                                <td style="border: none;"><b style="color: #a94442;"><s:property value="Masterchargetype"/></b></td>
	                                <td style="border: none;color: #999;">CHARGE NAME</td>
	                                <td style="border: none;"></td>
	                            </tr>
	                             <s:iterator value="Assesmentlist">
	                             <tr>
	                             	<td style="border: none;"></td>
	                             	<td style="border: none;"></td>
								 	<td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;color: #333;"><s:property value="appointmentType"/></td>
	                                <td style="border: none;"></td>
	                             </tr>
							 </s:iterator>	
							 </s:iterator>
						<%-- 		<s:iterator value="masterAssessmentList">	
								 <tr class="totalbor">
								 	<td style="border: none;"></td>
	                                <td style="border: none;"></td>	
	                                <td style="border: none;"></td>
	                                <td style="border: none;"></td>	
	                                <td style="border: none;"><b style="color: #a94442;"><s:property value="name"/></b></td>
	                                <td style="border: none;color: #999;">CHARGE NAME</td>
	                                <td style="border: none;"></td>
	                            </tr>
	                             <s:iterator value="assesmentList">
	                             <tr>
	                             	<td style="border: none;"></td>
	                             	<td style="border: none;"></td>
								 	<td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;color: #333;"><s:property value="appointmentType"/></td>
	                                <td style="border: none;"></td>
	                             </tr>
							 </s:iterator>	
							 
	                           </s:iterator> --%>
	                           
								<tr class="hidden">
	                             	<td style="border: none;"></td>
								 	<td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;color: #333;text-align:right;">SubTotal : </td>
	                                <td style="border: none;text-align: right;">00.00</td>
	                             </tr>
							</s:iterator>
						</s:if>
						
						<%-- <s:iterator value="Masterassesmentlist">	
								 <tr class="totalbor">
								 	<td style="border: none;"></td>
	                                <td style="border: none;"></td>	
	                                <td style="border: none;"></td>
	                                <td style="border: none;"></td>	
	                                <td style="border: none;"><b style="color: #a94442;"><s:property value="name"/></b></td>
	                                <td style="border: none;color: #999;">CHARGE NAME</td>
	                                <td style="border: none;"></td>
	                            </tr>
	                             <s:iterator value="Assesmentlist">
	                             <tr>
	                             	<td style="border: none;"></td>
	                             	<td style="border: none;"></td>
								 	<td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;"></td>
	                                <td style="border: none;color: #333;"><s:property value="appointmentType"/></td>
	                                <td style="border: none;"></td>
	                             </tr>
							 </s:iterator>	
							 </s:iterator> --%>
								<!-- Advance List -->
							<s:if test="advanceInvoiceList.size!=0">
							<tr style="background-color: #f2dede;">
								<td colspan="14">Advance Payment</td>
							</tr>
							<s:iterator value="advanceInvoiceList" status="rowstatus">
								<tr id="<s:property  value="id" />">
										<td><%if(loginfo.isBalgopal() || loginfo.isSeq_no_gen()){ %>(<s:property  value="bghseqId"  />)<%} %><s:property  value="id" /><%if(loginfo.isSeq_no_gen()){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<%-- <td><s:property value="advno"/></td> --%>
									<td><s:property value="userid"/></td>
									<td></td>
									<td><s:property value="date" /></td>
									<td><s:if test="cancelsts==1"><s:property value="clientName" />(<%if(loginfo.isSeq_no_gen()){%><s:property value='physical_payment_id'/><%}else{ %><s:property value="receiptid" /><%} %>)</s:if>
									<s:else>
									<s:property value="clientName" />
									</s:else>
									
									</td>	
									<td></td>
									<td><s:property value="clientName" /> (Self)</td>	
									
									
									<td><s:property value="paymentmode" /><s:if test="remark!=''">(<s:property value='remark'/>)</s:if></td>
									<%if(loginfo.getClinicUserid().equals("aureus")){ %>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									<%} %>
									<td ><%=Constants.getCurrency(loginfo) %><s:property value="charges" /></td> 
									
									<!--<td><s:property value="userid"/></td>
									
								--></tr>
									
									

							</s:iterator>
						</s:if>
						
						
							<!-- Cancelled Advance List -->
							<s:if test="cancelledInvoiceShifts.size!=0">
							<tr style="background-color: #f2dede;">
								<td colspan="14">Cancelled Invoices Advance Payment</td>
							</tr>
							<s:iterator value="cancelledInvoiceShifts" status="rowstatus">
								<tr id="<s:property  value="id" />">
										<td><%if(loginfo.isBalgopal() || loginfo.isSeq_no_gen()){ %>(<s:property  value="bghseqId"  />)<%} %><s:property  value="id" /><%if(loginfo.isSeq_no_gen()){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<%-- <td><s:property value="advno"/></td> --%>
									<td><s:property value="userid"/></td>
									<td></td>
									<td><s:property value="date" /></td>
									<td><s:if test="cancelsts==1"><s:property value="clientName" />(<%if(loginfo.isSeq_no_gen()){%><s:property value='physical_payment_id'/><%}else{ %><s:property value="receiptid" /><%} %>)</s:if>
									<s:else>
									<s:property value="clientName" />
									</s:else>
									
									</td>	
									<td></td>
									<td><s:property value="clientName" /> (Self)</td>	
									
									
									<td><s:property value="paymentmode" /><s:if test="remark!=''">(<s:property value='remark'/>)</s:if></td>
									<%if(loginfo.getClinicUserid().equals("aureus")){ %>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									<%} %>
									<td ><%=Constants.getCurrency(loginfo) %><s:property value="charges" /></td> 
									
									<!--<td><s:property value="userid"/></td>
									
								--></tr>
									
									

							</s:iterator>
						</s:if>
						
						
						<%-- <%if(!loginfo.isBalgopal()){ %>
							<s:if test="refundPaymentList.size!=0">
							<tr style="background-color: #f2dede;">
								<td colspan="14">Refund Payment</td>
							</tr>
							<s:iterator value="refundPaymentList" status="rowstatus">
								<tr id="<s:property  value="id" />">
								<td><s:property  value="id" /><%if(loginfo.isSeq_no_gen()&&(loginfo.getClinicUserid().equals("aureus")||loginfo.getClinicUserid().equals("nelson"))){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<td><s:property value="refid"/></td>
									<td>
										<s:if test="advref==1">
											<s:if test="manualinvoiceid==0">
												<s:property value="userid"/>
											</s:if>
											<s:else>
												<s:property value="userid"/>&nbsp;|&nbsp; [Invoice ID:<s:property value="invoicenameid"/>/<s:property value="ipdopdseq"/>]
											</s:else>
										</s:if>
										<s:else>
											<s:property value="userid"/>
										</s:else>
									</td>
									<td></td>
									
									<td><s:property value="date" /></td>
									<td><s:property value="clientName" /></td>	
									<td></td>
									<td><s:property value="clientName" /> (Self)</td>	
								
									
									<td>Cash</td>
									<%if(loginfo.getClinicUserid().equals("aureus")){ %>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									<%} %>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx" /></td>
									<!--<td><s:property value="userid"/></td>
									
								--></tr>
									
									

							</s:iterator>
						</s:if> --%>
							<%-- <%}else{ %> --%>
								<!-- oPD REFUND -->
							<s:if test="opdRefundList.size!=0">
							<tr style="background-color: #f2dede;">
								<td colspan="14">OPD Refund Payment</td>
							</tr>
							<s:iterator value="opdRefundList" status="rowstatus">
								<tr id="<s:property  value="id" />">
								<td><%if(loginfo.isBalgopal() || loginfo.isSeq_no_gen()){ %>(<s:property  value="bghseqId"  />)<%} %><s:property  value="id" /><%if(loginfo.isSeq_no_gen()){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<%-- <td><s:property value="refid"/></td> --%>
									<td><s:property value="userid"/></td>
									<td></td>
									
									<td><s:property value="date" /></td>
									<td><s:property value="clientName" /></td>	
									<td></td>
									<td><s:property value="clientName" /> (Self)</td>	
								
									
									<td><s:property value="Paymentmode" /></td>
									<%if(loginfo.getClinicUserid().equals("aureus")){ %>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									<%} %>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx" /></td>
									<!--<td><s:property value="userid"/></td>
									
								--></tr>
									
									

							</s:iterator>
						</s:if>
						
						<!-- IPD REFUND -->
						<s:if test="ipdRefundList.size!=0">
							<tr style="background-color: #f2dede;">
								<td colspan="14">IPD Refund Payment</td>
							</tr>
							<s:iterator value="ipdRefundList" status="rowstatus">
								<tr id="<s:property  value="id" />">
								<td><%if(loginfo.isBalgopal() || loginfo.isSeq_no_gen()){ %>(<s:property  value="bghseqId"  />)<%} %><s:property  value="id" /><%if(loginfo.isSeq_no_gen()){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<%-- <td><s:property value="refid"/></td> --%>
									<td><s:property value="userid"/></td>
									<td></td>
									
									<td><s:property value="date" /></td>
									<td><s:property value="clientName" /></td>	
									<td></td>
									<td><s:property value="clientName" /> (Self)</td>	
								
									
									<td><s:property value="Paymentmode" /></td>
									<%if(loginfo.getClinicUserid().equals("aureus")){ %>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									<%} %>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx" /></td>
									<!--<td><s:property value="userid"/></td>
									
								--></tr>
									
									

							</s:iterator>
						</s:if>
						
						<s:if test="invoiceList.size!=0">
							<table class="my-table xlstable" style="width: 100%">
					<col width="10%">
					<col width="7%">
					<col width="15%">
					<col width="30%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<thead>
						<tr>
							<th >
								
							Invoice Date </th>
							<th>Invoice</th>
							<th>Patient</th>
							<s:if test="payby=='Client'">
							<th>Payee </th>
							</s:if>
							<s:else>
								<th>Payee </th>
							</s:else>
							<th>Status</th>
							<th style="text-align: right;">Debit</th>
							<th style="text-align: right;">Credit</th>
							<th style="text-align: right;">Discount</th>
							<th style="text-align: right;">Balance</th>
							
						</tr>
					</thead>
					<tbody>
						<s:if test="invoiceList.size!=0">
							<s:iterator value="invoiceList" status="rowstatus">
								<tr >
									<td>  <s:property value="date" /></td>
									<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/> <%}else{%> <s:property value="id" /><%} %></td>
								<%-- 	<td><s:property value="id" /><a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a></td>
										 --%>
									<td><s:property value="clientName" /></td>		
									<td><%-- <s:property value="payby" /> (<s:property value="payeeName"/>) --%>
									<s:if test="payby=='Client'">
											Self
										</s:if>
										<s:else>
											Third Party
										</s:else>
									</td>
									<s:if test="balance==0">
										<td>Paid</td>
									</s:if>

									<s:else>
										
										<td>Not Paid</td>

									</s:else>
									
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitAmountx" /></td>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="creditTotalx" /></td>
									<td style="text-align: right;">(<%=Constants.getCurrency(loginfo) %><s:property value="discAmmount"/>) <s:property value="discount" />%</td>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="balancex" /></td> 
									
								<tr id="hiddenDetailsDiv<s:property value="id"/>"
									style="display: none" aria-hidden="true">
									<td colspan="7" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
					</tbody>

				</table>
						</s:if>
						
						
							<%-- <%} %> --%>
							
							<tr class="hidden">
								<td colspan="5" style="font-weight: bold;">Total</td>
								<td style="font-weight: bold;"><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx"/></td>
							</tr>
						
						
						
						
					</tbody>

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

	

