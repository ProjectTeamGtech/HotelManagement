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
                    
                       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top:5px">
                       <div class="form-inline">
                           <s:form theme="simple" action="bomkittemplateProduct">
                           		<div class="form-group">
	                           		<span class="text-uppercase"><b>BOM KIT Template</b>  &nbsp; - &nbsp; </span>
	                           	</div> 
                           		<%-- <div class="form-group">
								  	<s:textfield name="fromdate" id="fromdate" placeholder="Fromdate" theme="simple" cssClass="form-control"></s:textfield>
								</div>
								<div class="form-group">
								  	<s:textfield name="todate" id="todate" placeholder="Todate" theme="simple" cssClass="form-control"></s:textfield>
								</div>
								 --%>
								<div class="form-group">
								  <!-- 	<button type="submit" class="btn btn-primary">Go</button> -->
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
                                    <th style="width: 25%;">Template name</th>
                                    <th style="width: 25%;">Procedure name</th>
                                    <th style="width: 12%;">Date</th>
                                    <th style="width: 12%;">User Id</th>
                                    <th style="width: 15%;">Edit</th>
                                    <th style="width: 12%;">Delete</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            <%int xyz=0; %>
                               <s:iterator value="productList" >
                               
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++xyz) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="tempname"/></td>
                                    <td><s:property value="procedurename"/></td>
                                    <td><s:property value="date"/></td>
                                     <td><s:property value="userid"/></td>
                                    <td><a href="#" class="btn btn-primary" data-toggle="modal" onclick="getcathtemplatedata(<s:property value="id"/>)"><span>Edit </span></a></td>
                                	<td class="text-center"><a href="deletecathlabtemplateProduct?id=<s:property value="id"/>"
												onclick="return ConfirmDeleteTemplate()" class="text-danger"> <i
												class="fa fa-trash-o"></i> </a>
									</td>
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
                                    <th style="width: 25%;">Template name</th>
                                    <th style="width: 25%;">Procedure name</th>
                                    <th style="width: 12%;">Date</th>
                                    <th style="width: 12%;">User Id</th>
                                    <th style="width: 15%;">Edit</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            <%int i=0; %>
                               <s:iterator value="productList" >
                               
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++i) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="tempname"/></td>
                                    <td><s:property value="procedurename"/></td>
                                    <td><s:property value="date"/></td>
                                     <td><s:property value="userid"/></td>
                                    <td><a href="#" class="btn btn-primary" data-toggle="modal" onclick="getcathtemplatedata(<s:property value="id"/>)"><span>Edit </span></a></td>
                                </tr>
                                
                                </s:iterator>
                            </tbody>



                        </table>
                    </div>
	  <s:form action="bomkittemplateProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
									<div class="col-lg-12">
									<%-- <s:hidden name="fromdate"></s:hidden>
									<s:hidden name="todate"></s:hidden>
									<s:hidden name="isfromcathlab"></s:hidden> --%>
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



<!-- Cart Modal -->
<div id="cathtempcart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="updatecathlabtemplateCatalogue" theme="simple" id="cathtempproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Update Cathlab Template</h4>
      </div>
      <div class="modal-body">
         <div class="form-group">
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="updatecathprocedurelist">
          		<%-- <s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select Procedure" list="procedurelist"  id="cathtempprocedure" name="cathtempprocedure" /> --%>
          	</div>
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		<input type='text'  placeholder="Template name" class='form-control' id='cathtemplatename' name="cathtemplatename">
          	</div>
          	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
          		<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="genericname" headerValue="Select product" list="newproductlist"  id="cathproductid" name="cathproductid" />
          	</div>
           	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
           		<a href="#" onclick="addnewcathlabtemplaterow('tablecathcountnew')" class="btn btn-primary">Add</a>
          	</div>
           
          	
      	</div>
      	<div class="clearfix" style="height: 15px;"></div>
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tablecathcountnew" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">Category</th>
            <th style="width: 9%;">Sub Category</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 9%;">Product Code</th>
            <th style="width: 8%;">Batch No</th>
            <th style="width: 8%;">MFG</th>
            <th style="width: 9%;">Exp Date</th>
            <th style="width: 9%;">Location</th>
            <th style="width: 4%;">Stock</th>
            <th style="width: 10%;">Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="cathempbody">
           
          </tbody>
          </table>
          <input type="hidden" name="cathtempcount" id="cathtempcount">
          <input type="hidden" name="cathtempparentid" id="cathtempparentid">
          <input type="hidden" name="cathtempdeleteids" value="0" id="cathtempdeleteids">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="cathtempcomment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="cathtempdivbtn">
       		<button type="button" class="btn btn-primary" onclick="confirmCathTemp()">Update Template</button>
      </div>
      
      </s:form>
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
