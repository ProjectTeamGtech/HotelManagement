	<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Report.eu.entity.MisReport"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
    <script type="text/javascript" src="diarymanagement/js/marketing.js"></script>
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
        
		<script type="text/javascript">
			function printpagenesw(){
				var chart = $('#indicator').highcharts();
		        chart.setSize(950,1070, false);
		        
		        var chart1 = $('#indicator1').highcharts();
		        chart1.setSize(950,1260, false);
		        
		        var chart2 = $('#indicator2').highcharts();
		        chart2.setSize(950,1250, false);
		        
		        var chart3 = $('#indicator3').highcharts();
		        chart3.setSize(1000,1250, false);
		        
		        window.print();
		        chart.setSize(1021,450, false);
		        chart1.setSize(1021,450, false);
		        chart2.setSize(1021,450, false);
		        chart3.setSize(1021,450, false);
			}
			
		</script> 
	

        <%-- <script type="text/javascript">
        	window.onload=function(){
        		document.getElementById("indicator").className="chart33";
        	}
			
		</script> --%>
        <style>
        
        @media print{

.pdding{padding-top:110px;
padding-right:15px;
 }
 .chart1{
 	height: 1200px !important;
 }
 
 .chart2{
 	height: 1133px !important;
 }
 
 .chart11{
 	height: 1375px !important;
 }
 
 .chart12{
 	height: 1133px !important;
 }
 
 .chart21{
 	height: 1375px !important;
 }
 
 .chart22{
 	height: 1133px !important;
 }
 
  .chart31{
 	height: 1375px !important;
 }
 
 .chart32{
 	height: 1133px !important;
 }
 
}
        </style>
	<!-- start page content -->
            <div class="page-content-wrapper">
               
               
               	<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
         <% 
	ArrayList<Client> heightmasterlist =(ArrayList<Client>) session.getAttribute("heightmasterlist");
	ArrayList<Client> weightmasterlist =(ArrayList<Client>) session.getAttribute("weightmasterlist");
	ArrayList<Client> bmimasterlist =(ArrayList<Client>) session.getAttribute("bmimasterlist");
	ArrayList<Client> headcircumferncemasterlist =(ArrayList<Client>) session.getAttribute("headcircumferncemasterlist");
	ArrayList<Client> clientgrowthlist =(ArrayList<Client>) session.getAttribute("clientgrowthlist");
	String clientgender = (String) session.getAttribute("clientgender");
	
	//   
	ArrayList<Client> clientgrowthheightlist =(ArrayList<Client>) session.getAttribute("clientgrowthheightlist");
	ArrayList<Client> clientgrowthheadlist =(ArrayList<Client>) session.getAttribute("clientgrowthheadlist");
	ArrayList<Client> clientgrowthweightlist =(ArrayList<Client>) session.getAttribute("clientgrowthweightlist");
	ArrayList<Client> clientgrowthbmilist = (ArrayList<Client>) session.getAttribute("clientgrowthbmilist");

%>
               
               
               <div class="page-content">
                     
                   <!-- start widget -->
					
					<!-- end widget -->
                     <!-- chart start -->
                    <div class="row">
                     <div class="col-md-12 ">
                     <header class="inlinei"><b>Child Growth Chart &nbsp; &nbsp;<br> 
                     	Patient Name: <s:property value="fullname"/><br>
                     	UHID: <s:property value="abrivationid"/><br>
                     	Age: <s:property value="age"/><br>
                     	Gender: <s:property value="gender"/><br>
                     	</b></header>
                   
 
  				<div style="height:10px;" class="clearfix"> </div>
                     <s:hidden name="clientId" id="clientId"></s:hidden>
                </div>
                    
    
    
                     <div  class="clearfix"> </div>
       <div class="col-md-12 pdding chart1" >
      <h3> <a type="button" class="btn btn-success full-width hidden-print" style="width:20%;height:15%;align:right;" title="Print" onclick="printpagenesw()"><i class="fa fa-print"></i>&nbsp;&nbsp;Print</a></h3> 
      
           <div class="panel-group" id="accordion">
               <div class="panel panel-default">
					<div class="panel-heading">
						<h4 data-toggle="collapse" data-parent="#accordion"
								href="#collapse" class="panel-title expand">
								<div class="right-arrow pull-right">-</div>
								<a href="#">Height</a>
							</h4>
						</div>
						<div id="collapse" class="panel-collapse collapse in">
							<div class="panel-body chart2" >
								<div class="col-md-12">
								<div class="col-md-10">
									<!-- <div id="indicator" class="hidden-print"></div>
									<div id="indicator" class="visible-print" style="height: 1060px;"></div> -->
									<div id="indicator"></div>
								</div>
								<div class="col-md-2">
									<div class="panel">
										<!-- <header class="panel-heading panel-heading-blue headerpadingzero">
												
										</header> -->
										<div class="panel-body hidden-print">
											<div id="external-events">
													<!-- <div class="clearfix" style="height: 50px;"></div> -->
													<div class="form-inline">
														<label>Height and Month:</label>
													</div>
													<div class="form-inline">
														<div class="clearfix"></div>
															<div class="form-group padingleftzero" style="margin-left: 6px;">
																<input type="number"  id="heightmonth" class="form-control" onchange="changemonthofcgd(this.value,'height')"  placeholder="month" style="width: 100%;">
															</div>
														<div class="clearfix" style="height: 10px;"></div>
													
													</div>
													<div class="form-inline">
															<div class="form-group padingleftzero" style="margin-left: 6px;">
																<input type="number" id="heightdata" class="form-control" placeholder="height cm" style="width: 100%;">
															</div>
														<div class="clearfix" style="height: 10px;"></div>
													</div>
													<div class="form-inline" style="margin-left: 50px;">
														<a href="#" class="btn btn-success full-width" onclick="savechildgrowthdata('height')"> Save </a>
													</div>
											<hr class="visible-xs">
											</div>
										</div>
										<div class="panel-body">
											<div id="external-events">
													<div class="form-inline">
														<div class="clearfix"></div>
														<div class="form-group padingleftzero" style="margin-left: 6px;width: 100%">
																<table class="table table-responsive" style="width: 100%;text-transform: uppercase;margin-left: 6px;">
																	<thead>
										                                <tr>
										                                	<th style="width: 50%;text-align: center;">Month</th>
										                                	<th style="width: 50%;text-align: center;">Value</th>
										                                </tr>
										                            </thead>
										                            <tbody>
										                            	<%for(Client client:clientgrowthheightlist) {%>
																        	   <%if(client.getHeightdata()!=null){ %>
																	        	 <tr>
												                                	<td style="text-align: center;"><%=client.getMonthyear()%></td>
												                                	<td style="text-align: center;"> <%=client.getHeightdata()%></td>
											                                	 </tr>
										                                	<%} %>
															        	<%}%>
										                            </tbody>
																</table>
														</div>
													</div>
											</div>
										</div>
									</div>
								</div>
								</div>
							</div>
						</div>
					</div>
  				</div>
              </div>
              
              
              <div class="col-md-12 pdding">
           <div class="panel-group" id="accordion1">
               <div class="panel panel-default chart11">
					<div class="panel-heading">
						<h4 data-toggle="collapse" data-parent="#accordion1"
								href="#collapse1" class="panel-title expand">
								<div class="right-arrow pull-right">-</div>
								<a href="#">Weight</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body chart12">
								<div class="col-md-12">
								<div class="col-md-10">
									<div id="indicator1"></div>
								</div>
								<div class="col-md-2">
									<div class="panel">
										<!-- <header class="panel-heading panel-heading-blue headerpadingzero">
											Enter Data
										</header> -->
										<div class="panel-body hidden-print">
											<!-- <div id="external-events"> -->
											<div id="external-events hidden-print">
													<!-- <div class="clearfix" style="height: 50px;"></div> -->
													<div class="form-inline">
														<label>Weight and Month:</label>
													</div>
													<div class="form-inline">
														<div class="clearfix"></div>
															<div class="form-group padingleftzero" style="margin-left: 6px;">
																<input type="number" id="weightmonth" onchange="changemonthofcgd(this.value,'weight')" class="form-control"  placeholder="Month" style="width: 100%;">
															</div>
														<div class="clearfix" style="height: 10px;"></div>
													</div>
													<div class="form-inline">
															<div class="form-group padingleftzero" style="margin-left: 6px;">
																<input type="number" id="weightdata" class="form-control" placeholder="Weight kg" style="width: 100%;">
															</div>
														<div class="clearfix" style="height: 10px;"></div>
													</div>
													
													<div class="form-inline" style="margin-left: 50px;">
														<a href="#" class="btn btn-success full-width" onclick="savechildgrowthdata('weight')" > Save </a>
													</div>
											<hr class="visible-xs">
											</div>
									</div>
									<div class="panel-body">
											<div id="external-events">
													<div class="form-inline">
														<div class="clearfix"></div>
														<div class="form-group padingleftzero" style="margin-left: 6px;width: 100%">
																<table class="table table-responsive" style="width: 100%;text-transform: uppercase;margin-left: 6px;">
																	<thead>
										                                <tr>
										                                	<th style="width: 50%;text-align: center;">Month</th>
										                                	<th style="width: 50%;text-align: center;">Value</th>
										                                </tr>
										                            </thead>
										                            <tbody>
										                            	<%for(Client client:clientgrowthweightlist) {%>
																        	   <%if(client.getWeightdata()!=null){ %>
																        	 <tr>
											                                	<td style="text-align: center;"><%=client.getMonthyear()%></td>
											                                	<td style="text-align: center;"> <%=client.getWeightdata()%></td>
										                                	</tr>
										                                	<%}%>
															        	<%}%>
										                            </tbody>
																</table>
														</div>
													</div>
											</div>
										</div>
								</div>
								</div>
							</div>
						</div>
					</div>
  				</div>
              </div>
             </div>
              
              
              <div class="col-md-12 pdding">
           <div class="panel-group" id="accordion2">
               <div class="panel panel-default chart21">
					<div class="panel-heading">
						<h4 data-toggle="collapse" data-parent="#accordion2"
								href="#collapse2" class="panel-title expand">
								<div class="right-arrow pull-right">-</div>
								<a href="#"> BMI</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse in">
							<div class="panel-body chart22">
								<div class="col-md-12">
								<div class="col-md-10">
									<div id="indicator2"></div>
								</div>
								<div class="col-md-2">
									<div class="panel">
										<!-- <header class="panel-heading panel-heading-blue headerpadingzero">
											Enter Data
										</header> -->
										<div class="panel-body hidden-print">
											<div id="external-events">
											
													<!-- <div class="clearfix" style="height: 50px;"></div> -->
													<div class="form-inline">
														<label>BMI and Month:</label>
													</div>
													<div class="form-inline">
														<div class="clearfix"></div>
														<div class="form-group padingleftzero" style="margin-left: 6px;">
															<input type="number" id="bmimonth" onchange="changemonthofcgd(this.value,'bmi')" class="form-control"  placeholder="Month" style="width: 100%;">
														</div>
													<div class="clearfix" style="height: 10px;"></div>
													</div>
													<div class="form-inline">
															<div class="form-group padingleftzero" style="margin-left: 6px;">
																<input type="number" id="bmidata" class="form-control" placeholder="BMI" style="width: 100%;">
															</div>
														<div class="clearfix" style="height: 10px;"></div>
													</div>
													
													<div class="form-inline" style="margin-left: 50px;">
														<a href="#" class="btn btn-success full-width" onclick="savechildgrowthdata('bmi')" > Save </a>
													</div>
											<hr class="visible-xs">
										</div>
									</div>
									
									<div class="panel-body">
											<div id="external-events">
													<div class="form-inline">
														<div class="clearfix"></div>
														<div class="form-group padingleftzero" style="margin-left: 6px;width: 100%">
																<table class="table table-responsive" style="width: 100%;text-transform: uppercase;margin-left: 6px;">
																	<thead>
										                                <tr>
										                                	<th style="width: 50%;text-align: center;">Month</th>
										                                	<th style="width: 50%;text-align: center;">Value</th>
										                                </tr>
										                            </thead>
										                            <tbody>
										                            	<%for(Client client:clientgrowthbmilist) {%>
																        	<%if(client.getBmidata()!=null){ %>
																	        	 <tr>
												                                	<td style="text-align: center;"><%=client.getMonthyear()%></td>
												                                	<td style="text-align: center;"> <%=client.getBmidata()%></td>
											                                	</tr>
										                                	 <%}%>
															        	<%}%>
										                            </tbody>
																</table>
														</div>
													</div>
											</div>
										</div>
								</div>
								</div>
							</div>
						</div>
					</div>
  				</div>
              </div>
              
              
              
             </div>

 		
             <div class="col-md-12 pdding">
           <div class="panel-group" id="accordion3">
               <div class="panel panel-default chart31">
					<div class="panel-heading">
						<h4 data-toggle="collapse" data-parent="#accordion3"
								href="#collapse3" class="panel-title expand">
								<div class="right-arrow pull-right">-</div>
								<a href="#">Head Circumference</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse in">
							<div class="panel-body chart32">
								<div class="col-md-12">
								<div class="col-md-10">
									<div id="indicator3"></div>
								</div>
								<div class="col-md-2">
									<div class="panel">
										<!-- <header class="panel-heading panel-heading-blue headerpadingzero">
											Enter Data
										</header> -->
										<div class="panel-body hidden-print">
											<div id="external-events">
											<!-- <div class="clearfix" style="height: 50px;"></div> -->
													<div class="form-inline">
														<label>Head Circumference and Month:</label>
													</div>
													<div class="form-inline">
													<div class="clearfix"></div>
														<div class="form-group padingleftzero" style="margin-left: 6px;">
															<input type="number" id="headcircumferncemonth" onchange="changemonthofcgd(this.value,'headcircumfernce')" class="form-control"  placeholder="Month" style="width: 100%;">
														</div>
													<div class="clearfix" style="height: 10px;"></div>
													</div>
													<div class="form-inline">
															<div class="form-group padingleftzero" style="margin-left: 6px;">
																<input type="number" id="headcircumferncedata" class="form-control" placeholder="Head Circumference" style="width: 100%;">
															</div>
														<div class="clearfix" style="height: 10px;"></div>
													</div>
													
													<div class="form-inline" style="margin-left: 50px;">
														<a href="#" class="btn btn-success full-width"  onclick="savechildgrowthdata('headcircumfernce')"> Save </a>
													</div>
													<hr class="visible-xs">
										</div>
									</div>
									
									<div class="panel-body">
											<div id="external-events">
													<div class="form-inline">
														<div class="clearfix"></div>
														<div class="form-group padingleftzero" style="margin-left: 6px;width: 100%">
																<table class="table table-responsive" style="width: 100%;text-transform: uppercase;margin-left: 6px;">
																	<thead>
										                                <tr>
										                                	<th style="width: 50%;text-align: center;">Month</th>
										                                	<th style="width: 50%;text-align: center;">Value</th>
										                                </tr>
										                            </thead>
										                            <tbody>
										                            	<%for(Client client:clientgrowthheadlist) {%>
																        	  <%if(client.getHeadcircumferncedata()!=null){ %>
																	        	 <tr>
												                                	<td style="text-align: center;"><%=client.getMonthyear()%></td>
												                                	<td style="text-align: center;"> <%=client.getHeadcircumferncedata()%></td>
											                                	</tr>
										                                	<%}%>
															        	<%}%>
										                            </tbody>
																</table>
														</div>
													</div>
											</div>
										</div>
								</div>
								</div>
							</div>
						</div>
					</div>
  				</div>
              </div>
             </div>
        
     	        
</div>

</div>
 
</div>
               <!-- <div id="chartContainer" style="height: 300px; width: 100%;"></div>     --> 
 	<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
		<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>

		<script src="mis/kpiplugin/js/jquery.scrollto.js"></script>        



<script>

// Created by Abhi graph 1 for KPI heightmasterlist()
Highcharts.chart('indicator', {
	chart: {
			type: 'spline'
			},
    title: {
    		<%if(clientgender.equals("Male")){%>
	        	text: 'Length/Height-for-age BOYS'
	        <%}else{%>
	        	text: 'Length/Height-for-age GIRLS'
	    	<%}%>
	      },
	      //  xAxis: {
// 	    	tickInterval : 31,
// 		    title: {
// 	            text: 'Month'
// 	        }
// 	    },
// 	   
	     xAxis: {
	    	 min: 0,
<%-- 	        categories: [<%for(Client client:heightmasterlist) {%>
			/* 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'  */
			<%=client.getMonth()%>,
		 <%} %> ], --%>
		    title: {
	            text: 'Month'
	        }
	    },

	    
	    yAxis: {
	        title: {
	            text: 'Length/Height (cm)'
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true
	            },
	            enableMouseTracking: true
	        },
	        series: {
	            marker: {
	                enabled: true
	            }
	        }
	    },
	    series: [{
	    	name: '97th',
	    	color: "#D32F2F",
	        data: [
	        <%if(clientgender.equals("Male")){%> /* 'Height-for-age BOYS' */
	       		58.18,61.98,65.06,67.59,69.67,71.45,73.04,74.53,75.96,77.34,78.67,79.96,81.21,82.43,83.61,84.77,85.9,87.01,88.09,89.15,90.19,91.21,92.21,93.19,93.45,94.4,95.32,96.22,97.09,97.94,98.77,99.58,100.37,101.14,101.9,102.65,103.38,104.1,104.81,105.51,106.2,106.88,107.54,108.2,108.85,109.49,110.13,110.76,111.38,112,112.62,113.24,113.85,114.47,115.08,115.69,116.3,116.91,117.52,118.13,118.73,119.28,
	        <%}else{%> /* 'Height-for-age Girls' */
				57.17,60.7,63.56,65.96,68,69.79,71.43,72.97,74.45,75.87,77.25,78.59,79.88,81.14,82.36,83.55,84.71,85.84,86.94,88.03,89.08,90.11,91.12,92.11,92.38,93.32,94.25,95.16,96.05,96.92,97.77,98.6,99.42,100.22,101.01,101.79,102.55,103.31,104.05,104.79,105.52,106.23,106.94,107.64,108.33,109.01,109.68,110.35,111.01,111.66,112.3,112.94,113.57,114.2,114.82,115.43,116.04,116.65,117.24,117.84,118.42,118.95,
	    	<%}%>	        	
	        ]
	    },{
	        name: '90th',
	    	color: "#e32F2a",
	        data: [
	        <%if(clientgender.equals("Male")){%> /* 'Height-for-age BOYS' */
	   			57.02,60.78,63.84,66.35,68.41,70.17,71.74,73.21,74.62,75.97,77.28,78.54,79.76,80.95,82.1,83.23,84.33,85.4,86.45,87.48,88.48,89.47,90.43,91.37,91.6,92.51,93.39,94.26,95.1,95.92,96.72,97.49,98.25,99,99.72,100.44,101.15,101.84,102.52,103.2,103.86,104.52,105.16,105.8,106.42,107.04,107.65,108.26,108.86,109.46,110.06,110.65,111.25,111.84,112.43,113.02,113.61,114.2,114.79,115.37,115.96,116.48,
	        <%}else{%>
	        	/* 'Height-for-age Girls' */
				56.01,59.48,62.3,64.67,66.68,68.43,70.04,71.56,73.01,74.4,75.75,77.05,78.31,79.54,80.73,81.88,83.01,84.11,85.18,86.23,87.25,88.25,89.23,90.19,90.43,91.34,92.24,93.12,93.98,94.81,95.64,96.44,97.23,98.01,98.77,99.52,100.26,100.99,101.71,102.42,103.12,103.81,104.5,105.17,105.84,106.49,107.14,107.78,108.42,109.05,109.67,110.28,110.89,111.5,112.1,112.69,113.27,113.86,114.43,115.01,115.57,116.08,
	    	<%}%> 
	        ] 
	    },{
	    	name: '85th',
	    	color: "orange",
	        data: [
	        <%if(clientgender.equals("Male")){%> /* 'Height-for-age BOYS' */
				56.54,60.29,63.34,65.84,67.89,69.65,71.21,72.67,74.07,75.41,76.71,77.96,79.17,80.34,81.49,82.6,83.68,84.75,85.78,86.79,87.78,88.75,89.7,90.63,90.84,91.74,92.61,93.46,94.29,95.09,95.87,96.64,97.39,98.12,98.83,99.54,100.23,100.91,101.59,102.25,102.91,103.55,104.18,104.81,105.43,106.04,106.64,107.24,107.83,108.42,109.01,109.6,110.18,110.77,111.35,111.93,112.51,113.09,113.67,114.24,114.82,115.34,
          	<%}else{%> /* 'Height-for-age Girls' */
				55.53,58.99,61.79,64.14,66.13,67.88,69.48,70.98,72.42,73.8,75.13,76.42,77.67,78.88,80.06,81.2,82.32,83.4,84.46,85.5,86.51,87.49,88.46,89.4,89.63,90.53,91.42,92.28,93.13,93.96,94.76,95.56,96.34,97.1,97.85,98.59,99.32,100.04,100.75,101.45,102.14,102.82,103.5,104.16,104.82,105.46,106.1,106.74,107.36,107.98,108.59,109.2,109.8,110.39,110.98,111.57,112.14,112.72,113.28,113.85,114.4,114.9,
 			<%}%> ]
	    } ,{
	    	name: '50th',
	    	color: "#00E676",
	        data: [
	        <%if(clientgender.equals("Male")){%> /* 'Height-for-age BOYS' */
	        	54.53,58.22,61.22,63.68,65.71,67.43,68.97,70.39,71.75,73.05,74.3,75.5,76.67,77.79,78.88,79.93,80.96,81.97,82.94,83.9,84.82,85.73,86.62,87.49,87.64,88.47,89.28,90.07,90.84,91.59,92.32,93.03,93.72,94.4,95.07,95.72,96.37,97.01,97.63,98.25,98.86,99.47,100.06,100.65,101.22,101.8,102.36,102.92,103.48,104.03,104.58,105.13,105.67,106.22,106.76,107.31,107.85,108.39,108.93,109.47,110.01,110.5,
	        <%}else{%> /* 'Height-for-age Girls' */
				53.51,56.88,59.61,61.9,63.84,65.54,67.09,68.54,69.92,71.25,72.53,73.76,74.96,76.11,77.23,78.32,79.38,80.41,81.41,82.39,83.35,84.28,85.19,86.08,86.25,87.1,87.93,88.75,89.54,90.32,91.08,91.82,92.55,93.27,93.98,94.67,95.35,96.03,96.69,97.35,98,98.64,99.27,99.89,100.51,101.11,101.71,102.3,102.89,103.47,104.04,104.61,105.17,105.72,106.27,106.82,107.36,107.9,108.43,108.95,109.47,109.94,
	    	<%}%>    ]
	    }     ,{
	    	name: '15th',
	    	color: "green",
	        data: [
	        <%if(clientgender.equals("Male")){%>			/* 'Height-for-age BOYS' */
	        	52.51,56.15,59.11,61.53,63.52,65.22,66.72,68.11,69.43,70.69,71.9,73.05,74.16,75.23,76.27,77.27,78.24,79.19,80.11,81,81.87,82.71,83.54,84.35,84.43,85.2,85.95,86.68,87.39,88.09,88.76,89.41,90.05,90.68,91.3,91.91,92.5,93.1,93.68,94.26,94.82,95.39,95.94,96.48,97.02,97.55,98.08,98.6,99.12,99.63,100.15,100.66,101.17,101.67,102.18,102.69,103.19,103.7,104.2,104.71,105.21,105.66,
	        <%}else{%>					        	/* 'Height-for-age Girls' */
				51.49,54.78,57.44,59.66,61.55,63.2,64.69,66.1,67.43,68.7,69.93,71.11,72.24,73.34,74.41,75.44,76.44,77.42,78.36,79.29,80.19,81.06,81.92,82.76,82.87,83.67,84.45,85.21,85.96,86.69,87.4,88.09,88.77,89.44,90.1,90.75,91.39,92.02,92.64,93.25,93.85,94.45,95.04,95.62,96.19,96.76,97.32,97.87,98.41,98.95,99.48,100.01,100.54,101.05,101.56,102.07,102.58,103.07,103.57,104.05,104.54,104.97,

	    	<%}%>
	        	 ]
	    } ,{
	    	name: '10th',
	    	color: "blue",
	        data: [
	        	<%if(clientgender.equals("Male")){%>
	        	/* 'Height-for-age BOYS' */
	        	52.04,55.66,58.61,61.02,63.01,64.69,66.19,67.57,68.88,70.13,71.33,72.47,73.57,74.63,75.65,76.64,77.6,78.53,79.44,80.31,81.17,82,82.81,83.6,83.68,84.43,85.17,85.88,86.58,87.26,87.92,88.56,89.19,89.8,90.41,91,91.59,92.17,92.74,93.31,93.87,94.42,94.96,95.5,96.03,96.55,97.07,97.58,98.09,98.59,99.1,99.6,100.1,100.6,101.1,101.6,102.09,102.59,103.08,103.58,104.07,104.51,

	        <%}else{%>
	        	/* 'Height-for-age Girls' */
			51.01,54.28,56.92,59.13,61.01,62.64,64.13,65.52,66.84,68.1,69.31,70.48,71.6,72.69,73.74,74.76,75.75,76.71,77.64,78.55,79.44,80.3,81.15,81.97,82.07,82.86,83.63,84.38,85.11,85.83,86.52,87.21,87.88,88.54,89.18,89.82,90.45,91.07,91.68,92.28,92.87,93.46,94.04,94.61,95.17,95.73,96.28,96.82,97.35,97.88,98.41,98.93,99.44,99.95,100.45,100.95,101.45,101.93,102.42,102.9,103.37,103.8,
	    	<%}%>
	        	 ]
	    }
	    ,{
	    	name: '3rd',
	    	color: "#D32F2F",
	        data: [
	        <%if(clientgender.equals("Male")){%>
	        	/* 'Height-for-age BOYS' */
	        50.87,54.46,57.38,59.78,61.74,63.41,64.89,66.25,67.54,68.77,69.94,71.05,72.12,73.15,74.14,75.1,76.03,76.93,77.8,78.64,79.46,80.25,81.03,81.78,81.82,82.54,83.24,83.92,84.59,85.23,85.86,86.47,87.07,87.65,88.23,88.8,89.36,89.91,90.46,91,91.53,92.06,92.58,93.09,93.6,94.1,94.59,95.08,95.57,96.05,96.54,97.01,97.49,97.97,98.45,98.92,99.4,99.87,100.35,100.82,101.29,101.71,

	        <%}else{%>
	        	/* 'Height-for-age Girls' */
			49.84,53.06,55.66,57.84,59.68,61.29,62.74,64.1,65.39,66.63,67.81,68.94,70.03,71.09,72.11,73.09,74.05,74.98,75.88,76.76,77.61,78.45,79.26,80.05,80.12,80.88,81.61,82.33,83.04,83.72,84.39,85.05,85.69,86.32,86.94,87.55,88.15,88.75,89.33,89.91,90.48,91.04,91.59,92.14,92.68,93.21,93.74,94.26,94.77,95.27,95.77,96.27,96.76,97.25,97.73,98.21,98.68,99.15,99.61,100.07,100.52,100.92,
	    	<%}%>
	        ]
	    }
	    ,{
	        name: 'Actual Growth',
	        color: "#212121",
	        data: [
	        	<%for(Client client:clientgrowthheightlist) {%>
		        	    <%=client.getHeightdata()%>,
	        	<%}%>
	        ]
	    }
	    ]
	});
</script>

<script>
	//Created by Abhi graph 1 for KPI (weightmasterlist) 
	Highcharts.chart('indicator1', {
	chart: {
			type: 'spline'
			},
    title: {
    		<%if(clientgender.equals("Male")){%>
    			text: 'Weight-for-age BOYS'
    		<%}else{%>
    			text: 'Weight-for-age GIRLS'
			<%}%>
	    },
	   
	    xAxis: {
	    	min: 0,
<%-- 	        categories: [<%for(Client client:weightmasterlist) {%>
			/* 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'  */
			<%=client.getMonth()%>,
		 <%} %> ], --%>
		    title: {
	            text: 'Month'
	        }
	    },
	    yAxis: {
	        title: {
	            text: 'Weight (kg)'
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true
	            },
	            enableMouseTracking: true
	        },
	        series: {
	            marker: {
	                enabled: true
	            }
	        }
	    },
	    series: [{
	    	name: '97th',
	    	color: "#D32F2F",
	        data: [
<%--  	        		<%for(Client client:weightmasterlist) {%>
 						<%=client.getObesity()%>,
 				 	<%} %> --%>
		<%if(clientgender.equals("Male")){%>		/* 'Weight-for-age BOYS' */
5.64,6.93,7.86,8.58,9.17,9.67,10.11,10.5,10.85,11.17,11.48,11.77,12.06,12.33,12.6,12.87,13.14,13.4,13.66,13.92,14.18,14.45,14.71,14.97,15.23,15.49,15.75,16,16.26,16.5,16.75,16.99,17.22,17.46,17.69,17.92,18.15,18.38,18.6,18.83,19.06,19.29,19.52,19.75,19.98,20.21,20.45,20.68,20.92,21.15,21.39,21.62,21.86,22.1,22.34,22.58,22.82,23.06,23.3,23.54,23.79,24,

		 <%}else{%> 								/* 'Weight-for-age Girls' */
5.33,6.47,7.34,8.05,8.64,9.14,9.58,9.97,10.33,10.66,10.98,11.27,11.56,11.84,12.11,12.38,12.65,12.91,13.18,13.44,13.71,13.97,14.24,14.51,14.78,15.05,15.32,15.59,15.86,16.12,16.39,16.65,16.91,17.17,17.43,17.69,17.95,18.22,18.49,18.75,19.02,19.29,19.57,19.84,20.11,20.38,20.65,20.92,21.19,21.47,21.74,22.01,22.29,22.56,22.83,23.1,23.38,23.65,23.92,24.19,24.46,24.7,		 
		 <%}%>   
	        	]
	    },{
	    	name: '90th',
	    	color: "orange",
	        data: [
<%--  	        	<%for(Client client:weightmasterlist) {%>
 					<%=client.getOwerweight()%>,
 		 		<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Weight-for-age BOYS' */
5.22,6.45,7.34,8.03,8.59,9.07,9.48,9.84,10.17,10.48,10.76,11.04,11.3,11.56,11.81,12.05,12.3,12.54,12.78,13.02,13.26,13.5,13.74,13.98,14.22,14.45,14.69,14.92,15.15,15.37,15.59,15.81,16.02,16.23,16.44,16.65,16.86,17.06,17.27,17.47,17.68,17.89,18.09,18.3,18.51,18.71,18.92,19.13,19.34,19.55,19.76,19.97,20.18,20.39,20.6,20.81,21.03,21.24,21.45,21.66,21.87,22.07,

		 <%}else{%> 								/* 'Weight-for-age Girls' */
4.92,5.99,6.81,7.48,8.03,8.49,8.89,9.26,9.58,9.89,10.17,10.44,10.71,10.96,11.21,11.46,11.7,11.95,12.19,12.43,12.68,12.92,13.16,13.41,13.66,13.9,14.15,14.4,14.64,14.88,15.12,15.35,15.59,15.82,16.05,16.29,16.52,16.76,17,17.23,17.47,17.71,17.95,18.18,18.42,18.66,18.9,19.13,19.37,19.61,19.84,20.08,20.32,20.56,20.79,21.03,21.27,21.5,21.73,21.97,22.2,22.41,
		 
		 <%}%>   
	        ]
	    },{
	    	name: '85th',
	    	color: "green",
	        data: [
<%--  	        	<%for(Client client:weightmasterlist) {%>
 					<%=client.getOwerweight()%>,
 		 		<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Weight-for-age BOYS' */
5.06,6.26,7.13,7.81,8.36,8.83,9.23,9.59,9.91,10.2,10.48,10.75,11,11.25,11.49,11.73,11.97,12.2,12.44,12.67,12.9,13.13,13.36,13.59,13.82,14.05,14.28,14.5,14.72,14.93,15.14,15.35,15.56,15.76,15.96,16.16,16.36,16.56,16.75,16.95,17.15,17.34,17.54,17.74,17.94,18.14,18.33,18.53,18.73,18.93,19.13,19.33,19.53,19.73,19.94,20.14,20.34,20.54,20.74,20.94,21.14,21.33,

		 <%}else{%> 								/* 'Weight-for-age Girls' */
4.76,5.81,6.61,7.25,7.79,8.24,8.63,8.98,9.3,9.59,9.86,10.13,10.38,10.63,10.87,11.11,11.34,11.58,11.82,12.05,12.28,12.52,12.75,12.99,13.23,13.47,13.71,13.94,14.18,14.41,14.64,14.86,15.09,15.31,15.53,15.76,15.98,16.21,16.43,16.66,16.88,17.11,17.34,17.56,17.79,18.01,18.24,18.46,18.68,18.91,19.13,19.36,19.58,19.81,20.03,20.25,20.48,20.7,20.92,21.14,21.36,21.55,
		 
		 <%}%>   
	        ]
	    },{
	    	name: '50th',
	    	color: "#00E676",
	        data: [
<%--  	        	<%for(Client client:weightmasterlist) {%>
 					<%=client.getNormal()%>,
 	 			<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Weight-for-age BOYS' */
4.41,5.51,6.32,6.95,7.46,7.89,8.25,8.57,8.86,9.12,9.37,9.6,9.83,10.04,10.26,10.47,10.67,10.88,11.08,11.28,11.48,11.68,11.88,12.08,12.27,12.47,12.66,12.85,13.03,13.22,13.39,13.57,13.74,13.91,14.08,14.25,14.42,14.58,14.75,14.91,15.08,15.24,15.41,15.57,15.74,15.9,16.07,16.23,16.39,16.56,16.72,16.88,17.05,17.21,17.38,17.54,17.7,17.86,18.03,18.19,18.35,18.5,

		 <%}else{%> 								/* 'Weight-for-age Girls' */
4.14,5.08,5.8,6.38,6.85,7.25,7.6,7.91,8.18,8.44,8.68,8.9,9.12,9.34,9.55,9.76,9.96,10.17,10.38,10.58,10.78,10.99,11.19,11.4,11.61,11.81,12.02,12.22,12.42,12.61,12.81,13,13.19,13.37,13.56,13.75,13.93,14.12,14.3,14.49,14.67,14.86,15.04,15.22,15.4,15.58,15.76,15.94,16.12,16.3,16.48,16.65,16.83,17.01,17.19,17.36,17.54,17.72,17.89,18.06,18.24,18.39,
		 
		 <%}%>   ]
	    },{
	    	name: '15th',
	    	color: "orange",
	        data: [
<%-- 	          	<%for(Client client:weightmasterlist) {%>
		 					<%=client.getThinness()%>,
		  				<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Weight-for-age BOYS' */
3.83,4.84,5.59,6.17,6.65,7.03,7.36,7.65,7.91,8.14,8.36,8.57,8.77,8.96,9.15,9.33,9.52,9.69,9.87,10.05,10.22,10.39,10.56,10.73,10.9,11.07,11.23,11.39,11.55,11.7,11.86,12,12.15,12.3,12.44,12.58,12.72,12.86,13,13.14,13.28,13.42,13.55,13.69,13.83,13.97,14.1,14.24,14.37,14.51,14.64,14.78,14.91,15.05,15.18,15.31,15.45,15.58,15.71,15.84,15.98,16.09,

	        <%}else{%>
	        	/* 'Weight-for-age Girls' */
3.58,4.43,5.08,5.61,6.04,6.39,6.7,6.98,7.22,7.44,7.65,7.85,8.05,8.23,8.42,8.61,8.79,8.97,9.15,9.33,9.51,9.69,9.87,10.05,10.23,10.41,10.59,10.76,10.93,11.1,11.27,11.43,11.59,11.75,11.91,12.06,12.22,12.37,12.52,12.68,12.83,12.98,13.13,13.28,13.42,13.57,13.71,13.86,14,14.15,14.29,14.43,14.58,14.72,14.86,15,15.14,15.29,15.42,15.56,15.7,15.82,
			<%}%> 
	        ]
	    },{
	    	name: '10th',
	    	color: "Blue",
	        data: [
<%-- 	  	      	<%for(Client client:weightmasterlist) {%>
		 					<%=client.getThinness()%>,
		  				<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Weight-for-age BOYS' */
3.7,4.69,5.43,6,6.46,6.84,7.17,7.45,7.7,7.93,8.14,8.34,8.54,8.72,8.91,9.08,9.26,9.43,9.6,9.77,9.94,10.11,10.27,10.44,10.6,10.76,10.92,11.07,11.22,11.37,11.52,11.66,11.8,11.94,12.08,12.22,12.35,12.48,12.62,12.75,12.89,13.02,13.15,13.28,13.42,13.55,13.68,13.81,13.94,14.07,14.2,14.33,14.45,14.58,14.71,14.84,14.96,15.09,15.22,15.34,15.47,15.58,
	        <%}else{%>   								/* 'Weight-for-age Girls' */
3.46,4.29,4.93,5.44,5.86,6.21,6.51,6.77,7.01,7.23,7.43,7.63,7.81,8,8.18,8.36,8.54,8.71,8.89,9.07,9.24,9.41,9.59,9.76,9.94,10.11,10.28,10.45,10.62,10.78,10.94,11.1,11.25,11.4,11.55,11.7,11.85,12,12.15,12.29,12.44,12.58,12.72,12.86,13,13.14,13.28,13.42,13.56,13.69,13.83,13.97,14.1,14.24,14.37,14.51,14.64,14.78,14.91,15.04,15.17,15.29,
			<%}%>
	        ]
	    },{
	    	name: '3rd',
	    	color: "#D32F2F",
	        data: [
<%--  	        	<%for(Client client:weightmasterlist) {%>
 					<%=client.getSeverthinness()%>,
 				<%} %> --%>
			
			<%if(clientgender.equals("Male")){%>		/* 'Weight-for-age BOYS' */
	       3.4,4.34,5.05,5.6,6.04,6.4,6.71,6.97,7.2,7.42,7.62,7.81,7.99,8.16,8.33,8.5,8.66,8.82,8.98,9.14,9.29,9.45,9.6,9.75,9.9,10.04,10.19,10.33,10.47,10.61,10.74,10.87,11,11.12,11.25,11.37,11.5,11.62,11.74,11.86,11.98,12.1,12.22,12.34,12.46,12.58,12.7,12.82,12.93,13.05,13.17,13.28,13.4,13.51,13.62,13.74,13.85,13.96,14.08,14.19,14.3,14.4,
	        <%}else{%>
	        	/* 'Weight-for-age Girls' */
			3.17,3.96,4.57,5.05,5.45,5.78,6.06,6.31,6.53,6.73,6.92,7.11,7.28,7.45,7.62,7.79,7.96,8.12,8.29,8.45,8.61,8.78,8.94,9.1,9.26,9.42,9.58,9.74,9.89,10.04,10.19,10.33,10.47,10.61,10.75,10.89,11.02,11.15,11.29,11.42,11.55,11.68,11.8,11.93,12.05,12.18,12.3,12.42,12.54,12.67,12.79,12.91,13.03,13.15,13.27,13.39,13.51,13.63,13.74,13.86,13.98,14.08,
			<%}%>
	        ]
	    },{
	        name: 'Actual Growth',
	        color: "#212121",
	        data: [
	        	<%for(Client client:clientgrowthweightlist) {%>
		        	    <%=client.getWeightdata()%>,
	        	<%}%>
		        ]
	    }
	    ]
	});
</script>

<script>
	//Created by Abhi graph 1 for KPI (bmimasterlist)
	Highcharts.chart('indicator2', {
	chart: {
			type: 'spline'
			},
    title: {
    		<%if(clientgender.equals("Male")){%>
    			text: 'BMI-for-age BOYS'
    		<%}else{%>
    			text: 'BMI-for-age GIRLS'
			<%}%>
	    },
	   
	    xAxis: {
	    	min: 0,
<%-- 	        categories: [<%for(Client client:bmimasterlist) {%>
			/* 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'  */
			<%=client.getMonth()%>,
		 <%} %> ], --%>
		    title: {
	            text: 'Month'
	        }
	    },
	    yAxis: {
	        title: {
	            text: 'BMI (kg/cm*cm)'
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true
	            },
	            enableMouseTracking: true
	        },
	        series: {
	            marker: {
	                enabled: true
	            }
	        }
	    },
	    series: [{
	        name: 'P97',
	        color: "red",
	        data: [		
<%--  	        		<%for(Client client:bmimasterlist) {%>
 						<%=client.getObesity()%>,
 				 	<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'BMI-for-age BOYS' */
16.13,17.53,19.12,19.79,20.09,20.24,20.29,20.28,20.2,20.09,19.95,19.8,19.66,19.51,19.37,19.23,19.1,18.98,18.87,18.76,18.67,18.58,18.5,18.43,18.37,18.65,18.6,18.55,18.5,18.46,18.41,18.37,18.33,18.29,18.26,18.22,18.19,18.16,18.13,18.11,18.08,18.06,18.04,18.03,18.01,18,18,17.99,17.99,17.98,17.98,17.98,17.99,17.99,18,18,18.01,18.02,18.03,18.04,18.06,18.07,18.09,

	        <%}else{%> 									/* 'BMI-for-age Girls' */
15.89,17.24,18.7,19.41,19.79,19.99,20.08,20.08,20.01,19.89,19.75,19.59,19.43,19.28,19.14,19,18.88,18.77,18.66,18.57,18.49,18.42,18.35,18.3,18.26,18.51,18.48,18.44,18.41,18.38,18.35,18.33,18.3,18.28,18.26,18.24,18.23,18.22,18.21,18.21,18.21,18.21,18.22,18.23,18.24,18.25,18.26,18.27,18.29,18.3,18.32,18.34,18.36,18.38,18.4,18.42,18.45,18.47,18.49,18.51,18.54,18.56,18.57,
	        	
	       	<%}%> 	
	        	]
	    },{
	        name: 'P90',
	        color: "orange",
	        data: [		
<%--  	        		<%for(Client client:bmimasterlist) {%>
 						<%=client.getObesity()%>,
 				 	<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'BMI-for-age BOYS' */
15.19,16.64,18.17,18.81,19.09,19.24,19.29,19.28,19.21,19.1,18.97,18.83,18.69,18.55,18.41,18.28,18.16,18.04,17.94,17.84,17.74,17.66,17.59,17.52,17.46,17.73,17.69,17.64,17.6,17.56,17.52,17.48,17.45,17.41,17.38,17.34,17.31,17.28,17.26,17.23,17.2,17.18,17.16,17.14,17.13,17.11,17.1,17.09,17.08,17.07,17.07,17.06,17.06,17.05,17.05,17.05,17.05,17.05,17.05,17.05,17.06,17.06,17.07,

	        <%}else{%> 									/* 'BMI-for-age Girls' */
15.03,16.33,17.7,18.37,18.73,18.92,19,19,18.93,18.82,18.68,18.53,18.38,18.24,18.1,17.98,17.86,17.75,17.65,17.57,17.49,17.42,17.36,17.31,17.27,17.52,17.49,17.46,17.43,17.4,17.38,17.35,17.32,17.3,17.28,17.26,17.25,17.23,17.22,17.22,17.21,17.21,17.21,17.21,17.21,17.21,17.22,17.22,17.23,17.24,17.25,17.26,17.27,17.28,17.29,17.31,17.32,17.34,17.36,17.37,17.38,17.4,17.41,
	        	
	       	<%}%> 	
	        	]
	    },{
	        name: 'P85',
	        color: "pink",
	        data: [
<%--  	        	<%for(Client client:bmimasterlist) {%>
 					<%=client.getOwerweight()%>,
 		 		<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'BMI-for-age BOYS' */
14.83,16.28,17.79,18.42,18.7,18.85,18.9,18.89,18.82,18.71,18.58,18.45,18.31,18.17,18.04,17.91,17.79,17.68,17.57,17.48,17.39,17.31,17.24,17.17,17.11,17.38,17.33,17.29,17.25,17.21,17.18,17.14,17.1,17.07,17.03,17,16.97,16.94,16.91,16.89,16.86,16.84,16.82,16.8,16.78,16.77,16.75,16.74,16.73,16.72,16.71,16.7,16.7,16.69,16.69,16.68,16.68,16.68,16.67,16.67,16.67,16.67,16.68,
	        <%}else{%> 									/* 'BMI-for-age Girls' */
14.69,15.97,17.31,17.96,18.31,18.5,18.58,18.57,18.51,18.4,18.27,18.12,17.98,17.84,17.7,17.58,17.46,17.36,17.26,17.18,17.1,17.03,16.98,16.93,16.89,17.14,17.11,17.08,17.05,17.03,17,16.97,16.95,16.92,16.9,16.88,16.87,16.85,16.84,16.83,16.83,16.82,16.82,16.82,16.82,16.82,16.82,16.82,16.82,16.83,16.83,16.84,16.85,16.86,16.87,16.88,16.9,16.91,16.92,16.93,16.95,16.96,16.97,	        	
	       	<%}%> 
	        ]
	    },{
	        name: 'P50',
	        color: "#00E676",
	        data: [
<%--  	        	<%for(Client client:bmimasterlist) {%>
 					<%=client.getNormal()%>,
 	 			<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'BMI-for-age BOYS' */
13.41,14.84,16.26,16.87,17.14,17.28,17.34,17.33,17.28,17.18,17.07,16.95,16.82,16.7,16.58,16.47,16.36,16.26,16.17,16.08,16,15.93,15.86,15.81,15.75,16,15.96,15.92,15.88,15.85,15.81,15.78,15.74,15.71,15.68,15.65,15.62,15.59,15.56,15.53,15.5,15.48,15.46,15.44,15.41,15.4,15.38,15.36,15.34,15.33,15.31,15.3,15.29,15.27,15.26,15.25,15.24,15.23,15.22,15.21,15.2,15.19,15.18,

	        <%}else{%> 									/* 'BMI-for-age Girls' */
13.34,14.48,15.72,16.33,16.65,16.83,16.9,16.91,16.85,16.76,16.64,16.51,16.38,16.26,16.14,16.03,15.93,15.83,15.75,15.67,15.61,15.55,15.5,15.45,15.42,15.67,15.64,15.62,15.59,15.56,15.54,15.52,15.49,15.47,15.45,15.43,15.41,15.39,15.37,15.36,15.34,15.33,15.32,15.31,15.3,15.29,15.28,15.27,15.26,15.26,15.26,15.25,15.25,15.25,15.25,15.25,15.26,15.26,15.26,15.27,15.27,15.28,15.28,

	       	<%}%> 
	        ]
	    },{
	        name: 'P15',
	        color: "orange",
	        data: [
<%--  	        	<%for(Client client:bmimasterlist) {%>
 					<%=client.getThinness()%>,
  				<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'BMI-for-age BOYS' */
13.5,14.86,15.45,15.72,15.87,15.93,15.94,15.89,15.82,15.72,15.61,15.5,15.4,15.29,15.19,15.1,15.01,14.93,14.85,14.78,14.71,14.66,14.61,14.56,14.78,14.75,14.71,14.67,14.63,14.6,14.56,14.53,14.5,14.46,14.43,14.4,14.37,14.34,14.31,14.28,14.26,14.23,14.21,14.19,14.17,14.15,14.13,14.11,14.09,14.07,14.06,14.04,14.03,14.01,14,13.98,13.97,13.95,13.94,13.93,13.92,13.91,
	        <%}else{%> 									/* 'BMI-for-age Girls' */
12.12,13.09,14.25,14.83,15.14,15.32,15.4,15.42,15.38,15.3,15.2,15.09,14.98,14.87,14.77,14.67,14.58,14.5,14.43,14.36,14.3,14.25,14.21,14.17,14.14,14.39,14.36,14.34,14.31,14.29,14.27,14.25,14.22,14.2,14.18,14.16,14.14,14.12,14.1,14.08,14.06,14.04,14.02,14,13.98,13.97,13.95,13.94,13.92,13.91,13.9,13.89,13.88,13.87,13.86,13.86,13.85,13.85,13.84,13.84,13.84,13.84,13.84,
	        	
	       	<%}%> 
	        ]
	    },{
	        name: 'P3',
	        color: "#D32F2F",
	        data: [
<%--  	        	<%for(Client client:bmimasterlist) {%>
 					<%=client.getSeverthinness()%>,
 				<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'BMI-for-age BOYS' */
11.25,12.47,13.79,14.37,14.65,14.81,14.88,14.9,14.87,14.81,14.72,14.63,14.53,14.44,14.35,14.26,14.17,14.09,14.02,13.95,13.89,13.83,13.78,13.74,13.7,13.9,13.86,13.82,13.78,13.75,13.71,13.67,13.64,13.6,13.57,13.53,13.5,13.47,13.44,13.41,13.38,13.35,13.33,13.31,13.28,13.26,13.24,13.22,13.2,13.18,13.16,13.15,13.13,13.12,13.1,13.09,13.07,13.06,13.04,13.03,13.02,13.01,13,

	        <%}else{%> 									/* 'BMI-for-age Girls' */
11.21,12.03,13.14,13.7,14.01,14.2,14.29,14.31,14.29,14.23,14.14,14.05,13.95,13.86,13.77,13.68,13.6,13.53,13.47,13.41,13.36,13.31,13.28,13.25,13.22,13.46,13.43,13.41,13.39,13.37,13.35,13.33,13.31,13.28,13.26,13.24,13.22,13.19,13.17,13.15,13.13,13.11,13.08,13.06,13.04,13.02,13,12.98,12.96,12.94,12.93,12.91,12.9,12.88,12.87,12.86,12.85,12.84,12.84,12.83,12.83,12.82,12.82,
	        	
	       	<%}%> 
	        ]
	    },{
	        name: 'Actual Growth',
	        color: "#212121",
	        data: [
 	        	<%for(Client client:clientgrowthbmilist) {%>
		        	    <%=client.getBmidata()%>,
	        	<%} %>
	        ]
	    }
	    ]
	});
</script>

<script>
	//Created by Abhi graph 1 for KPI (headcircumferncemasterlist)
	Highcharts.chart('indicator3', {
	chart: {
			type: 'spline'
			},
    title: {
    		<%if(clientgender.equals("Male")){%>
    			text: 'Head Circumference-for-age BOYS'
    		<%}else{%>
    			text: 'Head Circumference-for-age GIRLS'
			<%}%>
	    },
	    xAxis: {
	    	min: 0,
<%-- 	        categories: [<%for(Client client:headcircumferncemasterlist) {%>
			/* 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'  */
			<%=client.getMonth()%>,
		 <%} %> ], --%>
	    title: {
            text: 'Month'
        }
	    },
	    yAxis: {
	        title: {
	            text: 'Head circumfernce (cm)'
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true
	            },
	            enableMouseTracking: true
	        },
	        series: {
	            marker: {
	                enabled: true
	            }
	        }
	    },
	    series: [{
	        name: '97th',
	        color: "#D32F2F",
	        data: [
<%--  	        		<%for(Client client:headcircumferncemasterlist) {%>
 						<%=client.getObesity()%>,
 				 	<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Head circumference-for-age BOYS' */
39.36,41.24,42.64,43.78,44.74,45.54,46.22,46.79,47.29,47.72,48.09,48.42,48.71,48.97,49.21,49.43,49.63,49.81,49.99,50.16,50.32,50.47,50.62,50.76,50.9,51.03,51.15,51.28,51.39,51.5,51.61,51.71,51.81,51.91,52,52.09,52.17,52.25,52.33,52.4,52.47,52.54,52.61,52.68,52.74,52.8,52.86,52.92,52.98,53.03,53.08,53.14,53.19,53.24,53.28,53.33,53.38,53.42,53.47,53.51,53.55,53.59,
	        <%}else{%> 									/* 'Head circumference-for-age Girls' */
38.65,40.44,41.78,42.87,43.79,44.57,45.22,45.79,46.27,46.69,47.06,47.39,47.68,47.94,48.18,48.4,48.6,48.79,48.97,49.13,49.3,49.45,49.61,49.75,49.89,50.03,50.16,50.29,50.41,50.53,50.64,50.74,50.84,50.94,51.03,51.12,51.2,51.29,51.37,51.44,51.51,51.58,51.65,51.72,51.78,51.84,51.9,51.96,52.02,52.07,52.13,52.18,52.23,52.28,52.33,52.38,52.42,52.47,52.52,52.56,52.6,52.64,	        	
	       	<%}%> 
	        	]
	    },{
	        name: '90th',
	        color: "red",
	        data: [
<%--  	        	<%for(Client client:headcircumferncemasterlist) {%>
 					<%=client.getOwerweight()%>,
 		 		<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Head circumference-for-age BOYS' */
38.67,40.53,41.93,43.07,44.01,44.81,45.48,46.05,46.54,46.96,47.33,47.65,47.94,48.19,48.43,48.64,48.84,49.02,49.19,49.36,49.51,49.66,49.81,49.95,50.08,50.21,50.33,50.45,50.56,50.67,50.78,50.88,50.97,51.06,51.15,51.24,51.32,51.4,51.47,51.54,51.61,51.68,51.75,51.81,51.87,51.93,51.99,52.05,52.1,52.15,52.2,52.25,52.3,52.35,52.4,52.44,52.49,52.53,52.57,52.62,52.66,52.69,
	        <%}else{%> 									/* 'Head circumference-for-age Girls' */
37.95,39.71,41.03,42.11,43.02,43.79,44.44,44.99,45.47,45.89,46.25,46.58,46.86,47.12,47.36,47.57,47.77,47.96,48.14,48.3,48.47,48.62,48.77,48.92,49.06,49.19,49.32,49.45,49.57,49.68,49.79,49.9,50,50.09,50.19,50.27,50.36,50.44,50.52,50.59,50.67,50.74,50.8,50.87,50.93,50.99,51.05,51.11,51.17,51.22,51.28,51.33,51.38,51.43,51.48,51.52,51.57,51.62,51.66,51.71,51.75,51.79,	        	
	       	<%}%> 
	        ]
	    },{
	        name: '85th',
	        color: "pink",
	        data: [
<%--  	        	<%for(Client client:headcircumferncemasterlist) {%>
 					<%=client.getOwerweight()%>,
 		 		<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Head circumference-for-age BOYS' */
38.38,40.25,41.64,42.78,43.72,44.51,45.18,45.74,46.23,46.65,47.02,47.34,47.62,47.88,48.11,48.32,48.51,48.69,48.87,49.03,49.18,49.33,49.47,49.61,49.74,49.87,49.99,50.11,50.22,50.33,50.43,50.53,50.63,50.72,50.8,50.89,50.97,51.05,51.12,51.19,51.26,51.33,51.39,51.46,51.52,51.58,51.63,51.69,51.74,51.79,51.84,51.89,51.94,51.99,52.03,52.08,52.12,52.17,52.21,52.25,52.29,52.33,
	        <%}else{%> 									/* 'Head circumference-for-age Girls' */
37.66,39.42,40.73,41.8,42.71,43.47,44.11,44.67,45.15,45.56,45.92,46.24,46.53,46.79,47.02,47.24,47.44,47.62,47.8,47.96,48.13,48.28,48.43,48.57,48.71,48.85,48.98,49.11,49.23,49.34,49.45,49.55,49.65,49.75,49.84,49.93,50.01,50.09,50.17,50.25,50.32,50.39,50.46,50.52,50.58,50.65,50.7,50.76,50.82,50.87,50.93,50.98,51.03,51.08,51.13,51.18,51.22,51.27,51.31,51.36,51.4,51.44,
	       	<%}%> 
	        ]
	    },{
	        name: '50th',
	        color: "#00E676",
	        data: [
<%--  	        	<%for(Client client:headcircumferncemasterlist) {%>
 					<%=client.getNormal()%>,
 	 			<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Head circumference-for-age BOYS' */
37.17,39.03,40.42,41.54,42.47,43.25,43.9,44.45,44.93,45.34,45.69,46.01,46.28,46.53,46.75,46.96,47.15,47.32,47.49,47.64,47.79,47.93,48.07,48.2,48.33,48.45,48.57,48.68,48.79,48.89,48.99,49.08,49.17,49.26,49.34,49.42,49.49,49.57,49.64,49.71,49.77,49.84,49.9,49.96,50.01,50.07,50.12,50.17,50.23,50.27,50.32,50.37,50.41,50.46,50.5,50.54,50.58,50.62,50.66,50.7,50.74,50.78,
	        <%}else{%> 									/* 'Head circumference-for-age Girls' */
36.45,38.16,39.45,40.5,41.37,42.12,42.75,43.29,43.76,44.17,44.52,44.84,45.12,45.37,45.6,45.81,46.01,46.19,46.36,46.53,46.69,46.84,46.99,47.13,47.27,47.4,47.53,47.65,47.77,47.88,47.99,48.09,48.19,48.29,48.38,48.46,48.55,48.63,48.7,48.78,48.85,48.92,48.99,49.05,49.11,49.18,49.23,49.29,49.35,49.4,49.45,49.51,49.56,49.61,49.66,49.7,49.75,49.8,49.84,49.88,49.93,49.97,     	
	       	<%}%> 
	        ]
	    },{
	        name: '15th',
	        color: "orange",
	        data: [
<%--  	        	<%for(Client client:headcircumferncemasterlist) {%>
 					<%=client.getThinness()%>,
  				<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Head circumference-for-age BOYS' */
35.96,37.82,39.2,40.3,41.22,41.98,42.62,43.17,43.63,44.03,44.37,44.68,44.94,45.18,45.4,45.6,45.78,45.95,46.11,46.26,46.4,46.54,46.67,46.79,46.91,47.03,47.14,47.25,47.35,47.45,47.54,47.63,47.72,47.8,47.87,47.95,48.02,48.09,48.16,48.22,48.28,48.34,48.4,48.46,48.51,48.56,48.61,48.66,48.71,48.76,48.8,48.84,48.89,48.93,48.97,49.01,49.05,49.08,49.12,49.16,49.19,49.22,
	        <%}else{%> 									/* 'Head circumference-for-age Girls' */
35.23,36.91,38.16,39.19,40.04,40.77,41.39,41.92,42.37,42.77,43.12,43.43,43.7,43.95,44.18,44.39,44.58,44.76,44.93,45.09,45.25,45.4,45.54,45.68,45.82,45.95,46.08,46.2,46.32,46.43,46.53,46.63,46.73,46.82,46.91,47,47.08,47.16,47.24,47.31,47.38,47.45,47.52,47.58,47.64,47.7,47.76,47.82,47.88,47.93,47.98,48.03,48.08,48.13,48.18,48.23,48.28,48.32,48.37,48.41,48.45,48.49,
	        	
	       	<%}%> 
	        ]
	    },{
	        name: '10th',
	        color: "Blue",
	        data: [
<%--  	        	<%for(Client client:headcircumferncemasterlist) {%>
 					<%=client.getThinness()%>,
  				<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Head circumference-for-age BOYS' */
35.67,37.53,38.91,40.01,40.92,41.68,42.32,42.86,43.32,43.72,44.06,44.36,44.63,44.87,45.08,45.28,45.46,45.62,45.78,45.93,46.07,46.21,46.34,46.46,46.58,46.69,46.8,46.91,47.01,47.11,47.2,47.29,47.37,47.45,47.53,47.6,47.67,47.74,47.81,47.87,47.93,47.99,48.05,48.1,48.15,48.21,48.26,48.3,48.35,48.4,48.44,48.48,48.52,48.57,48.6,48.64,48.68,48.72,48.75,48.79,48.83,48.86,

	        <%}else{%> 									/* 'Head circumference-for-age Girls' */
34.95,36.61,37.86,38.88,39.73,40.45,41.07,41.59,42.05,42.44,42.79,43.1,43.37,43.62,43.84,44.05,44.24,44.42,44.59,44.75,44.91,45.06,45.2,45.34,45.48,45.61,45.73,45.86,45.97,46.08,46.19,46.29,46.39,46.48,46.57,46.65,46.74,46.81,46.89,46.96,47.04,47.1,47.17,47.23,47.3,47.36,47.42,47.47,47.53,47.58,47.63,47.69,47.74,47.79,47.83,47.88,47.93,47.97,48.02,48.06,48.1,48.14,
	        	
	       	<%}%> 
	        ]
	    },{
	        name: '3rd',
	        color: "#D32F2F",
	        data: [
<%--  	        	<%for(Client client:headcircumferncemasterlist) {%>
 					<%=client.getSeverthinness()%>,
 				<%} %> --%>
			<%if(clientgender.equals("Male")){%>		/* 'Head circumference-for-age BOYS' */
34.97,36.83,38.2,39.3,40.2,40.95,41.58,42.12,42.57,42.96,43.3,43.59,43.85,44.09,44.3,44.49,44.67,44.83,44.98,45.13,45.27,45.4,45.53,45.65,45.76,45.87,45.98,46.08,46.18,46.27,46.36,46.45,46.53,46.61,46.68,46.75,46.82,46.89,46.95,47.01,47.07,47.13,47.18,47.23,47.29,47.33,47.38,47.43,47.47,47.52,47.56,47.6,47.64,47.68,47.72,47.75,47.79,47.83,47.86,47.9,47.93,47.96,

	        <%}else{%> 									/* 'Head circumference-for-age Girls' */
34.24,35.89,37.11,38.12,38.96,39.67,40.28,40.8,41.25,41.64,41.98,42.28,42.55,42.8,43.02,43.23,43.42,43.59,43.76,43.92,44.08,44.22,44.37,44.51,44.64,44.77,44.9,45.02,45.13,45.24,45.35,45.45,45.54,45.63,45.72,45.81,45.89,45.97,46.04,46.12,46.19,46.26,46.32,46.39,46.45,46.51,46.57,46.62,46.68,46.73,46.78,46.83,46.89,46.93,46.98,47.03,47.08,47.12,47.16,47.21,47.25,47.29,
	        	
	       	<%}%> 
	        ]
	    },{
	        name: 'Actual Growth',
	        color: "#212121",
	        data: [			
	        	<%for(Client client:clientgrowthheadlist) {%>
		        	    <%= client.getHeadcircumferncedata()%>,
	        	<%}%>
	        ]
	    }
	    ]
	});
</script>

<%-- <div>
<h1>Height Data</h1>
<!-- Below section has been created temporarily to check fetched data -->
   	<%for(Client client:clientgrowthheightlist) {%>
        <% String i = client.getHeightdata(); %>
        <%if(i.equals("0")){%>
        	null, 
        <%}else{%>
    	    <%=i%>,
    	<%} %>
   	<%}%>
   	
<h1>Weight Data</h1>
	<%for(Client client:clientgrowthweightlist) {%>
         <% String j = client.getWeightdata(); %>
        <%if(j.equals("0")){%>
        	null,
        <%}else{%>
    	    <%=j%>,
    	<%} %>
   	<%}%>
   	
<h1>BMI Data</h1>
   	<%for(Client client:clientgrowthbmilist) {%>
        <% String k = client.getBmidata(); %>
        <%if(k.equals("0")){%>
        	null,
        <%}else{%>
    	    <%=k%>,
    	<%} %>
   	<%}%>
   	
<h1>Head Circumference Data</h1>
   	<%for(Client client:clientgrowthheadlist) {%>
        <% String l = client.getHeadcircumferncedata(); %>
        <%if(l.equals("0")){%>
        	null,
        <%}else{%>
    	    <%=l%>,
    	<%} %>
   	<%}%>
</div> --%>