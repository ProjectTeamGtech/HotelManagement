<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="accounts/js/chargeAccountProcessing.js"></script>
<script type="text/javascript" src="report/js/chargesReport.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script>


    function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Auditor Report",
					filename: "Auditor_Report",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});

   }


	$(document).ready(function() {
	
	  $('#selecctall').click(function(event) {  //on click 
			            if(this.checked) { // check select status
			                $('.caseh').each(function() { //loop through each checkbox
			                    this.checked = true;  //select all checkboxes with class "checkbox1"               
			                });
			            }else{
			                $('.caseh').each(function() { //loop through each checkbox
			                    this.checked = false; //deselect all checkboxes with class "checkbox1"                       
			                });         
			            }
			        });

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
	
	
	function setActionForAll(){
		document.getElementById('orderby').value = 'date';
		document.getElementById('invoicerportfrm').submit();
	}
	
	function setSorting(column,order){
		if(order=='asc'){
			order = 'desc';
		}else{
			order = 'asc';
		}
		document.getElementById('orderby').value = column;
		document.getElementById('order').value = order;
		document.getElementById('invoicerportfrm').submit();
	}
	
	$("#thirdParty").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	
	  bkLib.onDomLoaded(function() {
	        
	      	 new nicEditor().panelInstance('invoiceReportEmailBody');
	      	 $('.nicEdit-panelContain').parent().width('500px');
	      	 $('.nicEdit-panelContain').parent().next().width('500px');
	      	 
	      	 $('.nicEdit-main').width('100%');
	      	 $('.nicEdit-main').height('80px');
	    });


	
</script>


						<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Auditor Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<s:form action="auditorChargesRpt" id="invoicerportfrm" theme="simple">

<s:hidden name="hdnprimaryinvoice" id="hdnprimaryinvoice"/>

<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
	
		<%if(loginfo.getJobTitle().equals("Admin")){%>
			<div class="col-lg-2 col-md-2" style="display: none;">
			<label>Invoice Category</label>
				<s:select onchange="showInvoicereportonly()" name="invoicecategory" id="invoicecategory" 
					list="#{'2':'All','0':'Primary','1':'Secondary' }"
					cssClass="form-control chosen-select"></s:select>
			</div>
		<%} %>
		
		<div class="col-lg-2 col-md-2">
		<label>Show Invoice Type</label>
		<s:select name="invcetype" id="invcetype" list="invoiceTypeLis" cssClass="form-control"
				listKey="id" listValue="name" headerKey="0" headerValue="All"/>
		</div>
	
		<div class="col-lg-2 col-md-2">
		<label>Show Payed by</label>
			<s:select name="payby" id="payby" 
				list="#{'0':'All','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control chosen-select" ></s:select>
		</div>
		
		<div class="col-lg-2 col-md-2">
			<label>Show TP</label>
			<s:select id="thirdParty" name="thirdParty" listKey="id" 
				listValue="thirdParty"  headerKey="0" headerValue="All"
				list="thirdPartyList" cssClass="form-control showToolTip chosen-select"></s:select>
		</div>
		
			<div class="col-lg-2 col-md-2">
		<label>Show Payment</label>
			<s:select  name="paymentStatus" id="paymentStatus" 
				list="#{'0':'All','Paid':'Paid','Not Paid':'Not Paid'}"
				cssClass="form-control chosen-select" ></s:select>
		</div>
		<div class="col-lg-1 col-md-1">
			<label>From Date</label>
			<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		
		<div class="col-lg-1 col-md-1">
			<label>To Date</label>
			<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		<div class="col-lg-2 col-md-2">
			<label></label>
			 <input type="button" value="Go"  class="btn btn-primary" style="margin-top:21px;" onclick="showInvoicereportonly()">
			<a type="button" class="btn btn-primary" style="margin-top:21px;" title="Print" onclick="printinvreport()"><i class="fa fa-print"></i></a>
			<!--<a type="button" title="Save As PDF" onclick="return saveAsPdfInvoiceReport();" class="btn btn-primary" style="margin-top: 21px;"><i class="fa fa-file-pdf-o"></i></a>
			--><a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls" style="margin-top: 21px;"><i class="fa fa-file-excel-o"></i></a><!--
			 <input type="button" value="Move Invoices"  class="btn btn-primary" style="margin-top:21px;" onclick="movetosecondary()">-->
		</div>
		</div>
		<div class="row hidden-print">
		<div class="col-lg-1 col-md-1" style="display: none" id = "previewInvoiceRpt">
			<!-- <a href="liveData/chargesReport/InvoiceReport.pdf" class="btn btn-primary" target = "blank" style="margin-top: 21px;">Preview</a> -->
			<input style="margin-top: 21px;" type="button" value="Send PDF in Mail" onclick="return openSendMailInvoiceRptPopup();" class="btn btn-primary">
		</div>
			
		</div>
			
		
		
		<table class="my-table" style="width: 100%">
				<tr>
					<th>Payed By</th>
					<th>Show TP</th>
					<th>Show Payment</th>
					<th>From Date</th>
					<th>To Date</th>
					<th style="text-align: right;">Debit</th>
					<th style="text-align: right;">Credit</th>
					<th style="text-align: right;">Balance</th>
				</tr>
				<tr>
					<s:if test="payby==0">
						<td>All</td>
					</s:if>
					<s:else>
						<td><s:property value="payby"/></td>
					</s:else>
					
					<s:if test="thirdParty==0">
						<td>All</td>
					</s:if>
					<s:else>
						<td><s:property value="tpName"/></td>
					</s:else>
					
					<s:if test="paymentStatus==0">
						<td>All</td>
					</s:if>
					<s:else>
						<td><s:property value="paymentStatus"/></td>
					</s:else>
					<td><s:property value="fromDate"/></td>
					<td><s:property value="toDate"/></td>
					<td style="font-weight: bold;text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="dtotal"/></td>
					<td style="font-weight: bold;text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="ctotal"/></td>
					<td style="font-weight: bold;text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="btotal"/></td>
				</tr>
		</table>
		
		
		<br><br>
		
		
		
				<table class="my-table xlstable" style="width: 100%">
					
					<thead>
						<tr>
							<th id="datesortdid">Invoice Date <a href="#" onclick="setSorting('date','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							<th>Invoice</th>
							<th>Bill No</th>
							<th>Patient</th>
							<th>IPD ID</th>
							<s:if test="payby=='Client'">
							<th>Payee <a href="#" onclick="setSorting('firstname','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							</s:if>
							<s:else>
								<th>Payee <a href="#" onclick="setSorting('company_name','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
							</s:else>
							<th>Status</th>
							<th style="text-align: right;">Debit</th>
							<th style="text-align: right;">Credit</th>
							<th style="text-align: right;">Discount</th>
							<th style="text-align: right;">Balance</th>
							
						</tr>
					</thead>
					<tbody>
						<s:if test="ipdInvoiceList.size!=0">
							<tr style="background-color: rgba(229, 217, 129, 0.46);">
								<td colspan="11">IPD Invoice</td>
							</tr>
							<s:iterator value="ipdInvoiceList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property value="date" /></td>
									<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id" /><%} %></td>
								<%-- 	<td><s:property value="id" /><a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a></td>
										 --%>
									<td><s:property value="resetinv" /></td>	 
									<td><s:property value="clientName" /></td>		
									<td><s:property value='ipdid'/></td>
									<td><s:property value="payby" /> (<s:property value="payeeName"/>)</td>
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
									<td colspan="11" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
						
						
						<!-- OPD List -->
							<s:if test="opdInvoiceList.size!=0">
							<tr style="background-color: rgba(229, 217, 129, 0.46);">
								<td colspan="11">OPD Invoice</td>
							</tr>
							<s:iterator value="opdInvoiceList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property value="date" /></td>
									<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id" /><%} %></td>
									<td><s:property value="resetinv" /></td>
								<%-- 	<td><s:property value="id" /><a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a></td>
										 --%>
									<td><s:property value="clientName" /></td>	
									<td></td>		
									<td><s:property value="payby" /> (<s:property value="payeeName"/>)</td>
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
									<td colspan="11" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
						
<s:if test="vaccinelist.size!=0">
							<tr style="background-color: rgba(241, 122, 122, 0.46);">
								<td colspan="11">Vaccine Invoice</td>
							</tr>
							<s:iterator value="vaccinelist" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property value="date" /></td>
									<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id" /><%} %></td>
									<td><s:property value="resetinv" /></td>
								<%-- 	<td><s:property value="id" /><a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a></td>
										 --%>
									<td><s:property value="clientName" /></td>	
									<td></td>		
									<td><s:property value="payby" /> (<s:property value="payeeName"/>)</td>
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
									<td colspan="11" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
						
							<!-- HD List -->
							<s:if test="hdInvoiceList.size!=0">
							<tr style="background-color: rgba(229, 217, 129, 0.46);">
								<td colspan="11">HD Invoice</td>
							</tr>
							<s:iterator value="hdInvoiceList" status="rowstatus">
								<tr id="<s:property  value="id" />">
								<td><s:property value="date" /></td>
								<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id" /><%} %></td>
								<td><s:property value="resetinv" /></td>
									
									
								<%-- 	<td><s:property value="id" /><a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a></td>
										 --%>
									<td><s:property value="clientName" /></td>	
									<td></td>		
									<td><s:property value="payby" /> (<s:property value="payeeName"/>)</td>
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
									<td colspan="11" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
						
							<!-- Investigation List -->
							<s:if test="invInvoiceList.size!=0">
							<tr style="background-color: rgba(229, 217, 129, 0.46);">
								<td colspan="11">Investigation Invoice</td>
							</tr>
							<s:iterator value="invInvoiceList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property value="date" /></td>
									<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id" /></td><%} %>
									<td><s:property value="resetinv" /></td>
								<%-- 	<td><s:property value="id" /><a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a></td>
										 --%>
									<td><s:property value="clientName" /></td>	
									<td></td>		
									<td><s:property value="payby" /> (<s:property value="payeeName"/>)</td>
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
									<td colspan="11" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
						
						<!-- Advance List -->
							<s:if test="advanceInvoiceList.size!=0">
							<tr style="background-color: rgba(229, 217, 129, 0.46);">
								<td colspan="11">Advance & Ref Invoice</td>
							</tr>
							<s:iterator value="advanceInvoiceList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property value="commencing" /></td>
										<td><s:property value="id" /></td>
									<td><s:property value="resetinv" /></td>
							
									<td><s:property value="clientName" /></td>		
									<td></td>	
									<td>Client (<s:property value="clientName"/>)</td>
									<td>Paid</td>
									
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx" /></td>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="charges" /></td>
									<td style="text-align: right;">(<%=Constants.getCurrency(loginfo) %><s:property value="discAmmount"/>) <s:property value="discount" />%</td>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="balancex" /></td> 
									
								<tr id="hiddenDetailsDiv<s:property value="id"/>"
									style="display: none" aria-hidden="true">
									<td colspan="11" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
						
						
					</tbody>

				</table>
	
</s:form>

<!-- Modal Email-->
<div class="modal fade" id="sendEmailInvoiceRptPopup" tabindex="-1" role="dialog"
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
							<s:textfield theme="simple" id = "invoiceReportEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "invoiceReportccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "invoiceReportSubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="invoiceReportEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfInvoiceReportMailId" style="display: none;">
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="sendInvoiceReportMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
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
  </script>



