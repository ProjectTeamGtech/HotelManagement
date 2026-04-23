<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<html class="no-js" lang="">
<!--<![endif]-->

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">


		<link rel="stylesheet" href="pharmacy/searchexport/dataTables.bootstrap.css">
		<link rel="stylesheet" href="pharmacy/searchexport/buttons.bootstrap.css">
		
		
		

<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>

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
</style>

    <style>
    .has-warning .checkbox-custom > i {
  border-color: #ffcd33;
}

.checkbox-custom,
.checkbox-custom-alt {
    padding-left: 20px;
    cursor: pointer;
    margin-top: 0px !important;
    margin-bottom: 0px !important;
}

.checkbox-custom input,
.checkbox-custom-alt input {
  position: absolute;
  opacity: 0;
}

.checkbox-custom input:checked + i,
.checkbox-custom-alt input:checked + i {
  border-color: #428bca;
  background-color: #428bca;
}

.checkbox-custom input:checked + i:before,
.checkbox-custom-alt input:checked + i:before {
  top: 3px;
  left: 3px;
  width: 12px;
  height: 12px;
  background-color: #fff;
}

.checkbox-custom input:checked + span .active,
.checkbox-custom-alt input:checked + span .active {
  display: inherit;
}

.checkbox-custom input[type='radio'] + i,
.checkbox-custom input[type='radio'] + i:before,
.checkbox-custom-alt input[type='radio'] + i,
.checkbox-custom-alt input[type='radio'] + i:before {
  border-radius: 50%;
}

.checkbox-custom input[disabled] + i,
.checkbox-custom-alt input[disabled] + i {
  border-color: #e2e2e2;
  background-color: #f2f2f2;
}

.checkbox-custom input[disabled] + i:before,
.checkbox-custom-alt input[disabled] + i:before {
  background-color: #e2e2e2;
}

.checkbox-custom > i,
.checkbox-custom-alt > i {
  position: relative;
  display: inline-block;
  width: 20px;
  height: 20px;
  margin-top: -2px;
  margin-right: 4px;
  margin-left: -20px;
  line-height: 1;
  vertical-align: middle;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.2);
  -webkit-transition: all 0.2s;
  -moz-transition: all 0.2s;
  transition: all 0.2s;
}

.checkbox-custom > i:before,
.checkbox-custom-alt > i:before {
  position: absolute;
  top: 50%;
  left: -100%;
  width: 0;
  height: 0;
  background-color: transparent;
  content: "";
  -webkit-transition: all 0.2s;
  -moz-transition: all 0.2s;
  transition: all 0.2s;
  z-index: 1;
}

.checkbox-custom > span,
.checkbox-custom-alt > span {
  margin-left: -20px;
}

.checkbox-custom > span .active,
.checkbox-custom-alt > span .active {
  display: none;
}

.checkbox-custom:hover > i,
.checkbox-custom-alt:hover > i {
  border-color: #22beef;
}

.checkbox-custom.checkbox-custom-sm input:checked + i:before,
.checkbox-custom-alt.checkbox-custom-sm input:checked + i:before {
  top: 2px;
  left: 2px;
  width: 10px;
  height: 10px;
}

.checkbox-custom.checkbox-custom-sm > i,
.checkbox-custom-alt.checkbox-custom-sm > i {
  width: 16px;
  height: 16px;
  margin-right: 6px;
  margin-left: -18px;
}

.checkbox-custom.checkbox-custom-lg input:checked + i:before,
.checkbox-custom-alt.checkbox-custom-lg input:checked + i:before {
  top: 3px;
  left: 3px;
  width: 22px;
  height: 22px;
}

.checkbox-custom.checkbox-custom-lg > i,
.checkbox-custom-alt.checkbox-custom-lg > i {
  width: 30px;
  height: 30px;
}

.checkbox-custom-alt input:checked + i {
  background-color: transparent;
  border-color: #666;
  color: #666;
}

.checkbox-custom-alt input:checked + i:before {
  top: 2px;
  left: 2px;
  width: auto;
  height: auto;
  background-color: transparent;
  opacity: 1;
}

.checkbox-custom-alt input[type='radio']:checked + i:before {
  left: 1px;
}

.checkbox-custom-alt input[disabled] + i {
  border-color: #e2e2e2;
  background-color: #f2f2f2;
}

.checkbox-custom-alt input[disabled] + i:before {
  background-color: transparent;
  color: #ccc;
}

.checkbox-custom-alt > i {
  width: 18px;
  height: 18px;
  background-color: transparent;
  border: 2px solid #dfdfdf;
}

.checkbox-custom-alt > i:before {
  content: "\f00c";
  top: 0;
  left: 0;
  display: inline-block;
  font-family: "FontAwesome";
  font-style: normal;
  font-weight: normal;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 11px;
  opacity: 0;
  -webkit-transition: all 0.2s;
  -moz-transition: all 0.2s;
  transition: all 0.2s;
}

.checkbox-custom-alt.checkbox-custom-sm > i:before {
  font-size: 9px;
}

.checkbox-custom-alt.checkbox-custom-sm input:checked + i:before {
  font-size: 9px;
  top: 1px;
}

.checkbox-custom-alt.checkbox-custom-lg input:checked + i:before {
  font-size: 18px;
  top: 4px;
  left: 4px;
}
    
    
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
    table.dataTable thead .sorting:after, table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_desc:after, table.dataTable thead .sorting_asc_disabled:after, table.dataTable thead .sorting_desc_disabled:after {
	    bottom: 0px;
	}
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 8px;
        }
        .red{
            color:red;
        }
        .addcatego {
            float: right;
            margin-top: -40px;
            margin-right: 30px;
        }
        .PaymentDetails {
            line-height: 25px;
            padding-left: 10px;
            border: 1px solid gray;
            float: left;
            width: 100%;
            margin-top: 2px !important;
        }
        .DivBlock {
            float: left;
            width: 24%;
            margin: 2px;
            height: 42px;
        }
        .headingset{
			display: inline-flex;
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


<script>

$(document).ready(function() {

		$("#paymentDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});
		
		document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false); 
});
		

</script>


<body id="his" class="appWrapper sidebar-xs-forced">

    <%-- <s:form action="payPoPayment" onsubmit="return isValid()" id="paymentfrm" theme="simple"> --%>
    <s:form action="payPoPayment"  id="paymentfrm" theme="simple">
    <s:hidden name="hdnProcurementid" id="hdnProcurementid" value="%{procurementid}" />
    <s:hidden name="hdntotalamount" id="hdntotalamount" value="%{total}" />
    <s:hidden name="vendor_id" />
    <s:hidden name="grnreturnids" />
    <s:hidden name="allvoucher" id="allvoucher" value="0" ></s:hidden>
        <section id="">

            <div class="">

                <div class="">
                    
                    
                    <div class="">
                    	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
                    	<div class="hidden-print">
							<ul class="breadcrumb">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
								<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
									<%if(breadcrumbs.isIscurrent()){ %>
										<li><%=breadcrumbs.getShowingname()%></li>
									<%}else{ %>
										<%if(breadcrumbs.getOn()){ %>
											<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getShowingname()%></a></li>
										<%}else{ %>
											<li><%=breadcrumbs.getShowingname()%></li>
										<%} %>
									<%} %>
									
								<%} %>
							</ul>
						</div>
                    	<div class="col-lg-12 col-xs-12 col-md-12" style="background-color: dimgray;color: #fff;">
                    		<h4 class="hidden">Payment ID : <span id="CPHcontent_lblPONo"><s:property value="procurementid"/>   </span></h4>
                    		<h4 >Payment And History </h4>
                    	</div>
                    	
                    	<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;background-color: #f5f5f5;padding-top: 10px;">
        	<div class="col-lg-2 col-xs-2 col-md-2">
        		 <div class="form-group">
				    <label for="email">Supplier Name</label>
				    <p id="CPHcontent_lblVendorName"><s:property value="vendor"/></p>
				  </div>
        	</div>
        	
        	<div class="col-lg-2 col-xs-2 col-md-2">
        		<div class="form-group">
				    <label for="email">Total Amount</label>
				    <p id="CPHcontent_lblPoAmount"><%=Constants.getCurrency(loginfo)%><s:property value="total"/></p>
				  </div>
        	</div>
        	<div class="col-lg-2 col-xs-2 col-md-2">
        		<div class="form-group">
				    <label for="email">Balance Amount</label>
				    <p style="color:#d9534f;" id="CPHcontent_lblBalance"><%=Constants.getCurrency(loginfo)%><s:property value="balance"/></p>
				    
				  </div>
        	</div>
        	<div class="col-lg-2 col-xs-2 col-md-2">
        		<div class="form-group">
				    <label for="email">Date</label>
				     <s:textfield name="paymentDate" id="paymentDate" readonly="true" cssClass="form-control"/>
								     <br><label id="lblDateError" style="color:Red"></label>
				  </div>
        	</div>
        	<div class="col-lg-2 col-xs-2 col-md-2">
        		<div class="form-group">
				    <!-- <label for="email">Payment Type</label>
				     -->
				    	
				  </div>
        	</div>
        	<div class="col-lg-2 col-xs-2 col-md-2 ">
        		<div class="form-group">
				   <!--  <label for="email">Payment Amount</label> -->
				    <label for="email"></label> 
				    <p><a class="btn btn-primary" id="btnhistory" onclick="hidediv()">Payment History</a></p>
				     
				  </div>
        	</div>
        	<div class="row">
	<div class="col-lg-12">
		<s:hidden name = "sucessmsg" id = "sucessmsg"></s:hidden>
		<s:if test="hasActionMessages()">
			<script>
				var msg = " " + document.getElementById('sucessmsg').value;
				showGrowl('', msg, 'success', 'fa fa-check');
			</script>
			<s:actionerror/>
		</s:if>
	</div>
</div>
        </div>
        
        <!-- Design By abhi 7-10-2017 -->
        <form action="" method="post" name="form1">
  <div id="megaidvd">
  <div class="form-inline">
  	<div class="form-control">
  	<label class="checkbox checkbox-custom-alt"><input type="radio" name="type" value="Individual" id="type_0" checked="checked"><i></i> Purchase Account</label>&nbsp;&nbsp;&nbsp;&nbsp;
  	<label class="checkbox checkbox-custom-alt"><input type="radio" name="type" value="Company" id="type_1"><i></i> Return Account</label> 
  	</div>
  	<div class="form-control">
  	<%if(loginfo.isIsledgerhosp()){ %>
	  	<s:select disabled="" name="ledgername" id="ledgername" list="ledgerList" listKey="id" listValue="name"
				headerKey="0" headerValue="Select Ledger" cssClass="form-control chosen-select"/>
	<%} %>
  	</div>
  </div>
 
 
  <div id="Individual_box">
    <div class="col-lg-12 col-md-12 col-xs-12 minheset" style="padding:0px;">
        	<table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                 	<th style="width:1%"><label style="margin-top: 4px !important;" class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox" id="selectallid" onclick="setAll(this)" /> <i></i></label></th>	
                                	<th style="width:3%">Invoice Date</th>
                                	<th style="width:2%">Invoice No</th>
                                	<th style="width:2%">GRN No.</th>
                                	<th style="width:6%">Location</th>
                                    <th style="width:8%">Payment Mode</th>
                                    <th style="width:9%">Cheque Maker</th>
                                    <th style="width:9%">Cheque Type</th>
                                    <th style="width:7%">Cheque No</th>
                                    <th style="width:7%">Select Bank</th>
                                    <th style="width:7%">Bank Name</th>
                                    <th style="width:9%">Handover To</th>
                                    <th style="text-align:right;width:7%">Total Amount</th>
                                    <th style="text-align:right;width:7%">Paid Amount</th>
                                    <th style="text-align:right;width:8%">Balance Amount</th>
                                    <th style="width:15%">Amount</th>
                                 </tr>
                            </thead>
                             
                            <tbody>
                               <%int i=0; %>
                            	<s:iterator value="voucherList">
                                <tr>
                                	 <td><label style="padding-top: 3px;" class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox" id="check<%=i%>" name="payments[<%=i%>].check" class="case" onclick="setTempAll()"  /><i></i></label></td>
                                	<td><s:property value="date"/></td>
                                	<td><s:property value="voucherno"/>
                                	 <input type="hidden" name="payments[<%=i%>].voucherno" value="<s:property value="voucherno"/>"   />  
                                	 <input type="hidden" name="payments[<%=i%>].procurementid" value="<s:property value="procurementid"/>"   />
                                	 <input type="hidden" name="payments[<%=i%>].vendor_id" value="<s:property value="vendor_id"/>"   />  
                                	 <input type="hidden" name="payments[<%=i%>].location" value="<s:property value="location"/>"   />
                                	</td>
                                	<td><s:property value="grnno"/></td>
                                	<td><s:property value="locationName"/></td>
                                    <td>
                                    <%if(i==0){ %>
                                    	<SELECT onchange="myFunction2(this.value,<%=i%>)" name="payments[<%=i%>].paymentType" id="paymentType<%=i%>" class="form-control">
                                       		<option value='0'>Select</option> 
                                       		<option value='Cash'>Cash</option> 
                                       		<option value='Cheque'>Cheque</option>
                                       		<option value='NEFT'>NEFT</option> 
                                    	</select>
                                    <%}else{ %>
                                    	<SELECT onchange="myFunction(this.value,<%=i%>)" name="payments[<%=i%>].paymentType" id="paymentType<%=i%>" class="form-control">
                                       		<option value='0'>Select</option> 
                                       		<option value='Cash'>Cash</option> 
                                       		<option value='Cheque'>Cheque</option>
                                       		<option value='NEFT'>NEFT</option> 
                                    	</select>
                                    <%} %>
                                    </td>
                                 	 <td>
                                 	 <%if(i==0){ %>
                                    	<input type="text" name="payments[<%=i%>].cheq_receiver" onblur="myFunction3(<%=i%>)" disabled="disabled" id="cheq_receiver<%=i%>" class="form-control" />
                                     	<label id="lblChequeReceiverError<%=i%>" style="color:Red"></label>
                                    <%}else{ %>
                                    	<input type="text" name="payments[<%=i%>].cheq_receiver" disabled="disabled" id="cheq_receiver<%=i%>" class="form-control" />
                                     	<label id="lblChequeReceiverError<%=i%>" style="color:Red"></label>
                                    <%} %>
                                    	
                                    </td> 
                                    <td>
                                    	<%if(i==0){ %>
                                    		<Select name="payments[<%=i%>].cheqType" id="cheqType<%=i%>" onchange="myFunction4(<%=i%>)" class="form-control" disabled="disabled" >
                                    	 	<option value="0">Cheque Type</option>
                                    	 	<option value="Bearer Cheque">Bearer Cheque</option>
                                    	 	<option value="Order Cheque">Order Cheque</option>
                                    	 	<option value="Uncrossed / Open Cheque">Uncrossed / Open Cheque</option>
                                    	 	<option value="Crossed Cheque">Crossed Cheque</option>
                                    	 	<option value="Anti-Dated Cheque">Anti-Dated Cheque</option>
                                    	 	<option value="Post-Dated Cheque">Post-Dated Cheque</option>
                                    		 <option value="Stale Cheque">Stale Cheque</option>
											</select>
                                   		 <%}else{ %>
                                    		<Select name="payments[<%=i%>].cheqType" id="cheqType<%=i%>"  class="form-control" disabled="disabled" >
                                    	 	<option value="0">Cheque Type</option>
                                    	 	<option value="Bearer Cheque">Bearer Cheque</option>
                                    	 	<option value="Order Cheque">Order Cheque</option>
                                    	 	<option value="Uncrossed / Open Cheque">Uncrossed / Open Cheque</option>
                                    	 	<option value="Crossed Cheque">Crossed Cheque</option>
                                    	 	<option value="Anti-Dated Cheque">Anti-Dated Cheque</option>
                                    	 	<option value="Post-Dated Cheque">Post-Dated Cheque</option>
                                    	 	<option value="Stale Cheque">Stale Cheque</option>
											</select>
                                    	<%} %>
                                    	
                                    </td>
                                    <td>
                                    	<%if(i==0){ %>
                                    		<input type="text" name="payments[<%=i%>].cheqNo" disabled="disabled" onblur="myFunction5(<%=i%>)"  id="cheqNo<%=i%>" class="form-control" />
                                     		<label id="lblChequeNoError<%=i%>" style="color:Red"></label>
										 <%}else{ %>
                                    		<input type="text" name="payments[<%=i%>].cheqNo" disabled="disabled" id="cheqNo<%=i%>" class="form-control" />
                                     		<label id="lblChequeNoError<%=i%>" style="color:Red"></label>
                                    	<%} %>
                                    	
                                    </td>
                                    <td>
                                    	<%if(i==0){ %>
                                    		<Select name="payments[<%=i%>].bankid" id="bankid<%=i%>" onchange="myFunction8(<%=i%>)" class="form-control" disabled="disabled" >
                                    	 		<option value="0">Bank Name</option>
                                    	 		<s:iterator value="bankNameList">
                                    	 			<option value="<s:property value="id"/>"><s:property value="name"/></option>
                                    	 		</s:iterator>
                                    	 	</select>
                                   		 <%}else{ %>
                                    		<Select name="payments[<%=i%>].bankid" id="bankid<%=i%>" onchange="myFunction8(<%=i%>)" class="form-control" disabled="disabled" >
                                    	 		<option value="0">Bank Name</option>
                                    	 		<s:iterator value="bankNameList">
                                    	 			<option value="<s:property value="id"/>"><s:property value="name"/></option>
                                    	 		</s:iterator>
                                    	 	</select>
                                    	<%} %>
                                    </td>
                                    <td>
                                    	<%if(i==0){ %>
                                    		<input type="text" name="payments[<%=i%>].bankName" disabled="disabled" onblur="myFunction6(<%=i%>)" id="bankName<%=i%>" class="form-control"/>
                                   			<label id="lblBankNameError<%=i %>" style="color:Red"></label>
										 <%}else{ %>
                                    		<input type="text" name="payments[<%=i%>].bankName" disabled="disabled" id="bankName<%=i%>" class="form-control"/>
                                   			<label id="lblBankNameError<%=i %>" style="color:Red"></label>
                                    	<%} %>
                                    	
                                    </td>
                                    <td>
                                   		 <%if(i==0){ %>
                                    			<input type="text" name="payments[<%=i%>].handoverTo" id="handoverTo<%=i%>" onblur="myFunction7(<%=i%>)" class="form-control"/><label id="lblPresonNameError<%=i %>" style="color:Red">
										 <%}else{ %>
                 								<input type="text" name="payments[<%=i%>].handoverTo" id="handoverTo<%=i%>" class="form-control"/><label id="lblPresonNameError<%=i %>" style="color:Red">
                                    	<%} %>
                                    	
                                    </label>
                                    </td>
                                    <td class="text-right">Rs.<s:property value="total"/></td>
                                    <td class="text-right" style="color:green;">Rs.<s:property value="payAmount"/></td>
                                    <td class="text-right" style="color:red;">Rs.<s:property value="balance"/> <input type="hidden" name="payments[<%=i%>].balance" id="balance<%=i%>" value="<s:property value="balance"/>" /> </td>
                                   <td>
                                    	<input type="number" name="payments[<%=i%>].paymentAmount" id="paymentAmount<%=i%>" onchange="setTempAll()"  class="form-control" /> 
                                    	<label id="lblPayAmtError<%=i %>" style="color:Red"></label>
                                    </td>
                                </tr>
                                <%i++; %>
                               </s:iterator>
                               
                               <input type="hidden" value="<%=i%>" id="totalcount" />
                                 
                            </tbody>

                        </table>
                        
        </div>
  </div>
  <div id="Company_box">
    <div class="col-lg-12 col-md-12 col-xs-12 minheset" style="padding:0px;">
        	<table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                 	<th style="width:3%">Sr No</th>	
                                	<th style="width:7%">Invoice Date</th>
                                	<th style="width:6%">Product Return No</th>
                                    <th style="text-align:left;width:7%">Total Amount</th>
                                 </tr>
                            </thead>
                             <%i=0; %>
                            <tbody>
                                  <s:iterator value="returnAccountList">
                                    <tr>
                                        <td><%=++i %></td>
                                         <td><s:property value="date"/></td>
                                         <td><s:property value="grnreturnid"/></td>
                                         <td><s:property value="total"/></td>
                                    </tr>
                                  </s:iterator>
                                 
                                 
                            </tbody>

                        </table>
                        
        </div>
  </div>
  </div>
</form>
        
        
        
        
        
        
        
        
        
        <div id="newidvd">
        
         <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 5px 5px 5px 5px;font-size: 20px;color: green;background-color: rgba(239, 239, 239, 0.51);">
	        <div class="col-lg-9 col-md-9 text-right">
	        	
	        	<label style="padding-top: 3px;" class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox" id="refundcheck" name="refundcheck" onclick="setTempAll()"  /><i></i></label>
	        </div>
	        <div class="col-lg-2 col-md-2" style="padding:0px;">
	       			   <span>Returned Amount :</span>
	       			</div>
	        
	        <div class="col-lg-1 col-md-1" style="padding:0px;">
	        	<!-- <input type="text" id="alltotal" style="text-align: right;color:green" class="form-control"/> -->
	        	<s:textfield readonly="true" name="sumofreturn" id="sumofreturn" cssStyle="text-align: right;color:green"  cssClass="form-control" ></s:textfield>
	        </div>
        </div>
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 5px 5px 5px 5px;font-size: 20px;color: green;background-color: rgba(239, 239, 239, 0.51);">
	        <div class="col-lg-11 col-md-11 text-right">
	        	<span>Total Amount :</span>
	        </div>
	        <div class="col-lg-1 col-md-1" style="padding:0px;">
	        	<input type="text" id="alltotal" name="total_amt" readonly="readonly" style="text-align: right;color:green" class="form-control"/>
	        </div>
        </div>
                    	
                    	
                    	
                    	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                    			<div class="form-group" style="margin-top: 22px;" id="salebutton">
								   	<!-- <input name="ctl00$CPHcontent$btnPayment" value="Complete Payment" id="CPHcontent_btnPayment" class="btn btn-primary pull-right" type="submit"> -->
									<a href="#" class="btn btn-primary pull-right"  onclick="return isValid()">Complete Payment</a>
								</div>
                    	</div>
                    
           </div>    
           </div>     
                    <div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;" id="pay_hist">
        
        <h4>Payment History</h4>
        
     				<table class="table table-bordered table-striped" cellspacing="0" width="100%" id="example" style="margin-bottom: 0px;">
                         <thead>
                             <tr>
                             	<th>Cheque Maker</th>
                             	<!-- <th>Invoice No</th> -->
                             	<th>Payment Id</th>
                             	<th>Pay.Date</th>
                             	<th>Supplier Name</th>
                                 <th>Payment Type</th>
                                 <th>Cheque No</th>
                                 <th>Cheque Type</th>
                                 <th>Handover To</th>
                                 <th>Paid Amt.</th>
                                 <th>Return Amt. Settled</th>
                                 <th style="text-align:right;">Total Paid Amt.</th>
                                 <th style="text-align:right;">Print</th>
                             </tr>
                         </thead>
                         <tbody>
                         	<s:iterator value="payrecieptList">
                             <tr>
                             	<td><s:property value="cheq_receiver"/></td>
                             	<td><s:property value="parentid"/></td>
                             	<%-- <td><s:property value="voucherno"/></td> --%>
                             	<td><s:property value="paymentDate"/></td>
                                 <td><s:property value="vendor"/></td>
                                 <td><s:property value="paymentType"/></td>
                                 <td><s:property value="cheqNo"/></td>
                                 <td><s:property value="cheqType"/></td>
                                 <td><s:property value="handoverTo"/>  </td>
                                 <td><s:property value="paidpayment"/>  </td>
                                 <td><s:property value="returnpayment"/></td>
                                 <td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="paymentAmount"/></td>
                                 <td class="text-right"><a href="#" onclick="openPopup('printpaymentProcurement?payid=<s:property value="parentid"/>')"><i class="fa fa-print"></i></a></td>
                             </tr>
                             </s:iterator>
                         </tbody>

                     </table>
       
        		
        
        	
        </div>
                        
                    </div>
                </div>


            </div>

        </section>
        <!--/ CONTENT -->

	</s:form>






    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="editcat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Edit</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Product ID :</label>
                                <div class="col-sm-9">
                                    2182
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Section :</label>
                                <div class="col-sm-9">
                                    <select id="ddlSection" class="PopDDl" onchange="getCategory();"><option value="11">OT Equipment</option><option value="12">Other</option><option value="13">Medical Equipment</option><option value="14">Baby Care</option><option value="15">Healthy Wealthy</option><option value="16">Ready to Eat</option><option value="17">Cloth Care</option><option value="20">Furniture</option><option value="21">Medicine</option><option value="23">Exclusive Store1</option><option value="24">Exclusive2</option><option value="25">Exclusive2</option><option value="26">Exclusive3</option><option value="27">person care2</option><option value="28">ssssssss</option><option value="29">test</option><option value="30"></option><option value="31"></option><option value="32"></option><option value="33">Other</option></select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Category :</label>
                                <div class="col-sm-9">
                                    <select id="ddlCatagory" class="PopDDl"><option value="4">Hospital Bed-ICU</option><option value="6">Hospital Bed-Fowler</option><option value="7">Hospital Bed-Plain</option><option value="8">Hospital Bed-Semi-Flower</option><option value="9">Obstetric Tables</option><option value="10">Trolly</option><option value="11">Stretchers</option><option value="12">Doctor Chair &amp; Stools</option><option value="209">Baby Crib</option><option value="210">Beds - Orthopaedic</option><option value="211">Beds Mattress</option><option value="220">STRETCHER TROLLEY</option><option value="221">INSTRUMENT TROLLEY</option><option value="222">EXAMINATION COUCH</option><option value="223">WHEEL CHAIR</option><option value="224">Medical Cabinets Cupboards</option><option value="225">Waiting Chairs &amp; Benches</option><option value="226">Office Conference, Coffee Tables</option></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Brand :</label>
                                <div class="col-sm-9">
                                    <select name="ctl00$CPHcontent$ddlPopUpBrads" id="CPHcontent_ddlPopUpBrads" style="width:200px;">
                                        <option value="133">Sun Pharma</option>
                                        <option value="134">Narang</option>
                                        <option value="135">PHCD</option>
                                        <option value="137">Bajaj</option>
                                        <option value="138">Philips</option>
                                        <option value="139">ATICO</option>
                                        <option value="141">SONOSITE</option>
                                        <option value="158">TOSHIBA</option>
                                        <option value="161">Assure</option>
                                        <option value="195">COVIDIEN</option>
                                        <option value="199">ConvaTec</option>
                                        <option value="202">Medtronic</option>
                                        <option value="204">Metrex</option>
                                        <option value="1206">NIPRO</option>
                                        <option value="1207">Prevail</option>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Name :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Infant Bed / Child Cot / Baby Bassinet">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">MRP :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 18000">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Purchase Price :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 14200">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">Sale Price :</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="inputEmail3" value="Rs. 15000">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Update</button>
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
    
    <script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

	<script>
				             $(function() {
								  $('.minheset').slimScroll({
								   		height : '380px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>
    
    	<script>
     $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'pdf', 'colvis' ]
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>
    
 




    
<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
     
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
   <%--  <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script> --%>
    
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
     
     <script>
	$(document).ready(function(){
	  $("input[name$='type']").click(function(){
	  var value = $(this).val();
	  if(value=='Individual') {
	    $("#Individual_box").show();
	     $("#Company_box").hide();
	  }
	  else if(value=='Company') {
	   $("#Company_box").show();
	    $("#Individual_box").hide();
	   }
	  });
	  $("#Individual_box").show();
	  $("#Company_box").hide();
	});
	
	function hidediv(){
		var x=document.getElementById("megaidvd").className;
		if(x==''){
			document.getElementById("megaidvd").className='hidden';
			document.getElementById("newidvd").className='hidden';
			document.getElementById("pay_hist").className='col-lg-12 col-md-12 col-xs-12 ';
			document.getElementById("btnhistory").innerHTML='Payment';	
		}else{
			document.getElementById("megaidvd").className='';
			document.getElementById("newidvd").className='';
			document.getElementById("pay_hist").className='col-lg-12 col-md-12 col-xs-12 hidden';
			document.getElementById("btnhistory").innerHTML='Payment History';	
		}
	}
</script>		
     <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
     
     
</body>
</html>
