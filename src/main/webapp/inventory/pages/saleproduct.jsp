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
    <SCRIPT type="text/javascript" src="inventory/js/addsale.js"></SCRIPT>
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
            padding: 1px 7px 1px 7px !important;
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
			    margin-top: -1px;
			    width: 58%;
        }
        .setcross1 {
                float: right;
			    margin-top: -1px;
			    width: 29%;
        }
        
    </style>
    
    
    <SCRIPT type="text/javascript">
      
      $(document).ready(function(){
        
              $("#quantity0").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			        //display error message
			        $("#errmsg").html("Digits Only").show().fadeOut("slow");
			               return false;
			    }
			   });
         });
    
    </SCRIPT>
    
</head>





<body id="his" class="appWrapper sidebar-xs-forced">

    
  
        <section id="">

            <div class="">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-6">
                        <div class="form-inline">
						  <div class="form-group">
						    <span>Manage Inventory | Sale Product |</span>
						    <input type="text" class="form-control"  id="barcodetxt" onchange="addproductBarcodesale('barcodetable',this.value)" placeholder="Enter Barcode No" style="background-color: floralwhite;">
						  </div>&nbsp;&nbsp;
						   <div class="checkbox">
						    <label>
						      <s:checkbox name="manual_entry" theme="simple" onclick="changeEntrySale(this.checked)"/> <span class="checkspan">Click here for manual entry</span>
						    </label>
						  </div>
						</div>
                        </div>
                        <div class="col-md-6 text-right">
                           
                        </div>
                    </div> 
                    <div>
                         
                         
                         <s:if test="manual_entry==true">
                         <s:form theme="simple" name="productform" action="addtosaleProduct" id="saleproductform">
                        <table class="table my-table xlstable table-bordered" id="table13" style="width: 100%;">
                        		
                        		<thead>
                        		<tr>
		                                    <th>No</th>
		                                    <th>Godown</th>
		                                    <th>Category</th>
		                                    <th>Product Type</th>
		                                    <th>Supplier</th>
		                                    <th>Product</th>
		                                    <th style="width:7%">Min Stock</th>
		                                    <th>Quantity</th>
		                                    <th style="width:8%">Sale Price</th>
		                                    <th>Remove</th>
		                        </tr>
                        		</thead>
                                <tr>
                                    <td>1)</td>
                                    <td>
                                        <s:select cssClass="form-control" list="godownlist" headerKey="0"  id="godownid0" name="sales[0].gowdown" listKey="id" headerValue="Select GoDown" listValue="name" cssStyle="width: 100%;" >
                                        </s:select>
                                    </td>
                                    <td>
                                        <s:select cssClass="form-control"  list="categoryList"  headerKey="0" headerValue="Select Category" listKey="id" 
                                        listValue="name" id="subcategory_id0" onchange="getprodtype(this.value,0)" name="sales[0].category" cssStyle="width: 100%;">
                                        </s:select>
                                    </td>
                                    <td id="tdsub0">
                                        <s:select cssClass="form-control"  list="subcategoryList"  headerKey="0" headerValue="Select Product Type" listKey="id" 
                                        listValue="name" id="subcategory_id0" name="sales[0].subcategory" cssStyle="width: 100%;">
                                        </s:select>
                                    </td>
                                    <td>
                                        <s:select cssClass="form-control" list="vendorList" id="vendor_id0"  name="products[0].vendor_id" headerKey="0"  headerValue="Select Supplier" onchange="getsalesproduct(this.value,0)" listKey="id" listValue="name" cssStyle="width: 100%;">
                                        </s:select>
                                    </td>
                                    <td id="tdprod0">
                                        <s:select cssClass="form-control" list="productList" onchange="getstockandprice(this.value,0)" listKey="id" headerKey="0" headerValue="Select Product" listValue="product_name" name="products[0].product_id" id="product_id0" cssStyle="width: 100%;"></s:select>
                                        
                                    </td>
                                    <td> <span id='minstock0'>50</span>
                                    </td>
                                    <td>
                                        <input class="form-control" id="quantity0" name="sales[0].quantity" type="text" placeholder="Enter Quantity">
                                        
                                    </td>
                                    <td> <span id="saleprice0">Rs. 130</span>
                                    </td>
                                     <td>
                                         <a href=""><img src="inventory/img/close_pop.png" class="setcross"/></a>
                                    </td>
                                </tr>
                        </table>
                        </s:form>
                        </s:if>
                        <s:else>
                            <div class="row">
                             <div class="col-lg-12 col-md-12">
                                <s:form action="addtosaleProduct" method="post" id="barodeprocsale">
                        		<table class="table my-table xlstable table-bordered" style="width: 100%;" id="barcodetable">
		                            <thead>
		                                <tr>
		                                    <th>No</th>
		                                    <th>Godown</th>
		                                    <th>Category</th>
		                                    <th>Product Type</th>
		                                    <th>Supplier</th>
		                                    <th>Product</th>
		                                    <th style="width:8%">Min Stock</th>
		                                    <th>Quantity</th>
		                                    <th>Sale Price</th>
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
		                            -->
		                            </tbody>
		                             
		                          </table>
		                          </s:form>
                        		</div>
                        </s:else>
                             <s:if test="manual_entry==false">
                        	<div class="col-lg-12 col-md-12" style="border-top: 1px solid #efefef;padding-top: 15px;">
                        		
                        		<div class="col-lg-9 col-md-9"></div>
                        		<div class="col-lg-3 col-md-3">
                        		<div class="row">
                        		<div class="col-lg-6 col-md-6 text-right hidden">
                        		<b>Total Amount :</b>
                        		</div>
                        		<div  class="col-lg-6 col-md-6 hidden">
                        		<b>Rs.<span id="totalrs">0</span></b>
                        		</div>
                        		<br>
                        		<br>
                        		<div class="col-lg-12 col-md-12 text-right">
                        		<a href="#" onclick="savebarodesale()" class="btn btn-primary">Create Sale</a>
                        		</div>
                        		</div>
                        		</div>
                        		
                        		
                        	</div>
                        	</s:if>
                        </div>
                        <s:if test="manual_entry==true">
                        <div class="col-lg-12 col-md-12">
                            <input name="ctl00$CPHcontent$btnaddProduct" onclick="addnewRowsale('table13')" value="Add more Sale" id="CPHcontent_btnaddProduct" class="btn btn-primary" type="button">
                            <input name="ctl00$CPHcontent$btnSave" onclick="submitsaleProduct()" value="Sale" id="CPHcontent_btnSave" class="btn btn-primary" type="button">
                        </div>
                        </s:if>
                    </div>

                </div>
            </div>

        </section>
        <!--/ CONTENT -->

       <s:form action="saleProduct" id="manualentryform">
         
           <s:hidden name="manual_entry" id="tmanual_entry"></s:hidden>
       </s:form>



</body>
</html>
