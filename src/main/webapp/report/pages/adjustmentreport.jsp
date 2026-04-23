<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script>
	function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "Adjustment Report",
			filename : "AdjustmentReport",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}

	$(document).ready(function() {

		$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		});
	});
</script>
<style>
.text-center {
	text-align: center;
}
</style>
<div class="">
	<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div>
	<div class="row details">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

			<h4>Adjustment Report</h4>

		</div>
	</div>

	<div class="row ">

		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<s:form action="adjustmentreportReport" theme="simple"
				id="adjustmentfrm">
				<div class="col-lg-12 col-md-12 topback2 hidden-print">
					<div class="form-inline">
						<div class="form-group" style="width: 10%;">
							<s:select list="locationlist"  theme="simple" name="location" cssStyle="width:100%" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
							</s:select> 
						</div>
						<div class="form-group" style="width: 10%;">
							<s:select list="userlist"  theme="simple" name="diaryUser" cssStyle="width:100%" cssClass="form-control chosen-select" listKey="userid" listValue="name" headerKey="0" headerValue="Select User" >
							</s:select> 
						</div>
						<div class="form-group" style="width: 18%;">
							<s:select list="#{'0':'Select Adjustment Type','1':'Excess Product Adjustment','2':'Shortage Product Adjustment','3':'Defective Product Adjustment',
											'4':'Expired/Dead Product Adjustment'}" name="adjustmenttype"  cssClass="form-control chosen-select"></s:select>
						</div>
						
						
						<div class="form-group" style="width: 7%;">
							<s:textfield readonly="true" name="fromDate" id="fromDate"
								cssClass="form-control" theme="simple" style="width:100%;"
								placeholder="from date"></s:textfield>
						</div>
						<div class="form-group" style="width: 7%;">
							<s:textfield readonly="true" name="toDate" id="toDate"
								cssClass="form-control" theme="simple" style="width:100%;"
								placeholder="to date"></s:textfield>
						</div>
						&nbsp;&nbsp;&nbsp;


						<div class="form-group">
							<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
							<a type="button" class="btn btn-primary" title="Print"
								onclick="printpage()"><i class="fa fa-print"></i></a> <a
								type="button" id="btnxls" title="Save As XLS"
								onclick="printExcel()" class="btn btn-primary"><i
								class="fa fa-file-excel-o"></i></a>
						</div>

					</div>
				</div>
			</s:form>
			<%
				int i = 1;
			%>
			<table class="my-table xlstable" style="width: 100%">
				<thead>
				<tr bgcolor="#4E7894">
					<td style="color: #eee; width: 2%">Sr.No.</td>
					<td style="width: 14%; color: #eee">Adjustment Type</td>
					<td style="color: #eee; width: 15%">Product Name</td>
					<td style="color: #eee; width: 7%">Location</td>
					<td style="color: #eee;width: 2%;">Pack</td>
					<td style="color: #eee;text-align:center;width: 3%;">MRP</td>
                    <td style="color: #eee;text-align:center;width: 7%;">Purchase Price</td>
                    <td style="color: #eee;text-align:center;width: 5%;">Sale Price</td>
					<td style="color: #eee;width: 7%;" class="text-center">Previous Stock</td>
					<td style="color: #eee;width: 6%;" class="text-center">Current Stock</td>
					<td style="color: #eee;width: 5%;" class="text-center">Adjust Qty</td>
					<td style="color: #eee;text-align:center;width: 4%;">S.V.</td>
					<td style="color: #eee;width: 4%;" class="text-center">User Id</td>
					<td style="color: #eee;width: 9%;" class="text-center">Date</td>
					<td style="color: #eee;width: 15%;">Remark</td>
				</tr>
				</thead>
				
				<tfoot style="background-color: rgba(245, 245, 245, 0.64);color: green;">
                     <tr>
                       	<td></td>
                       	<td></td>
                       	<td>Total</td>
                       	<td></td>
                       	<td></td>
                       	<td></td>
                       	<td></td>
                       	<td></td>
                       	<td></td>
                       	<td></td>
                       	<td></td>
                       	<td><s:property value="totalpurchaseprice" /></td>
                       	<td></td>
                       	<td></td>
                       	<td></td>
                       	
                    </tr>
				</tfoot>
				<s:if test="adjustmentlist.size!=0">
					<s:iterator value="adjustmentlist">
						<tr>
							<td><%=i++%></td>
							<s:if test="adjustment_type==1">
								<td>Excess Product Adjustment</td>
							</s:if>
							<s:elseif test="adjustment_type==2">
								<td>Shortage Product Adjustment</td>
							</s:elseif>
							<s:elseif test="adjustment_type==3">
								<td>Defective Product Adjustment</td>
							</s:elseif>
							<s:else>
								<td>Expired/Dead Product Adjustment</td>
							</s:else>
							<td><s:property value="prod_name" /></td>
							<td><s:property value="locationName" /></td>
							<td><s:property value="pack" /></td>
							<td style="text-align:right;"><s:property value="mrp" /></td>
                   			<td style="text-align:right;"><s:property value="purchase_price" /></td>
                    		<td style="text-align:right;"><s:property value="sale_price" /></td>
							
							<td class="text-center"><s:property value="pre_stock" /></td>
							<td class="text-center"><s:property value="current_stock" /></td>
							<td class="text-center"><s:property value="change_qty" /></td>
							
							<td style="text-align:right;width: 4%;"><s:property value="purpriceqty" /></td>
							
							<td class="text-center"><s:property value="userid" /></td>
							<td class="text-center"><s:property value="dateTime" /></td>
							<td><s:property value="remark" /></td>
							
						</tr>
					</s:iterator>
				</s:if>
				
			</table>
		</div>
	</div>


	<s:form action="adjustmentreportReport" name="paginationForm"
		id="paginationForm" theme="simple">

		<s:hidden name="fromDate"></s:hidden>
		<s:hidden name="toDate"></s:hidden>
		<div class="col-lg-12 col-md-12" style="margin-top: 15px;">
			<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
				Total:<label class="text-info"><s:property
						value="totalRecords" /></label>
			</div>
			<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
		</div>
	</s:form>
	
	<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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
  
</div>