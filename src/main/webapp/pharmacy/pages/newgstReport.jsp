<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>



 <style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style>
<script>


 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "newgst",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
	 document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false); 
});

</script>
	 <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
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

										<h4>GST Sale Report </h4>

									</div>

</div>	
<%-- <div class="hidden-print">
											<ul class="breadcrumb">
												&nbsp;
												<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
												<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
													<%if(breadcrumbs.isIscurrent()){ %>
														<li><%=breadcrumbs.getShowingname()%></li>
													<%}else{ %>
														<%if(breadcrumbs.getOn()){ %>
															<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
														<%}else{ %>
															<li><%=breadcrumbs.getName()%></li>
														<%} %>
													<%} %>
													
												<%} %>
											</ul>
										</div> --%>
<h4 class="print-visible hidden-md hidden-lg">Payment Mode: <b><s:if test="paymode==''">All</s:if><s:else><s:property value="paymode"/></s:else></b></h4>
<div class="">
<s:form action="newgstReportPharmacy" theme="simple" id = "invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			
				
		
			
			
			
			<div class="form-group" style="width:15%;">
			<label>From Date</label>
				<s:textfield readonly="true" name="fromdate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			
			<div class="form-group" style="width:15%;">
			<label>To Date</label>
				<s:textfield readonly="true" name="todate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="form-group" style="width:15%;">
			<label>Paymode</label>
				<s:select name="paymode" id="paymode" 
				list="#{'':'All','Card':'Card','Cash':'Cash','Credit':'Credit','NEFT':'NEFT','Cheque':'Cheque','Online Payment':'Online Payment'}"
				cssClass="form-control chosen-select" ></s:select>
			</div> 
			<div class="form-group" style="width:15%;">
			<label>Type</label>
				<s:select name="isreturn" id="isreturn" 
				list="#{'0':'Sale','1':'Return'}"
				cssClass="form-control chosen-select" ></s:select>
			</div> 
			<div class="form-group" style="width:15%;">
			<label>Report By</label>
				<s:select name="reportby" id="reportby" 
				list="#{'0':'Date','1':'Bill'}"
				cssClass="form-control chosen-select" ></s:select>
			</div> 
			<div class="form-group" >
			<br>
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
		
			
		
	</div>
	</div>
	</s:form>
</div>							
<div class="">
								<table class="my-table xlstable" style="width:100%">
								<tr bgcolor="#3c6ea0" style="color:white;">
								<td>Sr.</td>
								
								
								<td> Date</td>
								<s:if test="reportby==1"><td>Bill No.</td></s:if>
								
								<td>Amount(0%)</td>
								<td>0%</td>
								
								<td>MRP(5%)</td>
								<td>Amount(5%)</td>
								<td>2.5% </td>
								<td>2.5% </td>
								<td>MRP(%12)</td>
								<td>Amount(%12)</td>
								<td>6%</td>
								<td>6%</td>
								<td>MRP(%18)</td>
								<td>Amount(%18)</td>
								<td>9%</td>
								<td>9%</td>
								
								<td>MRP(%28)</td>
								<td>Amount(%28)</td>
								<td>14%</td>
								<td>14%</td>
								<td>NET Amount</td>
								<td>Gross Amount</td>
								<td>Total GST</td>
								<!-- <td>Payment Against Credit</td> -->
								</tr>
								<%int i=0; %>
								<s:iterator value="doctorreportList">
								<tr>
								<td><%=++i %></td>
								
						
								
								<td><s:property value="fromdate"/></td>
								<s:if test="reportby==1"><td><s:property value="billno"/></td></s:if>
								
								<%if(loginInfo.getClinicUserid().equals("aureus")){ %>
								<td><s:property value="zerosgst"/></td>
								<%}else{ %>
								<td><s:property value="zeropricemed"/></td>
								<%} %>
								<td><s:property value="zerocgst"/></td>
								
								<td><s:property value="fivepricemed"/></td>
								<td><s:property value="fivenomrp"/></td>
								<td><s:property value="fivecgst"/></td>
								<td><s:property value="fivecgst"/></td>
								<td><s:property value="tweelvepricemed"/></td>
								<td><s:property value="tweelvenomrp"/></td>
								<td><s:property value="tweelvecgst"/></td>
								<td><s:property value="tweelvecgst"/></td>
								<td><s:property value="eighteenpricemed"/></td>
								<td><s:property value="eighteennomrp"/></td>
								<td><s:property value="eighteencgst"/></td>
								<td><s:property value="eighteencgst"/></td>
								<td><s:property value="eighttwopricemed"/></td>
								<td><s:property value="eighttwonomrp"/></td>
								<td><s:property value="eighttwocgst"/></td>
								<td><s:property value="eighttwocgst"/></td>
								<td><s:property value="total"/></td>
								<td><%if(!loginInfo.getClinicUserid().equals("aureus")){ %> <s:property value="zeropricemed+fivenomrp+eighttwonomrp+eighteennomrp+tweelvenomrp"/><%}else{ %><s:property value="ttlgross"/><%} %></td>
								<td><%if(!loginInfo.getClinicUserid().equals("aureus")){ %><s:property value="total-(zeropricemed+fivenomrp+eighttwonomrp+eighteennomrp+tweelvenomrp)"/> <%}else{ %><s:property value="ttlgst"/><%} %> </td>
								<%-- <td><s:property value="todayagainstcredit"/></td> --%>
								</tr>
								
								</s:iterator>
								</table>
								
								
							
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
