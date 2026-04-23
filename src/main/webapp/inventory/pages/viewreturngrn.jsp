<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
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

<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>


<SCRIPT type="text/javascript">

     window.onload= function(){
    	 		document.addEventListener("contextmenu", function(e){
    				e.preventDefault();
    			}, false); 
     
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

								<div class="">
								
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
								
								<s:form theme="simple" action="updatereturnproductProduct" id="formMyPo">	
								<s:hidden name="procurementid" id="procurementid"></s:hidden>
								<s:hidden name="grnreturnid" id="grnreturnid"></s:hidden>
								
								
								<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                     
                        <div class="col-md-12" style="padding: 0px;">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                        	<div class="form-inline">
                         <div class="form-group">
	                           		<span class="text-uppercase"><b><span id="vendor"> <s:property value="vendor"/>   </span></b> &nbsp; - &nbsp;</span>
	                           </div>
                        <div class="form-group">
                        	<span class="text-uppercase" style="color: #d9534f;"><b>Product Return No</b>:<%=request.getParameter("grnreturnid") %></span> &nbsp; | &nbsp;
                        </div>
                        <div class="form-group" style="width: 7%;">
                        	<s:textfield  cssClass="form-control" id="" name="date" readonly="true" cssStyle="width: 100%;" />
                        </div>
                        <div class="form-group" style="width: 10%;">
                        	<s:textfield  placeholder="Security Outward No" cssClass="form-control"  id="security_no" required="required" name="security_no" cssStyle="width: 100%;" />
                        </div>
                        <div class="form-group" style="width: 7%;">
                        	<input type="text"  placeholder="Outward Date" readonly="readonly" class="form-control"  id="security_date" required="required" name="security_date" style="width: 100%;" />
                        </div>
                        
						</div>
                        </div>
                        
                        </div>
                        
                    </div> 
								
								
								
								
								
								
									
									<div class="col-lg-12 col-md-12 col-sm-12">
										
										<table class="table my-table xlstable table-bordered" style="width: 100%;    text-transform: uppercase;margin-top: 10px;margin-bottom:0px;" id='prodTable'>
				                            <thead>
				                                <tr>
				                                    <th style="width: 3%;">Sr</th>
				                                    <th style="width: 10%;">Product</th>
				                                    <td style="width: 5%;">HSN No</td>
				                                    <th style="width: 5%;">Mfg</th>
				                                    <th style="width: 6%;">Batch No</th>
				                                    <th style="width: 6%;">Exp. Date</th>
				                                    <td style="width: 5%;">Pack</td>
				                                    <th style="width: 6%;">Grn. No</th>
				                                    <th style="width: 7%;">Invoice No</th>
				                                    <th style="width: 3%;">GST</th>
				                                    <th style="width: 4%;">MRP</th>
				                                    <th style="width: 5%;">Rate</th>
				                                    <th style="width: 5%;">Stock</th> 
				                                    <th style="width: 5%;">Req. Qty</th>
				                                    <th style="width: 5%;">Free. Qty</th>
				                                     <th style="width: 5%;">Bal. Free Qty</th>
				                                    <th style="width: 5%;">Ret. Qty</th>
				                                    <th style="width: 5%;">Free. Ret. Qty</th>
				                                    <th style="text-align: right;">Amount</th>
				                                    <th style="text-align: right;">Disc. Amt</th>
				                                    <th style="text-align: right;">GST Amt</th>
				                                    <th style="text-align: right;">Total</th>
				                                 </tr>
				                            </thead>
				                            <tbody id="receiveddata">
				                              <% int i=0; %>
				                               <s:iterator value="grnreturnProductList">
				                                <tr>
				                                	 <td><%=++i %>
				                                	 	<input type="hidden" value="<s:property value="id"/>" class="akashreturnclass" />
				                                	 	<input type="hidden" name="vat<s:property value="id"/>" id="vat<s:property value="id"/>" value="<s:property value="vat"/>">
				                                	 	<input type="hidden" name="discper<s:property value="id"/>" id="discper<s:property value="id"/>" value="<s:property value="discper"/>">
				                                	 	<input type="hidden" name="discount<s:property value="id"/>" id="discount<s:property value="id"/>" value="<s:property value="discount"/>">
				                                	 	<input type="hidden" name="gstamount<s:property value="id"/>" id="gstamount<s:property value="id"/>" value="<s:property value="gstamount"/>">
				                                	 	<input type="hidden" name="newtotal<s:property value="id"/>" id="newtotal<s:property value="id"/>" value="<s:property value="total"/>">
				                                	 	<input type="hidden" name="voucherno<s:property value="id"/>" id="voucherno<s:property value="id"/>" value="<s:property value="voucherno"/>">
				                                	 	<input type="hidden" name="purchase_price<s:property value="id"/>" id="purchase_price<s:property value="id"/>" value="<s:property value="purchase_price"/>">
				                                	 	<input type="hidden" name="pack<s:property value="id"/>" id="pack<s:property value="id"/>" value="<s:property value="pack"/>">
				                                	 	<input type="hidden" name="qty<s:property value="id"/>" id="qty<s:property value="id"/>" value="<s:property value="qty"/>">
				                                	 	<input type="hidden" name="remainfreereturnqty<s:property value="id"/>" id="remainfreereturnqty<s:property value="id"/>" value="<s:property value="remainfreereturnqty"/>">
				                                	 	<input type="hidden" name="proc_childid<s:property value="id"/>" id="proc_childid<s:property value="id"/>" value="<s:property value="proc_childid"/>">
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
				                                    <td><s:property value="stock"/></td>
				                                     <td><s:property value="qty"/></td>
				                                     <td><s:property value="freeqty"/></td>
				                                     <td><s:property value="remainfreereturnqty"/></td>
				                                     <td><input type="number" class="form-control" onchange="calReturnSupplierSubTotal()" name="returnqty<s:property value="id"/>" id="returnqty<s:property value="id"/>" value="<s:property value="returnqty"/>"></td>
				                                     <td><input type="number" class="form-control" onchange="calReturnSupplierSubTotal()" name="returnfreeqty<s:property value="id"/>" id="returnfreeqty<s:property value="id"/>" value="<s:property value="returnfreeqty"/>"></td>
				                                     <td class="text-right" id="returntotal<s:property value="id"/>"><s:property value="total"/></td>
				                                     <td class="text-right" id="returndiscount<s:property value="id"/>"><s:property value="discount"/></td>
				                                     <td class="text-right" id="returngstamount<s:property value="id"/>"><s:property value="gstamount"/></td>
				                                     <td class="text-right" id="returnnetammt<s:property value="id"/>"><s:property value="netammt"/></td>
				                                </tr>
				                               </s:iterator>  
				                                  
				                            </tbody>
				                              <input type="hidden" id="tempcount" value="<%=i%>" />
				                        </table>
				                        
									</div>
									
									<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 col-xs-4">
										
									</div>
          <div class="col-lg-5 col-md-5">
                  <table class="table my-table xlstable table-bordered" style="width: 100%;margin-top: 10px;float: right;border: 1px solid #ddd;text-align:right;">
                                <thead>
                                    <tr>
                                        <th style="background-color: brown;width: 12%;">GST%</th>
                                        <th style="background-color: brown;width: 20%;text-align:right;">Total Amt.</th>
                                        <th style="background-color: brown;width: 22%;text-align:right;">Taxable Amt.</th>
                                        <th style="background-color: brown;width: 19%;text-align:right;">Dis. Amt.</th>
                                        <th style="background-color: brown;text-align:right;width: 18%;">Tax Amt.</th>
                                    </tr>
                                </thead>
                                <%i=0; %>
                                <tbody id="tTaxData">
                                   <s:iterator value="vatAllocationList">
                                      <tr>
                                      	   <td class="text-left"><span id="vatrate<%=i%>"><s:property value="vatrate"/></span>%</td> <input type="hidden" id="vatrateAmt<%=i%>" name="vatallocations[<%=i%>].vatrate" value="<s:property value="vatrate"/>" />
	                                       <td id="totalVatAmt<%=i%>" > <s:property value="totalamt"/> </td> <input type='hidden' id="totalVatAmtinput<%=i%>" value="<s:property value="totalamt"/>" name="vatallocations[<%=i%>].totalamt" />
	                                       <td id="vatgivenspan<%=i%>"><s:property value="taxable"/></td><input type="hidden" id="vatgiven<%=i%>" class="form-control" name="vatallocations[<%=i%>].taxable" value="<s:property value="taxable"/>" />
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
										  <textarea class="form-control" rows="5" id="remark" name="remark" style="background-color: rgba(95, 95, 95, 0.04);"></textarea>
										</div>
                            
                 </div>
                 
                 
                 <div class="col-lg-3 col-xs-3 col-md-3" style="padding:0px;">
                  <table style="width:100%;	">
                   <tbody>
                    <tr>
                    	<s:hidden name="isfromsamestate" id="isfromsamestate"></s:hidden>
                    	<s:hidden name="alltotvatTotal" id="alltotvatTotal"></s:hidden>
                     <td style="text-align: right;width:70%;">SUBTOTAL :&nbsp; </td>
                     <td><s:textfield name="total_amount" readonly="true" id="subTotal" cssClass="form-control" cssStyle="margin-bottom: 5px;text-align:right;" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;width:70%;">Discount :&nbsp; </td>
                     <td><s:textfield name="sale_discount" readonly="true" id="discount" cssClass="form-control" cssStyle="margin-bottom: 5px;text-align:right;" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;width:70%;">CGST :&nbsp; </td>
                     <td><s:textfield name="cgst" readonly="true" id="cgst" cssClass="form-control" cssStyle="margin-bottom: 5px;text-align:right;" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;width:70%;">SGST :&nbsp; </td>
                     <td><s:textfield name="sgst" readonly="true" id="sgst" cssClass="form-control" cssStyle="margin-bottom: 5px;text-align:right;" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;width:70%;">IGST :&nbsp; </td>
                     <td><s:textfield name="igst" readonly="true" id="igst" cssClass="form-control" cssStyle="margin-bottom: 5px;text-align:right;" /></td>
                    </tr>
                    <tr>
                     <td style="text-align: right;color:green;"><label>NET PAYBLE AMT :&nbsp; </label></td>
                     <td><s:textfield name="total_amt" readonly="true" cssClass="form-control" id="netpay"  cssStyle="margin-bottom: 5px; text-align:right;   background-color: rgba(220, 220, 220, 0.28);color: green;" />
                       <input type="hidden" id="nettemp" />  
                     </td>
                    </tr>
                    <tr>
                    
                   </tbody>
                  </table>
                 </div>
          </s:form>
          <a href="#" onclick="returntosuppliervalidate()" class="btn btn-primary savebigbtn salebutton" style="margin-top:15px;float:right;">Goods Return</a>
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
    
    function dosubmit(){
    	/* document.getElementById("salebutton").className="hidden"; */
       document.getElementById("formMyPo").submit();	
    }
    
    
  </script>
  

</body>
</html>