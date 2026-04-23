<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script></script>

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
						<b>Outwards Gate Pass</b>
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
						<div>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<p class="marboset">
									<b>SR Number :</b>&nbsp;<span><s:property value="product.sequence" /></span>
								</p>
								<p class="marboset">
									<b>Gate Pass Type :</b>&nbsp;<span><s:if test="product.gatePassType==0">
                    				Returnable
                    			</s:if>
                    			<s:else>
                    				Non Returnable
                    			</s:else></span>
								</p>
								<p class="marboset">
									<b>Date :</b>&nbsp;<span><s:property value="product.date" /></span>
								</p>
								<p class="marboset">
									<b>Vehicle No. :</b>&nbsp;<span><s:property value="product.vehicleNumber" /></span>
								</p>
								<p class="marboset">
									<b>Approximate Date Of Return :</b>&nbsp;<span><s:property value="product.dateOfReturn" /></span>
								</p>
								<p class="marboset">
									<b>Approximate value Rs. :</b>&nbsp;<span><s:property value="product.approximateValue" /></span>
								</p>
								
								<p class="marboset">
									<b>Vendor Code :</b>&nbsp;<span><s:property value="product.Vendorcode" /></span>
								</p>
								

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">

			<table class="" border="1"
				style="width: 100%;">
				<thead>
					<tr>
						<td style="text-align: center;"><b> Sr No</b></td>
						<td style="text-align: center;"><b> Product Name</b></td>
						<td style="text-align: center;"><b> Product Code</b></td>
						<%
							if (loginInfo.isLmh()) {
						%>
							<td style="text-align: center;"><b> UOM</b></td>
						<%
							} else {
						%>
							<td style="text-align: center;"><b> Pack</b></td>
						<%
							}
						%>
						<td style="text-align: center;"><b> Quantity</b></td>
						<td style="text-align: center;"><b>Remark</b></td>
					</tr>
				</thead>

				<tbody>
					<%
						int i = 0;
					%>
					<s:iterator value="productList">
						<tr>
							<td style="text-align: center;"><%=(++i)%></td>
							<td style="text-align: center;"><s:property value="product_name" /> </td>
							<td style="text-align: center;"><s:property value="product_code" /> </td>
							<%
								if (loginInfo.isLmh()) {
							%>
							<td style="text-align: center;"><s:property value="uom" /></td>
							<%
								} else {
							%>
							<td style="text-align: center;"><s:property value="pack" /></td>
							<%
								}
							%>
							<td style="text-align: center;"><s:property value="quantity" /></td>
							<td style="text-align: center;"><s:property value="comment" /></td>
							
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>

		<div class="row hidden">
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
					style="padding: 0px; text-align: right;">
					<small>In words: <label id="inword"></label></small>
				</div>
			</div>
		</div>
		<div class="">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="margin-top: 100px;">
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
					style="padding: 0px; ">
					<p style="margin-bottom: 0px;">Prepared By</p>
				</div>
				<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1"
					style="padding: 0px; text-align: center;">
					<p style="margin-bottom: 0px;">A.O</p>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4"
					style="padding: 0px; text-align: center;">
					<p style="margin-bottom: 0px;">Authorised Signatory</p>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
					style="padding: 0px; text-align: center;">
					<p style="margin-bottom: 0px;">Dean</p>
				</div>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3"
					style="padding: 0px; text-align: center;">
					<p style="margin-bottom: 0px;">Receiver Signatory</p>
				</div>
			</div>



			<div class="col-lg-12 col-xs-12 col-md-12"
				style="padding: 0px; margin-top: 10px;">
				<div class="col-lg-6 col-xs-6 col-md-6" style="padding: 0px;">
				</div>

				<div class="col-lg-6 col-xs-6 col-md-6"
					style="padding: 0px; text-align: right;">
					<br>
					<p style="margin-bottom: 0px;">
						Printed by :
						<s:property value="printedby" />
					</p>
					<a type="button"
						class="btn btn-primary btn-lg savebigbtn hidden-print"
						title="Print" onclick="printpage()">Print</a>
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
			savetoprocure('1', 1);
		}
		function myPrint() {
			window.print();
		}
		converttowordpo();
		changeletterhead();
	</script>
	<script>
		
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