<%@page import="com.apm.Report.eu.blogic.jdbc.JDBCSummaryReportDAO"%>
<%@page import="com.apm.Report.eu.bi.SummaryReportDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.jopendocument.dom.template.statements.ForEach"%>
<%@page import="com.apm.Report.eu.entity.SummaryReport"%>
<%@page import="java.util.ArrayList"%>
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

        $(".tablexls").table2excel({
					exclude: ".noExl",
					name: "Payment List",
					filename: "departmentwisepaymentsummaryreport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
</script>
<div class="">
	<div class="print-visible hidden-md hidden-lg printableArea letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>

							<div class="row details ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 printableArea">

										<h4>Department Wise Payment Summary Report</h4>

									</div>
								</div>
								<s:form action="departmentwisesummaryrevenueReport" theme="simple">
									
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
										<div class="form-inline">
											<%-- <div class="form-group" style="width:7%;">
												 <s:select cssClass="form-control" list="#{'01':'January', '02':'February', '03':'March', '04':'April', '05':'May','06':'June', '07':'July', '08':'August', '09':'September', '10':'October', '11':'November', '12':'December'}" name="fromDate" />
											</div>  --%>
											<div class="form-group hidden-print" style="width:7%;">
												 <s:select cssClass="form-control" list="#{'2014':'2014','2015':'2015','2016':'2016','2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020', '2021':'2021','2022':'2022', '2023':'2023', '2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028'}" name="toDate" />
											</div> 
											<div class="form-group hidden-print" style="width:9%;">
												<s:select cssClass="form-control" list="#{ '':'Invoice Type', 'IPD':'IPD', 'OPD':'OPD'}" name="ipdopd" />
												
											</div>
											<div class="form-group hidden-print" style="width:9%;">
												<s:select cssClass="form-control"
													list="#{'Select Month For Grapth':'0','1':'Jan', '2':'Feb', '3':'March', '4':'April' , '5':'May', '6':'June', '7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"
													theme="simple" id="month" name="month"  />
												
											</div>
											<div class="form-group hidden-print">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right hidden-print">
												<a href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right hidden-print">
												<a href="#" class="btn btn-warning" onclick="printpage()">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
				<table class="my-table tablexls"  style="width: 100%;font-size: 8px"  >
						<thead>
                                                        <tr style="background-color: #a2c4e6; font-weight: bold;">
                                                                <td style="font-weight: bold;">Sr. no</td>
                                                                <td style="font-weight: bold;">Department</td>
                                                                <td style="font-weight: bold;">January</td>
                                                                <td style="font-weight: bold;">February</td>
                                                                <td style="font-weight: bold;">March</td>
                                                                <td style="font-weight: bold;">April</td>
                                                                <td style="font-weight: bold;">May</td>
                                                                <td style="font-weight: bold;">June</td>
                                                                <td style="font-weight: bold;">July</td>
                                                                <td style="font-weight: bold;">August</td>
                                                                <td style="font-weight: bold;">September</td>
                                                                <td style="font-weight: bold;">October</td>
                                                                <td style="font-weight: bold;">November</td>
                                                                <td style="font-weight: bold;">December</td>
                                                                <!-- <td style="font-weight: bold;">Patients</td> -->
                                                        </tr>
                                                </thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="departmentwisereportlist">
					   		<tr>
					     		<td><%=++i %></td>
					     		<!--  jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec-->
					     		<td><a href="#" onclick="openBlankPopup('paymentagainstinvoice1ChargesRpt?Masterchargetype=<s:property value="Masterchargetype" />')"><s:property value="Masterchargetype"/></a></td>
					     		     <s:iterator value="Monthtotallist">
								         <td><s:property value="Totalcharge"/></td>
								     </s:iterator>
								
								<%-- <td><s:property value="count"/></td> --%>
							</tr>
							
					   </s:iterator>
					   <tr style="background-color:cyan">
							<td></td>
							<td><b>Total</b></td>
							<td><s:property value="January"/></td>
							<td><s:property value="Feburary"/></td>
							<td><s:property value="March"/></td>
							<td><s:property value="April"/></td>
							<td><s:property value="May2"/></td>
							<td><s:property value="Juneee1"/></td>
							<td><s:property value="July1"/></td>
							<td><s:property value="August1"/></td>
							<td><s:property value="September"/></td>
							<td><s:property value="October"/></td>
							<td><s:property value="November"/></td>
							<td><s:property value="December"/></td>
							<%-- <td><s:property value="totalpatient"/></td> --%>
							</tr>
					</tbody>
			 </table>
		</div>
		
</div>
<div class="col-lg-12 col-xs-12 col-md-12" >
			<div id="indicator">
				
			</div>
		</div>

<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
		<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>

		<script src="mis/kpiplugin/js/jquery.scrollto.js"></script>   

<%
	String fromdate = (String) session.getAttribute("deptwise_graphical_fromdate");
	String todate = (String) session.getAttribute("deptwise_graphical_todate");
	String month = (String) session.getAttribute("deptwise_graphical_month");
	String isopdipd = (String) session.getAttribute("deptwise_graphical_ipdopd");
	String year = (String) session.getAttribute("deptwise_graphical_newtodate");
	String monthname ="Jan";
	ArrayList<String> arrayList = new ArrayList<String>();
	ArrayList<String> valueList = new ArrayList<String>();
%>
			<%
			try {
				
				Connection connection = null;
				connection = Connection_provider.getconnection();
				SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
				StringBuffer buffer = new StringBuffer();
				buffer.append("select sum(debit),apm_discipline.id,apm_discipline.discipline from apm_charges_invoice ");
				buffer.append("inner join apm_user on apm_user.id = apm_charges_invoice.practid ");
				buffer.append("inner join apm_discipline on apm_discipline.id =apm_user.discription ");
				buffer.append("where date between '"+fromdate+"' and '"+todate+"' ");
				buffer.append("group by apm_discipline.discipline  ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String valudata="0";
					String newfromdate="";
					String newtodate="";
					if(Integer.parseInt(month)==1){
						newfromdate = year+"-01-01";
						newtodate = year+"-01-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate, newtodate, rs.getString(2), isopdipd);
						 monthname ="Jan";
					}else if(Integer.parseInt(month)==2){
						newfromdate = year+"-02-01";
						newtodate = year+"-02-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						 monthname ="Feb";
					}else if(Integer.parseInt(month)==3){
						newfromdate = year+"-03-01";
						newtodate = year+"-03-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						 monthname ="March";
					}else if(Integer.parseInt(month)==4){
						newfromdate = year+"-04-01";
						newtodate = year+"-04-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						 monthname ="Apr";
					}else if(Integer.parseInt(month)==5){
						newfromdate = year+"-05-01";
						newtodate = year+"-05-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						 monthname ="May";
					}else if(Integer.parseInt(month)==6){
						newfromdate = year+"-06-01";
						newtodate = year+"-06-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						 monthname ="Jun";
					}else if(Integer.parseInt(month)==7){
						newfromdate = year+"-07-01";
						newtodate = year+"-07-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						 monthname ="Jul";
					}else if(Integer.parseInt(month)==8){
						newfromdate = year+"-08-01";
						newtodate = year+"-08-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						monthname ="Aug";
					}else if(Integer.parseInt(month)==9){
						newfromdate = year+"-09-01";
						newtodate = year+"-09-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						monthname ="Sep";
					}else if(Integer.parseInt(month)==10){
						newfromdate = year+"-10-01";
						newtodate = year+"-10-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						monthname ="Oct";
					}else if(Integer.parseInt(month)==11){
						newfromdate = year+"-11-01";
						newtodate = year+"-11-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						monthname ="Nov";
					}else if(Integer.parseInt(month)==12){
						newfromdate = year+"-12-01";
						newtodate = year+"-12-31";
						valudata = summaryReportDAO.getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
						monthname ="Dec";
					}	
					String departname = rs.getString(3);
					arrayList.add(departname);
					valueList.add(valudata);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    		%>
    		<%
    			String datavaluess="";
    			String value_result="";
    		%>
    		<% 
    				int countaa=1;
	    			for (String sr2 : arrayList) {
		    			if(datavaluess.equals("")){
		    				//datavaluess = sr2+",";
		    				datavaluess = countaa+",";
		    			}else{
		    				//datavaluess = datavaluess+""+sr2+",";
		    				datavaluess = datavaluess+""+countaa+",";
		    			}
		    			countaa++;
	    			}
	    	%>
	    	<% 
	    			for (String sr2 : valueList) {
		    			if(value_result.equals("")){
		    				value_result = sr2;
		    			}else{
		    				value_result = value_result+","+sr2;
		    			}
	    			}
	    	%>
<%-- <script id="kipqiscript">
	Highcharts.chart('indicator', {
	chart: {
			type: 'line'
			},
    title: {
	        text: 'Department wise Revenue Report'
	    },
	    xAxis: {
	    	categories: [
	    		<%=datavaluess%>
	      ]
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
	   
	    
	     series: [
	    {
	        name: '<%=monthname%>',
	        data: [<%=value_result%>]
	    }]
	    
	   
	});
</script> --%>

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



	