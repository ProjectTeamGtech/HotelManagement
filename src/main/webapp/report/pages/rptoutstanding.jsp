<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script>

    function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Report Outstanding Report List",
					filename: "outStandingReport",
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
	
	


	
</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Reports Outstanding</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="rptoutstandingDailyReport" id="invoicerportfrm" theme="simple">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" style="width:100%;" placeholder="form date"></s:textfield>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			 <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
		</div>
		
</div>

</s:form>

	
	
	
	<table class="my-table xlstable" style="width: 100%">
				<tr>
					<th>PT Name</th>
					<th>Appt Type</th>
					<th>Practitioner</th>
					<th>Company</th>
				
				</tr>
				
				
				<s:if test="rptouststatndingList.size!=0">
							<s:iterator value="rptouststatndingList" status="rowstatus">
								<tr>
									<td><s:property value="clientname"/></td>
									<td><s:property value="apptName"/></td>
									<td><s:property value="practitionerName"/></td>
									<td><s:property value="tpName"/></td>
								</tr>
							
							</s:iterator>
				</s:if>
</table>
											

											
										
									</div>
								</div>
							</div>
						</div>







