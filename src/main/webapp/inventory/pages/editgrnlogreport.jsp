<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<SCRIPT type="text/javascript">
	var todayDate = new Date().getDate();
	$(document).ready(function(){
	   $("#fromdate").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-2010',
			changeMonth : true,
			changeYear : true,
			
		});
		$("#todate").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-2010',
			changeMonth : true,
			changeYear : true,
			
		});
   });
	function printReport() {
		  $("#tablesort").table2excel({
			exclude: ".noExl",
			name: "GRN Edit After Stock Transfer Log Report",
			filename: "editgrnafterstocktransferlog",
			fileext: ".xls",
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});          
   }

</SCRIPT>


<style>
	 b, strong {
    font-weight: 900;
}   
.topback2 {
    background-color: #f5f5f5;
    padding: 10px 0px 10px 15px;
}
</style>
 <script type="text/javascript" src="common/js/pagination.js"></script>
  <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>
<body>

<%-- <% double[] usedead= (double[]) session.getAttribute("usedead"); %> --%>


<div class="row">

<div class="print-visible hidden-md hidden-lg letterheadhgt" >
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div>
								<div class="col-lg-12 col-md-12 col-xs-12">
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding: 0px;">
										
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form cssClass="form-inline search" theme="simple" action="editgrnlogreportProduct" method="post">
										 	<div class="form-group">
					                           		<span class="text-uppercase"><b>GRN Edit After Stock Transfer Log Report</b> &nbsp; - &nbsp;</span>
					                        </div>
											<div class="form-group" style="width:8%;">
												 <s:textfield name="searchtext"  id="searchtext" cssClass="form-control" cssStyle="width:100%" placeholder="Search text" />
											</div>
											<div class="form-group" style="width:8%;">
												 <s:textfield name="fromdate" readonly="true" id="fromdate" cssClass="form-control" cssStyle="width:100%" placeholder="From Date" />
											</div>
											<div class="form-group" style="width:8%;">
												<s:textfield name="todate" id="todate" readonly="true" cssClass="form-control" cssStyle="width:100%" placeholder="To Date" /> 
											</div>
											
											 <div class="form-group" >
													<s:select list="warehouseList" id="location_filter" name="location_filter" listKey="id" listValue="name" headerKey="0" headerValue="Select"  cssClass="form-control chosen-select"></s:select>
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div> 
											<div class="form-group">
												<a type="button" id="btnxls" title="Save As XLS" onclick="printReport()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
												&nbsp;<a href="#" onclick="printpage();" class="btn btn-primary hidden-print"><i class="fa fa-print"></i></a>
											</div>
										 </s:form>
										</div>
										</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-sm hidden-md visible-print" style="padding: 0px;">
										<center><h3>GRN Edit After Stock Transfer Log Report </h3> </center>	
									</div>
									<table class="table table-bordered tablesort" id="tablesort" cellspacing="0" width="100%">
                                <thead>
                                <tr class="tableback">
                                		<th style="width:3%;"></th>
                                        <th>Product Name</th>
                                        <th>Category</th>
                                        <th >Sub Category</th>
                                        <th>Location</th>
                                        <th>Date</th>
                                        <th>User</th>
                                        
                                        <th>Pre-Pack</th>
                                        <th>After-Pack</th>
                                        <th>Pre-Freeqty</th>
                                        <th>After-Freeqty</th>
                                        <th>Pre-Stock</th>
                                        <th>After-Stock</th>
                                        <th>Pre-GRN-Qty</th>
                                        <th>After-GRN-Qty</th>
                                    </tr>
                                </thead>
                                	  <tbody>
                                	   <%int i=0; %>
                                	   <s:iterator value="editgrnreportlist">
	                                     <tr>
		                                     <td><%=(++i) %></td>
		                                      <td><s:property value="product_name"/> (<s:property value="genericname"/>)</td>
		                                      <td><s:property value="category"/></td>
		                                      <td><s:property value="subcategory"/></td>
		                                      <th><s:property value="locationName"/></th>
		                                     <td><s:property value="dateTime"/></td>
		                                     <td><s:property value="userid"/></td>
		                                     <td><s:property value="grn_pre_pack"/></td>
		                                     <td><s:property value="grn_new_pack"/></td>
		                                     <td><s:property value="grn_pre_freeqty"/></td>
		                                     <td><s:property value="grn_new_freeqty"/></td>
		                                     <td><s:property value="grn_pre_stock"/></td>
		                                     <td><s:property value="grn_new_stock"/></td>
		                                     <td><s:property value="grn_pre_qty"/></td>
		                                     <td><s:property value="grn_new_qty"/></td>
	                                    </tr>
	                                    </s:iterator>
	                                    </tbody>
                            </table>
                            
                           <%--  <s:form action="expirymedicineProduct" name="paginationForm" id="paginationForm" theme="simple">
							    							<s:hidden name="days"></s:hidden>
							    	 						<s:hidden name="fromdate"></s:hidden>
							    							<s:hidden name="todate"></s:hidden> 
							    							<s:hidden name="report"></s:hidden> 
							    							<s:hidden name="vendor_id"></s:hidden> 
															<div class="col-lg-12">
																<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
																	Total:<label class="text-info"><s:property value="totalRecords" /> Records
																</label>
																</div>
															<%@ include file="/common/pages/pagination.jsp"%>
															</div>
													</s:form>   --%>        
								</div>
							
									
								</div>
							</div>
							
						

</body>
</html>