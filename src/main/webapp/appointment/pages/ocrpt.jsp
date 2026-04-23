<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@ taglib uri="/struts-tags" prefix="s"%>




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
                                	
                                <div class="row hidden">
                               	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
                               	 	<hr/>
                               	 </div>
                               	</div>		
                                 

<h3 class="viewledrcenter padingleftrightzeroi"><b>Opening / Closing Report</b></h3>
<div class="row">
		<div class="col-lg-12">


<s:form action="ocrptsAppointmentType" theme="simple" id="invoicerportfrm">
<div class="col-lg-2">
		<div class="form-group" >
			<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
		</div>
	</div>
<div class="col-lg-2">
		<div class="form-group" >
			<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
		</div>
</div>

<div class="col-lg-1">
				<input type="submit" value="Go"  class="btn-primary">
			</div>
	
</s:form>
</div>
</div>

 <div class="row">
 <div class="col-lg-12">
 
 <table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
					
					
		
					    	<th>Date</th>
					    	<th>Particulars</th>
							<th>Vch No.</th>
							<th>Debit</th>
							
							<th>Credit</th>
							
						
						</tr>
						</thead>
						
						
						<s:if test="ocreptData.size()>0">
							<tbody>
								<s:iterator value="ocreptData" status="rowstatus">
							<tr>
								<td><s:property value="commencing"/></td>
								<td><s:property value="ledgername"/></td>
								<td><s:property value="id"/></td>
								
								<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx"/></td>
								
								
							</tr>
						
						</s:iterator>
						
						<tr>
							<td></td>
							<td style="font-weight: bold;">Closing Balance</td>
							<td></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="debitx"/></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="creditx"/></td>
						</tr>

					
							</tbody>
							
							</s:if>
							<s:else>
								<tr>
									<td><h4>No Record Found!!!</h4></td>
								</tr>
							</s:else>
						
						</table>
						
 
 </div>
 
 
 </div>