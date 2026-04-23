<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>



<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>
 
	$(document).ready(function() {

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
		
		document.addEventListener("contextmenu", function(e){
    		e.preventDefault();
    		}, false); 
	});
	

</script>
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



								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Return Product Request Dashboard</h4>

									</div>
								</div>
								<s:form action="returnmedicinedashPharmacy" theme="simple">
									<div class="col-lg-12 col-md-12 col-xs-12 topback2">
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
										<div class="form-inline">
											<div class="form-group" style="width:7%;">
												<s:textfield  name="fromdate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
												<s:textfield name="todate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
										</div>
									</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
				<table class="my-table tablexls" id="example" style="width: 100%;font-size: 8px"  >
							<thead>
							<tr>
								<th>Sr. no</th>
								<th>Patient Name</th>
								<th>Date</th>
						 		<th>User</th>
						 		<th>Action</th>
						 	</tr>
					</thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="returnmedlist">
					   		<tr>
					     		<td><%=++i %></td>
								<td><s:property value="clientname"/></td>
								<td><s:property value="dateTime"/></td>
								<td><s:property value="username"/></td>
								<s:if test="returnstatus==0">
									<td><a onclick="openSamePopup('returnipdmedicinePharmacy?id=<s:property value="id"/>&chargeid=<s:property value="chargeid"/>&isfromreturnnurse=1')" href="#" class="btn btn-warning"  >Return</a></td>
								</s:if>
								<s:elseif test="returnstatus==2">
									<td><a href="#">Discount Requested</a></td>
								</s:elseif>
								<s:else>
									<td><a href="#">Returned</a></td>
								</s:else>
							</tr>
					   </s:iterator>
					</tbody>
			 </table>
		</div>

  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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

<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>


<script>
     $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'colvis' ]
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>



	<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
	