<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<link rel="stylesheet" href="_assets/newtheme/css/main.css">


<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<%@page import="com.apm.main.common.constants.Constants"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript">
	/* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	            )
	        ); });  */
	        
	        bkLib.onDomLoaded(function() {
		           
	        	 new nicEditor().panelInstance('emailBody');
	        	 $('.nicEdit-panelContain').parent().width('500px');
	        	 $('.nicEdit-panelContain').parent().next().width('500px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px');
	      });
</script>
<script>

	$(function() {
		
		
		$( "#sendEmailPopUp").dialog({
			autoOpen: false,
			resizable: true,
			height: 480,
			width: 560,
			modal: true,
			buttons: {
				"Send": function() {
					//sendSaveEmail();
					sendPdfMail();
					
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			}
		});
	});	
		
		
		
	</script>
	
	<div class="">
							<div class="" >
								<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Report</h4>

									</div>
								</div>
								
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="row">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
		
		  <section id="content">
                <div class="page page-shop-single-invoice">
                    <div class="pagecontent">
                        <div class="">
								
								
								
								<div class="" >
                               <div class="">
								<link href="common/css/printpreview.css" rel="stylesheet" />
								<%@ include file="/accounts/pages/letterhead.jsp" %>
								</div>
                                <span class="controls pull-right">
                                
                                  <a href="#" onclick="sendmail();" class="btn btn-primary hidden-print" data-toggle="tooltip" title="Send Email"><i class="fa fa-envelope"></i></a>
                                  <a href="#" onclick="printpage();" class="btn btn-primary hidden-print" data-toggle="tooltip" title="Print"><i class="fa fa-print"></i></a>
                                </span>
                                 <hr>
                            </div>
                           
								<div role="tabpanel">


                                <div class="tab-content">
                                    <!-- tab in tabs -->
                                    <div role="tabpanel" class="" id="details">
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
										<section class="tile tile-simple">

                                                    <!-- tile body -->
                                                    <div class="tile-body">
															<div class="row b-t pt-20">

                                                            <!-- col -->
                                                            <div class="col-md-4 b-r">
                                                                <p class="text-uppercase text-strong mb-10 custom-font">
                                                                    RECEIPT TO
                                                                </p>
                                                               <ul class="list-unstyled text-default  mb-20">
                                                                    <li><s:property value="payeename"/>,</li>
                                                                    <li><s:property value="payeeadress"/></li>
                                                                    <li><s:property value="payeeTown"/>, <s:property value="payeePostcode"/></li>
                                                                   
                                                                </ul>
                                                            </div>
                                                            <!-- /col -->
                                                            <!-- col -->
                                                            <div class="col-md-4 b-r">
                                                                <p class="text-uppercase text-strong mb-10 custom-font">
                                                                    RECEIPT DETAIL
                                                                </p>
                                                                <ul class="list-unstyled text-default mb-20">
                                                                	<s:if test="payAmount > 0">
                                                                			<li><strong>Receipt No:</strong> 0000<s:property value="invoiceid"/></li>
																	</s:if>
																	<s:else>
																			<li><strong>Receipt No:</strong> 0000<s:property value="invoiceid"/></li>
																	</s:else>
                                                                    <li><strong>Receipt Date:</strong> <s:property value="date"/></li>
                                                                    <li><strong>Account No:</strong> 0000<s:property value="clientId"/></li>
                                                                    <li><strong>Status:</strong> (hardcoded)</li>
                                                                    <li><strong>Consultant:</strong> (hardcoded)</li>
                                                                    <li><input type="hidden" name="hiddenclientid" id="hiddenclientid" value="<s:property value="clientId"/>"></li>
                                                                </ul>
                                                            </div>
                                                            <!-- /col -->
                                                            <!-- col -->
                                                            <div class="col-md-4">
                                                                <p class="text-uppercase text-strong mb-10 custom-font">Patient DETAIL</p>
                                                                <ul class="list-unstyled text-default  mb-20">
                                                                    <li><s:property value="client"/>,</li>
                                                                    <li><s:property value="address"/></li>
                                                                     <li>(City and pin here)</li>
                                                                    <li><strong>Age/Sex:</strong> <s:property value="agegender"/> </li>
                                                                     <li><strong>D.O.A:</strong> (hardcoded)</li>
                                                                      <li><strong>D.O.D:</strong> (hardcoded)</li>
                                                                    <s:if test="payby=='Third Party'">
                                                                    <li><strong>Policy No:</strong> <s:property value="policyNo"/></li>
																	</s:if>
                                                                    <li>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                            <!-- /col -->
                                                        </div>
                                                            <!-- col -->
                                                    </div>
                                                    <!-- /tile body -->

                                                </section>
										 <!-- tile -->
                                                <section class="tile tile-simple invoicetop">

                                                    <!-- tile body -->
                                                    <div class="tile-body p-0">

                                                        <div class="col-lg-12 col-md-12">
                                                        <h1 class="text-center">RECEIPT<!--
                                                        
                                                        <s:if test="balancex==0">
													 	  <a href="" class="btn btn-ef btn-ef-1 btn-ef-1-default btn-ef-1a  mr-5" style="background-color:#616f77;color:#fff">Paid</a>
														</s:if>
														<s:else>
														<a href="" class="btn btn-ef btn-ef-1 btn-ef-1-default btn-ef-1a   mr-5" style="background-color:#616f77;color:#fff">Unpaid</a>
														</s:else>
                                                        --></h1>
                                                       
                                                      
                                                            <table class="table table-bordered">
                                                                <thead class="primarycolr">
                                                                <tr>
                                                                    <th>Charge Name</th>
                                                                    <th class="text-center">Quantity</th>
                                                                    <th class="text-right">Unit Cost</th>
                                                                    <th class="text-right">Total</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                  <s:iterator value="masterAssessmentList">
	                                                                <tr>
	                                                                	<td><b>(<s:property value="name"/>)</b></td>
	                                                                </tr>
	                                                                <s:iterator value="assesmentList">
																	<tr>
																		<td> 
																		<s:if test="dna==true">
																			<s:property value="appointmentType"/> <span style="color:red">(DNA)</span>
																		</s:if>
																		<s:else>
																			<s:property value="appointmentType"/>
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
										
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 20px;">
                                            <div class="col-md-3 col-lg-3 col-sm-6 col-xs-6 price-total" style="float:right;padding-right: 0px;">

                                                <!-- tile -->
                                                <section class="tile tile-simple bg-tr-black lter">

                                                    <!-- tile body -->
                                                    <div class="tile-body">
                                                    

                                                        <ul class="list-unstyled">
                                                         	<li class="ng-binding"><strong class="inline-block w-sm mb-5">Total:</strong> <span style="float:right"><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></span></li>
                                                            <li class="ng-binding"><strong class="inline-block w-sm mb-5">Payment Received:</strong> <span style="float:right"><%=Constants.getCurrency(loginfo)%><s:property value="creditAmt"/></span></li>
                                                           
                                                            <li class="ng-binding"><strong class="inline-block w-sm mb-5">Discount: <small>(<s:property value="discount"/>%)</small></strong> <span style="float:right">00.00</span></li>
                                                             <s:if test="payby=='Client'">
                                                            <li class="ng-binding"><strong class="inline-block w-sm mb-5">Policy Excess:</strong> <span style="float:right"><%=Constants.getCurrency(loginfo)%><s:property value="policyExcess"/></span></li>
                                                            </s:if>
                                                            <li class="ng-binding"><strong class="inline-block w-sm mb-5">Total Balance:</strong> <span style="float:right"><%=Constants.getCurrency(loginfo)%><s:property value="balancex"/></span></li>
                                                          	<s:if test="crdAmount!=0">
																<li class="ng-binding"><strong class="inline-block w-sm mb-5">Credit Balance:</strong><span style="float:right"><%=Constants.getCurrency(loginfo)%><s:property value="crdAmount"/></span></li>
															</s:if>
                                                          
                                                        </ul>

                                                    </div>
                                                    <!-- /tile body -->

                                                </section>
                                                <!-- /tile -->

                                            </div>
                                            <!-- /col -->
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 row" style="position: absolute;">
								            <ul class="list-unstyled text-default  mb-20">
								             	<li><strong>Payment Mode :</strong> <s:property value="paymode"/></li>
								                <li><strong>Notes :</strong> <s:property value="submitInvoiceNotes"/></li>
								           </ul>
											
											</div>
                                        </div>
										
										
										
										
										

											

											
										</div>
									</div>
								</div>
								</div>
								</div>
								</div>
								
							
								</div>
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
						</div>
	





<!-- Modal Email-->
<div class="modal fade" id="sendEmailPopUp2" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "thirdPartEmail" name = "email"
								cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver"
								title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "ccEmail" name = "ccEmail"
								cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "subject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject"
								placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="5" cols="60"
								title="Enter Body" name="emailBody"  id="emailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox"
								name="ispdf" id="ispdf" checked="checked">
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="sendPdfMail(<s:property value="id"/>);">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</br></br>