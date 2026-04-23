<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<html>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<SCRIPT type="text/javascript" src="pharmacy/js/pharmacy.js"></SCRIPT>

<style>
	body {
    font-family: "Lato", "Arial", sans-serif;
    font-size: 14px;
    font-weight: 400;
    text-rendering: optimizeLegibility !important;
    -webkit-font-smoothing: antialiased !important;
    -moz-osx-font-smoothing: grayscale !important;
    -ms-overflow-style: scrollbar;
    background-color: #ffffff;
    color: #616f77;
}
.checkbox {
    position: relative;
    display: block;
    min-height: 13px;
    margin-top: 0px;
    margin-bottom: 0px;
}
.bgreen{
	    background-color: rgba(22, 160, 133, 0.12);
    padding: 10px 15px 15px 15px;
    min-height: 443px;
}
.list-group.no-border .list-group-item {
    border-width: 1px 0;
    border-left: 3px solid;
}
.list-group-item {
    position: relative;
    display: block;
    padding: 10px 11px;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid #ddd;
}
.rightborder{
	    
	    min-height: 450px;
}
.setback5{
	    background-color: rgba(0, 0, 0, 0.02);
    min-height: 450px;
        padding-top: 10px;
}
.checkbox-custom-alt > i {
    width: 16px;
    height: 16px;
    background-color: transparent;
    border: 2px solid #dfdfdf;
    margin-right: 6px;
    margin-left: -18px;
}
.checkbox-custom-alt input:checked + i:before {
    top: 1px;
    left: 1px;
    width: auto;
    height: auto;
    background-color: transparent;
    opacity: 1;
}
.bg-lightred {
    background-color: #e05d6f !important;
    color: white !important;
}
.sehe20{
	    height: 20px;
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
				$("#expecteddate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
				
				document.addEventListener("contextmenu", function(e){
		    		e.preventDefault();
		    		}, false); 
			   
         });


</SCRIPT>
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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
					<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
								<div class="row details hidden">
                                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Request & Transfer Dashboard </h4>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                	<div class="col-lg-12 col-xs-12 col-xs-12" style="padding:0px;">
                                	<div id="npurchase" class="collapse">
									Lorem ipsum dolor text....
									</div>
                                		<!--<table class="table table-bordered hidden" cellspacing="0" width="100%">
                                <thead>
                                <tr class="tableback ">
                                		<th style="width:20%;">Date | Time</th>
                                        <th style="width:35%;">Product Name</th>
                                        <th style="width:25%;">Generic Name</th>
                                        <th style="width:18%;">Supplier</th>
                                        <th>UID</th>
                                        <th style="width: 1%;"></th>
                                    </tr>
                                </thead>
                                	  <tbody>
                                    <tr>
                                    	<td><label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i></label></td>
                                    	<td>16-03-2017 | 13:00:25</td>
                                        <td>XyZ Product</td>
                                        <td>Ravi_002</td>
                                    </tr>
                                </tbody>
                            </table>
                            -->
                            <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                            	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										<div class="hidden-print">
								<ul class="breadcrumb">
									&nbsp;
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
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form cssClass="form-inline search" theme="simple" action="requestmedicinePharmacy" method="post">
											<div class="form-group">
												<select class="form-control hidden" name="report">
												  <option value="0">Today</option>
												  <option value="0">Yesterday</option>
												</select>
											</div>
											<div class="form-group">
												<s:textfield id="fromdate" name="fromdate" theme="simple" cssClass="form-control" placeholder="From Date" />
											</div>
											<div class="form-group">
												<s:textfield id="todate" name="todate" theme="simple" cssClass="form-control" placeholder="To Date" />
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										
										</s:form>
										
										
										</div>
										</div>
								</div>
						<div class="col-lg-12 col-md-12">
							<s:form theme="simple" id="plistform" action="requestmedicinePharmacy" method="post"> 
							<div class="col-lg-12 col-md-12" style="padding:0px;">
								<input type="hidden" name="expectedDate" id="expecteddate1">
								<input type="hidden" name="warehouse_id" id="warehouse_id1">
								<table class="table table-striped table-bordered" style="width: 100%;">
									<thead>
										<tr>
											<th style="width: 4%;">Sr.No</th>
											<th style="width: 6%;">Product ID</th>
											<th style="width: 18%;">Product Name</th>
											<th style="width: 6%;">Stock</th>
											<th style="width: 7%;">HSN NO</th>
											<th style="width: 7%;">Batch No</th>
											<th style="width: 7%;">Unit Price</th>
											<th style="width: 12%;">From Location</th>
											<th style="width: 8%;">User Id</th>
											<th style="width: 10%;">Date</th>
											<th style="width: 5%;">Enter Qty</th>
											<th style="width: 20%;">Business reason</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody >
									<%int  i=0; %>
									 <s:iterator value="requestedMedicineList">
										<tr>
											<td><%=++i %></td>
											<td><s:property value="product_id"/></td>
											<td><s:property value="product_name" /> (<s:property value="genericname" />)</td>
											<td><s:property value="stock" /></td>
											<td><s:property value="hsnno" /></td>
											<td><s:property value="batch_no" /></td>
											<td><s:property value="sale_price" /></td>
											<td><s:property value="location" /></td>
											<td><s:property value="userid" /></td>
											<td><s:property value="request_date" /></td>
											<td>
												<%if(loginInfo.getUserType()==2){ %>
													<input type="text" class="form-control" value="<s:property value="reqqty"/>" name="qty<s:property value="id"/>" id="qty<s:property value="id"/>" placeholder="Qty" style="background-color: beige;height: 20px;"/>
												<%}else{ %>
												<s:if test="requisition_auth==1">
													<input type="text" class="form-control" value="<s:property value="reqqty"/>" name="qty<s:property value="id"/>" id="qty<s:property value="id"/>" placeholder="Qty" style="background-color: beige;height: 20px;"/>
												</s:if>
												<s:else>
													<input type="text" disabled="disabled" class="form-control" value="<s:property value="reqqty"/>" name="qty<s:property value="id"/>" id="qty<s:property value="id"/>" placeholder="Qty" style="background-color: beige;height: 20px;"/>
												</s:else>
												<%} %>
											</td>
											<td>
												<%if(loginInfo.getUserType()==2){ %>
													<textarea type="text" class="form-control" name="comment<s:property value="id"/>" id="comment<s:property value="id"/>" placeholder="Business reason" style="background-color: beige;"></textarea>
												<%}else{ %>
												<s:if test="requisition_auth==1">
													<textarea type="text" class="form-control" name="comment<s:property value="id"/>" id="comment<s:property value="id"/>" placeholder="Business reason" style="background-color: beige;"></textarea>
												</s:if>
												<s:else>
													<textarea type="text" disabled="disabled" class="form-control" name="comment<s:property value="id"/>" id="comment<s:property value="id"/>" placeholder="Business reason" style="background-color: beige;"></textarea>
												</s:else>
												<%} %>
											</td>
											<td>
												<%if(loginInfo.getUserType()==2){ %>
													<label style="padding-top: 3px;" class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox" class="case" value="<s:property value="id"/>" /><i></i></label>
												<%}else{ %>
												<s:if test="requisition_auth==1">
													<label style="padding-top: 3px;" class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox" class="case" value="<s:property value="id"/>" /><i></i></label>
												</s:if>
												<s:else>
													<label style="padding-top: 3px;" class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox" class="case" disabled="disabled" value="<s:property value="id"/>" /><i></i></label>
												</s:else>
												<%} %>
												
											</td>
											<td>
												<%if(loginInfo.getUserType()==2){ %>
													<a href="deleteMedReqByUserPharmacy?id=<s:property value="id"/>" onclick="return confirmDelete()"><i class="fa fa-trash" style="color: #d9534f;"></i></a>
												<%}else{ %>
												<s:if test="requisition_auth==1">
													<a href="deleteMedReqByUserPharmacy?id=<s:property value="id"/>" onclick="return confirmDelete()"><i class="fa fa-trash" style="color: #d9534f;"></i></a>
												</s:if>
												<%} %>
											</td>
										</tr>
									</s:iterator>
									</tbody>
								</table>
								<s:hidden name="allrequestlist" id="allrequestlist"></s:hidden>
								<div class="col-lg-12 col-xs-12 col-md-12 text-right" style="margin-top:15px;">
									<%if(loginInfo.getUserType()==2){ %>
										<input type="button" value="Add" onclick="requestmedicine()" class="btn btn-success" />
									<%}else{ %>
										<s:if test="Requisition_auth==1">
											<input type="button" value="Add" onclick="requestmedicine()" class="btn btn-success" />
										</s:if>
									<%} %>
								</div>
								</div>
								</s:form>
							<!--
							<div class="col-lg-6 col-md-6" style="border-left: 5px solid #ddd;min-height: 500px;padding: 0px;">
								<table class="table table-striped table-bordered" style="width: 100%;">
									<thead>
										<tr>
											<th style="width: 3%;">Requested ID</th>
											<th style="width: 10%;">Requested Date</th>
											<th style="width: 20%;">Request From</th>
											<th style="width: 13%;">Action</th>
											<th style="width: 20%;">status</th>
											<th style="width: 7%;">Request or Transfer</th>
										
											<th>Requested ID</th>
											<th>Requested Date</th>
											<th>Request From</th>
											<th>status</th>
											<th>Request or Transfer</th>
											<th style="text-align: center;">Action</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="parenttransferlist">
              						<tr>
              							<td><s:property value="parentid"/><s:hidden id="parentid" name="parentid"></s:hidden></td>
              							<td><s:property value="request_date"/></td>
              							<td style="color: chocolate;"><s:property value="from_location"/></td>
              							
              					   		<td>
              					   		<s:if test="status==0">
              					   			Pending
              					   		</s:if>
              					   		<s:elseif test="status==1">
              					   			Request Done
              					   		</s:elseif>
              					   		<s:elseif test="status==2">
              					   			Admin Aproved
              					   		</s:elseif>
              					   		<s:elseif test="status==3">
              					   			Recived
              					   		</s:elseif>
              					   		<s:elseif test="status==4">
              					   			Admin Rejected
              					   		</s:elseif>
              					   		<s:elseif test="status==5">
              					   			Head Rejected
              					   		</s:elseif>
              					   		</td>
              					   		<td>
              					   		<s:if test="req_or_transfer==0">
              					   			Request
              					   		</s:if>
              					   		<s:else>
              					   			Direct Transfer
              					   		</s:else>
              					   		</td>
              					   		<s:if test="req_or_transfer==1">
              								<td style="text-align:center;"><a href="#" style="background-color: #5cb85c;color: #fff;padding: 2px 5px 2px 5px;" data-toggle="modal" onclick="showRequestPopupForAprove1(<s:property value="parentid"/>)">View Details</a></td>
              					    	</s:if>
              					    	<s:else>
              					    		<s:if test="status==1">
              					    			<td style="text-align:center;"><a href="#" style="background-color: #5cb85c;color: #fff;padding: 2px 5px 2px 5px;" data-toggle="modal" onclick="showRequestPopupForAprove1(<s:property value="parentid"/>,2)">View Details</a></td>
              					    		</s:if>
              					    		<s:elseif test="status==2">
              					    			<td style="text-align:center;"><a href="#" style="background-color: #5cb85c;color: #fff;padding: 2px 5px 2px 5px;" data-toggle="modal" onclick="showRequestPopupForAprove1(<s:property value="parentid"/>,3)">View Details</a></td>
              					    		</s:elseif>
              					    		<s:elseif test="status==4">
              					    			<td style="text-align:center;"><a href="#" style="background-color: #5cb85c;color: #fff;padding: 2px 5px 2px 5px;" data-toggle="modal" onclick="showRequestPopupForAprove1(<s:property value="parentid"/>,4)">View Details</a></td>
              					    		</s:elseif>
              					    		<s:elseif test="status==5">
              					    			<td style="text-align:center;"><a href="#" style="background-color: #5cb85c;color: #fff;padding: 2px 5px 2px 5px;" data-toggle="modal" onclick="showRequestPopupForAprove1(<s:property value="parentid"/>,5)">View Details</a></td>
              					    		</s:elseif>
              					    		<s:elseif test="status==3">
              					    			<td style="text-align:center;"><a href="#" style="background-color: #5cb85c;color: #fff;padding: 2px 5px 2px 5px;" data-toggle="modal" onclick="showRequestPopupForAprove1(<s:property value="parentid"/>,6)">View Details</a></td>
              					    		</s:elseif>
              					    	</s:else>
              						</tr>
              					</s:iterator>
							</tbody>
						</table>
					</div>
				--></div>
              </div>
            </div>
            
 <div id="datemodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
    <s:form action="checkmedicineavabilityPharmacy" theme="simple"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">DEPARTMENT REQUEST NOTE</h4>
      </div>
     
      <div class="modal-body">
      <div id="page_printer">
      	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="hospitaltitlediv4">
									        	  		<h3><b>SHREE NARAYANA HOSPITAL</b></h3>
									        			<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj Mandi,</h5>
									        			<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
									        			<h5>Phone: 0771-3001234,35,36</h5>
									        		</div>
								        	      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								        		  <h4><b>DEPARTMENT REQUEST NOTE</b></h4>
								        	    </div>
								        	    	<s:hidden name="parentid2" id="parentid24"></s:hidden>
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-bottom: 15px;text-transform: uppercase;">
									        			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									        				<p style="margin: 0 0 2.5px;"><b>Issue Number : </b><span id="issueno4"></span></p>
										        			<p style="margin: 0 0 2.5px;"><b>Requested From : </b><span id="fromlocation4"> Store</span></p>
										        			<p style="margin: 0 0 2.5px;"><b>Product Number : </b><span id="indentno4"></span></p>
										        	</div>
										        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										        		<p style="margin: 0 0 2.5px;"><b>Issue Date : </b><span id="issuedate4">02-07-2017 18:45</span></p>
										        		<p style="margin: 0 0 2.5px;"><b> </b><span id="tolocation4">ESIC Pharmacy</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>Request Date : </b><span id="requestdate4">02-07-17 19:46</span></p>
										            </div>
							        	    </div>
							          <div>
							        	</div>
							        </div>
							      <div>
         <table class="table table-striped table-bordered" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 20%;">Product Name</th>
            <th style="width: 8%;">Batch No</th>
            <th style="width: 8%;">Exp Date</th>
            <th style="width: 7%;">Issue Qty</th>
            <th style="width: 7%;text-align: right;">Unit Rate</th>
            <th style="width: 9%;text-align: right;">Amount</th>
            <th style="width: 8%;text-align: right;">MRP</th>
            <th style="width: 9%;text-align: right;">MRP Amount</th>
           	<th style="width: 31%;text-align: right;">Location</th>
           </tr>
          </thead>
          <tbody id="tbodyid4">
           
           </tbody>
         </table>
        </div>
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;margin-top: 30px;">
        	<div class="col-lg-6 col-md-6 col-xs-6">
        		<p style="margin: 0px;" id="username4">Palli R</p>
        		<p style="margin: 0px;" id="userdatetime4"></p>
        		<p>Issued By</p>
        	</div>
        	<div class="col-lg-6 col-md-6 col-xs-6 text-right">
        		<p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p>Received By</p>
        		<s:textarea id="notes" cssClass="form-control" name="notes" theme="simple" placeholder="Write Note"></s:textarea>
        	</div>
        </div>
        	
      </div>
      
      </div>
      <div class="modal-footer" id="buttondiv">
      
      </div>
      </s:form>
     </div>
 
  </div>
</div>

<!-- Modal -->
<div id="datemodel2" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Enter Expected Date</h4>
      </div>
      <div class="modal-body">
      		<div class="form-group">
      			<s:select list="warehouseList" id="warehouse_id" listKey="id" listValue="name" disabled="true" theme="simple" cssClass="form-control chosen-select"></s:select>
      		</div>
      		<div class="form-group">
      			<input type="text" class="form-control" id="expecteddate" placeholder="Expected Date" style="background-color: beige;"/>
      		</div>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="requestmedicine1()" data-dismiss="modal" value="Request Stock">
      </div>
    </div>

  </div>
</div>

<script>
    function printDiv(divID) {
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