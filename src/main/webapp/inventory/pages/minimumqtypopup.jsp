<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<SCRIPT type="text/javascript">
	function printReport() {
		  $("#tablesort").table2excel({
			exclude: ".noExl",
			name: "Minimum Qty Report",
			filename: "MinimumQtyReport",
			fileext: ".xls",
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});          
    }

</SCRIPT>
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
					                           		<span class="text-uppercase"><b>Minimum Quantity Product List</b> &nbsp; - &nbsp;</span>
					                        </div>
											
											<div class="form-group hidden-print">
												<a type="button" id="btnxls" title="Save As XLS" onclick="printReport()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
												<a href="#" class="btn btn-warning" onclick="printpage()">Print</a>
											</div>
										 </s:form>
										</div>
										</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12">
									<table class="table table-bordered tablesort" id="tablesort" cellspacing="0" width="100%">
                                <thead>
           <tr class="loc">
            <td style="width: 3%;">Sr.no</td>
            <td style="width: 5%;">Indent N0</td>
            <td style="width: 8%;">Location</td>
            <td style="width: 5%;">Userid</td>
            <td style="width: 5%;">Product Code</td>
            <td style="width: 20%;">Product Name</td>
            <td style="width: 4%;">InStock</td>
            <td style="width: 6%;">Rate</td>
            <td style="width: 4%;">Discount</td>
            <td style="width: 4%;">Free</td>
            <td style="width: 6%;">GST</td>
            <td style="width: 4%;">Min Order</td>
            <td style="width: 4%;">Max Order</td>
            <td style="width: 15%;">Supplier</td>
            <td style="width: 7%;">Req Qty</td>
           </tr>
          </thead>
          <tbody>
          	   <%  int i=0; %>
          	   <s:iterator value="returnqueList">
          	     <tr>
          	       <td><%=i+1 %>  </td>
          	       <td><s:property value="indent"/>
          	       <td><s:property value="from_location"/>
          	       <td><s:property value="req_userid"/>
          	       <td><s:property value="pro_code"/></td>  
          	       <td><s:property value="product_name"/></td>
          	       <td><s:property value="stock"/></td>
          	       <td><s:property value="purchase_price"/></td>
          	       <td><s:property value="discount"/></td>
          	       <td><s:property value="freeqty"/></td>
          	       <td><s:property value="vat"/>%</td>
          	       <td><s:property value="minorder"/></td>
          	       <td><s:property value="maxorder"/></td>
          	       <td><s:property value="vendor"/></td>
          	       <td><s:property value="qty"/></td>
          	       </tr>
          	     <%i++; %>
          	   </s:iterator>
          	
          	</tbody> 

                            </table>
                            
                        
								</div>
							
									<s:form action="minimumqtypopupProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
									<div class="col-lg-12 col-md-12 hidden-print" style="padding:0px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
								
							</s:form> 
								</div>
							</div>
							
						

</body>
</html>