<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Refferal Report List",
					filename: "refferallist",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
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
	document.getElementById('invoicerportfrm').submit();
}


</script>
<style>
.shubh{
font-weight: 800;
}
</style>

<div class="">

							<div class="">
							
							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
							
							
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Reffered By Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="refferedbyreportSummary" theme="simple" id = "invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="col-lg-12 col-md-12 form-inline">
			
							
			<div class="col-lg-2 col-md-2 form-group">
				<s:select name="reffedby" list="referalList" listKey="referal" listValue="referal" cssClass="form-control chosen-select" headerKey="0" headerValue="Select Referred Dr " theme="simple" onchange = "setActionForAll()" ></s:select>
			</div>
			<div class="col-lg-1 col-md-1 form-group" style="width: 9%">
				<s:select cssClass="form-control chosen-select"
						list="#{'0':'IPD', '1':'OPD'}"
						theme="simple" id="itype" name="itype"  />
			</div>
			<div class="col-lg-2 col-md-2 form-group" style="width:10%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="col-lg-1 col-md-1 form-group" style="width:10%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="col-lg-2 col-md-2 form-group" >
				<s:select cssClass="form-control chosen-select"
						list="#{'0':'Addmission Date', '1':'Discharge Date'}"
						theme="simple" id="admissionordischarge" name="admissionordischarge"  />
			</div>
			<div class="col-lg-3 col-md-3 form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
				<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
		
	</div>
	</div>
	</s:form>
	
	<%int i=1; %>
	<table class="my-table xlstable" style="width: 100%">
				<tr><td class="shubh">Sr. No.</td>
					<td class="shubh">Referred by</td>
					<td class="shubh">Patient Name</td>
					<td class="shubh">UHID</td>
					<td class="shubh">Address</td>
					<td class="shubh">Mobile No</td>
					<td class="shubh">Type</td>
					<td class="shubh">Referred to Specialty</td>
					<td class="shubh">Practitioner</td>
					<s:if test="itype==0">
					<td class="shubh">Admission Date</td>
					</s:if>
					<s:else>
					<td class="shubh">Appointment Date</td>
					</s:else>
					<td class="shubh">Discharge Date</td>
					<%if(!loginInfo.isAyushman() || loginInfo.getSuperadminid()==1){ %>
					<td class="shubh">refer Share(%)</td>
					<%} %>
					<td class="shubh">Bill Amount</td>
					<%if(!loginInfo.isAyushman() || loginInfo.getSuperadminid()==1){ %>
					<td class="shubh">Share Amount</td>
					<%} %>
				</tr>
				<s:if test="refferedbylist.size!=0">
					<s:iterator value="refferedbylist" status="rowstatus">
						<tr>
						<td><%=i++ %></td>
						<s:if test="refferedby==0">
							<td>  </td>
						</s:if>
						<s:else>
						<td><s:property value="Referedby"/></td>
						</s:else>
						<td><s:property value="clientname"/></td>
						<td><s:property value="Abrivationid"/></td>
						<td><s:property value="Address"/></td>
						<td><s:property value="Mobno"/></td>
						<td><s:property value="type"/></td>
						<td><s:property value="department"/></td>
						<td><s:property value="practitionerName"/></td>
						<td> <s:property value="admissiondate"/></td>
						<td><s:property value="Dischargedate"/></td>
						<%if(!loginInfo.isAyushman() || loginInfo.getSuperadminid()==1){ %>
						<td> <s:property value="ReferedshareAmt"/></td>
						<%} %>
						<td> <s:property value="payment"/></td>
						<%if(!loginInfo.isAyushman() || loginInfo.getSuperadminid()==1){ %>
						<td> <s:property value="ShareAmt"/></td>
						<%} %>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					No Record Found!!
				</s:else>
		<tr style="background-color: #009cd2;color: white;"><td></td><td></td><td colspan="12" style="text-align: center;padding-left: 61%;"><b>Total Amount :&emsp;&emsp;&emsp;&emsp;&emsp; <label style="margin-right: 5%;"><s:property value="TotalBillAmt"/></label></b>
		&emsp;&emsp;&emsp;&emsp;<b><label style="margin-right: 0%;"><s:property value="TotalShareAmt"/></label></b></td></tr>
				
	</table><br>

										
									</div>
								</div>
							</div>
						</div>


<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>


