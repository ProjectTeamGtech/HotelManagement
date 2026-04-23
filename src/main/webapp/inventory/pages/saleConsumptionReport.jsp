<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>



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
    padding: 10px 0px 10px 15px;
}
</style>

<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<SCRIPT type="text/javascript">

function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Consumption Report",
					filename: "Consumption Report",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}
     window.onload = function(){
              
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
  
           
</SCRIPT>
 <script type="text/javascript" src="common/js/pagination.js"></script>
</head>
<body>
<div class="print-visible hidden-md hidden-lg letterheadhgt" >
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div>

								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding: 0px;">
									 <s:form theme="simple" action="consumptionreportProduct" >
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										
										<div class="form-inline">
										<div class="form-group">
											<span class="text-uppercase"><b>Consumption Report</b> &nbsp; - &nbsp;</span>
										</div>
										    <div class="form-group" style="width: 7%;">
										      <s:textfield cssClass="form-control" id="fromdate" readonly="true" name="fromdate" placeholder="From Date" cssStyle="width:100%"/>
										    </div>
										    <div class="form-group" style="width: 7%;">
										      <s:textfield cssClass="form-control" id="todate" readonly="true" name="todate" placeholder="To Date" cssStyle="width:100%"/>
										    </div>
										    <div class="form-group" style="width: 10%">
										      <s:select name="salereturn" id="salereturun" list="#{'0':'Select Sale Type','1':'Sales','2':'Sales Return'}" cssClass="form-control chosen-select" />
										    </div>
										    <div class="form-group" style="width: 10%">
										      <s:select name="billtype" id="billtype" list="#{'0':'Select Paymode','Cash':'Cash','Credit':'Credit','Cash':'Cash','Card':'Card','Cheque':'Cheque','NEFT/RTGS':'NEFT/RTGS','Estimate':'Estimate','Hospital':'Hospital'}" cssClass="form-control chosen-select" />
										    </div>
										    
										    <%if(loginInfo.getUserType()==2 || loginInfo.getPharmacyUserType()==2 ) {%>
												<div class="form-group">
													<s:select list="locationListPharmacy" name="location" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
											     	</s:select> 
											     </div>
											<%}%>  
										    	<button type="submit" class="btn btn-primary">Go</button>
										  		<a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  		<button type="button" class="btn btn-warning pull-right hidden" onclick="printDiv('page_printer');" style="margin-right: 5px;">Print</button>
										  		<a href="#" onclick="printReport()" class="btn btn-warning pull-right hidden" style="margin-right: 10px;">Download Excel</a>
										  		<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
										  		&nbsp;<a href="#" onclick="printpage();" class="btn btn-primary hidden-print"><i class="fa fa-print"></i></a>
										  		
										  </div>
										
										</div>
										</div>
									</s:form>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-sm hidden-md visible-print" style="padding: 0px;">
										<center><h3>Consumption Report </h3> </center>	
									</div>
									<table class="table table-bordered table-striped xlstable" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<th style="width: 3%;">Sr.No</th>
					                                    	<th style="width: 11%;">Date/Time</th>
					                                        <th style="width: 18%;">Patient Name</th>
					                                        <th style="width: 5%;">OPD/IPD</th>
					                                        <th style="width: 6%;">Third Party</th>
					                                        <th style="width: 5%;">Bill No</th>
					                                        <th style="width: 5%;text-align:right;">Bill Amt</th>
					                                        <th style="width: 5%;text-align:right;">SGST</th>
					                                        <th style="width: 5%;text-align:right;">CGST</th>
					                                        <th style="width: 8%;text-align:right;">Total Amt</th>
					                                        <th style="width: 7%;">Trans.Type</th>
					                                        <th style="width: 12%;">Doctor Name</th>
					                                        <th style="width: 6%;">Bill Type</th>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                   
					                                  <%int i=0; %>
					                                    <s:iterator value="salesReturnProduct">
					                                    <tr>
					                                        <td><%=(++i) %></td>
					                                        <td><s:property value="dateTime"/></td>
					                                        <td><s:property value="clientname"/></td>
					                                        <td><s:property value="clienttype"/></td>
					                                        <td><s:property value="tpname"/></td>
					                                        <td><a href="#" onclick="openDisplayPopup('viewbillPharmacy?selectedid=0&billno=<s:property value="id"/>')" ><s:property value="id"/></a></td>
					                                        <td class="text-right">Rs.<s:property value="debit"/></td>
					                                        <td class="text-right">Rs.<s:property value="sgst"/></td>
					                                        <td class="text-right">Rs.<s:property value="cgst"/></td>
					                                        <td class="text-right">Rs.<s:property value="total"/></td>
					                                        <td><s:property value="type"/></td>
					                                        <td><s:property value="doctor"/></td>
					                                        <td><s:property value="billtype"/></td>
					                                    </tr>
					                                    </s:iterator>	
					                                    
					                                </tbody>
					                            </table>
					                            	<s:form action="consumptionreportProduct" name="paginationForm" id="paginationForm" theme="simple">
							    							<s:hidden name="salereturun"></s:hidden>
							    	 						<s:hidden name="fromdate"></s:hidden>
							    							<s:hidden name="todate"></s:hidden> 
							    							<s:hidden name="billtype"></s:hidden> 
							    							<s:hidden name="location"></s:hidden>
															<div class="col-lg-12 hidden-print">
																<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
																	Total:<label class="text-info"><s:property value="totalRecords" /> Records
																</label>
																</div>
															<%@ include file="/common/pages/pagination.jsp"%>
															</div>
													</s:form>             
											</div>

<script>
    function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
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
	
	
	
</body>
</html>