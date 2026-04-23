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

    <SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
    <script type="text/javascript" src="common/js/pagination.js"></script>

 
    <SCRIPT type="text/javascript">
   
   		window.onload= function(){
   		
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
    
    </SCRIPT>
    <%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
   
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
    </style>
</head>



<body id="his" class="appWrapper sidebar-xs-forced">
								
    
        <section id="">

            <div class="">

                <div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                     <s:form theme="simple" action="agreementdashboardProcurement" method="post">
                      <div class="col-md-12" style="padding: 0px;">
                      		<span class="text-uppercase"><b>&nbsp;&nbsp;&nbsp;&nbsp;AGREEMENT DASHBOARD</b> &nbsp; - &nbsp;</span>
                      		<%-- <div class="col-lg-6 col-md-6">
								<span class="text-uppercase"><b>&nbsp;&nbsp;&nbsp;&nbsp;AGREEMENT DASHBOARD</b> &nbsp; - &nbsp;</span>
							</div>
							<div class="col-lg-6 col-md-6 hidden">
								<s:form action="freeqtytraMaster">
									<input type="submit" value="freeqtytra" class="btn btn-danger">
								</s:form>
								<s:form action="catatoproducttraMaster">
									<input type="submit" value="catatoproducttra" class="btn btn-danger">
								</s:form>
							</div> --%>
                      </div>
                        <div class="col-md-12" style="padding: 0px;">
                        <div class="col-lg-7 col-md-7 col-sm-7">
                        	<div class="form-inline">
		                        <div class="form-group" >
		                        	<s:textfield name="voucherno" cssClass="form-control" placeholder="Search Agreement No"></s:textfield>
		                        </div>
		                        <div class="form-group" >
		                        	<s:textfield id="fromdate" name="fromdate"  cssClass="form-control" placeholder="From Date" cssStyle="width:100%;"/>
		                        </div>
		                        <div class="form-group" >
		                        	<s:textfield  name="todate" id="todate"  cssClass="form-control" placeholder="To Date" cssStyle="width:100%;"/>
		                        </div>
		                        <div class="form-group">
		                        	<button type="submit" class="btn btn-primary">Go</button>
		                        	<a type="button"  title="Save As XLS" onclick="downloadexcelprint()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
		                        </div>
                        	</div>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 text-right">
                        	<div class="form-inline">
                        		<div class="form-group">
                        			<a class="btn btn-primary" href="newDmListProcurement"> DM List</a>
                        		</div>
                        		<div class="form-group">
                        			<a class="btn btn-primary" href="requestedpoProcurement"> GRN with PO</a>
                        		</div>
                        		<div class="form-group">
                        			<a class="btn btn-primary" href="#" onclick="openPopup('purorderProcurement')">GRN without PO</a>
                        		</div>
                        	</div>
                        </div>
                        </div>
                        <div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
                        <div class="col-lg-2 col-xs-2 col-md-2" style="padding: 0px;">
                        </div>
                        <div class="col-lg-2 col-xs-2 col-md-2" style="padding: 0px;">
                        </div>
                        </div> 
                        </s:form>
                        </div>
                        
                    </div> 
                    
                    <div class="col-lg-12 col-xs-12 col-md-12">
                         <table class="table my-table xlstable table-striped table-bordered" id="tablesort" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 3%;">No</th>
                                    <th style="width: 6%;">Agreement No.</th>
                                    <th style="width: 9%;">Agreement Date</th>
                                    <th style="width: 10%;">Supplier Name</th>
                                    <th style="width: 8%;">Location </th>
                                    <th class="" style="width: 5%;">Status</th>
                                   <!--  <th style="width: 10%;"></th> -->
                                </tr>
                            </thead>
                            <tbody>
                            	<%int i=0; %>
                            	<s:iterator value="agreementList">
                            		<td><%=(++i) %></td>
                            		<td><s:property value="id"/></td>
                            		<td><s:property value="date"/></td>
                            		<td><s:property value="vendor"/></td>
                            		<td><s:property value="locationname"/></td>
                            		<td>
                            			<s:if test="iscancel==1">
                            				Canceled
                            			</s:if>
                            			<s:else>
                            				<s:if test="status==0">
                            					<a onclick="openAgreementConfirmPopup(<s:property value="id"/>)" class="baksetp2" style="color:#fff;" href="#" >Confirm</a>
                                    		</s:if>
                            				<s:elseif test="status==1">
                            					<a onclick="openAgreementOrderPopup(<s:property value="id"/>)" class="baksetp2" style="color:#fff;" href="#" >Order</a>
                            				</s:elseif>
                            				<s:elseif test="status==2">
                            					
                            				</s:elseif>
                            			</s:else>
                            			
                            		</td>
                            		<!-- <td></td> -->
                            	</s:iterator>    
                        	</table><br />
                        
                    </div>

						<s:form action="agreementdashboardProcurement" name="paginationForm" id="paginationForm" theme="simple">
							    	<s:hidden name="voucherno"></s:hidden>
							    	 <s:hidden name="fromdate"></s:hidden>
							    	<s:hidden name="todate"></s:hidden> 
									<div class="col-lg-12">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /> Records</label>
									</div>
									<%@ include file="/common/pages/pagination.jsp"%>
								</div>
							</s:form>                     

                </div>

               

            </div>

        </section>

        <!--/ CONTENT -->
         








<div class="modal fade" role="dialog" id="confirmprod">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content" style="margin-left:-100px;margin-right:-100px !important">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agreement For :- <span id="vendorname"></span> &nbsp;|&nbsp; Agreement No: <span id="pono"></span> &nbsp;|&nbsp; Date: <span id="podate"></span></h4>
        
      </div>
      <div class="modal-body">
        	<div class="col-lg-12 col-xs-12 col-md-12 col-lg-12" style="padding:0px;">
        	<div class="" style="background-color: rgba(239, 239, 239, 0.42);padding: 9px;border: 1px dashed #ddd;">
	<table class="table table-striped table-bordered" style="width:100%;">
         
          <tbody>
          	<tr>
          		<td style="border-top: none;width: 15%;">
        			<%-- <select name="warehouse" id="warehouse" class="form-control chosen-select" onchange="getVendWarehouse(this.value)" >
    <option value="0">Select Warehouse</option>
    <option value="32">Central Drug Store</option>
    <option value="36">Central Material Store</option>


</select> --%>
  <%-- <s:select theme="simple" list="warehouseList" name="warehouse" id="warehouse"  listKey="id" listValue="name" headerKey="0" headerValue="Select Store" cssClass="form-control chosen-select" onchange="getVendWarehouse(this.value)" ></s:select> --%>
   				<s:select theme="simple" list="warehouseList" name="warehouse" id="warehouse"  listKey="id" listValue="name" headerKey="0" headerValue="Select Store" cssClass="form-control chosen-select" onchange="setProductofStoreInPO(this.value)" ></s:select>
          		</td>
          		<td id="categorydiv" class="hidden" style="border-top: none; width: 15%;">
        				<select class="form-control">
        				 <option>Select Category </option>
        				</select>
          		</td>
          		<td id="subdiv" class="hidden" style="border-top: none;width: 15%;">
        				<select class="form-control">
        				<option>Select SubCategory </option>
        				</select>
          		</td>
          		<td id="proddiv" style="border-top: none;width: 15%;">
        				<select name="" class="form-control chosen-select" >
						    <option value="0">Select</option>
						</select>
          		</td>
          		<td style="border-top: none;">
          			<input type="number" onchange="calculateWithPoDisc()"  class="form-control" id="rate" placeholder="Rate">
          		</td>
          		<td style="border-top: none;">
          			<input type="number" onchange="calculateWithPoDisc()" class="form-control" id="discount" placeholder="Discount in %">
          		</td>
          		<td style="border-top: none;">
          			<input type="number" onchange="calculateWithPoDisc()" class="form-control" id="qty" placeholder="Qty">
          		</td>
          		<td style="border-top: none;">
          			<input type="number" readonly="readonly" class="form-control" id="xtotal" placeholder="Total">
          		</td>
          		<td style="border-top: none;"><a href="#" onclick="reqnewAgreement()"  id="addpopnew" class="btn btn-success">Add</a></td>
          	</tr>
          	</tbody>
         </table>
</div>
        	
        	
			   <s:form action="confirmagreementProcurement" theme="simple" id="procform" method="post">
			   		<input type="hidden" id="vendorid" value="0" />
			    	<s:hidden name="proc_id" id="proc_id"></s:hidden>
			     	<s:hidden name="mailcheck" id="mailcheck"></s:hidden>
			   	 	<s:hidden name="venderemail" id="venderemail"></s:hidden>
			    	<s:hidden name="saveconfirmpo" id="saveconfirmpo" value="0"></s:hidden>
			    	<s:hidden name="cancelconfirmpo" id="cancelconfirmpo" value="0"></s:hidden>
				<table class="table my-table xlstable table-bordered" id="prodTable" style="width: 100%;"  >
					<thead>
						<tr>
				    		<th style="width: 5%;">Sr No</th>
							<th style="width: 20%;">Product Name</th>
							<th style="width: 7%;">Product Code</th>
							<th style="width: 6%;">GST</th>
							<th style="width: 6%;">Quantity</th>
							<th style="width: 10%;">Rate</th>
							<th style="width: 9%;">Amount</th>
							<th style="width: 9%;">GST Amt</th>
							<th style="width: 6%;">Discount</th>
							<th style="width: 10%;">Net Amount</th>
							<th style="width: 7%;">Old Stock</th>
							<th style="width: 7%;">Old price</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="innerData">
						
					</tbody>

				</table>
		
		<table   style="width: 100%;"  >
			<thead>
				<tr>
				    <th style="width: 5%;"></th>
					<th style="width: 20%;"></th>
					<th style="width: 7%;"></th>
					<th style="width: 6%;"></th>
					<th style="width: 6%;"></th>
					<th style="width: 10%;"></th>
					<th style="width: 9%;"></th>
					<th style="width: 9%;"></th>
					<th style="width: 6%;"></th>
					<th style="width: 10%;"></th>
					<th style="width: 7%;"></th>
					<th style="width: 7%;"></th>
					<th></th>
				</tr>
			</thead>
			<tbody id="tfootconfirmpo">
				
			</tbody>

		</table>
		
		<div class="form-group" style="margin-top: 20px;">
		  <label for="comment">Remark:</label>
		  <textarea class="form-control" rows="3" id="remark" name="remark" style="background-color: rgba(255, 248, 220, 0.41);"></textarea>
		</div>
		</s:form>
		
			</div>
      </div>
      <div class="modal-footer" >
      	<input value="Save" onclick="saveAgreementConfirm()" class="btn btn-success" type="button" style="margin-top: 15px;" >
       <% if(loginInfo.getUserType()==2 || loginInfo.isApprove_po() || loginInfo.getJobTitle().equals("Admin")) {%>
      		<input value="Confirm" onclick="submitAgreementConfirm()" class="btn btn-success" type="button" style="margin-top: 15px;" id="newpopup">
      <!--Lokesh  -->
      <%if(loginInfo.getUserType()==2){ %>
      		 <s:if test="iscancel!=1">
       			<input value="Cancel Agreement" onclick="cancelAgreement()" class="btn btn-danger" type="button" style="margin-top: 15px;">
        	</s:if>
      <%}else if(loginInfo.getCancel_po()!=null){ %>
		<%if(loginInfo.getCancel_po().equals("1")){ %>
       		<s:if test="iscancel!=1">
       			<input value="Cancel Agreement" onclick="cancelAgreement()" class="btn btn-danger" type="button" style="margin-top: 15px;">
        	</s:if>
        <%} %>    
      <%} %>
      <%} %>
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Create Agreement</button>
      </div>
    </div>

  </div>
  </div>
  
  
  <div class="modal fade" role="dialog" id="orderagreement">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content" style="margin-left:-100px;margin-right:-100px !important">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agreement For :- <span id="ordervendorname"></span> &nbsp;|&nbsp; Agreement No: <span id="orderpono"></span> &nbsp;|&nbsp; Date: <span id="orderpodate"></span></h4>
        <input type="hidden" id="vendorid" value="0" />
      </div>
      <div class="modal-body">
        	<div class="col-lg-12 col-xs-12 col-md-12 col-lg-12" style="padding:0px;">
        		<s:form action="orderagreementProcurement" theme="simple" id="orderform" method="post">
        			<input type="hidden" id="orderchildids" name="orderchildids" value="0" />
			    	<input type="hidden" id="ordervendorid" value="0" />
			    	<s:hidden name="proc_id" id="orderproc_id"></s:hidden>
			     	<s:hidden name="cancelorderagree" id="cancelorderagree" value="0"></s:hidden>
				<table class="table my-table xlstable table-bordered" id="prodTable" style="width: 100%;"  >
					<thead>
						<tr>
				    		<th style="width: 5%;">Sr No</th>
							<th style="width: 20%;">Product Name</th>
							<th style="width: 7%;">Product Code</th>
							<th style="width: 6%;">GST</th>
							<th style="width: 5%;">Agree. Qty</th>
							<th style="width: 5%;">Ordered Qty</th>
							<th style="width: 6%;">Qty</th>
							<th style="width: 10%;">Rate</th>
							<th style="width: 9%;">Amount</th>
							<th style="width: 9%;">GST Amt</th>
							<th style="width: 6%;">Discount</th>
							<th style="width: 10%;">Net Amount</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="orderinnerDataagreement">
						
					</tbody>

				</table>
		
		<table   style="width: 100%;"  >
			<thead>
				<tr>
				    <th style="width: 5%;"></th>
					<th style="width: 20%;"></th>
					<th style="width: 7%;"></th>
					<th style="width: 6%;"></th>
					<th style="width: 5%;"></th>
					<th style="width: 5%;"></th>
					<th style="width: 6%;"></th>
					<th style="width: 10%;"></th>
					<th style="width: 9%;"></th>
					<th style="width: 9%;"></th>
					<th style="width: 6%;"></th>
					<th style="width: 10%;"></th>
					<th></th>
				</tr>
			</thead>
			<tbody id="ordertfootagreement">
				
			</tbody>

		</table>
		
		<div class="form-group" style="margin-top: 20px;">
		  <label for="comment">Remark:</label>
		  <textarea class="form-control" rows="3" id="orderremark" name="remark" style="background-color: rgba(255, 248, 220, 0.41);"></textarea>
		</div>
		</s:form>
		
			</div>
      </div>
      <div class="modal-footer" >
       <% if(loginInfo.getUserType()==2 || loginInfo.isApprove_po() || loginInfo.getJobTitle().equals("Admin")) {%>
      		<input value="Order" onclick="submitAgreementOrder()" class="btn btn-success" type="button" style="margin-top: 15px;" id="newpopup">
      <%if(loginInfo.getUserType()==2){ %>
      		 <s:if test="iscancel!=1">
       			<input value="Cancel Agreement" onclick="cancelAgreement()" class="btn btn-danger" type="button" style="margin-top: 15px;">
        	</s:if>
      <%}else if(loginInfo.getCancel_po()!=null){ %>
		<%if(loginInfo.getCancel_po().equals("1")){ %>
       		<s:if test="iscancel!=1">
       			<input value="Cancel Agreement" onclick="cancelAgreement()" class="btn btn-danger" type="button" style="margin-top: 15px;">
        	</s:if>
        <%} %>    
      <%} %>
      <%} %>
      </div>
    </div>

  </div>
  </div>


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
	<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<SCRIPT type="text/javascript">
 
    function downloadexcelprint() {
        $(".tablestock").table2excel({
					exclude: ".noExl",
					name: "Procurment",
					filename: "Procurment",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
 
</SCRIPT>
	
</body>
</html>




