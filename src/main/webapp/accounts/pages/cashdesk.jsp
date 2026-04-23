<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>

<!-- <link rel="stylesheet" href="_assets/newtheme/css/main.css"> -->


<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>
window.onload = function () {
	
	currencySign = '<%=Constants.getCurrency(loginfo)%>';	
}
$(window).on('load', function() {
	$('#newloaderPopup').modal('hide');
});
		
	  
    $(document).ready(function() {
    	$('#newloaderPopup').modal('show');
    });	

 function getchno(val){
 
        
        if(val=='Cheque'){
            document.getElementById("chdiv").className="form-group";
        } else {
        
        	document.getElementById("chdiv").className="form-group hidden";
        }
   
} 
 

</script>

<style>	
body {
	background-color: #fff !important;
}
.cash{
	padding-bottom: 15px;
    background-color: indianred;
    padding-top: 15px;
    color: #fff;
}
.backwhite{
	    background-color: #fff;
}
.btn-lg {
    height: 35px !important;
}
.topbg{
	padding-left: 0px;
    padding-right: 0px;
    background-color: #efefef;
    padding: 20px 0px 20px 0px;
}
.topbg1{
    background-color: #efefef;
    padding-top: 20px;
    padding-bottom: 20px;
}
.checkmainback{
	background-color: #5bbaa7;
    padding-top: 30px;
    padding-bottom: 30px;
}
.formborbot{
	border: 0px !important;
    border-bottom: 2px solid #dddddd !important;
    background-color: transparent !important;
}
hr {
    border-top: 2px solid #ddd !important;
    width:96% !important;
}
.enterchamt{
	background-color: #fff !important;
    border: none !important;
    height: 38px !important;
}
.rupsymbol{
	background-color: #fff;
    border: none;
    font-size: 18px;
}
.borderboset{
	    margin-top: 20px;
    border-bottom: 1px solid #ddd;
    padding-bottom: 15px;
}
.thfont{
	    font-size: 18px;
    font-weight: bold !important;
}

.setbofocash{
	    border: 2px solid #e0e0e0;
}
.appWrapper.aside-fixed #sidebar {
    min-height: 666px !important;
}
.marbot0set{
	    margin-bottom: 0px;
}
.borrights{
	border-right: 1px solid #efefef;
    min-height: 450px;
}
.selftlefpaymenu{
	background-color: #fff;
    padding-left: 15px;
    padding-top: 0px;
    padding-bottom: 10px;
    min-height: 580px;
    border-left: 1px solid #ddd;
}
.topback2 {
    background-color: #f5f5f5;
    padding: 5px 0px 5px 15px;
}
.tab-content .tab-pane {
     padding: 0px !important; 
}
.nav-tabs > li {
    float: right;
}
label {
    font-weight:bold;
}
.fonwidg{
	font-weight: normal !important; 
}
</style>


<style>
/*  bhoechie tab */
div.bhoechie-tab-container{
  z-index: 10;
  background-color: #ffffff;
  padding: 0 !important;
  border-radius: 4px;
  -moz-border-radius: 4px;
  border:1px solid #ddd;
  margin-top: 20px;
  margin-left: 0px;
  -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  box-shadow: 0 6px 12px rgba(0,0,0,.175);
  -moz-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  background-clip: padding-box;
  opacity: 0.97;
  filter: alpha(opacity=97);
}
div.bhoechie-tab-menu{
  padding-right: 0;
  padding-left: 0;
  padding-bottom: 0;
}
div.bhoechie-tab-menu div.list-group{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a .glyphicon,
div.bhoechie-tab-menu div.list-group>a .fa {
  color: #5A55A3;
}
div.bhoechie-tab-menu div.list-group>a:first-child{
  border-top-right-radius: 0;
  -moz-border-top-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a:last-child{
  border-bottom-right-radius: 0;
  -moz-border-bottom-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a.active,
div.bhoechie-tab-menu div.list-group>a.active .glyphicon,
div.bhoechie-tab-menu div.list-group>a.active .fa{
  background-color: #5A55A3;
  background-image: #5A55A3;
  color: #ffffff;
}
div.bhoechie-tab-menu div.list-group>a.active:after{
  content: '';
  position: absolute;
  left: 100%;
  top: 50%;
  margin-top: -13px;
  border-left: 0;
  border-bottom: 13px solid transparent;
  border-top: 13px solid transparent;
  border-left: 10px solid #5A55A3;
}

div.bhoechie-tab-content{
  background-color: #ffffff;
  /* border: 1px solid #eeeeee; */
  padding-left: 20px;
  padding-top: 10px;
}

div.bhoechie-tab div.bhoechie-tab-content:not(.active){
  display: none;
}

</style>
<s:form action="savecashCharges" theme="simple" id="cashdeskfrm">
<s:hidden name="ledgerservicestr" id="ledgerservicestr"/>
<s:hidden name="clraradv" id="clraradv"/>
<s:hidden name="ispreiousipdid"></s:hidden>
<s:hidden name="ipdidnew"></s:hidden>
<s:hidden name="hdnmorehowpaid" id="hdnmorehowpaid"/>
<s:hidden name="hdnmorepaudamount" id="hdnmorepaudamount"/>
<s:hidden name="hdnbnkname" id="hdnbnkname"/>
<s:hidden name="sessioncount" id="sessioncount"></s:hidden>
<s:hidden name="refpayMode" id="refpayMode"/>			
<div class="">
	<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Cash Desk for <b><s:property value="initial"/> <s:property value="firstname"/> <s:property value="lastname"/></b></h4>

									</div>
								</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2">





<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				
				
			%>

	<s:hidden name="advref" id="advref" /><!--int aj  -->
	<s:hidden name="client" id="client" />
	<s:hidden name="clientId" id="clientId" />
	<s:hidden name="payby" id="payby" />
	<s:hidden name="creditCharge" id="creditCharge" />
	<s:hidden name="totalassesment" id="totalassesment" />
	<s:hidden name="practitionerId" id="practitionerId" />
	<s:hidden name="practitionerName" id="practitionerName" />
	<s:hidden name="invoiceid" id="invoiceid" /><!--int  -->
	<s:hidden name="debitTotalx" id="hdndebittotal" value="%{debitTotal}" /> <!--double aj  -->
	<s:hidden name="location" id="loaction"/>
	<s:hidden name = "creditDebitCharge" id = "creditDebitCharge"></s:hidden>
	<s:hidden name = "creditChargeId" id = "creditChargeId"></s:hidden>
	<s:hidden name="appointmentid" id="appointmentid"/>
	<%-- <s:hidden name="crdAmount" id="crdAmount"/> --%>
	<s:hidden name="creditNote" id="creditNotes"/>
			
			
			
			<div>
				<div class="form-inline">
				  <div class="form-group" style="width: 100%;">
				    <label for="exampleInputName2" data-toggle="collapse" data-target="#demo" style="cursor:pointer;">Date :</label>
				   <%if(loginfo.isInvoice_date_modify()){ %>
				<input type="text"  id="invoiceDate"  name="invoiceDate" class="form-control"  value="<%=temp[0] %>" ></input>
				<% }else{%>
				<input type="text"   readonly="readonly"  class="form-control"  value="<%=temp[0] %>" ></input>
				<input type="hidden" name="invoiceDate" id="invoiceDate" value="<%=temp[0] %>">
				<%} %>
				  </div>
				</div>
			</div>
			
			<div id="demo" class="collapse">
					<div class="radio-inline">
					  <label style="line-height: 22px;">
					    <input type="radio" name="invoicepstype" id="pr" value="0" checked="true"/>
					    Primary
					  </label>
					</div>
					<div class="radio-inline">
					  <label style="line-height: 22px;">
					    <input type="radio" name="invoicepstype" id="se" value="1"/>
					    Secondary
					  </label>
					</div>
					
			</div>
			
			

	

</div>	

<%-- 	<div style="font-weight: bold; font-size: 12px;">Invoice can be
		raise as below</div>
	<br>

	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table class="table table-hover table-condensed table-bordered">
					<thead>
						<tr>
							<th>Status</th>
							<th>To</th>
							<th>Invoicee</th>
							<th>No. of Charges</th>
							<th>Total</th>
							<th>Location</th>

						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Invoiced</td>
							<s:if test="payby == 'Client'">
								<td>Client</td>
								<td><s:property value="initial"/> <s:property value="firstname"/> <s:property value="lastname"/></td>
							</s:if>
							<s:else>
								<td>Third Party</td>
								<td><s:property value="insuranceCompany" /></td>
							</s:else>


							<td><s:property value="numberOfChages" /></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotal" /></td>
							<td><s:property value="locationName"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<br> --%>
	

	<s:hidden id="hiddenbalence" name="balanceAmt"/> <!-- double aj  -->
	<s:hidden id="prepaymntamntid" name="balanceAmount"/>
	<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left: 0px;padding-right:0px;">
	  <div role="tabpanel">

                                <!-- Nav tabs -->
                               <!--  <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#details" aria-controls="details" role="tab" data-toggle="tab">Payment</a></li>
                                    <li role="presentation" class="hidden"><a href="#notes" aria-controls="notes" role="tab" data-toggle="tab">Online Payment</a></li>
                                </ul> -->


                                <div class="tab-content">
                                    <!-- tab in tabs -->
                                    <div role="tabpanel" class="tab-pane active" id="details">
<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding: 0px;">

	
<div class="col-lg-7 col-md-7 col-sm-7" style="padding-left: 0px;">
<h4 style="color: #16a085;">Record Payment for below Invoice</h4>
<div>
				<table id="assementtable" class="table table-condensed table-bordered">
					<thead>

						<tr style="background-color: #65c4fd">
							<th>Sr.No.</th>
							<th>Date</th>
							<th>Transaction</th>
							<th>Description</th>
							<th class="text-right">Debit</th>
							<th class="text-right">Credit</th>
							<th class="text-right">Balance</th>

						</tr>
					</thead>
					<tbody>
						<%int c1=1; String tcnt=""; %>
						
						<s:if test="creditDebitCharge==0">
							<s:iterator value="accountList">
							<%if(c1<=9) {tcnt = "0"+c1;}else{tcnt = ""+c1+"";}%>
							<tr>
							<%if(loginfo.getClinicid1().equals("dentalcl")){ %>
								<td><%=tcnt%> <input type="checkbox" name="chid" class="chcaseinvst"
									id="<s:property value="invoiceid"/><%=c1%>"
									value="<s:property value="invoiceid"/>" checked="checked"
									onclick="setTotalCashDeskCharges('<s:property value="debitAmount"/>','<s:property value="invoiceid"/><%=c1%>');"></td>
							<%}else{ %>	
                               <td><%=tcnt%> <input type="checkbox" name="chid" class="chcaseinvst"
									id="<s:property value="invoiceid"/>"
									value="<s:property value="invoiceid"/>" checked="checked"
									onclick="setTotalCashDeskCharges('<s:property value="debitAmount"/>','<s:property value="invoiceid"/>');"></td>
							<%} %>
								<td><s:property value="commencing" /></td>
								<s:if test="chargeType=='Invoice'">
									<td><s:property value="chargeType" />(<s:property
											value="invoiceid" />)<br> <a href="javascript: void(0);"
										onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><img
											style="margin-left: 20%;" width="20" height="15"
											align="middle" src="common/images/Arrows-Down-icon.png" /></a></td>
								</s:if>
								<s:else>
									<td><s:property value="chargeType" /><a href="javascript: void(0);"
										onclick="showDetailsDiv('hiddenDetailsDiv<s:property value="invoiceid"/>','<s:property value="invoiceid"/>','<s:property value ="payby"/>');"><img
											style="margin-left: 20%;" width="20" height="15"
											align="middle" src="common/images/Arrows-Down-icon.png" /></a></td>
								</s:else>
								<s:if test="apmtChargeType == 1">
								<td>Additional Charge (<s:property value="appointmentType" />)</td>
								</s:if>
								<s:else>
								<td><s:property value="appointmentType" /><br>
									Treatment: <s:property value="treatmentEpisodeName" /><br>
									Practitioner: <s:property value="practitionerName" /></td>
								</s:else>
								<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx" /></td>
								<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx" /></td>
								<td class="text-right" style="color: red;"><%=Constants.getCurrency(loginfo)%><s:property
										value="debitAmountx" /></td>
							</tr>
							<tr class="text-right" id="hiddenDetailsDiv<s:property value="invoiceid"/>"
								style="display: none">
								<td colspan="7"
									id="hiddenDetailsDiv1<s:property value="invoiceid"/>"></td>
							</tr>
							<%c1 = c1+1;%>
						</s:iterator>
						
						<tr class="text-right" style="border-top: 2px solid #efefef;font-size: 16px;">
							<td colspan="3"></td>
							<td style="text-align: center;"><b>Total</b></td>
							<input type="hidden" name="hdndiscdebit" id="hdndiscdebit" value="<s:property value="debitTotal"/>"/>
							<td id="totaltdid"><b><%=Constants.getCurrency(loginfo)%> <s:property
										value="debitTotal" /></b></td>
							<td><b><%=Constants.getCurrency(loginfo)%> <s:property
										value="creditTotal" /></b></td>
							<td id="balancetotalid" style="color: red"><b><%=Constants.getCurrency(loginfo)%>
									<s:property value="balanceTotal" /></b></td>

							<input type="hidden" name="hdntotal" id="hdntotal" value="<s:property value="debitTotal"/>" />
						</tr>
						
						
						</s:if>
						<s:else>
							<s:iterator value="accountList">
								<%if(c1<=9) {tcnt = "0"+c1;}else{tcnt = ""+c1+"";}%>
								<tr>
									<td><%=tcnt%> <input type="checkbox" name="chid" disabled="disabled"
										id="<s:property value="invoiceid"/>"
										value="<s:property value="invoiceid"/>" checked="checked"
										onclick="setTotalCashDeskCharges('<s:property value="debitAmount"/>','<s:property value="invoiceid"/>');"></td>
									<td><s:property value="commencing" /></td>
									<td>Charge</td>
									
									<td><s:property value="appointmentType" /></td>
									
									
									<td class="text-right"><%=Constants.getCurrency(loginfo)%>0.00</td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="charges" /></td>
									<td class="text-right" style="color: red;"><%=Constants.getCurrency(loginfo)%><s:property
											value="charges" /></td>
								</tr>
								<tr id="hiddenDetailsDiv<s:property value="invoiceid"/>"
									style="display: none">
									<td colspan="7"
										id="hiddenDetailsDiv1<s:property value="invoiceid"/>"></td>
								</tr>
								<%c1 = c1+1;%>
						</s:iterator>
						
							<tr class="text-right">
							
								<td colspan="3"></td>
								<td style="text-align: center;"><b>Total</b></td>
								<input type="hidden" name="hdndiscdebit" id="hdndiscdebit" value="<s:property value="debitTotal"/>"/>
								<td class="text-right" id="totaltdid"><b><%=Constants.getCurrency(loginfo)%>0.00</b></td>
								<td class="text-right"><b><%=Constants.getCurrency(loginfo)%> <s:property
											value="debitTotal" /></b></td>
								<td class="text-right" id="balancetotalid" style="color: red"><b><%=Constants.getCurrency(loginfo)%>
										<s:property value="debitTotal" /></b></td>
	
								<input type="hidden" name="hdntotal" id="hdntotal" value="<s:property value="debitTotal"/>" />
							</tr>
						</s:else>

					</tbody>
				</table>
			</div>
			
			
				<div class="col-lg-8 col-md-12 col-xs-12" style="padding: 0px;">
						<table class="table table-bordered" >
						<thead>
							<tr>
							<th>Services</th>
							<th>Amount</th>
							</tr>
							</thead>
							
								<tbody>
							<s:iterator value="ledgerservicesList">
								<tr>
									<td> <input type="checkbox" name="lservicechid" class="lservicechidcase"
									id="<s:property value="id"/>"
									value="<s:property value="charge"/>" checked="checked"
									onclick="calcledgerrecamt('<s:property value="charge"/>','<s:property value="id"/>');">
									<s:property value="name" />
									</td>
									
								<td><%=Constants.getCurrency(loginfo)%><s:property value="charge" /></td>

								</tr>
							</s:iterator>
							</tbody>
						</table>
				</div>


	</div>
	
	
	<div class="col-lg-5 col-md-5 col-sm-5 selftlefpaymenu">
	<h4 style="color: #16a085;">Payment Details</h4>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">  
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
			  <%if(loginfo.getJobTitle().equals("PHYSIO RECEPTION")){ %>
			      <!-- For balgopal physio ipd -->
				<div class="form-group">
			    <label for="exampleInputEmail1">Invoice Type</label>
			    <s:select name="invcetype" id="invcetype" list="invoiceTypeLis" cssClass="form-control" listKey="id" listValue="name" headerKey="0" />
			  </div>
			  <%}else{ %>
			     <div class="form-group">
			    <label for="exampleInputEmail1">Invoice Type</label>
			    <s:select name="invcetype" id="invcetype" list="invoiceTypeLis" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Invoice Type"/>
			  </div>
			  <%} %>
			  
			  
			</div>
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
			<div class="form-group">
		    <label for="exampleInputEmail1">Payment Mode</label>
		    <select name="howpaid" id="howpaid" class="form-control" onchange="getchno(this.value,<s:property value="debitTotal" />,<s:property value="balanceAmount"/>)">
				<option value="0">Select</option>
				<option value="Cash">Cash</option>
				<option value="Cheque">Cheque</option>
				<option value="NEFT">NEFT</option>
				<!--<option value="BACS">BACS</option>-->
				<!-- <option value="C/Card">Credit Card</option> -->
				<option value="D/Card">D/Card</option>
				<option value="UPI">UPI</option>
				<!-- <option value="Other">Other</option> -->
				
				<!--<option value="S/O">S/O</option>
				--><s:if test="creditDebitCharge==0">
					<option value="<%=Constants.PREPYMENT %>"> Credit Balance <%=Constants.getCurrency(loginfo) %><s:property value="balanceAmount"/></option>
				</s:if>
			</select> 
		  </div>
		  
		  
		  		
			
		</div>
		
		<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
			<div class="form-group">
		    <label for="exampleInputEmail1">Select Bank</label>
		    	 <s:select name="bnkname" id="bnkname" list="bankNameList"
		  cssClass="form-control" listKey="id" listValue="name"
		  headerKey="0" headerValue="Select Bank"
		  />
		    </div>
		   </div>
	</div>
	<%LoginInfo info= (LoginInfo)session.getAttribute("logininfo"); %>
	<input type="hidden" id="lmhac" value="<%=loginfo.isLmh()%>">
	<div class="row" id="morepaymntdivid" style="display: none;">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">  
		<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12" >
		<div class="form-group">
				<label for="exampleInputEmail1"> More Payment</label>
				<div><a href="#" onclick="showmorepaymentpopup(<s:property value="debitTotal" />,<s:property value="balanceAmount"/>,<s:property value=""/>)"> + </a></div>
			</div>
		</div>	
	</div>
	</div>
	
	<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">  
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1">Balance to Pay <%=Constants.getCurrency(loginfo) %></label>
					    <s:textfield onchange="setAmountDueTotal(this.value);" name="payAmountx" id="totalamount" value="%{debitTotalx}" cssClass="form-control" readonly="true"/>
					  
					  </div>
				</div>
		 <div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
			 <s:if test="Gp_id>0">
				  <div class="form-group">
				      <label for="exampleInputEmail1">Doctor List </label><br>
		                <%--  <s:property value="PractitionerName" /> --%>
		                 <s:select name="Doctorid" id="doctorid" list="userList" cssClass="form-control chosen-select" listKey="id" listValue="diaryUser" headerKey="0" headerValue="Select Doctor"/>
		                 <!-- for ayushman outside doctor Investigation list  -->
		            </div>			
			  </s:if>
				<s:else>
					 <div class="form-group">
			            <label for="exampleInputEmail1">Doctor List</label>
			                <s:select name="doctorid" id="doctorid" list="userList" cssClass="form-control chosen-select" listKey="id" listValue="diaryUser" headerKey="0" headerValue="Select Doctor"/>
		  	         </div>			
				</s:else>	
				
		  <%--  <div class="form-group">
			    <label for="exampleInputEmail1">Doctor List</label>
			    <s:select name="doctorid" id="doctorid" list="userList" cssClass="form-control chosen-select" listKey="id" listValue="diaryUser" headerKey="0" headerValue="Select Doctor"/>
		  	 </div> --%>
		  	 
			</div>
			
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group hidden" id="chdiv" >
		              <s:textfield id="chqno" name="chequeno" cssClass="form-control" placeholder="enter cheque number"></s:textfield>
		       		    <s:textfield id="bankname" name="bankname" cssClass="form-control" placeholder="enter cheque bank name"></s:textfield>	
		     </div>
			</div>
			
		</div>
	</div>
	
	
	<div class="">
		
				<%if(loginfo.isShowdiscount()){ %>
				<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="background-color:rgba(245, 245, 245, 0.69);padding: 10px 0px 0px 0px;">
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Discount Type </label>
					    <s:select  list="#{'0':'Percent','1':'Rupees'}" theme="simple" name="disctype" id="disctype" cssClass="form-control" />
					   	
					  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Amount</label>
					    <s:textfield name="discount" id="discount" cssClass="form-control" onchange="setAmountDue()" value="0" cssStyle="color: green;font-weight: bold;" />
					  </div>
				</div>
				</div>
				<%}else{ %>
				<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12 hidden" style="background-color:rgba(245, 245, 245, 0.69);padding: 10px 0px 0px 0px;">
					<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Discount Type </label>
					    <s:select  list="#{'0':'Percent','1':'Rupees'}" theme="simple"  name="disctype" id="disctype" cssClass="form-control" />
					   	
					  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Amount</label>
					    <s:textfield name="discount" id="discount" cssClass="form-control"  onchange="setAmountDue()" value="0" cssStyle="color: green;font-weight: bold;" />
					  </div>
				</div>
				</div>
				<%} %>
		
	</div>
	
	<div class="">
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
				    <label for="exampleInputEmail1">Payment Received <%=Constants.getCurrency(loginfo) %></label>
				   		<s:if test="creditDebitCharge==1">
							<s:textfield name="amount" id="payAmount" value="%{debitTotalx}"
							cssClass="form-control" readonly="true" />
						</s:if>
						<s:else>
								<s:textfield name="amount" id="payAmount" value="%{debitTotalx}"
							cssClass="form-control" onchange="checkNumber('payAmount')"/>
						</s:else>
				  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1">Credit Balance <%=Constants.getCurrency(loginfo) %></label>
					   		<s:textfield name="crdAmount" id="crdAmount" cssClass="form-control" placeholder="0.00" onchange="checkNumber('crdAmount')"/>
					  </div>
				
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group hidden">
					    	<label for="exampleInputEmail1">Transaction ID</label>
					   		<s:textfield name="transactionid" id="transactionid" cssClass="form-control"  />
					  </div>
				
				</div>
		</div>
	</div>
	
	<div class="">
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="padding: 5px 0px 0px 0px;">
				<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12" style="padding-right: 0px;">
					<div class="form-group">
					    <label for="exampleInputEmail1">Payment Note</label>
					   		<s:textarea name="paymentNote" id="paymentNote" rows="2" style="width: 100%;" cssClass="form-control"/>
					  </div>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12" style="padding-right: 0px;">
					<div class="form-group">
					    <label for="exampleInputEmail1">Invoice Note</label>
					   		<s:textarea  name="submitInvoiceNotes" id="submitInvoiceNotes" rows="2" cssStyle="width: 100%;" cssClass="form-control"></s:textarea>
					  </div>
				</div>
				
		</div>
	</div>
	
	<div class="">
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
		  <input type="hidden" id="adv" value="<s:property value="balanceAmount"/>">
		  <input type="hidden" id="debt" value="<s:property value="debitTotal" />">
			<input type="button" class="btn btn-primary pull-right" value="Record Payment" onclick="saveCashDesk()">
		</div>
	</div>
	
		  
		<% String clinicid=loginfo.getClinicid1(); %>
	<input  type="hidden" id="clinicid" value="<%=clinicid%>">  
		  
			
	</div>

</div>

                                    </div>
                                    <!-- tab in tabs -->
                                    <div role="tabpanel" class="tab-pane" id="notes">

                                    </div>
                                    <!-- tab in tabs -->
                                </div>
                            </div>
	
	
	
	
	
	<div class="col-lg-6 col-md-6 col-sm-6 hidden">
	<div class="row">
		<div class="col-lg-4 col-md-4 col-xs-5 col-sm-2">
			
			
			
		<%-- 	
			<label>Payment Amount<%=Constants.getCurrency(loginfo) %></label>
			<s:textfield onchange="setAmountDueTotal(this.value);"
				name="payAmount" id="totalamount" value="%{debitTotalx}"
				cssClass="form-control" readonly="true"/>
			
			<s:if test="payby == 'Client'">	

				<label>Discount(if any)%</label>
				<s:textfield name="discount" id="discount" cssClass="form-control"
					onchange="setAmountDue()" value="0" />
	
				<label>Amount Due<%=Constants.getCurrency(loginfo) %></label>
				<s:if test="creditDebitCharge==1">
					<s:textfield name="amount" id="payAmount" value="%{debitTotalx}"
					cssClass="form-control" readonly="true" />
				</s:if>
				<s:else>
						<s:textfield name="amount" id="payAmount" value="%{debitTotalx}"
					cssClass="form-control" />
				</s:else>
		
			</s:if>	
			<s:else>
				
				<s:textfield name="discount" id="discount" cssClass="form-control"
					onchange="setAmountDue()" value="0" cssStyle="display:none;" />
				<label>Amount Due<%=Constants.getCurrency(loginfo) %></label>
				<s:textfield name="amount" id="payAmount" value="%{debitTotalx}"
					cssClass="form-control" />
			</s:else> --%>

		</div>
	</div>
	
	<br>
	
		
	<!--<s:else>
	
		<div class="row">
			<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
				<s:textfield name="discount" id="discount" cssClass="form-control"
					onchange="setAmountDue()" value="0" cssStyle="display:none;" />
				<label>Payment Received <%=Constants.getCurrency(loginfo) %></label>
			</div>
			<div class="col-lg-1 col-md-1 col-xs-2 col-sm-1">	
			</div>
			<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">
				<s:textfield name="amount" id="payAmount" value="%{debitTotalx}"
					cssClass="form-control" />
			</div>
		</div>
		<br>
	</s:else>
	-->
	
		
		
	</div>
	
	</div>
	
	
	
	<div class="hidden">
	<div class="">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div class="form-group">
			    <label for="exampleInputEmail1">Payment Type</label>
			    <input type="email" class="form-control" id="exampleInputEmail1">
			  </div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div class="form-group">
			    <label for="exampleInputEmail1">Balance to Pay Rs.</label>
			    <input type="email" class="form-control" id="exampleInputEmail1">
			  </div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div class="form-group">
			    <label for="exampleInputEmail1">Discount(if any)%</label>
			    <input type="email" class="form-control" id="exampleInputEmail1">
			  </div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div class="form-group">
			    <label for="exampleInputEmail1">Payment Received Rs.</label>
			    <input type="email" class="form-control" id="exampleInputEmail1">
			  </div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<div class="form-group">
			    <label for="exampleInputEmail1">Credit Balance Rs.</label>
			    <input type="email" class="form-control" id="exampleInputEmail1">
			  </div>
		</div>
	
	</div>
	
	
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bhoechie-tab-container">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 bhoechie-tab-menu">
              <div class="list-group">
                <a href="#" class="list-group-item active text-center">
                  <h4 class="glyphicon glyphicon-plane"></h4><br/>Cash
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-road"></h4><br/>Cheque
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-home"></h4><br/>NEFT
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-cutlery"></h4><br/>Credit Balance Rs.00
                </a>
                
                
              </div>
            </div>
            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 bhoechie-tab">
                <!-- flight section -->
                <div class="bhoechie-tab-content active">
                    <center>
                      <h1 class="glyphicon glyphicon-plane" style="font-size:14em;color:#55518a"></h1>
                      <h2 style="margin-top: 0;color:#55518a">Cooming Soon</h2>
                      <h3 style="margin-top: 0;color:#55518a">Flight Reservation</h3>
                    </center>
                </div>
                <!-- train section -->
                <div class="bhoechie-tab-content">
                    <center>
                      <h1 class="glyphicon glyphicon-road" style="font-size:12em;color:#55518a"></h1>
                      <h2 style="margin-top: 0;color:#55518a">Cooming Soon</h2>
                      <h3 style="margin-top: 0;color:#55518a">Train Reservation</h3>
                    </center>
                </div>
    
                <!-- hotel search -->
                <div class="bhoechie-tab-content">
                    <center>
                      <h1 class="glyphicon glyphicon-home" style="font-size:12em;color:#55518a"></h1>
                      <h2 style="margin-top: 0;color:#55518a">Cooming Soon</h2>
                      <h3 style="margin-top: 0;color:#55518a">Hotel Directory</h3>
                    </center>
                </div>
                <div class="bhoechie-tab-content">
                    <center>
                      <h1 class="glyphicon glyphicon-cutlery" style="font-size:12em;color:#55518a"></h1>
                      <h2 style="margin-top: 0;color:#55518a">Cooming Soon</h2>
                      <h3 style="margin-top: 0;color:#55518a">Restaurant Diirectory</h3>
                    </center>
                </div>
                
            </div>
        </div>
  </div>
</div>
	
	
	
	<s:token />
</s:form>


<!-- Modal Email-->
<div class="modal fade" id="creditnotepopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Credit Ammount</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				
					</div>
						<div class="form-group">
							<label>The paid amount (currency to be added to <span id="crdpaidamt"></span>)  is greater than balaence (Currency <span id="crdbalamt"></span>) amount. The remaining amount (Currency <span id="crdremainamt"></span>) will store to clients credit account</label>
							
						</div>
						
						
						<div class="form-group">
							<label>Credit Note:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="10" cols="60"
								title="Enter Body" name="creditnote"  id="creditnote" ></textarea>
						</div>
						
						
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveCreditAmt();">OK</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<div id="finalconfirmpopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Please Confirm Selected Information </h4>
      </div>
      <div class="modal-body">
    	<span>  Payment Mode:<h4 id="finalconfirmPaymode"></h4></span>
    	<span> 	Invoice Type:<h4 id="finalconfirmInvtype"></h4> </span>
    	<span> 	Amount:<h4 id="finalconfirmAmount"></h4> </span>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-info " id="cnfrmid" onclick="submitFinalConfirm()" data-dismiss="modal" value="Ok">
         <input type="button" class="btn btn-success  close" onclick=""  style="font-size: inherit;"  data-dismiss="modal" value="Cancel">
      </div>
    </div>

  </div>
</div>



<div id="refmodeppup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Please Confirm The Payment Mode </h4>
      </div>
      <div class="modal-body">
      <label>Select Payment Mode For Refund</label>
    	   <select name="refhowpaid" id="refhowpaid" class="form-control" ">	
				<option value="Cash">Cash</option>
				<option value="Cheque">Cheque</option>
				<option value="NEFT">NEFT</option>
				<option value="D/Card">Card</option>
				<option value="UPI">UPI</option>
			</select> 			
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-info " onclick="goforrefundprepay(0)" data-dismiss="modal" value="Ok">
         <input type="button" class="btn btn-success  close" onclick=""  style="font-size: inherit;"  data-dismiss="modal" value="Cancel">
      </div>
    </div>

  </div>
</div>




<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="diarymanagement/js/paynow.js"></script>

<script>
$(document).ready(function() {
    $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
        e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
        var index = $(this).index();
        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
    });
});

</script>

<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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
  
  
  <!-- Modal -->
<div id="morepaymentmodelpopupid" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add More Payment</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" name="selectedid" id="pro_id">
      		<div class="form-group" style="margin-bottom: 0px;">
	      		  <label for="exampleInputEmail1">Payment Mode</label>
		    <select name="morehowpaid" id="morehowpaid" class="form-control" ">
				<option value="0">Select</option>
				<option value="Cash">Cash</option>
				<option value="Cheque">Cheque</option>
				<option value="NEFT">NEFT</option>
				<!--<option value="BACS">BACS</option>-->
				<!-- <option value="C/Card">Credit Card</option> -->
				<option value="D/Card">Card</option>
				<option value="UPI">UPI</option>
				<option value="Other">Other</option>
				
			</select> 
      		</div>
      		
      		<div class="form-group" style="margin-bottom: 0px;">
	      		  <label for="exampleInputEmail1">Select Bank</label>
	      		    <s:select name="morepaybank" id="morepaybank" list="bankNameList"
		  cssClass="form-control" listKey="id" listValue="name"
		  headerKey="0" headerValue="Select Bank"
		  />
	      	</div>
      		<div class="form-group" style="margin-bottom: 0px;">
	      		  <label for="exampleInputEmail1">Payment Received <%=Constants.getCurrency(loginfo) %></label>
				   		<input type="number" name="moreamount" id="morepayAmount" 
					class="form-control" value="0"  readonly="readonly"/>
      		</div>
      </div>
      <div class="modal-footer">
         <input type="button" onclick="setmorepaymenttoform()" class="btn btn-danger" value="Ok">
      </div>
    </div>
  </div>
</div>

<!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);z-index: 99" id="newloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->
  <script>
  function checkNumber(val) {
	  var val1=document.getElementById(val).value;
	  var validNumber = new RegExp(/^\d*\.?\d*$/);
	  if(!validNumber.test(val1)) {
		    jAlert('error', "Please enter Numbers Only.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			document.getElementById(val).value="0.00";
		}
}
  
  </script>