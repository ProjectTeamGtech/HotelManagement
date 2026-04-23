<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>



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
	function printcancelInvoiceReportExcel() {

        $(".tablecancelinvoice").table2excel({
					exclude: ".noExl",
					name: "Daily Admission and Discharge",
					filename: "Bed_Occupancy_Report",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
</script>


								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Bed Occupancy Report</h4>

									</div>
								</div>
								<s:form action="bedoccupancyreportSummary" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
										<div class="form-inline">
										<div class="form-group" style="width:7%;">
												 <s:select cssClass="form-control" list="#{'01':'January', '02':'February', '03':'March', '04':'April', '05':'May','06':'June', '07':'July', '08':'August', '09':'September', '10':'October', '11':'November', '12':'December'}" name="fromDate" />
											</div> 
											<div class="form-group" style="width:7%;">
												 <s:select cssClass="form-control" list="#{'2014':'2014','2015':'2015','2016':'2016','2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020', '2021':'2021','2022':'2022', '2023':'2023', '2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028'}" name="toDate" />
											</div> 
											<%-- <div class="form-group" style="width:7%;">
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div> --%>
											<%-- <div class="form-group">
												<s:select list="doctorlist" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="name" headerKey="0" headerValue="Select Practitioner"></s:select>
											</div>
											<div class="form-group">
												<s:select list="clientlist" cssClass="form-control chosen-select" name="clientId" id="clientId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Client"></s:select>
											</div> --%>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right">
												
												<a href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
				<table class="my-table tablexls"  style="width: 100%;font-size: 8px"  >
							<thead>
							<tr>
								<th>Sr. no</th>
								<th>Date</th>
								<th>Occupied Bed</th>
								<th>Total Bed</th>
							</tr>
					</thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="bedoccupancylist">
					   		<tr>
					     		<td><%=++i %></td>
								<td><s:property value="date"/></td>
								<td><s:property value="totalpatient"/></td>
								<td><s:property value="totalbed"/></td>
								
					     	</tr>
					   </s:iterator>
					</tbody>
			 </table>
			 
		<%-- 	 <table  class="my-table"  style="width: 100%;font-size: 8px"  >
							<thead>
							<tr>
								<th>Sr. no</th>
								<th>Date</th>
								<th>Total Patient</th>
								<th>Total</th>
							</tr>
					</thead>
					<tbody>
						<%int k=0; %>
						<s:iterator value="newbedoccupancylist">
					   		<tr>
					     		<td><%=++k %></td>
								<td><s:property value="date"/></td>
								<td><s:property value="totalpatient"/></td>
								<td><s:property value="total"/></td>
							</tr>
					   </s:iterator>
					</tbody>
			 </table> --%>
		</div>

<table class="my-table tablecancelinvoice" id = "cancelinvoiceReportTable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
		    <tr>
								<th>Sr. no</th>
								<th>Date</th>
								<th>Total Patient</th>
								<th>Bed Occupancy</th>
								
						 		
							</tr>
		    </thead>
             <tbody>
             
            <%i=0; %>
						<s:iterator value="bedoccupancylist">
					   		<tr>
					     		<td><%=++i %></td>
								<td><s:property value="date"/></td>
								<td><s:property value="totalpatient"/></td>
								<td><s:property value="totalbed"/></td>
							
					     	</tr>
					   </s:iterator>
             
</table>

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



	