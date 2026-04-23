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
<meta name="viewport" content="width=device-width,g=350px,maximum-scale=1.0">

<title>Insert title here</title>
<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" />
<link href="common/css/printpreview.css" rel="stylesheet" />

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
html, body{
		display: block;
		min-height: 300px;
    	height: 300px !important;
}
	
}
.font{
font-family:"sans-serif";
}

.newcap{
	text-transform: uppercase !important;
	}
	
	
@media print {
	html, body #fullheight{
		display: block;

     @page {size: A3 landscape;max-height:350px; max-width:100%}

}

	#fullheight {
    width: 100%;
    height: 300px;
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
		margin: 0mm 5mm 2mm 0mm;
		
	}
	
	div#heading1{
                page-break-after: always;
        }
        .container {
   	padding:0;position:relative;top:0; left:0; background: blue;
        }
        .gstlist {
       padding:0;position:absolute;bottom:0; left:0;background: red;
        }
    	 .total {
	position:absolute;bottom:10; left:0;right:5;background: white;
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
<body>
<%String showletterhd=(String)session.getAttribute("showletterhd");
 String margin="";
if(showletterhd==null){
	showletterhd="";
}
if(showletterhd.equals("true")){
	showletterhd="";
}else{
	showletterhd="none";
	margin="margin-top:10px";
}
String pagelimit=(String)session.getAttribute("pagelimit");
if(pagelimit==null){
	pagelimit="12";
}
if(pagelimit!=null){
	if(!pagelimit.equals("")){
		
	}else{
		pagelimit="12";
	}
}
int pagelimitx= Integer.parseInt(pagelimit);  %>    



<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="fullheight" style="padding: 10px 5px 0px 0px;">
<div class="hidden-print">
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
							</div>
		<%
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		%>
	<h5 class="hidden">page limit=<%=pagelimitx%></h5>
	
<!--  P1: TOP -->	
	<div class="" style="<%=margin%>; text-transform: uppercase;">	
<!--  TOP Letterhead  with Hospital Name, Address, Phone & email of bill ########################## -->
			<div class="hidden" style="display:<%=showletterhd%>">		
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<img class="img-responsive logsetma" src="liveData/clinicLogo/<s:property value='clinicLogo'/>" />
			</div>	
				<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 hadsetma" style="padding-top:10px">
					<div class="clinicname" style="padding-top:12px"> <s:property value="clinicName" />
							<div class="cliqualif hidden"><s:property value="fullname" />
							</div>
							<div class="clicniaddress"><s:property value="clinicaddress" />
							</div>
							<div class="bottext mabset"> Phone:
								<s:property value="landLine" /> , Email:
								<s:property value="email" /> , Website:
								<s:property value="websiteUrl" />
								<br>
							</div>
						</div>
			</div>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 form-group text-right hidden-print"> </div>
		</div>
		
			<%Client client = (Client)session.getAttribute("clientinfo"); %>		
<!--  P1 :TOP  Bill NO and Patient Name ############################# -->	
			<div class="" style="width: 100%;">		
				<div class="col-lg-8 col-xs-8 col-md-8 col-sm-8 newcap" style="padding: 8px;" >

					<!-- ############################# Patient Name, mobile, address ############################# -->
						
						<div>
							<div class="hidden"> <b>NAME :</b></div>
							<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
							<%=client.getTitle() + " " + client.getFirstName() + " " + client.getMiddlename() + " " + client.getLastName() %>
						</div>
					
					<!-- ############################# Pt Address ############################# -->
						<div>
							<div class="hidden"> <b>ADDRESS : </b></div>
							<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
							<s:property value="addr" />
						</div>
					<!-- ############################# DR. NAME ############################# -->
						<div>
							<div class="hidden"><b>DR. NAME :</b></div>
							
							<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
							<s:property value="practitionerName" />
						</div>
				</div>
				<div class="col-lg-4 col-xs-4 col-md-4 col-sm-4 newcap" style="padding: 8px;">
						<!-- ############################# Bill No ############################# -->
							<div style="padding-left: 60px;">
							<div class="hidden"><b>BILL NO : </b></div>
								<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
								<s:if test="refundid>0">
								SD<s:property value="refundid" />
								</s:if>
								<s:else> 
									
									<s:if test="dummybillno>0">
											Se<s:property value="dummybillno" />
											</s:if>
											<s:else>
												SD<s:property value="billno" />
											</s:else>
								</s:else>
								<%-- <s:if test="isreturn!=0"> &nbsp;;|&nbsp;; #<s:property value="reference" />
								</s:if> --%>
							</div>
						<!-- ############################# Bill DATE ############################# -->
							<div style="padding-left: 60px;">
								<div class="hidden"><b>BILL DATE :</b></div>
								<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
								<s:property value="commencing" />
								<%-- <s:property value="invoiceTime" /> --%>
							</div>
						<!-- ############################# Payment / Memo ############################# -->
						<div style="text-transform: none; padding-left: 60px;"> <div class="hidden"><b> MEMO :</b></div>
								<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
							<s:property	value="memo" />
						</div>
						<!-- ############################# WARD/BED ############################# -->
				</div>		
			</div>	
		</div>
<!-- P1: Middle: Medicine   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="width: 100%; height: 215px; padding: 0px 10px 0px 0px; "> 
<!-- Middle: Page 1 Medicine Header  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
			<div class="" style="padding: 15px 10px 0px 0px;">
					<div style="width: 100%; float: left; border: 0px; padding: 10px 0px 0px 0px;">.</div> 
					<div class="hidden" style="width:  4%; padding: 2px 0px; float: left; border: 0px solid black">No.</div>
					<div class="hidden" style="width:  6%; padding: 2px 0px; float: left; border: 0px solid black">HSN</div>
					<div class="hidden" style="width: 37%; padding: 2px 0px; float: left; border: 0px solid black">Name of Drug</div>
					<div class="hidden" style="width:  6%; padding: 2px 0px; float: left; border: 0px solid black">Pack</div>
					<div class="hidden" style="width:  6%; padding: 2px 0px; float: left; border: 0px solid black">Mfg</div>
					<div class="hidden" style="width: 10%; padding: 2px 0px; float: left; border: 0px solid black">Bt #</div>
					<div class="hidden" style="width: 10%; padding: 2px 0px; float: left; border: 0px solid black">Exp. Dt</div>
					<div class="hidden" style="width:  6%; padding: 2px 0px; text-align: right; float: left; border: 0px solid black">Qty</div>
					<div class="hidden" style="width: 10%; padding: 2px 0px; text-align: right; float: left; border: 0px solid black">Amount</div>
					<div class="hidden" style="width:  9%; padding: 2px 0px; text-align: right; float: left; border: 0px solid black">GST</div>	
			</div>
			<%int i=1,testcount=0; %>
			<s:iterator value="medicineChargeList">
			<% if(i % pagelimitx == 0 ) {%>	
			
<!-- Footer Carried Forward  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 30px 0px 0px 0px;">
				<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">Balance Carried Forward</div>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="text-align: right;"><s:property value="broughtfwd" /></div> 
        </div>	
		
<!-- Footer in each page except last GST >>>>>>>>>>>>>>>> -->			
		<div id="heading1" style="border: 0px solid black">
				<div class="col-lg-6 col-md-6 col-xs-6 hidden" style="padding: 0px 0px 0px 0px;"> 
						D.L.No</b> : <s:property value="dlno" /> | GSTIN</b> : <s:property value="tinno" />
				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 hidden" style="padding: 0px 0px 0px 0px;"> </div>
		</div>
		
<!--  P2 TOP -->		
<!--  P2 TOP Letterhead  with Hospital Name, Address, Phone & email of bill ########################## -->
			<div class="hidden" style="display:<%=showletterhd%>">		
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<img class="img-responsive logsetma" src="liveData/clinicLogo/<s:property value='clinicLogo'/>" />
			</div>	
				<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 hadsetma" style="padding-top:10px">
					<div class="clinicname" style="padding-top:12px"> <s:property value="clinicName" />
							<div class="cliqualif hidden"><s:property value="fullname" />
							</div>
							<div class="clicniaddress"><s:property value="clinicaddress" />
							</div>
							<div class="bottext mabset"> Phone:
								<s:property value="landLine" /> , Email:
								<s:property value="email" /> , Website:
								<s:property value="websiteUrl" />
								<br>
							</div>
						</div>
			</div>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 form-group text-right hidden-print"> </div>
		</div>
				
<!--  TOP Bill Section ############################# -->	
			<div class="" style="width: 100%;">		
				<div class="col-lg-8 col-xs-8 col-md-8 col-sm-8 newcap" style="padding: 8px 0px 0px 0px;" >

					<!-- ############################# Patient Name, mobile, address ############################# -->
						
						<div>
							<div class="hidden"> <b>NAME :</b></div>
							<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
							<%=client.getTitle() + " " + client.getFirstName() + " " + client.getMiddlename() + " " + client.getLastName() %>
						</div>
					
					<!-- ############################# Pt Address ############################# -->
						<div>
							<div class="hidden"> <b>ADDRESS : </b></div>
							<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
							<s:property value="addr" />
						</div>
					<!-- ############################# DR. NAME ############################# -->
						<div>
							<div class="hidden"><b>DR. NAME :</b></div>
							
							<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
							<s:property value="practitionerName" />
						</div>
				</div>
				<div class="col-lg-4 col-xs-4 col-md-4 col-sm-4 newcap" style="padding: 8px 0px 0px 0px;">
						<!-- ############################# Bill No ############################# -->
							<div style="padding-left: 60px;">
							<div class="hidden"><b>BILL NO : </b></div>
								<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
								<s:if test="refundid>0">
								SD<s:property value="refundid" />
								</s:if>
								<s:else> SD<s:property value="billno" />
								</s:else>
								<s:if test="isreturn!=0"> &nbsp;;|&nbsp;; #<s:property value="reference" />
								</s:if>
							</div>
						<!-- ############################# Bill DATE ############################# -->
							<div style="padding-left: 60px;">
								<div class="hidden"><b>BILL DATE :</b></div>
								<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
								<s:property value="commencing" />
								<%-- <s:property value="invoiceTime" /> --%>
							</div>
						<!-- ############################# Payment / Memo ############################# -->
						<div style="text-transform: none; padding-left: 60px;"> <div class="hidden"><b> MEMO :</b></div>
								<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
							<s:property	value="memo" />
						</div>
						<!-- ############################# WARD/BED ############################# -->
				</div>		
			</div>	

<!--  P2 TOP Balance Brough Forward >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->	
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"  style="height: 215px; padding: 0px 0px 0px 0px;">
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"  style=" padding: 0px 0px 10px 0px;">Balance Brought Forward</div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="padding: 0px 0px 0px 0px; text-align: right;"><s:property	value="broughtfwd" /></div> 
<!-- P2 Middle Medicine Header  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="heading3" style="padding: 5px 10px 0px 0px; width: 100%; font-weight: bold;text-transform: uppercase;">&nbsp;
					<div class="hidden" style="width: 4%;padding: 2px 0px; float: left; border: 0px solid black">No.</div>
					<div class="hidden" style="width: 6%; padding: 2px 0px;float: left; border: 0px solid black">HSN</div>
					<div class="hidden" style="width: 37%; padding: 2px 0px; float: left; border: 0px solid black">Name of Drug</div>
					<div class="hidden" style="width: 6%; padding: 2px 0px; text-align: center; float: left; border: 0px solid black">Pack</div>
					<div class="hidden" style="width: 6%; padding: 2px 0px; float: left; border: 0px solid black">Mfg</div>
					<div class="hidden" style="width: 12%; padding: 2px 0px;float: left; border: 0px solid black">Bt #</div>
					<div class="hidden" style="width: 8%; padding: 2px 0px;float: left; border: 0px solid black">Exp. Dt</div>
					<div class="hidden" style="width:  6%; padding: 2px 0px; text-align: right;float: left; border: 0px solid black">Qty</div>
					<div class="hidden" style="width: 10%; padding: 2px 0px; text-align: right; float: left; border: 0px solid black">Amount</div>
					<div class="hidden" style="width:  9%; padding: 2px 0px; text-align: right; float: left; border: 0px solid black">GST</div>
			</div>
<!-- P2 Middle Medicine Print >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->	
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="width:100%; padding: 10px 0px 0px 0px; text-transform:uppercase; font-family:sans-serif;">
					<div class="hidden" style="width: 0%;"><%=(i++)%></div>
					<div style="width:  6%; padding: 0px 0px 0px 0px; text-align: center; float: left; border: 0px solid black"><s:property value="hsnno" /></div>
					<div style="width: 37%; padding: 0px 0px 0px 2px; text-align: left; float: left; border: 0px solid black;text-transform: uppercase;"><s:property value="mdicinenametxt" /></div> 
					<div style="width:  6%; padding: 0px 0px 0px 2px; text-align: center; float: left; border: 0px solid black"><s:property value="pack" /></div> 
					<div style="width:  6%; padding: 0px 0px 0px 2px; text-align: right; float: left; border: 0px solid black"><s:property value="mfg" /></div> 
					<div style="width: 12%; padding: 0px 2px 0px 2px; text-align: right; float: left; border: 0px solid black"><s:property value="batch_no" /></div> 
					<div style="width:  8%; padding: 0px 2px 0px 2px; text-align: right; float: left; border: 0px solid black"><s:property value="nweexp" /></div>
					<div style="width:  6%; padding: 0px 0px 0px 2px; text-align: center; float: left; border: 0px solid black"><s:property value="saleqty" /></div> 
					<div style="width: 10%; padding: 0px 0px 0px 2px; text-align: right; float: left; border: 0px solid black"><s:property value="total" /></div>
					<div style="width:  9%; padding: 0px 0px 0px 4px; text-align: right; float: left; border: 0px solid black"><s:property value="vat" />.00%</div> 
			</div>		
<!-- No Page Break ############################# -->
		<%}  else  { %>
<!--  Page 1 Medicine Table Print  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
			<div style="width:100%; height: 0px; padding: 10px 0px 0px 0px; text-transform:uppercase; font-family:sans-serif;">
					<div class="hidden" style="width: 0%;"><%=(i++)%></div>
					<div style="width:  6%; padding: 0px 0px 0px 0px; text-align: center; float: left; border: 0px solid black"><s:property value="hsnno" /></div>
					<div style="width: 37%; padding: 0px 0px 0px 2px; text-align: left; float: left; border: 0px solid black;text-transform: uppercase;"><s:property value="mdicinenametxt" /></div> 
					<div style="width:  6%; padding: 0px 0px 0px 2px; text-align: center; float: left; border: 0px solid black"><s:property value="pack" /></div> 
					<div style="width:  6%; padding: 0px 0px 0px 2px; text-align: right; float: left; border: 0px solid black"><s:property value="mfg" /></div> 
					<div style="width: 12%; padding: 0px 2px 0px 2px; text-align: right; float: left; border: 0px solid black"><s:property value="batch_no" /></div> 
					<div style="width:  8%; padding: 0px 2px 0px 2px; text-align: right; float: left; border: 0px solid black"><s:property value="nweexp" /></div>
					<div style="width:  6%; padding: 0px 0px 0px 2px; text-align: center; float: left; border: 0px solid black"><s:property value="saleqty" /></div> 
					<div style="width: 10%; padding: 0px 0px 0px 2px; text-align: right; float: left; border: 0px solid black"><s:property value="total" /></div>
					<div style="width:  9%; padding: 0px 0px 0px 4px; text-align: right; float: left; border: 0px solid black"><s:property value="vat" />.00%</div> 
			</div>			
				<% 	}  	%>
			</s:iterator>
		</div>
<!-- Footer :Left GST  & Total >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->
			<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding: 25px 5px 0px 10px;">
				<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7" style="padding: 0px 0px 0px 0px;text-align: left;">							
				<table class="" style="align:center; width: 100%;" >
                           <thead>
                           <tr>
                           <th>%Age</th>
                           <th class="adjus">0%</th>
                           <th class="adjus">5%</th>
                           <th class="adjus">12%</th>
                           <th class="adjus">18%</th>
                           <th class="adjus">28%</th>
                           <th class="adjus">TOTAL</th>
                           </tr>
                           </thead>
                      <tr>
                        <td>AMT </td> 
                          <td class="adjus"><s:property value="zeromrp"/></td>
                          <td class="adjus"><s:property value="fivemrp"/></td>
                          <td class="adjus"><s:property value="twelvemrp"/></td>
                          <td class="adjus"><s:property value="eightteenmrp"/></td>
                          <td class="adjus"><s:property value="twentyeightmrp"/></td>
                           <td class="adjus"><s:property value="totalnmrp"/></td>
                          </tr> 
                          <tr>
                           <td>GST </td>
                          <td class="adjus"><s:property value="zerogst"/></td>
                          <td class="adjus"><s:property value="fivegst"/></td>
                          <td class="adjus"><s:property value="twelvegst"/></td>
                          <td class="adjus"><s:property value="eighteen"/></td>
                          <td class="adjus"><s:property value="twentyeight"/></td>
                           <td class="adjus"><s:property value="totalgst"/></td>
                           </tr>
                           </table>
					</div>
				<div class=" col-lg-5 col-md-5 col-xs-5 col-sm-5" style="padding: 0px 0px 0px 0px; float: right; ">
						<div class="text-right">
							<div class="text-right">
							<span style="margin: 0px;"> 
								<span class="text-right hidden">[ Payment : <s:property	value="paymode" /> ]</span>
								<s:if test="isreturn!=0">
									<p style="margin: 0px;">
										<b>/ Sales Return </b>
									</p>
								</s:if>
							</span> 
					
							<span class="text-right" style="margin: 0px;"> GROSS :  
							<s:property	value="subtotal" />
							</span>
					
						</div>
								<p style="margin: 0px;"> DISC :  <s:property value="discount" /></p>
							<div class="text-right hidden"> <p class="hidden" style="font-size: 13px;"> VAT : Rs. <s:property value="vat"/>
						</p>
						</div>
								<span class="hidden" style="margin: 0px;font-size: 10px;">[CGST:Rs.
							<s:property value="cgst"/>/
						</span>
								<span class="hidden" style="margin: 0px;font-size: 10px;">SGST:Rs.<s:property value="sgst"/>
								]</span>
						</div>
						<div class="text-right">
							<span style=" font-weight: bold;font-size: 12px;"> 
							<b>NETAMT :  <s:property value="total"/></b>
							<input type="hidden" value="<s:property value="total"/>" id="wordtot"/>
							</span>
						</div>
					</div>
			</div>	
			
<!-- not required during printing -->
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="width: 100%; height: 0px; padding: 2px 0px 0px 0px;"> 

				<% if(loginInfo.isSMS_authority() || loginInfo.getUserType()==2) {%>
				<a type="button" class="btn btn-primary btn-lg savebigbtn" style="float: right;" title="Print" onclick="sendSms(<s:property value="billno"/>)"> Send SMS</a>
				<%} else { %>
				<a type="button" class="btn btn-primary btn-lg savebigbtn hidden" style="float: right;" title="Print" onclick="sendSms(<s:property value="billno"/>)">Send SMS</a>
				<%} %> &nbsp;&nbsp;&nbsp;&nbsp;
				<a type="button" class="btn btn-primary btn-lg savebigbtn" style="float: right;" title="Print" onclick="printpage()">Print</a>
		</div>
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



</body>
</html>

