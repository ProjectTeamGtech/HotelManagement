<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

 <script>
 
 function showdebtorledgerreport(ledgerid){
		
		document.getElementById('hdnledgernameid').value = ledgerid;
		document.getElementById('ledgerfrm').submit();
	}
 
 </script> 


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
                               	
                                <div class="row ">
                               	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose hidden">
                               	 	<hr/>
                               	 </div>
                               	</div>		
 <div class="manascommheader row">   
 <img src="dashboardicon/Financei.png" class="img-responsive prescripiconcircle" style="margin: 0px;">                             
<h4 class=""><b>Trial Balance</b></h4>
</div>
<br>
<div class="row">
		<div class="col-lg-12">


<s:form action="tbAppointmentType" theme="simple" id="invoicerportfrm">
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
 
 <s:iterator value="debitaheadList">
 <s:if test="sdebtorlist.size()>0">
 <div style="font-weight: bold;font-size: 16px;"><s:property value="name"/></div><br>
 </s:if>
 <table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
						
						<col width="20%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						
						<s:if test="sdebtorlist.size()>0">
							<thead>
							<tr>
					
					
		
					    	<th>Particulars</th>
							
							<th>Debit</th>
							<th>Credit</th>
							
							<th>Debit Balance</th>
							<th>Credit Balance</th>
							
							
						
						</tr>
						</thead>
							<tbody>
								<s:iterator value="sdebtorlist" status="rowstatus">
							<tr>
								<td><a href="#" onclick="showdebtorledgerreport(<s:property value="id"/>)"><s:property value="ledgername"/></a></td>
								
								<!--  Credit is debit and debit is credit-->
								<td><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx"/></td>
								
								
								<td><%=Constants.getCurrency(loginfo)%><s:property value="cbalx"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="dbalx"/></td>
								
							</tr>
						
						</s:iterator>
						
						<tr>
							
							<td style="font-weight: bold;">Total</td>
							
							
							<td><%=Constants.getCurrency(loginfo)%><s:property value="creditx"/></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="debitx"/></td>
							
							
							<td><%=Constants.getCurrency(loginfo)%><s:property value="cbaltotalx"/></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="dbaltotalx"/></td>
						</tr>
						<tr>
							
							<td style="font-weight: bold;">Closing Balance</td>
							
							<td><%=Constants.getCurrency(loginfo)%><s:property value="cbalancex"/></td>
						</tr>

					
							</tbody>
							
							</s:if>
						
						</table>
						</s:iterator>
						
						 <table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 16px;font-weight: bold;">
						
						<col width="20%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						
						<tr style="font-size: 16px;">
							<td>Total</td>
							<td></td>
							<td></td>
							
							
							<td><%=Constants.getCurrency(loginfo)%><s:property value="cctotal"/></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="ddtotal"/></td>
						</tr>
						
						</table>
						<br/>
						
						
						 
						
						<s:form action="viewledrportAppointmentType" id="ledgerfrm">
						<s:hidden name="ledgername" id="hdnledgernameid"/>
						<s:hidden name="fromDate"/>
						<s:hidden name="toDate"/>
						
						
						
						<s:hidden name="servicename" value="0"/>
						<s:hidden name="actype"/>
						<s:hidden name="bnkname" value="0"/>
						
						</s:form>
						
 
 </div>
 
 
 </div>