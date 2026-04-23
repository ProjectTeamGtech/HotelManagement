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
			   
			   setState();
			   
     }

</SCRIPT>


</head>
<body>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									
										<h4>Goods Received</h4>

									</div>
								</div>
								<div class="">
								<s:form theme="simple" action="updateprocurementProcurement" id="formMyPo">	
									<div class="col-lg-12 col-md-12 col-xs-12 topheadbaxck">
									<div class="col-lg-2 col-md-2 col-sm-2 hidden">
											<div class="form-group">
												<label>Select State</label>
												<select name="" class="form-control chosen-select">
												    <option value="0">Interstate</option>
												    <option value="0">Intrastate</option>
												</select>
											</div>
											
										</div>
									 <%if(loginfo.getUserType()==2) {%>
									    <div class="col-lg-2 col-md-2 col-sm-2">
											<div class="form-group">
												<label>Select Location</label>
												<s:select list="locationListPharmacy" theme="simple" id="location" name="location" cssStyle="width:30%" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
										     	</s:select> 
												    
											</div>	
										</div>
									<%} else { %>	
									      <div class="form-group hidden">
												<label>Select Location</label>
												<s:select list="locationListPharmacy" theme="simple" id="location" name="location" cssStyle="width:30%" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
										     	</s:select> 
												    
											</div>	
									 <%} %>
										<div class="col-lg-2 col-md-2 col-sm-2">
											<div class="form-group">
												<label>Select Supplier</label>
												<s:select list="vendorList"  id="vendorid" name="vendor" listKey="id" onchange="getvendorProduct(this.value)" listValue="name" cssClass="form-control chosen-select" theme="simple" headerKey="0" headerValue="Select Supplier" />
											</div>	
										</div>
										
										<div class="col-lg-2 col-md-2 col-sm-2">
											<div class="form-group" id="prodListId" >
												<label>Select Product</label>
												<select name="" class="form-control chosen-select">
												    <option value="0">Select Product</option>
												</select>
											</div>
											
										</div>
										<div class="col-lg-1 col-md-1 col-sm-2">
											<div class="form-group">
												<a href="#" type="button" class="btn btn-primary" onclick="addNewProd()" style="margin-top: 22px;">New Product</a>
											</div>
										</div>
										<div class="col-lg-1 col-md-1 col-sm-2">
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
										<div class="col-lg-1 col-md-1 col-sm-2">
											<div class="form-group">
												<label>Pack</label>
												<input type="text" class="form-control" id="pack" required="required">
											</div>
										</div>
										<div class="col-lg-2 col-md-2 col-sm-2">
											<div class="form-group">
												<label>Product Type</label>
												<s:select list="#{'0':'Regular','1':'Narcotics','2':'H1'}" cssClass="form-control" name="medicine_shedule" id="medicine_shedule"  ></s:select>
											</div>
										</div>
										<div class="col-lg-1 col-md-1 col-sm-2">
											<a href="#" class="btn btn-warning" onclick="addProductForProcurement('prodTable')" style="margin-top: 22px;">Add</a>
										</div>
									</div>
									<input type="hidden" value="0" id="repeat" />
									<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
										<b style="color: brown;"> <span id="vendorName">  </span> &nbsp; | &nbsp;<small style="color:green;">Purchase Date : </small> &nbsp; | &nbsp; <input type="text"  placeholder="Enter Voucher No" onkeyup="chkvoucher(this.value)" class="form-control"  id="voucherno" required="required" name="voucherno" style="width: 10%;background-color: cornsilk;" /> <label id="errvoucher" style="color:red" ></label> &nbsp; | &nbsp; <input type="text" class="form-control" id="voucherdate" name="voucherdate" placeholder="Voucher Date" style="width: 10%;background-color: cornsilk;" /> </b>
										<table class="table my-table xlstable table-bordered" style="width: 100%;margin-top: 10px;margin-bottom:0px;" id='prodTable'>
				                            <thead>
				                                <tr>
				                                    <th>Sr</th>
				                                    <th style="width: 25%;">Product</th>
				                                    <th>Pack</th>
				                                    <th>Type</th>
				                                    <th>Batch No</th>
				                                    <th style="width: 9%;">Expiry Date</th>
				                                    <th>HSN No</th>
				                                    <th>Mfg</th>
				                                    <th class="">MRP</th>
				                                    <th style="width: 6%;">Sale <small>(per unit)</small></th>
				                                    <th>GST</th>
				                                    <th>Rate</th>
				                                    <th>Rec.Qty</th>
				                                     <th>Free Qty</th>
				                                    <th style="width:6%;">Shelf No</th>
				                                    <th></th>
				                                    <th>Amount</th>
				                                    <th></th>
				                                    <!--<th>Edit</th>
				                                    <th>Delete</th>
				                                --></tr>
				                            </thead>
				                            <tbody id="receiveddata">
				                               <tr></tr>
				                                  
				                                  
				                            </tbody>
				                              <input type="hidden" id="tempcount" value="0" />
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
          <div class="col-lg-9 col-md-9">
                  <table class="table my-table xlstable table-bordered" style="width: 40%;margin-top: 10px;float: right;border: 1px solid #ddd;">
                                <thead>
                                    <tr>
                                        <th style="background-color: brown;width: 12%;">GST</th>
                                        <th style="background-color: brown;width: 20%;text-align:right;">Total Amt.</th>
                                        <th style="background-color: brown;width: 22%;text-align:right;">Taxable Amt.</th>
                                        <th style="background-color: brown;width: 19%;text-align:right;">Dis. Amt.</th>
                                        <th style="background-color: brown;text-align:right;width: 18%;">Tax Amt.</th>
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
                            
                            
                 </div>
                 <div class="col-lg-2 col-md-2">
                  <h4>Subtotal :</h4>
                  <h4>Discount :</h4>
                  <h4>CGST :</h4>
                  <h4>SGST :</h4>
                  <h4>IGST :</h4>
                  <h4>Surcharge :</h4>
                  <h4>Credit :</h4>
                  <h4>Debit :</h4>
                  <h4 style="color:green;"><b>NET Payble Amount :</b></h4>
                 </div>
                 
                 
                <div class="col-lg-1 col-md-1">
                  <h4> Rs. <span id="subTotal" >00.00</span> </h4>
                  <h4 style="display: inline-flex;margin-top: 0px;margin-bottom: 5px;color: red;"> Rs. <input type="number" class="form-control" name="discount" value="0" onchange="calDisc()" id="discount" style="height: 22px;background-color: rgba(220, 220, 220, 0.28);color: red;font-size: 15px;margin-top: -3px;"/> </h4>
                  <h4 style="margin-top: 2px;"> Rs.<span id="tcgst" >00.00</span> <input type="hidden" name="vat" id="vat" /> <input type="hidden" name="cgst" id="cgst" /></h4>
                  <h4> Rs.<span id="tsgst" >00.00</span><input type="hidden" name="sgst" id="sgst" /></h4>
                  <h4> Rs.<span id="tigst" >00.00</span><input type="hidden" name="igst" id="igst" /></h4>
                  <h4 style="display: inline-flex;margin-top: 0px;margin-bottom: 5px;">Rs.<span> <input type="text"  class="form-control" onkeyup="calVatDiscount()" value="0.00" name="surcharge" id="subcharge" style="height: 22px;font-size: 15px;margin-top: -3px;"></span></h4>
                  <h4 style="display: inline-flex;margin-top: 0px;margin-bottom: 5px;">Rs.<span> <input type="text" class="form-control" onkeyup="calVatDiscount()" id="credit" name="credit" style="height: 22px;font-size: 15px;margin-top: -3px;"></span></h4>
                  <h4 style="display: inline-flex;margin-top: 0px;margin-bottom: 5px;">Rs.<span> <input type="text" class="form-control" onkeyup="calVatDiscount()" id="debit" name="debit" style="height: 22px;font-size: 15px;margin-top: -3px;"></span></h4>
                  <h4 style="color:green;margin: 0px;"><b> Rs.<span id="netpay" >00.00</span></b></h4>
                 </div>
          <br>
          </s:form>
          <a href="#" onclick="savePoReceived()" class="btn btn-primary" style="margin-top:15px;font-size:16px;">Goods Received</a>
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