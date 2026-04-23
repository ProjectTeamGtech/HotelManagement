<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<style type="text/css" media="all">
    /* fix rtl for demo */
    .chosen-rtl .chosen-drop { left: -9000px; }
  </style>
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
	
	
	function setActionForAll(){
		//document.getElementById('invoicerportfrm').submit();
	}
	
	


	
</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Total Patients Seen</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										

<s:form action="totalptsseenDailyReport" id="invoicerportfrm" theme="simple">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
	
		<div class="form-inline">
			<div class="form-group">
				<s:select name="payby" id="payby" 
				list="#{'0':'All Payed By','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group">
				<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div>
			<div class="form-group">
				<s:select id="location" name="location" listKey="id"
				listValue="location" headerKey="0" headerValue="All Location"
				list="locationList" value="location" cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group">
				<s:select id="thirdParty" name="thirdParty" listKey="id" onchange="setActionForAll()"
				listValue="thirdParty"  headerKey="0" headerValue="All TP"
				list="thirdPartyList" cssClass="form-control chosen-select"></s:select>
			</div>
			<div class="form-group">
				<s:select id="condition" name="condition" listKey="id" onchange="setActionForAll()"
				listValue="treatmentType"  headerKey="0" headerValue="All Speciality"
				list="treatmentTypeList" cssClass="form-control chosen-select"></s:select>
			</div>
			<div class="form-group">
				<s:select name="apmtType" id="apmtType" list="additionalChargesList" listKey="id" listValue="name" headerKey="0" headerValue="All Appointment Type" cssClass="form-control chosen-select" title="Select Appointment Type" theme ="simple" ></s:select>
				
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
			cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
			cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			</div>
			<div class="form-group" style="float: right;">
				<span style="color: green;font-size: 18px;">Total Patients : <s:property value="totalPts"/></span>
			</div>
		</div>
	<label id = "apmtTypeError" class="text-danger"></label>
</div>
</s:form>

	
	
	
	
	<table class="my-table" style="width: 100%">
				<tr>
					<!-- <th>Client</th> -->
					<th>Payed By</th>
					<th>Practitioner</th>
					<th>Location</th>
					<th>Condition</th>
					<th>Appointment Type</th>
					<th>Count</th>
					
				
				</tr>
				
				
				<s:if test="totalPtsList.size!=0">
							<s:iterator value="totalPtsList" status="rowstatus">
								<tr>
								<%-- <td><s:property value="clientname"/></td> --%>
								<s:if test="payby=='Client'">
									<td>Self (<s:property value="clientname"/>)</td>
								</s:if>
								<s:else>
									<td>Third Party (<s:property value="tpName"/>)</td>
								</s:else>
								
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="location"/></td>
								<td><s:property value="condition"/></td>
								<td><s:property value="apmttypetext"/></td>
								<td><s:property value="count"/></td>
									
									
								</tr>
							
							</s:iterator>
				</s:if>
				<s:else>
					No Record Found!!
				</s:else>
				
</table>
											

											
										
									</div>
								</div>
							</div>
						</div>


 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>



