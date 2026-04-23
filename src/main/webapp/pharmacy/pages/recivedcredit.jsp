<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<%-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" 
  href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> --%>

<script>


 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "creditpayments",
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
});

</script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

							
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

										<h4>Payment Against Credit DashBoard</h4>

									</div>

</div>	

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print">
<br>
<div class="form-inline ">
<s:form action="recivedcreditPharmacy" theme="simple" id = "invoicerportfrm">
	
	<div class="form-group" style="width:7%;">
				<label>From Date</label><s:textfield readonly="true" name="fromdate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
	</div>		
	<div class="form-group" style="width:7%;">
				<label>To Date</label><s:textfield readonly="true" name="todate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
	</div>
	<div class="form-group" style="width:10%;">
	<label>Paymode</label>
				<s:select name="paymode" id="paymode" 
				list="#{'':'All','Card':'Card','Cash':'Cash','Credit':'Credit','NEFT':'NEFT','Cheque':'Cheque','Online Payment':'Online Payment'}"
				cssClass="form-control chosen-select" ></s:select>
	</div> 
	<input type="submit" class='btn btn-primary' value='Go' style="margin-top: 22px;">
	<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"  style="margin-top: 22px;"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"  style="margin-top: 22px;"><i class="fa fa-file-excel-o"></i></a>
</s:form>	
</div>
<br>
<div>

</div>
<br>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<table class="my-table xlstable" style="width:100%">
<tr>
<th>Sr No</th>
<th>Bill No.</th>
<th>Date(Payment)</th>
<th>Date(Bill)</th>
<th>User ID</th>
<th>Pay Mode</th>
<th>Bill Amount</th>
<th>Payment</th>
<th>Type</th>
</tr>
<%int i=0; %>
<s:iterator value='allBillList'>
<tr>
<td><%=i+1 %><%i=i+1; %></td>
<td><s:property value='billno'/></td>
<td><s:property value='date'/></td>
<td><s:property value='dateTime'/></td>
<td><s:property value='userid'/></td>
<td><s:property value='paymode'/></td>
<td><s:property value='debit'/></td>
<td><s:property value='payment'/></td>
<td><s:if test="isreturn==0">Sale</s:if><s:else>Return</s:else></td>
</tr>
</s:iterator>
<tfoot>
<tr><td>Total:</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td><b><s:property value='totalqty'/></b></td>
<td></td>
</tr>
</tfoot>
</table>
</div>
</div>
