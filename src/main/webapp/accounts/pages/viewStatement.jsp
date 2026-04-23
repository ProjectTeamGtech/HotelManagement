<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.opensymphony.xwork2.util.ValueStack" %>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>


<script type="text/javascript" src="common/js/pagination.js"></script>
<%-- <script type="text/javascript"
	src="diarymanagement/js/nonavailableslot.js"></script> --%>
<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="accounts/js/viewstatement.js"></script>
<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
	<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
<%-- <script src="diarymanagement/js/jquery.freezetablecolumns.1.1.js"></script>
<script type="text/javascript" src="diarymanagement/js/popupscript.js"></script> --%>
<%-- <script type="text/javascript"
	src="diarymanagement/js/commonAppointmentView.js"></script> --%>
<script>


$(window).on('load', function() {
	$('#newloaderPopup').modal('hide');
});

$(document).ready(function() {
	$('#newloaderPopup').modal('show');
	$("#fromDate").datepicker({

		dateFormat : 'dd/mm/yy',
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd/mm/yy',
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true
	});
	
	$("#pkgfromdate").datepicker({

		   dateFormat : 'dd-mm-yy',
		   yearRange: yearrange,
		   minDate : '30-12-1880',
		   changeMonth : true,
		   changeYear : true
		
		 });
		   
		   $("#pkgtodate").datepicker({
		
		   dateFormat : 'dd-mm-yy',
		   yearRange: yearrange,
		   minDate : '30-12-1880',
		   changeMonth : true,
		   changeYear : true
		
		 });
	var autocharge= document.getElementById('chkautocharge').value;
	if(autocharge=="1"){
		document.getElementById('autocharge').checked=true;
	} else {
		document.getElementById('autocharge').checked=false;
	}
	
	
});


function recordPaymentForSelectedInvoice(id){
	document.getElementById('selectedInvoiceid').value = id ;
	document.getElementById('viewaccfrm').submit();
}


       
       
       bkLib.onDomLoaded(function() {
          
       	 new nicEditor().panelInstance('emailBody');
       	 $('.nicEdit-panelContain').parent().width('98%');
       	 $('.nicEdit-panelContain').parent().next().width('98%');
       	 
       	 $('.nicEdit-main').width('98%');
       	 $('.nicEdit-main').height('80px');
     });


</script>

<style>
.table>thead>tr>td.info, .table>tbody>tr>td.info, .table>tfoot>tr>td.info, .table>thead>tr>th.info, .table>tbody>tr>th.info, .table>tfoot>tr>th.info, .table>thead>tr.info>td, .table>tbody>tr.info>td, .table>tfoot>tr.info>td, .table>thead>tr.info>th, .table>tbody>tr.info>th, .table>tfoot>tr.info>th {
     background-color: white; 
}
  .thumbnail{
  background-color: #046994 !important;
  }
  .thumbnail a>img{
  filter: invert(1);
  }
  .thumbnail .caption {
  color: white !important;
  }
  
    .disabled {
    z-index: 1000 !important;
    background-color: lightgrey !important;
    opacity: 0.6 !important;
    pointer-events: none !important;
}
a {
	color: black;
}
a:hover {
    color: #ffffff !important;
    text-decoration: none;
}
i{
	cursor: pointer;
}
.backtotal{
    background-color: #4b5367 !important;
    padding: 6px 10px 6px 6px;
    width: 57%;
    float: right;
    color: #fff;
}
.right{
float: right;
}
.iocnheadset {
    width: 10.666667%;
}
hr {
    margin-top: 11px;
    margin-bottom: 11px;
    border: 0;
    border-top: 1px solid #eee;
}
.coinwidth{
	    width: 75%;
    float: right;
}
.thumbnail {
    height: 80px !important;
}
.table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 5px 0px 5px !important;
}
.checkbox-custom-alt > i {
    border: 2px solid rgba(97, 111, 119, 0.58);
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

<div class="" style="font-family: 'Open Sans', sans-serif;">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft hidden-print">
									<img src="manasmaindashboard/images/Accounts_Icon.svg" class="img-responsive prescripiconcircle" style="filter: brightness(5.25);width: 18%;margin-top: 4px; margin-left: 10px">
								</div>
								<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
								<h4>View Account</h4>
								</div>
									</div>
								</div>
								<div class="">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-right:0px;padding-left:0px;">
										<div>
      <s:form action="Statement" id="category_form" theme="simple">
      <s:hidden name="clraradv" id="clraradv"/>
	  <s:hidden name="hdnSelectedID" id="hdnSelectedID" />
	     <div class="col-lg-12 col-md-12 hidden-print" style="padding: 2px 0px 0px 0px !important;">
	       <div class="col-lg-12 col-md-12 col-sm-12" style="padding-left: 0px;padding-right: 0px;">
	      
	       <div class="col-lg-9 col-md-9 col-sm-9" style="padding-right: 0px;padding-left: 0px;">
	       <%-- <div class="hidden-print">
								<ul class="breadcrumb">
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
	       <div class="col-lg-12 col-md-12 col-sm-12 topback2i" style="padding-right: 0px;padding-left: 0px;">
	       <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8" style="padding-right: 0px;padding-left: 0px;">
	       <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label><%=loginfo.getPatientname_field() %></label>
                <s:textfield name="client" id="client"  readonly="true"
				cssClass="form-control" onclick="showPopUp(1)" data-toggle="modal" data-target="#clientSearch" aria-describedby="basic-addon1"/>
				<s:hidden name="clientId" id="clientId"></s:hidden>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Paid by</label>
			<s:select name="selectedPayBy"
				list="#{'All':'All','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control"></s:select>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Transaction</label>
			<s:select name="transactionType"
				list="#{'All':'All','Charge':'Charge', 'DNA':'DNA','Invoice':'Invoice','Pending Payment':'Pending Payment','Payments':'Payments','C/D Charge':'C/D Charge'}"
				cssClass="form-control"></s:select>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Department</label>
			<s:select id="location" name="location" listKey="id"
				headerValue="All" headerKey="All" listValue="location"
				list="locationList" value="location" cssClass="form-control"></s:select>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 hidden">
			<label>Third Party</label>
			<s:select id="thirdParty" name="thirdParty" listKey="id"
				listValue="thirdParty" headerValue="All" headerKey="All"
				list="thirdPartyList" cssClass="form-control"></s:select>
		</div>
		
		
	       </div>
	       <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="padding-right: 0px;padding-left: 0px;">
	       <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			<label>From Date:</label>
			<div class="input-group ">
               <s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" aria-describedby="basic-addon1"></s:textfield>
             </div>
		</div>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			<label>To Date:</label>
			<div class="input-group ">
               
               <s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" aria-describedby="basic-addon1"></s:textfield>
            </div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3 hidden-md hidden-xs hidden-sm" style=" margin-top: 5px;">
			<label></label>
			<div class=" ">
				<input type="submit" value="View" onclick="shownewidpopup()" class="btn btn-primary">
            </div>
		</div>
		
	       </div>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style=" margin-top: 5px;">
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style=" margin-top: 0px;">
			<label>IPDs</label>
			<s:select  name="ipdidnew" listKey="ipdid"
				headerValue="All" headerKey="" listValue="ipdseqno"
				list="ipdseqlist"  cssClass="form-control" id="ipdidnew"></s:select>
			</div>
			<s:if test="admissionDate!=''">
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: center; padding-top: 30px">
			<b>Admission Date: </b><s:property value="admissionDate"/>
			</div>
			</s:if>
		</div>
	
	</div>
	
	
	<div id="printableArea">
	<s:if test="clientId!=0">
	<div class="visible-print" >
		<div class="col-lg-12 col-md-12 col-sm-12 " style="padding-right:0px;padding-left:0px;margin-top: 15px;">
	<div class="col-lg-4 col-md-3 col-sm-3 col-xs-3" style="padding-left:0px">
		
							<b><%=loginfo.getPatientname_field() %> Details:</b>
				<%-- 	<br> Reg No:<s:property value="clientId" /> --%>
				<br> <b> UHID: </b><s:property value="abrivationid" />
					<br>
					<s:property value="initial" /><s:property value="firstname" /> <s:property value="lastname" /><br>
					<s:property value="address" />
					<s:property value="postcode" /><br>
					<s:property value="mobno" /><br>
					<s:property value="email" />
					<br>
					<b>Third Party Details</b><br />
					<s:property value="insuranceCompany" /><br>
					<s:property value="thirdPartyAddress" /><br>
					<s:property value="thirdPartyPostcode" /><br>
					<s:property value="thirdPartyContacttno" /><br>
					<s:property value="thirdPartyemail" /><br>
					
	</div>
	<div class="col-lg-8 col-md-9 col-sm-9 col-xs-9" style="padding-right: 0px;">
			<div class="col-lg-12 col-md-12" style="padding-right: 0px;">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<img src="accounts/coins.png" class="img-responsive coinwidth hidden-print"/>
			</div>
			
			
			<div class="pull-right">
				<h4 class="viewstatmentprintfonts">Total Charges : <b class="right" style="color:green"><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" /></b></h4>
			 
				<h4 class="viewstatmentprintfonts">Total Discount : <b class="right" style="color:green"><%=Constants.getCurrency(loginfo)%><s:property value="discount" /></b></h4>
			 
				<h4 class="viewstatmentprintfonts">Total Payments : <b class="right" style="color:green"><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx" /></b></h4>
				 
				<h4 class="viewstatmentprintfonts">Outstanding Payment : <b class="right" style="color:red"><%=Constants.getCurrency(loginfo)%><s:property value="balanceTotalx" /></b></h4>
		 
				
				<s:if test="creditBalancex>0">
				  <h4 class="viewstatmentprintfonts">Credit Balance : <b class="right" style="color:green;cursor:pointer;"><%=Constants.getCurrency(loginfo)%><span style="color: green;" onclick="openPopup('creditListAdditional?clientId=<s:property value="clientId" />')"><s:property value="creditBalancex" /></span></b></h4>
				</s:if>
				<s:else>
				   <h4 class="viewstatmentprintfonts">Credit Balance : <b class="right" style="color:green"><%=Constants.getCurrency(loginfo)%>0.00</b></h4>
				</s:else>
			 
				<h4 class="viewstatmentprintfonts">Self Credit : <b class="right" style="color:"><%=Constants.getCurrency(loginfo)%><s:property value="selfcredit" /></b></h4>
			 
			</div> 
			
			
		</div>
	</div>
	</div>
	</div>
	</s:if>
	
	<div class="clearfix" style="clear: both;"></div>
	<div class="">
		<div class="">
		     <h4 class="hidden-print" style="display: flex;">Auto Charge 
		     <ul class="settings" style="padding: 0px;list-style: none; margin-left: 10px;margin-bottom: 0px;">
		      	<s:hidden name="autocharge" id="chkautocharge"></s:hidden>
                                    <li>
                                        <div class="form-group" style="margin: 0px;">
                                           <div class="onoffswitch dutch" id="salebilldiv">
                                                    <input type="checkbox" name="onoffswitch" onclick="setAutocharge()" class="onoffswitch-checkbox" id="autocharge" >
                                                    <label class="onoffswitch-label" for="autocharge">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                        </div>
                                    </li>
                                </ul>
                                
                                
                              </h4>
                              
                        <div  class="hidden-print viewstatmentprintbtn" style="margin-top: -30px">
                              <!-- <a onclick="printDiv('printableArea')" value="print a div!" type="button" class="btn btn-primary pull-right"><i class="fa fa-print"></i></a> -->
                              <input  onclick="sendmail();" type="button" class="btn btn-primary pull-right viewstatmentprintbtnmr" value=" Send Email ">
                              <input type="button" onclick="openBlankPopup('NewChargeType?selectedid=1')" class="btn btn-primary pull-right" value="Charge Master" style="margin-right: 4px">
							  <%-- <input onclick="refreshAutoChargesData(<s:property value="ClientId"/>)" type="button" class="btn btn-primary pull-right" value="Refresh Auto Charge" style="margin-right: 4px"> --%> 
							  <a  href="updateautochargeStatement?clientId=<s:property value="ClientId"/>" onclick="shownewidpopup()" class="btn btn-primary pull-right" style="margin-right: 4px">Refresh Auto Charge</a>
						</div>
				<table  class="table table-responsive  table-condensed table-bordered ">
					<thead >
						<tr>
							
							<!--  <th><i class="fa fa-user"></i> #</th> -->
                              <th class="" style="background-color: #4e7894;"> Date</th>
                              <th> Transaction</th>
                              <th>Print</th>
                              <th>Bill Summary</th>
                              <th> Paid By</th>
                              <th style="text-align:right;"> Charges</th>
                              <th class="text-right">Discount</th>
                              <th class="text-right">Refund</th>
                              <th class="text-right"> Payments</th>
                              <!--<th> Balance</th>
							--><!-- <th>Details</th> -->
						</tr>
					</thead>
					<tbody>
						<%
							int c1 = 1;
							String bgcolor = "rgb(79 216 197 / 55%);";
						%>
						
						
					<s:if test="accountList.size>0">
						<s:iterator value="accountList">
							
							<s:if test="chargeType!='Submit'">
								<%bgcolor = "rgb(202 175 255 / 63%);"; %>
							</s:if>
							<s:if test="chargeType=='DNA'">
								<s:if test="totalAmountx=='0.00'">
									<%bgcolor = "rgb(202 175 255 / 63%);"; %>
								</s:if>
							</s:if>
							
								<tr style="background-color:<%=bgcolor %>" id="<s:property value="invoiceid"/>">
									<%-- <td><%=c1%></td> --%>
									<td>
										<% if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){%>
									  		<span class="hidden-print"><a href="#" title="Cancel Charge" onclick="deletechages(<s:property value="invoiceid"/>,0)"><i class="fa fa-times text-danger hidden-print" aria-hidden="true"></i></a></span> &nbsp;
									  <%} %>
									<s:property value="commencing" /> 
									</td>

										
											<td>
											<s:if test="chargeType=='DNA'">
												<!-- <a href="#" style="color:#3c763d" data-toggle="modal" data-target="#sharecharge" title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp; --> 
												<%-- <a href="#" style="color:#3c763d" onclick="shareChargesToDr(0,<s:property value="invoiceid"/>,0,<s:property value="totalAmountx" />,'')" title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp; --%>
												<s:if test="indivisual_shared==0">
													<a href="#" style="color:#3c763d" onclick="shareChargesToDrNew(0,<s:property value="invoiceid"/>,0,<s:property value="totalAmountx" />,'')" title="Share">
														<s:if test="isShared==0">
															<i class="fa fa-share-alt hidden-print" aria-hidden="true" style="color: green;"></i>
														</s:if>
														<s:else>
															<i class="fa fa-share-alt hidden-print" aria-hidden="true" style="color: red;"></i>
														</s:else>
													</a> &nbsp;
												</s:if>
												<a href="#" onclick="opencPopup('cestimateCharges?id=<s:property value="invoiceid"/>')"><font color=""><s:property value="chargeType"/></font> (<s:property
												value="invoiceid" />)1</a>  / <s:property value="location"/>
											</s:if> 
											<s:else>
												<!-- <a href="#" style="color:#3c763d" data-toggle="modal" data-target="#sharecharge" title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp; -->
												<%-- <a href="#" style="color:#3c763d" onclick="shareChargesToDr(0,<s:property value="invoiceid"/>,0,<s:property value="totalAmountx" />,'')" title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp; --%>
												<s:if test="indivisual_shared==0">
													<a href="#" style="color:#3c763d" onclick="shareChargesToDrNew(0,<s:property value="invoiceid"/>,0,<s:property value="totalAmountx" />,'')" title="Share">
														<s:if test="isShared==0">
															<i class="fa fa-share-alt hidden-print" aria-hidden="true" style="color: green;"></i>
														</s:if>
														<s:else>
															<i class="fa fa-share-alt hidden-print" aria-hidden="true" style="color: red;"></i>
														</s:else>
													</a> &nbsp;
												</s:if>
												<a href="#" onclick="opencPopup('cestimateCharges?id=<s:property value="invoiceid"/>')"><font color=""><s:property value="chargeType"/></font> (<s:property
												value="invoiceid" />)</a>  / <s:property value="location"/>
											</s:else>
											
											  <a
											href="javascript: void(0);"
											onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');">
												<i class="fa fa-arrow-down"></i> 
										</a></td> 
										<td></td>
										<td></td>
									<td><s:property value="whoPay" /> <span style="margin-left:5px;"><a href="#">(<s:property value="ClientName"/>)</a></span></td>
									

									
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx" /></td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="discount" /></td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="refundAmountx" /></td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx" /></td>
									<!--<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx" /></td>
									
									

								--></tr>
								
								
											
												<%--  <tr id="charges<s:property value="invoiceid"/>" style="display: none; ">
														<td colspan="9">
															<table class="table  table-bordered  ">
																<thead>
																	<tr class="bg-info">
																		<th>Charge Name</th>
																		<th>Quantity</th>
																		<th class="text-right">Unit Price</th>
																		<th class="text-right">Total</th>
																	</tr>
																	</thead>
																	<tbody>
																	<s:iterator value="chargeList">
																	<tr>
																		<td>
																			<% if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){%>
																			<span class="hidden-print"><a href="#" title="Cancel Charge" onclick="deletechages(<s:property value="id"/>,1)"><i class="fa fa-times text-danger hidden-print" aria-hidden="true"></i></a></span> &nbsp;
																			<a href="#" style="color:#3c763d" data-toggle="modal" data-target="#sharecharge" title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp;<s:property value="chargeName"/> (<s:property value="commencing"/>)
																			<a href="#" style="color:#3c763d" onclick="shareChargesToDr(1,<s:property value="invoiceid"/>,<s:property value="id"/>,<s:property value="chargeTotal"/>,'<s:property value="chargeName"/>')"  title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp;<s:property value="chargeName"/> (<s:property value="commencing"/>)
																				<s:if test="all_shared==0">
																					<a href="#" style="color:#3c763d" onclick="shareChargesToDrNew(1,<s:property value="invoiceid"/>,<s:property value="id"/>,<s:property value="chargeTotal"/>,'<s:property value="chargeName"/>')"  title="Share">
																					<s:if test="isshared==0">
																						<i class="fa fa-share-alt hidden-print" aria-hidden="true" style="color: green;"></i>
																					</s:if>
																					<s:else>
																						<i class="fa fa-share-alt hidden-print" aria-hidden="true" style="color: red;"></i>
																					</s:else>
																					</a> &nbsp;
																				</s:if>
																				<s:property value="chargeName"/> (<s:property value="commencing"/>)
																			<%} else{%>
																				<a href="#" style="color:#3c763d" data-toggle="modal" data-target="#sharecharge" title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp;<s:property value="chargeName"/> (<s:property value="commencing"/>)
																				<a href="#" style="color:#3c763d" onclick="shareChargesToDr(1,<s:property value="invoiceid"/>,<s:property value="id"/>,<s:property value="chargeTotal"/>,'<s:property value="chargeName"/>')"  title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp;<s:property value="chargeName"/> (<s:property value="commencing"/>)
																				<a href="#" style="color:#3c763d" onclick="shareChargesToDrNew(1,<s:property value="invoiceid"/>,<s:property value="id"/>,<s:property value="chargeTotal"/>,'<s:property value="chargeName"/>')"  title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> &nbsp;<s:property value="chargeName"/> (<s:property value="commencing"/>)
																				<s:if test="all_shared==0">
																					<a href="#" style="color:#3c763d" onclick="shareChargesToDrNew(1,<s:property value="invoiceid"/>,<s:property value="id"/>,<s:property value="chargeTotal"/>,'<s:property value="chargeName"/>')"  title="Share">
																					<s:if test="isshared==0">
																						<i class="fa fa-share-alt hidden-print" aria-hidden="true" style="color: green;"></i>
																					</s:if>
																					<s:else>
																						<i class="fa fa-share-alt hidden-print" aria-hidden="true" style="color: red;"></i>
																					</s:else>
																					</a> &nbsp;
																				</s:if>
																				<s:property value="chargeName"/> (<s:property value="commencing"/>)
																			<%} %>
																		</td>
																		<td>
																			<%if(loginfo.getUserType()==2 || loginfo.getAcaccess().equals("1")){ %>
																		  		<input style="text-align: center;" maxlength="3" size="5" type="text" name="vqtxt<s:property value="id"/>" id="vqtxt<s:property value="id"/>"
																		  		onchange="changeChargeQty(<s:property value="id"/>,this.value)" class="form-control"
																		  		onkeypress="return isNumberKey(event,this);" value="<s:property value="quantity"/>">
																		  <% }else{%>
																		  	<s:property value="quantity"/>
																		  <%} %>
																		</td>
																		<td class="text-right"><%=Constants.getCurrency(loginfo) %><s:property value="charge"/> </td>
																		<td class="text-right"><%=Constants.getCurrency(loginfo) %><s:property value="chargeTotal"/> </td>
																	</tr>
																	</s:iterator>
																</tbody>
															</table>
														</td>
												</tr> --%> 
											
											



								 <tr id="hiddenDetailsDiv<s:property value="invoiceid"/>"
									style="display: none">
									<td colspan="12"
										id="hiddenDetailsDiv1<s:property value="invoiceid"/>"></td>
								</tr> 
								
								<%
								    int i = c1;
								  ValueStack stack = ActionContext.getContext().getValueStack();
								    stack.getContext().put("varName", i);
								    stack.setValue("#attr['varName']", i, false);
								%>

								<s:if test="totalChargesCount==#varName">
									<% i++;%>
									<s:iterator value="invoiceList">
										<s:if test="balancex==0">
											<%bgcolor = "rgb(79 216 197 / 55%);"; %>
										</s:if>
										<s:else>
											<%bgcolor = "rgba(255 159 15 / 75%);"; %>
										</s:else>
										
										<tr style="background-color: <%=bgcolor %>" id="<s:property value="id"/>">
											<%-- <td><%=i %></td> --%>
											<td><s:property value="date" /></td>
											<td><a href="#" onclick="recordPaymentForSelectedInvoice(<s:property value="id"/>)">Invoice <%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/>(Ref Id:<s:property value="id"/>)
											<%}else{%>(<s:property	value="id" />)<%} %></a>  / 
														
														<s:if test="deleted==1">
															Cancelled
														</s:if>
														<s:else>
														   <s:property value="Department"/>
														
														</s:else>
														
														 </td>
											<td><a href="#"  onclick="openBlankPopup('viewInvoiceCharges?invoiceid=<s:property value="id" />&action=show&discount=<s:property value="newdiscount" />&payby=<s:property value="payby" />')"><i class="fa fa-print" aria-hidden="true"></i></a></td>
											<td><a href="#" onclick="openBlankPopup('viewInvoiceCharges?invoiceid=<s:property value="id" />&action=show&discount=<s:property value="newdiscount" />&payby=<s:property value="payby" />&billsummary=1')"  ><i class="fa fa-print" aria-hidden="true"></i></a></td>
											
											<s:if test="payby=='Client'">
												<td>Self<span style="margin-left:5px;"><a href="#">(<s:property value="clientName"/>)</a></span></td>
											</s:if>
											<s:else>
												<td><s:property value="payby" /> <span style="margin-left:5px;"><a href="#">(<s:property value="clientName"/>)</a></span></td>
											</s:else>
		
											
											
											<td class="text-right"><%=Constants.getCurrency(loginfo)%>
											<s:if test="inddiscsts">
											<s:property value="indwodisctot" />
											</s:if>
											<s:else>
											<s:property value="charges" />
											</s:else>
											</td> 
											<td class="text-right"><%=Constants.getCurrency(loginfo)%>
										<s:if test="inddiscsts">
											<s:property value="inddisctot" />
											</s:if>
											<s:else>
											<s:property value="discount" />
											</s:else>
											</td>
											<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="refundAmountx" /></td>
											<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="creditCharge" /></td>
											
											<!--<td class="text-danger"><%=Constants.getCurrency(loginfo)%><s:property value="balancex" /></td>
										--></tr>
									<%
										i++;
									%>
									
								
										<%-- <tr id="invch<s:property value="id"/>" style="display: none; ">
														<td colspan="7">
															<table class="table table-bordered">
																<thead>
																	<tr class="bg-warning">
																		<th>Date</th>
																		<th>Transaction</th>
																		<th class="text-right">Total Charge</th>
																	</tr>
																	</thead>
																	<tbody>
																<s:iterator value="chargeInvoiceList">
																	<tr>
																		<td><s:property value="commencing"/></td>
																		<td><s:property value="chargeInvoiceid"/>  <i class="fa fa-arrow-down hidden-print" onclick="showhidechargeNumberdetails(<s:property value="chargeInvoiceid"/>)"></i></td>
																		<td class="text-right"><%=Constants.getCurrency(loginfo) %><s:property value="invoiceDebitx"/></td>
																	</tr>
																	
																	
																		<tr id="chno<s:property value="chargeInvoiceid"/>" style="display:none;  ">
																				<td colspan="7">
																					<table class="table table-hover table-condensed table-bordered table-striped ">
																						<thead>
																							<tr class="bg-info">
																								<th>Charge Type</th>
																								<th>Quantity</th>
																								<th class="text-right">Unit Price</th>
																								<th class="text-right">Cost</th>
																							</tr>
																							</thead>
																							<tbody>
																							<s:iterator value="chargeInvoList">
																								<tr>
																									<td><s:property value="chargeName"/></td>
																									<td><s:property value="quantity"/></td>
																									<td class="text-right"><%=Constants.getCurrency(loginfo) %><s:property value="charge"/></td>
																									<td class="text-right"><%=Constants.getCurrency(loginfo) %><s:property value="chargeTotal"/></td>
																								</tr>
																							</s:iterator>
																						</tbody>
																					</table>
																				</td>
																		</tr>
																		
																</s:iterator>
																</tbody>
															</table>
														</td>
												</tr> --%>
												
													
											
													
											</s:iterator>
									
									
								
								
								
								
								</s:if>
								


							
							
							<%
								c1 = c1 + 1;
							%>

						</s:iterator>
					</s:if>
					<s:else>
						<%int j = 1; %>
						<s:iterator value="invoiceList">
						
						<s:if test="balancex==0">
								<%bgcolor = "rgba(79 216 197 / 55%);"; %>
							</s:if>
							<s:else>
								<%bgcolor = "rgba(255 159 15 / 75%);"; %>
							</s:else>
										<tr style="background-color: <%=bgcolor %>" id="<s:property value="invoiceid"/>">
											<%-- <td><%=j %></td> --%>
											<td><s:property value="date" /></td>
											<td><a href="#" onclick="recordPaymentForSelectedInvoice(<s:property value="id"/>)">Invoice <%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/>(Ref Id:<s:property value="id"/>)
											<%}else{%>(<s:property value="id" />)<%} %></a> /
														
														<s:if test="deleted==1">
														        Cancelled
														</s:if>
														<s:else>
														      <s:property value="Department"/>
														</s:else>
														 </td> 
											<td><a href="#"  onclick="openBlankPopup('viewInvoiceCharges?invoiceid=<s:property value="id" />&action=show&discount=<s:property value="newdiscount" />&payby=<s:property value="payby" />')"><i class="fa fa-print" aria-hidden="true"></i></a></td>
											<td><a href="#" onclick="openBlankPopup('viewInvoiceCharges?invoiceid=<s:property value="id" />&action=show&discount=<s:property value="newdiscount" />&payby=<s:property value="payby" />&billsummary=1')"  >Bill Summary</a></td>
											
											<s:if test="payby=='Client'">
												<td>Self  <span style="margin-left:5px;"><a href="#">(<s:property value="clientName"/>)</a></span></td>
											</s:if>
											<s:else>
												<td><s:property value="payby" />  <span style="margin-left:5px;"><a href="#">(<s:property value="clientName"/>)</a></span></td>
											</s:else>
		
											<td class="text-right"><%=Constants.getCurrency(loginfo)%>
											<s:if test="inddiscsts">
											<s:property value="indwodisctot" />
											</s:if>
											<s:else>
											<s:property value="charges" />
											</s:else>
											</td>
											<td class="text-right"><%=Constants.getCurrency(loginfo)%>
											<s:if test="inddiscsts">
											<s:property value="inddisctot" />
											</s:if>
											<s:else>
											<s:property value="discount" />
											</s:else>
											</td>
											<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="refundAmountx" /></td>
											<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="creditCharge" /></td>
											<!--<td class="text-danger"><%=Constants.getCurrency(loginfo)%><s:property value="balancex" /></td>
											<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="creditCharge" /></td>
										--></tr>
									<%j++; %>
									
										
										<%-- <tr id="invch<s:property value="id"/>" style="display:none; ">
														<td colspan="7">
															<table class="table table-hover table-condensed table-bordered table-striped ">
																<thead>
																	<tr class="bg-warning">
																		<th>Date</th>
																		<th>Transaction</th>
																		<th class="text-right">Total Charge</th>
																	</tr>
																	</thead>
																	<tbody>
																	<s:iterator value="chargeInvoiceList">
																	<tr>
																		<td><s:property value="commencing"/></td>
																		<td><s:property value="chargeInvoiceid"/>  <i class="fa fa-arrow-down hidden-print" onclick="showhidechargeNumberdetails(<s:property value="chargeInvoiceid"/>)"></i></td>
																		<td class="text-right"><%=Constants.getCurrency(loginfo) %><s:property value="invoiceDebitx"/></td>
																	</tr>
																	
																	<tr id="chno<s:property value="chargeInvoiceid"/>" style="display:none;  ">
																		<td colspan="7">
																			<table class="table table-bordered table-striped ">
																				<thead>
																					<tr class="bg-info">
																						<th>Charge Name</th>
																						<th>Quantity</th>
																						<th class="text-right">Unit Price</th>
																						<th class="text-right">Cost</th>
																					</tr>
																					</thead>
																					<tbody>
																					<s:iterator value="chargeInvoList">
																					<tr>
																						<td><s:property value="chargeName"/></td>
																						<td><s:property value="quantity"/></td>
																						<td class="text-right"><%=Constants.getCurrency(loginfo) %><s:property value="charge"/></td>
																						<td class="text-right"><%=Constants.getCurrency(loginfo) %><s:property value="chargeTotal"/></td>
																					</tr>
																					</s:iterator>
																				</tbody>
																				</table>
																			</td>
																	</tr>
																	
																</s:iterator>
															</tbody>
														</table>
													</td>
											</tr> --%>
												
													
											
														
							</s:iterator>
									
					</s:else>
					
					
					
					
					<!--Show  Client Credit Account  -->
					<!--<s:if test="creditList.size!=0">
							<s:iterator value="creditList" status="rowstatus">
								<tr style="background-color: <%=bgcolor %>" id="<s:property  value="id" />">
								
									<td><s:property value="commencing" /></td>
									<td><a href="#"><%=Constants.CD_CHARGE %> (<s:property value="id"/>)</a></td>
									<s:if test="payby=='Client'">
										<td>Self <a href="#">(<s:property value="ClientName"/>)</a></td>
									</s:if>
									<s:else>
										<td><s:property value="payby" /> <a href="#">(<s:property value="ClientName"/>)</a></td>
									</s:else>
									
									<td>N/A</td>
									
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" /></td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="charges" /></td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="refundAmountx" /></td>
									<%-- <td class="text-danger"><%=Constants.getCurrency(loginfo)%><s:property value="balancex" /></td> --%>
									<td class="text-danger"><%=Constants.getCurrency(loginfo)%>0.00</td>
									
									
									</tr>
								</s:iterator>
						</s:if>
						--><tr style="background-color: #efefef;color: green;">
							<td colspan="4"></td>
							<td class="text-right"><b>Total</b></td>
							<td class="text-right"><b><%=Constants.getCurrency(loginfo)%> <s:property
										value="debitTotalx" /></b></td>
							<td></td>			
							<td class="text-right"><b><%=Constants.getCurrency(loginfo)%> <s:property
										value="totalrefund" /></b></td>
							<td class="text-right"><b><%=Constants.getCurrency(loginfo)%> <s:property
										value="creditTotalx" /></b></td>
							<!--<td class="text-danger"><b><%=Constants.getCurrency(loginfo)%> <s:property value="balanceTotalx" /></b></td>
							--><!-- <td colspan="3"></td> -->
						</tr>
					</tbody>
				</table>
				
			
		</div>
	</div>
	</div>
	<s:if test="accountList!=null">
	
<s:form action="moveStatement" name="paginationForm" id="paginationForm" theme="simple">
		<div class="col-lg-12 col-md-12 hidden" style="padding:0px;margin-top: 15px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">Total:<b class="text-info"><s:property value="totalRecords" />
						</b></div>		 
						<%@ include file="/common/pages/pagination.jsp"%>
		</div>
				
				

			
	</s:form>
</s:if>
	
	
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 <div class="col-lg-3 col-md-3 col-sm-3" >
	       										
	       		<div class="viewaccountdiv">
		<input type="hidden" name="hiddenclientid" id="hiddenclientid" value="<s:property value="clientId" />">
							<b><%=loginfo.getPatientname_field() %> Details:</b>
					
					<br>
					<%-- <b>Name:</b> <s:property value="initial" /><s:property value="firstname" />  <s:property value="lastname" />(<s:property value="dob"/>) --%>
					<b>Name:</b> <s:property value="firstname" />
					<s:property value="address" />
					<s:property value="postcode" /><br>
					<s:if test="mobno!=''">
					<s:property value="mobno" /><br>
					</s:if>
					<s:if test="email!=''">
					<s:property value="email" /><br>
					</s:if>
					
					<div class="hidden">
					<b>Third Party Details</b><br />
					<s:property value="insuranceCompany" /><br>
					<s:property value="thirdPartyAddress" /><br>
					<s:property value="thirdPartyPostcode" /><br>
					<s:property value="thirdPartyContacttno" /><br>
					<s:property value="thirdPartyemail" />
					</div>
	</div>
	       		<div style="clear: both; height: 10px;"></div>								
	       										<div class="viewaccountdiv">
				<h4>Total Charges : <b class="right" style="color:#43b9be"><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx" /></b></h4>
				<hr>
				<h4>Total Discount : <b class="right" style="color:#43b9be"><%=Constants.getCurrency(loginfo)%><s:property value="discount" /></b></h4>
				<hr>
				<h4>Total Payments : <b class="right" style="color:#43b9be"><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx" /></b></h4>
				<hr>
				<h4>Outstanding Payment : <b class="right" style="color:red"><%=Constants.getCurrency(loginfo)%><s:property value="balanceTotalx" /></b></h4>
				<hr>
				
				<s:if test="creditBalancex>0">
				  <h4>Credit Balance : <b class="right" style="color:#43b9be;cursor:pointer;"><%=Constants.getCurrency(loginfo)%><span style="color: green;" onclick="openPopup('creditListAdditional?clientId=<s:property value="clientId" />')"><s:property value="creditBalancex" /></span></b></h4>
				</s:if>
				<s:else>
				   <h4>Credit Balance : <b class="right" style="color:#43b9be"><%=Constants.getCurrency(loginfo)%>0.00</b></h4>
				</s:else>
				<hr>
				<h4>Self Credit : <b class="right" style="color:"><%=Constants.getCurrency(loginfo)%><s:property value="selfcredit" /></b></h4>
			
			 
			</div> 
			
			<div style="clear: both; height: 10px;"></div>
	       										
	       										
	       										
	       										
	       										
	       										
	       										<div class="col-xs-6 col-md-6 panthumb hidden" >
                                                      <div class="thumbnail">
                                                      <a href="#" onclick="openPopup('pendingChargesCompleteApmt')">
                                                          <img src="cicon/pendingcharge.png" alt="Add Clients" style="width: 45px;">
                                                          <div class="caption">
                                                              <p class="textclinet">Pending Charges</p>
                                                          </div>
                                                          </a>
                                                      </div>
                                                  </div>
 													<div class="col-xs-6 col-md-6 panthumb">
 													<%if(loginfo.isAcc_createinvoice()){ %>
                                                      <div class="thumbnail">
                                                      
                                                      <a href="#" onclick="openPopup('Accounts?clientId=<s:property value="clientId" />')"">
                                                          <img src="popicons/invoice.png" alt="assesments_form" style="width: 45px;">
                                                         <div class="caption">
                                                              <p class="textclinet">Create Invoice</p>
                                                          </div>
                                                          </a>
                                                          </div>
                                                          <%}else{ %>
                                                          <div class="thumbnail disabled ">
                                                          		<img src="popicons/invoice.png" alt="assesments_form" style="width: 45px;">
															    <div class="caption">
                                                            		  <p class="textclinet">Create Invoice</p>
														    	</div>
									    					
                                                      </div>
                                                      <%} %>
                                                  </div>
                                                  <div class="col-xs-6 col-md-6 panthumb" >
                                                  <%if(loginfo.isAcc_recordpayment()){ %>
                                                      <div class="thumbnail">
                                                      
                                                      <a href="#" onclick="openPopup('ProcessingAccount?clientId=<s:property value="clientId" />')"">
                                                          <img src="popicons/cash_desk.png" alt="Email" style="width: 45px;">
                                                          <div class="caption">
                                                              <p class="textclinet">Record Payment</p>
                                                          </div>
                                                         </a>
                                                         </div>
                                                          <%}else{ %>
                                                          <div class="thumbnail disabled ">
                                                          		 <img src="popicons/cash_desk.png" alt="Email" style="width: 45px;">
		                                                          <div class="caption">
		                                                              <p class="textclinet">Record Payment</p>
		                                                          </div>
									    					
                                                      </div>
                                                      <%} %>
                                                  </div>
												<div class="col-xs-6 col-md-6 panthumb hidden">
                                                     <div class="thumbnail">
                                                     <a href="#" onclick="openPopup('OutstandingReport?clientId=<s:property value="clientId" />')"">
                                                         <img src="cicon/outinvoice.png" alt="view_Account" style="width: 45px;">
                                                         <div class="caption">
                                                             <p class="textclinet">Outstanding Invoices</p>
                                                         </div>
                                                        </a>
                                                     </div>
                                                 </div>
                                                  <div class="col-xs-6 col-md-6 panthumb">
                                                   <%if(loginfo.isAcc_viewcreditaccount()){ %>
                                                     <div class="thumbnail">
                                                     <a href="#" onclick="openPopup('creditListAdditional?clientId=<s:property value="clientId" />')">
                                                         <img src="popicons/view_account.png" alt="view_Account" style="width: 45px;">
                                                         <div class="caption">
                                                             <p class="textclinet">View Credit Account</p>
                                                         </div>
                                                        </a>
                                                         </div>
                                                          <%}else{ %>
                                                          <div class="thumbnail disabled ">
                                                          		 <img src="popicons/view_account.png" alt="view_Account" style="width: 45px;">
		                                                         <div class="caption">
		                                                             <p class="textclinet">View Credit Account</p>
		                                                         </div>
									    					
                                                        
                                                     </div>
                                                     <%} %>
                                                 </div>
                                                 <div class="col-xs-6 col-md-6 panthumb">
                                                  <%if(loginfo.isAcc_addcharges()){ %>
                                                     <div class="thumbnail">
                                                     <a href="#" onclick="openPopup('debitAdditional?clientId=<s:property value="clientId" />')">
                                                         <img src="cicon/addcharge.png" alt="view_Account" style="width: 45px;">
                                                         <div class="caption">
                                                             <p class="textclinet">Add Charges</p>
                                                         </div>
                                                        </a>
                                                        </div>
                                                          <%}else{ %>
                                                          <div class="thumbnail disabled ">
                                                          		 <img src="cicon/addcharge.png" alt="view_Account" style="width: 45px;">
		                                                         <div class="caption">
		                                                             <p class="textclinet">Add Charges</p>
		                                                         </div>
									    					
                                                     </div>
                                                     <%} %>
                                                 </div>
                                                 <div class="col-xs-6 col-md-6 panthumb">
                                                 <%if(loginfo.isAcc_advandref()){ %>
                                                     <div class="thumbnail">
                                                     <a href="#" onclick="openPopup('creditAdditional?clientId=<s:property value="clientId" />')">
                                                         <img src="cicon/raise_credit.png" alt="view_Account" style="width: 45px;">
                                                         <div class="caption">
                                                             <p class="textclinet">Advance & Refund</p>
                                                         </div>
                                                        </a>
                                                         </div>
                                                          <%}else{ %>
                                                          <div class="thumbnail disabled ">
                                                          		<img src="cicon/raise_credit.png" alt="view_Account" style="width: 45px;">
		                                                         <div class="caption">
		                                                             <p class="textclinet">Advance & Refund</p>
		                                                         </div>
									    					
                                                     </div>
                                                     <%} %>
                                                 </div>
                                                  <div class="col-xs-6 col-md-6 panthumb">
                                                   <%if(loginfo.isAcc_chargedetails()){ %>
                                                     <div class="thumbnail">
                                                     <a href="#" onclick="openPopup('detailStatement?clientId=<s:property value="clientId" />')">
                                                         <img src="popicons/view_treatment.png" alt="Charge Details" style="width: 45px;">
                                                         <div class="caption">
                                                             <p class="textclinet">Charge Details</p>
                                                         </div>
                                                        </a>
                                                         </div>
                                                          <%}else{ %>
                                                          <div class="thumbnail disabled ">
                                                          		 <img src="popicons/view_treatment.png" alt="Charge Details" style="width: 45px;">
		                                                         <div class="caption">
		                                                             <p class="textclinet">Charge Details</p>
		                                                         </div>
									    					
                                                     </div>
                                                     <%} %>
                                                 </div>
                                                 
                                                  <div class="col-xs-6 col-md-6 panthumb hidden">
                                                     <div class="thumbnail">
                                                     <a href="#" onclick="openPopup('otdetailsStatement?clientId=<s:property value="clientId" />')">
                                                         <img src="popicons/view_treatment.png" alt="Charge Details" style="width: 45px;">
                                                         <div class="caption">
                                                             <p class="textclinet">OT Details</p>
                                                         </div>
                                                        </a>
                                                         </div>
                                                 </div>
                                                 
                                                 <div class="col-xs-6 col-md-6 panthumb hidden">
                                                 <%if(loginfo.isAcc_chargeinvoice()){ %>
                                                     <div class="thumbnail">
                                                     <a href="#" onclick="openPopup('chargeinvoiceStatement?clientId=<s:property value="clientId" />')">
                                                         <img src="popicons/view_treatment.png" alt="Charge Invoice" style="width: 45px;">
                                                         <div class="caption">
                                                             <p class="textclinet">Charge Invoice</p>
                                                         </div>
                                                        </a>
                                                         </div>
                                                          <%}else{ %>
                                                          <div class="thumbnail disabled ">
                                                          		 <img src="popicons/view_treatment.png" alt="Charge Invoice" style="width: 45px;">
		                                                         <div class="caption">
		                                                             <p class="textclinet">Charge Invoice</p>
		                                                         </div>
									    					
                                                     </div>
                                                     <%} %>
                                                 </div>
                                                 <div class="col-xs-6 col-md-6 panthumb">
                                                     <div class="thumbnail">
                                                     <a href="#" onclick="getpackage()">
                                                         <img src="popicons/view_treatment.png" alt="Charge Details" style="width: 45px;">
                                                         <div class="caption">
                                                             <p class="textclinet">Select Package</p>
                                                         </div>
                                                        </a>
                                                         </div>
                                                 </div>
	       </div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	       </div>
                                                
                                                 
                                              </div>
                                             
	</div>
	
                                              </div>
                                          </div><br />
	
	
	
	


</s:form>


<s:actionmessage cssClass="messageDiv" />



<s:form action="ProcessingAccount" id="viewaccfrm">
	<s:hidden name="actionType" value="viewacc"/>
	<s:hidden name="selectedInvoiceid" id="selectedInvoiceid"/>
	<s:hidden name="clientId"/>

</s:form>







	

<div class="modal fade" id="transactionPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Transaction List</h4>
      </div>
      <div class="modal-body">
      <div class="table-responsive">
       <div id="transactionList">
       
       </div>
	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><%=loginfo.getPatientname_field() %> Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
function printDiv(divName) {

     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}
</script>


<!-- Share Charge with Doctors -->
<%-- <div id="sharecharge" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Share with Doctor</h4>
      </div>
      <div class="modal-body">
      <div class="col-lg-12 col-md-12 col-xs-12">
              <s:hidden id="indiorall"></s:hidden>
              <s:hidden id="share_invoiceid"></s:hidden>
              <s:hidden id="share_chargeid"></s:hidden>
              <s:hidden id="share_chargename"></s:hidden>
              
               <div class="form-group" style="margin: 0px;color: green;font-size: 15px;">
              		<span><label>Total:</label> Rs.<span id="share_chargetotal">0.00</span></span>
            	</div>
              
                <div class="form-group">
              		<label for="email">Select Doctor</label>
		              <select class="form-control" name="doct" id="">
		              		<option value="0">Select</option>
		              		<option value="Dr.Abc">Dr.Abc</option>
			                <option value="Dr.Xyz">Dr.Xyz</option>
		              </select>
		              <s:select list="doctorlist" cssClass="form-control chosen-select" listKey="id" listValue="name" id="share_doctorid" headerKey="0" headerValue="Select"></s:select>
            </div>
               
               </div>
               
               
               
               <div class="col-lg-12 col-md-12 col-xs-12" style="background-color: rgba(239, 239, 239, 0.33);    padding: 7px 0px 0px 0px;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              		<label for="email" style="color:red;">Discount</label>
		              <select class="form-control" name="disctype" id="disctype" onchange="changeShareDiscountType()">
		              		<option value="Percent">Percent</option>
			                <option value="Rupees">Rupees</option>
		              </select>
            </div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                 <input type="number" id="share_amount" onblur="calculateShareAmount(this.value)" class="form-control"  />
               </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="margin: 0px;color: green;font-size: 15px;">
               <div class="form-group" style="margin: 0px;">
              		<span><label>Commission:</label> Rs.<span id="share_calamount">0</span></span>
            	</div>
               </div>
               
               
               <div class="col-lg-12 col-md-12 col-xs-12 hidden" style="border-bottom: 1px solid #ddd;margin-bottom: 15px;">
               		<p style="font-size:15px;margin: 3px 15px 3px 15px;color: green;">Total To Paid <span style="float: right;"> Rs.00.00</span></p>
               </div>
               
           <div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 15px;">
            <div class="form-group">
              <label for="email">Remark</label>
              <textarea type="text" rows="3" id="sharing_remark" class="form-control" placeholder="write remark"></textarea>
            </div>
         </div>
               
             </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary"  onclick="saveShareChargeToDr()">Save</button>
      </div>
    </div>

  </div>
</div> --%>

<div id="newsharecharge" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Share with Doctor</h4>
      </div>
      <div class="modal-body">
      <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
              <s:hidden id="newindiorall"></s:hidden>
              <s:hidden id="newshare_invoiceid"></s:hidden>
              <s:hidden id="newshare_chargeid"></s:hidden>
              
              <div class="col-lg-7 col-md-7 col-xs-7 col-sm-7" style="padding: 0px;">
              <div class="shareheight">
              	<table class="table table-responsive">
              		<thead>
              			<tr>
              				<th>Sharing With</th>
              				<th>Amt</th>
              				<th>UserId / Date</th>
              			</tr>
              		</thead>
              		<tbody id="tbodysharecharge">
              			<tr>
              				<td>Dr. Avnish Shirke</td>
              				<td>Rs.2500 (5%)</td>
              				<td>Demo / 17-10-2017 05:17:45</td>
              			</tr>
              		</tbody>
              	</table>
              </div>
              	
              </div>
                <div class="col-lg-5 col-md-5 col-xs-5 col-sm-5" style="padding: 10px;background-color: rgba(221, 221, 221, 0.19);border: 1px dashed #ddd;">
              	<div class="form-group" style="margin-bottom: 0px;">
              		<label>Charge Name: <span><span id="newshare_chargename"></span></span></label>
              	</div>
              	<div class="form-group" style="margin-bottom: 0px;color:green;">
              		<label>Amount: <span>Rs.<span id="newshare_chargetotal">0.00</span></span></label>
              	</div>
              	<div class="form-group" style="margin-bottom: 0px;">
              		<label>Shared Amount: <span>Rs.<span id="newshare_amount">0.00</span></span></label>
              	</div>
              		<div class="form-group" style="margin-bottom: 0px;color:red;">
              		<label>Balance: <span>Rs.<span id="newshare_balance">0.00</span></span></label>
              	</div>
              	<div class="form-group" style="margin-bottom: 0px;margin-top: 15px;">
              		<s:select list="doctorlist" cssClass="form-control chosen-select" listKey="id" listValue="name" id="newshare_doctorid" headerKey="0" headerValue="Select"></s:select> 
              	</div>
              	<div class="form-group" style="background-color: rgba(221, 221, 221, 0.67);margin: 0px;padding: 10px;margin-top: 15px;">
              		<div class="form-inline">
              			<div class="form-group">
              				 <select class="form-control" name="sharetype" id="sharetype" onchange="newchangeShareDiscountType()">
			              		<option value="Percent">%</option>
				                <option value="Rupees">Rs.</option>
		              		</select>
              			</div>
              			<div class="form-group" style="width: 35%;">
              				<input type="number" id="iptext" onblur="newcalculateShareAmount(this.value)" class="form-control"  style="width: 100%;"/>
              			</div>
              			<div class="form-group" style="float: right;margin-top: 10px;font-weight: bold;">
              				<span>Rs.<span id="newshare_calamount">0</span></span>
              			</div>
              		</div>
              	</div>
              	<div class="form-group" style="margin-top: 15px;">
	              <textarea type="text" rows="3" id="newsharing_remark" class="form-control" placeholder="write remark"></textarea>
	            </div>
              </div>
               </div>
           
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary"  onclick="saveShareChargeToDrNew()" style="margin-top: 15px;">Save</button>
      </div>
    </div>

  </div>
</div>


<%-- <div id="pkgnewmew" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Package</h4>
      </div>
      <div class="modal-body">
      <div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
      <form action="savePkgDataIpdDashboard">
      <div class='form-inline'>
       <div class="form-group">
				  	<input type="hidden" name="ipdid" id="pkgipdid">
				  	<input type="hidden" name="clientid" id="pkgclienid">
				    <label for="exampleInputName2">Start Date</label>
				    <s:textfield  name="pkgfromdate" id="pkgfromdate" cssClass="form-control"/>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail2">End Date</label>
				    <s:textfield  name="pkgtodate" id="pkgtodate" cssClass="form-control"/>
				  </div>
        <div class="form-group">
				    <label for="exampleInputName2">HH:MM</label>
									  <div class="form-group">
									    <s:select name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="0" headerValue="Select"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select name="minute" id="minute" list="minuteList" cssClass="form-control mmwidthmang" headerKey="0" headerValue="Select"/>
									  </div>
				  </div>
				  <div class="form-group">
				   <input type="button" value="Pkg" onclick="openpatientpkgmaster()" class='btn btn-primary'>
				   </div>
				   </div>
 					<div class="form-group" style="width: 65%;">				   
				     <s:select onchange="showPackageData(this.value)" name="packagename" id="packagename" list="packageList"
					  	listKey="id" listValue="name" headerKey="0" headerValue="Select Package"
					  	cssClass="form-control chosen-select" style="width:100%;"
					  />
					  </div>
					   <div class="form-group">
					    <label class="sr-only" for="exampleInputEmail3">Email address</label>
					  <input type="text" placeholder="Change Name" id="newpackagename" name="newpackagename" class="form-control"/>
					  </div>
					  <div class="col-lg-3 col-md-3 col-xs-4" style="padding-right: 0px;">
        		<div class="form-group">
        			<span style="float: right;font-size: 15px;color: green;">Amount : <input type="text" id="pkgamtid" name="newpackageamt" onblur="changePackageTotal(this.value)" name="pkgamtid" class="form-control" style="width: 60%;float: right;"/></span>
        		</div>
        	</div>
      
       <div class="modal-footer" style="padding-right: 10px;">
        <input type="submit" onclick="return validatepackage()" class="btn btn-primary" style="margin-top: 15px;" value="Apply Package">
      </div>
      </form>
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" style="margin-top: 15px;">Save</button>
      </div>
    </div>

  </div>
</div>
</div> --%>
<div class="modal fade" id="pkgnewmew" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Select Package</h4>
      </div>
      <form action="savePkgDataFromAccIpdDashboard">
      <div class="modal-body">
     	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
        	<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #efefef;padding: 5px 5px 5px 5px;">
        		<div class="form-inline">
				  <div class="form-group">
				  	<input type="hidden" name="ipdid" id="pkgipdid">
				  	<input type="hidden" name="clientid" id="pkgclienid">
					<input type="hidden" name="lastipdid" id="lastipdid" value="<s:property value="lastipdid" />">
					
				    <label for="exampleInputName2">Start Date</label>
				    <s:textfield  name="pkgfromdate" id="pkgfromdate" cssClass="form-control" cssStyle="width:86px" onchange="diffoftwodate();"/>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail2">End Date</label>
				    <s:textfield  name="pkgtodate" id="pkgtodate" cssClass="form-control" cssStyle="width:86px" onchange="diffoftwodate();"/>
				    <input type="hidden"  id="actualpkgfromdate">
				     <input type="hidden"  id="actualpkgtodate">
				      <input type="hidden"  id="actualdays">
				  </div>
				  
				  <div class="form-group">
				    <label for="exampleInputName2">HH:MM</label>
									  <div class="form-group">
									    <s:select name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="0" headerValue="Select" cssStyle="width:46px"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select name="minute" id="minute" list="minuteList" cssClass="form-control mmwidthmang" headerKey="0" headerValue="Select" cssStyle="width:46px"/>
									  </div>
				  </div>
				   <div class="form-group">
				    <label for="exampleInputName2">Duration</label>
				   <input type="text" id="days" readonly="readonly" class="form-control" style="width: 20px">Days
				   </div> 
				   <div class="form-group">
				   <input type="button" value="New Package" onclick="openpatientpkgmaster()" class='btn btn-primary'>
				   </div>
				  <!--
				  <span style="color: crimson;">3 Days</span>
				  <span style="float: right;font-size: 15px;margin-top: 4px;">Code: 123</span>
				--></div>
        	</div>
        	
        	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
        		<div class="col-lg-9 col-md-9 col-xs-8" style="padding: 0px;">
        		<div class="col-lg-12 col-md-12 col-xs-12" style="padding-left: 0px;">
        		<div class="form-inline">
					  <div class="form-group" style="width: 65%;">
					  <input type="hidden" name='tpnew' id='tpnew'>
					    <label class="sr-only" for="exampleInputEmail3">Email address</label>
					  <s:select onchange="showPackageData(this.value)" name="packagename" id="packagename" list="packageList"
					  	listKey="id" listValue="name" headerKey="0" headerValue="Select Package"
					  	cssClass="form-control chosen-select" style="width:100%;"
					  />
					  </div>
					  <div class="form-group">
					    <label class="sr-only" for="exampleInputEmail3">Email address</label>
					  <input type="text" placeholder="Change Name" id="newpackagename" name="newpackagename" class="form-control"/>
					  </div>
					  <button class="btn btn-warning hidden" data-toggle="collapse" data-target="#packagedemo">Add More</button>
					</div>
        		</div>
        	</div>
        	<div class="col-lg-3 col-md-3 col-xs-4" style="padding-right: 0px;">
        		<div class="form-group">
        			<span style="float: right;font-size: 15px;color: green;">Amount : <input type="text" id="pkgamtid" name="newpackageamt" onblur="changePackageTotal(this.value)" name="pkgamtid" class="form-control" style="width: 60%;float: right;"/></span>
        		</div>
        	</div>
        	</div>
        	
        	<div id="packagedemo" class="collapse">
				<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
        			<div class="form-inline">
						  <div class="form-group" style="width: 426px;">
						    <label class="sr-only" for="exampleInputEmail3">Email address</label>
						    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="Enter name" style="width:100%;background-color: aliceblue;">
						  </div>
						  <div class="form-group" style="width: 45px;">
						    <label class="sr-only" for="exampleInputPassword3">Password</label>
						    <input type="text" class="form-control" id="exampleInputPassword3" placeholder="%" style="width:100%;background-color: aliceblue;">
						  </div>
						  <button type="submit" class="btn btn-info">+</button>
						</div>
        		</div>
			</div>
        	
        	
        	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
	        		<table class="table" style="border: none;">
	        		<thead>
	        			<tr>
	        				<th style="width: 60%;background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        			</tr>
	        		</thead>
	        			<tbody id="pkgdtailbody">
	        				<!--<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Ortho Surgen</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="50%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.50,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">OT Charge</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="20%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.20,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Anesthesia Surgen</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Equipment</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Other</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;background-color: cornsilk;">
	        					<td style="font-size: 14px;"><b>Total</b></td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="100%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><b><input style="text-align: right;" type="text" class="form-control" value="Rs.1,00,000.00"></b></td>
	        					<td></td>
	        				</tr>
	        			--></tbody>
	        		</table>
        	</div>
        </div>
      </div>
      <div class="modal-footer" style="padding-right: 10px;">
        <input type="submit" onclick="return validatepackage()" class="btn btn-primary" style="margin-top: 15px;" id="selectpkgbtn" value="Select Package">
      </div>
      </form>
    </div>
  </div>
</div>
<!-- Loader -->
	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="newloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>
	<!--End loader  -->		

  
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
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
  //abhi created 17-10-2017
				             $(function() {
								  $('.shareheight').slimScroll({
								   		height : '300px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
/*         
  function neww(){
	  $('#pkgnewmew').modal( "show" );
  } */
  function getpackage(){
		var clientid = document.getElementById("clientId").value;
		var ipdid = document.getElementById("lastipdid").value;
		document.getElementById("pkgipdid").value = ipdid;
		document.getElementById("pkgclienid").value = clientid;
		$('#pkgnewmew').modal( "show" );
	}
  </script>

