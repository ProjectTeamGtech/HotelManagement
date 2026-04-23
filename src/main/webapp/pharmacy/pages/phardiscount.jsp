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
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		document.addEventListener("contextmenu", function(e){
    		e.preventDefault();
    		}, false); 
		
		
	});
    
    </SCRIPT>
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
     <script type="text/javascript" src="common/js/pagination.js"></script>
     <script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
   
</head>
<body>

<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
	<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
									<!-- <img src="dashboardicon/Approval.png" class="img-responsive prescripiconcircle"> -->
									<img src="manasmaindashboard/images/Discount_Icon.svg" style="filter: brightness(5);width: 21%;margin-top: 5px;margin-left: 7px;">
								</div>
								<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
								<h4>Pharmacy Discount Dashboard </h4>
								</div>
								</div>
							</div>
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
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
                       <div class="col-md-12">
                          <div class="form-inline">
						  	<s:form action="phardiscountPharmacy" theme="simple">
						  		<div class="form-group hidden">
							         <span class="text-uppercase"><b style="font-weight: 900;">Pharmacy Discount Dashboard</b> &nbsp; - &nbsp;</span>
							    </div>
							    <div class="form-group" style="width: 15%;">
									<s:textfield name="searchText" id="searchText" cssClass="form-control" style="width:100%;" placeholder="Search Patient / ID / Bill No"/>
								</div>
						  		<div class="form-group" style="width: 7%;">
						  			<s:textfield id="fromdate" readonly="true" name="fromdate"  cssClass="form-control" placeholder="From Date" style="width:100%;"/>
						  		</div>
						  
						  		<div class="form-group" style="width: 7%;">
						  			<s:textfield readonly="true"  name="todate" id="todate"  cssClass="form-control" placeholder="To Date" style="width:100%;"/>
						  		</div>
						  		
						  		<div class="form-group">
						  		<button type="submit" class="btn btn-primary">Go</button>
						  		</div>
						  		<div class="form-group" style="float: right;">
						  			<% if(loginInfo.isRefund_dashboard()) {%>	<a href="#" class="btn btn-primary" onclick="approvedDiscountAll()">Approve All</a><%} %>
						  		</div>
						  	<!-- <div class="form-inline" style="float: right;margin-top: 5px;text-transform: uppercase;">
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#ffa3a3"></i> Request
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:rgb(72, 204, 184);"></i> Approved
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#7fcc80"></i> Paid
											    </label>
											  </div>
							</div> -->
						  	<input type="button" onclick="printReport()" value="Download Excel" class="btn btn-warning hidden" />
						   </s:form>
						   </div>
						  
                       </div>
                    </div> 
                    <div class="">
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr>
                                	<th style="width: 5%;"><input type="checkbox" onclick="selectallapprovedisc(this)"></th>
                                	<th style="width: 5%;">Sr. No</th>
                                	<th style="width: 10%;">Bill no.</th>
                                	<th style="width: 15%;">Patient Name</th>
                                	<th style="width: 10%;">Request Date</th>
                                    <th style="width: 10%;">Requested By</th>
                                    <th style="width: 10%;">Approved By</th>
                                    <th style="width: 10%;">Bill Amount</th>
                                   	<th style="width: 8%;">Disc. Type</th>
                                   	<th style="width: 10%;">Disc.</th>
                                   	<th style="width: 8%;">Action</th>
                                   	<th style="width: 5%;">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%int i=1; %>
                              <s:iterator value="discountList">
              					<tr>
              						<td>
              							<s:if test="status==0">
              								<input type="checkbox" class="aprovealldiscclass" value="<s:property value="id" />">
              								<input type="hidden" id="disc_bill_idno<s:property value="id" />" value="<s:property value="billno" />">
              							</s:if>
              						</td>
              						<td><%=i%></td>
              						<td><s:property value="billno" /></td>
              						
              						<td><s:property value="clientname" /></td>
              						
              						<td><s:property value="date" /></td>
              						<td><s:property value="requestuserid" /></td>
              						<td><s:property value="approve_userid" /></td>
              						<td><s:property value="totalamt" /></td>
              						<td>
              							<s:if test="disc_type==0">
              								%
              							</s:if>
              							<s:else>
              								Rs.
              							</s:else>
              						</td>
              						<td>
              							<s:if test="disc_type==0">
              								<s:property value="discount" />%
              							</s:if>
              							<s:else>
              								Rs.<s:property value="discount" />
              							</s:else>	
              						</td>
              						<td>
              							<s:if test="disc_delete==1">
              								-
              							</s:if>
              							<s:else>
	              							<s:if test="status==0">
	              							<% if(loginInfo.isRefund_dashboard() || loginInfo.getUserType()==2) {%>
	              								<a href="#" class="btn btn-primary" onclick="approvedPharmacyDiscount(<s:property value="id" />,<s:property value="billno" />)">Approve</a>
	              							<%} %>
	              									
	              							</s:if>
	              							<s:elseif test="status==1">
	              								Applied
	              							</s:elseif>
	              							<s:elseif test="status==2">
	              								Direct Applied
	              							</s:elseif>
              							</s:else>
              							
              						</td>
              						<td style="text-align: center;">
              							<s:if test="disc_delete==1">
              								Deleted
              							</s:if>
              							<s:else>
              								<% if(loginInfo.isRefund_dashboard()) {%>
              								<s:if test="status==0">
              									<a href="#" style="color: #d9534f;" onclick="deletePharDiscRequest(<s:property value="billno"/>,<s:property value="id"/>)"> <i class="fa fa-times" aria-hidden="true" style="color: #d9534f;"></i></a>
              								</s:if>
              								<%} %>
              							</s:else>
              						</td>
              						
              					</tr>
              					<%i++; %>
              				</s:iterator>
                            </tbody>
                        </table>
                    </div>
	</div>
	
</div>

<!-- Adarsh for Approved notes -->
<!-- Modal -->

<div id="approvedmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        
        <h4 class="modal-title">Are You Sure To Approve?</h4>
      </div>
      <div class="modal-body">
      <form action="aprovedbilldiscountPharmacy" method="post" id="discountmodelform">
      		<input type="hidden" name="billno" id="approvebillno">
      		<input type="hidden" name="discinper" id="discinper">
      		<input type="hidden" id="approveddiscount_id" name="id">
      		<input type="hidden" id="isgroupdiscount" name="isgroupdiscount"></input>
      		<input type="hidden" id="aprovediscids" name="aprovediscids"></input>
      		<s:hidden name="searchText"></s:hidden>
      		<s:hidden name="fromdate"></s:hidden>
      		<s:hidden name="todate"></s:hidden>
      		
        	<textarea rows="3"  class="form-control" id="approve_notes" name="approve_notes" placeholder="Approve Note" style="background-color: beige;"></textarea>
     </form>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger"  onclick="approvedDiscountBill()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>
<!-- ----------- -->

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
        <input type="button" class="btn btn-danger" onclick="deleteRefundRequest1()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>

<!-- Modal for deleteing discount lokesh -->
<div id="deletedisc" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Delete?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="deleteid">
      		<input type="hidden" id="invid">
        	<textarea rows="3"  class="form-control" id="deldel" placeholder="Cancel Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="deletediscount()" data-dismiss="modal" value="Ok">
      </div>
    </div>

  </div>
</div>

<!-- Cart Modal -->
<div id="cart3" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">REFUND REQUEST NOTE</h4>
			</div>
			<div class="modal-body">
				<div id="page_printer3">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="hospitaltitlediv3">

							</div>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								<h4>
									<b>REFUND REQUEST NOTE</b>
								</h4>
							</div>
							<s:hidden name="parentid" id="parentid23"></s:hidden>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
								style="padding: 0px; margin-bottom: 15px; text-transform: uppercase;">
								<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									<p style="margin: 0 0 2.5px;">
										<b>Request Number : </b><span id="issueno3"></span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b>Requested From : </b><span id="fromlocation3">Medical Store</span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b>Indent Number : </b><span id="indentno3"></span>
									</p>
								</div>
								<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									<p style="margin: 0 0 2.5px;">
										<b>Issue Date : </b><span id="issuedate3">02-07-2017 18:45</span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b> </b><span id="tolocation3">ESIC Pharmacy</span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b>Request Date : </b><span id="requestdate3">02-07-17 19:46</span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b>Request User : </b><span id="requestuser3">   </span>
									</p>
								</div>
							</div>
							<div></div>
						</div>
						<div>
							<table class="table" id="mytable" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 4%;">Sr.no</th>
										<th style="width: 20%;">Name</th>
										<th style="width: 27%;">Charge</th>
									</tr>
								</thead>
								<tbody id="tbodyid3">

								</tbody>
							</table>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12"
							style="padding: 0px; margin-top: 30px;">
							<div class="col-lg-8 col-md-8 col-xs-6">
								<p style="margin: 0px;" id="username3"></p>
								<p style="margin: 0px;" id="userdatetime3"></p>
								<p>Issued By</p>
							</div>
							<div class="col-lg-4 col-md-4 col-xs-6 text-right">
								<p class="hidden" style="margin: 0px;">Ajay Air</p>
								<p class="hidden" style="margin: 0px;">Ajay Air</p>
								<p id="receivedbyid">Received By</p>
								<div id="noteTextBox">
									<s:textarea id="notes" cssClass="form-control" name="notes"
										theme="simple" placeholder="Write Note"></s:textarea>
								</div>

							</div>
						</div>

					</div>

				</div>
				<div class="modal-footer" id="btndiv">
					
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
function deletepopup(id,invid){
	document.getElementById("deleteid").value=id;
	document.getElementById("invid").value=invid;
	$('#deletedisc').modal( "show" );
}
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
	
	


</body>
</html>