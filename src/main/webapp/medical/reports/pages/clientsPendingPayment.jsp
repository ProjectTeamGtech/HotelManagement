<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>


<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script>

    function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Pending Payment Report",
					filename: "pendingpaymentReport",
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
	
	function setActionForAll(){
		//document.getElementById('pendingpaymntfrm').submit();
	}
	
	function showSort(order,columnname){
		
		document.getElementById('orderid').value = order;
		document.getElementById('orderField').value = columnname;
		
		document.getElementById('pendingpaymntfrm').submit();
		
	}
	
</script>
<script> 
function submitReport(){
	var fromdate = document.getElementById("fromDate").value;
	var todate = document.getElementById("toDate").value;
	if(fromdate==''){
		jAlert('error', "Please enter from date!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(todate==''){
		jAlert('error', "Please enter to date!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		//parts[1] + "/" + parts[0] + "/" + parts[2]
		var parts = fromdate.split("/");
		fromdate = parts[1] + "/" + parts[0] + "/" + parts[2];
		
		var parts1 = todate.split("/");
		todate = parts1[1] + "/" + parts1[0] + "/" + parts1[2];
		
		var date1 = new Date(fromdate);
		var date2 = new Date(todate);
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
		document.getElementById("pendingpaymntfrm").submit();
		/* if(diffDays>=31){
			jAlert('error', "Date differnce is greater one month!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			document.getElementById("pendingpaymntfrm").submit();
		} */
		
	}
	
}	
</script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Aged Debtors Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											<s:form action="PendingPaymentReports" id="pendingpaymntfrm" theme="simple">
<div class="col-lg-12 col-md-12 topback2 hidden-print">

<s:hidden name="order" id="orderid"/>
<s:hidden name="orderField" id="orderField"/>

<div class="col-lg-12 col-md-12 col-xs-12">
	<div class="form-inline">
		<div class="form-group">
			<s:select name="payby" id="payby" 
				list="#{'0':'All Paid By','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
		</div>
		<div class="form-group">
			<s:select id="location" name="location" listKey="id"
				listValue="location" headerKey="0" headerValue="Show All Referral Location"
				list="locationList" value="location" cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
		</div>
		<div class="form-group">
			<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
		</div>
		<div class="form-group">
			<s:select id="thirdParty" name="thirdParty" listKey="id" onchange="setActionForAll()"
				listValue="thirdParty"  headerKey="0" headerValue="Show All TP"
				list="thirdPartyList" cssClass="form-control showToolTip chosen-select"></s:select>
		</div>
		
		<div class="form-group" style="width:7%;">
			<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
		</div>
		<div class="form-group" style="width:7%;">
			<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
		</div>
		<div class="form-group">
			<!-- <input type="submit" value="Go" class="btn btn-primary"> -->
			<a href="#" class="btn btn-primary" onclick="submitReport()">Go</a>
		<a type="button" class="btn btn-primary" value="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
		<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
		</div>
	</div>
</div>


</div>



</s:form>



<s:form action="PendingPaymentReports" id="category_form" theme="simple">

	
<%-- 	<div class="row">
		<div class="col-lg-2 col-md-2">
			<label>Client:</label>
			<s:textfield name="client" id="client" size="25" readonly="true"
				cssClass="form-control" onclick="showPopUp()" data-toggle="modal"
				data-target="#clientSearch" />
			<s:hidden name="clientId" id="clientId"></s:hidden>
		</div>
	</div>
	<br /> --%>
 
 
 
 
	<div class="row">
		<div class="col-lg-12">
			<s:actionmessage cssClass="messageDiv" />
			<div>
				<table class="my-table xlstable" style="width: 100%">
					<thead>
						
						<tr>
							<th>Date</th>
							<th>Invoice No</th>
							<th>Patient Name <a href="#" onclick="showSort('<s:property value="order"/>','firstname');"><i class="fa fa-sort"></i></a></th>
							<th>Payed By <a href="#" onclick="showSort('<s:property value="order"/>','company_name');"><i class="fa fa-sort"></i></a></th>
							<th>Current(<30)</th>
							<th>30-60 Days</th>
							<th>60-100 Days</th>
							<th> > 100 Days</th>
							
						</tr>
						
					</thead>
					<tbody>
						<s:if test="pendingPaymentList.size!=0">
							<s:iterator value="pendingPaymentList" status="rowstatus">
								
								<tr>
									<td><s:property value="date"/></td>
									<td>0000<s:property value="invoiceId"/></td>
									<td><s:property value="clientName"/></td>
									<s:if test="payBy=='Client'">
										<td>Self (<s:property value="clientName"/>) <s:property value="postcode"/> / <s:property value="contactNo"/></td>
									</s:if>
									<s:else>
										<td>TP (<s:property value="name"/>) <s:property value="postcode"/> / <s:property value="contactNo"/></td>
									</s:else>
									
									<td><%=Constants.getCurrency(loginfo) %><s:property value="amountBeforeThirtyx"/></td>
									<td><%=Constants.getCurrency(loginfo) %><s:property value="amountAfterThirtyx"/></td>
									<td><%=Constants.getCurrency(loginfo) %><s:property value="amountAfterSixtyx"/></td>
									<td><%=Constants.getCurrency(loginfo) %><s:property value="amountAfterHundredx"/></td>
								</tr>
								
							</s:iterator>
							<tr>
								<td style="font-weight: bold;">Grand Total</td>
								<td></td>
								<td></td>
								<td></td>
								<td style="font-weight: bold;"><%=Constants.getCurrency(loginfo) %><s:property value="amountBeforeThirtyTotal"/></td>
								<td style="font-weight: bold;"><%=Constants.getCurrency(loginfo) %><s:property value="amountAfterThirtyTotal"/></td>
								<td style="font-weight: bold;"><%=Constants.getCurrency(loginfo) %><s:property value="amountAfterSixtyTotal"/></td>
								<td style="font-weight: bold;"><%=Constants.getCurrency(loginfo) %><s:property value="amountAfterHundredTotal"/></td>
							</tr>
						</s:if>
						<s:else>
						<h3 class="text-center"><i class="fa fa-times text-danger"></i> There is no Record found!!</h3>
					</s:else>
					</tbody>

				</table>
			</div>
		</div>
	</div>
</s:form>

											
										</div>
									</div>
								</div>
							</div>
						</div>

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




