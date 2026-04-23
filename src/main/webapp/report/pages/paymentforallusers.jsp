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




 
 					
						
						
						
						<div class="">
							<div class="">
								<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Payment Report</h4>
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
	
		<%if(loginfo.getJobTitle().equals("Admin")){%>
			<div class="col-lg-1 col-md-1 hidden">
			<label>Category</label>
				<s:select name="invoicecategory" id="invoicecategory" 
					list="#{'2':'All','0':'Primary','1':'Secondary' }"
					cssClass="form-control"></s:select>
			</div>
		<%} %>
		
		<div class="col-lg-1 col-md-1">
			<label>Payed by</label>
			<s:select name="payby" id="payby" 
				list="#{'0':'All','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control" ></s:select>
		</div>
		
		<div class="col-lg-1 col-md-1 ">
				<label>Payment</label>
				
					<s:select name="howpaid" id="howpaid" 
				list="#{'0':'All','BACS':'BACS','Cheque':'Cheque','C/Card':'C/Card','Cash':'Cash','D/D':'D/D','Other':'Other','S/O':'S/O','prepayment':'Pre-Payment'}"
				cssClass="form-control" ></s:select>
				
				<%-- <select name="howpaid" id="howpaid" class="form-control" onchange="setActionForAll()">
					<option value="0">All</option>
					<option value="BACS">BACS</option>
					<option value="Cheque">Cheque</option>
					<option value="C/Card">C/Card</option>
					<option value="Cash">Cash</option>
					<option value="D/Card">D/Card</option>
					<option value="D/D">D/D</option>
					<option value="Other">Other</option>
					<option value="S/O">S/O</option>
					<option value="<%=Constants.PREPYMENT %>">Pre-Payment</option>
			
			
			</select>  --%>
		
		</div>
		
		<div class="col-lg-1 col-md-2">
		 <label>From Date</label>
			<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		
		<div class="col-lg-1 col-md-2">
			<label>To Date</label>
			<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		
		<div class="col-lg-2 col-md-2 hidden">
			<label>Select User</label>
			<%if(loginfo.getJobTitle().equals("Admin")){%>
				<s:select list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			<%}else{ %>
				<s:select disabled="true" list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			<%} %>
		</div>
		
		
		
		<div class="col-lg-2 col-md-2 hidden">
			<label>Select Invoice</label>
			 <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="width:100%;">Select Invoice Type <span class="caret"></span></button>
												<ul class="dropdown-menu">
													<s:iterator value="invoiceTypeLis">
												 	 <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" class="pacss" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												  </s:iterator>
												 
												</ul>
		</div>
			<div class="col-lg-3 col-md-3 text-left hidden-print" style="margin-top: 21px;">
				<h4 class="hidden" style="margin-top:0px;margin-bottom:0px;color: green;"><a href="javascript: void(0);" onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="chargeid"/>','<s:property value="chargeid"/>','<s:property value ="payby"/>');">Expand All</a> &nbsp; | &nbsp; <a href="#">Collapse All</a></h4>
				<!-- <input type="button" value="Go"  class="btn btn-primary" onclick="setActionForAll()"> -->
				<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
				<a type="button" title="Save as PDF" onclick="return saveAsPdfPaymentReport();" class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a>
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
				<input type="button" value="All User Print"  class="btn btn-primary" onclick="setActionForAllUser()">
			</div>
		</div>
		
			
			
			
			
		</div>
		<%int i=0; %>
		<s:iterator value="maserUserList">
		<h3><s:property value="userid"/></h3>
		<div class="col-lg-12 col-md-12 col-xs-12 topback2" style="text-align: center;padding: 4px 0px 9px 0px;background-color: rgba(217, 237, 247, ">
		<h3 class="text-center hidden-lg hidden-md hidden-sm visible-print" style="border-bottom: 4px double #ddd;line-height: 38px;margin:0px;">Payment Report</h3>
		
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">OPD</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="opdtotal"/></span></h4>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">IPD</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="ipdtotal"/></span></h4>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">Pathlab</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="pathlabtotal"/></span></h4>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;mAdvance Paymentargin-bottom:0px;"><span class="titsetas">Medicine</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="mdcinetotal"/></span></h4>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;"><span class="titsetas">Adv & Ref</span><br> <span><%=Constants.getCurrency(loginfo) %><s:property value="advreftotal"/></span></h4>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<h4 style="margin-top:0px;margin-bottom:0px;color: green;"><span class="titsetas">Total <a href="#" data-toggle="collapse" data-target="#detailview"><i class="fa fa-arrow-down" style="color: chocolate;" aria-hidden="true"></i></a></span><br> 
				<span><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx"/></span>
				- <span><%=Constants.getCurrency(loginfo) %><s:property value="totalref"/></span>
				= <span><%=Constants.getCurrency(loginfo) %><s:property value="calcdebittotal"/></span>
				</h4>
			</div>
		
		</div>
		
		
		
		<div id="detailview" class="">
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
					
					<p style="margin: 0px;">
						<span>D/Card </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="crtotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>C/Card </span>
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
		</s:iterator>
		<div class="row hidden-print">
	 	<div class="col-lg-1 col-md-1" style="display: none" id = "previewPaymentRpt">
		<!-- 	<a href="liveData/chargesReport/PaymentReport.pdf" class="btn btn-primary" target = "blank">Preview</a> -->
			<input style="margin-top: 21px;" type="button" value="Send PDF in Mail" onclick="return openSendMailPaymentRptPopup();" class="btn btn-primary">
		</div>	 
		</div>
	
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

	

