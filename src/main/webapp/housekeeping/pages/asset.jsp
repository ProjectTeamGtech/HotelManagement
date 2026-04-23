<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
</style>
</head>
<body>

       <div class="">
       <div class="col-lg-12 col-md-12 col-sm-12 topback2">
                        <div class="col-md-12 martop6">
                            <form class="form-inline">
                            	<div class="form-group">
                                    <label for="exampleInputEmail3"> Asset Management -</label>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                    <s:select list="linenList" cssClass="form-control" listKey="id" listValue="product_name" name="product_id" headerKey="0" headerValue="Select Linen"></s:select> 
                                </div>
                                <button class="btn btn-primary">Go</button>
                                <button class="btn btn-primary hidden">View Reports</button>
                                <button title="print" class="btn btn-primary"><i class="fa fa-print"></i></button>
                                <a href="#" onclick="openPopup('Catalogue')" title="Add Asset" class="btn btn-primary">Add Asset</a>
                            </form>
                        </div>
                        
                    </div>
                <div class="">
                   
                    <div class="col-lg-12">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>Sr</th>
                                    <th>Product Type</th>
                                    <th>Name</th>
                                    <th>Brand</th>
                                    <th>Quantity</th>
                                    <th>Current Available</th>
                                    <th>Washing/Cleaning/Maintanence</th><!--
                                    <th>Edit</th>
                                    <th>Delete</th>
                                --></tr>
                            </thead>
                            <tbody>
                              <%int i=0; %>
                              <s:iterator value="productList">
                                <tr>
                                    <td><%=(++i) %></td>
                                      <td>Asset</td>
                                    <td><s:property value="product_name" /></td>
                                    
                                    <td><s:property value="brand"/></td>
                                    <!--
                                    <td><s:property value="min_delivery_time"/></td>
                                    --><td><s:property value="stock"/></td>
                                    <td><s:property value="remains"/></td>
                                    <td><s:property value="processing"/></td><!--
                                    <td><a href="#" onclick="editstock(<s:property value="id"/>)"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="deletestockProduct?selectedid=<s:property value="id"/>" onclick="return cnfmdelete()"><i class="fa fa-trash text-danger"></i></a></td>
                                --></tr>
                               </s:iterator>
                            </tbody>
                        </table><br />
                        
                    </div>

                </div>

               
                 <s:form action="assetHousekeepingdashboard" name="assetForm" id="assetForm" theme="simple">
							<div class="col-lg-12 col-md-12">
								<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
											Total:<label class="text-info"><s:property value="totalRecords" /></label>
								</div>
								<%@ include file="/common/pages/pagination.jsp"%>
						</div>
						</s:form> 
            </div>

        </section>
        <!--/ CONTENT -->
      


</body>
</html>