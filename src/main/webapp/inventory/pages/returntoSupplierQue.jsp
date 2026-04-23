
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

<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">

<style>
.form-control {
    border: 1px solid rgba(0, 0, 0, 0.1) !important;
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
ul, ol {
    margin-top: 0;
    margin-bottom: 8.5px;
    list-style: none;
}
.savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
    line-height: 40px;
}
ul, ol {
    margin-top: 0;
    margin-bottom: 8.5px;
    list-style: none;
    padding: 0px;
    margin: 0px;
}
.text-info {
    color: #31708f !important;
}
label {
    font-weight: bold !important;
}
.topheadbaxck {
    background-color: rgb(239, 239, 239);
    padding: 8px 0px;
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
<script type="text/javascript">
window.onload= function(){
	document.addEventListener("contextmenu", function(e){
		e.preventDefault();
		}, false); 
}

</script>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<script type="text/javascript" src="common/js/pagination.js"></script>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
</head>
<body>
<div class="col-lg-12 col-xs-12 col-md-12">
					<div class="hidden-print">
						<ul class="breadcrumb">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
							<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
								<%if(breadcrumbs.isIscurrent()){ %>
									<li><%=breadcrumbs.getShowingname()%></li>
								<%}else{ %>
									<%if(breadcrumbs.getOn()){ %>
										<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getShowingname()%></a></li>
									<%}else{ %>
										<li><%=breadcrumbs.getShowingname()%></li>
									<%} %>
								<%} %>
								
							<%} %>
						</ul>
					</div>
<form action="returnproductProduct" onsubmit="return returntoSupplier()" method="post">


<div class="row hidden" style="background-color: rgba(239, 239, 239, 0.42);padding: 9px;border: 1px dashed #ddd;">
	<table class="table table-striped table-bordered" style="width:100%;">
         
          <tbody>
          	<tr>
          		<td style="border-top: none;width: 15%;">
        			<s:select list="warehouseList" theme="simple" name="warehouse" headerKey="0" id="warehouse" headerValue="Select Warehouse" onchange="setwarehouse(this.value)" listKey="id" listValue="name" cssClass="form-control chosen-select" />
          		</td>
          		<td id="categorydiv" style="border-top: none; width: 15%;">
        				<select class='form-control'>
        				 <option>Select Category </option>
        				</select>
          		</td>
          		<td id="subdiv" style="border-top: none;width: 15%;">
        				<select class='form-control'>
        				<option>Select SubCategory </option>
        				</select>
          		</td>
          		<td id="proddiv" style="border-top: none;width: 15%;">
        				<select name="" class="form-control chosen-select">
						    <option value="0">Select</option>
						</select>
          		</td>
          		<td id="vendid" style="border-top: none;width: 15%;">
          			<select name="" class="form-control chosen-select">
						    <option value="0">Select</option>
						    
						</select>
          		</td>
          		<td style="border-top: none;">
          			<input type="number" readonly="readonly" class="form-control" id="rate" placeholder="Rate">
          		</td>
          		<td style="border-top: none;">
          			<input type="number" readonly="readonly" class="form-control" id="discount" placeholder="Discount">
          		</td>
          		<td style="border-top: none;">
          			<input type="number" class="form-control" id="qty" placeholder="Qty">
          		</td>
          		<td style="border-top: none;"><a href="#" onclick="saveNewGrn()" class="btn btn-success">Add</a></td>
          	</tr>
          	</tbody>
         </table>
         
         
</div>

<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                     
                        <div class="col-md-12">
                        <div class="form-inline">
                         <div class="form-group">
	                           		<span class="text-uppercase"><b>RETURN QUE LIST</b></span>
	                           </div>
                       
                        
						</div>
                        
                        </div>
                       
                    </div>
</div>



<div class="" style="min-height: 300px;">

			<div class="fixedscroll">
				<table class="table table-striped table-bordered" style="width:100%;" id="prodtableid">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 8%;">Location</th>
            <th style="width: 25%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Expiry Date</th>
            <th style="width: 8%;">Rate</th>
            <th style="width: 4%;">Pack</th>
            <th style="width: 8%;">Stock</th>
            <th style="width: 18%;">Supplier</th>
            <th style="width: 21%;">Return Qty</th>
            <th style="width: 3%;"> 
            	<ul class="vertical default_list" id="">
						      <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" class="form-control" onclick="selectAllCheckBox(this)"/><i style="border-color: #fff;color: #fff;"></i> </label></li>
						</ul>
            
            	 </th>
            <th>Delete</th>
           </tr>
          </thead>
          <tbody>
          	   <% int i=0; %>
          	   <s:iterator value="returnqueList">
          	     <tr>
          	       <td><%=i+1 %> <input type="hidden" value="<s:property value="id"/>" name="products[<%=i%>].id" />  </td>
          	       <tD><s:property value="locationName"/></tD>
          	       
          	       <td><s:property value="product_name"/>
          	         	<input type="hidden" value="<s:property value="product_id"/>" name="products[<%=i%>].product_id" />
          	          	<input type="hidden" value="<s:property value="stock"/>" id="stock<%=i%>" name="products[<%=i%>].stock" />
          	         </td>
          	         <td><s:property value="batch_no"/></td>
          	         <td><s:property value="expiry_date"/></td>
          	       <td><s:property value="purchase_price"/></td>
          	       <td><s:property value="pack"/></td>
          	       <td><s:property value="stock"/></td>
          	       <td><s:property value="vendor"/>
          	           <input type="hidden" value="<s:property value="vendor_id"/>" name="products[<%=i%>].vendor_id" />
          	           <input type="hidden" value="<s:property value="procurementid"/>" name="products[<%=i%>].procurementid" />
          	       </td>
          	       <td>
          	         <input type="number" name="products[<%=i%>].qty" onchange="checkReturnQtyNotGreater(<%=i%>)" class="form-control" id="qty<%=i%>" value="<s:property value="stock"/>" />
          	       </td>
          	       <td>
          	        <article>
						<ul class="vertical default_list" id="">
						   <%-- <s:if test="newpo==1">
						      <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input name="products[<%=i%>].status" value="<%=i%>" type="checkbox" checked="checked" class="form-control case" /><i></i> </label></li>
						   </s:if>
						   <s:else> --%>
						   		<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input name="products[<%=i%>].status" value="<%=i%>" type="checkbox" class="form-control case" /><i></i> </label></li>
						  <%--  </s:else> --%>
							
						</ul>
					</article>
          	       </td>
          	         <td><a href="#" onclick="deleteReturnTempReq(this,'prodtableid',<s:property value="id"/>)" ><i class="fa fa-times text-danger fa-2x" aria-hidden="true"></i></a></td>
          	     </tr>
          	     <%i++; %>
          	   </s:iterator>
          	
          	</tbody> 
         </table>
			</div>
	
         <input type="submit" value="Add To Return" onclick="return validateReturnQueSubmit()" class="btn btn-primary savebigbtn" style="float: right;margin-top: 15px;"  />
         
         
</div>



</form>
    
   <%--  <s:form action="returnqueProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
									<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
									<div class="col-lg-4 col-md-4 text-left">
										Total:<label class="text-info"><s:property value="totalRecords" /> Records</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
								 <s:hidden name="searchtext"></s:hidden>
							</s:form>  --%>
    
</div>

<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
  
  <script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

<script>
				             $(function() {
								  $('.fixedscroll').slimScroll({
								   		height : '480px',
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