<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
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
</style>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>

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


</head>
<body>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
<div class="col-lg-12 col-xs-12 col-md-12" oncontextmenu="return false;">
<s:if test="mainstatus==4">
	<form action="transferproductdataProduct" id="deliverproductsubmit"  method="post">
</s:if>
<s:else>
   <form action="transferrequestedproductdataProduct" id="deliverproductsubmit"  method="post">
</s:else>
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

<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden" style="border-bottom: 1px solid #ddd;" id="letterHead">
				<h4>SHREE NARAYANA PHARMACY</h4>
				<h5>SHREE NARAYANA HOSPITAL (A unit of Health-Tech Chattisgarh Pvt.Ltd), NEAR GANJ MANDI, BEHIND SECTOR-5, DEVENDRA NAGAR, PANDRI, RAIPUR-492001 CHHATTISGARH</h5><h5>Website:http://www.snh.org.in, Email:info@snh.org.in, Contact : 0771-3001234/35/36</h5>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print">
				<p>	<a class="btn btn-danger hidden" href="#" onclick="window.history.back()"  title="Indent Dashboard" >Back </a></p>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
				<h4><b>DEPARTMENT REQUEST NOTE</b></h4>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;padding:0px;margin-bottom: 15px;" id="billanddata">
				<input type="hidden" name="parentid" id="parentid" value="<s:property value="parentid"/>"> 
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: left;">
					<p class="marboset"><b>Issue Number :</b>&nbsp;<span><s:property value="issueno"/></span></p>
					<p class="marboset"><b>Requested From  :</b>&nbsp;<span ><s:property value="from_location"/></span></p>
					<p class="marboset hidden"><b>Indent Number :</b>&nbsp;<span><s:property value="indentid"/></span></p>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="text-align: right;">
					<p class="marboset hidden"><b>Issue Date :</b>&nbsp;<span><s:property value="issue_date"/></span></p>
					<p class="marboset"><b>Request Date :</b>&nbsp;<span ><s:property value="request_date"/></span></p>
					<p class="marboset"><b>Request User :</b>&nbsp;<span><s:property value="userid"/></span></p>
					
				</div>
			</div>
							        	
	</div>
</div>
	

<s:hidden name="location" id="indentlocationid"></s:hidden>
<div class="row" style="background-color: rgba(239, 239, 239, 0.42);padding: 0px;border: 1px dashed #ddd;">
	<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Request Indents :-</h5>
	<div class="depart1">
		<table class="table table-striped table-bordered" style="width:100%;">
         <thead>
           <tr>
            <th style="width: 2%;">Sr</th>
            <th style="width: 5%;">Catalogue ID</th>
            <th style="width: 15%;">Product Name</th>
            <%
		        if (loginfo.isLmh()) {
             %>
            <th style="width: 15%;">Product Code</th>
            <%
		      } 
	        %>
             <%
		        if (loginfo.isLmh()) {
             %>
            <th style="width: 15%;">UOM</th>
            <%
		      } 
	        %>
            <th style="width: 5%;">Store Avail Qty</th>
            <th style="width: 5%;" class="text-center">Req. Dept Avail. Qty</th>
            <!-- <th class="hidden" style="width: 10%;">Sale Price</th> -->
           <!--  <th style="width: 7%;">Unit Price</th> -->
            <th style="width: 4%;" class="text-center">Req Qty</th>
            <th style="width: 10%;" class="text-center">Approved Qty</th>
            <th style="width: 6%;">Expected Date</th>
            <th style="width: 15%;">Business Reason </th>
            <th style="width: 5%;" class="text-center">Trans. Qty</th>
            <th style="width: 7%;">Status</th>
            <th style="width: 10%;">Cancel Note</th>
            <s:if test="delipo==1">
            	<th style="width: 2%;">Cancel</th>
           	</s:if>
           	<th style="width: 4%;">Replace</th>
           </tr>
          </thead>
          <tbody id="reqlisttbody"> 
          	<%int i=0; %>
          	<s:iterator value="requestedmedicineList">
          	<tr>
          		<td><%=++i%></td>
          		<td><s:property value="catalogueid"/></td>
          		<td><s:property value="product_name"/></td>
          		<%
		        if (loginfo.isLmh()) {
                %>
          		<td><s:property value="pro_code"/></td>
          		<%
		           } 
	            %>
          		<%
		        if (loginfo.isLmh()) {
                %>
          		<td><s:property value="uom"/></td>
          		<%
		           } 
	            %>
          		<td class="text-center"><s:property value="store_avail_stock"/></td>
          		<td class="text-center"><s:property value="avail_qty"/></td>
          		<%-- <td><s:property value="unit"/></td> --%>
          		<td class="text-center"><s:property value="requested_qty"/></td>
          		<td class="text-center"><s:property value="stock"/></td>
          		<td><s:property value="expectedDate"/></td>
          		<td><s:property value="comment"/></td>
          		<td class="text-center"><s:property value="totaltransferqt"/></td>
          		<td>
          			<s:if test="status==0">
          				<s:if test="cancel_req==0">
          					<span><b>Not Transferred</b></span>
          				</s:if>
          				<s:else>
          					<span><b>Cancelled</b></span>
          				</s:else>
          			</s:if>
          			<s:else>
          				<span><b>Transferred</b></span>
          			</s:else>
          		</td>
          		<td><s:property value="cancel_req_note"/></td>
          		<s:if test="delipo==1">
          		<s:if test="status==0">
          		<s:if test="cancel_req==0">
          			<td style="text-align: center;"><a href="#" onclick="cancelRequestedRequest(<s:property value="id"/>,<s:property value="parentid"/>)"><i class="fa fa-times fa-2x text-danger" ></i></a></td>
          		</s:if>
          		<s:else>
          			<td></td>
          		</s:else>
          		</s:if>
          		<s:else>
          			<td></td>
          		</s:else>
          		</s:if>
          		<td style="text-align: center;">
          		<s:if test="status==0">
       				<s:if test="cancel_req==0">
       					<%if(loginfo.isChange_indent_product()  || loginfo.getUserType()==2){ %>
       						<a href="#" onclick="getreplaceindentproductpopup(<s:property value="catalogueid"/>,<s:property value="id"/>,<s:property value="parentid"/>)"><i class="fa fa-reply-all" aria-hidden="true"></i></a>
       					<%} %>
          				
       					
       				</s:if>
          		</s:if>
          		</td>
          	</tr>
          	</s:iterator>
          	</tbody>
         </table>
	
	</div>
	
         
         
</div>
<div class="row" style="padding-top: 10px;min-height: 185px;">
	<span><h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Available Indents :-</h5> <span style="float: right;margin-top: -21px;" class="hidden"><label style="color: deeppink;" class="checkbox-inline"><s:if test="mainstatus==4"><input type="checkbox" id="checkbox1" onclick="checkOtherLocationAfterPo()" style="margin-top: 1px;"></s:if><s:else><input type="checkbox" id="checkbox1" onclick="checkOtherLocation()" style="margin-top: 1px;"></s:else>Check Other Location</label></span></span>
	<div class="depart1">
		<table class="table table-striped table-bordered" style="width:100%;" id="prodtableid">
          <thead>
           <tr>
            <th style="width: 3%;">Sr.no</th>
            <th hidden style="width: 5%;">HSN Code</th>
            <th style="width: 20%;">Product Name</th>
            <%
		        if (loginfo.isLmh()) {
             %>
            <th style="width: 15%;">Product Code</th>
            <%
		      } 
	        %>
            <%
		        if (loginfo.isLmh()) {
             %>
            <th style="width: 15%;">Uom</th>
            <%
		      } 
	        %>
            
            <th style="width: 5%;">Batch No</th>
            <th style="width: 5%;">ExpDt</th>
            <th style="width: 9%;">From Location</th>
            <%
		        if (loginfo.isKalmegha()) {
             %>
            <th style="width: 15%;">Quantity Limit</th>
            <th style="width: 15%;">Extra Requested Qty</th>
            <th style="width: 15%;">Total Extra Requested Qty</th>
            <th style="width: 8%;" class="hidden"></th>
            <%
		      } 
	        %>
            <th style="width: 3%;">Instock</th>
            <th style="width: 4%;">Required Qty</th>
            <th style="width: 1%;"></th>
            <s:if test="mainstatus==4">
            	
            </s:if>
            <s:else>
            	<th style="width: 1%;"></th>
            </s:else>
           </tr>
          </thead>
          <tbody>
          	<%int j=0; %>
          	<%int k=0; %>
          	<s:iterator value="checkmedicinelist">
          	<s:if test="status==0 && stockqty>Limit_qty">
          	<tr style="color: red;">
          		</s:if>
          		<s:else>
          		<tr>
          		</s:else>
          		<td><input type="hidden" value="<s:property value="product_id"/>" id="indent_prodid<%=j%>" class="form-control" name="productdata[<%=j%>].product_id" >
          		<%=++k%>
          		<input type="hidden" class="checkclass" value="<%=j %>" >
          		</td>
          		
          		<td hidden><s:property value="hsnno"/><input type="hidden" value="<s:property value="hsnno"/>" class="form-control"  name="productdata[<%=j%>].hsnno" ></td>
          		<td><s:property value="product_name"/><input type="hidden"  value="<s:property value="product_name"/>" class="form-control"  name="productdata[<%=j%>].product_name" ></td>
          		<%
		        if (loginfo.isLmh()) {
                %>
          		<td><s:property value="Pro_code"/><input type="hidden"  value="<s:property value="Pro_code"/>" class="form-control"  name="productdata[<%=j%>].Pro_code" ></td>
          		<%
		           } 
	            %>
          		
          		<%
		        if (loginfo.isLmh()) {
                %>
          		<td><s:property value="uom"/><input type="hidden"  value="<s:property value="uom"/>" class="form-control"  name="productdata[<%=j%>].uom" ></td>
          		<%
		           } 
	            %>
          		<td><s:property value="batch_no"/><input type="hidden"  value="<s:property value="batch_no"/>" class="form-control" name="productdata[<%=j%>].batch_no" ></td>
          		<td><s:property value="expiry_date"/><input type="hidden" value="<s:property value="expiry_date"/>" class="form-control" name="productdata[<%=j%>].expiry_date" ></td>
          		<td><s:property value="fromlocation"/><input type="hidden" value="<s:property value="fromlocation"/>" class="form-control" name="productdata[<%=j%>].fromlocation" ></td>
          		<%
		        if (loginfo.isKalmegha()) {
                %>
          		<td><s:property value="Limit_qty"/><input type="hidden"  value="<s:property value="Limit_qty"/>" class="form-control"  name="productdata[<%=j%>].Limit_qty" ></td>
          		<td><s:property value="Req_limit_qty"/><input type="hidden"  value="<s:property value="Req_limit_qty"/>" class="form-control"  id="Req_limit_qty<%=j%>" name="productdata[<%=j%>].Req_limit_qty" ></td>
          		<td><s:property value="Extra_limit_qty"/><input type="hidden"  value="<s:property value="Extra_limit_qty"/>" class="form-control"  id="Extra_limit_qty<%=j%>" name="productdata[<%=j%>].Extra_limit_qty" ></td>
          		
          		<td class="hidden"><s:property value="status"/><input type="hidden"  value="<s:property value="status"/>" class="form-control"  id="status<%=j%>" name="productdata[<%=j%>].status" ></td>
          		<td class="text-center hidden">
										<s:if test="status==0 && Req_limit_qty!=0 ">
							           <input onclick="approvelimitqty(<s:property value="Parentid"/>,<s:property value="Catalogueid"/>)" type='button' value='Approve' class='btn btn-primary' >
											
										 </s:if> 
										<s:else>
											Approved
										</s:else>
									</td>
          		<%
		           } 
	            %>
          		<td><s:property value="stock"/><input type="hidden" value="<s:property value="stock"/>" class="form-control" id="stock<%=j%>" name="productdata[<%=j%>].stock" ></td>
          		<td>
          			<%-- <s:if test="avail_stock>=req_qty">
          				<input type="text" value="<s:property value="reqqty"/>" onmouseout="checkNotGreterThanStock(<%=j%>)"  onblur="checkNotGreterThanStock(<%=j%>)" id="reqqty<%=j%>" class="form-control" name="productdata[<%=j%>].reqqty" style="background-color: cornsilk;">
          			</s:if>
          			<s:else>
          				<input type="text" value="<s:property value="stock"/>" onmouseout="checkNotGreterThanStock(<%=j%>)" onblur="checkNotGreterThanStock(<%=j%>)" id="reqqty<%=j%>" class="form-control" name="productdata[<%=j%>].reqqty" style="background-color: cornsilk;">
          			</s:else>  --%>
          			<input type="number" value="<s:property value="stockqty"/>" onmouseout="checkNotGreterThanStock(<%=j%>)"  onblur="checkNotGreterThanStock(<%=j%>)" id="reqqty<%=j%>" class="form-control" name="productdata[<%=j%>].reqqty" style="background-color: cornsilk;">
          		</td>
          		<td><a href="#" onclick="deleteRequetedRow(this)"><i class="fa fa-times fa-2x text-danger" ></i></a></td>
          		<s:if test="mainstatus==4">
            	
            	</s:if>
            	<s:else>
            		<td><a href="#" onclick="addOtherToTempPo(<s:property value="product_id"/>,<%=j%>,<s:property value="reqqty"/>,<s:property value="catalogueid"/>)"><i class="fa fa-plus-square fa-2x text-success"></i></a></td>
          		</s:else>
          		<%j++; %>
          	</tr>
          	</s:iterator>
          	</tbody> 
          	<tbody id="otherlocationid"></tbody>
          	<input type="hidden" name="count" id="count" value="<%=k%>">
         </table>
	</div>
 <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">	
	<div class="form-group">
		  <label for="comment">Remark:</label>
		  <textarea class="form-control" rows="3" id="comment" name="comment"></textarea>
		</div>
		</div>
         </form>
</div>

<div class="row" style="padding-top: 80px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
		<p class="hidden">Prepared by : userid/date</p>
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;text-align:right;">
		<p class="hidden">Manager/Purchase Officer</p>
	</div>
</div>
</div>
</form>

<div class="row">
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<p>ADMIN REMARK : <span><s:property value="admin_notes"/></span></p>
	</div>
</div>

<div class="row" style="margin-top: 15px;">
<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
			 <s:if test="mainstatus==4">
				
			</s:if>
			<s:else>
   				<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">PO List :-</h5>
   				
   					<table class="table table-striped table-bordered" id="indentpolisttable" style="width:50%;">
          <thead>
           <tr>
            	<th style="width: 2%;background-color: brown;">Sr.no</th>
            	<th style="width: 20%;background-color: brown;">Product Name</th>
            	<th style="width: 10%;background-color: brown;">Qty</th>
            	<th style="width: 2%;background-color: brown;"><i class="fa fa-times fa-2x" aria-hidden="true"></i></th>
           </tr>
          </thead>
          <tbody id="potbodyid">
			 <%int s=1,q=0; %>   
		    
          <s:iterator value="polist">
          	<tr>
          		<td><%=s%><input type="hidden" value="<s:property value="id"/>" name="podata[<%=q%>].id" ></td>
          		<td><s:property value="prod_name"/><input type="hidden" value="<s:property value="prod_name"/>" class="form-control" name="podata[<%=q%>].prod_name" ></td>
          		<td><input type="number" value="<s:property value="qty"/>" class="form-control" name="podata[<%=q%>].qty" style="background-color: cornsilk;"></td>
          		<td><a href="#" onclick="deleteIndentPOListTempReq(this,'indentpolisttable',<s:property value="id"/>,<s:property value="catalogueid"/>)" ><i class="fa fa-times text-danger fa-2x" aria-hidden="true"></i></a></td>
          		<%++q; %>
          		<%s++; %>
          	</tr>
          </s:iterator>
          	</tbody>
         </table>
   				
			
				<input type="hidden" name="pocount" id="pocount" value="<%=q%>">
			</s:else>  
	
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
	<!-- <div class="form-group">
		  <label for="comment">Remark:</label>
		  <textarea class="form-control" rows="3" id="comment" name="comment"></textarea>
		</div> -->
		<!--<a type="button" class="btn btn-primary btn-lg savebigbtn hidden-print" style="float: right;"  title="Transfer Stock">Transfer</a>
	-->
	<%
	  if (loginfo.isKalmegha()) {
    %>
	<s:if test="delipo==1">
	<div id="btnhideid">
		<input type="button" class="btn btn-primary btn-lg savebigbtn hidden-print" style="float: right;" onclick="deilverproductfromstorekalmegh()"  title="Transfer Stock" value="Deliver">
	 </div>
	 <s:if test="mainstatus==4">
            </s:if>
            <s:else>
            <div id="delivertopoque">
            	<input type="button" class="btn btn-primary btn-lg savebigbtn hidden-print" style="float: right;margin-right: 15px;" onclick="createPoOnTransfer()"  title="Add to PO Que" value="Add to PO Que">
            </div>
            </s:else>
	</s:if>
	
	<%}else{ %>
	<s:if test="delipo==1">
	<div id="btnhideid">
		<input type="button" class="btn btn-primary btn-lg savebigbtn hidden-print" style="float: right;" onclick="deilverproductfromstore()"  title="Transfer Stock" value="Deliver">
	 </div>
	 <s:if test="mainstatus==4">
            </s:if>
            <s:else>
            <div id="delivertopoque">
            	<input type="button" class="btn btn-primary btn-lg savebigbtn hidden-print" style="float: right;margin-right: 15px;" onclick="createPoOnTransfer()"  title="Add to PO Que" value="Add to PO Que">
            </div>
            </s:else>
	</s:if>
<%} %>	
	</div>
</div>
</div>

</div>


<!-- Modal -->
<div id="deletemodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="invnetoryparentid">
      		<input type="hidden" id="invnetorychildid">
        	<textarea rows="3"  class="form-control" id="delete_reason" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="deleteRequestedEntry()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>

<!-- Adjustment process -->
	 <div class="modal fade" id="changeindentprodmodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Change Product Process</h4>
                </div>
                <div class="modal-body" style="padding:0px;">
                    <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                    	    <s:hidden id="change_indent_oldcatid"></s:hidden>
                    	    <s:hidden id="change_indent_childid"></s:hidden>
                    	    <s:hidden id="change_indent_parentid"></s:hidden>
                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding: 0px 10px 0px 10px;">
                            		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
	                                		<label for="inputEmail3" class="control-label">Product Name :</label>
	                                		<!-- <input type="text" readonly="readonly" class="form-control" id="adj_productname"  style="text-transform: uppercase;"> -->
	                           		 		<p id="changecatindent_productname"></p>
	                           		 	</div>
	                           		</div>
	                           		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group" id="indent_change_catlist_div">
	                                		
	                           		 	</div>
	                           		</div>
	                           		
	                           		<div class="col-lg-12 col-md-12 col-xs-12">
                            			<div class="form-group">
											<label for="comment">Remark</label><label><span class="text-danger">*</span></label>
											<textarea class="form-control" rows="3" id="change_indent_comment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
										</div>
                            		</div>
	                           </div>
	                       
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="savechangeindentproductdata()"  class="btn btn-primary">Change</button>
                </div>
            </div>
        </div>
    </div>


<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	


<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

<script>
				             $(function() {
								  $('.depart1').slimScroll({
								   		height : '145px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>


<script>
	 function myPrint() {
            window.print();
        }
</script>

</body>
</html>