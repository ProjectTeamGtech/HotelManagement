<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
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
<script>
function hideinprint(){
	var x=document.getElementById("checker").checked;
	if(x){
		document.getElementById("indentreq").className="col-lg-12 col-md-12 col-xs-12 hidden-print";
	}
}

</script>
</head>
<body oncontextmenu="return false;">
<div class="col-lg-12 col-xs-12 col-md-12" id="page_printer2">
	 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
<s:if test="mainstatus==4">
	<form action="updatePendingAproveProduct">
</s:if>
<s:else>
	<form action="updatefinalAproveProduct">
</s:else>

<div class="">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class=" letterheadhgt" style="border-bottom: 2px solid #6699cc;padding-top: 36px;padding-bottom: 15px;height: 160px;">
				<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
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
			<!-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print">
				<p>	<a class="btn btn-danger" href="#" onclick="window.history.back()"  title="Indent Dashboard" >Back </a></p>
			</div> -->
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
				<h4><b>DEPARTMENT REQUEST NOTE</b></h4>
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


<div class="col-lg-12 col-md-12 col-xs-12"  id="indentreq" style="background-color: rgba(239, 239, 239, 0.42);padding: 9px;border: 1px dashed #ddd;margin-bottom: 15px;">
	<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Indent Request :-</h5>
	Hide indent request in print &nbsp;&nbsp;<input class="hidden-print" type="checkbox"  id="checker" onclick="hideinprint()">
	<table class="" border="1"  style="width:100%;"> <!-- class="table table-striped table-bordered" -->
         <thead>
           <tr>
            <td style="width: 1%;text-align: center;padding-top: 5px;">Sr.no</td>
            <td style="width: 4%;text-align: center;padding-top: 5px;">Catalogue Id</td>
            <td style="width: 20%;text-align: center;padding-top: 5px;">Product Name</td>
             <%
			  if (loginInfo.isLmh()) {
			%>
			<td style="width: 4%;text-align: center;padding-top: 5px;"><b> Product Code</b></td>
			<%
			} 
			%>
            <%
			  if (loginInfo.isLmh()) {
			%>
			<td style="width: 3%;text-align: center;padding-top: 5px;"><b> UOM</b></td>
			<%
			} 
			%>
             <td style="width: 4%;text-align: center;padding-top: 5px;" class="text-center">Req. Dept Avail. Qty</td>
             <td style="width: 4%;text-align: center;padding-top: 5px;">Req Qty</td>
            <td class="hidden" style="width: 10%;text-align: center;padding-top: 5px;">Sale Price</td>
            <td class="hidden" style="width: 7%;text-align: center;padding-top: 5px;">Price</td>
            <td style="width: 10%;text-align: center;padding-top: 5px;">Approved Qty</td>
            <td style="width: 6%;text-align: center;padding-top: 5px;">Expected Date</td>
            <td width=1px  style="width: 9%;text-align: center;padding-top: 5px;">Business Reason </td>
           	<s:if test="mainstatus==4">
            	<td style="width: 9%;text-align: center;padding-top: 5px;">Status</td>
            </s:if>
            <td style="width: 6%;text-align: center;padding-top: 5px;">Trans. Qty</td>
            <td style="width: 9%;text-align: center;padding-top: 5px;">Transfer Date</td>
            </tr>
          </thead>
          <tbody>
          	<%int i=0; %>
          	<s:iterator value="requestedmedicineList">
          	<tr>
          		<td  style="width: 1%;text-align: center;padding-top: 5px;"><%=++i%></td>
          		<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="Catalogueid"/></td>
          		<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="product_name"/></td>
          		<%
					if (loginInfo.isLmh()) {
				%>
          		<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="Product_code"/></td>
          		<%
			       } 
			    %>
          		<%
					if (loginInfo.isLmh()) {
				%>
          		<td  style="width: 3%;text-align: center;padding-top: 5px;"><s:property value="uom"/></td>
          		<%
			       } 
			    %>
          		<td class="text-center"  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="avail_qty"/></td>
          		<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="requested_qty"/></td>
          		<td class="hidden"><s:property value="unit"/></td>
          		<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="stock"/></td>
          		<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="expectedDate"/></td>
          		<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="comment"/>  
            		<s:if test="mainstatus!=4">
          				 <s:if test="status==0">
          					
          					<s:if test="cancel_req==0">
            					<span><b>- NA</b></span>
            				</s:if>
            				<s:else>
            					<span><b>Cancelled</b></span>
            				</s:else>
          				</s:if> 
          			</s:if>
          		</td>
          		<s:if test="mainstatus==4">
          			<td  style="width: 9%;text-align: center;padding-top: 5px;">
            			<s:if test="status==0">
            				<s:if test="cancel_req==0">
            					<span><b>Not Available</b></span>
            				</s:if>
            				<s:else>
            					<span><b>Cancelled</b></span>
            				</s:else>
          					
          				</s:if>
          				<s:else>
          					<span><b>Transfered</b></span>
          				</s:else>
          				</td>
            		</s:if>
            	<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="totaltransferqt"/></td>
            	<td  style="width: 9%;text-align: center;padding-top: 5px;"><s:property value="transfer_date"/></td>
          	</tr>
          	</s:iterator>
          	</tbody>
         </table>
         
         
</div>
<s:if test="printbeforeapprove==1">
</s:if>
<s:else>


<div class="" style="padding-top: 10px;min-height: 185px;">
	<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Indent Transfer Status :-</h5>
	
	<s:iterator value="checkmedicinelist">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0" id="billanddata">
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset" style="margin-bottom:0px;"><b>Location :</b>&nbsp;<span><s:property value="from_location"/></span></p>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<p class="marboset" style="margin-bottom:0px;"><b>Deliver Date :</b>&nbsp;<span ><s:property value="transfer_date"/></span></p>
				</div>
			</div>
	<table class="table table-striped table-bordered" style="width:100%;" id="prodtableid">
		  <thead>
           <tr>
            <td style="width: 4%;">Sr.no</td>
            <td class="hidden" style="width: 0%;">HSN Code</td>
            <td style="width: 5%;" class="hidden">Id</td>
            <td style="width: 20%;">Product Name</td>
            <%
		        if (loginInfo.isLmh()) {
             %>
            <td style="width: 10%;">Product Code</td>
            <%
		      } 
	        %>
            <%
		        if (loginInfo.isLmh()) {
             %>
            	<td style="width: 5%;">UOM</td>
            <%
		      } 
	        %>
	            <td style="width: 8%;">Batch No</td>
	            <td style="width: 8%;">Exp Date</td>
             <%
			  if (loginInfo.isLmh()) {
			%>
				<td style="width: 30%;"><b> Business Reason</b></td>
			<%
			} 
			%>
            
            <td style="width: 5%;">Issue Qty</td>
            <td style="width: 0%;text-align: right;" class="hidden">Unit Rate</td>
            <td style="width: 0%;text-align: right;" class="hidden">Amount</td>
            <td style="width: 0%;text-align: right;" class="hidden">MRP</td>
            <td style="width: 0%;text-align: right;" class="hidden">MRP Amount</td>
           	<!-- <td style="width: 31%;">Location/Date</td> -->
            </tr>
          </thead>
          <tfoot style="color: green;">
          	<tr>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td class="hidden"></td>
          		<%
		        	if (loginInfo.isLmh()) {
             	%>
            		<td></td>
	            <%
			      } 
		        %>
          		<td></td>
          		<td></td>
          		<td class="hidden" style="font-weight: bold;">Total</td>
          		<td></td>
          		<td class="hidden" style="text-align: right;font-weight: bold;"><s:property value="total_amount"/></td>
          		<td></td>
          		<td class="hidden" style="text-align: right;font-weight: bold;"><s:property value="totolmrp"/></td>
          		<!-- <td></td> -->
          	</tr>
          </tfoot>
          
          <tbody>
          	<%int j=0; %>
          	<%int k=0; %>
          	<s:iterator value="materiallist">
          	<tr>
          		<td><%=++k%></td>
          		<td hidden><s:property value="hsnno"/></td>
          		<td class="hidden"><s:property value="product_id"/></td>
          		<td><s:property value="product_name"/></td>
          		<%
		        if (loginInfo.isLmh()) {
                %>
          		<td><s:property value="Product_code"/></td>
          		<%
		           } 
	            %>
          		<%
		        if (loginInfo.isLmh()) {
                %>
          		<td><s:property value="uom"/></td>
          		<%
		           } 
	            %>
          		<td><s:property value="batch_no"/></td>
          		<td><s:property value="expiry_date"/></td>
          		<%
		        if (loginInfo.isLmh()) {
                %>
          		<td><s:property value="comment"/></td>
          		<%
		           } 
	            %>
          		
          		<td><s:property value="stock"/></td>
          		<td style="text-align: right;"class="hidden" ><s:property value="sale_price"/></td>
          		<td style="text-align: right;" class="hidden"><s:property value="amountno"/></td>
          		<td style="text-align: right;" class="hidden"><s:property value="mrp"/></td>
          		<td style="text-align: right;" class="hidden"><s:property value="amountmrp"/></td>
          		<%-- <td><s:property value="from_location"/>/<s:property value="transfer_date"/></td> --%>
          	</tr>
          	</s:iterator>
          	
          	</tbody> 
         </table>
        </s:iterator>
</div>
</s:else>

<s:if test="printbeforeapprove==1">
</s:if>
<s:else>

<div class="" style="margin-top: 15px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<div class="form-group">
		<label for="comment">Admin Remark: <span style="font-weight: normal;"><s:property value="admin_notes"/></span></label><br>
		<label for="comment">CMS/CDS Remark: <span style="font-weight: normal;"><s:property value="check_availability_note"/></span></label>
	</div>
	<div class="form-group" id="handover_to_div">
		<s:if test="mainstatus==6">
		
		</s:if>
		<s:else>
				<s:if test="mainstatus==4">
				<s:if test="handover_to==0">
					<s:if test="status==1">
						<%if(loginInfo.getUserType()==2){ %>
							<s:textfield cssClass="hidden-print form-control" id="handover_to" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_To(<s:property value="parentid"/>)">
						<%} %>
					</s:if>
					<s:elseif test="status==2">
						<s:textfield cssClass="hidden-print form-control" id="handover_to" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_To(<s:property value="parentid"/>)">
					</s:elseif>
					<s:else>
						<s:textfield cssClass="hidden-print form-control" id="handover_to" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_To(<s:property value="parentid"/>)">
					</s:else>
				</s:if>
				<s:else>
				<s:if test="status==1">
						<label for="comment">Handover To: <span style="font-weight: normal;"><s:property value="handover_to"/></span></label><br>
						<s:textfield cssClass="hidden-print form-control" id="handover_to2" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_ToNew(<s:property value="parentid"/>)">
						
				</s:if>
				<s:elseif test="status==2">
								<label for="comment">Handover To: <span style="font-weight: normal;"><s:property value="handover_to"/></span></label><br>
						<s:textfield cssClass="hidden-print form-control" id="handover_to2" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_ToNew(<s:property value="parentid"/>)">
				
				</s:elseif>
				<s:else>
						<label for="comment">Handover To: <span style="font-weight: normal;"><s:property value="handover_to"/></span></label><br>
				</s:else>
			</s:else>
		</s:if>
		<s:else>
			<s:if test="handover_to==0">
			<s:if test="status==1">
				<%if(loginInfo.getUserType()==2){ %>
					<s:textfield cssClass="hidden-print form-control" id="handover_to" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_To(<s:property value="parentid"/>)">
				<%} %>
			</s:if>
			<s:elseif test="status==2">
				<s:textfield cssClass="hidden-print form-control" id="handover_to" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_To(<s:property value="parentid"/>)">
			</s:elseif>
			<s:else>
					<s:textfield cssClass="hidden-print form-control" id="handover_to" autofocus="autofocus" placeholder="Handover To" cssStyle="width: 15%;background-color: beige;"></s:textfield><input type="button" value="Handover" class="btn btn-primary hidden-print" onclick="addHandover_To(<s:property value="parentid"/>)">
			</s:else>
			
		</s:if>
		<s:else>
			<s:if test="status==1">
				<s:if test="handover_to==0">
					<label for="comment">Handover To: <span style="font-weight: normal;"></span></label>
				</s:if>
				<s:else>
					<label for="comment">Handover To: <span style="font-weight: normal;"><s:property value="handover_to"/></span></label>
				</s:else>
			</s:if>
			<s:elseif test="status==2">
				<s:if test="handover_to==0">
					<label for="comment">Handover To: <span style="font-weight: normal;"></span></label>
				</s:if>
				<s:else>
					<label for="comment">Handover To: <span style="font-weight: normal;"><s:property value="handover_to"/></span></label>
				</s:else>
			</s:elseif>
			<s:else>
					<s:if test="handover_to==0">
					<label for="comment">Handover To: <span style="font-weight: normal;"></span></label>
				</s:if>
				<s:else>
					<label for="comment">Handover To: <span style="font-weight: normal;"><s:property value="handover_to"/></span></label>
				</s:else>
			</s:else>
		</s:else>			
		</s:else>
		</s:else>
		
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 hidden" style="padding:0px;    margin-top: 15px;">
	<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">PO List :-</h5>
	<table class="table table-striped table-bordered" style="width:50%;">
          <thead>
           <tr>
            	<th style="width: 2%;background-color: brown;">Sr.no</th>
            	<th style="width: 20%;background-color: brown;">Product Name</th>
            	<th style="width: 10%;background-color: brown;">Qty</th>
           </tr>
          </thead>
          <tbody id="potbodyid">
          <%int s=0,q=0; %>
          <s:iterator value="polist">
          	<tr>
          		<td><%=++s %></td>
          		<td><s:property value="prod_name"/></td>
          		<td><s:property value="qty"/></td>
          	</tr>
          </s:iterator>
          	</tbody>
         </table>
		
	</div>
	
</div>
</div>
</s:else>
<s:if test="printbeforeapprove==1">
</s:if>
<s:else>
<div class="" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
		<%-- <label>Terms & Conditions</label>
		<%int x=1; %>
		<s:iterator value="termsandconditionlist">
			<p style="margin-bottom:0px;"><%=x++%> <s:property value="name"/></p>
		</s:iterator> --%>
		<p style="margin-bottom:0px;" class="hidden">1) Delivery Time: Within one week from the date of order.</p>
		<p style="margin-bottom:0px;" class="hidden">2) Item Inspection has to be done before receiving it.</p>
		<!-- <br> -->
		<!-- <a class="btn btn-primary" href="#" onclick="opencPopup('TermsConditionMaster')">Add Terms And Condition</a> -->
	</div>
	
      <%
		if (loginInfo.isLmh()) {
      %>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
		<p style="margin-bottom:0px;margin-right: 40px;">Store Incharge</p>
		<p style="margin-bottom:0px;">Transfered by : <s:property value="check_avail_userid"/> | <s:property value="check_availability_date"/></p>
		<p style="margin-bottom:0px;">Printed by : <s:property value="curr_user"/> | <s:property value="date"/></p>
	</div>
	<%
		} else {
	%>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
		<p style="margin-bottom:0px;">Manager/Purchase Officer</p>
		<p style="margin-bottom:0px;">Transfered by : <s:property value="check_avail_userid"/> | <s:property value="check_availability_date"/></p>
		<p style="margin-bottom:0px;">Printed by : <s:property value="curr_user"/> | <s:property value="date"/></p>
	</div>
	<%
		} 
	%>
</div>
</div>
</s:else>

<div class="" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<s:if test="printbeforeapprove==1">
	<input type="button" onclick="printDiv2('page_printer2')" class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
	</s:if>
	<s:else>
	<s:if test="mainstatus==4">
			<s:if test="status==1">
			<input type="button" onclick="printDiv2('page_printer2')" class="btn btn-primary savebigbtn hidden-print" value="PRINT"/>
			<input type="submit"  class='btn btn-success savebigbtn hidden-print' style="float: right;" value='RECEIVED'>
		</s:if>
		<s:elseif test="status==2">
			<input type="button" onclick="printDiv2('page_printer2')"  class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
		</s:elseif>
		<s:elseif test="status==4">
			<input type="button" onclick="printDiv2('page_printer2')"  class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
		</s:elseif>
		<s:else>
			<input type="button" onclick="printDiv2('page_printer2')" class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
		</s:else>
	</s:if>
	<s:elseif test="mainstatus==5">
		<input type="button" onclick="printDiv2('page_printer2')"  class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
	</s:elseif>
	<s:elseif test="mainstatus==6">
		<input type="button" onclick="printDiv2('page_printer2')"  class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
	</s:elseif>
	<s:else>
		<s:if test="status==1">
			<input type="button" onclick="printDiv2('page_printer2')" class="btn btn-primary savebigbtn hidden-print" value="PRINT"/>
			<input type="submit"  class='btn btn-success savebigbtn hidden-print' style="float: right;" value='RECEIVED'>
		</s:if>
		<s:elseif test="status==2">
			<input type="button" onclick="printDiv2('page_printer2')"  class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
		</s:elseif>
		<s:elseif test="status==4">
			<input type="button" onclick="printDiv2('page_printer2')"  class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
		</s:elseif>
		<s:else>
			<input type="button" onclick="printDiv2('page_printer2')" class="btn btn-primary savebigbtn hidden-print" style="float: right;" value="PRINT"/>
		</s:else>
	</s:else>
	</s:else>
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