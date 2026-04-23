<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<style>
@media print{
	#lttrhd{
	margin-bottom: -200px;
	}
}
</style>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>


function printExcel() {

      $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Death Report List",
					filename: "deathReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
  }
 

	$(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

	/* 	$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		}); */
	});
	
	   
</script>
<div class="">

							<div class="">
<div class="row print-visible hidden-md hidden-lg" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
		


			</div>
			<!-- </div> -->
		</div>
		
		 <script type="text/javascript" src="dietary/js/dietaryCatDetail.js"></script>
		
		<div class="col-lg-1 col-md-1"></div>
		
	</div>
	<div class="">

							<div class="">
		
							
							
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

						
 
										<h4>Diet Plan Sheet</h4>

									</div>
								</div>
								<s:form action="printgeneraldietplanDietarydetails" id="printform" theme="simple">
								
								<s:hidden name="allward" id="allward" />
								
								<s:hidden name="id" />
								<div class="col-lg-12 col-md-12 col-xs-12 topback2 ">
										<div class="form-inline">
											<%-- <div class="form-group" style="width:12%;">
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="select date" style="width:100%;"></s:textfield>
											</div> --%>
												<%-- <div class="form-group" style="width:160px;">
												<select name="filter_status" id="searchfrm_filter_status" class="form-control" style="width:100%;">
												    <option value="0" selected="selected">Select All</option>
												    <option value="1">Morning</option>
												    <option value="2">Noon</option>
												    <option value="3">Night</option>
												  
												</select>
											</div> --%>
											<%-- <div class="form-group" style="width:7%;">
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div> --%>
											<%-- <div class="form-group">
												<s:select list="doctorlist" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="name" headerKey="0" headerValue="Select Practitioner"></s:select>
											</div>
											<div class="form-group">
												<s:select list="clientlist" cssClass="form-control chosen-select" name="clientId" id="clientId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Client"></s:select>
											</div> --%>
											<!-- <div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div> -->
											
											<td>
									<s:if test="diettimeshift==1">
										Diet Plan For Morning
									</s:if>
									<s:elseif test="diettimeshift==2">Diet Plan For Noon</s:elseif>
									<s:else>Diet Plan For Evening</s:else>
								</td><!-- 
								 <td><s:property value="date"/></td>-->
								
								
								                        	<div class="form-group">
				<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="width:100%;">Select Bed/Ward <span class="caret"></span></button>
												<ul class="dropdown-menu">
												<li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="all" onchange="dofilter()" value="0">all </a></li>
													<s:iterator value="wardlistname">
													
													<s:if test="status==1">
														 <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" checked="checked" onchange="dofilter()" id="p<s:property value="id" />" class="pacss" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="wardname"/></span></a></li>
													</s:if>
													<s:else>
														 <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" onchange="dofilter()" id="p<s:property value="id" />" class="pacss" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="wardname"/></span></a></li>
													</s:else>
												 	
												  </s:iterator>
												 
												</ul>
			</div> 
			
			<s:hidden name="date"/>
			
			<div class="form-group">
												<button type="button" class="btn btn-primary" onclick="dofilter()">Go</button>
												
												
											</div>
											<div class="form-group"></div>
											<div class="form-group">
											<span><b>Date : <s:property value="date"/> </b></span>
											</div>
											<div class="form-group" style="padding-left: 250px;font-size: 16px;
											">
											<label><b> Dietician Incharge :</b></label>
											<td><s:property value="dietician_incharge"/></td>
											</div>
											
											<tr>
									<%-- <th width="50%">Dietician Incharge</th>
										</tr>
										
										<tbody>
										<tr>
									
										<td><s:property value="dietician_incharge"/></td>
									
										</tr>
										
										</tbody> --%>
											<!-- <div class="form-group pull-right">
												
												<a href="#"  onclick="printDiscountReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div> -->
											</tr>
											<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a>
											</div>
										</div>
										
								</div>
								
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea" >
				<table class="my-table tablexls tableborderprint"  style="width: 100%;font-size: 8px"  >
												
							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 100px;" id="lttrhd">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
	<div class="print-visible hidden-md hidden-lg">
		<span ><b>Date : <s:property value="date"/> </b></span>&nbsp;&nbsp;&nbsp;
		<s:if test="diettimeshift==1">
										Diet Plan For Morning
									</s:if>
									<s:elseif test="diettimeshift==2">Diet Plan For Noon</s:elseif>
									<s:else>Diet Plan For Evening</s:else>
									</div>
							<thead class="theadfinal">
							<tr>
								<th width="1%">S.</th>
                              <th class="hidden" width="10%">Date</th>  
                                    <th width="15%">Patient Name</th>
                                    <th width="11%">Ward / Bed</th>
                                    <s:if test="printnew!=1">
                                     <th width="50%">Diagnosis</th>
                                     </s:if>
                                    <th width="10%"> Diet Plan</th>
                                    <!--  <th>Diet Time</th> -->
                                    <th width="10%">Remark</th>
                                    
							</tr>
						
					</thead>
					<tbody>
					
						<%int i=0; %>
						<s:iterator value="generaldietplanlist">
					   		<tr>
					     			<td><%=++i%></td>
              				<%-- <td><s:property value="date"/> --%><s:hidden id="ipdid" ></s:hidden></td>
              				<td><s:property value="clientname"/></td>
              				<td><s:property value="wardname"/>  
              					<s:if test="bedname!=null">
              						/<s:property value="bedname"/>
              					</s:if></td>
              					  <s:if test="printnew!=1">
								<td><s:property value="conditionname"/></td>
								</s:if>
								<td>
								<s:iterator value="list2">
								<strong><b>
								<s:property value="dietplan"/><br>
								</b></strong>
								</s:iterator>
								<%-- <td><s:property value="diaetplan"/></td> --%>
								</td>
						
								<td><s:property value="remark"/>  
					     	</tr>
					   </s:iterator>
					</tbody>
			 </table> <br><div class="print-visible hidden-md hidden-lg">
			 
			 
			 
			
		
		
		 <p>	Full Diet : <s:property value="fulldiet"/>,
			Soft Diet: <s:property value="softdiet"/>,
			Liquid Diet: <s:property value="liquiddiet"/>,
			Diabetic Diet : <s:property value="diabetic"/>,
			RT Feed :<s:property value="rtfeed"/>,
			Renal : <s:property value="renal"/>,
			Hepatic :<s:property value="Hepatic"/>,
			Clear Liquid :<s:property value="clearliquid"/>,
			Semi Solid : <s:property value="nbm"/>,
			NBM :<s:property value="blended"/>,
			Blended :<s:property value="semisolid"/>,
			<br> 
		<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" >	
			<div class="col-lg-6 col-xs-6 col-md-6" style="padding: 0px;" >	
		<b>	<p>Dietitian Incharge : <s:property value="dietician_incharge"/></p></b>
			</div>
			<div class="col-lg-6 col-xs-6 col-md-6" style="padding: 0px;" >	
			<p align="right"><b>Signature</b></p>
			</div>
			</div>
			 </div></div>
			 
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



	