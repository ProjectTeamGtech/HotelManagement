 <%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>


 <style>
@media print{
	#table1{
	margin-top: -35px;
	}
	
	#ledgernm{
	margin-top: -30px;
	margin-bottom: 10px;
	}
}
</style>



<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

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

</script>


<div class="row details" style="margin-top: -35px !important;">
                                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
         <img src="dashboardicon/Financei.png" class="img-responsive prescripiconcircle">
          </div>
          <div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
          <h4>Vew Ledger Report</h4>
          
          
          </div>
                                    </div>
                                </div>
   <!-- Accounts Common Menu -->
                                <div class="row ">
                               	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose hidden">
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('ExpenceManagement')" type="button" class="btn btn-success" >Create Voucher</button>
                               			</a>
                               		</div>
                               		
                               		
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('ledgAppointmentType')" type="button" class="btn btn-success" >Create New Ledger</button>
                               			</a>
                               		</div>
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('viewledrportAppointmentType')" type="button" class="btn btn-success" >View Ledger Report</button>
                               			</a>
                               		</div>
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('aheadAppointmentType')" type="button" class="btn btn-success" >Add New Ledger Head</button>
                               			</a>
                               		</div>
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('ocrptsAppointmentType')" type="button" class="btn btn-success" >Opening / Closing Report</button>
                               			</a>
                               		</div>
                               		
                               		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" >
                               			<a href="#">
                               				 <button onclick="opencPopup('tbAppointmentType')" type="button" class="btn btn-success" >Trial Balance</button>
                               			</a>
                               		</div>
                               		
                               	</div>
                        

  <s:form action="viewledrportAppointmentType" theme="simple">       
 <div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>		

 
 

	

	
			
							<div class="print-visible hidden-md hidden-lg  letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
 
 		<div class="manascommheader hidden-print">
 		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
 		<h4>&#8377; &nbsp; Ledger Dashboard</h4>
 		</div>
 		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
 		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
 		</div>
 		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
 		<button onclick="printpage()"  type="button" class="btn btn-warning   hidden-print" ><i class="fa fa-print"></i> Print</button>
 		</div>  
 		
 	 	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
 	 		<select Class="form-control chosen-select" onchange="setsessionyear">
 	 		
 	 		<%
 	 		int minYear=2015,maxYear=2025;
 	 		for(int i=minYear;i<maxYear;i++){
 	 		%>
 	 		<option value="<%=i%>-<%=i+1%>"><%=i%>-<%=i+1%></option>
 	 		<% 	
 	 		}
 	 		%>
 	 		</select>
 	 	</div>
 		</div>
 	
 	</div>
 	
		<h3 class="viewledrcenter padingleftrightzeroi print-visible hidden-md hidden-lg" id="ledgernm"><b><s:property value="ledgerreportname"/></b></h3>


<div class="col-md-12 vewledrgobox padingleftrightzeroprint">
 

 
<div class="col-md-1 viewledrprintwidth padingleftrightzeroprint hidden-print">
  <p class="hidden"></p>
		<div class="form-group" >
	<label>From Date:</label>	
	  <s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control viewledrnoborder" theme="simple" placeholder="from date" ></s:textfield>
		</div>
	</div>
<div class="col-md-1 viewledrprintwidth hidden-print">
 <p class="hidden">To Date</p>
		<div class="form-group" >
		<label>To Date :</label>	
		 	<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control viewledrnoborder" theme="simple" placeholder="to date"></s:textfield>
		</div>
</div>



<div class="col-md-2 viewledrprintwidth hidden-print">
 <p class="hidden">Ledger Name</p>
 <label>Ledger Name :</label>	
						<s:select name="ledgername" id="ledgername" list="ledgerList" listKey="id" listValue="name"
			headerKey="0" headerValue="Select Ledger" cssClass="form-control chosen-select"
			
			/>
			</div>
			
			<div class="col-md-2 hidden-print hidden">
						<s:select name="servicename" id="servicename" list="ledgerserviceList" listKey="id" listValue="name"
			headerKey="0" headerValue="Select Service" cssClass="form-control chosen-select"
			 
			/>
			</div>
			
				<div class="col-md-2 hidden-print">
				<label>Accounts Type :</label>	
				<s:select name="actype" id="actype" list="#{'0':'Select Account Type','1':'Credit','2':'Debit'}" 
				cssClass="form-control chosen-select" ></s:select>
			</div>
 
<div class="col-md-2 hidden-print">
<label>Bank Name :</label>	
				  <s:select name="bnkname" id="bnkname" list="bankNameList"
		  cssClass="form-control" listKey="id" listValue="name"
		  headerKey="0" headerValue="Select Bank"
		  />	
			</div>
			
			
			
			<div class="col-md-1 hidden-print">
			<br>
				<button  type="submit" class="btn btn-primary btn-lg addvoucher vewledrgobtn hidden-print">Go</button>		
			</div>
			
			
</div>



 
		
				
				
				<span class="hidden-print"> &nbsp; </span> <div class="clearfix hidden-print" style="height:0px;">   </div>
		 
					
				
 <div class="clearfix" style="height:1px;">  </div>
				&nbsp; <div class="clearfix" style="height:0px;">   </div>
 
</s:form>

<br/>
 

<div id="table1">
<p class="print-visible hidden-md hidden-lg">From Date :<b><s:property value="fromDate"/></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To Date :<b><s:property value="toDate"/></b></p>


	<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr class="theadfinal">
					
					
		
					    	<th>ID</th>
							<th>Party Name</th>
							<!-- <th>Invoice Type</th> -->
							<th>Particular</th>
							<th>Commencing</th>
							<th>Payment Mode</th>
							
							
							<th>Credit</th>
							<th>Debit</th>
							<!-- <th>Balance</th> -->
						
						</tr>
						</thead>
					
							<tbody>
								<s:iterator value="ledgerreport" status="rowstatus">
							<tr>
								<td><s:property value="id"/></td>
								<td><s:property value="clientName"/></td>
								<%-- <td><s:property value="itype"/> (<s:property value="invoiceid"/>)</td> --%>
								<td><s:property value="ledgername"/></td>
								<td><s:property value="commencing"/></td>
								
								<td>
									<s:if test="ltype==2">
										Bank
									</s:if>
									<s:else>
										Cash
									</s:else>
								</td>
								
								<!-- Credit is debit and debit is credit -->
								
							
								<td><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx"/></td>
									<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx"/></td>
								<%-- <td><%=Constants.getCurrency(loginfo)%><s:property value="balanceTotalx"/></td> --%>
							</tr>
						
						</s:iterator>
						
						<tr>
							<td style="font-weight: bold;">Total</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							
							
							
							
							<td style="font-weight: bold;"><%=Constants.getCurrency(loginfo)%><s:property value="creditx"/></td>
							<td style="font-weight: bold;"><%=Constants.getCurrency(loginfo)%><s:property value="debitx"/></td>
							<td></td>
						</tr>

					
							</tbody>
							
						</table>
						</div>
					<br>
					<button onclick="printpage()"  type="button" class="btn btn-primary btn-lg addvoucher vewledrgobtn pull-right hidden-print"><i class="fa fa-print"></i> Print</button>	
					 <div class="row col-lg-12 col-md-12 col-xs-12 col-sm-12">
		            <div class="form-group text-right hidden-print">
                                    	<!--<a type="button" onclick="openPopup('checkavailabilityPharmacy?selectedid=<s:property value="id"/>')" class="btn btn-primary btn-lg">Check Availability</a>
                                    	--> 
                                    		
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
                                                     </div>

                              </div>        