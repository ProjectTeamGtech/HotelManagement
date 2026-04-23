<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<%-- <script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script> --%>
<%LoginInfo loginfo007 = LoginHelper.getLoginInfo(request);%>
<%String pcsAdmin="hidden";
if(loginfo007.isPcsAdmin()){ 
	pcsAdmin="";
}%>	

<script>
window.onload = function () {
	var today = new Date();
	var dd = today.getDate();

	var mm = today.getMonth()+1; 
	var yyyy = today.getFullYear();
	if(dd<10) 
	{
	    dd='0'+dd;
	} 

	if(mm<10) 
	{
	    mm='0'+mm;
	} 
	today = dd+'-'+mm+'-'+yyyy;
    document.getElementById('date').value = today;
    
	$("#date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
}


</script>

<div style="display: none;">

<div class="row">
	<div class="col-lg-12">
		<label> Additional Charges: </label>
		
		
	</div>
</div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Payee :
	</div>
	<div class="col-lg-8 col-md-8" id="">
		
	</div>
		 
</div>
<br>


<div class="row">
	<div class="col-lg-4 col-md-4">
	Charge For :
	</div><!--
	<div class="col-lg-8 col-md-8" id="additionalChargeAjax">
		<s:select name="chargeTYpe" id="chargeTYpe" list="additionalChargesList" listKey="id" listValue="name"
		headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen"
		onchange="setAdditionalChargeAjax1(this.value)">
			
		</s:select>
	</div>
		 
--></div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Cost :
	</div>
	<div class="col-lg-8 col-md-8" id="chargeajax"><!--
	<input type="text" id="charge" name="charge" 
					class="form-control" disabled="disabled"
					 />
--></div>
</div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Charge To :
	</div>
	<div class="col-lg-8 col-md-8" id = "chargeTo2">
		
	</div>
		 
</div>

<br>
<div class="row">
	<div class="col-lg-4 col-md-4">
	Quantity :
	</div>
	<div class="col-lg-8 col-md-8" id = "chargeTo2">
		<!--<input style="width: 66px;text-align: center;" type="text" id="quantity" name="quantity" 
		maxlength="2" value="1" class="form-control">
	--></div>
		 
</div>

<br>

<div class="row">
	<!--<div class="col-lg-4 col-md-5">
		
	</div>
	
--></div>

<hr />


</br>

<div class="row">
	<div class="col-lg-4 col-md-4">Total Charges:</div>
	<div class="col-lg-8 col-md-8" id="chargeTotalajax">
		<input type="text" class="form-control showToolTip" id="chargeTotal"
			name="chargeTotal" value="0.00" disabled="disabled"
			 />
	</div>
</div>
</br>
<div class="row text-info">
	<div class="col-lg-4 col-md-4">Note :</div>
	<div class="col-lg-8 col-md-8">Click on Add Charge button to
		show charges and add new charge.</div>
</div>
<br />
<div class="row text-info">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="cashDesk22"
				class="table table-hover table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th>Id</th>
						<th>Appointment Type</th>
						<th>Charge</th>
						<th>Delete</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
</br>

</div>
<s:if test="newbalnace<=0">
<div class="row">
</s:if>
<s:else>
<div class="row">
</s:else>
<div class="col-lg-12">
<label>Add Charges &nbsp;&nbsp; <small>( Account Note:-)</small></label><br>
<div>

		<label>Date:</label><input id="date" name="date" placeholder="Select Date" class="form-control dato" autocomplete="off" style="width: 12%">
</div>
<table class="table table-bordered" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th class="padby">Paid By</th>
												<th class="chartype">Charge Type</th>
												<th>Charge Name</th>
												<th>Qty</th>
												<th style="width: 7%; ">Unit Price</th>
												<th class="amount">Amount</th>
											
												<th class='<%=pcsAdmin%>'>Tax Amount</th>
										
											</tr>
										</thead>


										<tbody>
												<tr>

													<td>
														<!--<input type="radio" id="payBuy" name="payBuy" value="0" checked="checked" style="display: inline; float: left;padding: 3px;"> <label for="payBuy" style="display: inline; float: left;padding: 3px;">Self</label> 
														<input type="radio" id="payBuy1" name="payBuy" value="1" style="display: inline; float: left;padding: 3px;"><label for="payBuy" style="display: inline; float: left;padding: 3px;">Third Party</label>
													-->
														<div id="paybydiv"></div>
													</td>
													<td>
														<div >
														<s:select theme="simple" name="masterchargetype" id="masterchargetype" list="masterChageTypeList" listKey="name" listValue="name"
															headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen-select"
															onchange="filterCharges(this.value)"/>
														</div>
														<s:hidden id="isindisharecharge" value="0"></s:hidden>
														<div id="visitingconsltantdiv">
															<s:hidden id="consultantdr" value="0"></s:hidden>
														</div>
													</td>
													<td>
														<div id="additionalChargeAjax" style="margin-bottom: 2px;">
														<s:select theme="simple" name="chargeTYpe" id="addChargeType" list="additionalChargesList" listKey="id" listValue="name"
															headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen"
															onchange="setAdditionalChargeAjax(this.value)"/>
														</div>
														
														<input type="text" onchange="calcmanualcharge()"  name="mannualcharge"  id="mannualcharge"
														 class="form-control" placeholder="Manual Charge">
													</td>
													<td><input onchange="calcamount()" style="width: 66px;text-align: center;" type="text" id="quantity" name="quantity" 
														maxlength="3" value="1" class="form-control"></td>
													<td>
													<div  id="chargeajax">
														<input onchange="calcamount()" type="text" id="charge" name="charge" class="form-control" />
													</div>
													
													</td>
													<td id="amount"></td>
													<td class='<%=pcsAdmin%>' id='txamt'></td>
												</tr>
											
												
											
										</tbody>
									</table>
									<div class="row">
									<div class="col-lg-7 col-md-7">
									<textarea class="form-control" name='chargeNote1' id='chargeNote1' rows="5" onchange="alerte(this.value)"></textarea>
									</div>
									<div class="col-lg-3 col-md-3">
									<input type="hidden" id='taxtypes'>
									<s:iterator value="taxlist">
									<p><input type="checkbox" class="lokclass" value="<s:property value='percent'/>" onclick="calcamount()" id='<s:property value="type"/>'>  <label><s:property value='name'/></label> [<s:property value='percent'/> %]</p>
									</s:iterator>
									</div>
									<div class="col-lg-2 col-md-2">
										<input id="addchargebtn" type="button" value="Add Charge"
													onclick="setChargeAmount()" disabled="disabled"
													class="btn btn-primary top5">
									</div>
	
</div>
</div>
</div>



<div class="row">

<div class="col-lg-12">
<div class="col-lg-1 hidden" style="margin-top: 0">
<label>Discount<s:select name="discforall" id="discforall" list="#{'0':'%','1':'Rs'}" cssClass="form-control" onchange="discval()"/></label>
</div>
<div class="col-lg-2 hidden"style="padding-top: 17px">
<input onchange="discmodifychargepopup(this.value)" type="number" placeholder="Enter discount for all" class="form-control" name="allchargedisc" id="allchargedisc">
</div><br><br><br>
<label>View All Charges </label>
<%LoginInfo l = LoginHelper.getLoginInfo(request);%>
<div style="overflow: scroll; height: 300px;">
<table class="table table-bordered" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>Date</th>
												<th class="padby" style="width: 8%">Paid By</th>
												<th class="chartype" style="width: 16%">Charge Type</th>
												<th>Charge Name</th>
												<th>Code</th>
												<th class="text-center" style="width: 13%;">Qty</th>
												<th class="text-right">Unit Price</th>
												<th class="text-right">%</th>
												<th class="text-center" style="width: 16%">Discount <%Constants.getCurrency(l); %></th>
												<th class="amount text-right">Amount</th>
												<th style="width: 2%;"></th>

											</tr>
										</thead>


										<tbody id="cashDesk" >
												<!-- <tr>
													<td>Self</td>
													<td>Bed Charges</td>
													<td>ICU Bed</td>
													<td class="text-center">2</td>
													<td class="text-right">Rs.2500</td>
													<td class="text-right">Rs.5000</td>
													<td><a href=""><i class="fa fa-times text-danger"></i></a></td>
												</tr>
												<tr>
													<td>TP</td>
													<td>Medicine</td>
													<td>Insulin</td>
													<td class="text-center">1</td>
													<td class="text-right">Rs.999</td>
													<td class="text-right">Rs.999</td>
													<td><a href=""><i class="fa fa-times text-danger"></i></a></td>
												</tr>
												<tr style="background-color: #efefef;">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td class="text-right"><b>Total:</b></td>
													<td class="text-right"><b>Rs.5999</b></td>
													<td></td>
												</tr>
											 -->
												
											
										</tbody>
									</table>
									</div>
									
</div>
</div>
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
  <script>
  $(function() {
	  $('.fixedscrolldiv').slimScroll({
	   		height : '50px',
	   		railVisible: true,
			alwaysVisible: true
	  });
	});
  
  
  
 function alerte(val){
	 /* var dataObj= {
				"ipdid":""+0+"", 
				"val":""+0+"", 
				"column":""+val+"", 
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "savedischargefieldsCommonnew",
		  data : data1,
		  dataType : 'json',
		  contentType : 'application/json',
		  type : 'POST',
		  async : true,
		  // beforeSend: function () { LockScreen(); },
		  success : function(data) {
			  
		  },
		  error : function(result) {
		   console.log("error");
		  }
		 }); */

 }
  </script>
  

