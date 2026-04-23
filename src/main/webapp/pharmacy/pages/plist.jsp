<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<SCRIPT type="text/javascript" src="pharmacy/js/pharmacy.js"></SCRIPT>

<style>
	body {
    font-family: "Lato", "Arial", sans-serif;
    font-size: 14px;
    font-weight: 400;
    text-rendering: optimizeLegibility !important;
    -webkit-font-smoothing: antialiased !important;
    -moz-osx-font-smoothing: grayscale !important;
    -ms-overflow-style: scrollbar;
    background-color: #ffffff;
    color: #616f77;
}
.checkbox {
    position: relative;
    display: block;
    min-height: 13px;
    margin-top: 0px;
    margin-bottom: 0px;
}
.bgreen{
	    background-color: rgba(22, 160, 133, 0.12);
    padding: 10px 15px 15px 15px;
    min-height: 443px;
}
.list-group.no-border .list-group-item {
    border-width: 1px 0;
    border-left: 3px solid;
}
.list-group-item {
    position: relative;
    display: block;
    padding: 10px 11px;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid #ddd;
}
.rightborder{
	    
	    min-height: 450px;
}
.setback5{
	    background-color: rgba(0, 0, 0, 0.02);
    min-height: 450px;
        padding-top: 10px;
}
.checkbox-custom-alt > i {
    width: 16px;
    height: 16px;
    background-color: transparent;
    border: 2px solid #dfdfdf;
    margin-right: 6px;
    margin-left: -18px;
}
.checkbox-custom-alt input:checked + i:before {
    top: 1px;
    left: 1px;
    width: auto;
    height: auto;
    background-color: transparent;
    opacity: 1;
}
.bg-lightred {
    background-color: #e05d6f !important;
    color: white !important;
}
.sehe20{
	    height: 20px;
}
</style>

<SCRIPT type="text/javascript">

	 $(document).ready(function(){
			   
			   $("#fromdate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
				$("#todate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
			   
         });


</SCRIPT>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
								<div class="row details hidden">
                                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Purchase List </h4>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                	<div class="col-lg-12 col-xs-12 col-xs-12" style="padding:0px;">
                                	<div id="npurchase" class="collapse">
									Lorem ipsum dolor text....
									</div>
                                		<table class="table table-bordered hidden" cellspacing="0" width="100%">
                                <thead>
                                <tr class="tableback ">
                                		<th style="width:20%;">Date | Time</th>
                                        <th style="width:35%;">Product Name</th>
                                        <th style="width:25%;">Generic Name</th>
                                        <th style="width:18%;">Supplier</th>
                                        <th>UID</th>
                                        <th style="width: 1%;"></th>
                                    </tr>
                                </thead>
                                	  <tbody>
                                    <!--<tr>
                                    	<td><label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i></label></td>
                                    	<td>16-03-2017 | 13:00:25</td>
                                        <td>XyZ Product</td>
                                        <td>Ravi_002</td>
                                    </tr>
                                --></tbody>
                            </table>
                            
                            <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                            	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form cssClass="form-inline search" theme="simple" action="plistPharmacy" method="post">
											<div class="form-group">
												<select class="form-control" name="report">
												  <option value="0">Today</option>
												  <option value="0">Yesterday</option>
												</select>
											</div>
											<div class="form-group">
												<s:textfield id="fromdate" name="fromdate" theme="simple" cssClass="form-control" placeholder="From Date" />
											</div>
											<div class="form-group">
												<s:textfield id="todate" name="todate" theme="simple" cssClass="form-control" placeholder="To Date" />
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										 <input type="button" value="Create PO" onclick="posalelist()" class="btn btn-primary hidden" />
										</s:form>
										
										
										
										</div>
										</div>
								</div>
								
								 <s:form theme="simple" id="plistform" action="medicinepoProcurement" method="post"> 
								 
								 <s:hidden id="allpolist" name="allpolist"></s:hidden>
								 <div class="col-lg-12 col-md-12" style="padding:0px;">
								<table class="table my-table" style="width: 100%;">
									<thead>
										<tr>
											<th style="width: 3%;">Sr.No</th>
											<th style="width: 10%;">Product ID</th>
											<th style="width: 5%;">Pack</th>
											<th style="width: 10%;">Product Type</th>
											<th style="width: 20%;">Product Name</th>
											<th style="width: 13%;">Generic Name</th>
											<th style="width: 4%;">Sold</th>
											<th style="width: 7%;">In Stock</th>
											<th class="" style="width: 21%;">Supplier Name</th>
											<th>Enter Qty</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
									<%int  i=0; %>
									 <s:iterator value="soldProductList">
									 
									     <% String bgcolor="";  %>
									     <s:if test="postatus!=0">
									             <%bgcolor="rgba(240, 173, 78, 0.18);"; %>   
									     </s:if>
									     <s:else>
									        	<%bgcolor="White;"; %>
									     </s:else>
									     
									     <s:if test="soltStock==0">
									           <%bgcolor="rgba(217, 83, 79, 0.06)"; %>
									     </s:if>
									   
										<tr style="background-color: <%=bgcolor %>" >
											<td><%=(i+1) %></td>
											<td><s:property value="product_id"/></td>
											<td>
												<s:property value="pack" />  
											</td>
											<td>
												<s:property value="medicine_type" />  
											</td>
											<td><s:property value="product_name"/></td>
											<td><s:property value="genericname"/></td>
											<td><s:property value="soltStock"/></td>
											<td><s:property value="stock"/></td>
											<td><s:property value="vendor"/></td>
											<td><input type="text" class="form-control" name="qty<s:property value="product_id"/>" placeholder="Qty" /></td>
											<td><label style="padding-top: 3px;" class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox" class="case" value="<s:property value="product_id"/>" /><i></i></label></td>
											
										</tr>
										<%i++; %>
										</s:iterator>
																					
									</tbody>
						
								</table>
								</div>
									
								 <div class="col-lg-12 col-xs-12 col-md-12 text-right" style="margin-top:15px;">
									<input type="button" value="Create PO" onclick="posalelist()" class="btn btn-primary" />
								</div>
								</s:form>
                           
                                
                                
                                </div>
                            
                                	</div>
                                	<div class="col-lg-3 col-xs-3 col-xs-12 hidden" style="padding:0px;">
										<div class="hidden" id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                	
                                		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden topback2 " style="padding: 7px 0px 7px 7px !important;">
                                		<div class="col-lg-6 col-md-6">
                                		<div class="form-group">
										    <label for="exampleInputEmail1">Create PO Under:</label>
										    
										  </div>
                                	</div>
                                	<div class="col-lg-6 col-md-6">
                                		<button data-toggle="collapse" data-target="#npurchase" class="btn btn-primary pull-right hidden">New Purchase</button>
                                	</div>
                                	</div>
                                	<div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;">
                                	<s:form id="poform" theme="simple" action="createpoPharmacy">
                                	<s:hidden id="vendorid" name="vendor"></s:hidden>
                                		<table class="table my-table xlstable" style="width: 100%;">
									<thead>
										<tr>
											<th style="width: 3%;">Sr.No</th>
											<th style="width: 14%;">Product ID</th>
											<th style="width: 35%;">Product Name</th>
											<th style="width: 34%;">Generic Name</th>
											<th class="hidden" style="width: 12%;text-align:right">MRP</th>
											<th style="width: 7%;" class="hidden">In Stock</th>
											<th style="width: 6%;">Quantity</th>
											<th class="hidden" style="width: 112%;text-align:right">Total</th>
											<th></th>
										</tr>
									</thead>
									  
									<tbody id="alllist">
										<!--<tr>
											<td>1</td>
											<td>med123</td>
											<td>XyZ Product</td>
											<td>XyZ Product</td>
											<td class="text-right">Rs.120.00</td>
											<td class="text-center">2</td>
											<td><input type="email" class="form-control" placeholder="Qty"></td>
											<td class="text-right">Rs.00.00</td>
											<td class="text-center"><a herf="#"><i class="fa fa-trash-o"></i></a></td>
										</tr>
									--></tbody>
						
								</table>
								</s:form>
								<div class="col-lg-12 col-xs-12 col-md-12 text-right hidden" style="padding: 15px 15px 15px 3px;">
									<b><span>Total:</span> Rs.<label id="sumall">00.00</label></b>
								</div>
								<div class="col-lg-12 col-xs-12 col-md-12 text-right" style="margin-top:15px;">
									<button class="btn btn-primary pull-right" onclick="createpo()" >Create PO</button>
								</div>
                                	</div>
                                	</div>
                                
                                
                                
</body>
</html>