<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
        .scroll {
            display: block;
            border: 0px solid red;
            padding: 5px;
            margin-top: 5px;
            width: 100%;
            height: 100px;
            overflow-y: scroll;
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
        
    </style>
    <SCRIPT type="text/javascript" >
    var todayDate = new Date().getDate();
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
		$("#expected_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			/* minDate : '30-12-1880', */
			changeMonth : true,
			changeYear : true,
			minDate: new Date(new Date().setDate(todayDate))
		});
		 /* document.addEventListener("contextmenu", function(e){
	    		e.preventDefault();
	    		}, false);  */
		   
	});
    
      
    	   
    	
    	/* function dhee(){
    		GoInFullscreen($(window).get(0));
    	}   
      
       
       function GoInFullscreen(element){
    	   if(element.requestFullscreen)
    			element.requestFullscreen();
    		else if(element.mozRequestFullScreen)
    			element.mozRequestFullScreen();
    		else if(element.webkitRequestFullscreen)
    			element.webkitRequestFullscreen();
    		else if(element.msRequestFullscreen)
    			element.msRequestFullscreen();
       } */
    </SCRIPT>
     <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
     <SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
     <script type="text/javascript" src="common/js/pagination.js"></script>
     
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
<!-- <body id='demo-element' oncontextmenu="return false;"> -->
<body id='demo-element' >

<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;" >
		 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
								<div class="row <!-- details mainheader -->">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<img src="manasmaindashboard/images/Indent_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
										 	<h4>Indent Dashboard</h4>
										</div>
										<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
											<img src="dashboardicon/indent_request.png" class="img-responsive prescripiconcircle">
											<img src="manasmaindashboard/images/Indent_Icon.svg" style="filter: brightness(5);width: 21px;margin-top: 8px;margin-left: 7px;">
										</div>
										<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
											<h4>Indent Dashboard </h4>
										</div> -->
									</div>
								</div>
							<div class="hidden-print">
								<ul class="breadcrumb">
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
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<!-- <input type="button" value="hhh" id='go-button' onclick="dhee()"> -->
		<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                       <div class="col-md-12">
                          <div class="form-inline">
						  	<s:form action="transferdashboardProduct" theme="simple">
						  		<div class="form-group" style="width: 8%;">
						  			<s:textfield id="searchText" name="searchtext"  cssClass="form-control" placeholder="Search by ID" />
						  		</div>
						  
						  		<div class="form-group" style="width: 7%;">
						  			<s:textfield id="fromdate" name="fromdate"  cssClass="form-control" placeholder="From Date" style="width:100%;"/>
						  		</div>
						  
						  		<div class="form-group" style="width: 7%;">
						  			<s:textfield  name="todate" id="todate"  cssClass="form-control" placeholder="To Date" style="width:100%;"/>
						  		</div>
						  		<div class="form-group">
						  			<s:if test="isindentapprove==1">
						  				<s:select headerKey="10" headerValue="Select Status" cssClass="form-control" list="#{'1':'Approved', '2':'Rejected', '3':'Delivered' , '4':'Received', '5':'PO Created', '6':'Pending','7':'Ready To Transfer','8':'Direct Transfer','9':'Return'}" name="filter_status" />
						  			</s:if>
						  			<s:else>
						  				<s:if test="isstoreuser==1">
						  					<s:select headerKey="10" headerValue="Select Status" cssClass="form-control" list="#{'2':'Rejected', '3':'Delivered' , '4':'Received', '5':'PO Created', '6':'Pending','7':'Ready To Transfer','8':'Direct Transfer','9':'Return'}" name="filter_status" />
						  				</s:if>
						  				<s:else>
						  					<s:select headerKey="10" headerValue="Select Status" cssClass="form-control" list="#{'0':'Request', '1':'Approved', '2':'Rejected', '3':'Delivered' , '4':'Received', '5':'PO Created', '6':'Pending','7':'Ready To Transfer','8':'Direct Transfer','9':'Return'}" name="filter_status" />
						  				</s:else>
						  			</s:else>
						  					
								</div>
								<div class="form-group">
									<s:select cssClass="form-control" list="#{'0':'Request Date', '1':'Approved Date', '2':'Delivered'}" name="filter_bydate" />
								</div>
							<s:if test="haveAllDeptAccess==1">	
								<div class="form-group">
	      			                <s:select list="DisciplineList" name='specialityName' id='speciality' listKey="id"  listValue="name" headerKey="" headerValue="All Department"  cssClass="form-control chosen-select" ></s:select>
      			               </div>
      			            </s:if> 
								<div class="form-group">
						  		<button type="submit" class="btn btn-primary">Go</button>
						  		<a href="#" class="btn btn-primary" onclick="openIndentPopup()">New</a>
						  		</div>
						  	<div class="form-inline" style="float: right;margin-top: 5px;text-transform: uppercase;">
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#ffa3a3"></i> Request
											    </label>
											  </div>|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgb(72, 204, 184);"></i> Approved
											    </label>
											  </div>|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgba(229, 217, 129, 0.46)"></i> Delivered
											    </label>
											  </div>|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#7fcc80"></i> Received
											    </label>
											  </div>|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgb(102, 153, 204);"></i> PO Created
											    </label>
											  </div>|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgba(0, 0, 0, 0.53)"></i> Direct Transferred
											    </label>
											  </div>
											</div>
						  	<input type="button" onclick="printReport()" value="Download Excel" class="btn btn-warning hidden" />
						   </s:form>
						   </div>
						  
                       </div>
                    </div> 
                   	<s:if test="isstoreuser==1">
                   		<s:if test="aproveparenttransferlist.size!=0">
                   		<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Approved Indents :-</h5>
                   		<s:if test="warehoseshelfrequest==1">
                   			<s:if test="requestparenttransferlist.size!=0">
                   				<div class="depart1" style="overflow: scroll; width: 100%; height: 175px;">	
                   			</s:if>
                   			<s:else>
                   				<div class="depart1" style="overflow: scroll; width: 100%; height: 275px;">
                   			</s:else>
                   		</s:if>
                   		<s:else>
                   			<div class="depart1" style="overflow: scroll; width: 100%; height: 275px;">
                   		</s:else>
						  
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr>
                                	<th style="width: 1%;" class="manastableheader">SR.</th>
                                	<th style="width: 3%;" class="manastableheader">ID</th>
                                	<th style="width: 9%;" class="manastableheader">Request Date</th>
                                    <th style="width: 13%;" class="manastableheader">Requested By</th>
                                    <th style="width: 8%;" class="manastableheader">Req. Dept</th>
                                   	<th style="text-align: center;width: 7%;"class="manastableheader">Req/Tran</th>
                                	<th style="width: 10%;" class="manastableheader">Transfer To</th>
                                	<th style="text-align: center;width: 8%;" class="manastableheader">Expected By</th>
                                	<th style="text-align: center;width: 10%;" class="manastableheader">Admin A/R Date</th>
                                	<th style="text-align: center;width: 10%;" class="manastableheader">Requester A/R Date</th>
                                	<th style="text-align: center;width: 7%;" class="hidden">Status</th>
                                	<th style="text-align:center;width: 8%;" class="manastableheader">Action</th>
                                	<th style="text-align:center;width: 1%;" class="manastableheader">Print</th>
                                	<th style="text-align:center;width: 1%;" class="manastableheader">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%int aprovecount=0; %>
                              <s:iterator value="aproveparenttransferlist">
              					  <s:if test="status==6">
                              <tr style="background-color:red">
                              </s:if>
                               <s:elseif test="status==7">
                              <tr style="background-color: #ffff99">
                              </s:elseif>
                              <s:else>
                               <tr>
                              </s:else>
                              		<td><%=(++aprovecount) %></td>
              						<td><s:property value="parentid"/><s:hidden id="parentid" name="parentid"></s:hidden></td>
              						<td><s:property value="request_date"/></td>
              						<td style="color: chocolate;"><s:property value="from_location"/> / <s:property value="userid"/></td>
              						<td><s:property value="warehouse_name"/></td>
              					
              						<td style="text-align: center;">
              					   	<s:if test="req_or_transfer==0">
              					   		REQUEST
              					   	</s:if>
              					   	<s:elseif test="req_or_transfer==1">
              					   		DIRECT
              					   	</s:elseif>
              					   	<s:else>
              					   		RETURN
              					   	</s:else>
              					   </td>
              					   <td style="color: mediumblue;">
              					   		<s:if test="req_or_transfer==0">
              					   			<s:property value="from_location"/>
              					   		</s:if>
              					   		<s:else>
              					   			<s:property value="to_location"/>
              					   		</s:else>
              					   </td>
              					   <td style="text-align: center;" ><s:property value="expectedDate"/></td>
              					   <td style="text-align: center;"><s:property value="admin_aprove_date"/>
              					   		<s:if test="admin_approve_userid!=''">
              					   			(<s:property value="admin_approve_userid"/>)
              					   		</s:if>
              					   	
              					   </td>
              					   <td class="hidden"><s:property value="admin_notes"/></td>
              					   <td style="text-align: center;"><s:property value="head_aprove_date"/></td>
              					   <td class="hidden"><s:property value="head_notes"/></td>
              					   <td style="text-align: center;" class="hidden">
              					   		<s:if test="status==0">
              					   			PENDING
              					   		</s:if>
              					   		<s:elseif test="status==1">
              					   			APPROVED</s:elseif>
              					   		<s:elseif test="status==2">
              					   			REJECTED
              					   		</s:elseif>
              					   		<s:elseif test="status==3">
              					   			DELIVERED
              					   		</s:elseif>
              					   		<s:elseif test="status==4">
              					   			RECEIVED
              					   		</s:elseif>
              					   </td>
              					   <s:if test="req_or_transfer==1">
              								<td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="deliverPrintDirectProduct?id=<s:property value="parentid" />" style="color:#fff;" >Transferred</a></td> 
              					   	</s:if>
              					    <s:elseif test="req_or_transfer==2">
              								<td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="deliverPrintDirectProduct?id=<s:property value="parentid" />" style="color:#fff;" >RETURN TO STORE</a></td>
              					    		
              					    </s:elseif>
              					    <s:else>
              					    	<s:if test="status==0">
              					    		<%if(loginInfo.isLmh() || loginInfo.isKalmegha()){ %>
	              					    		<s:if test="modify_status==0">
	              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" onclick="modifyIndentRequest(<s:property value="parentid"/>)">Confirm</a></td>
	              					    		</s:if>
	              					    		<s:else>
	              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td>
	              					    		</s:else>
              					    		<%}else{ %>
              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td>
              					    		<%} %>
              					    	</s:if>
              					    	<s:elseif test="status==1">
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;">Approved</a></td>
              					   			<%}else{ %>
              					   				<s:if test="deilverproduct==1">
              					   					<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;" >Approved</a></td>
              					   				</s:if>
              					   				<s:else>
              					   					<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><span style="color:#fff;">Approved</span></td>
              					   				</s:else>
	              					   			<%-- <s:if test="curr_location==1">
	              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;" >Approved</a></td>
	              					   			</s:if>
	              					   			<s:else> 
	              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;" >Approved</a></td>
	              					   			</s:else> --%>
              					   			<%} %>
              					   		</s:elseif>
              					   		<s:elseif test="status==2">
              					   			<td style="text-align:center;background-color: rgba(255, 0, 0, 0.51);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,2)">Rejected</a></td>
              					   		</s:elseif>
              					   		
              					   		<s:elseif test="status==3">
              					   			<td style="text-align:center;background-color: rgba(229, 217, 129, 0.92);"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1" style="color:#fff;" >Delivered</a></td>
              					   		</s:elseif>
              					   		
              					   		<s:elseif test="status==4">
              					   			<td style="text-align:center;background-color: rgba(127, 204, 128, 0.98);"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=2" style="color:#fff;" >Received</a></td>
              					   		</s:elseif>
              					   		<s:elseif test="status==5"> 
              					   			<td style="text-align:center;background-color: rgb(102, 153, 204);"><span style="color:#fff;">PO CREATED</span></td>
              					   		</s:elseif>
 										<s:elseif test="status==6"> 
              					   			
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Pending</a></td>
              					   			<%}else{ %>
              					   			<s:if test="curr_location==1">
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Pending</a></td> 
              					   				
              					   			</s:if>
              					   			<s:else> 
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4"  style="color:#fff;" >Pending</a></td>
              					   			</s:else>
              					   			<%} %>
              					   		</s:elseif> 
              					   		
              					   		<s:elseif test="status==7"> 
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Ready To Transfer</a></td>
              					   			<%}else{ %>
              					   				<s:if test="curr_location==1">
              					   					<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;">Ready To Transfer</a></td>
              					   				</s:if>
              					   				<s:else> 
              					   					<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4"  style="color:#fff;" >Ready To Transfer</a></td> 
              					   					
              					   				</s:else>
              					   			<%} %>
              					   		</s:elseif>              					
              					   	</s:else>
              					    	<s:if test="req_or_transfer==1">
              								<td></td>		 
              					   		</s:if>
              					    	<s:elseif test="req_or_transfer==2">
              								<td></td>	
              					    	</s:elseif>
              							<s:elseif test="status==6"> 
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=0" ><i class="fa fa-print"></i></a></td> 
              					   		</s:elseif> 
              					   		<s:elseif test="status==7">
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=0"><i class="fa fa-print"></i></a></td>
              					   		</s:elseif> 
              					   		<s:elseif test="status==3">
              					   		<td></td>
              					   		</s:elseif> 
              					   		<s:else>
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=1" ><i class="fa fa-print"></i></a></td>
              					   		</s:else>
              							<td style="text-align:center;">
              							<s:if test="status==0">
              								<%if(loginInfo.getUserType()==2){%>
              									<a href="#" onclick="deleteIndent(<s:property value="parentid"/>)"><i class="fa fa-trash text-danger"></i></a>
              								<%}else{ %>
              									<s:if test="cancel_indent==1">
              										<a href="#" onclick="deleteIndent(<s:property value="parentid"/>)"><i class="fa fa-trash text-danger"></i></a>
              									</s:if>
              								<%} %>
              							</s:if>
              							
              						</td>
              					</tr>
              				</s:iterator>
                            </tbody>
                        </table>
                    </div>
                    </s:if>	
				</s:if>
				<s:if test="warehoseshelfrequest==1">
					<%int requestcount=0; %>
					<s:if test="requestparenttransferlist.size!=0">
					<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Self Requested Indents :-</h5>
						  <div class="depart1" style="overflow: scroll; width: 100%; height: 100px;">
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr>
                                	<th style="width: 1%;" class="manastableheader">SR.</th>
                                	<th style="width: 3%;" class="manastableheader">ID</th>
                                	<th style="width: 9%;" class="manastableheader">Request Date</th>
                                    <th style="width: 13%;" class="manastableheader">Requested By</th>
                                    <th style="width: 8%;" class="manastableheader">Req. Dept</th>
                                   	<th style="text-align: center;width: 7%;" class="manastableheader">Req/Tran</th>
                                	<th style="width: 10%;" class="manastableheader">Transfer To</th>
                                	<th style="text-align: center;width: 8%;" class="manastableheader">Expected By</th>
                                	<th style="text-align: center;width: 10%;" class="manastableheader">Admin A/R Date</th>
                                	<th style="text-align: center;width: 10%;" class="manastableheader">Requester A/R Date</th>
                                	<th style="text-align: center;width: 7%;"class="hidden">Status</th>
                                	<th style="text-align:center;width: 8%;" class="manastableheader">Action</th>
                                	<th style="text-align:center;width: 1%;" class="manastableheader">Print</th>
                                	<th style="text-align:center;width: 1%;" class="manastableheader">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                              <s:iterator value="requestparenttransferlist">
                              <s:if test="status==6">
                               <tr style="background-color:red">
                              </s:if>
                               <s:elseif test="status==7">
                              <tr style="background-color: #ffff99">
                              </s:elseif>
                              <s:else>
                              <tr>
                              </s:else>
              						<td><%=(++requestcount) %></td>
              						<td><s:property value="parentid"/><s:hidden id="parentid" name="parentid"></s:hidden></td>
              						<td><s:property value="request_date"/></td>
              						<td style="color: chocolate;"><s:property value="from_location"/> / <s:property value="userid"/></td>
              						<td><s:property value="warehouse_name"/></td>
              					
              						<td style="text-align: center;">
              					   	<s:if test="req_or_transfer==0">
              					   		REQUEST
              					   	</s:if>
              					   	<s:elseif test="req_or_transfer==1">
              					   		DIRECT
              					   	</s:elseif>
              					   	<s:else>
              					   		RETURN
              					   	</s:else>
              					   </td>
              					   <td style="color: mediumblue;">
              					   		<s:if test="req_or_transfer==0">
              					   			<s:property value="from_location"/>
              					   		</s:if>
              					   		<s:else>
              					   			<s:property value="to_location"/>
              					   		</s:else>
              					   </td>
              					   <td style="text-align: center;" ><s:property value="expectedDate"/></td>
              					   <td style="text-align: center;"><s:property value="admin_aprove_date"/>
              					   		<s:if test="admin_approve_userid!=''">
              					   			(<s:property value="admin_approve_userid"/>)
              					   		</s:if>
              					   </td>
              					   <td class="hidden"><s:property value="admin_notes"/></td>
              					   <td style="text-align: center;"><s:property value="head_aprove_date"/></td>
              					   <td class="hidden"><s:property value="head_notes"/></td>
              					   <td style="text-align: center;" class="hidden">
              					   		<s:if test="status==0">
              					   			PENDING
              					   		</s:if>
              					   		<s:elseif test="status==1">
              					   			APPROVED</s:elseif>
              					   		<s:elseif test="status==2">
              					   			REJECTED
              					   		</s:elseif>
              					   		<s:elseif test="status==3">
              					   			DELIVERED
              					   		</s:elseif>
              					   		<s:elseif test="status==4">
              					   			RECEIVED
              					   		</s:elseif>
              					   </td>
              					   <s:if test="req_or_transfer==1">
              								<td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="deliverPrintDirectProduct?id=<s:property value="parentid" />" style="color:#fff;" >Transferred</a></td> 
              					   	</s:if>
              					    <s:elseif test="req_or_transfer==2">
              								<td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="deliverPrintDirectProduct?id=<s:property value="parentid" />" style="color:#fff;" >RETURN</a></td>
              					    		
              					    </s:elseif>
              					    <s:else>
              					    	<s:if test="status==0">
              					    		<%-- <td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td> --%>
              					    		<%if(loginInfo.isLmh() || loginInfo.isKalmegha()){ %>
	              					    		<s:if test="modify_status==0">
	              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" onclick="modifyIndentRequest(<s:property value="parentid"/>)">Confirm</a></td>
	              					    		</s:if>
	              					    		<s:else>
	              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td>
	              					    		</s:else>
              					    		<%}else{ %>
              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td>
              					    		<%} %>
              					    	</s:if>
              					    	<s:elseif test="status==1">
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="#" style="color:#fff;">Approved</a></td>
              					   			<%}else{ %>
              					   			<s:if test="curr_location==1">
              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="#" style="color:#fff;" >Approved</a></td>
              					   			</s:if>
              					   			<s:else> 
              					   			<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="#" style="color:#fff;" >Approved</a></td>
              					   			</s:else>
              					   			<%} %>
              					   		</s:elseif>
              					   		<s:elseif test="status==2">
              					   			<td style="text-align:center;background-color: rgba(255, 0, 0, 0.51);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,2)">Rejected</a></td>
              					   		</s:elseif>
              					   		
              					   		<s:elseif test="status==3">
              					   			<td style="text-align:center;background-color: rgba(229, 217, 129, 0.92);"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1" style="color:#fff;" >Delivered</a></td>
              					   		</s:elseif>
              					   		
              					   		<s:elseif test="status==4">
              					   			<td style="text-align:center;background-color: rgba(127, 204, 128, 0.98);"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=2" style="color:#fff;" >Received</a></td>
              					   		</s:elseif>
              					   		<s:elseif test="status==5"> 
              					   			<td style="text-align:center;background-color: rgb(102, 153, 204);"><span style="color:#fff;">PO CREATED</span></td>
              					   		</s:elseif>
 										<s:elseif test="status==6"> 
              					   			
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Pending</a></td>
              					   			<%}else{ %>
              					   			<s:if test="curr_location==1">
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Pending</a></td> 
              					   				
              					   			</s:if>
              					   			<s:else> 
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4"  style="color:#fff;" >Pending</a></td>
              					   			</s:else>
              					   			<%} %>
              					   		</s:elseif> 
              					   		
              					   		<s:elseif test="status==7"> 
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Ready To Transfer</a></td>
              					   			<%}else{ %>
              					   				<s:if test="curr_location==1">
              					   					<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;">Ready To Transfer</a></td>
              					   				</s:if>
              					   				<s:else> 
              					   					<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4"  style="color:#fff;" >Ready To Transfer</a></td> 
              					   					
              					   				</s:else>
              					   			<%} %>
              					   		</s:elseif>              					
              					   	</s:else>
              					    	<s:if test="req_or_transfer==1">
              								<td></td>		 
              					   		</s:if>
              					    	<s:elseif test="req_or_transfer==2">
              								<td></td>	
              					    	</s:elseif>
              							<s:elseif test="status==6"> 
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=0" ><i class="fa fa-print"></i></a></td> 
              					   		</s:elseif> 
              					   		<s:elseif test="status==7">
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=0" ><i class="fa fa-print"></i></a></td>
              					   		</s:elseif> 
              					   		<s:elseif test="status==3">
              					   		<td></td>
              					   		</s:elseif> 
              					   		<s:else>
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=1" ><i class="fa fa-print"></i></a></td>
              					   		</s:else>
              							<td style="text-align:center;">
              							<s:if test="status==0">
              								<%if(loginInfo.getUserType()==2){%>
              									<a href="#" onclick="deleteIndent(<s:property value="parentid"/>)"><i class="fa fa-trash text-danger"></i></a>
              								<%}else{ %>
              									<s:if test="cancel_indent==1">
              										<a href="#" onclick="deleteIndent(<s:property value="parentid"/>)"><i class="fa fa-trash text-danger"></i></a>
              									</s:if>
              								<%} %>
              							</s:if>
              							
              						</td>
              					</tr>
              				</s:iterator>
                            </tbody>
                        </table>
                    </div>
                    </s:if>
				</s:if>
				<s:if test="isindentapprove==1">
				<%int requestcount=0; %>
					<s:if test="requestparenttransferlist.size!=0">
					<h5 style="color: chocolate;text-transform: uppercase;margin: 0px 0px 3px 0px;">Requested Indents :-</h5>
						  <div class="depart1" style="overflow: scroll; width: 100%; height: 275px;">
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr>
                                	<th style="width: 1%;" class="manastableheader">SR.</th>
                                	<th style="width: 3%;" class="manastableheader">ID</th>
                                	<th style="width: 9%;" class="manastableheader">Request Date</th>
                                    <th style="width: 13%;" class="manastableheader">Requested By</th>
                                    <th style="width: 8%;" class="manastableheader">Req. Dept</th>
                                   	<th style="text-align: center;width: 7%;" class="manastableheader">Req/Tran</th>
                                	<th style="width: 10%;" class="manastableheader">Transfer To</th>
                                	<th style="text-align: center;width: 8%;" class="manastableheader">Expected By</th>
                                	<th style="text-align: center;width: 10%;" class="manastableheader">Admin A/R Date</th>
                                	<th style="text-align: center;width: 10%;" class="manastableheader">Requester A/R Date</th>
                                	<th style="text-align: center;width: 7%;"class="hidden">Status</th>
                                	<th style="text-align:center;width: 8%;" class="manastableheader">Action</th>
                                	<th style="text-align:center;width: 1%;" class="manastableheader">Print</th>
                                	<th style="text-align:center;width: 1%;" class="manastableheader">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                              <s:iterator value="requestparenttransferlist">
                              <s:if test="status==6">
                               <tr style="background-color:red">
                              </s:if>
                               <s:elseif test="status==7">
                              <tr style="background-color: #ffff99">
                              </s:elseif>
                              <s:else>
                              <tr>
                              </s:else>
              						<td><%=(++requestcount) %></td>
              						<td><s:property value="parentid"/><s:hidden id="parentid" name="parentid"></s:hidden></td>
              						<td><s:property value="request_date"/></td>
              						<td style="color: chocolate;"><s:property value="from_location"/> / <s:property value="userid"/></td>
              						<td><s:property value="warehouse_name"/></td>
              					
              						<td style="text-align: center;">
              					   	<s:if test="req_or_transfer==0">
              					   		REQUEST
              					   	</s:if>
              					   	<s:elseif test="req_or_transfer==1">
              					   		DIRECT
              					   	</s:elseif>
              					   	<s:else>
              					   		RETURN
              					   	</s:else>
              					   </td>
              					   <td style="color: mediumblue;">
              					   		<s:if test="req_or_transfer==0">
              					   			<s:property value="from_location"/>
              					   		</s:if>
              					   		<s:else>
              					   			<s:property value="to_location"/>
              					   		</s:else>
              					   </td>
              					   <td style="text-align: center;" ><s:property value="expectedDate"/></td>
              					   <td style="text-align: center;"><s:property value="admin_aprove_date"/>
              					   		<s:if test="admin_approve_userid!=''">
              					   			(<s:property value="admin_approve_userid"/>)
              					   		</s:if>
              					   </td>
              					   <td class="hidden"><s:property value="admin_notes"/></td>
              					   <td style="text-align: center;"><s:property value="head_aprove_date"/></td>
              					   <td class="hidden"><s:property value="head_notes"/></td>
              					   <td style="text-align: center;" class="hidden">
              					   		<s:if test="status==0">
              					   			PENDING
              					   		</s:if>
              					   		<s:elseif test="status==1">
              					   			APPROVED</s:elseif>
              					   		<s:elseif test="status==2">
              					   			REJECTED
              					   		</s:elseif>
              					   		<s:elseif test="status==3">
              					   			DELIVERED
              					   		</s:elseif>
              					   		<s:elseif test="status==4">
              					   			RECEIVED
              					   		</s:elseif>
              					   </td>
              					   <s:if test="req_or_transfer==1">
              								<td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="deliverPrintDirectProduct?id=<s:property value="parentid" />" style="color:#fff;" >Transferred</a></td> 
              					   	</s:if>
              					    <s:elseif test="req_or_transfer==2">
              								<td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="deliverPrintDirectProduct?id=<s:property value="parentid" />" style="color:#fff;" >RETURN</a></td>
              					    		
              					    </s:elseif>
              					    <s:else>
              					    	<s:if test="status==0">
              					    		<%-- <td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td> --%>
              					    		<%if(loginInfo.isLmh() || loginInfo.isKalmegha()){ %>
	              					    		<s:if test="modify_status==0">
	              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" onclick="modifyIndentRequest(<s:property value="parentid"/>)">Confirm</a></td>
	              					    		</s:if>
	              					    		<s:else>
	              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td>
	              					    		</s:else>
              					    		<%}else{ %>
              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td>
              					    		<%} %>
              					    	</s:if>
              					    	<s:elseif test="status==1">
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;">Approved</a></td>
              					   			<%}else{ %>
              					   			<s:if test="curr_location==1">
              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;" >Approved</a></td>
              					   			</s:if>
              					   			<s:else> 
              					   			<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;" >Approved</a></td>
              					   			</s:else>
              					   			<%} %>
              					   		</s:elseif>
              					   		<s:elseif test="status==2">
              					   			<td style="text-align:center;background-color: rgba(255, 0, 0, 0.51);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,2)">Rejected</a></td>
              					   		</s:elseif>
              					   		
              					   		<s:elseif test="status==3">
              					   			<td style="text-align:center;background-color: rgba(229, 217, 129, 0.92);"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1" style="color:#fff;" >Delivered</a></td>
              					   		</s:elseif>
              					   		
              					   		<s:elseif test="status==4">
              					   			<td style="text-align:center;background-color: rgba(127, 204, 128, 0.98);"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=2" style="color:#fff;" >Received</a></td>
              					   		</s:elseif>
              					   		<s:elseif test="status==5"> 
              					   			<td style="text-align:center;background-color: rgb(102, 153, 204);"><span style="color:#fff;">PO CREATED</span></td>
              					   		</s:elseif>
 										<s:elseif test="status==6"> 
              					   			
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Pending</a></td>
              					   			<%}else{ %>
              					   			<s:if test="curr_location==1">
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Pending</a></td> 
              					   				
              					   			</s:if>
              					   			<s:else> 
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4"  style="color:#fff;" >Pending</a></td>
              					   			</s:else>
              					   			<%} %>
              					   		</s:elseif> 
              					   		
              					   		<s:elseif test="status==7"> 
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Ready To Transfer</a></td>
              					   			<%}else{ %>
              					   				<s:if test="curr_location==1">
              					   					<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;">Ready To Transfer</a></td>
              					   				</s:if>
              					   				<s:else> 
              					   					<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4"  style="color:#fff;" >Ready To Transfer</a></td> 
              					   					
              					   				</s:else>
              					   			<%} %>
              					   		</s:elseif>              					
              					   	</s:else>
              					    	<s:if test="req_or_transfer==1">
              								<td></td>		 
              					   		</s:if>
              					    	<s:elseif test="req_or_transfer==2">
              								<td></td>	
              					    	</s:elseif>
              							<s:elseif test="status==6"> 
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=0" ><i class="fa fa-print"></i></a></td> 
              					   		</s:elseif> 
              					   		<s:elseif test="status==7">
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=0" ><i class="fa fa-print"></i></a></td>
              					   		</s:elseif> 
              					   		<s:elseif test="status==3">
              					   		<td></td>
              					   		</s:elseif> 
              					   		<s:else>
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=1" ><i class="fa fa-print"></i></a></td>
              					   		</s:else>
              							<td style="text-align:center;">
              							<s:if test="status==0">
              								<%if(loginInfo.getUserType()==2){%>
              									<a href="#" onclick="deleteIndent(<s:property value="parentid"/>)"><i class="fa fa-trash text-danger"></i></a>
              								<%}else{ %>
              									<s:if test="cancel_indent==1">
              										<a href="#" onclick="deleteIndent(<s:property value="parentid"/>)"><i class="fa fa-trash text-danger"></i></a>
              									</s:if>
              								<%} %>
              							</s:if>
              							
              						</td>
              					</tr>
              				</s:iterator>
                            </tbody>
                        </table>
                    </div>
                    </s:if>
					</s:if>	  				
					
                    <div class="">
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr>
                                	<th style="width: 1%;" class="manastableheader">SR.</th>
                                	<th style="width: 3%;" class="manastableheader">ID</th>
                                	<th style="width: 9%;" class="manastableheader">Request Date</th>
                                    <th style="width: 13%;" class="manastableheader">Requested By</th>
                                    <th style="width: 8%;" class="manastableheader">Req. Dept</th>
                                   	<th style="text-align: center;width: 7%;" class="manastableheader">Req/Tran</th>
                                	<th style="width: 10%;" class="manastableheader">Transfer To</th>
                                	<th style="text-align: center;width: 8%;" class="manastableheader">Expected By</th>
                                	<th style="text-align: center;width: 10%;" class="manastableheader">Admin A/R Date</th>
                                	<th style="text-align: center;width: 10%;" class="manastableheader">Requester A/R Date</th>
                                	<th style="text-align: center;width: 7%;" class="hidden">Status</th>
                                	<th style="text-align:center;width: 8%;" class="manastableheader">Action</th>
                                	<th style="text-align:center;width: 1%;" class="manastableheader">Print</th>
                                	<th style="text-align:center;width: 1%;" class="manastableheader">Delete</th>
                                </tr>
                            </thead>
                           
                            <tbody>
                            	<%int transfercount=0; %>
                              <s:iterator value="parenttransferlist">
              					 
                              <s:if test="status==6">
                               <tr style="background-color:cyan">
                              </s:if>
                              <s:elseif test="status==7">
                              <tr style="background-color: #ffff99">
                              </s:elseif>
                              <s:else>
                              <tr>
                              </s:else>
                              		<td><%=(++transfercount) %></td>
              						<td><s:property value="parentid"/><s:hidden id="parentid" name="parentid"></s:hidden></td>
              						<td><s:property value="request_date"/></td>
              						<td style="color: chocolate;"><s:property value="from_location"/> / <s:property value="userid"/></td>
              						<td><s:property value="warehouse_name"/></td>
              						<!--<td style="color: mediumblue;"><s:property value="to_location"/></td>
              						--><!--<td style="text-align:center;"><a href="#" data-toggle="modal" data-target="#cart">View Details</a></td>
              					-->
              						<td style="text-align: center;">
              					   	<s:if test="req_or_transfer==0">
              					   		REQUEST
              					   	</s:if>
              					   	<s:elseif test="req_or_transfer==1">
              					   		DIRECT
              					   	</s:elseif>
              					   	<s:else>
              					   		RETURN
              					   	</s:else>
              					   </td>
              					   <td style="color: mediumblue;">
              					   		<s:if test="req_or_transfer==0">
              					   			<s:property value="from_location"/>
              					   		</s:if>
              					   		<s:else>
              					   			<s:property value="to_location"/>
              					   		</s:else>
              					   </td>
              					   <td style="text-align: center;" ><s:property value="expectedDate"/></td>
              					   <td style="text-align: center;"><s:property value="admin_aprove_date"/>
              					  		<s:if test="admin_approve_userid!=''">
              					   			(<s:property value="admin_approve_userid"/>)
              					   		</s:if>
              					  	</td>
              					   <td class="hidden"><s:property value="admin_notes"/></td>
              					   <td style="text-align: center;"><s:property value="head_aprove_date"/></td>
              					   <td class="hidden"><s:property value="head_notes"/></td>
              					   <td style="text-align: center;" class="hidden">
              					   		<s:if test="status==0">
              					   			PENDING
              					   		</s:if>
              					   		<s:elseif test="status==1">
              					   			APPROVED</s:elseif>
              					   		<s:elseif test="status==2">
              					   			REJECTED
              					   		</s:elseif>
              					   		<s:elseif test="status==3">
              					   			DELIVERED
              					   		</s:elseif>
              					   		<s:elseif test="status==4">
              					   			RECEIVED
              					   		</s:elseif>
              					   </td>
              					   <s:if test="req_or_transfer==1">
              								<td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="deliverPrintDirectProduct?id=<s:property value="parentid" />" style="color:#fff;" >Transferred</a></td> 
              					   	</s:if>
              					    <s:elseif test="req_or_transfer==2">
              							<%-- <td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showTransferPopup(<s:property value="parentid"/>)">Transfered</a></td> --%> 
              					    		<td style="text-align:center;background-color: rgba(0, 0, 0, 0.45);"><a href="deliverPrintDirectProduct?id=<s:property value="parentid" />" style="color:#fff;" >RETURN</a></td>
              					    		
              					    </s:elseif>
              					    <s:else>
              					    	<s:if test="status==0">
              					    		<%-- <td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td> --%>
              					    		<%if(loginInfo.isLmh() || loginInfo.isKalmegha()){ %>
	              					    		<s:if test="modify_status==0">
	              					    			<%-- <td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Modify</a></td> --%>
	              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" onclick="modifyIndentRequest(<s:property value="parentid"/>)">Confirm</a></td>
	              					    		</s:if>
	              					    		<s:else>
	              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td>
	              					    		</s:else>
              					    		<%}else{ %>
              					    			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,0)">Request</a></td>
              					    		<%} %>
              					    	</s:if>
              					    	<s:elseif test="status==1">
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<%-- <td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="#" style="color:#fff;" onclick="openPopup('checkmedicineavabilityProduct?id=<s:property value="parentid" />')">Approved</a></td> --%>
              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;">Approved</a></td>
              					   			<%}else{ %>
              					   			<s:if test="curr_location==1">
              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;" >Approved</a></td>
              					   			</s:if>
              					   			<s:else> 
              					   				<%-- <td style="text-align:center;"><a href="#" data-toggle="modal" onclick="showRequestMedicine(<s:property value="parentid"/>,1)">Approved</a></td> --%>
              					   				<%-- <td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="#" style="color:#fff;" onclick="openPopup('checkmedicineavabilityProduct?id=<s:property value="parentid" />')">Approved</a></td> --%>
              					   				<td style="text-align:center;background-color: rgba(72, 204, 184, 0.75);"><a href="checkmedicineavabilityProduct?id=<s:property value="parentid" />" style="color:#fff;" >Approved</a></td>
              					   			</s:else>
              					   			<%} %>
              					   		</s:elseif>
              					   		<s:elseif test="status==2">
              					   			<td style="text-align:center;background-color: rgba(255, 0, 0, 0.51);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,2)">Rejected</a></td>
              					   		</s:elseif>
              					   		
              					   		<s:elseif test="status==3">
              					   			<!--<td style="text-align:center;"><a href="#" data-toggle="modal" style="background-color: darkslategrey;color: #fff;padding: 5px 5px 5px 5px;line-height: 30px;" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,3)">View Details</a></td>-->
              					   			<!--<td style="text-align:center;background-color: rgba(229, 217, 129, 0.46);"><a href="#" data-toggle="modal" onclick="lastRequestedPopup(<s:property value="parentid"/>,1)">Delivered</a></td>-->
              					   			<!-- code here -->
              					   			<%-- <td style="text-align:center;background-color: rgba(229, 217, 129, 0.92);"><a href="#" style="color:#fff;" onclick="openPopup('deliverPrintProduct?id=<s:property value="parentid" />&status=1')">Delivered</a></td> --%> 
              					   			<td style="text-align:center;background-color: rgba(229, 217, 129, 0.92);"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1" style="color:#fff;" >Delivered</a></td>
              					   		</s:elseif>
              					   		
              					   		<s:elseif test="status==4">
              					   			<!--<td style="text-align:center;"><a href="#" data-toggle="modal" style="background-color: darkslategrey;color: #fff;padding: 5px 5px 5px 5px;line-height: 30px;" onclick="showRequestPopupCheckAvaibility(<s:property value="parentid"/>,4)">View Details</a></td>-->
              					   			<!--<td style="text-align:center;background-color: rgba(127, 204, 128, 0.4);"><a href="#" data-toggle="modal" onclick="lastRequestedPopup(<s:property value="parentid"/>,2)">Received</a></td>-->
              					   			<%-- <td style="text-align:center;background-color: rgba(127, 204, 128, 0.98);"><a href="#" style="color:#fff;" onclick="openPopup('deliverPrintProduct?id=<s:property value="parentid" />&status=2')">Received</a></td> --%>
              					   			<td style="text-align:center;background-color: rgba(127, 204, 128, 0.98);"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=2" style="color:#fff;" >Received</a></td>
              					   		</s:elseif>
              					   		<s:elseif test="status==5"> 
              					   			<!--<td style="text-align:center;background-color: rgba(72, 204, 184, 0.35);"><a href="#" onclick="openPopup('deliverPrintProduct?id=<s:property value="parentid" />&status=3')">PO CREATED</a></td>
              					   		-->
              					   			<td style="text-align:center;background-color: rgb(102, 153, 204);"><span style="color:#fff;">PO CREATED</span></td>
              					   		</s:elseif>
 										<s:elseif test="status==6"> 
              					   			<%-- <td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="#" style="color:#fff;" onclick="openPopup('deliverPrintProduct?id=<s:property value="parentid" />&status=4')">Pending</a></td> --%>
              					   			
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				<%-- <td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="#" style="color:#fff;" onclick="openPopup('checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4')">Pending</a></td> --%> 
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Pending</a></td>
              					   			<%}else{ %>
              					   			<s:if test="curr_location==1">
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Pending</a></td> 
              					   				
              					   			</s:if>
              					   			<s:else> 
              					   				<%-- <td style="text-align:center;"><a href="#" data-toggle="modal" onclick="showRequestMedicine(<s:property value="parentid"/>,1)">Approved</a></td> --%>
              					   				<%-- <td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="#"  style="color:#fff;" onclick="openPopup('checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4')">Pending</a></td> --%>
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4"  style="color:#fff;" >Pending</a></td>
              					   			</s:else>
              					   			<%} %>
              					   		</s:elseif> 
              					   		
              					   		<s:elseif test="status==7"> 
              					   			<%if(loginInfo.getUserType()==2){ %>
              					   				 <%-- <td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="#" style="color:#fff;" onclick="openPopup('checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4')">Ready To Transfer</a></td> --%> 
              					   				<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;" >Ready To Transfer</a></td>
              					   			<%}else{ %>
              					   				<s:if test="curr_location==1">
              					   					<%-- <td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="#" style="color:#fff;" onclick="openPopup('checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4')">Ready To Transfer</a></td> --%> 
              					   					<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4" style="color:#fff;">Ready To Transfer</a></td>
              					   				</s:if>
              					   				<s:else> 
              					   					<td style="text-align:center;background-color: rgba(138, 109, 59, 0.83);"><a href="checkmedicineavabilityafterPOProduct?id=<s:property value="parentid" />&status=4"  style="color:#fff;" >Ready To Transfer</a></td> 
              					   					
              					   				</s:else>
              					   			<%} %>
              					   		</s:elseif>              					
              					   	</s:else>
              					    <!--  <td style="text-align:center;"><a href="deleteindentparentProduct?id=<s:property value="parentid"/>" onclick="return confirmdelete()"><i class="fa fa-trash text-danger"></i></a></td>-->
              							<s:if test="req_or_transfer==1">
              								<td></td>		 
              					   		</s:if>
              					    	<s:elseif test="req_or_transfer==2">
              								<td></td>	
              					    	</s:elseif>
              							<s:elseif test="status==6"> 
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=0" ><i class="fa fa-print"></i></a></td> 
              					   		</s:elseif> 
              					   		<s:elseif test="status==7">
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=0" ><i class="fa fa-print"></i></a></td>
              					   		</s:elseif> 
              					   		<s:elseif test="status==3">
              					   		<td></td>
              					   		</s:elseif> 
              					   		<s:else>
              					   			<td style="text-align:center;"><a href="deliverPrintProduct?id=<s:property value="parentid" />&status=1&mainstatus=4&printbeforeapprove=1" ><i class="fa fa-print"></i></a></td>
              					   		</s:else>
              							<td style="text-align:center;">
              							<s:if test="status==0">
              								<%if(loginInfo.getUserType()==2){%>
              									<a href="#" onclick="deleteIndent(<s:property value="parentid"/>)"><i class="fa fa-trash text-danger"></i></a>
              								<%}else{ %>
              									<s:if test="cancel_indent==1">
              										<a href="#" onclick="deleteIndent(<s:property value="parentid"/>)"><i class="fa fa-trash text-danger"></i></a>
              									</s:if>
              								<%} %>
              							</s:if>
              							
              						</td>
              					</tr>
              				</s:iterator>
                            </tbody>
                        </table>
                    </div>
	</div>
	
	<s:form action="transferdashboardProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromdate"></s:hidden>
							     <s:hidden name="todate"></s:hidden>
							     <s:hidden name="searchtext"></s:hidden>
							     <s:hidden name="filter_status"></s:hidden>
									<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" />
											Records
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form> 
	
</div>



       <!--Indent Rquest Modal -->
<div id="ireq" class="modal fade " role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Requested Date :- <s:property value="date"/> &nbsp;|&nbsp; Location :- <s:property value="location"/> / <s:property value="userid"/></h4>
      </div>
      <s:form theme="simple" action="saveindentProduct" id="indentform">
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="background-color: rgba(22, 160, 133, 0.07);padding-top: 9px;">
        			<input type="hidden" name="allproductid" id="allproductid">
        			<input type="hidden" name="indentModifyButton" id="indentModifyButton">
        			<input type="hidden" name="limit" id="limitqty">
        		<div class="col-lg-2 col-md-2 col-xs-2">
        			<div class="form-group">
        				<label>Select Store</label>
        				<%-- <select name="warehouse_id" onchange="setProductofStore(this.value)" class="form-control chosen-select">
        				   
						    <option value="0">Select</option>
						    <option value="32">Central Drug Store</option>
						    <option value="36">Central Material Store</option>
						</select> --%>
						<s:select list="warehouseList" id="warehouse_id" name="warehouse_id" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="setProductofStore(this.value)" cssClass="form-control chosen-select"></s:select>
        			</div>
        		</div>
        		<div class="col-lg-2 col-md-2 col-xs-2 hidden">
        			<div class="form-group">
        				<label>Select Category</label>
        				<s:select list="categoryList" theme="simple" onchange="getsub(this.value)" cssClass="form-control chosen-select" id="category_id" name="category_id" listKey="id" listValue="name" headerKey="0" headerValue="Select Category" ></s:select>
        			</div>
        		</div>
        		<div class="col-lg-2 col-md-2 col-xs-2 hidden">
        			<div class="form-group" id="subdiv" >
        				<label>Select Sub Category</label>
        				<s:select list="subcategoryList" theme="simple" cssClass="form-control chosen-select" id="subcategory_id" name="subcategory_id" listKey="id" listValue="name" headerKey="0" headerValue="Select SubCategory" ></s:select>
        			</div>
        		</div>
        		<div class="col-lg-4 col-md-4 col-xs-4">
        			<div class="form-group" id="proddiv" >
        				<label>Select Product</label>
        				<select name="" id="product_id" class="form-control chosen-select">
						    <option value="0">Select</option>
						</select>
        			</div>
        		</div>
        	<%if(loginInfo.isKalmegha()){ %>	
        	<div class="col-lg-1 col-md-1 col-xs-1">
        			<div class="form-group">
        				<label>Qty_Limit</label>
        				<span id="prolimitqty"></span>
        			</div>
        		</div>
        		
        		<div class="col-lg-1 col-md-1 col-xs-2">
        			<div class="form-group">
        				<label>Qty</label>
      				<input type="number" min="0" name="qty" id="qty" onchange="checklimitqty(this.value)" class="form-control" placeholder="Qty" > <!-- for kaalmegh requiremen -->
      
         				<!-- <input type="number" min="0" name="qty" id="qty" class="form-control" placeholder="Qty" > -->
    			
 </div>
        		</div>
        	<%}else{ %>
        	  <div class="col-lg-1 col-md-1 col-xs-2">
        			<div class="form-group">
        				<label>Qty</label>
        				<input type="number" min="0" name="qty" id="qty" class="form-control" placeholder="Qty" >
        			</div>
        		</div>
        	<%} %>
        		<div class="col-lg-2 col-md-2 col-xs-2">
        			<div class="form-group">
        				<label>Expected Date</label>
        				<input type="text" readonly="readonly" name="expected_date" id="expected_date" class="form-control" placeholder="Select Date">
        				
        			</div>
        		</div>
        		<div class="col-lg-1 col-md-1 col-xs-2">
        			<input type="hidden" id="parentTransferId" name="parentTransferId">
        			<input type="hidden" id="parentTransferlocation" >
        			<div class="form-group" style="margin-top:22px;">
        				<a href="#" class="btn btn-success" onclick="addnewIndent('indenttable')">Add</a>
        			</div>
        		</div>
        	</div>
        </div>
        
	<div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12 scrolltable " style="padding: 0px;">
        	<table class="table my-table xlstable table-striped table-bordered" id="indenttable" style="width: 100%;">
                            <thead>
                                <tr>
                                	<th style="width: 1%;">Sr.no</th>
                                	<th style="width: 18%;">Product Name</th>
                                	<th style="width: 9%;">My Available Qty</th>
                                	<!-- <th style="width: 8%;">Store Avail. Qty</th> -->
                                	<th style="width: 6%;">Req. Qty</th>
                                	<%if(loginInfo.isKalmegha()){ %>
                                	<th style="width: 6%;">Qty Limit</th>
                                	<%} %>
                                	<th style="width: 6%;">Expect Date</th>
                                	<th style="width: 25%;">Business Comments</th>
                                	<%if(loginInfo.isKalmegha()){ %>
                                	<th style="width: 6%;">Request For Qty</th>
                                	<%} %>
                                	<th style="width: 3%;"></th>
                                </tr>
                            </thead>
                            <tbody id="indentbody">
                              	<tr></tr> 
                            </tbody>
                        </table>
        	
        	</div>
        </div>
      </div>
      
      </s:form>
      <div class="modal-footer">
      	<div id="saveIndentButton">
      		<button type="button" class="btn btn-primary" onclick="saveInden(0)" style="margin-top: 20px;">Request</button>
      	</div>
      	<div id="saveIndentButton1">
      		<button type="button" class="btn btn-primary" onclick="saveInden(1)" style="margin-top: 20px;">Update</button>
      		<button type="button" class="btn btn-primary" onclick="saveInden(2)" style="margin-top: 20px;">Update And Confirm</button>
      	</div>
        
      </div>
    </div>

  </div>
</div>             
                 
                 
                    
                    
                    
                    
                    <!-- Cart Modal -->
<div id="cart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
     
    <div class="modal-content">
    <s:form action="checkmedicineavabilityPharmacy" theme="simple"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">DEPARTMENT ISSUE NOTE</h4>
      </div>
     
      <div class="modal-body">
      <div id="page_printer5">
      	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="hospitaltitlediv">
									        	  		<h3><b>SHREE NARAYANA HOSPITAL</b></h3>
									        			<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj Mandi,</h5>
									        			<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
									        			<h5>Phone: 0771-3001234,35,36</h5>
									        		</div>
								        	      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								        		  <h4><b>DEPARTMENT ISSUE NOTE</b></h4>
								        	    </div>
								        	    	<s:hidden name="parentid2" id="parentid2"></s:hidden>
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-bottom: 15px;text-transform: uppercase;">
									        			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									        				<p style="margin: 0 0 2.5px;"><b>Issue Number : </b><span id="issueno"></span></p>
										        			<p style="margin: 0 0 2.5px;"><b>From : </b><span id="fromlocation">Store</span></p>
										        			<p style="margin: 0 0 2.5px;"><b>Product Number : </b><span id="indentno"></span></p>
										        	</div>
										        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										        		<p style="margin: 0 0 2.5px;"><b>Issue Date : </b><span id="issuedate">02-07-2017 18:45</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>To : </b><span id="tolocation">ESIC Pharmacy</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>Request Date : </b><span id="requestdate">02-07-17 19:46</span></p>
										            </div>
							        	    </div>
							          <div>
							        	</div>
							        </div>
							      <div>
         <table class="table" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 20%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 9%;">Issue Qty</th>
            <th style="width: 10%;text-align: right;">Unit Rate</th>
            <th style="width: 10%;text-align: right;">Amount Rs</th>
            <th style="width: 9%;text-align: right;">MRP</th>
            <th style="width: 23%;text-align: right;">MRP Amount</th>
           </tr>
          </thead>
          <tbody id="tbodyid">
           
          </tbody>
         </table>
        </div>
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;margin-top: 30px;">
        	<div class="col-lg-6 col-md-6 col-xs-6">
        		<p style="margin: 0px;" id="username">Palli R</p>
        		<p style="margin: 0px;" id="userdatetime"></p>
        		<p>Issued By</p>
        	</div>
        	<div class="col-lg-6 col-md-6 col-xs-6 text-right">
        		<p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p>Received By</p>
        	</div>
        </div>
      
      </div>
      
      </div>
      <div class="modal-footer">
      	<input type="button" onclick="printDiv2('page_printer5');" class="btn btn-primary" value="Print">
      </div>
      </s:form>
     </div>
 
  </div>
</div>
                    <!-- Cart Modal -->
<div id="cart3" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
    <s:form action="checkmedicineavabilityPharmacy" id="deptrequestform" theme="simple"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">DEPARTMENT REQUEST NOTE</h4>
      </div>
     
      <div class="modal-body">
      <div id="page_printer3"> 
      	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="hospitaltitlediv3">
									        	  		<h3><b>SHREE NARAYANA HOSPITAL</b></h3>
									        			<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj Mandi,</h5>
									        			<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
									        			<h5>Phone: 0771-3001234,35,36</h5>
									        		</div>
								        	      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								        		  <h4><b>DEPARTMENT REQUEST NOTE</b></h4>
								        	    </div>
								        	    	<s:hidden name="parentid" id="parentid23"></s:hidden>
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-bottom: 15px;text-transform: uppercase;">
									        			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									        				<p style="margin: 0 0 2.5px;"><b>Issue Number : </b><span id="issueno3"></span></p>
										        			<p style="margin: 0 0 2.5px;"><b>Requested From : </b><span id="fromlocation3">Store</span></p>
										        			<p style="margin: 0 0 2.5px;" class="hidden"><b>Indent Number : </b><span id="indentno3"></span></p>
										        	</div>
										        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										        		<p style="margin: 0 0 2.5px;" class="hidden"><b>Issue Date : </b><span id="issuedate3">02-07-2017 18:45</span></p>
										        		<p style="margin: 0 0 2.5px;"><b> </b><span id="tolocation3">ESIC Pharmacy</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>Request Date : </b><span id="requestdate3">02-07-17 19:46</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>Request User : </b><span id="requestuser3">   </span></p>
										            </div>
							        	    </div>
							          <div>
							        	</div>
							        </div>
							      <div>
         <table class="table" id="mytable" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 7%;" class="hidden">Product ID</th>
            <th style="width: 27%;">Product Name</th>
            <th style="width: 0%;" class="hidden">MRP</th>
            <th class="hidden" style="width: 10%;">Sale Price</th>
            <th style="width: 8%;">Store Avail. Qty</th>
            <th style="width: 10%;">Req. Loc. Avail. Qty</th>
            <th style="width: 7%;">Req Qty</th>
            <th style="width: 7%;">Exceed Qty</th>
            <th style="width: 25%;">Business Reason </th>
            <th style="width: 1%;" class="hidden-print"></th>
           </tr>
          </thead>
          <tbody id="tbodyid3">
          
          </tbody>
         </table>
         
         <table class="table" id="deletecart3" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 27%;">Product Name</th>
            <th style="width: 7%;">Req Qty</th>
            <th style="width: 10%;">Delete Userid</th>
            <th style="width: 20%;">Delete Datetime</th>
           </tr>
          </thead>
          <tbody id="deletetbodyid3">
          
          </tbody>
         </table>
        </div>
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;margin-top: 30px;">
        	<div class="col-lg-8 col-md-8 col-xs-6">
        		<p style="margin: 0px;" id="username3"> </p>
        		<p style="margin: 0px;" id="userdatetime3"></p>
        		<p>Issued By</p>
        	</div>
        	<div class="col-lg-4 col-md-4 col-xs-6 text-right">
        		<p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p id="receivedbyid">Received By</p>
        		<div id="noteTextBox">
        			<s:textarea id="notes" cssClass="form-control" name="notes" theme="simple" placeholder="Write Note"></s:textarea>
        		</div>
        		
        	</div>
        </div>
      
      </div>
      
      </div>
      <div class="modal-footer" id="btndiv">
      	 <input type="submit"  class="btn btn-primary" value="Check Availability">
         <!--<input type="button" onclick="printDiv('page_printer');" class="btn btn-primary" value="Print">-->
      </div>
      </s:form>
     </div>
 
  </div>
</div>

<!-- Check Availability Modal -->
<div id="checkavailabilitymodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">DEPARTMENT REQUEST NOTE</h4>
      </div>
     
   <div class="modal-body">
    <div id="page_printer"> 
      	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="hospitaltitlediv5">
					<h3><b>SHREE NARAYANA HOSPITAL</b></h3>
					<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj Mandi,</h5>
					<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
					<h5>Phone: 0771-3001234,35,36</h5>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
					<h4><b>DEPARTMENT REQUEST NOTE</b></h4>
					<s:hidden name="parentid2" id="parentid5"></s:hidden>
			</div>	
		</div>
	<div>
      <table class="table" style="width:100%;">
         <thead>
           <tr>
            	<th style="width: 4%;">Sr.no</th>
            	<th style="width: 10%;">Product ID</th>
            	<th style="width: 20%;">Product Name</th>
            	<th style="width: 9%;">Requested Quantity</th>
           </tr>
         </thead>
        <tbody id="reqtbody">
        </tbody>
      </table>
	
	  <table class="table" style="width:100%;">
         <thead>
           <tr>
           		<th style="width: 4%;"></th>
            	<th style="width: 10%;">Product ID</th>
            	<th style="width: 20%;">Product Name</th>
            	<th style="width: 10%;">HSNO NO</th>
            	<th style="width: 10%;">Expiry Date</th>
            	<th style="width: 10%;">Batch No</th>
            	<th style="width: 20%;">Available Quantity</th>
           </tr>
         </thead>
        	<tbody id="checkavailtbody">
          	</tbody>
      </table>
    </div>
   </div>
  </div>
   	<div class="modal-footer" id="btndiv5">
      	<input type="button"   class="btn btn-primary" onclick="addToTransferTemp()" value="Add to Transfer">
      	<input type="button"  class="btn btn-primary" value="Create PO">
     </div>
   </div>
 </div>
</div>


<!-- Transfer Modal -->
<div id="transfermodel" class="modal fade" role="dialog">
 	<div class="modal-dialog modal-lg">
    	<!-- Modal content-->
 		   <div class="modal-content">
   				<s:form action="transferrequestedproductdataProduct" theme="simple" id="transferproductform"> 
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal">&times;</button>
        				<h4 class="modal-title">Transfer Stock</h4>
      				</div>
      				<div class="modal-body">
        				<div class="">
        					<table class="table table-striped table-bordered" style="width:100%;">
         						<thead>
           							<tr>
            						<th style="width: 4%;">Sr.no</th>
            						<th style="width: 10%;">Product ID</th>
            						<th style="width: 20%;">Product Name</th>
            						<th style="width: 9%;">Requested Quantity</th>
           							</tr>
        						</thead>
        						<tbody id="reqtbody2">
        						</tbody>
      						</table>
      						<br><label>TRANSFER STOCK LIST</label>
         					<table class="table my-table xlstable table-striped table-bordered" id="tabletrcount" style="width:100%;">
          						<thead>
           							<tr>
            							<th style="width: 4%;">Sr.no</th>
            							<th style="width: 9%;">HSN Code</th>
            							<th style="width: 26%;">Product Name</th>
            							<th style="width: 10%;">Batch No</th>
            							<th style="width: 10%;">Exp Date</th>
           								<th style="width: 10%;">From Location</th>
            							<th style="width: 9%;">Current Stock</th>
            							<th style="width: 13%;">Requested Location</th>
            							<th style="width: 10%;">Transfer Qty</th>
            							<th style=""></th>
           							</tr>
          						</thead>
          						<tbody id="transfertbody">
           
          						</tbody>
          					</table>
          					<input type="hidden" name="tcount" id="tcount">
       				 	</div>
      				</div>
      				<div class="modal-footer">
       					<button type="button" class="btn btn-primary" onclick="confirmTransfer()">Transfer</button>
      				</div>
      			</s:form>
    		</div>
	  </div>
</div>

<!-- last cart after check avilability model -->
 <div id="lastcart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
   <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal">&times;</button>
       	 <h4 class="modal-title">DEPARTMENT REQUEST NOTE</h4>
      </div>
     
      <div class="modal-body">
      <div id="page_printer2">
      	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="lasthospitalnamediv">
									        	  		<h3><b>SHREE NARAYANA HOSPITAL</b></h3>
									        			<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj Mandi,</h5>
									        			<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
									        			<h5>Phone: 0771-3001234,35,36</h5>
									        		</div>
								        	      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								        		  <h4><b>DEPARTMENT REQUEST NOTE</b></h4>
								        	    </div>
								        	    	<s:hidden name="parentid2" id="lastparentid"></s:hidden>
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-bottom: 15px;text-transform: uppercase;">
									        			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									        				<p style="margin: 0 0 2.5px;"><b>Issue Number : </b><span id="lastissueno"></span></p>
										        			<p style="margin: 0 0 2.5px;"><b>Requested From : </b><span id="lastfromlocation"> Store</span></p>
										        			<p style="margin: 0 0 2.5px;"><b>Indent Number : </b><span id="lastindentno"></span></p>
										        	</div>
										        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										        		<p style="margin: 0 0 2.5px;"><b>Issue Date : </b><span id="lastissuedate"></span></p>
										        		<p style="margin: 0 0 2.5px;"><b> </b><span id="tolocation3"></span></p>
										        		<p style="margin: 0 0 2.5px;"><b>Request Date : </b><span id="lastrequestdate">02-07-17 19:46</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>Request User : </b><span id="lastrequestuser">   </span></p>
										            </div>
							        	    </div>
							          <div>
							        	</div>
							        </div>
							      <div>
							      <table class="table" style="width:100%;">
         						<thead>
           							<tr>
            						<th style="width: 4%;">Sr.no</th>
            						<th style="width: 10%;">Product ID</th>
            						<th style="width: 20%;">Product Name</th>
            						<th style="width: 9%;">Avail Qty</th>
            						<th style="width: 9%;">Req Qty</th>
            						<th style="width: 15%;">Expected Date</th>
            						<th style="width: 20%;">Business Reason</th>
           							</tr>
        						</thead>
        						<tbody id="lastreqtbody">
        						</tbody>
      						</table>
         <table class="table" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 20%;">Product Name</th>
            <th style="width: 8%;">Batch No</th>
            <th style="width: 8%;">Exp Date</th>
            <th style="width: 7%;">Issue Qty</th>
            <th style="width: 7%;text-align: right;">Unit Rate</th>
            <th style="width: 9%;text-align: right;">Amount</th>
            <th style="width: 8%;text-align: right;">MRP</th>
            <th style="width: 9%;text-align: right;">MRP Amount</th>
           	<th style="width: 31%;">Location</th>
           </tr>
          </thead>
          <tfoot id="lasttfoot">
          	<tr>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td></td>
          		<td style="text-align: right;">Rs.0.00</td>
          		<td style="text-align: right;">Rs.0.00</td>
          		<td style="text-align: right;">Rs.0.00</td>
          		<td style="text-align: right;">Rs.0.00</td>
          		<td></td>
          	</tr>
          </tfoot>
          <tbody id="lasttbodyid">
          		
          </tbody>
         </table>
        </div>
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;margin-top: 30px;">
        	<div class="col-lg-6 col-md-6 col-xs-6">
        		<p>Issued By</p>
        	</div>
        	<div class="col-lg-6 col-md-6 col-xs-6 text-right">
        		<p>Received By</p>
        	</div>
        </div>
        	
      </div>
      
      </div>
      <div class="modal-footer" id="lastbuttondiv">
      
      </div>
     </div>
 
  </div>
</div>



                   <!-- Cart Modal -->
<div id="incart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
    <s:form action="checkmedicineavabilityPharmacy" id="deptrequestform" theme="simple"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">DEPARTMENT REQUEST NOTE</h4>
      </div>
     
      <div class="modal-body">
      <div id="page_printer4"> 
      	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="inhospitaltitlediv">
									        	  		<h3><b>SHREE NARAYANA HOSPITAL</b></h3>
									        			<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj Mandi,</h5>
									        			<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
									        			<h5>Phone: 0771-3001234,35,36</h5>
									        		</div>
								        	      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								        		  <h4><b>DEPARTMENT REQUEST NOTE</b></h4>
								        	    </div>
								        	    	<s:hidden name="parentid" id="inparentid"></s:hidden>
									        	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-bottom: 15px;text-transform: uppercase;">
									        			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									        				<p style="margin: 0 0 2.5px;"><b>Issue Number : </b><span id="inissueno"></span></p>
										        			<p style="margin: 0 0 2.5px;"><b>Requested From : </b><span id="infromlocation">Store</span></p>
										        			<p style="margin: 0 0 2.5px;"><b>Indent Number : </b><span id="inindentno"></span></p>
										        	</div>
										        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										        		<p style="margin: 0 0 2.5px;"><b>Issue Date : </b><span id="inissuedate">02-07-2017 18:45</span></p>
										        		<p style="margin: 0 0 2.5px;"><b> </b><span id="intolocation">ESIC Pharmacy</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>Request Date : </b><span id="inrequestdate">02-07-17 19:46</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>Request User : </b><span id="inrequestuser">   </span></p>
										            </div>
							        	    </div>
							          <div>
							        	</div>
							        </div>
							      <div>
         <table class="table" id="mytable" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 7%;">Product ID</th>
            <th style="width: 20%;">Product Name</th>
            <th style="width: 7%;">MRP</th>
            <th class="hidden" style="width: 10%;">Sale Price</th>
            <th style="width: 7%;">Avail Qty</th>
            <th style="width: 7%;">Req Qty</th>
            <th style="width: 25%;">Business Reason </th>
            <th style="width: 1%;" class="hidden-print"></th>
           </tr>
          </thead>
          <tbody id="intbodyid">
          
          </tbody>
         </table>
        </div>
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;margin-top: 30px;">
        	<div class="col-lg-8 col-md-8 col-xs-6">
        		<p style="margin: 0px;" id="inusername"> </p>
        		<p style="margin: 0px;" id="inuserdatetime"></p>
        		<p>Issued By</p>
        	</div>
        	<div class="col-lg-4 col-md-4 col-xs-6 text-right">
        		<p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p id="receivedbyid">Received By</p>
        		<div id="noteTextBox">
        			<p>Admin Note:</p><p id="inadminnote"></p>
        		</div>
        		
        	</div>
        </div>
      
      </div>
      
      </div>
      <div class="modal-footer">
      	<input type="button" onclick="printDiv2('page_printer4')" class="btn btn-warning hidden-print" value='PRINT'>
      </div>
      </s:form>
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
        <input type="button" class="btn btn-danger" onclick="deleteIndent1()"  value="Ok">
      </div>
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
  
<script>
    function printDiv2(divID) {
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
	
	<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script>
				             $(function() {
								  $('.scrolltable').slimScroll({
								   		height : '350px',
								   		railVisible: true,
										alwaysVisible: true
								  });
								 
				 				});
 				 
             </script>
	
	


</body>
</html>