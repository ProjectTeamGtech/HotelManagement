<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Death Wise count Report List",
					filename: "deptwisecount",
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
							
							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
							
						
								<div class="row details">
								
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Department Wise Monthly Count Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="print-visible hidden-md hidden-lg">
	FromDate :01/<s:property value="month"/>/<s:property value="year"/>  &nbsp;&nbsp;  ToDate:  31/<s:property value="month"/>/<s:property value="year"/>
	</div>	
<s:form action="deptwiseavgcountSummary" theme="simple" id = "invoicerportfrm">

	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group">
						<%-- 	<div class="form-group">
				<s:select name="month" id="month" 
				list="#{'01':'January','02':'Feb','03':'March','04':'April','05':'May','06':'June','07':'July','08':'Augest','09':'Sept','10':'October','11':'Nov','12':'December'}"
				cssClass="form-control chosen-select" ></s:select>
			</div> --%>
			<%-- <div class="form-group" style="width:100px">
				<s:select name="month" id="date1"  
				list="#{'01':'January','02':'February','03':'March','04':'April','05':'May','06':'Jun','07':'July','08':'Augest','09':'September','10':'October','11':'November','12':'December'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			
			
			<div class="form-group" style="width:100px">
				<s:select name="year" id="date"  
				list="#{'2014':'2014','2015':'2015','2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			 --%>
				<div class="form-group" style="width:20%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
			</div>
			<div class="form-group" style="width:20%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
			</div>
			
		
			
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
		</div>
	</div>
	</div>
	</s:form>
	
	<%int i=1; %>
	<table class="my-table " style="width: 100%">
				<tr><th>Sr. No.</th>
					<th>Department</th>
					<th>Count(OPD)</th>
					<th>New</th>
					<th>Old</th>
					<!-- <th>Diagnosis</th> -->
					
				
				</tr>
				
				
				<s:if test="avg_list.size!=0">
							<s:iterator value="avg_list" status="rowstatus">
							<s:if test="avgopd!=0.0||avgipd!=0.0||avgot!=0.0">
								<tr>
								<td><%=i++ %></td>
									<td><s:property value="department"/>
									<s:if test="otlist.size!=0">
									
									<table class="my-table" style="width: 100%; border:1px solid" >
									
									<tr style="background-color: #6699cc;color: white;">
									<!-- <td></td> -->
									<td >Procedure name</td>
									</tr>
									<s:iterator value="otlist">
									<tr>
									<!-- <td></td> -->
									<td><s:property value="procedurename"/></td>
									</tr>
										
									</s:iterator>
								 	</table> 
									</s:if>
								
									</td>
									<td><s:if test="avgopd!=0.0"><s:property value="avgopd"/></s:if></td>
									<td><s:property value="Newcount"/></td>
									<td><s:property value="Oldcount"/></td>
								
								
								</tr>
								</s:if>
							</s:iterator>
							
						</s:if>
						
						<s:else>
					No Record Found!!
				</s:else>
				<tr>
				<th></th>
					<th><b>TOTAL:</b></th>
					<th><b><s:property value="totalopd"/></b></th>
					<%-- <th><b><s:property value="totalipd"/></b></th>
					<th><b><s:property value="totalot"/></b></th> --%>
					<th></th>
					<th></th>
				<tr>
</table><br>


<table class="my-table xlstable hidden-lg hidden-md visible-print hidden-print" style="width: 100%">
				<tr><th>Sr. No.</th>
					<th>Department</th>
					<th>Count(OPD)</th>
					<th>Count(IPD)</th>
					<th>Count(OT)</th>
					<th>Diagnosis</th>
					<th></th>
				</tr>
				
				
				<s:if test="avg_list.size!=0">
							<s:iterator value="avg_list" status="rowstatus">
							<s:if test="avgopd!=0.0||avgipd!=0.0||avgot!=0.0">
								<tr>
									<td><%=i++ %></td>
									<td><s:property value="department"/></td>
									<td><s:if test="avgopd!=0.0"><s:property value="avgopd"/></s:if></td>
									<td><s:if test="avgipd!=0.0"><s:property value="avgipd"/></s:if></td>
									<td><s:if test="avgot!=0.0"><s:property value="avgot"/></s:if></td>
									<td><s:property value="diagnosis"/></td>
									
										<s:if test="otlist.size!=0">
											<td style="background-color: #6699cc;color: white;">Procedure name</td>
										</s:if>
										<s:else>
											<td></td>
										</s:else>
								</tr>
								
									<s:if test="otlist.size!=0">
												<s:iterator value="otlist">
													<tr>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td><s:property value="procedurename"/></td>
													</tr>
												</s:iterator>
											</s:if>
								
								
								
								</s:if>
							</s:iterator>
							
						</s:if>
						
						<s:else>
					No Record Found!!
				</s:else>
				<tr>
				<th></th>
					<th><b>TOTAL:</b></th>
					<th><b><s:property value="totalopd"/></b></th>
					<th><b><s:property value="totalipd"/></b></th>
					<th><b><s:property value="totalot"/></b></th>
					<th></th>
					<th></th>
				<tr>
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


			
						