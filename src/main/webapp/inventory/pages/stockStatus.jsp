<!doctype html>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<html class="no-js" lang="">


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

        .contain {
  width: 500px;
  margin: 0 auto;
  position: relative;
  padding: 20px;
}
.notificationicon {
 float:right;
}


.topheadbaxck {
    background-color: #f5f5f5;
   padding: 10px 0px 10px 0px;
}

.notificationicon.on i {
  font-size: 18px;
}
.notifications {
  max-width: 300px;
  width: 300px;
  background: #fff;
  border: 1px solid #ccc;
  padding: 0px;
  box-shadow: 0px 0px 20px #666;
  position: absolute;
  opacity: 1;
  top: 25px;
  transition: .2s;
  opacity: 0;
  right: 5px;
}
.chosen-container-single .chosen-single {
    background-color: rgba(245, 245, 245, 0.46);
}
.notifications.open {
  opacity: 1;
  transition: .2s;
}
.notifications li {
 
  list-style-type: none;
}
.notifications li.titlebar {
  border-bottom: 1px solid #ccc;
  color: #666;
  font-size: 12px;
  cursor: inherit;
  padding: 2px 5px;
}
.notifications li.titlebar:hover {
  background: #fff;
}
.notifications li.titlebar .settings {
  float: right;
  cursor: pointer;
}
.notifications li.seeall {
  text-align: center;
  font-size: 12px;
  min-height: 30px;
  text-transform: uppercase;
  position: relative;
  border-top: 1px solid #ccc;
}
.notifications li.seeall a {
  background: #f5f5f5;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  color: #007fff;
  padding-top: 8px;
}
.notifications .notifbox {
  max-height: 300px;
  overflow: auto;
}
.notifications .notifbox li {
  color: #666;
}
.notifications .notifbox li a {
  color: #666;
}
.notifications .notifbox li.unread {
  background: #e5f2ff;
}
.notifications .notifbox li.notif {
  min-height: 40px;
  border-bottom: 1px solid #ccc;
  position: relative;
}
.notifications .notifbox li.notif:last-child {
  border-bottom: none;
}
.notifications .notifbox li.notif .imageblock {
  width: 70px;
  position: absolute;
  left: 5px;
}
.notifications .notifbox li.notif .imageblock .notifimage {
  height: 60px;
}
.notifications .notifbox li.notif .messageblock {
    padding: 8px;
}
.notifications .notifbox li.notif .messageblock .message a {
  color: #007fff;
}
.notifications .notifbox li.notif .messageblock .messageaction {
  min-height: 30px;
  margin-bottom: 5px;
}
.notifications .notifbox li.notif .messageblock .messageaction .button {
  font-weight: normal;
  text-transform: uppercase;
}
.notifications .notifbox li.notif .messageblock .messageaction .button.success,
.notifications .notifbox li.notif .messageblock .messageaction .button.alert {
  color: #fff;
}
.notifications .notifbox li.notif .messageblock .messageinfo {
  font-size: 13px;
  color: #999;
}
.badge {
  position: absolute;
  top: 30px;
  right: -20px;
}    
.title {
    display: inline;
    float: none;
    margin-left: 17px;
   color: #fff;
    font-size: 12px;
}
.btnbackset{
 background-color: #efefef;
 padding: 6px;
}
     .count {
    position: absolute;
    top: -2px;
    right: 11px;
    text-align: center;
    font-size: 9px;
    padding: 2px 3px;
    line-height: .9;
     background-color: #dd4b39 !important;
     border-radius: 50px;
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
    
    <SCRIPT type="text/javascript">
    
      $(document).ready(function(){
    	  /* document.addEventListener("contextmenu", function(e){
				e.preventDefault();
			}, false);  */
			   
			   $("#fromdate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

			   });
			
			   
         });
    
        function printReport() {
				
				  $("#tablesort").table2excel({
					exclude: ".noExl",
					name: "Stock Status",
					filename: "stockStatusReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }
        function printStockReportExcel() {
            $(".tablestock").table2excel({
   					exclude: ".noExl",
   					name: "Catalogue",
   					filename: "catalogueList",
   					fileext: ".xls",
   					exclude_img: true,
   					exclude_links: true,
   					exclude_inputs: true
    			});
             }
    
    </SCRIPT>
    <SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
     <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>





<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%>


<body id="his" class="appWrapper sidebar-xs-forced">

        <section id="">

            <div class="page-sidebar-xs-layout">

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
								
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                    <div class="col-lg-12">
                    	<div class="col-md-8" style="padding:0px;">
                           <div class="form-inline">
                           <s:form action="Catalogue" theme="simple">
	                            <div class="form-group">
	                           		<span class="text-uppercase"><b>CATALOGUE</b> &nbsp; - &nbsp;</span>
	                           </div>
                           		<div class="form-group">
								  	<s:textfield cssClass="form-control" id="searchtext" name="searchtext" placeholder="Search Product"/>
								</div>
								<div class="form-group">
								  	<s:select list="categoryList" listKey="id" onchange="getsubCategoriesCatalogue(this.value)" listValue="name" headerKey="0" headerValue="select category" cssClass="form-control chosen-select" name="category_id" ></s:select>
								</div>
								<div class="form-group" id="sublist">
								  	<s:select list="subcategoryList" listKey="id" listValue="name" headerKey="0" headerValue="select sub category" cssClass="form-control chosen-select" name="subcategory_id" ></s:select>
								</div>
								<div class="form-group">
								  	<button type="submit" class="btn btn-primary">Go</button>
								  	<a type="button"  title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
								</div>
								
                           	</s:form>
                           		
                           </div>
                        </div>
                       
                        <div class="col-md-4 text-right" style="padding:0px;">
                        	<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addproduct">Add Product</button> -->
                        	<a class="btn btn-primary" href="#" onclick="callMfgMaster()">Add MFG</a>
                        	<a class="btn btn-primary" href="#" onclick="callGenericNameMaster()">Add Generic Name</a>
                        	<button type="button" class="btn btn-primary" onclick="addnewcatalogueproduct()">Add Product</button>
                        </div>
                    </div>
                    	
                    
                        
                        <%-- <div class="col-md-2 text-right">
                               
                              <div class="hidden">
        <a href="#" class="notificationicon on" title="Expiry Date Notification">
         <i class="fa fa-bell-o" aria-hidden="true"></i>
         <span class="label count label-danger"><s:property value="count"/></span>
        </a>
          <ul id="notificationMenu" class="notifications">
             
            <s:iterator value="allWillExpireProduct">
            <div class="notifbox">
              <li class=" notif">
                <a href="#">
                  <div class="messageblock">
                    <div class="">Product <strong><s:property value="product_name"/></strong> will expire on.
                    </div>
                    <div class="messageinfo">
                      <span style="color: chocolate;"><s:property value="expiry_date"/></span> |
                      <a style="color: #6699CC;" href="poProcurement">Purchase Now</a>
                    </div>
                  </div>
                </a>
              </li>
            </div>
            </s:iterator> 
            <li class="notif unread btnbackset">
                <a href="#">
                  <div class="messageblock">
                    <div class="messageaction">
                      <a style="float: none;" class="btn btn-danger notificationicon on">Close</a>
                    </div>
                  </div>
                </a>
              </li>
          </ul>
        </div>
       <span class="text-uppercase"><b>Catalogue</b></span>
                           
                           
                        </div> --%>
                    </div> 
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <table class="table my-table table-striped xlstable table-bordered tablestock" id="example" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 3%;">Sr</th>
                                    <th style="width: 5%;">Id</th>
                                    <th style="width: 12%;">Catagory</th>
                                    <th style="width: 12%;">Sub Catagory</th>
                                    <th style="width: 10%;">Product Type</th>
                                    <th style="width: 15%;">Product Name</th>
                                    <th style="width: 10%;">Generic Name</th>
                                    <th style="width: 7%;">Product Code</th>
                                    <th style="width: 4%;">Pack</th>
                                    <th style="width: 4%;">Mfg</th>
                                    <th style="width: 8%;text-align:right;">MRP</th>
                                    <th style="width: 8%;text-align:right;">Pur.Price</th>
                                    <th style="width: 8%;text-align:right;">Sale Price</th>
                                    <th style="width: 4%;text-align:right;">GST</th>
                                    <th style="width: 4%;text-align:right;">Min Qty</th>
                                    <th style="width: 4%;text-align:right;">Max Qty</th>
                                    <th style="width: 4%;text-align:right;">Limit Qty</th>
                                    <th>Edit</th>
                                    <!-- <th></th> -->
                                    <!-- <th style="width: 6%;">HSN No</th>
                                    <th style="width: 15%;">Supplier Name</th>
                                    
                                    <th style="width: 20%;">Location</th>
                                    <th style="width: 8%;">Product Type</th>
                                    <th class="hidden" style="width: 8%;">Purchase Date</th>
                                    <th style="width: 4%;">Pack</th>
                                    
                                    <th style="">GST</th>
                                    <th style="width: 7%;">Expiry Date</th> -->
                                    
                                    <!--<th style="width: 8%;">Delivery Time</th>
                                    -->
                                   <!--  <th>Stock</th>
                                    <th>Shelf</th> -->
                                    <!--<th>Edit</th>
                                    <th>Delete</th>
                                -->
                                </tr>
                                
                            </thead>
                            <tbody>
                              <%int i=0; %>
                              <s:iterator value="productList">
                                <input type="hidden" id="prodid" value="<s:property value="id"/>"/> 
                                <s:if test="stock<10">
                                <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="category"/></td>
                                    <td><s:property value="subcategory"/></td>
                                    <td><s:property value="shedule"/></td>
                                    <td><s:property value="product_name"/></td>
                                    <td><s:property value="genericname"/></td>
                                    <td><s:property value="pro_code"/></td>
                                    <td><s:property value="pack"/></td>
                                    <td><s:property value="mfg" /></td>
                                    <td style="text-align:right;">Rs.<s:property value="mrp"/></td>
                                    <td style="text-align:right;">Rs.<s:property value="purchase_price"/></td>
                                    <td style="text-align:right;">Rs.<s:property value="sale_price"/></td>
                                    <td style="text-align:right;"><s:property value="vat"/></td>
                                    <td style="text-align:right;"><s:property value="minorder"/></td>
                                    <td style="text-align:right;"><s:property value="maxorder"/></td>
                                    <td><input onclick="showlimitqty(<s:property value="id"/>)" type='button' value='Add Limit' class='btn btn-primary' ></td>
                                    
									
                                    <%if(loginInfo.getJobTitle().equals("Admin")){ %>
                                    	<td><a href="#" onclick="editProd(<s:property value="id"/>)"><i class="fa fa-pencil"></i></a></td>
                                    <%}else{ %>
	                                    <s:if test="edit_catalogue==1">
	 										<td><a href="#" onclick="editProd(<s:property value="id"/>)"><i class="fa fa-pencil"></i></a></td>
	                                    </s:if>
	                                    <s:else>
	                                     <td></td>
	                                    </s:else>
                                    <%} %>
                                    
                                    <!-- <td><a href="#"><i class="fa fa-times text-danger"></i></a></td> -->
                                    <%-- <td><s:property value="hsnno" /></td>
                                    <td><s:property value="vendor"/></td>
                                    
                                    <td><s:property value="location"/></td>
                                    <td><s:property value="shedule"/></td>
                                    <td class="hidden"><s:property value="lastmodified"/></td>
                                    <td><s:property value="pack"/></td>
                                    
                                    <td><s:property value="vat"/>%</td>
                                    <td><s:property value="expiry_date"/></td> --%>
                                    
                                    <!--<td><s:property value="min_delivery_time"/></td>
                                    -->
                                    <%-- <td><s:property value="stock"/></td>
                                    <td style="text-transform: uppercase;"><s:property value="shelf"/></td> --%>
                                    <!--<td><a href="#" onclick="editstock(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="deletestockProduct?selectedid=<s:property value="id"/>" onclick="return cnfmdelete()"><i class="fa fa-trash text-danger"></i></a></td>
                                --></tr>
                                </s:if>
                                <s:else>
                                   <tr>
                                    <td><%=(++i) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="category"/></td>
                                    <td><s:property value="subcategory"/></td>
                                     <td> <s:property value="product_name"/></td>
                                    <td><s:property value="mfg" /></td>
                                    <td style="text-align:right;">Rs.<s:property value="mrp"/></td>
                                    <td style="text-align:right;">Rs.<s:property value="purchase_price"/></td>
                                    <td style="text-align:right;">Rs.<s:property value="sale_price"/></td>
                                    <td style="text-align:right;"><s:property value="vat"/></td>
                                    <%if(loginInfo.getJobTitle().equals("Admin")){ %>
                                    	<td><a href="#" onclick="editProd(<s:property value="id"/>)"><i class="fa fa-pencil"></i></a></td>
                                    <%}else{ %>
	                                    <s:if test="edit_catalogue==1">
	 										<td><a href="#" onclick="editProd(<s:property value="id"/>)"><i class="fa fa-pencil"></i></a></td>
	                                    </s:if>
	                                    <s:else>
	                                     <td></td>
	                                    </s:else>
                                    <%} %>
                                    <!-- <td><a href="#"><i class="fa fa-times  text-danger"></i></a></td> -->
                                    
                                   <%--  <td><s:property value="hsnno" /></td>
                                    <td><s:property value="vendor"/></td>
                                   
                                    <td><s:property value="location"/></td>
                                    <td><s:property value="shedule"/></td>
                                    <td class="hidden"><s:property value="lastmodified"/></td>
                                     <td><s:property value="pack"/></td>
                                  
                                    <td><s:property value="vat"/>%</td>
                                    <td><s:property value="expiry_date"/></td>
                                  
                                    <td><s:property value="stock"/></td>
                                    <td style="text-transform: uppercase;"><s:property value="shelf"/></td> --%>
                                    <!--<td><a href="#" onclick="editstock(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="deletestockProduct?selectedid=<s:property value="id"/>" onclick="return cnfmdelete()"><i class="fa fa-trash text-danger"></i></a></td>
                                --></tr>
                                
                                </s:else>
                                
                               </s:iterator>
                            </tbody>
                        </table><br />
                    </div>
                </div>

		<s:form action="Catalogue" name="paginationForm" id="paginationForm" theme="simple">
							    	<s:hidden name="category_id"></s:hidden>
							    	<s:hidden name="subcategory_id"></s:hidden>
							    	<%-- <s:hidden name="searchtext"></s:hidden> --%>
									<div class="col-lg-12">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Showing <label class="text-info">(<s:property
												value="pagerecords" /> of <s:property value="totalRecords" />
											Records)
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
								 <s:hidden name="searchtext"></s:hidden>
							</s:form> 

            </div>

        </section>
        <!--/ CONTENT -->




 <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="editcat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="width: 78%">
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
		                                <label for="inputEmail3" class="control-label">Category</label>
										<s:select list="categoryList" listKey="id" onchange="getsubCategories(this.value)" listValue="name" headerKey="0" headerValue="Select Category" name="category_id"  id="category_id" cssClass="form-control" cssStyle="width:95%;" />
	                            	</div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group" >
		                                <label for="inputEmail3" class="control-label">Sub Category</label>
		                                 <div id="subdiv">
		                                	<s:select theme="simple" name="subcategory_id" id="subcategory_id" list="subcategoryList" cssClass="form-control" listKey="id"  cssStyle="width:95%;" listValue="name" headerKey="0" headerValue="Select Sub Category"   ></s:select> 
		                                 </div>
	                            	</div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
	                                <label for="inputEmail3" class="control-label">Product Name</label>
	                                <%if(loginInfo.isEdit_catalogue_name() || loginInfo.getSuperadminid()==1){ %>
	                                	<input type="text" class="form-control"  id="product_name" name="product_name" style="text-transform: uppercase;width:95%;">
	                                <%}else{ %>
	                                	<input type="text" class="form-control" readonly="readonly" id="product_name" name="product_name" style="text-transform: uppercase;width:95%;">
	                                <%} %>
	                                
	                            </div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Generic Name</label>
		                                <%if(loginInfo.isAuto_generic_name()){ %>
											<s:select list="genericnamelist" theme="simple"  cssClass="form-control" id="genericname" name="genericname" listKey="name" listValue="name" headerKey="" headerValue="Generic Name" style="background-color: rgba(245, 245, 245, 0.46);" ></s:select>
										<%}else{ %>
											<input type="text" name="genericname" id="genericname"  class="form-control"  style="text-transform: uppercase;width:95%;">
										<%} %>
		                                
		                            </div>
                            	</div>
                            	<div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Product Code</label>
		                                <input type="text" name="productcode" readonly="readonly" id="productcode"  class="form-control"  style="text-transform: uppercase;width:95%;">
		                            </div>
                            	</div>
                            <div class="col-lg-2 col-md-2 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">HSN Code</label>
		                                <input class="form-control" type="text" maxlength="8" pattern="([0-9]|[0-9]|[0-9])" id="hsnno" name="hsnno" style="text-transform: uppercase;width:95%;"/>
		                            </div>
                            	</div>
                            	<div class="col-lg-2 col-md-2 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">GST</label>
										<select name="gst" id="gst" class="form-control" style="width:95%;">
										    <option value="GST" selected="selected">Select GST</option>
										    <option value="0">0%</option>
										    <option value="5">5%</option>
										    <option value="12">12%</option>
										    <option value="18">18%</option>
										    <option value="28">28%</option>
										</select>                     
	                            	</div>
                            	</div>
                            	
                            	
                            	<div class="col-lg-2 col-md-2 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Product Type</label>
		                                <s:select list="#{'Regular':'Regular','Narotics':'Narotics','H1':'H1','High Risk Medicine':'High Risk Medicine','Vaccination':'Vaccination'}" cssClass="form-control" cssStyle="width:95%;"  name="medicine_shedule" id="medicine_shedule"></s:select>
	                            	</div>
                            	</div>
                            	
                            	<div class="col-lg-2 col-md-2 col-xs-3">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">MRP</label>
                            		<input type="number" class="form-control" id="mrp" name="mrp" onkeyup="calSalePerUnitCatalogue()"  style="width:95%;">
                               	</div>
                             </div>
                             <div class="col-lg-2 col-md-2 col-xs-3">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">Pur.Price</label>
                            		<input type="number" class="form-control" id="purchase_price" name="purchase_price" style="width:95%;">
                               </div>
                             </div>
                             <div class="col-lg-2 col-md-2 col-xs-3">
                             	<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Sale.Price</label>
		                                <input type="number" class="form-control" id="sale_price" name="sale_price" style="width:95%;" readonly="readonly" />
		                            </div>
                             </div>
                            	<%-- <div class="col-lg-3 col-md-3 col-xs-3">
                            		<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Select Location</label>
										<s:select theme="simple" readonly="readonly"  name="location" id="location" list="locationListPharmacy" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Select Location"   ></s:select>			                                
	                            	</div>
                            	</div> --%>
                            </div>
                           
                             <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="margin-top: 10px;">
                             
                             <div class="col-lg-2 col-md-2 col-xs-3">
                             	<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Mfg</label>
		                                <%if(loginInfo.isAuto_generic_name()){ %>
											<s:select list="mfglist" theme="simple" cssClass="form-control" id="mfg" name="mfg" listKey="name" listValue="name" headerKey="" headerValue="MFG" style="background-color: rgba(245, 245, 245, 0.46);width:95%;" ></s:select>
										<%}else{ %>
											<input type="text" class="form-control" id="mfg" name="mfg" style="width:95%;" />
										<%} %>
		                                
		                            </div>
                             </div>
                             <div class="col-lg-2 col-md-2 col-xs-3">
                             		<div class="form-group hidden">
		                                <label for="inputEmail3" class="control-label">Select Shelf</label>
		                                <s:select list="cellList" cssClass="form-control" listKey="name" listValue="name" name="shelf" id="shelf" style="width:95%;"></s:select>
		                            </div>
		                            <div class="form-group">
		                                <label for="inputEmail3" class="control-label">Min Qty</label>
		                                <input type="number" class="form-control" id="minorder" name="minorder" style="width:95%;" />
		                            </div>
		                             <div class="form-group">
		                                <label for="inputEmail3" class="control-label">Max Qty</label>
		                                <input type="number" class="form-control" id="maxorder" name="maxorder" style="width:95%;" />
		                            </div>
		                            <div class="form-group">
		                                <%if(loginInfo.isLmh()){ %>
		                            		<label for="inputEmail3" class="control-label">UOM</label>
											<input type="hidden" class="form-control" id="pack" name="pack"  onkeyup="calSalePerUnitCatalogue()" style="width:95%;" />
											<s:select cssStyle="width:90%;" list="unitOfMeseurementList" theme="simple"  cssClass="form-control chosen-select" id="uom" name="uom" headerKey="" headerValue="Select" style="background-color: rgba(245, 245, 245, 0.46);" ></s:select>
										<%}else{ %>
											<label for="inputEmail3" class="control-label">Pack</label>
											<input type="number" class="form-control" id="pack" name="pack"  onkeyup="calSalePerUnitCatalogue()" style="width:95%;" />
											<input type="hidden" class="form-control" value="" id="uom" name="uom" placeholder="UOM" style="background-color: rgba(245, 245, 245, 0.46);">
										<%} %>
		                            </div>
                             </div>
                             <div class="col-lg-8 col-md-8 col-xs-6">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">Description</label>
                            		<textarea rows="3" cols="50" id="description" name="description" class="form-control" style="width:100%;"></textarea>
                               	</div>
                               	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">Update Remark</label>
                            		<textarea rows="3" cols="50" id="comment" name="comment"  class="form-control" style="background-color: rgba(245, 245, 220, 0.36);"></textarea>
                               	</div>
                             </div>
                              
                             <div class="col-lg-3 col-md-3 col-xs-3 hidden">
                             	<div class="form-group">
		                                <label for="inputEmail3" class="control-label">Stock</label>
		                                <input type="number" class="form-control" id="stock" name="stock"  style="background-color: lightgoldenrodyellow;"/>
		                            </div>
                             </div>
                            </div>
                            
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 hidden">
                            
                             <div class="col-lg-3 col-md-3 col-xs-3 hidden">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">Expiry Date</label>
                            		<input type="text" class="form-control" readonly="readonly" id="expiry_date" name="expiry_date"  style="width:95%;"/>
                               	</div>
                             </div>
                             <div class="col-lg-3 col-md-3 col-xs-3 hidden">
                             	<div class="form-group">
                            		<label for="inputEmail3" class="control-label">Batch Number</label>
                            		<input type="text" class="form-control" readonly="readonly" id="batch_no" name="batch_no"  style="width:95%;"/>
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


<div id="showsitting" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="modal-title">Sitting of <label id="patientnm"></label></h4>
        </div>
  <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      	
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"> 
      	 <%if(loginInfo.getUserType()==2 || loginInfo.getPharmacyUserType()==2 ) {%>
				<label>Select Location</label><label class="text-danger">*</label><s:select list="locationListPharmacy"  theme="simple" name="location" id="loc" cssStyle="width:30%" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
				  </s:select> 
	   <%}else{%>  
				<s:select list="locationListPharmacy" id="dropdown" onchange="setlocPharmacy(this.value)" theme="simple" name="location" cssStyle="width:30%" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
				  </s:select>
      	  <%} %>
      	 </div>    
          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                <div class="form-group" id="sitting_num" >
        				 <label>Sitting No</label><label class="text-danger">*</label>
				              <input type="number" id="limit" name="limit"
				                Class="showToolTip form-control" data-toggle="tooltip"
					            title="Enter Limit" placeholder="Enter Limit"/>
        	    </div>
        	 </div>
         </div>
     </div>
     <div class="modal-footer">
     	<button type="button" class="btn btn-primary"
							onclick="addlimitqty()" style="margin-top: 15px;">Save</button>
       </div>
     </div>
   </div>
</div>  
    
    <!--Add Product Modal -->
<div id="addproduct" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg" style="width: 99%;">

   <s:form action="saveproductsCatalogue" theme="simple" id="savaprodform">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add Product</h4>
      </div>
      <div class="modal-body">
       <div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
				<%-- <table class="table my-table xlstable table-bordered" style="width: 100%;" id="mytable">
			<thead>
				<tr>
					<th style="width: 40%;">Supplier Name</th>
					<th style="width: 40%;">Product Name</th>
					<th style="width: 20%;"> Quantity</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td id="listvendor0">
						<s:select list="vendorList" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="Select Supplier"
						  name="products[0].vendor" id="vendor0" onchange="getproduct(this.value,0)"  />
					</td>
					<td id="listproduct0">
						<select name="" id="product0" class="form-control chosen-select">
							    <option value="0">Select Product</option>
					    </select>
					</td>
					<td><input type="text" class="form-control" placeholder="qty" name="products[0].quantity" id="quantity0" /></td>
					<td></td>
				</tr>

			</tbody>

		</table> --%>
		
		<%-- <%if(loginInfo.isAuto_generic_name()){ %>
			<input type="hidden" id="auto_generic_name" value="1">
		<%}else{ %>
			<input type="hidden" id="auto_generic_name" value="0">
		<%} %> --%>
		<table class="table my-table xlstable table-bordered" style="width: 100%;" id="mytable">
			<thead>
				<tr>
					<th style="width: 8%;">Category</th>
					<th style="width: 8%;">Sub-Category</th>
					<th style="width: 8%;">Product Type</th>
					<th style="width: 14%;">Product Name</th>
					<th style="width: 8%;">GEN NAME</th>
					<%if(loginInfo.isKalmegha()){ %>
					<th class="hidden" style="width: 5%;">Product Code</th>
					<%}else{ %>
					<th style="width: 5%;">Product Code</th>
					<%} %>
					<%if(loginInfo.isLmh() || loginInfo.isBams1()){ %>
						<th style="width: 4%;">UOM</th>
					<%}else{ %>
						<th style="width: 4%;">Pack</th>
					<%} %>
					
					
					<th style="width: 6%;">MRP</th>
					<th style="width: 6%;">Pur.Rate</th>
					<th style="width: 6%;">Sale Price</th>
					<th style="width: 6%;">MFG</th>
					<th style="width: 6%;">GST</th>
					<th style="width: 6%;">HSN NO</th>
					<th style="width: 0%;" class="hidden">Description</th>
					<th style="width: 5%;" class="">Min Qty</th>
					<th style="width: 5%;" class="">Max Qty</th>
					<!-- <th style="width: 2%;"></th> -->
				</tr>
			</thead>
			<tbody id="tbodyid">
				<tr>
					<input type="hidden" class="akash" value="0" class="form-control" />
					<td>
						<s:select list="categoryList" theme="simple" onchange="getsubcatagory(this.value,0)" cssClass="form-control chosen-select" id="category_id0" name="productdata[0].category_id" listKey="id" listValue="name" headerKey="0" headerValue="Select Category" ></s:select>
					</td>
					<td id="subcatdiv0">
						<select  name="productdata[0].subcategory_id" id="subcategory_id0" class="form-control chosen-select"> 
							<option value="0">Select</option>
						</select>
					</td>
					<td>	
						<select id="prodtype0" name="productdata[0].prodtype" class="form-control chosen-select"> 
							<option value="0">Select</option>
							<option value="Regular">Regular</option>
							<option value="H1">H1</option>
							<option value="Narcotics">Narcotics</option>
							<option value="High Risk Medicine">High Risk Medicine</option>
							<option value="Vaccination">Vaccination</option>
						</select>
					</td>
					<td><input type="text" class="form-control" id="product_name0" name="productdata[0].product_name" onchange="chkNameExist(this)" placeholder="Product Name" style="background-color: rgba(245, 245, 245, 0.46);"></td>
					<td>
						<%if(loginInfo.isAuto_generic_name()){ %>
							<s:select list="genericnamelist" theme="simple"  cssClass="form-control chosen-select" id="generic_name0" name="productdata[0].generic_name" listKey="name" listValue="name" headerKey="" headerValue="Generic Name" style="background-color: rgba(245, 245, 245, 0.46);" ></s:select>
						<%}else{ %>
							<input type="text" class="form-control" id="generic_name0" name="productdata[0].generic_name" placeholder="Generic Name" style="background-color: rgba(245, 245, 245, 0.46);">
						<%} %>
					</td>
					<%if(loginInfo.isKalmegha()){ %>
					      <td class="hidden"><input type="text" class="form-control" id="pro_code0" onchange="checkProductCodeExistNew(0,this.value)" name="productdata[0].pro_code" placeholder="Product Code" style="background-color: rgba(245, 245, 245, 0.46);"></td>
					<%}else{ %>
					     <td><input type="text" class="form-control" id="pro_code0" onchange="checkProductCodeExistNew(0,this.value)" name="productdata[0].pro_code" placeholder="Product Code" style="background-color: rgba(245, 245, 245, 0.46);"></td>
					<%} %>
					<td>
						<%if(loginInfo.isLmh() || loginInfo.isBams1()){ %>
							<input type="hidden" class="form-control" value="1" id="pack0" onchange="getcalsaleprice(0)" name="productdata[0].pack" placeholder="Pack" style="background-color: rgba(245, 245, 245, 0.46);">
							<s:select list="unitOfMeseurementList" theme="simple"  cssClass="form-control chosen-select" id="uom0" name="productdata[0].uom" headerKey="" headerValue="Select" style="background-color: rgba(245, 245, 245, 0.46);" ></s:select>
						<%}else{ %>
							<input type="number" class="form-control" id="pack0" onchange="getcalsaleprice(0)" name="productdata[0].pack" placeholder="Pack" style="background-color: rgba(245, 245, 245, 0.46);">
							<input type="hidden" class="form-control" value="" id="uom0" name="productdata[0].uom" placeholder="UOM" style="background-color: rgba(245, 245, 245, 0.46);">
						<%} %>
					</td>
					<td><input type="number" class="form-control" id="mrp0" onchange="getcalsaleprice(0)" name="productdata[0].mrp" placeholder="MRP" style="background-color: rgba(245, 245, 245, 0.46);"></td>
					<td><input type="number" class="form-control" id="purchase_price0" name="productdata[0].purchase_price" placeholder="Rate" style="background-color: rgba(245, 245, 245, 0.46);"></td>
					<td><input type="number" class="form-control" id="sale_price0" name="productdata[0].sale_price" placeholder="Sale Price" style="background-color: rgba(245, 245, 245, 0.46);"></td>
					
					<td>
						<%if(loginInfo.isAuto_generic_name()){ %>
							<s:select list="mfglist" theme="simple"  cssClass="form-control chosen-select" id="mfg0" name="productdata[0].mfg" listKey="name" listValue="name" headerKey="" headerValue="MFG" style="background-color: rgba(245, 245, 245, 0.46);" ></s:select>
						<%}else{ %>
							<input type="text" class="form-control" id="mfg0" name="productdata[0].mfg" placeholder="MFG" style="background-color: rgba(245, 245, 245, 0.46);">
						<%} %>
						
					</td>
					<td>
						<select  id="vat0" name="productdata[0].vat"  class="form-control chosen-select"> 
							<option value="">Select</option>
							<option value="0">0%</option>
							<option value="5">5%</option>
							<option value="12">12%</option>
							<option value="18">18%</option>
							<option value="28">28%</option>
						</select>
					</td>
					<td><input type="number" class="form-control" id="hsnno0" name="productdata[0].hsnno" placeholder="HSN NO" style="background-color: rgba(245, 245, 245, 0.46);"></td>
					<td class="hidden"><textarea rows="2" class="form-control" id="description0" placeholder="Description" name="productdata[0].description" style="background-color: rgba(245, 245, 245, 0.46)"></textarea></td>
					<td class=""><input type="number" class="form-control" name="productdata[0].minorder" id="minorder0" placeholder="Min Order" style="background-color: rgba(245, 245, 245, 0.46);" /></td>
					<td class=""><input type="number" class="form-control" name="productdata[0].maxorder" id="maxorder0" placeholder="Max Order" style="background-color: rgba(245, 245, 245, 0.46);" /></td>
					<!-- <td><a href="#" onclick="deleteProductRow(this)"><i class="fa fa-times fa-2x text-danger" ></i></a></td> -->
				</tr>
			</tbody>
		</table>
		
		<div class="col-lg-12 col-xs-12" style="padding:0px;margin-top:15px;">
			<div class="col-lg-6 col-md-6 text-left">
			<input value="Add more" onclick="addMoreProductRow('mytable')" id="addmorecataloguebtn" class="btn btn-primary" type="button">
			</div>
			<div class="col-lg-6 col-md-6 text-right">
			<input value="Save" class="btn btn-primary" type="button" onclick="validateaddproduct()"></div>
			</div>
		
		
		
			</div>
      </div>
      </s:form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
    
    
    <%if(loginInfo.isAuto_generic_name()){ %>
		<input type="hidden" id="isautogenericname">
	<%} %>
		
   <SCRIPT type="text/javascript">
   
      function updatestock() {
       
            document.updateform.action="updatestockProduct";
            document.updateform.submit();
      }
      
      
       
   
   </SCRIPT>
   <script>
     $(document).ready(function () {
  $(".notificationicon").click(function () {
    $(this).toggleClass("open");
    $("#notificationMenu").toggleClass("open");
  });
});
     
    </script>

    
     
   
   

</body>
</html>
