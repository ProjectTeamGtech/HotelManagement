<%@page import="com.apm.Inventory.eu.entity.Product"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style>

@media print {
		.loc td{
			background-color: #6699cc  !important;
			color: white !important;
		}
	}
	p {
    margin: 0 0 1.5px;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.savebigbtn {
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
    line-height: 40px;
}

 @media print
{

body {
    font-size: 9px !important;
}

/* .table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 9px !important;
    border-right: none !important;
    border-left: none !important;
}
 */

.settopbac {
    background-color: #ddd !important;
}
.totalbor {
    background-color: #f5f5f5 !important;
}

    .print_special { border: none !important; } 
    label {
    	font-size: 9px !important;
	}
	p {
	    margin: 0 0 2.5px !important;
	    font-size: 9px !important;
	}
	
	.form-group {
    margin-bottom: 0px !important;
}
.setotas {
    padding: 0px;
    text-align: right;
    color: #008000 !important;
    font-size: 10px;
}
.wordscolr{
	    color: #d07878 !important;
    text-transform: uppercase;
}
.titleset {
    margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    line-height: 20px;
}
h4, .h4 {
    font-size: 10px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:10px !important;
	color: firebrick !important;
}
.titleset {
    margin: 0px;
    color: #6699cc !important;
    border-bottom: 1px dashed #efefef;
    font-size: 15px;
    line-height: 20px;
}
/* .table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
} */
.setotas{
	 padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 10px !important;
}

}
</style>

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

<style>
	.loc{
		background-color: #6699cc !important;
		color: white !important;
	}
	
</style>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>
<body oncontextmenu="return false;">
<%Product product = (Product) session.getAttribute("jobworkprintproduct");%>
<div class="col-lg-12 col-xs-12 col-md-12" id="page_printer2">
<form action="updatefinalAproveProduct">
<div class="">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class=" letterheadhgt" style="border-bottom: 2px solid #6699cc;padding-top: 36px;padding-bottom: 15px;height: 135px;">
				<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
					 	<link href="common/css/printpreview.css" rel="stylesheet" />
						<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
				</div>
			</div>
				
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
				<h4><b>Job Work</b></h4>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;" id="billanddata">
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset" style="margin-bottom:0px;"><b>Voucher Number :</b>&nbsp;<span><%=product.getId() %></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Voucher Date :</b>&nbsp;<span><%=DateTimeUtils.getCommencingDate1(product.getDateTime().split(" ")[0])+" "+product.getDateTime().split(" ")[1] %></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Issue By :</b>&nbsp;<span><%=product.getUserid() %></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Issue Date :</b>&nbsp;<span><%=DateTimeUtils.getCommencingDate1(product.getDate()) %></span></p>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<p class="marboset" style="margin-bottom:0px;"><b>Vendor :</b>&nbsp;<span ><%=product.getVendor() %></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Address :</b>&nbsp;<span ><%=product.getAddress() %></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Mobile :</b>&nbsp;<span ><%=product.getMobile() %></span></p>
					
				</div>
			</div>
							        	
	</div>
</div>

<div class="" style="padding-top: 10px;min-height: 185px;">
	<table class="table my-table" style="width:100%;" id="prodtableid">
          
              <tr class="loc">
              	<td style="width: 1%;">Sr.</td>
              	<!-- <td style="width: 1%;">Voucher No.</td>
              	<td style="width: 8%;">Voucher Date</td>
              	<td style="width: 5%;">Issue by</td>
              	<td style="width: 8%;">Issue Date</td> -->
              	<td style="width: 9%;">Product Type</td>
              	<td style="width: 15%;">Product Name</td>
              	<td style="width: 2%;">Quantity</td>
              	<td style="width: 8%;">Expected Date</td>
              	<td style="width: 15%;">Issued Remark</td>
              	<%if(!product.getStatus().equals("0")){ %>
              	<td style="width: 10%;">Status</td>
              	<td style="width: 16%;">Receipt Remark</td>
              	<td style="width: 5%;">Receipt by</td>
              	<td style="width: 8%;">Receipt Date</td>
              	<%} %>
              </tr>
             
             <tbody>
             	  <%int count=0; %>
                  <s:iterator value="jobworkprint">
                  	<tr>
	                  	<td><%=(++count) %></td>
	                  	<%-- <td><s:property value="parentid"/></td>
	                  	<td><s:property value="dateTime"/></td>
	                  	<td><s:property value="userid"/></td>
	                  	<td><s:property value="date"/></td> --%>
	                  	<s:if test="isinventory==0">
	                  		<td>Inventory</td>
	                  	</s:if>
	                  	<s:else>
	                  		<td>Non-Inventory</td>
	                  	</s:else>
	                  	<td><s:property value="product_name"/></td>
	                  	<td style="text-align: center;"><s:property value="qty"/></td>
	                  	<td><s:property value="expectedDate"/></td>
	                  	<td><s:property value="remark"/></td>
	                  	<%if(!product.getStatus().equals("0")){ %>
	                  	<td>
		                  	<s:if test="status==0">
		                  		-
		                  	</s:if>
		                  	<s:elseif test="status==1">
		                  		Repaired
		                  	</s:elseif>
		                  	<s:elseif test="status==2">
		                  		Un-Repaired
		                  	</s:elseif>
	                  	</td>
	                  	<td><s:property value="comment"/></td>
	                  	<td><s:property value="receipt_userid"/></td>
	                  	<td><s:property value="receipt_datetime"/></td>
	                  	<%} %>
                  	</tr>
                  </s:iterator>
             </tbody>
         </table>
         
</div>
<div class="" style="padding-top: 80px;">
	<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
			<%if(product.getStatus().equals("3")){ %>
				<p style="margin-bottom:0px;">Cancel by : <%=product.getCancel_userid() %> | <%=product.getCancel_date() %></p>
				<p style="margin-bottom:0px;">Cancel Remark : <%=product.getCancel_notes() %></p>
			<%} %>
		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
			<p style="margin-bottom:0px;">Printed by : <s:property value="curr_user"/> | <s:property value="date"/></p>
		</div>
	</div>
</div>


<div class="" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<input type="button" onclick="print()" class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
</div>
</div>

</form>
</div>




<script>
    function printDiv2(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
    //document.body.innerHTML = oldPage;

    //Print Page
    setTimeout(function () {
        print_page();
    }, 2000);

    function print_page() {
        window.print();
    }

    //Restore orignal HTML
    setTimeout(function () {
        restore_page();
    }, 3000);

    function restore_page() {
        document.body.innerHTML = oldPage;
    }
}
	</script>

</body>
</html>