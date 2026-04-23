<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
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
}
hr {
    margin-top: 5px;
    margin-bottom: 5px;
    border: 0;
    border-top: 2px solid rgba(119, 119, 119, 0.6);
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
		   document.addEventListener("contextmenu", function(e){
	    		e.preventDefault();
	    		}, false);          
    };
  
    function submitit(){
    
        document.getElementById("formbal").submit();
    }


</SCRIPT>



</head>
<body>
						<div class="row details mainheader hidden">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Sale Report</h4>
								</div>
							</div>
							
							
							<div class="row ">
								
							</div>
							
							
							
							<div class="row">
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
									<%-- <div class="hidden-print">
										<ul class="breadcrumb">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
											<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
												<%if(breadcrumbs.isIscurrent()){ %>
													<li><%=breadcrumbs.getName()%></li>
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
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4">
										
									</div>
									<div class="col-lg-4 col-xs-4 col-sm-4 col-md-4" style="padding: 0px;border: 1px solid #ddd;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<a style="display: none;" href="#" onclick="opencPopup('getPatientRecordEmr')" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Create
										Prescription </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form class="form-inline search" action="reportpriscPharmacy" method="post">
										  <div class="form-group hidden" style="width: 20%;">
										   <s:textfield name="searchText" id="searchText" cssClass="form-control" cssStyle="width:100%;" placeholder="Search Patient / ID / Bill No"/>
										  </div>
										    <s:textfield cssClass="form-control" name="fromdate" id="fromdate" placeholder="From Date" cssStyle="width:30%" />
										    <s:textfield  cssClass="form-control" name="todate" id="todate" placeholder="To Date" style="width:30%" />
										    <select name="from" class="form-control hidden">
											  <option value="0">Select</option>
											  <option value="IPD">IPD</option>
											  <option value="OPD">OPD</option>
											  <option value="NEW">New</option>
											</select>
											<!--<select class="form-control" name="report">
											  <option value="0">Report</option>
											  <option value="Daily">Daily</option>
											  <option value="Monthly">Monthly</option>
											</select>
										  	-->
										  	  <select name="paymode" class="form-control hidden">
											  <option value="0">Select</option>
											  <option value="Cash">Cash</option>
											  <option value="Card">Card</option>
											  <option value="Credit">Credit</option>
											</select>
											
											<SELECT name="isreturn" class="form-control hidden">
											 
											   <option value="0">Sale</option>
											   <option value="1">Return</option>
											</SELECT>
											
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="printDiv2('page_printer3');" class="btn btn-sm btn-warning hidden-print" style="float: right;">Print</a>
										<form>
										</div>
										</div>
								</div>
								
								<div class="col-lg-12 col-xs-12 col-md-12" id="page_printer3" style="padding: 0px;">
									
										<div class="text-center">
											<h3><s:property value="clinicName"/></h3>
											<h6 class="hidden-lg hidden-md visible-print"><s:property value="clinicaddress"/></h6>
											<h6 class="hidden-lg hidden-md visible-print">Website:<s:property value="websiteurl"/>, Email:<s:property value="email"/>, Contact : <s:property value="landLine"/></h6>
											<h4><s:property value="location"/></h4>
											(From- <s:property value="fromdate"/> To <s:property value="todate"/>)
										</div>
											
										
										<div id="container" class="hidden" style="min-width: 310px; height: 300px; max-width: 600px; margin: 0 auto"></div>
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: rgba(224, 93, 111, 0.23);    margin-top: 15px;">
								           <div class="col-lg-12 col-md-12 col-xs-12">
								          	<p> <h4 class="text-left"><b>Credit :</b> <span style="float: right;"><b>Rs.<s:property value="totalcredit" /></b></span></h4></p>
								          	<p> <h4 class="text-left"><b>Payment Against Credit :</b> <span style="float: right;"><b>Rs.<s:property value="todayagainstcredit" /></b></span></h4></p>
								          	<p class=""><h4 class="text-left"><b>Total Credit :</b><span style="float: right;"><b style="color:red;">Rs.<s:property value="finalcredit" /></b></span></h4></p>
								           	<p> <h4 class="text-left"><b>Hospital :</b> <span style="float: right;"><b>Rs.<s:property value="hospital" /></b></span></h4></p>
											<p><h4 class="text-left"><b>Hospital Return :</b><span style="float: right;"><b style="color:red;">Rs.<s:property value="hospitalReturn" /></b></span></h4></p>
											<p><h4 class="text-left"><b>Credit Return :</b><span style="float: right;"><b style="color:red;">Rs.<s:property value="creditReturn" /></b></span></h4></p>
											<p><h4 class="text-left"><b>Online Payment Return :</b><span style="float: right;"><b style="color:red;">Rs.<s:property value="onlinepaymentreturn" /></b></span></h4></p>
											<p><h4 class="text-left"><b>Card Return :</b><span style="float: right;"><b style="color:red;">Rs.<s:property value="cardreturn" /></b></span></h4></p>
								          </div>
								          </div>
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: #f9f9f9;">
											<div class="col-lg-12 col-xs-12 col-md-12">
												<p><h4 class="text-left"><b>Cash :</b> <span style="float: right;"><b style="color:green;">Rs.<s:property value="todaycash" /></b></span></h4> </p>
												<p><h4 class="text-left"><b>Card :</b> <span style="float: right;"><b style="color:green;">Rs.<s:property value="todaycard" /></b></span></h4> </p>
												<p><h4 class="text-left"><b>Cheque :</b> <span style="float: right;"><b style="color:green;">Rs.<s:property value="chequepayment" /></b></span></h4> </p>
												<p><h4 class="text-left"><b>NEFT/RTGS :</b> <span style="float: right;"><b style="color:green;">Rs.<s:property value="neftpayment" /></b></span></h4> </p>
												<p><h4 class="text-left"><b>Online Payment :</b> <span style="float: right;"><b style="color:green;">Rs.<s:property value="onlinepayment" /></b></span></h4> </p>
												<hr>
												<p><h4 class="text-left" style="color: green;"><b>Total Received :</b> <span style="float: right;"><b style="color: green;">Rs.<s:property value="totalpayment" /></b></span></h4></p>
												<hr>
												
												<p><h4 class="text-left"><b>Cash Return :</b><span style="float: right;"><b style="color:red;">Rs.<s:property value="todayReturn" /></b></span></h4></p>
												
												<p><h4 class="text-left"><b>Discount :</b><span style="float: right;"><b style="color:red;">Rs.<s:property value="todaydisc" /></b></span></h4></p>
												<hr>
												<p><h4 class="text-left"><b>Closing Balance :</b><span style="float: right;"><b>Rs.<s:property value="total" /></b></span></h4></p>
												
											</div>
								          
								          </div>
								          <div class="col-lg-12 col-xs-12 col-md-12 hidden-lg hidden-md visible-print">
								          	<div class="col-lg-12 col-xs-12 col-md-12 text-right" style="margin-top: 15px;">
								          		<p><s:property value="userid" /><br><s:property value="dateTime" /></p>
								          	</div>
								          </div>	
							
									<table class="table table-bordered table-striped hidden" cellspacing="0" width="100%" >
                                <thead>
                                <tr class="tableback">
                                		<th style="width:3%;"></th>
                                        <th>Patient Name</th>
                                        <th>Date</th>
                                        <th>Contact</th>
                                        <th>Balance</th>
                                        <th>Total Amount</th>
                                        <th>Payment mode</th>
                                       	<th></th>
                                       	<th></th>
                                    </tr>
                                </thead>
                                	  <tbody>
                                	 <s:iterator value="salesreportList">
                                     <tr>
                                    	<td>
                                    	    <s:if test="outp==1">
                                    	        <img src="emr/img/medicineflag_new_small.png" class="img-responsive imflag"></img>
                                    	    </s:if> 
                                    	    <s:elseif test="ipdid==0">
                                    	    	<img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag"></img>	
                                    	    </s:elseif>
                                    	    <s:else>
                                    	      <img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag"></img>
                                    	    </s:else>
                                    	</td>
                                        <td><s:property value="fullname"/></td>
                                        <td><s:property value="date"/></td>
                                        <td><s:property value="mobile"/></td>
                                        <td style="color: #d9534f;">Rs.<s:property value="balance"/></td>
                                        <td>Rs.<s:property value="totalamt"/></td>
                                        <td><s:property value="paymode"/></td>
                                       <td>
                                            <!--<s:if test="balance>0">
                                               <s:if test="outp==1">	  
                                                <a href="#" onclick="clearBal(1,<s:property value="pclientid"/>,<s:property value="balance"/>,'<s:property value="fullname"/>')" >clear balance</a>
                                                </s:if>
                                                <s:else>
                                                  <a href="#" onclick="clearBal(0,<s:property value="clientId"/>,<s:property value="balance"/>,'<s:property value="fullname"/>')" >clear balance</a>
                                                </s:else>
                                            </s:if>
                                       --></td>
										  <s:if test="outp==1">	                                       
                                        	<td><a href="#" onclick="openHistory(<s:property value="pclientid"/>,1)" >history</a></td>
                                         </s:if>
                                         <s:else>
                                             <td><a href="#" onclick="openHistory(<s:property value="clientId"/>,0)" >history</a></td>
                                         </s:else>
                                         
                                    </tr>
                                    </s:iterator>   
                                    <!--<tr>
                                    	<td><img src="emr/img/medicineflag_new_small.png" class="img-responsive imflag"></img></td>
                                        <td>Mrs. Ruchita Google</td>
                                        <td>Female/27</td>
                                        <td>GENRAL/18</td>
                                        <td>9568245625</td>
                                        <td>Rs.1250.00</td>
                                        <td>Rs.7254.00</td>
                                        <td>Rs.00.00</td>
                                        <td>Cash</td>
                                        <td><a href="#" data-toggle="modal" data-target="#history">history</a></td>
                                    </tr>
                                    <tr>
                                    	<td><img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag"></img></td>
                                        <td>Mrs. Arun Varma</td>
                                        <td>Female/25</td>
                                        <td>GENRAL/18</td>
                                        <td>9568245625</td>
                                        <td>Rs.00.00</td>
                                        <td>Rs.1254.00</td>
                                        <td>Rs.00.00</td>
                                        <td>Cash</td>
                                        <td><a href="#" data-toggle="modal" data-target="#history">history</a></td>
                                    </tr>
                                --></tbody>
                            </table>
								</div>
								
										
									</div>
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
								</div>
							</div>
							
							<!-- Modal -->
       <div id="clearbal" class="modal fade" role="dialog">
       
      
         <div class="modal-dialog modal-sm">
           <!-- Modal content-->
           
           <div class="modal-content">
           
             <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
              
               <h4 class="modal-title">Receipt from (<label id="patiName"></label>) </h4>
             </div>
             <div class="modal-body">
               <s:form action="getbalpaymentPharmacy" theme="simple" id="formbal" method="post" >
                <input type="hidden" name="clientid" id="pid" />
               <input type="hidden" name="flag" id="flag" />
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              <label for="email">Payment Amount</label>
              <input type="text" id="bal" name=payamt class="form-control" >
            </div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              <label for="email">Date</label>
              <s:textfield  cssClass="form-control" name="dateTime" id="date" />
            </div>
               </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              <label for="email">Payment Mode</label>
              <select class="form-control" onchange="hideCardDiv(this.value)" name="paymode1" id="paymode">
               <option value="Cash">Cash</option>
               <option value="Card">Card</option>
               <option value="Paytm">Paytm</option>
              </select>
            </div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6">
                 <div class="form-group hidden" id="carddiv">
                  <label for="email">Card No</label>
             	 <input type="text" class="form-control" name="cardno" id="cardno" placeholder="Last 4 digit no">
            </div>
               </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12">
            <div class="form-group">
              <label for="email">Payment Notes</label>
              <input type="text" name="paynotes" id="paynotes" class="form-control">
            </div>
             
               </div>
               </s:form>
             </div>
                
             <div class="modal-footer">
               <input type="button" class="btn btn-primary" onclick="paybalance()" value="Pay" />
             </div>
           </div>
          
       
         </div>
     
       </div>
							
							
							
							
							<!-- Patient History Modal -->
							<div id="historymodal" class="modal fade" role="dialog">
							  <div class="modal-dialog">
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title" id="maintitle">Sales Summary</h4>
							      </div>
							      <div class="modal-body">
							        <div id="page_printer" class="minhesigh">
							        <h4 class="modal-title visible-print hidden-md hidden-lg hidden-sm" id="hiddentitle">Mrs. Khushi Gupta | Female/20 | GENRAL/18 | 9568245625</h4>
							        
							        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
							        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="letterHead">
							        		<h4>N.P.M PHARMACY UNIT OF TREATME HOSPITAL PVT.LTD</h4>
							        		<h5>7,Hisdustan colony, wardha road, nagpur -440015</h5>
							        		<h5>Websit:www.google.com, Email:, Contact : 7066044412</h5>
							        	</div>
							        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
							        		<h4><b>Sales Summary</b></h4>
							        	</div>
							        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;" id="billanddata">
							        		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
							        			<p class="marboset"><b>Registration No :</b><span>IPD-17-18-62</span></p>
								        		<p class="marboset"><b>Patient Name :</b><span>Mr.Abhinav Parmar</span></p>
								        		<p class="marboset"><b>Address :</b><span>Duttwadi, Nagpur</span></p>
								        		<p class="marboset"><b>Contact No :</b><span>8446545681</span></p>
								        		<p class="marboset"><b>Admission Date :</b><span>24/04/2017</span></p>
								        	</div>
								        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								        		<p class="marboset"><b>Bill Amount :</b><span class="pull-right">Rs.23069.73</span></p>
								        		<p class="marboset"><b>Balance :</b><span class="pull-right">Rs.430.00</span></p>
								        		<p class="marboset"><b>Discount :</b><span class="pull-right" style="color: #d9534f;">- Rs.47.00</span></p>
								        		<p class="bonetamt"><b>Round Off :</b><span class="pull-right" style="color: #d9534f;">- Rs.00.73</span></p>
								        		<p><b>Net Amount :</b><span class="pull-right">Rs.23022.00</span></p>
								        	</div>
							        	</div>
							        	
							        </div>
							         
							           <div id="history"> 
							        	<b style="color: #e69e18;font-weight: normal;">Prescription Date: 01-01-2017</b>
							        		<div class="">
					                            <table class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                         
					                                        <th style="width: 38%;">Name of Drug</th>
					                                        <th style="width: 4%;">Pkg</th>
					                                        <th style="width: 4%;">Mfg</th>
					                                        <th style="width: 6%;">Batch No</th>
					                                        <th style="width: 8%;">Exp. Dt</th>
					                                        <th style="width: 4%;">Qty</th>	 
					                                        <th style="width: 6%;" class="text-right">Amount</th>
					                                    </tr>
					                                </thead>
					                                
					                                	  <tbody>
					                                	  
					                                    <tr>
					                                        
					                                        <td>CARDACE TAB (2.5 MG)</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					                                   <tr>
					                                        <td>TAMFLOW S</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					                                    <tr>
					                                        <td>TAMFLOW S</td>
					                                        <td>6</td>
					                                        <td>ZUV</td>
					                                        <td>123456</td>
					                                        <td>MAR-2017</td>
					                                        <td>10</td>
					                                        <td class="text-right">Rs.120.00</td>
					                                    </tr>
					
					                                </tbody>
					                              
					                            </table>
					                            <div class="" style="border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;">
					                           
					                            <div class="text-right">
					                            	<div class="" style="">
					                            	<h4 style="font-size: 13px;">Sub Total : Rs.482.00</h4>
					                            	<h4 style="color: #868686;font-size: 13px;">Discount(%) : Rs.30.00</h4>
					                            	<h4 style="font-size: 13px;">Vat : Rs.100.00</h4>
					                            	<h4 style="font-weight: bold;font-size: 13px;color:green;">Total : Rs.582.00</h4>
					                            </div>
					                            </div>
					                            
											</div>
					                        </div>
					                       </div>  
							        	
							        	
							      </div>
							      <div class="modal-footer">
							      	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							        <button type="button" class="btn btn-primary" onclick="printDiv('page_printer');">Print</button>
							      </div>
							    </div>
							
							  </div>
							</div>
							
							
							
							
							
							
							
							
							
							
							
							
							<script src="dtr/assets/js/vendor/hichart/highcharts.js"></script>
							<script src="dtr/assets/js/vendor/hichart/exporting.js"></script>
							
							
							<script>
								$(function () {

								    $(document).ready(function () {
								
								        // Build the chart
								        Highcharts.chart('container', {
								            chart: {
								                plotBackgroundColor: null,
								                plotBorderWidth: null,
								                plotShadow: false,
								                type: 'pie'
								            },
								            title: {
								                text: 'Summary'
								            },
								            tooltip: {
								                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
								            },
								            plotOptions: {
								                pie: {
								                    allowPointSelect: true,
								                    cursor: 'pointer',
								                    dataLabels: {
								                        enabled: false
								                    },
								                    showInLegend: true
								                }
								            },
								            series: [{
								                name: 'Sale',
								                colorByPoint: true,
								                data: [{
								                    name: 'OPD Patient',
								                    y: <%=priscription.getOpdper()%>
								                }, {
								                    name: 'IPD Patient',
								                    y: <%=priscription.getIpdper()%>
								                }, {
								                    name: 'New Patient',
								                    y: <%=priscription.getNewper()%>
								                }]
								            }]
								        });
								    });
								});
							</script>
							
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