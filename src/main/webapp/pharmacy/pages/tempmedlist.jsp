<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
   <script>
    
window.onload = function () {
	document.addEventListener("contextmenu", function(e){
		e.preventDefault();
		}, false); 

}
</script>
<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
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
<div class="">
								<%-- <div class="hidden-print">
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
								</div> --%>
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Temporary Products </h4>

									</div>
								</div>
								
								<div class="">
								<table class="table table-striped table-bordered tablesorter" width="100%">
								<tr>
								<th>sr.no</th>
								<th> Product Name</th>
								<th>Generic Name</th>
								<th>Added By</th>
								<th>Approve</th>
								
								</tr>
								<s:iterator value="priscriptionlist">
								
								<tr>
								<td><s:property value="id"/></td>
								<td><s:property value="drug"/></td>
								<td><s:property value="genericname"/></td>
								<td><s:property value="userid"/></td>
								<td><button class="btn-success" onclick='settempmedtoper(<s:property value="id"/>)'>Approve</button></td>
								</tr>
								</s:iterator>
								</table>
								</div>
</div>