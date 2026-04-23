<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.ThirdParties.eu.entity.OutstandingReport"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="thirdParties/js/allocation.js"></script>
	
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>


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



<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Allocation List </h4>
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

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


<s:form action="allocationOutstandingReport" id = "outstanding_report_frm" theme="simple">
<div class="col-lg-12 col-md-12 topback2">
		
			<div class="col-lg-2 col-md-2">
			<label>From Date</label>
			<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
	
		<div class="col-lg-2 col-md-2">
			<label>To Date:</label>
			<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		
		
	
		
		<div class="col-lg-1 col-md-1">
			<s:submit value="Go" theme="simple" cssClass="btn btn-primary" cssStyle="margin-top:21px;"></s:submit>
			
		</div>
		
		<div class="col-lg-2 col-md-2">
			<input type="button" onclick="showAllocationPopup()" value="Add Allocation"  class="btn btn-primary" style="margin-top:21px;"/>
			
		</div>
		
		
	
</div>
</s:form>	

					 
	<s:if test="allotList!=null">
		
		<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
						<th>Receipt No.</th>
						<th>Add</th>
						<!--<th>Edit</th>
						--><th>Date</th>
						<th>Third Party</th>
						<th class="text-right">Amount</th>
					</thead>
					
					<tbody>
					<s:if test="allotList.size>0">
						<s:iterator value="allotList">
							
								<tr id="<s:property value="id"/>">
									<td><s:property value="id"/></td>
									<td>
										<a href="showaltOutstandingReport?id=<s:property value="id"/>&tpid=<s:property value="thirdPartyId"/>&ra=<s:property value="allotamount"/>&action=ad">
											Add Allocation
										</a>
									</td>
									<!--<td>
										<a href="showaltOutstandingReport?id=<s:property value="id"/>&tpid=<s:property value="thirdPartyId"/>&ra=<s:property value="allotamount"/>&action=ed">
											Edit Allocation
										</a>
									</td>
									--><td><s:property value="date"/></td>
									<td><s:property value="thirdPartyName"/></td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="allotamount"/></td>
								
									
									
								</tr>
								
								       
										
									
									
								
							
								</s:iterator>
								 </s:if>
								 <s:else>	
								 	<h3 class="text-center"><i class="fa fa-times text-danger"></i> There is no Record found!!</h3>
								 </s:else>
								</tbody>
							
							</table>
		
						
					
		</s:if>
		

<!-- Dropdown Modal -->
<s:form action="savealtOutstandingReport" id="savaltfrm" theme="simple" >
<div class="modal fade modal-draggable" id="allocationpopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Search</h4>
      </div>
      <div class="modal-body">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 15px;margin-bottom: 0px;">
        	<div class="col-lg-col-lg-6 col-md-6 col-md-6">
        		<div class="form-group">
        	 <label for="email">Select Third Party</label>
        	 <s:select cssClass="form-control showToolTip chosen-select" 
								id="thirdParty" name="thirdParty" list="thirdPartyList" listKey="id"
								listValue="thirdParty" headerKey="0" theme="simple"
								headerValue="Select User" onchange="setClientsajax(this.value)"
								 />
        	</div>
        	</div>
        	<div class="col-lg-col-lg-3 col-md-3 col-md-3">
        		<div class="form-group">
        		  <label for="email">Enter Allocated Amount</label>
				 <div id="emrclientdataajaxdiv">
						<s:textfield onkeypress="return isNumberKey(event,this);" name="allotamount" id="allotamount" cssClass="form-control"/>
						
					</div>	
        	</div>
        	</div>
        	<div class="col-lg-col-lg-3 col-md-3 col-md-3">
        		<div class="form-group">
        		 <label for="email">Payment Type</label>
				    	<s:select onchange="myFunction(this.value)" name="paymentType" id="paymentType" list="#{'Cash':'Cash','Cheque':'Cheque'}" cssClass="form-control chosen-select"></s:select>
        		 
				 
        	</div>
        	</div>
        	<div class="col-lg-col-lg-3 col-md-3 col-md-3"></div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12" id="myDIV" style="display:none;" style="padding:0px;">
					<div class="col-lg-col-lg-3 col-md-3 col-md-3">
						<div class="form-group">
				    <label for="email">Cheque Type</label>
				    	<s:select name="cheqType" id="cheqType" 
										list="#{'Bearer Cheque':'Bearer Cheque','Order Cheque':'Order Cheque','Uncrossed / Open Cheque':'Uncrossed / Open Cheque','Crossed Cheque':'Crossed Cheque','Anti-Dated Cheque':'Anti-Dated Cheque','Post-Dated Cheque':'Post-Dated Cheque','Stale Cheque':'Stale Cheque'}"
										cssClass="form-control chosen-select" ></s:select>
				  </div>
					</div>
					<div class="col-lg-col-lg-3 col-md-3 col-md-3">
						<div class="form-group">
				    <label for="email">Cheque No</label>
				    	<s:textfield name="cheqNo" id="cheqNo" cssClass="form-control"/>
				  </div>
					</div>
					<div class="col-lg-col-lg-3 col-md-3 col-md-3">
						<div class="form-group">
				    <label for="email">Bank Name</label>
						<s:select name="bankname" id="bankname" list="banknamelist"
						listKey="name" listValue="name" cssClass="form-control chosen-select"
						headerKey="0" headerValue="Select Bank Name"
						/>
				  </div>
					</div>
					<div class="col-lg-col-lg-3 col-md-3 col-md-3">
						<div class="form-group">
				    <label for="email">Handover To</label>
				    	 <s:textfield name="handoverTo" id="handoverTo" cssClass="form-control"/>
				  </div>
					</div>
						<div >
        	
				  
				  
				  
        	
        </div>
					
					</div>
				
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
        <button onclick="savealtdata()" type="button" class="btn btn-primary ">Save changes</button>
      </div>
    </div>
  </div>
</div> 

</s:form>

<script>
	function myFunction(btn) {

	  var x = document.getElementById('myDIV');
	if(btn.value=="1" || btn.value==1)
	{
	 x.style.display = 'none';
	}
	else
	{
	 x.style.display = 'block';
	}
	
  
   
}
</script>
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
