<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="common/js/pagination.js"></script>  
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>

<%-- <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script type="text/javascript" src="pharmacy/js/tableExport.jquery.plugin-master/tableExport.js"> </script>
<script type="text/javascript" src="pharmacy/js/tableExport.jquery.plugin-master/jquery.base64.js"> </script>
<script type="text/javascript" src="pharmacy/js/tableExport.jquery.plugin-master/html2canvas.js"> </script>
<script type="text/javascript" src="pharmacy/js/tableExport.jquery.plugin-master/jspdf/libs/base64.js"> </script>
<script type="text/javascript" src="pharmacy/js/tableExport.jquery.plugin-master/jspdf/libs/sprintf.js"> </script>
<script type="text/javascript" src="pharmacy/js/tableExport.jquery.plugin-master/jspdf/jspdf.js"> </script>

 --%>
 <script type="text/javascript">"WebContent/"
function exportF(elem) {
	  var table = document.getElementById("history");
	  var html = table.outerHTML;
	  var url = 'data:application/vnd.ms-excel,' + escape(html); // Set your html table into url 
	  elem.setAttribute("href", url);
	  elem.setAttribute("download", "EmployeeSalRpt.xls"); // Choose the file name
	  return false;
	}
</script> 
<style>
	hr {
    margin-top: 8px;
    margin-bottom: 8px;
}
.imflag{
	display: inline-block;
    width: 88%;
}
.marboset{
	    margin-bottom: 3px;
}
.bonetamt{
	border-bottom: 1px solid black !important;
    margin-bottom: 3px;
}
@media print
{
.bonetamt{
	border-bottom: 1px solid black !important;
    margin-bottom: 3px !important;
}
.marboset{
	    margin-bottom: 3px !important;
}
p {
    margin: 0 0 0px;
    font-size:11px;
}
}
hr {
    margin-top: 5px;
    margin-bottom: 5px;
    border: 0;
    border-top: 2px solid rgba(119, 119, 119, 0.6);
}
p {
    margin: 0 0 0px;
}
</style>


<% Priscription priscription=(Priscription) session.getAttribute("report"); %>


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
		             
    };
  
    function submitit(){
    
        document.getElementById("formbal").submit();
    }
    
	function printReport() {
		
		  $(".pharppatienttable").table2excel({
			exclude: ".noExl",
			name: "Pharmacy Bill Summary",
			filename: "pharmacybillsummary",
			fileext: ".xls",
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});          
}


</SCRIPT>



</head>
<body>
		  <div class="row">
							        <div class="" >
							       
							        <!--  -->
							        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
							        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="letterHead">
							        		<h4><s:property value="clinicName"/></h4>
							        		<h5><s:property value="clinicaddress"/></h5>
							        		<h5>Website:<s:property value="websiteUrl"/>, Email:<s:property value="clinicemail"/>, Contact : <s:property value="clinicphoneno"/></h5>
							        	</div>
							        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
							        		<h4><b>Sales Summary</b></h4>
							        	</div>
							        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;" id="billanddata">
							        		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
							        			<p class="marboset"><b>Registration No :</b><span><s:property value="clientId"/></span></p>
								        		<p class="marboset"><b>Patient Name :</b><span><s:property value="fullname"/></span></p>
								        		<p class="marboset"><b>Address :</b><span><s:property value="address"/></span></p>
								        		<p class="marboset"><b>Contact No :</b><span><s:property value="mobno"/></span></p>
								        		<s:if test="isisIpd==true">
								        			<p class="marboset"><b>Admission Date :</b><span><s:property value="admissionDate"/></span></p>
								        		</s:if>
								        		<s:else>
								        			<p class="marboset"></p>
								        		</s:else>
								        	</div>
								        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								        		<p class="marboset"><b>Bill Amount :</b><span class="pull-right">Rs.<s:property value="totalbill"/></span></p>
								        		<p class="marboset"><b>Return :</b><span class="pull-right">Rs.<s:property value="totalreturn"/></span></p>
								        		<p class="marboset"><b>Discount :</b><span class="pull-right">Rs.<s:property value="totaldisc"/></span></p>
								        		<p class="marboset"><b>Round Off :</b><span class="pull-right">Rs.<s:property value="roundOf"/></span></p>
								        		<p ><b>Net Amount :</b><span class="pull-right">Rs.<s:property value="netamt"/></span></p>
								        		<p class="marboset"><b>Balance :</b><span class="pull-right">Rs.<s:property value="balance"/></span></p>
								        	</div>
							        	</div>
							        	
							        </div>
							        
							         
							           <div id="history" class="pharppatienttable"> 
							           <table class="hidden-lg hidden-md visible-print hidden-print">
							           		<tr>
							           			<th></th>
							           			<th></th>
							           			<th></th>
							           			<th><b>Sales Summary</b></th>
							           			<th></th>
							           			<th></th>
							           			<th></th>
							           			<th></th>
							           			<th></th>
							           		</tr>
							           	</table>
							           	<table class="hidden-lg hidden-md visible-print hidden-print">
							           	
							           		<tr>
							           			<td>Registration No :</td>
							           			<td><s:property value="clientId"/></td>
							           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
							           			<td>Bill Amount :</td>
							           			<td>Rs.<s:property value="totalbill"/></td>
							           		</tr>
							           		<tr>
							           			<td>Patient Name :</td>
							           			<td><s:property value="fullname"/></td>
							           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
							           			<td>Return :</td>
							           			<td>Rs.<s:property value="totalreturn"/></td>
							           		</tr>
							           		<tr>
							           			<td>Address :</td>
							           			<td><s:property value="address"/></td>
							           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
							           			<td>Discount :</td>
							           			<td>Rs.<s:property value="totaldisc"/></td>
							           		</tr>
							           		<tr>
							           			<td>Contact No :</td>
							           			<td><s:property value="mobno"/></td>
							           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
							           			<td>Round Off :</td>
							           			<td>Rs.<s:property value="roundOf"/></td>
							           		</tr>
							           		<tr>
								           		<s:if test="isisIpd==true">
									           		<td>Admission Date :</td>
								           			<td><s:property value="admissionDate"/></td>
								           		</s:if>
								           		<s:else>
									           		<td></td>
									           		<td></td>
								           		</s:else>
							           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
							           			<td>Net Amount :</td>
							           			<td>Rs.<s:property value="netamt"/></td>
							           		</tr>
							           		<tr>
							           			<td></td>
							           			<td></td>
							           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
							           			<td>Balance :</td>
							           			<td>Rs.<s:property value="balance"/></td>
							           		</tr>
							           	</table>
							           <s:iterator value="allBillList">
							         
							        	<b style="color: #e69e18;font-weight: normal;">Bill Date: <s:property value="date"/>&nbsp;|</b>
							        	<s:if test="isreturn==1">
							        		<b> Bill no: <s:property value="id"/> (Sales Return / <s:property value="paymode"/>) </b>
							        	</s:if>
							        	<s:else>
							        		<b> Bill no: <s:property value="id"/> (<s:property value="paymode"/>)</b>
							        	</s:else>
							        	
							        	<table class="hidden-lg hidden-md visible-print hidden-print">
							           		<tr>
							           			<td>Bill Date:</td>
							           			<td><s:property value="date"/></td>
							           			<td></td>
							           			<s:if test="isreturn==1">
							        				<td><b>Bill no: <s:property value="id"/></b></td>
							        				<td>(Sales Return / <s:property value="paymode"/>)</td>
									        	</s:if>
									        	<s:else>
									        		<td><b>Bill no: <s:property value="id"/></b></td>
							        				<td>(Sales / <s:property value="paymode"/>)</td>
									        	</s:else>
							           		</tr>
							           	</table>
							        	
							        		<div class="">
					                            <table class="table table-bordered table-responsive">
					                                <thead>
					                                    <tr class="tableback">
					                                            <th style="width: 4%;">SR.No</th>
					                                            <th style="width: 25%;">Name of Drug</th>
					                                            <th style="width: 10%;">HSN No</th>
					                                            <th style="width: 4%;">Qty</th>
					                                            <th style="width: 7%;">Batch</th>
					                                        	<th style="width: 8%;">Exp. Dt</th>
					                                        	<!-- <th style="width: 4%;">Pkg</th> -->
					                                        	<!-- <th style="width: 4%;">Mfg</th> -->
					                                        	<th style="width: 4%;">GST</th>
					                                        	<th style="width: 4%;">MRP</th>
					                                        	<th style='width: 6%;' class="text-right">Amount</th>
					                                    </tr>
					                                </thead>
					                               	<tbody>
					                               		<%int xx=1; %>
					                               		<s:iterator value="priscriptionlist">
					                                	<tr>
					   										<td><%=xx %></td>
					    									<td><s:property value="mdicinenametxt"/></td>
					    									<td><s:property value="hsnno"/></td>
					    									<td><s:property value="saleqty"/></td>
					    									<td><s:property value="batch_no"/></td>
					    									<td><s:property value="expiry_date"/></td>
					    									<%-- <td><s:property value="pkg"/></td> --%>
					    									<%-- <td><s:property value="mfg"/></td> --%>
					    									<td><s:property value="vat"/> %</td>
					    									<td><s:property value="sale_price"/></td>
					    									<td class='text-right'>Rs.<s:property value="total"/></td>
					    								</tr> 	  
					    								<%xx++; %>
					                                    </s:iterator>
					
					                                </tbody>
					                              
					                            </table>
					                            <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12" style="border-bottom: 1px solid #000;padding: 0px;">
				  								<div class="">
				   								<div class="col-lg-4 col-sm-4 col-xs-4 col-md-4 text-left" style="padding: 0px;">
				   								<p>Sub Total : Rs.<s:property value="subtotal" /></p>
				   								<p>Discount(%) : Rs.<s:property value="discount" /></p>
				   								</div>
				   								<div class="col-lg-4 col-sm-4 col-xs-4 col-md-4 text-center" style="padding: 0px;">
				   								<p>CGST : Rs.<s:property value="cgst" /></p>
				   								<p>SGST : Rs.<s:property value="sgst" /></p>
				   								</div>
				   								<div class="col-lg-4 col-sm-4 col-xs-4 col-md-4 text-right" style="padding: 0px;">
				   								<p style="font-weight: bold;font-size: 13px;color:green;">Total : Rs.<s:property value="total" /></p>
				   								<p>Received : Rs.<s:property value="billpayamt"/></p>
				   								<p>Balance : Rs.<s:property value="balance" /></p>
				   								</div>
				   								</div>
				   								</div> 
				   								<table class="hidden-lg hidden-md visible-print hidden-print">
									           		<tr>
									           			<td><b>Sub Total :</b></td>
									           			<td>Rs.<s:property value="subtotal"/></td>
									           			<td><b>CGST :</b></td>
									           			<td>Rs.<s:property value="cgst"/></td>
									           			<td><b>Total :</b></td>
									           			<td>Rs.<s:property value="total"/></td>
									           			<td></td>
									           			<td><b>Received :</b></td>
									           			<td>Rs.<s:property value="billpayamt"/></td>
									           		</tr>
									           		
									           		<tr>
									           			<td><b>Discount(%) :</b></td>
									           			<td>Rs.<s:property value="discount"/></td>
									           			<td><b>SGST :</b></td>
									           			<td>Rs.<s:property value="sgst"/></td>
									           			<td></td>
									           			<td></td>
									           			<td></td>
									           			<td><b>Balance :</b></td>
									           			<td>Rs.<s:property value="balance"/></td>
									           		</tr>
									           		
							           			</table>
				   								 
					                            <!-- <div class="" style="border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;">
					                           
					                            <div class="text-right">
					                            	<div class="" style="">
					                            	<h4 style="font-size: 13px;">Sub Total : Rs.482.00</h4>
					                            	<h4 style="color: #868686;font-size: 13px;">Discount(%) : Rs.30.00</h4>
					                            	<h4 style="font-size: 13px;">Vat : Rs.100.00</h4>
					                            	<h4 style="font-weight: bold;font-size: 13px;color:green;">Total : Rs.582.00</h4>
					                            </div>
					                            </div>
					                            
											</div> -->
					                        </div>
					                         <table class="hidden-lg hidden-md visible-print hidden-print">
							           		<tr></tr>
							           	</table>
					                        </s:iterator>
					                       </div>  
							        	
							        	
							      </div>
							     <!--   <a href="#"  onclick="exportF(this)" title="Download CSV file" style="line-height: 26px;" class="d-print-none"><i class="fa fa-download"></i></a> &ensp;&ensp;&ensp; -->
							      <button type="button" onclick="printReport()" style="float: right;" class="btn btn-primary">Download xls</button>
							    	<!-- <a href="#" onclick="$('#history').tableExport({type:'excel',escape:'false'});">Test</a> -->
							    </div>
							
							
							
							
							
							
							
							
							
							
							
							<script src="dtr/assets/js/vendor/hichart/highcharts.js"></script>
							<script src="dtr/assets/js/vendor/hichart/exporting.js"></script>
							<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '450px'
								    });
								     $('.tableheiset').slimScroll({
								        height: '500px'
								    });
								});
							</script>
							
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
	
	
	
	<script>
    function printDiv2(divID) {
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