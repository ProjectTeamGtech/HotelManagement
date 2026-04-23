<!DOCTYPE html>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<html lang="en">
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" />
<link href="common/css/printpreview.css" rel="stylesheet" />

<!--  <script src="assets/js/priscription/jquery-2.1.1.min.js"></script>
    <script src="assets/css/priscription/bootstrap-3.3.1-dist/dist/js/bootstrap.min.js"></script>
    <script src="//google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script> -->

<%-- <style>
hr {
	margin-top: 0px;
	margin-bottom: 10px;
}

.marbot10 {
	margin-bottom: 0px;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	padding: 1px !important;
	vertical-align: top;
	border-top: 0px solid #DDD !important;
}

.marleft16 {
	margin-left: -16px;
}

.docsig {
	float: right;
	margin-right: -30px;
}

.rx {
	width: 39%;
	margin-bottom: 5px;
}

.pristitle {
	margin-left: -51px;
	font-size: 14px;
}

.med21 {
	width: 30% !important;
}

.btn-lg, .btn-group-lg>.btn {
	padding: 8px 14px;
	font-size: 17px;
	line-height: 7px;
	border-radius: 0px;
}

.hadsetma {
	padding-left: 0px;
	margin-top: 0px;
}

.mabset {
	margin-bottom: 5px;
}

.panamse {
	padding-top: 5px;
	border-bottom: 1px solid #6699cc;
	padding-bottom: 5px;
	background-color: rgba(91, 192, 222, 0.16);
}

.form-group {
	margin-bottom: 9px !important;
}

p {
	margin: 0 0 2px !important;
}

.print_special {
	border: 1px solid #43b9be;
}

@media print {
	.marleft16 {
		margin-left: 0px !important;
		font-size: 9px !important;
	}
	.hfour {
		font-size: 9px !important;
	}
	.panamse {
		padding-top: 5px;
		border-bottom: 1px solid #6699cc;
		padding-bottom: 5px;
		background-color: rgba(91, 192, 222, 0.16) !important;
	}
	.clinicname {
		font-size: 15px !important;
		font-weight: bold;
	}
	td, th {
		padding: 0;
		font-size: 9px !important;
	}
	.logsetma {
		margin-top: 6px !important;
	}
	.clicniaddress {
		font-size: 10px !important;
		font-weight: Normal !important;
	}
	p {
		margin: 0 0 2px !important;
		font-size: 9px;
	}
	.tableback {
		background-color: #cccccc !important;
		color: #fff !important;
		font-style: normal;
	}
	.table>thead>tr>th {
		vertical-align: bottom;
		border-bottom: transparent;
		background-color: #ccc !important;
		color: #000 !important;
	}
	.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
		.table>tbody>tr>td, .table>tfoot>tr>td {
		padding: 0px 5px 0px 5px !important;
		vertical-align: top;
		line-height: 12px !important;
		border-top: 0px solid #DDD !important;
		font-size: 9px !important;
	}
	.setase {
		padding-right: 5px !important;
	}
}

.setase {
	padding-right: 5px;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	padding: 0px 5px 0px 5px !important;
	vertical-align: top;
	border-top: 0px solid #DDD !important;
}

ul, ol {
	margin-top: 0;
	margin-bottom: 7.5px;
	padding: 0px 0px 0px 15px !important;
}

.sepaymeth {
	position: absolute;
	margin-top: -25px;
}

.hfour {
	font-size: 12px;
}

.datest {
	padding: 0px;
	margin-top: 10px;
	border-bottom: 1px dashed #ddd;
	margin-bottom: 0px;
	text-align: right;
}

ul, ol {
	margin-top: 0;
	margin-bottom: 7.5px;
	padding: 0px 0px 0px 0px !important;
	list-style: none;
}

h4, .h4, h5, .h5, h6, .h6 {
	margin-top: 3.5px;
	margin-bottom: 4.5px;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #43b9be !important;
	margin-bottom: 15px;
	line-height: 40px;
}
</style> --%>

<style>

@media screen {
	div#heading1{
		display: none;
	}
	
	div#heading2{
		display: none;
	}
	
	div#heading3{
		display: none;
	}
	
	div#heading4{
		display: none;
	}
}


@media print {
	html, body {
		display: block;
		font-family: "Tahoma";
	}
	table {
		page-break-inside: auto
	}
	tr, tbody {
		page-break-inside: avoid;
		page-break-after: auto
	}
	thead {
		display: table-header-group
	}
	@page {
		margin: 2mm 10mm 2mm 10mm;
	}
	@page :left {
		margin: 2mm 10mm 2mm 10mm;
		@bottom-left {
			content: counter(page);
		}
	}
	@page :right {
		margin: 2mm 10mm 2mm 10mm;
		@bottom-left {
			content: counter(page);
		}
	}
	
	div#heading1{
		page-break-after: always;
	}
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

<SCRIPT type="text/javascript" src="pharmacy/js/pharmacy.js"></SCRIPT>
</head>
<body oncontextmenu="return false">
<%String showletterhd=(String)session.getAttribute("showletterhd");
 String margin="";
if(showletterhd==null){
	showletterhd="";
}
if(showletterhd.equals("true")){
	showletterhd="";
}else{
	showletterhd="none";
	margin="margin-top:130px";
}
String pagelimit=(String)session.getAttribute("pagelimit");
if(pagelimit==null){
	pagelimit="6";
}
if(pagelimit!=null){
	if(!pagelimit.equals("")){
		
	}else{
		pagelimit="6";
	}
}
int pagelimitx= Integer.parseInt(pagelimit); 

%>    

	<div style="padding: 0px 5px 0px 0px;">
	<s:if test="shownavigation==1">
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
	</s:if>
	
		
		<%
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		%>
		<!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TOP section of bill >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
		<div>
		<h5 class="hidden">page limit=<%=pagelimitx %></h5>
		
		 <div class="" style="<%=margin%>">
		 <div class="" style="display:<%=showletterhd%>">
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
					<img class="img-responsive logsetma" src="liveData/clinicLogo/<s:property value='clinicLogo'/>" />
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 hadsetma" style="padding-top:12px">
					<div class="clinicname" style="padding-top:12px">
						<s:property value="clinicName" />
						<div class="cliqualif hidden">
							<s:property value="fullname" />
						</div>
						
						<div class="clicniaddress">
							<s:property value="clinicaddress" />
						</div>
						
						<div class="bottext mabset">
							Phone:
							<s:property value="landLine" />
							, Email:
							<s:property value="email" />
							, Website:
							<s:property value="websiteUrl" />
							<br>
						</div>
					</div>
				</div>
				</div>
				<s:if test="shownavigation==1">
					<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 form-group text-right">
						<p>	<a class="btn btn-primary btn-lg" href="salepriscPharmacy"	title="New Sale">New Sale</a><a class="btn btn-primary btn-lg" href="Pharmacy"  title="Pharmacy Dashboard" ><i class="fa fa-users" ></i> </a></p>
					</div>
				</s:if>
				<s:else>
					<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 form-group text-right hidden">
					<p>	<a class="btn btn-primary btn-lg" href="salepriscPharmacy"	title="New Sale">New Sale</a><a class="btn btn-primary btn-lg" href="Pharmacy"  title="Pharmacy Dashboard" ><i class="fa fa-users" ></i> </a></p>
					</div>
				</s:else>
 				
			

			<!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Patient Details  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->

				<%Client client = (Client)session.getAttribute("clientinfo"); %>

				<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6" style="padding: 0px;">
				    <!-- ############################# Bill No ############################# -->
					<div><b>BILL NO : </b>
						<s:if test="refundid>0">
							#<s:property value="refundid" />
						</s:if>
						<s:else>
							<s:if test="dummybillno>0">
								#<s:property value="dummybillno" />
							</s:if>
							<s:else>
								#<s:property value="billno" />
							</s:else>
	                             	
						</s:else>
						
						<%-- <s:if test="isreturn!=0"> 
	                          	&nbsp;|&nbsp; #<s:property value="reference" />
						</s:if> --%>
					</div>
					
					<!-- ############################# UHID ############################# -->
					<div>
								<b>UHID / OPD/IPD :</b><s:property value="abrivationid" /><span> / </span>
							
							
							<!-- ############################# IPD ############################# -->
							<s:if test="ipdoropd!=null">
								<s:if test="ipdoropd>0">
									<span>
										<s:if test="thirdPartyCompanyName==0">
										</s:if>
										<s:else>
											(<s:property value="thirdPartyCompanyName" />)                            				
	                                	</s:else>
									</span>
								</s:if>
							<s:else>
									<span>
										<s:if test="thirdPartyCompanyName==0">
										</s:if>
										<s:else>
											(<s:property value="thirdPartyCompanyName" />)                            				
	                                	</s:else>
									</span>
							</s:else>
							</s:if>
							</div>
							
					
					<!-- ############################# Name, mobile, address ############################# -->
					<div>
						<b>NAME :</b>
						<%=client.getTitle() + " " + client.getFirstName() +" "+client.getMiddlename()+" "+client.getLastName()%>
					</div>
					
					<div>
						<b>MOBILE :</b>
						<%=client.getMobNo()%>
					</div>
					
					<div>
						<b>ADDRESS :</b>
						<s:property value="wardname" />
					</div>
				</div>
	
				<!-- ############################# DATE ############################# -->
				<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6"
					style="padding: 0px;">
					<div>
						<b>DATE :</b>
						<s:property value="commencing" />
						<s:property value="invoiceTime" />
					</div>
	
					<!-- ############################# DR. NAME ############################# -->
					<div>
						<b>DR. NAME :</b>
						<s:property value="practitionerName" />
					</div>
					
					<!-- ############################# WARD/BED ############################# -->
					<s:if test="ipdoropd!=null">
						<s:if test="ipdoropd>0">
							<div>
								<b>WARD/BED :</b>
								<s:property value="wardbed" />
							</div>
						</s:if>
					</s:if>
					
					<!-- ############################# PHARMACY, PLACE, TAX PAID ON INVOICE  ############################# -->
					<div>
						<b>PHARMACY :</b>
						<s:property value="location" />
					</div>
					
					<div>
						<b>PLACE :</b>
						<s:property value="place" />
					</div>
					
					<div>
						<b>TAX PAID ON INVOICE :</b> Rs.
						<s:property value="gst" />
					</div>
				</div>


				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden">
					<div class="form-group">
						<label for="inputEmail3" class="text-left">SEX/AGE</label>
						<p><%=client.getGender() %>/<%=client.getAge() %></p>
					</div>
				</div>
</div>
</div>

		<!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Medicine Table Details  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px; ">
			<div style="width: 100%; font-weight: bold;text-transform: uppercase;">
				<div style="width: 4%;padding: 2px 0px; float: left; border: 0px solid black">No.</div>
				<div style="width: 31%; padding: 2px 0px;float: left; border: 0px solid black">Name of Drug</div>
				<div style="width: 6%; padding: 2px 0px;float: left; border: 0px solid black">HSN</div>
				<div style="width: 4%; padding: 2px 0px; text-align: center;float: left; border: 0px solid black">Qty</div>
				<div class="hidden" style="width: 4%;float: left; border: 0px solid black">Pkg</div>
				<div class="hidden" style="width: 5%; padding: 2px 0px;float: left;border: 0px solid black">Mfg</div>
				<div style="width: 9%; padding: 2px 0px;float: left; border: 0px solid black">Batch</div>
				<div style="width: 9%; padding: 2px 0px;float: left; border: 0px solid black">Exp. Dt</div>
				<div style="width: 5%; text-align: right; padding: 2px 0px;float: left; border: 0px solid black">AV</div>
				<div style="width: 7%; text-align: right; padding: 2px 0px;float: left; border: 0px solid black">CGST</div>
				<div style="width: 7%; text-align: right; padding: 2px 0px;float: left; border: 0px solid black">SGST</div>
				<div style="width: 7%; padding: 2px 0px; text-align: right;float: left; border: 0px solid black">MRP</div>
				<div style="width: 11%; padding: 2px 0px;float: right;border: 0px solid black" class="text-right">Amount</div>
			</div>
			
			<%int i=1; %>
			<s:iterator value="medicineChargeList">
				<% if(i % pagelimitx == 0 ) {%>
					<!-- <<<<<<<<<<<<<<<< Footer in each page except last >>>>>>>>>>>>>>>> -->
					<div id="heading1" style="border: 0px solid black">
						<div class="col-lg-6 col-md-6 col-xs-6" style="padding: 5px 0px 0px 0px;">
							<div class="text-left" style="padding-left: 0px;">
								<div class="" style="">
									<span style="margin: 0px; font-size: 10px;">
										<b>D.L.No</b> :
										<s:property value="dlno" />
		
									</span>
									
									<span class="" style="margin: 0px; font-size: 10px;">
										<b>GSTIN</b> :
										<s:property value="tinno" />
									</span>
									
									<p style="font-size: 10px">
										<s:property value="instruction1" />
										<br>
										<s:property value="instruction2" />
										<br>
										<s:property value="instruction3" />
										<br>
										<s:property value="instruction4" />
									</p>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-xs-6" style="padding: 5px 0px 0px 0px;">
						</div>
					</div>
				
				
					<!-- ############################# Page break ############################# 
					     Note: need to check why setting below border to 0 px is impacting page break -->
					<div id="heading2" style="width: 100%; border-bottom: 1px solid white">
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
							<img class="img-responsive logsetma" src="liveData/clinicLogo/<s:property value="clinicLogo"/>" />
						</div>

						<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
							<div class="clinicname">
								<s:property value="clinicName" />
								<div class="cliqualif hidden">
									<s:property value="fullname" />
								</div>

								<div class="clicniaddress">
									<s:property value="clinicaddress" />
								</div>

								<div class="bottext mabset">
									Phone: <s:property value="landLine" /> ,
									, Email: <s:property value="email" /> ,
									, Website:
									<s:property value="websiteUrl" />
									<br>
								</div>
							</div>
						</div>


					<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6" style="padding: 0px;">
						    <!-- ############################# Bill No ############################# -->
							<div><b>BILL NO : </b>
								<s:if test="refundid>0">
									#<s:property value="refundid" />
								</s:if>
								<s:else>
	                               	#<s:property value="billno" />
								</s:else>
								
								<s:if test="isreturn!=0"> 
	                            	&nbsp;|&nbsp; #<s:property value="reference" />
								</s:if>
							</div>
							
							<!-- ############################# UHID ############################# -->
							<div>
								<b>UHID / OPD/IPD :</b><s:property value="abrivationid" /><span> / </span>
							
							
							<!-- ############################# IPD ############################# -->
							<s:if test="ipdoropd!=null">
								<s:if test="ipdoropd>0">
									<span>
										<s:if test="thirdPartyCompanyName==0">
										</s:if>
										<s:else>
											(<s:property value="thirdPartyCompanyName" />)                            				
	                                	</s:else>
									</span>
								</s:if>
							<s:else>
									<span>
										<s:if test="thirdPartyCompanyName==0">
										</s:if>
										<s:else>
											(<s:property value="thirdPartyCompanyName" />)                            				
	                                	</s:else>
									</span>
							</s:else>
							</s:if>
							</div>
							
							<!-- ############################# Name, mobile, address ############################# -->
							<div>
								<b>NAME :</b>
								<%=client.getTitle() + " " + client.getFirstName() + " " + client.getLastName()%>
							</div>
							
							<div>
								<b>MOBILE :</b>
								<%=client.getMobNo()%>
							</div>
							
							<div>
								<b>ADDRESS :</b>
								<s:property value="wardname" />
							</div>
						</div>
	
						<!-- ############################# DATE ############################# -->
						<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6"
							style="padding: 0px;">
							<div>
								<b>DATE :</b>
								<s:property value="commencing" />
								<s:property value="invoiceTime" />
							</div>
	
							<!-- ############################# DR. NAME ############################# -->
							<div>
								<b>DR. NAME :</b>
								<s:property value="practitionerName" />
							</div>
							
							<!-- ############################# WARD/BED ############################# -->
							<s:if test="ipdoropd!=null">
								<s:if test="ipdoropd>0">
									<div>
										<b>WARD/BED :</b>
										<s:property value="wardbed" />
									</div>
								</s:if>
							</s:if>
							
							<!-- ############################# PHARMACY, PLACE, TAX PAID ON INVOICE  ############################# -->
							<div>
								<b>PHARMACY :</b>
								<s:property value="location" />
							</div>
							
							<div>
								<b>PLACE :</b>
								<s:property value="place" />
							</div>
							
							<div>
								<b>TAX PAID ON INVOICE :</b> Rs.
								<s:property value="gst" />
							</div>
						</div>
					</div>
				
					<div id="heading3" style="width: 100%; font-weight: bold;text-transform: uppercase;">
						<div style="width: 4%;padding: 2px 0px;float: left; border: 0px solid black">No.</div>
						<div style="width: 31%; padding: 2px 0px;float: left; border: 0px solid black">Name of Drug</div>
						<div style="width: 6%; padding: 2px 0px;float: left; border: 0px solid black">HSN</div>
						<div style="width: 4%; padding: 2px 0px; text-align: center;float: left; border: 0px solid black">Qty</div>
						<div class="hidden" style="width: 4%;float: left; border: 0px solid black">Pkg</div>
						<div class="hidden" style="width: 5%; padding: 2px 0px;float: left;border: 0px solid black">Mfg</div>
						<div style="width: 9%; padding: 2px 0px;float: left; border: 0px solid black">Batch</div>
						<div style="width: 9%; padding: 2px 0px;float: left; border: 0px solid black">Exp. Dt</div>
						<div style="width: 5%; text-align: right; padding: 2px 0px;float: left; border: 0px solid black">AV</div>
						<div style="width: 7%; text-align: right; padding: 2px 0px;float: left; border: 0px solid black">CGST</div>
						<div style="width: 7%; text-align: right; padding: 2px 0px;float: left; border: 0px solid black">SGST</div>
						<div style="width: 7%; padding: 2px 0px; text-align: right;float: left; border: 0px solid black">MRP</div>
						<div style="width: 11%; padding: 2px 0px;float: right;border: 0px solid black" class="text-right">Amount</div>
					</div>
						
					<div style="width: 100%; text-transform: uppercase;">
						<div style="width: 4%;float: left; border: 0px solid black"><%=(i++)%></div> 
						<div style="width: 31%; float: left; border: 0px solid black;text-transform: uppercase;"><s:property value="mdicinenametxt" /></div> 
						<div style="width: 6%; float: left; border: 0px solid black"><s:property value="hsnno" /></div>
						<div style="width: 4%; text-align: center;float: left; border: 0px solid black"><s:property value="saleqty" /></div> 
						<div class="hidden" style="width: 4%;float: left; border: 0px solid black">6</div> 
						<div class="hidden" style="width: 5%;float: left; border: 0px solid black"><s:property value="mfg" /></div> 
						<div style="width: 9%; float: left; border: 0px solid black"><s:property value="batch_no" /></div> 
						<div style="width: 9%; float: left; border: 0px solid black"><s:property value="expiry_date" /></div>
						<div style="width: 5%; text-align: right; text-transform: uppercase;float: left; border: 0px solid black"><s:property	value="av" /></div> 
						<div style="width: 7%; text-align: right; text-transform: uppercase;float: left; border: 0px solid black"><s:property value="cgst" /></div> 
						<div style="width: 7%; text-align: right; text-transform: uppercase;float: left; border: 0px solid black"><s:property value="sgst" /></div> 
						<div style="width: 7%; text-align: right;float: left; border: 0px solid black"><s:property value="mrp" /></div> 
						<div style="width: 11%; float: left; border: 0px solid black" class="text-right"><s:property value="total" /></div>
					</div>
				<!-- ############################# No Page Break ############################# -->
				<%} else { %>
					<div style="width: 100%">
						<div style="width: 4%;float: left; border: 0px solid black"><%=(i++)%></div> 
						<div style="width: 31%; float: left; border: 0px solid black;text-transform: uppercase;"><s:property value="mdicinenametxt" /></div> 
						<div style="width: 6%; float: left; border: 0px solid black"><s:property value="hsnno" /></div>
						<div style="width: 4%; text-align: center;float: left; border: 0px solid black"><s:property value="saleqty" /></div> 
						<div class="hidden" style="width: 4%;float: left; border: 0px solid black">6</div> 
						<div class="hidden" style="width: 5%;float: left; border: 0px solid black"><s:property value="mfg" /></div> 
						<div style="width: 9%; float: left; border: 0px solid black"><s:property value="batch_no" /></div> 
						<div style="width: 9%; float: left; border: 0px solid black"><s:property value="expiry_date" /></div>
						<div style="width: 5%; text-align: right; text-transform: uppercase;float: left; border: 0px solid black"><s:property	value="av" /></div> 
						<div style="width: 7%; text-align: right; text-transform: uppercase;float: left; border: 0px solid black"><s:property value="cgst" /></div> 
						<div style="width: 7%; text-align: right; text-transform: uppercase;float: left; border: 0px solid black"><s:property value="sgst" /></div> 
						<div style="width: 7%; text-align: right;float: left; border: 0px solid black"><s:property value="mrp" /></div> 
						<div style="width: 11%; float: left; border: 0px solid black" class="text-right"><s:property value="total" /></div>
					</div>
				<%
					}
				%>
			</s:iterator>


			<!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Bill Total  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->

			<!-- <<<<<<<<<<<<<<<< Left Column >>>>>>>>>>>>>>>> -->
			<div class="col-lg-5 col-md-5 col-xs-5" style="padding: 5px 0px 0px 0px;">
				<div class="text-left" style="padding-left: 0px;">
						<span style="margin: 0px; font-size: 10px;"> <b>D.L.No</b> :
								<s:property value="dlno" />
					
						</span>
						
						<span class="" style="margin: 0px; font-size: 10px;"> <b>GSTIN</b> :
								<s:property value="tinno"/>
						</span>
						
						<p style="font-size: 10px">
							<s:property value="instruction1" /><br>
							<s:property value="instruction2" /><br>
							<s:property value="instruction3" /><br>
							<s:property value="instruction4" /><br>
						</p>
						
						<p style="margin: 0px;">
							<b>Note</b> : <p><s:property value="notes" /> </p>
							<s:if test="edited!=0">
								<b>Edit Bill Note</b> : <p><s:property	value="edit_note" /></p>
							</s:if>
						</p>
				</div>

			</div>

			<!-- <<<<<<<<<<<<<<<< Right Column 1 JUl 2018 >>>>>>>>>>>>>>>> -->
			<div class="col-lg-7 col-md-7 col-xs-7" style="padding: 5px 0px 0px 0px;">
				<div class="text-right">
					[ <span style="margin: 0px;"> Payment 
						<span>: <s:property	value="paymode" /> 
							<s:if test="isreturn!=0">
								<p style="margin: 0px;">
									<b>Sales Return </b>
								</p>
							</s:if>
						</span>
					</span> ] 
					
					<span style="margin: 0px;"> Sub Total : Rs. 
						<s:property	value="subtotal" />
					</span>
					
					<p style="margin: 0px;">
						Discount(%) : Rs. <s:property value="discount" />
					</p>
					
					<p class="hidden" style="font-size: 13px;">
						Vat : Rs. <s:property value="vat"/>
					</p>
					
					[<span style="margin: 0px;font-size: 10px;">CGST:Rs.
						<s:property value="cgst"/>
					</span>/ 
					
					<span style="margin: 0px;font-size: 10px;">SGST:Rs.<s:property value="sgst"/>
					</span>]
					
					<span style="margin: 0px; font-weight: bold;font-size: 15px;"> Total : Rs.
						<s:property value="total"/><input type="hidden"
						value="<s:property value="total"/>" id="wordtot"/>
					</span>
				
					<p style="margin: 0px;"> 
						<s:if test="isreturn==0"> 
							Received : Rs. <s:property value="payamt" />
						</s:if> 
						
						<s:else>
							Refund : Rs. <s:property value="payamt" />
						</s:else>
					</p> 
					
					<p style="margin: 0px;">
						Balance : Rs. <s:property value="balance" />
					</p>
					
					<span>
						In Words: <span id="words"> </span> Only
					</span>
				</div>
			</div>

			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9 hfour"
				style="padding: 0px;">
				<p class="hidden" style="margin: 0px;">
					<b>Prescription ID</b> <span>: <s:property
							value="selectedid" /></span>
				</p>
				<p style="margin: 0px; font-size: 9px;">
					<b>Created by</b> :
					<s:property value="createdby" />
					/
					<s:property value="commencing" />
					<s:property value="invoiceTime" />
				<p style="margin: 0px; font-size: 9px;">
					<b>Printed by</b> :
					<s:property value="userid" />
					/
					<s:property value="dateTime" />
				</p>

				<s:if test="edited!=0">
					<b>Edited by</b> : <p>
						<s:property value="userid" />
						/
						<s:property value="dateTime" />
					</p>
				</s:if>
				<s:if test="deleted==1">
					<b>Canceled </b>
				</s:if>

			</div>

			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 text-right"
				style="margin-top: 10px; color: #bbb; padding: 0px; font-size: 9px;">
				<span class="hfour">Sign of Q.P</span>
			</div>
		</div>


         <!-- not required during printing -->
		<div class="form-group text-right hidden-print" style="padding: 0px 12px 0px 0px;">
			<% if(loginInfo.isSMS_authority() || loginInfo.getUserType()==2) {%>
			<a type="button" class="btn btn-primary btn-lg savebigbtn"
				style="float: left;" title="Print"
				onclick="sendSms(<s:property value="billno"/>)">Send SMS</a>
			<%} else { %>
			<a type="button" class="btn btn-primary btn-lg savebigbtn hidden"
				style="float: left;" title="Print"
				onclick="sendSms(<s:property value="billno"/>)">Send SMS</a>
			<%} %>
			<a type="button" class="btn btn-primary btn-lg savebigbtn"
				title="Print" onclick="printpage()">Print</a>
		</div>
	</div>








	<script>
        function myPrint() {
            window.print();
        }
        
        window.onload = function(){
        	
           var t=  document.getElementById("wordtot").value;
           document.getElementById("words").innerHTML=convertNumberToWords(t);
           	
        };
   
    </script>
	<script>
	function convertNumberToWords(amount) {
    var words = new Array();
    words[0] = '';
    words[1] = 'One';
    words[2] = 'Two';
    words[3] = 'Three';
    words[4] = 'Four';
    words[5] = 'Five';
    words[6] = 'Six';
    words[7] = 'Seven';
    words[8] = 'Eight';
    words[9] = 'Nine';
    words[10] = 'Ten';
    words[11] = 'Eleven';
    words[12] = 'Twelve';
    words[13] = 'Thirteen';
    words[14] = 'Fourteen';
    words[15] = 'Fifteen';
    words[16] = 'Sixteen';
    words[17] = 'Seventeen';
    words[18] = 'Eighteen';
    words[19] = 'Nineteen';
    words[20] = 'Twenty';
    words[30] = 'Thirty';
    words[40] = 'Forty';
    words[50] = 'Fifty';
    words[60] = 'Sixty';
    words[70] = 'Seventy';
    words[80] = 'Eighty';
    words[90] = 'Ninety';
    amount = amount.toString();
    var atemp = amount.split(".");
    var number = atemp[0].split(",").join("");
    var n_length = number.length;
    var words_string = "";
    if (n_length <= 9) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < n_length; i++) {
            received_n_array[i] = number.substr(i, 1);
        }
        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
            n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                if (n_array[i] == 1) {
                    n_array[j] = 10 + parseInt(n_array[j]);
                    n_array[i] = 0;
                }
            }
        }
        value = "";
        for (var i = 0; i < 9; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                value = n_array[i] * 10;
            } else {
                value = n_array[i];
            }
            if (value != 0) {
                words_string += words[value] + " ";
            }
            if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Crores ";
            }
            if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Lakhs ";
            }
            if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Thousand ";
            }
            if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                words_string += "Hundred and ";
            } else if (i == 6 && value != 0) {
                words_string += "Hundred ";
            }
        }
        words_string = words_string.split("  ").join(" ");
    }
    return words_string;
}
</script>


</body>
</html>