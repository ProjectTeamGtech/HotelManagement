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
    <script type="text/javascript" src="inventory/js/managestock.js"></script>
    <script type="text/javascript" src="common/js/pagination.js"></script>
    <SCRIPT type="text/javascript" src="inventory/js/addsale.js"></SCRIPT>

    
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
        .addbtnns{
        	    float: right;
			    margin-right: 21px;
			    margin-top: -23px;
        }
        @media (min-width: 768px){
		.modal-dialog {
		    width: 900px !important;
		    margin: 30px auto;
		}}
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
    
    
</head>





<body id="his" class="appWrapper sidebar-xs-forced">

        <section id="">

            <div class="page-sidebar-xs-layout">
            
                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-9">
                        <s:form action="listsaleProduct" theme="simple">
                        <div class="form-inline">
						  <div class="form-group">
						    <span>Sale Stock |</span>
						    <s:select list="godownlist" cssClass="form-control" name="godown" listKey="id" listValue="name" headerKey="0" headerValue="Select GoDown"></s:select>
						    <s:textfield cssClass="form-control" id="fromdate" name="fromdate" placeholder="From Date" />
						    <s:textfield cssClass="form-control" id="todate" name="todate" placeholder="To Date" />
						  </div>
						    <button type="submit" class="btn btn-primary">Filter</button>
                            <button type="submit" class="btn btn-warning">Reset</button>
						</div>
						</s:form>
                        </div>
                        <div class="col-md-3 text-right">
                        <a href="saleProduct" class="btn btn-primary">Add Sale</a></div>
                    </div> 
                    <div class="">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>Sr</th>
                                    <th>Product Id / Code</th>
                                    <th>Godown</th>
                                    <th>Name</th>
                                    <th>Product Type</th>
                                    <th>Supplier</th>
                                    <th>Product</th>
                                    <th>Sale Date</th>
                                    <th>Expiry Date</th>
                                    <th>Sale Price</th>
                                    <th>Min Stock</th>
                                    <th>Quantity</th>
                                    <th>Sale Return</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                              <%int i=0; %>
                              <s:iterator value="soldproductList">
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="product_id"/> / <s:property value="product_code" /></td>
                                    <td><s:property value="gowdown"/></td>
                                    <td><s:property value="product_name" /></td>
                                    <td><s:property value="subcategory"/></td>
                                    <td><s:property value="vendor"/></td>
                                    <td><s:property value="product_name"/></td>
                                    <td><s:property value="sale_date"/></td>
                                    <td><s:property value="expiry_date"/></td>
                                    <td><s:property value="sale_price"/></td>
                                    <td><s:property value="stock"/></td>
                                    <td><s:property value="quantity"/></td>
                                    <td><a href="#" onclick="viewReturnpop(<s:property value="id"/>)">Return</a></td>
                                    <td><a href="#"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="#" onclick="return cnfmdelete()"><i class="fa fa-trash text-danger"></i></a></td>
                                </tr>
                               </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
							                  <s:form action="listProduct" name="listproduct" id="listproduct"
									theme="simple">
							    
									<div class="row">
									<div class="col-lg-4 col-md-4 text-right">
										Showing all <label class="text-info">(<s:property
												value="pagerecords" /> of <s:property value="totalRecords" />
											Records)
										</label>
									</div>
									<%@ include file="/common/pages/pagination.jsp"%>
								</div>
							</s:form> 

            </div>

        </section>
        <!--/ CONTENT -->



    <!--Add Sale Modal -->
    <div class="modal fade" id="salestock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content modal-lg">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="">Add Sale</h4>
                    <a href="#" onclick="addsaleProduct('saletable')" class="addbtnns btn btn-sm btn-warning"><i class="fa fa-plus"></i></a>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                    <s:form theme="simple" action="addtosaleProduct" id="saletableform">
                   <table class="table my-table xlstable table-bordered" id="saletable" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 12%;">Godown</th>
                                    <th style="width: 13%;">Category</th>
                                    <th style="width: 16%;">Product Type</th>
                                    <th style="width: 13%;">Supplier</th>
                                    <th style="width: 18%;">Product</th>
                                    <th style="width: 9%;">Min Stock</th>
                                    <th style="width: 7%;">Quantity</th>
                                    <th style="width: 9%;">Sale Price</th>
                                    <th>Edit</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody><!--
                                <tr>
                                    <td>Godown02</td>
                                    <td>Medicine</td>
                                    <td>Niddle</td>
                                    <td>Baxter india pvt.ltd</td>
                                    <td>CAHP Single-Use Dialyzers</td>
                                    <td>50</td>
                                    <td>40</td>
                                    <td>Rs.110</td>
                                    <td><a href="#"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="#">Remove</a></td>
                                </tr>
                                --><tr>
                                	<td>
                                		<s:select list="godownlist" cssClass="form-control" listKey="id" listValue="name" name="sales[0].gowdown"></s:select>
                                	</td>
                                    <td><s:select list="categoryList" onchange="getprodtype(this.value,0)" cssClass="form-control" listKey="id" listValue="name" id="category0" name="sales[0].category"></s:select>
                                    </td>
                                    <td id="tdsub0">
                                    	<s:select list="subcategoryList" cssClass="form-control" listKey="id" listValue="name" id="subcategory_id0" name="sales[0].subcategory"></s:select>
                                    </td>
                                    <td>
                                        <s:select list="vendorList" cssClass="form-control" onchange="getsalesproduct(this.value,0)" listKey="id" listValue="name" name="sales[0].vendor_id"></s:select>
                                    </td>
                                    <td id="tdprod0">
                                    	<s:select list="productList" cssClass="form-control" listKey="id" listValue="product_name" onchange="getstockandprice(this.value,0)"  name="sales[0].product_id"></s:select>
										
                                    </td>
                                    <td><span id="minstock0">50</span></td>
                                    <td><input class="form-control" type="text" name="sales[0].quantity" placeholder="enter qty"></td>
                                    <td><span id="saleprice0">Rs. 130</span></td>
                                    <td><a href="#"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="#">Remove</a></td>
                                </tr>
                            </tbody>
                        </table>
                        </s:form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="saleproduct()" class="btn btn-primary">Sale</button>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
       <!--Return Sale Modal -->
    <div class="modal fade" id="returnstock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content modal-lg">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Return Sale History</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                   <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 12%;">Select Godown</th>
                                    <th style="width: 9%;">Sale Date</th>
                                    <th style="width: 10%;">Category</th>
                                    <th style="width: 12%;">Product Type</th>
                                    <th style="width: 13%;">Supplier</th>
                                    <th style="width: 18%;">Product</th>
                                    <th style="width: 9%;">Sale</th>
                                    <th style="width: 9%;">Return Qty</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--<tr>
                                    <td>Godown02</td>
                                    <td>05/10/2016</td>
                                    <td>Medicine</td>
                                    <td>Niddle</td>
                                    <td>Baxter india pvt.ltd</td>
                                    <td>CAHP Single-Use Dialyzers</td>
                                    <td>70</td>
                                    <td>20</td>
                                    <td><a href="#"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="#">Remove</a></td>
                                </tr>
                                --><tr id="returnsale">
                                    <td>Godown02</td>
                                    <td>05/10/2016</td>
                                    <td>Medicine</td>
                                    <td>Niddle</td>
                                    <td>Baxter india pvt.ltd</td>
                                    <td>CAHP Single-Use Dialyzers</td>
                                    <td>90</td>
                                    <td><input class="form-control" type="text" placeholder="qty return"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="updatesalestock()" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>

   <SCRIPT type="text/javascript">
   
      function updatestock() {
       
            document.updateform.action="updatestockProduct";
            document.updateform.submit();
      }
   
      function cnfmdelete() {
      
         var t=confirm("Are you sure to Delete?");
         
         if(t==true) {
           
            return true;
         } else
          {
             return false;
          }
      
      }
   
   </SCRIPT>

</body>
</html>
