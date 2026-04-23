<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
        .padright {
            padding-left: 40px;
        }
        .table.table {
            color: RGBA(85, 85, 85, 0.85);
            background-color: #fff;
        }

        .comtitle {
            font-size: 13px;
            background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
            color: rgb(255, 255, 255);
        }

        .marbot25 {
            margin-bottom: 25px;
        }

        .editcompany {
            float: right;
            font-size: 17px;
            color: #fff;
        }

        .borright {
            border-right: 1px dashed rgb(192, 192, 192);
        }

        .buildinglogo {
            width: 60%;
            margin-top: 30px;
        }
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
            border-top: 0;
            min-height: auto !important;
        }
        .miheight {
            min-height: auto !important;
        }
        .my-table th {
            background-color: #424A5D;
            color: #fff !important;
            border-bottom: 1px solid #DFD8D4;
            border-right: 1px solid #DFD8D4;
            border-top: 1px solid #DFD8D4;
            padding: 3px 3px 4px 5px;
            text-align: left;
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 1px 7px 1px 7px !important;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
                display: inline-block;
            }
        .sidebar-xs #header .branding {
            width: 100%;
            padding-top: 7px;
            text-align: center;
        }
        .theight {
            height: 21px;
        }
    </style>


    <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
        .addcatego {
            float: right;
            margin-top: -40px;
            margin-right: 30px;
        }
    </style>
    
    
    <SCRIPT type="text/javascript">
    
      $(document).ready(function(){
			   
			   $("#fromdate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
				 $("#todate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
			   
         });
    
    
    </SCRIPT>
    
    
</head>

<body id="his" class="appWrapper sidebar-xs-forced">

    
        <section id="">

            <div class="">
                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                       <s:form theme="simple" action="quicksearchProduct">
                        <div class="col-md-9">
                        <div class="form-inline">
						  <div class="form-group">
						    <span>View History |</span>
						    <s:select list="vendorList" headerKey="0" headerValue="Select Supplier" cssClass="form-control" name="vendor" listKey="id" listValue="name"></s:select>
							<s:select list="brandnameList" headerKey="0" headerValue="Select Product" cssClass="form-control" name="brand" listKey="id" listValue="name"></s:select>
						    <s:textfield cssClass="form-control" id="fromdate" name="fromdate" placeholder="From Date" />
						    <s:textfield cssClass="form-control" id="todate" name="todate" placeholder="To Date" />
						  </div>
						    <button type="submit" class="btn btn-primary">Filter</button>
                            <button type="submit" class="btn btn-warning">Reset</button>
						</div>
                        </div>
                        </s:form>
                        <div class="col-md-3 text-right"></div>
                    </div> 
                    <div class="">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product</th>
                                    <th>Product Type</th>
                                    <th>Supplier Name</th>
                                    <th>Product Name</th>
                                    <th>Date</th>
                                    <th>Qty</th>
                                    <th>Amount</th>
                                    <th>View History</th>
                                    <!--<th>Remove</th>
                                --></tr>
                            </thead>
                            <tbody>
                            <% int i=0; %>
                            <s:iterator value="productList">
                            <tr>
                            	<td><%=(++i) %></td>
                            	<td><s:property value="brand"/></td>
                            	<td><s:property value="subcategory"/></td>
                            	<td><s:property value="vendor"/></td>
                            	<td><s:property value="product_name"/></td>
                            	<td><s:property value="lastmodified"/></td>
                            	<td><s:property value="stock"/></td>
                            	<td>Rs.<s:property value="total"/></td>
                            	<td><a data-toggle="collapse" href="#viewhistory<%=i%>" aria-expanded="false" aria-controls="viewhistory">View History</a></td>
                            	<!--<td><a href="">Remove</a></td>
                            --></tr>   
                             <tr style="background-color: #ddd;" class="collapse" id="viewhistory<%=i%>" aria-expanded="true">
                                    <td colspan="999"> 
                                    <table class="table my-table xlstable table-bordered">
                                    	<tbody>
                                    		<tr>
                                    			<th>Product Code</th>
                                    			<th>Purchase Date</th>
                                    			<th>Purchase Price</th>
                                    			<th>Quantity</th>
                                    			<th>Amount</th>
                                    		</tr>
                                    		<s:iterator value="historyvendorProduct">
                                    		<tr>
                                    			<td><s:property value="product_code"/></td>
                                    			<td><s:property value="lastmodified"/></td>
                                    			<td>Rs.<s:property value="purchase_price"/></td>
                                    			<td><s:property value="stock"/></td>
                                    			<td>Rs.<s:property value="total"/></td>
                                    		</tr>
                                    		</s:iterator>
                                    	</tbody>
                                    </table>
                                   </td>
                              </tr>  
                             </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
                
                   <s:form action="quicksearchProduct" name="quicksearch" id="quicksearch"
									theme="simple">
							    
									<div class="row">
									<div class="col-lg-4 col-md-4 text-right">
										Showing all <label class="text-info">(<s:property
												value="pagerecords" /> of <s:property value="totalRecords" />
											Records)
										</label>
									</div>
									<%@ include file="/common/pages/pagination.jsp"%>
								</div>
							</s:form> 
                   
                
            </div>
        </section>
        <!--/ CONTENT -->
        
    

</body>
</html>