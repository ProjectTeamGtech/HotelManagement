<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@page import="java.util.ArrayList"%>
<%@page import = "java.util.Date"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>		
<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="report/js/chargesReport.js"></script>

<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script>

    function printExcel() {

       $(".tablexls").table2excel({
					exclude: ".noExl",
					name: "Rota Duty List",
					filename: "ota Duty",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}
 
 
 
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
	
	function setSorting(column,order){
		if(order=='asc'){
			order = 'desc';
		}else{
			order = 'asc';
		}
		document.getElementById('orderby').value = column;
		document.getElementById('order').value = order;
		document.getElementById('invoicerportfrm').submit();
	}
	
	
</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Rota Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											<s:form action="rotaDiaryMangent" theme="simple" id="invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
	
	
		
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
		
		<div class="col-lg-2 col-md-2">
			<label>Job Title:</label>
			<s:select id="jobtitle" name="jobtitle" list="jobTitleList"
			headerKey="0" headerValue="Select Job Title"
			title="Select Job title" onchange="checkDoctorSelect()"
			cssClass="form-control showToolTip" data-toggle="tooltip" />
			
		</div>
		
		<div class="col-lg-2 col-md-2">
			<s:submit value="Go" theme="simple" cssClass="btn btn-primary" cssStyle="margin-top:21px;"></s:submit>
			<a type="button" class="btn btn-primary" style="margin-top:21px;" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" title="Save As PDF" onclick="return saveAsPdfChargesReport();" class="btn btn-primary" style="margin-top: 21px;"><i class="fa fa-file-pdf-o"></i></a>
		    <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls" style="margin-top: 21px;"><i class="fa fa-file-excel-o"></i></a>
		</div>
		
		
		<div class="col-lg-1 col-md-1" style="display: none" id = "previewChargeRpt">
		<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String temp[] = currentDate.split(" ");
		String temptime[] = currentDate.split("at");
		String temp1[] = temp[0].split("-");
		String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
		String time = temptime[1]; %>
			<!-- <a style="margin-top: 21px;" href="liveData/chargesReport/ChargeReport.pdf" class="btn btn-primary" target = "blank">Preview</a> -->
		</div>
		
		
		
		</div>
		
		<div class="row hidden-print">
			
			<div class="col-lg-1 col-md-1">
				
			</div>	
			
			<div class="col-lg-1 col-md-1">
				
			</div>
			
			<div class="col-lg-1 col-md-1">
				
			</div>
			
			<div class="col-lg-1 col-md-1" style="display: none" id = "sendMailChargeRpt">
				<input style="margin-top: 21px;" type="button" value="Send PDF in Mail" onclick="return openSendMailChargeRptPopup();" class="btn btn-primary">
			</div>
		
		</div>
		<br>		
		<s:if test="rotaCommencingList!=null">
		
						<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
									<!--<div align="center"><h4><u>Rota Duty Data</u></h4></div>
									<div align="center">26/06/2016 To 03/06/2016</div>
							--><thead>
							<tr>
					
					
						<th>Sr No</th>
						<th>Name</th>
						
						<s:iterator value="rotaCommencingList">
							<th><s:property value="commencing"/><br>
							<s:property value="weekFullName"/>
							</th>
						</s:iterator>
						</tr>
						</thead>
								<tbody>
								<s:iterator value="rotaList">
									<tr>
										<td></td>
										<td><s:property value="fullname"/></td>
										<s:iterator value="rotadataList">
											<td><s:property value="sTime"/> - <s:property value="endTime"/></td>
										</s:iterator>
									</tr>
								</s:iterator>
								
								</tbody>
							
							</table>
					
		</s:if>
		</s:form>
		

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




	