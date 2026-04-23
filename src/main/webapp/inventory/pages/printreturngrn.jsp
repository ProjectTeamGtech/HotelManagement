<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />

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
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 12px !important;
    border-right: none !important;
    border-left: none !important;
}
</style>

<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>


<SCRIPT type="text/javascript">

     window.onload= function(){
     
                $("#expiry").datepicker({

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
			    $("#security_date").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

			   });
			   
			   //setState();
			   
     }

</SCRIPT>


</head>
<body>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>


<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="letterHead">
				<h4><s:property value="clinicName"/></h4>
				<h5><s:property value="clinicaddress"/></h5><h5>Website:<s:property value="websiteUrl"/>, Email:<s:property value="email"/>, Contact :<s:property value="landLine"/> </h5>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
				<h3 class="text-uppercase"><b>Product Return
					<s:if test="status==1">
						(Debit Note)
					</s:if>
					<s:else>
						(Credit Note)
					</s:else>
				</b></h3>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;" id="billanddata">
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset"><b>Return ID :</b>&nbsp;<span><%=request.getParameter("grnreturnid") %></span></p>
					<p class="marboset"><b>Return Date :</b>&nbsp;<span><s:property value="date"/></span></p>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<p class="marboset"><b>Supplier Name :</b>&nbsp;<span><s:property value="vendor"/></span></p>
					<p class="marboset"><b>Contact :</b>&nbsp;<span><s:property value="mobile"/></span></p>
					<p class="marboset"><b>Security Out No/Date :</b>&nbsp;<span><s:property value="security_no"/>/<s:property value="security_date"/></span></p>
				</div>
			</div>
	</div>
</div>

<div class="row">
	<s:form theme="simple" action="Procurement" id="formMyPo">	
		<s:hidden name="procurementid" id="procurementid"></s:hidden>
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
										<table class="table my-table xlstable table-bordered" style="width: 100%;" id='prodTable'>
				                            <thead>
				                                <tr>
				                                    <td style="width: 2%;">Sr</td>
				                                    <td style="width: 15%;">Product</td>
				                                    <td style="width: 4%;">HSN No
				                                    </td><td style="width: 4%;">Mfg</td>
				                                    <td style="width: 8%;">Batch No</td>
				                                    <td style="width: 8%;">ExpDate</td>
				                                    <td style="width: 5%;">Pack</td>
				                                    <th style="width: 6%;">Grn. No</th>
				                                    <td style="width: 9%;">Invoice No</td>
				                                     <td style="width: 2%;">GST</td>
				                                    <td style="width: 5%;">MRP</td>
				                                    <td style="width: 7%;">Rate</td>
				                                   <!--  <td style="width: 7%;">Stock</td> -->
				                                    <td style="width: 3%;">Req. Qty</td>
				                                    <td style="width: 3%;">Ret. Qty</td>
				                                    <td style="width: 3%;">Ret. Free Qty</td>
				                                    <td style="text-align: right;width: 7%;">Amount</td>
				                                    <td style="text-align: right;width: 7%;">Disc. Amt</td>
				                                    <td style="text-align: right;width: 8%;">GST Amt</td>
				                                    <td style="text-align: right;width: 15%;">Total</td>
				                                 </tr>
				                            </thead>
				                             <tfoot>
				                            <tr style="background-color: rgba(239, 239, 239, 0.46);color: green;">
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<!-- <td></td> -->
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td><b>Subtotal</b></td>
				                            	<td class="text-right"><b><s:property value="totalDebit"/></b></td>
				                            	<td></td>
				                            	<td></td>
				                            	<td></td>
				                            	</tr>
				                            </tfoot>
				                            <tbody id="receiveddata">
				                              <% int i=0; %>
				                               <s:iterator value="grnreturnProductList">
				                                <tr>
				                                	 <td><%=++i %>
				                                	 	<input type="hidden" name="vat<s:property value="id"/>" value="<s:property value="vat"/>">
				                                	 	<input type="hidden" name="discper<s:property value="id"/>" value="<s:property value="discper"/>">
				                                	 	<input type="hidden" name="discount<s:property value="id"/>" value="<s:property value="discount"/>">
				                                	 	<input type="hidden" name="gstamount<s:property value="id"/>" value="<s:property value="gstamount"/>">
				                                	 	<input type="hidden" name="newtotal<s:property value="id"/>" value="<s:property value="total"/>">
				                                	 	<input type="hidden" name="voucherno<s:property value="id"/>" value="<s:property value="voucherno"/>">
				                                	 </td>
				                                     <td><s:property value="product_name"/></td>
				                                     <td><s:property value="hsnno"/></td>
				                                     <td><s:property value="mfg"/></td>
				                                     <td><s:property value="batch_no"/></td>
				                                     <td><s:property value="expiry_date"/></td>
				                                     <td><s:property value="pack"/></td>
				                                     <td><s:property value="proSeqNo"/></td>
				                                     <td><s:property value="voucherno"/></td>
				                                     <td><s:property value="vat"/>%</td>
				                                     <td><s:property value="mrp"/></td>
				                                     <td><s:property value="purchase_price"/></td>
				                                     <%-- <td><s:property value="stock"/></td>  --%>
				                                     <td><s:property value="qty"/></td>
				                                     <td><s:property value="returnqty"/></td>
				                                      <td><s:property value="returnfreeqty"/></td>
				                                     <td class="text-right"><s:property value="total"/></td>
				                                     <td class="text-right"><s:property value="discount"/></td>
				                                     <td class="text-right"><s:property value="gstamount"/></td>
				                                     <td class="text-right"><s:property value="netammt"/></td>
				                                </tr>
				                               </s:iterator>  
				                                  
				                            </tbody>
				                              <input type="hidden" id="tempcount" value="<%=i%>" />
				                        </table>
									</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-top:15px;">
				<div class="col-lg-6 col-xs-6 col-sm-6 col-md-6" style="text-align: right;padding: 0px;">
					 <table class="table my-table xlstable table-bordered" style="width: 100%;margin-top: 10px;float: right;border: 1px solid #ddd;text-align:right;">
                                <thead>
                                    <tr>
                                        <td style="background-color: brown;width: 12%;">GST%</td>
                                        <td style="background-color: brown;width: 20%;text-align:right;">Total Amt.</td>
                                        <td style="background-color: brown;width: 22%;text-align:right;">Taxable Amt.</td>
                                        <td style="background-color: brown;width: 19%;text-align:right;">Dis. Amt.</td>
                                        <td style="background-color: brown;text-align:right;width: 18%;">Tax Amt.</td>
                                    </tr>
                                </thead>
                                <%i=0; %>
                                <tbody id="tTaxData">
                                   <s:iterator value="vatAllocationList">
                                      <tr>
                                      	   <td class="text-left"><span id="vatrate<%=i%>"><s:property value="vatrate"/></span>%</td> <input type="hidden" id="vatrateAmt<%=i%>" name="vatallocations[<%=i%>].vatrate" value="<s:property value="vatrate"/>" />
	                                       <td id="totalVatAmt<%=i%>" > <s:property value="totalamt"/> </td> <input type='hidden' value="<s:property value="totalamt"/>" name="vatallocations[<%=i%>].totalamt" />
	                                       <td><s:property value="taxable"/><input type="hidden" id="vatgiven<%=i%>" class="form-control" name="vatallocations[<%=i%>].taxable" value="<s:property value="taxable"/>" /></td>
	                                       <td id="discvat<%=i%>" ><s:property value="discvat"/></td> <input type="hidden" id="discvatVal<%=i%>" value="<s:property value="discvat"/>"  name="vatallocations[<%=i%>].discvat" />
	                                       <td id="taxAmt<%=i %>"> <s:property value="taxamt"/> </td><input type="hidden" id="taxAmtVal<%=i %>" value="<s:property value="taxamt"/>" name="vatallocations[<%=i %>].taxamt" /> 
	                                   </tr>
                                   	  <%i++; %>
                                   </s:iterator>
                                   
                                    <input type="hidden" id="vatcount" value="<%=i%>" />
                                   
                               </tbody>
                            </table>
					 <div class="form-group text-left">
						<label for="comment">Remark: <s:property value="notes"/> </label>
					</div>
				</div>
				<div class="col-lg-6 col-xs-6 col-sm-6 col-md-6 text-right" style="text-align: right;padding: 0px;">
					 <table style="width:100%;	">
                   <tbody>
                    <tr>
                    	<s:hidden name="alltotvatTotal"></s:hidden>
                     <td style="text-align: right;width:70%;">SUBTOTAL :&nbsp; </td>
                     <td>Rs.<s:property value="totalDebit"/></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;width:70%;">Discount :&nbsp; </td>
                     <td>Rs.<s:property value="sale_discount"/></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;width:70%;">CGST :&nbsp; </td>
                     <td>Rs.<s:property value="cgst"/></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;width:70%;">SGST :&nbsp; </td>
                     <td>Rs.<s:property value="sgst"/></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;width:70%;">IGST :&nbsp; </td>
                     <td>Rs.<s:property value="igst"/></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;color:green;"><label>NET PAYBLE AMT :&nbsp; </label></td>
                     <td>Rs.<s:property value="total_amt"/>
                       <input type="hidden" id="nettemp" />  
                     </td>
                    </tr>
                    <s:if test="status==2">
						<tr>
                     		<td style="text-align: right;color:green;"><label>APPROVED AMT :&nbsp; </label></td>
                     		<td>Rs.<s:property value="aprovedamt"/>
                       			
                     		</td>
                    	</tr>
					</s:if>
                    <tr>
                    <%--  <td>
                      <select name="paymode" id="paymode" class="form-control">
				          <option value="Credit">CREDIT</option>
				          <option value="Cash">CASH</option>
				         </select>
                     </td> --%>
                   </tbody>
                  </table>
					<%--  <div class="form-group">
					 	<label>SUBTOTAL : <s:property value="totalDebit"/></label><br>
					 	<label style="color: red">Discount : <s:property value="sale_discount"/></label><br>
					 	<label>CGST : <s:property value="cgst"/></label><br>
					 	<label>SGST : <s:property value="sgst"/></label><br>
					 	<label>IGST : <s:property value="igst"/></label><br>
						<label for="comment">NET PAYBLE AMT: <s:property value="total_amt"/><s:property value="total_amt"/><input type="hidden" id="nettemp" /></label>
					</div> --%>
					<br>
					<p style="margin-bottom:0px;">Manager/Officer</p>
					<p style="margin-bottom:0px;" class="hidden">Requested by : <s:property value="check_avail_userid"/></p>
					<p style="margin-bottom:0px;">Printed by : <s:property value="printedby"/></p>
					<a type="button" class="btn btn-primary btn-lg savebigbtn hidden-print"  title="Print" onclick="printpage()">Print</a>
				</div>
                 </div>

 	</s:form>
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
    
    
    function printThis(){
    	
    	window.print();
    }
    
  </script>

</body>
</html>