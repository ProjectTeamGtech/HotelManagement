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

.hola{
height: 450px!important;
overflow: scroll;
}
#multieditapporvetbody{

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
		
		     document.addEventListener("contextmenu", function(e){
	    		e.preventDefault();
	    		}, false);      
		    
	});
    
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
<body id='demo-element' >

<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;" >
		 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
							<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Multiple Adjustment And Edit Dashboard </h4>
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
						  	<s:form action="multipleadjusteditdashboardProduct" theme="simple">
						  		
						  		<div class="form-group" style="width: 7%;">
						  			<s:textfield id="fromdate" name="fromdate" readonly="true"  cssClass="form-control" placeholder="From Date" style="width:100%;"/>
						  		</div>
						  
						  		<div class="form-group" style="width: 7%;">
						  			<s:textfield  name="todate" id="todate" readonly="true"  cssClass="form-control" placeholder="To Date" style="width:100%;"/>
						  		</div>
						  		<div class="form-group">
						  			<button type="submit" class="btn btn-primary">Go</button>
						  		</div>
						  		<div class="form-group" style="float: right;">
						  			<%if(loginInfo.isAdjustment_approve() || loginInfo.getSuperadminid()==1){ %>	
						  				<a href="#" class="btn btn-primary" onclick="approveAdjustEditAll()">Approve All</a>
						  			<%} %>
						  		</div>		
						  	
						   </s:form>
						   </div>
						  
                       </div>
                    </div> 
                   
                    <div class="">
                        <table class="table table-responsive" style="width: 100%;text-transform: uppercase;">
                            <thead>
                                <tr>
                                	<th style="width: 1%;">SR.</th>
                                	<th style="width: 1%;"><input type="checkbox" onclick="selectallapproveadjustedit(this)"></th>
                                	<th style="width: 3%;">ID</th>
                                	<th style="width: 9%;">Request Date</th>
                                    <th style="width: 13%;">Requested By</th>
                                    <th style="width: 8%;">Req. Dept</th>
                                    <th style="width: 15%;">Req. Type</th>
                                   	<th style="text-align: center;width: 10%;">Admin A/R Date</th>
                                   	<th style="text-align: center;width: 10%;">Admin A/R Userid</th>
                                	<th style="text-align:center;width: 8%;">Action</th>
                                	<th style="text-align:center;width: 1%;">Print</th>
                                	<th style="text-align:center;width: 1%;">Delete</th>
                                </tr>
                            </thead>
                           
                            <tbody>
                            	<%int transfercount=0; %>
                              <s:iterator value="parenttransferlist">
              				  <tr>
                              		<td><%=(++transfercount) %></td>
                              		<td>
                              			<s:if test="status==2">
	             					  		<!-- Adjustment Request -->
	             					  		<s:if test="request==0">
	             					  			<input type="checkbox" class="aproveallapproveditclass" value="<s:property value="id" />">
	                              			</s:if>
	              					   	</s:if>
	              					   	<s:elseif test="status==3">
	              					   		<!-- Edit Request -->
	              					   		<s:if test="request==0">
	             					  			<input type="checkbox" class="aproveallapproveditclass" value="<s:property value="id" />">
	                              			</s:if>
	              					   	</s:elseif>
                              		</td>
                              		<td><s:property value="id"/></td>
              						<td><s:property value="dateTime"/></td>
              						<td style="color: chocolate;"><s:property value="userid"/></td>
              						<td><s:property value="locationName"/></td>
              						
	              					   	<s:if test="status==0">
	              					   		<td style="text-align:center;background-color: rgb(72, 204, 184);">DIRECT ADJUSTMENT</td>
	              					   	</s:if>
	              					   	<s:elseif test="status==2">
	              					   		<td style="text-align:center;background-color: rgb(72, 204, 184);">REQUEST ADJUSTMENT</td>
	              					   	</s:elseif>
	              					   	<s:elseif test="status==1">
	              					   		<td style="text-align:center;background-color: rgba(229, 217, 129, 0.46);">DIRECT EDIT</td>
	              					   	</s:elseif>
	              					   	<s:else>
	              					   		<td style="text-align:center;background-color: rgba(229, 217, 129, 0.46);">REQUEST EDIT</td>
	              					   	</s:else>
              					   	
              					  	<td style="text-align: center;"><s:property value="approve_date"/></td>
              					  	<td><s:property value="admin_approve_userid"/></td>
             					  	<s:if test="status==2">
             					  		<!-- Adjustment Request -->
             					  		<s:if test="request==0">
             					  			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showMuliAdjustEdit(<s:property value="id"/>)">Request</a></td>
              					   		</s:if>
              					   		<s:elseif test="request==2">
              					   			<td style="text-align:center;background-color: rgba(255, 0, 0, 0.51);">Rejected</td>
              					   		</s:elseif>
             					  		<s:else>
             					  			<td style="text-align:center;background-color: #7fcc80;;">Applied</td>
              					   		</s:else>
              					   	</s:if>
              					   	<s:elseif test="status==3">
              					   		<!-- Edit Request -->
              					   		<s:if test="request==0">
             					  			<td style="text-align:center;background-color: rgba(255, 163, 163, 0.93);"><a href="#" style="color:#fff;" data-toggle="modal" onclick="showMuliEditAprove(<s:property value="id"/>)">Request</a></td>
              					   		</s:if>
              					   		<s:elseif test="request==2">
              					   			<td style="text-align:center;background-color: rgba(255, 0, 0, 0.51);">Rejected</td>
              					   		</s:elseif>
             					  		<s:else>
             					  			<td style="text-align:center;background-color: #7fcc80;;">Applied</td>
              					   		</s:else>
              					   	</s:elseif>
              					   	<s:else>
              					   		<td style="text-align:center;background-color: #7fcc80;;">Direct Applied</td>
              					   	</s:else>
              					   	
              					   	<!-- Print td -->
              					   	<td style="text-align:center;"><a href="#" onclick="openSamePopup('multiadjustmentprintProduct?id=<s:property value="id" />')"><i class="fa fa-print" aria-hidden="true"></i></a></td>
              					   	
              					   	<!-- Delete td -->
              					   	<s:if test="status==2">
             					  		<s:if test="request==0">
             					  			<td style="text-align:center;"><a href="#" onclick="deleteadjustmentrequset(<s:property value="id" />)"><i class="fa fa-trash" aria-hidden="true" style="color: red;"></i></a></td>
              					   		</s:if>
              					   		<s:else>
              					   			<td ></td>
              					   		</s:else>
              					   	</s:if>
              					   	<s:elseif test="status==3">
              					   		<s:if test="request==0">
             					  			<td style="text-align:center;"><a href="#" onclick="deleteadjustmentrequset(<s:property value="id" />)"><i class="fa fa-trash" aria-hidden="true" style="color: red;"></i></a></td>
              					   		</s:if>
              					   		<s:else>
              					   			<td ></td>
              					   		</s:else>
              					   	</s:elseif>
              					   	<s:else>
              					   		<td ></td>
              					   	</s:else>
              					</tr>
              				</s:iterator>
                            </tbody>
                        </table>
                    </div>
	</div>
	
	 <s:form action="multipleadjusteditdashboardProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
							     <s:hidden name="fromdate"></s:hidden>
							     <s:hidden name="todate"></s:hidden>
							     <s:hidden name="searchtext"></s:hidden>
							    
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



<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
	
	<!-- Multi Adjustment Modal -->
<div id="multiadjustmentmodel" class="modal fade"  role="dialog">
  <div class="modal-dialog modal-lg" style="width: 95%;">

    <!-- Modal content-->
    <div class="modal-content hola">
   <s:form action="updatemultiadjustmentProduct" theme="simple" id="multiadjustmentform"> 
   <input type="hidden" id="aproveadjustid" name="id"> 
    <s:hidden name="fromdate"></s:hidden>
	<s:hidden name="todate"></s:hidden>
	 <div class="modal-header">
	      <button type="button" class="close" data-dismiss="modal">&times;</button>
	      <h4 class="modal-title">Multiple Adjustment Process</h4>
      </div>
      <div class="modal-body">
        <div class="">
        	 <div style="color: red;">
        		<p><b>1.&nbsp;Excess Product Adjustment: If product are in excess then reduce stock.
        		&nbsp;2.&nbsp;Shortage Product Adjustment: If product are in shortage then add stock.</b></p>
        		<p><b>3.&nbsp;Defective Product Adjustment: If product are in defective then reduce stock.
        		&nbsp;4.&nbsp;Expired/Dead Product Adjustment: If product are in expired/dead then reduce stock.</b></p>
	         </div>
	         <div >
        		 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-bottom: 15px;text-transform: uppercase;">
		        			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
		        				<p style="margin: 0 0 2.5px;"><b>Number : </b><span id="issueno"></span></p>
			        			<p style="margin: 0 0 2.5px;"><b>Location : </b><span id="fromlocation"></span></p>
			        	</div>
			        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
			        		<p style="margin: 0 0 2.5px;"><b>Requested Date : </b><span id="issuedate"></span></p>
			        		<p style="margin: 0 0 2.5px;"><b>Requested Userid : </b><span id="touserid"></span></p>
			        		
			            </div>
        	    </div>
	         </div>
         <table class="table my-table xlstable table-striped table-bordered" id="multiadjusttabletrcount" style="width:100%;">
          <thead>
	         	<tr>
		            <th style="width: 4%;">Sr.no</th>
		            <th style="width: 5%;">HSN Code</th>
		            <th style="width: 22%;">Product Name</th>
		            <th style="width: 5%;">Batch No</th>
		            <th style="width: 6%;">Exp Date</th>
		            <th style="width: 3%;">Current Stock</th>
		            <th style="width: 10%;">Adjustment Type</th>
		            <th style="width: 3%;">Before Qty</th>
		            <th style="width: 3%;">Change Qty</th>
		            <th style="width: 3%;">After Qty</th>
		            <th style="width: 14%;">Req. Remark</th>
		            <th style="width: 21%;">Remark</th>
	           	</tr>
          </thead>
          <tbody id="multiadjustmenttbody">
           
          </tbody>
          </table>
          <input type="hidden" name="tcount" id="multiadjustcount">
        </div>
        
      </div>
      
      <div class="modal-footer">
   		<%if(loginInfo.isAdjustment_approve() || loginInfo.getSuperadminid()==1){ %>
      		<button type="button" class="btn btn-primary" onclick="approvemultiadjustment()">Approve Adjustment</button>
      		<button type="button" class="btn btn-danger" onclick="rejectmultiadjustment()">Reject Adjustment</button>
     	<%} %>
      </div>
      
      </s:form>
    </div>

  </div>
</div>


	<!-- Multi Adjustment Modal -->
<div id="multiediapprovetmodel" class="modal fade"  role="dialog">
  <div class="modal-dialog modal-lg" style="width: 95%;">

    <!-- Modal content-->
    <div class="modal-content hola" >
   <s:form action="updatemultieditappoveProduct" theme="simple" id="multieditform"> 
   <input type="hidden" id="aprovemultieditid" name="id"> 
   	<s:hidden name="fromdate"></s:hidden>
	<s:hidden name="todate"></s:hidden>
	 <div class="modal-header">
	      <button type="button" class="close" data-dismiss="modal">&times;</button>
	      <h4 class="modal-title">Multiple Edit Process</h4>
      </div>
      <div class="modal-body">
        <div class="">
        	 
	         <div >
        		 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-bottom: 15px;text-transform: uppercase;">
		        			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
		        				<p style="margin: 0 0 2.5px;"><b>Number : </b><span id="multiedit_issueno"></span></p>
			        			<p style="margin: 0 0 2.5px;"><b>Location : </b><span id="multiedit_fromlocation"></span></p>
			        	</div>
			        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
			        		<p style="margin: 0 0 2.5px;"><b>Requested Date : </b><span id="multiedit_issuedate"></span></p>
			        		<p style="margin: 0 0 2.5px;"><b>Requested Userid : </b><span id="multiedit_touserid"></span></p>
			        		
			            </div>
        	    </div>
	         </div>
	         <div id='multieditapporvetbodyww'>
         	<table class="table my-table xlstable table-striped table-bordered"  style="width:100%;">
	          <thead>
		         	<tr>
			            <th style="width: 1%;">Sr.no</th>
			            <th style="width: 10%;">Product Name</th>
			            <th style="width: 2%;" class="hidden">Pre. Pack</th>
			            <th style="width: 2%;" class="hidden">Req. Pack</th>
			            <th style="width: 4%;">Pre. MRP</th>
			            <th style="width: 4%;">Req. MRP</th>
			            <th style="width: 4%;">Pre. Sale Price</th>
			            <th style="width: 4%;">Req. Sale Price</th>
			            <th style="width: 3%;">Pre. Pur. Price</th>
			            <th style="width: 3%;">Req. Pur. Price</th>
			            <th style="width: 4%;">Pre. Batch</th>
			            <th style="width: 4%;">Req. Batch</th>
			            <th style="width: 3%;">Pre. Expiry Date</th>
			            <th style="width: 3%;">Req. Expiry Date</th>
			            <th style="width: 3%;">Pre. GST</th>
			            <th style="width: 3%;">Req. GST</th>
			            <th style="width: 2%;">Pre. P.Shelf</th>
			            <th style="width: 2%;">Req. P.Shelf</th>
			            <th style="width: 2%;">Pre. S.Shelf</th>
			            <th style="width: 2%;">Req. S.Shelf</th>
			            <th style="width: 14%;">Req. Remark</th>
				        <th style="width: 21%;">Remark</th>
		           	</tr>
	          </thead>
	          
	          <tbody id="multieditapporvetbody">
	           
	          </tbody>
	          
          </table>
         </div>
        </div>
        
      </div>
      
      <div class="modal-footer">
      		<%if(loginInfo.isEdit_approve() || loginInfo.getSuperadminid()==1){ %>
	       		<button type="button" class="btn btn-primary" onclick="approvemultieditappove()">Approve Edit</button>
	       		<button type="button" class="btn btn-danger" onclick="rejectmultiedit()">Reject Edit</button>
       		 <%} %>
      </div>
      
      </s:form>
    </div>

  </div>
</div>

<!-- Modal -->
<div id="rejectmultiadjustment" class="modal fade" role="dialog">
	<s:hidden name="fromdate" id="reject_fromdate"></s:hidden>
	<s:hidden name="todate" id="reject_todate"></s:hidden>
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are you sure to reject?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" id="rejectadjustmentid">
        	<textarea rows="3"  class="form-control" id="rejectadjust_reason" placeholder="Reject Reason" style="background-color: beige;"></textarea>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="rejectmultipleadjsutment()"  value="Ok">
      </div>
    </div>

  </div>
</div>

<div id="approveallmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Approve?</h4>
      </div>
      <div class="modal-body">
	      <form action="multipleapproveadjusteditProduct" method="post" id="multipleapproveadjusteditform">
		      <input type="hidden" name="approverequestid" id="approverequestid">
		      <s:hidden name="fromdate"></s:hidden>
		      <s:hidden name="todate"></s:hidden>
		      <textarea rows="3"  class="form-control" id="approve_notes" name="approve_notes" placeholder="Approve Note" style="background-color: beige;"></textarea>
	      </form>
      </div>
      <div class="modal-footer">
        	<input type="button" class="btn btn-danger"  onclick="approvealladjusteditrequest()"  value="Ok">
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
	
	


</body>
</html>