<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
	type="text/css" />
<link href="plugin/monthyear/MonthPicker.min.css" rel="stylesheet"
	type="text/css" />
<script src="plugin/monthyear/jquery-ui.min.js"></script>
<script src="plugin/monthyear/jquery.maskedinput.min.js"></script>
<script src="plugin/monthyear/MonthPicker.min.js"></script>
<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">


<style>
.form-control {
	border: 1px solid rgba(0, 0, 0, 0.1) !important;
}

p {
	margin: 0 0 1.5px;
}

.table {
	width: 100%;
	max-width: 100%;
	margin-bottom: 5px;
}

.savebigbtn {
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
	line-height: 40px;
}

ul, ol {
	margin-top: 0;
	margin-bottom: 8.5px;
	list-style: none;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
	line-height: 40px;
}

ul, ol {
	margin-top: 0;
	margin-bottom: 8.5px;
	list-style: none;
	padding: 0px;
	margin: 0px;
}
</style>
 <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
       .setborba{
	background-color: #efefef !important;
    padding-top: 5px !important;
}
 .dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus {
    background-image: linear-gradient(to bottom, #777 0, #777 100%) !important;
    
}
.dropdown-menu {
    padding: 0px 0 !important;
    margin: 0px 0 0 !important;
}
ul.dt-button-collection.dropdown-menu>* {
    -webkit-column-break-inside: avoid;
    break-inside: avoid;
    border-bottom: 1px solid rgba(0, 0, 0, 0.5) !important;
}
b, strong {
    font-weight: 900;
}
    </style>
    <style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style> 

<SCRIPT type="text/javascript">
$(document).ready(function() {

	$("#voucherdate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#security_date").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
	
	document.addEventListener("contextmenu", function(e){
		e.preventDefault();
		}, false); 
});
			  

</SCRIPT>

<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<script type="text/javascript" src="common/js/pagination.js"></script>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>
</head>
<body>
	<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
	<div class="">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="background: #43b9be !important;color: rgb(255, 255, 255);">
			<h4>
				<b>Delivery Memo Dashboard</b>
			</h4>
		</div>			
		<form action="updatedmProcurement" id="dmformsbid"
			method="post">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="padding: 0px;">
					<div
						class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden"
						style="border-bottom: 1px solid #ddd;" id="letterHead">
						<h4>SHREE NARAYANA PHARMACY</h4>
						<h5>SHREE NARAYANA HOSPITAL (A unit of Health-Tech
							Chattisgarh Pvt.Ltd), NEAR GANJ MANDI, BEHIND SECTOR-5, DEVENDRA
							NAGAR, PANDRI, RAIPUR-492001 CHHATTISGARH</h5>
						<h5>Website:http://www.snh.org.in, Email:info@snh.org.in,
							Contact : 0771-3001234/35/36</h5>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="hidden-print">
								<ul class="breadcrumb">
									&nbsp;
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getShowingname()%></li>
										<%}else{ %>
											<%if(breadcrumbs.getOn()){ %>
												<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
											<%}else{ %>
												<li><%=breadcrumbs.getName()%></li>
											<%} %>
										<%} %>
										
									<%} %>
								</ul>
							</div>
						</div>
				</div>
			</div>
			<!-- <div class="clearfix" style="height: 50px;"></div> -->
			<s:hidden name="dmgrnids" id="dmgrnids"></s:hidden>

			<div class="">
				<div class=""
					style="background-color: rgba(239, 239, 239, 0.42); padding: 9px; border: 1px dashed #ddd;">

					<div class="form-inline">
						<div class="form-group" id="vendid" style="width: 20%;">
							<s:select theme="simple" id="vendorid" name="vendor_id"
								onchange="setVendorProductForDM(this.value)" list="vendorList"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Supplier"
								cssClass="form-control chosen-select" />
						</div>

					</div>
				</div>
			</div>
			<div class="clearfix" style="height: 5px;"></div>

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="padding: 10px;">
					<b style="color: brown;"><small style="color: green;">
					</small> <input type="text" placeholder="Invoice No" class="form-control"
						id="voucherno" required="required" name="voucherno"
						style="width: 10%; background-color: cornsilk;" /> <label
						id="errvoucher" style="color: red"></label> &nbsp; | &nbsp; <input readonly="readonly"
						type="text" class="form-control" id="voucherdate"
						name="voucherdate" placeholder="Invoice Date"
						style="width: 10%; background-color: cornsilk;" /> &nbsp; |
						&nbsp; <input type="text" placeholder="Security Inward No" 
						class="form-control" id="security_no" required="required"
						name="security_no" style="width: 10%; background-color: cornsilk;" />
						&nbsp; | &nbsp; <input type="text" 
						placeholder="Security Inward Date" class="form-control" readonly="readonly"
						id="security_date" required="required" name="security_date"
						style="width: 10%; background-color: cornsilk;" /> </b>
				</div>
			</div>

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px; min-height: 75px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden">
					<h4>
						<b>DM LIST</b>
					</h4>
				</div>
				<div class="fixedscroll">
					<table class="table table-striped table-bordered"
						style="width: 100%;" id="prodtableid">
						<thead>
							<tr>
								<th style="width: 3%;">
									<ul class="vertical default_list" id="">
										<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
												type="checkbox" class="form-control"
												onclick="selectAllDMCheckBox(this)" /><i
												style="border-color: #fff; color: #fff;"></i> </label></li>
									</ul>

							</th>
								<th>Sr.no</th>
								<th>PO No</th>
								<th>PO Date</th>
								<th>DM No</th>
								<th>DM Date</th>
								<th>Vendor Name</th>
								<th>Product Name</th>
								<th>Amount</th>
								<th>Print</th>
							</tr>
						</thead>
						<tbody id="dmtbody">
							<%
								int i = 0;
							%>
							<s:iterator value="dmlist">
								<tr>
									<td><article>
										<ul class="vertical default_list" id="">
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="status<s:property value="id"/>" onchange="calculatedmdata()"
													value="<s:property value="id"/>" type="checkbox"
													class="form-control case" /><i></i> </label></li>
										</ul>
										</article></td>
									<td><%=(++i)%>
										<input type="hidden" id="cgst<s:property value="id"/>" value="<s:property value="cgst"/>">
										<input type="hidden" id="sgst<s:property value="id"/>" value="<s:property value="sgst"/>">
										<input type="hidden" id="igst<s:property value="id"/>" value="<s:property value="igst"/>">
										<input type="hidden" id="total<s:property value="id"/>" value="<s:property value="total"/>">
										<input type="hidden" id="discount<s:property value="id"/>" value="<s:property value="discount"/>">
										<input type="hidden" id="netAmt<s:property value="id"/>" value="<s:property value="netAmt"/>">
									</td>
									<s:if test="grnno==0">
                                    	<td>-</td>
                                    </s:if>
                                    <s:else>
                                    	<%if(loginInfo.isLmh()){ %>
                                    		<td><s:property value="poSequence"/></td>
                                    	<%}else{ %>
                                    		<td><s:property value="grnno"/></td>
                                    	<%} %>
                                    	
                                    </s:else>
                                    <td><s:property value="grndate"/></td>
									<td><s:property value="delivermemoid" /></td>
									<td><s:property value="delivermemodate" /></td>
									<td><s:property value="vendor" /></td>
									<td>-</td>
									<td><s:property value="netAmt" /></td>
									<td><a onclick="printDMgrn(<s:property value="childid"/>)">Print</a></td>
								</tr>
								<s:iterator value="procurmentlist">
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td>-</td>
									<td>-</td>
									<td>-</td>
									<td><s:property value="product_name" /></td>
									<td><s:property value="total" /></td>
									<td>-</td>
								</tr>
								</s:iterator>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<table class="table table-striped table-bordered"
					style="width: 100%;">
					<thead>
					</thead>
					<tbody>
						<tr style="border-top: 1px solid #ddd;">
							<td colspan="13"
								style="font-size: 11px; text-align: right; font-weight: bold; border: none;">Sub
								Total</td>
							<td
								style="font-size: 11px; text-align: right; font-weight: bold; border: none;" id="givensubtotal">Rs.0</td>
						</tr>
						<tr>
							<td colspan="13"
								style="font-size: 11px; text-align: right; font-weight: bold; color: red; border: none;">Discount</td>
							<td style="font-size: 11px; text-align: right; font-weight: bold; color: red; border: none;" id="givendiscount">Rs.0</td>
						</tr>
						<tr>
							<td colspan="13"
								style="font-size: 11px; text-align: right; font-weight: bold; border: none;">SGST</td>
							<td style="font-size: 11px; text-align: right; font-weight: bold; border: none;" id="givensgst">Rs.0</td>
						</tr>
						<tr>
							<td colspan="13" style="font-size: 11px; text-align: right; font-weight: bold; border: none;">CGST</td>
							<td style="font-size: 11px; text-align: right; font-weight: bold; border: none;" id="givencgst">Rs.0</td>
						</tr>
						<tr>
							<td colspan="13"
								style="font-size: 11px; text-align: right; font-weight: bold; border: none;">IGST</td>
							<td style="font-size: 11px; text-align: right; font-weight: bold; border: none;" id="givenigst">Rs.0</td>
						</tr>
						<tr>
							<td colspan="13" style="font-size: 11px; text-align: right; font-weight: bold; color: green; border: none;">Net
								Payment</td>
							<td style="font-size: 11px; text-align: right; font-weight: bold; color: green; border: none;" id="givennetamount">Rs.0</td>
						</tr>
					</tbody>
				</table>
				<!-- <input type="submit" value="Save"  class="btn btn-primary savebigbtn"
					style="float: right; margin-top: 15px;" /> -->
				<a href="#" onclick="checkdmSubmit()" class="btn btn-primary savebigbtn" style="margin-top:15px;float:right;">Goods Received</a>

			</div>



		</form>



	</div>

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

	<script type="text/javascript"
		src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

	<script>
				             $(function() {
								  $('.fixedscroll').slimScroll({
								   		height : '285px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>

	<script>
	 function myPrint() {
            window.print();
        }
</script>

</body>
</html>