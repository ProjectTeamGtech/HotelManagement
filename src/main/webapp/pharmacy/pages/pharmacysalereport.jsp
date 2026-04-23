<!DOCTYPE html>

<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
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


<script>
	var patientId = 0;
	var diaryuserId = 0;
	var editcondition_id = 0;
	function onAdd(cid,pid,conid){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		editparentpriscid = 0;
		repeatePriscDateAjax(cid,pid,conid);
		
	}
	function printReport() {
		
		  $(".table").table2excel({
			exclude: ".noExl",
			name: "Pharmacy rport",
			filename: "pharmacy",
			fileext: ".xls",
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});          
 }

	
	function editviewparentprisc(id,cid,pid,conid){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		editparentpriscid = id;
		//editparentprisc(id);
		
		repeateEditPriscDateAjax(cid,pid,conid);
	}
	
	function paybalance(){
		$('#clearbal').modal( "hide" );
	   document.getElementById("formbal").submit();
	}
	function paybalance1(){
		$('#clearbal1').modal( "hide" );
	   document.getElementById("formbal1").submit();
	   
	}
	
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
	 $("#date1").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		 $("#date").datepicker({

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
 
};
</script>
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
			
				<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Pharmacy Payment Report</h4>
								</div>
							</div>
					
						<div class="">
							
							<div class="">
							<%-- <div class="hidden-print">
											<ul class="breadcrumb">
												&nbsp;
												<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
												<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
													<%if(breadcrumbs.isIscurrent()){ %>
														<li><%=breadcrumbs.getShowingname()%></li>
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
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									<a style="display: none;" href="#" onclick="opencPopup('getPatientRecordEmr')" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Create
										Prescription </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form action="pharmacysalereportPharmacy?action=0" theme="simple" cssClass="form-inline search">
										  <div class="form-group" style="width: 10%;">
										    <s:textfield name="searchText" id="searchText" cssClass="form-control" style="width:100%;" placeholder="Search Patient / ID / Bill No"/>
										  </div>
										  <div class="form-group" style="width: 7%;">
										    <s:textfield id="fromdate" cssStyle="width: 100%;" name="fromdate" cssClass="form-control" placeholder="From Date"/>
										  </div>
										  <%if(loginfo.isAyushman() || loginfo.isParihar()){%>
			<div class="form-group" style="width:7%;">
                                    	 <div class="form-inline hhmm">
                                   <label for="exampleInputName2">HH:MM</label><br>
									  <div class="form-group">
									    <s:select  name="fromhour" id="fromhour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select  name="fromminute" id="fromminute" list="minuteList" cssClass="form-control mmwidthmang" headerKey="0" headerValue="Select"/>
									     
									  </div>
									</div>
                                    </div>
                                    <%} %>
										  <div class="form-group" style="width: 7%;">
										    <s:textfield id="todate" name="todate" cssStyle="width: 100%;" cssClass="form-control" placeholder="To Date" />
										  </div>
										  <%if(loginfo.isAyushman() ||  loginfo.isParihar()){%>
			<div class="form-group" style="width:7%;">
                                    	 <div class="form-inline hhmm">
                                   <label for="exampleInputName2">HH:MM</label><br>
									  <div class="form-group">
									    <s:select  name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select  name="minute" id="minute" list="minuteList" cssClass="form-control mmwidthmang" headerKey="0" headerValue="Select"/>
									     
									  </div>
									</div>
                                    </div>
                                    <%} %>
										  <div class="form-group" style="width: 11%;">
										 <s:select cssStyle="width: 100%;" list="#{'0':'Transaction Mode','1':'Sales Return','2':'Sales','3':'Cancel'}" name="returnbill" cssClass="form-control" theme="simple"></s:select>   
										  </div>
										  <div class="form-group" style="width: 11%;">
										    <s:select cssStyle="width: 100%;" list="#{'0':'Payment Mode','Cash':'CASH','Card':'CARD','Credit':'CREDIT','Hospital':'HOSPITAL','NEFT':'NEFT','Estimate':'ESTIMATE','Online Payment':'ONLINE PAYMENT'}" name="paymode" cssClass="form-control" theme="simple"></s:select>
										  </div>
										  <div class="form-group" style="width: 15%;">
											<s:select cssStyle="width: 100%;" list="locationListPharmacy"   theme="simple" name="location"  cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
											</s:select>
										  </div>
										  <div class="form-group" style="width: 13%;">
										    <s:select cssStyle="width: 100%;" list="pharmacyUserList"   theme="simple" name="userid" cssClass="form-control chosen-select" listKey="userid" listValue="fullname" headerKey="0" headerValue="Select User" >
											</s:select>
										  </div>
										  
										  
										  
										  
										  		
										  <%--  <s:select headerKey="0" headerValue="Select" cssClass="form-control" list="#{'1':'Sales', '2':'Sales Return'}" name="filter_status" />--%>
										  <button type="submit" class="btn btn-primary">Go</button>
										   <div class="form-group">
													<!-- <button type="button" onclick="printReport()" class="btn btn-primary">Download xls</button> -->
													<a type="button" id="btnxls" title="Save As XLS" onclick="printReport()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
												<a type="button" class="btn btn-primary" title="Print" onclick="printDiv('page_printer')"><i class="fa fa-print"></i></a>
											</div>
										  </s:form>
										  </div>
										  <div class="col-lg-12 col-md-12 topback2">
										   		<div class="form-inline">
												   <div class="form-group">
														<span style="font-size: 15px;color: green;padding-top: 1px !important;">Total : <s:property value="totalamt"/></span>	
													</div>
												   <div class="form-group" style="float: right;padding-right: 67px;">
												  	<input type="button" class='btn btn-primary' onclick="openPopup('recivedcreditPharmacy')" value='Credit Recived '>
													</div>
												</div>
											</div>
										</div>
									<b style="font-size: 30px"><s:property value="Subcategoryname"/></b>
										<div class="" id="page_printer">
												<table class="table table-striped table-bordered tablesorter" width="100%">
											<thead>
												<tr class="tableback">
													<td>Sr</th>
													<td style="width: 12%;">Bill Date</td>
													<td style="width: 10%;">Payment Date</td>
													<td style="width: 7%;">Bill UserId</td>
													<td style="width: 7%;">Payment UserId</td>
													<td style="width: 7%;">Bill No</td>
													<td style="width: 12%;">UHID</td>
													<td style="width: 12%;">location</td>
													<td class="hidden" style="width: 4%;text-align: center;">Urgent</td>
													<td style="width: 26%;">Patient Details</td>
													<td style="width: 7%;">Paymode</td>
													<td class="hidden" style="width: 3%">Note</td>
													<td style="width: 9%;text-align: right;">Received</td>
													<td style="width: 9%;text-align: right;">Credit</td>
													<td style="width: 9%;text-align: right;">Refund</td>
													<td style="width: 9%;text-align: right;">Discount</td>
													<td style="width: 9%;text-align: right;">Card No</td>
													<!-- <td style="width: 9%;text-align: right;">Balance</td> -->
													<td style="width: 6%;">Status</td>
													<!-- <th></th>
													<th></th>
													<th></th>
													<th></th> -->
													
												</tr>
											</thead>
											<tfoot>
								               <tr style="color: green;">
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td><b>Total</b></td>
								                 <td></td>
								                 <td style="text-align: right;"><b>Rs.<s:property value="totalReceived"/></b></td>
								                 <td style="text-align: right;"><b>Rs.<s:property value="totalcredit"/></b></td>
								                 <td style="text-align: right;"><b>Rs.<s:property value="totalRefund"/></b></td>
								                 <!-- for parihar -->
								                 <td style="text-align: right;"><b>Rs.<s:property value="Totaldisc"/></b></td>
								                 
								                 <%-- <td style="text-align: right;"><b style="color: red;">Rs.<s:property value="totalBalance"/></b></td> --%>
								                 <td></td>
								                 <td></td>
								                <!--  <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td> -->
								               </tr>
								             </tfoot>
											<tbody>
												<%int i=0; %>
												<%String bgcolor = ""; %>
												<s:iterator value="priscriptionlist">
												
													<tr style="cursor: pointer;background-color: <%=bgcolor %>" id="<s:property value="id" />" class="even pointer" ondblclick="setEmrSelectedRows(<s:property value="id" />,<s:property value="clientId" />)">
														 <td><%=(++i) %></td>
														<td class="">
																<s:property value="lastmodified"/>
														</td>
														<td class="">
																<s:property value="paymentdate"/>
														</td>
														<td>
																<s:property value="userid"/>
														</td>
														<td>
																<s:property value="paymentuserid"/>
														</td>
														<td>
															<s:property value="billno"/>
														</td>
														<td>
														 	<s:if test="outp==1">
														 		<s:property value="abrivationid" /> 
															</s:if>
															<s:else>
																<s:property value="abrivationid" /> 
															</s:else>
														</td>
														<td>
														 		<s:property value="locationame" /> 
														</td>
														 
														<td class="hidden" style="text-align: center;"><span  class="blink_me"><i class="fa fa-circle" aria-hidden="true"></i></span></td>
														<td class=" ">
														<s:if test="outp==1">
															<img src="emr/img/medicineflag_new_small.png" class="img-responsive imflag"></img>
														</s:if>
														<s:elseif test="ipdid==0">
														  <img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag"></img> 
														</s:elseif>
														<s:else>
															<s:if test="urgentward==1">
																<span class="blink_me">
																	<img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag"></img>
														  		</span>
															</s:if>
															<s:else>
															<span>
																<img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag"></img>
														  	</span>
															</s:else>
															
														</s:else>
														
														<s:if test="outp==1">
														   <span ><s:property value="fullname" /></span>
														</s:if>
														<s:else>
															<span ><s:property value="fullname" /></span>
														</s:else>
														
														<s:if test="delivered==0">
														  <td class="">-</td>
														</s:if>
														<s:else>
														  <td class="">
														    	<s:property value="paymode"/>
														  </td>
														</s:else>
														
														<td class="hidden"><s:property value="advoice" /></td>
														<td style="text-align: right;">
																Rs.<s:property value="payment"/>
														</td>
														<td style="text-align: right;">
																Rs.<s:property value="credit"/>
														</td>
														<%if(!loginfo.isParihar()){%>
														<s:if test="returnbill>0">
														    <td style="text-align: right;">Rs.<s:property value="total"/></td>
														</s:if>  
														<s:else>
														   <td></td>
														</s:else>
														<%}else{ %>
														   <td style="text-align: right;">Rs.<s:property value="Refund_amt"/></td>
														<%} %>
														<%-- <s:if test="balance==0">
																<td style="text-align: right;">Rs.00</td>
														</s:if>
														<s:else>
														  <td style="text-align: right;"><a href="#" style="color: red;" >Rs.<s:property value="balance"/></a></td>
														</s:else> --%>
														<td><s:property value="discount"/></td>
														<td><s:property value="cardno"/></td>
														
														<td>
														    <s:if test="deleted==1">
																<span style="color: red;">	Canceled</span>		 												    
														    </s:if>
														   <s:elseif test="returnbill>0">
														    <span style="color: #ff8f04;">Returned</span>
															</s:elseif> 
														    <s:elseif test="estimate>0">
														     <span style="color: dodgerblue;"><a href="#"> Estimate</a></span>
														   </s:elseif>   
															<s:elseif test="delivered==0">
																<!--<a href="changestatusPharmacy?priscid=<s:property value="id"/>&status=0">New</a>
															-->
															       New
															</s:elseif>
															<s:elseif test="delivered==1">
																Delivered
															</s:elseif>
															<s:elseif test="delivered==2">
																<!--<a href="changestatusPharmacy?priscid=<s:property value="id"/>&status=1">In Process</a>
															--></s:elseif>
															
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
										</div>
								</div>
							</div>
							
							<s:form action="pharmacysalereportPharmacy" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromdate"></s:hidden>
							     <s:hidden name="todate"></s:hidden>
							     <s:hidden name="searchText"></s:hidden>
							     <s:hidden name="returnbill"></s:hidden>
							     <s:hidden name="paymode"></s:hidden>
							     <s:hidden name="userid"></s:hidden>
							     <s:hidden name="location"> </s:hidden>
							     <s:hidden name="Hour"> </s:hidden>
							     <s:hidden name="Minute"> </s:hidden>
							     <s:hidden name="Fromhour"> </s:hidden>
							     <s:hidden name="Fromminute"> </s:hidden>
								<div class="col-lg-12 col-md-12" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<%-- <jsp:include page="/common/pages/pagination.jsp"></jsp:include> --%>
								</div>
							</s:form> 
						</div>
			<!-- page end-->
		</section>
	<!-- /MAIN CONTENT -->
	<!--main content end-->


		
	
	
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
  



<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '450px'
								    });
								    
								});
							</script>
<script>
    function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    window.print();
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
	
	<%-- <script language="javascript">
    setTimeout(function(){
       window.location.reload(1);
    }, 30000);
   </script> --%>
   
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
