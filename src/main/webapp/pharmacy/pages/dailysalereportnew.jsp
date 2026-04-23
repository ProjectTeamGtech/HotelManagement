<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
 <script type="text/javascript" src="common/js/pagination.js"></script>
 <SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<style>
	 .padright {
            padding-left: 40px;
        }
        .table.table {
            color: RGBA(85, 85, 85, 0.85);
            background-color: #fff;
        }

        .comtitle {
            font-size: 13px;
            background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
            color: rgb(255, 255, 255);
        }

        .marbot25 {
            margin-bottom: 25px;
        }

        .editcompany {
            float: right;
            font-size: 17px;
            color: #fff;
        }

        .borright {
            border-right: 1px dashed rgb(192, 192, 192);
        }

        .buildinglogo {
            width: 60%;
            margin-top: 30px;
        }
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
            border-top: 0;
            min-height: auto !important;
        }
        .miheight {
            min-height: auto !important;
        }
        .my-table th {
            background-color: #424A5D;
            color: #fff !important;
            border-bottom: 1px solid #DFD8D4;
            border-right: 1px solid #DFD8D4;
            border-top: 1px solid #DFD8D4;
            padding: 3px 3px 4px 5px;
            text-align: left;
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 1px 7px 1px 7px !important;
        }
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
                display: inline-block;
            }
        .sidebar-xs #header .branding {
            width: 100%;
            padding-top: 7px;
            text-align: center;
        }
        .theight {
            height: 21px;
        }

        .contain {
  width: 500px;
  margin: 0 auto;
  position: relative;
  padding: 20px;
}
.notificationicon {
 float:right;
}


.topheadbaxck {
    background-color: #f5f5f5;
    padding: 10px 10px 10px 10px;
}

.notificationicon.on i {
  font-size: 18px;
}
.notifications {
  max-width: 300px;
  width: 300px;
  background: #fff;
  border: 1px solid #ccc;
  padding: 0px;
  box-shadow: 0px 0px 20px #666;
  position: absolute;
  opacity: 1;
  top: 25px;
  transition: .2s;
  opacity: 0;
  right: 5px;
}

.notifications.open {
  opacity: 1;
  transition: .2s;
}
.notifications li {
 
  list-style-type: none;
}
.notifications li.titlebar {
  border-bottom: 1px solid #ccc;
  color: #666;
  font-size: 12px;
  cursor: inherit;
  padding: 2px 5px;
}
.notifications li.titlebar:hover {
  background: #fff;
}
.notifications li.titlebar .settings {
  float: right;
  cursor: pointer;
}
.notifications li.seeall {
  text-align: center;
  font-size: 12px;
  min-height: 30px;
  text-transform: uppercase;
  position: relative;
  border-top: 1px solid #ccc;
}
.notifications li.seeall a {
  background: #f5f5f5;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  color: #007fff;
  padding-top: 8px;
}
.notifications .notifbox {
  max-height: 300px;
  overflow: auto;
}
.notifications .notifbox li {
  color: #666;
}
.notifications .notifbox li a {
  color: #666;
}
.notifications .notifbox li.unread {
  background: #e5f2ff;
}
.notifications .notifbox li.notif {
  min-height: 40px;
  border-bottom: 1px solid #ccc;
  position: relative;
}
.notifications .notifbox li.notif:last-child {
  border-bottom: none;
}
.notifications .notifbox li.notif .imageblock {
  width: 70px;
  position: absolute;
  left: 5px;
}
.notifications .notifbox li.notif .imageblock .notifimage {
  height: 60px;
}
.notifications .notifbox li.notif .messageblock {
    padding: 8px;
}
.notifications .notifbox li.notif .messageblock .message a {
  color: #007fff;
}
.notifications .notifbox li.notif .messageblock .messageaction {
  min-height: 30px;
  margin-bottom: 5px;
}
.notifications .notifbox li.notif .messageblock .messageaction .button {
  font-weight: normal;
  text-transform: uppercase;
}
.notifications .notifbox li.notif .messageblock .messageaction .button.success,
.notifications .notifbox li.notif .messageblock .messageaction .button.alert {
  color: #fff;
}
.notifications .notifbox li.notif .messageblock .messageinfo {
  font-size: 13px;
  color: #999;
}
.badge {
  position: absolute;
  top: 30px;
  right: -20px;
}    
.title {
    display: inline;
    float: none;
    margin-left: 17px;
   color: #fff;
    font-size: 12px;
}
.btnbackset{
 background-color: #efefef;
 padding: 6px;
}
     .count {
    position: absolute;
    top: -2px;
    right: 11px;
    text-align: center;
    font-size: 9px;
    padding: 2px 3px;
    line-height: .9;
     background-color: #dd4b39 !important;
     border-radius: 50px;
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
.topback2 {
    background-color: #f5f5f5;
    padding: 10px 10px 10px 0px;
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
<body>

<script>

function printExcel() {

   			$(".xlstable").table2excel({
				exclude: ".noExl",
				name: "Daily sale report",
				filename: "dailysalereport",
				fileext: ".xls",
				exclude_img: true,
				exclude_links: true,
				exclude_inputs: true
			});
}


	
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
									<div class="row details mainheader">
										<h4>&nbsp;&nbsp;Daily Sale Report New </h4>
									</div>
									<div class="col-lg-12 col-md-12 topback2 ">
										<div class="col-md-12">
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
				                           <form class="form-inline" name="dailysalereportPharmacyAjax" method="post" id="myform" >
				                          		
				                           		<div class="form-group" style="width:9%;">
												  	<s:textfield cssClass="form-control" id="fromdate" name="fromdate" placeholder="From Date" cssStyle="width:100%;"/>
												</div>
												<div class="form-group" style="width:9%;">
												  	<s:textfield cssClass="form-control" id="todate" name="todate" placeholder="To Date" cssStyle="width:100%;"/>
												</div>
												<%-- <div class="form-group" style="width:9%;">
												  	<s:textfield cssClass="form-control" id="searchbyprodname" name="searchbyprodname" placeholder="Product name" cssStyle="width:100%;"/>
												</div>
												<div class="form-group" style="width:15%;">
													<s:select list="locationListPharmacy"  name="location_filter" id="location_filter" cssStyle="width:0px !important" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
										     		</s:select> 	  
												</div> --%>
												
												<div class="form-group" style="width:3%;">
												  	<button type="submit" class="btn btn-primary">Go</button>
												</div>
												<div class="form-group" style="width:3%;">
													<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
												</div>
										   </form>
				                        </div>
				                      

										</div>
										
										<div class="">
										
											<table class="table table-bordered xlstable" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
					                           <thead>
				                          			
					                                    <tr class="tableback">
					                                    	<th style="width: 20%;text-align: right;"></th>
					                                        <th style="width: 10%;text-align: right;">CASH</th>
					                                        <th style="width: 10%;text-align: right;">CARD</th>
					                                        <th style="width: 10%;text-align: right;">CHEQUE</th>
					                                        <th style="width: 10%;text-align: right;">NEFT/RTGS</th>
					                                        <th style="width: 10%;text-align: right;">Online Payment</th>
					                                        <th style="width: 10%;text-align: right;">CREDIT</th>
					                                        <th style="width: 10%;text-align: right;">HOSPITAL</th>
					                                        <th style="width: 10%;text-align: right;">Total</th>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                   <tr>
						                                   	<td>Sales</td>
						                                   	<td style="text-align: right;">Rs.<s:property value="salecash"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="salecard"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="salecheque"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="saleneft"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="saleonlinepayment"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="salecredit"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="salehospital"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="saletotal"/></td>
					                                   </tr>
					                                   
					                                    <tr>
						                                   	<td>Less: Discount</td>
						                                   	<td style="text-align: right;">Rs.<s:property value="discountsalecash"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="discountsalecard"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="discountsalecheque"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="discountsaleneft"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="discountsaleonlinepayment"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="discountsalecredit"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="discountsalehospital"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="discountsaletotal"/></td>
					                                   </tr>
					                                   
					                                    <tr>
						                                   	<td>Net Sales</td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netsalecash"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="netsalecard"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="netsalecheque"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netsaleneft"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netsaleonlinepayment"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netsalecredit"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netsalehospital"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netsaletotal"/></td>
					                                   </tr>
					                                   
					                                   <tr>
						                                   	<td>Payment</td>
						                                   	<td style="text-align: right;">Rs.<s:property value="cashpayments"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="cardpayments"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="chequepayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="neftpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="onlinepaymentpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="creditpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="hospitalpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="totalpayments"/></td>
					                                   </tr>
					                                   
					                                    <tr>
						                                   	<td>Receipt Against Outstanding</td>
						                                   	<td style="text-align: right;">Rs.<s:property value="outcashpayments"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="outcardpayments"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="outchequepayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="outneftpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="outonlinepaymentpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="outcreditpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="outhospitalpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="outtotalpayments"/></td>
					                                   </tr>
					                                   
					                                    <tr>
						                                   	<td>Total Collection</td>
						                                   	<td style="text-align: right;">Rs.<s:property value="totalcashpayments"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="totalcardpayments"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="totalchequepayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="totalneftpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="totalonlinepaymentpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="totalcreditpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="totalhospitalpayments"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="totaltotalpayments"/></td>
					                                   </tr>
					                                   
					                                    <tr>
						                                   	<td>Sales Return</td>
						                                   	<td style="text-align: right;">Rs.<s:property value="returncash"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="returncard"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="returncheque"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="returnneft"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="returnonlinepayment"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="returncredit"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="returnhospital"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="returntotal"/></td>
					                                   </tr>
					                                   
					                                    <tr>
						                                   	<td>Net Collection</td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netcashcollection"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="netcardcollection"/></td>
						                            		<td style="text-align: right;">Rs.<s:property value="netchequecollection"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netneftcollection"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netonlinepaymentcollection"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="netcreditcollection"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="nethospitalcollection"/></td>
						                                   	<td style="text-align: right;">Rs.<s:property value="nettotalcollection"/></td>
					                                   </tr>
					                                </tbody>
				                          		    
					                                
					                            </table><br>
					                                
										</div>

</body>
<%-- <script>
function setpglimit(val){
	document.getElementById('countval').value=val;
	document.getElementById("myform").submit();
}
</script> --%>


</html>