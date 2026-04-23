<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<link rel="stylesheet" href="_assets/newtheme/css/mbile.css">
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "IPD Daily Account Report",
					filename: "ipddailyaccountReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

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


function setSorting(column,order){
	if(order=='asc'){
		order = 'desc';
	}else{
		order = 'asc';
	}
	document.getElementById('orderby').value = column;
	document.getElementById('order').value = order;
	document.getElementById('invoicerportfrm').submit();
}


</script>

<div class="">
							<div class="">
							
							
							 <div class=" letterheadhgt" style="height: 135px;">
  <div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
     <link href="common/css/printpreview.css" rel="stylesheet" />
   <%@ include file="/accounts/pages/letterhead.jsp" %>
   </div>
  </div>
 </div>
							
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

											<h4 class="colorwhitprint">IPD Daily Account Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<s:form action="ipdSummary" theme="simple" id = "invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
	
		<div class="form-inline">
			<div class="form-group">
				<s:select name="ipdopd" id="ipdopd" 
				list="#{'0':'All Patient Type','IPD':'IPD','OPD':'OPD'}"
				cssClass="form-control" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group">
				<s:select name="payby" id="payby" 
				list="#{'0':'All Payed by','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group" style="width: 10%;">
				<s:select name="diaryUser" style="width: 100%;" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div>
			<div class="form-group hidden" style="width: 10%;">
				<s:select id="location" name="location" listKey="id"
				listValue="location" headerKey="0" headerValue="All Referral Location"
				list="locationList" value="location" cssClass="form-control chosen-select" onchange="setActionForAll()" style="width: 100%;"></s:select>
			</div>
			<div class="form-group hidden" style="width: 10%;">
				<s:select cssClass="form-control showToolTip chosen-select" name="dischrgeOutcomes"
				id="dischrgeOutcomes" list="dischargeOutcomeList"
				listKey="id" listValue="name" headerKey="0"
				headerValue="All Outcomes" style="width: 100%;"/>
			</div>
			<div class="form-group hidden">
				<s:select cssClass="form-control showToolTip chosen-select" name="dischargeStatus"
				id="dischargeStatus" list="dischargeStatusList"
				listKey="id" listValue="name" headerKey="0"
				headerValue="All Discharge Status" />
			</div>
			<div class="form-group">
				<s:select name="discharge" id="discharge" 
					list="#{'All':'Discharged','0':'No','1':'Yes'}"
					cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<%-- <div class="form-group">
				<s:select name="presentipd" id="presentipd" 
					list="#{'1':'Admitted Patient','0':'All'}"
					cssClass="form-control chosen-select" ></s:select>
			</div> --%>
			
			<div class="form-group">
				<%-- <s:select name="presentipd" id="presentipd" 
					list="#{'1':'Admitted Patient','0':'All','2':'Discharged'}"  
					cssClass="form-control chosen-select" ></s:select> --%>
					
					<s:select name="presentipd" id="presentipd" 
					list="#{'2':'Discharged'}"  
					cssClass="form-control chosen-select" ></s:select>
			</div>
			
			
			<div class="form-group">
				<s:select name="balancelimit" id="balancelimit" 
					list="#{'0':'Select Balance','5000':'5000','10000':'10000','20000':'20000','50000':'50000','100000':'100000'}"
					cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			
			
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width: 7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
		</div>
	</div>
	
	</s:form>
	
	
	<table class="table table-striped table-responsive tableborderprint tablewidthipdreportsprint xlstable" >
				<thead>
      <tr>
    
     
      <th> 
     <span class="displaynonesrno">Sr.No</span>
     <span class="displaynoneweb colorwhitprint">#</span>
     </th>
      <th class="text-right tableborderprint">IPD No.</th>
     <th class="tableborderprint">UHID</th>
      <th class="tableborderprint">Payee</th>
     <th class="tableborderprint">Patient Details</th>
     <th class="tableborderprint">Address</th>
     
     <!-- <th class="hidden tableborderprint">Payed By</th> -->
     <th class="tableborderprint">Practitioner</th>
     <th class=" tableborderprint">Department</th>
     <th class=" tableborderprint hidden">Outcomes</th>
     <th class=" tableborderprint">Discharge Status</th>
     <th class="tableborderprint">Status</th>
     <th class=" tableborderprint">referred By</th>
     <% if(loginInfo.isAyushman()) {%>
			<th class="text-right tableborderprint hidden">Self Amount</th>
	<%}else{ %>
	<th class="text-right tableborderprint">Self Amount</th>
	<%} %>	
<!--      <th class="text-right tableborderprint">Self Amount</th>
 -->     
     <th class="text-right tableborderprint">Tp Amount</th>
     <th  class="text-right tableborderprint">Advance</th>
     <th class="text-left tableborderprint">Paid Amount</th>
      <th class="text-right tableborderprintt">Discount</th>
     <th class="text-right tableborderprintt">Self Balance</th>
     <th class="text-right tableborderprint">Self Credit</th>
     <th class="text-right tableborderprint">Bill Amount</th>
     <th class="text-right tableborderprint">Admn Date </th>
     <th class="text-right tableborderprint">Disch Date</th>
      
     </tr>
    </thead>
				
					<tbody >
					<s:if test="odReportList.size!=0">
					<%int i=0; %>
							<s:iterator value="odReportList" status="rowstatus">
								<tr>
								<td class="tableborderprint"><%=(++i) %></td>
								<td class="text-right tableborderprint"><s:property value="ipdseqno"/>  </td>
								 <td class="tableborderprint"><s:property value="abrivationid"/></td>
								 	<td class="tableborderprint">
								 		<s:if test="payby=='Client'">
											Self
										</s:if>
										<s:else>
											Third Party 	
										</s:else>
								 	<%-- <s:property value="payby"/> --%></td>
									<td class="tableborderprint">
									<s:property value="clientname"/> 
									<div class="clearfix "></div> 
									<s:property value="gender"/> 
								 
									 <s:property value="clientage"/> Yr 
                                	<div class="clearfix "></div> 
                                 <s:property value="wardbed"/>
                                 <div class="clearfix "></div> 
                                 <s:property value="mobno"/>
                                 <s:if test="emergencycontno==''">
                                 </s:if>
                                 <s:else>
                                  <div class="clearfix "></div> 
                                  
                                 <s:property value="emergencycontno"/>(E) </s:else> </td>
                                 <td class="tableborderprint"><s:property value="Address"/></td>
                                 
								
								<%-- <s:if test="payby=='Client'">
									<td class="hidden tableborderprint">Self (<s:property value="clientname"/>)</td>
								</s:if>
								<s:else>
									<td class="hidden tableborderprint">Third Party (<s:property value="tpName"/>)</td>
								</s:else> --%>
								
								<td class="tableborderprint"><s:property value="practitionerName"/> </td>
								<td class=" tableborderprint"><s:property value="location"/></td>
								
								<td class=" tableborderprint hidden"><s:property value="outcomes"/></td>
								<td class=" tableborderprint"><s:property value="dschargeStatus"/></td>
								
								<s:if test="discharge==0">
									<td class="tableborderprint">No <div class="clearfix"></div>  </td>
								</s:if>
								<s:else>
									<td class="tableborderprint">Yes   <div class="clearfix"></div>   <div class="clearfix"></div></td>
								</s:else>
							
								<td class="tableborderprint"><s:property value="refferedby"/></td>
								<% if(loginInfo.isAyushman()) {%>
			                      <td class="text-right tableborderprint hidden"><s:property value="selfcharge"/></td>
	                            <%}else{ %>
	                             <td class="text-right tableborderprint"><s:property value="selfcharge"/></td>
	                          <%} %>	
								<%-- <td class="text-right tableborderprint"><s:property value="selfcharge"/></td> --%>
								<td class="text-right tableborderprint"><s:property value="tpcharge"/></td>
								<td class="text-right tableborderprint"><s:property value="advance"/></td>
								<td class="text-righ tableborderprintt"><s:property value="cashreceived"/></td>
								<td class="text-righ tableborderprintt"><s:property value="totaldiscount"/></td>
								<td class="text-right tableborderprint" style="color: red;"><s:property value="selefbalance"/></td>
								<td class="text-right tableborderprint"><s:property value="selfcredit"/></td>
								<td class="text-right tableborderprint"><s:property value="Totalipdamount"/></td>
								<td class="text-right tableborderprint"><s:property value="admissiondate"/>- <s:property value="time"/> </td>
								<td class="text-right tableborderprint"><s:property value="dischargedate"/>  </td>
								
								</tr>
							</s:iterator>
						</s:if>
						
						<s:else>
					No Record Found!!
				</s:else>
				</tbody>
				
				
</table>




<%-- <table class="my-table xlstable" style="display:none;" >
				<thead>
					 
					<th class="tableborderprint ipddalyreportsthead ">
					<span class="displaynonesrno">Sr.No</span>
					<span class="displaynoneweb colorwhitprint">#</span>
					</th>
					<th class="tableborderprint">UHID</th>
					<th class="tableborderprint">Patient Details</th>
					<th class="hidden tableborderprint">Payed By</th>
					<th class="tableborderprint">Practitioner</th>
					<th class="hidden tableborderprint">Department</th>
					<th class="hidden tableborderprint">Outcomes</th>
					<th class="hidden tableborderprint">Discharge Status</th>
					<th class="tableborderprint">Status</th>
					<th class="hidden tableborderprint">referred By</th>
					<th class="text-right tableborderprint">Self Amount</th>
					<th class="text-right tableborderprint">Tp Amount</th>
					<th class="text-right tableborderprint">Advance</th>
					<th class="text-right tableborderprint">Invoiced</th>
					<th class="text-righ tableborderprintt">Self Balance</th>
					<th class="text-right tableborderprint">Self Credit</th>
				</thead>
				
					<tbody >
					<s:if test="odReportList.size!=0">
					<%int i=0; %>
							<s:iterator value="odReportList" status="rowstatus">
								<tr>
								<td class="tableborderprint"><%=(++i) %></td>
								 <td class="tableborderprint"><s:property value="abrivationid"/></td>
									<td class="tableborderprint">
									<s:property value="clientname"/> 
									<div class="clearfix "></div> 
									<s:property value="gender"/> 
								 
									 <s:property value="clientage"/> Yr 
                                	<div class="clearfix "></div> 
                                 <s:property value="wardbed"/></td>
								
								<s:if test="payby=='Client'">
									<td class="hidden tableborderprint">Self (<s:property value="clientname"/>)</td>
								</s:if>
								<s:else>
									<td class="hidden tableborderprint">Third Party (<s:property value="tpName"/>)</td>
								</s:else>
								
								<td class="tableborderprint"><s:property value="practitionerName"/> </td>
								<td class="hidden tableborderprint"><s:property value="location"/></td>
								
								<td class="hidden tableborderprint"><s:property value="outcomes"/></td>
								<td class="hidden tableborderprint"><s:property value="dschargeStatus"/></td>
								
								<s:if test="discharge==0">
									<td class="tableborderprint">No <div class="clearfix"></div>  <s:property value="admissiondate"/> <div class="clearfix"></div>  <s:property value="time"/></td>
								</s:if>
								<s:else>
									<td class="tableborderprint">Yes   <div class="clearfix"></div>  <s:property value="admissiondate"/>  <div class="clearfix"></div> <s:property value="time"/></td>
								</s:else>
							
								<td class="hidden tableborderprint"><s:property value="refferedby"/></td>
								<td class="text-right tableborderprint"><s:property value="selfcharge"/></td>
								<td class="text-right tableborderprint"><s:property value="tpcharge"/></td>
								<td class="text-right tableborderprint"><s:property value="advance"/></td>
								<td class="text-righ tableborderprintt"><s:property value="cashreceived"/></td>
								<td class="text-right tableborderprint" style="color: red;"><s:property value="selefbalance"/></td>
								<td class="text-right tableborderprint"><s:property value="selfcredit"/></td>
								
								</tr>
							</s:iterator>
						</s:if>
						
						<s:else>
					No Record Found!!
				</s:else>
				</tbody>
				
				
</table> --%>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>






						
	
	