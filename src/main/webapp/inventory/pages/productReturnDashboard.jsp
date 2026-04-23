
<!doctype html>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
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

    <SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
    <script type="text/javascript" src="common/js/pagination.js"></script>
<style>
.topheadbaxck {
    background-color: rgb(239, 239, 239);
    padding: 8px 0px;
}
.table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    font-size: 14px;
}
.savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
    line-height: 40px;
}
.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
    cursor: not-allowed;
    opacity: 1;
    background-color: rgba(221, 221, 221, 0.28);
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
  		};
   		
   
  		function confirmCancel(){
  			
  			 var t=confirm("Are you Want to Cancel Purchase Order?");
  	          if(t==true){
  	             return true;
  	          } else {
  	             return false;
  	          } 
  			
  		}
    
       function confirmedDone() {
          
          var t=confirm("Are you Want to Confirm Product Order?");
          if(t==true){
             return true;
          } else {
             return false;
          } 
       }
       
       function confirmreceipt() {
       
          var t=confirm("Do you Received Product Order?");
          if(t==true){
             return true;
          } else {
             return false;
          } 
       }
       
        function confirmreceived() {
       
          var t=confirm("Do you didn't Received Product Order?");
          if(t==true){
             return true;
          } else {
             return false;
          } 
       }
       
       
       function submitPO() {
       
           document.getElementById("poform1").submit();
       }
       
       
    </SCRIPT>


 <SCRIPT type="text/javascript" >
       $(document).ready(function() {

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
       
       	function printProductReturnReportExcel() {
	           $(".tablestock").table2excel({
   					exclude: ".noExl",
   					name: "Product Return Dashboard",
   					filename: "productreturndasboardList",
   					fileext: ".xls",
   					exclude_img: true,
   					exclude_links: true,
   					exclude_inputs: true
   				});
            }
    
    </SCRIPT>
     <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
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
        .baksetp{
        	    background-color: #616f77;
			    color: #fff;
			    padding: 0px 5px 0px 5px;
			    width: 93px;
			    position: absolute;
			    text-align: center;
        }
        .baksetp1{
	        	background-color: #16a085;
			    color: #fff;
			    padding: 0px 5px 0px 5px;
			    width: 93px;
			    position: absolute;
			    text-align: center;
        }
        .baksetp2{
	        	background-color: #daae71;
			    color: #fff;
			    padding: 0px 5px 0px 5px;
			    width: 93px;
			    position: absolute;
			    text-align: center;
        }
        .hest{height: 20px !important;}
        
        
       @media print
{


.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 9px !important;
    border-right: none !important;
    border-left: none !important;
}




 
    label {
    	font-size: 9px !important;
	}
	p {
	    margin: 0 0 2.5px !important;
	    font-size: 9px !important;
	}
	
	.form-group {
    margin-bottom: 0px !important;
}

.titleset {
    margin: 0px;
    color: #6699cc !important;
    border-bottom: 1px dashed #efefef;
    font-size: 15px;
    line-height: 20px;
}
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent !important;
    background-color: #4E7894 !important;
    color: #fff !important;
}
h5, .h5 {
    font-size: 9px !important;
}

}
h5, .h5 {
    font-size: 11px;
}
h5, .h5{
    margin-top: 4px;
    margin-bottom: 0px;
}
.tocolor{
	font-size: 14px;
    font-weight: bold;
    color: green;
}
b, strong {
    font-weight: 900;
}
.return1{
	background-color: #f0ad4e;
    padding: 2px;
    color: #fff;
    border-radius: 3px;
}
.return0{
	background-color: #16a085;
    padding: 2px;
    color: #fff;
    border-radius: 3px;
}
    </style>
</head>





<body id="his" class="appWrapper sidebar-xs-forced">
								<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
    
        <section id="">

            <div class="">

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
                     <s:form theme="simple" action="" method="post">
                        <div class="col-md-12" style="padding: 0px;">
                        <div class="col-lg-9 col-md-9 col-sm-9">
                        	<div class="form-inline">
                         <div class="form-group">
	                           		<span class="text-uppercase"><b>PRODUCT RETURN LIST</b> &nbsp; - &nbsp;</span>
	                           </div>
                        <div class="form-group">
                        	<s:textfield name="searchtext" cssClass="form-control" placeholder="Search Return No/Supplier Name"></s:textfield>
                        </div>
                        <div class="form-group" style="width: 8%;">
                        	<s:textfield id="fromdate" readonly="true" name="fromdate"  cssClass="form-control" placeholder="From Date" cssStyle="width:100%;"/>
                        </div>
                        <div class="form-group" style="width: 8%;">
                        	<s:textfield  name="todate" id="todate" readonly="true"  cssClass="form-control" placeholder="To Date" cssStyle="width:100%;"/>
                        </div>
                        <div class="form-group">
                        	<select class="form-control">
                        		<option>Select </option>
                        		<option>Returned </option>
                        		<option>Ready To Return </option>
                        	</select>
                        </div>
                        <div class="form-group">
	                       		<button type="submit" class="btn btn-primary">Go</button>
	                       		<a type="button"  title="Save As XLS" onclick="printProductReturnReportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
                        </div>
						</div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 text-right">
                        	<div class="form-inline">
                        		<div class="form-group">
                        			<span style="font-size: 15px;color: green;padding-top: 1px !important;">[ Today Return : <s:property value="todayreturn"/> ]&nbsp;</span> 
                        		</div>
                        	</div>
                        </div>
                        </div>
                        </s:form>
                    </div> 
                    <div class="col-lg-12 col-xs-12 col-md-12">
                         <table class="table my-table xlstable table-striped table-bordered tablestock" id="tablesort" style="width: 100%;">
                            <thead>
                            
                                <tr>
                                    <th style="width: 3%;">No</th>
                                    <th style="width: 12%;">Product Return No</th>
                                    <th style="width: 11%;">Date/Time</th>
                                    <th style="width: 20%;">Supplier Name</th>
                                    <th style="width: 6%;text-align:right;">Total</th>
                                    <th style="width: 6%;text-align:right;">Debit Amt</th>
                                    <th style="width: 6%;text-align:right;">Credit Amt</th>
                                    <th style="text-align:center;">Status</th> 
                                    <th class="noExl">Debit Print</th> 
                                    <th class="noExl">Credit Print</th> 
                                    <th>Settlement Status</th> 
                                    <th  class="noExl">Cancel</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <%int i=0; %>
                                <s:iterator value="productReturnList">
	                                <tr>
	                                   <td><%=++i %></td>
	                                   <td><s:property value="grnreturnid"/></td>
	                                   <td><s:property value="dateTime"/></td>
	                                   <td><s:property value="vendor"/></td>
	                                   <td class="text-right"><s:property value="total"/></td>
	                                   <td class="text-right"><s:property value="netammt"/></td>
	                                   <td class="text-right"><s:property value="aprovedamt"/></td>
	                                   <%-- <td><s:property value="voucherno"/></td> --%>
	                                    
	                                    <s:if test="deleted==0">
		                                   <s:if test="status==0">
		                                      <td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><span><a href="viewreturngrnProduct?grnreturnid=<s:property value="grnreturnid"/>" >Ready To Return</a></span></td>
		                                   </s:if>
		                                   <s:elseif test="status==1">
		                                   		<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><span><a href="#" onclick="openAproveReturnProductPopupNew(<s:property value="grnreturnid"/>)" >Returned</a></span></td>
		                                   </s:elseif>
		                                   <s:else>
		                                       <td style="text-align:center;background-color: rgba(127, 204, 128, 0.98);"><span>Return Approved</span></td>
		                                   </s:else>
		                                </s:if>
		                                <s:else>
		                                	 <td style="text-align:center;background-color: rgba(255, 0, 0, 0.51);">Cancelled</td>
		                                </s:else>
	                                    
	                                    <s:if test="status==1">
	                                   		<td style="text-align:center;" class="noExl"><a href="#" onclick="openPopup('printreturngrnProduct?grnreturnid=<s:property value="grnreturnid"/>&status=1')"><i class="fa fa-print" style="padding-left: 13px;"></i></a></td>
	                                   </s:if>
	                                    <s:elseif test="status==2">
	                                   		<td style="text-align:center;" class="noExl"><a href="#" onclick="openPopup('printreturngrnProduct?grnreturnid=<s:property value="grnreturnid"/>&status=1')"><i class="fa fa-print" style="padding-left: 13px;"></i></a></td>
	                                   </s:elseif>
	                                   <s:else>
	                                       <td style="text-align:center;" class="noExl"></td>
	                                   </s:else>
	                                   
	                                   <s:if test="status==2">
	                                   		<td style="text-align:center;" class="noExl"><a href="#" onclick="openPopup('printreturngrnProduct?grnreturnid=<s:property value="grnreturnid"/>&status=2')"><i class="fa fa-print" style="padding-left: 13px;"></i></a></td>
	                                   </s:if>
	                                   <s:else>
	                                       <td style="text-align:center;" class="noExl"></td>
	                                   </s:else>
	                                   <td>
		                                   <s:if test="amtreturnstatus==0">
		                                   	Not Setteled
		                                   </s:if>
		                                   <s:else>
		                                   	Setteled
		                                   </s:else>
	                                   </td>
	                                   <td style="text-align:center;" class="noExl">
	                                   		<s:if test="deleted==0">
	                                   			 <s:if test="status==0">
	                                   				<a href="#" onclick="deleteReturnProductReq(<s:property value="grnreturnid"/>)"><i class="fa fa-trash text-danger"></i></a>
	                                   			</s:if>
	                                   		</s:if>
	                                   </td>
	                                   
	                                </tr>  
							 	</s:iterator>	                            
                            </tbody>
                        </table><br />
                    </div>

						<s:form action="returnproductdashboardProduct" name="paginationForm" id="paginationForm" theme="simple">
							    	<s:hidden name="voucherno"></s:hidden>
							    	 <s:hidden name="fromdate"></s:hidden>
							    	<s:hidden name="todate"></s:hidden> 
									<div class="col-lg-12">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /> Records
										</label>
									</div>
									<%@ include file="/common/pages/pagination.jsp"%>
								</div>
							</s:form>                     

                </div>

               

            </div>

        </section>

        <!--/ CONTENT -->
         
  
  <!-- Modal -->
       <div id="clearaproveamt" class="modal fade" role="dialog" style="background-color: rgba(0, 0, 0, 0.5);">
       
      
         <div class="modal-dialog modal-sm">
           <!-- Modal content-->
           
           <div class="modal-content">
           
             <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
              
               <h4 class="modal-title">Approve Amount Pop Up </h4>
             </div>
             <div class="modal-body">
               <s:form action="saveaprovereturngrnamtProduct" theme="simple"   id="formaprove" method="post" >
                	<input type="hidden" name="aprovereturnid" id="aprovereturnid">
                	<input type="hidden" name="aprovereturnbal" id="aprovereturnbal">
                <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               		
                		<div class="form-group">
              				<label for="email">Total Amount:</label>
              				<span id="aprovereturnbaltext"></span> 
            			</div>
               		</div>
               
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               			<div class="form-group">
              				<label for="email">Approved Amount</label>
             				<input type="number" id="totalenteredpayamt" name="totalenteredpayamt"  class="form-control" >
            			</div>
               		</div>
                </s:form>
             </div>
             <div class="modal-footer">
               <input type="button" class="btn btn-success disblebtnone" onclick="aprovebalance()" value="Pay" />
             </div>
           </div>
          
       
         </div>
     
       </div>
  
  <!-- Modal -->
<div id="deletemodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="parent_id">
        	<textarea rows="3"  class="form-control" id="delete_reason" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="deleteReturnProductReq1()"  value="Ok">
      </div>
    </div>

  </div>
</div>
  
  
  <form action="Procurement" name="testform"></form>
  <SCRIPT type="text/javascript">
  
     function calTotal(qty) {
     
       var price= document.getElementById("purchase_price").innerHTML;
        
       var total=parseFloat(price)*qty; 
        
       document.getElementById("total").innerText=total;
     }
  
  </SCRIPT>
  
  <script>
    function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
    //document.body.innerHTML = oldPage;

    //Print Page
    setTimeout(function () {
        print_page();
    }, 2000);

    function print_page() {
        window.print();
    }

    //Restore orignal HTML
    setTimeout(function () {
        restore_page();
    }, 3000);

    function restore_page() {
        document.body.innerHTML = oldPage;
    }
    
    
    
}
 	
	</script>
	
</body>
</html>




