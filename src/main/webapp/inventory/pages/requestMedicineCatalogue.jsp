<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->
<%@ taglib prefix="s" uri="/struts-tags" %>


<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    
    
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
        .sort{
        width: 15%;padding-top: 2px;
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
        
    </style>
    
    <SCRIPT type="text/javascript">
    
    			function submitForm(){
    			
    					document.getElementById("myform").submit();
    			}
    		
    </SCRIPT>
    
    <SCRIPT type="text/javascript">

    window.onload = function(){
              
         $("#expiry_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		             
    };
  
     


</SCRIPT>
    
    
    <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
      <script type="text/javascript" src="common/js/pagination.js"></script>
</head>




<body id="his" class="appWrapper sidebar-xs-forced">

    
        <section id="">

            <div class="">
                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                    
                       <div class="col-md-6">
                        <s:form theme="simple" action="Catalogue">
                          <div class="form-inline">
						  	<div class="form-group">
						  		
						  	   <s:textfield  name="prod_name" placeholder="Search Product Name/ GenericName" theme="simple" cssClass="form-control"></s:textfield>
						  	</div>
						  	<button type="submit"  class="btn btn-primary">Go</button>
						  	<input type="button" onclick="printReport()" class="hidden" value="Download Excel" class="btn btn-warning" />
						  	<a href="#" class="btn btn-info" onclick="addToCart2()">Add To Transfer</a>
						  	<div class="form-group" id="cartdiv2">
						  		<%if(session.getAttribute("tcount")!=null){ %>
						  			<%  String p_id =  (String)session.getAttribute("tcount"); %>
         								<a href="#" data-toggle="modal" onclick="showCartPopUp2()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=p_id %> </span></a>
						  		<%}else{ %>
						  			<a href="#" data-toggle="modal" data-target="#cart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  		<%} %>
						  	</div>
						   </div>
						</s:form>
                       </div>
                        <div class="col-md-6">
                
                            
                        </div>
                    </div> 
                    <div class="">
                        <table class="table my-table xlstable table-striped table-bordered" id="example" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th><a href="#" onclick="deleteCheck()"><i class="fa fa-trash" style="color: #fff;"></i></a></th>
                                    <th>ID</th>
                                     <th>Location</th>
                                    <th>HSN Code</th>
                                    <th style="width: 13%;">Generic Name</th>
                                    <th style="width: 20%;">Product Name</th>
                                    <th style="text-align:right">MRP</th>
                                    <th style="text-align:right">Purchase Price</th>
                                    <th style="text-align:right">Sale Price (pu)</th>
                                    <th style="text-align:center">Stock</th>
                                    <th style="text-align:center">Expiry Date</th>
                                    <th style="text-align:center">Batch No</th>
                                    <th style="text-align:center">MFG</th>
                                    <th style="text-align:center">Shelf</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                               <s:iterator value="productList">
                                <tr>
                                    <td><input type="checkbox" class="case" value="<s:property value="id"/>" class="form-control" /></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="location"/></td>
                                    <td><s:property value="hsnno"/></td>
                                    <td style="text-transform: uppercase;"><s:property value="genericname"/></td>
                                    <td><s:property value="product_name"/></td>
                                    <td class="text-right">Rs.<s:property value="mrp"/></td>
                                    <td class="text-right">Rs.<s:property value="purchase_price"/></td>
                                    <td class="text-right">Rs.<s:property value="sale_price"/></td>
                                    <td class="text-center"><s:property value="stock"/></td>
                                    <td class="text-center"><s:property value="expiry_date"/></td>
                                    <td class="text-center"><s:property value="batch_no"/></td>
                                    <td class="text-center"><s:property value="mfg"/></td>
                                    <td class="text-center"><s:property value="shelf"/></td>
                                    <td><a href="#" onclick="editProd(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
									<td><a href="deleteprodCatalogue?id=<s:property value="id"/>" onclick="return confirmdelete()" ><i class="fa fa-trash" style="color: #d9534f;"></i></a></td>
                                </tr>
                                </s:iterator>
                            </tbody>

                        </table><br/>
                    </div>

					  <s:form action="Catalogue" name="paginationForm" id="paginationForm" theme="simple">
							    
									<div class="">
									<div class="col-lg-4 col-md-4 text-right">
										Showing all <label class="text-info">(<s:property
												value="pagerecords" /> of <s:property value="totalRecords" />
											Records)
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form>                   
                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->



<!-- Cart Modal -->
<div id="cart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="transferrequestedproductdataCatalogue" theme="simple" id="transferproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Transfer Stock</h4>
      </div>
      <div class="modal-body">
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tabletrcount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 10%;">From Location</th>
            <th style="width: 9%;">Current Stock</th>
            <th style="width: 13%;">Requested Location</th>
            <th style="width: 10%;">Transfer Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="carttbody">
           
          </tbody>
          </table>
          <input type="hidden" name="tcount" id="tcount">
        </div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" onclick="confirmTransfer()">Transfer</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>




    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="editcat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Update Product</h4>
                </div>
                <div class="modal-body" style="padding:0px;">
                    <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                    	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                    		<div class="form-group">
							    <label for="email">Generic Name:</label>
							    <input type="text" class="form-control" >
							  </div>
                    	</div>
                    	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                    		<div class="form-group">
							    <label for="email">Product Name</label>
							    <input type="text" class="form-control">
							  </div>
                    	</div>
                    	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                    		<div class="form-group">
							    <label for="email">MRP/Purchase Price</label>
							    <p>Rs.1200.00 / Rs.1150.00</p>
							  </div>
                    	</div>
                    	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                    			<div class="form-group">
							    <label for="email">Sale Price<small>(per unit)</small></label>
							    <input type="text" class="form-control">
							  </div>
                    	</div>
                    
                        <form class="form-horizontal" id="myform" action="updateprodProduct" method="post">
                            <input type="hidden" name="id" id="id" /> 
                            <div class="form-group hidden">
                                <label for="inputEmail3" class="col-sm-3 control-label">Section :</label>
                                <div class="col-sm-9">
                                    <select id="ddlSection" class="PopDDl" onchange="getCategory();"><option value="11">OT Equipment</option><option value="12">Other</option><option value="13">Medical Equipment</option><option value="14">Baby Care</option><option value="15">Healthy Wealthy</option><option value="16">Ready to Eat</option><option value="17">Cloth Care</option><option value="20">Furniture</option><option value="21">Medicine</option><option value="23">Exclusive Store1</option><option value="24">Exclusive2</option><option value="25">Exclusive2</option><option value="26">Exclusive3</option><option value="27">person care2</option><option value="28">ssssssss</option><option value="29">test</option><option value="30"></option><option value="31"></option><option value="32"></option><option value="33">Other</option></select>
                                </div>
                            </div>

                            <div class="form-group hidden">
                                <label for="inputEmail3" class="col-sm-3 control-label">Category :</label>
                                <div class="col-sm-9">
                                    <select id="ddlCatagory" class="PopDDl"><option value="4">Hospital Bed-ICU</option><option value="6">Hospital Bed-Fowler</option><option value="7">Hospital Bed-Plain</option><option value="8">Hospital Bed-Semi-Flower</option><option value="9">Obstetric Tables</option><option value="10">Trolly</option><option value="11">Stretchers</option><option value="12">Doctor Chair &amp; Stools</option><option value="209">Baby Crib</option><option value="210">Beds - Orthopaedic</option><option value="211">Beds Mattress</option><option value="220">STRETCHER TROLLEY</option><option value="221">INSTRUMENT TROLLEY</option><option value="222">EXAMINATION COUCH</option><option value="223">WHEEL CHAIR</option><option value="224">Medical Cabinets Cupboards</option><option value="225">Waiting Chairs &amp; Benches</option><option value="226">Office Conference, Coffee Tables</option></select>
                                </div>
                            </div>
                            
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding: 0px 10px 0px 10px;background-color: #efefef;">
                            <div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">HSN Code</label>
		                                <input class="form-control" type="text" maxlength="8" pattern="([0-9]|[0-9]|[0-9])" name="hsnno" style="text-transform: uppercase;width:95%;"/>
		                            </div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Generic Name</label>
		                                <input type="text" name="genericname" id="genericname"  class="form-control"  style="text-transform: uppercase;width:95%;">
		                            </div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
	                                <label for="inputEmail3" class="control-label">Name</label>
	                                <input type="text" class="form-control" id="product_name" name="product_name" style="text-transform: uppercase;width:95%;">
	                            </div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Select Product Type</label>
		                                <s:select list="#{'0':'Regular','1':'Narotics','2':'H1'}" cssClass="form-control" cssStyle="background-color: rgba(224, 93, 111, 0.6);color: #fff;" name="medicine_shedule" id="medicine_shedule" ></s:select>
	                            	</div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Select Product Type</label>
										<s:select theme="simple" name="medicine_type" id="medicine_type" list="medicineTypeList" cssClass="form-control" listKey="name" listValue="name" headerKey="0" headerValue="Select Medicine Type"   ></s:select>			                                
	                            	</div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Select Location</label>
										<s:select theme="simple" name="location" id="location" list="locationListPharmacy" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Select Location"   ></s:select>			                                
	                            	</div>
                            	</div>
                            </div>
                           
                             <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="margin-top: 10px;">
                             <div class="col-lg-3 col-md-3 col-xs-3">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">MRP</label>
                            		<input type="text" class="form-control" id="mrp" name="mrp"  style="width:95%;">
                               	</div>
                             </div>
                             <div class="col-lg-3 col-md-3 col-xs-3">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">Pur.Price</label>
                            		<input type="text" class="form-control" id="purchase_price" name="purchase_price" style="width:95%;">
                            		<small>without VAT</small>
                               </div>
                             </div>
                             <div class="col-lg-3 col-md-3 col-xs-3">
                             	<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Sale.Price</label>
		                                <input type="text" class="form-control" id="sale_price" name="sale_price" style="width:95%;" />
		                            </div>
                             </div>
                             <div class="col-lg-3 col-md-3 col-xs-3">
                             	<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Stock</label>
		                                <input type="number" class="form-control" id="stock" name="stock"  style="background-color: lightgoldenrodyellow;"/>
		                            </div>
                             </div>
                            </div>
                            
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12">
                             <div class="col-lg-3 col-md-3 col-xs-3">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">Expiry Date</label>
                            		<input type="text" class="form-control" id="expiry_date" name="expiry_date"  style="width:95%;"/>
                               	</div>
                             </div>
                             <div class="col-lg-3 col-md-3 col-xs-3">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">Batch Number</label>
                            		<input type="text" class="form-control" id="batch_no" name="batch_no"  style="width:95%;"/>
                               </div>
                             </div>
                             <div class="col-lg-3 col-md-3 col-xs-3">
                             	<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Mfg</label>
		                                <input type="text" class="form-control" id="mfg" name="mfg" style="width:95%;" />
		                            </div>
                             </div>
                             <div class="col-lg-3 col-md-3 col-xs-3">
                             	<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Select Shelf</label>
		                                <s:select list="cellList" cssClass="form-control" listKey="name" listValue="name" name="shelf" id="shelf" ></s:select>
		                            </div>
                             </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="submitForm()" class="btn btn-primary">Update</button>
                </div>
            </div>
        </div>
    </div>


     

	
	 

</body>
</html>
