<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	LoginInfo loginfo = LoginHelper.getLoginInfo(request);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<link rel="icon" href="assets/img/favicon.ico">
<title>Advance Practice Management</title>

<script type="text/javascript" src="expence/js/expencelist.js"></script>
<script type="text/javascript" src="expence/js/expencemanagement.js"></script>

<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">

<!--Bootstrap select css-->
<link href="_assets/css/priscription/bootstrap-select.css"
	rel="stylesheet" />

<style>
.wrapperfixed {
	display: inline-block;
	margin-top: 35px;
	padding: 0px 0px 0px;
	width: 100%;
	height: 600px;
}

.pharna {
	margin-left: 0px;
}

.details {
	margin-top: -39px;
	margin-bottom: 0px;
	color: #FFF;
}

.martop2 {
	margin-top: 4px;
}

.addvoucher {
	margin-top: 6px;
	float: left;
	margin-bottom: 7px;
}

.amtwidth {
	width: 35%;
}

.form-horizontal .control-label {
	text-align: right;
}

.paddingnil {
	padding-left: 0px;
	padding-right: 0px;
}

.vno {
	width: 85px;
}

.marlftmin4 {
	margin-left: -4px;
}

.payrece {
	background-color: rgba(230, 230, 230, 0.69);
	padding: 0px 10px 10px 17px;
	margin-top: -11px;
}

.marleft10 {
	margin-left: 10px;
}

.searbiox {
	margin-left: 15px;
	margin-top: -10px;
}

.martops {
	margin-top: -10px;
}

.datecol1 {
	width: 13%;
}

.satuscol2 {
	width: 9%;
}

.merchantcol3 {
	width: 31%;
}

.paidcol4 {
	width: 11%;
}

.categorycol5 {
	width: 24%;
}

.tdline {
	padding: 4px 3px 8px 5px !important;
}

.form-control {
	background-color: #FFF !important;
}

.padleamout {
	padding-left: 0px;
	padding-right: 10px;
}

.padnil {
	padding-left: 0px;
	padding-right: 0px;
}

hr {
	margin-top: 5px;
	margin-bottom: 10px;
	border: 0;
	border-top: 1px solid #efefef;
}

.form-control {
	border: 1px solid #ddd;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	padding: 4px 5px 3px 5px !important;
}
</style>


</head>

<script>
 $(document).ready(function() {

  $("#date").datepicker({

   dateFormat : 'dd/mm/yy',
   yearRange: yearrange,
   minDate : '30/12/1880',
   changeMonth : true,
   changeYear : true

  }); 
  
  $("#datev").datepicker({

	   dateFormat : 'dd/mm/yy',
	   yearRange: yearrange,
	   minDate : '30/12/1880',
	   changeMonth : true,
	   changeYear : true

	  }); 
	  
 
  
  $("#todate").datepicker({

	   dateFormat : 'dd/mm/yy',
	   yearRange: yearrange,
	   minDate : '30/12/1880',
	   changeMonth : true,
	   changeYear : true

	  }); 
	
  
  
  
  $("#hdnfrmdate").datepicker({

	   dateFormat : 'dd-mm-yy',
	   yearRange: yearrange,
	   minDate : '30/12/1880',
	   changeMonth : true,
	   changeYear : true
	  }); 
  
  $("#fromdate").datepicker({

	   dateFormat : 'dd/mm/yy',
	   yearRange: yearrange,
	   minDate : '30/12/1880',
	   changeMonth : true,
	   changeYear : true
	  }); 
	  
  
  var dd = document.getElementById('hdnfrmdate').value; 
 /*  var tmp = dd.split('/');
  var dte = tmp[0] + '/' + tmp[1] + '/' + tmp[2]; */
  document.getElementById('datev').value=dd;
  
  $("#edtdate").datepicker({

	   dateFormat : 'dd-mm-yy',
	   yearRange: yearrange,
	   minDate : '30/12/1880',
	   changeMonth : true,
	   changeYear : true

	  }); 
  
  
 
  
      
  
 });

</script>

<body style="width: 100%">


	<!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
	<!--main content start-->
	<%--   <section>
            <section class="wrapperfixed"> --%>

	<!-- page start-->
	<div class="">
		<!--   <aside> -->
		<div class="row">
			<div class="col-md-12 col-lg-12">
				<div class="  ">


					<h4 style="padding: 8px; font-weight: bold;">Voucher Dashboard</h4>




				</div>

				<!-- Accounts Common Menu -->

				<div class="row">
					<div
						class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose hidden">
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
							<a href="#">
								<button onclick="opencPopup('ExpenceManagement')" type="button"
									class="btn btn-success">Create Voucher</button>
							</a>
						</div>



						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
							<a href="#">
								<button onclick="opencPopup('ledgAppointmentType')"
									type="button" class="btn btn-success">Create New
									Ledger</button>
							</a>
						</div>

						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
							<a href="#">
								<button onclick="showLedgerAccount()" type="button"
									class="btn btn-success">View Ledger Report</button>
							</a>
						</div>

						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
							<a href="#">
								<button onclick="opencPopup('aheadAppointmentType')"
									type="button" class="btn btn-success">Add New Ledger
									Head</button>
							</a>
						</div>

						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
							<a href="#">
								<button onclick="opencPopup('ocrptsAppointmentType')"
									type="button" class="btn btn-success">Opening /
									Closing Report</button>
							</a>
						</div>

						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
							<a href="#">
								<button onclick="opencPopup('tbAppointmentType')" type="button"
									class="btn btn-success">Trial Balance</button>
							</a>
						</div>

					</div>

					<div class="row hidden">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
							<hr />
						</div>
					</div>






					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
						<div class=" ">
							<div class="">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2"
									style="padding: 0px 0px 0px 0px !important;">
									<s:form action="ExpenceManagement?action=0" theme="simple"
										id="viewldgerfrm">
										<s:hidden name="hdnfrmdate" id="hdnfrmdate" />
										<div class="col-lg-12 col-md-12">
											<div class="form-inline">
												<div class="form-group">
													<s:textfield title="From Date"
														cssClass="form-control addvoucher" name="fromdate"
														readonly="true" placeholder="From Date" id="fromdate" />
												</div>
												<div class="form-group">
													<s:textfield title="To Date"
														cssClass="form-control addvoucher" name="todate"
														readonly="true" placeholder="To Date" id="todate" />
												</div>
												<div class="form-group">
													<%--  <select id="paymentmode" class="form-control chosen-select" name="paymentmode">
							                                 <option value="">Payment Mode</option>
							                                 <option value="Cash">Cash</option>
							                                 <option value="Cheque">Cheque</option>
							                                 <option value="Credit/Debit Card">Credit/Debit Card</option>
							                                 <option value="Online Transfer /ECS">Online Transfer /ECS</option>
							                             </select> --%>
													<%--  <s:select cssClass="form-control chosen-select"  list="#{'Cash':'Cash','Cheque':'Cheque','2':'Cheque','Credit/Debit Card':'Credit/Debit Card','Online Transfer /ECS':'Online Transfer /ECS'}" name="mainpaymentmode" headerKey="0"  headerValue="Select Payment Mode" ></s:select> --%>
													<s:select name="mainpaymentmode" id="mainpaymentmode"
														list="#{'0':'Select Voucher','Payment':'Payment','Receipt':'Receipt','Contra':'Contra','Journal':'Journal Voucher'}"
														cssClass="form-control chosen-select" onchange=""></s:select>
													<%-- 	<s:hidden name="paymentmodevalue" id="paymentmodevalue"></s:hidden> --%>

												</div>
												<div class="form-group">
													<%-- <select name="expenseType" id="expenseType" class="form-control chosen-select">
														    <option value="">Expense Type</option>
														    <option value="DELIVERY, COURIER">DELIVERY, COURIER</option>
														    <option value="GIFTS">GIFTS</option>
														    <option value="INSURANCE">INSURANCE</option>
														    <option value="LODGING/HOTEL">LODGING/HOTEL</option>
														    <option value="MAINTENANCE AND REPAIRS">MAINTENANCE AND REPAIRS</option>
														    <option value="MEALS AND ENTERTAINMENT">MEALS AND ENTERTAINMENT</option>
														    <option value="OTHER">OTHER</option>
														    <option value="OFFICE EQUIPMENT AND SUPPLIES">OFFICE EQUIPMENT AND SUPPLIES</option>
														    <option value="PARKING">PARKING</option>
														    <option value="PETROL">PETROL</option>
														    <option value="POSTAGE">POSTAGE</option>
														    <option value="TAXI">TAXI</option>
														    <option value="TELEPHONE &amp; BROADBAND">TELEPHONE &amp; BROADBAND</option>
														    <option value="TRAVEL TICKET">TRAVEL TICKET</option>
														    <option value="UTILITY BILL">UTILITY BILL</option>
														    <option value="CONVEYANCE CHARGES">CONVEYANCE CHARGES</option>
														    <option value="POST OFFICE CHARGES">POST OFFICE CHARGES</option>
														    <option value="PRINTING &amp; STATIONARY CHARGES">PRINTING &amp; STATIONARY CHARGES</option>
														    <option value="TRAVELLING CHARGES">TRAVELLING CHARGES</option>
														    <option value="CARTING CHARGES">CARTING CHARGES</option>
														    <option value="MEETING EXPENSES">MEETING EXPENSES</option>
														    <option value="STAFF WELFARE CHARGES">STAFF WELFARE CHARGES</option>
														    <option value="REFUND CHARGES">REFUND CHARGES</option>
														    <option value="GENERATOR DIESEL EXPENSES">GENERATOR DIESEL EXPENSES</option>
														    <option value="ADVERTISEMENT EXPENSE">ADVERTISEMENT EXPENSE</option>
														    <option value="NEWSPAPER CHARGES">NEWSPAPER CHARGES</option>
														    <option value="HOUSE KEEPING MATERIAL CHARGES">HOUSE KEEPING MATERIAL CHARGES</option>
														    <option value="PERSONAL EXPENSE">PERSONAL EXPENSE</option>
														    <option value="MOBILE BILL">MOBILE BILL</option>
														</select> --%>
													<s:select onchange="showsupplierledaccount()"
														list="categories" listKey="name" listValue="name"
														id="expenseType" cssClass="form-control chosen-select"
														name="expenseType" headerKey="0"
														headerValue="Select Expense Type">
													</s:select>

													<%-- <s:hidden name="expensetypevalue" id="expensetypevalue"></s:hidden> --%>
												</div>
												<div class="form-group">
													<%--  <select id="range" class="form-control chosen-select" name="range">
							                                 <option value="">Amt Range</option>
							                                 <option value="<1000">Below 1000</option>
							                                 <option value="2500 AND 5000">2500 - 5000</option>
							                                 <option value="5000 AND 10000">5000 - 10000</option>
							                                 <option value=">10000">Above 10000</option>
							                             </select> --%>

													<%-- <s:select cssClass="form-control chosen-select"  list="#{'<1000':'Below 1000','2500 AND 5000':'2500 - 5000','5000 AND 10000':'5000 - 10000','>10000':'Above 10000'}" name="range"  headerValue="Select Range" headerKey="0"></s:select> --%>
													<s:select cssClass="form-control chosen-select"
														list="#{'1':'Below 1000','2':'2500 - 5000','3':'5000 - 10000','4':'Above 10000'}"
														name="range" headerValue="Select Range" headerKey="0"></s:select>
													<%-- <s:hidden name="rangevalue" id="rangevalue"></s:hidden> --%>

												</div>
												<div class="form-group">
													<!-- <button type="button" onclick="getSortedList()" class="btn btn-primary addvoucher" id="btngo">GO</button> -->
													<button type="submit" class="btn btn-primary addvoucher"
														id="btngo">GO</button>
												</div>
												<div class="form-group hidden" style="float: right;">
													<!-- <button type="button" class="btn btn-primary addvoucher" data-toggle="modal" data-target="#exampleModal">Add</button> -->
													<button type="button" class="btn btn-primary addvoucher"
														onclick="addExpensesPopUp()">Add</button>
												</div>

											</div>
										</div>
									</s:form>
									<s:form action="reportExpenceManagement" method="post"
										id="expencereportfrm" theme="simple">
										<s:hidden name="totalExpenceCheckbox"
											id="totalExpenceCheckbox" />
										<div class="col-lg-12 col-md-12"
											style="background-color: #fff; padding: 0px;">
											<div class="form-inline">
												<div class="form-group">
													<input type="text" placeholder="Enter report name"
														class="form-control addvoucher" name="reportName"
														id="reportName">
												</div>
												<a href="#" onclick="showLedgerAccountdata()">
													<button onclick="" type="button" class="btn btn-success">View
														Ledger Account</button>
												</a> <a href="#" class="btn btn-success"
													onclick="refreshlegdersetcode()">Refresh</a>
											</div>
										</div>
									</s:form>
								</div>

							</div>
							<div id="tableheight2">
								<table class="table table-bordered" cellspacing="0" width="100%">
									<thead>
										<tr class="tableback">
											<th style="width: 3%;"><label
												class="checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
													id="selecctall" type="checkbox" value=""><i
													style="border-color: #fff; color: #fff;"></i></label></th>
											<th class="" style="width: 8%;">Date</th>

											<th class="" style="width: 25%;">Particulars</th>
											<th class="" style="width: 6%;">Vch Type</th>
											<th class="" style="width: 6%;">Vch No</th>
											<!--  <th class="satuscol2">Satus</th> -->
											<th class="" style="width: 31%;">Name / Note</th>
											<!-- <th class="" style="width: 7%;">Status</th> -->
											<th class="" style="width: 7%;">Debit</th>
											<!-- <th style="width: 5%;">Currency</th> -->
											<th class="text-right" style="width: 8%;">Credit</th>
											<!-- <th>PDF</th> -->
											<th style="width: 5%;"></th>

										</tr>
									</thead>
									<tfoot
										style="background-color: rgba(239, 239, 239, 0.58); color: green;">
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>

											<td style="font-size: 13px; font-weight: bold;">Total</td>
											<td style="font-size: 13px; font-weight: bold;" class=""><%=Constants.getCurrency(loginfo)%><s:property
													value="ctotal" /></td>
											<td style="font-size: 13px; font-weight: bold;"
												class="text-right"><%=Constants.getCurrency(loginfo)%><s:property
													value="expenceTotal" /></td>

											<td></td>
										</tr>
									</tfoot>
									<tbody>
										<s:hidden name="date" id="defaultdate"></s:hidden>
										<s:if test="expenceList.size>0">
											<s:iterator value="expenceList">
												<tr>
													<td><label
														class="checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
															type="checkbox" class="case"
															value="<s:property value="id"/>"><i></i></label></td>
													<td><s:property value="caldate" /></td>

													<s:if test="xpayment == 'Opening'">
														<td>Opening Balance</td>
														<td></td>
														<td></td>
													</s:if>
													<s:else>

														<td><s:property value="category" /></td>
														<td><s:property value="xpayment" /></td>
														<td><s:property value="parantid" /></td>

													</s:else>
													<!--   <td>Completed</td> -->
													<td><s:property value="merchant" /> / <s:property
															value="comments" /></td>
													<!-- <td>Unpaid</td> -->
													<td><%=Constants.getCurrency(loginfo)%><s:property
															value="credit" /></td>
													<%-- <td><s:property value="currency"/></td> --%>
													<s:if test="status!='cancel'">
														<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property
																value="amount" /></td>
													</s:if>
													<s:else>
														<td class="text-right"
															style="text-decoration: line-through;"><%=Constants.getCurrency(loginfo)%><s:property
																value="credit" /></td>
													</s:else>

													<!--  <td><center><a href="" title="Create pdf"><i class="fa fa-file-pdf-o"></i></a></center> </td> -->
													<td><s:if test="status!='cancel'">
															<a style="cursor: pointer; text-align: center"
																onclick="openPopup('printvoucherExpenceManagement?id=<s:property value="id"/>')"
																href="#" title="Print"><i class="fa fa-print"
																aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<a
																style="cursor: pointer; text-align: center"
																onclick="editPaymentVoucherNew(<s:property value="id"/>)"
																title="Edit"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;&nbsp;
                                                         	 <a
																style="cursor: pointer; text-align: center" href="#"
																title="Delete"
																onclick="deleteExpenceManagement(<s:property value="id"/>)"><i
																class="fa fa-times text-danger" aria-hidden="true"></i></a>
															<%--  <td class="text-center"><a href="#" onclick="deleteExpenceManagement(<s:property value="id"/>)" class="text-danger">  --%>
														</s:if></td>

												</tr>
											</s:iterator>
										</s:if>
									</tbody>
								</table>
							</div>
							<!-- Modal -->
							<div id="deletemodel" class="modal fade" role="dialog">
								<div class="modal-dialog modal-sm">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Are You Sure To Cancel?</h4>
										</div>
										<div class="modal-body">
											<input type="hidden" id="cancelexpenseid">
											<textarea rows="3" class="form-control" id="delete_reason"
												placeholder="Cancel Reason" style="background-color: beige;"></textarea>
										</div>
										<div class="modal-footer">
											<input type="button" class="btn btn-danger"
												onclick="deleteExpenceDetails()" data-dismiss="modal"
												value="Ok">
										</div>
									</div>

								</div>
							</div>
							<%-- <div class="col-lg-12 col-md-12 col-xs-12" style="border-top: 1px dashed #ddd;padding-top: 10px;">
                                            	<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10"></div>
                                            	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="padding:0px;">
                                            		<div class="form-group" style="margin:0px;color:green;">
                                            			<label style="font-weight: bold;width: 100%;">Total Amount : <span style="float: right;">Rs.500.00</span></label>
                                            		</div>
                                            		<div class="form-group">
                                            			<select id="" class="form-control chosen-select" name="paymentmode">
							                                 <option value="">Payment Mode</option>
							                                 <option value="Cash">Cash</option>
							                                 <option value="Cheque">Cheque</option>
							                                 <option value="Credit/Debit Card">Credit/Debit Card</option>
							                                 <option value="Online Transfer /ECS">Online Transfer /ECS</option>
							                             </select>
                                            		</div>
                                            		<div class="form-group text-right">
                                            			<textarea name="notes" cols="" rows="3" id="notes" class="form-control" style="height: 50px !important;" placeholder="Write Note"></textarea>
                                            		</div>
                                            		<div class="form-group text-right">
                                            			<a href="#" class="btn btn-primary">PAID</a>
                                            		</div>
                                            	</div>
                                            </div> --%>


						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- </aside> -->
	</div>
	<!-- page end-->
	<%--   </section>
        </section> --%>
	<!-- /MAIN CONTENT -->
	<!--main content end-->

	<%-- <s:form action="ExpenceManagement" name="paginationForm" id="paginationForm"
	theme="simple">

	<div class="row">
		<div class="col-lg-4 col-md-4 text-right">
			Showing all <label class="text-info">(<s:property
					value="pagerecords" /> of <s:property value="totalRecords" />
				Records)
			</label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>

</s:form> --%>


	<!-- Payment Voucher BedModal -->
	<div class="modal fade modal-draggable" id="exampleModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">

		<s:form action="saveExpenceManagement" theme="simple" id="saveexpfrm">

			<s:hidden name="editaction" id="editaction" />
			<s:hidden name="editledgername" id="editledgername" />


			<s:hidden name="epayment" id="epayment" />
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" aria-label="Close"
							onclick="govcreload()">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="headselectedvoucherid">Payment
							Voucher</h4>
					</div>
					<div class="modal-body">

						<div class="row" id="upperrowvoucherid">
							<div class="col-lg-12">

								<div class="col-lg-4" id='ledgernamediv'>
									<s:select disabled="" name="ledgername" id="ledgername"
										list="ledgerList" listKey="id" listValue="name" headerKey="0"
										headerValue="Select Ledger"
										cssClass="form-control chosen-select"
										onchange="showSelectedServices()" />
								</div>



								<div class="col-lg-2 hidden">
									<s:select name="pmode" id="pmode"
										list="#{'0':'Select Payment Mode','Cash':'Cash','Cheque':'Cheque','Credit/Debit':'Credit/Debit','Online Transfer':'Online Transfer'}"
										cssClass="form-control chosen-select"
										onchange="setActionForAll()"></s:select>
								</div>

								<div class="col-lg-2 hidden" id="paymnttodivid">
									<s:textfield name="paymantto" id="paymantto"
										cssClass="form-control" Placeholder="Enter Paid To Name" />
								</div>

								<div class="col-lg-2" id="contradivid">
									<s:select name="contratrans" id="contratrans"
										list="#{'0':'Select Transaction','1':'Deposit','2':'Withdraw'}"
										cssClass="form-control chosen-select"
										onchange="setActionForAll()"></s:select>
								</div>
							</div>

						</div>
						<br />
						<div class="row" id="paymntvchrrowid">
							<div class="col-lg-12">

								<div class="col-lg-4 hidden" id="newdebtorsdivid">
									<s:select name="debitornametxt" id="debitornametxt"
										list="debitorList" listKey="id" listValue="name" headerKey="0"
										headerValue="Select paid to" cssClass="form-control hidden"
										onchange="" />

								</div>

								<%-- <div class="col-lg-2">
				<s:textfield name="dbitamt" id="dbitamt" cssClass="form-control"
					Placeholder="Enter Debit Amt"
				/>
			</div> --%>
								<div class="col-lg-4">
									<s:textfield placeholder="Enter New Paid To" name="debiorname"
										id="debiorname" cssClass="form-control" />
								</div>
								<div class="col-lg-1 hidden">
									<input onclick="addNewDebitors()" type="button"
										value=" Add Debitors" class="btn btn-primary" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="col-lg-3">
									<label>Total Amt.</label>
									<div id="vendortotalamt">0.00</div>
								</div>
								<div class="col-lg-3">
									<label>Total Paid.</label>
									<div id="vendorpaidamt">0.00</div>
								</div>
								<div class="col-lg-3">
									<label>Balance Return Amt.</label>
									<div id="vendorreturnamt">0.00</div>
								</div>
								<div class="col-lg-3">
									<label>Balance Amt.</label>
									<div id="vendorbalanceamt">0.00</div>
								</div>
							</div>
						</div>
						<br />
						<div class="">
							<table class="table table-bordered" cellspacing="0" width="100%"
								id="tableexpence">

								<col width="10%" />
								<col width="20%" />
								<col width="18%" />
								<col width="18%" />
								<col width="18%" />
								<col width="10%" />
								<col width="6%" />

								<thead>
									<tr class="tableback">
										<th class="">Date</th>
										<th class="">Particulars</th>
										<!--  <th class="">Paid to</th> -->
										<th>Description</th>
										<!--  <th>Paid By</th> -->
										<th class="">Debit</th>
										<th class="">Credit</th>


										<th class="">Currency</th>
										<th>Delete</th>

									</tr>
								</thead>


								<tbody>
									<tr>
										<td class="tdline"><s:textfield readonly="true"
												name="voucher[0].caldate" id="datev"
												cssClass="form-control casedate" theme="simple"></s:textfield></td>
										<td class="tdline" id="expencetypetdid">
											<%--  <s:select list="categories" listKey="name" listValue="name"  id="category" cssClass="form-control choosen-select"  name="voucher[0].category" headerKey="0"
                            
                            headerValue="Select Expense Type">
                                 
                             </s:select> --%> <s:select disabled=""
												name="voucher[0].category" id="category" list="categories"
												listKey="name" listValue="name" headerKey="0"
												headerValue="Select Ledger"
												cssClass="form-control chosen-select"
												onchange="showVendorBalance(this.value)" /> <!-- onchange="showSelectedServices()" -->
										</td>


										<td style="display: none;" class="tdline"><input
											type="text" class="form-control" name="voucher[0].merchant"></td>

										<td class="tdline"><input type="text"
											class="form-control" name="voucher[0].comments"
											id="comments1"></td>

										<td class="tdline" style="display: none;"><select
											id="basic" class="form-control choosen-select"
											name="voucher[0].paidby">
												<option>Please Select</option>
												<option>Cash</option>
												<option>Cheque</option>
												<option>Credit/Debit Card</option>
												<option>Online Transfer /ECS</option>
										</select></td>
										<td class="tdline"><input onchange="showcvcredittotal()"
											type="text" name="voucher[0].credit"
											class="form-control caseccc" id="credit0"></td>

										<td class="tdline"><input onchange="showcvdbittotal()"
											type="text" name="voucher[0].amount"
											class="form-control caseddd" id="debit0"></td>
										<td class="tdline"><s:select list="currencies"
												listKey="name" listValue="name" id="basic"
												cssClass="form-control" name="voucher[0].currency"
												data-live-search="true" /></td>

										<td class="text-center" id="tdbutton0"><a href="#"
											onclick="deleteRow(this)"> <i class="fa fa-minus-circle"
												style="color: #c50404; font-size: 17px; padding-top: 2px;"
												aria-hidden="true"></i>
										</a></td>
									</tr>

								</tbody>
							</table>
						</div>
						<br />
						<div class="row" id="jvrowid">
							<div class="col-lg-12">
								<hr style="border-top: 4px solid #efefef;" />
								<table class="table table-bordered" cellspacing="0" width="100%"
									id="tableexpence">
									<col width="10%" />
									<col width="20%" />
									<col width="18%" />
									<col width="18%" />
									<col width="18%" />
									<col width="10%" />
									<col width="6%" />
									<tbody>
										<tr>

											<td></td>
											<td></td>
											<td></td>

											<td style="font-weight: bold; font-size: 16px; text-align:;:"><%=Constants.getCurrency(loginfo)%><span
												id="creditspanid">0.00</span></td>
											<td style="font-weight: bold; font-size: 16px; text-align:;"><%=Constants.getCurrency(loginfo)%><span
												id="dbitspanid">0.00</span></td>

											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<%-- <s:submit cssClass="btn btn-primary" value="Save" /> --%>
						<input type="button" value="Save" onclick="saveexpmngmntdata()"
							class="btn btn-primary"> <span id="adnewbtnid"><button
								type="button" class="btn btn-primary"
								onclick="addRow('tableexpence')">Add New line</button></span>
						<button type="button" class="btn btn-primary"
							onclick="govcreload()">Close</button>
					</div>
				</div>
			</div>
		</s:form>
	</div>
	<!-- /.modal -->



	<div class="modal fade" id="selectvouchermodelid" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">

		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">Select Voucher</h4>
				</div>
				<div class="modal-body">

					<div class="row">
						<div class="col-sm-12">

							<s:select name="xpayment" id="xpayment"
								list="#{'0':'Select Payment Type','Payment':'Payment','Receipt':'Receipt','Contra':'Contra','Journal':'Journal Voucher'}"
								cssClass="form-control"
								onchange="showselectedvouchrpopup(this.value)"></s:select>
						</div>

					</div>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>

	</div>



	<!-- Edit Payment Voucher BedModal -->
	<div class="modal fade" id="editModel" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<s:form action="updateExpenceManagement" theme="simple">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">Payment
							Voucher</h4>
					</div>
					<div class="modal-body">



						<div class="">
							<table class="table table-bordered" cellspacing="0" width="100%"
								id="tableexpence">
								<thead>
									<tr class="tableback">
										<th class="">Date</th>
										<th class="">Particulars</th>

										<th class="">Merchant</th>
										<th>Comment</th>
										<th>Paid with</th>
										<th class="">Currency</th>
										<th class="">Amount</th>

									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="tdline"><s:textfield readonly="true"
												name="caldate" id="edtdate" cssClass="form-control"
												theme="simple"></s:textfield></td>
										<td class="tdline"><s:select list="categories"
												listKey="name" listValue="name" id="edtcat"
												cssClass="form-control" data-live-search="true"
												name="category" headerKey="0"
												headerValue="Select Expense Type">

											</s:select></td>


										<td class="tdline"><input type="text"
											class="form-control" name="merchant" id="edtmerchant"></td>
										<td class="tdline"><input type="text"
											class="form-control" name="comments" id="edtcomments"></td>

										<td class="tdline"><select class="form-control"
											data-live-search="true" name="paidby" id="edtpaidby">
												<option>Please Select</option>
												<option>Cash</option>
												<option>Cheque</option>
												<option>Credit/Debit Card</option>
												<option>Online Transfer /ECS</option>
										</select></td>
										<td class="tdline hidden"><s:select list="currencies"
												listKey="name" listValue="name" id="edtcurrency"
												cssClass="form-control" name="currency"
												data-live-search="true" /></td>
										<td class="tdline"><input type="text" name="amount"
											class="form-control" id="edtamt"></td>

										<s:hidden id="edtid" name="id" />
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<s:submit cssClass="btn btn-primary" value="Update" />
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>

		</s:form>
	</div>
	<!-- /.modal -->
	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);"
		id="dashboardloaderPopup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo"
						style="margin-left: auto; margin-right: auto;"></img>

				</div>
			</div>
		</div>
	</div>

	<!--Bootstrap select-->
	<script src="_assets/js/bootstrap-select.js"></script>


	<script src="common/chosen_v1.1.0/chosen.jquery.js"
		type="text/javascript"></script>
	<script src="common/chosen_v1.1.0/docsupport/prism.js"
		type="text/javascript" charset="utf-8"></script>
	<script
		src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="common/chosen_v1.1.0/chosen.jquery.js"
		type="text/javascript"></script>
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
	$(function() {
	  $('#tableheight2').slimScroll({
	        height: '400px',
	        railVisible: true,
			alwaysVisible: true
	  });
 	});
</script>



</body>
</html>
