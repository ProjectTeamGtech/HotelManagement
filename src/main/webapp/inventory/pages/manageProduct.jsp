<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <SCRIPT type="text/javascript" src="inventory/js/addcategory.js"></SCRIPT> 
        <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
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
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
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
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 5px 7px 7px 7px !important;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
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


    <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
        .addcatego {
            float: right;
            margin-top: -40px;
            margin-right: 30px;
        }
        .mar9 {
            margin-top: 9px;
        }
        .mar5 {
            margin-top: 5px;
        }
        .table > tbody > tr td, .table > tbody > tr th, .table > tfoot > tr td, .table > tfoot > tr th {
            border-color: transparent !important;
        }
        .marleft34 {
            margin-left: 34px;
            margin-top: -8px;
        }
        select {
            text-transform: none;
        }
        .setcross {
            float: right;
            margin-top: 5px;
        }
        .checkspan{
            margin-top: -19px;
		    position: fixed;
		    margin-left: 15px;
        }
    </style>
    
    
    <script type="text/javascript">
     var tid=0;
      
    
    
    
      $(document).ready(function(){
         $("#tax0").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			        //display error message
			        $("#errmsg").html("Digits Only").show().fadeOut("slow");
			               return false;
			    }
			   });
			   
			   
			   
			   $("#expiry_date0").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
    
			   
			   
         });
    
     
    </script>
    
</head>

<% String categoryid=(String)session.getAttribute("category"); %>
<%if(categoryid==null){
	
	categoryid="1";
} %> 

<body>

            <div class="">
                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-6">
                        <div class="form-inline">
						  <div class="form-group">
						    <input type="text" class="form-control"  id="barcodetxt" onchange="addproductBarcode('barcodetable',this.value)" placeholder="Enter Barcode No">
						  </div>
						   <div class="checkbox">
						    <label>
						      <s:checkbox name="manual_entry" theme="simple" onclick="changeEntry(this.checked)"/> <span class="checkspan">Click here for manual entry</span>
						    </label>
						  </div>
						</div>
                        </div>
                        <div class="col-md-6 text-right">
                           <a href="#" onclick="addnewRow('table13')" class="btn btn-primary">Add New</a>
                        </div>
                    </div> 
                    
                         <s:if test="manual_entry==true">
                          <div class="row">
                         <s:form theme="simple" name="productform" action="saveproductsProduct">
                          <div class="col-lg-12 col-md-12 col-xs-12">
                    	  <div class="table-responsive">
                    		<table class="table my-table table-bordered" id="table13" style="width: 100%;">
		                            <thead>
		                                <tr>
		                                    <th style="width: 2%;">No</th>
		                                     <th style="width: 15%;">Select Product</th>
		                                    <th style="width: 15%;">Supplier Name</th>
		                                    <th style="width: 15%;">Generic Name</th>
		                                    <th style="width: 6%;">MRP</th>
		                                    <th style="width: 6%;">VAT %</th>
		                                    <th style="width: 8%;">Purchase Price</th>
		                                    <th style="width: 8%;">Sale Price</th>
		                                    <th style="width: 8%;display:none;">Pcs</th>
		                                    <th>Remove</th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <tr>
		                            </tr>
		                            	<tr>
		                            		<td>1</td>
		                            		
		                            		<td>
		                            			<s:select list="productList" listKey="id" id="brand0" name="products[0].brand" listValue="name" onchange="getvendor(this.value,0)" headerKey="0" headerValue="Select Product" cssClass="form-control" cssStyle="width: 100%;" /> 
												   
		                            		</td>
		                            		<td id="listvendor0">
		                            			 <select class="form-control" style="width: 100%;">
												    <option value="1">Mr Darvekar Biosys</option>
												    <option value="2">Baxter (India) Private Limited</option>
												    <option value="3">Devidas Kanojia</option>
												    <option value="4">Sandesh</option>
												    <option value="5">Product Vendor</option>
												</select>
		                            		</td>
		                            		<%if(categoryid.equals("1")){ %>
		                            		<td><input type="text" class="form-control" name="products[0].genericname" id="genericname0" ></td>
		                            		<%} else { %>
		                            		 <td><s:select list="genericnameList" listKey="id" headerKey="0" headerValue="Select Generic Name" listValue="genericname" cssClass="form-control" name="products[0].genericname" id="genericname0" /></td>
		                            		<%} %>
		                            		<td><input type="text" class="form-control" name="products[0].mrp" id="mrp0"></td>
		                            		<td>
		                            			<select class="form-control" name="products[0].vat" id="vat0" style="width: 100%;">
		                            				<option value="0">0</option>
												    <option value="6">6</option>
												    <option value="13.5">13.5</option>
												</select>
		                            		</td>
		                            		<td><input type="text" class="form-control" id="purchase_price0" name="products[0].purchase_price"></td>
		                            		<td><input type="text" class="form-control" name="products[0].sale_price" id="sale_price0" ></td>
		                            		<td class="hidden"><input type="text" class="form-control"></td>
		                            		<td><a href="#" onclick="deleteRow('table13')" >Remove</a></td>
		                            	</tr>
		                            </tbody>
		                             
		                          </table>
                    		
                     	</div>
                     	</div>
                        </s:form>
                        </s:if>
                        <s:else>
                            <div class="row">
                             <div class="col-lg-12 col-md-12">
                                <s:form action="savepobarcodeProduct" method="post" id="barodeproc">
                        		<table class="table my-table xlstable table-bordered" style="width: 100%;" id="barcodetable">
		                            <thead>
		                                <tr>
		                                    <th>No</th>
		                                    <th>Product Type</th>
		                                    <th>Supplier Name</th>
		                                    <th>Product Name</th>
		                                    <th>Amount</th>
		                                    <th style="width:8%">Quantity</th>
		                                    <th>Total</th>
		                                    <th>Remove</th>
		                                </tr>
		                            </thead>
		                            
		                            <tbody>
		                            
		                            <tr>
		                            </tr>
		                           
		                            	<!--<tr>
		                            		<td>1</td>
		                            		<td>Niddle</td>
		                            		<td>Baxter India Private limited</td>
		                            		<td>CAHP single-use dialyzer</td>
		                            		<td>Rs.110</td>
		                            		<td>
		                            		<div class="form-inline">
		                            		<a href=""><i class="fa fa-plus-square" aria-hidden="true"></i></a>
											  <div class="form-group" style="width: 59%;">
											    <input type="text" class="form-control" id="exampleInputEmail3" value="10" style="width:100%">
											  </div>
											  <a href=""><i class="fa fa-minus-square" aria-hidden="true"></i></a>
											</div>
		                            		</td>
		                            		<td>Rs.1100</td>
		                            		<td><a href="">Remove</a></td>
		                            	</tr>
		                            --></tbody>
		                             
		                          </table>
		                          </s:form>
                        		</div>
                        </s:else>
                             <s:if test="manual_entry==false">
                        	<div class="col-lg-12 col-md-12" style="border-top: 1px solid #efefef;padding-top: 15px;">
                        		
                        		<div class="col-lg-9 col-md-9"></div>
                        		<div class="col-lg-3 col-md-3">
                        		<div class="row">
                        		<div class="col-lg-6 col-md-6 text-right">
                        		<b>Total Amount :</b>
                        		</div>
                        		<div class="col-lg-6 col-md-6">
                        		<b>Rs.<span id="totalrs">0</span></b>
                        		</div>
                        		<br>
                        		<br>
                        		<div class="col-lg-12 col-md-12 text-right">
                        		<a href="#" onclick="savebarodeproc()" class="btn btn-primary">Create PO</a>
                        		</div>
                        		</div>
                        		</div>
                        		
                        		
                        	</div>
                        	</s:if>
                        </div>
                        <s:if test="manual_entry==true">
                        <div class="col-lg-12 col-md-12">
                            <input name="ctl00$CPHcontent$btnSave" onclick="submitProduct()" value="Save" id="CPHcontent_btnSave" class="btn btn-primary" type="button">
                        </div>
                        </s:if>
                    </div>

                </div>
            </div>

        <!--/ CONTENT -->

       <s:form action="Product" id="manualentryform">
         
           <s:hidden name="manual_entry" id="tmanual_entry"></s:hidden>
       </s:form>





    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="editcat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Edit</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Product ID :</label>
                                <div class="col-sm-9">
                                    2182
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Section :</label>
                                <div class="col-sm-9">
                                    <select id="ddlSection" class="PopDDl" onchange="getCategory();"><option value="11">OT Equipment</option><option value="12">Other</option><option value="13">Medical Equipment</option><option value="14">Baby Care</option><option value="15">Healthy Wealthy</option><option value="16">Ready to Eat</option><option value="17">Cloth Care</option><option value="20">Furniture</option><option value="21">Medicine</option><option value="23">Exclusive Store1</option><option value="24">Exclusive2</option><option value="25">Exclusive2</option><option value="26">Exclusive3</option><option value="27">person care2</option><option value="28">ssssssss</option><option value="29">test</option><option value="30"></option><option value="31"></option><option value="32"></option><option value="33">Other</option></select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Category :</label>
                                <div class="col-sm-9">
                                    <select id="ddlCatagory" class="PopDDl"><option value="4">Hospital Bed-ICU</option><option value="6">Hospital Bed-Fowler</option><option value="7">Hospital Bed-Plain</option><option value="8">Hospital Bed-Semi-Flower</option><option value="9">Obstetric Tables</option><option value="10">Trolly</option><option value="11">Stretchers</option><option value="12">Doctor Chair &amp; Stools</option><option value="209">Baby Crib</option><option value="210">Beds - Orthopaedic</option><option value="211">Beds Mattress</option><option value="220">STRETCHER TROLLEY</option><option value="221">INSTRUMENT TROLLEY</option><option value="222">EXAMINATION COUCH</option><option value="223">WHEEL CHAIR</option><option value="224">Medical Cabinets Cupboards</option><option value="225">Waiting Chairs &amp; Benches</option><option value="226">Office Conference, Coffee Tables</option></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Brand :</label>
                                <div class="col-sm-9">
                                    <select name="ctl00$CPHcontent$ddlPopUpBrads" id="CPHcontent_ddlPopUpBrads" style="width:200px;">
                                        <option value="133">Sun Pharma</option>
                                        <option value="134">Narang</option>
                                        <option value="135">PHCD</option>
                                        <option value="137">Bajaj</option>
                                        <option value="138">Philips</option>
                                        <option value="139">ATICO</option>
                                        <option value="141">SONOSITE</option>
                                        <option value="158">TOSHIBA</option>
                                        <option value="161">Assure</option>
                                        <option value="195">COVIDIEN</option>
                                        <option value="199">ConvaTec</option>
                                        <option value="202">Medtronic</option>
                                        <option value="204">Metrex</option>
                                        <option value="1206">NIPRO</option>
                                        <option value="1207">Prevail</option>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Name :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Infant Bed / Child Cot / Baby Bassinet">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">MRP :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 18000">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Purchase Price :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 14200">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Sale Price :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 15000">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Update</button>
                </div>
            </div>
        </div>
    </div>




</body>
</html>
