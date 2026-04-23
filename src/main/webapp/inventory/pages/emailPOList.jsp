<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>

 <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
       .setborba{
	background-color: #efefef !important;
    padding-top: 5px !important;
}
 .dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus {
    background-image: linear-gradient(to bottom, #777 0, #777 100%) !important;
    
}
.dropdown-menu {
    padding: 0px 0 !important;
    margin: 0px 0 0 !important;
}
ul.dt-button-collection.dropdown-menu>* {
    -webkit-column-break-inside: avoid;
    break-inside: avoid;
    border-bottom: 1px solid rgba(0, 0, 0, 0.5) !important;
}
b, strong {
    font-weight: 900;
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


<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Lab report",
					filename: "labreport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

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
</script>

								<div class="row details hidden">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>PO Mail List</h4>

									</div>
								</div>

<div class="row ">

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
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
 <s:form theme="simple" action="emailPOListProcurement" method="post">
<div class="form-inline">
 
                        <div class="col-md-12" style="padding: 12px;">
                        <!-- <div class="col-lg-6 col-md-6 col-sm-6"> -->
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="text-uppercase"><b>PO MAIL LIST</b> &nbsp; - &nbsp;</span> 
                        <%-- <div class="form-group">
	                           		<span class="text-uppercase"><b></b> &nbsp; From Date - &nbsp;</span>
	                           </div> --%>
	                    <div class="form-group">
	                          <s:select  style="width:25%" list="vendorList" id="vendorlist" name="vendor_id" listKey="id" listValue="name" headerKey="" headerValue="Select Vendor" cssClass=" chosen-select"></s:select>
						</div>
	                    <div class="form-group" style="width: 18%;">
                        	<s:textfield name="voucherno" cssStyle="width:100%;" cssClass="form-control" placeholder="Search by po no"></s:textfield>
                        </div>
                        <div class="form-group" style="width: 6%;">
                        	<s:textfield id="fromDate" name="fromdate" readonly="true"  cssClass="form-control" placeholder="From Date" cssStyle="width:100%;"/>
                        </div>
                         <!-- &nbsp;To Date - &nbsp; -->
                        <div class="form-group" style="width: 6%;">
                        	<s:textfield  name="todate" id="toDate" readonly="true" cssClass="form-control" placeholder="To Date" cssStyle="width:100%;"/>
                        </div>
                        
                        <div class="form-group">
                        	<button type="submit" class="btn btn-primary">Go</button>
                        </div>
                         &nbsp;  &nbsp;  &nbsp;  &nbsp;
                        
						<!-- </div> -->
</div>

</div>
</s:form>
<div>
<table class="table my-table xlstable table-bordered" style="width: 100%;" id="mynewtab" >
<tr>
<th>sr no</th>
<th>Po. No.</th>
<th>Vendor</th>
<th> Date</th>
<th>Sender</th>
<th>Receiver</th>

<th>Print</th>
</tr>
<%int i=0; %>
<s:iterator value="emaillist">
<tr>

						<td><%=++i %></td>
						<td><s:property value="procurementid"/></td>
	          	        <td><s:property value="vendor"/></td>
	          	        <td><s:property value="date"/></td>
	          	        <td><s:property value="sender"/></td>
	          	        <td><s:property value="reciver"/></td>
						
						<td>
							<a href="#" onclick="openPopup('printconfirmProcurement?id=<s:property value="grnno"/>')"><i class="fa fa-print" style="padding-left: 13px;"></i></a>
						</td>

</tr>
</s:iterator>
</table>
</div>
</div>
	<%-- <s:form action="newDmListProcurement" name="paginationForm" id="paginationForm" theme="simple">
							    	
							    	 <s:hidden name="fromdate"></s:hidden>
							    	<s:hidden name="todate"></s:hidden> 
									<div class="col-lg-12">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /> Records</label>
									</div>
									<%@ include file="/common/pages/pagination.jsp"%>
								</div>
							</s:form>                  --%>
</div>