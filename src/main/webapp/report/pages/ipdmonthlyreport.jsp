<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="report/js/report.js"></script>


<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>
 
	$(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		});
	});
	
	
</script>
<div id="printableArea">
								<div  >
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class=" letterheadhgt" style="border-bottom: 2px solid #6699cc;padding-top: 36px;padding-bottom: 15px;height: 135px;">
				<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
					 	<link href="common/css/printpreview.css" rel="stylesheet" />
						<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
				</div>
			</div>
			
			
							        	
	</div>
</div>
								<div class="row">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>IPD Monthly Occupancy Report (
										<s:property value="month"/>
										- <s:property value="toDate"/>
										
										)</h4>
										
										
										
										
										

									</div>
								</div>
								
								
								
								<s:form action="ipdmonthlyreportSummary" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2 hidden-print">
										<div class="form-inline">
											<%-- <div class="form-group" style="width:7%;">
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div> --%>
											<div class="form-group" style="width:7%;">
												 <s:select cssClass="form-control" list="#{'01':'January', '02':'February', '03':'March', '04':'April', '05':'May','06':'June', '07':'July', '08':'August', '09':'September', '10':'October', '11':'November', '12':'December'}" name="fromDate" />
											</div> 
											<div class="form-group" style="width:7%;">
												 <s:select cssClass="form-control" list="#{'2014':'2014','2015':'2015','2016':'2016','2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020', '2021':'2021','2022':'2022', '2023':'2023', '2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028'}" name="toDate" />
											</div> 
											<%-- <div class="form-group">
												<s:select list="doctorlist" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="name" headerKey="0" headerValue="Select Practitioner"></s:select>
											</div>
											<div class="form-group">
												<s:select list="clientlist" cssClass="form-control chosen-select" name="clientId" id="clientId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Client"></s:select>
											</div> --%>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
												<!-- <a href="#" class="btn btn-primary" onclick="submitmainform()">Go</a> -->
											</div>
											<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" >
				
				<table class="table table-no-border m-0"  style="width: 100%;font-size: 8px"  >
							<thead>
							<tr>
								<th style="width: 55%;"></th>
								<th style="width: 20%;"></th>
								<th style="width: 25%;"></th>
							</tr>
					</thead>
					<tbody>
					<%-- <s:if test="fromDate==12">
						<tr>
							<td><b>Bed Occupancy</b></td>
							<td>Bed Occupancy rate=(Inpatient Days of Care) / (Bed Days Available) x 100</td>
							<td>1899/3689 = 51.47%</td>
						</tr>
					</s:if>
					<s:elseif test="fromDate==01">
						<tr>
							<td><b>Bed Occupancy</b></td>
							<td>Bed Occupancy rate=(Inpatient Days of Care) / (Bed Days Available)  x 100</td>
							<td>1846/3689 = 50.04%</td>
						</tr>
					</s:elseif>
					<s:elseif test="fromDate==02">
						<tr>
							<td><b>Bed Occupancy</b></td>
							<td>Bed Occupancy rate=(Inpatient Days of Care) / (Bed Days Available)  x 100</td>
							<td>2103/3332 = 63.11%</td>
						</tr>
					</s:elseif> --%>
					
						
					
					
					<%-- <s:if test="fromDate==12">
						<tr>
							<td><b>Average Length of stay</b></td>
							<td>Average Length of stay :(TOTAL INPATIENT DAYS OF STAY IN HOSPITAL  / TOTAL ADMISSIONS) = AVERAGE LENGTH OF STAY (IN DAYS)</td>
							<td>1899/555 = 3.42 days</td>
						</tr>
					</s:if>
					<s:elseif test="fromDate==01">
						<tr>
							<td><b>Average Length of stay</b></td>
							<td>Average Length of stay :(TOTAL INPATIENT DAYS OF STAY IN HOSPITAL  / TOTAL ADMISSIONS) = AVERAGE LENGTH OF STAY (IN DAYS)</td>
							<td>1899/527 = 3.50 days</td>
						</tr>
					</s:elseif>
					<s:elseif test="fromDate==02">
						<tr>
							<td><b>Average Length of stay</b></td>
							<td>Average Length of stay :(TOTAL INPATIENT DAYS OF STAY IN HOSPITAL  / TOTAL ADMISSIONS) = AVERAGE LENGTH OF STAY (IN DAYS)</td>
							<td>2103/588 = 3.57 days</td>
						</tr>
					</s:elseif>
					<s:else> --%>
					<tr>
							<td><b>Bed Occupancy</b></td>
							<td>Bed Occupancy rate=(Inpatient Days of Care) / (Bed Days Available) x 100</td>
							<td><s:property value="totalpatient" />/<s:property value="totalbed" /> = <s:property value="totalbedoccupancy" />%</td>
						</tr>
						<tr>
							<td><b>Average Length of stay</b></td>
							<td>Average Length of stay :(TOTAL INPATIENT DAYS OF STAY IN HOSPITAL  / TOTAL ADMISSIONS) = AVERAGE LENGTH OF STAY (IN DAYS)</td>
							<td><s:property value="totalpatient" />/<s:property value="ipdnewadmission" /> = <s:property value="averagestay" /> days</td>
						</tr>
					<%-- </s:else> --%>
						
						<tr>
							<td style=""><b>Number of OPD patients</b></td>
							<td></td>
							<td><s:property value="totalopdseen" /></td>
						</tr>
						
						<tr>
							<td><b>Number of IPD patients</b></td>
							<td></td>
							<td><s:property value="ipdnewadmission" /></td>
						</tr>
						<tr>
							<td><b>Gross IPD revenues</b></td>
							<td></td>
							<td><s:property value="ipddebitcount" /></td>
						</tr>
						<tr>
							<td><b>Gross OPD revenues</b></td>
							<td></td>
							<td><s:property value="opddebitcount" /></td>
						</tr>
						<tr>
							<td><b>Gross Investigation revenues</b></td>
							<td></td>
							<td><s:property value="investigationdebitcount" /></td>
						</tr>
						<tr>
							<td><b>Other revenues</b></td>
							<td></td>
							<td><s:property value="medicinedebitcount" /></td>
						</tr>
						
						<tr>
							<td><b>IPD Collection</b></td>
							<td></td>
							<td><s:property value="netipddebitcount" /></td>
						</tr>
						<tr>
							<td><b>OPD Collection</b></td>
							<td></td>
							<td><s:property value="netopddebitcount" /></td>
						</tr>
						<tr>
							<td><b>Investigation Collection</b></td>
							<td></td>
							<td><s:property value="netinvestigationdebitcount" /></td>
						</tr>
						<s:if test="netmedicinedebitcount!=0">
							<tr>
								<td><b>Other Collection</b></td>
								<td></td>
								<td><s:property value="netmedicinedebitcount" /></td>
							</tr>
						</s:if>
						
						
						<tr>
						<td colspan="3"><b>Department/Specialty wise revenues (top ten specialization and others)</b></td>
						</tr>
						<s:iterator value="chargereportlist">
							<tr>
							<td><s:property value="department" /></td>
							<td></td>
							<td><s:property value="totalAmountx" /></td>
							</tr>
						</s:iterator>
						
						<tr>
						<td colspan="2"><b>Total No. of cases of surgeries conducted, with the break up of various types/classes of surgeries</b></td>
						<td><s:property value="totalprocedure" /></td>
						</tr>
						<s:iterator value="otprocedurechargelist">
							<tr>
							<td><s:property value="procedure" /></td>
							<td></td>
							<td><s:property value="count" /></td>
							</tr>
						</s:iterator>
						
					</tbody>
			 </table>
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

<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	    
	}
	
	
	</script>



	