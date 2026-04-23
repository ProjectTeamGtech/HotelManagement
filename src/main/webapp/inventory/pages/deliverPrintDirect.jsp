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

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 9px !important;
    border-right: none !important;
    border-left: none !important;
}


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
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}
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
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
</head>
<body oncontextmenu="return false;">
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
			<div class="hidden-print">
				<ul class="breadcrumb">
					<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
					<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
						<%if(breadcrumbs.isIscurrent()){ %>
							<li><%=breadcrumbs.getName()%></li>
						<%}else{ %>
							<%if(breadcrumbs.getOn()){ %>
								<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
							<%}else{ %>
								<li><%=breadcrumbs.getName()%></li>
							<%} %>
						<%} %>
						
					<%} %>
				</ul>
			</div>
				
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
				<h4><b>DEPARTMENT REQUEST NOTE</b></h4>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;" id="billanddata">
				<input type="hidden" name="parentid" id="parentid" value="<s:property value="parentid"/>"> 
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset" style="margin-bottom:0px;"><b>Issue Number :</b>&nbsp;<span><s:property value="issueno"/></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>From  :</b>&nbsp;<span ><s:property value="from_location"/></span></p>
					<p class="marboset hidden" style="margin-bottom:0px;"><b>Indent Number :</b>&nbsp;<span><s:property value="indentid"/></span></p>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<%-- <p class="marboset" style="margin-bottom:0px;"><b>Issue Date :</b>&nbsp;<span><s:property value="issued_date"/></span></p> --%>
					<p class="marboset" style="margin-bottom:0px;"><b>To  :</b>&nbsp;<span ><s:property value="to_location"/></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Request Date :</b>&nbsp;<span ><s:property value="request_date"/></span></p>
				</div>
			</div>
							        	
	</div>
</div>

<div class="" style="padding-top: 10px;min-height: 185px;">
	<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Transfer Indents :-</h5>
	<table class="table table-striped table-bordered" style="width:100%;" id="prodtableid">
          <thead>
           <tr>
            <td style="widtd: 4%;">Sr.no</td>
            <td style="widtd: 7%;">HSN Code</td>
            <td class="hidden" style="widtd: 5%;">Id</td>
            <td style="widtd: 17%;">Product Name</td>
            <td style="widtd: 8%;">Batch No</td>
            <td style="widtd: 8%;">Exp Date</td>
            <td style="widtd: 7%;">Issue Qty</td>
            <td class="hidden" style="widtd: 7%;text-align: right;">Unit Rate</td>
            <td class="hidden" style="widtd: 9%;text-align: right;">Amount</td>
            <td class="hidden" style="widtd: 8%;text-align: right;">MRP</td>
            <td class="hidden" style="widtd: 9%;text-align: right;">MRP Amount</td>
           	</tr>
          </thead>
          <%-- <tfoot style="color: green;">
          	<tr>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td style="font-weight: bold;">Total</td>
          		<td></td>
          		<td style="text-align: right;font-weight: bold;"><s:property value="total_amount"/></td>
          		<td></td>
          		<td style="text-align: right;font-weight: bold;" class="hidden"><s:property value="totolmrp"/></td>
          	</tr>
          </tfoot> --%>
          
          <tbody>
          	<%int j=0; %>
          	<%int k=0; %>
          	<s:iterator value="checkmedicinelist">
          	<tr>
          		<td><%=++k%></td>
          		<td><s:property value="hsnno"/></td>
          		<td class="hidden"><s:property value="product_id"/></td>
          		<td><s:property value="product_name"/></td>
          		<td><s:property value="batch_no"/></td>
          		<td><s:property value="expiry_date"/></td>
          		<td><s:property value="stock"/></td>
          		<td style="text-align: right;" class="hidden"><s:property value="sale_price"/></td>
          		<td style="text-align: right;" class="hidden"><s:property value="amountno"/></td>
          		<td style="text-align: right;" class="hidden"><s:property value="mrp"/></td>
          		<td style="text-align: right;" class="hidden"><s:property value="amountmrp"/></td>
          	</tr>
          	</s:iterator>
          	
          	</tbody> 
         </table>
         
</div>

<div class="" style="margin-top: 15px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
<div class="form-group">
		<label for="comment">CMS/CDS Remark: <span style="font-weight: normal;"><s:property value="comment"/></span></label>
	</div>
<div class="form-group" id="handover_to_div">
		<s:if test="mainstatus==6">
		
		</s:if>
		<s:else>
			<s:if test="handover_to==0">
				<s:textfield class="hidden-print form-control" id="handover_to" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_To(<s:property value="parentid"/>)">
			</s:if>
			<s:else>
				<label for="comment">Handover To: <span style="font-weight: normal;"><s:property value="handover_to"/></span></label>
			</s:else>
		</s:else>
		
</div>

</div>
</div>

 


<div class="" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
		<label class="hidden">Terms & Conditions</label>
		<p class="hidden" style="margin-bottom:0px;">1) Delivery Time: Within one week from the date of order.</p>
		<p class="hidden" style="margin-bottom:0px;">2) Item Inspection has to be done before receiving it.</p>
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
		<p class="hidden" style="margin-bottom:0px;">Manager/Purchase Officer</p>
		<p style="margin-bottom:0px;">Transfered by : <s:property value="check_avail_userid"/> | <s:property value="check_availability_date"/></p> 
		<p style="margin-bottom:0px;">Printed by : <s:property value="curr_user"/> | <s:property value="date"/></p>
	</div>
</div>
</div>


<div class="" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<input type="button" onclick="printDiv2('page_printer2')" class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
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