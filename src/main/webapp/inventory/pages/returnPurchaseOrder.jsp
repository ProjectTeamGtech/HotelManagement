<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />

<style>
.topheadbaxck {
    background-color: rgb(239, 239, 239);
    padding: 8px 0px;
}
.table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    font-size: 14px;
}
</style>

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
		
				                                setTindex();
		  										calTotalAmt();
		  										
		  										
		  										
     }
     
     function submitPoReturn() {
     
  			document.getElementById("formMyPo").submit()     
     }
     
      

</SCRIPT>


</head>
<body>
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									
										<h4>Goods Return</h4>

									</div>
								</div>
								<div class="">
								<s:form theme="simple" action="returnstockProcurement" id="formMyPo">	
								  <s:hidden name="id"></s:hidden>
									<div class="col-lg-12 col-md-12 col-xs-12 topheadbaxck">
										<div class="col-lg-3 col-md-3 col-sm-3">
											<div class="form-group">
												<label>Select Supplier</label>
												<s:select list="vendorList"  id="vendorid" name="vendor_id" listKey="id" onchange="callPopup(this.value)" listValue="name" cssClass="form-control chosen-select" theme="simple" headerKey="0" headerValue="Select Supplier" />
												    
											</div>	
										</div>
										<div class="col-lg-3 col-md-3 col-sm-3 hidden">
											<div class="form-group" id="prodListId" >
												<label>Select Product</label>
												<select name="" class="form-control chosen-select">
												    <option value="0">Select Product</option>
												</select>
											</div>
											
										</div>
										<div class="col-lg-1 col-md-1 col-sm-2 hidden">
											<div class="form-group">
												<a href="#" type="button" class="btn btn-primary" onclick="addNewProd()" style="margin-top: 22px;">New Product</a>
											</div>
										</div>
										<div class="col-lg-1 col-md-1 col-sm-2 hidden">
											<div class="form-group">
												<label>Product Type</label>
												<SELECT class="form-control" name="medicine_type" id="medicine_type">
												 <option value="Tablet">TAB</option>
												 <option value="AMP">AMP</option>
												 <option value="Tablet">CAP</option>
												 <option value="Tablet">KIT</option>
												 <option value="BTL">BTL</option>
												 <option value="ML">ML</option>
												 <option value="NOS">NOS</option>
												 <option value="PACK">PACK</option>
												 <option value="PIECE">PIECE</option>
												 <option value="SACH">SACH</option>
												 <option value="Tablet">STRIP</option>
												 <option value="TUBE">TUBE</option>
												 <option value="VIAL">VIAL</option>
												</SELECT>
												
											</div>
										</div>
										<div class="col-lg-1 col-md-1 col-sm-2 hidden">
											<div class="form-group">
												<label>Pack</label>
												<input type="text" class="form-control" id="pack" required="required">
											</div>
										</div>
										<div class="col-lg-2 col-md-2 col-sm-2 hidden">
											<div class="form-group">
												<label>Product Type</label>
												<s:select list="#{'0':'Regular','1':'Narcotics','2':'H1'}" cssClass="form-control" name="medicine_shedule" id="medicine_shedule"  ></s:select>
											</div>
										</div>
										<div class="col-lg-1 col-md-1 col-sm-2 hidden">
											<a href="#" class="btn btn-warning" onclick="addProductForProcurement('prodTable')" style="margin-top: 22px;">Add</a>
										</div>
									</div>
									<input type="hidden" value="0" id="repeat" />
									<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
										<b style="color: brown;"> <span id="vendorName"> <s:property value="vendor"/> </span> &nbsp; | &nbsp;<small style="color:green;">Return Date : <s:property value="date"/></small> &nbsp;  &nbsp; <s:textfield  placeholder="Enter Voucher No" onkeyup="chkvoucher(this.value)" cssClass="form-control hidden"  id="voucherno" required="required" name="voucherno" cssStyle="width: 10%;background-color: cornsilk;" /> <label id="errvoucher" style="color:red" ></label> <s:textfield  cssClass="form-control hidden" id="voucherdate" name="voucherdate" placeholder="Voucher Date" cssStyle="width: 10%;background-color: cornsilk;" /> </b>
										<table class="table my-table xlstable table-bordered" style="width: 100%;margin-top: 10px;margin-bottom:0px;" id='prodTable'>
				                            <thead>
				                                <tr>
				                                    <th>Sr</th>
				                                    <th style="width: 35%;">Product</th>
				                                    <th style="width: 4%;">Pack</th>
				                                    <th style="width: 7%;">Voucher No</th>
				                                    <th style="width: 7%;">Batch No</th>
				                                    <th style="width: 7%;">Expiry Date</th>
				                                    <th style="width: 6%;">HSN No</th>
				                                    <th style="width: 7%;">Mfg</th>
				                                    <th style="width: 7%;" class="">MRP</th>
				                                    <th style="width: 7%;">Sale <small>(per unit)</small></th>
				                                    <th style="width: 5%;">GST (%)</th>
				                                    <th style="width: 5%;">Rate </th>
				                                     <th style="width: 4%;">Dis</th>
				                                    <th style="width: 5%;">Qty</th>
				                                    
				                                    <th style="width: 8%;text-align:right;">Amount</th>
				                                    <!--<th>Edit</th>
				                                    <th>Delete</th>
				                                --></tr>
				                            </thead>
				                            <tbody id="receiveddata">
				                               <tr></tr>
				                                  <%int i=0; %>
				                                  <s:iterator value="productList">
				                            	  <tr>
				                            	     
				                            		<td><%=(i+1) %>
				                            		 <input type="hidden" value="<%=i %>" class="dclass" />
				                            		 <input type="hidden" value="<s:property value="id"/>" name="procurements[<%=i%>].id" /> </td>
				                            		<td><s:property value="product_name"/> <input type="hidden" value="<s:property value="product_id"/>" name="procurements[<%=i%>].product_id" /> </td>
				                            		<td><s:property value="pack"/> <input type="hidden" id="pack<%=i%>" value="<s:property value="pack"/>" name="procurements[<%=i%>].pack" /> </td>
				                            		<td><s:property value="voucherno"/>  <s:select theme="simple" list="#{'0':'Regular','1':'Narcotics','2':'H1'}" cssClass="form-control hidden" name="procurements[<%=i%>].medicine_shedule" ></s:select></td>
				                            		<td><s:property value="batch_no"/> <input type="hidden"  value="<s:property value="batch_no"/>" class="form-control" required="required" name="procurements[<%=i%>].batch_no" /> </td>
				                            		<td><s:property value="expiry_date"/> <input type="hidden" value="<s:property value="expiry_date"/>" class="form-control" name="procurements[<%=i%>].expiry_date" id="expiry_date<%=i%>" required="required" placeholder="MM/YYYY" /></td>
				                            		<td><s:property value="hsnno"/></td>
				                            		<td><s:property value="mfg"/><input type="hidden" value="<s:property value="mfg"/>" class="form-control" name="procurements[<%=i%>].mfg" required="required"></td>
				                            		<td class=""><s:property value="mrp"/><input type="hidden" value="<s:property value="mrp"/>" onkeyup="calSalPer()" class="form-control" id="mrp<%=i%>" name="procurements[<%=i%>].mrp" required="required"></td>
				                            		
				                            		<td> <s:property value="sale_price"/><input type="hidden" class="form-control" value="<s:property value="sale_price"/>" id="sale_price<%=i%>" name="procurements[<%=i%>].sale_price" required="required"></td>
				                            		<td><s:property value="vat"/><input type="hidden" value="<s:property value="vat"/>" onkeyup="calVatTotal()" id="vat<%=i%>" class="form-control" name="procurements[<%=i%>].vat" required="required"></td>
				                            		<td><s:property value="purchase_price"/><input type="hidden" value="<s:property value="purchase_price"/>" onkeyup="calTotalAmt()" class="form-control"  id="purchase_price<%=i%>" name="procurements[<%=i%>].purchase_price" required="required"></td>
				                            		<td><s:property value="discount"/><input type="hidden" value="<s:property value="discount"/>" onkeyup="calTotalAmt()" class="form-control"  id="discount<%=i%>" name="procurements[<%=i%>].discount" required="required"></td>
				                            		
				                            		<td><input type="text" class="form-control" value="<s:property value="qty"/>" onkeyup='calTotalAmt()' id="received_qty<%=i%>" name="procurements[<%=i%>].received_qty" required="required"></td>
				                            		<td style="text-align:right;">Rs.<span id="amount<%=i%>"></span></td>
				                            	  </tr>
				                            	  
				                            	  <%i++; %>
				                            	</s:iterator>
				                            	<input type="hidden" value="<%=(i-1)%>" id="tindex" />
				                            	 <input type="hidden" id="tempcount" value="<%=(i-1)%>" />
				                            	
				                            </tbody>
				                        </table>
				                        
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
									
									<div class="col-lg-12 col-md-12 col-xs-12" style="text-align: right;padding: 0px;">
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
                                    </tr>
                                </thead>
                                <tbody id="tTaxData">
                                 
                               </tbody>
                            </table>
                 <div class="form-group">
										  <label for="comment" style="text-align:left;">Remark:</label>
										  <a class="hidden" href="#" data-toggle="modal" data-target="#viewdetailspro">view</a>
										  <textarea class="form-control" rows="5" id="remark" name="remark" style="background-color: rgba(95, 95, 95, 0.04);"></textarea>
										</div>
                 </div>
                 <div class="col-lg-2 col-md-2" style="text-align: right;">
                  <h4>Subtotal :</h4>
                  <h4 style="color: red;">Discount :</h4>
                  <h4>CGST :</h4>
                  <h4>SGST :</h4>
                  <h4>IGST :</h4>
                  <h4>Surcharge :</h4>
                  <h4 style="color:green;"><b>NET Payble Amount :</b></h4>
                 </div>
                  <div class="col-lg-1 col-md-1" style="text-align: right;">
                  <h4> Rs. <span id="subTotal" >00.00</span> </h4>
                  <h4 style="display: inline-flex;margin-top: 0px;margin-bottom: 5px;color: red;"> Rs. <input type="number" class="form-control" name="discount" value="0" id="discount" style="height: 22px;background-color: rgba(220, 220, 220, 0.28);color: red;font-size: 15px;margin-top: -3px;"/> </h4>
                  <h4 style="margin-top: 2px;"> Rs.<span id="tcgst" >00.00</span> <input type="hidden" name="vat" id="vat" /> <input type="hidden" name="cgst" id="cgst" /></h4>
                  <h4> Rs.<span id="tsgst" >00.00</span><input type="hidden" name="sgst" id="sgst" /></h4> <input type="hidden" id="credit" /> <input type="hidden" id="debit" /> <input type="hidden" id="subcharge" />
                  <h4> Rs.<span id="tigst" >00.00</span><input type="hidden" name="igst" id="igst" /></h4>
                  <h4 style="display: inline-flex;margin-top: 0px;margin-bottom: 5px;">Rs.<span> <input type="text"  class="form-control" onkeyup="calVatDiscount()" value="0.00" name="surcharge" id="subcharge" style="height: 22px;font-size: 15px;margin-top: -3px;"></span></h4>
                  <h4 style="color:green;margin: 0px;"><b> Rs.<span id="netpay" >00.00</span></b></h4>
                 </div>
          <br>
          </s:form>
          <a href="#" onclick="submitPoReturn()" class="btn btn-primary" style="margin-top:15px;font-size:16px;">Goods Return</a>
         </div>
									
		
								
								<form action="returntosupplierProcurement" id="rtform" method="post" >
					 
					   <input type="hidden" name="alldata" id="all" /> 
					</form>			
					
								
								
								
								
								
								
								
								
								
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
								
								
								
					
								
								
	 <!-- Return Product Modal -->
<div id="returnpro" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Supplier Name</h4>
      </div>
      <div class="modal-body">
         <div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
         
    <table class="table">
        <thead>
          <tr>
            <th>Voucher No</th>
            <th>Voucher Date</th>
            <th class="text-right">Payble Amount</th>
          </tr>
        </thead>
        <tbody id="alldata">
          
         </tbody>
          </table>
   </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="allreturnprod()" style="margin-top: 15px;">Add</button>
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