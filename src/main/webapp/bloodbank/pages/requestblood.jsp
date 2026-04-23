<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">
<SCRIPT type="text/javascript" src="bloodbank/js/bloodbank.js"></SCRIPT>

<link rel="stylesheet" type="text/css" href="plugin/checkbox/style.css">
<style>
	.pnew{
    width: 3%;

}
.pview{
    width: 4%;

}
body {
    background-color: #fff;
}
.form-control {
    transition: none;
    border: 1px solid #e4e4e4;
    color: #222222;
    font-size: 12px;
    font-weight: normal;
    height: 25px;
    letter-spacing: 0.04em;
    margin-bottom: 0px;
    padding: 0px;
    width: 100%;
    background-color: #fff;
}
.rightborhe{
	border-right: 2px solid #efefef;
    min-height: 450px;
}
.page{
    width: 7%;
}

.mainheader {
    background-color: #43b9be !important;
}.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
.ipdtrans{
	    background-color: #a94442;
    color: #fff;
        padding: 3px 11px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}


.ottrans{
	    background-color: #f0ad4e;
    color: #fff;
    padding: 3px 13px 3px 5px;
    
    border-radius: 4px;
    font-size: 11px;
}
.opdtrans{
  background-color: #8a6d3b;
    color: #fff;
       padding: 3px 5px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}
.shifetdca{
	color: #555 !important;
    background-color: #efefef;
    padding: 3px 5px 3px 5px;
    border-radius: 4px;
    font-size: 11px;
}
.stockheight{
	    height: 16px;
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
	    border-right: 4px solid #ddd;
	    min-height: 215px;
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
.table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    border-top: none; 
}
.checkbox-custom, .checkbox-custom-alt {
    padding-left: 13px;
    cursor: pointer;
}
.imflag {
    display: inline-block;
    width: 4%;
    margin-right: 5px;
}
.imflag1 {
    display: inline-block;
    width: 10%;
}
.blink_me {
  animation: blinker 1s linear infinite;
  color:#d9534f;
}

@keyframes blinker {  
  50% { opacity: 0; }
}

.newstate {
    background-color: #ff6767;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
    width: 100%;
}
.approvedstate {
    background-color: #60b761;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
    width: 100%;
}
.completedstate {
    background-color: #af9f22;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-transform: uppercase;
    text-align: center;
    border-radius: 6px;
    width: 100%;
}
.collectstate {
    background-color: #36b9a5;
    text-transform: uppercase;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    border-radius: 6px;
    width: 100%;
}
.fontset {
    font-size: 9px;
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


<script type="text/javascript">

    window.onload= function(){
    	document.addEventListener("contextmenu", function(e){
    		e.preventDefault();
    		}, false); 
    	
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


</head>
<body>

								<div class="row">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
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
										<div Class="form-inline">
										  <s:form theme="simple" action="requestbloodBloodbank">
										  <div class="form-group">
										    <input name="name" class="form-control" placeholder="Search Name/ID"/>
										  </div>
										  <div class="form-group" style="width: 80px;">
										    <s:textfield name="fromdate" style="width:100%" id="fromdate" readonly="true" cssClass="form-control" placeholder="From Date" />
										  </div>
										   <div class="form-group" style="width: 80px;">
										    <s:textfield name="todate" id="todate" style="width:100%" readonly="true" cssClass="form-control" placeholder="To Date"/>
										  </div>
										  <div class="form-group hidden">
										    <select class="form-control" name="from">
		                                        <option value="0">Select From</option>
		                                        <option value="IPD">IPD </option>
		                                        <option value="OPD">OPD </option>
		                                        <option value="OT">OT </option>
		                                    </select>
										  </div>
										  <div class="form-group hidden">
										  <select class="form-control" name="blood_group">
		                                        <option value="0">Select Blood Group</option>
		                                        <option value="O+">O+ </option>
		                                        <option value="O-">O- </option>
		                                        <option value="A+">A+ </option>
		                                        <option value="A-">A- </option>
		                                        <option value="B+">B+ </option>
		                                        <option value="B-">B- </option>
		                                        <option value="AB+">AB+ </option>
		                                        <option value="AB-">AB- </option>
		                                    </select>
										  </div>
										   <div class="form-group hidden">
										    <select class="form-control" name="status">
		                                        <option value="">Select Status</option>
		                                        <option value="0">Pending </option>
		                                        <option value="1">Completed </option>
		                                    </select>
										  </div>
										 
										  <button type="submit" class="btn btn-primary" >Go</button>
										  <a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										 
										 <div class="form-group" style="float: right;">
												<!-- <div class="form-inline" style="float: right;margin-top: 5px;">
												
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#ffa3a3"></i> Deliver
											    </label>
											  </div>&nbsp;| 
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgb(72, 204, 184);"></i> Collected
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgba(229, 217, 129, 0.46)"></i> Completed
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#7fcc80"></i> Completed
											    </label>
											  </div>
											</div> -->
											</div>
											</s:form>
										</div>
										</div>
										<div class="col-lg-12 col-md-12" style="">
											<table class="table my-table xlstable table-bordered text-uppercase" style="width: 100%;">
                                        <thead>
                                           <tr>
                                            	<th style="width: 3%;color: black !important;" class="manastableheader">Sr.No</th>
                                            	<th style="width: 10%;color: black !important;" class="manastableheader">Date</th>
                                            	<!-- <th style="width: 7%;">Adm. ID</th> -->
                                                <th style="width: 31%;color: black !important;" class="manastableheader">Patient Details</th>
                                                <th style="width: 15%;color: black !important;" class="manastableheader">Practitioner Name</th>
                                               <!--  <th style="width: 7%;">Weight (Kg) </th> -->
                                                <th style="width: 6%;text-align:center;color: black !important;" class="manastableheader">Group</th>
                                               <!--  <th style="width: 5%;">Stock</th> -->
                                                <th style="width: 10%;color: black !important;" class="manastableheader">Req.Date | Time</th>
                                                <th style="width: 4%;color: black !important;" class="manastableheader">Req.Unit</th>
                                               <!--  <th style="width: 4%;">Allocate</th> -->
                                               <!--  <th style="width: 4%;"></th> -->
                                                <th style="width: 10%;text-align: center;color: black !important;" class="manastableheader">Status</th>
                                                <th style="width: 10%;text-align: center;color: black !important;" class="manastableheader">Print</th>
                                               <!--  <th></th>
                                                <th style="width: 6%;"></th>
                                                 -->
                                            </tr>
                                        </thead>
                                        <tbody>
                                         <%int i=0; %>
											<s:iterator value="requestedPatientList">
                                           <tr>	
                                           		<td><%=(++i)%></td>
                                           		<td><s:property value="commencing"/> | 12:27:05 </td>
                                           		<%-- <td><s:property value="ipdid"/></td> --%>
                                           		<s:if test="requestfrom=='IPD'">
                                           			<td><a><img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag"></a> <s:property value="clientname"/> | <s:property value="age"/> | <s:property value="ward"/></td>
                                           		</s:if>
                                           		<s:if test="requestfrom=='OT'">
                                           			<td><a><img src="emr/img/mediflag_opd_small.png" class="img-responsive imflag"></a> <s:property value="clientname"/> | <s:property value="age"/> | <s:property value="ward"/></td>
                                           		</s:if>
                                           		<s:if test="requestfrom=='OPD'">
                                           			<td><a><img src="emr/img/mediflag_casualty_small.png" class="img-responsive imflag"></a> <s:property value="clientname"/> | <s:property value="age"/> | <s:property value="ward"/></td>
                                           		</s:if>
                                           		<td><s:property value="pract_name"/></td>
                                           		<%-- <td><s:property value="weight"/></td> --%>
                                           		<td style="text-align:center;"><s:property value="blood_group"/></td>
                                           		
                                           		<%-- <s:if test="stock==0">
                                           		    <td style="color:red"><s:property value="stock"/></td>
                                           		</s:if>
                                           		<s:else>
                                           			<td><s:property value="stock"/></td>
                                           		</s:else> --%>
                                           		<td><s:property value="commencing"/> | <s:property value="time"/></td>
                                           		<td style="text-align:center;"><b style="color: coral;"><s:property value="qty"/></b></td>
                                           		<%-- <s:if test="given==0">
                                           		    <s:if test="stock==0">
                                           		       <td>NA</td>
                                           		   </s:if>
                                           		   <s:else>
                                           		   	<td><input type="text" id="alloc<s:property value="id"/>" onchange="checkwrap(this,<s:property value="qty"/>)" class="form-control stockheight" style="width: 60%;"> </td>
                                           		   </s:else> 
                                           			
                                           		</s:if>
                                           		<s:else>
                                           		  <td id="given<s:property value="id"/>"><s:property value="given"/> <s:if test="pending!=0"><span style="float: right;" id="edit<s:property value="id"/>"><a href="#" disabled="disabled" onclick="editBlood(<s:property value="id"/>,<s:property value="qty"/>)"><i class="fa fa-pencil"></i></a></span></s:if></td>
                                           		</s:else> --%>
                                           		
                                           		<%-- <s:elseif test="status==0">
                                           			<td><a href="#" disabled="disabled"   >Save</a></td>
                                           		</s:elseif>
                                           		<s:else>
                                           			<td><strike><a href="#" disabled="disabled">Save</a></strike></td>
                                           		</s:else> --%>
                                           		<%-- <s:if test="status==0">
                                           			<td><button class="fontset newstate" onclick="allocateBlood(<s:property value="id"/>)">Delivered</button></td>
                                           		</s:if>
                                           		<s:else>
                                           				<td><button class="fontset approvedstate" onclick="allocateBlood(<s:property value="id"/>)">Completed</button> </td>
                                           		</s:else> --%>
                                           		
                                           		<s:if test="status==0">
                                           			<td><button class="fontset newstate" onclick="doCrossMatch(<s:property value="id"/>,<s:property value="blood_group_id"/>,'<s:property value="blood_group"/>','<s:property value="clientname"/>','<s:property value="pract_name"/>','<s:property value="qty"/>')">Requested</button></td>
                                           		</s:if>
                                           		<s:elseif test="status==1">
                                           			<td><button class="fontset collectstate" onclick="doIssueBloodBank(<s:property value="id"/>,'<s:property value="blood_group"/>','<s:property value="clientname"/>','<s:property value="qty"/>','<s:property value="crossmatch_productid"/>')" >Cross Matched</button></td>
                                           		</s:elseif>
                                           		<s:elseif test="status==2">
                                           			<td><button class="fontset collectstate" onclick="showAddchargePopupBloodbank(<s:property value="id"/>,<s:property value="clientid"/>)" >Add Charge</button></td>
                                           		</s:elseif>
                                           		 <s:else>
                                           				<td><button class="fontset approvedstate">Completed</button> </td>
                                           		</s:else>
                                           		
                                           		<td style="text-align:center;"><a href="#" onclick="openPopup('bloodbankprintBloodbank?reqid=<s:property value="id"/>')"><i class="fa fa-print"></i></a></td>
                                           		
                                           		<%-- <s:if test="reqqty>stock">
                                           		    <td><a href="#" onclick="bloodrequest('<s:property value="blood_group_id"/>')" ><i class="fa fa-commenting" aria-hidden="true"></i> SMS</a></td>
                                           		</s:if>
                                           		<s:else>
                                           			<td></td>
                                           		</s:else>
                                           		   <td> <a href="#" onclick="openPopup('Statement?clientId=<s:property value="clientid"/>')" ><small>Add Charge</small></a> </td> --%>
                                           		
                                           </tr>
                                           </s:iterator><!--
                                           <tr>
                                           		<td>02</td>
                                           		<td>03/03/2017</td>
                                           		<td>123456</td>
                                           		<td><a class="ottrans" style=" color: #ffffff !important;">OT</a> </td>
                                           		<td>21/Genral</td>
                                           		<td>Praful ghagre</td>
                                           		<td>26/Male</td>
                                           		<td>55kg</td>
                                           		<td>O+</td>
                                           		<td>2 | 03-03-2017 | 05:45 pm</td>
                                           		<td>5</td>
                                           		<td><input type="text" class="form-control stockheight" style="width: 60%;"></td>
                                           		<td><a href="#" disabled="disabled">Save</a></td>
                                           		<td><i class="fa fa-circle" aria-hidden="true" style="color:red;"></i> Pending</td>
                                           		<td></td>
                                           </tr>
                                           <tr>
                                           		<td>03</td>
                                           		<td>03/03/2017</td>
                                           		<td>123456</td>
                                           		<td><a class="opdtrans" style=" color: #ffffff !important;">OPD</a></td>
                                           		<td>21/Genral</td>
                                           		<td>Praful ghagre</td>
                                           		<td>26/Male</td>
                                           		<td>55kg</td>
                                           		<td>A+</td>
                                           		<td>2 | 03-03-2017 | 05:45 pm</td>
                                           		<td><span style="color:red;">1</span></td>
                                           		<td><input type="text" class="form-control stockheight" style="width: 60%;"></td>
                                           		<td><a href="#" disabled="disabled">Save</a></td>
                                           		<td><i class="fa fa-circle" aria-hidden="true" style="color:red;"></i> Pending</td>
                                           		<td><a href="#" data-toggle="modal" data-target="#donarlist"><i class="fa fa-commenting" aria-hidden="true"></i> SMS</a></td>
                                           </tr>
                                           
                                           
                                           <tr>	
                                           		<td>04</td>
                                           		<td>01/03/2017</td>
                                           		<td>123456</td>
                                           		<td><a class="ipdtrans" style=" color: #ffffff !important;">IPD</a></td>
                                           		<td>21/Genral</td>
                                           		<td>Praful ghagre</td>
                                           		<td>26/Male</td>
                                           		<td>55kg</td>
                                           		<td>O+</td>
                                           		<td>2 | 02-03-2017 | 05:45 pm</td>
                                           		<td>3</td>
                                           		<td>2</td>
                                           		<td><strike><a href="#" disabled="disabled">Save</a></strike></td>
                                           		<td><i class="fa fa-check-circle" aria-hidden="true" style="color:green;"></i> Completed</td>
                                           		<td></td>
                                           		
                                           </tr>
                                        --></tbody>
                                    </table>
                                    
                                    <table class="table my-table xlstable table-bordered hidden" style="width: 100%;">
                                        <thead>
                                            <tr>
                                            	
                                            	<th style="width: 5%;">Date</th>
                                            	<th style="width: 9%;">Dr.Name</th>
                                                <th style="width: 14%;">Patient Details</th>
                                                <th style="width: 7%;">Bed | Ward</th>
                                                <th style="width: 4%;">Group</th>
                                                <th style="width: 32%;">Components Request</th>
                                                <th style="width: 10%;">Date | Time</th>
                                                <th>Status</th>
                                                <th>Received</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <tr>
                                           		
                                           		<td>03/03/2017</td>
                                           		<td>Dr.Girish Wankhede</td>
                                           		<td><img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag">Praful ghagre | 26/Male | 55kg </td>
                                           		<td>21 | Genral</td>
                                           		<td>O+</td>
                                           		<td><span style="color: brown;">PRC</span> 2(BTL) | <span style="color: cornflowerblue;">FFP</span> 1(BTL) | <span style="color: fuchsia;">PC</span> 3(BTL) | <span style="color: lightcoral;">SDP</span> 2(BTL) | <span style="color: seagreen;">CRYO</span> 1(BTL) | <span style="color: darkorange;">WB</span> 3(BTL) </td>
                                           		<td>22/05/2017 | 12:11 PM</td>
                                           		<td style="text-align: center;"><span class="blink_me"><i class="fa fa-circle" aria-hidden="true"></i></span></td>
                                           		<td><a href="#" data-toggle="modal" data-target="#samreci">Sample Received</a></td>
                                           </tr>
                                           <tr>
                                           		<td>03/03/2017</td>
                                           		<td>Dr.Girish Wankhede</td>
                                           		<td><img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag">Praful ghagre | 26/Male | 55kg </td>
                                           		<td>21 | Genral</td>
                                           		<td>O+</td>
                                           		<td><span style="color: brown;">PRC</span> 2(BTL) | <span style="color: cornflowerblue;">FFP</span> 1(BTL) | <span style="color: fuchsia;">PC</span> 3(BTL) | <span style="color: lightcoral;">SDP</span> 2(BTL) | <span style="color: seagreen;">CRYO</span> 1(BTL) | <span style="color: darkorange;">WB</span> 3(BTL) </td>
                                           		<td>22/05/2017 | 12:11 PM</td>
                                           		<td style="text-align: center;"><span title="Ready"><i class="fa fa-check" style="color:green;" aria-hidden="true"></i></span></td>
                                           		<td>Abhinav Parmar | 22/05/2017 13:33 PM</td>
                                           </tr>
                                           <tr>
                                           		<td>03/03/2017</td>
                                           		<td>Dr.Girish Wankhede</td>
                                           		<td><img src="emr/img/medicineflag_casualty_small.png" class="img-responsive imflag">Praful ghagre | 26/Male | 55kg </td>
                                           		<td>21 | Genral</td>
                                           		<td>O+</td>
                                           		<td><span style="color: brown;">PRC</span> 2(BTL) | <span style="color: cornflowerblue;">FFP</span> 1(BTL) | <span style="color: fuchsia;">PC</span> 3(BTL) | <span style="color: lightcoral;">SDP</span> 2(BTL) | <span style="color: seagreen;">CRYO</span> 1(BTL) | <span style="color: darkorange;">WB</span> 3(BTL) </td>
                                           		<td>22/05/2017 | 12:11 PM</td>
                                           		<td style="text-align: center;"><a href="#" data-toggle="modal" data-target="#rejectedsam"><span title="Rejected"><i class="fa fa-times" style="color:red;" aria-hidden="true"></i></span></a></td>
                                           		<td>Abhinav Parmar | 22/05/2017 13:33 PM</td>
                                           </tr>
                                        </tbody>
                                    </table>
										</div>
										
										<s:form action="requestbloodBloodBank" name="paginationForm" id="paginationForm" theme="simple">
										<div class="col-lg-12 col-md-12" style="    padding-top: 15px;">
											<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
												Total:<label class="text-info"><s:property value="totalRecords" /></label>
											</div>
											<%@ include file="/common/pages/pagination.jsp"%>
										</div>
									</s:form>		
										
								</div>
							</div>
							
							
							
							
							
							
							
							<!-- Modal -->
							<div id="rejectedsam" class="modal fade" role="dialog">
							  <div class="modal-dialog modal-sm">
							
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title">Sample Rejected</h4>
							      </div>
							      <div class="modal-body">
							        <div class="col-lg-12 col-md-12 col-xs-12">
							        	<div class="form-group">
										    <label for="email"><i class="fa fa-tint" style="color:red;" aria-hidden="true"></i> Praful Ghagre</label>
										    <span class="pull-right">21 | Genral</span>
										</div>
										<div class="form-group">
										    <label for="email">Rejected By</label>
										    <p>Abhinav Parmar</p>
										  </div>
							        	<div class="form-group">
										    <label for="email">Reson For Rejection</label>
										    <textarea class="form-control" rows="3" value="Not Available In Stock"></textarea>
										  </div>
										  
							        </div>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-primary hidden">Received & Send</button>
							      </div>
							    </div>
							  </div>
							</div>
							
							<!-- Modal -->
							<div id="samreci" class="modal fade" role="dialog">
							  <div class="modal-dialog modal-sm">
							
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title">Sample Received</h4>
							      </div>
							      <div class="modal-body">
							        <div class="col-lg-12 col-md-12 col-xs-12">
							        	<div class="form-group">
										    <label for="email"><i class="fa fa-tint" style="color:red;" aria-hidden="true"></i> Praful Ghagre</label>
										    <span class="pull-right">21 | Genral</span>
										</div>
							        	<div class="form-group">
										    <label for="email">Technician Name</label>
										    <select class="form-control" name="donor_state">
											    <option value="Abhinav Parmar">Abhinav Parmar</option>
											    <option value="Ruchita Ghugal">Ruchita Ghugal</option>
										    </select>
										  </div>
										  <div class="form-group">
										    <label for="email">Send To</label>
										    <select class="form-control" name="donor_state">
											    <option value="Test/Component Lab">Test/Component Lab</option>
											    <option value="Cross Matching Lab">Cross Matching Lab</option>
										    </select>
										  </div>
							        </div>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-primary">Received & Send</button>
							      </div>
							    </div>
							
							  </div>
							</div>
							
							
							
							
							
							<s:form action="sendsmsBloodbank" id="smsform" method="post">
							 
							     <s:hidden name="message" id="message"/>
							     <s:hidden name="users" id="users"/>
							     <s:hidden name="banks" id="banks"/>
							</s:form>
							
							
							
							
							
							
							
							<!-- Request SMS to Donar Lsit -->
							<div id="donarlist" class="modal fade" role="dialog">
							  <div class="modal-dialog">
							
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title">Send SMS To Donor List</h4>
							      </div>
							      <div class="modal-body">
							        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
							        	<div class="col-lg-2 col-md-2 col-xs-12 rightborhe">
							        		<div class="form-group">
											    <label for="exampleInputEmail1">Blood Group</label>
											    <select class="form-control" name="blood_group" id="blood_group">
		                                        <option value="O+">O+ </option>
		                                        <option value="O-">O- </option>
		                                        <option value="A+">A+ </option>
		                                        <option value="A-">A- </option>
		                                        <option value="B+">B+ </option>
		                                        <option value="B-">B- </option>
		                                        <option value="AB+">AB+ </option>
		                                        <option value="AB-">AB- </option>
		                                    </select>
											  </div>
							        	</div>
							        	<div class="col-lg-4 col-md-4 col-xs-12 rightborhe" style="padding-right: 0px;">
							        		<div class="setovehe">
							        		<label for="exampleInputEmail1">Select Donor</label>
							        		<div class="donorlist">
							        				<table class="table table-hover table-bordered"> 
									  		<tbody class="" id="donorlist"> 
									  			<tr> 
									  				<th>
									  					<label class="checkbox checkbox-custom-alt m-0"><input type="checkbox" id="select-all"><i></i> Select All</label>
									  				</th> 
									  			</tr> 
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i> Jitendra Binzade</label>
									  				</th>
									  				
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i> Manoj Mishra</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i> Abhinav Parmar</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i> Ruchita Ghugal</label>
									  				</th>
									  			</tr>
									  			
												
									  			
									  		</tbody> 
									  	</table>
							        		</div>
									  	
									  	</div>
									  	<br>
									  	
									  	
									  	
									  	<div class="setovehe">
							        		<label for="exampleInputEmail1">Select Blood Bank</label>
							        		<div class="banklist">
							        				<table class="table table-hover table-bordered"> 
									  		<tbody class="" id="banklist"> 
									  			<tr> 
									  				<th>
									  					<label class="checkbox checkbox-custom-alt m-0"><input type="checkbox" id="select-all"><i></i> Select All</label>
									  				</th> 
									  			</tr> 
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i> Ayush Blood Bank</label>
									  				</th>
									  				
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i> Life Blood Bank</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i> Jivan Jyoti Blood Bank</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i> Arogya Blood Bank</label>
									  				</th>
									  			</tr>
									  			
												
									  			
									  		</tbody> 
									  	</table>
							        		</div>
									  	
									  	</div>
							        	
							        	</div>
							        	<div class="col-lg-6 col-md-6 col-xs-12">
							        	<div class="form-group">
										    <label for="exampleInputEmail1">SMS text</label>
										    <textarea class="form-control" id="smstext" rows="10">Dear Blood Donor,&#13;&#10;We urgently need your blood (A+) contact us if it's possible.&#13;&#10;- Hospital Name &#13;&#10;- Contact No</textarea>
										  </div>
										  <div class="form-group">
										  	<button type="button" class="btn btn-primary pull-right" onclick="sendsms()">Send SMS</button>
										  </div>
							        	</div>
							        
							        
							        </div>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-primary hidden" data-dismiss="modal">Send SMS</button>
							      </div>
							    </div>
							
							  </div>
							</div>
							
<!--   25-02-2019 -->
<div class="modal fade popoverpop" id="crossmatchmodel" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Cross Match Popup</h5>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12">
							<div class="col-lg-12 col-md-12 col-xs-12">
							<s:hidden id="crossmatchid"></s:hidden>
									<div class="form-group">
									    <label for="exampleInputEmail1">Patient Name:</label>
									   	<div id="patientnamecrossmatchdiv"></div>
									</div>
									  
									<div class="form-group">
									    <label for="exampleInputEmail1">Practitioner Name:</label>
									   	<div id="practnamecrossmatchdiv"></div>
									</div>
									  
									<div class="form-group">
									    <label for="exampleInputEmail1">Blood Group:</label>
									   	<label id="bloodgroupcrossmatchdiv"></label>
									</div>
									  
									 <div class="form-group">
									    <label for="exampleInputEmail1">Requested Qty:</label>
									   	<label id="reqqtycrossmatchdiv"></label>
									</div>
									  
									<div class="form-group">
									    <label for="exampleInputEmail1">Select Product Bag:</label>
									   	<div id="productlistcrossmatchdiv"></div>
									</div>
									  
									
							</div>
						</div>
					</div>
                </div>
				<div class="modal-footer">
					<a class="btn btn-primary" href="#" onclick="validatecrossmatch()">Cross Match</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade popoverpop" id="issuebloodbankmodel" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Blood bank Issue Popup</h5>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12">
							<div class="col-lg-12 col-md-12 col-xs-12">
									<s:hidden id="issuebloodid"></s:hidden>
									<s:hidden id="reqqtyissueqty"></s:hidden>
									<s:hidden id="productissueid"></s:hidden>
									<div class="form-group">
									    <label for="exampleInputEmail1">Patient Name:</label>
									   	<div id="patientnameissuediv"></div>
									</div>
									  
									<div class="form-group">
									    <label for="exampleInputEmail1">Blood Group:</label>
									   	<label id="bloodgroupissuediv"></label>
									</div>
									  
									<div class="form-group">
									    <label for="exampleInputEmail1">Requested Qty:</label>
									   	<label id="reqqtyissuediv"></label>
									</div>
									  
									<div class="form-group">
									    <label for="exampleInputEmail1">Blood Bag:</label>
									   	<div id="productlistissuediv"></div>
									</div>
									 
									 <div class="form-group">
									    <label for="exampleInputEmail1">Handover To:</label>
									   	<input type="text" id="issuehandoverid" class="form-control">
									</div>
									
							</div>
						</div>
					</div>
                </div>
				<div class="modal-footer">
					<a class="btn btn-primary" href="#" onclick="validateissuebloodbank()">Issue Bag</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="addchargepopupblood" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Add Charges</span></h4>
				</div>
				<div class="modal-body">
				
					<%-- <%@ include file="/ipd/pages/addcharges.jsp"%> --%>
					<jsp:include  page="/ipd/pages/invaddcharge.jsp" />
				</div>
				
				<s:form action="estimateCharges" theme="simple" id="estimatefrm" target="formtarget">
					<s:hidden name="clientId" id="estimateclientid"/>
					<s:hidden name="actionType" value="0"/>
				</s:form>
				
				<div class="modal-footer">
				<%-- <%if(loginfo.isCash_desk() || loginfo.getUserType()==2){ %>
				<button type="button" class="btn btn-primary" 
							onclick="createChargeAndUpdateAccount('cash')">Cash Desk</button>
				<%} %>
					<button type="button" class="btn btn-primary" 
							onclick="createestimate()">View
							Estimate</button> --%>
				
				
					<button type="button" class="btn btn-primary" 
						onclick="createChargeAndUpdateAccountBloodbank('Charge')">Create
						Charge</button>
					
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
							
							<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
              <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
							 <script src="_assets/newtheme/js/main.js"></script>
							
							<script>
					            $(window).load(function(){
					                $('#select-all').change(function() {
					                    if ($(this).is(":checked")) {
					                        $('#mails-list .mail-select input').prop('checked', true);
					                    } else {
					                        $('#mails-list .mail-select input').prop('checked', false);
					                    }
					                });
					            });
        					</script>
        					
        					<script>
				             $(function() {
								  $('.donorlist').slimScroll({
								   		height : '200px',
								   		railVisible: true,
										alwaysVisible: true
								  });
								  $('.banklist').slimScroll({
								   		height : '170px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>
        <!--/ Page Specific Scripts -->
        <script>
        function disableCHK()
				                	{
					                	document.getElementById("lblCHK1");
					                	document.getElementById("checkbox-1").disabled = true;
				                	}
									
							</script>
							

</body>
</html>