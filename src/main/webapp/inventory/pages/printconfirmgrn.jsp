<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
 <%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script type="text/javascript" src="inventory\js\procurement.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<style>
p {
	margin: 0 0 1.5px;
}

.table {
	width: 100%;
	max-width: 100%;
	margin-bottom: 5px;
}
.table {
  border: 0.5px solid #000000;
}
.table-bordered > thead > tr > th,
.table-bordered > tbody > tr > th,
.table-bordered > tfoot > tr > th,
.table-bordered > thead > tr > td,
.table-bordered > tbody > tr > td,
.table-bordered > tfoot > tr > td {
   border: 0.5px solid #000000;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
	line-height: 40px;
}
</style>
<style>
.padright {
	padding-left: 40px;
}

.table.table {
	color: RGBA(85, 85, 85, 0.85);
	background-color: #fff;
}

.comtitle {
	font-size: 13px;
	background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
	color: rgb(255, 255, 255);
}

.marbot25 {
	margin-bottom: 25px;
}

.editcompany {
	float: right;
	font-size: 17px;
	color: #fff;
}

.borright {
	border-right: 1px dashed rgb(192, 192, 192);
}

.buildinglogo {
	width: 60%;
	margin-top: 30px;
}

#sidebar .panel-group .panel>.panel-heading+.panel-collapse>.panel-body
	{
	border-top: 0;
	min-height: auto !important;
}

.miheight {
	min-height: auto !important;
}

.my-table th {
	background-color: #424A5D;
	color: #fff !important;
	border-bottom: 1px solid #DFD8D4;
	border-right: 1px solid #DFD8D4;
	border-top: 1px solid #DFD8D4;
	padding: 3px 3px 4px 5px;
	text-align: left;
	font-weight: bold;
	font-size: 11px;
	background-size: 100% 100%;
}

.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
	.table>thead>tr>td, .table>thead>tr>th {
	padding: 1px 7px 1px 7px !important;
}

.sidebar-xs #header .branding>a {
	background-position: 6px 10px;
	width: 100% !important;
	font-size: 21px;
	padding: 0px 1px 2px 15px;
	text-align: center;
	color: #fff;
}

.sidebar-xs #header .branding>a>span {
	display: inline-block;
}

.sidebar-xs #header .branding {
	width: 100%;
	padding-top: 7px;
	text-align: center;
}

.theight {
	height: 21px;
}
</style>
<%-- <style type="text/css">
@media print {
   .table {
	width: 100%;
	max-width: 100%;
	margin-bottom: 5px;
}
.table {
  border: 0.5px solid #000000;
}
.table-bordered > thead > tr > th,
.table-bordered > tbody > tr > th,
.table-bordered > tfoot > tr > th,
.table-bordered > thead > tr > td,
.table-bordered > tbody > tr > td,
.table-bordered > tfoot > tr > td {
   border: 0.5px solid #000000;
}
}
</style> --%>
<script></script>
<script>
	$(document).ready(function() {

		$("#noteSheetDate").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true,
			maxDate : '0',
		});
		$("#durationFrom").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true,
		});
		$("#durationTo").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true,
		});
		
	});
</script>
</head>
<body>
	<%
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	%>
	<div class="col-lg-12 col-xs-12 col-md-12">
		<div class="row">

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="padding: 0px;">
				<%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="letterHead">
				<h4><s:property value="clinicName"/></h4>
				<h5><s:property value="clinicaddress"/></h5><h5>Website:<s:property value="websiteUrl"/>, Email:<s:property value="email"/>, Contact :<s:property value="landLine"/> </h5>
			</div> --%>
				<div class="print-visible hidden-md hidden-lg letterheadhgt"
					style="height: 80px;">
					<div id="newpost"
						class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
							style="padding-left: 0px; padding-right: 0px; text-align: center">
							<link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp"%>
						</div>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
					<h5 class="text-uppercase">
						<s:if test="isfromworkorder==1">
							<b>Work Order 
								<s:if test="isCancel==1">
									(Cancelled)
								</s:if>
							</b>
						</s:if>
						<s:elseif test="isfromworkorder==2">
							<b><s:property value="amcCmc" />
								<s:if test="isCancel==1">
									(Cancelled)
								</s:if>
							</b>
						</s:elseif>
						<s:else>
							<b>Purchase Order
								<s:if test="isCancel==1">
									(Cancelled)
								</s:if>
							</b>
						</s:else>

					</h5>
				</div>
				<s:hidden name="seconderyletterhead" id="seconderyletterhead"></s:hidden>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<%-- <p class="marboset"><b>PO No :</b>&nbsp;<span><s:property value="grnno"/></span> &nbsp;|&nbsp; <b>PO Date :</b>&nbsp;<span><s:property value="grndate"/></span></p> --%>
				</div>

				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="border-bottom: 1px solid #ddd; padding: 0px; margin-bottom: 15px;"
					id="billanddata">
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6"
						style="text-align: left; margin-left: -15px;">
						<%-- <p class="marboset"><b>Department :</b>&nbsp;<span><s:property value="location"/></span></p> --%>
						<p class="marboset">
							<span><b>To,</b></span>
						</p>
						<s:hidden name="procurementid" id="procurementidterm"></s:hidden>
						<p class="marboset">
							<b>Company Name :</b>&nbsp;<span><s:property
									value="vendor" /></span>
						</p>
						<p class="marboset">
							<b>Address :</b>&nbsp;<span><s:property value="address" /></span>
						</p>
						<p class="marboset">
							<b>Contact No :</b>&nbsp;<span><s:property value="mobile" /></span>
						</p>
						<p class="marboset">
							<b>Email Id :</b>&nbsp;<span><s:property
									value="vendoremail" /></span>
						</p>
						<%
							if (loginInfo.isLmh()) {
						%>
						<p class="marboset">
							<b>GST No.</b>&nbsp;<span><s:property value="vendorGstNo" /></span>
						</p>

						<%
							}
						%>

						<s:if test="bankname!=''">
							<p class="marboset">
								<b>Bank :</b>&nbsp;<span><s:property value="bankname" /></span>,<span><s:property
										value="branch" /></span>
							</p>

							<p class="marboset">
								<b>Account no :</b>&nbsp;<span><s:property
										value="accountno" /></span>
							</p>
							<p class="marboset">
								<b>IFSC :</b>&nbsp;<span><s:property value="ifsc" /></span>
							</p>
						</s:if>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6"
						style="margin-left: 15px;">

						<%
							if (loginInfo.isLmh()) {
						%>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<p class="marboset hidden-lg hidden-md visible-print">
								<b>NOTE SHEET NO : </b>
								<s:property value="noteSheetNo" />
							</p>
							<p class="marboset hidden-print">
								<b>NOTE SHEET NO : </b>
								<s:textfield cssClass="form-control" name="noteSheetNo"
									onchange="changeGRNPoDetails(this.value,'noteSheetNo')"></s:textfield>
							</p>
							<s:if test="isfromworkorder!=0">
								<p class="marboset hidden-lg hidden-md visible-print">
									<b>NOTE SHEET DATE : </b>
									<s:property value="noteSheetDate" />
								</p>
								<p class="marboset hidden-print">
									<b>NOTE SHEET DATE : </b>
									<s:textfield cssClass="form-control" readonly="true"
										id="noteSheetDate" name="noteSheetDate"
										onchange="changeGRNPoDates(this.value,'noteSheetDate')">
									</s:textfield>
								</p>
							</s:if>

						</div>
						<%
							}
						%>

						<div>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<p class="marboset">
									<s:if test="isfromworkorder==1">
										<b>WO No :</b>&nbsp;
								</s:if>
									<s:elseif test="isfromworkorder==2">
										<b><s:property value="amcCmc" /> No :</b>&nbsp;
								</s:elseif>
									<s:else>
										<b>PO No :</b>&nbsp;
								</s:else>
									<span> <%
 	if (loginInfo.isLmh()) {
 %> <s:property value="poSequence" /> <%
 	} else {
 %> <s:property value="grnno" /> <%
 	}
 %>
									</span> &nbsp;|&nbsp;
									<s:if test="isfromworkorder==1">
										<b>WO Date :</b>&nbsp;
								</s:if>
									<s:elseif test="isfromworkorder==2">
										<b><s:property value="amcCmc" /> Date :</b>&nbsp;
								</s:elseif>
									<s:else>
										<b>PO Date :</b>&nbsp;
								</s:else>
									<span><s:property value="grndate" /></span>
								</p>
								<s:if test="isfromworkorder==2">
									<p class="marboset hidden-lg hidden-md visible-print">
										<b>DURATION FROM : </b>
										<s:property value="durationFrom" />
									</p>
									<p class="marboset hidden-print">
										<b>DURATION FROM : </b>
										<s:textfield readonly="true" cssClass="form-control" name="durationFrom" id="durationFrom"
											onchange="changeGRNPoDates(this.value,'durationFrom')"></s:textfield>
									</p>

									<p class="marboset hidden-lg hidden-md visible-print">
										<b>DURATION TO : </b>
										<s:property value="durationTo" />
									</p>
									<p class="marboset hidden-print">
										<b>DURATION TO : </b>
										<s:textfield readonly="true" cssClass="form-control" name="durationTo" id="durationTo"
											onchange="changeGRNPoDates(this.value,'durationTo')"></s:textfield>
									</p>
									<p class="marboset">
										<b>INSTITUTION : </b><span><s:property
												value="clinicName" />&nbsp;<s:property
												value="clinicaddress" /></span>
									</p>
								</s:if>
								<s:else>
									<%
										if (loginInfo.isLmh()) {
									%>
									<p class="marboset">
										<span><b>SHIP AND BILL TO</b></span>
									</p>
									<%
										} else {
									%>
									<p class="marboset">
										<span><b>Goods Delivered at,</b></span>
									</p>
									<%
										}
									%>
									<p class="marboset">
										<span><s:property value="clinicName" />&nbsp;<s:property
												value="clinicaddress" /></span>
									</p>
									<%
										if (!loginInfo.isLmh()) {
									%>
									<p class="marboset">
										<span><b>GSTN :</b> <s:property value="gstnno" /></span>
									</p>
									<%
										}
									%>
								</s:else>


							</div>
						</div>
						<%
							if (loginInfo.isLmh()) {
						%>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<s:if test="isfromworkorder==0">
								<p class="marboset hidden-lg hidden-md visible-print">
									<b>Rate Contract No. : </b>
									<s:property value="rateContractNo" />
								</p>
								<p class="marboset hidden-print">
									<b>Rate Contract No. : </b>
									<s:textfield cssClass="form-control" name="rateContractNo"
										onchange="changeGRNPoDetails(this.value,'rateContractNo')"></s:textfield>
								</p>
							</s:if>
							<s:if test="isfromworkorder!=2">
								<p class="marboset hidden-lg hidden-md visible-print">
									<span><b>Department : </b></span>
									<s:property value="poDepartment" />
								</p>
								<p class="marboset hidden-print">
									<span><b>Department : </b></span>
									<s:select list="locationListPharmacy"
										onchange="changeGRNPoDetails(this.value,'poDepartmentId')"
										theme="simple" name="poDepartmentId"
										cssClass="form-control chosen-select" listKey="id"
										listValue="name" headerKey="0" headerValue="Select Department">
									</s:select>
								</p>
							</s:if>
							<s:if test="isfromworkorder==0">
								<p class="marboset hidden-lg hidden-md visible-print">
									<b>SUPPLIER REF. NO. : </b>
									<s:property value="supplierRefNo" />
								</p>
								<p class="marboset hidden-print">
									<b>SUPPLIER REF. NO. : </b>
									<s:textfield cssClass="form-control" name="supplierRefNo"
										onchange="changeGRNPoDetails(this.value,'supplierRefNo')"></s:textfield>
								</p>
							</s:if>
						</div>

						<%
							}
						%>



						<%-- 	<p class="marboset">&nbsp;<span><b></b><s:property value="owner_qualification"/></span></p> --%>

					</div>

				</div>
			</div>
		</div>

		<div class="row" style="margin-top: -10px;">

			<p class="marboset hidden-lg hidden-md visible-print">
				<s:if test="isfromworkorder==2">
				</s:if>
				<s:else>
					<b>Subject : 
				</s:else>
				<s:if test="issubjectavailable==false">
					<%
						if (loginInfo.isLmh()) {
					%>
					<s:if test="isfromworkorder!=2">
								Supply of material at 
							</s:if>
					<%
						} else {
					%>
							Supply of material or medicine at 
						<%
						}
					%>
					<s:if test="isfromworkorder!=2">
						<s:property value="clinicName" />, <s:property
							value="clinicaddress" />
					</s:if>
				</s:if>
				<s:else>
					<s:property value="subject_msg" />
				</s:else>
			</p>

			<p class="marboset hidden-print">
				<s:if test="isfromworkorder==2">
				</s:if>
				<s:else>
					<b>Subject : 
				</s:else>
				<s:if test="issubjectavailable==false">
					<%
						if (loginInfo.isLmh()) {
					%>
					<!-- onchange="changesubjectofpo(this.value)"  -->
					<s:if test="isfromworkorder!=2">
						<input type="text" style="width: 95%" class="form-control"
							onchange="changeGRNPoDetails(this.value,'subject_msg')"
							value="Supply of material at <s:property value="clinicName"/>, <s:property value="clinicaddress"/>">
					</s:if>
					<s:else>
						<input type="text" style="width: 95%" class="form-control"
							onchange="changeGRNPoDetails(this.value,'subject_msg')">
					</s:else>
					<%
						} else {
					%>
					<input type="text" style="width: 95%" class="form-control"
						onchange="changeGRNPoDetails(this.value,'subject_msg')"
						value="Supply of material or medicine at <s:property value="clinicName"/>, <s:property value="clinicaddress"/>">
					<%
						}
					%>


				</s:if>
				<s:else>
					<s:textfield name="subject_msg" cssClass="form-control"
						cssStyle="width:95%"
						onchange="changeGRNPoDetails(this.value,'subject_msg')"></s:textfield>
				</s:else>
			</p>


			<s:hidden name="grnno" id="grnnooo"></s:hidden>
			<!--  
	     	<p class="marboset"><span>We hereby request you to please despatch the following material as per Rate, Terms and Conditions given below.</span></p>  
	     -->
			<s:if test="isfromworkorder==0">
				<h5 style="cmargin: 0px 0px 3px 0px;">Dear sir,</h5>
				<p class="marboset hidden-lg hidden-md visible-print">
					<s:if test="ismail_content==false">
						<span>We hereby request you to please despatch the
							following material as per Rate, Terms and Conditions given below.</span>
					</s:if>
					<s:else>
						<s:property value="mail_content" />
					</s:else>
				</p>

				<p class="marboset hidden-print">
					<s:if test="ismail_content==false">

						<input type="text" style="width: 95%" class="form-control"
							onchange="changemailcontentofpo(this.value)"
							value="We hereby request you to please despatch the following material as per Rate, Terms and Conditions given below.">
					</s:if>
					<s:else>

						<s:textfield name="mail_content" cssClass="form-control"
							cssStyle="width:95%" onchange="changemailcontentofpo(this.value)"></s:textfield>
					</s:else>

				</p>
			</s:if>


			<br>
		</div>


		<s:if test="newpo>0">
			<div class="row hidden"
				style="background-color: rgba(239, 239, 239, 0.42); padding: 9px; border: 1px dashed #ddd; margin-bottom: 15px;">
				<s:if test="isfromworkorder!=2">
					<h5
						style="color: chocolate; text-transform: uppercase; margin: 0px 0px 3px 0px;">Requested
						Items :-</h5>
				</s:if>
				<table class="table table-striped table-bordered"
					style="width: 100%;">

					<thead>
						<tr>
							<td style="width: 4%;">Sr.no</td>
							<td style="width: 7%;">Date/Time</td>
							<td style="width: 20%;">Product Name</td>
							<td style="width: 7%;">Req Qty</td>
							<td style="width: 7%;">Rate</td>
							<td style="width: 7%;">GST</td>
							<td style="width: 7%;">Discount</td>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 0;
						%>

						<tr>
							<td><%=++i%></td>
							<td><s:property value="date" /></td>
							<td><s:property value="product_name" /></td>
							<td><s:property value="qty" /></td>
							<td>Rs.<s:property value="purchase_price" /></td>
							<td><s:property value="vat" />%</td>
							<td><s:property value="discount" /></td>
						</tr>

					</tbody>
				</table>


			</div>
		</s:if>



		<div class="row" i>
			<s:if test="isfromworkorder!=2">
				<h5
					style="color: chocolate; text-transform: uppercase; margin: 0px 0px 3px 0px;">Requested
					Items :-</h5>
			</s:if>
			<table border="1px"
				style="width: 100%;">
				<s:if test="hidecalinpoprint==true">
					<thead>
						<b>
							<tr>
								<td style="width: 3%;"><b> Sr</b></td>
								<td style="width: 24%;"><b> Product Name</b></td>
								<td style="width: 5%;"><b> Qty</b></td>
							</tr>
					</thead>
					<tfoot
						style="color: green; background-color: rgba(239, 239, 239, 0.45);">
						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tfoot>
					<tbody>
						<%
							int i = 0;
						%>
						<s:iterator value="productList">
							<tr>
								<td><%=(++i)%></td>
								<td><s:property value="product_name" /></td>
								<td><s:property value="quantity" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</s:if>
				<s:else>
					<thead>
						<b>
							<tr>
								<td style="width: 3%;"><b> Sr</b></td>
								<td style="width: 24%;"><b> Product Name</b></td>
								<%
									if (loginInfo.isLmh()) {
								%>
								<!-- <td style="width: 10%;"><b> Description</b></td> -->
								<td style="width: 8%;"><b>Product Code</b></td>
								<td style="width: 8%;"><b> HSN NO</b></td>
								<td style="width: 4%;"><b> UOM</b></td>
								<%
									} else {
								%>
								<td style="width: 8%;"><b> Product Code</b></td>
								<td style="width: 4%;"><b> Pack</b></td>
								<%
									}
								%>
								<td style="width: 4%; text-align: center;"><b> Qty</b></td>
								<td style="width: 7%; text-align: center;" class=""><b>Unit
										Rate</b></td>
								<td style="width: 4%; text-align: center;"><b> GST</b></td>
								<td style="width: 6%; text-align: center;"><b> Total</b></td>
								<td style="width: 4%; text-align: center;"><b> Disc %</b></td>
								<td style="width: 6%; text-align: center;"><b> GST Amt</b></td>
								<td style="width: 6%; text-align: center;"><b> Net
										Total</b></td>
							</tr>
						</b>
					</thead>
					<tfoot
						style="color: green; background-color: rgba(239, 239, 239, 0.45);">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							
							<%
								if (loginInfo.isLmh()) {
							%>
							<!-- <td></td> -->
							<td></td>
							<td></td>
							<td></td>
							<%
								} else {
							%>
							<td></td>
							<td></td>
							<%
								}
							%>
							<td></td>
							<td style="text-align: center;"><b>Total</b></td>
							<td style="text-align: center;"><b>Rs.<s:property
										value="subTotal" /></b></td>
							<td style="text-align: center;"><b>Rs.<s:property
										value="totaldiscount" /></b></td>
							<td style="text-align: center;"><b>Rs.<s:property
										value="totalgstamount" /></b></td>
							<td style="text-align: center;"><b>Rs.<s:property
										value="totalnetamount" /></b></td>
							<s:hidden name="totalnetamount" id="totalnetamount"></s:hidden>
						</tr>
					</tfoot>
					<tbody>
						<%
							int i = 0;
						%>
						<s:iterator value="productList">
							<tr>
								<td><%=(++i)%></td>
								<td><s:property value="product_name" /> <s:if
										test="poDescription!=''">
									(<s:property value="poDescription" />)
								</s:if></td>
								<%
									if (loginInfo.isLmh()) {
								%>
								<%-- <td><s:property value="poDescription" /></td> --%>
								<td><s:property value="Pro_code" /></td>
								<td><s:property value="poHsnno" /></td>
								<td><s:property value="uom" /></td>
								<%
									} else {
								%>
								<td><s:property value="pro_code" /></td>
								<td><s:property value="pack" /></td>
								<%
									}
								%>
								<td style="text-align: center;"><s:property
										value="quantity" /></td>
								<td style="text-align: center;"><s:property
										value="purchase_price" /></td>
								<td style="text-align: center;"><s:property value="vat" />%</td>
								<td style="text-align: center;"><s:property value="total" /></td>
								<td style="text-align: center;"><s:property
										value="discount" /></td>
								<td style="text-align: center;"><s:property
										value="gstamount" /></td>
								<td style="text-align: center;"><s:property
										value="netamount" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</s:else>


			</table>

		</div>

		<div class="row">
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
					style="padding: 0px; text-align: right;">
					<small>In words: <label id="inword"></label></small>
				</div>
			</div>
			
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
				<div class="col-lg-6 col-xs-6 col-md-12" style="padding: 0px;">
					<s:if test="remark!=''">
						<p>
							Remark : <span
								style="font-family: Arial, Helvetica, sans-serif; font-weight: lighter;"><s:property
									value="remark" /></span>
						</p>
					</s:if>
					<div class="form-inline">
						<label>Terms & Conditions</label><br>
	
						<textarea rows="10" cols="100" id="termsncond" onchange="savetoprocure(<s:property value="procurementid"/>,0)" class="hidden-print">
						<s:property value="termsncond" />
						<%-- onchange="savetoprocure(<s:property value="procurementid"/>,0)" --%>
								
						</textarea>
						<p style="margin-bottom: 0px;" class="hidden">1) Delivery Time:
							Within one week from the date of order.</p>
						<p style="margin-bottom: 0px;" class="hidden">2) Item
							Inspection has to be done before receiving it.</p>
					</div>
				
				</div>
				<div class="col-lg-6 col-xs-6 col-md-12" style="padding: 0px;">
					<s:if test="isfromworkorder==2">
						<div class="form-inline">
							<label>Payment Terms & Conditions</label><br>
							<textarea rows="10" cols="100" id="paymenttermsncond"
								onchange="savepaymenttoprocure('<s:property value="procurementid"/>')"
								class="hidden-print"
								style="font-family: Arial, Helvetica, sans-serif;">
									<s:property value="paymenttermsncond" />
							</textarea>
						</div>
					</s:if>
					
				</div>
				

				<!-- </div> -->
			</div>
			<s:if test="isfromworkorder==2">
				<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
					<div class="col-lg-6 col-xs-6 col-md-12" style="padding: 0px;">
						<div id="termsshowprint" class="print-visible hidden-md hidden-lg"
							style="font-family: Arial, Helvetica, sans-serif; font-weight: lighter;">
						</div>
					</div>
					<div class="col-lg-6 col-xs-6 col-md-12" style="padding: 0px;">
						<div id="termsshowpayprint" class="print-visible hidden-md hidden-lg"
						style="font-family: Arial, Helvetica, sans-serif; font-weight: lighter;">
						<s:property value="paymenttermsncond" />
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
					<div id="termsshowprint" class="print-visible hidden-md hidden-lg"
							style="font-family: Arial, Helvetica, sans-serif; font-weight: lighter;">
						</div>
				</div>
			</s:else>
			
			
		</div>
		<div class="" style="margin-top: -600px">
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
				<br>
				<%
					if (loginInfo.isLmh()) {
				%>
				<s:if test="isfromworkorder==2">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
						style="margin-top: 30px;padding-left: 0px;">
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
							style="padding: 0px;text-align: center;">
							<p style="margin-bottom: 0px;">Prepared By</p>
						</div>
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
							style="padding: 0px;text-align: center;">
							<p style="margin-bottom: 0px;">Purchase Head</p>
						</div>
						<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1"
							style="padding: 0px;text-align: center;">
							<p style="margin-bottom: 0px;">Dean</p>
						</div>
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
							style="padding: 0px;text-align: center;">
							<p style="margin-bottom: 0px;">Treasurer</p>
						</div>
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
							style="padding: 0px;text-align: center;">
							<p style="margin-bottom: 0px;">Working Chairman</p>
						</div>
						<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1"
							style="padding: 0px;text-align: center;padding-left: 2%;">
							<p style="margin-bottom: 0px;">Chairman</p>
						</div>
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
							style="padding: 0px;text-align: center;padding-left: 5%;">
							<p style="margin-bottom: 0px;">CA & FO</p>
						</div>
					</div>
					
				</s:if>
				<s:else>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
						style="margin-top: 30px;">
						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
							style="padding: 0px;">
							<p style="margin-bottom: 0px;">Prepared By</p>
						</div>
						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
							style="padding: 0px;">
							<p style="margin-bottom: 0px;">Purchase Head</p>
						</div>
						<s:if test="isfromworkorder==1">
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
								style="padding: 0px;">
								<p style="margin-bottom: 0px;">WO Dept. Head</p>
							</div>
						</s:if>
						<s:else>
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
								style="padding: 0px;">
								<p style="margin-bottom: 0px;">Finance Head</p>
							</div>
						</s:else>
	
	
						<s:if test="isfromworkorder==1">
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1"
								style="padding: 0px;">
								<p style="margin-bottom: 0px;">Dean</p>
							</div>
						</s:if>
						<s:else>
							<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
								style="padding: 0px;">
								<p style="margin-bottom: 0px;">CA & FO</p>
							</div>
						</s:else>
						
						<s:if test="isfromworkorder==1">
							<!-- <div class="col-lg-1 col-md-1 col-xs-1 col-sm-1"
								style="padding: 0px;">
								<p style="margin-bottom: 0px;">Principal</p>
							</div> -->
						</s:if>
						<s:else>
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1"
								style="padding: 0px;">
								<p style="margin-bottom: 0px;">Dean</p>
							</div>
						</s:else>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
						style="margin-top: 85px;">
						<s:if test="isfromworkorder==1">
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
								style="padding: 0px;">
								<p style="margin-bottom: 0px;">CA & FO</p>
							</div>
						</s:if>
						<s:else>
	
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
								style="padding: 0px;">
								<p style="margin-bottom: 0px;">Director Audit</p>
							</div>
						</s:else>
	
						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
							style="padding: 0px;">
							<p style="margin-bottom: 0px;">Treasurer</p>
						</div>
						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
							style="padding: 0px;">
							<p style="margin-bottom: 0px;">Working Chairman</p>
						</div>
						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
							style="padding: 0px;">
							<p style="margin-bottom: 0px;">Chairman</p>
						</div>
					</div>
				</s:else>
				<%
					} else {
				%>
				<div>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
						style="padding: 0px;">

						<p style="margin-bottom: 0px;">Accepted By</p>

					</div>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
						style="padding: 0px;">
						<p style="margin-bottom: 0px;">Prepared By</p>
					</div>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
						style="padding: 0px;">
						<p style="margin-bottom: 0px;">Checked By</p>
					</div>

					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
						style="padding: 0px; text-align: right;"
						onclick="savetoprocure(<s:property value="procurementid"/>,0)">
						<!-- <p style="margin-bottom:0px;">Manager/Purchase Officer</p> -->
						<p style="margin-bottom: 0px;">Authorised Signatory</p>
						<p style="margin-bottom: 0px;" class="hidden">
							Requested by :
							<s:property value="check_avail_userid" />
						</p>

					</div>
				</div>
				<%
					}
				%>
				<div class="col-lg-12 col-xs-12 col-md-12"
					style="padding: 0px; margin-top: 10px;">
					<div class="col-lg-6 col-xs-6 col-md-6" style="padding: 0px;">
					</div>

					<div class="col-lg-6 col-xs-6 col-md-6"
						style="padding: 0px; text-align: right;">
						<br>
						<p style="margin-bottom: 0px;" hidden>
							Printed by :
							<s:property value="printedby" />
						</p>
						<a type="button"
							class="btn btn-primary btn-lg savebigbtn hidden-print"
							title="Print" onclick="myPrint()">Print</a>
					</div>
				</div>
			</div>
		</div>



	</div>
	<script type="text/javascript">
		function changeletterhead() {
			var c = document.getElementById("seconderyletterhead").value;
			if (c != '') {
				document.getElementById("clinicnamenew").innerHTML = c;
			}
		}
	</script>
	<script>
		window.onload = function() {
			savetoprocure('1',1);
			var amt=document.getElementById("totalnetamount").value;
	         document.getElementById("inword").innerText=convertNumberToWords(amt);
		}
		function myPrint() {
			window.print();
		}
		converttowordpo();
		changeletterhead();
	</script>
	<script>
	
	/*  window.onload =function(){
	        
         var amt=document.getElementById("totalnetamount").value;
         document.getElementById("inword").innerText=convertNumberToWords(amt);
   }; */	
	</script>

	<script>
		function convertNumberToWords(amount) {
			var words = new Array();
			words[0] = '';
			words[1] = 'One';
			words[2] = 'Two';
			words[3] = 'Three';
			words[4] = 'Four';
			words[5] = 'Five';
			words[6] = 'Six';
			words[7] = 'Seven';
			words[8] = 'Eight';
			words[9] = 'Nine';
			words[10] = 'Ten';
			words[11] = 'Eleven';
			words[12] = 'Twelve';
			words[13] = 'Thirteen';
			words[14] = 'Fourteen';
			words[15] = 'Fifteen';
			words[16] = 'Sixteen';
			words[17] = 'Seventeen';
			words[18] = 'Eighteen';
			words[19] = 'Nineteen';
			words[20] = 'Twenty';
			words[30] = 'Thirty';
			words[40] = 'Forty';
			words[50] = 'Fifty';
			words[60] = 'Sixty';
			words[70] = 'Seventy';
			words[80] = 'Eighty';
			words[90] = 'Ninety';
			amount = amount.toString();
			var atemp = amount.split(".");
			var number = atemp[0].split(",").join("");
			var n_length = number.length;
			var words_string = "";
			if (n_length <= 9) {
				var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
				var received_n_array = new Array();
				for (var i = 0; i < n_length; i++) {
					received_n_array[i] = number.substr(i, 1);
				}
				for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
					n_array[i] = received_n_array[j];
				}
				for (var i = 0, j = 1; i < 9; i++, j++) {
					if (i == 0 || i == 2 || i == 4 || i == 7) {
						if (n_array[i] == 1) {
							n_array[j] = 10 + parseInt(n_array[j]);
							n_array[i] = 0;
						}
					}
				}
				value = "";
				for (var i = 0; i < 9; i++) {
					if (i == 0 || i == 2 || i == 4 || i == 7) {
						value = n_array[i] * 10;
					} else {
						value = n_array[i];
					}
					if (value != 0) {
						words_string += words[value] + " ";
					}
					if ((i == 1 && value != 0)
							|| (i == 0 && value != 0 && n_array[i + 1] == 0)) {
						words_string += "Crores ";
					}
					if ((i == 3 && value != 0)
							|| (i == 2 && value != 0 && n_array[i + 1] == 0)) {
						words_string += "Lakhs ";
					}
					if ((i == 5 && value != 0)
							|| (i == 4 && value != 0 && n_array[i + 1] == 0)) {
						words_string += "Thousand ";
					}
					if (i == 6 && value != 0
							&& (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
						words_string += "Hundred and ";
					} else if (i == 6 && value != 0) {
						words_string += "Hundred ";
					}
				}
				words_string = words_string.split("  ").join(" ");
			}
			return words_string;
		}
	</script>
	<script src="common/chosen_v1.1.0/chosen.jquery.js"
		type="text/javascript"></script>
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

</body>
</html>