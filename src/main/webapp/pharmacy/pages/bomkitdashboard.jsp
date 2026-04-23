<!doctype html>
<html class="no-js" lang="">
<!--<![endif]-->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

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
        input[type="checkbox"] {
		    margin: 0px 0 0 !important;
		    margin-top: 1px \9;
		    line-height: normal;
		    /* padding-top: 15px; */
		    position: absolute !important;
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
        .table>tbody>tr>td, .table>tfoot>tr>td {
		    padding: 2px 5px 0px 5px !important;
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
     b, strong {
    font-weight: 900;
}   
    </style>
    
    <SCRIPT type="text/javascript">
    
    			function submitForm(){
    			
    					document.getElementById("myform").submit();
    			}
    		
    </SCRIPT>
    
    <SCRIPT type="text/javascript">

    window.onload = function(){
              
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
         $("#expiry_date").datepicker({

 			dateFormat : 'dd-mm-yy',
 			yearRange: yearrange,
 			minDate : '30-12-1880',
 			changeMonth : true,
 			changeYear : true

 		});
        
 		                  
    };
  
    function printReport() {
				
				  $("#tablesort").table2excel({
					exclude: ".noExl",
					name: "Stock Status",
					filename: "CatalogueReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }
    
    
    function printProductExcel() {

        $(".xlsproduct").table2excel({
					exclude: ".noExl",
					name: "Product List",
					filename: "ProductList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
    function printStockReportExcel() {

        $(".tablestock").table2excel({
					exclude: ".noExl",
					name: "Stock Status Report",
					filename: "stcokstatusList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
     


</SCRIPT>
    
    
    <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
      <script type="text/javascript" src="common/js/pagination.js"></script>
      <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>




<body id="his" class="appWrapper sidebar-xs-forced">
		 						<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
    
        <section id="">

            <div class="">
                <div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                    
                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right">
                    <!-- <div class="form-inline">
                   
                    </div> -->
	                       <div class="form-inline">
	                      	<!-- <div class="form-group" >
	                        <label  style="margin-right:200px"><b>Stock Status Dashboard</b></label>
	                      	</div> -->
	                      	
						  		&nbsp; &nbsp; 
	                      	
	                      	<s:if test="isfromcathlab==1">
	                      			&nbsp; &nbsp;
						  			<div class="form-group " id="issuediv" >
						  				<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#issuecart"><span >Issue BOM kit </span></a>
						  			</div>
						  	&nbsp; &nbsp; &nbsp; &nbsp;
	                      	</s:if>
	                      	<s:else>
	                       		<%if(loginInfo.getUserType()==2) {%>
	                        	<a href="#" class="btn btn-primary" onclick="addToCart()">Add to Transfer</a>
						  		<div class="form-group" id="cartdiv">
						  			<%if(session.getAttribute("tcount")!=null){ %>
						  				<%  String p_id =  (String)session.getAttribute("tcount"); %>
         									<a href="#" data-toggle="modal" onclick="showCartPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=p_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#cart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
	                        <%}else{ %>
						  	<s:if test="direct_transfer==1">
						  		<a href="#" class="btn btn-primary" onclick="addToCart()">Transfer without indent</a>
						  		<div class="form-group" id="cartdiv">
						  			<%if(session.getAttribute("tcount")!=null){ %>
						  				<%  String p_id =  (String)session.getAttribute("tcount"); %>
         									<a href="#" data-toggle="modal" onclick="showCartPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=p_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#cart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
						  	</s:if>
						  	<%} %>
						  	&nbsp; &nbsp; &nbsp; &nbsp;
						  	<a href="#" class="btn btn-primary" onclick="addToReturn()">Return to Store</a>
						  		<div class="form-group" id="returndiv">
						  			<%if(session.getAttribute("returncount")!=null){ %>
						  				<%  String r_id =  (String)session.getAttribute("returncount"); %>
         									<a href="#" data-toggle="modal" onclick="showReturnPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=r_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#returncart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
						  	&nbsp; &nbsp;
						  	<a href="#" class="btn btn-primary" onclick="addToIssue()">Add to Issue</a>
						  		<div class="form-group" id="issuediv">
						  			<%if(session.getAttribute("issuecount")!=null){ %>
						  				<%  String i_id =  (String)session.getAttribute("issuecount"); %>
         									<a href="#" data-toggle="modal" onclick="showIssuePopUp(0)"> <span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=i_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#issuecart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
						  	&nbsp; &nbsp; &nbsp; &nbsp;
						  	  <a href="#" class="btn btn-primary" onclick="addToReturnQue()">Product Return Que</a>
						  	  </s:else>
	                       </div>
                       </div>
                    
                    
                       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top:5px">
                       <div class="form-inline">
                           <s:form theme="simple" action="bomkitdashboardProduct">
                           		<s:hidden name="isfromcathlab" id="isfromcathlab"></s:hidden>
	                            <%-- <div class="form-group">
	                           		<span class="text-uppercase"><b>Stock Status</b> <!-- &nbsp; - &nbsp; --></span>
	                           </div> --%>
                           		<div class="form-group">
								  	<s:textfield name="fromdate" id="fromdate" placeholder="Fromdate" theme="simple" cssClass="form-control"></s:textfield>
								</div>
								<div class="form-group">
								  	<s:textfield name="todate" id="todate" placeholder="Todate" theme="simple" cssClass="form-control"></s:textfield>
								</div>
								
								<div class="form-group">
								  	<button type="submit" class="btn btn-primary">Go</button>
								  	<a type="button"  title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
									<a type="button" class="btn btn-primary"  title="Print" onclick="printDiv('page_printer')"><i class="fa fa-print"></i></a>
								</div>
							
                           	</s:form>
                           		
                           </div>
                        </div>
                       </div>
                        
                    <div class="col-lg-12">
                        <table class="table my-table xlstable table-striped table-bordered" id="tablesort" style="width: 100%;">
                            <thead>
                               <tr>
                               		<th style="width: 5%;">Sr. No</th>
                                    <th style="width: 5%;">ID</th>
                                    <th style="width: 25%;">Procedure name</th>
                                    <th style="width: 25%;">Patient Name</th>
                                    <th style="width: 12%;">Date</th>
                                    <th style="width: 11%;">User Id</th>
                                    <th style="width: 15%;">Status</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            <%int xyz=0; %>
                               <s:iterator value="productList" >
                               
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++xyz) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="tempname"/></td>
                                    <td><s:property value="clientname"/></td>
                                    <td><s:property value="date"/></td>
                                    <td><s:property value="userid"/></td>
                                    <td><s:if test="status==0">
                                    		<a href="#" class="btn btn-primary" data-toggle="modal" onclick="setissuetotransfer(<s:property value="id"/>)"><span>Consume </span></a>
                                    	</s:if>
                                    	<s:else>
                                    		Consumed
                                    	</s:else></td>
                                </tr>
                                
                                </s:iterator>
                            </tbody>

                        </table><br/>
                    </div>



 <!-- <div class="hidden" id="page_printer"> -->
 <div style="width: 100%;font-size: 8px;display:none;" id="page_printer">
 						<h5><span class="text-uppercase"><b>Stock Status</b> &nbsp;  &nbsp;</span></h5>
                        <table class="my-table tablestock" id="productlistnew" style="width: 100%;font-size: 8px;">
                           <thead>
                               <tr>
                               		<th style="width: 5%;">Sr. No</th>
                                    <th style="width: 5%;">ID</th>
                                    <th style="width: 30%;">Procedure name</th>
                                    <th style="width: 30%;">Patient Name</th>
                                    <th style="width: 2%;">Date</th>
                                    <th style="width: 11%;">User Id</th>
                                    <th style="width: 7%;">Status</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            <%int xyz1=0; %>
                               <s:iterator value="productList" >
                               
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++xyz1) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="tempname"/></td>
                                    <td><s:property value="clientname"/></td>
                                    <td><s:property value="date"/></td>
                                    <td><s:property value="userid"/></td>
                                    <td>
                                    	<s:if test="status==0">
                                    		<a href="#" class="btn btn-primary" data-toggle="modal" onclick="setissuetotransfer(<s:property value="id"/>)"><span>Consume </span></a>
                                    	</s:if>
                                    	<s:else>
                                    		Consumed
                                    	</s:else>
                                    	
                                    </td>
                                </tr>
                                
                                </s:iterator>
                            </tbody>


                        </table>
                    </div>
	  <s:form action="bomkitdashboardProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
									<div class="col-lg-12">
									<s:hidden name="fromdate"></s:hidden>
									<s:hidden name="todate"></s:hidden>
									<s:hidden name="isfromcathlab"></s:hidden>
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" />
											Records
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
   <s:form action="transferproductdataCatalogue" theme="simple" id="transferproductform"> 
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
            <th style="width: 15%;">Exp Date</th>
            <th style="width: 10%;">Location</th>
            <th style="width: 9%;">Current Stock</th>
            <th style="width: 13%;">Transfer Location</th>
            <th style="width: 10%;">Transfer Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="carttbody">
           
          </tbody>
          </table>
          <input type="hidden" name="tcount" id="tcount">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="comment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="divbtn">
       <button type="button" class="btn btn-primary" onclick="confirmTransfer()">Transfer</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>




   <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="editcat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Update Stock</h4>
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
                    
                        <form class="form-horizontal" id="myform" action="updateprodstockProduct" method="post">
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
                            
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding: 0px 10px 0px 10px;">
                            <div class="col-lg-3 col-md-3 col-xs-3 hidden">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Category</label>
										<s:select list="categoryList" listKey="id" onchange="getsubCategories(this.value)" listValue="name" headerKey="0" headerValue="Select Category" name="category_id"  id="category_id" cssClass="form-control" cssStyle="width:95%;" />
	                            	</div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                            		<div class="form-group" >
		                                <label for="inputEmail3" class="control-label">Sub Category</label>
		                                 <div id="subdiv">
		                                	<s:select theme="simple" name="subcategory_id" id="subcategory_id" list="subcategoryList" cssClass="form-control" listKey="id"  cssStyle="width:95%;" listValue="name" headerKey="0" headerValue="Select Sub Category"   ></s:select> 
		                                 </div>
	                            	</div>
                            	</div>
                            	<div class="col-lg-12 col-md-12 col-xs-12">
                            		<div class="form-group">
	                                <label for="inputEmail3" class="control-label">Product Name</label>
	                                <input type="text" readonly="readonly" class="form-control" id="product_name" name="product_name" style="text-transform: uppercase;">
	                            </div>
	                             <div class="form-group">
		                                <label for="inputEmail3" class="control-label">PACK</label>
		                                <input class="form-control" type="number" onchange="calSalePerUnitCatalogue()"  id="pack" name="pack"  />
		                            </div>
	                             <div class="form-group">
		                                <label for="inputEmail3" class="control-label">MRP</label>
		                                <input class="form-control" type="number" onchange="calSalePerUnitCatalogue()"  id="mrp" name="mrp"  />
		                            </div>
		                            <div class="form-group">
		                                <label for="inputEmail3" class="control-label">Sale Price</label>
		                                <input class="form-control" type="text" readonly="readonly"  id="sale_price" name="sale_price"  />
		                            </div>
	                            <div class="form-group">
		                                <label for="inputEmail3" class="control-label">Batch Number</label>
		                                <input class="form-control" type="text"  id="batch_no" name="batch_no"  />
		                            </div>
		                            <div class="form-group">
		                                <label for="inputEmail3" class="control-label">Expiry Date</label>
		                                <input class="form-control" type="text"  id="expiry_date" name="expiry_date"  />
		                            </div>
	                            <div class="form-group">
		                                <label for="inputEmail3" class="control-label">Stock</label>
		                                <input class="form-control" type="number"  id="stock" name="stock"  style="background-color: cornsilk;"/>
		                            </div>
		                            <div class="form-group" >
		                                <label for="inputEmail3" class="control-label">Shelf</label>
		                                <s:select theme="simple" name="shelf" id="shelfid" list="shelflist" cssClass="form-control" listKey="name"  cssStyle="width:95%;" listValue="name" headerKey="" headerValue="Select Shelf"   ></s:select> 
		                            </div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3 hidden">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Generic Name</label>
		                                <input type="text" readonly="readonly" name="genericname" id="genericname"  class="form-control"  style="text-transform: uppercase;width:95%;">
		                            </div>
                            	</div>
                            <div class="col-lg-2 col-md-2 col-xs-3">
                            		
                            	</div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="submitForm1()" class="btn btn-primary">Update</button>
                </div>
            </div>
        </div>
    </div>

     


<!--Return Cart Modal -->
<div id="returncart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="returnproductdataCatalogue" theme="simple" id="returnproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Return Stock</h4>
      </div>
      <div class="modal-body">
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tabletreturncount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 10%;">Location</th>
            <th style="width: 9%;">Current Stock</th>
            <th style="width: 13%;">Return Location</th>
            <th style="width: 10%;">Return Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="returncarttbody">
           
          </tbody>
          </table>
          <input type="hidden" name="returncount" id="returncount">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="returncomment" name="returncomment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="returnbtndiv">
     	 <button type="submit" class="btn btn-primary" onclick="return confirmReturn()">Return</button> 
      </div>
      
      </s:form>
    </div>

  </div>
</div>


<!-- Cart Modal -->
<div id="issuecart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="transferissueproductdataCatalogue" theme="simple" id="issueproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <s:if test="isfromcathlab==1">
        	<h4 class="modal-title">Issue Bom Kit</h4>
      	</s:if>
      	<s:else>
      		<h4 class="modal-title">Item Issue To Patient/User</h4>
      	</s:else>
      </div>
      <div class="modal-body">
         <div class="form-group">
         <s:hidden name="isfromcathlab"></s:hidden>
         <s:hidden name="isfrombimkit" id="isfrombimkit"></s:hidden>
         	<s:if test="isfromcathlab==1">
         		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
         			
          			<input type='hidden' class='form-control' name="issuepatientid" value="0" id='issuepatientid'>
         			<input type='text' readonly="readonly" placeholder="Select Patient/User" class='form-control' id='issuepatientname' onclick='getpatientpopForIssue()'>
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="bomkittemplate">
          			<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select" list="cathtemplist" onchange="setcathTempValue(this.value)"  id="cathtempid" name="cathtempid" />
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		
          		</div>
         	</s:if>
         	<s:else>
         		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          			<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select Department" list="issuedeptlist"  id="hisdepartmentfilter" name="hisdepartmentfilter" />
          		</div>
         		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          			<s:select cssClass="form-control chosen-select" list="#{'0':'Patient', '1':'YUVICARE User'}" onchange="setpatientuserlist(this.value)" id="hisuserfilter" name="hisuserfilter" />
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="issuechangediv">
          			<!-- <label>Patient:</label> -->
          			<input type='hidden' class='form-control' name="issuepatientid" value="0" id='issuepatientid'>
         			<input type='text' readonly="readonly" placeholder="Select Patient/User" class='form-control' id='issuepatientname' onclick='getpatientpopForIssue()'>
          		</div>
           		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="issueprocediv">
           			<select id="issueproceid" name="issueproceid" class="from-control chosen-select">
           				<option value="0">Select Procedure</option>
           			</select>
          		</div>
         	</s:else>
         
        </div>
      	<div class="clearfix" style="height: 15px;"></div>
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tableissuecount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 10%;">Location</th>
            <s:if test="isfromcathlab==1">
            </s:if>
            <s:else>
            	<th style="width: 9%;">Current Stock</th>
            </s:else>
           <!--  <th style="width: 13%;">Patient Name</th> -->
            <th style="width: 10%;">Transfer Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="issuebody">
           
          </tbody>
          </table>
          <input type="hidden" name="issuecount" id="issuecount">
        </div>
        
        <div class="form-group">
			<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="issuecomment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="issuedivbtn">
      	 <s:if test="isfromcathlab==1">
      	 	<button type="button" class="btn btn-primary" onclick="confirmIssueCathLab()">Submit</button>
         </s:if>
         <s:else>
           <button type="button" class="btn btn-primary" onclick="confirmIssue()">Transfer</button>
         </s:else>
       
      </div>
      
      </s:form>
    </div>

  </div>
</div>

<!-- Cart Modal -->
<div id="cathtempcart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="savecathlabtemplateCatalogue" theme="simple" id="cathtempproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Create Cathlab Template</h4>
      </div>
      <div class="modal-body">
         <div class="form-group">
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select Procedure" list="procedurelist"  id="cathtempprocedure" name="cathtempprocedure" />
          	</div>
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		<input type='text'  placeholder="Template name" class='form-control' id='cathtemplatename' name="cathtemplatename">
          	</div>
          	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
          		
          	</div>
           	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
           		
          	</div>
           
          	
      	</div>
      	<div class="clearfix" style="height: 15px;"></div>
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tablecathcount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 10%;">Location</th>
           <!--  <th style="width: 9%;">Current Stock</th> -->
           <!--  <th style="width: 13%;">Patient Name</th> -->
            <th style="width: 10%;">Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="cathempbody">
           
          </tbody>
          </table>
          <input type="hidden" name="cathtempcount" id="cathtempcount">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="cathtempcomment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="cathtempdivbtn">
       <button type="button" class="btn btn-primary" onclick="confirmCathTemp()">Create Template</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>

<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%-- <%@ include file="/ipd/pages/ipdPatientList.jsp"%> --%>
        <%@ include file="/inventory/pages/patientlist.jsp"%>
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Add New Patient -->
	<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">			
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
				</div>
				<div class="modal-body addnewpat">
					<%-- <jsp:include page="/diarymanagement/pages/addPatientPage.jsp"/> --%>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="return savePatient()">Save</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" >Close</button>
				</div>
			</div>
		</div>
	</div>
<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>
</body>
</html>
