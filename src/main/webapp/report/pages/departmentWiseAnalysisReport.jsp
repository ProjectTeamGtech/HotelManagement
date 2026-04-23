<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



</head>
<body>
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Department Wise Analysis Report</h4>
									</div>
								</div>
<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			<div class="form-group" style="width:7%;">
				<input type="text" name="fromDate" readonly="readonly" id="fromDate" class="form-control" placeholder="from date" style="width:100%;">
			</div>
			<div class="form-group" style="width:7%;">
				<input type="text" name="toDate" readonly="readonly" id="toDate" class="form-control" placeholder="to date" style="width:100%;">
			</div>
			<div class="form-group">
				<input type="submit" id="invoicerportfrm_0" value="Go" class="btn btn-primary">
			<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" title="Save As PDF" onclick="return saveAsPdfChargesReport();" class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a>
		    <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
			</div>
		</div>

		</div>
		<table class="my-table tablexls" style="width: 100%;">
				<thead>
					<tr>
						<th style="width: 51%;">Service Name</th>
						<th colspan="2" style="width: 12%;">OPD</th>
						<th colspan="2" style="width: 12%;">IPD</th>
						<th>Total Units</th>
						<th style="text-align: right;">Total Charges</th>
					</tr>
				</thead>
			<tbody>
				<tr style="background-color: rgba(102, 153, 204, 0.11);">
					<td></td>
					<td style="font-weight: bold;">Units</td>
					<td style="font-weight: bold;text-align:right">Charges</td>
					<td style="font-weight: bold;">Units</td>
					<td style="font-weight: bold;text-align:right">Charges</td>
					<td></td>
					<td></td>
				</tr>
				<tr style="background-color: beige;">
					<td colspan="5" style="border-right: none;font-weight: bold;color: #a94442;">ADMISSION CHARGE</td>
					<td style="border-right: none;"></td>
					<td></td>
					
				</tr>	
				<tr>
					<td>Doctor Visit Charges</td>
					<td>0</td>
					<td style="text-align:right">0.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
				</tr>
				<tr>
					<td>Intensive Charge</td>
					<td>0</td>
					<td style="text-align:right">0.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
				</tr>
				<tr>
					<td>Nursing Charge</td>
					<td>0</td>
					<td style="text-align:right">0.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
				</tr>
				<tr style="font-weight: bold;color: #a94442;">
					<td style="text-align: right;">Total :</td>
					<td>0</td>
					<td style="text-align:right;">0.00</td>
					<td>146</td>
					<td style="text-align:right; ">100000.00</td>
					<td>146</td>
					<td style="text-align:right;">100000.00</td>
				</tr>
				<tr style="font-weight: bold;color: red;">
					<td style="text-align: right;">Less Discount :</td>
					<td>0</td>
					<td style="text-align:right;">0.00</td>
					<td>146</td>
					<td style="text-align:right; ">100000.00</td>
					<td>146</td>
					<td style="text-align:right;">100000.00</td>
				</tr>
				<tr style="font-weight: bold;color: red;">
					<td style="text-align: right;">Less Refund :</td>
					<td>0</td>
					<td style="text-align:right;">0.00</td>
					<td>146</td>
					<td style="text-align:right; ">100000.00</td>
					<td>146</td>
					<td style="text-align:right;">100000.00</td>
				</tr>
				<tr style="font-weight: bold;color: green;">
					<td style="text-align: right;">Net :</td>
					<td>0</td>
					<td style="text-align:right;">0.00</td>
					<td>146</td>
					<td style="text-align:right; ">100000.00</td>
					<td>146</td>
					<td style="text-align:right;">100000.00</td>
				</tr>
				<tr style="background-color: beige;">
					<td colspan="5" style="border-right: none;font-weight: bold;color: #a94442;">BIOCHEMISTRY SERVICES</td>
					<td style="border-right: none;"></td>
					<td></td>
					
				</tr>
				<tr>
					<td>Doctor Visit Charges</td>
					<td>0</td>
					<td style="text-align:right">0.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
				</tr>
				<tr>
					<td>Intensive Charge</td>
					<td>0</td>
					<td style="text-align:right">0.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
					<td>146</td>
					<td style="text-align:right">100000.00</td>
				</tr>
				<tr style="font-weight: bold;color: #a94442;">
					<td style="text-align: right;">Total :</td>
					<td>0</td>
					<td style="text-align:right;">0.00</td>
					<td>146</td>
					<td style="text-align:right; ">100000.00</td>
					<td>146</td>
					<td style="text-align:right;">100000.00</td>
				</tr>
				<tr style="font-weight: bold;color: red;">
					<td style="text-align: right;">Less Discount :</td>
					<td>0</td>
					<td style="text-align:right;">0.00</td>
					<td>146</td>
					<td style="text-align:right; ">100000.00</td>
					<td>146</td>
					<td style="text-align:right;">100000.00</td>
				</tr>
				<tr style="font-weight: bold;color: red;">
					<td style="text-align: right;">Less Refund :</td>
					<td>0</td>
					<td style="text-align:right;">0.00</td>
					<td>146</td>
					<td style="text-align:right; ">100000.00</td>
					<td>146</td>
					<td style="text-align:right;">100000.00</td>
				</tr>
				<tr style="font-weight: bold;color: green;">
					<td style="text-align: right;">Net :</td>
					<td>0</td>
					<td style="text-align:right;">0.00</td>
					<td>146</td>
					<td style="text-align:right; ">100000.00</td>
					<td>146</td>
					<td style="text-align:right;">100000.00</td>
				</tr>	
			</tbody>
		</table>
		
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;padding-top: 15px;">
			<div class="col-lg-6 col-xs-6 col-md-6" style="padding: 0px;">
				<span>User ID : Abhi</span>
			</div>
			<div class="col-lg-6 col-xs-6 col-md-6 text-right" style="padding: 0px;">
				<span>Date : 24/06/2017</span>
			</div>
		</div>
</body>
</html>