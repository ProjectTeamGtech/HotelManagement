
    <%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="s" uri="/struts-tags" %>
    <%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />

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
    </style>
    
    <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
        .addcatego {
            float: right;
            margin-top: -40px;
            margin-right: 30px;
        }
        .sort{
        width: 15%;padding-top: 2px;
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
 @media print
{
body {
    font-size: 9px !important;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 2px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 9px !important;
    border-right: none !important;
    border-left: none !important;
}

.settopbac {
    background-color: #ddd !important;
}
.totalbor {
    background-color: #f5f5f5 !important;
}

    .print_special { border: none !important; } 
    label {
    	font-size: 9px !important;
	}
	p {
	    margin: 0 0 2.5px !important;
	    font-size: 9px !important;
	}
	


.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #ccc !important;
    color: #000 !important;
    font-size: 9px !important;
}
}

.btn-warning{
	color: #fff !important;
    background-color: #477b84 !important;
    border-color: #555855 !important;
}
        
    </style>
    <SCRIPT type="text/javascript" >
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
    
    
<%--     <script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
 --%>     <script type="text/javascript" src="common/js/pagination.js"></script>
 <script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
     <%-- <script>
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
	
	
	function editviewparentprisc(id,cid,pid,conid){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		editparentpriscid = id;
		//editparentprisc(id);
		
		repeateEditPriscDateAjax(cid,pid,conid);
	}
</script> --%>
</head>
<body>
<input type="hidden" name="invstcmyModalLabel" id="invstcmyModalLabel">
<input type="hidden" name="invstdatetimeid" id="invstdatetimeid">
<input type="hidden" name="invstpriscdoctornameid" id="invstpriscdoctornameid">
<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
								<div class="row <!-- details mainheader -->">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 <img src="manasmaindashboard/images/Prescription_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
										 <h4>Prescription Dashboard</h4>
									</div>
								<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
									<img src="dashboardicon/pills-1.png" class="img-responsive prescripiconcircle">
									<img src="manasmaindashboard/images/Prescription_Icon.svg" style="filter: brightness(5);width: 21px;margin-top: 4px;margin-left: 7px;">
								</div>
								<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
								<h4>Prescription Dashboard </h4>
								</div> -->

								</div>
							</div>
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                       <div class="col-md-12">
                          <div class="row ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<a style="display: none;" href="#" onclick="opencPopup('getPatientRecordEmr')" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Create
										Prescription </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form action="Prescription" theme="simple" id="priscform" cssClass="form-inline search">
										  <div class="form-group">
										    <s:textfield name="searchText" id="searchText" cssClass="form-control" placeholder="Search Prescription"/>
										  </div>
										  <div class="form-group" >
										    <s:textfield id="fromdate" name="fromdate" cssClass="form-control" placeholder="From Date" readonly="true" />
										  </div>
										  <div class="form-group" >
										    <s:textfield id="todate" name="todate" cssClass="form-control" placeholder="To Date" readonly="true" />
										  </div>
										  
										   <div class="form-group" >
										    	<s:select  list="wardlist" listKey="id" listValue="wardname" 
                                               			cssClass="form-control chosen-select" name="phar_wardid" id="wardid" 
                                               			headerKey="0" headerValue="Select Ward"/>
										  </div> 
										  <div class="form-group" >
										    	<s:select headerKey="10" headerValue="Select Location chosen-select" cssClass="form-control" list="#{'10':'All', '0':'IPD', '1':'OPD' , '2':'OT'}" name="filter_location" />
										  </div> 
										   <div class="form-group" >
										    	 <s:select list="locationListPharmacy" cssClass="form-control chosen-select"   name="filter_phar_location"  headerKey="0" headerValue="Select Request Location" listKey="id" listValue="name" ></s:select>
										  
										  </div>
										 <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="refreshData()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  <a href="#" onclick="openPopup('InitialDischarge')"  class="btn btn-primary" title="Discharge Dashboard"><i class="fa fa-bed"></i></a>
										  
										</s:form>
										</div>
										</div>
									
										<table class="table table-bordered" width="100%">
											<thead>
												<tr class="tableback">
													<th style="width: 5%;" class="pdate manastableheader">Date</th>
													<th style="width: 8%" class="pnamewidth manastableheader">Patient Name</th>
													<th style="width: 5%;" class="page manastableheader">Age / Gender</th>
													<th style="width: 4%" class="pdate manastableheader">Ward / Bed</th>
													<th style="width: 2%" class="pnew manastableheader">User ID</th>
													<th style="width: 2%" class="manastableheader">Req. Location</th>
													<th style="width: 2%" class="manastableheader">Location</th>
													<th style="width: 2%" class="manastableheader">Type</th>
													<%if(loginInfo.isBalgopal()){ %>
														<th style="width: 2%" class="manastableheader">Print Status</th>
													<%} %>
													<%if(!loginInfo.isBalgopal()){ %>
													<!-- <th class="pnew">New</th> -->
													<th style="width: 1%" class="pnew manastableheader">Print</th>
													<%} %>
													<!-- th class="pview">Update</th> -->
													<th style="width: 8%" class="manastableheader">Note</th>
													<!-- <th style="width:8%">Add Charges</th> -->
													<!-- <th>Status</th> -->
													<th style="width: 1%" class="manastableheader">Tracking</th>
													<th style="width: 5%;" class="manastableheader">Pharmacy Status</th>
													
													<%if(loginInfo.isPrisc_deliver_return()){ %>
													<th style="width: 1%" class="manastableheader">Request Status</th>
													 <th style="width: 1%" class="manastableheader">Return</th> 
													<%} %>
													<th style="width: 1%;" class="manastableheader">Cancel</th>
												</tr>
											</thead>
											<tbody>
												<%String bgcolor = "";int index=0; %>
												<%ArrayList<Priscription> list= (ArrayList<Priscription>)request.getAttribute("priscriptionlist");%>
												<s:iterator value="priscriptionlist">
												<s:if test="isdeliverstaus==1">
													<% bgcolor = "rgba(229, 217, 129, 0.46);"; %>
												</s:if>
												<s:elseif test="isdeliverstaus==2">
													<% bgcolor = "#bbb3b3;"; %>
												</s:elseif>
												<s:elseif test="isdeliverstaus==3">
													<% bgcolor = "#c8e87a99;"; %>
												</s:elseif>
												<s:else>
													<% bgcolor = "white"; %>
												</s:else>
												
												
													<%-- <tr style="cursor: pointer;background-color: <%=bgcolor %>" id="<s:property value="id" />" class="even pointer" ondblclick="setEmrSelectedRows(<s:property value="id" />,<s:property value="clientId" />)"> --%>
													<tr style="background-color: <%=bgcolor %>" id="<s:property value="id" />"  >
													<td class=" "><s:property value="lastmodified"/></td>
														<td class=" "><s:property value="fullname" /></td>
														<td class=" "><s:property value="ageandgender" /></td>
														<td class=" ">
															<s:if test="ipdid!=0">
																<s:property value="wardname" /> / <s:property value="bedname" />
															</s:if> 
														 </td>
														 <td class=" "><s:property value="userid" /></td>
														 <td><s:property value="req_location"/></td>
														 <td><s:property value="location"/></td>
														<td><s:property value="whopay"/></td>
														<%if(loginInfo.isBalgopal()){ %>
															<td>
																<s:if test="shownewrequest==false">
																	-
																</s:if>
																<s:elseif test="prisc_print_taken==0">
																	<input type="checkbox" onclick="saveprinttakenstatus(this.checked,<s:property value="id" />)">
																</s:elseif>
																<s:elseif test="prisc_print_taken==1">
																	<input type="checkbox" readonly="readonly" checked="checked">
																</s:elseif>
															</td>
														<%} %>
														<%-- <td class=" ">&nbsp;&nbsp;<a href="#" data-toggle="modal" onclick="onAdd(<s:property value="clientId"/>,<s:property value="prectionerid"/>,<s:property value="conditionid"/>)"><i class="fa fa-plus-square"></i></a>
															</td> --%>
															<%if(!loginInfo.isBalgopal()){ %>
														<td class=" "><a href="#" data-toggle="modal" onclick="editviewparentprisc(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,<s:property value="conditionid"/>)"></a>&nbsp; <a href="#" onclick="openPopup('printpriscEmr?selectedid=<s:property value="id"/>')"><i class="fa fa-print"></i></a></td>
														<%} %>
														<%-- <td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" data-toggle="modal" onclick="editviewparentprisc(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,<s:property value="conditionid"/>)"><i class="fa fa-pencil-square-o"></i></a></td> --%>
														<td class=""><%-- <%=DateTimeUtils.isNull(list.get(index).getAdvoice().toString()) %><%index++; %> --%></td>
														
														
														
														<%-- <td>
															<s:if test="checkChargeRaised==0">
																<a href="#" onclick="showAddchargePopupPriscription(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>','<s:property value="fullname"/>','<s:property value="whopay"/>')">Add Charges</a>
															</s:if>
															<s:else>
																<s:if test="checkInvoiceCreated==0">
																	<s:form action="Accounts" id="accountchargefrm1" target="formtarget">
																		<s:hidden name="clientId" id="accountChargeClientid1"/>
																		<s:hidden name="thirdParty" id="accountchargethirdparty1"/>
																		<s:hidden name="transactionType" id="accountchargetransactionType1"/>
																		<s:hidden name="location" id="accountsLocationid1"/>
																		<s:hidden name="client" id="accountclientid1"/>
																		<s:hidden name = "payby" id ="accountpayby1"></s:hidden>
																		<a href="#" onclick="redirectToCreateCharge1(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>','<s:property value="fullname"/>','<s:property value="whopay"/>')">Create Invoice</a>
																	</s:form>
																</s:if>
																<s:else>
																	<s:form action="ProcessingAccount" id="accountpaymentfrm2" method="post" 
								              							 target="formtarget">
								              							
																		<s:hidden name="clientId" id="accountpaymentClientid2"/>
																		<s:hidden name="thirdParty" id="accountpaymentthirdparty2"/>
																		<s:hidden name="transactionType" id="accountpaymenttransactionType2"/>
																		<s:hidden name="location" id="accountspaymentLocationid2"/>
																		<s:hidden name="client" id="accountpaymentclientid2"/>
																		<s:hidden name="fromDate" id="accountspaymentfromDateid2"/>
																		<s:hidden name="toDate" id="accountspaymenttoDateid2"/>
																		<s:hidden name = "payby" id ="accountPaymentPayby2"></s:hidden>
																		<a href="#" onclick="redirectToRecordPayment2(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>','<s:property value="fullname"/>','<s:property value="whopay"/>')">Record Payment</a>
																	</s:form>
																</s:else>
															</s:else>
														</td> --%>
														<%-- <td>
															<s:if test="dstatus==0">
																<a href="dstatusPrescription?id=<s:property value="id"/>&ipdid=<s:property value="ipdid"/>&mstatus=0&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>">Not Delivered</a>
															</s:if>
															<s:else>
																<a href="dstatusPrescription?id=<s:property value="id"/>&ipdid=<s:property value="ipdid"/>&mstatus=1&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>">Delivered</a>
															</s:else>
															
														</td> --%>
														<td>
														<s:if test="ipdid==0">
															OPD Request
														</s:if>
														<s:elseif test="shownewrequest==false">
															-
														</s:elseif>
														<s:else>
															<%-- <s:if test="prisc_status==0">
																<a href="#" onclick="openPriscStatusPopup(<s:property value="id"/>)">Request</a> 
																 <a href="#" class="btn btn-warning" data-toggle="modal" onclick="openPriscStatusPopup(<s:property value="id"/>)" >New Request</a>
															</s:if> --%>
															<s:if test="deleted==0">
																<%if(loginInfo.isBalgopal()){ %>
																	<%if(loginInfo.isPrisc_new_req_access() || loginInfo.getUserType()==2){ %>
																		<a href="#" class="btn btn-warning" data-toggle="modal" onclick="openPriscStatusPopup(<s:property value="id"/>,<s:property value="count"/>)" >New Request</a>
																	<%} %>
																<%}else{ %>
																	<a href="#" class="btn btn-warning" data-toggle="modal" onclick="openPriscStatusPopup(<s:property value="id"/>,<s:property value="count"/>)" >New Request</a>
																<%} %>
																
															</s:if>
														</s:else>
														</td>
														<td>
														<s:if test="deleted==0">
															<a href="#" style="color:#a03533;"  onclick="openPriscRequestCountPopup(<s:property value="id"/>)">Request Status (<s:property value="count"/>)</a>
														</s:if>
															 
														</td>
														<%if(loginInfo.isPrisc_deliver_return()){ %>
															<s:if test="new_prisc_status==0">
																<td style="background:#ffa3a3;text-align:center;">
																	<a href="#" class="btn" style="color:#fff;" data-toggle="modal" onclick="openPriscriptionRequest(<s:property value="id"/>,0)">REQUESTED</a>
																</td>
															</s:if>
															<s:elseif test="new_prisc_status==1">
																<td style="background-color: rgba(229, 217, 129, 0.92);text-align:center;">
																	<a href="#" class="btn" style="color:#fff;" data-toggle="modal" onclick="openPriscriptionRequest(<s:property value="id"/>,1)">DELIVERED</a>
																</td>
															</s:elseif>
															<s:else>
																<td style="background:rgba(138, 109, 59, 0.83);text-align:center;">
																	<a href="#" class="btn" style="color:#fff;" data-toggle="modal" onclick="openPriscriptionRequest(<s:property value="id"/>,2)">PENDING</a>
																</td>
															</s:else>
															 <s:if test="new_prisc_status!=0">
																<td style="background-color: rgba(127, 204, 128, 0.98);">
																	<a href="#" class="btn" style="color:#fff;" data-toggle="modal" onclick="openPriscriptionReturn(<s:property value="id"/>)">RETURN</a>
																</td> 
															</s:if>
															<s:else>
																<td></td>
															</s:else>
															
														<%} %>
														<td style="text-align: center;">
														<s:if test="deliverystatus!=1">
															<s:if test="deleted==0">
																<a href="#" onclick="deletePrescription(<s:property value="id"/>)"><i class="fa fa-trash text-danger"></i></a>
															</s:if>
															<s:else>
																Cancelled
															</s:else>
															
														</s:if>
														
														</td>
													</tr>
												</s:iterator>


											</tbody>
										</table>
									

								</div>
							</div>
						</div>
						  
                       </div>
                    </div> 
                   	
				
					
                    
	</div>
	
		<s:form action="Prescription" name="paginationForm" id="paginationForm"	theme="simple">
				<div class="col-lg-12 col-md-12" style="padding:0px;margin-top:15px;">
					<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
						<s:hidden name="searchText"/>
						<s:hidden name="fromdate"></s:hidden>
						<s:hidden name="todate"></s:hidden>
						<s:hidden name="phar_wardid"></s:hidden>
						<s:hidden name="filter_location"></s:hidden>
						<s:hidden name="filter_phar_location"></s:hidden>
						Total:<label class="text-info"><s:property value="totalRecords" /></label>
					</div>
					<%-- <%@ include file="/common/pages/pagination.jsp"%> --%>
					<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
				</div>
			</s:form> 
	

<div id="priscrequestnewpopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Prescription:</h4>
      </div>
      <div class="modal-body">
      		<div class="" style="overflow: scroll; width: 100%; height: 300px;">
         		<table class="table my-table xlstable table-striped table-bordered" style="width:100%;">
          			<thead>
           				<tr>
           					<th>Sr.no</th>
            				<th>Date</th>
            				<th>Token No.</th>
            				<th>Id/Med Name</th>
            				<th>Bill No/Qty</th>
            				<th>Status</th>
            				<th>Requested Location</th>
            				<th>Userid</th>
            				<th>Print</th> 
            			</tr>
          			</thead>
          			<tbody id="priscrequestnewbody">
           
          			</tbody>
          		</table>
			</div>
			<!-- <div class="form-group">
					<label class="text-danger">Note:</label>
					<label class="text-danger">Please click on Id for check availability</label>
			</div> -->
      </div>
      <div class="modal-footer">
        
      </div>
    </div>

  </div>
</div>
	

	<div class="modal fade" id="priscstatuspopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			<s:hidden id="default_phar_location" name="default_phar_location"></s:hidden>
			<s:form action="savemedreqforpharPrescription" id="newmedreqform" theme="simple">
						<s:hidden name="searchText"/>
						<s:hidden name="fromdate"></s:hidden>
						<s:hidden name="todate"></s:hidden>
						<s:hidden name="phar_wardid"></s:hidden>
						<s:hidden name="filter_location"></s:hidden>
						<s:hidden name="filter_phar_location"></s:hidden>
						
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Prescription Pop Up</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="priscreqids" id="priscreqids">
					<input type="hidden" name="hiddenval" id="hiddenval">
					
			         <div class="form-group">
			         	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
			          		<s:select cssClass="form-control showToolTip chosen-select" headerKey="0" headerValue="Select Prescription" list="medicineList" listKey="id" listValue="genericname" id="priscstatusmedid" name="priscstatusmedid" />
			          	</div>
			          	<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
			          		<!-- <button type="button" class="btn btn-primary" onclick="">Add</button>  -->
			          		<a href="#" class="btn btn-primary" onclick="addnewpriscbynurse()">Add</a>
			          	</div>
			           	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			           		Request Pharmacy Location:
			           	</div>
			           	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			           		<s:select list="locationListPharmacy"  cssStyle="width:100%" name="default_phar_location" id="default_phar_location_new" headerKey="0" headerValue="Select Location" listKey="id" listValue="name" cssClass="form-control chosen"></s:select>
			           	</div>
			        </div>
			      	<div class="clearfix" style="height: 25px;"></div>
			        	<div class="" style="overflow: scroll; width: 100%; height: 300px;">
			         		<table class="table my-table xlstable table-striped table-bordered" style="width:100%;">
			          			<thead>
			           				<tr>
			           					<th><input type="checkbox" onclick="selectPriscCheckBoxfun(this.checked)"></th>
			            				<th>Sr.no</th>
			            				<th>Product name</th>
			            				<th>Dose</th>
			            				<th>Days</th>
			            				<th>Req. Qty</th>
			            				<th>Quantity</th>
			            			</tr>
			          			</thead>
			          			<tbody id="priscstatustbody">
			           
			          			</tbody>
			          		</table>
			        	</div>
			        	<div class="form-group">
							<label for="comment">Remark:</label>
							<textarea class="form-control" rows="3" id="priscstatusremark" name="remark" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
						</div>
						<div class="form-group">
							<label class="text-danger">Note:</label>
							<label class="text-danger">Please enter quantity for selected check box</label>
						</div>
						<div class="form-group" style="display: none" id="displaywarningdivs">
						<input type="hidden" id="rqeuestcouts">
							<label class="text-danger">Warning:</label>
							<label class="text-danger">Already one request sent in pharmacy against this request.</label>
						</div>
						
			      </div>
				  <div class="modal-footer">
				  <a href="#" class="btn btn-primary" onclick="savepriscbynurse(1)">Save & Print</a>
						<a href="#" class="btn btn-primary" onclick="savepriscbynurse(0)">Save</a>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				  </div>
				  </s:form>
			</div>
		</div>
	</div>



                 
                    
                    
<div class="modal fade" id="priscrequestpopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			<s:form action="saverequestpriscPrescription" id="newmedreqform_request" theme="simple">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Prescription Request Pop Up</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="priscreqids" id="priscreqids_request">
			      	<div class="clearfix" style="height: 25px;"></div>
			        	<div class="" style="overflow: scroll; width: 100%; height: 300px;">
			         		<table class="table my-table xlstable table-striped table-bordered" style="width:100%;">
			          			<thead>
			           				<tr>
			           					<th><input type="checkbox" onclick="selectPriscCheckBox(this.checked)"></th>
			            				<th>Sr.no</th>
			            				<th>Product name</th>
			            				<th>Dose</th>
			            				<th>Days</th>
			            				<th>Req. Qty</th>
			            			</tr>
			          			</thead>
			          			<tbody id="priscrequesttbody">
			           
			          			</tbody>
			          		</table>
			        	</div>
			        	<div class="form-group hidden">
							<label for="comment">Remark:</label>
							<textarea class="form-control" rows="3" id="priscstatusremark_request" name="remark" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
						</div>
						<div class="form-group">
							<label class="text-danger">Note:</label>
							<label class="text-danger">Please select check box of return product</label>
						</div>
			      </div>
				  <div class="modal-footer">
				  		<a href="#" style="width: 15%;float: right;" class="btn btn-primary" id="deliverbtn111" onclick="deliverPriscRequest()">Deliver</a>
						<!-- <button style="width:50%;" type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->
				  </div>
				  </s:form>
			</div>
		</div>
	</div>


<div class="modal fade" id="priscreturnpopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			<s:form action="savereturnpriscPrescription" id="newmedreqform_return" theme="simple">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Prescription Return Pop Up</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="priscreqids" id="priscreqids_return">
					<h3>Prescription Return:</h3>
			      	<div class="clearfix" style="height: 5px;"></div>
			        	<div class="" style="overflow: scroll; width: 100%; height: 300px;">
			         		<table class="table my-table xlstable table-striped table-bordered" style="width:100%;">
			          			<thead>
			           				<tr>
			           					<th><input type="checkbox" onclick="selectPriscReturnCheckBox(this.checked)"></th>
			            				<th>Sr.no</th>
			            				<th>Product name</th>
			            				<th>Dose</th>
			            				<th>Days</th>
			            				<th>Req. Qty</th>
			            				<th>Available. Qty</th>
			            				<th>Return Qty</th>
			            			</tr>
			          			</thead>
			          			<tbody id="priscreturntbody">
			           
			          			</tbody>
			          		</table>
			        	</div>
			        	<div class="form-group">
							<label for="comment">Remark:</label>
							<textarea class="form-control" rows="3" id="priscstatusremark_return" name="remark" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
						</div>
						<div class="form-group">
							<label class="text-danger">Note:</label>
							<label class="text-danger">Please select check box of return product</label>
						</div>
			      </div>
				  <div class="modal-footer">
				  		<a href="#" style="width: 15%;float: right;" class="btn btn-primary" id="deliverbtn111" onclick="returnproductrequested()">Return</a>
						 <!-- <button style="width:50%;" type="button" class="btn btn-primary" data-dismiss="modal">Close</button>  -->
				  </div>
				  </s:form>
			</div>
		</div>
	</div>


<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	


<!-- Modal -->
<div id="deletemodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="parent_id">
        	<textarea rows="3"  class="form-control" id="delete_reason" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="deletePrescription1()"  value="Ok">
      </div>
    </div>

  </div>
</div>
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
 </body>
</html>