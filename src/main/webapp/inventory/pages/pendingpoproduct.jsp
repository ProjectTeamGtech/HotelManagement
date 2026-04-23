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


.table>thead>tr>th,td .table>tbody>tr>th,td .table>tfoot>tr>th,td .table>thead>tr>td, .table>tbody>tr>td .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 10px solid #000000;
    font-weight: normal;
    font-size: 9px !important;
    border-right: none !important;
    border-left: none !important;
    border: thick solid;
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
    color: #000000 !important;
    border: 1px solid black;
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
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<SCRIPT type="text/javascript">

	function printReport() {
				
				  $(".table").table2excel({
					exclude: ".noExl",
					name: "PENDING PO PRODUCT REPORT",
					filename: "PENDING PO PRODUCT REPORT",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }

  function submitForm(){
	  var vendor_id = document.getElementById("vendor_id").value;
	  if(vendor_id==0){
		  jAlert('error', "Please select supplier!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	  }else{
		 document.getElementById("formSubmit").submit(); 
	  }
  }

</SCRIPT>



<body id="his" class="appWrapper sidebar-xs-forced">
								<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
    
        <section id="">
			<div class="print-visible hidden-md hidden-lg letterheadhgt" >
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div>
            <div class="">

                <div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck hidden-print">
                     <s:form theme="simple" id="formSubmit" action="pendingPOProductProcurement" method="post">
                     <s:hidden name="isfromcathlab"></s:hidden>
                      <div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
                      
                      <span class="text-uppercase"><b>&nbsp;&nbsp;&nbsp;&nbsp;Pending Purchase Order Product Report</b> &nbsp; - &nbsp;</span>
                      </div>
                        <div class="col-lg-12 col-md-12 col-sm-12" >
                        <!-- <div class="col-lg-8 col-md-8 col-sm-8"> -->
	                        <div class="form-inline">
	                        	<div class="form-group" >
									<s:select list="vendorList" cssClass="form-control chosen-select" listKey="id" listValue="Data" name="vendor_id" id="vendor_id" headerKey="0" headerValue="Select Supplier"></s:select>
								</div>
	                        	<div class="form-group">
	                        		<s:textfield name="voucherno" cssClass="form-control" placeholder="Search PO"></s:textfield>
	                        	</div>
	                        	<div class="form-group" style="width: 7%;">
	                        		<s:textfield id="fromdate" name="fromdate" readonly="true"  cssClass="form-control" placeholder="From Date" cssStyle="width:100%;"/>
	                        	</div>
	                        	<div class="form-group" style="width: 7%;">
	                        		<s:textfield  name="todate" id="todate" readonly="true" cssClass="form-control" placeholder="To Date" cssStyle="width:100%;"/>
	                        	</div>
	                        	<%if(loginInfo.getUserType()==2 || loginInfo.getPharmacyUserType()==2 ) {%>
									<div class="form-group" >
										<s:select list="locationListPharmacy" name="location" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
								     	</s:select> 
								     </div>
							    <%}%>  
	                         	<div class="form-group">
		                        	<!-- <button type="submit" class="btn btn-primary">Go</button> -->
		                        	<a href="#" class="btn btn-primary" onclick='submitForm()'>Go</a>
		                        	<button type="button" onclick="printReport()" class="btn btn-primary">Download xls</button>
									&nbsp;<a href="#" onclick="printpage();" class="btn btn-primary hidden-print"><i class="fa fa-print"></i></a>
		                        </div>
		                        <div class="form-group">
									
								</div>
	                        </div>
						<!-- </div> -->
                        <!-- <div class="col-lg-4 col-md-4 col-sm-4 text-right">
                        			
                        </div> -->
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
                    
                    <div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
                         <table class="table my-table xlstable table-bordered"  id="tablesort" style="width: 100%;">        <!-- class="table my-table xlstable table-striped table-bordered table" -->
                            <thead>
                               <tr>
		                           <td>No</td>
		                           <td>Supplier</td>
		                           <td>PO.No</td>
		                           <td>PO Date</td>
		                           <td>Product Name</td>
		                           <td>Product Code</td>
		                           <td>Rate</td>
						           <td>GST</td>
						           <td>Requested Quantity</td>
		                           <td>Received Quantity</td>
		                           <td>Balance Quantity</td>
		                           <td>Pending Amount</td>
		                       </tr>
                            </thead>
                            <tbody>
                                <%int i=0; %>
                                <s:iterator value="procurementList">
	                                <tr>
	                                    <td><%=(++i) %></td>
	                                    <td><s:property value="vendor"/></td>
	                                    <td><a href="#" onclick="openPopup('printconfirmProcurement?id=<s:property value="id"/>')"><s:property value="grnYearSeq"/></a></td>
	                                    <td>
	                                    	<s:property value="date"/>
	                                    </td>
	                                    <td><s:property value="product"/></td>
	                                    <td><s:property value="product_code"/></td>
	                                    <td><s:property value="purchase_price"/></td>
					                    <td class="text-center"><s:property value="Ttlgst"/>%</td>
					                    <td class="text-center"><s:property value="Quantity"/></td>
					                    <td class="text-center"><s:property value="Received_qty"/></td>
					                    <td class="text-center"><s:property value="Balanceqty"/></td>
					                    <td class="text-center"><s:property value="Pendingamt"/></td>
					                </tr>
                             	</s:iterator>	                            
                            </tbody>
                        </table><br />
                    </div>
			    </div>

               

        </section>

        <!--/ CONTENT -->




  
  
  
  
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
