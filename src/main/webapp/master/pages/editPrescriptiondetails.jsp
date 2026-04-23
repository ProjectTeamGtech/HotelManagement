<%@taglib uri="/struts-tags" prefix="s"%>

<script src="master/js/prescriptiondetails.js"></script>
<script src="http://www.hinkhoj.com/common/js/keyboard.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<!-- <link rel="stylesheet" type="text/css"
href="http://www.hinkhoj.com/common/css/keyboard.css" /> -->

<div class="row details mainheader">


								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Prescription Details</h4>
								</div>
							</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="updatePrescriptiondetails" id="investigation_update_form" theme="simple">
<input type='hidden' name="specialization" id="specialization" />
<input type='hidden' name="location" id="location" />
<!-- <input type="hidden" name="hindiremrark" id="hindiremrark"> -->


<div class="row">
	<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "prescriptiondetailsTable" width="100%" >
								<thead>
									<tr>
										<th align="center" class="hidden">Prescription Category</th>
										<th align="center" >Generic Name</th>
										<th align="center">Medicine Name</th>	
										<th align="center">Component</th>		
										<!--<th align="center">Strength</th>	
										--><th align="center" class="hidden">MRP</th>	
										<th align="center" class="hidden">Purchase Price</th>	
										<th align="center" class="hidden">Sale Price</th>
										<!--<th align="center">Expiry Date</th>
										<th align="center">Package</th>
										<th align="center">Batch No</th>
										<th align="center">Mfg</th>
										   	--><th align="center" class="hidden">Location</th>
									   	<th align="center" class="hidden">Specialization</th>
									<th align="center">Risk</th>
									<th align="center">Dosage Note</th>
									<th><p>Prescription type</p></th>
									<th></th>
									<th>Is Weight Based</th>
										<th></th>
									</tr>
									 <s:hidden name="id"/>
								</thead>
								<tbody>
									<tr style="height:70px">
									   
										<td class="hidden"><s:select list="prescriptioncategorylist" listKey="id" listValue="name" name="categoryid" title="select prescription category"
										cssClass="form-control showToolTip" > </s:select>
										</td>									
										<td><s:textfield name="genericname" placeholder="enter generic name" id="genericname" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><s:textfield name="drug" placeholder="enter drug" id="name" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><s:textfield name="component" placeholder="enter component"  cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<!--<td><s:textfield name="strength" placeholder="enter strength" id="strength" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										--><td class="hidden"><s:textfield name="mrp" placeholder="enter mrp" id="mrp" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td class="hidden"><s:textfield name="purchase_price" placeholder="enter purchase price" id="purchase_price" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td class="hidden"><s:textfield name="sale_price" placeholder="enter sale price" id="sale_price" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<!--
 										<td><s:textfield name="expiry_date" placeholder="enter expiry date" id="expiry_date" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
 										<td><s:textfield name="pkg" placeholder="enter package " id="pkg" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
 										<td><s:textfield name="batch_no" placeholder="enter batch no" id="pkg" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
 										<td><s:textfield name="mfg" placeholder="enter mfg" id="mfg" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									   -->
									    <td class="hidden">
									        <div id=""> 
										             <input type="checkbox" onclick="selectAll('casem0',this)"/> Select All <br>
													<s:iterator value="listAllLocations"> 
												  			<s:if test="status==1">
												  			   <input type="checkbox" checked="checked" class="casem0" value="<s:property value="id"/>" /> <s:property value="name"/> <br>
												  			</s:if>
												  			<s:else>
												  				<input type="checkbox" class="casem0" value="<s:property value="id"/>" /> <s:property value="name"/> <br>
												  			</s:else>
												  </s:iterator>
												
										  	</div>
									    
									    </td> 
									    <td class="hidden" >
									    	<div id="scroll"> 
										        <input type="checkbox" onclick="selectAll('caser0',this)"/> Select All <br>
										        <s:iterator value="listSpecializations">
												  			<s:if test="status==1">
												  			<input type="checkbox" class="caser0" checked="checked" value="<s:property value="id"/>" /> <s:property value="treatmentName"/> <br>
												  			</s:if>
												  			<s:else>
												  			  <input type="checkbox" class="caser0" value="<s:property value="id"/>" /> <s:property value="treatmentName"/> <br>
												  			</s:else>
												</s:iterator>
										  	</div>
									    
									    </td>
									    <td>
										  <s:select list="#{'0':'Select Risk','1':'High','2':'Low'}" cssClass="form-control" id="" name="risk" ></s:select>
										</td>
										<td class=""><s:select list="dosagenoteList" listKey="id" listValue="name" name="dosagenote" title="select dosage note"
												cssClass="form-control showToolTip" > </s:select>
												
													
											
										</td>
										<td>	
											<s:select list="prisctypelist" listKey="name" listValue="name" name="prisctypename" title="select type" headerKey="" headerValue="All Types"
												cssClass="form-control  chosen-select" > </s:select>	</td>
												<td><td><s:if test="iswbd==1"><input type="checkbox" name="iswbd" checked="checked" value="1" class="form-control showToolTip filedname"></s:if>
								<s:else><input type="checkbox" name="iswbd" value="1" class="form-control showToolTip filedname"></s:else></td></td>
												<td></td>
												<td></td>
									</tr>
								</tbody>
								<!-- <tbody>
								<tr><td></td></tr>
								<tr><td></td></tr>
								
								</tbody> -->
								<thead >
								<tr>
								
								<th>Frequency (Farmula)</th>
								<th>Frequency</th>
								<th>Dose</th>
								<th >Strength</th>
								<th style="width:10%;">Qty</th>
								<th style="width:10%;">No. of Days</th>
								<th style="width:10%;">UOM</th>
								<th style="width:10%;">Remark</th>
								</tr>
								</thead>
								<tbody>
								<tr style="height:70px">
								
								
								<td>
									 <input type="number" name="frequency" value='<s:property value="frequency"/>'> 
									
									
								</td>
								<td>
									<s:select list="dosageList" id="prisccode" name="dosefreq" onchange="setdosegiventiming(this.value)" headerKey="0" headerValue="Frequency" listKey="name" 
									listValue="name" cssClass="form-control chosen-select"></s:select>
									
								</td>
								<td><input type="text" name="caldose" value='<s:property value="caldose"/>'></td>
								<td><input type="number" name="strength" value='<s:property value="strength"/>'></td>
								<td><input type="number" name="qty1" value='<s:property value="dr_qty"/>'></td>
								<td><input type="number" name="noofdays" value='<s:property value="days"/>'></td>
								
								<td>
									<%-- <input type="text" name="uom" value='<s:property value="uom"/>'> --%>
										<s:select name="uom" id="unitextension" list="priscUnitList" 
                  							listKey="name" listValue="name" headerKey="0" 
                  							headerValue="Select Type"  cssClass="form-control"></s:select>
								</td>
								
								<%String sessionrnote = (String)session.getAttribute("sessionrnote"); %>
								<td>
								<%-- <input type="text" name="remark" value='<%=sessionrnote%>'> --%>
								<div id="remlang"  style="width:100%;display:inline-flex;">
								<s:select name="remark" 
								list="nimairemarklist" listKey="id" 
								listValue="name" cssClass="form-control chosen-select" 
								headerKey="0" headerValue="All Remarks" theme="simple" 
								id="priscindivisualremark" ></s:select>&nbsp;<i class="fa fa-plus" style="width:10px;" onclick="openEmrPopup('addremarkMaster')"></i>&nbsp;<i class="fa fa-refresh" style="width:10px;" onclick="refreshremarks()"></i>
								</div>
								</td>
								
								</tr>
								<!-- <thead>
								<tr>
								
								<th>Remark (Hindi)</th>
								
								</tr>
								</thead>
								<tbody>
								<tr>
								<td>
							 <input type="text" id="transliterateTextarea"  name="hindiremrark">

								</td>
								</tr>
								</tbody> -->
								
							</table>
						</div>
						
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<button class="btn btn-primary" onclick="updateValidate()" >Update </button>
			<s:reset cssClass="btn btn-primary" />
			<a href="backPrescriptiondetails" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
  </div>
</div>	
</s:form>
<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script>
$(function () {
    $('#scroll').slimScroll({
        height: '200px'
    });
});

/* 
setInterval(function(){
var hin=document.getElementById("id1").value;


document.getElementById("hindiremrark").value=hin;
}, 30); */

// Load the Google Transliterate API
google.load("elements", "1", {
      packages: "transliteration"
    });

function onLoad() {
  var options = {
      sourceLanguage:
          google.elements.transliteration.LanguageCode.ENGLISH,
      destinationLanguage:
          [google.elements.transliteration.LanguageCode.HINDI],
      shortcutKey: 'ctrl+g',
      transliterationEnabled: true
  };

  // Create an instance on TransliterationControl with the required
  // options.
  var control =
      new google.elements.transliteration.TransliterationControl(options);

  // Enable transliteration in the textbox with id
  // 'transliterateTextarea'.
  control.makeTransliteratable(['transliterateTextarea']);
}
google.setOnLoadCallback(onLoad);
</script>
				
		