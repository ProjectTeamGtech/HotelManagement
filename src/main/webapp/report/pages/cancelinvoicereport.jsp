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
	@media print {
		.loc{
			background-color: #6699cc  !important;
			color: white;
		}
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
	function printcancelInvoiceReportExcel() {

        $(".tablecancelinvoice").table2excel({
					exclude: ".noExl",
					name: "Cancel Invoice List",
					filename: "cancelinvoice",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
</script>


								<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Cancel Invoice Report</h4>

									</div>
								</div>
								<s:form action="cancelinvoicereportSummary" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2 hidden-print">
										<div class="form-inline">
											<div class="form-group" style="width:7%;">
											<label>From date</label>
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
											<label>To date</label>
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div>
											
											<div class="form-group" style="width:11%;">
											<label>Invoice Type</label>
											<%--< s:select name='ipdopd' list="#{'':'All','2':'Ipd','1':'Opd'}"
												cssClass="form-control chosen-select" ></s:select> --%>
												<s:select list="invoicetypelist" cssClass="form-control chosen-select" name="itype" id="itype" listKey="id" listValue="name" headerKey="" headerValue="Select invoive Type"></s:select>
											</div>
											<%-- <div class="form-group">
												<s:select list="doctorlist" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="name" headerKey="0" headerValue="Select Practitioner"></s:select>
											</div>
											<div class="form-group">
												<s:select list="clientlist" cssClass="form-control chosen-select" name="clientId" id="clientId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Client"></s:select>
											</div> --%>
											<div class="form-group" style="margin-left: 10px;margin-top: 7px;">
											<br>
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right">
												
												<a href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right">
												<!-- <a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a> -->
												<a href="#" class="btn btn-warning" onclick="printpage()">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
								<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 90px;">
									<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
											 <link href="common/css/printpreview.css" rel="stylesheet" />
										<%@ include file="/accounts/pages/letterhead.jsp" %>
										</div>
									</div>
								</div>
								
								<div class="row details print-visible hidden-md hidden-lg">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Cancel Invoice Report</h4>

									</div>
								</div>
								<div style="padding-top: 10px;" class="print-visible hidden-md hidden-lg"></div>
				<table class="my-table tablecancelinvoice"  style="width: 100%;font-size: 8px"  >
							<thead>
							<tr class="loc">
								<td>Sr. no</td>
								<td>Patient Name</td>
								<td>UID</td>
								<td>Doctor Name</td>
								<td>Admission Date</td>
						 		<td>Discharge Date</td>
						 		<td>Bill No.</td>
						 		<td>Bill Date</td>
								<td>Bill Amount</td>
								<td>Cancel Note</td>
								<td>Cancel Userid</td>
								<td>Cancel Date</td>
							</tr>
					</thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="cancelinvoicereportlist">
					   		<tr>
					     		<td><%=++i %></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="abrivationid"/></td>
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="admissiondate"/></td>
								<td><s:property value="dischargedate"/></td>
								<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id"/><%} %></td>
								<td><s:property value="date"/></td>
								<td><s:property value="debitAmount"/></td>
								<td><s:property value="cancelNotes"/></td>
								<td><s:property value="cancelUserid"/></td>
								<td>
									<s:property value="cancelDT"/>
								</td>
					     	</tr>
					   </s:iterator>
					</tbody>
			 </table>
		</div>

<%-- <table class="my-table tablecancelinvoice" id = "cancelinvoiceReportTable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
		    <tr>
		   
								<th>Sr. no</th>
								<th>Patient Name</th>
								<th>UID</th>
								<th>Doctor Name</th>
								<th>Admission Date</th>
						 		<th>Discharge Date</th>
						 		<th>Bill No.</th>
						 		<th>Bill Date</th>
								<th>Bill Amount</th>
								<th>Cancel Note</th>
								<th>Cancel Userid</th>
								<th>Cancel Date</th>
		    </tr>
		    </thead>
             <tbody>
             
            
            <s:iterator value="cancelinvoicereportlist">
             
            	<tr>
					     		<td><%=++i %></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="abrivationid"/></td>
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="admissiondate"/></td>
								<td><s:property value="dischargedate"/></td>
								<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="id"/><%} %></td>
								<td><s:property value="date"/></td>
								<td><s:property value="debitAmount"/></td>
								<td><s:property value="cancelNotes"/></td>
								<td><s:property value="cancelUserid"/></td>
								<td>
									<s:property value="cancelDT"/>
								</td>
					     	</tr>
             </s:iterator>
</table> --%>

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



	