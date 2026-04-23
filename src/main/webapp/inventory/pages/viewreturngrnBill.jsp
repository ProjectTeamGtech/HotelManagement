<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	p {
    margin: 0 0 1.5px;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
    line-height: 40px;
}
</style>
</head>
<body>
<div class="col-lg-12 col-xs-12 col-md-12">
<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="letterHead">
				<h4><s:property value="clinicName"/></h4>
				<h5><s:property value="clinicaddress"/></h5><h5>Website:<s:property value="websiteUrl"/>, Email:<s:property value="email"/>, Contact :<s:property value="landLine"/> </h5>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
			       <h2 class="text-uppercase"><b>Goods Return Note</b></h2>
				
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;" id="billanddata">
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset"><b>Supplier Name :</b>&nbsp;<span>Rose Enterprises <s:property value="vendor"/></span></p>
					<p class="marboset"><b>PO No :</b>&nbsp;<span><s:property value="grnno"/></span></p>
					<p class="marboset"><b>Date :</b>&nbsp;<span><s:property value="date"/></span></p>
					
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<p class="marboset"><b>Department :</b>&nbsp;<span><s:property value="location"/></span></p>
					<p class="marboset"><b>Security Out No :</b>&nbsp;<span><s:property value="security_no"/></span></p>
					<p class="marboset"><b>Security Out Date :</b>&nbsp;<span><s:property value="security_date"/></span></p>
					<p class="marboset"><b>Invoice No :</b>&nbsp;<span><span><s:property value="voucherno"/></span></p>
					<p class="marboset"><b>Invoice Date :</b>&nbsp;<span><s:property value="voucherdate"/></span></p>
				</div>
			</div>
							        	
	</div>
</div>


<div class="row">
<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Received Items :-</h5>
	<table class="table" style="width: 100%;">
				                            <thead>
				                                <tr>
				                                    <th style="width: 3%;">Sr</th>
				                                    <th style="width: 25%;">Product</th>
				                                    <th style="width: 7%;">Batch No</th>
				                                    <th style="width: 10%;">ExpDt</th>
				                                    <th style="width: 7%;" class="">Rate</th>
				                                    <th style="width: 5%;">Qty</th>
				                                    <th style="width: 5%;">Pack</th>
				                                    <th style="width: 7%;text-align:right;">Amount</th>
				                                   </tr>
				                            </thead>
				                            <tbody >
				                             <%int i=0; %>
				                                
				                                <tr>
				                                </tr>
				                                
				                                
				                               <tr style="border-top: 1px solid #ddd;">
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Sub Total</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="subTotal"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;color: red;border: none;">Discount</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;color: red;border: none;">Rs.<s:property value="discount"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">SGST</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="sgst"/></td>
				                               </tr>
				                              <tr >
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">CGST</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="cgst"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">IGST</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="igst"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Surcharge</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="surcharge"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Credit </td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="credit"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Debit Note</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="debit"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="12" style="font-size: 11px;text-align: right;font-weight: bold;color: green;border: none;">Net Payment</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;color: green;border: none;">Rs.<s:property value="netTotal"/>
				                               		<s:hidden name="netTotal" id="net"> </s:hidden>
				                               	</td>
				                               </tr>
				                               
				                                  
				                            </tbody>
				                        </table>
				                        <span style="font-size: 11px;text-align: right;font-weight: bold;border: none;position: absolute;margin-top: -25px;"><b>Payment Mode:</b><s:property value="paymode"/> </span>
</div>

<div class="row">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
		<p>Remark : <s:property value="remark"/> </p>
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
		<small>In words: <label id="inword"></label></small>
	</div>
</div>

</div>
<div class="row" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
		<p>Created by : <s:property value="createdby"/> </p>
		<p>Printed by : <s:property value="printedby"/> </p>
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
		<p>Manager/Purchase Officer</p>
		<a type="button" class="btn btn-primary btn-lg savebigbtn hidden-print"  title="Print" onclick="printpage()">Print</a>
	</div>
</div>
</div>


</div>
<script>
	 function myPrint() {
            window.print();
        }
        
        window.onload =function(){
        
              var amt=document.getElementById("net").value;
              document.getElementById("inword").innerText=convertNumberToWords(amt);
        };
        
        
</script>
  <script>
	function convertNumberToWords(amount) {
    var words = new Array();
    words[0] = '';
    words[1] = 'One';
    words[2] = 'Two';
    words[3] = 'Three';
    words[4] = 'Four';
    words[5] = 'Five';
    words[6] = 'Six';
    words[7] = 'Seven';
    words[8] = 'Eight';
    words[9] = 'Nine';
    words[10] = 'Ten';
    words[11] = 'Eleven';
    words[12] = 'Twelve';
    words[13] = 'Thirteen';
    words[14] = 'Fourteen';
    words[15] = 'Fifteen';
    words[16] = 'Sixteen';
    words[17] = 'Seventeen';
    words[18] = 'Eighteen';
    words[19] = 'Nineteen';
    words[20] = 'Twenty';
    words[30] = 'Thirty';
    words[40] = 'Forty';
    words[50] = 'Fifty';
    words[60] = 'Sixty';
    words[70] = 'Seventy';
    words[80] = 'Eighty';
    words[90] = 'Ninety';
    amount = amount.toString();
    var atemp = amount.split(".");
    var number = atemp[0].split(",").join("");
    var n_length = number.length;
    var words_string = "";
    if (n_length <= 9) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < n_length; i++) {
            received_n_array[i] = number.substr(i, 1);
        }
        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
            n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                if (n_array[i] == 1) {
                    n_array[j] = 10 + parseInt(n_array[j]);
                    n_array[i] = 0;
                }
            }
        }
        value = "";
        for (var i = 0; i < 9; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                value = n_array[i] * 10;
            } else {
                value = n_array[i];
            }
            if (value != 0) {
                words_string += words[value] + " ";
            }
            if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Crores ";
            }
            if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Lakhs ";
            }
            if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Thousand ";
            }
            if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                words_string += "Hundred and ";
            } else if (i == 6 && value != 0) {
                words_string += "Hundred ";
            }
        }
        words_string = words_string.split("  ").join(" ");
    }
    return words_string;
}
</script>  

</body>
</html>