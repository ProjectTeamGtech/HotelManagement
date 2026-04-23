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
<script type="text/javascript" src="thirdParties/js/tpcharges.js"></script>

<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
		   						

<script type="text/javascript">

document.addEventListener('readystatechange', event => {
	document.cookie = "googtrans=/en/<%=loginInfo.getRegional_lang()%>";
});

function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
}
</script>
<style type="text/css">
.goog-te-banner-frame.skiptranslate {
    display: none !important;
    }
body {
    top: 0px !important;
    min-height: 0px !important;
    }
</style>
<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>  
 

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
	
	function updatepaymodesave(){
		$('#changepaymodemodels').modal( "hide" );
	   document.getElementById("changepaymodeform").submit();
	   
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
   
   $("#fromdate1").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
   $("#todate1").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
   $("#dummybilldate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
   
   $(function() {
	    $(".datepicker").datepicker();
	    $('.ui-datepicker').addClass('notranslate');
	});
   
    document.addEventListener("contextmenu", function(e){
		e.preventDefault();
		}, false); 
 
};
</script>
  <script>
			var myWindow;
			function expiryproductwithin31days() {
			     myWindow = window.open("expiryproductpopupProduct", "", "width=800, height=600, addressbar=no,");
			}
	</script>
<body>
<%boolean isregionallanguage= false; %>
<%
	if(!loginInfo.getRegional_lang().equals("en")){
		isregionallanguage = true;
	}
%>

<input type="hidden" name="invstcmyModalLabel" id="invstcmyModalLabel">
<input type="hidden" name="invstdatetimeid" id="invstdatetimeid">
<input type="hidden" name="invstpriscdoctornameid" id="invstpriscdoctornameid">

	<!-- **********************************************************************************************************************************************************
     MAIN CONTENT
     *********************************************************************************************************************************************************** -->
	<!--main content start-->
	
		<section>

			<!-- page start-->
			
				
					
						<div class="">
							
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
							
							
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									<a style="display: none;" href="#" onclick="opencPopup('getPatientRecordEmr')" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Create
										Prescription </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form action="Pharmacy" theme="simple" cssClass="form-inline search">
										<div class="form-group">
							                           		<span class="text-uppercase"><b style="font-weight: 900;">Pharmacy Dashboard</b> &nbsp; - &nbsp;</span>
							                           </div>
										  <div class="form-group" style="width: 15%;">
										    <s:textfield name="searchText" id="searchText" cssClass="form-control" style="width:100%;" placeholder="Search Patient / ID / Bill No"/>
										  </div>
										  <s:textfield id="fromdate" name="fromdate" cssClass="form-control" placeholder="From Date" cssStyle="width: 6%;"/>
										  <s:textfield id="todate" name="todate" cssClass="form-control" placeholder="To Date" cssStyle="width: 6%;"/>
										  
										  <s:select list="#{'0':'All','1':'Sales Return','2':'Sales'}" name="returnbill" cssClass="form-control" theme="simple"></s:select>
										  <s:select list="#{'0':'All','Cash':'CASH','Card':'CARD','Credit':'CREDIT','Hospital':'HOSPITAL','NEFT':'NEFT','Estimate':'ESTIMATE','Cheque':'CHEQUE','Online Payment':'ONLINE PAYMENT'}" name="paymode" cssClass="form-control" theme="simple"></s:select>
										  <div class="form-group">
										  <s:select list="#{'0':'All','2':'Self','1':'Third Party','3':'self free'}"  onchange="gettplist(this.value)"  name="payee" id="payee" cssClass="form-control"/>
										  </div>
										  <div class="form-group">
										  <div id="dstp">
										  <s:select list="ThirdPartyTypeList" listKey="id" listValue="companyName" id="tpid" headerKey="0" headerValue="Select Payer" name="tpid" title="select Third Party"
										     cssClass="form-control"   > </s:select>
										</div>
										</div>
										  <%--  <s:select headerKey="0" headerValue="Select" cssClass="form-control" list="#{'1':'Sales', '2':'Sales Return'}" name="filter_status" />--%>
										  <button type="submit" class="btn btn-primary">Search</button>
										   <div class="form-group">
												<!-- <button type="button" onclick="printReport()" class="btn btn-primary">Download xls</button> -->
												<a type="button"  title="Save As XLS" onclick="printReport()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
										   </div>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" onclick="openPopup('InitialDischarge')"  class="btn btn-primary hidden" title="Discharge Dashboard"><i class="fa fa-bed"></i></a>
										  <a href="#" onclick="openPopup('salepriscPharmacy')"  class="btn btn-primary hidden" title="Outdoor Patient">Sale</a>
										  
										  <div class="hidden" style="text-align: right;margin-top: -24px;">
										  	<a href="#" onclick="openPopup('reportpriscPharmacy')" class="btn btn-primary" title="Report"><i class="fa fa-line-chart" aria-hidden="true"></i> Report</a>
										  	<a href="#" class="btn btn-primary" title="Purchase List" onclick="openPopup('plistPharmacy')"><i class="fa fa-cart-arrow-down" aria-hidden="true"></i> P List</a>
										  </div>
										  <div class="form-group">
											<a href="#" class="btn btn-info" onclick="expiryproductwithin31days()">Product Which Expiry Within 31 Days</a>&nbsp; &nbsp;
										</div>
										</s:form>
										</div>
										</div>
								<b style="font-size: 30px"><s:property value="Subcategoryname"/></b>
										<div class="">
												<table class="table table-striped table-bordered tablesorter" width="100%">
											<thead>
												<tr class="tableback">
													<th style="width: 2%;">Sr. No</th>
													<th style="width: 12%;">Date</th>
													<th style="width: 7%;">User Id</th>
													
													<%if(isregionallanguage){ %>
														<th style="width: 7%;">Bill num</th>
													<%}else{ %>
														<th style="width: 7%;">Bill No.</th>
													<%} %>
													
													<th style="width: 12%;">UHID</th>
													<th class="hidden" style="width: 4%;text-align: center;">Urgent</th>
													<th style="width: 26%;">Patient Details</th>
													<th style="width: 7%;">Pay mode</th>
													<th class="hidden" style="width: 3%">Note</th>
													<th style="width: 9%;text-align: right;">Received</th>
													<th style="width: 9%;text-align: right;">Refund</th>
													<th style="width: 9%;text-align: right;">Balance</th>
													<th style="width: 6%;">Status</th>
													<th style="width: 6%;">Payer Type</th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th>Discount</th>
													<%if(loginfo.getIskunal()==1){ %>
														<th></th>
													<%} %>
												</tr>
											</thead>
											<tfoot>
								               <tr style="color: green;">								    
								                 <td></td>
								                  <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td><b>Total</b></td>
								                 <td></td>
								                 <td class="notranslate" style="text-align: right;"><b>Rs.<s:property value="totalReceived"/></b></td>
								                 <td class="notranslate" style="text-align: right;"><b>Rs.<s:property value="totalRefund"/></b></td>
								                 <td class="notranslate" style="text-align: right;"><b style="color: red;">Rs.<s:property value="totalBalance"/></b></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <td></td>
								                 <%if(loginfo.getIskunal()==1){ %>
														 <td></td>
												 <%} %>
								               </tr>
								             </tfoot>
											<tbody>
												<%String bgcolor = ""; %>
												<%int srno=0; %>
												<s:iterator value="priscriptionlist">
												<%-- <s:if test="dstatus==1">
													<% bgcolor = "rgba(229, 217, 129, 0.46)"; %>
												</s:if>
												<s:else>
													<% bgcolor = "white"; %>
												</s:else> --%>
													
													<tr style="cursor: pointer;color:#50C878;background-color: <%=bgcolor %>" id="<s:property value="id" />" class="even pointer" ondblclick="setEmrSelectedRows(<s:property value="id" />,<s:property value="clientId" />)">
														<td><%=(++srno) %></td>
														<td class="">
															<s:if test="deleted==1">
																<span style="text-decoration: line-through;cursor: pointer;"><s:property value="lastmodified"/></span>														    
														    </s:if>
														    <s:else>
														    	<s:property value="lastmodified"/>
														    </s:else>
														</td>
														<td>
															<s:if test="deleted==1">
																<span style="text-decoration: line-through;cursor: pointer;"><s:property value="userid"/></span>														    
														    </s:if>
														    <s:else>
														    	<s:property value="userid"/>
														    </s:else>
														</td>
														<td><a href="#" onclick="editviewparentprisc(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,<s:property value="conditionid"/>)"></a>
														<s:if test="id>0">
														  <s:if test="billno>0">
														  	<s:if test="opdipdprisc==1">
														  		<a href="#" onclick="openMultipleRequestPopup(<s:property value="id"/>)"> <s:property value="id"/>/ </a> 
														  	</s:if>
														    <s:if test="estimate==0">
														         <%-- <s:property value="id"/> --%>
														    </s:if>
														     
														  </s:if>
														  <s:else>
														  	<%-- s  --%>
														 	<a href="#" onclick="openMultipleRequestPopup(<s:property value="id"/>)"> <s:property value="id"/> </a> 
														  </s:else>
														</s:if>
														 <s:if test="estimate>0">
														 </s:if>
														 <s:else>
														 	<s:if test="deleted==1">
																<span style="text-decoration: line-through;cursor: pointer;"><s:property value="billno"/></span>														    
														    </s:if>
														    <s:else>	
														    	<s:if test="refundid==0">
														    		<s:if test="dummybillno>0">
														    			<s:property value="dummybillno"/>
														    		</s:if>
														    		<s:else>
														    			<s:property value="billno"/>
														    		</s:else>
														    	</s:if>
														    	<s:else>
																	<s:if test="dummybillno>0">
														    			<s:property value="dummybillno"/>(<s:property value="refundid"/>)
														    		</s:if>
														    		<s:else>
														    			<s:property value="refundid"/>
														    		</s:else>														    	
														    	   
														    	</s:else>
														    </s:else>
														 </s:else>
														 <s:if test="edited==1">
				                            	 			<b style="color: red;">[Modified]</b>
				                            	 		 </s:if> 
														 </td>
														 <td>
														   <s:if test="outp==1">
														      <s:property value="abrivationid" /> 
															</s:if>
															<s:else>
																<s:property value="abrivationid" /> 
															</s:else>
														
														 </td>
														 
														<td class="hidden" style="text-align: center;"><span  class="blink_me"><i class="fa fa-circle" aria-hidden="true"></i></span></td>
														<td class=" ">
														<s:if test="outp==1">
															<img src="emr/img/medicineflag_new_small.png" class="img-responsive imflag"></img>
														</s:if>
														<%-- <s:elseif test="ipdid==0">
														  <img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag"></img> 
														</s:elseif> --%>
														<s:elseif test="reqfromlocation==0">
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
														</s:elseif>
														<s:elseif test="reqfromlocation==1">
														  <img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag"></img> 
														</s:elseif>
														<s:elseif test="reqfromlocation==2">
														  <label style="color: red;">OT</label>
														</s:elseif>
														
														<s:if test="outp==1">
															<s:if test="deleted==1">
																<span style="text-decoration: line-through;cursor: pointer;"><s:property value="fullname"/></span> |
														    </s:if>
														    <s:else>
														    	<a href="#" onclick="showbillspatient(<s:property value="pclientid"/>,0)" ><s:property value="fullname" /></a> |
														    </s:else>
														</s:if>
														<s:else>
															<s:if test="deleted==1">
																<span style="text-decoration: line-through;cursor: pointer;"><s:property value="fullname" /></span> |
														    </s:if>
														    <s:else>
														    	<a href="#" onclick="showbillspatient(<s:property value="clientId"/>,1)"><s:property value="fullname" /></a> |
														    </s:else>
														</s:else>
														
														<%-- <s:if test="outp==0">
														 <s:property value="ageandgender" /> 
														 </s:if> --%>
														 	<s:if test="deleted==1">
																<span style="text-decoration: line-through;cursor: pointer;">
																	<s:if test="ipdid!=0">
														     			<s:if test="ipdid!=0"><s:property value="wardname" /> / <s:property value="bedname" /></s:if> |
																	</s:if> 
																</span>														    
														    </s:if>
														    <s:else>
																<s:if test="ipdid!=0">
														     		<s:if test="ipdid!=0"><s:property value="wardname" /> / <s:property value="bedname" /></s:if> |
																</s:if>    	
														    </s:else>
															<s:if test="deleted==1">
																<span style="text-decoration: line-through;cursor: pointer;"><s:property value="mobile"/></span>														    
														    </s:if>
														    <s:else>
														    	<s:property value="mobile"/>
														    </s:else>
														
															<%-- <s:if test="deleted==1">
																	
																	<b >(Canceled)</b>													    
														    </s:if> --%>
														 
														<%-- <s:if test="estimate>0">
														    <b style="color: red;"><a href="saleestimatePharmacy?estimatebill=<s:property value="estimatebill"/>">(Estimate)</a> </b>
														</s:if>   --%>
														
														</td>
														<%-- <td class=""><s:property value="practitionername"/></td> --%>
														
														<s:if test="delivered==0">
														  <td class="">-</td>
														</s:if>
														<s:else>
														  <td class="notranslate">
														  	<s:if test="deleted==1">
																<%-- <span style="text-decoration: line-through;cursor: pointer;"><s:property value="paymode"/></span> --%>														    
														    	<span style="text-decoration: line-through;cursor: pointer;"><s:property value="newpaymentmode"/></span>
														    </s:if>
														    <s:else>
														    	<%-- <s:property value="paymode"/> --%>
														    	<s:property value="newpaymentmode"/>
														    </s:else>
														  </td>
														</s:else>
														
														
														<!--<td class="hidden">&nbsp;&nbsp;<a href="#" data-toggle="modal" onclick="onAdd(<s:property value="clientId"/>,<s:property value="prectionerid"/>,<s:property value="conditionid"/>)"><i class="fa fa-plus-square"></i></a>
															</td>
														
														--><td class="hidden"><s:property value="advoice" /></td>
														
														<td style="text-align: right;" class="notranslate">
															<s:if test="deleted==1">
																<span style="text-decoration: line-through;cursor: pointer;">Rs.<s:property value="payment"/></span>														    
														    </s:if>
														    <s:else>
														    	Rs.<s:property value="payment"/>
														    </s:else>
														</td>
														<s:if test="returnbill>0">
														    <td class="notranslate" style="text-align: right;">Rs.<s:property value="total"/></td>
														</s:if>  
														<s:else>
														   <td></td>
														</s:else>
														<s:if test="balance==0">
															<s:if test="deleted==1">
																<td class="notranslate" style="text-decoration: line-through;cursor: pointer;text-align: right;">Rs.00</td>												    
														    </s:if>
														    <s:else>
														    	<td class="notranslate" style="text-align: right;">Rs.00</td>
														    </s:else>
														</s:if>
														<s:else>
														  <%-- <td style="text-align: right;"><a href="#" style="color: red;" onclick="openBalPopup('<s:property value="billno"/>','<s:property value="fullname"/>','<s:property value="balance"/>')" >Rs.<s:property value="balance"/></a></td> --%>
															<td class="notranslate" style="text-align: right;">
																<s:if test="discount_status==1">
																	<label style="color: red;">Rs.<s:property value="balance"/></label>
																</s:if>
																<s:else>
																	<a href="#" style="color: red;" onclick="openBalPopupNew('<s:property value="billno"/>')" >Rs.<s:property value="balance"/></a>
																</s:else>
																
															</td>
														</s:else>
														
														
														<td class="notranslate">
														    <s:if test="deleted==1">
																<span style="color: red;">	Canceled</span>		 												    
														    </s:if>
														   <s:elseif test="returnbill>0">
														    <span style="color: #ff8f04;">Returned</span>
															</s:elseif> 
														    <s:elseif test="estimate>0">
														     <span style="color: dodgerblue;"><a href="saleestimatePharmacy?estimatebill=<s:property value="estimatebill"/>"> Estimate</a></span>
														   </s:elseif>   
															<s:elseif test="delivered==0">
																<!--<a href="changestatusPharmacy?priscid=<s:property value="id"/>&status=0">New</a>
															--><div >
															     <img src="dashboardicon/newdiet.gif">
															      </div>
															</s:elseif>
															<s:elseif test="delivered==1">
																Delivered
															</s:elseif>
															<s:elseif test="delivered==2">
																<!--<a href="changestatusPharmacy?priscid=<s:property value="id"/>&status=1">In Process</a>
															--></s:elseif>
															
														</td>
														<td><s:property value="Third_party_name" /></td>
														<!--<td>
														 <s:if test="delivered==1">
														 </s:if>
														 <s:else>
														 
														     <s:if test="billno!=0">
																<a href="#" onclick="openPopup('editmedicinePharmacy?selectedid=<s:property value="id"/>&billno=<s:property value="billno"/>')"><i class="fa fa-pencil-square-o"></i></a>								     
														     </s:if>
														 	 
														 </s:else>
														</td>
														-->
														<td class="">
														<%if(loginInfo.getUserType()==2 || loginfo.isReturn_medicine()){ %>
														<s:if test="returnbill==0">
														  <s:if test="billno!=0">
														         <s:if test="estimate>0">
														         </s:if>
														          <s:elseif test="deleted==1">
														         </s:elseif>
														          <s:elseif test="opdipdprisc==1">
														      </s:elseif>
														         <s:else>
															         <s:if test="discount_status!=1">
															         	<a href="#" onclick="openSamePopup('returnmedicinePharmacy?selectedid=0&billno=<s:property value="billno"/>')" title="Return"><i class="fa fa-reply" aria-hidden="true"></i></a>
															         </s:if>
															           
														         </s:else>
														  </s:if>
														  <s:else>
														   	  
														  </s:else>
														  </s:if>
														  <%}%>
														</td>
														<td>
															<s:if test="billno>0">
																 <s:if test="estimate>0">
															     </s:if>  
															     <s:elseif test="opdipdprisc==1">
															     </s:elseif>
															     <s:else>
																    <a href="#" style="color: #555;" onclick="openBlankPopup('viewbillPharmacy?selectedid=0&billno=<s:property value="billno"/>')"> <i class="fa fa-print" aria-hidden="true"></i></a>
															     </s:else>
														  	</s:if>
														</td>
														
														
														<td>
															<%if(loginInfo.isEditpharmacy_bill() || loginInfo.getSuperadminid()==1){ %>
															 <s:if test="deleted!=1">
															 	<s:if test="sameeditdate==1">
															 	<s:if test="newpaymentmode=='Credit'"> 
															 		<s:if test="balance>0">
															 			<s:if test="returnbill==0">
																	 		<s:if test="discount_status!=1">
																	 			<s:if test="discount_status!=3">
																	 				<a href="#" onclick="openPopup('editpharmacybillPharmacy?selectedid=0&billno=<s:property value="billno"/>')"><i class="fa fa-pencil-square-o"></i></a>
																	 			</s:if>
																			</s:if>
																		</s:if>
																     </s:if>
															 	</s:if>	
															 	</s:if>	
															 </s:if>
															 <%} %>
														</td>
														
														<td>
														
														<%--  <%if(loginInfo.isEdit_bill() || loginInfo.getUserType()==2) {%>
														     <s:if test="billno>0">
														     <s:if test="estimate>0">
														     </s:if> 
														      <s:elseif test="deleted==1">
														      </s:elseif>
														       <s:elseif test="returnbill==1">
														      </s:elseif>
														      <s:elseif test="opdipdprisc==1">
														      </s:elseif>
														     <s:else>
														        <a href="editbillPharmacy?billno=<s:property value="billno"/>&selectedid=0" style="color: #d9534f;" > <i class="fa fa-edit" aria-hidden="true" style="color: #d9534f;"></i></a>
														     </s:else>
														     </s:if>
															<%} %> --%>
														</td>
														<td>
														   <%if(loginInfo.isDelete_bill() || loginInfo.getUserType()==2) { %>
														    <s:if test="deleted==1">
																															    
														    </s:if>
														    <s:else>
														    	<s:if test="returnbill>0">
														    	
														    	</s:if>
														    	<s:elseif test="isdeletable==0">
															    	
															      <%-- <a href="deletebillPharmacy?billno=<s:property value="billno"/>" style="color: #d9534f;" onclick="return confirmDel()"> <i class="fa fa-times" aria-hidden="true" style="color: #d9534f;"></i></a> --%>
																	 <s:if test="estimate>0">
															     	 	<a href="#" style="color: #d9534f;" onclick="deletePharmacyBill(<s:property value="billno"/>,0)"> <i class="fa fa-times" aria-hidden="true" style="color: #d9534f;"></i></a>
															     	 </s:if> 
															     	 <s:else>
															     		<a href="#" style="color: #d9534f;" onclick="deletePharmacyBill(<s:property value="billno"/>,1)"> <i class="fa fa-times" aria-hidden="true" style="color: #d9534f;"></i></a>
															     	</s:else>
																</s:elseif>
																														   
														    </s:else>
														  <%} %>
														</td>
														<td style="text-align: center;">
														 	<s:if test="deleted==1">
																															    
														    </s:if>
														    <s:else>
															    <%-- <s:if test="newpaymentmode=='Credit'"> --%>
																	<s:if test="balance>0">
																		<s:if test="discount>0">
																			<s:if test="discount_status==2">
																				Applied
																			</s:if>
																			<s:else>
																				Direct Disc.
																			</s:else>
																		</s:if>
																		<s:else>
																			<s:if test="discount_status==0">
																				<a href="#" onclick="applypharmacydiscount(<s:property value="billno"/>)"> 
																	 				Request Discount
																				</a>
																			</s:if>
																			<s:elseif test="discount_status==1">
																				Requested
																			</s:elseif>
																			<s:elseif test="discount_status==2">
																				Applied
																			</s:elseif>
																			<s:elseif test="discount_status==3">
																				Product Return
																			</s:elseif>
																		</s:else>
																	</s:if>
																<%-- </s:if> --%>
														    </s:else>
														 </td>
														<%if(loginfo.getIskunal()==1){ %>
														 	<td style="text-align: center;">
															 	<a href="#" onclick="changepharmacybillno(<s:property value="billno"/>,'<s:property value="lastmodified"/>')"> 
															 		<i class="fa fa-refresh fa-spin" aria-hidden="true"></i>
																		<span class="sr-only">Change Bill No</span>
															 	</a>
														 	</td>
												 		<%} %>
													</tr>
												</s:iterator>
											</tbody>
										</table>
										</div>
								</div>
							</div>
							
							<s:form action="Pharmacy" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromdate"></s:hidden>
							     <s:hidden name="todate"></s:hidden>
							     <s:hidden name="searchText"></s:hidden>
							     <s:hidden name="returnbill"></s:hidden>
							     <s:hidden name="paymode"></s:hidden>
									<div class="col-lg-12 col-md-12" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form> 
						</div>
			<!-- page end-->
		</section>
	<!-- /MAIN CONTENT -->
	<!--main content end-->






<div id="clearbal1" class="modal fade" role="dialog" style="z-index: 9999;background-color: rgba(0, 0, 0, 0.5);">
       
      
         <div class="modal-dialog modal-sm">
           <!-- Modal content-->
           
           <div class="modal-content">
           
             <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
              
               <h4 class="modal-title"><span class="notranslate">Receipt from</span> (<label id="patiName1"></label>) </h4>
             </div>
             <div class="modal-body">
               <s:form action="getbalpaymentPharmacy" theme="simple" id="formbal1"   method="post" >
                <input type="hidden" name="clientid" id="pid1" />
               <input type="hidden" name="flag" id="flag" />
               <input type="hidden" value="0" name="discount" id="discamt" />
               <input type="hidden" name="fromdashboard" value="1"  />
                <input type="hidden" name="clearbilltotalids"  id="clearbilltotalids" />
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               		<div class="col-lg-6 col-md-6 col-xs-6">
                		<div class="form-group">
              				<label for="email">Total Amount</label>
              				<input type="text" id="tttbal" readonly="readonly" class="form-control" style="background-color: beige;">
            			</div>
               		</div>
               		<div class="col-lg-6 col-md-6 col-xs-6">
                		<div class="form-group">
              				
            			</div>
               		</div>
               </div>
               
               
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               		<div class="col-lg-6 col-md-6 col-xs-6">
                		<div class="form-group">
              				<label for="email">Payment Amount</label>
              				<input type="text" id="bal1" onkeyup="calBalDiscount()" name="payamt"  class="form-control" >
            			</div>
               		</div>
               		<div class="col-lg-6 col-md-6 col-xs-6">
                		<div class="form-group">
              				<label for="email">Date</label>
              				<s:textfield  cssClass="form-control" name="commencing" id="date" />
            			</div>
               		</div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;border-bottom: 1px solid #ddd;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              		  <label for="email" style="color:red;">Discount</label>
		              <select class="form-control" name="" onchange="calBalDiscount()" id="disctp">
		               		<option value="0">Cash</option>
		              		<option value="1">Percent</option>
		              </select>
            </div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                 <input type="number" id="discrate" onkeyup="calBalDiscount()" name="" class="form-control"  />
	                            	
               </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;border-bottom: 1px solid #ddd;margin-bottom: 15px;">
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                    <p style="font-size:15px;color: green;">Total To Paid </p>            	
                    </div>
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
                        <input type="text" readonly="readonly"  id="finalpayamt" style="background-color: beige;"  class="form-control"  />
                    </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              <label for="email">Payment mode</label>
              <select class="form-control notranslate" onchange="setPaymodeNew(this.value)" name="paymode" id="paymode">
   					<option value="Cash">CASH</option>
                  	<option value="Card">CARD</option>
                  	<option value="Cheque">CHEQUE</option>
                  	<option value="NEFT">NEFT</option>
                  	<option value="Hospital">HOSPITAL</option>
                  	<option value="Online Payment">ONLINE PAYMENT</option>
              </select>
            </div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                            	<input type="text" id="card1" name="card" placeholder="Enter 4 Digit No" class="form-control hidden"  />
	                            	<input type="number" id="chequeno1" name="chequeno" placeholder="Enter Cheque No" class="form-control hidden"  />
	                            	<input type="text" id="neftid1" name="neftid" placeholder="Enter Transaction ID" class="form-control hidden"  />
               </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12">
            <div class="form-group">
              <label for="email">Payment Notes</label>
              <textarea type="text" rows="3" name="paynotes" id="paynotes" class="form-control" placeholder="write notes"></textarea>
            </div>
             <div class="form-group hidden">
              <label for="email">Note:</label>
              <label class="text-danger">Discount text box is non editable when any bill have discount. So clear that discount bill separately.</label>
            </div>
               </div>
               </s:form>
             </div>
                
             <div class="modal-footer">
               <input type="button" class="btn btn-success disblebtnone" onclick="paybalance1()" value="Take Payment" />
             </div>
           </div>
          
       
         </div>
     
       </div>
							
							
<!-- Modal -->
       <div id="clearbal" class="modal fade" role="dialog" style="background-color: rgba(0, 0, 0, 0.5);z-index: 9999">
       
      
         <div class="modal-dialog modal-sm">
           <!-- Modal content-->
           
           <div class="modal-content">
           
             <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
              
               <h4 class="modal-title"><span class="notranslate">Receipt from</span> (<label id="patiName"></label>) </h4>
             </div>
             <div class="modal-body">
               <s:form action="getbillbalancePharmacy" theme="simple" id="formbal" method="post" >
               <input type="hidden" name="billno" id="billno" />
               <input type="hidden" value="0" name="discount1" id="singlediscamt" />
              <!--  <input type="hidden" value="0" name="discinper" id="discinper" >  -->
              <input type="hidden" value="0" name="discinper" id="discinper1" > 
                <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               		<div class="col-lg-6 col-md-6 col-xs-6">
                		<div class="form-group">
              				<label for="email">Bill Amount</label>
              				<input type="text" id="ttbalamt" readonly="readonly"  class="form-control" style="background-color: beige;">
            			</div>
               		</div>
               		<div class="col-lg-6 col-md-6 col-xs-6">
                		<div class="form-group">
              				<label for="email">Balance Amount</label>
              				<input type="text" id="ttbal" readonly="readonly"  class="form-control" style="background-color: beige;">
            			</div>
               		</div>
               </div>
               
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               		<div class="col-lg-6 col-md-6 col-xs-6">
                		<div class="form-group">
              				<label for="email">Payment Amount</label>
             				<input type="text" id="bal" name="payamt" onchange="calSingleBalDiscount()"  class="form-control" >
            			</div>
               		</div>
               		<div class="col-lg-6 col-md-6 col-xs-6">
                		<div class="form-group">
              				<label for="email">Date</label>
              				<s:textfield  cssClass="form-control" name="commencing" id="date" />
            			</div>
               		</div>
               </div>
               
               <div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;border-bottom: 1px solid #ddd;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              		<label for="email" style="color:red;">Discount</label>
		              <select class="form-control" onchange="calSingleBalDiscount()"  id="singlebilldisctype">
		              		<option value="0">Cash</option>
		              		<option value="1">Percent</option>
		              </select>
            	</div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6"  style="margin-top: 22px;">
	                 <input type="number" value="0" onchange="calSingleBalDiscount()" onkeyup="calSingleBalDiscount()"  id="singlebilldisc" class="form-control"  />
	                            	
               </div>
               </div>
                <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;border-bottom: 1px solid #ddd;margin-bottom: 15px;">
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                    <p style="font-size:15px;color: green;">Total To Paid </p>            	
                    </div>
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
                        <input type="text"  id="singlefinalpayamt" readonly="readonly" style="background-color: beige;"  class="form-control"  />
                    </div>
               </div>
               
               <%-- <div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;border-bottom: 1px solid #ddd;margin-bottom: 15px;">
               		<p style="font-size:15px;margin: 3px 15px 3px 15px;color: green;">Total To Paid <span style="float: right;"> Rs.00.00</span></p>
               </div> --%>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              <label for="email">Payment mode</label>
              <select class="form-control  notranslate" onchange="setPaymodeNew(this.value)" name="paymode" id="paymode">
     					<option value="Cash">CASH</option>
                    	<option value="Card">CARD</option>
                    	<option value="Cheque">CHEQUE</option>
                    	<option value="NEFT">NEFT</option>
                    	<option value="Hospital">HOSPITAL</option>
                    	<option value="Online Payment">ONLINE PAYMENT</option>
              </select>
            </div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                            	<input type="text" id="card" name="card" placeholder="Enter 4 Digit No" class="form-control hidden"  />
	                            	<input type="number" id="chequeno" name="chequeno" placeholder="Enter Cheque No" class="form-control hidden"  />
	                            	<input type="text" id="neftid" name="neftid" placeholder="Enter Transaction ID" class="form-control hidden"  />
               </div>
               </div>
               
               
               <div class="col-lg-12 col-md-12 col-xs-12">
               
            <div class="form-group">
              <label for="email">Payment Notes</label>
              <textarea type="text" rows="3" name="paynotes" id="paynotes" class="form-control" placeholder="write notes"></textarea>
            </div>
            <div class="form-group hidden">
              <label for="email">Note:</label>
              <label class="text-danger">Discount text box is non editable when discount already given or payment done against bill.</label>
            </div>
             
               </div>
               </s:form>
             </div>
                
             <div class="modal-footer">
               <input type="button" class="btn btn-success disblebtnone" onclick="paybalance()" value="Take Payment" />
             </div>
           </div>
          
       
         </div>
     
       </div>






<!-- Stock purchase -->
<div id="purstock" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Requested Stock</h4>
      </div>
      <div class="modal-body">
         <div class="row col-lg-12 col-md-12 col-xs-12 col-sm-12">
            <s:iterator value="requestedMedicineList">
        		<b>Request Date: <s:property value="lastmodified"/></b>
        		<s:iterator value="requestedMedicineList">
        		    <p style="margin:0px;">Qty:<s:property value="reqqty"/> | <s:property value="name"/></p>
        		</s:iterator>
        		<hr>
        	</s:iterator>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Print</button>
      </div>
    </div>

  </div>
</div>





<!-- Patinet Details View Modal -->
<div id="detailsview" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Bill Transaction Report</h4>
      </div>
      <div class="modal-body">
      <div id="page_printer" class="minhesigh">
      <input type="hidden" id="paybillclientid">
      <input type="hidden" id="paybillflag">
      <input type="hidden" id="paybilldiscountstatus">
       <h4 class="modal-title">Patient Name - <SPAN id='tempname'></SPAN></h4>
       <div style="padding-bottom: 5px;padding-top: 5px; ">
       <label>From Date :</label>
       <input type="text" placeholder="From Date" name="fromdate1"  id="fromdate1" class="form-control" style="width: 11%;border-color : #4b88bd" >
         &nbsp;&nbsp;&nbsp;
        <label>To Date :</label>
       <input type="text" placeholder="To Date" name="todate1" id="todate1" class="form-control" style="width: 11%;border-color : #4b88bd">
       &nbsp;&nbsp;&nbsp;
       <input type="button" value="Search" class="btn btn-success" onclick="getBillHistByDate()">
       <input type="hidden" name="clienttemp" id="clienttemp">
       <input type="hidden" name="flagtemp" id="flagtemp">
       </div>
      	<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;" id="newdiv">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden-lg hidden-md visible-print" style="border-bottom: 1px solid #ddd;" id="letterHead">
        		<h4><s:property value="clinicName"/></h4>
        		<h5><s:property value="clinicaddress"/></h5>
        		<h5>Website:<s:property value="websiteUrl"/>, Email:<s:property value="clinicemail"/>, Contact : <s:property value="clinicphoneno"/></h5>
        	</div>
         <table class="table table-responsive" >
          <thead>
           <tr>
            <th><input type="checkbox" onchange="selectallclearbalcheck(this)"></th>
            <th>Sr. No</th>
            <th>Bill</th>
            <th>Bill ID</th>
            <th>Bill Date</th>
            <th>Bill Type</th>
            <th style="text-align:right;">Total Discount</th>
            <th style="text-align:right;">Total Amount</th>
            <th style="text-align:right;">Paid Amount</th>
            <th style="text-align:right;">Balance</th>
            <th style="text-align:right;">Return Amount</th>
            <th>Discount</th>
            <th>Change Mode</th>
            <th>Receipt</th>
            <th></th>
            <th></th>
           
           </tr>
          </thead>
          <tfoot style="background-color: rgba(239, 239, 239, 0.35);">
        <tr style="color: green;" id="tfootdata" class="notranslate">
          <td></td>
          
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>Total</td>
          <td style="text-align:right;">1731.70</td>
          <td style="text-align:right;">1700.70</td>
          <td style="text-align:right;"><a href="#" style="color: green;">31.00</a></td>
          <td style="text-align:right;">1700.70</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </tfoot>
          <tbody id="tbodydata" class="notranslate">
           
          </tbody>
         </table>
        </div>
      </div>
      </div>
      <div class="modal-footer" >
      	<div id="takepaymentdiv">
      	
      	</div>
      	<div id="printbtndiv">
      		<a href="#" class="btn btn-warning hidden-print" onclick="printDiv('newdiv')" style="margin-top: 15px;">Print</a>
      	</div>
        
      </div>
    </div>

  </div>
</div>



		
							
<div id="priscrequestpopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Prescription Pop Up</h4>
      </div>
      <div class="modal-body">
      		<div class="" style="overflow: scroll; width: 100%; height: 300px;">
         		<table class="table my-table xlstable table-striped table-bordered" style="width:100%;">
          			<thead>
           				<tr>
           					<th>Sr.no</th>
            				<th>Date</th>
            				<th>Id</th>
            				<th>Bill No</th>
            				<th>Status</th>
            			</tr>
          			</thead>
          			<tbody id="priscrequesttbody">
           
          			</tbody>
          		</table>
			</div>
			<div class="form-group">
					<label class="text-danger">Note:</label>
					<label class="text-danger">Please click on Id for check availability</label>
			</div>
      </div>
      <div class="modal-footer">
        
      </div>
    </div>

  </div>
</div>


<div id="parttimepaymentlist" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Part Time Payment Receipt</h4>
      </div>
      <div class="modal-body">
      		<div class="" style="overflow: scroll; width: 100%; height: 300px;">
         		<table class="table my-table xlstable table-striped table-bordered" style="width:100%;">
          			<thead>
           				<tr>
           					<th>Sr.no</th>
            				<th>Date</th>
            				<th>Payment</th>
            				<th>Paymode</th>
            				<th></th>
            			</tr>
          			</thead>
          			<tbody id="parttimepaymenttbody">
           
          			</tbody>
          		</table>
			</div>
			
      </div>
      <div class="modal-footer">
        
      </div>
    </div>

  </div>
</div>

<div id="patientpaymentmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Payment Receipt</h4>
      </div>
      <div class="modal-body">
      		<div class="" style="overflow: scroll; width: 100%; height: 300px;">
         		<table class="table my-table xlstable table-striped table-bordered" style="width:100%;">
          			<thead>
           				<tr>
           					<th>Sr.no</th>
            				<th>Date</th>
            				<th>Payment</th>
            				<th>Pay mode</th>
            				<th>Change Pay Mode</th>
            			</tr>
          			</thead>
          			<tbody id="patientpaymentmodeltbody">
           
          			</tbody>
          		</table>
			</div>
			
      </div>
      <div class="modal-footer">
        
      </div>
    </div>

  </div>
</div>


<div id="changepaymodemodels" class="modal fade" role="dialog" style="z-index: 9999;background-color: rgba(0, 0, 0, 0.5);">
       
      
         <div class="modal-dialog modal-sm">
           <!-- Modal content-->
           
           <div class="modal-content">
           
             <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
              
               <h4 class="modal-title">Change Paymode </h4>
             </div>
             <div class="modal-body">
               <s:form action="savenewpaymodePharmacy" theme="simple"  id="changepaymodeform"  method="post" >
               <s:hidden id="change_paymode_id" name="change_paymode_id"></s:hidden>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;border-bottom: 1px solid #ddd;margin-bottom: 15px;">
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                    <p style="font-size:15px;color: green;">Amount :</p>            	
                    </div>
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
                         <p style="font-size:15px;color: green;" id="chngepaymodeamt"></p>            	
                    </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              <label for="email">Payment Mode</label>
              <select class="form-control" onchange="changePaymodeNew(this.value)" name="paymode" id="changepaymode">
              						<option value="Cash">CASH</option>
	                            	<option value="Card">CARD</option>
	                            	<option value="Cheque">CHEQUE</option>
	                            	<option value="NEFT">NEFT</option>
	                            	<option value="Hospital">HOSPITAL</option>
              </select>
            </div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                            	<input type="text" id="changepaymodecard" name="card" placeholder="Enter 4 Digit No" class="form-control hidden"  />
	                            	<input type="number" id="changepaymodechequeno" name="chequeno" placeholder="Enter Cheque No" class="form-control hidden"  />
	                            	<input type="text" id="changepaymodeneftid" name="neftid" placeholder="Enter Transaction ID" class="form-control hidden"  />
               </div>
               </div>
           
               </s:form>
             </div>
                
             <div class="modal-footer">
               <input type="button" class="btn btn-success disblebtnone" onclick="updatepaymodesave()" value="Update" />
             </div>
           </div>
          
       
         </div>
     
       </div>

	<!-- create Prescription -->
		 <div class="modal fade" id="priscriptionpopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="priscmyModalLabel">Create Prescription For <b class="pname">NAME: </b></h5>
				</div>
				<div class="modal-body">
						
						
					
				    
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="insertViewEmrPriscription()" id="prescs_save_btn">Save</button>
						
						 <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)" id="prescs_save_print_btn">Save & Print 11</button>
 
					<button  type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
				<div class="form-group text-right hidden-print">
					<a type="button" class="btn btn-primary btn-lg savebigbtn"
						title="Print" onclick="dotmatrix_print()">Print DOTMATRIX</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
<div id="deletemodel2" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="bill_no">
      		<input type="hidden" id="isbillorestimate">
        	<textarea rows="3"  class="form-control" id="delete_reason" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="deletePharmacyBill1()" value="Okay">
      </div>
    </div>

  </div>
</div>

<div id="changebillnopopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Change Bill No and Date:</h4>
      </div>
      <div class="modal-body">
      			<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;border-bottom: 1px solid #ddd;margin-bottom: 15px;">
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                    <p style="font-size:15px;color: green;">Bill No:</p>            	
                    </div>
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
                         <p style="font-size:15px;color: green;" id="changepopup_bill_div"></p>            	
                    </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<label for="email">Bill Date:</label>
		            	</div>
	               </div>
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<p style="font-size:15px;" id="changepopup_date"></p>       
		            	</div>
	               </div>
      		   </div>
      		   <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<label for="email">New Bill No:</label>
		            	</div>
	               </div>
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		                	<input type="hidden" id="changepopup_bill_no" class="form-control"  />
		              		<input type="number" id="dummybillno" class="form-control"  />
		            	</div>
	               </div>
      		   </div>
      		   <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<label for="email">New Bill Date:</label>
		            	</div>
	               </div>
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<input type="text" readonly="readonly" id="dummybilldate" class="form-control"  />
		            	</div>
	               </div>
      		   </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="validatechangedummybill()" value="Change">
      </div>
    </div>

  </div>
</div>
</div>

	<div id="requestdiscopopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Request Discount</h4>
      </div>
      <div class="modal-body">
      			<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;border-bottom: 1px solid #ddd;margin-bottom: 15px;">
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                    <p style="font-size:15px;color: green;">Bill Number:</p>            	
                    </div>
                    <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
                         <p style="font-size:15px;color: green;" id="requestdisc_bill_div"></p>   
                         <input type="hidden" id="requestdisc_billno">
                         <input type="hidden" id="requestdisc_totalamt">      
                         <input type="hidden" id="discinpernewdiscount">      
                          <input type="hidden" id="requestdisc_balanceamt">	
                          <input type="hidden" id="requestdisc_typee">	
                    </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<label for="email">Pay mode:</label>
		            	</div>
	               </div>
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group notraslate" >
		              		<p  style="font-size:15px;" id="requestdisc_paymode"></p>       
		            	</div>
	               </div>
      		   </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<label for="email">Total Amount:</label>
		            	</div>
	               </div>
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<p style="font-size:15px;">Rs<span id="requestdisc_total_p"></span></p>       
		            	</div>
	               </div>
      		   </div>
      		   <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<label for="email">Balance:</label>
		            	</div>
	               </div>
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<p style="font-size:15px;" >Rs <span id="requestdisc_balance"></span></p>       
		            	</div>
	               </div>
      		   </div>
      		   <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<label for="email">Discount Type:</label>
		            	</div>
	               </div>
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		                	  <select class="form-control notranslate" onchange="changediscrequestval(this.value)"  id="requestdisc_disctype">
			              		<option value="0">Percent</option>
			              		<option value="1">Cash</option>
			              	 </select>
		            	</div>
	               </div>
      		   </div>
      		   <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px; margin-top: 10px;">
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<label for="email">Discount Amount:</label>
		            	</div>
	               </div>
	               <div class="col-lg-6 col-md-6 col-xs-6">
		                <div class="form-group">
		              		<input type="number"  id="requestdisc_amt" class="form-control"  />
		            	</div>
	               </div>
      		   </div>
      		   
      <div class="modal-footer">
      	<input type="button" class="btn btn-danger" id="requestdiscnewbutn" onclick="requestdiscountupto10()" value="Apply Upto 10%">
        <input type="button" class="btn btn-danger" id="requestdiscbutn" onclick="requestdiscount()" value="Request above 10%">
      </div>
      <div style="padding-left: 12px;">
        <p>Note: If payment mode is not Credit then "Request above 10%" is disabled.</p>
      </div>
    </div>

  </div>
</div>
</div>
	
		
	
	<div class="modal fade" id="addchargepopupinv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Add Charges for <span id="addchargehead"></span></h4>
				</div>
				<div class="modal-body">
				
					
				</div>
				</div>
				</div>
				</div>
				
				<!--
				<s:form action="estimateCharges" theme="simple" id="estimatefrm" target="formtarget">
					<s:hidden name="clientId" id="estimateclientid"/>
					<s:hidden name="actionType" value="0"/>
				</s:form>
				
				<div class="modal-footer">
				
					<button type="button" class="btn btn-primary" 
							onclick="createestimate()">View
							Estimate</button>
				
					<button type="button" class="btn btn-primary" 
						onclick="createChargeAndUpdateAccount('Charge')">Create
						Charge</button>
					  <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="createInvoice('Invoice')">Invoice Now / Pay Later</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="submitInvoice()">Submit Invoice</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="paynowForInvoice()">Record Payment</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="paynowForInvoice()">Cash Desk</button> 
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>   


-->

<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	

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
    document.body.innerHTML = oldPage;
    //detailsview
    window.location.reload();
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
