<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<style>
	.loc{
		background-color: #6699cc;
		color: white;
	}
</style>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
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
	
	function printBillingReportExcel() {

        $(".tablebilling").table2excel({
					exclude: ".noExl",
					name: "Billing Summary List",
					filename: "billingSummaryList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
</script>


								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Billing Report</h4>

									</div>
								</div>
								<s:form action="billingreportSummary" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
										<div class="form-inline">
											<div class="form-group" style="width:7%;">
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div>
											<%-- <div class="form-group">
												<s:select list="doctorlist" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="name" headerKey="0" headerValue="Select Practitioner"></s:select>
											</div>
											<div class="form-group">
												<s:select list="clientlist" cssClass="form-control chosen-select" name="clientId" id="clientId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Client"></s:select>
											</div> --%>
											<div class="form-group">
												<s:select list="invoicetypelist" cssClass="form-control chosen-select" name="itype" id="itype" listKey="id" listValue="name" headerKey="0" headerValue="Select invoive Type"></s:select>
											</div>
											<div class="form-group">
												<s:select name="paymentmode" id="paymentmode" 
													list="#{'0':'Payee ','1':'Self','2':'Third Party'}"
													cssClass="form-control chosen-select" >
												</s:select>
												</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right">
												<!-- <a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Download Excel</a> -->
												<a href="#"  onclick="printBillingReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" >
				<table class="my-table tablexls hidden-print" id="example" style="width: 100%;font-size: 8px"  >
							<thead>
							<tr class="loc">
								<td>Sr. no</td>
								<td>Patient Name</td>
								<%if(loginfo.getClinicUserid().equals("aureus")){ %>
								<td>Invoice Id</td>
								<%} %>
								<td>Invoice Type</td>
								<td>UID</td>
								<td>Doctor Name</td>
								<td>Admission Date</td>
						 		<td>Discharge Date</td>
						 		<td>Bill No.</td>
						 		<td>Bill Date</td>
						 		<td>Payment Mode</td>
								<td>Bill Amount</td>
								<td>Amount Received</td>
								<td>Balance</td>
								<td>Discount</td>
								<td>Refund</td>
							</tr>
					</thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="bilingreportlist">
					   		<tr>
					     		<td><%=++i %></td>
					     		<td><s:property value="clientName"/></td>
					     		<%if(loginfo.getClinicUserid().equals("aureus")){ %>
								<td><s:property value="id"/></td>
									<%} %>
								<td><s:property value="invoicetype"/></td>
								<td><s:property value="abrivationid"/></td>
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="admissiondate"/></td>
								<td><s:property value="dischargedate"/></td>
								<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id"/><%} %></td>
								<td><s:property value="date"/></td>
								<td><s:property value="paymentmode"/></td>
								<td><s:property value="debitAmount"/></td>
								<td><s:property value="amountx"/></td>
								<td><s:property value="balance"/></td>
								
								<td>
									<s:if test="disctype==0">
									<%if(loginfo.getClinicUserid().equals("aureus")){ %>
									Rs.<s:property value="discountamtt"/>
									<%}else{ %>
									<s:property value="discountx"/>%
									<%} %>
										
										
									</s:if>
									<s:else>
										Rs.<s:property value="discountx"/>
									</s:else>
								</td>
								<td><s:property value="totalref"/></td>
					     	</tr>
					   </s:iterator>
					</tbody>
			 </table>
			 
		</div>
		
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
			<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 90px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
			<h5 class="hidden-lg hidden-md visible-print"><span class="text-uppercase"><b>Billing Report</b> &nbsp;  &nbsp;</span></h5>
			
			 <table class="my-table tablebilling hidden-lg hidden-md visible-print" id = "billingReportTable " style="width: 100%;font-size: 8px;">    
            <thead>
							<tr class="loc">
								<td>Sr. no</td>
								<td>Patient Name</td>
								<%if(loginfo.getClinicUserid().equals("aureus")){ %>
								<td>Invoice Id</td>
								<%} %>
								<td>Invoice Type</td>
								<td>UID</td>
								<td>Doctor Name</td>
								<td>Admission Date</td>
						 		<td>Discharge Date</td>
						 		<td>Bill No.</td>
						 		<td>Bill Date</td>
						 		<td>Payment Mode</td>
								<td>Bill Amount</td>
								<td>Amount Received</td>
								<td>Balance</td>
								<td>Discount</td>
								<td>Refund</td>
							</tr>
					</thead>
					<tbody>
						<%int j=0; %>
						<s:iterator value="bilingreportlist">
					   		<tr>
					     		<td><%=++j %></td>
					     		<td><s:property value="clientName"/></td>
					     		<%if(loginfo.getClinicUserid().equals("aureus")){ %>
								<td><s:property value="id"/></td>
									<%} %>
								<td><s:property value="invoicetype"/></td>
								<td><s:property value="abrivationid"/></td>
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="admissiondate"/></td>
								<td><s:property value="dischargedate"/></td>
								<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id"/><%} %></td>
								<td><s:property value="date"/></td>
								<td><s:property value="paymentmode"/></td>
								<td><s:property value="debitAmount"/></td>
								<td><s:property value="amountx"/></td>
								<td><s:property value="balance"/></td>
								
								<td>
									<s:if test="disctype==0">
									<%if(loginfo.getClinicUserid().equals("aureus")){ %>
									Rs.<s:property value="discountamtt"/>
									<%}else{ %>
									<s:property value="discountx"/>%
									<%} %>
										
										
									</s:if>
									<s:else>
										Rs.<s:property value="discountx"/>
									</s:else>
								</td>
								<td><s:property value="totalref"/></td>
					     	</tr>
					   </s:iterator>
					</tbody>
				</table>
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

<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>


<script>
     $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'colvis' ]
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>



	<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
	