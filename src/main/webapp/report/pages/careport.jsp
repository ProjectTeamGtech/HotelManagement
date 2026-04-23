<%@page import="com.apm.Accounts.eu.entity.Invoice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<link rel="stylesheet" href="_assets/newtheme/css/main.css">

<%@page import="com.apm.main.common.constants.Constants"%>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<style>
.table>thead>tr>th, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 0px 14px 2px 5px !important;
    }
</style>

<script>
	$(document).ready(function() {

		$("#fromDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true
		});
	});
	
	function searchInvoice(invoiceid){
		document.getElementById("invsearchfrm").submit();
	}
	
	
	function printpageww(id){
		var bodydata=document.body.innerHTML;
		var  printpage=document.getElementById("div"+id);
		document.body.innerHTML=printpage.innerHTML;
		 window.print();
		document.body.innerHTML=bodydata; 
	}
</script>

<s:form action="invoiceCharges" id="invsearchfrm" theme="simple">
<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>CA Report</h4>
									</div>
								</div>
<div class=" hidden-print">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: #fff;padding-bottom: 10px;">
		<div class="col-lg-2 col-md-2">
			<label>Search Bill</label>
			<s:textfield onblur="searchInvoice(this.value)"  cssClass="form-control" name="invoicesearchid" id="" placeholder="Search by bill no"></s:textfield>
		</div>
		
		<div class="col-lg-2 col-md-2">
		<label>Invoice Type</label>
			<s:select name="invoicetype" id="invoicetype" 
				list="#{'0':'All','1':'OPD','2':'IPD','3':'INVESTIGATION','6':'Vaccination' }"
				cssClass="form-control"></s:select>
		</div>
		
		<%if(loginfo.getJobTitle().equals("Admin")){%>
			<div class="col-lg-2 col-md-2">
			<label>Invoice Category</label>
				<s:select name="invoicecategory" id="invoicecategory" 
					list="#{'0':'All','Primary':'Primary','Secondary':'Secondary' }"
					cssClass="form-control"></s:select>
			</div>
		<%} %>
		
		<div class="col-lg-1 col-md-1">
		<label>Self / Tp :</label>
		<s:select name="payby1" id="payby1" list="#{'':'All','Client':'Self','Third Party':'Third Party' }" cssClass="form-control"></s:select>
		</div>
		
		
		<div class="col-lg-1 col-md-1">
		<label>Paid / Not :</label>
		<s:select name="invpaid" id="invpaid" list="#{'':'All','1':'Paid','2':'Not Paid' }" cssClass="form-control"></s:select>
		</div>
		
		<div class="col-lg-1 col-md-1">
			<label>From Date</label>
			<s:textfield readonly="true" name="fromDate" id="fromDate" placeholder="Select From Date"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
	
		<div class="col-lg-1 col-md-1">
			<label>To Date:</label>
			<s:textfield readonly="true" name="toDate" id="toDate" placeholder="Select To Date"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		
		
		
		<div class="col-lg-1 col-md-1">
		<s:submit value="Go" theme="simple" cssClass="btn btn-primary" cssStyle="margin-top:21px;"></s:submit>
		</div>
		<div class="col-lg-1 col-md-1" style="">
		<br>
		<input type="button" class='btn btn-primary' value ='Print' onclick="printpage()">
		</div>
		<!-- <div class="col-lg-2 col-md-2">
			<a href="#" onclick="printpage();" class="btn btn-primary hidden-print" data-toggle="tooltip" title="Print" style="margin-top: -33px;position: absolute;margin-left: -18px;"><i class="fa fa-print"></i></a>
		</div> -->
	</div>
</div>
</s:form>

<br>
<%ArrayList<Invoice> list=(ArrayList<Invoice>)request.getAttribute("caInvoiceList"); %>
<%int i = 0; %>
<s:iterator value="caInvoiceList">
<%i++; %>

								
								<!--<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"></div>
										<div>
--><div class="row" id='div<%=i%>'>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
		
		  <section id="content">
                <div class="page page-shop-single-invoice">
                    <div class="pagecontent" style="margin-top: 0px;">
                        <div class="">
                           <div role="tabpanel" style="margin-top: -40px;">
                                <div class="">
                                    <!-- tab in tabs -->
                                    <div role="tabpanel" class="tab-pane active" id="details">
                                        <!-- row -->
                                        <div class="row">
                                        <div class="col-lg-12 col-md-12" style="    padding: 10px 27px 0px 29px;background-color: #efefef;margin-top: 12px;">
                                        	<h4>Bill No: <s:property value="id"/><s:if test="cancelled==1"><span style="padding-left: 80px;"><b style="color:white;background-color: red">Cancelled</b></span></s:if></h4>
                                        	<span class="controls pull-right" style="margin-top:15px;">
			                            		<a href="#" onclick="printpageww(<%=i %>)" class="btn btn-primary hidden-print" data-toggle="tooltip" title="Print" style="margin-top: -33px;position: absolute;margin-left: -18px;"><i class="fa fa-print"></i></a>
			                            	</span>
                                        </div><br>
                                            <!-- col -->
                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                <!-- tile -->
                                                <section class="tile tile-simple" style="margin-top: 0px;">
                                                    <!-- tile body -->
                                                    <div class="tile-body">
														<div class="row pt-20">

                                                            <!-- col -->
                                                            <div class="col-md-4 b-r">
                                                                <p class="text-uppercase text-strong mb-10 custom-font">
                                                                    INVOICE TO
                                                                </p>

                                                               <ul class="list-unstyled text-default  mb-20">
                                                                    <li><s:property value="payeename"/>,</li>
                                                                   <%--  <li><s:property value="payeeadress"/></li>
                                                                    <li><s:property value="payeeTown"/>, <s:property value="payeePostcode"/></li> --%>
                                                                   
                                                                </ul>
                                                            </div>
                                                            <!-- /col -->
                                                            <!-- col -->
                                                            <div class="col-md-4 b-r">
                                                                <p class="text-uppercase text-strong mb-10 custom-font">
                                                                    Invoice DETAIL
                                                                </p>
                                                                <ul class="list-unstyled text-default mb-20">
                                                                	<s:if test="payAmount > 0">
                                                                			<li><strong>Invoice No:</strong> <%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="invoiceid"/><%} %>
                                                                				(<s:property value="invoicename"/>)
                                                                				
                                                                			</li>
																	</s:if>
																	<s:else>
																			<li><strong>Invoice No:</strong> <%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="invoiceid"/><%} %>
																				(<s:property value="invoicename"/>)
																			</li>
																	</s:else>
                                                                    <li><strong>Invoice Date:</strong> <s:property value="date"/></li>
                                                                    <s:if test="itype==2">
                                                                      <li><strong>Ipd No:</strong> <s:property value="ipseqno"/></li>
                                                                    </s:if>
                                                                  
                                                                    <li><input type="hidden" name="hiddenclientid" id="hiddenclientid" value="<s:property value="clientId"/>"></li>
                                                                </ul>
                                                            </div>
                                                            <!-- /col -->
                                                            <!-- col -->
                                                            <div class="col-md-4">
                                                                <p class="text-uppercase text-strong mb-10 custom-font">CLIENT DETAIL</p>
                                                                <ul class="list-unstyled text-default  mb-20">
                                                                    <li><strong>Client Name : </strong><s:property value="client"/>,</li>
                                                                    <li><strong>Address : </strong><s:property value="address"/></li>
                                                                    <li><strong>Age/Sex:</strong> <s:property value="agegender"/> | <strong>D.O.B:</strong> <s:property value="dob"/></li>
                                                                    
                                                                     <s:if test="itype==2">
                                                                   		 <li><strong>Admission on:</strong> <s:property value="admissionDate"/></li>
                                                                  
                                                                   
                                                                     	 <li><strong>Discharge on:</strong> <s:property value="dischargeDate"/></li>
                                                                    </s:if>
                                                                    <s:if test="payby=='Third Party'">
                                                                    <li><strong>Policy No:</strong> <s:property value="policyNo"/></li>
																	</s:if>
                                                                    <li>
                                                                    
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                            <!-- /col -->
                                                        </div>
                                                    </div>
                                                    <!-- /tile body -->
                                                </section>
                                                <!-- /tile -->


                                                <!-- tile -->
                                                <section class="tile tile-simple invoicetop" style="margin-bottom: 10px;"> 
                                                    <!-- tile body -->
                                                    <div class="tile-body p-0">
                                                        <div>
                                                        <h1 class="text-center">INVOICE 
                                                        <s:if test="balancex==0">
													 	  <span class="paidbtn">Paid</span>
														</s:if>
														<s:else>
														<span class="unpaidbtn">Unpaid</span>
														</s:else>
                                                        </h1>
                                                            <table class="table table-bordered">
                                                                <thead class="primarycolr">
                                                                <tr>
                                                                    <th style="width: 80%;"><b>Charge Name</b></th>
                                                                    <th class="text-center"><b>Qty</b></th>
                                                                    <th class="text-right"><b>Unit Cost</b></th>
                                                                    <th class="text-right"><b>Total</b></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                  <s:iterator value="masterAssessmentList">
	                                                                <tr style="background-color: #e8e8e8">
	                                                                	<td><b>(<s:property value="name"/>)</b></td>
	                                                                	<td></td>
	                                                                	<td></td>
	                                                                	<td></td>
	                                                                </tr>
                                                                <s:iterator value="assesmentList">
                                                               
																<tr>
																	<td class="padletab">
																	<s:if test="dna==true">
																		(<s:property value="subchargeid"/>) <s:property value="appointmentType"/> <span style="color:red">(DNA)</span>
																	</s:if>
																	<s:else>
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(<s:property value="subchargeid"/>) <s:property value="appointmentType"/>
																	</s:else> 
																	</td>
																	<%-- <td><s:property value="appointmentType"/></td> --%>
																	<td class="text-center"><s:property value="quantity"/></td>
																	<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="charges"/></td>
																	<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="chargeTotal"/></td>
																</tr>
																</s:iterator>
																</s:iterator>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <!-- /tile body -->
                                                </section>
                                                <!-- /tile -->
                                            </div>
                                            <!-- /col -->
                                        </div>
                                        <!-- /row -->
                                        <!-- row -->
                                        <div class="row" style="margin-bottom: -35px;">
                                            <!-- col -->
                                            <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">
                                                <ul class="list-unstyled text-default  mb-20">
								             	<li class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;">
								             	<div class="col-md-6 col-lg-3 col-sm-6" style="padding-left: 0px;"><strong>Payment Mode</strong></div>
								             	<div class="col-md-2 col-lg-2 col-sm-2"><strong>:</strong></div>
								             	<div class="col-md-4 col-lg-7 col-sm-4" style="padding-left: 0px;"><s:property value="paymode"/></div>
								             	</li>
								             	
								             		<%String notes=DateTimeUtils.removeBreaks(list.get(i-1).getSubmitInvoiceNotes()); %>
								             	<%if(!notes.equals("")){ %>
								                <li class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;">
								                <div class="col-md-6 col-lg-3 col-sm-6" style="padding-left: 0px;"><strong>Cash Notes</strong></div>
								             	<div class="col-md-2 col-lg-2 col-sm-2"><strong>:</strong></div>
								             	
								             
								             	<div class="col-md-4 col-lg-7 col-sm-4" style="padding-left: 0px;"><%=notes %></div>
								                </li><br><br>
								                <%} %>
								                <li class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;">
								                <div class="col-md-6 col-lg-3 col-sm-6" style="padding-left: 0px;"><strong>Prepared By</strong></div>
								             	<div class="col-md-2 col-lg-2 col-sm-2"><strong>:</strong></div>
								             	<div class="col-md-4 col-lg-7 col-sm-4" style="padding-left: 0px;"><s:property value="preparedby"/></div>
								             	</li>
								           </ul>
								           
								           <table class='table'>
								           <s:if test="prepaymentList.size==0 && transactionList.size==0 && refundList.size==0"></s:if>
								           <s:else>
								           <thead> 
												<tr> 
													<th class="th8">Date</th> 
													<th class="th9">Receipt</th> 
													<th class="th11 text-right">Amount</th> 
													<th class="th11 text-left">Type</th> 
													
												</tr> 
										  </thead> 
										  </s:else>
								
								<s:iterator value="prepaymentList">
									<tr>
										<td><s:property value="date"/></td>
										<td>Receipt - (R.<s:property value="id"/>)(<s:property value="physical_payment_id"/>)</td>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										
										<s:if test="advref==0">
											<td>Advance Receipt</td>
										</s:if>
										<s:else>
											<td>Refund Receipt</td>
										</s:else>
									</tr>
								</s:iterator>
								<s:if test="refundList.size!=0">
								<s:iterator value="refundList">
									<tr>
										<td><s:property value="date"/></td>
										<td>Receipt - (RF.<s:property value="invoicee"/>)</td>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										<td>Refund Receipt</td>
									</tr>
							   </s:iterator>
							   </s:if>
							   <s:iterator value="transactionList">
									<s:if test="paymentmode!='prepayment'">
									<tr>
										<td><s:property value="fromDate"/></td>
										<td>Receipt - (R.<s:property value="id"/>)(<s:property value="physical_payment_id"/>)</td>
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										<td>Payment(<s:property value="paymentmode"/>)</td>
									</tr>
									</s:if>
							</s:iterator>
								
								           </table>
								           
								           
								           
                                             </div>
                                            <div class="col-md-3 col-lg-3 col-sm-5 col-xs-6 price-total" style="float:right">
                                                <!-- tile -->
                                                <section class="tile tile-simple bg-tr-black lter" style="margin-top: 0px;">
                                                    <!-- tile body -->
                                                    <div class="tile-body">
                                                        <ul class="list-unstyled">
                                                         	<li class="ng-binding col-lg-12 col-md-12 col-sm-12">
	                                                         	<div class="col-md-6 col-lg-6 col-sm-6"><strong class="inline-block w-sm mb-5">Total</strong></div>
												             	<div class="col-md-2 col-lg-2 col-sm-2"><strong>:</strong></div>
												             	<div class="col-md-4 col-lg-4 col-sm-4" style="padding-right: 0px;"><span style="float:right"><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></span></div>
                                                         	</li>
                                                            
                                                            <li class="ng-binding col-lg-12 col-md-12 col-sm-12">
	                                                            <div class="col-md-6 col-lg-6 col-sm-6"><strong class="inline-block w-sm mb-5">Discount <small>(<s:property value="discount"/>%)</small></strong></div>
												             	<div class="col-md-2 col-lg-2 col-sm-2"><strong>:</strong></div>
												             	<div class="col-md-4 col-lg-4 col-sm-4" style="padding-right: 0px;"><span style="float:right"><s:property value="discAmmount"/></span></div>
                                                            </li>
                                                          
                                                             <li class="ng-binding col-lg-12 col-md-12 col-sm-12">
                                                             	<div class="col-md-6 col-lg-6 col-sm-6"><strong class="inline-block w-sm mb-5">Payment Received</strong></div>
												             	<div class="col-md-2 col-lg-2 col-sm-2"><strong>:</strong></div>
												             	<div class="col-md-4 col-lg-4 col-sm-4" style="padding-right: 0px;"><span style="float:right"><%=Constants.getCurrency(loginfo)%><s:property value="creditAmt"/></span></div>
                                                            </li>
                                                            <li class="ng-binding col-lg-12 col-md-12 col-sm-12">
                                                            	<div class="col-md-6 col-lg-6 col-sm-6"><strong class="inline-block w-sm mb-5">Total Balance</strong></div>
												             	<div class="col-md-2 col-lg-2 col-sm-2"><strong>:</strong></div>
												             	<div class="col-md-4 col-lg-4 col-sm-4" style="padding-right: 0px;"><span style="float:right"><%=Constants.getCurrency(loginfo)%><s:property value="balancex"/></span></div>
                                                            </li><!--
                                                            <%//double creditAmount = (Double)session.getAttribute("creditAmount"); %>
															<% //if(creditAmount>0){%>
															<li class="ng-binding col-lg-12 col-md-12 col-sm-12">
																<div class="col-md-6 col-lg-6 col-sm-6"><strong class="inline-block w-sm mb-5">Credit Balance</strong></div>
												             	<div class="col-md-2 col-lg-2 col-sm-2"><strong>:</strong></div>
												             	<div class="col-md-4 col-lg-4 col-sm-4" style="padding-right: 0px;"><span style="float:right"><//%=Constants.getCurrency(loginfo) + DateTimeUtils.changeFormat(creditAmount)%></span></div>
															</li>
															<%//} %>
                                                        --></ul>
                                                    </div>
                                                    <!-- /tile body -->
                                                </section>
                                                <!-- /tile -->
                                            </div>
                                            <!-- /col -->
                                        </div>
                                        <!-- /row -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
	</div>
</div>
</div>
</div>
</s:iterator>

