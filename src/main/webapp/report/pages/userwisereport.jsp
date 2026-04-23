<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>


<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

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
/*  @media print
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
}  */
p {
    margin: 0 0 2.5px;
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
		             
});
  

</SCRIPT>

</head>
<body class="reportprint">
<% ArrayList<Priscription> dailyuserreport = (ArrayList<Priscription>) session.getAttribute("dailyuserreport"); %>
<div class="row">
			<div class="">
							
		
								<!-- <div class="col-lg-12 col-md-12 col-xs-12">
									<div id=""> -->
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding: 0px;">
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<form class="form-inline search" action="userwisepaymentreportSummary" method="post">
										    <s:textfield cssClass="form-control" name="fromDate" id="fromdate" placeholder="From Date" cssStyle="width:10%" />
										    <s:textfield  cssClass="form-control" name="toDate" id="todate" placeholder="To Date" style="width:10%" />
										    <div class="form-group">
												<s:select name="username" list="uniquelist" cssClass="form-control chosen-select" headerKey="0" headerValue="All Users" ></s:select>
											</div>
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" style="float: right;" onclick="printpage()" class="btn btn-primary">Print</a>
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
								<div class="col-lg-12 col-xs-12 col-md-12" style="border-bottom: 4px double #ddd;">
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
                                  
                                  <div class="col-lg-12 col-xs-12 col-md-12 setboback " style="background-color: #e88e8e;border-bottom: 1px solid #ddd;border-top: 1px solid #ddd;color: #fff;">
                                	  <h4>GRAND TOTAL</h4>
                                	</div>
								<div class="col-lg-12 col-xs-12 col-md-12 " style="padding:0px;border: 1px solid #ddd;background-color: #f5f5f5;">
                                	 <div class="col-lg-3 col-xs-3 col-sm-3 col-md-3">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Received</h6>
										
										<s:if test="(ttcash!=0.0)||(rettcash!=0.0)||(adttcash!=0.0)">
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="ttcash"/></span></p>
										</s:if>
										<s:if test="(ttcard!=0.0)||(rettcard!=0.0)||(adttcard!=0.0)">
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="ttcard"/></span></p>
										</s:if>
										<s:if test="(ttcheque!=0.0)||(rettcheque!=0.0)||(adttcheque!=0.0)">
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="ttcheque" /></span></p>
										</s:if>
										<s:if test="(ttneft!=0.0)||(rettneft!=0.0)||(adttneft!=0.0)">
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="ttneft" /></span></p>
										</s:if>
										<s:if test="(ttupi!=0.0)||(rettupi!=0.0)||(adttupi!=0.0)">
										<p><span style="text-align:left;">UPI :</span><span style="float: right;">Rs.<s:property value="ttupi" /></span></p>
										</s:if>
										<p><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="gtrec"/></span></p>
										<p><b><span style="text-align:left;">OPD :</span><span style="float: right;">Rs.<s:property value="totalopdamount"/></span></b></p>
										<p><b><span style="text-align:left;">Invoice Amt :</span><span style="float: right;">Rs.<s:property value="debitamttotal"/></span></b></p>
									</div>
									
									<div class="col-lg-3 col-xs-3 col-sm-3 col-md-3">
										<h6 style="color: chocolate;font-weight: bold;">Amt. Refund</h6>
										
											<s:if test="(ttcash!=0.0)||(rettcash!=0.0)||(adttcash!=0.0)">
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="rettcash"/></span></p>
										</s:if>
										<s:if test="(ttcard!=0.0)||(rettcard!=0.0)||(adttcard!=0.0)">
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="rettcard"/></span></p>
										</s:if>
											<s:if test="(ttcheque!=0.0)||(rettcheque!=0.0)||(adttcheque!=0.0)">
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="rettcheque"/></span></p>
										</s:if>
										<s:if test="(ttneft!=0.0)||(rettneft!=0.0)||(adttneft!=0.0)">
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;"> Rs.<s:property value="rettneft"/></span></p>
										</s:if>
										<s:if test="(ttupi!=0.0)||(rettupi!=0.0)||(adttupi!=0.0)">
										<p><span style="text-align:left;">UPI :</span><span style="float: right;"> Rs.<s:property value="rettupi"/></span></p>
										</s:if>
										<p><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="gtadv"/></span></p>
										<p><b><span style="text-align:left;">IPD :</span><span style="float: right;">Rs.<s:property value="totalipdamount"/></span></b></p>
										<p><b><span style="text-align:left;">Invoice Balance Amt :</span><span style="float: right;">Rs.<s:property value="balanceamttotal"/></span></b></p>
									</div>
									<div class="col-lg-3 col-xs-3 col-sm-3 col-md-3">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Advance</h6>
										
											<s:if test="(ttcash!=0.0)||(rettcash!=0.0)||(adttcash!=0.0)">
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="adttcash"/></span></p>
										</s:if>
										<s:if test="(ttcard!=0.0)||(rettcard!=0.0)||(adttcard!=0.0)">
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="adttcard"/></span></p>
										</s:if>
										<s:if test="(ttcheque!=0.0)||(rettcheque!=0.0)||(adttcheque!=0.0)">
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="adttcheque"/></span></p>
										</s:if>
										<s:if test="(ttneft!=0.0)||(rettneft!=0.0)||(adttneft!=0.0)">
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="adttneft"/></span></p>
										</s:if>
										<s:if test="(ttupi!=0.0)||(rettupi!=0.0)||(adttupi!=0.0)">
										<p><span style="text-align:left;">UPI :</span><span style="float: right;">Rs.<s:property value="adttupi"/></span></p>
										</s:if>
										<p><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="gtref"/></span></p>
										<p><b><span style="text-align:left;">Investigation :</span><span style="float: right;">Rs.<s:property value="totalinvstamount"/></span></b></p>
									</div>
									<div class="col-lg-3 col-xs-3 col-sm-3 col-md-3">
										<h6 style="color:	 chocolate;font-weight: bold;">Net Collection</h6>
										
											<s:if test="(ttcash!=0.0)||(rettcash!=0.0)||(adttcash!=0.0)">
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="tcash"/></span></p>
										</s:if>
										<s:if test="(ttcard!=0.0)||(rettcard!=0.0)||(adttcard!=0.0)">
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="tcard"/></span></p>
										</s:if>
										<s:if test="(ttcheque!=0.0)||(rettcheque!=0.0)||(adttcheque!=0.0)">
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="tcheque" /></span></p>
										</s:if>
										<s:if test="(ttneft!=0.0)||(rettneft!=0.0)||(adttneft!=0.0)">
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="tneft" /></span></p>
										</s:if>
										<s:if test="(ttupi!=0.0)||(rettupi!=0.0)||(adttupi!=0.0)">
										<p><span style="text-align:left;">UPI :</span><span style="float: right;">Rs.<s:property value="tupi" /></span></p>
										</s:if>
										<p><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="tnettotal"/></span></p>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="totalitypeamount"/></span></b></p>
									</div>
                                	  	</div>
								<%int i=0;%>
                                	 
                                	  	<s:iterator value="userwisepaymentlist"> 
                                	  
                                	  	<%++i;%>
                                	  	
                                	<div class="col-lg-12 col-xs-12 col-md-12 setboback" style="background-color: #efefef;border-bottom: 1px solid #ddd;border-top: 1px solid #ddd;">
                                	  <h4><a href="#" onclick="openbillHistory('<s:property value="userid"/>')"><s:property value="username" /></a> (<s:property value="location"/> | <s:hidden id="userid" name="userid"></s:hidden><s:property value="userid"/>) <small style="float: right;"><s:property value="billcount"/></small></h4>
                                	</div>
                                	<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px">
                                	 <div class="col-lg-3 col-xs-4 col-sm-4 col-md-4">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Received</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="paymentbycash"/>.0</span></p>
										<s:if test="(paymentbyCCard!=0.0)||(refundbyCCard!=0.0)||(advancebyCCard!=0.0)">
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="paymentbyCCard"/></span></p>
										</s:if>
										<s:if test="(paymentbyCheque!=0.0)||(refundbyCheque!=0.0)||(advancebyCheque!=0.0)">
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="paymentbyCheque"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyNEFT!=0.0)||(refundbyNEFT!=0.0)||(advancebyNEFT!=0.0)">
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="paymentbyNEFT"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyUPI!=0.0)||(refundbyUPI!=0.0)||(advancebyUPI!=0.0)">
										<p><span style="text-align:left;">UPI :</span><span style="float: right;">Rs.<s:property value="paymentbyUPI"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyOther!=0.0)||(refundbyOther!=0.0)||(advancebyOther!=0.0)">
										<p><span style="text-align:left;">Other :</span><span style="float: right;">Rs.<s:property value="paymentbyOther"/></span></p>
										</s:if>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="totalpayment"/></span></b></p>
										<p><b><span style="text-align:left;">OPD :</span><span style="float: right;">Rs.<s:property value="totalopdamount"/></span></b></p>
										<p><b><span style="text-align:left;">Invoice Amt :</span><span style="float: right;">Rs.<s:property value="debitamttotal"/></span></b></p>
									</div>
									<div class="col-lg-3 col-xs-4 col-sm-4 col-md-4">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Refund</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="refundbycash"/>.0</span></p>
										<s:if test="(paymentbyCCard!=0.0)||(refundbyCCard!=0.0)||(advancebyCCard!=0.0)">
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="refundbyCCard"/></span></p>
										</s:if>
										<s:if test="(paymentbyCheque!=0.0)||(refundbyCheque!=0.0)||(advancebyCheque!=0.0)">
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="refundbyCheque"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyNEFT!=0.0)||(refundbyNEFT!=0.0)||(advancebyNEFT!=0.0)">
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="refundbyNEFT"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyUPI!=0.0)||(refundbyUPI!=0.0)||(advancebyUPI!=0.0)">
										<p><span style="text-align:left;">UPI :</span><span style="float: right;">Rs.<s:property value="refundbyUPI"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyOther!=0.0)||(refundbyOther!=0.0)||(advancebyOther!=0.0)">
										<p><span style="text-align:left;">Other :</span><span style="float: right;">Rs.<s:property value="refundbyOther"/></span></p>
										</s:if>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="totalrefund"/></span></b></p>
										<p><b><span style="text-align:left;">IPD :</span><span style="float: right;">Rs.<s:property value="totalipdamount"/></span></b></p>
										<p><b><span style="text-align:left;">Invoice Balance Amt :</span><span style="float: right;">Rs.<s:property value="balanceamttotal"/></span></b></p>
									</div>
									<div class="col-lg-3 col-xs-4 col-sm-4 col-md-4">
										<h6 style="color: chocolate;font-weight: bold;">Amt.Advance</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="advancebycash"/>.0</span></p>
										<s:if test="(paymentbyCCard!=0.0)||(refundbyCCard!=0.0)||(advancebyCCard!=0.0)">
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="advancebyCCard"/></span></p>
										</s:if>
										<s:if test="(paymentbyCheque!=0.0)||(refundbyCheque!=0.0)||(advancebyCheque!=0.0)">
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="advancebyCheque"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyNEFT!=0.0)||(refundbyNEFT!=0.0)||(advancebyNEFT!=0.0)">
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="advancebyNEFT"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyUPI!=0.0)||(refundbyUPI!=0.0)||(advancebyUPI!=0.0)">
										<p><span style="text-align:left;">UPI :</span><span style="float: right;">Rs.<s:property value="advancebyUPI"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyOther!=0.0)||(refundbyOther!=0.0)||(advancebyOther!=0.0)">
										
										<p><span style="text-align:left;">Other :</span><span style="float: right;">Rs.<s:property value="advancebyOther"/></span></p>
										</s:if>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="totaladvance"/></span></b></p>
										<p><b><span style="text-align:left;">Investigation :</span><span style="float: right;">Rs.<s:property value="totalinvstamount"/></span></b></p>
									</div>
									
									<div class="col-lg-3 col-xs-4 col-sm-4 col-md-4" style="color: green;">
										<h6 style="color: chocolate;font-weight: bold;">Net Collection</h6>
										<p><span style="text-align:left;">Cash :</span><span style="float: right;">Rs.<s:property value="totalcashpayment"/>.0</span></p>
										<s:if test="(paymentbyCCard!=0.0)||(refundbyCCard!=0.0)||(advancebyCCard!=0.0)">
										<p><span style="text-align:left;">Card :</span><span style="float: right;">Rs.<s:property value="totalccardpayment"/></span></p>
										</s:if>
										<s:if test="(paymentbyCheque!=0.0)||(refundbyCheque!=0.0)||(advancebyCheque!=0.0)">
										<p><span style="text-align:left;">Cheque :</span><span style="float: right;">Rs.<s:property value="totalchequepayment"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyNEFT!=0.0)||(refundbyNEFT!=0.0)||(advancebyNEFT!=0.0)">
										<p><span style="text-align:left;">NEFT/RTGS :</span><span style="float: right;">Rs.<s:property value="totalneftpayment"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyUPI!=0.0)||(refundbyUPI!=0.0)||(advancebyUPI!=0.0)">
										<p><span style="text-align:left;">UPI :</span><span style="float: right;">Rs.<s:property value="totalupipayment"/>.0</span></p>
										</s:if>
										<s:if test="(paymentbyOther!=0.0)||(refundbyOther!=0.0)||(advancebyOther!=0.0)">
										<p><span style="text-align:left;">Other :</span><span style="float: right;">Rs.<s:property value="totalotherpayment"/></span></p>
										</s:if>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="nettotal"/></span></b></p>
										<p><b><span style="text-align:left;">Total :</span><span style="float: right;">Rs.<s:property value="totalitypeamount"/></span></b></p>
									</div>
                                	  	</div>
                               
                                   	 </s:iterator>
                                   
									<!--<td class="hidden">Rs.<s:property value="totalpayment"/></td>
								-->
								
								
								</div>
							</div>
						</div>
					</div>
				<!-- </div> -->
							
							
							
							<!-- Modal -->
							<%-- <div id="viewbill" class="modal fade" role="dialog">
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
							</div> --%>
							
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
							
							<%-- <script>
										        
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
									 
									 
							</script> --%>





</body>
</html>