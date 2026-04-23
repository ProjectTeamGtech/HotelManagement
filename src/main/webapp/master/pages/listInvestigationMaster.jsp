<%@taglib uri="/struts-tags" prefix="s"%>

<style>
.innerTable{
width: 100%;
border: none !important;
}
.innerTable th{
background-color: gray !important;

font-weight: bold;
}

</style>

<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="assesmentForms/js/assessment.js"></script>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
 <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
 <script type="text/javascript" src="emr/js/addInvestigation.js"></script> 
<SCRIPT type="text/javascript">


function printStockReportExcel() {
    $(".tablestock").table2excel({
				exclude: ".noExl",
				name: "Investigation Type Report",
				filename: "invtypereport",
				fileext: ".xls",
				exclude_img: true,
				exclude_links: true,
				exclude_inputs: true
			});
}

</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Investigation Type</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="col-lg-12 col-md-12 topback2">
	<div class="col-lg-3 col-md-2">
			<s:select list="masterlist"
					cssClass="form-control showToolTip chosen-select" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)"></s:select>
		</div>
		<div class="col-lg-1 col-md-1">
			<a class="btn btn-primary" href="#" onclick="opencPopup('addInvestigationMaster')"><i
				class="fa fa-plus"></i> Add</a>
		</div>
		<div class="col-lg-3 col-md-2">
			<input type="button" value ="Set TP" onclick="openPopup('thirdpartysettingsInvestigation')" class="btn btn-danger">
			
		</div>
		
<form action="InvestigationMaster">
<div class='form-inline'>	
		<div class="col-lg-1 col-md-1 form-group">
			<s:select name="isdeleted" id="isdeleted" 
				list="#{'2':'All','0':'Active','1':'Deleted'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
		<div class="col-lg-3 col-md-2 form-group">
			<s:textfield theme="simple" name="searchText" placeholder="Search By Investigation Type"  cssClass="form-control" />
			
		</div>
		<div class="col-lg-1 col-md-1 form-group">
			<input type="submit" value="Go" class="btn btn-primary"/>
			<a type="button"  title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
		</div>
</div>		
</form>
	<!--<div class="col-lg-3 col-md-2">
	<label>Select Master</label>
	<s:select list="masterlist" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
	</div>
	<div class="col-lg-6 col-md-8">
		<a class="btn btn-primary" href="addInvestigationMaster" style="margin-top:21px;"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>
--><div class="row">
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

<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed tablestock">
				<thead>
					<tr>
					
						<th style="width: 2%" class="text-left">Sr No</th>
						<!-- <th class="text-left">Ward</th> -->
						<th style="width: 8%" class="text-left">Section</th>
						<th style="width: 15%" class="text-left">Investigation Type</th>
						<th style="width: 8%" class="text-left">Report Type</th>
						<th style="width: 8%" class="text-left hidden">Charge</th>
						<th style="width: 8%" class="text-left">Round Charge</th>
						
						<th style="width: 10%" class="hidden-print hidden">Ward Name</th>
						<th style="width: 15%" class="hidden-print">Alternative Name</th>
						<th style="width: 8%" class="hidden-print">Charge</th>
						<th style="width: 5%" class="hidden-print">Save</th>
						
						<th style="width: 2%" class="text-center hidden-print">Edit</th>
						<th style="width: 2%" class="text-center hidden-print">Delete</th>
					</tr>
				</thead>
				<tbody>
				<%int index=0; %>
					<s:if test="invsTypeList.size!=0">
						<s:iterator value="invsTypeList" status="element">
							<%index++; %>
							<tr id='<s:property value="id" />'  >
								
								<td><s:property value="id" /></td>
								<td><s:property value="sectionid" /></td>
								<td><a onclick="invstNameType(<s:property value='id' />)" data-toggle="collapse" data-target="#innerrow<s:property value='id' />"><s:property value="name" /><i class='fa fa-arrow-down'></i></a>
								&emsp;<a href="#" style="font-size: larger;color: black;" onclick="addInvest(<s:property value='id' />)">+</a></td>
								<td><s:property value="report_type" /></td>
								<td class="hidden"><s:property value="charge"/></td>
								<td><s:property value="roundcharge" /></td>
								
								<td class="hidden">
									<input type="hidden" value="<s:property value="name" />" id="invtypename<%=index %>"> 
									<select class="form-control chosen-select" id="wardid<%=index %>" >
										<option value="0">Select Ward</option>
										<s:iterator value="wardlist">
											<option value="<s:property value="id"/>"><s:property value="wardname"/></option>
										</s:iterator>
									</select>
								</td>
								<td><input type="text" id="alternativetext<%=index %>" value="<s:property value="altername"/>" class="form-control"></td>
								<td><input type="number" id="chargess<%=index %>" value="<s:property value="charge"/>" class="form-control"></td>
								<td><input type="button" value="Save" class="btn btn-info" onclick="updateorsavecharges(<s:property value="id" />,<%=index %>)"></td>
								
								<%-- <td>
									<s:select list="wardlist" listKey="id" listValue="wardname" id="wardid<%=index %>" title="Select Ward" headerKey="0" headerValue="Select Ward"
											cssClass="form-control chosen-select"   > </s:select>
								</td>
								<td></td>
								<td></td>
								<td></td> --%>
								
								<s:hidden value="%{id}" name="id"></s:hidden>
								
								 <s:if test='%{id==35}'> 
								 	 <td class="text-center hidden-print">Can't Edit</td>
									<td class="text-center hidden-print">Can't Delete</td>
								</s:if>
								 <s:elseif test='%{id==94}'> 
								 	 <td class="text-center hidden-print">Can't Edit</td>
									<td class="text-center hidden-print">Can't Delete</td>
								</s:elseif>
								
								 <s:elseif test='%{id==8}'> 
								 	 <td class="text-center hidden-print">Can't Edit</td>
									<td class="text-center hidden-print">Can't Delete</td>
								</s:elseif>
								
								 <s:elseif test='%{id==16}'> 
								 	 <td class="text-center hidden-print">Can't Edit</td>
									<td class="text-center hidden-print">Can't Delete</td>
								</s:elseif>
								
								<s:elseif test='%{id==50}'> 
								 	 <td class="text-center hidden-print">Can't Edit</td>
									<td class="text-center hidden-print">Can't Delete</td>
								</s:elseif>
								
								<s:elseif test='%{id==91}'> 
								 	 <td class="text-center hidden-print">Can't Edit</td>
									<td class="text-center hidden-print">Can't Delete</td>
								</s:elseif>
							
								<s:else>
									<s:url action="editInvestigationMaster" id="edit">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<!--<td class="text-center"><s:a href="%{edit}"
										class="text-warning">
										<i class="fa fa-edit"></i>
									</s:a></td>
								-->
								<td class="text-center hidden-print"><a href="#" onclick="opencPopup('editInvestigationMaster?selectedid=<s:property value="id" />')"><i class="fa fa-edit"></i></a></td>
								<s:url action="deleteInvestigationMaster" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<s:if test="isdeleted==1">
								
								<td class="text-center hidden-print">Deleted</td>
								</s:if>	
								<s:else>	
								<td class="text-center hidden-print"><s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
										
									</s:a></td>
									</s:else>
								</s:else>
							</tr>
							
							<tr id='innerrow<s:property value="id" />' class='noExl' class='collapse out ' >
							<td colspan="11" id='innertd<s:property value="id" />' ></td>
							</tr>
							
						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Investigation Type List found!!
					</s:else>
			</table>
		</div>
	</div>
</div>
<s:form action="InvestigationMaster" name="paginationForm" id="paginationForm" theme="simple">
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>

</s:form>
											

											
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<!-- Add Investigation -->

<div id="addinvst" class="modal fade" role="dialog" >

  <div class="modal-dialog modal-sm" style="width: 50%; height: 20%;">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="modal-title">ADD TASK</h4>
        </div>
  <div class="modal-body" >
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      	
      	      <s:hidden id="invstid" name="invstid"></s:hidden>
      	   
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group col-lg-6 col-md-6 col-xs-6 col-sm-6">
	      			<label><b>ThirdParty Name</b> </label>
	      	       <s:select  list="thirdPartyTypeNameList" name="tpname" id="tpname"  listKey="id" listValue="thirdparty" headerKey="0" headerValue="Select ThirdParty"  cssClass="form-control showToolTip chosen"/>

      			</div>
      			 <div class="form-group col-lg-6 col-md-6 col-xs-6 col-sm-6">
	      			<label><b>Ward Name </b></label>
	      			 <s:select  list="wardnamelist" name="ward" id="ward"  listKey="id" listValue="wardname" headerKey="0" headerValue="Select Ward Name"  cssClass="form-control showToolTip chosen"/>
      			</div>
      	  </div>
      	  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group col-lg-6 col-md-6 col-xs-6 col-sm-6">
	      			<label><b>Charge Name </b></label>
	      			<s:textfield  name="chargename" id="chargename" cssClass="form-control" theme="simple" style="width:100%;"></s:textfield> 
	      	    </div>
	     
	           <div class="form-group col-lg-6 col-md-6 col-xs-6 col-sm-6">
	      			<label><b>Code </b></label>
	      			<s:textfield  name="code" id="code" cssClass="form-control" theme="simple" style="width:100%;"></s:textfield> 
      			</div>
      	</div>
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	      	    <div class="form-group col-lg-6 col-md-6 col-xs-6 col-sm-6">
	      			<label><b>Amount</b></label>
	      			<s:textfield  name="amount" id="amount"
					   cssClass="form-control" theme="simple" style="width:100%;"></s:textfield> 
      			</div>
        </div>
      </div>
      	 
      	
      		
      		</div>
     </div>
     <div class="modal-footer">
 <%--  <%String invstid=(String)session.getAttribute("invstid"); %> --%>
     	<button type="button" class="btn btn-primary"
							onclick="addnewchargename()" style="margin-top: 60px;">Save</button>&emsp;
							<!-- <button type="button" class="btn btn-primary" id="updatebtn"
						name="updatebtn" 	onclick="" style="margin-top: 60px;">Update</button> -->
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
  <script type="text/javascript">
  	function updateorsavecharges(id,index) {

  		try {
  			var alternativetext = document.getElementById("alternativetext"+index).value;
			var wardid = document.getElementById("wardid"+index).value;
			var chargess = document.getElementById("chargess"+index).value;
			var invtypename = document.getElementById("invtypename"+index).value;
			if(chargess==''){
				jAlert('error', "Please enter charges!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			}else if(chargess==0){
				jAlert('error', "Please enter charges!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			}else if(chargess<0){
				jAlert('error', "Please enter valid charges!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			}else{
				var dataObj={
					  	"alternativetext":""+alternativetext+"",
						"wardid":""+wardid+"",
						"chargess":""+chargess+"",
						"id":""+id+"",
						"invtypename":""+invtypename+"",
				};
				var data1 =  JSON.stringify(dataObj);
				$.ajax({
				   url : "updateorsaveinvchargeBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   success : function(data) {
					   jAlert('success', 'Charge updated successfully.', 'Success Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					   window.location.reload();	  
				   },
				   error : function(result) {
					   jAlert('error', "Something wrong!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				   }
				}); 
			}
			
  		   
  		} catch (e) {
  			  window.location.reload();	      
  		}

	}
  </script>



