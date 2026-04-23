<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="report/js/chargesReport.js"></script>

<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
	<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
	
<%
	LoginInfo loginfo = LoginHelper.getLoginInfo(request);
%>
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
<script>
	function printExcel() {

		$(".tablexls").table2excel({
			exclude : ".noExl",
			name : "Charges Report List",
			filename : "chargesReport",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}

	$(document).ready(function() {

		$("#fromDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange : yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange : yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true
		});
	});

	function setActionForAll() {
		//document.getElementById('invoicerportfrm').submit();
	}

	function setSorting(column, order) {
		if (order == 'asc') {
			order = 'desc';
		} else {
			order = 'asc';
		}
		document.getElementById('orderby').value = column;
		document.getElementById('order').value = order;
		document.getElementById('invoicerportfrm').submit();
	}

	$("#thirdParty").trigger("chosen:updated");
	$(".chosen").chosen({
		allow_single_deselect : true
	});

	bkLib.onDomLoaded(function() {

		new nicEditor().panelInstance('chargesReportEmailBody');
		$('.nicEdit-panelContain').parent().width('500px');
		$('.nicEdit-panelContain').parent().next().width('500px');

		$('.nicEdit-main').width('100%');
		$('.nicEdit-main').height('80px');
	});
</script>
<script>
	function submitChargeReport() {
		var fromdate = document.getElementById("fromDate").value;
		var todate = document.getElementById("toDate").value;
		if (fromdate == '') {
			jAlert('error', "Please enter from date!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else if (todate == '') {
			jAlert('error', "Please enter to date!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} else {
			//parts[1] + "/" + parts[0] + "/" + parts[2]
			var parts = fromdate.split("/");
			fromdate = parts[1] + "/" + parts[0] + "/" + parts[2];

			var parts1 = todate.split("/");
			todate = parts1[1] + "/" + parts1[0] + "/" + parts1[2];

			var date1 = new Date(fromdate);
			var date2 = new Date(todate);
			var timeDiff = Math.abs(date2.getTime() - date1.getTime());
			var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
			if (diffDays >= 31) {
				jAlert('error', "Date differnce is greater one month!",
						'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			} else {
				document.getElementById("invoicerportfrm").submit();
			}

		}

	}
</script>


<div class="">
	<div class="">
	<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 90px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
		<div class="row details">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>Charges Report</h4>

			</div>
		</div>
		<div class="row ">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
				<div>


					<s:form action="ChargesRpt" theme="simple" id="invoicerportfrm">
						<s:hidden name="order" id="order" />
						<s:hidden name="orderby" id="orderby" />
						<div class="col-lg-12 col-md-12 topback2 hidden-print">

							<div class="form-inline">
								<div class="form-group">
									<s:select name="payby" id="payby"
										list="#{'All':'Show All Paid By','Client':'Self','Third Party':'Third Party'}"
										cssClass="form-control chosen-select"
										onchange="setActionForAll()"></s:select>
								</div>
								<div class="form-group">
									<s:select id="thirdParty" name="thirdParty" listKey="id"
										onchange="setActionForAll()" multiple=""
										listValue="thirdParty" headerKey="0" headerValue="Show All TP"
										list="thirdPartyList"
										cssClass="form-control showToolTip chosen-select"></s:select>
								</div>
								<div class="form-group">
									<s:select name="invoiceStatus" id="invoiceStatus"
										list="#{'0':'Show All Status','Invoiced':'Invoiced','Not Invoiced':'Not Invoiced'}"
										cssClass="form-control chosen-select"
										onchange="setActionForAll()"></s:select>
								</div>
								<div class="form-group" style="width: 7%;">
									<s:textfield readonly="true" name="fromDate" id="fromDate"
										cssClass="form-control" theme="simple" placeholder="from date"
										style="width:100%;"></s:textfield>
								</div>
								<div class="form-group" style="width: 7%;">
									<s:textfield readonly="true" name="toDate" id="toDate"
										cssClass="form-control" theme="simple" placeholder="to date"
										style="width:100%;"></s:textfield>
								</div>
								<div class="form-group">
									<s:select cssClass="form-control showToolTip chosen"
										id="condition" name="condition" list="conditionList"
										listKey="id" listValue="treatmentType" headerKey="0"
										theme="simple" headerValue="Select Speciality" />
								</div>
								<div class="form-group">
									<%-- <s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit> --%>
									<a href="#" class="btn btn-primary"
										onclick="submitChargeReport()">Go</a> <a type="button"
										class="btn btn-primary" title="Print" onclick="printpage()"><i
										class="fa fa-print"></i></a> <a type="button" title="Save As PDF"
										onclick="return saveAsPdfChargesReport();"
										class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a> <a
										type="button" id="btnxls" title="Save As XLS"
										onclick="printExcel()" class="btn btn-primary btnxls"><i
										class="fa fa-file-excel-o"></i></a>
								</div>
							</div>




							<div class="col-lg-1 col-md-1" style="display: none"
								id="previewChargeRpt">
								<%
									String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
										String temp[] = currentDate.split(" ");
										String temptime[] = currentDate.split("at");
										String temp1[] = temp[0].split("-");
										String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
										String time = temptime[1];
								%>
							</div>

						</div>


						<!-- <div class="col-lg-1 col-md-1" style="display: none" id = "sendMailChargeRpt">
				<input type="button" value="Send PDF in Mail" onclick="return openSendMailChargeRptPopup();" class="btn btn-primary">
			</div> -->

						<s:if test="list!=null">

							<table class="my-table tablexls" id="example"
								style="width: 100%;">
								<thead>
									<tr class="loc">
										<td style="width: 5%">Date <a href="#"
											onclick="setSorting('tpcommencing','<s:property value="order"/>')"><i
												class="fa fa-sort"></i></a></td>
										<td style="width: 6%">Charge No</td>
										<td style="width: 7%">Charge Type</td>
										<td style="text-align: center;">UHID</td>
										<td style="text-align: center;">Patient</td>
										<td>Status</td>

										<s:if test="payby=='Client'">
											<td style="width: 5%">Payee <a href="#"
												onclick="setSorting('firstname','<s:property value="order"/>')"><i
													class="fa fa-sort"></i></a></td>
										</s:if>
										<s:else>
											<td style="width: 5%">Payee <a href="#"
												onclick="setSorting('company_name','<s:property value="order"/>')"><i
													class="fa fa-sort"></i></a></td>
										</s:else>
										<td>Department <a href="#"
											onclick="setSorting('location','<s:property value="order"/>')"><i
												class="fa fa-sort"></i></a></td>
										<td style="text-align: center;">Description</td>
										<td>Practitioner</td>
										<td style="width: 6%">Ward Name</td>
										<td style="text-align: right;">Debit</td>
										<td style="text-align: right;">Credit</td>
										<td style="text-align: right;">Balance <a href="#"
											onclick="setSorting('sum(charge)','<s:property value="order"/>')"><i
												class="fa fa-sort"></i></a>
										</td>
									</tr>
								</thead>
								<tbody>
									<s:if test="list.size>0">
										<s:iterator value="list">

											<tr id="<s:property value="invoiceid"/>">
												<td><s:property value="commencing" /></td>

												<td>(00<s:property value="invoiceid" />) <a
													href="javascript: void(0);"
													onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><i
														class="fa fa-arrow-down"></i></a>

												</td>
												<td><s:property value="chargeType" /></td>
												<td><s:property value="abrivationid" /></td>
												<td><s:property value="clientName" /></td>

												<s:if test="chargesInvoiced==true">
													<td>Invoiced</td>
												</s:if>
												<s:else>
													<td>Not Invoiced</td>
												</s:else>

												<s:if test="whoPay=='Self'">
													<td><s:property value="whoPay" /></td>
												</s:if>
												<s:else>
													<td><s:property value="whoPay" /> (<s:property
															value="tpName" />)</td>
												</s:else>



												<td><s:property value="locationName" /></td>
												<td><s:property value="appointmentType" /></td>
												<td><s:property value="practitionerName" /></td>
												<s:if test="ward==''">
													<td style="text-align: center;">-</td>
												</s:if>
												<s:else>
													<td style="text-align: center;"><s:property value="ward" /></td>
												</s:else>
												<td style="text-align: right;"><%=Constants.getCurrency(loginfo)%><s:property
														value="debitTotalx" /></td>
												<td style="text-align: right;"><%=Constants.getCurrency(loginfo)%><s:property
														value="creditTotalx" /></td>
												<td style="text-align: right;" class="text-danger"><%=Constants.getCurrency(loginfo)%><s:property
														value="balanceTotalx" /></td>


											</tr>



											<tr id="hiddenDetailsDiv<s:property value="invoiceid"/>"
												style="display: none">
												<td colspan="8"
													id="hiddenDetailsDiv1<s:property value="invoiceid"/>">
												</td>
											</tr>

										</s:iterator>
										<tr style="background-color: rgba(239, 239, 239, 0.51);">
											<td colspan="10"></td>
											<td style="text-align: right; font-size: 12px;"><b>Total</b></td>
											<td style="text-align: right; font-size: 12px;"><b><%=Constants.getCurrency(loginfo)%>
													<s:property value="debitTotalx" /></b></td>
											<td style="text-align: right; font-size: 12px;"><b><%=Constants.getCurrency(loginfo)%>
													<s:property value="creditTotalx" /></b></td>
											<td style="text-align: right; font-size: 12px; color: green;"><b><%=Constants.getCurrency(loginfo)%>
													<s:property value="balanceTotalx" /></b></td>
										</tr>
									</s:if>
									<s:else>
										<h3 class="text-center">
											<i class="fa fa-times text-danger"></i> There is no Record
											found!!
										</h3>
									</s:else>
								</tbody>
							</table>
						</s:if>
					</s:form>

					<!-- Modal Email-->
					<div class="modal fade" id="sendEmailChargeRptPopup" tabindex="-1"
						role="dialog" aria-labelledby="lblsendEmailPopUp"
						aria-hidden="true">
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
												<div class="col-lg-1 col-md-1"></div>

											</div>
											<div class="form-group">
												<label>To:</label>
												<s:textfield theme="simple" id="chargesReportEmail"
													name="email" cssClass="form-control showToolTip"
													placeholder="Enter email address of receiver"
													title="Enter Email Id" data-toggle="tooltip" />
											</div>
											<div class="form-group">
												<label>Cc:</label>
												<s:textfield theme="simple" id="chargesReportccEmail"
													name="ccEmail" cssClass="form-control showToolTip"
													title="Enter Cc" data-toggle="tooltip"
													placeholder="Enter Cc" />
											</div>
											<div class="form-group">
												<label>Subject:</label> <input type="text" name="subject"
													id="chargesReportSubject" class="form-control showToolTip"
													data-toggle="tooltip" title="Enter Subject"
													placeholder="Enter Subject">
											</div>
											<div class="form-group">
												<label>Body:</label>
												<textarea class="form-control showToolTip"
													data-toggle="tooltip" rows="20" cols="60"
													title="Enter Body" name="emailBody"
													id="chargesReportEmailBody"></textarea>
											</div>
											<div class="form-group">
												<s:property value="filename" />
												<span style="margin-left: 3px;"><a
													href="invoice/<s:property value="filename"/>"
													target="blank"><i
														class="fa fa-file-pdf-o fa-2x text-danger"
														title="Attached PDF"></i></a></span>
											</div>
											<div class="form-group" id="pdfChargesReportMailId"
												style="display: none;"></div>
											<div class="form-group">
												<button type="button" class="btn btn-primary"
													onclick="sendChargesReportMail();">Send</button>
												<button type="button" class="btn btn-primary"
													data-dismiss="modal">Close</button>
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

<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/chosen_v1.1.0/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var config = {
		'.chosen-select' : {},
		'.chosen-select-deselect' : {
			allow_single_deselect : true
		},
		'.chosen-select-no-single' : {
			disable_search_threshold : 10
		},
		'.chosen-select-no-results' : {
			no_results_text : 'Oops, nothing found!'
		},
		'.chosen-select-width' : {
			width : "100%"
		}
	}
	for ( var selector in config) {
		$(selector).chosen(config[selector]);
	}
</script>


<script>
	$(document).ready(
			function() {
				var table = $('#example').DataTable({
					lengthChange : false,
					buttons : [ 'excel', 'colvis' ]
				});

				table.buttons().container().appendTo(
						'#example_wrapper .col-sm-6:eq(0)');
			});
</script>



<script type="text/javascript"
	src="pharmacy/searchexport/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
<script type="text/javascript"
	src="pharmacy/searchexport/dataTables.buttons.js"></script>
<script type="text/javascript"
	src="pharmacy/searchexport/buttons.bootstrap.js"></script>
<script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
<script type="text/javascript"
	src="pharmacy/searchexport/buttons.html5.js"></script>
<script type="text/javascript"
	src="pharmacy/searchexport/buttons.colVis.js"></script>

