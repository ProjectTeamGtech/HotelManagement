<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<style>
td{
border: 2px solid #4E7894 !important;
text-align: center;
}
.lok{
border: 2px solid #4E7894 !important; 
margin-top: 10px !important;
}
th{
border: 2px solid #4E7894 !important; 
text-align: center !important;
}
h4{
font-family: cursive;
}
.shublok{
background-color: #6699cc !important; 
color: white;
}

</style>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="report/js/report.js"></script>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "totalrevenue",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  
function settogenerate(){
	document.getElementById("refresh").value='1';
	document.getElementById("myform").submit();
}

function setyearly(){
	document.getElementById("yearly").value="1";
	
		document.getElementById("myform").submit();
	
	
}
function setMonthly(){
	document.getElementById("yearly").value="0";
	
		document.getElementById("myform").submit();
	
	
}
 
</script>


<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4> <s:if test="yearly==1">Yearly Revenue Report </s:if>
										<s:else>Monthly Revenue Report</s:else>
										</h4>

									</div>

</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
<form action="totalrevenueSummary" method="post" id="myform"> 

		<div class="col-lg-12 col-md-12 topback2 hidden-print lok" >
		
		<div class="form-inline">
			
		
					<div class="form-group" style="width:10%;">		
						 <s:select style="width:100%;" cssClass="form-control chosen-select" list="#{'01':'January', '02':'February', '03':'March', '04':'April', '05':'May','06':'June', '07':'July', '08':'August', '09':'September', '10':'October', '11':'November', '12':'December'}" name="fromDate" />
					</div>				
	
					<div class="form-group" style="width:10%;">
						<s:select style="width:100%;" cssClass="form-control chosen-select" list="#{'2014':'2014','2015':'2015','2016':'2016','2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020', '2021':'2021','2022':'2022', '2023':'2023', '2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028'}" name="toDate" />
					</div>
					<div class="form-group" style="width:10%;">
						<s:if test="yearly==1">
							<input type="button" onclick="setMonthly()" class="btn btn-info" value="Monthly Report">
						</s:if>
						<s:else>
							<input type="button" onclick="setyearly()" class="btn btn-info" value="Yearly Report"> 
						</s:else>
					
					</div>
			<div class="form-group" style="width:20%;">		
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
			<div class="form-group" style="width:20%;">		
			<input type="button" value="Generate Report " name="gen" id="gen" class="btn btn-success" onclick="settogenerate()">
			<input type="hidden" name="refresh" id='refresh'   value="" >
			<input type="hidden" name="yearly" id='yearly'   value="" >
			
			</div>
		
		</div>
		</div>
  </form>			
</div>
<s:if test="yearly!=1">
<div class="xlstable">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 20px;">
<h4><b>YUVICARE</b></h4>
<table class="hidden-lg hidden-md visible-print hidden-print"><tr><th>YUVICARE</th></tr></table>
<table class="my-table" style="width:100%;" >
<tr class="shublok">
<td>Bed Occupancy</td>
<td>Avg Stay</td>
<td>Total Opds</td>
<td>Total Ipds</td>
<td>Gross Ipd Revenue</td>
<td>Gross Opd Revenue</td>
<td>Gross Investigation Revenue</td>
<td>Gross Other Revenue</td>
<td>OPD Collection</td>
<td>IPD Collection</td>
<td>Investigation Collection</td>
<td>Other Collection</td>
</tr>
<tr >
<td><s:property value="totalbedoccupancy"/></td>
<td><s:property value="averagestay"/></td>
<td><s:property value="totalopdseen"/></td>
<td><s:property value="ipdnewadmission"/></td>
<td><s:property value="ipddebitcount"/></td>
<td><s:property value="opddebitcount"/></td>
<td><s:property value="Investigationdebitcount"/></td>
<td><s:property value="Medicinedebitcount"/></td>
<td><s:property value="netipddebitcount"/></td>
<td><s:property value="netopddebitcount"/></td>
<td><s:property value="netinvestigationdebitcount"/></td>
<td><s:property value="netmedicinedebitcount"/></td>

</tr>
</table>

</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 20px;">
<h4><b>PHARMACY</b></h4>
<table class="hidden-lg hidden-md visible-print hidden-print"><tr><th>PHARMACY</th></tr></table>
<table class="my-table " style="width:100%;" >
<tr class="shublok">
<td>Credit</td>
<td>Credit Return</td>
<td>Card Sale</td>
<td>Cash Sale</td>
<td>Cheque</td>
<td>NEFT/RTGS</td>
<td>Cash Return</td>
<td>Discount</td>
<td>Total Received</td>
</tr>
<tr>
<td><s:property value="totalcredit"/></td>
<td><s:property value="creditReturn"/></td>
<td><s:property value="todaycard"/></td>
<td><s:property value="todaycash"/></td>
<td><s:property value="chequepayments"/></td>
<td><s:property value="neftpayment"/></td>
<td><s:property value="todayreturn"/></td>
<td><s:property value="todaydisc"/></td>
<td><s:property value="totalpayment"/></td>
</tr>
</table>
</div>


</div>
</s:if>
<s:else>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 20px;">
<table class="my-table xlstable" style="width:100%;" >
<tr class="shublok">
<td>Month</td>
<td>Bed Occupancy</td>
<td>Avg Stay</td>
<td>Total Opds</td>
<td>Total Ipds</td>
<td>Gross Ipd Revenue</td>
<td>Gross Opd Revenue</td>
<td>Gross Investigation Revenue</td>
<td>Gross Other Revenue</td>
<td>OPD Collection</td>
<td>IPD Collection</td>
<td>Investigation Collection</td>
<td>Other Collection</td>
<td>Credit</td>
<td>Credit Return</td>
<td>Card Sale</td>
<td>Cash Sale</td>
<td>Cheque</td>
<td>NEFT/RTGS</td>
<td>Cash Return</td>
<td>Discount</td>
<td>Total Received</td>

</tr>

<s:iterator value="totalrevenuelist">
<tr>
<td><s:if test="mnth=='01'">Jan</s:if>
<s:elseif test="mnth=='02'">Feb</s:elseif>
<s:elseif test="mnth=='03'">Mar</s:elseif>
<s:elseif test="mnth=='04'">Apr</s:elseif>
<s:elseif test="mnth=='05'">May</s:elseif>
<s:elseif test="mnth=='06'">Jun</s:elseif>
<s:elseif test="mnth=='07'">Jul</s:elseif>
<s:elseif test="mnth=='08'">Aug</s:elseif>
<s:elseif test="mnth=='09'">Sep</s:elseif>
<s:elseif test="mnth=='10'">Oct</s:elseif>
<s:elseif test="mnth=='11'">Nov</s:elseif>
<s:elseif test="mnth=='12'">Dec</s:elseif>
'<s:property value="yr"/>
</td>
<td><s:property value="totalbedoccupancy"/></td>
<td><s:property value="averagestay"/></td>
<td><s:property value="totalopdseen"/></td>
<td><s:property value="ipdnewadmission"/></td>
<td><s:property value="ipddebitcount"/></td>
<td><s:property value="opddebitcount"/></td>
<td><s:property value="Investigationdebitcount"/></td>
<td><s:property value="Medicinedebitcount"/></td>
<td><s:property value="netipddebitcount"/></td>
<td><s:property value="netopddebitcount"/></td>
<td><s:property value="netinvestigationdebitcount"/></td>
<td><s:property value="netmedicinedebitcount"/></td>


<td><s:property value="totalcredit"/></td>
<td><s:property value="creditReturn"/></td>
<td><s:property value="todaycard"/></td>
<td><s:property value="todaycash"/></td>
<td><s:property value="chequepayments"/></td>
<td><s:property value="neftpayment"/></td>
<td><s:property value="todayreturn"/></td>
<td><s:property value="todaydisc"/></td>
<td><s:property value="totalpayment"/></td>

</tr>
</s:iterator>
</table>
</div>
</s:else>	
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
