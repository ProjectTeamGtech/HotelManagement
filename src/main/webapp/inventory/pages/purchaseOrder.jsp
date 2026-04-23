<%@page import="javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
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


<style>
.topheadbaxck {
	background-color: rgb(239, 239, 239);
	padding: 8px 0px;
}

.table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
	font-size: 14px;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
	line-height: 40px;
}

.chosen-container {
	width: 100% !important;
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
.search-text:hover{
width: 120px;
}

#ui-datepicker-div {    z-index: 1000 !important;
}
</style> 

<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>


<SCRIPT type="text/javascript">
	window.onload = function() {

		$("#expiry").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#voucherdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#security_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#delivermemodate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		 document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false);  
		setState();

	}
</SCRIPT>


</head>
<body>
	<%
		LoginInfo loginfo = LoginHelper.getLoginInfo(request);
	%>
	<% String scolomn="hidden"; %>
   
   <%if(loginfo.isParihar()){ %>
         <%scolomn="";%>
   <%} %>
   
	<div class="row">
			<div class="hidden-print">
				<ul class="breadcrumb">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
					<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
						<%if(breadcrumbs.isIscurrent()){ %>
							<li><%=breadcrumbs.getShowingname()%></li>
						<%}else{ %>
							<%if(breadcrumbs.getOn()){ %>
								<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getShowingname()%></a></li>
							<%}else{ %>
								<li><%=breadcrumbs.getShowingname()%></li>
							<%} %>
						<%} %>
						
					<%} %>
				</ul>
			</div>
	</div>
	<div class="row details">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

			<h4>Goods Rece</h4>

		</div>
	</div>
	<div class="">
							
	<s:form theme="simple" action="updateprocurementProcurement"
				id="formMyPo">
				<% String clinicid=loginfo.getClinicid1(); %>
		    <input type="hidden" id="hiddenclinicid" value="<%=clinicid%>">
		<div class=""
			style="background-color: rgba(239, 239, 239, 0.42);  border: 1px dashed #ddd;"><!--padding: 9px;  -->
			<s:hidden name="isfromuploadstock" id="isfromuploadstock"></s:hidden>
			<s:hidden id="calculatereqqtyafterpackchange"></s:hidden>
			<s:if test="isfromuploadstock==1">
				<input type="hidden" id="isfromuploadstock" value="1">
				<div class="form-inline hidden">
			</s:if>
			<s:else>
				<div class="form-inline">
			</s:else>
			
			<!-- <div class="form-inline"> -->
				<div class="form-group" style="width: 10%;">
					<s:hidden name="haslocation" id="haslocation"></s:hidden>
					<s:hidden name="expiryDateAlert" id="expiryDateAlert"></s:hidden>
					<%
						if (loginfo.getUserType() == 2) {
					%>
					<s:select list="warehouseList" id="warehouse" name="warehouse"
						listKey="id" listValue="name" headerKey="0" headerValue="Select"
						cssClass="form-control chosen-select"></s:select>
					<%
					
						} else {
					%>
					<s:hidden name="warehouse" id="warehouse">
					</s:hidden>
					<%
						}
					%>
				</div>
				<div class="form-group" id="vendid" style="width: 20%;">
					<s:select theme="simple" id="vendorid"
						onchange="setVendorProductForPo(this.value)" list="vendorList"
						listKey="id" listValue="name" headerKey="0"
						headerValue="Select Supplier"
						cssClass="form-control chosen-select" />
				</div>
				<div class="form-group" id="proddiv" style="width: 50%;">
					<select name="" class="form-control chosen-select" id="product_id"
						style="display: none;">
						<option value="0">Select</option>
					</select>
				</div>
				<div class="form-group hidden" id="categorydiv">
					<s:select list="categoryList" theme="simple" listKey="id"
						readOnly="true" listValue="name" cssClass="form-control"
						id="categoryid" name="category_id"></s:select>
				</div>
				<div class="form-group hidden" id="subdiv">
					<s:select list="subcategoryList" theme="simple" listKey="id"
						readOnly="true" listValue="name" cssClass="form-control"
						id="subcategoryid" name="subcategory_id"></s:select>
				</div>
				<div class="form-group hidden">
					<input type="number" readonly="readonly" class="form-control"
						id="rate" placeholder="Rate">
				</div>
				<div class="form-group">
					<a href="#" onclick="addProductForProcurement('prodTable')"
						class="btn btn-success">Add</a>
				</div>
				<div class="form-group">
					<a data-toggle="modal" href="#addnewproduct"
						aria-expanded="false" onclick="resetAddproductdata()"
						class="btn btn-success">New Product</a>
				</div>
				<%if(!loginfo.isLmh()){ %>
				<div class="form-group">
						<a href="#"
						onclick="opencPopup('salepriscPharmacy')"
						class="btn btn-success">Sale Bill</a>
				</div>
				<%} %>
			</div>

										<%
										 	if (loginfo.isLmh()) {
										 %> 
										 	<input type="hidden" id="lmhAccess" value="1"> 
										 <%
										 	} else {
										 %> 
										 	<input type="hidden" id="lmhAccess" value="0">
										 <%
										 	}
										 %>



			
				<s:hidden name="procurementid" id="procurementid" />
				<input type="hidden" value="0" id="repeat" />
				<div class="" style="padding: 0px; margin-top: 10px;">
					<b style="color: brown;"> <span id="vendorName"> </span>
						&nbsp; | &nbsp; 
						<input type="text" placeholder="Invoice No" class="form-control"  id="voucherno" required="required" name="voucherno" 
						style="width: 7%; background-color: cornsilk;" />
						<label id="errvoucher" style="color: red"></label> 
						
						&nbsp; | &nbsp; 
						<input type="text" class="form-control" readonly="readonly" id="voucherdate" name="voucherdate" placeholder="Invoice Date" 
						style="width: 7%; background-color: cornsilk;" /> 
						
						&nbsp; | &nbsp; 
						<input type="text" placeholder="Security Inward No" class="form-control" id="security_no" required="required"
						name="security_no" style="width: 9%; background-color: cornsilk;" />
						
						&nbsp; | &nbsp; 
						<input type="text" placeholder="Security Inward Date" readonly="readonly" class="form-control" id="security_date" required="required" 
						name="security_date" style="width: 10%; background-color: cornsilk;" />
						
						&nbsp; |  &nbsp; 
						<input type="checkbox" id="isdelivermemo" name="isdelivermemo" onclick="changeDMTextbox(this.checked)" >&nbsp; <span>Is Deliver Memo?</span>
						
						&nbsp; | &nbsp; 
						<input type="text" placeholder="Deliver Memo No." class="form-control" id="delivermemoid" required="required"
						name="delivermemoid" style="width: 9%; background-color: cornsilk;" readonly="readonly" /> 
					
						&nbsp; | &nbsp; 
						<input type="text" placeholder="Deliver Memo Date" readonly="readonly" class="form-control" readonly="readonly" id="delivermemodate" required="required"
						name="delivermemodate" style="width: 9%; background-color: cornsilk;" /> 
						
						&nbsp; | &nbsp; 
						<input type="text" placeholder="GRN Amount" class="form-control" id="firstnetamout" required="required"
						name="firstnetamout" style="width: 9%; background-color: cornsilk;" />
						
					</b>
					<table class="table my-table xlstable table-bordered"
						style="width: 100%; text-transform: uppercase; margin-top: 10px; margin-bottom: 0px;"
						id='prodTable'>
						<thead style="position: -webkit-sticky; position: sticky; top: 0px; z-index: 100;">
							<tr>
								<th>Sr</th>
								<th style="width: 15%;">Product</th>
								<th style="width: 5%;">Pack</th>
								<th style="width: 6%;">HSN No</th>
								<th style="width: 5%;">Mfg</th>
								<th style="width: 7%;" class="">MRP</th>
								<th style="width: 6%;">per unit</th>
								<th style="width: 5%;">GST</th>
								<th style="width: 7%;">Rate</th>
								<th style="width: 8%;">Batch No</th>
								<th style="width: 7%;">ExpDate</th>
								<th style="width: 5%;">Pack Qty</th>
								<th style="width: 5%;">Rec.Qty</th>
								<th style="width: 5%;">Dis</th>
								<%if(loginfo.isParihar()){ %>
							    <th style="width: 5%;">Scheme</th>
							    <%}%>
								<th style="width: 5%;">Free Qty</th>
								<%if(loginfo.isParihar()){ %>
								<th class="hidden" style="width: 5%;">Shelf No</th>
								<%}else{ %>
								<th style="width: 5%;">Shelf No</th>
								<%} %>
								<th class="hidden"></th>
								<th>Amt</th>
								<th class=<%=scolomn%>>DiscAmt </th>
								<th class=<%=scolomn%>>Value</th>
				                <th>GST Amt</th>
								<th>Net Total</th>
								<th></th>
							</tr>
						</thead>
						<%
							int i = 0;
						%>
						<tbody id="receiveddata">
							<s:if test="isfromuploadstock==1">
						
						<s:iterator value="productList">
							<tr>
								<td><%=(i + 1)%></td>
								<td><a href="#" onclick="viewallsupplier(<s:property value="product_id"/>)"> <s:property value="product_name" /></a> 
								<input type='hidden' value='<%=i%>' class='dclass' /> 
								<input type='hidden' value='<s:property value="grnno"/>'  name='procurements[<%=i%>].grnno'/>
								<input type='hidden' value='<s:property value="catalogueid"/>'  name='procurements[<%=i%>].catalogueid'/>
								<input type='hidden' value="<s:property value="id"/>" name='procurements[<%=i%>].id' /> 
								<input type='hidden' value='<s:property value="product_id"/>' name='procurements[<%=i%>].product_id' /> 
								<input type='hidden' value="<s:property value="minorder"/>" id="minorder<%=i%>" />
								<input type='hidden' value="<s:property value="maxorder"/>" id="maxorder<%=i%>" /> 
								<br> <small style="font-size: 11px;">(<s:property value="pack" /> / <s:property value="medicine_shedule" />) </small>
								<input type="text" name="procurements[<%=i%>].barcode" id="barcode<%=i%>" placeholder="Barcode" /> 
								</td>
								<td><input type="number" onkeyup='calSalPer()' onchange="calSalPer()"
									class="form-control" style="margin: 0;padding: 0;outline: 0;" value="<s:property value="pack" />"
									name="procurements[<%=i%>].pack" id="pack<%=i%>" /></td>

								<td><input type='number' maxlength='8' style="margin: 0;padding: 0;outline: 0;" class="form-control"
									value="<s:property value="hsnno"/>" placeholder='hsn no'
									name='procurements[<%=i%>].hsnno' id='hsnno<%=i%>' /></td>
								<td>
							 		<%if(loginfo.isAuto_generic_name()){ %>
										<select class="form-control chosen-select" style="margin: 0;padding: 0;outline: 0;" name='procurements[<%=i%>].mfg' id='mfg<%=i%>'>
											<option value=''>MFG</option>
											<s:iterator value="mfglist">
												<%-- <option value='<s:property value="name"/>'><s:property value="name" /></option> --%>
												<s:if test="name==mfg">
                                  						<option value='<s:property value="name"/>' selected="selected"><s:property value="name"/></option>
                                  				</s:if>
                                   				<s:else>
                                   					<option value='<s:property value="name"/>'><s:property value="name"/></option>
                                   				</s:else>
											</s:iterator>
										</select>
									<%}else{ %>
										<input type='text' style="margin: 0;padding: 0;outline: 0;" class="form-control"
											value="<s:property value="mfg"/>" placeholder='mfg' value="<s:property value="mfg"/>" 
											name='procurements[<%=i%>].mfg' id='mfg<%=i%>' />
									<%} %>
								</td>
								<td><input type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control"
									value="<s:property value="mrp"/>" placeholder='mrp'
									onkeyup='calSalPer()' onchange="calSalPer()" name='procurements[<%=i%>].mrp'
									id='mrp<%=i%>' /></td>
								<td><input type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control"
									placeholder='sale_price' name='procurements[<%=i%>].sale_price'
									id='sale_price<%=i%>' /></td>
								<td><select class="form-control" style="margin: 0;padding: 0;outline: 0;" onchange='calVatTotal()'
									name='procurements[<%=i%>].vat' id='vat<%=i%>'>
										<option value='0'>GST</option>
										<s:if test="vat==0">
											<option selected="selected" value='0'>0%</option>
										</s:if>
										<s:else>
											<option value='0'>0%</option>
										</s:else>
										<s:if test="vat==5">
											<option selected="selected" value='5'>5%</option>
										</s:if>
										<s:else>
											<option value='5'>5%</option>
										</s:else>
										<s:if test="vat==12">
											<option selected="selected" value='12'>12%</option>
										</s:if>
										<s:else>
											<option value='12'>12%</option>
										</s:else>
										<s:if test="vat==18">
											<option selected="selected" value='18'>18%</option>
										</s:if>
										<s:else>
											<option value='18'>18%</option>
										</s:else>
										<s:if test="vat==28">
											<option selected="selected" value='28'>28%</option>
										</s:if>
										<s:else>
											<option value='28'>28%</option>
										</s:else>

								</select></td>
								<td><input type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control"
									placeholder='Rate' value="<s:property value="purchase_price"/>"
									name='procurements[<%=i%>].purchase_price'
									onkeyup='calTotalAmt()' onchange="calTotalAmt()" id='purchase_price<%=i%>' /></td>
								<td style="background-color: rgba(210, 105, 30, 0.11);"><input
									type='text' style="margin: 0;padding: 0;outline: 0;" class="form-control search-text" value="<s:property value="batch_no"/>"
									placeholder='batch no' name='procurements[<%=i%>].batch_no'
									id='batch_no<%=i%>' /></td>
								<td style="background-color: rgba(210, 105, 30, 0.11);">
									<input type='text'  style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='MM/YYYY'
									value="<s:property value="expiry_date"/>" name='procurements[<%=i%>].expiry_date' id='expiry_date<%=i%>' />
								</td>

								<td style="background-color: rgba(210, 105, 30, 0.11);">
									<input type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='Pack Qty'
									onchange='calTotalAmt()' value="<s:property value="received_qty"/>" name='procurements[<%=i%>].received_qty' id='received_qty<%=i%>' />
								</td>
								<td style="background-color: rgba(210, 105, 30, 0.11);"><input
									type='number' style="margin: 0;padding: 0;outline: 0;" style="background-color: #ccc;"
									class="form-control" readonly="readonly" placeholder='Rec Qty'
									name='procurements[<%=i%>].newreceived_qty'
									id='newreceived_qty<%=i%>' /></td>
								<td style="background-color: rgba(210, 105, 30, 0.11);"><input
									type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='Discount'
									onkeyup='calTotalAmt()' name='procurements[<%=i%>].discount'
									id='discount<%=i%>' /></td>
								<%if(loginfo.isParihar()){ %>
								<!-- for parihar scheme discount -->	
								<td style="background-color: rgba(210, 105, 30, 0.11);">
									<input type='number' style="margin: 0;padding: 0;outline: 0;width:34px;" class="form-control" placeholder='scheme'
									onkeyup='calTotalAmt()' name='procurements[<%=i%>].scheme' id='scheme<%=i%>' /></td>
								<%}else{ %>
								<td class= "hidden" tyle="background-color: rgba(210, 105, 30, 0.11);">
									<input type='hidden' style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='scheme'
									onkeyup='calTotalAmt()' name='procurements[<%=i%>].scheme' value='0'
									id='scheme<%=i%>' /></td>
								
								<%} %>	
								<td style="background-color: rgba(210, 105, 30, 0.11);"><input
									type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='Free'
									name='procurements[<%=i%>].freeqty' value="<s:property value="freeqty"/>" id='freeqty<%=i%>' /></td>
								<%if(loginfo.isParihar()){ %>
								<td class="hidden" style="background-color: rgba(210, 105, 30, 0.11);">
									<%-- <s:select theme="simple" list="cellList" listKey="name" listValue="name" cssClass="form-control" name="procurements[<%=i %>].shelf" id="shelf<%=i%>"  ></s:select> --%>
									<select name="procurements[<%=i%>].shelf" id="shelf<%=i%>"
									class="form-control" style="margin: 0;padding: 0;outline: 0;">
										<s:iterator value="shelfList">
											<option value='<s:property value="name"/>'><s:property
														value="name" /></option>
										</s:iterator>
									</select>
									
									<select name="procurements[<%=i%>].secondary_shelf" id="secondary_shelf<%=i%>"
									class="form-control" style="margin: 0;padding: 0;outline: 0;">
										<s:iterator value="secondshelfList">
											<option value='<s:property value="name"/>'><s:property
														value="name" /></option>
										</s:iterator>
									</select>
									
								</td>
								<%}else{ %>
								<td style="background-color: rgba(210, 105, 30, 0.11);">
									<%-- <s:select theme="simple" list="cellList" listKey="name" listValue="name" cssClass="form-control" name="procurements[<%=i %>].shelf" id="shelf<%=i%>"  ></s:select> --%>
									<select name="procurements[<%=i%>].shelf" id="shelf<%=i%>"
									class="form-control" style="margin: 0;padding: 0;outline: 0;">
										<s:iterator value="shelfList">
											<option value='<s:property value="name"/>'><s:property
														value="name" /></option>
										</s:iterator>
									</select>
									
									<select name="procurements[<%=i%>].secondary_shelf" id="secondary_shelf<%=i%>"
									class="form-control" style="margin: 0;padding: 0;outline: 0;">
										<s:iterator value="secondshelfList">
											<option value='<s:property value="name"/>'><s:property
														value="name" /></option>
										</s:iterator>
									</select>
									
								</td>
								
								<%} %>
								<%-- <td><i class='fa fa-plus-circle' aria-hidden='true' title='Add Batch' onclick='opennewBatchProduct(<s:property value="product_id"/>,<%=i%>)' style='color:#6699CC;cursor:pointer;'></i> <input type='hidden' id='newproduct<%=i %>' value='0' name='procurements[<%=i%>].newproduct'> </td> --%>
								<td><i class='fa fa-plus-circle hidden' aria-hidden='true'
									title='Add Batch'
									onclick='addProductForGrnWithPoOtherBatch(<s:property value="vendor_id"/>,<s:property value="procurementid"/>,<s:property value="location"/>,<s:property value="grnno"/>,<s:property value="catalogueid"/>,<s:property value="parentpoid"/>)'
									style='color: #6699CC; cursor: pointer;'></i> <input
									type='hidden' id='newproduct<%=i%>' value='0'
									name='procurements[<%=i%>].newproduct'></td>
								<td>Rs.<span style="margin: 0;padding: 0;outline: 0;" id='amount<%=i%>'>00.00</span></td>
				                <td class=<%=scolomn%>>Rs.<span class="cdschme" style="margin: 0;padding: 0;outline: 0;" id='disamt<%=i%>'>00.00</span></td>
								<td hidden><input type="number" name="procurements[<%=i%>].totalschmedisc" id='totalschmedisc<%=i%>'></td>
							    <td class=<%=scolomn%>>Rs.<span style="margin: 0;padding: 0;outline: 0;" id='discountamt<%=i%>'>00.00</span></td>
								<td>Rs.<span style="margin: 0;padding: 0;outline: 0;" id='gstcalamount<%=i%>'>00.00</span></td>
								<td>Rs.<span style="margin: 0;padding: 0;outline: 0;" id='netamount<%=i%>'>00.00</span></td>
								<td></td>
							</tr>
							<%
								i++;
							%>
						</s:iterator>

							</s:if>
							<s:else>
								<tr></tr>
							</s:else>


						</tbody>
						<s:if test="isfromuploadstock==1">
							<input type="hidden" id="tempcount" value="<%=(++i)%>" />
						</s:if>
						<s:else>
							<input type="hidden" id="tempcount" value="0" />
						</s:else>
						
					</table>

					<div class="col-lg-12 col-md-12 col-xs-12"
						style="padding: 0px; padding-bottom: 15px;">
						<table class="table my-table xlstable table-bordered hidden"
							id="tableInner" style="width: 38%;">
							<thead>
								<tr>
									<td>Product Name</td>
									<td>Batch No</td>
									<td>Expiry Date</td>
									<td>Qty</td>
								</tr>
							</thead>
							<tbody id="innerTBody">
								<!--<tr>
		              <td>Saridon 500</td>
		              <td>AD-120</td>
		              <td>31-04-2017</td>
		              <td>12</td>
		             </tr>
		            -->
								<tr></tr>
							</tbody>
						</table>

					</div>


				</div>

				<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
					<div class="col-lg-4 col-md-4 col-xs-4"></div>
					<div class="col-lg-5 col-md-5">
						<table class="table my-table xlstable table-bordered"
							style="width: 100%; margin-top: 10px; float: right; border: 1px solid #ddd; text-align: right;">
							<thead>
								<tr>
									<th
										style="background-color: brown; width: 12%; text-align: right;">GST</th>
									<th
										style="background-color: brown; width: 20%; text-align: right;">Total
										Amt.</th>
									<th
										style="background-color: brown; width: 22%; text-align: right;">Taxable
										Amt.</th>
									<th
										style="background-color: brown; width: 19%; text-align: right;">Dis.
										Amt.</th>
									<th
										style="background-color: brown; text-align: right; width: 18%; text-align: right;">Tax
										Amt.</th>
									<th
										hidden style="background-color: brown; text-align: right; width: 18%; text-align: right;">Scheme
										Amt.</th>	
								</tr>
							</thead>
							<tbody id="tTaxData">
								<!--
                                 <tr>
                                  <td class="text-left">6.00%</td>
                                  <td class="text-right">1478.47 </td>
                                  <td><input type="text" class="form-control" required="required" name="procurements[0].batch_no"> </td>
                                  <td class="text-right">1478.47 </td>
                                  <td class="text-right">88.70</td>
                                  
                                 </tr>
                                 <tr>
                                  <td class="text-left">13.50%</td>
                                  <td class="text-right">1478.47 </td>
                                  <td><input type="text" class="form-control" required="required" name="procurements[0].batch_no"> </td>
                                  <td class="text-right">45.49</td>
                                  <td class="text-right">45.49</td>
                                 </tr>
                                 
                                -->
							</tbody>

						</table>
						<div class="form-group">
							<label for="comment" style="text-align: left;">Remark:</label> <a
								class="hidden" href="#" data-toggle="modal"
								data-target="#viewdetailspro">view</a>
							<textarea class="form-control" rows="5" id="remark" name="remark"
								style="background-color: rgba(95, 95, 95, 0.04);"></textarea>
						</div>


					</div>


					<div class="col-lg-3 col-xs-3 col-md-3" style="padding: 0px;">
						<table style="width: 100%;">
							<tbody>
								<tr>
									<td style="text-align: right; width: 55%;">SUBTOTAL :</td>
									<td><input type="text" readonly="readonly" id="subTotal"
										class="form-control" style="margin-bottom: 5px;"></td>
								</tr>
								<tr>
									<td style="text-align: right; color: red;"><select
										name="disctype" id="disctype"
										onchange="getDiscType(this.value)" class="form-control"
										style="width: 50%; background-color: rgba(255, 0, 0, 0.07);"><option
												value="0">Percent</option>
											<option value="1">Cash</option></select> DISCOUNT :</td>
									<td>
										<!-- <input type="number" class="form-control"
										onkeyup="calDiscNet(this.value)" name="discount"
										readonly="readonly" value="0" id="discount"
										style="background-color: rgba(220, 220, 220, 0.28); color: red; margin-bottom: 5px;" /> -->
										<input type="number" class="form-control"
											onkeyup="calTotalAmt()" name="discount"
											readonly="readonly" value="0" id="discount"
											style="background-color: rgba(220, 220, 220, 0.28); color: red; margin-bottom: 5px;" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">Taxable Amount :</td>
									<td><input type="text" readonly="readonly"
										class="form-control" id="discountedamt" style="margin-bottom: 5px;">
										
								</tr>
								<tr>
									<td style="text-align: right;">CGST :</td>
									<td><input type="text" readonly="readonly"
										class="form-control" id="tcgst" style="margin-bottom: 5px;">
										<input type="hidden" name="vat" id="vat" /> <input
										type="hidden" name="cgst" id="cgst" /></td>
								</tr>
								<tr>
									<td style="text-align: right;">SGST :</td>
									<td><input type="text" readonly="readonly" id="tsgst"
										class="form-control" style="margin-bottom: 5px;" /><input
										type="hidden" name="sgst" id="sgst" /></td>
								</tr>
								<tr>
									<td style="text-align: right;">IGST :</td>
									<td><input type="text" readonly="readonly"
										class="form-control" id="tigst" style="margin-bottom: 5px;" /><input
										type="hidden" name="igst" id="igst" /></td>
								</tr>
								<tr>
									<td style="text-align: right;">SURCHARGE :</td>
									<td><input type="text" class="form-control"
										onkeyup="calVatDiscount()" value="0.00" name="surcharge"
										id="subcharge" style="margin-bottom: 5px;"></td>
								</tr>
								<tr>
									<td style="text-align: right;">CREDIT :</td>
									<td><input type="text" class="form-control"
										onkeyup="calVatDiscount()" id="credit" name="credit"
										style="margin-bottom: 5px;"></td>
								</tr>
								<tr>
									<td style="text-align: right;">DEBIT :</td>
									<td><input type="text" class="form-control"
										onkeyup="calVatDiscount()" id="debit" name="debit"
										style="margin-bottom: 5px;"></td>
								</tr>
								<tr>
									<td style="text-align: right; color: green;"><label>NET
											PAYBLE AMT :</label></td>
									<td><input type="text" readonly="readonly"
										class="form-control" id="netpay" name="netpay"
										style="margin-bottom: 5px; background-color: rgba(220, 220, 220, 0.28); color: green;" />
										<input type="hidden" id="nettemp" /></td>
								</tr>
								<tr>
									<td style="text-align: right;">PAYMENT :</td>
									<td><select name="paymode" id="paymode"
										class="form-control">
											<option value="Credit">CREDIT</option>
											<option value="Cash">CASH</option>
									</select></td>
								</tr>
							</tbody>
						</table>
					</div>
					
			<a href="#" onclick="savePoReceived()" class="btn btn-primary savebigbtn"
				style="margin-top: 15px; float: right;">Goods Received</a>
		</div>
	</div>
	</s:form>







		<!-- View Product Details Modal -->
		<div id="viewdetailspro" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">
							Previous Invoice Details For - <span id="tabdata"> ALCOMAX
								TAB </span>
						</h4>
					</div>
					<div class="modal-body">
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<table class="table table-striped table-bordered"
								style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 27%;">Supplier</th>
										<th style="width: 9%;">GRN Date</th>
										<th style="width: 7%;">GRN</th>
										<th style="width: 7%;">Invoice No.</th>
										<th style="width: 7%;">GST</th>
										<th style="width: 7%;">Quantity</th>
										<th style="width: 7%;">Free Qty</th>
										<th style="width: 7%; background-color: brown; text-align: right;">MRP</th>
										<th style="width: 7%; background-color: brown; text-align: right;">Sale Price</th>
										<th style="width: 7%; background-color: brown; text-align: right;">Purchase Price</th>
										<th style="width: 7%;">Dis.</th>
										<th style="width: 7%;">Total</th>
									</tr>
								</thead>
								<tbody id="innerProduct">
									<!--<tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           <tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           <tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           <tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           <tr>
            <td>ABC Supplier Pvt.Ltd</td>
            <td>22-07-2017</td>
            <td>100</td>
            <td style="background-color: rgba(165, 42, 42, 0.07);text-align:right;">23.54</td>
            <td>0</td>
            <td>12%</td>
           </tr>
           -->
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>






		<!-- Create New Product Modal -->
		<div id="newpo" class="modal fade" role="dialog"
			style="background-color: rgba(0, 0, 0, 0.59);">
			<div class="modal-dialog modal-sm">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Add New Batch Order</h4>
					</div>
					<div class="modal-body">
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<s:form action="savenewpoProcurement" theme="simple"
								method="post" id="poform">
								<table class="table my-table xlstable table-bordered"
									style="width: 100%;" id="mynewtab">
									<thead>
										<tr>
											<th style="width: 40%;">Batch Number</th>
											<th style="width: 38%;">Expiry</th>
											<th>Quantity</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input class="form-control" id="batch"
												placeholder="Batch Number" type="text" /></td>
											<td><input class="form-control" id="expiry"
												placeholder="Expiry" type="text" /></td>
											<td><input class="form-control" id="qty"
												placeholder="Qty" type="text" /></td>
										</tr>

									</tbody>

								</table>
							</s:form>
							<div class="col-lg-12 col-xs-12 hidden"
								style="padding: 0px; margin-top: 15px;">
								<div class="col-lg-12 col-md-12 text-right">

									<p>
										Total : Rs.<label id="lblPOTotal">00.00</label>
									</p>
								</div>
							</div>
							<div class="col-lg-12 col-xs-12 hidden"
								style="padding: 0px; margin-top: 15px;">
								<div class="col-lg-6 col-md-6 text-left">
									<input value="Add more" onclick="addnewpo('mynewtab')"
										class="btn btn-primary" type="button">
								</div>
								<div class="col-lg-6 col-md-6 text-right" style="padding: 0px;">
									<input value="Create PO" onclick="submitPo('')"
										class="btn btn-primary pull-right" type="button">
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="savetoProductPo('tableInner')"
							class="btn btn-primary" style="margin-top: 15px;">Add
							Product</button>
					</div>
				</div>

			</div>
		</div>


		<!-- Add New Product Modal -->
		<div id="addnewproduct" class="modal fade" role="dialog">
			<div class="modal-dialog modal-sm">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title" id="suppliername">Add New Product</h4>
					</div>
					<div class="modal-body">
						
					<div class="col-lg-12 col-md-12">
        				<div class="form-group">
        					<label>Select Category</label>
        					<s:select list="categoryList" theme="simple" onchange="getsubproducttype(this.value)" cssClass="form-control chosen-select" id="productcategoryid" name="categoryid" listKey="id" listValue="name" headerKey="0" headerValue="Select Category" ></s:select>
        				</div>
        			</div>
        			<div class="col-lg-12 col-md-12">
        				<div class="form-group" id="subprodtypediv" >
        					<label>Sub Category</label>
        					<select name="subcategory" id="subcategory" class="form-control chosen-select">
        						<option value="0">Select Sub Category</option>
        					</select>
        				</div>
        			</div>
        			<div class="col-lg-12 col-md-12">
        				<div class="form-group"  >
        					<label>Product Type</label>
        					<s:select list="#{'Regular':'Regular','Narotics':'Narotics','H1':'H1','High Risk Medicine':'High Risk Medicine','Vaccination':'Vaccination'}" cssClass="form-control" cssStyle="width:95%;"  name="medicine_shedule" id="medicine_shedule"></s:select>
	                    </div>
        			</div>
						
					   <%--  <div class="col-lg-12 col-md-12">
							<div class="from-group">
								<label>Product Type</label> 
								
								 <s:select list="subcategoryList" id="subcategory" name="subcategory" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Product Type"></s:select>	
							</div>

						</div> --%>
						<div class="col-lg-12 col-md-12">
							<div class="from-group">
								<label>Generic Name</label> 
									<!-- <input type="text" class="form-control" id="genericname" required="required"> -->
									<%if(loginfo.isAuto_generic_name()){ %>
										<s:select list="genericnamelist" theme="simple" required="required"  cssClass="form-control chosen-select" id="genericname" listKey="name" listValue="name" headerKey="" headerValue="Generic Name" style="background-color: rgba(245, 245, 245, 0.46);" ></s:select>
									<%}else{ %>
										<input type="text" class="form-control" id="genericname" required="required">
									<%} %>
							</div>

						</div>
						<div class="col-lg-12 col-md-12" style="margin-top: 10px;">

							<div class="from-group">
								<label>Product Name</label> <input type="text"
									class="form-control" onchange="chkNameExistIncatalogue(this)"
									id="prodname" required="required">
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12" style="margin-top: 10px;">

							<div class="from-group">
								<label>Product Code</label> <input type="text"
									class="form-control" onchange="checkProductCodeExistSingle(this.value)"
									id="pro_code" >
							</div>
						</div>
						
						<%
							if (loginfo.isLmh()) {
						%>
							<input type="hidden" id="packHidden">
							<div class="col-lg-12 col-md-12 hidden" style="margin-top: 10px;">
								<div class="from-group">
									<label>Pack</label> <!-- <input type="text" class="form-control"
										value="1" id="pack" required="required"> -->
										<s:hidden value="1" id="pack"></s:hidden>
								</div>
							</div>
							<div class="col-lg-12 col-md-12" style="margin-top: 10px;">
								<div class="from-group">
									<label>UOM</label> 
									<s:select list="unitOfMeseurementList" theme="simple"
										cssClass="form-control chosen-select" id="uom"
										name="uom" headerKey="" headerValue="Select"
										style="background-color: rgba(245, 245, 245, 0.46);"></s:select>
								</div>
							</div>
						<%
							} else {
						%>
							<div class="col-lg-12 col-md-12" style="margin-top: 10px;">
								<div class="from-group">
									<label>Pack</label> <input type="number" class="form-control"
										id="pack" required="required">
								</div>
							</div>
							<div class="col-lg-12 col-md-12 hidden" style="margin-top: 10px;">
								<div class="from-group">
									<label>UOM</label> 
									<s:select list="unitOfMeseurementList" theme="simple"
										cssClass="form-control chosen-select" id="uom"
										name="uom" headerKey="" headerValue="Select"
										style="background-color: rgba(245, 245, 245, 0.46);"></s:select>
								</div>
							</div>
						<%
							}
						%>
						
						<div class="col-lg-12 col-md-12" style="margin-top: 10px;">

							<div class="from-group">
								<label>GST</label> 
								<select  id="addgst"   class="form-control chosen-select"> 
									<option value="">Select</option>
									<option value="0">0%</option>
									<option value="5">5%</option>
									<option value="12">12%</option>
									<option value="18">18%</option>
									<option value="28">28%</option>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="savenewCatalogue(0)" style="margin-top: 15px;">Save</button>
						<button type="button" class="btn btn-primary hidden" style="margin-top: 15px;"
							data-dismiss="modal">Close</button>
					</div>
					</div>
				</div>

			</div>
		</div>

		<!--Add Product Modal -->
		<div id="addproduct" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg" style="width: 99%;">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Add Product</h4>
					</div>
					<div class="modal-body">
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<table class="table my-table xlstable table-bordered"
								style="width: 100%;" id="mytable">
								<thead>
									<tr>
										<th style="width: 15%;">Category</th>
										<th style="width: 15%;">Sub-Category</th>
										<th style="width: 8%;">Product Type</th>
										<th style="width: 15%;">Product Name</th>
										<th style="width: 5%;">Pack</th>
										<th style="width: 7%;">MRP</th>
										<th style="width: 7%;">Pur.Rate</th>
										<th style="width: 7%;">Sale Price</th>
										<th style="width: 6%;">MFG</th>
										<th style="width: 6%;">GST</th>
										<th style="width: 20%;">Description</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><s:select list="categoryList" theme="simple"
												onchange="getsubcat(this.value)"
												cssClass="form-control chosen-select" id="category_id0"
												listKey="id" listValue="name" headerKey="0"
												headerValue="Select Category"></s:select></td>
										<td id="subcatdiv"><select id="subcategory_id0"
											class="form-control chosen-select">
												<option value="0">Select</option>
										</select></td>
										<td><select id="prodtype0"
											class="form-control chosen-select">
												<option value="0">Select</option>
												<option value="Regular">Regular</option>
												<option value="H1">H1</option>
												<option value="Narcotics">Narcotics</option>
										</select></td>
										<td><input type="text" class="form-control"
											id="product_name0" placeholder="Product Name"
											style="background-color: rgba(245, 245, 245, 0.46);"></td>
										<td><input type="text" class="form-control" id="pack0"
											placeholder="Pack"
											style="background-color: rgba(245, 245, 245, 0.46);"></td>
										<td><input type="text" class="form-control" id="mrp0"
											onblur="getcalsaleprice(0)" onmousemove="getcalsaleprice(0)"
											onmouseout="getcalsaleprice(0)" placeholder="MRP"
											style="background-color: rgba(245, 245, 245, 0.46);"></td>
										<td><input type="text" class="form-control"
											id="purchase_price0" placeholder="Rate"
											style="background-color: rgba(245, 245, 245, 0.46);"></td>
										<td><input type="text" class="form-control"
											id="sale_price0" placeholder="Sale Price"
											style="background-color: rgba(245, 245, 245, 0.46);"></td>
										<td><input type="text" class="form-control" id="mfg0"
											placeholder="MFG"
											style="background-color: rgba(245, 245, 245, 0.46);"></td>
										<td><select id="vat0" class="form-control chosen-select">
												<option value="0">Select</option>
												<option value="0">0%</option>
												<option value="5">5%</option>
												<option value="12">12%</option>
												<option value="18">18%</option>
												<option value="28">28%</option>
										</select></td>
										<td><textarea rows="2" class="form-control"
												id="description0" placeholder="Description"
												style="background-color: rgba(245, 245, 245, 0.46)"></textarea></td>
									</tr>
								</tbody>
							</table>

							<div class="col-lg-12 col-xs-12"
								style="padding: 0px; margin-top: 15px;">
								<div class="col-lg-6 col-md-6 text-left"></div>
								<div class="col-lg-6 col-md-6 text-right">
									<input value="Save" class="btn btn-primary" type="button"
										onclick="addNewItem()">
								</div>
							</div>



						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
			</div>


<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	




			
</body>
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

				// Apply an input mask which mkase sure the user 
				// limits the user to typing a month in the 
				//fromat specified in the MonthFormat option.
				$('#DigitalBush').MonthPicker({
					UseInputMask : true
				});
				$('#DigitalBushBoth').MonthPicker({
					UseInputMask : true,
					ValidationErrorMessage : 'Invalid Date!'
				});
			</script>
</html>