<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
 <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>

<link href="common/BootstrapNew/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<%@page import="com.apm.main.common.constants.Constants"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>


<script type="text/javascript">
	 /* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	                
	            )
	        ); });   */
	        
	        
	        bkLib.onDomLoaded(function() {
	           
	        	 new nicEditor().panelInstance('emailBody');
	        	 $('.nicEdit-panelContain').parent().width('500px');
	        	 $('.nicEdit-panelContain').parent().next().width('500px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px');
	      });
</script>
    <style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #43b9be !important;
    margin-bottom: 15px;
}
        .adformback {
            border: 1px solid;
            padding: 10px 0px 0px;
            margin-top: 0px;
            width: 98%;
            margin-left: 9px;
        }
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0px;
            text-align: right;
            font-size: 12px;
        }
        .marbot15 {
            margin-bottom: 15px;
        }
        .martop15 {
            margin-top: 15px;
        }
        .diagtitle {
            background-color: #000;
            color: #FFF;
            padding: 10px;
            font-weight: normal;
            padding-top: 12px !important;
        }
        .bednosele {
            width: 10%;
            margin-top: -40px;
        }
        .textareaheight{
        height: 50px !important;;
        }
       
       .paddtop{
        padding: 0px 0px 14px 2px;
    	}
        .widthtabhedth1{
        	width: 30%;
        }
        .widthtabhedth2{
        	width: 7%;
        }
        .admissionbackgreen {
		    width: 210px;
		}
		.form-group {
    		margin-top: 4px;
		}
		.pad8{
			padding-top: 8px;
		}
		.backgrey{
			        background-color: rgba(149, 222, 91, 0.19);
		}
		.pnameback{
			    background-color: #f5f5f5;
    			margin-top: -7px;
		}
		.panel-primary {
		    border-color: #43b9be;
		}
		.padsign{
			padding-top: 100px;
			padding-left:0px;
			padding-right:0px;
		}
		.help-block {
		    display: block;
		    margin-top: 0px !important;
		    margin-bottom: 0px !important;
		    color: #737373;
		}
		 .bordertopgreen1 {
    border-top: 2px solid #43b9be;
}
  .panel-primary {
      border-color: #43b9be;
  }
  .padsign{
   padding-top: 40px;
  }
  .help-block {
    display: block;
    margin-top: 0px;
    margin-bottom: 0px;
    color: #737373;
}
h3, .h3 {
    font-size: 16px;
    font-weight: bold;
    color: #6699cc;
    margin-top: 0px;
    margin-bottom: 5px;
}
.form-group {
    margin-bottom: 4px !important;
}
p {
    margin: 0 0 5.5px !important;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}

.settopbac {
    background-color: #ddd;
}
.totalbor {
    background-color: #f5f5f5;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 5px 2px 5px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 12px;
    border-right: none !important;
    border-left: none !important;
}
 @media print
{
    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
	}
	p {
	    margin: 0 0 5.5px !important;
	    font-size: 9px !important;
	}
	.form-group {
    margin-bottom: 4px !important;
}
.titleset {
    margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    line-height: 20px;
}
h4, .h4 {
    font-size: 12px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:12px !important;
	color: firebrick !important;
}
.titleset {
    margin: 0px;
    color: #6699cc !important;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
}
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}
.table>tbody>tr>td, .table>tfoot>tr>td {
    font-size: 9px !important;
}
.wordscolr{
	color: #d07878;
    text-transform: uppercase;
     font-size: 9px !important;
    
}
}
    </style>
    <style>

.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 135px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 12px;
    font-weight: bold;
}
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
.titleset{
	margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
}
label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: bold;
}
td, th {
    padding: 0px 3px 0px 5px !important;
    border-right: 1px solid #eee !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
}
.wordscolr{
	color: #d07878;
    text-transform: uppercase;
    text-size:12px;
    
}

</style>


<div class="col-lg-12 col-xs-12 col-md-12" >
	<div class="row letterheadhgt" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp" %>
				
			</div>
		</div>
		
		
		
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
							<s:if test="advrefsrno!=0">
								<b class="setticolors">REFUND RECEIPT</b>
								</s:if>
								<s:else>
								<b class="setticolors">PAYMENT RECEIPT</b>
								</s:else>
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
					</div>
					</div>
		
			<div class="col-lg-7 col-md-7 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
			<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label print-visible hidden-md hidden-lg">Date</label><span class="print-visible hidden-md hidden-lg">: <script>
  var currentDate = new Date(),
      day = currentDate.getDate(),
      month = currentDate.getMonth() + 1,
      year = currentDate.getFullYear();
  document.write(day + "/" + month + "/" + year)
</script> 
&nbsp;|&nbsp;
<script>
	var currentTime = new Date(),
      hours = currentTime.getHours(),
      minutes = currentTime.getMinutes();

	if (minutes < 10) {
	 minutes = "0" + minutes;
  }

	var suffix = "AM";
	if (hours >= 12) {
    suffix = "PM";
    hours = hours - 12;
	}
	if (hours == 0) {
	 hours = 12;
	}

	document.write(hours + ":" + minutes + " " + suffix)
</script>
</span>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Patient Name</label><span>: <s:property value="client"/> </span><span class="hidden-print">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Age / Gender</label><span class="hidden-print">: <s:property value="agegender" /></span>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Age / Gender</label><span>: <s:property value="agegender" /></span>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Address</label><span>: <s:property value="address"/>, <s:property value="clienttown"/>, <s:property value="clientpostcode"/> </span>
				</div>
				
			</div>
			<div class="col-lg-5 col-md-5 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
				<div class="form-group" style="margin-bottom: 3px;">
					<s:if test="payAmount > 0">
						<label for="inputEmail3" class="control-label">Receipt No</label>
						<span>: <%if(loginfo.isSeq_no_gen()){%>
									(<s:property value="physical_paymentid"/>)
								<%}else{} %> 
									<s:property value="invoiceid"/>
						</span>
					</s:if>
					<s:else>
						<label for="inputEmail3" class="control-label">Receipt No</label>
						<span>:  <%if(loginfo.isSeq_no_gen()){%>
									(<s:property value="physical_paymentid"/>)
								<%} %>  
									<s:property value="invoiceid"/>
						</span>
					</s:else>
						<label for="inputEmail3" class="control-label hidden-print">&nbsp; | &nbsp; Receipt Date</label><span class="hidden-print">: <s:property value="date"/></span>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Receipt Date</label><span>: <s:property value="date"/></span>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">UHID</label><span>: <s:property value="abrivationid"/> </span>
				</div>
				
			</div>
		</div>
		
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding: 0px;">
			<div class="tile-body p-0 paddnil">
                                                        <div>
                                                        <s:if test="balancex==0">
													 	  <span class="paidbtn">Paid</span>
														</s:if>
														<s:else>
														<!--<span class="unpaidbtn">Unpaid</span>
														--></s:else>
                                                        </h1>
                                                            <table class="table">
                                                                <thead>
                                                                <tr>
                                                                    <th style="width: 45%;">Charge Name</th>
                                                                    <th style="width: 6%;" class="text-center">Qty</th>
                                                                    <th style="width: 10%;"class="text-right">Unit Cost</th>
                                                                    <th style="width: 10%;"class="text-right">Total</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                  <%-- <s:iterator value="masterAssessmentList"> --%>
	                                                                <%-- <tr class="totalbor">
	                                                                	<td style="border:none !important;"><s:property value="name"/></td>
	                                                                	<td style="border:none !important;"></td>
	                                                                	<td style="border:none !important;"></td>
	                                                                	<td style="border:none !important;"></td>
	                                                                </tr> --%>
                                                                <s:iterator value="assesmentList">
																<tr>
																	<td class="padletab">
																	<s:if test="dna==true">
																		<s:property value="appointmentType"/> <span style="color:red">(DNA)</span>
																	</s:if>
																	<s:else>
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="appointmentType"/>
																	</s:else> 
																	</td>
																	<%-- <td><s:property value="appointmentType"/></td> --%>
																	<td class="text-center"><s:property value="quantity"/></td>
																	<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="charges"/></td>
																	<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="chargeTotal"/></td>
																</tr>
																</s:iterator>
																<%-- </s:iterator> --%>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    
                                                    
                                                    
                                                   <div class="row">
                                            <!-- col -->
                                            	<div class="col-lg-12 col-md-12 col-xs-12">
                                            		<div class="col-lg-8 col-xs-6 col-md-6 col-sm-6" style="padding: 0px;">
                                            		<div class="form-group">
                                            			<p style="margin: 0px 0px 4px 0px;">Payment Mode : <s:property value="paymode"/>
                                            				<s:if test="paymode=='Cheque'">
							                            		(<s:property value="chequeno"/>)
							                         		</s:if></p>
							                         		<p style="margin: 0px 5px 0px 0px !important;">Prepared By : <s:property value="preparedby"/></p>
							                         		<p style="margin: 0px 5px 0px 0px !important;">Notes : <s:property value="submitInvoiceNotes"/></p>
                                            		</div>
                                            		</div>
                                            		<div class="col-lg-2 col-xs-3 col-md-3 col-sm-3" style="padding: 0px;text-align: right;color: green;">
                                            			<p style="margin: 0px 5px 0px 0px !important;"><strong class="inline-block">Total :</strong></p>
                                            			<p style="margin: 0px 5px 0px 0px !important;"><strong class="inline-block">Payment Received :</strong></p>
                                            		</div>
                                            		<div class="col-lg-2 col-xs-3 col-md-3 col-sm-3" style="padding: 0px;text-align: right;color: green;">
                                            			<p style="margin: 0px 5px 0px 0px !important;"><span style=""><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></span></p>
                                            			<p style="margin: 0px 5px 0px 0px !important;"><span style=""><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></span></p>
                                            		</div>
                                            	</div>
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
								               <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-top: 80px;">
								               	 <s:if test="payby!='Third Party'">
															<span>Authorized Signatory</span>
												</s:if>
								               </div>
								               <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right">
								               <div class="form-group wordscolr" style="color: #d07878;text-transform: uppercase;">
												<span>In Words : <s:property value="totalinword"/> Only.</span>
											</div>
								               </div>
								               </div>
                                            <div class="col-lg-12 col-md-12 hidden-print" style="margin-bottom:10px;padding: 0px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;text-align: right;">
			                                <a href="#" onclick="sendmail();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Send Email">Email</i></a>
	                                  		<a href="#" onclick="printpage();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Print">Print</a>
		                                </div>
		                            </div>
		                        </div>
                                            <!-- /col -->
                                        </div>
                                        
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
</div>



<div class="">
							<div class="">
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="row hidden">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div id="login_main" class="main_layout_content">
	<div id="login" class="block_div">
		
		  <section >
                <div class="page page-shop-single-invoice">
                    <div class="pagecontent">
                        <div class="">
                            <div class="">
                               <div class="">
								
								</div>
                                <span class="controls pull-right">
                                
                                  
                                </span>
                            </div>
                            <div role="tabpanel">


                                <div class="tab-content">
                                    <!-- tab in tabs -->
                                    <div role="tabpanel" class="tab-pane active" id="details">
                                        <!-- row -->
                                        <div class="row">
                                            <!-- col -->
                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                <!-- tile -->
                                                <section class="tile tile-simple" style="margin-top: 20px;">
                                                    <!-- tile body -->
                                                    <div class="tile-body">
														<div class="row b-t pt-20">

                                                            <!-- col -->
                                                            
                                                            <!-- /col -->
                                                            <!-- col -->
                                                            <div class="col-md-4 b-r">
                                                                <p class="text-uppercase text-strong mb-10 custom-font">
                                                                    Invoice DETAIL
                                                                </p>
                                                                <ul class="list-unstyled text-default mb-20">
                                                                	
																	
																	<s:if test="ipdid>0">
																	 <li><strong>Admission ID:</strong> <s:property value="ipdid"/></li>
																	 
																	</s:if>
																	
                                                                   
                                                                    
                                                                    <s:if test="invoicename=='HD'">
                                                                       <li><strong>From Date:</strong> <s:property value="fromDate"/></li>
                                                                       <li><strong>To Date:</strong> <s:property value="toDate"/></li>
                                                                    </s:if>
                                                                    
                                                                    <s:if test="ipdid>0">
	                                                                    <s:if test="dischargeDate!=''">
	                                                                    	 <li><strong>Status:</strong> Discharged</li>
	                                                                    </s:if>
	                                                                    <s:else>
	                                                                    	 <li><strong>Status:</strong> Not Discharged</li>
	                                                                    </s:else>
	                                                                  
	                                                                   
	                                                                    <li><strong>Consultant:</strong> <s:property value="ipdconsultant"/></li>
                                                                      </s:if>
                                                                    <li><input type="hidden" name="hiddenclientid" id="hiddenclientid" value="<s:property value="clientId"/>"></li>
                                                                </ul>
                                                            </div>
                                                            <!-- /col -->
                                                          
                                                        </div>
                                                    </div>
                                                    <!-- /tile body -->
                                                </section>
                                                <!-- /tile -->

                                               
                                            </div>
                                        </div>
                                        <!-- /row -->
                                        <!-- row -->
                                        
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
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				<%-- 	<div class="col-lg-4 col-md-4">				
						<label>Show Template Name</label>
					</div>
					<div class="col-lg-5 col-md-5">		
						<s:select id="templateName" name="templateName" listKey="id"
							headerValue="All" headerKey="All" listValue="templateName" list="templateNameList" 
							value="templateName" cssClass="form-control" onchange="showTemplateDetails(this.id)"></s:select>
						<s:hidden name="templateName" id="templateName"></s:hidden>
					</div> --%>
					</div>
						<div class="form-group">
							<label>To:</label>
							<s:if test="payby=='Third Party'">
								<s:textfield theme="simple" id = "thirdPartEmail" name = "payeeEmail"
								cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver"
								title="Enter Email Id" data-toggle="tooltip" />
							</s:if>
							
							<s:else>
								<s:textfield theme="simple" id = "thirdPartEmail" name = "email"
								cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver"
								title="Enter Email Id" data-toggle="tooltip" />
							
							</s:else>
							
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
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="60" cols="60"
								title="Enter Body" name="emailBody"  id="emailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox"
								name="ispdf" id="ispdf" checked="checked">
						</div>
						
						
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="sendPdfMail();">Send</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
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

<script>
    function showhide()
     {
           var div = document.getElementById("newpost");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }
  </script>


