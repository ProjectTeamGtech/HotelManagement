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
	
	function printDiscountReportExcel() {

        $(".tablexls").table2excel({
					exclude: ".noExl",
					name: "Discount Report List",
					filename: "discountSummaryList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
</script>


								<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Discount Report</h4>

									</div>
								</div>
								<s:form action="discountreportSummary" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2 hidden-print">
										<div class="form-inline">
											<div class="form-group" style="width:7%;">
											<label>From Date :</label>
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
											<label>To Date :</label>
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:10%;">
											<label>Invoice Type</label>
											<s:select name="itype" id="itype" list="invoicetypelist" headerKey="" headerValue="All" listKey="id" listValue="name" cssClass="form-control"></s:select>
											</div>
											<%-- <div class="form-group">
												<s:select list="doctorlist" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="name" headerKey="0" headerValue="Select Practitioner"></s:select>
											</div>
											<div class="form-group">
												<s:select list="clientlist" cssClass="form-control chosen-select" name="clientId" id="clientId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Client"></s:select>
											</div> --%>
											<div class="form-group">
											<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right">
												
												<a href="#"  onclick="printDiscountReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right">
												<!-- <a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a> -->
												<a href="#" class="btn btn-warning" onclick="printpage()">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12 tablexls" style="padding: 0px;" id="printableArea">
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

										<h4>Discount Report</h4>

									</div>
								</div>
								<div style="padding-top: 10px;" class="print-visible hidden-md hidden-lg"></div>
			<s:if test="discountreportlist.size>0">
				<table class="my-table "  style="width: 100%;font-size: 8px"  >
							
							<tr class="loc">
								<td>Sr. no</td>
								<td>Patient Name</td>
								<td>UID</td>
								<td>Doctor Name</th>
								<td>Admission Date</td>
						 		<td>Discharge Date</th>
						 		<td>Bill No.</td>
						 		<td>Bill Date</td>
								<td>Bill Amount</td>
								<td>Amount Received</td>
								<td>Refund Amount</td>
								<td>Balance</td>
								<td>Discount</td>
								
							</tr>
					
					<tbody>
						<%int i=0; %>
						
						<s:iterator value="discountreportlist">
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
								<td><s:property value="amountx"/></td>
								<td><s:property value="refundamountbyid"/></td>
								<td><s:property value="balance"/></td>
								<td>
									<s:if test="disctype==0">
										<s:property value="discountx"/>%(Rs.<s:property value="discountbyrs"/>)
									</s:if>
									<s:else>
										Rs.<s:property value="discountx"/>
									</s:else>
								</td>
								
					     	</tr>
					   </s:iterator>
					   <tr  style="background-color:grey;">
					   <td></td>
					   <td></td>
					   <td></td>
					   <td></td>
					   <td></td>
					   <td></td>
					   <td></td>
					   <td style="font-size: 13px;height:5px;color:white;">Total</td>
					   <td style="font-size: 13px;height:5px;color:white;"><b><s:property value="totalbillamount"/></b></td>
					   <td style="font-size: 13px;height:5px;color:white;"><b><s:property value="totalrecammount"/></b></td>
					   <td style="font-size: 13px;height:5px;color:white;">Rs.<b><s:property value="totalrefundamount"/></b></td>
					   <td></td>
					   <td style="font-size: 13px;height:5px;color:white;">Rs.<b><s:property value="totaldiscountammount"/></b></td>
					   
					   </tr>
					 
					   
					</tbody>
			 </table>
			   </s:if>
			   <s:if test="discountchargereportlist.size>0">
			 <h5 style="background-color: #905faf;color: white;font-weight: 800;">Charge Discount</h5>
			 <table class="my-table "  style="width: 100%;font-size: 8px"  >
			 <thead>
			 <tr class="loc">
								<td>Sr. no</td>
								<td>Patient Name</td>
								<td>UID</td>
								<td>Admission Date</td>
						 		<td>Discharge Date</td>
								<td>Charge Amount</td>
								<td>Discount</td>
								<td>After Discount</td>
								
							</tr>
			 </thead>
			 <tbody>
					  
							<%int j=0; %>
						
						<s:iterator value="discountchargereportlist">
					   		<tr>
					     		<td><%=++j %></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="abrivationid"/></td>
								<td><s:property value="admissiondate"/></td>
								<td><s:property value="dischargedate"/></td>
								<td><s:property value="debitAmount"/></td>
								<td>
									<s:if test="disctype==0">
										<s:property value="discountx"/>%(Rs.<s:property value="discountbyrs"/>)
									</s:if>
									<s:else>
										Rs.<s:property value="discountx"/>
									</s:else>
								</td>
								<td><s:property value="amountx"/></td>
								
					     	</tr>
					   </s:iterator>
					   
			 </tbody>
			 </table>
			 </s:if>
		</div>
<%-- <table class="my-table tablediscount" id = "billingReportTable " style="width: 100%;font-size: 8px;display:none;">    
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
								<th>Amount Received</th>
								<th>Balance</th>
								<th>Discount</th>
		    </tr>
		    </thead>
             <tbody>
             
            
            <s:iterator value="discountreportlist">
             
            	<tr>
					     		<td><%=++i %></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="abrivationid"/></td>
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="admissiondate"/></td>
								<td><s:property value="dischargedate"/></td>
								<td><s:property value="id"/></td>
								<td><s:property value="date"/></td>
								<td><s:property value="debitAmount"/></td>
								<td><s:property value="amountx"/></td>
								<td><s:property value="balance"/></td>
								<td>
									<s:if test="disctype==0">
										<s:property value="discountx"/>%
									</s:if>
									<s:else>
										Rs.<s:property value="discountx"/>
									</s:else>
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



	