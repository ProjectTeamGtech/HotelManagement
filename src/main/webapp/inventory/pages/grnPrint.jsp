<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
 <%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
 <%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
 %>
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
<style>
        .padright {
            padding-left: 40px;
        }
        .table.table {
            color: RGBA(85, 85, 85, 0.85);
            background-color: #fff;
        }

        .comtitle {
            font-size: 13px;
            background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
            color: rgb(255, 255, 255);
        }

        .marbot25 {
            margin-bottom: 25px;
        }

        .editcompany {
            float: right;
            font-size: 17px;
            color: #fff;
        }

        .borright {
            border-right: 1px dashed rgb(192, 192, 192);
        }

        .buildinglogo {
            width: 60%;
            margin-top: 30px;
        }
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
            border-top: 0;
            min-height: auto !important;
        }
        .miheight {
            min-height: auto !important;
        }
        .my-table th {
            background-color: #424A5D;
            color: #fff !important;
            border-bottom: 1px solid #DFD8D4;
            border-right: 1px solid #DFD8D4;
            border-top: 1px solid #DFD8D4;
            padding: 3px 3px 4px 5px;
            text-align: left;
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 1px 7px 1px 7px !important;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
                display: inline-block;
            }
        .sidebar-xs #header .branding {
            width: 100%;
            padding-top: 7px;
            text-align: center;
        }
        .theight {
            height: 21px;
        }
    </style>
</head>
<body>
<%
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	%>
	
<% String scolomn="hidden"; %>
   
   <%if(loginInfo.isParihar()){ %>
         <%scolomn="";%>
   <%} %>	
<div class="col-lg-12 col-xs-12 col-md-12">
<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="letterHead">
				<h4><s:property value="clinicName"/></h4>
				<h5><s:property value="clinicaddress"/></h5><h5>Website:<s:property value="websiteUrl"/>, Email:<s:property value="email"/>, Contact :<s:property value="landLine"/> </h5>
			</div> --%>
			<div class="row letterheadhgt" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
				 <%if(!loginInfo.isHidelogoemr()){%>
				<%@ include file="/accounts/pages/letterhead.jsp" %>
			<%} %>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
			   <%-- <s:if test="grnno==0">
			       <h2 class="text-uppercase"><b>Goods Receipt Note</b></h2>
			   </s:if>
			   <s:else>
			   	<h2 class="text-uppercase"><b>Purchase Order</b></h2>
			   </s:else> --%>
				<s:if test="isdelivermemo==1">
			   <h2 class="text-uppercase"><b>Delivery Memo Receipt </b></h2>	
				</s:if>
				<s:else>
				<h2 class="text-uppercase"><b>Goods Receipt Note</b></h2>		
				</s:else>		
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;" id="billanddata">
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset"><b>Supplier Name :</b>&nbsp;<span><s:property value="vendor"/></span></p>
					<p class="marboset"><b>Address :</b>&nbsp;<span><s:property value="address"/></span></p>
					<p class="marboset"><b>Contact No :</b>&nbsp;<span><s:property value="mobile"/></span></p>
					<p class="marboset"><b>Email Id :</b>&nbsp;<span><s:property value="vendoremail"/></span></p>
					<%
						if (loginInfo.isLmh()) {
					%>
						<p class="marboset"><b>GST No. :</b>&nbsp;<span><s:property value="vendorGstNo"/></span></p>
					<%
						}
					%>
					
					<p class="marboset"><b>PO No :</b>&nbsp;
						<span>
							<%
							 	if (loginInfo.isLmh()) {
							 %> <s:property value="poSequence" /> <%
							 	} else {
							 %> <s:property value="grnno" /> <%
							 	}
							 %>
						</span>
					</p>
					<p class="marboset"><b>PO Date :</b>&nbsp;<span><s:property value="grndate"/></span></p>
					
					<s:if test="isdelivermemo==1">
						<p class="marboset"><b>DM No :</b>&nbsp;<span><td><s:property value="dmseq"/></td> <%-- <s:property value="procurementid"/> --%></span></p>
					<p class="marboset"><b>DM Date :</b>&nbsp;<span><s:property value="date"/></span></p>
					
					</s:if>
					<s:else>
					<p class="marboset"><b>GRN No :</b>&nbsp;<span><td>
					
							<%
							 	if (loginInfo.isLmh()) {
							 %> GRN/<s:property value="proSeqNo"/> <%
							 	} else {
							 %> <s:property value="proSeqNo"/> <%
							 	}
							 %>
					</td> <%-- <s:property value="procurementid"/> --%></span></p>
					<p class="marboset"><b>GRN Date :</b>&nbsp;<span><s:property value="date"/></span></p>
					</s:else>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
			
					<p class="marboset"><b>Department :</b>&nbsp;<span><s:property value="location"/></span></p>
					<p class="marboset"><b>Security In No :</b>&nbsp;<span><s:property value="security_no"/></span></p>
					<p class="marboset"><b>Security In Date :</b>&nbsp;<span><s:property value="security_date"/></span></p>
					<p class="marboset"><b>Invoice No :</b>&nbsp;<span><span><s:property value="voucherno"/></span></p>
					<s:if test="dmno!=''">
					<p class="marboset"><b>DM No :</b>&nbsp;<span><span><s:property value="dmno"/></span></p>
					</s:if>
					<p class="marboset"><b>Invoice Date :</b>&nbsp;<span><s:property value="voucherdate"/></span></p>
				</div>
			</div>
							        	
	</div>
</div>

<%-- <s:if test="newpo>0">
<div class="row" style="background-color: rgba(239, 239, 239, 0.42);padding: 9px;border: 1px dashed #ddd;    margin-bottom: 15px;">
	<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Requested Items :-</h5>
	<table class="table table-striped table-bordered" style="width:100%;">
         <thead>
           <tr>
            <td style="width: 4%;">Sr.no</td>
            <td style="width: 7%;">Date/Time</td>
            <td style="width: 20%;">Product Name</td>
            <td style="width: 7%;">Req Qty</td>
            <td style="width: 7%;">Rate</td>
            <td style="width: 7%;">GST</td>
            <td style="width: 7%;">Discount</td>
           </tr>
          </thead>
          <tbody>
           <% int i=0; %>
           <s:iterator value="requestedPoList">
          	<tr>
          		<td><%=++i %></td>
          		<td><s:property value="date"/> </td>
          		<td><s:property value="product_name"/></td>
          		<td><s:property value="qty"/></td>
          		<td>Rs.<s:property value="purchase_price"/></td>
          		<td><s:property value="vat"/>%</td>
          		<td><s:property value="discount"/></td>
          	</tr>
          	</s:iterator>
          	</tbody>
         </table>
         
         
</div>
</s:if> --%>



<div class="row">
<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Received Items :-</h5>
	<table class="" border="1" style="width: 100%;">
				                            <thead>
				                                <tr>
				                                    <td style="width: 3%;text-align: center;padding-top: 5px;">Sr</td>
				                                    <td style="width: 25%;text-align: center;padding-top: 5px;">Product</td>
				                                    <td style="width: 9%;text-align: center;padding-top: 5px;">UOM</td>
				                                    <td style="width: 10%;text-align: center;padding-top: 5px;">Product Code</td>
				                                    <td style="width: 7%;text-align: center;padding-top: 5px;">Batch No</td>
				                                    <td style="width: 10%;text-align: center;padding-top: 5px;">ExpDt</td>
				                                    <td style="width: 9%;text-align: center;padding-top: 5px;">HSN No</td>
				                                    <%if(!loginInfo.isLmh()){ %>
				                                    <td style="width: 7%;text-align: center;padding-top: 5px;" class="">MRP</td>
				                                    <%} %>
				                                    <td style="width: 6%;text-align: center;padding-top: 5px;">GST</td>
				                                    <td style="width: 7%;text-align: center;padding-top: 5px;" class="">Rate</td>
				                                    <td style="width: 5%;text-align: center;padding-top: 5px;">Qty</td>
				                                    <td style="width: 5%;text-align: center;padding-top: 5px;">Pack</td>
				                                    <td style="width: 6%;text-align: center;padding-top: 5px;">Dis %</td>
				                                    <td class=<%=scolomn%> style="width: 4%;text-align: center;padding-top: 5px;">SCH %</td>
				                                    <td class=<%=scolomn%> style="width: 4%;text-align: center;padding-top: 5px;">Value</td>
				                                    <td class=<%=scolomn%> style="width: 4%;text-align: center;padding-top: 5px;">Disc Amt</td>
				                                    <td style="width: 6%;text-align: center;padding-top: 5px;">Free Qty</td>
				                                    <td style="width: 7%;text-align: center;padding-top: 5px;">Amount</td>
				                                   </tr>
				                            </thead>
				                            <tbody >
				                             <%int i=0; %>
				                              <s:iterator value="productList">
				                               <%String bgcolor=""; %>
				                              
				                               <s:if test="edit==1">
				                                  <%bgcolor="rgba(221, 221, 221, 0.28);"; %>
				                               </s:if>
				                               <s:else>
				                               <% bgcolor="white"; %>
				                                  
				                               </s:else>
				                              
				                               <tr style="background-color: <%=bgcolor %>" >
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><%=(++i) %></td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="product_name"/><s:if
										                   test="poDescription!=''">
									                 (<s:property value="poDescription" />)
									              </s:if></td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="uom"/> 
				                               	
				                               	<s:if test="edit==1">
				                               	</s:if> 
				                               	</td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="pro_code"/> 
				                               	</td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="batch_no"/></td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="expiry_date"/></td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="hsnno"/></td>
				                               	<%if(!loginInfo.isLmh()){ %>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="mrp"/></td>
				                               	<%} %>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="vat"/>%</td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="purchase_price"/></td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="quantity"/></td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="pack"/></td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="discount"/></td>
				                               	<!-- For Parihar -->
				                               	<td class=<%=scolomn%> style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="Scheme"/></td>
				                               	<td class=<%=scolomn%> style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="Totalschmedisc"/></td>
				                               	<td class=<%=scolomn%> style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="Amountafterdisc"/></td>
				                               	<%-- <td><s:property value="freeqty"/></td> --%>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="totalfreeqty"/></td>
				                               	<td style="width: 7%;text-align: center;padding-top: 5px;"><s:property value="total"/></td>
				                               </tr>
				                              </s:iterator>
				                               <tr style="border-top: 1px solid #ddd;">
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Sub Total</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="subTotal"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;color: red;border: none;">Discount</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;color: red;border: none;">Rs.<s:property value="discount"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">SGST</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="sgst"/></td>
				                               </tr>
				                              <tr >
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">CGST</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="cgst"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">IGST</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="igst"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Surcharge</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="surcharge"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Credit </td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="credit"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Debit Note</td>
				                               	<td style="font-size: 11px;text-align: right;font-weight: bold;border: none;">Rs.<s:property value="debit"/></td>
				                               </tr>
				                               <tr >
				                               	<td colspan="13" style="font-size: 11px;text-align: right;font-weight: bold;color: green;border: none;">Net Payment</td>
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
		<small>In words: <label id="inword"></label> <b>only</b></small>
	</div>
</div>

</div>
<div class="row" style="padding-top: 80px;">
	<%if(loginInfo.isLmh()){ %>
		<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px;">
				<p>STORE HOD</p>
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px;">
				<p>DEPT HOD</p>
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px;">
				<p>DEAN</p>
			</div>
		</div>
		<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;margin-top: 20px;">
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
				<p>Created by : <s:property value="createdby"/> </p>
				<p>Printed by : <s:property value="printedby"/> </p>
			</div>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
				<a type="button" class="btn btn-primary btn-lg savebigbtn hidden-print"  title="Print" onclick="printpage()">Print</a>
			</div>
		</div>
	<%}else{ %>
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
	<%} %>
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