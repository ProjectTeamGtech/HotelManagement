<!DOCTYPE html>

<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<meta name="description" content="">
<meta name="author" content="Dashboard">


<link rel="icon" href="assets/img/favicon.ico">
<title>YUVICARE</title>
<!-- Bootstrap core CSS -->



<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>


<script>
	//NProgress.start();
</script>


<style>

.table.table {
    text-transform: uppercase !important;
}
.pnew{
    width: 3%;

}
.bg-lightred {
    background-color: #e05d6f !important;
}
.pview{
    width: 4%;

}
.page{
    width: 7%;
}
.mainheader {
    background-color: #43b9be !important;
}
.imflag{
 display: inline-block;
    width: 4%;
}
.blink_me {
  animation: blinker 1s linear infinite;
  color:red;
}

@keyframes blinker {  
  50% { opacity: 0; }
}

</style>

</head>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
<script>
window.onload = function () {
 
 currencySign = '<%=Constants.getCurrency(loginfo)%>';
 
 
 
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

 
};
</script>

<script>

setInterval(function(){ 
	setnewtokenentry();
}, 30000);
 

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
<body>


<input type="hidden" name="invstcmyModalLabel" id="invstcmyModalLabel">
<input type="hidden" name="invstdatetimeid" id="invstdatetimeid">
<input type="hidden" name="invstpriscdoctornameid" id="invstpriscdoctornameid">

	<!-- **********************************************************************************************************************************************************
     MAIN CONTENT
     *********************************************************************************************************************************************************** -->
	<!--main content start-->
	<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
		   						
		<section>

			<!-- page start-->
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="row details">
											<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
												<h4>Token Dashboard </h4>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
											<s:form action="tokenrequestdashboardPharmacy" theme="simple" cssClass="form-inline search">
											
											  <s:textfield id="fromdate" readonly="true" name="fromdate" cssClass="form-control" placeholder="From Date" cssStyle="width: 10%;"/>
											  <s:textfield id="todate" readonly="true" name="todate" cssClass="form-control" placeholder="To Date" cssStyle="width: 10%;"/>
											  
											  <button type="submit" class="btn btn-primary">Go</button>
											</s:form>
											</div>
										</div>
								
										<div class="">
											<table class="table table-striped table-bordered tablesorter tablefontsize" width="100%">
											<thead>
												<tr class="tableback">
													<th style="width: 12%;">Date</th>
													<th style="width: 10%;">Token Number</th>
													<th style="width: 12%;">UHID</th>
													<th style="width: 26%;">Patient Details</th>
													<th style="width: 6%;">Status</th>
												</tr>
											</thead>
											
											<tbody id="priscrequesttbody">
												<%String bgcolor = ""; %>
												<s:iterator value="priscriptionlist">
													<tr style="cursor: pointer;background-color: <%=bgcolor %>" id="<s:property value="id" />" class="even pointer" ondblclick="setEmrSelectedRows(<s:property value="id" />,<s:property value="clientId" />)">
														<td class=""><s:property value="lastmodified"/></td>
														
														<td>TN-<s:property value="tokenno"/></td>
														<td><s:property value="abrivationid" /></td>
														<td class=" ">
															<a href="#" ><s:property value="fullname" /></a>
														</td>
														<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);">Sale In Process</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
										</div>
								</div>
							
			<!-- page end-->
		</section>
	





   <script>
   $(document).ready(function () {
	    $(".disblebtnone").on("click", function() {
	        $(this).attr("disabled", "disabled");
	        doWork();
	   });
	});
   
   
   function dotmatrix_print(){
   	var printWin = window.open();
       printWin.document.write(
       	"<!DOCTYPE html>"+
           	"<html>"+
               	"<head>"+
       				"<style>"+
       					"@media screen {"+
         						"  body {"+
                               	"    color: green; "+
           						"  }"+
           				"}"+
           				" @media print {"+
             					" body {"+
               					"   color: red; "+
               					" }"+
               			" }"+
               		" </style>"+
               	" </head>"+
               " <body>"+
               	" <h1>&nbsp;&nbsp;&nbsp;&nbsp;The @\media Rule</h1>"+
               	" <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use mediaqueries to set the text printed.</p>"+
               "</body>"+
               " </html>");
       printWin.document.write("<P>&nbsp;&nbsp;Line 01-Col01  -----   Line 01-Col02  ------   Line 01-Col03  ------ </P>" + "\n");
       printWin.document.write("-------------------------------------------------------------------------------- ---------- ----------");
       printWin.document.write("<P>&nbsp;&nbsp;  col01-Value  -----   col02-Value    ------    col03-Value   ------ </P>" + "\n");
       printWin.document.close();
       printWin.focus();
       printWin.print();
       printWin.close();
   }
   </script>
   
</body>
</html>
