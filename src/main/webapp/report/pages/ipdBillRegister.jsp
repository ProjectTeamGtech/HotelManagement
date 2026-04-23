<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.my-table td {
    border-bottom: 1px solid #DFD8D4;
    border-right: 1px solid #DFD8D4;
    padding: 2px 1px 1px 5px;
    font-size: small;
    font-size: 9px;
}
.my-table th {
    background-color: #4E7894;
    color: #fff !important;
    border-bottom: 1px solid #DFD8D4;
    /* border-right: 1px solid #DFD8D4; */
    /* border-top: 1px solid #DFD8D4; */
    padding: 2px 5px 2px 5px !important;
    text-align: left;
    font-size: 9px;
    background-size: 100% 100%;
    font-weight: normal;
}
</style>

</head>
<body>
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>IPD Bill Register</h4>
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
						<th>SI.No</th>
						<th>Date</th>
						<th>Bill No</th>
						<th>IP Number</th>
						<th>Patient ID</th>
						<th>Patient Name</th>
						<th>Consultant Name</th>
						<th>Credit/Ac/Payor/Corporate</th>
						<th style="text-align: right;">Gross Amt</th>
						<th style="text-align: right;">Discount</th>
						<th style="text-align: right;">Cancelled Amt</th>
						<th style="text-align: right;">Packages Covered Amt</th>
						<th style="text-align: right;">Net Billed Amt</th>
						<th style="text-align: right;">Pharmacy Paid Amt</th>
						<th style="text-align: right;">Adv.Amt</th>
						<th style="text-align: right;">Recd Amt</th>
						<th style="text-align: right;">Write Off</th>
						<th style="text-align: right;">Outs Amt</th>
						<th style="text-align: right;">Refundable Amt</th>
						<th style="text-align: right;">Refunded Amt</th>
						<th style="text-align: right;">Cash</th>
						<th style="text-align: right;">CC</th>
						<th style="text-align: right;">Cheque/DD</th>
						<th style="text-align: right;">NEFT</th>
						<th>User</th>
						<th>Referral Doctor</th>
						<th>Admission Purpose</th>
						<th>Location</th>
					</tr>
				</thead>
			<tbody>
				
				<tr>
					<td>1</td>
					<td>20/6/2017</td>
					<td>IP/SNH/17-18/1987</td>
					<td>IPA/SNH/17-18/06/700</td>
					<td>SNH/170406248</td>
					<td>Mrs.Rita Patel</td>
					<td>Dr.C.P.Dubey</td>
					<td>SNHRaipur_genral</td>
					<td style="text-align: right;">Rs.2500.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.809.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.1230.00</td>
					<td style="text-align: right;">Rs.20000.00</td>
					<td style="text-align: right;">Rs.50000.00</td>
					<td style="text-align: right;">Rs.25000.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.2500.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td>Reshma</td>
					<td>Dr.Wilson</td>
					<td>Medical Care</td>
					<td>SNH</td>
				</tr>
				<tr>
					<td>2</td>
					<td>20/6/2017</td>
					<td>IP/SNH/17-18/1987</td>
					<td>IPA/SNH/17-18/06/700</td>
					<td>SNH/170406248</td>
					<td>Mrs.Rita Patel</td>
					<td>Dr.C.P.Dubey</td>
					<td>SNHRaipur_genral</td>
					<td style="text-align: right;">Rs.2500.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.809.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.1230.00</td>
					<td style="text-align: right;">Rs.20000.00</td>
					<td style="text-align: right;">Rs.50000.00</td>
					<td style="text-align: right;">Rs.25000.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.2500.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td style="text-align: right;">Rs.0.00</td>
					<td>Reshma</td>
					<td>Dr.Wilson</td>
					<td>Medical Care</td>
					<td>SNH</td>
				</tr>
					
						
							
								 
								 
			</tbody>
		</table>
		
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;padding-top: 15px;">
			<div class="col-lg-6 col-xs-6 col-md-6" style="padding: 0px;">
				<span>User ID : Jatin</span>
			</div>
			<div class="col-lg-6 col-xs-6 col-md-6 text-right" style="padding: 0px;">
				<span>Date : 24/06/2017</span>
			</div>
		</div>
</body>
</html>