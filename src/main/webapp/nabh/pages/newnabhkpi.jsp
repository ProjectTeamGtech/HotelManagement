	<%@page import="com.apm.Report.eu.entity.MisReport"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <script src="mis/js/mischart.js"></script>
    <script type="text/javascript" src="nabh/js/newnabh.js"></script>
    <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
     <SCRIPT type="text/javascript">
         function printKPIIndiExcel() {

             $(".xlsKPItable").table2excel({
   					exclude: ".noExl",
   					name: "KPI List",
   					filename: "KPI Data",
   					fileext: ".xls",
   					exclude_img: true,
   					exclude_links: true,
   					exclude_inputs: true
   				});
              }
        </SCRIPT>
	<!-- start page content -->
            <div class="page-content-wrapper">
               
               <div class="page-content">
                     
                   <!-- start widget -->
					
					<!-- end widget -->
                     <!-- chart start -->
                    <div class="row">
                     <div class="col-md-12 ">
                     <header class="inlinei"><b>QI Tracker &nbsp; &nbsp; </b></header>
                   
  
                     
    		<div class="clearfix" style="height:40px;"></div>    
              
<div class="row">
<s:form class="form-inline" action="Nabh"  theme="simple" >
	<s:hidden name="action" id="action" value="kpinabh"></s:hidden>
	<s:hidden name="isKPI" id="isKPI" value="1"></s:hidden> 
   <div class="col-md-3"> 
    	<s:select headerKey="0" headerValue="Select KPI Area" cssClass="form-control" list="kpiarealist" listKey="id" listValue="name" name="kpiarea_filter" />
   </div>   
   <div class="col-md-3"> 
		<s:select cssClass="form-control" list="#{'2015-2016':'2015-2016', '2016-2017':'2016-2017', '2017-2018':'2017-2018', '2018-2019':'2018-2019', '2019-2020':'2019-2020','2020-2021':'2020-2021','2021-2022':'2021-2022'}" id="year_filter" name="year_filter" />
   </div>
   <div class="col-md-2">
   		<button  type="submit" class="btn btn-primary "> Go</button>
   </div>
   <div class="col-md-4">
    	<div class="form-group pull-right" >
			<!-- <a href="QI_Settings.jsp"  title="QI Settings"><i class="fa fa-cogs" aria-hidden="true"></i></a> -->
		</div>
    </div>
    </s:form>
 </div>
 
  <div style="height:10px;" class="clearfix"> </div>
                     
                     </div>
                    
  
    
                     <div  class="clearfix"> </div>
                        <div class="col-md-12">
                              
                              
                              
                              <div class="panel-group" id="accordion">
                     <%int x=1; %>
					<s:iterator value="kpilist">         
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 data-toggle="collapse" data-parent="#accordion<%=x%>"
								href="#collapse<%=x%>" class="panel-title expand">
								<div class="right-arrow pull-right">+</div>
								<s:if test="ismannual==1">
				          			<a href="#" onclick="opendownloadExcel(<s:property value="Kpiid"/>)" title="Download"><i class="fa fa-download" aria-hidden="true"></i> &nbsp; </a>		
				          		</s:if>
								<a href="#"> <%=x%>. <s:property value="kpiarea"/> - <s:property value="kpiindicator"/></a>
							</h4>
						</div>
						<div id="collapse<%=x%>" class="panel-collapse collapse out">
							<div class="panel-body">
								<div class="col-md-8">
									<div id="indicator<%=x%>"></div>
								</div>
								<div class="col-md-4">
									<div class="panel">
										<header class="panel-heading panel-heading-blue headerpadingzero">
											KPI Formula<br><s:property value="formula_desc"/>
										</header>
										<div class="panel-body">
											<div id="external-events">
												<div class="form-group">
													<%-- <s:select cssClass="form-control" list="#{'0':'Select Year','2015-2016':'2015-2016', '2016-2017':'2016-2017', '2017-2018':'2017-2018', '2018-2019':'2018-2019', '2019-2020':'2019-2020','2020-2021':'2020-2021','2021-2022':'2021-2022'}" id="kpiyearfilter<s:property value="kpiid"/>" /> --%>
													<label>Select Year</label>
													 <select class="form-control" id="kpiyearfilter<s:property value="kpiid"/>" onChange="kpiyearchange(this.value,<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>,<%=x%>)">
														<option value="0">Select</option>
														<option value="2015-2016">2015-2016</option>
														<option value="2016-2017">2016-2017</option>
														<option value="2017-2018">2017-2018</option>
														<option value="2018-2019">2018-2019</option>
														<option value="2019-2020">2019-2020</option>
														<option value="2020-2021">2020-2021</option>
														<option value="2021-2022">2021-2022</option>
														<option value="2022-2023">2022-2023</option>
														<option value="2023-2024">2023-2024</option>
														<option value="2024-2025">2024-2025</option>
														<option value="2025-2026">2025-2026</option>
														<option value="2026-2027">2026-2027</option>
													</select>
												</div>
												<div class="form-group">
													<label>Select Month</label>
													 <select class="form-control" id="monthfilter<s:property value="kpiid"/>" onChange="kpimonthchange(this.value,<s:property value="kpiid"/>)">
														<option value="00">Select</option>
														<option value="01">January</option>
														<option value="02">February</option>
														<option value="03">March</option>
														<option value="04">April</option>
														<option value="05">May</option>
														<option value="06">June</option>
														<option value="07">July</option>
														<option value="08">August</option>
														<option value="09">September</option>
														<option value="10">October</option>
														<option value="11">November</option>
														<option value="12">December</option>
													</select>
												</div>
											<div class="form-inline">
													<label>Enter values for KPI</label>
													<div class="clearfix"></div>
													<%int y=1; %>
													<s:if test="noofinputlist.size==1">
														<s:iterator value="noofinputlist">
															<div class="form-group col-md-12 padingleftzero">
																<input type="hidden" class="akash<s:property value="kpiid"/>" value="<%=y%>"/>
													        	<input type="number" name="input<%=y%><s:property value="kpiid"/>" id="input<%=y%><s:property value="kpiid"/>" class="form-control" value="<s:property value="inputData"/>" placeholder="input<%=y%>" style="width: 100%;">
																<%y++;%>
															</div>
														</s:iterator>
													</s:if>
													<s:elseif test="noofinputlist.size==2">
														<s:iterator value="noofinputlist">
															<div class="form-group col-md-6 padingleftzero">
																<input type="hidden" class="akash<s:property value="kpiid"/>" value="<%=y%>"/>
													        	<input type="number" name="input<%=y%><s:property value="kpiid"/>" id="input<%=y%><s:property value="kpiid"/>" class="form-control" value="<s:property value="inputData"/>" placeholder="input<%=y%>" style="width: 100%;">
																<%y++;%>
															</div>
														</s:iterator>
													</s:elseif>
													<s:elseif test="noofinputlist.size==3">
														<s:iterator value="noofinputlist">
															<div class="form-group col-md-4 padingleftzero">
																<input type="hidden" class="akash<s:property value="kpiid"/>" value="<%=y%>"/>
													        	<input type="number" name="input<%=y%><s:property value="kpiid"/>" id="input<%=y%><s:property value="kpiid"/>" class="form-control" value="<s:property value="inputData"/>" placeholder="input<%=y%>" style="width: 100%;">
																<%y++;%>
															</div>
														</s:iterator>
													</s:elseif>
													<s:elseif test="noofinputlist.size==4">
														<s:iterator value="noofinputlist">
															<div class="form-group col-md-3 padingleftzero">
																<input type="hidden" class="akash<s:property value="kpiid"/>" value="<%=y%>"/>
													        	<input type="number" name="input<%=y%><s:property value="kpiid"/>" id="input<%=y%><s:property value="kpiid"/>" class="form-control" value="<s:property value="inputData"/>" placeholder="input<%=y%>" style="width: 100%;">
																<%y++;%>
															</div>
														</s:iterator>
													</s:elseif>
													<s:elseif test="noofinputlist.size==5">
														<s:iterator value="noofinputlist">
															<div class="form-group col-md-2 padingleftzero">
																<input type="hidden" class="akash<s:property value="kpiid"/>" value="<%=y%>"/>
													        	<input type="number" name="input<%=y%><s:property value="kpiid"/>" id="input<%=y%><s:property value="kpiid"/>" class="form-control" value="<s:property value="inputData"/>" placeholder="input<%=y%>" style="width: 100%;">
																<%y++;%>
															</div>
														</s:iterator>
													</s:elseif>
													
													
													<div class="clearfix" style="height: 50px;"></div>
													<!-- <a href="javascript:;" id="event_add"
														class="btn btn-success full-width"> Calculate </a> -->
														<a href="#" class="btn btn-success full-width" 
														onclick="calculateKPI(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>)"> Calculate </a>
													</div>
												<div class="form-inline">
													<div style="height: 50px;" class="clearfix"></div>
													<div class="form-group col-md-6 padingleftzero">
														<input type="hidden" class="akash1" value="1"> <input
															type="number" placeholder="KPI Result" name="result<s:property value="kpiid"/>"
															id="result<s:property value="kpiid"/>" value="" class="form-control"
															style="width: 100%;">
													</div>
													<div class="form-group col-md-6 padingrightzero">
														<input type="number" placeholder="Target" name="target<s:property value="kpiid"/>"
															id="target<s:property value="kpiid"/>" value="" class="form-control"
															style="width: 100%;">
													</div>
													<div class="clearfix" style="height: 50px;"></div>
													<!-- <a href="javascript:;" id="event_add"
														class="btn btn-success full-width"> Save </a> -->
														<s:if test="ismannual==0">
															<a href="#" class="btn btn-success full-width" 
													    		 onclick="saveNewKPIResult(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>,'<s:property value="kpi_year"/>','<s:property value="kpi_month"/>',<s:property value="kpi_dataid"/>,<%=x%>)"> Save </a>
													    	<%-- <s:if test="kpi_dataid==0">
													    		<a href="#" class="btn btn-success full-width" 
													    		 onclick="saveNewKPIResult(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>,'<s:property value="kpi_year"/>','<s:property value="kpi_month"/>',<s:property value="kpi_dataid"/>,<%=x%>)"> Save </a>
													          	<a href="#" class="btn btn-primary full-width" onclick="saveKPIResult(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>,'<s:property value="kpi_year"/>','<s:property value="kpi_month"/>',<s:property value="kpi_dataid"/>)">Save</a>
													        </s:if>
													        <s:else>
													        	<a href="#" class="btn btn-success full-width" 
													        	onclick="saveNewKPIResult(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>,'<s:property value="kpi_year"/>','<s:property value="kpi_month"/>',<s:property value="kpi_dataid"/>,<%=x%>)"> Edit </a>
													          	<a href="#" class="btn btn-primary full-width" onclick="saveKPIResult(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>,'<s:property value="kpi_year"/>','<s:property value="kpi_month"/>',<s:property value="kpi_dataid"/>)">Edit</a>
													        </s:else> --%>
													   </s:if>
														
												</div>
											<hr class="visible-xs">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%x++; %>
					</s:iterator>
  </div>

                        </div>
                          
                    </div>


 <!-- Select Month Modal content-->
		<div id="downreport" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-sm">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Download Report</h4>
		      </div>
		      <div class="modal-body">
		      <div class="row">
		        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		        	<div class="from-group">
		        		<s:hidden id="kpiidfordownload"></s:hidden>
		        		<s:hidden id="kpiyearfordownload"></s:hidden>
		        		<label>Select Month</label>
		        		<select id="monthdownloadid" class="form-control">
						    <option value="01">January</option>
						    <option value="02">February</option>
						    <option value="03">March</option>
						    <option value="04">April</option>
						    <option value="05">May</option>
						    <option value="06">June</option>
						    <option value="07">July</option>
						    <option value="08">August</option>
						    <option value="09" selected="selected">September</option>
						    <option value="10">October</option>
						    <option value="11">November</option>
						    <option value="12">December</option>
						</select>
		        	</div>
		        </div>
		        </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" onclick="downloadExcelForKPI()" class="btn btn-primary" data-dismiss="modal">Download</button>
		      </div>
		    </div>
		
		  </div>
		</div>
            <!--Indent Report @ -->
		<table class="my-table xlsKPItable" style="width: 100%;font-size: 8px;display:none;">
         
           							 <thead id="kpitheadid">
											<tr>
												<th>KPI Area</th>
												<th>Indicator</th>
												<th>KPI Year</th>
												<th>KPI Month</th>
												<th>Patient name</th>
											</tr>
										</thead>
										<tbody id="kpitbodyid">
												
										</tbody>
         </table>           
                    
 	<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
		<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>

		<script src="mis/kpiplugin/js/jquery.scrollto.js"></script>        
<% ArrayList<MisReport> arrayList =(ArrayList<MisReport>) session.getAttribute("graphicalkpilist");%>
<%int aj=1; %>
<%for(MisReport misReport:arrayList) {%>
<div id="kpiqichart<%=aj%>">
<script id="kipqiscript<%=aj%>">
	//Created by Abhi graph 1 for KPI
	Highcharts.chart('indicator<%=aj%>', {
	chart: {
			type: 'line'
			},
    title: {
	        text: '<%=misReport.getKpi_year()%>'
	    },
	    xAxis: {
	        categories: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar', ]
	    },
	    yAxis: {
	        title: {
	            text: ''
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true
	            },
	            enableMouseTracking: true
	        }
	    },
	    series: [{
	       <%--  name: '<%=misReport.getKpiindicator()%>', --%>
	        name: 'Result',
	        data: [<%=misReport.getMonth4()%>,<%=misReport.getMonth5()%>, <%=misReport.getMonth6()%>, <%=misReport.getMonth7()%>, <%=misReport.getMonth8()%>, <%=misReport.getMonth9()%>, <%=misReport.getMonth10()%>, <%=misReport.getMonth11()%>, <%=misReport.getMonth12()%>, <%=misReport.getMonth1()%>, <%=misReport.getMonth2()%>, <%=misReport.getMonth3()%>]
	    },{
	        name: 'Target',
	        data: [<%=misReport.getMonth4_target()%>,<%=misReport.getMonth5_target()%>, <%=misReport.getMonth6_target()%>, <%=misReport.getMonth7_target()%>, <%=misReport.getMonth8_target()%>, <%=misReport.getMonth9_target()%>, <%=misReport.getMonth10_target()%>, <%=misReport.getMonth11_target()%>, <%=misReport.getMonth12_target()%>, <%=misReport.getMonth1_target()%>, <%=misReport.getMonth2_target()%>, <%=misReport.getMonth3_target()%>]
	    }]
	});
</script>
</div>
<%aj++;} %>
                
                    
                    
                     <!-- Chart end -->
                      
                     <!-- start admited patient list -->
                     
                    <!-- end admited patient list -->
                </div>
               
            </div>
            <!-- end page content -->