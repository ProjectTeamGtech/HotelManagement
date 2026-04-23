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


<SCRIPT type="text/javascript">

	function printReport() {
				
				  $("#tablesort").table2excel({
					exclude: ".noExl",
					name: "Consumption Report",
					filename: "ConsumptionReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }
	 function printStockReportExcel() {

	        $(".tablestock").table2excel({
						exclude: ".noExl",
						name: "Consumption Report",
						filename: "Consumption Report",
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
		             
	 });
  
           
</SCRIPT>
 <script type="text/javascript" src="common/js/pagination.js"></script>
  <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>
<body>

								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									 <s:form theme="simple" action="patientconsumptionreportProduct" >
									 <s:hidden name="isfromcathlab"></s:hidden>
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										
										<div class="form-inline">
										<div class="form-group">
											<s:if test="isfromcathlab==1">
													<span class="text-uppercase"><b>Consumption Report</b> &nbsp; - &nbsp;</span>
											</s:if>
											<s:else>
												<s:if test="hisuserfilter==0">
													<span class="text-uppercase"><b>Patient Consumption Report</b> &nbsp; - &nbsp;</span>
												</s:if>
												<s:elseif test="hisuserfilter==1">
													<span class="text-uppercase"><b>User Consumption Report</b> &nbsp; - &nbsp;</span>
												</s:elseif>
												<s:else>
													<span class="text-uppercase"><b>Department Consumption Report</b> &nbsp; - &nbsp;</span>
												</s:else>
											</s:else>
											
										</div>
											  <s:if test="isfromcathlab==1">
													<div class="form-group" style="width: 15%;">
										      			<s:textfield cssClass="form-control" id="searchtext" name="searchtext" placeholder="Search Patient/User/UHID" cssStyle="width:100%"/>
										    		</div>
											</s:if>
											<s:else>
												 <s:if test="hisuserfilter==2">
										     		
										    	</s:if>
										    	<s:else>
										    		<div class="form-group" style="width: 15%;">
										      			<s:textfield cssClass="form-control" id="searchtext" name="searchtext" placeholder="Search Patient/User/UHID" cssStyle="width:100%"/>
										    		</div>
										    	</s:else>
										    </s:else>
											 
										    <div class="form-group" style="width: 7%;">
										      	<s:textfield cssClass="form-control" id="fromdate" readonly="true" name="fromdate" placeholder="From Date" cssStyle="width:100%"/>
										    </div>
										    <div class="form-group" style="width: 7%;">
										      	<s:textfield cssClass="form-control" id="todate" readonly="true" name="todate" placeholder="To Date" cssStyle="width:100%"/>
										    </div>
										    <s:if test="isfromcathlab==1">
													
											</s:if>
											<s:else>
												<div class="form-group" >
										      		<s:select cssClass="form-control chosen-select" list="#{'0':'Patient', '1':'YUVICARE User', '2':'Department'}"  name="hisuserfilter" />
										    	</div>
							       <%
									if (loginInfo.getSuperadminid() == 1 || loginInfo.getUserType() == 2 || loginInfo.getJobTitle().equals("Accounts")) {
								   %>	
												 <s:if test="hisuserfilter==2">
										     		<div class="form-group" >
										      			<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select Department" list="issuedeptlist"  id="hisdepartmentfilter" name="hisdepartmentfilter" />
										    		</div>
										    	</s:if>
										    	
									<%
									} else {
								   %>
										 <s:if test="hisuserfilter==2">
										     		<div class="form-group" >
										      			<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id"  disabled="true" listValue="name" headerValue="Select Department" list="issuedeptlist"  id="hisdepartmentfilter" name="hisdepartmentfilter" />
										    		</div>
										    	</s:if>
										
						         <%
									}
								%>   		
											</s:else>
										   
										    
										    
										    <button type="submit" class="btn btn-primary">Go</button>
										  	<a href="#" onclick="goreferesh()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  	<a type="button"  title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
										<a type="button" class="btn btn-primary"  title="Print" onclick="printDiv('page_printer')"><i class="fa fa-print"></i></a>
										  </div>
										
										</div>
										</div>
									</s:form>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="page_printer" style="padding: 0px;">
										<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
											<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
												<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 									<link href="common/css/printpreview.css" rel="stylesheet" />
													<%@ include file="/accounts/pages/letterhead.jsp" %>
												</div>
											</div>
										</div>
										 <s:if test="isfromcathlab==1">
													<table class="table table-bordered table-striped tablestock" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<td style="width: 3%;">Sr.No</td>
					                                    	<td style="width: 10%;">Date</td>
					                                    	<td style="width: 10%;">UHID</td>
					                                    	<td style="width: 10%;">Patient Name</td>  
					                                    	<td style="width: 10%;">Procedure Name</td> 
					                                    	<td style="width: 12%;">Procedure Date</td>
					                                    	<td style="width: 7%;">Category</td>
					                                        <td style="width: 7%;">Sub-Category</td>
					                                        <td style="width: 12%;">Product Name</td>
					                                        <td style="width: 10%;">Product Code</td>
					                                        <td style="width: 7%;">MFG</td>
					                                        <!-- <td style="width: 10%;">Batch No</td> -->
					                                        <td style="width: 10%;">Expiry Date</td>
					                                        <!-- <td style="width: 10%;">UserID</td> -->
					                                        <td style="width: 6%;">Qty</td>
					                                        <td>GST</td>
					                                        <td>Rate</td>
					                                        <td>MRP</td>
					                                        <td>Unit Sale Rate</td>
					                                        <td>Total</td> 
					                                          
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                   <tfoot>
					                                   		<td></td>
					                                   		<td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                         <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td>Total</td>
					                                        <td><s:property value="total_amt"/></td>
					                                   </tfoot>
					                                  <%int i=0; %>
					                                    <s:iterator value="salesReturnProduct">
						                                    <tr>
						                                        <td><%=(++i) %></td>
						                                        <td><s:property value="dateTime"/></td>
						                                         <td><s:property value="abrivationid"/></td>
						                                        <td><s:property value="clientname"/></td>
						                                        <td><s:property value="procedurename"/></td>
						                                        <td><s:property value="given_date"/></td>
						                                         <td><s:property value="category"/></td>
						                                        <td><s:property value="subcategory"/></td>
						                                        <td><s:property value="product_name"/></td>
						                                        <td><s:property value="pro_code"/></td>
						                                        <td><s:property value="mfg"/></td>
						                                       <%--  <td><s:property value="batch_no"/></td> --%>
						                                        <td><s:property value="expiry_date"/></td>
						                                        <%-- <td><s:property value="userid"/></td> --%>
						                                        <td><s:property value="tqty"/></td>
						                                        
						                                        <td><s:property value="vat"/></td>
						                                        <td><s:property value="purchase_price"/></td>
						                                        <td><s:property value="mrp"/></td>
						                                        
						                                        <td><s:property value="sale_price"/></td>
						                                        <td><s:property value="total_amount"/></td>
						                                    </tr>
					                                    </s:iterator>	
					                                    
					                                </tbody>
					                            </table>
											</s:if>
											<s:else>
									<s:if test="hisuserfilter==0">
									<table class="table table-bordered table-striped tablestock" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<td style="width: 3%;">Sr.No</td>
					                                    	<td style="width: 10%;">Date</td>
					                                        <td style="width: 10%;">UHID</td>
					                                        <td style="width: 18%;">Patient Name</td>   
					                                        <td style="width: 5%;">OPD/IPD</td>
					                                         <td style="width: 5%;">TP</td>
					                                       <!--  <th style="width: 8%;">Mobile</th> -->
					                                        <td style="width: 12%;">Product Name</td>
					                                         <td style="width: 10%;">Batch No</td>
					                                         <td style="width: 10%;">Expiry Date</td>
					                                        <td style="width: 10%;">UserID</td>
					                                         <td style="width: 10%;">Tran.Qty</td>
					                                         <td style="width: 15%;">Unit sale rate</td>
					                                         <td style="width: 8%;">Total</td>
					                                          
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                   <tfoot>
					                                   <td></td>
					                                   		<td></td>
					                                        <td></td>
					                                        <td></td>
					                                      <!--   <td></td> -->
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td>Total</td>
					                                        <td><s:property value="total_amt"/></td>
					                                   </tfoot>
					                                  <%int i=0; %>
					                                    <s:iterator value="salesReturnProduct">
					                                    <tr>
					                                        <td><%=(++i) %></td>
					                                        <td><s:property value="dateTime"/></td>
					                                         <td><s:property value="abrivationid"/></td>
					                                        <td><s:property value="clientname"/></td>
					                                        <td><s:property value="clienttype"/></td>
					                                        <td><s:property value="tpname"/></td>
					                                        <%-- <td><s:property value="mobile"/></td> --%>
					                                        <td><s:property value="Product_name"/></td>
					                                         <td><s:property value="batch_no"/></td>
					                                          <td><s:property value="expiry_date"/></td>
					                                        <td><s:property value="userid"/></td>
					                                        <td><s:property value="tqty"/></td>
					                                        <td><s:property value="sale_price"/></td>
					                                        <td><s:property value="total_amount"/></td>
					                                    </tr>
					                                    </s:iterator>	
					                                    
					                                </tbody>
					                            </table>
									</s:if >
									<s:elseif test="hisuserfilter==1">
									<table class="table table-bordered table-striped tablestock" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<td style="width: 3%;">Sr.No</td>
					                                    	<td style="width: 10%;">Date</td>
					                                        <td style="width: 10%;">UHID</td>
					                                        <td style="width: 18%;">User Name</td>  
					                                        <td style="width: 8%;">Mobile</td>
					                                        <td style="width: 12%;">Product Name</td>
					                                         <td style="width: 10%;">Batch No</td>
					                                         <td style="width: 10%;">Expiry Date</td>
					                                         <td style="width: 10%;">UserID</td>
					                                         <td style="width: 10%;">Tran.Qty</td>
					                                         <td style="width: 15%;">Unit sale rate</td>
					                                         <td style="width: 8%;">Total</td>
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                   <tfoot>
					                                   		<td></td>
					                                   		<td></td>
					                                        <td></td>
					                                      	<td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td>Total</td>
					                                        <td><s:property value="total_amt"/></td>
					                                   </tfoot>
					                                  <%int i=0; %>
					                                    <s:iterator value="salesReturnProduct">
					                                    <tr>
					                                        <td><%=(++i) %></td>
					                                        <td><s:property value="dateTime"/></td>
					                                         <td><s:property value="abrivationid"/></td>
					                                        <td><s:property value="clientname"/></td>
					                                       	<td><s:property value="mobile"/></td>
					                                        <td><s:property value="Product_name"/></td>
					                                        <td><s:property value="batch_no"/></td>
					                                        <td><s:property value="expiry_date"/></td>
					                                        <td><s:property value="userid"/></td>
					                                        <td><s:property value="tqty"/></td>
					                                        <td><s:property value="sale_price"/></td>
					                                        <td><s:property value="total_amount"/></td>
					                                    </tr>
					                                    </s:iterator>	
					                                    
					                                </tbody>
					                            </table>
									</s:elseif>
									<s:else>
										<table class="table table-bordered table-striped tablestock" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
					                                <thead>
					                                    <tr class="tableback">
					                                    	<td style="width: 3%;">Sr.No</td>
					                                    	<td style="width: 10%;">Date</td>
					                                    	<td style="width: 10%;">UHID</td>
					                                    	<td style="width: 18%;">Department Name</td>   
					                                        <td style="width: 12%;">Product Name</td>
					                                         <td style="width: 10%;">Batch No</td>
					                                         <td style="width: 10%;">Expiry Date</td>
					                                        <td style="width: 10%;">UserID</td>
					                                         <td style="width: 10%;">Tran.Qty</td>
					                                         <td style="width: 10%;">Balance Qty</td>
					                                         <td style="width: 15%;">Unit sale rate</td>
					                                         <td style="width: 8%;">Total</td>
					                                          
					                                    </tr>
					                                </thead>
					                                <tbody>
					                                   <tfoot>
					                                   <td></td>
					                                   		<td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                        <td></td>
					                                         <td></td>
					                                          <td></td>
					                                        <td><b>Total</b></td>
					                                        <td><b><s:property value="total_amt"/></b></td>
					                                   </tfoot>
					                                  <%int i=0; %>
					                                    <s:iterator value="salesReturnProduct">
					                                    <tr>
					                                        <td><%=(++i) %></td>
					                                        <td><s:property value="dateTime"/></td>
					                                         <td><s:property value="abrivationid"/></td>
					                                        <td><s:property value="clientname"/></td>
					                                        <td><s:property value="Product_name"/></td>
					                                         <td><s:property value="batch_no"/></td>
					                                          <td><s:property value="expiry_date"/></td>
					                                        <td><s:property value="userid"/></td>
					                                        <td><s:property value="tqty"/></td>
					                                        <td><s:property value="Balanceqty"/></td>
					                                        <td><s:property value="sale_price"/></td>
					                                        <td><b><s:property value="total_amount"/></b></td>
					                                    </tr>
					                                    </s:iterator>	
					                                    
					                                </tbody>
					                            </table>
									
									</s:else>
									</s:else>
									
					                            	<s:form action="patientconsumptionreportProduct" name="paginationForm" id="paginationForm" theme="simple">
							    							<s:hidden name="fromdate"></s:hidden>
							    							<s:hidden name="todate"></s:hidden> 
							    							<s:hidden name="searchtext"></s:hidden> 
							    							<s:hidden name="hisuserfilter"></s:hidden> 
							    							<s:hidden name="hisdepartmentfilter"></s:hidden>
							    							<s:hidden name="isfromcathlab"></s:hidden>
							    							<div class="col-lg-12">
																<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
																	Total:<label class="text-info"><s:property value="totalRecords" /> Records
																</label>
																</div>
															<%@ include file="/common/pages/pagination.jsp"%>
															</div>
													</s:form>             
											</div>

<script>
function printDiv(divName) {
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}
	</script>
	
	
	
</body>
</html>