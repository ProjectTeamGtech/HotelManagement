<%@taglib uri="/struts-tags" prefix="s"%>

<script>

$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange : yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange : yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true
	});
});


function submitChargeReport(){
	document.getElementById("frmid").submit();
}

</scriPt>


<div class="">
	<div class="">
		<div class="row details">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>District level OPD count Report</h4>

			</div>
		</div>
		
		<s:form action="districtChargesRpt" id="frmid" >
		<div class="row ">
			
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
					<div class="form-group" >
					<s:textfield readonly="true" name="fromDate" id="fromDate"
						cssClass="form-control" theme="simple" placeholder="from date"
						style="width:100%;"></s:textfield>
				</div>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				
				<div class="form-group" >
					<s:textfield readonly="true" name="toDate" id="toDate"
						cssClass="form-control" theme="simple" placeholder="to date"
						style="width:100%;"></s:textfield>
				</div>
				
				</div>
				
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"> 
									<a href="#" class="btn btn-primary"
										onclick="submitChargeReport()">Go</a>
										
								</div>
								
								
								
				
					
					
			
		</div>
	</s:form>
	
	
	<s:iterator value="distlist">
	
	<h3><s:property value="district"/></h3><br>
	<table class="my-table tablexls" id="example" style="width: 100%;">
		<tr>
		<th>Total OPD</th>
		<th>Total Completed
		<th>Total Invoiced</th>
		</tr>
		<tr>
			<td><s:property value="opdnt"/></td>
			<td><s:property value="completedcnt"/></td>
			<td><s:property value="invoicedcnt"/></td>
		</tr>
		
		
		
		</table>						
								
	</s:iterator>

							
						

								