<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>



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
.setboback{
	    background-color: beige;
    border: 1px solid rgba(221, 221, 221, 0.21);
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
.setboback{
	background-color: #efefef !important;
}
h4, .h4, h5, .h5, h6, .h6 {
    margin-top: 3.5px !important;
    margin-bottom: 3.5px !important;
    font-size: 9px !important;
}
}
p {
    margin: 0 0 2.5px;
}
</style>

<style>
	hr {
    margin-top: 8px;
    margin-bottom: 8px;
}
.imflag{
	display: inline-block;
    width: 88%;
}
.bg-lightred {
    background-color: #e05d6f !important;
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
		   
		   document.addEventListener("contextmenu", function(e){
	    		e.preventDefault();
	    		}, false); 
  
           
});

</SCRIPT>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
<% ArrayList<Priscription> dailyuserreport = (ArrayList<Priscription>) session.getAttribute("dailyuserreport"); %>


</head>
<body>

<div class="row">
								<div class="col-lg-12 col-md-12 col-xs-12">
								<%-- <div class="hidden-print">
											<ul class="breadcrumb">
												&nbsp;
												<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
												<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
													<%if(breadcrumbs.isIscurrent()){ %>
														<li><%=breadcrumbs.getShowingname()%></li>
													<%}else{ %>
														<%if(breadcrumbs.getOn()){ %>
															<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
														<%}else{ %>
															<li><%=breadcrumbs.getName()%></li>
														<%} %>
													<%} %>
													
												<%} %>
											</ul>
										</div> --%>
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4" style="border-right: 1px solid #ddd;">
										<div id="container" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
									</div>
									<div class="col-lg-8 col-xs-8 col-sm-8 col-md-8" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form class="form-inline search" action="userreportPharmacy" method="post">
											<div class="form-group">
										    <s:textfield readonly="true" cssClass="form-control" name="fromdate" id="fromdate" placeholder="From Date"  />
											</div>
											
											<div class="form-group">
										    <s:textfield readonly="true"  cssClass="form-control" name="todate" id="todate" placeholder="To Date"  />
										   	</div>
										 
										   	<%if(loginfo.getJobTitle().equals("Admin")|| loginfo.getUserType()==2){ %>
										   	<div class="form-group">
										    <s:select list="pharmacyUserList"   theme="simple" name="userid" cssClass="form-control chosen-select" listKey="userid" listValue="fullname" headerKey="0" cssStyle="width:15%" headerValue="Select User" >
											</s:select>
											</div>
											  	<%} %>
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" style="float: right;" onclick="printDiv('page_printer');" class="btn btn-primary">Print</a>
										</form></div>
										</div>
								</div>
								
								<div class="col-lg-12 col-xs-12 col-md-12" id="page_printer" style="padding: 0px;">
								<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
																<div class="col-lg-12 col-xs-12 col-md-12 hidden-lg hidden-md visible-print" style="border-bottom: 4px double #ddd;">
	                                	 <center><h3>COLLECTION REPORT (USER WISE)</h3></center> 
	                              </div>
	                               <div class="col-lg-12 col-xs-12 hidden" style="margin-top: 8px;margin-bottom: 8px;font-size: 13px;padding: 0px;">
	                                 <s:iterator value="locationWiseReport">
                                      
                                        <div class="col-lg-3 col-md-3 col-xs-3" style="border-right: 1px solid #ddd;">
                                    <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                                     <center>
                                      <p style="color: cornflowerblue;font-weight: bold;"><s:property value="location" /></p>
                                      <p style="font-weight: bold;">Rs.<s:property value="total" /></p>
                                     </center>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                                     <p><span style="text-align:left;">Credit :</span><span style="float: right;">Rs.<s:property value="balance" /></span></p>
                                     <p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="totalcash" /></span></p>
                                     <p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="totalcard" /></span></p>
                                     <p><span style="text-align:left;">Cheque :</span><span style="float: right;"><s:property value="chequepayment" /></span></p>
                                     <p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;"><s:property value="neftpayment" /></span></p>
                                     <p><span style="text-align:left;">Return :</span><span style="float: right;">Rs.<s:property value="refundtotal" /></span></p>
                                    </div>
                                   </div>
                                   
                                    </s:iterator>
                                   
                                  </div>
                                  
                                  <div class="col-lg-12 col-xs-12 col-md-12 setboback" style="background-color: #e88e8e;border-bottom: 1px solid #ddd;border-top: 1px solid #ddd;color: #fff;">
                                	  <h4>GRAND TOTAL</h4>
                                	</div>
								<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;border: 1px solid #ddd;background-color: #f5f5f5;">
                                	 <div class="col-lg-4 col-xs-4 col-sm-4 col-md-4">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Received</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="todaycash"/></span></p>
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="todaycard"/></span></p>
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="chequepayment" /></span></p>
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="grandneftnetco" /></span></p>
										<p><span style="text-align:left;">Online Payment :</span><span style="float: right;">Rs.<s:property value="todayonline"/></span></p>
										<p><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="netReceivedTotal"/></span></p>
										<p><span style="text-align:left;color: green;font-weight: bold;">Credit :</span><span style="float: right;">Rs.<s:property value="newcredit"/></span></p>
									</div>
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Refund</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="todayReturn"/></span></p>
										<p><span style="text-align:left;">Card :</span><span style="float: right;">-</span></p>
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">-</span></p>
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="totalneftamtref"/></span></p>
										<p><span style="text-align:left;">Online Payment :</span><span style="float: right;">-</span></p>
										<p><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="finalrettotal"/></span></p>
										<p><span style="text-align:left;color: orange;font-weight: bold;">Credit Return :</span><span style="float: right;">Rs.<s:property value="totalcreditReturn"/></span></p>
									</div>
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4" style="color: green;">
										<h6 style="color:	 chocolate;font-weight: bold;">Net Collection</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="netcash"/></span></p>
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="netcard"/></span></p>
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="chequepayment" /></span></p>
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="neftpayment" /></span></p>
										<p><span style="text-align:left;">Online Payment :</span><span style="float: right;">Rs.<s:property value="todayonline" /></span></p>
										<p><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="nettotal"/></span></p>
										<p><span style="text-align:left;color: green;font-weight: bold;">Payment Against Credit :</span><span style="float: right;">Rs.<s:property value="todayagainstcredit"/></span></p>
									</div>
                                	  	</div>
								<%int i=0;%>
                                	  <s:if test="dailyuserreport!=null">
                                	  	<s:iterator value="dailyuserreport"> 
                                	  	<%++i;%>
                                	  	
                                	<div class="col-lg-12 col-xs-12 col-md-12 setboback" style="background-color: #efefef;border-bottom: 1px solid #ddd;border-top: 1px solid #ddd;">
                                	  <h4><a href="#" onclick="openbillHistory('<s:property value="userid"/>')"><s:property value="fullname" /></a> (<s:property value="location"/> | <s:hidden id="userid" name="userid"></s:hidden><s:property value="userid"/>) <small style="float: right;">Bills Created:<s:property value="billcount"/></small></h4>
                                	</div>
                                	<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px">
                                	 <div class="col-lg-4 col-xs-4 col-sm-4 col-md-4">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Received</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="totalcash"/></span></p>
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="totalcard"/></span></p>
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="totalcheque"/></span></p>
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="totalneft"/></span></p>
										<p><span style="text-align:left;">Online Payment :</span><span style="float: right;">Rs.<s:property value="totalonline"/></span></p>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="receivedtotal"/></span></b></p>
										<p><span style="text-align:left;color: green;font-weight: bold;" >Credit :</span><span style="float: right;">Rs.<s:property value="newcredit"/></span></p>
										</div>
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Refund</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="totalReturn"/></span></p>
										<p><span style="text-align:left;">Card :</span><span style="float: right;">-</span></p>
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">-</span></p>
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;"><s:property value="totalreturnneft"/></span></p>
										<p><span style="text-align:left;">Online Payment :</span><span style="float: right;">-</span></p>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="totalneftnetamt"/></span></b></p>
										<p><span style="text-align:left;color: chocolate;font-weight: bold;">Credit Return :</span><span style="float: right;">Rs.<s:property value="totalcreditReturn"/></span></p>
									</div>
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4" style="color: green;">
										<h6 style="color: chocolate;font-weight: bold;">Net Collection</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="netcash"/></span></p>
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="netcard"/></span></p>
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="totalcheque"/></span></p>
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="totalneftamtrec"/></span></p>
										<p><span style="text-align:left;">Online Payment :</span><span style="float: right;">Rs.<s:property value="totalonline"/></span></p>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="nettotal"/></span></b></p>
										<p><span style="text-align:left;color: green;font-weight: bold;">Payment Against Credit :</span><span style="float: right;">Rs.<s:property value="todayagainstcredit"/></span></p>
									</div>
                                	  	</div>
                                	
                                   	 </s:iterator>
                                    </s:if>
									<!--<td class="hidden">Rs.<s:property value="totalpayment"/></td>
								-->
								
								
								</div>
									</div>
								</div>
							</div>
							
							
							
							<!-- Modal -->
							<div id="viewbill" class="modal fade" role="dialog">
							  <div class="modal-dialog">
							
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title" id="maintitle">View Bills : Ravi Verma</h4>
							      </div>
							      <div class="modal-body">
							        <div id="page_printer" class="minhesigh">
							           <div id="test">
							                <h4 class="modal-title visible-print hidden-md hidden-lg hidden-sm">Ravi Verma (Ravi_01)</h4>
							        
							                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;">
									        	  		<h4>N.P.M PHARMACY UNIT OF TREATME HOSPITAL PVT.LTD</h4>
									        			<h5>7,Hisdustan colony, wardha road, nagpur -440015</h5>
									        			<h5>Websit:www.google.com, Email:, Contact : 7066044412</h5>
									        		</div>
								        	      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								        		  <h4><b>User Wise Summary</b></h4>
								        	    </div>
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;">
									        			<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
										        			<p class="marboset"><b>User Name :</b><span>Abhinav Parmar</span></p>
										        			<p class="marboset"><b>Contact No :</b><span>8446545681</span></p>
										        	</div>
										        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
										        		<p class="marboset"><b>Cash Collected :</b><span class="pull-right">Rs.23069.73</span></p>
										        		<p class="marboset"><b>Card Collected :</b><span class="pull-right">Rs.430.00</span></p>
										        		<p class="marboset"><b>Cash Return :</b><span class="pull-right">Rs.430.00</span></p>
										        		<p class="marboset bonetamt"><b>Discount :</b><span class="pull-right" style="color: #d9534f;">- Rs.47.00</span></p>
										        		<p><b>Net Amount :</b><span class="pull-right">Rs.23022.00</span></p>
										            </div>
							        	    </div>
							          <div>
							        	</div>
							        </div>
							        </div>
							           <div id="history"> 
							        	
							        		<div class="">
							        		
					                            <table class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                         
					                                        <th style="width: 38%;">Name of Drug</th>
					                                        <th style="width: 4%;">Mfg</th>
					                                        <th style="width: 6%;">Batch No</th>
					                                        <th style="width: 8%;">Exp. Dt</th>
					                                        <th style="width: 4%;">Qty</th>	 
					                                        <th style="width: 4%;">Shelf</th>	
					                                        <th style="width: 6%;" class="text-right">Amount</th>
					                                    </tr>
					                                </thead>
					                                
					                                	  <tbody>
					                                	  
					                                    <tr>
					                                        
					                                        <td>CARDACE TAB (2.5 MG)</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td>A6</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					                                   <tr>
					                                        <td>TAMFLOW S</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td>A6</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					                                    <tr>
					                                        <td>TAMFLOW S</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td>A6</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					
					                                </tbody>
					                              
					                            </table>
					                            <div class="" style="border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;">
					                           
					                            <div class="text-right">
					                            	<div class="" style="">
					                            	<h4 style="font-size: 13px;">Sub Total : Rs.482.00</h4>
					                            	<h4 style="color: #868686;font-size: 13px;">Discount(%) : Rs.30.00</h4>
					                            	<h4 style="font-weight: bold;font-size: 13px;color:green;">Total : Rs.582.00</h4>
					                            </div>
					                            </div>
					                            
											</div>
					                        </div>
					                       </div>  
							        	
							        	
							      </div>
							      <div class="modal-footer">
							      	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							        <button type="button" class="btn btn-primary" onclick="printDiv1('page_printer');">Print</button>
							      </div>
							    </div>
							     
							    </div>
							
							  </div>
							</div>
							
							
							
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
    function printDiv1(divID) {
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
							
							<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '450px'
								    });
								});
							</script>
							
							<script src="dtr/assets/js/vendor/hichart/highcharts.js"></script>
							<script src="dtr/assets/js/vendor/hichart/exporting.js"></script>
							
							<script>
										        
								Highcharts.chart('container', {
									    chart: {
									        type: 'bar'
									    },
									    title: {
									        text: 'User Wise Report'
									    },
									    xAxis: {
									    
									    	categories: [
									    	
									    	<%for(Priscription priscription:dailyuserreport){%>
									    	
									      	'<%=priscription.getUserid()%>',
									         <%}%>	
									    	],
									        title: {
									            text: null
									        }
									       
									    },
									    yAxis: {
									        min: 0,
									        title: {
									            text: 'Amount',
									            align: 'high'
									        },
									        labels: {
									            overflow: 'justify'
									        }
									    },
									    tooltip: {
									        valueSuffix: ' Rs collection'
									    },
									    plotOptions: {
									        bar: {
									            dataLabels: {
									                enabled: true
									            }
									        }
									    },
									    legend: {
									        layout: 'vertical',
									        align: 'right',
									        verticalAlign: 'top',
									        x: -40,
									        y: 80,
									        floating: true,
									        borderWidth: 1,
									        backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
									        shadow: true
									    },	
									    credits: {
									        enabled: false
									    },
									    series: [{
									        name: 'Total',
									        data: [<%for(Priscription priscription:dailyuserreport){%>
									    	
									      	<%=priscription.getNettotal()%>,
									         <%}%>	
									         ]
									    }]
									   
									});
									 
									 
							</script>





</body>
</html>