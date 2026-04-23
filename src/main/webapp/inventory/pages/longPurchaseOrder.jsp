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


<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>

<SCRIPT type="text/javascript">

  window.onload= function(){
	  document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false); 
  
            $('.dclass').each(function() {
        
        			 var i=this.value;
        			    /* $("#expiry_date"+i).datepicker({

				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true

  		 });  */
  					   $("#expiry_date"+i).MonthPicker({
		     UseInputMask: true,
		     ValidationErrorMessage: 'Invalid Date!'
		 }); 
        			
             });
  
  
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
   $("#delivermemodate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange : yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
   var poDiscAmt = document.getElementById("poDiscAmt").value;
   if(poDiscAmt>0){
	   setPoDiscAmount(poDiscAmt);
   }else{
	   setState(1);
   }
   
  // calSalPer();
   //getvendorstateforlongpo();
   //calTotalAmt();
  }

</SCRIPT>

	<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<SCRIPT type="text/javascript">
 
    function downloadexcelprint() {
        $(".tablestock").table2excel({
					exclude: ".noExl",
					name: "Pending PO",
					filename: "Pending PO",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
 </script>
</head>
<body>
	<%
		LoginInfo loginfo = LoginHelper.getLoginInfo(request);
	%>
	<% String scolomn="hidden"; %>
   
   <%if(loginfo.isParihar()){ %>
         <%scolomn="";%>
   <%} %>
   
   <% boolean clinicid11=loginfo.isParihar(); %>
		<input type="hidden" id="hiddenclinicid" value="<%=clinicid11%>">
	<%-- <%String hidedmno=""; %>
	<%if(!loginfo.isLmh()){
		hidedmno="hidden";
	} %>
	 --%>
	
							<div class="hidden-print">
								<ul class="breadcrumb">
									&nbsp;
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
								<%
							 	if (!loginfo.isLmh()) {
								%> 
									<a href="#"	onclick="opencPopup('salepriscPharmacy')" class="btn btn-success" style="float: right;margin-right: 10px;">Sale Bill</a> 
								<%
							 		} 
								%> 
								
							</div>
	
	<div class="row details">
		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
			<s:hidden name="poDiscAmt" id="poDiscAmt"></s:hidden>
			<h4>
				<span id="vendorName"> <label>
					<s:if test="isfromworkorder==1">
						Work Order No: 
					</s:if>
					<s:else>
						Purchase Order No: 
					</s:else>
				
					 <%
					 	if (loginfo.isLmh()) {
					 %> 
					 	<s:property value="poSequence" />
					 	<input type="hidden" id="lmhAccess" value="1"> 
					 <%
					 	} else {
					 %> 
					 	<s:property value="newgrno" /> 
					 	<input type="hidden" id="lmhAccess" value="0">
					 <%
					 	}
					 %>
				</label>
				</span>
				
			</h4>
			
			
			
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 hidden" style="text-align: right;">
				<p>	<a class="btn btn-danger hidden-print" href="#" onclick="window.history.back()"  title="Procurement Dashboard" >Back </a></p>
		</div>
	</div>
	<div class="">
		<div class=""
			style="background-color: rgba(239, 239, 239, 0.42); padding: 9px; border: 1px dashed #ddd; margin-bottom: 15px;">
			<h5
				style="color: chocolate; text-transform: uppercase; margin: 0px 0px 3px 0px;">
					<s:if test="isfromworkorder==1">
						Request WO :-
					
					</s:if>
					<s:else>
						Request PO :-
					</s:else>
					<a type="button" style="float: right;"  title="Save As XLS" onclick="downloadexcelprint()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
				</h5>
				
			<table class="table table-striped table-bordered tablestock"
				style="width: 100%;" >
				<thead>
					<tr>
						<th style="width: 4%;">Sr.no</th>
						<th style="width: 7%;">Date/Time</th>
						<th style="width: 20%;">Product Name</th>
						<th style="width: 10%;">Product Code</th>
						<th style="width: 7%;">Requested Qty</th>
						<th style="width: 7%;">Received Qty</th>
						<th style="width: 7%;">Balance Qty</th>
						<th style="width: 7%;">Rate</th>
						<th style="width: 7%;">GST</th>
						<th style="width: 7%;">Discount</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 0;
					%>
					<s:iterator value="requestedPoList">
						<tr>
							<td><%=(++i)%></td>
							<td><s:property value="date" /></td>
							<td><s:property value="product_name" /></td>
							<td><s:property value="pro_code" /></td>
							<td><s:property value="qty" /></td>
							<td><s:property value="received_qty" /></td>
							<td><s:property value="balanceqty" /></td>
							<td>Rs.<s:property value="purchase_price" /></td>
							<td><s:property value="vat" />%</td>
							<td><s:property value="discount" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>


		</div>
		<s:hidden name="size" id="listsize" />
		<s:form theme="simple" action="updateprocurementProcurement"
			id="formMyPo">
			<% String clinicid=loginfo.getClinicid1(); %>
		    <input type="hidden" id="hiddenclinicid" value="<%=clinicid%>">
			<s:hidden name="newgrno" id="newgrno" />
			<s:hidden name="procurementid" id="procurementid" />
			<s:hidden name="expiryDateAlert" id="expiryDateAlert"></s:hidden>
			<s:hidden name="isfromlongpo" id="isfromlongpo" value="1"></s:hidden>
			<s:hidden name="vendoridlongpo" id="vendoridlongpo"></s:hidden>
			<div class="col-lg-12 col-md-12 col-xs-12 topheadbaxck hidden">
				<div class="col-lg-2 col-md-2 col-sm-2 hidden">
					<div class="form-group">
						<label>Select State</label> <select name=""
							class="form-control chosen-select">
							<option value="0">Interstate</option>
							<option value="0">Intrastate</option>
						</select>
					</div>

				</div>
				<%
					if (loginfo.getUserType() == 2) {
				%>
				<div class="col-lg-2 col-md-2 col-sm-2">
					<div class="form-group">
						<label>Select Location</label>
						<s:select list="locationListPharmacy" theme="simple" id="location"
							name="location" cssStyle="width:30%"
							cssClass="form-control chosen-select" listKey="id"
							listValue="name" headerKey="0" headerValue="Select Location">
						</s:select>

					</div>
				</div>
				<%
					} else {
				%>
				<div class="form-group hidden">
					<label>Select Location</label>
					<s:select list="locationListPharmacy" theme="simple" id="location"
						name="location" cssStyle="width:30%"
						cssClass="form-control chosen-select" listKey="id"
						listValue="name" headerKey="0" headerValue="Select Location">
					</s:select>

				</div>
				<%
					}
				%>
				<div class="col-lg-2 col-md-2 col-sm-2">
					<div class="form-group">
						<label>Select Supplier</label>
						<s:select list="vendorList" id="vendorid" name="vendor"
							listKey="id" onchange="getvendorProduct(this.value)"
							listValue="name" cssClass="form-control chosen-select"
							theme="simple" headerKey="0" headerValue="Select Supplier" />
					</div>
				</div>

				<div class="col-lg-2 col-md-2 col-sm-2">
					<div class="form-group" id="prodListId">
						<label>Select Product</label> <select name=""
							class="form-control chosen-select">
							<option value="0">Select Product</option>
						</select>
					</div>

				</div>
				<div class="col-lg-1 col-md-1 col-sm-2">
					<div class="form-group">
						<a href="#" type="button" class="btn btn-primary"
							onclick="addNewProd()" style="margin-top: 22px;">New Product</a>
					</div>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-2">
					<div class="form-group">
						<label>Product Type</label>
						<s:select cssClass="form-control" list="medicineTypeList"
							headerKey="0" listKey="id" listValue="name"
							headerValue="Product Type" name="medicine_type"
							id="medicine_type"></s:select>

					</div>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-2">
					<div class="form-group">
						<label>Pack</label> <input type="text" class="form-control"
							id="pack" required="required">
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2">
					<div class="form-group">
						<label>Product Type</label>
						<s:select list="#{'0':'Regular','1':'Narcotics','2':'H1'}"
							cssClass="form-control" name="medicine_shedule"
							id="medicine_shedule"></s:select>
					</div>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-2">
					<a href="#" class="btn btn-warning"
						onclick="addProductForProcurement('prodTable')"
						style="margin-top: 22px;">Add</a>
				</div>
			</div>
			<input type="hidden" value="0" id="repeat" />
			<div class="col-lg-12 col-md-12 col-sm-12"
				style="padding: 0px; margin-top: 10px;">
				<b style="color: brown;"><small style="color: green;"> </small>
					&nbsp;
					<input type="text" placeholder="Invoice No" class="form-control"
					id="voucherno" required="required" name="voucherno" style="width: 10%; background-color: cornsilk;" /> 
					<label id="errvoucher" style="color: red"></label> 
					
					&nbsp; | &nbsp; 
					
					<input readonly="readonly" type="text" class="form-control" id="voucherdate"
					name="voucherdate" placeholder="Invoice Date"
					style="width: 10%; background-color: cornsilk;" /> 
					
					&nbsp; | &nbsp;
					
					<input type="text" placeholder="Security Inward No"
					class="form-control" id="security_no" required="required"
					name="security_no" style="width: 10%; background-color: cornsilk;" />
					
					&nbsp; | &nbsp; 
					
					<input type="text" readonly="readonly"
					placeholder="Security Inward Date" class="form-control"
					id="security_date" required="required" name="security_date"
					style="width: 10%; background-color: cornsilk;" /> 
					
					&nbsp; | &nbsp;
					
					<input type="checkbox" id="isdelivermemo" name="isdelivermemo"
					onclick="changeDMTextbox(this.checked)">&nbsp; <span>Is
						Deliver Memo?</span> 
						
					&nbsp; | &nbsp; 
					
					<input type="text"
					placeholder="Deliver Memo No." class="form-control"
					id="delivermemoid" required="required" name="delivermemoid"
					style="width: 10%; background-color: cornsilk;" readonly="readonly" />

					&nbsp; | &nbsp; 
					
					<input type="text" placeholder="Deliver Memo Date"
					class="form-control" readonly="readonly" id="delivermemodate"
					required="required" name="delivermemodate"
					style="width: 10%; background-color: cornsilk;" />
					
					&nbsp; | &nbsp; 
					
					<input type="text" placeholder="GRN Amount" class="form-control" 
						id="firstnetamout" required="required"
					name="firstnetamout" style="width: 10%; background-color: cornsilk;" />
					&nbsp; | &nbsp;
					<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 10px; margin-top: 5px;">
					<input type="text" placeholder="DM No." class="form-control" 
						id="dmno" required="required"
					 name="dmno" style="width:10%; background-color: cornsilk;" />
					</div>
					</b>
					<input type="hidden" id="calculatereqqtyafterpackchange">
				<table class="table my-table xlstable table-bordered"
					style="width: 100%; text-transform: uppercase; margin-top: 10px; margin-bottom: 0px;"
					id='prodTable'>
					<thead style="position: -webkit-sticky; position: sticky; top: 0px; z-index: 100;">
						<tr>
						    
							<th>Sr</th>
							<th></th>
							<!-- <td style="width: 2%;" class="hidden-print">
                                 <input type="checkbox" id="selectalliventorystockid" onclick="selectAllCheckBox(this)" />
                             </td> -->
							<th style="width: 14%;">Product</th>
							<th style="width: 5%;">Pack</th>
							<th style="width: 1%;">Req Qty</th>
							<th style="width: 8%;">HSN No</th>
							<th style="width: 5%;">Mfg</th>
							<th class="" style="width: 7%;">MRP</th>
							<th style="width: 6%;">per unit</th>
							<th style="width: 5%;">GST</th>
							<th style="width: 7%;">Rate</th>
							<th style="width: 6%;">Batch No</th>
							<th style="width: 7%;">Expiry Date</th>
							<th style="width: 5%;">Pack Qty</th>
							<th style="width: 5%;">Rec.Qty</th>
							<%if(loginfo.isBams1()){ %>
							<th style="width: 1%;">Uom</th>
							<%}%>
							<th style="width: 5%;">Discount</th>
							<%if(loginfo.isParihar()){ %>
							<th style="width: 1%;">Scheme</th>
							<%}%>
							<th style="width: 5%;">Free Qty</th>
							<%if(loginfo.isParihar()){ %>
							<th class="hidden" style="width: 6%;">Shelf No</th>
							<%}else{ %>
							<th style="width: 6%;">Shelf No</th>
							<%}%>
							<th></th>
							<th>Amt</th>
							<th class=<%=scolomn%>>DiscAmt </th>
							<th class=<%=scolomn%>>Value</th>
							<th>GST Amt</th>
							<th>Net Total</th>
							<th></th>
							<!--<th>Edit</th>
	                                    <th>Delete</th>
	                                -->
						</tr>
					</thead>
					
					<tbody id="receiveddata">
						<%
							i = 0;
						%>
						<s:iterator value="productList">
							<tr id="chk<s:property value="id"/>">
								<td><%=(i + 1)%></td>
								<td class="hidden-print">
                                    	<input type="checkbox" id="checkbox<s:property value="id"/>" class="case" class="form-control" onclick="chngecolor(this.id,'<s:property value="id"/>')"/>
                                </td>
								<td><a href="#" onclick="viewallsupplier(<s:property value="product_id"/>)">
								<s:property value="pro_code" /> - <s:property value="product_name" /></a> <input
									type='hidden' value='<%=i%>' class='dclass' /> 
								<input type='hidden' value="<s:property value="id"/>"
									name='procurements[<%=i%>].id' /> 
								<input type='hidden' value='<s:property value="product_id"/>'
									name='procurements[<%=i%>].product_id' /> <input type='hidden'
									value="<s:property value="minorder"/>" id="minorder<%=i%>" />
									<input type='hidden' value="<s:property value="maxorder"/>"
									id="maxorder<%=i%>" /> <br> 
								<small style="font-size: 11px;">
									(<s:property value="pack" /> / Regular)
								</small>
									<input type='hidden' value="<s:property value="parentpoid"/>" name="procurements[<%=i%>].parentpoid" />
									<input type='hidden' value="<s:property value="quantity"/>" name="procurements[<%=i%>].reqpoqty" />
									<br>
									<s:property value="poDescription"/>
									<input type="text" name="procurements[<%=i%>].barcode" id="barcode<%=i%>" placeholder="Barcode" /> 
								</td>
								<td><input type="number" onkeyup='calSalPer()' onchange='calSalPer()'
									class="form-control" style="margin: 0;padding: 0;outline: 0;" value="<s:property value="pack" />"
									name="procurements[<%=i%>].pack" id="pack<%=i%>" /></td>
 
								<td><span id="qun<%=i%>"><s:property value="quantity" /></span></td>

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
											value="<s:property value="mfg"/>" placeholder='mfg'
											name='procurements[<%=i%>].mfg' id='mfg<%=i%>' />
									<%} %>
								</td>
								<td><input type='number' style="margin: 0;padding: 0;outline: 0;width:57px;" class="form-control" 
									value="<s:property value="mrp"/>" placeholder='mrp'
									onkeyup='calSalPer()' onchange="calSalPer()" name='procurements[<%=i%>].mrp'
									id='mrp<%=i%>' /></td>
								<td><input type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control"
									placeholder='sale_price' name='procurements[<%=i%>].sale_price'
									id='sale_price<%=i%>' /></td>
								<td><select class="form-control" style="margin: 0;padding: 0;outline: 0;width:53px;" onchange='calVatTotal()'
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
							<%if(loginfo.isLmh()){ %>
							    <td><input type='number' style="margin: 0;padding: 0;outline: 0;width:60px;" class="form-control"  readonly="readonly"
									placeholder='Rate' value="<s:property value="purchase_price"/>"
									name='procurements[<%=i%>].purchase_price'
									onkeyup='calTotalAmt()' onchange="calTotalAmt()" id='purchase_price<%=i%>' /></td>
								<%}else{ %>
								<td><input type='number' style="margin: 0;padding: 0;outline: 0;width:60px;" class="form-control"
									placeholder='Rate' value="<s:property value="purchase_price"/>"
									name='procurements[<%=i%>].purchase_price'
									onkeyup='calTotalAmt()' onchange="calTotalAmt()" id='purchase_price<%=i%>' /></td>
								<%} %>	
								<td style="background-color: rgba(210, 105, 30, 0.11);"><input 
									type='text' style="margin: 0;padding: 0;outline: 0;" class="form-control search-text" value=""
									placeholder='batch no' name='procurements[<%=i%>].batch_no'
									id='batch_no<%=i%>' /></td>
								<td style="background-color: rgba(210, 105, 30, 0.11);"><input
									type='text'  style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='MM/YYYY'
									name='procurements[<%=i%>].expiry_date' id='expiry_date<%=i%>' />
								</td>

								<td style="background-color: rgba(210, 105, 30, 0.11);"><input
									type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='Pack Qty'
									onchange='calTotalAmt()' value="<s:property value="quantity" />"
									name='procurements[<%=i%>].received_qty'
									id='received_qty<%=i%>' /></td>	
								<td style="background-color: rgba(210, 105, 30, 0.11);"><input
									type='number' style="margin: 0;padding: 0;outline: 0;width:40px;" style="background-color: #ccc;"
									class="form-control" readonly="readonly" placeholder='Rec Qty'
									name='procurements[<%=i%>].newreceived_qty'
									id='newreceived_qty<%=i%>' /></td>
								<%if(loginfo.isBams1()){ %>	
								<td><s:select  list="unitOfMeseurementList" theme="simple"  cssClass="form-control chosen-select" id="uom" name="uom" headerKey="" headerValue="Select" style="margin: 0;padding: 0;outline: 0;width:100px;" ></s:select></td> 
							   <%}else{ %>
							    <td class= "hidden"><s:select cssStyle="width:90%;" list="unitOfMeseurementList" theme="simple"  cssClass="form-control chosen-select" id="uom" name="uom" headerKey="" headerValue="Select" style="background-color: rgba(245, 245, 245, 0.46);" ></s:select></td> 
							   <%} %>	
								<td style="background-color: rgba(210, 105, 30, 0.11);">
									<input type='number' style="margin: 0;padding: 0;outline: 0;width:40px;" class="form-control" placeholder='Discount'
									onkeyup='calTotalAmt()' name='procurements[<%=i%>].discount' value='<s:property value="discount"/>'
									id='discount<%=i%>' /></td>
								<%if(loginfo.isParihar()){ %>	
								<td style="background-color: rgba(210, 105, 30, 0.11);">
									<input type='number' style="margin: 0;padding: 0;outline: 0;width:34px;" class="form-control" placeholder='scheme'
									onkeyup='calTotalAmt()' name='procurements[<%=i%>].scheme' value='<s:property value="scheme"/>' id='scheme<%=i%>' /></td>
								<%}else{ %>
								<td class= "hidden" style="background-color: rgba(210, 105, 30, 0.11);">
									<input type='hidden' style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='scheme'
									onkeyup='calTotalAmt()' name='procurements[<%=i%>].scheme' value='0'
									id='scheme<%=i%>' /></td>
								
								<%} %>
								<td style="background-color: rgba(210, 105, 30, 0.11);"><input
									type='number' style="margin: 0;padding: 0;outline: 0;" class="form-control" placeholder='Free'
									name='procurements[<%=i%>].freeqty' id='freeqty<%=i%>' /></td>
								<%if(loginfo.isParihar()){ %>	
								<td class="hidden" style="background-color: rgba(210, 105, 30, 0.11);">
									<%-- <s:select theme="simple" list="cellList" listKey="name" listValue="name" cssClass="form-control" name="procurements[<%=i %>].shelf" id="shelf<%=i%>"  ></s:select> --%>
									<select name="procurements[<%=i%>].shelf" id="shelf<%=i%>"
									class="form-control" style="margin: 0;padding: 0;outline: 0;">
										<s:iterator value="shelfList">
											<s:if test="status==1">
												<option value='<s:property value="name"/>'
													selected="selected"><s:property value="name" /></option>
											</s:if>
											<s:else>
												<option value='<s:property value="name"/>'><s:property
														value="name" /></option>
											</s:else>

										</s:iterator>
									</select>
									
									<select name="procurements[<%=i%>].secondary_shelf" id="secondary_shelf<%=i%>"
									class="form-control" style="margin: 0;padding: 0;outline: 0;">
										<s:iterator value="secondshelfList">
											<s:if test="status==1">
												<option value='<s:property value="name"/>'
													selected="selected"><s:property value="name" /></option>
											</s:if>
											<s:else>
												<option value='<s:property value="name"/>'><s:property
														value="name" /></option>
											</s:else>

										</s:iterator>
									</select>
									
								</td>
								<%}else{ %>
								<td style="background-color: rgba(210, 105, 30, 0.11);">
									<%-- <s:select theme="simple" list="cellList" listKey="name" listValue="name" cssClass="form-control" name="procurements[<%=i %>].shelf" id="shelf<%=i%>"  ></s:select> --%>
									<select name="procurements[<%=i%>].shelf" id="shelf<%=i%>"
									class="form-control" style="margin: 0;padding: 0;outline: 0;">
										<s:iterator value="shelfList">
											<s:if test="status==1">
												<option value='<s:property value="name"/>'
													selected="selected"><s:property value="name" /></option>
											</s:if>
											<s:else>
												<option value='<s:property value="name"/>'><s:property
														value="name" /></option>
											</s:else>

										</s:iterator>
									</select>
									
									<select name="procurements[<%=i%>].secondary_shelf" id="secondary_shelf<%=i%>"
									class="form-control" style="margin: 0;padding: 0;outline: 0;">
										<s:iterator value="secondshelfList">
											<s:if test="status==1">
												<option value='<s:property value="name"/>'
													selected="selected"><s:property value="name" /></option>
											</s:if>
											<s:else>
												<option value='<s:property value="name"/>'><s:property
														value="name" /></option>
											</s:else>

										</s:iterator>
									</select>
									
								</td>
								
								
								<%} %>
								<%-- <td><i class='fa fa-plus-circle' aria-hidden='true' title='Add Batch' onclick='opennewBatchProduct(<s:property value="product_id"/>,<%=i%>)' style='color:#6699CC;cursor:pointer;'></i> <input type='hidden' id='newproduct<%=i %>' value='0' name='procurements[<%=i%>].newproduct'> </td> --%>
								<td><i class='fa fa-plus-circle' aria-hidden='true'
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
<!-- 								<td><a href='#' onclick='deleteLongPoProduct(this)'><i class='fa fa-times fa-x text-danger' ></i></a></td>
 -->				            <td><span onclick='deleteLongPoProduct(this)'><i class='fa fa-times fa-x text-danger' ></i></span></td>
 
                               </tr>
							<%
								i++;
							%>
						</s:iterator>

					</tbody>
					<input type="hidden" id="tempcount" value="<%=(++i)%>" />
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
						style="width: 100%; margin-top: 10px; border: 1px solid #ddd;">
						<thead>
							<tr>
								<th style="background-color: brown; width: 12%;">GST</th>
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
									style="background-color: brown; text-align: right; width: 18%;">Tax
									Amt.</th>
								<%if(loginfo.isParihar()){ %>
									<th hidden
									style="background-color: brown; text-align: right; width: 18%;">Scheme
									Amt.</th>
									<%} %>
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
						<label for="comment">Remark:</label>
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
									style="width: 45%; background-color: rgba(255, 0, 0, 0.22);"><option
											value="0">Percent</option>
										<option value="1">Cash</option></select> DISCOUNT :</td>
								<!-- <td><input type="number" class="form-control" onkeyup="calDiscNet(this.value)" name="discount" readonly="readonly" value="0" id="discount" style="background-color: rgba(220, 220, 220, 0.28);color: red;margin-bottom: 5px;"/></td> -->
								<td><input type="number" class="form-control"
									onkeyup="calTotalAmt()" name="discount" readonly="readonly"
									value="0" id="discount"
									style="background-color: rgba(220, 220, 220, 0.28); color: red; margin-bottom: 5px;" /></td>
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
								<td><input type="number" class="form-control"
									onkeyup="calVatDiscount()" value="0.00" name="surcharge"
									id="subcharge" style="margin-bottom: 5px;"></td>
							</tr>
							<tr>
								<td style="text-align: right;">CREDIT :</td>
								<td><input type="number" class="form-control"
									onkeyup="calVatDiscount()" id="credit" name="credit"
									style="margin-bottom: 5px;"></td>
							</tr>
							<tr>
								<td style="text-align: right;">DEBIT :</td>
								<td><input type="number" class="form-control"
									onkeyup="calVatDiscount()" id="debit" name="debit"
									style="margin-bottom: 5px;"></td>
							</tr>
							<tr>
								<td style="text-align: right; color: green;"><label>NET
										PAYBLE AMT :</label></td>
								<td><input type="number" readonly="readonly"
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
		</s:form>
		<a href="#" onclick="savePoReceived()"
			class="btn btn-primary savebigbtn"
			style="margin-top: 15px; float: right;">Goods Received</a>
	</div>






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
								<!-- <tr>
         <th style="width: 27%;">Supplier</th>
         <th style="width: 9%;">PO Date</th>
         <th style="width: 7%;">Quantity</th>
         <th style="width: 7%;background-color: brown;text-align:right;">Price</th>
         <th style="width: 7%;">Dis</th>
         <th style="width: 7%;">GST</th>
        </tr> -->
								<tr>
									<th style="width: 27%;">Supplier</th>
									<th style="width: 9%;">GRN Date</th>
									<th style="width: 7%;">GRN</th>
									<th style="width: 7%;">Invoice No.</th>
									<th style="width: 7%;">GST</th>
									<th style="width: 7%;">Quantity</th>
									<th style="width: 7%;">Free Qty</th>
									<th
										style="width: 7%; background-color: brown; text-align: right;">MRP</th>
									<th
										style="width: 7%; background-color: brown; text-align: right;">Sale
										Price</th>
									<th
										style="width: 7%; background-color: brown; text-align: right;">Purchase
										Price</th>
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
						<s:form action="savenewpoProcurement" theme="simple" method="post"
							id="poform">
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
					<h4 class="modal-title" id="suppliername">Supplier Name</h4>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12">
						<div class="from-group">
							<label>Generic Name</label> <input type="text"
								class="form-control" id="genericname" required="required">
						</div>

					</div>
					<div class="col-lg-12 col-md-12" style="margin-top: 10px;">

						<div class="from-group">
							<label>Product Name</label> <input type="text"
								class="form-control" id="prodname" required="required">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveProductProcurement()" style="margin-top: 15px;">Save</button>
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

</body>
</html>