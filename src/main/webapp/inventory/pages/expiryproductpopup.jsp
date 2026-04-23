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
			 maxDate: new Date(new Date().setDate(todayDate))
		});
		$("#todate").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-2010',
			changeMonth : true,
			changeYear : true,
			 maxDate: new Date(new Date().setDate(todayDate))
		});
   });
	function printReport() {
		  $("#tablesort").table2excel({
			exclude: ".noExl",
			name: "Expiry Report",
			filename: "expiryReport",
			fileext: ".xls",
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});          
   }

</SCRIPT>
<script type="text/javascript">
 var todayDate1 = new Date().getDate();
 function setminmaxexpirydate(val) {
	 
	if(val=='2'){
		$('#fromdate').datepicker('option', 'maxDate', 0);
		$('#todate').datepicker('option', 'maxDate', 0);
		$('#fromdate').datepicker('option', 'minDate', '30-12-2010');
		$('#todate').datepicker('option', 'minDate', '30-12-2010');
		document.getElementById("fromdate").value="";
		document.getElementById("todate").value="";
	}else if(val=='3'){
		$('#fromdate').datepicker('option', 'maxDate', '30-12-2030');
		$('#todate').datepicker('option', 'maxDate', '30-12-2030');
		$('#fromdate').datepicker('option', 'minDate', 0);
		$('#todate').datepicker('option', 'minDate', 0);
		
		document.getElementById("fromdate").value="";
		document.getElementById("todate").value="";
	}else{
		$('#fromdate').datepicker('option', 'minDate', '30-12-2010');
		$('#todate').datepicker('option', 'minDate', '30-12-2010');
		
		$('#fromdate').datepicker('option', 'maxDate', '30-12-2030');
		$('#todate').datepicker('option', 'maxDate', '30-12-2030');
		document.getElementById("fromdate").value="";
		document.getElementById("todate").value="";
	}
}
</script>

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
								<div class="col-lg-12 col-md-12 col-xs-12">
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form cssClass="form-inline search" theme="simple" action="expirymedicineProduct" method="post">
										 	<div class="form-group">
					                           		<span class="text-uppercase"><b>Product Expiry Within 90 Days</b> &nbsp; - &nbsp;</span>
					                        </div>
											<%-- <div class="form-group">
												<s:select list="#{'0':'Use','1':'Dead Stock','2':'Expired','3':'Expiring'}" onchange="setminmaxexpirydate(this.value)" cssClass="form-control" name="report"  />
											</div>
											<div class="form-group" style="width:8%;">
												 <s:textfield name="fromdate" readonly="true" id="fromdate" cssClass="form-control" cssStyle="width:100%" placeholder="From Date" />
											</div>
											<div class="form-group" style="width:8%;">
												<s:textfield name="todate" id="todate" readonly="true" cssClass="form-control" cssStyle="width:100%" placeholder="To Date" /> 
											</div>
											<div class="form-group hidden">
												<s:select list="#{'0':'Select Day','30':'30 Days','60':'60 Days','90':'90 Days','180':'180 Days'}" cssClass="form-control" name="days"  />
											</div>
											 <div class="form-group" >
													<s:select   list="vendorList" id="vendorlist" name="vendor_id" listKey="id" listValue="name" headerKey="" headerValue="Select Vendor" cssClass=" chosen-select"></s:select>
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div> --%>
											<div class="form-group">
												<a type="button" id="btnxls" title="Save As XLS" onclick="printReport()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
											</div>
										 </s:form>
										</div>
										</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12">
									<table class="table table-bordered tablesort" id="tablesort" cellspacing="0" width="100%">
                                <thead>
                                <tr class="tableback">
                                		<th style="width:3%;"></th>
                                        <th>Days</th>
                                        <th>Product Name</th>
                                        <th style="width:10%;">Expiry Date</th>
                                        <!-- <th>Supplier Name</th>
                                        <th>Contact No</th> -->
                                        <th>Location</th>
                                        <th>Qty</th>
                                        <th class="text-right">Total Cost</th>
                                        <th>Stock Status</th>
                                        
                                    </tr>
                                </thead>
                                	  <tbody>
                                	   <%int i=0; %>
                                	   <s:iterator value="expiryMedicineReport">
	                                     <tr>
		                                     <td><%=(++i) %></td>
		                                     <td><s:property value="days"/></td>
		                                     <td><s:property value="product_name"/> (<s:property value="genericname"/>)</td>
		                                      <td style="color:red;"><s:property value="expiry_date"/></td>
		                                     <%-- <td><s:property value="vendor"/></td>
		                                     <td><s:property value="contact"/></td> --%>
		                                      <th><s:property value="locationName"/></th>
		                                     <td><s:property value="stock"/></td>
		                                     <td class="text-right">Rs.<s:property value="total"/></td>
		                                     <td>Expiring</td>
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