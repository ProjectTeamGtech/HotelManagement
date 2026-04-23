<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
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
<style>
	.loc{
		background-color: #6699cc;
		color: white;
	}
	@media print {
		.loc{
			background-color: #6699cc  !important;
			color: white;
		}
	}
</style>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<script>
function hideinprint(){
	var x=document.getElementById("checker").checked;
	if(x){
		document.getElementById("indentreq").className="col-lg-12 col-md-12 col-xs-12 hidden-print";
	} 
}

</script>

<SCRIPT type="text/javascript">

    $(document).ready(function() {
		
	 	 document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false);
		
	});
 
</SCRIPT>
</head>
<body >
<div class="col-lg-12 col-xs-12 col-md-12" id="page_printer2">
	 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>

<div class="">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class=" letterheadhgt" style="border-bottom: 2px solid #6699cc;padding-top: 36px;padding-bottom: 15px;height: 135px;">
				<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
					 	<link href="common/css/printpreview.css" rel="stylesheet" />
						<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
				</div>
			</div>
				<s:if test="showbreadcrumbs==1">
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
				</s:if>
						
				<%int isdirect=0; %>
				<s:if test="isadjustment==0 || isadjustment==1">
					<%isdirect=1; %>
				</s:if>
				
			<!-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print">
				<p>	<a class="btn btn-danger" href="#" onclick="window.history.back()"  title="Indent Dashboard" >Back </a></p>
			</div> -->
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
				<s:if test="isadjustment==0">
					<h4><b>PRODUCT DIRECT ADJUSTMENT</b></h4>
				</s:if>
				<s:elseif test="isadjustment==2">
					<h4><b>PRODUCT REQUEST ADJUSTMENT</b></h4>
				</s:elseif>
				<s:elseif test="isadjustment==1">
					<h4><b>PRODUCT DIRECT EDIT</b></h4>
				</s:elseif>
				<s:elseif test="isadjustment==3">
					<h4><b>PRODUCT REQUEST EDIT</b></h4>
				</s:elseif>
				
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;" id="billanddata">
				<input type="hidden" name="parentid" id="parentid" value="<s:property value="parentid"/>"> 
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset" style="margin-bottom:0px;"><b>Issue Number :</b>&nbsp;<span><s:property value="issueno"/></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Requested From  :</b>&nbsp;<span ><s:property value="from_location"/></span></p>
					<p class="marboset hidden" style="margin-bottom:0px;"><b>Indent Number :</b>&nbsp;<span><s:property value="indentid"/></span></p>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<%-- <p class="marboset" style="margin-bottom:0px;"><b>Issue Date :</b>&nbsp;<span><s:property value="issued_date"/></span></p> --%>
					<p class="marboset" style="margin-bottom:0px;"><b>Request Date :</b>&nbsp;<span ><s:property value="request_date"/></span></p>
					<p class="marboset" style="margin-bottom:0px;"><b>Request User :</b>&nbsp;<span><s:property value="userid"/></span></p>
				</div>
			</div>
							        	
	</div>
</div>






<div class="" style="padding-top: 10px;min-height: 185px;">
	<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">LIST :-</h5>
		
		<s:if test="isadjustment==0 || isadjustment==2">
			<table class="table table-striped table-bordered" style="width:100%;" id="prodtableid">
	          <thead>
	           <tr class="loc">
		            <td style="widtd: 4%;">Sr.no</td>
		            <td style="widtd: 7%;">HSN Code</td>
		            <td style="widtd: 5%;" class="hidden">Id</td>
		            <td style="widtd: 20%;">Product Name</td>
		            <td style="widtd: 8%;">Batch No</td>
		            <td style="widtd: 8%;">Exp Date</td>
		            <td style="widtd: 5%;">Adjustment Type</td>
		            <td style="widtd: 5%;">Current Stock</td>
		            <td style="widtd: 5%;">Before Qty</td>
		            <td style="widtd: 5%;">Change Qty</td>
		            <td style="widtd: 5%;">After Qty</td>
		            <td style="widtd: 10%;">Req. Remark</td>
		            <%if(isdirect==0){ %>
		            	<td style="widtd: 10%;">Approve Remark</td>
		            <%} %>
		            
	            </tr>
	          </thead>
	          
	          <tbody>
	          	<%int j=0; %>
	          	<%int k=0; %>
	          	<s:iterator value="childtransferlist">
	          	<tr>
	          		<td><%=++k%></td>
	          		<td><s:property value="hsnno"/></td>
	          		<td class="hidden"><s:property value="product_id"/></td>
	          		<td><s:property value="product_name"/></td>
	          		<td><s:property value="batch_no"/></td>
	          		<td style='color:<s:property value="color"/>'><s:property value="expiry_date"/></td>
	          		<td>
	          		<s:if test="adjustment_type==1">
						Excess Product Adjustment
					</s:if>
					<s:elseif test="adjustment_type==2">
						Shortage Product Adjustment
					</s:elseif>
					<s:elseif test="adjustment_type==3">
						Defective Product Adjustment
					</s:elseif>
					<s:else>
						Expired/Dead Product Adjustment
					</s:else>
					</td>
	          		<td><s:property value="stock"/></td>
	          		<td><s:property value="pre_stock"/></td>
	          		<td><s:property value="change_qty"/></td>
	          		<td><s:property value="current_qty"/></td>
	          		<td><s:property value="remark"/></td>
	          		<%if(isdirect==0){ %>
	          			<td><s:property value="Admin_approve_remark"/></td>
		            <%} %>
	          		
	          	</tr>
	          	</s:iterator>
	          	</tbody> 
	         </table>
        </s:if>
		<s:elseif test="isadjustment==1 || isadjustment==3">
			<table class="table table-striped table-bordered" style="width:100%;" id="prodtableid">
	          <thead>
	           		<tr class="loc">
			            <th style="width: 1%;">Sr.no</th>
			            <th style="width: 15%;">Product Name</th>
			            <th style="width: 2%;">Hsnno</th>
			            <th style="width: 2%;" class="hidden">Pre. Pack</th>
			            <th style="width: 2%;" class="hidden">Req. Pack</th>
			            <th style="width: 4%;">Pre. MRP</th>
			            <th style="width: 4%;">Req. MRP</th>
			            <th style="width: 4%;">Pre. Sale Price</th>
			            <th style="width: 4%;">Req. Sale Price</th>
			            <th style="width: 3%;">Pre. Pur. Price</th>
			            <th style="width: 3%;">Req. Pur. Price</th>
			            <th style="width: 4%;">Pre. Batch</th>
			            <th style="width: 4%;">Req. Batch</th>
			            <th style="width: 3%;">Pre. Expiry Date</th>
			            <th style="width: 3%;">Req. Expiry Date</th>
			            <th style="width: 3%;">Pre. GST</th>
			            <th style="width: 3%;">Req. GST</th>
			            <th style="width: 2%;">Pre. P.Shelf</th>
			            <th style="width: 2%;">Req. P.Shelf</th>
			            <th style="width: 2%;">Pre. S.Shelf</th>
			            <th style="width: 2%;">Req. S.Shelf</th>
			            <%if(isdirect==0){ %>
			            	<th style="width: 10%;">Req. Remark</th>
			            	<th style="width: 10%;">Approve Remark</th>
		            	<%} %>
				        
		           	</tr>
	          </thead>
	          
	          <tbody>
	          	<%int j=0; %>
	          	<%int k=0; %>
	          	<s:iterator value="childtransferlist">
	          	<tr>
	          		<td><%=++k%></td>
	          		<td><s:property value="product_name"/></td>
	          		<td><s:property value="hsnno"/></td>
	          		<td class="hidden"><s:property value="pre_pack"/></td>
	          		<td class="hidden"><s:property value="curr_pack"/></td>
	          		<td><s:property value="pre_mrp"/></td>
	          		<td><s:property value="curr_mrp"/></td>
	          		<td><s:property value="pre_saleprice"/></td>
	          		<td><s:property value="curr_saleprice"/></td>
	          		<td><s:property value="pre_purprice"/></td>
	          		<td><s:property value="curr_purprice"/></td>
	          		<td><s:property value="pre_batch"/></td>
	          		<td><s:property value="curr_batch"/></td>
	          		<td style='color:<s:property value="preexpcolor"/>'><s:property value="pre_expdate"/></td>
	          		<td style='color:<s:property value="currexpcolor"/>'><s:property value="curr_expdate"/></td>
	          		<td><s:property value="pre_gst"/></td>
	          		<td><s:property value="curr_gst"/></td>
	          		<td><s:property value="pre_pshelf"/></td>
	          		<td><s:property value="curr_pshelf"/></td>
	          		<td><s:property value="pre_sshelf"/></td>
	          		<td><s:property value="curr_sshelf"/></td>
	          		<%if(isdirect==0){ %>
	          			<td><s:property value="remark"/></td>
	          			<td><s:property value="child_approve_remark"/></td>
		            <%} %>
	          	</tr>
	          	</s:iterator>
	          	</tbody> 
	         </table>
		</s:elseif>
		
		
		
</div>
<div class="" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
		<s:if test="request_statuss==2">
			<p style="margin-bottom:0px;">Rejected Remark : <s:property value="rejectremark"/></p>
		</s:if>
	</div>
	 
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
		<s:if test="isadjustment==2 || isadjustment==3">
			<s:if test="request_statuss==2">
				<p style="margin-bottom:0px;">Rejected by : <s:property value="check_avail_userid"/> | <s:property value="check_availability_date"/></p>
			</s:if>
			<s:else>
				<p style="margin-bottom:0px;">Approve by : <s:property value="check_avail_userid"/> | <s:property value="check_availability_date"/></p>
			</s:else>
			
		</s:if>
		<p style="margin-bottom:0px;">Printed by : <s:property value="curr_user"/> | <s:property value="date"/></p>
	</div>
</div>
</div>

<div class="" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<input type="button" onclick="printpage()" class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
</div>
</div>

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