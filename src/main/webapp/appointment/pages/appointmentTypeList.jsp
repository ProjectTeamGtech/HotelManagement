<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<%@page import="com.apm.main.common.constants.Constants"%>


<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>

function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Hospital Rate List",
					filename: "HospitalRateList",
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
										<s:if test="viewaccess==1">
										<h4>Hospital Rate List</h4>
										</s:if>
										<s:else>
										<h4>Create Charge</h4>
										</s:else>
										

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<s:if test="viewaccess!=1">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2 hidden-print">
	<div class="col-lg-3 col-md-2 col-xs-12 col-sm-12">
		<label>Select Master</label>
		<s:select list="masterlist" name="mastername"
		istKey="id" listValue="name" onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
	</div>
	<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12 hidden-print form-group">
		<br>
		<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
		<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
		<s:if test="viewaccess!=1">
			<a class="btn btn-primary " href="addAppointmentType">Create Self Charge</a>
		</s:if>	
		<s:if test="viewaccess!=1">
	 <!-- <a href="addchargesThirdParty" class="btn btn-primary">Create Payer Charges</a> --> 
 
 <a href="#" onclick="opencPopup('addchargesThirdParty')" class="btn btn-primary">Create Payer Charges</a>
 </s:if>	
		<input type="button" class="btn btn-primary" value="New ThirdParty Charges" onclick="openBlankPopup('newThirdpartyChargeThirdParty?action=0')"  >
		<%if(loginfo.isLmh()){ %>	
			<a href="payerCreationMaster?selectedid=84" class="btn btn-primary">Payer Creation</a>
		<%} %>
		
	</div>
	
	<!-- <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 form-group" >
		
	</div>
	<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12  form-group" style="width: 13%">
	</div>
	<div class="col-lg-3 col-md-2 col-xs-12 col-sm-12"></div> -->
</div>
</s:if>
<s:form action="AppointmentType" theme="simple" id="appointmenttypefrm">
	<div class="col-lg-12 col-md-12 topback2 hidden-print form-inline">
		<div class="col-lg-2 col-md-2 col-sm-2">
			<label>Show Payed by</label>
			<s:select name="payby" id="payby" 
			list="#{'0':'All','Client':'Self','Third Party':'Third Party'}"
			cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3">
			<label>Show TP</label>
			<s:select id="thirdParty" name="thirdParty" listKey="id" onchange="setActionForAll()"
				listValue="thirdParty"  headerKey="0" headerValue="All"
				list="thirdPartyList" cssClass="form-control showToolTip chosen-select"></s:select>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3">
			<label>&nbsp;</label>
			<s:select id="ward" name="ward" listKey="wardid"
			listValue="wardname" headerKey="0" headerValue="Ward list"
			list="wardlist"  cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-2">
			<div class="form-group">
				<label for="exampleInputEmail1">Charge Type</label>
 				<s:select list="newChargeTypeList" listKey="name" listValue="name" name="chargeType" id="chargeType" title="select Charge Type"
				cssClass="form-control chosen-select" onchange="getcharges(this.value)" headerKey="0" headerValue="Select Charge Type" > </s:select>  <!-- old listKey="id" -->
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12" >
			<input type="submit" value="Go" class="btn btn-primary" style="margin-top:3px;" />
			<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a> <a type="button" id="btnxls" title="Download Excel" onclick="printExcel()"
				class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
		</div>
	</div>
			<div class="col-lg-12 col-md-12 topback2 hidden-print form-inline">
			
					
			<%-- <div class="col-lg-2 col-md-2 form-group" style="padding: 0">
			<label>Search</label><br>
			<s:textfield theme="simple" name="searchText"
					placeholder="Search By Name"  cssClass="form-control" style="width:100%"/>
				
					
			</div> --%>
			
			<div class="col-lg-8 col-md-8 hidden-print form-group">
			</div>
			
		</div>
		
		<br>
		<s:hidden name="order" id="order"/>
    <s:hidden name="orderby" id="orderby"/>
		<s:hidden name="viewaccess"/>
		</s:form>
		<div class="col-lg-12 col-md-12 topback2 hidden-print form-inline">
		<input type="text" name="chargesearch" id="chargesearch" class="form-control" placeholder="Search " onkeyup="searchcreatecharge(this.value,<s:property value='viewaccess'/>)" style="width: 32%">
		</div>
			<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
<div class="row">
	<div class="col-lg-12">
	
		<input type="hidden" id="client" name="client">
		<div class="table-responsive">
			<table id="results" 
				class="table table-responsive  table-condensed table-bordered xlstable">
				<thead>
					<tr>
						<td>Payed By <a href="#" onclick="setSorting('tpid','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></td>
							<td>Ward Name</td>
							<td>Charge Type<a href="#" onclick="setSorting('chargeType','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></td>
						<td>Charge Name<a href="#" onclick="setSorting('name','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></td>
					
						<td class="text-center">Code<a href="#" onclick="setSorting('code','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></td>
						
						<td class="text-right">Charges<a href="#" onclick="setSorting('charges','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></td>
						<s:if test="viewaccess!=1">
						<td class="text-left">Duration</td>
						<th class="text-center hidden-print">Edit</th>
						<th class="text-center hidden-print">Delete</th>
						<th class="text-center hidden-print">Non Editable</th>
					</s:if>
					</tr>
				</thead>
				<tbody id=tblchrg>
					<s:if test="appointmentTypeList.size!=0">
						<s:iterator value="appointmentTypeList" status="rowstatus">
							<tr>
								
								<td><s:property value="tpName"/></td>
								<td><s:property value="wardname"/></td>
								<td><s:property value="chargeType" /></td>
								<td><s:property value="name" /></td>
								
								
								<td class="text-center"><s:property value="code" /></td>
								
								<td style="text-align: right;"><%=Constants.getCurrency(loginfo)%><s:property value="charges" /></td>
								<s:if test="viewaccess!=1">
								<td><s:property value="duration" /></td>
								
								</s:if>

								<s:hidden value="%{id}" name="id"></s:hidden>
								<s:url action="editAppointmentType" id="edit">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<s:if test="viewaccess!=1">
								<td class="text-center hidden-print">
									<s:if test="tpid==0">
										<s:a href="%{edit}">
											<i class="fa fa-edit"></i>
										</s:a>
									</s:if>
									
								</td>
								<s:url action="deleteAppointmentType" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center hidden-print">
									<s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
									</s:a>
								</td>
								<s:if test="noneditamt==1">
									<td class="text-center hidden-print">
										<input checked="checked" type="checkbox" class="case setchk" value="<s:property value="id"/>" 
										class="form-control" name="nonedit" id="nonedit" onchange="setnoneditable(<s:property value="id"/>,this.checked);"/>
									</td>
								</s:if>
								<s:else>
									<td class="text-center hidden-print">
										<input type="checkbox" class="case setchk" value="<s:property value="id"/>" 
										class="form-control" name="nonedit" id="nonedit" onchange="setnoneditable(<s:property value="id"/>,this.checked);"/>
									</td>
								</s:else>
								</s:if>
							</tr>

						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> No Appointment type
						found!!
					</h3>


				</s:else>

			</table>
		</div>
	</div>
</div>
<s:form action="AppointmentType" name="paginationForm"
	id="paginationForm" theme="simple">
	
	<%--  <s:hidden name="payby" --%> 
	<s:hidden name="searchText"/> 
	<s:hidden name="chargeType"/> 
	<s:hidden name="tpName"/> 
	<s:hidden name="name"/> 
	<s:hidden name="duration"/> 
	<s:hidden name="code"/> 
	<s:hidden name="charges"/> 
	<s:hidden name="viewaccess"/>
	<s:hidden name="payby"/>
	<s:hidden name="thirdParty"/>
	<s:hidden name="ward"/>
	
	
	<%-- <s:hidden name="thirdParty"/>  --%>
	<%-- <input type="hidden" name="payby" value="<s:property value="payby"/>">
	<input type="hidden" name="thirdParty" value="<s:property value="thirdParty"/>"> --%>
	
	<div class="col-lg-12 col-md-12 hidden-print" style="padding:0px;margin-top:15px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" />
			</label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
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
    
    
    
    function setSorting(column,order){
		if(order=='asc'){
			order = 'desc';
		}else{
			order = 'asc';
		}
		document.getElementById('orderby').value = column;
		document.getElementById('order').value = order;
		document.getElementById('appointmenttypefrm').submit();
	}
  </script>
	
			
			

	


