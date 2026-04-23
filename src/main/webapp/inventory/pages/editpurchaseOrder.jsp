<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />
<link href="plugin/monthyear/MonthPicker.min.css" rel="stylesheet" type="text/css" />
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
.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
    cursor: not-allowed;
    opacity: 1;
    background-color: rgba(221, 221, 221, 0.28);
}
#ui-datepicker-div {    z-index: 1000 !important;
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

<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>

<SCRIPT type="text/javascript">

     window.onload= function(){
    	 
    	 document.addEventListener("contextmenu", function(e){
    			e.preventDefault();
    			}, false); 
    	 
    	 $('.dclass').each(function() {
             
   			 var i=this.value;
   			 /*  $("#expiry_date"+i).datepicker({

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
     
                $("#expiry").datepicker({

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
			   $("#voucherdate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

			   });
			   
			   $("#delivermemodate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

			   });
			   
		
				                                setTindex();
				                                //calSubTotal();
				                                //calVatDiscount();
				                                
				                                
				                                
     } 

</SCRIPT>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
</head>
<body>
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
							</div>

<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									
										<h4>Goods Received</h4>
										<s:hidden id="isfromeditpo" value="1"></s:hidden>
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
									</div>
								</div>
								<div class="">
								<div class="hidden" style="background-color: rgba(239, 239, 239, 0.42);padding: 9px;border: 1px dashed #ddd;">
	<table class="table table-striped table-bordered" style="width:100%;">
         
          <tbody>
          	<tr>
          		<td style="border-top: none;width: 15%;">
          		   <% if(loginfo.getUserType()==2){  %>
        			<%--  <select name="warehouse" id="warehouse" class="form-control chosen-select" >
					      <option value="0">Select Store</option>
					    <option value="1">Central Drug Store</option>
					    <option value="2">Cenral Material Store</option>
				   </select>  --%>
				   <s:select list="warehouseList" id="warehouse" name="warehouse"
						listKey="id" listValue="name" headerKey="0" headerValue="Select"
						cssClass="form-control chosen-select"></s:select> 
				   <%} else {%>
				          <s:hidden name="warehouse" id="warehouse"> </s:hidden>
				  <%} %>
          		</td>
          		<td id="vendid" style="border-top: none;width: 15%;">
          			<s:select theme="simple" id="vendorid" name="vendor" disabled="true" readOnly="true" onchange="setVendorProductForPo(this.value)" list="vendorList" listKey="id" listValue="name" headerKey="0" headerValue="Select Supplier" cssClass="form-control chosen-select"   />
          		</td>
          		<td id="proddiv" style="border-top: none;width: 15%;">
        				<s:select list="vendorProductList" theme="simple" listKey="id" listValue="product_name" headerKey="0" headerValue="Select Product" cssClass="form-control chosen-select" id="product_id" name="product_id" ></s:select>
          		</td>
          		<td id="categorydiv" style="border-top: none; width: 15%;" class="hidden" > 
        				 <s:select list="categoryList" theme="simple" listKey="id" readOnly="true" listValue="name" cssClass="form-control" id="categoryid" name="category_id" ></s:select>
          		</td>
          		<td id="subdiv" style="border-top: none;width: 15%;" class="hidden">
        				<s:select list="subcategoryList" theme="simple" listKey="id" readOnly="true" listValue="name" cssClass="form-control" id="subcategoryid" name="subcategory_id" ></s:select>
          		</td>
          		
          		<td style="border-top: none;" class="hidden">
          			<input type="number" readonly="readonly" class="form-control" id="rate" placeholder="Rate">
          		</td>
          		<!-- <td style="border-top: none;">
          			<input type="number" readonly="readonly" class="form-control" id="discount" placeholder="Discount">
          		</td>
          		<td style="border-top: none;">
          			<input type="number" class="form-control" id="qty" placeholder="Qty">
          			<td style="border-top: none;"><a href="#"  onclick="addProductForProcurement('prodTable')" class="btn btn-success">Add</a></td>
          		</td> -->
          		<td style="border-top: none;"><a href="#"  class="btn btn-success">Add</a></td>
          		<!-- <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addproduct">Add Product</button></td>  -->
          	</tr>
          	</tbody>
         </table>
         
         
</div>
								<s:form theme="simple" action="updateprocurementProcurement" id="formMyPo">	
									<input type="hidden" id="isfromeditpo" value="1">
								  <s:hidden name="id"></s:hidden>
								  <s:hidden name="procurementid" id="procurementid"></s:hidden>
								  <s:hidden name="iseditlocation" id="iseditlocation"></s:hidden>
									<input type="hidden" value="0" id="repeat" />
									<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
										<b style="color: brown;"> <span id="vendorName"> <s:property value="name"/> </span> &nbsp; | &nbsp;<small style="color:green;">Purchase Date : <s:property value="date"/></small> 
										<s:if test="isdelivermemo==0">
											&nbsp; | &nbsp; <!--onchange="chkvoucher(this.value)"  -->
											<s:textfield  placeholder="Enter Voucher No"  cssClass="form-control"  id="voucherno" required="required" name="voucherno" cssStyle="width: 10%;background-color: cornsilk;" /> <label id="errvoucher" style="color:red" ></label> 
											
											&nbsp; | &nbsp; 
											<s:textfield  cssClass="form-control" id="voucherdate" readOnly="true" name="voucherdate" placeholder="Voucher Date" cssStyle="width: 10%;background-color: cornsilk;" /> 
											
											&nbsp; | &nbsp; 
											<s:textfield placeholder="Security Inward No" cssClass="form-control"  id="security_no" required="required" name="security_no" cssStyle="width: 10%;background-color: cornsilk;" /> 
											
											&nbsp; | &nbsp; 
											<s:textfield  placeholder="Security Inward Date" readOnly="true" cssClass="form-control"  id="security_date" required="required" name="security_date" cssStyle="width: 10%;background-color: cornsilk;" /> 
											
											&nbsp; | &nbsp; 
											<s:textfield  placeholder="GRN Amount" cssClass="form-control"  id="firstnetamout" required="required" name="firstnetamout" cssStyle="width: 10%;background-color: cornsilk;" /> 
											
											<input type="checkbox" class="hidden" id="isdelivermemo" onclick="return false;" name="isdelivermemo" onclick="changeDMTextbox(this.checked)" >&nbsp; <span class="hidden">Is Deliver Memo?</span>
											
											<s:hidden id="delivermemoid" name="delivermemoid" ></s:hidden>
 											<s:hidden id="delivermemodate" name="delivermemodate"></s:hidden>
											<!-- &nbsp; | &nbsp; 
											<input type="text" placeholder="Deliver Memo No." class="form-control" id="delivermemoid" required="required"
											name="delivermemoid" style="width: 10%; background-color: cornsilk;" readonly="readonly" /> 
										
											&nbsp; | &nbsp; 
											<input type="text" placeholder="Deliver Memo Date" class="form-control" readonly="readonly" id="delivermemodate" required="required"
											name="delivermemodate" style="width: 10%; background-color: cornsilk;" />  -->
										</s:if>
										<s:else>
											<%-- &nbsp; | &nbsp; 
											<s:textfield  placeholder="Enter Voucher No" readonly="true" onchange="chkvoucher(this.value)" cssClass="form-control"  id="voucherno" required="required" name="voucherno" cssStyle="width: 10%;background-color: cornsilk;" /> <label id="errvoucher" style="color:red" ></label> 
											
											&nbsp; | &nbsp; 
											<s:textfield  cssClass="form-control" readonly="true" id="voucherdate" name="voucherdate" placeholder="Voucher Date" cssStyle="width: 10%;background-color: cornsilk;" /> 
											
											&nbsp; | &nbsp; 
											<s:textfield placeholder="Security Inward No" readonly="true" cssClass="form-control"  id="security_no" required="required" name="security_no" cssStyle="width: 10%;background-color: cornsilk;" /> 
											
											&nbsp; | &nbsp; 
											<s:textfield  placeholder="Security Inward Date" readonly="true" cssClass="form-control"  id="security_date" required="required" name="security_date" cssStyle="width: 10%;background-color: cornsilk;" /> 
 											--%>	
 											<s:hidden id="voucherno" name="voucherno" ></s:hidden>
 											<s:hidden id="voucherdate" name="voucherdate"></s:hidden>
 											<s:hidden id="security_no" name="security_no"></s:hidden>
 											<s:hidden id="security_date" name="security_date"></s:hidden>										
											&nbsp; |  &nbsp; 
											<input type="checkbox" onclick="return false;" class="hidden" id="isdelivermemo" checked="checked" name="isdelivermemo" onclick="changeDMTextbox(this.checked)" >&nbsp; <span>Is Deliver Memo?</span>
											
											&nbsp; | &nbsp; 
											<s:textfield  placeholder="Deliver Memo No."  cssClass="form-control"  id="delivermemoid" required="required" name="delivermemoid" cssStyle="width: 10%;background-color: cornsilk;" /> 
											
											&nbsp; | &nbsp; 
											<s:textfield  placeholder="Deliver Memo Date"  cssClass="form-control"  id="delivermemodate" required="required" name="delivermemodate" cssStyle="width: 10%;background-color: cornsilk;" />
											
											&nbsp; | &nbsp; 
											<s:textfield  placeholder="GRN Amount" cssClass="form-control"  id="firstnetamout" required="required" name="firstnetamout" cssStyle="width: 10%;background-color: cornsilk;" />
										</s:else>
										
										</b>
										<table class="table my-table xlstable table-bordered" style="width: 100%;margin-top: 10px;margin-bottom:0px;" id='prodTable'>
				                            <thead style="position: -webkit-sticky; position: sticky; top: 0px; z-index: 100;">
				                                <tr>
				                                    <th>Sr</th>
				                                    <th style="width: 15%;">Product</th>
				                                    <th style="width: 4%;">Pack</th>
				                                    <!-- @rahul req quntity show in edit -->
				                                    <th style="width: 4%;">Req Qty</th>
				                                    <th style="width: 7%;">HSN No</th>
				                                    <th style="width: 5%;">Mfg</th>
				                                    <th style="width: 7%;" class="">MRP</th>
				                                    <th style="width: 6%;">per unit</th>
				                                    <th style="width: 5%;">GST</th>
				                                    <th style="width: 7%;">Rate</th>
				                                    <th style="width: 8%;">Batch No</th>
				                                    <th style="width: 7%;">ExpDate</th>
				                                    <th style="width: 5%;">Pack Qty</th>
				                                    <th style="width: 5%;">Rec.Qty</th>
				                                    <th style="width: 4%;">Dis</th>
				                                    <%if(loginfo.isParihar()){ %>
							                        <th style="width: 5%;">Scheme</th>
							                       <%}%>
				                                    <th style="width: 5%;">Free Qty</th>
				                                    <th style="width:5%;">Shelf No</th>
				                                    <th></th>
				                                    <th>Amt</th>
				                                    <th>Discount Amt</th>
				                                    <th>GST Amt</th>
				                                    <th>Net Total</th>
				                                    <th></th>
				                                    <th></th>
				                                </tr>
				                            </thead>
				                            <tbody id="receiveddata">
				                               <tr ></tr>
				                                  <%int i=0; %>
				                                  <% String color=""; %>
				                                  <s:iterator value="productList">
				                                   
				                                        <s:if test="edit==1">
				                                           <%color=" rgba(221, 221, 221, 0.28)"; %>
				                                        </s:if>
				                                        <s:else>
				                                           <%color="white"; %>
				                                        </s:else>
				                                   
				                                   <tr style="background-color: <%=color%>">
				                            	   
				                            		<td><%=(i+1) %>
				                            		  
				                            		 <input type="hidden" value="<%=i %>" class="dclass" />
				                            		 <input type="hidden" value="<s:property value="id"/>" name="procurements[<%=i%>].id" /> </td>
				                            		 <input type="hidden" value="<s:property value="edit"/>" name="procurements[<%=i%>].isedit" /> </td>
				                            		
				                            		<td><s:property value="pro_code"/> - <s:property value="product_name"/> 
				                            		 <%--  <s:if test="edit==1">
				                            		     T
				                            		  </s:if> --%>
				                            		<input type="hidden" value="<s:property value="product_id"/>" name="procurements[<%=i%>].product_id" /> 
				                            		  <br><small style='font-size: 11px;'>( <s:property value="pack"/> <%-- <input type='hidden' value='<s:property value="pack"/>' name='procurements[<%=i%>].pack' id='pack<%=i%>'  /> --%> / <s:property value="type_name"/> <input type='hidden' value='<s:property value="medicine_shedule"/>' name='procurements[<%=i %>].medicine_shedule' id='medicine_shedule<%=i%>'  />) </small>
				                            				<s:if test="edit==1">
				                            		     		<input type="text" readonly="readonly" value="<s:property value="barcode" />" name="procurements[<%=i%>].barcode" id="barcode<%=i%>" placeholder="Barcode" />
						                                   	</s:if>
						                                   	<s:else>
					                                        	<input type="text" name="procurements[<%=i%>].barcode" value="<s:property value="barcode" />" id="barcode<%=i%>" placeholder="Barcode" />
					                                   	   	</s:else>
				                            			
				                            		</td>
				                            		
				                            		<td>
				                            			<%if(loginfo.isProc_after_stock()){%>
				                            				<input style="margin: 0;padding: 0;outline: 0;" type='number'  class="form-control" placeholder='pack' onchange="calSalPerEdit(<%=i%>)" value="<s:property value="pack" />" name='procurements[<%=i %>].pack' id='pack<%=i%>' />
				                            			<%}else{ %>
				                            				<s:if test="edit==1">
				                            		     		<input style="margin: 0;padding: 0;outline: 0;" type='number' onchange="calSalPerEdit(<%=i%>)"  readonly="readonly" class="form-control" placeholder='pack' value="<s:property value="pack" />" name='procurements[<%=i %>].pack' id='pack<%=i%>' />
						                                   	</s:if>
						                                   	<s:else>
					                                        	<input style="margin: 0;padding: 0;outline: 0;" type='number'  class="form-control" placeholder='pack' onchange="calSalPerEdit(<%=i%>)" value="<s:property value="pack" />" name='procurements[<%=i %>].pack' id='pack<%=i%>' />
					                                   	   	</s:else>
				                            			<%}%>
				                            		 </td>
				                            		 <!-- @rahul req quntity show in edit -->
				                            		<td><span id="qun<%=i%>"><s:property value="quantity" /></span></td>
				                            		<td>
				                            		   <s:if test="edit==1">
				                            		     <input style="margin: 0;padding: 0;outline: 0;" type='number' maxlength='8' readonly="readonly" class="form-control" placeholder='hsn no' value="<s:property value="hsnno" />" name='procurements[<%=i %>].hsnno' id='hsnno<%=i%>' />
					                                   </s:if>
					                                   <s:else>
				                                        <input style="margin: 0;padding: 0;outline: 0;" type='number' maxlength='8'  class="form-control" placeholder='hsn no' value="<s:property value="hsnno" />" name='procurements[<%=i %>].hsnno' id='hsnno<%=i%>' />
				                                   </s:else>
				                            		
				                            		</td>
				                            		<td>
				                            		 <s:if test="edit==1">
				                            		      <input style="margin: 0;padding: 0;outline: 0;" type="text" readonly="readonly" value="<s:property value="mfg"/>" id="mfg<%=i%>" class="form-control" name="procurements[<%=i%>].mfg" required="required">
				                                     </s:if>
				                                     <s:else>
				                                     	<%if(loginfo.isAuto_generic_name()){ %>
															<select class="form-control chosen-select" style="margin: 0;padding: 0;outline: 0;" name='procurements[<%=i%>].mfg' id='mfg<%=i%>'>
																<option value=''>MFG</option>
																<s:iterator value="mfglist">
																	<s:if test="name==mfg">
				                                   						<option value='<s:property value="name"/>' selected="selected"><s:property value="name"/></option>
				                                   					</s:if>
					                                   				<s:else>
					                                   					<option value='<s:property value="name"/>'><s:property value="name"/></option>
					                                   				</s:else>
																</s:iterator>
															</select>
														<%}else{ %>
															<input style="margin: 0;padding: 0;outline: 0;" type="text"  value="<s:property value="mfg"/>" id="mfg<%=i%>" class="form-control" name="procurements[<%=i%>].mfg" required="required">
														<%} %>
				                                       
				                                    </s:else>
				                            		
				                            		</td>
				                            		<td class="">
				                            		<s:if test="edit==1">
				                            		    <input style="margin: 0;padding: 0;outline: 0;" type="number" readonly="readonly" value="<s:property value="mrp"/>" onkeyup="calSalPer()" class="form-control" id="mrp<%=i%>" name="procurements[<%=i%>].mrp" required="required">
				                                   </s:if>
				                                   <s:else>
				                                        <input style="margin: 0;padding: 0;outline: 0;" type="number"  value="<s:property value="mrp"/>" onkeyup="calSalPer()" class="form-control" id="mrp<%=i%>" name="procurements[<%=i%>].mrp" required="required">
				                                   </s:else>
				                            		</td>
				                            		<td>
				                            		 <s:if test="edit==1">
				                            		    <input style="margin: 0;padding: 0;outline: 0;" type="number" readonly="readonly" class="form-control" value="<s:property value="sale_price"/>" id="sale_price<%=i%>" name="procurements[<%=i%>].sale_price" required="required">
				                            		 </s:if>
				                            		 <s:else>
				                            		   <input style="margin: 0;padding: 0;outline: 0;" type="number" class="form-control" value="<s:property value="sale_price"/>" id="sale_price<%=i%>" name="procurements[<%=i%>].sale_price" required="required">
				                            		 </s:else>
				                            		</td>
				                            		
				                            		 <td>
				                            		   <s:if test="edit==1">
				                            		   	 <select style="margin: 0;padding: 0;outline: 0;" class="form-control" onchange='calVatTotal()'   name='procurements[<%=i %>].vat' id='vat<%=i%>' >
				                                           <s:if test="vat==0">
				                                             <option value='0' selected="selected">0%</option>
				                                             <option value='5'>5%</option>
				                                             <option value='12'>12%</option>
				                                             <option value='18'>18%</option>
				                                             <option value='28'>28%</option>
				                                          </s:if>
				                                          <s:if test="vat==5">
				                                             <option value='0'>0%</option>
				                                             <option value='5' selected="selected">5%</option>
				                                             <option value='12'>12%</option>
				                                             <option value='18'>18%</option>
				                                             <option value='28'>28%</option>
				                                          </s:if>
				                                           <s:if test="vat==12">
				                                             <option value='0'>0%</option>
				                                             <option value='5'>5%</option>
				                                             <option value='12' selected="selected">12%</option>
				                                             <option value='18'>18%</option>
				                                             <option value='28'>28%</option>
				                                          </s:if>
				                                           <s:if test="vat==18">
				                                             <option value='0'>0%</option>
				                                             <option value='5'>5%</option>
				                                             <option value='12'>12%</option>
				                                             <option value='18' selected="selected">18%</option>
				                                             <option value='28'>28%</option>
				                                          </s:if>
				                                           <s:if test="vat==28">
				                                             <option value='0'>0%</option>
				                                             <option value='5'>5%</option>
				                                             <option value='12'>12%</option>
				                                             <option value='18'>18%</option>
				                                             <option value='28' selected="selected">28%</option>
				                                          </s:if>
				                                          
				                                       </select>
				                            		   </s:if>
				                            		   <s:else>
				                            		       <select style="margin: 0;padding: 0;outline: 0;" class="form-control"  onchange='calVatTotal()' name='procurements[<%=i %>].vat' id='vat<%=i%>' >
				                                          <option value='0'>GST</option>  
				                                          <option value='0'>0%</option>
				                                          <s:if test="vat==5">
				                                             <option value='5' selected="selected">5%</option>
				                                          </s:if>
				                                          <s:else>
				                                             <option value='5'>5%</option>
				                                          </s:else>
				                                           <s:if test="vat==12">
				                                             <option value='12' selected="selected">12%</option>
				                                          </s:if>
				                                          <s:else>
				                                             <option value='12'>12%</option>
				                                          </s:else>
				                                           <s:if test="vat==18">
				                                             <option value='18' selected="selected">18%</option>
				                                          </s:if>
				                                          <s:else>
				                                             <option value='18'>18%</option>
				                                          </s:else>
				                                           <s:if test="vat==28">
				                                             <option value='28' selected="selected">28%</option>
				                                          </s:if>
				                                          <s:else>
				                                             <option value='28'>28%</option>
				                                          </s:else>
				                                          
				                                       </select>
				                            		   
				                            		   </s:else>
				                                      
				                                      
				                                   </td>
				                            		<td>
				                            		  <s:if test="edit==1">
				                            		     <input style="margin: 0;padding: 0;outline: 0;" type="number" value="<s:property value="purchase_price"/>" onchange="calTotalAmt()" onkeyup="calTotalAmt()" class="form-control"  id="purchase_price<%=i%>" name="procurements[<%=i%>].purchase_price" required="required">
				                            		  </s:if>
				                            		  <s:else>
				                            				<input style="margin: 0;padding: 0;outline: 0;" type="number" value="<s:property value="purchase_price"/>" onchange="calTotalAmt()" onkeyup="calTotalAmt()" class="form-control"  id="purchase_price<%=i%>" name="procurements[<%=i%>].purchase_price" required="required">  
				                            		  </s:else>
				                            		</td>
				                            		<td>
				                            		    <s:if test="edit==1">
				                            		    	<input style="margin: 0;padding: 0;outline: 0;" type="text" readonly="readonly" value="<s:property value="batch_no"/>" id="batch_no<%=i%>" class="form-control" required="required" name="procurements[<%=i%>].batch_no" />
				                            		    </s:if>
				                            		    <s:else>
				                            		       <input style="margin: 0;padding: 0;outline: 0;" type="text"  value="<s:property value="batch_no"/>" id="batch_no<%=i%>" class="form-control" required="required" name="procurements[<%=i%>].batch_no" />
				                            		    </s:else>
				                            		    
				                           
				                            		</td>
				                            		<td>
				                            		    <s:if test="edit==1">
				                            		       <input style="margin: 0;padding: 0;outline: 0;" type="text" readonly="readonly" id="expiry_date<%=i%>" value="<s:property value="newexpirydate"/>"  class="form-control" name="procurements[<%=i%>].expiry_date"  required="required" placeholder="MM/YYYY" />
				                            		    </s:if>
				                            		    <s:else>
				                            				<input style="margin: 0;padding: 0;outline: 0;" type="text"  value="<s:property value="newexpirydate"/>" id="expiry_date<%=i %>" class="form-control" name="procurements[<%=i%>].expiry_date"  required="required" placeholder="MM/YYYY" />    
				                            		    </s:else>
				                            		</td>
				                            		<td>
				                            		
				                            			<%if(loginfo.isProc_after_stock()){%>
				                            				<input style="margin: 0;padding: 0;outline: 0;" type="number" class="form-control" value="<s:property value="qty"/>" onfocus="calTotalAmt()" onchange='calTotalAmt()' id="received_qty<%=i%>" name="procurements[<%=i%>].received_qty" required="required">
				                            			<%}else{ %>
				                            				<s:if test="edit==1">
						                            		   <input style="margin: 0;padding: 0;outline: 0;" type="number" readonly="readonly" class="form-control" value="<s:property value="qty"/>" onchange='calTotalAmt()' id="received_qty<%=i%>" name="procurements[<%=i%>].received_qty" required="required">
						                            		 </s:if>
						                            		 <s:else>
						                            		 	<input style="margin: 0;padding: 0;outline: 0;" type="number" class="form-control" value="<s:property value="qty"/>" onchange='calTotalAmt()' id="received_qty<%=i%>" name="procurements[<%=i%>].received_qty" required="required">
						                            		 </s:else>
				                            			<%}%>
				                            		
				                            		 
				                            		
				                            		</td>
				                            		<td>
				                            		   <input style="margin: 0;padding: 0;outline: 0;" type="number" readonly="readonly" class="form-control" value="<s:property value="qty"/>"  id="newreceived_qty<%=i%>" name="procurements[<%=i%>].newreceived_qty" required="required">
				                            		</td>
				                            		<td>
				                            		 <s:if test="edit==1">
				                            		 	<input style="margin: 0;padding: 0;outline: 0;" type='number' readonly="readonly" class="form-control" placeholder='Discount' value="<s:property value="discount"/>" onkeyup='calTotalAmt()' name='procurements[<%=i %>].discount' id='discount<%=i%>' />
				                            		 </s:if>
				                            		 <s:else>
				                            		    <input style="margin: 0;padding: 0;outline: 0;" type='number' class="form-control" placeholder='Discount' value="<s:property value="discount"/>" onkeyup='calTotalAmt()' name='procurements[<%=i %>].discount' id='discount<%=i%>' />
				                            		 </s:else>
				                            		
				                            		</td>
				                            		
				                            		<td>
				                            		 <s:if test="edit==1">
				                            		 	<input style="margin: 0;padding: 0;outline: 0;" type='number' readonly="readonly" class="form-control" placeholder='Scheme Discount' value="<s:property value="Scheme"/>" onkeyup='calTotalAmt()' name='procurements[<%=i %>].scheme' id='scheme<%=i%>' />
				                            		 </s:if>
				                            		 <s:else>
				                            		    <input style="margin: 0;padding: 0;outline: 0;" type='number' class="form-control" placeholder='Scheme Discount' value="<s:property value="Scheme"/>" onkeyup='calTotalAmt()' name='procurements[<%=i %>].scheme' id='scheme<%=i%>' />
				                            		 </s:else>
				                            		
				                            		</td>
				                            		
				                            		<td>
				                        				 <s:if test="edit==1">
				                        				 		<input style="margin: 0;padding: 0;outline: 0;" type="number" readonly="readonly" class="form-control" value="<s:property value="freeqty"/>"  name="procurements[<%=i %>].freeqty" id="freeqty<%=i %>"  required="required">
				                        				 </s:if>
				                        				 <s:else>
				                        				      <input style="margin: 0;padding: 0;outline: 0;" type="number" class="form-control" value="<s:property value="freeqty"/>"  name="procurements[<%=i %>].freeqty" id="freeqty<%=i %>"  required="required">
				                        				 </s:else>
				                            		</td>
				                            		<td>
				                            		   <%-- <s:select list="cellList" name="procurements[<%=i %>].shelf" cssClass="form-control"  listKey="id" listValue="name"></s:select> --%>
				                            			<select style="margin: 0;padding: 0;outline: 0;" name="procurements[<%=i %>].shelf" id="shelf<%=i%>" class="form-control">
				                                   			<s:iterator value="shelfList">
				                                   				<s:if test="status==1">
				                                   					<option value='<s:property value="name"/>' selected="selected"><s:property value="name"/></option>
				                                   				</s:if>
				                                   				<s:else>
				                                   					<option value='<s:property value="name"/>'><s:property value="name"/></option>
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
				                            		<td></td>
				                            		<td>Rs.<span style="margin: 0;padding: 0;outline: 0;" id="amount<%=i%>"></span></td>
				                            		<!-- For Parihar -->
				                                    <td>Rs.<span style="margin: 0;padding: 0;outline: 0;" id="discountamt<%=i%>"></span></td>
				                            		<td hidden>Rs.<span style="margin: 0;padding: 0;outline: 0;" id="totalschmedisc<%=i%>"></span></td>
				                            		<!-- End For Parihar -->
				                            		 <td>
				                                     Rs.<span style="margin: 0;padding: 0;outline: 0;" id='gstcalamount<%=i%>'>00.00</span>
				                                   </td>
				                            		<td>
				                                     Rs.<span style="margin: 0;padding: 0;outline: 0;" id='netamount<%=i%>'></span>
				                                   </td>
				                            		<td><%-- <input type="button" onclick="opennewBatchProduct(<s:property value="product_id"/>,<%=i%>)" class="btn btn-success" value="+"> <input type="hidden" id="newproduct<%=i %>" value="0" name="procurements[<%=i%>].newproduct">  --%></td>
				                            		<s:if test="edit==1">
				                            		<td></td>
				                            		</s:if>
				                            		<s:else>
				                            		
				                            		<td >
				                            		<%if(loginfo.getUserType()==2||loginfo.isRemoveprocurement()){ %>
				                            		<i class="fa fa-times hidden"  title="Delete Row" onclick="deletePONew(this,<s:property value="id"/>)" style="color:#d9534f;cursor:pointer;"></i>
				                            		<%} %>
				                            		 </td>
				                            		</s:else>
				                            	  </tr>
				                            	  <%i++; %>
				                            	</s:iterator>
				                            	<input type="hidden" value="<%=(i)%>" id="tindex" />
				                            </tbody>
				                            
				                            <input type="hidden" id="tempcount" value="<%=(i)%>" />
				                        </table>
				                        <input type="hidden" name="deleteids" id="deleteids" value="0">
				                        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;padding-bottom: 15px;">
		           <table class="table my-table xlstable table-bordered hidden" id="tableInner" style="width: 38%;">
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
          <div class="col-lg-4 col-md-4"></div>
          <div class="col-lg-5 col-md-5">
                  <table class="table my-table xlstable table-bordered" style="width: 100%;margin-top: 10px;border: 1px solid #ddd;">
                                <thead>
                                    <tr>
                                        <th style="background-color: brown;width: 12%;">GST%</th>
                                        <th style="background-color: brown;width: 20%;text-align:right;">Total Amt.</th>
                                        <th style="background-color: brown;width: 22%;text-align:right;">Taxable Amt.</th>
                                        <th style="background-color: brown;width: 19%;text-align:right;">Dis. Amt.</th>
                                        <th style="background-color: brown;text-align:right;width: 18%;">Tax Amt.</th>
                                        <th
										style="background-color: brown; text-align: right; width: 18%; text-align: right;">Scheme
										Amt.</th>
                                    </tr>
                                </thead>
                                <%i=0; %>
                                <tbody id="tTaxData">
                                   <s:iterator value="vatAllocationList">
                                      <tr>
                                      <td><span id="vatrate<%=i%>"><s:property value="vatrate"/></span>%</td> <input type="hidden" id="vatrateAmt<%=i%>" name="vatallocations[<%=i%>].vatrate" value="<s:property value="vatrate"/>" />
                                       <td id="totalVatAmt<%=i%>" > <s:property value="totalamt"/> </td> <input type='hidden' value="<s:property value="totalamt"/>" name="vatallocations[<%=i%>].totalamt" />
                                       <td><input type="number" id="vatgiven<%=i%>" class="form-control" name="vatallocations[<%=i%>].taxable" value="<s:property value="taxable"/>"  onchange="calVatDiscount()"  /></td>
                                       <td id="discvat<%=i%>" ><s:property value="discvat"/></td> <input type="hidden" id="discvatVal<%=i%>" value="<s:property value="discvat"/>"  name="vatallocations[<%=i%>].discvat" />
                                       <td id="taxAmt<%=i %>"> <s:property value="taxamt"/> </td><input type="hidden" id="taxAmtVal<%=i %>" value="<s:property value="taxamt"/>" name="vatallocations[<%=i %>].taxamt" /> 
                                        
                                   	  </tr>
                                   	  <%i++; %>
                                   </s:iterator>
                                   
                                    <input type="hidden" id="vatcount" value="<%=i%>" />
                                   
                               </tbody>
                            </table>
                               <div class="form-group">
										  <label for="comment" style="text-align:left;">Remark:</label>
										  <a class="hidden" href="#" data-toggle="modal" data-target="#viewdetailspro">view</a>
										  <s:textarea cssClass="form-control" rows="5" id="remark" name="remark" cssStyle="background-color: rgba(95, 95, 95, 0.04);"></s:textarea>
										</div>
                 </div>
                 <div class="col-lg-3 col-xs-3 col-md-3" style="padding:0px;">
                  <table style="width:100%;	">
                   <tbody>
                    <tr>
                     <td style="text-align: right;width:55%;">SUBTOTAL :</td>
                     <td><input type="text" readonly="readonly" id="subTotal" class="form-control" style="margin-bottom: 5px;"></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;color:red;"><s:select theme="simple" list="#{'0':'Percent','1':'Cash'}" name="disctype" id="disctype" onchange="getDiscType(this.value)" cssClass="form-control" cssStyle="width: 50%;background-color: rgba(255, 0, 0, 0.07);" /> DISCOUNT :</td>
                     <%-- <td><s:textfield cssClass="form-control" onkeyup="calVatDiscount()" name="discount" readonly="readonly" id="discount" cssStyle="background-color: rgba(220, 220, 220, 0.28);color: red;margin-bottom: 5px;"/></td> --%>
                    <td><s:textfield cssClass="form-control" onkeyup="calTotalAmt()" name="discount" readonly="readonly" id="discount" cssStyle="background-color: rgba(220, 220, 220, 0.28);color: red;margin-bottom: 5px;"/></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;">CGST :</td>
                     <td><input type="text" readonly="readonly" class="form-control"  id="tcgst" style="margin-bottom: 5px;"> <input type="hidden" name="vat" id="vat" /> <input type="hidden" name="cgst" id="cgst" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;">SGST :</td>
                     <td><input type="text" readonly="readonly" id="tsgst" class="form-control" style="margin-bottom: 5px;"/><input type="hidden" name="sgst" id="sgst" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;">IGST :</td>
                     <td><input type="text" readonly="readonly" class="form-control" id="tigst" style="margin-bottom: 5px;"/><input type="hidden" name="igst" id="igst" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;">SURCHARGE :</td>
                     <td><s:textfield cssClass="form-control"  onkeyup="calVatDiscount()" name="surcharge" id="subcharge" cssStyle="margin-bottom: 5px;" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;">CREDIT :</td>
                     <td><s:textfield cssClass="form-control" onkeyup="calVatDiscount()" id="credit" name="credit" cssStyle="margin-bottom: 5px;" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;">DEBIT :</td>
                     <td><s:textfield cssClass="form-control" onkeyup="calVatDiscount()" id="debit" name="debit" cssStyle="margin-bottom: 5px;" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;color:green;"><label>NET PAYBLE AMT :</label></td>
                     <td><input type="text" readonly="readonly" class="form-control" id="netpay" name="netpay" style="margin-bottom: 5px;    background-color: rgba(220, 220, 220, 0.28);color: green;"/>
                       <input type="hidden" id="nettemp" />  
                      
                     </td>
                    </tr>
                    <tr>
                     <td style="text-align: right;">PAYMENT :</td>
                     <td>
                      <select name="paymode" id="paymode" class="form-control">
				          <option value="Credit">CREDIT</option>
				         <!--  <option value="Cash">CASH</option> -->
				         </select>
                     </td>
                    </tr>
                   </tbody>
                  </table>
                 </div>
          </s:form>
          <a href="#" onclick="savePoReceived()" class="btn btn-primary savebigbtn" style="margin-top:15px;float:right;">Save</a>
         </div>
								
								
								
								
								
								
								
								
								
								
			 <!-- Create New Product Modal -->
<div id="newpo" class="modal fade" role="dialog" style="background-color: rgba(0, 0, 0, 0.59);">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add New Batch Order</h4>
      </div>
      <div class="modal-body">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
			   <s:form action="savenewpoProcurement" theme="simple" method="post" id="poform">
				<table class="table my-table xlstable table-bordered" style="width: 100%;" id="mynewtab" >
			<thead>
				<tr>
					<th style="width: 40%;">Batch Number</th>
					<th style="width: 38%;">Expiry</th>
					<th>Quantity</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input class="form-control" id="batch" placeholder="Batch Number"  type="text" /></td>
					<td><input class="form-control" id="expiry" placeholder="Expiry" type="text" /></td>
					<td><input class="form-control" id="qty" placeholder="Qty"  type="text" /></td>
				</tr>

			</tbody>

		</table>
		</s:form>
		<div class="col-lg-12 col-xs-12 hidden" style="padding:0px;margin-top:15px;">
			<div class="col-lg-12 col-md-12 text-right">
			
			<p>Total : Rs.<label id="lblPOTotal">00.00</label></p>
			</div>
		</div>
		<div class="col-lg-12 col-xs-12 hidden" style="padding:0px;margin-top:15px;">
			<div class="col-lg-6 col-md-6 text-left">
			<input value="Add more" onclick="addnewpo('mynewtab')" class="btn btn-primary" type="button">
			</div>
			<div class="col-lg-6 col-md-6 text-right" style="padding:0px;">
			<input value="Create PO" onclick="submitPo('')" class="btn btn-primary pull-right" type="button"></div>
			</div>
		
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" onclick="savetoProductPo('tableInner')" class="btn btn-primary" style="margin-top:15px;">Add Product</button>
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
        		<label>Generic Name</label>
				<input type="text" class="form-control" id="genericname" required="required">
        	</div>
        	
        </div>
        <div class="col-lg-12 col-md-12" style="margin-top: 10px;">
        	
        	<div class="from-group">
        		<label>Product Name</label>
				<input type="text" class="form-control" id="prodname" required="required">
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="saveProductProcurement()" style="margin-top: 15px;">Save</button>
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
								
								
								
								
								
								
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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